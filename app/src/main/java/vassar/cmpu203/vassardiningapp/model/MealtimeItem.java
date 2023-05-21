package vassar.cmpu203.vassardiningapp.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Set;

/** Represents a food item that could appear on multiple menus. */
public class MealtimeItem implements Serializable {

    private final String name;
    private final String id;
    private final String description;
    private final String station;
    private final Set<DietaryRestriction> dietaryRestrictions;

    public MealtimeItem(String name, String id, String description, String station, Set<DietaryRestriction> dietaryRestrictions) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.dietaryRestrictions = dietaryRestrictions;
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        return this.id.equals(((MealtimeItem) obj).id);
    }
}
