package CafeBonHTTPRequest.bonappetit;


import java.io.IOException;
import java.util.List;

public class MenuParserTest {
    public static void main(String[] args) {
        MenuParser menuParser = new MenuParser();

        String cafeName = "the-retreat"; // Change the cafe name as desired
        String date = "2023-05-06"; // Change the date as desired

        try {
            List<Menu> menus = menuParser.MenuParserMethod(cafeName, date);

            for (Menu menu : menus) {
                System.out.println(menu.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}