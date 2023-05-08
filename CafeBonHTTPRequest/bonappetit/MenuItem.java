package CafeBonHTTPRequest.bonappetit;

import java.util.Map;

public class MenuItem {
    private String id;
    private String label;
    private String description;
    private Map<String, String> cor_icon;
    private int tier;
    private String station;



    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getCor_icon() {
        return cor_icon;
    }

    public void setCor_icon(Map<String, String> cor_icon) {
        this.cor_icon = cor_icon;
    }

    public int getTier() {
        return tier;
    }


    public void setTier(int tier) {
        this.tier = tier;
    }


    public String getStation() {
        String[] before = station.split("<strong>@");
        String[] after = before[1].split("</strong>");
        String parsedStation= after[0] ;
        return parsedStation;
    }


    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu Item: ").append(label).append("\n");
        //sb.append("ID: ").append(id).append("\n");
        sb.append("Description: ").append(description).append("\n");
        //sb.append("Tier: ").append(tier).append("\n");
        //sb.append("vassar.cmpu203.vassardiningapp.model.bonappetit.Station: ").append(this.getStation()).append("\n");
        sb.append("Dietary Restrictions: ").append("\n");

        for (Map.Entry<String, String> entry : cor_icon.entrySet()) {
            sb.append("\t- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }
}
