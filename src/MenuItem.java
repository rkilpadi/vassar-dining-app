import java.util.Set;

public record MenuItem(String name, String description, Set<DietaryRestriction> dietaryRestrictions) {}
