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

    public List<String> getItems() {
        return items;
    }

    public boolean hasMenuItem(String itemId) {
        return items.contains(itemId);
    }
}
