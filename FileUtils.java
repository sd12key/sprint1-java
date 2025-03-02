import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

public class FileUtils {

    public static void loadMedicationsFromCSV(MedicationTrackingSystem med_system, String filename) {
        File file = Paths.get("data", filename).toFile();

        try {
            Scanner scanner = new Scanner(file);

            // skip 1st line with header
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

    public static void loadDoctorsFromCSV(MedicationTrackingSystem med_system, String filename) {
        File file = Paths.get("data", filename).toFile();

        try {
            Scanner scanner = new Scanner(file);

            // skip 1st line with header
            if (scanner.hasNextLine()) scanner.nextLine(); 

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                if (values.length < 5) continue;

                String name = values[1].trim();
                int age = Integer.parseInt(values[2].trim());
                String phone_number = values[3].trim();
                String specialization = values[4].trim();
                med_system.addDoctor(name, age, phone_number, specialization);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + filename);
        }
    }

    public static void loadPatientsFromCSV(MedicationTrackingSystem med_system, String filename) {
        File file = Paths.get("data", filename).toFile();

        try {
            Scanner scanner = new Scanner(file);

            // skip 1st line with header            
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                if (values.length < 4) continue;

                String name = values[1].trim();
                int age = Integer.parseInt(values[2].trim());
                String phone_number = values[3].trim();

                med_system.addPatient(name, age, phone_number);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + filename);
        }
    }

    public static void loadPrescriptionsFromCSV(MedicationTrackingSystem med_system, String filename) {
        File file = Paths.get("data", filename).toFile();

        try {
            Scanner scanner = new Scanner(file);

            // Skip the first line (header)
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] values = scanner.nextLine().split(",");
                if (values.length < 5) continue; // Ensure all required columns are present

                int medication_id = Integer.parseInt(values[1].trim());
                int doctor_id = Integer.parseInt(values[2].trim());
                int patient_id = Integer.parseInt(values[3].trim());
                String expiry_date_str = values[4].trim();

                // convert expiry_date string to LocalDate
                LocalDate expiry_date = MedUtils.stringToLocalDate(expiry_date_str);

                // get references from the system
                Doctor doctor = med_system.getDoctorById(doctor_id);
                Patient patient = med_system.getPatientById(patient_id);
                Medication medication = med_system.getMedicationById(medication_id);

                // check that all references exist before adding
                if (doctor == null || patient == null || medication == null) {
                    System.out.println("Skipping prescription - Missing reference (Doctor: " + doctor_id +
                            ", Patient: " + patient_id + ", Medication: " + medication_id + ")");
                    continue;
                }

                // add the prescription (ID is auto-generated)
                med_system.addPrescription(doctor, patient, medication, expiry_date);
                // System.out.println("Added Prescription: Doctor ID " + doctor_id + ", Patient ID " + patient_id +
                        // ", Medication ID " + medication_id);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + filename);
        }
    }

    // workaround to clear the console
    public static void clear() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

}
