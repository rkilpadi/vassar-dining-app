import java.util.Set;

/** Represents the set of MenuItems at a given cafe at a given mealtime. */
public record Menu(String cafe, String mealtime, Set<MenuItem> menuItems) {

    /**
     * @param user the current user of the app
     * @return a textual representation of the Menu based on the user's preferences
     */
    public String toString(User user) {
        StringBuilder menuStr = new StringBuilder();
        for (MenuItem item : menuItems) {
            menuStr.append(item.name());
            if (user.getFavorites().contains(item)) {
                menuStr.append("*");
            }
            item.dietaryRestrictions().forEach(restriction -> menuStr.append(restriction.getSymbol()));
            if (!item.description().isBlank()) {
                menuStr.append(" - ").append(item.description());
            }
            menuStr.append("\n");
        }
        return menuStr.toString();
    }
}

