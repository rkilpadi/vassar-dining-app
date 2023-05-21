package vassar.cmpu203.vassardiningapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a user, containing all data that is variable between different users, such as favorite items.
 */
public class User implements Serializable {

    private final Map<String, MealtimeItem> favorites = new HashMap<>();
    private Set<DietaryRestriction> dietaryRestrictions = new HashSet<>();
    private boolean favoriteFiltered;
    private boolean restrictionFiltered;
    private LocalDate date;

    public Map<String, MealtimeItem> getFavorites() {
        return favorites;
    }

    /**
     * Either favorites or unfavorites an item by adding or removing it from the favorites map
     * @param item the item to either add or remove from the map
     */
    public void switchFavoriteStatus(MealtimeItem item) {
        if (favorites.containsKey(item.getId())) {
            favorites.remove(item.getId());
        } else {
            favorites.put(item.getId(), item);
        }
    }

    public void setDietaryRestrictions(Set<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    /**
     * Adds or removes a restriction from the restrictions set
     * @param restriction the restriction to either add or remove from the map
     */
    public void switchRestrictionStatus(DietaryRestriction restriction) {
        if (dietaryRestrictions.contains(restriction)) {
            dietaryRestrictions.remove(restriction);
        } else {
            dietaryRestrictions.add(restriction);
        }
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public boolean isFavoriteFiltered() {
        return favoriteFiltered;
    }

    public void toggleFavoriteFilter() {
        favoriteFiltered = !favoriteFiltered;
    }

    public boolean isRestrictionFiltered() {
        return restrictionFiltered;
    }

    public void toggleRestrictionFilter() {
        restrictionFiltered = !restrictionFiltered;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Checks whether an item's restrictions comply with this user's restrictions,
     * taking into account restrictions that are implied
     * @param restrictionsToMatch the item's restrictions to match
     * @return whether or not the item matches this user's restrictions
     */
    public boolean matchRestriction(Set<DietaryRestriction> restrictionsToMatch) {
        Set<DietaryRestriction> effectiveRestrictions = new HashSet<>(restrictionsToMatch);
        if (effectiveRestrictions.contains(DietaryRestriction.VEGAN)) {
            effectiveRestrictions.add(DietaryRestriction.KOSHER);
            effectiveRestrictions.add(DietaryRestriction.VEGETARIAN);
            effectiveRestrictions.add(DietaryRestriction.HALAL);
        } else if (effectiveRestrictions.contains(DietaryRestriction.VEGETARIAN)) {
            effectiveRestrictions.add(DietaryRestriction.HALAL);
        }
        return effectiveRestrictions.containsAll(dietaryRestrictions);
    }

    /**
     * Filter menus by items that are visible based on this user's preferences
     * @param menus the menus consisting of all items that are available
     * @return menus containing only items that are visible
     */
    public List<MealtimeMenu> filterMenus(List<MealtimeMenu> menus) {
        List<MealtimeMenu> filteredMenus = new ArrayList<>();
        for (MealtimeMenu menu : menus) {
            List<MealtimeItem> visibleItems = new ArrayList<>();
            for (MealtimeItem item : menu.getMenuItems()) {
                boolean matchFavorite = !isFavoriteFiltered() || favorites.containsKey(item.getId());
                boolean matchRestriction = !isRestrictionFiltered() || matchRestriction(item.getDietaryRestrictions());
                if (matchFavorite && matchRestriction) {
                    visibleItems.add(item);
                }
            }
            filteredMenus.add(new MealtimeMenu(menu.getCafe(), menu.getDate(), menu.getLabel(), visibleItems));
        }
        return filteredMenus;
    }
}
