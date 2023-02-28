public class Controller {

    private Menu currentMenu;

    public void findMenu(String cafe, String mealtime) {
        for (Menu menu : Data.getMenus()) {
            if (menu.cafe.equalsIgnoreCase(cafe) && menu.mealtime.equalsIgnoreCase(mealtime)) {
                setCurrentMenu(menu);
                return;
            }
        }
        throw new IllegalArgumentException("No menu with that cafe and mealtime");
    }

    public void setCurrentMenu(Menu menu) {
        this.currentMenu = menu;
    }

    public Menu getMenu() {
        return currentMenu;
    }

    public static void main(String[] args) {
        new TextUI(new Controller(), System.out, System.in).run();
    }
}
