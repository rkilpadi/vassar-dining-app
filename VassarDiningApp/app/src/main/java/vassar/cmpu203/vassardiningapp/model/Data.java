package vassar.cmpu203.vassardiningapp.model;

import java.util.ArrayList;
import java.util.List;

/** A mock database containing populated menus for testing */
public class Data {

    public static final ArrayList<MealtimeMenu> MENUS = new ArrayList<>();

    /** Populate the set of Menus */
    public static void populateMenus() {
        ArrayList<MealtimeItem> breakfastItems = new ArrayList<>();
        ArrayList<MealtimeItem> lunchItems = new ArrayList<>();
        MealtimeItem eggs = new MealtimeItem("eggs", "scrambled", new ArrayList<>(List.of(DietaryRestriction.VEGETARIAN)));

        breakfastItems.add(eggs);
        breakfastItems.add(new MealtimeItem("bacon", "crispy", new ArrayList<>()));
        lunchItems.add(eggs);
        lunchItems.add(new MealtimeItem("pasta", "", new ArrayList<>(List.of(DietaryRestriction.VEGETARIAN))));
        lunchItems.add(new MealtimeItem("salad", "", new ArrayList<>(List.of(DietaryRestriction.VEGAN, DietaryRestriction.VEGETARIAN))));

        MealtimeMenu breakfast = new MealtimeMenu("deece", "today", "breakfast", breakfastItems);
        MealtimeMenu lunch = new MealtimeMenu("deece", "today", "lunch", lunchItems);
        MealtimeMenu tmrBreakfast = new MealtimeMenu("deece", "tomorrow", "breakfast", lunchItems);

        MENUS.add(breakfast);
        MENUS.add(lunch);
        MENUS.add(tmrBreakfast);
    }

    /**
     * Search through all the menus to find one that matches the given cafe and mealtime.
     * If none is found, throw an error.
     *
     * @param cafe the name of the desired cafe
     * @param date the name of the desired mealtime
     * @return a menu that matches the cafe and mealtime, otherwise throw an error
     */
    public static List<MealtimeMenu> findMenus(String cafe, String date) {
        List<MealtimeMenu> menus = new ArrayList<>();
        for (MealtimeMenu menu : MENUS) {
            if (menu.getCafe().equalsIgnoreCase(cafe) && menu.getDate().equalsIgnoreCase(date)) {
                menus.add(menu);
            }
        }
        return menus;
    }
}
