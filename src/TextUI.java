import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;

public class TextUI {

    private Controller controller;
    private final PrintStream out;
    private Scanner scanner;

    public TextUI(Controller controller, PrintStream out, InputStream in) {
        this.controller = controller;
        this.out = out;
        this.scanner = new Scanner(in);
    }

    public void run() {
        out.println("VASSAR DINING APP PROTOTYPE");

        String cafe = pickCafe();
        String mealTime = pickMealtime();
        controller.findMenu(cafe, mealTime);
        out.println(controller.getMenu().toString());
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
        String mealtimesString = String.join(",", mealtimes);

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
}
