public class Controller {
    
    private Menu currentMenu;
    private final User currentUser = new User();

    public void findMenu(String cafe, String mealtime) {
        for (Menu menu : Data.getMenus()) {
            if (menu.cafe().equalsIgnoreCase(cafe) && menu.mealtime().equalsIgnoreCase(mealtime)) {
                setCurrentMenu(menu);
                return;
            }
        }
        throw new IllegalArgumentException("No menu with that cafe and mealtime");
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu menu) {
        this.currentMenu = menu;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        System.out.println("VASSAR DINING APP PROTOTYPE");
        new TextUI(new Controller(), System.out, System.in).run();
    }
}
