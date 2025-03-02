import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileUtils {

    public static void loadMedicationsFromCSV(MedicationTrackingSystem med_system, String filename) {
        File file = Paths.get("data", filename).toFile();

        try {
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                if (values.length < 5) continue;

                String name = values[1].trim();
                double dose = Double.parseDouble(values[2].trim());
                int quantity_in_stock = Integer.parseInt(values[3].trim());
                String expiry_date = values[4].trim();

                med_system.addMedication(name, dose, quantity_in_stock, expiry_date);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + filename);
        }
    }
}
