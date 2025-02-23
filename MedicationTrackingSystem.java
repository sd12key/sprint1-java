import java.io.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicationTrackingSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    // empty constructor
    // actually other constructors can be introduced, initializing from arrays
    // of patients, doctors, medications, specializations, prescriptions
    // or directly from csv files
    public MedicationTrackingSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // getters of statistics
    public int getNumPatients() {
        return this.patients.size();
    }

    public int getNumDoctors() {
        return this.doctors.size();
    }

    public int getNumMedications() {
        return this.medications.size();
    }

    public int getNumPrescriptions() {
        return this.prescriptions.size();
    }

    /**
     * Searches for patients by name.
     *
     * @param name       the name to search for
     * @param exact_match if true, performs an exact match search; if false, performs a partial match search
     * @return a list of patients matching the search criteria; empty list if no patients are found
     */
    public List<Patient> searchPatientsByName(String name, boolean exact_match) {
        List<Patient> result = new ArrayList<>();
        
        // normalize the search string
        name = name.trim().replaceAll("\\s+", " ");

        for (Patient patient : this.patients) {
            if (exact_match) {
                // exact match (case-insensitive)
                if (patient.getName().equalsIgnoreCase(name)) {
                    result.add(patient);
                }
            } else {
                // partial match (case-insensitive)
                if (patient.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(patient);
                }
            }
        }
        // in case no patients are found, return an empty list
        return result; 
    }

    // overloaded method, default exact_match is false, search for partial match
    public List<Patient> searchPatientsByName(String name) {
        return searchPatientsByName(name, false);
    }
   
    // adding a patient, no checks
    public void addPatientBypassChecks(String name, int age, String phone_number) {
        this.patients.add(new Patient(this.getNumPatients()+1, name, age, phone_number));
    }

    // adding a patient
    // with checks (non-empty name, age, phone number at least 10 digits)
    public boolean addPatientWithChecks(String name, int age, String phone_number) {
        name = name.trim().replaceAll("\\s+", " ");
        phone_number = phone_number.trim().replaceAll("\\D", "");
        // check for non-empty name, age in [0, 150], phone number at least 10 digits
        if (name == null || name.isEmpty() || age < 0 || age > 150 || phone_number.length() < 10) {
            return false; 
        }
        this.addPatientBypassChecks(name, age, phone_number);
        return true;
    }
       
    public void addDoctorBypassChecks(String name, int age, String phone_number, Specialization specialization) {
        this.doctors.add(new Doctor(this.getNumDoctors()+1, name, age, phone_number, specialization));
    }

    // toString() method, shows statistics
    public String toString() {
        return "MedicationTrackingSystem[Patients: " + patients.size() +
               ", Doctors: " + doctors.size() +
               ", Medications: " + medications.size() +
               ", Prescriptions: " + prescriptions.size() + "]";
    }
}