import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** A mock database containing populated menus for testing */
public class Data {

    private static final Set<Menu> MENUS = new HashSet<>();

    /** Populate the set of Menus */
    public static void populateMenus() {
        Set<MenuItem> breakfastItems = new HashSet<>();
        Set<MenuItem> lunchItems = new HashSet<>();
        MenuItem eggs = new MenuItem("eggs", "scrambled", new HashSet<>(List.of(DietaryRestriction.VEGETARIAN)));

        breakfastItems.add(eggs);
        breakfastItems.add(new MenuItem("bacon", "crispy", new HashSet<>()));
        lunchItems.add(eggs);
        lunchItems.add(new MenuItem("pasta", "", new HashSet<>(List.of(DietaryRestriction.VEGETARIAN))));
        lunchItems.add(new MenuItem("salad", "", new HashSet<>(List.of(DietaryRestriction.VEGAN))));

        Menu breakfast = new Menu("deece", "breakfast", breakfastItems);
        Menu lunch = new Menu("deece", "lunch", lunchItems);

        MENUS.add(breakfast);
        MENUS.add(lunch);
    }

    /** @return a Set of valid cafes */
    public static Set<String> getCafes() {
        return new HashSet<>(List.of("deece"));
    }

    /** @return a Set of valid mealtimes */
    public static Set<String> getMealtimes() {
        return new HashSet<>(List.of("breakfast", "lunch"));
    }

    /**
     * Search through all the menus to find one that matches the given cafe and mealtime.
     * If none is found, throw an error.
     *
     * @param cafe the name of the desired cafe
     * @param mealtime the name of the desired mealtime
     * @return a menu that matches the cafe and mealtime, otherwise throw an error
     */
    public static Menu findMenu(String cafe, String mealtime) {
        for (Menu menu : MENUS) {
            if (menu.cafe().equalsIgnoreCase(cafe) && menu.mealtime().equalsIgnoreCase(mealtime)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("No menu with that cafe and mealtime");
    }
}
