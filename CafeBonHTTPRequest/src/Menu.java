package CafeBonHTTPRequest.src;/*
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    public String cafeName;
    public String date;
    public List<MenuItem> menuItems;



    public static List<MenuItem> process(String json, String mealtime) {
        Gson gson = new Gson();
        List<MenuItem> items = null;

        if (json != null && !json.isEmpty() && mealtime != null && !mealtime.isEmpty()) {
            Cafe cafe = gson.fromJson(json, Cafe.class);
            Mealtime mt = cafe.getMealtime(mealtime);

            if (mt != null) {
                items = new ArrayList<MenuItem>();
                for (String itemId : getItemIdsFromJson(json)) {
                    MenuItem mi = mt.getMenuItemById(itemId);
                    if (mi != null) {
                        items.add(mi);
                    }
                }
            }
        }

        return items;
    }

    private static List<String> getItemIdsFromJson(String json) {
        Gson gson = new Gson();
        List<Station> stations = gson.fromJson(json, new TypeToken<List<Station>>(){}.getType());

        List<String> itemIds = new ArrayList<String>();
        for (Station s : stations) {
            itemIds.addAll(s.getItems());
        }

        return itemIds;
    }

}


 */