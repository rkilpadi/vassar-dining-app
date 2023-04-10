package vassar.cmpu203.vassardiningapp.model;

import java.util.Set;

/** Represents the set of MenuItems at a given cafe at a given mealtime. */
public class Menu {

    private final String cafe;
    private final String mealtime;
    private final Set<MenuItem> menuItems;

    public Menu(String cafe, String mealtime, Set<MenuItem> menuItems) {
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

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * @param user the current user of the app
     * @return a textual representation of the Menu based on the user's preferences
     */
    public String toString(User user) {
        StringBuilder menuStr = new StringBuilder();
        for (MenuItem item : menuItems) {
            menuStr.append(item.getName());
            if (user.getFavorites().contains(item)) {
                menuStr.append("*");
            }
            item.getDietaryRestrictions().forEach(restriction -> menuStr.append(restriction.getSymbol()));
            if (!item.getDescription().isEmpty()) {
                menuStr.append(" - ").append(item.getDescription());
            }
            menuStr.append("\n");
        }
        return menuStr.toString();
    }
}

