package vassar.cmpu203.vassardiningapp.CafeBonHTTPRequest;


import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MenuParserTest {
    public static void main(String[] args) {

        String cafeName = "gordon"; // Change the cafe name as desired
        String date = "2023-05-08"; // Change the date as desired

        Map<String, String> cor = new HashMap<>();
        try {
            List<Menu> menus = MenuParser.MenuParserMethod(cafeName, date);

            for (Menu menu : menus) {
                for (Station station : menu.getStations()) {
                    for (MenuItem item : station.getItems()) {
                        cor.putAll(item.getCor_icon());
                    }
                }
                System.out.println(menus.get(0).toString());
                System.out.println(cor.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}