import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class TextUI {

    private final Controller controller;
    private final PrintStream out;
    private final Scanner scanner;

    public TextUI(Controller controller, PrintStream out, InputStream in) {
        this.controller = controller;
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public void run() {
        String cafe = pickCafe();
        String mealTime = pickMealtime();
        controller.findMenu(cafe, mealTime);
        out.println(controller.getCurrentMenu().toString(controller.getCurrentUser().getFavorites()));
        pickFavorite();
    }

    private String pickCafe() {
        Set<String> cafes = Data.getCafes();
        String cafesString = String.join(", ", cafes);

        out.printf("Select a cafe: %s\n", cafesString);

        while (true) {
            String userInput = scanner.nextLine();

            for (String cafe : cafes) {
                if (cafe.equalsIgnoreCase(userInput)) {
                    return cafe;
                }
            }
            out.printf("Please select a valid cafe: %s\n", cafesString);
        }
    }

    private String pickMealtime() {
        Set<String> mealtimes = Data.getMealtimes();
        String mealtimesString = String.join(", ", mealtimes);

        out.printf("Select a mealtime: %s\n", mealtimesString);

        while (true) {
            String userInput = scanner.nextLine();

            for (String mealtime : mealtimes) {
                if (mealtime.equalsIgnoreCase(userInput)) {
                    return mealtime;
                }
            }
            out.printf("Please select a valid mealtime: %s\n", mealtimesString);
        }
    }

    private void pickFavorite() {
        Set<MenuItem> items = controller.getCurrentMenu().menuItems();
        Set<String> itemNames = items.stream().map(MenuItem::name).collect(Collectors.toSet());
        String itemsString = String.join(", ", itemNames);

        out.printf("Select an item to favorite or unfavorite, or type skip: %s\n", itemsString);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("skip")) {
                run();
            }

            for (MenuItem item : items) {
                if (item.name().equalsIgnoreCase(userInput)) {
                    controller.getCurrentUser().switchFavoriteStatus(item);
                    out.printf("Switched favorite status for %s. Favorited items appear with a *.\n", item.name());
                    doNext();
                }
            }
            out.printf("Please select a valid mealtime: %s\n", itemsString);
        }
    }

    private void doNext() {
        out.println("Would you like to select another item? (y/n)");
        switch (scanner.nextLine()) {
            case "y": pickFavorite();
            case "n": run();
            default: doNext();
        }
    }
}
