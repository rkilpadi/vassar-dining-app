package vassar.cmpu203.vassardiningapp.model;

import java.io.Serializable;
import java.util.List;

/**
 *  Represents the set of MenuItems at a given cafe at a given mealtime.
 */
public class MealtimeMenu implements Serializable {

    private final String cafe;
    private final String date;
    private final String label;
    private final List<MealtimeItem> menuItems;

    public MealtimeMenu(String cafe, String date, String label, List<MealtimeItem> menuItems) {
        this.cafe = cafe;
        this.date = date;
        this.label = label;
        this.menuItems = menuItems;
    }

    public String getCafe() {
        return cafe;
    }

    public String getDate() {
        return date;
    }

    public String getLabel() {
        return label;
    }

    public List<MealtimeItem> getMenuItems() {
        return menuItems;
    }
}
