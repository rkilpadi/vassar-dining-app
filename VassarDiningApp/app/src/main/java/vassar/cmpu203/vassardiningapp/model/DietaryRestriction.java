package vassar.cmpu203.vassardiningapp.model;

import vassar.cmpu203.vassardiningapp.R;

/**
 * Represents dietary restrictions and relates them to symbols to be displayed on the menu.
 */
public enum DietaryRestriction {
    VEGETARIAN ("1", R.drawable.ic_vegetarian),
    VEGAN ("4", R.drawable.ic_vegan),
    GLUTEN_FREE ("9", R.drawable.ic_gluten),
    BALANCE ("7", R.drawable.ic_balance),
    HALAL ("10", R.drawable.ic_halal),
    KOSHER ("11", R.drawable.ic_kosher),
    FARM ("6", R.drawable.ic_farm),
    SEAFOOD ("3", R.drawable.ic_seafood),
    HUMANE ("18", R.drawable.ic_humane);

    private final String restrictionId;
    private final int iconId;

    /**
     * Private enum constructor
     *
     * @param iconId the ID of the image to be displayed alongside the item
     */
    DietaryRestriction(String restrictionId, int iconId) {
        this.restrictionId = restrictionId;
        this.iconId = iconId;
    }

    public int getIconId() {
        return iconId;
    }

    public static DietaryRestriction getById(String id) {
        for (DietaryRestriction restriction : values()) {
            if (restriction.restrictionId.equals(id)) return restriction;
        }
        throw new RuntimeException("No known dietary restriction with that ID");
    }
}
