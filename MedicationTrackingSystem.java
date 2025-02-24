import java.util.ArrayList;
import java.util.List;


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

    //getters of patients, doctors, medications, prescriptions by id
    public Patient getPatientById(int patient_id) {
        for (Patient patient : this.patients) {
            if (patient.getId() == patient_id) {
                return patient;
            }
        }
        return null;
    }

    public Doctor getDoctorById(int doctor_id) {
        for (Doctor doctor : this.doctors) {
            if (doctor.getId() == doctor_id) {
                return doctor;
            }
        }
        return null;
    }

    /**
     * Gets a medication by its ID.
     *
     * @param medication_id the id of the medication to retrieve
     * @return the medication if found, null otherwise
     */
    public Medication getMedicationById(int medication_id) {
        for (Medication medication : this.medications) {
            if (medication.getId() == medication_id) {
                return medication;
            }
        }
        return null;
    }

    public Prescription getPrescriptionById(int prescription_id) {
        for (Prescription prescription : this.prescriptions) {
            if (prescription.getId() == prescription_id) {
                return prescription;
            }
        }
        return null;
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
        name = MedUtils.normalizeName(name);

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
    
    // adds patient only if field validation is true
    // returns true if patient was added, false otherwise
    public boolean addPatient(String name, int age, String phone_number) {
        if (MedUtils.validateName(name) && MedUtils.validateAge(age) && MedUtils.validatePhoneNumber(phone_number)) {
            this.patients.add(new Patient(this.getNumPatients()+1, name, age, phone_number));
            return true;
        }
        return false;
    }
    
    public boolean editPatient(int patient_id, String name, int age, String phone_number) {
        for (Patient patient : this.patients) {
            if (patient.getId() == patient_id) {
                // try to change the name, age, phone_number
                return patient.updateCommonFields(name, age, phone_number);
            }
        } 
        // looped through all patients, but patient not found
        return false;
    }
    
    /**
     * Searches for doctors by name.
     *
     * @param name        the name to search for
     * @param exact_match if true, performs an exact match search; if false, performs a partial match search
     * @return a list of doctors matching the search criteria; empty list if no doctors are found
     */
    public List<Doctor> searchDoctorsByName(String name, boolean exact_match) {
        List<Doctor> result = new ArrayList<>();
        
        // Normalize the search string
        name = MedUtils.normalizeName(name);

        for (Doctor doctor : this.doctors) {
            if (exact_match) {
                // Exact match (case-insensitive)
                if (doctor.getName().equalsIgnoreCase(name)) {
                    result.add(doctor);
                }
            } else {
                // Partial match (case-insensitive)
                if (doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(doctor);
                }
            }
        }
        // In case no doctors are found, return an empty list
        return result;
    }

    // Overloaded method, default exact_match is false (partial match)
    public List<Doctor> searchDoctorsByName(String name) {
        return searchDoctorsByName(name, false);
    }

    // add doctor only if field validation is true
    // returns true if doctor was added, false otherwise
    public boolean addDoctor(String name, int age, String phone_number, String specialization) {
        if (MedUtils.validateName(name) && MedUtils.validateAge(age) && MedUtils.validatePhoneNumber(phone_number) && MedUtils.validateName(specialization)) {
            this.doctors.add(new Doctor(this.getNumDoctors()+1, name, age, phone_number, specialization));
            return true;
        }
        return false;
    }

    public boolean editDoctor(int doctor_id, String name, int age, String phone_number, String specialization) {
        for (Doctor doctor : this.doctors) {
            if (doctor.getId() == doctor_id) {
                // try to change the name, age, phone_number and specialization
                return doctor.updateFields(name, age, phone_number, specialization);
            }
        } 
        // looped through all doctors, but doctor with this id was not found
        return false;
    }

    /**
     * Searches for medications by name.
     *
     * @param name        the name to search for
     * @param exact_match if true, performs an exact match search; if false, performs a partial match search
     * @return a list of medications matching the search criteria; empty list if no medications are found
     */
    public List<Medication> searchMedicationsByName(String name, boolean exact_match) {
        List<Medication> result = new ArrayList<>();
        
        // Normalize the search string
        name = MedUtils.normalizeName(name);

        for (Medication medication : this.medications) {
            if (exact_match) {
                // Exact match (case-insensitive)
                if (medication.getName().equalsIgnoreCase(name)) {
                    result.add(medication);
                }
            } else {
                // Partial match (case-insensitive)
                if (medication.getName().toLowerCase().contains(name.toLowerCase())) {
                    result.add(medication);
                }
            }
        }
        // In case no medications are found, return an empty list
        return result;
    }

    // Overloaded method, default exact_match is false (partial match)
    public List<Medication> searchMedicationsByName(String name) {
        return searchMedicationsByName(name, false);
    }

    /**
     * Adds a medication only if field validation is true.
     *
     * @param name              the name of the medication
     * @param dose              the dose of the medication
     * @param quantity_in_stock the quantity in stock
     * @param expiry_date       the expiry date in "YYYY-MM" format
     * @return true if the medication was added, false otherwise
     */
    public boolean addMedication(String name, double dose, int quantity_in_stock, String expiry_date) {
        if (MedUtils.validateName(name) && MedUtils.validateDose(dose) && MedUtils.validateStockQty(quantity_in_stock) && MedUtils.validateYearMonthString(expiry_date)) {
            this.medications.add(new Medication(this.getNumMedications() + 1, name, dose, quantity_in_stock, expiry_date));
            return true;
        }
        return false;
    }

    /**
     * Edits a medication record.
     * If a new value is not specified (null for name/expiry_date, -1 for dose/quantity_in_stock),
     * the old value is retained.
     *
     * @param medication_id     the id of the medication to edit
     * @param name              the new name (null to keep the old name)
     * @param dose              the new dose (-1 to keep the old dose)
     * @param quantity_in_stock the new quantity in stock (-1 to keep the old quantity)
     * @param expiry_date       the new expiry date in "YYYY-MM" format (null to keep the old expiry date)
     * @return true if the medication was found and updated, false otherwise
     */
    public boolean editMedication(int medication_id, String name, double dose, int quantity_in_stock, String expiry_date) {
        for (Medication medication : this.medications) {
            if (medication.getId() == medication_id) {
                // Update name if specified
                if (name != null && !medication.setName(name)) {
                    return false;
                }
                // Update dose if specified
                if (dose != -1 && !medication.setDose(dose)) {
                    return false;
                }
                // Update quantity in stock if specified
                if (quantity_in_stock != -1 && !medication.setQuantityInStock(quantity_in_stock)) {
                    return false;
                }
                // Update expiry date if specified
                if (expiry_date != null && !medication.setExpiryDateFromString(expiry_date)) {
                    return false;
                }
                // All updates were successful
                return true;
            }
        }
        // Medication not found
        return false;
    }

    // toString() method, shows statistics
    public String toString() {
        return "MedicationTrackingSystem[Patients: " + this.patients.size() +
               ", Doctors: " + this.doctors.size() +
               ", Medications: " + this.medications.size() +
               ", Prescriptions: " + this.prescriptions.size() + "]";
    }
}