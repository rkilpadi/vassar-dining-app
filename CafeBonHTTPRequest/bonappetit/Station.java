package CafeBonHTTPRequest.bonappetit;

import java.util.List;

public class Station {
    private String label;
    private List<MenuItem> items;

    public Station(String label, List<MenuItem> items) {
        this.label = label;
        this.items = items;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
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
