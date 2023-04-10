package vassar.cmpu203.vassardiningapp.model;

/**
 * Represents dietary restrictions and relates them to symbols to be displayed on the menu.
 */
public enum DietaryRestriction {
    VEGETARIAN ("(V)"),
    VEGAN ("(VG)");

    private final String symbol;

    /**
     * Private enum constructor
     *
     * @param symbol a string to be displayed alongside the item
     */
    DietaryRestriction(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
