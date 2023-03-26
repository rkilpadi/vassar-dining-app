import java.util.Set;

/** Represents a food item that could appear on multiple menus. */
public record MenuItem(String name, String description, Set<DietaryRestriction> dietaryRestrictions) {};
