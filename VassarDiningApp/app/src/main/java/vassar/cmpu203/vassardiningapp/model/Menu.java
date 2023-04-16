package vassar.cmpu203.vassardiningapp.model;

import java.util.List;

/** Represents the set of MenuItems at a given cafe at a given mealtime. */
public class Menu {

    private final String cafe;
    private final String mealtime;
    private final List<MenuItem> menuItems;

    public Menu(String cafe, String mealtime, List<MenuItem> menuItems) {
        this.cafe = cafe;
        this.mealtime = mealtime;
        this.menuItems = menuItems;
    }

    public String getCafe() {
        return cafe;
    }

    public String getMealtime() {
        return mealtime;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Menu &&
                ((Menu) other).cafe.equals(this.cafe) &&
                ((Menu) other).mealtime.equals(this.mealtime);
    }
}

