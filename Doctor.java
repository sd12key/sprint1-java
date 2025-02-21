import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private int specialization_id;
    private List<Patient> patients;

    public Doctor(int id, String name, int age, String phone_number, int specialization_id) {
        super(id, name, age, phone_number);
        this.specialization_id = specialization_id;
        this.patients = new ArrayList<>();
    }

    public int getSpecializationId() {
        return specialization_id;
    }

    // getter of patient object list
    public List<Patient> getPatients() { 
        return patients; 
    }

    // getter of just ids of patients (in a list)
    public List<Integer> getPatientIds() {
        List<Integer> patient_ids = new ArrayList<>();
        for (Patient patient : this.patients) {
            patient_ids.add(patient.getId());
        }
        return patient_ids;
    }

    // setters
    public boolean addPatient(Patient patient) {
        if (!this.patients.contains(patient)) {
            this.patients.add(patient);
            return true; 
        }
        return false; 
    }

    // remove patient
    // returns true if patient was found and was removed
    // false otherwise
    public boolean removePatient(Patient patient) {
        return this.patients.remove(patient);  
    }

    // remove patient by id
    public boolean removePatientById(int patient_id) {
        for (Patient patient : this.patients) {
            if (patient.getId() == patient_id) {
                return this.patients.remove(patient);
            }
        }
        return false;
    }

    // remove all patients
    public void removeAllPatients() {
        this.patients.clear();
    }
    
    public String toString() {
        return "Doctor[ID: " + super.getId() + ", " + super.getName() + ", Specialization ID: " + this.specialization_id + 
               ", Number of Patients: " + this.patients.size() + "]";
    }
}
