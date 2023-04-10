package vassar.cmpu203.vassardiningapp.model;

import java.util.Set;

/** Represents a food item that could appear on multiple menus. */
public class MenuItem {

    private final String name;
    private final String description;
    private final Set<DietaryRestriction> dietaryRestrictions;

    public MenuItem(String name, String description, Set<DietaryRestriction> dietaryRestrictions) {
        this.name = name;
        this.description = description;
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }
}