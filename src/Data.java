import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Data {

    public static Set<Menu> getMenus() {
        Set<MenuItem> breakfastItems = new HashSet<>();
        Set<MenuItem> lunchItems = new HashSet<>();
        DietaryRestriction vegan, vegetarian;
        Set<Menu> menus = new HashSet<>();

        vegan = new DietaryRestriction("vegan", "");
        vegetarian = new DietaryRestriction("vegetarian", "");
        MenuItem eggs = new MenuItem("eggs", "scrambled", new HashSet<>(List.of(vegetarian)));

        breakfastItems.add(eggs);
        breakfastItems.add(new MenuItem("bacon", "crispy", new HashSet<>()));
        lunchItems.add(eggs);
        lunchItems.add(new MenuItem("pasta", "", new HashSet<>(List.of(vegetarian))));
        lunchItems.add(new MenuItem("salad", "", new HashSet<>(List.of(vegan))));

        Menu breakfast = new Menu("deece", "breakfast", breakfastItems);
        Menu lunch = new Menu("deece", "lunch", lunchItems);

        menus.add(breakfast);
        menus.add(lunch);

        return menus;
    }

    public static Set<String> getCafes() {
        return new HashSet<>(List.of("deece"));
    }

    public static Set<String> getMealtimes() {
        return new HashSet<>(Arrays.asList("breakfast", "lunch"));
    }
}

