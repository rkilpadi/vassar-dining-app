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
        String date = "2023-05-09"; // Change the date as desired

        try {
            List<Menu> menus = MenuParser.MenuParserMethod(cafeName, date);

            menus.forEach(menu -> System.out.println(menu.getMenuItems()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}