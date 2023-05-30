package vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuParser {

    private static final String USER_AGENT = "Mozilla/5.0";

    // Map<Sting, MenuItem>
    // return List<Menu> sorted by mealtime with the map
    public static List<Menu> MenuParserMethod(String cafeName, String date) throws IOException {
        //Scanner scanner = new Scanner(System.in);

            System.out.println("Please enter the name of the cafe (food-truck, the-retreat, or gordon): ");
            //cafeName = scanner.nextLine();

            System.out.println("Please enter the date in yyyy-mm-dd format: ");
            //date = scanner.nextLine();
        String url = "https://vassar.cafebonappetit.com/cafe/" + cafeName + "/" + date + "/";

        System.out.println("Sending GET request to " + url);
        String urlBody = sendGET(url);
        System.out.println("GET DONE");


        //Splitting html string to isolate Bamco.menu_items
        assert urlBody != null;
        String[] before = urlBody.split("Bamco.menu_items = ");
        String[] after = before[1].split("\\}\\}\\};");
        String menuItemsStr = after[0] + "}}}";

        //Finding mealtimes and menuItems contained within mealtimes
        //gordon[1: Breakfast, 3: Lunch, 739: Light Lunch, 4: Dinner, 7: Late Night]
        //the-retreat[1: Breakfast , 3: Lunch, 4: Dinner]
        //food-truck[4: Dinner]


        //Pretty print the JSON from the website
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        JsonElement jsonElement = new JsonParser().parse(menuItemsStr);
        String postJson = gson.toJson(jsonElement);
        //Print menu in PrettyPrint form
        //System.out.println(postJson);


        InputStream inputStream = new ByteArrayInputStream(postJson.getBytes(StandardCharsets.UTF_8));
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

        Map<String, MenuItem> menuItems = gson.fromJson(inputStreamReader, new TypeToken<Map<String, MenuItem>>() {
        }.getType());

        ArrayList<MenuItem> menuItemArrayList = new ArrayList<>();
        //Print MenuItem objects
        for (Map.Entry<String, MenuItem> entry : menuItems.entrySet()) {
            MenuItem item = entry.getValue();
            //ArrayList of menuItems
            menuItemArrayList.add(item);
            //Map of ID to menuItems
            Map<String, MenuItem> idMenuItem = new HashMap<>();
            idMenuItem.put(item.getId(), item);
            //System.out.println(item);
            //System.out.println(idMenuItem);
        }


        ArrayList<String> bamcoDayParts = null;
        if (cafeName.equals("gordon")) {
            ArrayList<String> deeceTime = new ArrayList<String>(Arrays.asList("1", "2", "3", "739", "4", "7"));
            bamcoDayParts = bamcoDayParts(urlBody, deeceTime);
        } else if (cafeName.equals("the-retreat")) {
            ArrayList<String> retreatTime = new ArrayList<String>(Arrays.asList("1", "3", "4"));
            bamcoDayParts = bamcoDayParts(urlBody, retreatTime);
        } else if (cafeName.equals("food-truck")) {
            ArrayList<String> streetEatsTime = new ArrayList<String>(Arrays.asList("4"));
            bamcoDayParts = bamcoDayParts(urlBody, streetEatsTime);
        } else {
            System.out.println("Invalid cafe name: " + cafeName);

        }



        ArrayList<Menu> resultList = new ArrayList<>();
        // Loop over the bamcoDayParts ArrayList and skip over any strings that contain "No match found for"
        for (String bamcoDayPart : bamcoDayParts) {
            if (bamcoDayPart.contains("No match found for")) {
                continue;
            }

            //Pretty print the JSON from the website
            JsonElement jsonMealTimeElement = new JsonParser().parse(bamcoDayPart);

            //Parse the JSON string as a Map of Café objects
            String mealTimeJson = gson.toJson(jsonMealTimeElement);
            //PrettyPrint CafeJSON
            //System.out.println(mealTimeJson);

            MenuRawData newMealtime = gson.fromJson(mealTimeJson, MenuRawData.class);

            Menu finalMenu = new Menu(cafeName, date);

            finalMenu.parseMealtimeRawData(newMealtime, menuItemArrayList);
            resultList.add(finalMenu);


        }
        return resultList;
    }



    private static String sendGET(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("GET request did not work.");
            return null;
        }
    }

    private static boolean isValidCafe(String cafeName) {
        String[] validCafes = {"food-truck", "the-retreat", "gordon"};
        for (String cafe : validCafes) {
            if (cafe.equals(cafeName)) {
                return true;
            }
        }
        System.out.println("Invalid cafe name. Please try again.");
        return false;
    }

    private static boolean isValidDate(String date) {
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
        if (!date.matches(datePattern)) {
            System.out.println("Invalid date format. Please try again.");
            return false;
        }
        return true;
    }

    private static ArrayList<String> bamcoDayParts(@NotNull String html, List<String> numDayParts) {
        ArrayList<String> bamcoMealTimeStringList = new ArrayList<>();
        for (String numDayPart : numDayParts) {
            String pattern = "Bamco\\.dayparts\\['" + Pattern.quote(numDayPart) + "'\\] = (.*?);";
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(html);
            if (m.find()) {
                String match = m.group(1);
                bamcoMealTimeStringList.add(match);
            } else {
                System.out.println("No match found for day-part: " + numDayPart );
            }
        }
        return bamcoMealTimeStringList;
    }
}
