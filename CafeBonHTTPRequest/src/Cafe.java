package CafeBonHTTPRequest.src;

import com.google.gson.Gson;

import java.util.List;

public class Cafe {
    private String label;
    private String starttime;
    private String endtime;
    private List<Station> stations;

    public Cafe(String name, String startTime, String endTime, List<Station> stations) {
        this.label = label;
        this.starttime = starttime;
        this.endtime = endtime;
        this.stations = stations;
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

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public static Cafe convert(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Cafe.class);
    }



    @Override
    public String toString() {
        return "Cafe{" +
                "name='" + label + '\'' +
                ", startTime='" + starttime + '\'' +
                ", endTime='" + endtime + '\'' +
                ", stations=" + stations +
                '}';
    }
}
