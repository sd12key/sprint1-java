import java.time.LocalDate;

public class Prescription {
    private final int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication; 
    private LocalDate prescription_expiry;

    // constructor
    // actually, these better have to be clones of the objects passed in, so it's is a "snapshot"
    // or deep copies, so that the objects inside prescription can't be changed from outside
    // but for now, we'll just pass in the objects
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication, LocalDate prescription_expiry) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.prescription_expiry = prescription_expiry;

        // make sure the patient is managed by the doctor
        if (!doctor.getPatients().contains(patient)) {
            doctor.addPatient(patient);
        }

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
                + " (" + doctor.getId() + ")" + ", Patient: " + patient.getName() 
                + " (" + patient.getId() + ")" + ", Medication: " 
                + medication.getName() + " (" + medication.getId() + ")" 
                + ", Expiry: " + this.prescription_expiry + "]";
    }
}
