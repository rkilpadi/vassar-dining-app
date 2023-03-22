/** Updates variable data based on user input */
public class Controller {
    
    private Menu currentMenu;
    private final User currentUser = new User();

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(String cafe, String mealtime) {
        this.currentMenu = Data.findMenu(cafe, mealtime);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        System.out.println("VASSAR DINING APP PROTOTYPE");
        Data.populateMenus();
        new TextUI(new Controller(), System.out, System.in).run();
    }
}
