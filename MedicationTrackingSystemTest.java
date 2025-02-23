import java.io.*;

public class MedicationTrackingSystemTest {
    public static void main(String[] args) {
        
        MedicationTrackingSystem system = new MedicationTrackingSystem();
        System.out.println("\n === Testing MedicationTrackingSystem Class ===\n");
        
        System.out.println(system);
        

    }

    private static final String FOLDER_PREFIX = ".\\csv\\";
    private static final String DOCTORS_FILE = FOLDER_PREFIX + "doctors.csv";
    private static final String PATIENTS_FILE = FOLDER_PREFIX + "patients.csv";
    private static final String MEDICATIONS_FILE = FOLDER_PREFIX + "medications.csv";
    private static final String PRESCRIPTIONS_FILE = FOLDER_PREFIX + "prescriptions.csv";
   



}
