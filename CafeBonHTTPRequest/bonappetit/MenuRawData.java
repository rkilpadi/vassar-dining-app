package CafeBonHTTPRequest.bonappetit;

import com.google.gson.Gson;

import java.util.List;

public class MenuRawData {
    private String label;
    private String starttime;
    private String endtime;
    private List<StationRawData> stations;

    public MenuRawData(String name, String startTime, String endTime, List<StationRawData> stationRawData) {
        this.label = label;
        this.starttime = starttime;
        this.endtime = endtime;
        this.stations = stationRawData;
    }

    public String getName() {
        return label;
    }

    public String getStartTime() {
        return starttime;
    }

    public String getEndTime() {
        return endtime;
    }

    public List<StationRawData> getStations() {
        return stations;
    }

    public void setStations(List<StationRawData> stationRawData) {
        this.stations = stationRawData;
    }

    public static MenuRawData convert(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, MenuRawData.class);
    }



    @Override
    public String toString() {
        return "Mealtime{" +
                "name='" + label + '\'' +
                ", startTime='" + starttime + '\'' +
                ", endTime='" + endtime + '\'' +
                ", stations=" + stations +
                '}';
    }
}
