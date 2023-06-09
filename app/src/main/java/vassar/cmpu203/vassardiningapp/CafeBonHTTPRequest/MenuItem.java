package vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import vassar.cmpu203.vassardiningapp.model.DietaryRestriction;
import vassar.cmpu203.vassardiningapp.model.MealtimeItem;

public class MenuItem {
    private String id;
    private String label;
    private String description;
    private Map<String, String> cor_icon;
    private int tier;
    private String station;
    private Map<String, MenuItem> idMap;

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
        return after[0];
    }


    public void setStation(String station) {
        this.station = station;
    }

    //public Map<String, MenuItem> IdMapper{}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu Item: ").append(label).append("\n");
        //sb.append("ID: ").append(id).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Tier: ").append(tier).append("\n");
        sb.append("Station: ").append(this.getStation()).append("\n");
        sb.append("Dietary Restrictions: ").append("\n");

        for (Map.Entry<String, String> entry : cor_icon.entrySet()) {
            sb.append("\t- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }

    public MealtimeItem toMealtimeItem() {
        Set<DietaryRestriction> restrictions = new HashSet<>();
        for (String restrictionId : cor_icon.keySet()) {
            try {
                restrictions.add(DietaryRestriction.getById(restrictionId));
            } catch (Exception e) {
                System.out.println("Unknown dietary restriction: " + cor_icon.get(restrictionId));
                e.printStackTrace();
            }
        }
        return new MealtimeItem(label, id, description, station, restrictions);
    }
}
