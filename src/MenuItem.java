import java.util.Set;

public class MenuItem {

    String name;
    String description;
    Set<DietaryRestriction> dietaryRestrictions;
    boolean favoriteStatus;

    public MenuItem(String name, String description, Set<DietaryRestriction> dietaryRestrictions) {
        this.name = name;
        this.description = description;
        this.dietaryRestrictions = dietaryRestrictions;
        this.favoriteStatus = false;
    }
}
