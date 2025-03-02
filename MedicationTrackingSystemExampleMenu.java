import java.util.List;
import java.util.Scanner;

public class MedicationTrackingSystemExampleMenu {

    public static void mainMenu(MedicationTrackingSystem med_system, Scanner scanner) {
        int choice = -1; // Default to an invalid value
    
        do {
            clearScreen();
            System.out.println("===== Medication Tracking System Menu =====");
            System.out.println("(1) (2) (3)  Add:    (1)-->Patient (2)-->Doctor (3)-->Medication");
            System.out.println("(4) (5) (6)  Search: (4)-->Patient (5)-->Doctor (6)-->Medication");
            System.out.println("(7) (8) (9)  Edit:   (7)-->Patient (8)-->Doctor (9)-->Medication");
            System.out.println("(10)(11)(12) Delete: (10)->Patient (11)->Doctor (12)->Medication");
            System.out.println("(13) Assign Patient to Doctor");
            System.out.println("(14) Enter New Prescription");
            System.out.println("(15) List Prescriptions by Doctor");
            System.out.println("(16) Generate Full System Report");
            System.out.println("(17) Expired Medications Report");
            System.out.println("(18) Restock Medications");
            System.out.println("(0) Exit");
            System.out.print("Enter your choice: ");
    
            // read input as a full string and trim spaces
            String input = scanner.nextLine().trim();
    
            if (input.isEmpty()) {
                // just reload the menu if enter was pressed without input
                continue;
            }

            // check if input is a number
            if (input.matches("\\d+")) {
                choice = Integer.parseInt(input);
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
                pressAnyKeyToContinue(scanner);
                // reload the menu
                continue; 
            }
    
            switch (choice) {
                case 1:
                    addPatient(med_system, scanner);
                    break;
                case 2:
                    addDoctor(med_system, scanner);
                    break;
                case 3:
                    addMedication(med_system, scanner);
                    break;
                case 4:
                    searchPatient(med_system, scanner);
                    break;
                case 5:
                    searchDoctor(med_system, scanner);
                    break;
                case 6:
                    searchMedication(med_system, scanner);
                    break;
                case 7:
                    editPatient(med_system, scanner);
                    break;
                case 8:
                    editDoctor(med_system, scanner);
                    break;
                case 9:
                    editMedication(med_system, scanner);
                    break;
                case 10:
                    deletePatient(med_system, scanner);
                    break;
                case 11:
                    deleteDoctor(med_system, scanner);
                    break;
                case 12:
                    deleteMedication(med_system, scanner);
                    break;
                case 13:
                    assignPatientToDoctor(med_system, scanner);
                    break;
                case 14:
                    enterNewPrescription(med_system, scanner);
                    break;
                case 15:
                    listPrescriptionsByDoctor(med_system, scanner);
                    break;
                case 16:
                    generateFullSystemReport(med_system);
                    break;
                case 17:
                    expiredMedicationsReport(med_system);
                    break;
                case 18:
                    restockMedications(med_system, scanner);
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    choice = -1; 
                    break;
            }
    
            if (choice != 0) {
                pressAnyKeyToContinue(scanner);
            }
    
        } while (choice != 0);
    }
    
    public static void pressAnyKeyToContinue(Scanner scanner) {
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Wait for Enter key
    }

    // workaround to clear the console (os independent)
    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    // helper method to console out a report line by line
    public static void printReport(List<String> report) {
        for (String line : report) {
            System.out.println(line);
        }
    }

    private static void addPatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement addPatient()");
    }

    private static void addDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement addDoctor()");
    }

    private static void addMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement addMedication()");
    }

    private static void searchPatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement searchPatient()");
    }

    private static void searchDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement searchDoctor()");
    }

    private static void searchMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement searchMedication()");
    }

    private static void editPatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement editPatient()");
    }

    private static void editDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement editDoctor()");
    }

    private static void editMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement editMedication()");
    }

    private static void deletePatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement deletePatient()");
    }

    private static void deleteDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement deleteDoctor()");
    }

    private static void deleteMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement deleteMedication()");
    }

    private static void assignPatientToDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement assignPatientToDoctor()");
    }

    private static void enterNewPrescription(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement enterNewPrescription()");
    }

    private static void listPrescriptionsByDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement listPrescriptionsByDoctor()");
    }

    private static void generateFullSystemReport(MedicationTrackingSystem med_system) {
        System.out.println("TODO: Implement generateFullSystemReport()");
    }

    private static void expiredMedicationsReport(MedicationTrackingSystem med_system) {
        System.out.println("TODO: Implement expiredMedicationsReport()");
    }

    private static void restockMedications(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement restockMedications()");
    }
}
