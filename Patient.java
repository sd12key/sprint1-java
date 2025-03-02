import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    // in order to make the Patient class database-friendly we 
    // probably have to have patient_id property as well here 
    // (which would be auto-incremented in the patients table)
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    // full constructor
    public Patient(int id, String name, int age, String phone_number) {
        super(id, name, age, phone_number);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // getters
    public List<Medication> getMedications() {
        return this.medications;
    }

    public List<Prescription> getPrescriptions() {
        return this.prescriptions;
    }

    // getters of just ids of medications and prescriptions (in a list)
    public List<Integer> getMedicationIds() {
        List<Integer> medication_ids = new ArrayList<>();
        for (Medication medication : this.medications) {
            medication_ids.add(medication.getId());
        }
        return medication_ids;
    }

    public List<Integer> getPrescriptionIds() {
        List<Integer> prescription_ids = new ArrayList<>();
        for (Prescription prescription : this.prescriptions) {
            prescription_ids.add(prescription.getId());
        }
        return prescription_ids;
    }

    // setters
    // the only setter needed is addPrescription
    // since it will add the corresponding medication as well
    public boolean addPrescription(Prescription prescription) {
        // check if the prescription id already exists
        // checking by ID, but could also check by reference
        for (Prescription current_prescription : this.prescriptions) {
            if (current_prescription.getId() == prescription.getId()) {
                // such prescription id already exists
                // do not allow to add!
                return false;  
            }
        }
        // try to add medication, but only continue if successful
        // if such medication already exists (same id), will return false
        if (!this.addMedication(prescription.getMedication())) {
            return false; 
        }
        // if medication was successfully added, add the prescription
        this.prescriptions.add(prescription);
        return true; 
    }
    
    // private method to add medication
    // this method is called from addPrescription    
    private boolean addMedication(Medication medication) {
        // check if the medication id already exists
        // checking by ID, but could also check by reference
        for (Medication current_medication : this.medications) {
            if (current_medication.getId() == medication.getId()) {
                // such medication id already exists
                // do not allow to add!
                return false;
            }
        }
        this.medications.add(medication);
        return true; 
    }

    // remove prescription from patient, can be useful if prescription expired
    // returns true if prescription was found and was removed
    public boolean removePrescription(Prescription prescription) {
        // we check if prescriotion was found by id the list
        // if found, we remove it and remove the corresponding medication, returning true
        // otherwise, we return false
        if (this.prescriptions.removeIf(current_prescription -> current_prescription.getId() == prescription.getId())) {
            removeMedication(prescription.getMedication()); 
            return true;
        }
        return false;
    }

    // private method to remove medication
    // this method is called from removePrescription
    private void removeMedication(Medication medication) {
        this.medications.removeIf(current_medication -> current_medication.getId() == medication.getId());
    }
    
    public String toString() {
        return "Patient[" + super.getId() + ", " + super.getName() + ", Age: " + super.getAge() + ", Phone: " + super.getPhoneNumber() + ", Medications: " + this.medications.size() 
                + ", Prescriptions: " + this.prescriptions.size() + "]";
    }
}
