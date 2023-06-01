package vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import vassar.cmpu203.vassardiningapp.model.MealtimeItem;
import vassar.cmpu203.vassardiningapp.model.MealtimeMenu;

public class Menu {
    //Ex: Gordon
    private String cafeName;
    private String date;
    //Ex: Breakfast
    private String label;
    private String startTime;
    private String endTime;
    private List<Station> stations;
    private List<MenuItem> menuItems;

    public Menu(String cafeName, String date, String label, String startTime, String endTime, List<Station> stations) {
        this.cafeName = cafeName;
        this.date = date;
        this.label = label;
        this.startTime = startTime;
        this.endTime = endTime;
        this.stations = stations;
        this.menuItems = new ArrayList<>();
    }
    public Menu(String cafeName, String date) {
        this.cafeName = cafeName;
        this.date = date;
        this.stations = new ArrayList<>();
        this.menuItems = new ArrayList<>();
    }

    public void parseMealtimeRawData(MenuRawData mealtimeRawData, List<MenuItem> menuItems) {
        this.startTime = mealtimeRawData.getStartTime();
        this.endTime = mealtimeRawData.getEndTime();
        this.label = mealtimeRawData.getName();
        for (StationRawData stationRaw : mealtimeRawData.getStations()) {
            List<MenuItem> stationMenuItems = new ArrayList<>();
            for (String itemId : stationRaw.getItems()) {
                for (MenuItem menuItem : menuItems) {
                    if (menuItem.getId().equals(itemId) && menuItem.getTier() == 1) {
                        stationMenuItems.add(menuItem);
                        break;
                    }
                }
            }
            Station station = new Station(stationRaw.getLabel(), stationMenuItems);
            this.stations.add(station);
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu: ");
        sb.append("\nCafé Name: ").append(cafeName);
        sb.append("\nDate: ").append(date);
        sb.append("\nLabel: ").append(label);
        sb.append("\nStart Time: ").append(startTime);
        sb.append("\nEnd Time: ").append(endTime);
        sb.append("\nStations: ");
        for (Station station : stations) {
            sb.append("\n").append(station.getLabel());
            List<MenuItem> stationMenuItems = station.getItems();
            for (MenuItem menuItem : stationMenuItems) {
                sb.append("\n- ").append(menuItem.toString());
            }
        }
        sb.append("\nMenu Items: ");
        for (MenuItem menuItem : menuItems) {
            sb.append("\n- ").append(menuItem.toString());
        }
        return sb.toString();
    }

    public MealtimeMenu toMealtimeMenu() {
        List<MealtimeItem> retItems = new ArrayList<>();
        for (Station station : stations) {
            for (MenuItem item : station.getItems()) {
                retItems.add(item.toMealtimeItem());
            }
        }
        return new MealtimeMenu(cafeName, date, label, retItems);
    }
}
