import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    // in order to make the Doctor class database-firlendly we 
    // probably have to have doctor_id here 
    // (which would be auto-incremented in the doctors table)
    private String specialization;
    private List<Patient> patients;

    public Doctor(int id, String name, int age, String phone_number, String specialization) {
        super(id, name, age, phone_number);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    public String getSpecialization() {
        return this.specialization;
    }

    // getter of patient object list
    public List<Patient> getPatients() { 
        return this.patients; 
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
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

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
        return "Doctor[ID: " + super.getId() + ", " + super.getName() + ", Specialization: " + this.specialization + 
               ", Number of Patients: " + this.patients.size() + "]";
    }
}
