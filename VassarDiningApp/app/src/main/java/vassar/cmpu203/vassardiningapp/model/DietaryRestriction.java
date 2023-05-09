package vassar.cmpu203.vassardiningapp.model;

import vassar.cmpu203.vassardiningapp.R;

/**
 * Represents dietary restrictions and relates them to symbols to be displayed on the menu.
 */
public enum DietaryRestriction {
    VEGETARIAN ("1", R.drawable.ic_vegetarian, "Vegetarian"),
    VEGAN ("4", R.drawable.ic_vegan, "Vegan"),
    GLUTEN_FREE ("9", R.drawable.ic_gluten, "Made without Gluten-Containing Ingredients"),
    BALANCE ("7", R.drawable.ic_balance, "In Balance"),
    HALAL ("10", R.drawable.ic_halal, "Halal"),
    KOSHER ("11", R.drawable.ic_kosher, "Kosher"),
    FARM ("6", R.drawable.ic_farm, "Farm to Fork"),
    SEAFOOD ("3", R.drawable.ic_seafood, "Seafood Watch"),
    HUMANE ("18", R.drawable.ic_humane, "Humane"),
    ORGANIC ("8", R.drawable.ic_organic, "Organic");

    private final String restrictionId;
    private final int iconId;
    private final String name;

    /**
     * Private enum constructor
     *
     * @param iconId the ID of the image to be displayed alongside the item
     */
    DietaryRestriction(String restrictionId, int iconId, String name) {
        this.restrictionId = restrictionId;
        this.iconId = iconId;
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public String getName() {
        return name;
    }

    public static DietaryRestriction getById(String id) {
        for (DietaryRestriction restriction : values()) {
            if (restriction.restrictionId.equals(id)) return restriction;
        }
        throw new RuntimeException("No known dietary restriction with that ID");
    }
}
