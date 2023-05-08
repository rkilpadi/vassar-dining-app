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
    public void switchFavoriteStatus(MealtimeItem item) {
        if (this.favorites.contains(item)) {
            this.favorites.remove(item);
        } else {
            this.favorites.add(item);
        }
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(Set<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
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

    public List<MealtimeMenu> filterMenus(List<MealtimeMenu> items) {
        List<MealtimeMenu> filteredMenus = new ArrayList<>();
        for (MealtimeMenu mealtimeMenu : items)  {
            List<MealtimeItem> visibleItems = new ArrayList<>();
            for (MealtimeItem mealtimeItem : mealtimeMenu.getMenuItems()) {
                boolean matchFavorite = getFavorites().contains(mealtimeItem);
                boolean matchRestriction = mealtimeItem.getDietaryRestrictions().containsAll(getDietaryRestrictions());
                if ((!isFavoriteFiltered() || matchFavorite) && (!isRestrictionFiltered() || matchRestriction)) {
                    visibleItems.add(mealtimeItem);
                }
            }
            filteredMenus.add(new MealtimeMenu(mealtimeMenu.getCafe(), mealtimeMenu.getDate(), mealtimeMenu.getMealtime(), visibleItems));
        }
        return filteredMenus;
    }
}
