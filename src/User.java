import java.util.HashSet;
import java.util.Set;

public class User {

    private final Set<MenuItem> favorites;

    User() {
        this.favorites = new HashSet<>();
    }

    public Set<MenuItem> getFavorites() {
        return favorites;
    }

    public void switchFavoriteStatus(MenuItem item) {
        if (this.favorites.contains(item)) {
            this.favorites.remove(item);
        } else {
            this.favorites.add(item);
        }
    }
}
