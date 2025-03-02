import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private static final String DEFAULT_SPECIALIZATION = "Unknown Specialization";
    // in order to make the Doctor class database-firlendly we 
    // probably have to have doctor_id here 
    // (which would be auto-incremented in the doctors table)
    private String specialization;
    private List<Patient> patients;

    public Doctor(int id, String name, int age, String phone_number, String specialization) {
        super(id, name, age, phone_number);
        this.specialization = MedUtils.validateName(specialization) ? MedUtils.normalizeName(specialization) : DEFAULT_SPECIALIZATION;
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
    public boolean setSpecialization(String specialization) {
        if (MedUtils.validateName(specialization)) {
            this.specialization = MedUtils.normalizeName(specialization);
            return true;
        }
        return false;
    }

    /**
     * Updates the doctor's fields, including specialization.
     *
     * @param name           the new name (null to keep the old name)
     * @param age            the new age (-1 to keep the old age)
     * @param phoneNumber    the new phone number (null to keep the old phone number)
     * @param specialization the new specialization (null to keep the old specialization)
     * @return true if all updates were successful, false otherwise
     */
    // extending the updateCommonFields method from the parent class
    public boolean updateFields(String name, int age, String phoneNumber, String specialization) {
        // Update common fields using the parent class method
        if (!super.updateCommonFields(name, age, phoneNumber)) {
            return false;
        }
        // Update specialization if specified
        if (specialization != null && !this.setSpecialization(specialization)) {
            return false;
        }
        return true;
    }

    // add patient
    public boolean addPatient(Patient patient) {
        if (patient == null) return false;
        
        // first check if the patient is already in the doctor's list
        if (this.patients.contains(patient)) return false;

        // if patient not in the list, add!
        this.patients.add(patient);
        return true;
    }

    // remove patient
    // returns true if patient was found and was removed
    // false otherwise
    public boolean removePatient(Patient patient) {
        if (patient == null) return false;
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
