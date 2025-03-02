import java.util.List;
import java.util.Scanner;

public class MedicationTrackingSystemTest {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MedicationTrackingSystem med_system = new MedicationTrackingSystem();
        System.out.println("\n === Testing MedicationTrackingSystem Class ===");
        FileUtils.loadMedicationsFromCSV(med_system, "medications.csv");
        FileUtils.loadPatientsFromCSV(med_system, "patients.csv");
        FileUtils.loadDoctorsFromCSV(med_system, "doctors.csv");
        FileUtils.loadPrescriptionsFromCSV(med_system, "prescriptions.csv");
        System.out.println("\n === System Initialized from CSV files ===\n");

        MedicationTrackingSystemExampleMenu.mainMenu(med_system, scanner);
        
    }



}
