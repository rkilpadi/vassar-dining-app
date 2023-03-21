import java.util.Set;

public record Menu(String cafe, String mealtime, Set<MenuItem> menuItems) {
    public String toString(Set<MenuItem> favorites) {
        StringBuilder menuStr = new StringBuilder();
        for (MenuItem item : menuItems) {
            menuStr.append(item.name());
            if (favorites.contains(item)) {
                menuStr.append("*");
            }
            item.dietaryRestrictions().forEach(restriction -> menuStr.append(restriction.toString()));
            if (!item.description().isBlank()) {
                menuStr.append(" - ").append(item.description());
            }
            menuStr.append("\n");
        }
        return menuStr.toString();
    }
}
