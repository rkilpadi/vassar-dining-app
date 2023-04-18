package vassar.cmpu203.vassardiningapp.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user, containing all data that is variable between different users, such as favorite items.
 */
public class User {

    private final Set<MenuItem> favorites = new HashSet<>();

    public Set<MenuItem> getFavorites() {
        return favorites;
    }

    /**
     * Either favorites or unfavorites an item by adding or removing it from the favorites set.
     *
     * @param item the item to either add or remove from the set.
     */
    public void switchFavoriteStatus(MenuItem item) {
        if (this.favorites.contains(item)) {
            this.favorites.remove(item);
        } else {
            this.favorites.add(item);
        }
    }
}
