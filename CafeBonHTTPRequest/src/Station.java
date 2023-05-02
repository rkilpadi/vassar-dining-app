package CafeBonHTTPRequest.src;

import java.util.List;

public class Station {
    private String label;
    private List<String> items;

    public Station(String label, List<String> items) {
        this.label = label;
        this.items = items;
    }

    public String getLabel() {
        return label;
    }

    public MenuItem getItems() {
        return (MenuItem) items;
    }

    public boolean hasMenuItem(String itemId) {
        return items.contains(itemId);
    }

    @Override
    public String toString() {
        return "Station{" +
                "label='" + label + '\'' +
                ", items=" + items +
                '}';
    }
}
