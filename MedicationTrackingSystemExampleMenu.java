import java.util.List;
import java.util.Scanner;

public class MedicationTrackingSystemExampleMenu {

    public static void mainMenu(MedicationTrackingSystem med_system, Scanner scanner) {
        int choice = -1; 
    
        do {
            // clearScreen();
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
        // wait for the user to press enter
        scanner.nextLine(); 
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
            int doctor_id = getPositiveIntegerOrZero(scanner, "Enter Doctor ID");
            if (doctor_id == 0) return 0; 
            if (med_system.getDoctorById(doctor_id) != null) return doctor_id;
            System.out.println("Invalid Doctor ID. Please try again.");
        }
    }
    
    public static int inputPatientIdOrZero(MedicationTrackingSystem med_system, Scanner scanner) {
        while (true) {
            int patient_id = getPositiveIntegerOrZero(scanner, "Enter Patient ID");
            if (patient_id == 0) return 0; 
            if (med_system.getPatientById(patient_id) != null) return patient_id;
            System.out.println("Invalid Patient ID. Please try again.");
        }
    }
    
    public static int inputMedicationIdOrZero(MedicationTrackingSystem med_system, Scanner scanner) {
        while (true) {
            int medication_id = getPositiveIntegerOrZero(scanner, "Enter Medication ID");
            if (medication_id == 0) return 0; 
            if (med_system.getMedicationById(medication_id) != null) return medication_id;
            System.out.println("Invalid Medication ID. Please try again.");
        }
    }

    private static void addPatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Add New Patient <======\n");
    
        // step 1: enter name
        String name;
        while (true) {
            System.out.print("Enter Patient Name: ");
            name = scanner.nextLine().trim();
            if (MedUtils.validateName(name)) {
                name = MedUtils.normalizeName(name);
                break;
            }
            System.out.println("Invalid name. Please enter a valid name.");
        }
    
        // step 2: enter age
        int age;
        while (true) {
            System.out.print("Enter Patient Age: ");
            String input = scanner.nextLine().trim();
            if (MedUtils.isValidInteger(input)) {
                age = Integer.parseInt(input);
                if (MedUtils.validateAge(age)) {
                    break;
                }
            }
            System.out.println("Invalid age. Please enter a valid age (between 1 and 150).");
        }
    
        // step 3: enter phone number
        String phone_number;
        while (true) {
            System.out.print("Enter Patient Phone Number: ");
            phone_number = scanner.nextLine().trim();
            if (MedUtils.validatePhoneNumber(phone_number)) {
                phone_number = MedUtils.normalizePhoneNumber(phone_number);
                break;
            }
            System.out.println("Invalid phone number. Please enter a valid 10-digit phone number.");
        }
    
        // step 4: confirm adding the patient
        System.out.println("\nNew Patient Details:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone: " + phone_number);
    
        if (!confirmYesNo(scanner, "\nConfirm adding this patient?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 5: add patient to the system
        boolean success = med_system.addPatient(name, age, phone_number);
    
        if (success) {
            // step 6: show newly added patient
            int new_patient_id = med_system.getNumPatients(); 
            Patient new_patient = med_system.getPatientById(new_patient_id);
            if (new_patient != null) {
                System.out.println("\nAdded " + new_patient);
            } else {
                System.out.println("\nFailed to retrieve the newly added patient. Please check the system.");
            }
        } else {
            System.out.println("\nError adding patient. Please check the system.");
        }
    
        System.out.println("\n =======> Patient Added Successfully! <======\n");
    }
    

    private static void addDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Add New Doctor <======\n");
    
        // step 1: enter name
        String name;
        while (true) {
            System.out.print("Enter Doctor Name: ");
            name = scanner.nextLine().trim();
            if (MedUtils.validateName(name)) {
                name = MedUtils.normalizeName(name);
                break;
            }
            System.out.println("Invalid name. Please enter a valid name.");
        }
    
        // step 2: enter age
        int age;
        while (true) {
            System.out.print("Enter Doctor Age: ");
            String input = scanner.nextLine().trim();
            if (MedUtils.isValidInteger(input)) {
                age = Integer.parseInt(input);
                if (MedUtils.validateAge(age)) {
                    break;
                }
            }
            System.out.println("Invalid age. Please enter a valid age (between 1 and 150).");
        }
    
        // step 3: enter phone number
        String phone_number;
        while (true) {
            System.out.print("Enter Doctor Phone Number: ");
            phone_number = scanner.nextLine().trim();
            if (MedUtils.validatePhoneNumber(phone_number)) {
                phone_number = MedUtils.normalizePhoneNumber(phone_number);
                break;
            }
            System.out.println("Invalid phone number. Please enter a valid 10-digit phone number.");
        }
    
        // step 4: enter specialization
        String specialization;
        while (true) {
            System.out.print("Enter Doctor Specialization: ");
            specialization = scanner.nextLine().trim();
            if (MedUtils.validateName(specialization)) {
                specialization = MedUtils.normalizeName(specialization);
                break;
            }
            System.out.println("Invalid specialization. Please enter a valid specialization.");
        }
    
        // step 5: confirm adding the doctor
        System.out.println("\nNew Doctor Details:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone: " + phone_number);
        System.out.println("Specialization: " + specialization);
    
        if (!confirmYesNo(scanner, "\nConfirm adding this doctor?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 6: add doctor to the system
        boolean success = med_system.addDoctor(name, age, phone_number, specialization);
    
        if (success) {
            // step 7: show newly added doctor
            int new_doctor_id = med_system.getNumDoctors(); 
            Doctor new_doctor = med_system.getDoctorById(new_doctor_id);
            if (new_doctor != null) {
                System.out.println("\nAdded " + new_doctor);
            } else {
                System.out.println("\nFailed to retrieve the newly added doctor. Please check the system.");
            }
        } else {
            System.out.println("\nError adding doctor. Please check the system.");
        }
    
        System.out.println("\n =======> Doctor Added Successfully! <======\n");
    }
    
    private static void addMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Add New Medication <======\n");
    
        // step 1: enter medication name
        String name;
        while (true) {
            System.out.print("Enter Medication Name: ");
            name = scanner.nextLine().trim();
            if (MedUtils.validateName(name)) {
                name = MedUtils.normalizeName(name);
                break;
            }
            System.out.println("Invalid name. Please enter a valid medication name.");
        }
    
        // step 2: enter dosage
        double dose;
        while (true) {
            System.out.print("Enter Medication Dosage: ");
            String input = scanner.nextLine().trim();
            if (MedUtils.isValidDouble(input)) {
                dose = Double.parseDouble(input);
                if (MedUtils.validateDose(dose)) {
                    break;
                }
            }
            System.out.println("Invalid dosage. Please enter a valid positive dose.");
        }
    
        // step 3: enter quantity in stock
        int quantity_in_stock;
        while (true) {
            System.out.print("Enter Medication Stock Quantity: ");
            String input = scanner.nextLine().trim();
            if (MedUtils.isValidInteger(input)) {
                quantity_in_stock = Integer.parseInt(input);
                if (MedUtils.validateStockQty(quantity_in_stock)) {
                    break;
                }
            }
            System.out.println("Invalid quantity. Please enter a valid non-negative integer.");
        }
    
        // step 4: enter exp. date (validate format YYYY-MM)
        String expiry_date;
        while (true) {
            System.out.print("Enter Expiry Date (YYYY-MM): ");
            expiry_date = scanner.nextLine().trim();
            if (MedUtils.validateYearMonthString(expiry_date)) {
                break;
            }
            System.out.println("Invalid expiry date format. Please enter a valid date in YYYY-MM format.");
        }
    
        // step 5: confirm adding medication
        System.out.println("\nNew Medication Details:");
        System.out.println("Name: " + name);
        System.out.println("Dosage: " + dose);
        System.out.println("Stock Quantity: " + quantity_in_stock);
        System.out.println("Expiry Date: " + expiry_date);
    
        if (!confirmYesNo(scanner, "\nConfirm adding this medication?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 6: add medication to the system
        boolean success = med_system.addMedication(name, dose, quantity_in_stock, expiry_date);
    
        if (success) {
            // step 7: display newly added medication
            int new_medication_id = med_system.getNumMedications(); 
            Medication new_medication = med_system.getMedicationById(new_medication_id);
            if (new_medication != null) {
                System.out.println("\nAdded " + new_medication);
            } else {
                System.out.println("\nFailed to retrieve the newly added medication. Please check the system.");
            }
        } else {
            System.out.println("\nError adding medication. Please check the system.");
        }
    
        System.out.println("\n =======> Medication Added Successfully! <======\n");
    }
    
    private static void searchPatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Search Patient <======\n");
    
        // enter search string (allow empty input for full search)
        System.out.print("Input Patient Name to Search (or Enter to list all patients): ");
        String name = scanner.nextLine().trim();
    
        if (!name.isEmpty() && !MedUtils.validateName(name)) {
            System.out.println("Invalid name. Please enter a valid name.");
            return;
        }
    
        // search and print report
        List<String> report = med_system.searchPatientReport(name);
        printReport(report);
        System.out.println();
        
    }

    private static void searchDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Search Doctor <======\n");
    
        // enter search string (allow empty input for full search)
        System.out.print("Enter Doctor Name to Search (or press Enter to list all doctors): ");
        String name = scanner.nextLine().trim();
    
        if (!name.isEmpty() && !MedUtils.validateName(name)) {
            System.out.println("Invalid name. Please enter a valid name.");
            return;
        }
    
        // search and print report
        List<String> report = med_system.searchDoctorReport(name);
        printReport(report);
        System.out.println();
    }
    
    private static void searchMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Search Medication <======\n");
    
        // enter search string (allow empty input for full search)
        System.out.print("Enter Medication Name to Search (or press Enter to list all medications): ");
        String name = scanner.nextLine().trim();
    
        if (!name.isEmpty() && !MedUtils.validateName(name)) {
            System.out.println("Invalid name. Please enter a valid name.");
            return;
        }
    
        // search and print report
        List<String> report = med_system.searchMedicationReport(name);
        printReport(report);
        System.out.println();    
    }

    private static void editPatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Edit Patient <======\n");
    
        // step 1: enter patient ID (loop until valid)
        int patient_id;
        Patient patient;
        while (true) {
            patient_id = inputPatientIdOrZero(med_system, scanner);
            if (patient_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            patient = med_system.getPatientById(patient_id);
            System.out.println("\nSelected Patient: " + patient);
    
            if (confirmYesNo(scanner, "Is this the correct patient?")) {
                break;
            }
        }
    
        // step 2: edit fields (show old values, allow pressing enter to keep unchanged)
        System.out.println("\nEditing Patient: " + patient);
        System.out.println("Press Enter to keep a field unchanged.");
    
        // edit name
        String name;
        while (true) {
            System.out.print("Enter new Name (current: " + patient.getName() + "): ");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                name = null; 
                break;
            }
            if (MedUtils.validateName(name)) {
                name = MedUtils.normalizeName(name);
                break;
            }
            System.out.println("Invalid name. Try again.");
        }
    
        // edit age
        String age_input;
        int age = -1;
        while (true) {
            System.out.print("Enter new Age (current: " + patient.getAge() + "): ");
            age_input = scanner.nextLine().trim();
            if (age_input.isEmpty()) {
                age = -1; 
                break;
            }
            if (MedUtils.isValidInteger(age_input)) {
                age = Integer.parseInt(age_input);
                if (MedUtils.validateAge(age)) {
                    break;
                }
            }
            System.out.println("Invalid age. Try again.");
        }
    
        // edit phone number
        String phone_number;
        while (true) {
            System.out.print("Enter new Phone Number (current: " + patient.getPhoneNumber() + "): ");
            phone_number = scanner.nextLine().trim();

            if (phone_number.isEmpty()) {
                phone_number = null; // Keep unchanged
                break;
            }
            if (MedUtils.validatePhoneNumber(phone_number)) {
                phone_number = MedUtils.normalizePhoneNumber(phone_number);
                break;
            }
            System.out.println("Invalid phone number. Try again.");
        }
    
        // step 3: display old patient details and new fields
        System.out.println("\nOld Patient Details: " + patient);
        System.out.println("\nNew Patient Details:");
        System.out.println("Name: " + (name == null ? "[Unchanged]" : name));
        System.out.println("Age: " + (age == -1 ? "[Unchanged]" : age));
        System.out.println("Phone: " + (phone_number == null ? "[Unchanged]" : phone_number));
    
        // step 4: confirm editing
        if (!confirmYesNo(scanner, "\nConfirm changes?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 5: apply
        boolean success = med_system.editPatientById(patient_id, name, age, phone_number);
    
        if (success) {
            Patient updated_patient = med_system.getPatientById(patient_id);
            System.out.println("\nUpdated " + updated_patient);
        } else {
            System.out.println("\nError editing patient. Please check the system.");
        }
    
        System.out.println("\n =======> Patient Edit Completed! <======\n");
    }
    

    private static void editDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Edit Doctor <======\n");
    
        // step 1: enter doctor ID (loop until valid)
        int doctor_id;
        Doctor doctor;
        while (true) {
            doctor_id = inputDoctorIdOrZero(med_system, scanner);
            if (doctor_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            doctor = med_system.getDoctorById(doctor_id);
            System.out.println("\nSelected Doctor: " + doctor);
    
            if (confirmYesNo(scanner, "Is this the correct doctor?")) {
                break;
            }
        }
    
        // step 2: edit fields (show old values, allow pressing enter to keep unchanged)
        System.out.println("\nEditing Doctor: " + doctor);
        System.out.println("Press Enter to keep a field unchanged.");
    
        // edit name
        String name;
        while (true) {
            System.out.print("Enter new Name (current: " + doctor.getName() + "): ");
            name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                name = null; 
                break;
            }
            if (MedUtils.validateName(name)) {
                name = MedUtils.normalizeName(name);
                break;
            }
            System.out.println("Invalid name. Try again.");
        }
    
        // edit age
        String age_input;
        int age = -1;
        while (true) {
            System.out.print("Enter new Age (current: " + doctor.getAge() + "): ");
            age_input = scanner.nextLine().trim();
            if (age_input.isEmpty()) {
                age = -1;
                break;
            }
            if (MedUtils.isValidInteger(age_input)) {
                age = Integer.parseInt(age_input);
                if (MedUtils.validateAge(age)) {
                    break;
                }
            }
            System.out.println("Invalid age. Try again.");
        }
    
        // edit phone number
        String phone_number;
        while (true) {
            System.out.print("Enter new Phone Number (current: " + doctor.getPhoneNumber() + "): ");
            phone_number = scanner.nextLine().trim();
            
            if (phone_number.isEmpty()) {
                phone_number = null; 
                break;
            }
            if (MedUtils.validatePhoneNumber(phone_number)) {
                phone_number = MedUtils.normalizePhoneNumber(phone_number);
                break;
            }
            System.out.println("Invalid phone number. Try again.");
        }
    
        // edit specialization
        String specialization;
        while (true) {
            System.out.print("Enter new Specialization (current: " + doctor.getSpecialization() + "): ");
            specialization = scanner.nextLine().trim();
            
            if (specialization.isEmpty()) {
                specialization = null; 
                break;
            }
            if (MedUtils.validateName(specialization)) {
                specialization = MedUtils.normalizeName(specialization);
                break;
            }
            System.out.println("Invalid specialization. Try again.");
        }
    
        // step 3: display old doctor details and new fields
        System.out.println("\nOld Doctor Details: " + doctor);
        System.out.println("\nNew Doctor Details:");
        System.out.println("Name: " + (name == null ? "[Unchanged]" : name));
        System.out.println("Age: " + (age == -1 ? "[Unchanged]" : age));
        System.out.println("Phone: " + (phone_number == null ? "[Unchanged]" : phone_number));
        System.out.println("Specialization: " + (specialization == null ? "[Unchanged]" : specialization));
    
        // step 4: confirm editing
        if (!confirmYesNo(scanner, "\nConfirm changes?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 5: apply
        boolean success = med_system.editDoctorById(doctor_id, name, age, phone_number, specialization);
    
        if (success) {
            Doctor updated_doctor = med_system.getDoctorById(doctor_id);
            System.out.println("\nSuccessfully updated Doctor: " + updated_doctor);
        } else {
            System.out.println("\nError editing doctor. Please check the system.");
        }
    
        System.out.println("\n =======> Doctor Edit Completed! <======\n");
    }
    

    private static void editMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Edit Medication <======\n");
    
        // step 1: enter medication ID (loop until valid)
        int medication_id;
        Medication medication;
        while (true) {
            medication_id = inputMedicationIdOrZero(med_system, scanner);
            if (medication_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            medication = med_system.getMedicationById(medication_id);
            System.out.println("\nSelected Medication: " + medication);
    
            if (confirmYesNo(scanner, "Is this the correct medication?")) {
                break;
            }
        }
    
        // step 2: edit fields (show old values, allow pressing enter to keep unchanged)
        System.out.println("\nEditing Medication: " + medication);
        System.out.println("Press Enter to keep a field unchanged.");
    
        // edit name
        String name;
        while (true) {
            System.out.print("Enter new Name (current: " + medication.getName() + "): ");
            name = scanner.nextLine().trim();
            
            if (name.isEmpty()) {
                name = null; 
                break;
            }
            if (MedUtils.validateName(name)) {
                name = MedUtils.normalizeName(name);
                break;
            }
            System.out.println("Invalid name. Try again.");
        }
    
        // edit dosage
        String dose_input;
        double dose = -1;
        while (true) {
            System.out.print("Enter new Dosage (current: " + medication.getDose() + "): ");
            dose_input = scanner.nextLine().trim();
            if (dose_input.isEmpty()) {
                dose = -1; 
                break;
            }
            if (MedUtils.isValidDouble(dose_input)) {
                dose = Double.parseDouble(dose_input);
                if (MedUtils.validateDose(dose)) {
                    break;
                }
            }
            System.out.println("Invalid dosage. Try again.");
        }
    
        // edit stock qty
        String stock_input;
        int quantity_in_stock = -1;
        while (true) {
            System.out.print("Enter new Stock Quantity (current: " + medication.getQuantityInStock() + "): ");
            stock_input = scanner.nextLine().trim();
            if (stock_input.isEmpty()) {
                quantity_in_stock = -1; 
                break;
            }
            if (MedUtils.isValidInteger(stock_input)) {
                quantity_in_stock = Integer.parseInt(stock_input);
                if (MedUtils.validateStockQty(quantity_in_stock)) {
                    break;
                }
            }
            System.out.println("Invalid stock quantity. Try again.");
        }
    
        // edit expiry date
        String expiry_date;
        while (true) {
            System.out.print("Enter new Expiry Date (YYYY-MM, current: " + medication.getExpiryDateString() + "): ");
            expiry_date = scanner.nextLine().trim();
            if (expiry_date.isEmpty()) {
                expiry_date = null; // Keep unchanged
                break;
            }
            if (MedUtils.validateYearMonthString(expiry_date)) {
                break;
            }
            System.out.println("Invalid expiry date format. Try again.");
        }
    
        // step 3: display old medication details and new fields
        System.out.println("\nOld Medication Details: " + medication);
        System.out.println("\nNew Medication Details:");
        System.out.println("Name: " + (name == null ? "[Unchanged]" : name));
        System.out.println("Dosage: " + (dose == -1 ? "[Unchanged]" : dose));
        System.out.println("Stock Quantity: " + (quantity_in_stock == -1 ? "[Unchanged]" : quantity_in_stock));
        System.out.println("Expiry Date: " + (expiry_date == null ? "[Unchanged]" : expiry_date));
    
        // step 4: confirm editing
        if (!confirmYesNo(scanner, "\nConfirm changes?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 5: apply
        boolean success = med_system.editMedicationById(medication_id, name, dose, quantity_in_stock, expiry_date);
    
        if (success) {
            Medication updated_medication = med_system.getMedicationById(medication_id);
            System.out.println("\nSuccessfully updated Medication: " + updated_medication);
        } else {
            System.out.println("\nError editing medication. Please check the system.");
        }
    
        System.out.println("\n =======> Medication Edit Completed! <======\n");
    }
    

    private static void deletePatient(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Delete Patient <======\n");
    
        // step 1: enter patient ID (loop until valid)
        int patient_id;
        Patient patient;
        while (true) {
            patient_id = inputPatientIdOrZero(med_system, scanner);
            if (patient_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            patient = med_system.getPatientById(patient_id);
            System.out.println("\nSelected Patient: " + patient);
    
            if (confirmYesNo(scanner, "Is this the correct patient?")) {
                break;
            }
        }
    
        // step 2: display patient details again for final confirmation
        System.out.println("\nFinal Confirmation: You are about to delete this patient.");
        System.out.println(patient);
    
        if (!confirmYesNo(scanner, "Are you sure you want to delete this patient?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 3: apply delete
        boolean success = med_system.removePatientById(patient_id);
    
        if (success) {
            System.out.println("\nPatient successfully deleted.");
        } else {
            System.out.println("\nError deleting patient. Please check the system.");
        }
    
        System.out.println("\n =======> Patient Deletion Completed! <======\n");
    }
    

    private static void deleteDoctor(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Delete Doctor <======\n");
    
        // step 1: enter doctor ID (loop until valid)
        int doctor_id;
        Doctor doctor;
        while (true) {
            doctor_id = inputDoctorIdOrZero(med_system, scanner);
            if (doctor_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            doctor = med_system.getDoctorById(doctor_id);
            System.out.println("\nSelected Doctor: " + doctor);
    
            if (confirmYesNo(scanner, "Is this the correct doctor?")) {
                break;
            }
        }
    
        // step 2: display doctor details again for final confirmation
        System.out.println("\nFinal Confirmation: You are about to delete this doctor.");
        System.out.println(doctor);
    
        if (!confirmYesNo(scanner, "Are you sure you want to delete this doctor?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 3: apply delete
        boolean success = med_system.removeDoctorById(doctor_id);
    
        if (success) {
            System.out.println("\nDoctor successfully deleted.");
        } else {
            System.out.println("\nError deleting doctor. Please check the system.");
        }
    
        System.out.println("\n =======> Doctor Deletion Completed! <======\n");
    }
    

    private static void deleteMedication(MedicationTrackingSystem med_system, Scanner scanner) {
        System.out.println("\n =======> Delete Medication <======\n");
    
        // step 1: enter medication ID (loop until valid)
        int medication_id;
        Medication medication;
        while (true) {
            medication_id = inputMedicationIdOrZero(med_system, scanner);
            if (medication_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            medication = med_system.getMedicationById(medication_id);
            System.out.println("\nSelected Medication: " + medication);
    
            if (confirmYesNo(scanner, "Is this the correct medication?")) {
                break;
            }
        }
    
        // step 2: display medication details again for final confirmation
        System.out.println("\nFinal Confirmation: You are about to delete this medication.");
        System.out.println(medication);
    
        if (!confirmYesNo(scanner, "Are you sure you want to delete this medication?")) {
            System.out.println("Operation canceled.");
            return;
        }
    
        // step 3: apply delete
        boolean success = med_system.removeMedicationById(medication_id);
    
        if (success) {
            System.out.println("\nMedication successfully deleted.");
        } else {
            System.out.println("\nError deleting medication. Please check the system.");
        }
    
        System.out.println("\n =======> Medication Deletion Completed! <======\n");
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
                break; 
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
    
        // enter patient ID
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
                break; 
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
        System.out.println("\n =======> Enter New Prescription <======\n");
    
        // step 1: enter doctor ID (loop until valid)
        int doctor_id;
        Doctor doctor;
        while (true) {
            doctor_id = inputDoctorIdOrZero(med_system, scanner);
            if (doctor_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            doctor = med_system.getDoctorById(doctor_id);
            System.out.println("\nSelected Doctor: " + doctor);
    
            if (confirmYesNo(scanner, "Confirm this doctor?")) {
                break;
            }
        }
    
        // step 2: enter patient ID (loop until valid)
        int patient_id;
        Patient patient;
        while (true) {
            patient_id = inputPatientIdOrZero(med_system, scanner);
            if (patient_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            patient = med_system.getPatientById(patient_id);
            System.out.println("\nSelected Patient: " + patient);
    
            if (confirmYesNo(scanner, "Confirm this patient?")) {
                break;
            }
        }
    
        // step 3: enter medication ID (loop until valid)
        int medication_id;
        Medication medication;
        while (true) {
            medication_id = inputMedicationIdOrZero(med_system, scanner);
            if (medication_id == 0) {
                System.out.println("Operation canceled.");
                return;
            }
            medication = med_system.getMedicationById(medication_id);
            System.out.println("\nSelected Medication: " + medication);
    
            if (confirmYesNo(scanner, "Confirm this medication?")) {
                break;
            }
        }
    
        // step 4: enter prescription exp.date (optional, press enter to use default +1 year)
        System.out.print("\nEnter prescription expiry date (YYYY-MM-DD), or press Enter to set 1 year from today: ");
        String expiry_date = scanner.nextLine().trim();
        
        boolean success;
        if (expiry_date.isEmpty()) {
            success = med_system.addPrescription(doctor, patient, medication);
        } else {
            if (!MedUtils.validateDateString(expiry_date)) {
                System.out.println("Invalid date format. Prescription not added.");
                return;
            }
            success = med_system.addPrescription(doctor, patient, medication, expiry_date);
        }
    
        // step 5: check if the prescription was successfully added
        if (!success) {
            System.out.println("\nFailed to add prescription. Please check the system.");
            return;
        }
    
        // step 6: display new prescription
        int new_prescription_id = med_system.getNumPrescriptions(); 
        Prescription new_prescription = med_system.getPrescriptionById(new_prescription_id);
    
        if (new_prescription != null) {
            System.out.println("\nSuccessfully added Prescription: " + new_prescription);
        } else {
            System.out.println("\nFailed to retrieve the newly added prescription. Please check the system.");
        }
    
        System.out.println("\n =======> Prescription Entry Completed! <======\n");
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
