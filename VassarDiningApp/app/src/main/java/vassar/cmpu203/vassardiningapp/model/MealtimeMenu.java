package vassar.cmpu203.vassardiningapp.model;

import java.util.List;

/** Represents the set of MenuItems at a given cafe at a given mealtime. */
public class MealtimeMenu {

    private final String cafe;
    private final String date;
    private final String mealtime;
    private final List<MealtimeItem> menuItems;

    public MealtimeMenu(String cafe, String date, String mealtime, List<MealtimeItem> menuItems) {
        this.cafe = cafe;
        this.date = date;
        this.mealtime = mealtime;
        this.menuItems = menuItems;
    }

    public String getCafe() {
        return cafe;
    }

    public String getDate() {
        return date;
    }

    public String getMealtime() {
        return mealtime;
    }

    public List<MealtimeItem> getMenuItems() {
        return menuItems;
    }
}
