import java.time.LocalDate;

public class Prescription {
    // all fields are final once set, so prescription onject cannot be modified
    // which make sense, since prescription is a legal document
    private final int id;
    private final Doctor doctor;
    private final Patient patient;
    private final Medication medication; 
    private final LocalDate prescription_expiry;

    // constructor with prescription_expiry set to 1 year from now
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication; 
        this.prescription_expiry = LocalDate.now().plusYears(1);
        // add this prescription to the patient's list of prescriptions
        this.patient.addPrescription(this);
    }

    // constructor with specific expiry date
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication, LocalDate prescription_expiry) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.prescription_expiry = prescription_expiry;
        // add this prescription to the patient's list of prescriptions
        this.patient.addPrescription(this);
    }

    // getters
    public int getId() {
        return this.id;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public Medication getMedication() {
        return this.medication;  
    }

    public LocalDate getPrescriptionExpiry() {
        return this.prescription_expiry;
    }

    // no setters, since all fields are final

    // toString() method
    public String toString() {
        return "Prescription[" + this.id + ", Doctor: " + doctor.getName() 
                + ", Patient: " + patient.getName() + ", Medication: " 
                + medication.getName() + ", Expiry: " + prescription_expiry + "]";
    }
}
