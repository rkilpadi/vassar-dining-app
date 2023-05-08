package CafeBonHTTPRequest.bonappetit;

import java.util.List;

public class StationRawData {
    private String label;
    private List<String> items;

    public StationRawData(String label, List<String> items) {
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

    @Override
    public String toString() {
        return "vassar.cmpu203.vassardiningapp.model.bonappetit.Station{" +
                "label='" + label + '\'' +
                ", items=" + items +
                '}';
    }
}
