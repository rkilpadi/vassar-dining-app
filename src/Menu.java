import java.util.Set;

public class Menu {

    String cafe;
    String mealtime;
    Set<MenuItem> menuItems;

    public Menu(String cafe, String mealtime, Set<MenuItem> menuItems) {
        this.cafe = cafe;
        this.mealtime = mealtime;
        this.menuItems = menuItems;
    }

    public String toString() {
        StringBuilder menuStr = new StringBuilder();
        for (MenuItem item : menuItems) {
            menuStr.append(item.name).append(" - ").append(item.description).append("\n");
        }
        return menuStr.toString();
    }
}
