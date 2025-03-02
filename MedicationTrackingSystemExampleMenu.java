import java.util.Scanner;

public class MedicationTrackingSystemExampleMenu {

    public static void pressAnyKeyToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Wait for Enter key
    }

    // workaround to clear the console
    public static void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

}
