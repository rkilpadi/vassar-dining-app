package vassar.cmpu203.vassardiningapp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a user, containing all data that is variable between different users, such as favorite items.
 */
public class User {

    private final Set<MealtimeItem> favorites = new HashSet<>();
    private Set<DietaryRestriction> dietaryRestrictions = new HashSet<>();
    private boolean favoriteFiltered;
    private boolean restrictionFiltered;

    public Set<MealtimeItem> getFavorites() {
        return favorites;
    }

    /**
     * Either favorites or unfavorites an item by adding or removing it from the favorites set.
     *
     * @param item the item to either add or remove from the set.
     */
    public <T> void switchStatus(T item, Set<T> set) {
        if (set.contains(item)) {
            set.remove(item);
        } else {
            set.add(item);
        }
    }

    public void setDietaryRestrictions(Set<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
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

    public List<MealtimeMenu> filterMenus(List<MealtimeMenu> items) {
        List<MealtimeMenu> filteredMenus = new ArrayList<>();
        for (MealtimeMenu mealtimeMenu : items) {
            List<MealtimeItem> visibleItems = new ArrayList<>();
            for (MealtimeItem mealtimeItem : mealtimeMenu.getMenuItems()) {
                boolean matchFavorite = !isFavoriteFiltered() || getFavorites().contains(mealtimeItem);
                boolean matchRestriction = !isRestrictionFiltered() || matchRestriction(mealtimeItem.getDietaryRestrictions());
                if (matchFavorite && matchRestriction) {
                    visibleItems.add(mealtimeItem);
                }
            }
            filteredMenus.add(new MealtimeMenu(mealtimeMenu.getCafe(), mealtimeMenu.getDate(), mealtimeMenu.getLabel(), visibleItems));
        }
        return filteredMenus;
    }
}
