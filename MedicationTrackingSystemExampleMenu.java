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

    public static boolean confirmYesNo(Scanner scanner, String message) {
        while (true) {
            System.out.print(message + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
    
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' for Yes or 'n' for No.");
            }
        }
    }

    public static int getPositiveInteger(Scanner scanner, String prompt) {
        int number = -1;
        while (number <= 0) {
            System.out.print(prompt + " (positive integer): ");
            String input = scanner.nextLine().trim();
            if (MedUtils.isValidInteger(input)) {
                number = Integer.parseInt(input);
                if (number <= 0) {
                    System.out.println("Input must be a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
        return number;
    }

    public static int getPositiveIntegerOrZero(Scanner scanner, String prompt) {
        int number = -1;
        while (number < 0) {  
            System.out.print(prompt + " (positive integer, or 0 to cancel): ");
            String input = scanner.nextLine().trim();
            if (MedUtils.isValidInteger(input)) {
                number = Integer.parseInt(input);
                if (number < 0) {  
                    System.out.println("Input must be a non-negative number. Enter 0 to cancel.");
                }
            } else {
                System.out.println("Invalid input. Please enter a non-negative integer. Enter 0 to cancel.");
            }
        }
        return number;
    }

    public static int inputDoctorIdOrZero(MedicationTrackingSystem med_system, Scanner scanner) {
        while (true) {
            int doctor_id = getPositiveIntegerOrZero(scanner, "Enter Doctor ID (or 0 to cancel)");
            if (doctor_id == 0) return 0; 
            if (med_system.getDoctorById(doctor_id) != null) return doctor_id;
            System.out.println("Invalid Doctor ID. Please try again.");
        }
    }
    
    public static int inputPatientIdOrZero(MedicationTrackingSystem med_system, Scanner scanner) {
        while (true) {
            int patient_id = getPositiveIntegerOrZero(scanner, "Enter Patient ID (or 0 to cancel)");
            if (patient_id == 0) return 0; 
            if (med_system.getPatientById(patient_id) != null) return patient_id;
            System.out.println("Invalid Patient ID. Please try again.");
        }
    }
    
    public static int inputMedicationIdOrZero(MedicationTrackingSystem med_system, Scanner scanner) {
        while (true) {
            int medication_id = getPositiveIntegerOrZero(scanner, "Enter Medication ID (or 0 to cancel)");
            if (medication_id == 0) return 0; 
            if (med_system.getMedicationById(medication_id) != null) return medication_id;
            System.out.println("Invalid Medication ID. Please try again.");
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
        System.out.println("\n =======> Assign Patient to Doctor <======\n");
    
        int doctor_id;
        int patient_id;
        Doctor doctor;
        Patient patient;
    
        // loop until a valid doctor is confirmed
        while (true) {
            doctor_id = inputDoctorIdOrZero(med_system, scanner);
            if (doctor_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            doctor = med_system.getDoctorById(doctor_id);
            System.out.println("\nSelected Doctor: " + doctor);
            System.out.println();
            if (confirmYesNo(scanner, "Confirm this doctor?")) {
                break; // Exit loop if confirmed
            }
        }
    
        // display assigned patients
        List<Patient> assigned_patients = doctor.getPatients();
        if (!assigned_patients.isEmpty()) {
            System.out.println("\n** Patients currently assigned to this doctor:");
            for (Patient patient_in_list : assigned_patients) {
                System.out.println(patient_in_list);
            }
        } else {
            System.out.println("\nThis doctor has no assigned patients.");
        }
        System.out.println();
    
        // enter Patient ID
        while (true) {
            patient_id = inputPatientIdOrZero(med_system, scanner);
            if (patient_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            patient = med_system.getPatientById(patient_id);
            System.out.println("\nSelected Patient: " + patient);
            System.out.println();
            if (confirmYesNo(scanner, "Confirm this patient?")) {
                break; // Exit loop if confirmed
            }
        }
            
        // check if patient is already assigned
        if (doctor.getPatients().contains(patient)) {
            System.out.println("\nThis patient is already assigned to this doctor.");
            return;
        }
    
        // assign patient
        if (doctor.addPatient(patient)) {
            System.out.println("\nPatient successfully assigned to the doctor.");
        } else {
            System.out.println("\nFailed to assign patient. Please check the system.");
            return;
        }
    
        // display updated doctor details
        System.out.println("\nUpdated Doctor Information: " + doctor);
        System.out.println("Updated List of Patients:");
        for (Patient assigned_patient : doctor.getPatients()) {
            System.out.println(assigned_patient);
        }
    
        System.out.println("\n =======> Assignment Completed! <======\n");
    }
    
    
    private static void enterNewPrescription(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("TODO: Implement enterNewPrescription()");
    }

    private static void listPrescriptionsByDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> List Prescriptions by Doctor <======\n");
    
        int doctor_id = getPositiveInteger(scanner, "Enter Doctor ID");
    
        List<String> report = med_system.checkPrescriptionsByDoctorReport(doctor_id);
        printReport(report);
        System.out.println();
    }
    

    private static void generateFullSystemReport(MedicationTrackingSystem med_system) {
        List<String> report = med_system.fullSystemReport();
        System.out.println();
        printReport(report);
        System.out.println();
    }

    private static void expiredMedicationsReport(MedicationTrackingSystem med_system) {
        List<String> report = med_system.checkForExpiredMedsReport();
        System.out.println();
        printReport(report);
        System.out.println();
    }
  

    private static void restockMedications(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Restocking Medications <======\n");
    
        // ask for threshold
        int threshold = getPositiveInteger(scanner, "Enter Stock Threshold");
        
        // retrieve and display low-stock medications
        List<Medication> low_stock_meds = med_system.checkLowStockMeds(threshold);
        if (low_stock_meds.isEmpty()) {
            System.out.println("No medications found below the given threshold.");
            return;
        }
    
        System.out.println("\nLow Stock Medications (below " + threshold + " units):");
        for (Medication med : low_stock_meds) {
            System.out.println(med);
        }
    
        // ask if the user wants to restock
        if (!confirmYesNo(scanner, "\nDo you want to restock these medications?")) {
            System.out.println("Restocking canceled.");
            return;
        }
    
        // ask for restock amount
        int restock_amount = getPositiveInteger(scanner, "Enter Re-Stock Amount");
        
        // restock all chosen medications
        for (Medication med : low_stock_meds) {
            med_system.restockMedication(med, restock_amount);
        }
    
        // display updated medications
        System.out.println("\nUpdated Medication Stocks:");
        for (Medication med : low_stock_meds) {
            System.out.println(med);
        }
    
        System.out.println("\n =======> Restocking Completed! <======\n");
    }
        
}
