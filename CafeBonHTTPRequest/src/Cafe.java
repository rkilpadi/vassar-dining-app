package CafeBonHTTPRequest.src;

import com.google.gson.Gson;

import java.util.List;

    public class Cafe {
        private String name;
        private List<Mealtime> mealtimes;

        public Cafe(String name, List<Mealtime> mealtimes) {
            this.name = name;
            this.mealtimes = mealtimes;
        }

        public String getName() {
            return name;
        }

        public List<Mealtime> getMealtimes() {
            return mealtimes;
        }

        public Mealtime getMealTime(String mealtime) {
            for (Mealtime m : mealtimes) {
                if (m.getStartTime().equalsIgnoreCase(mealtime)) {
                    return m;
                }
            }

            return null;
        }

        public static Cafe convert(String json) {
            Gson gson = new Gson();
            return gson.fromJson(json, Cafe.class);
        }



    }


