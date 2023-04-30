package vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

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

       /*if (cafeName.equals("gordon")){
            String deeceBreakfast = bamcoDayParts(urlBody, "1");
            String deeceLunch = bamcoDayParts(urlBody, "3");
            String deeceLightLunch =  bamcoDayParts(urlBody, "739");
            String deeceDinner= bamcoDayParts(urlBody, "4");
            String deeceLateNight = bamcoDayParts(urlBody, "7");
        } else if (cafeName.equals("the-retreat")) {
            String retreatBreakfast = bamcoDayParts(urlBody, "1");
            String retreatLunch = bamcoDayParts(urlBody, "3");
            String retreatDinner= bamcoDayParts(urlBody, "4");
        } else if (cafeName.equals("food-truck")) {
            String streetEatsDinner= bamcoDayParts(urlBody, "4");
        }
        */
        String numDayPart = "4";
        System.out.println("Bamco.dayparts['" + numDayPart + "'] = ");

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

        Map<String, MenuItem> menuItems = gson.fromJson(inputStreamReader, new TypeToken<Map<String, MenuItem>>(){}.getType());
        //Print MenuItem objects
        for (Map.Entry<String, MenuItem> entry : menuItems.entrySet()) {
            MenuItem item = entry.getValue();
            System.out.println(item.toString());
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

    private static String bamcoDayParts(String html, String numDayPart){
        String[] before = html.split("Bamco.dayparts['" + numDayPart + "'] = ");
                String[] after = before[1].split(";}");
        String mealTimeMenu = after[0];
        return mealTimeMenu;
    }

}
