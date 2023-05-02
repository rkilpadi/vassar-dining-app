package CafeBonHTTPRequest.src;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuParser {
    
   private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String cafeName;

        do {
            System.out.println("Please enter the name of the cafe (food-truck, the-retreat, or gordon): ");
            cafeName = scanner.nextLine();
        } while (!isValidCafe(cafeName));
        String date;
        do {
            System.out.println("Please enter the date in yyyy-mm-dd format: ");
            date = scanner.nextLine();
        } while (!isValidDate(date));
        String url = "https://vassar.cafebonappetit.com/cafe/" + cafeName + "/" + date + "/";

        System.out.println("Sending GET request to " + url);
        String urlBody = sendGET(url);
        System.out.println("GET DONE");


        //Splitting html string to isolate Bamco.menu_items
        assert urlBody != null;
        String[] before = urlBody.split("Bamco.menu_items = ");
        String[] after = before[1].split("}}};");
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
        //Print MenuItem objects
        for (Map.Entry<String, MenuItem> entry : menuItems.entrySet()) {
            MenuItem item = entry.getValue();
            System.out.println(item.toString());
        }

        ArrayList<String> bamcoDayParts;
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
            return;
        }

        /*
        for (String mealTime : bamcoDayParts) {
            System.out.println(mealTime);
        }

         */


        // Loop over the bamcoDayParts ArrayList and skip over any strings that contain "No match found for"
        for (String bamcoDayPart : bamcoDayParts) {
            if (bamcoDayPart.contains("No match found for")) {
                continue;
            }

            //Pretty print the JSON from the website
           // try {
                JsonElement jsonMealTimeElement = new JsonParser().parse(bamcoDayPart);

                //Parse the JSON string as a Map of Café objects
                String mealTimeJson = gson.toJson(jsonMealTimeElement);
                //PrettyPrint CafeJSON
                //System.out.println(mealTimeJson);

                Cafe newCafe = gson.fromJson(mealTimeJson, Cafe.class);
                System.out.println(newCafe.toString());
                //Map<String, Cafe> cafes = gson.fromJson(removeQuotesAndUnescape(mealTimeJson), new TypeToken<Map<String, Cafe>>() {}.getType());

                // Create a Café object for each mealtime in the Café
                /*for (Mealtime mealtime : cafes.get("regular").getMealtimes()) {
                    Cafe cafe = new Cafe(cafeName, Collections.singletonList(mealtime));
                    System.out.println(cafe.toString());
                }
                 */
            //} catch (JsonSyntaxException e) {
                //System.out.println("Invalid JSON string: " + bamcoDayPart);
            //}
        }

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

    private String removeQuotesAndUnescape(String uncleanJson) {
        String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");

        return StringEscapeUtils.unescapeJava(noQuotes);
    }

}
