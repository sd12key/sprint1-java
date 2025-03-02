import java.util.List;

public class MedicationTrackingSystemTest {
    public static void main(String[] args) {
        
        MedicationTrackingSystem med_system = new MedicationTrackingSystem();
        System.out.println("\n === Testing MedicationTrackingSystem Class ===\n");
        
        System.out.println("\n === Loading Medications from CSV ===");
        FileUtils.loadMedicationsFromCSV(med_system, "medications.csv");
        System.out.println("\n === Medications Loaded ===");
        for (Medication medication : med_system.getMedications()) {
            System.out.println(medication);
        }

        System.out.println("\n === Loading Patients from CSV ===");
        FileUtils.loadPatientsFromCSV(med_system, "patients.csv");
        System.out.println("\n === Patients Loaded ===");
        for (Patient patient : med_system.getPatients()) {
            System.out.println(patient);
        }

        System.out.println("\n === Loading Doctors from CSV ===");
        FileUtils.loadDoctorsFromCSV(med_system, "doctors.csv");
        System.out.println("\n === Doctors Loaded ===");
        for (Doctor doctor : med_system.getDoctors()) {
            System.out.println(doctor);
        }

        System.out.println("\n === Loading Prescriptions from CSV ===");
        FileUtils.loadPrescriptionsFromCSV(med_system, "prescriptions.csv");
        System.out.println("\n === Prescriptions Loaded ===");
        for (Prescription prescription : med_system.getPrescriptions()) {
            System.out.println(prescription);
        }

        FileUtils.clear();
        List<String> system_report = med_system.fullSystemReport();
        system_report.forEach(line -> System.out.println(line));

    }

 



}
