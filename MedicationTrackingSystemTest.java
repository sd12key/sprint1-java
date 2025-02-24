

public class MedicationTrackingSystemTest {
    public static void main(String[] args) {
        
        MedicationTrackingSystem med_system = new MedicationTrackingSystem();
        System.out.println("\n === Testing MedicationTrackingSystem Class ===\n");

        med_system.addPatient("John Doe", 30, "123-456-7890");
        med_system.addPatient("Jane Doe", 25, "123-456-7891");

        med_system.addDoctor("John Doctor", 30, "123-456-7890", "Cardiology");

        Doctor doctor_john = med_system.getDoctorById(1);
        System.out.println(doctor_john);

        Patient patient_john1 = med_system.getPatientById(1);
        Patient patient_john2 = med_system.getPatientById(1);
        System.out.println(patient_john1);
        System.out.println(patient_john2);

        System.out.println(patient_john1 == patient_john2);

        doctor_john.addPatient(patient_john1);
        System.out.println(doctor_john);
        doctor_john.addPatient(patient_john2);
        System.out.println(doctor_john);

        doctor_john.removePatient(patient_john1);
        System.out.println(doctor_john);

        Patient patient_jane = med_system.getPatientById(2);
        System.out.println(patient_jane);

        

        System.out.println(med_system);
        

    }

 



}
