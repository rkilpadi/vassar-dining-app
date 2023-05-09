package vassar.cmpu203.vassardiningapp.model;

import java.util.Set;

/** Represents a food item that could appear on multiple menus. */
public class MealtimeItem {

    private final String name;
    private final String description;
    private final String station;
    private final Set<DietaryRestriction> dietaryRestrictions;

    public MealtimeItem(String name, String description, String station, Set<DietaryRestriction> dietaryRestrictions) {
        this.name = name;
        this.description = description;
        this.dietaryRestrictions = dietaryRestrictions;
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStation() {
        return station;
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }
}
