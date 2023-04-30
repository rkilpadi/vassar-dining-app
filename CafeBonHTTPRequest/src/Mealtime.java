package CafeBonHTTPRequest.src;

import java.util.List;

public class Mealtime {
    private String label;
    private String startTime;
    private String endTime;
    private List<Station> stations;

    public Mealtime(String startTime, String endTime, List<Station> stations) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.stations = stations;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<Station> getStations() {
        return stations;
    }

    public Station getStation(String label) {
        for (Station s : stations) {
            if (s.getLabel().equalsIgnoreCase(label)) {
                return s;
            }
        }

        return null;
    }
}
