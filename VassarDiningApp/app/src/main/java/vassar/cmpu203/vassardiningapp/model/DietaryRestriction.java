package vassar.cmpu203.vassardiningapp.model;

import vassar.cmpu203.vassardiningapp.R;

/**
 * Represents dietary restrictions and relates them to symbols to be displayed on the menu.
 */
public enum DietaryRestriction {
    VEGETARIAN (R.drawable.vegetarian),
    VEGAN (R.drawable.vegan);

    private final int iconId;

    /**
     * Private enum constructor
     *
     * @param iconId the ID of the image to be displayed alongside the item
     */
    DietaryRestriction(int iconId) {
        this.iconId = iconId;
    }

    public int getIconId() {
        return iconId;
    }
}
