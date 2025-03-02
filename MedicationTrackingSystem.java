import java.time.LocalDate;
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

    public List<Patient> getPatients() {
        return this.patients;
    }

    public List<Doctor> getDoctors() {
        return this.doctors;
    }

    public List<Medication> getMedications() {
        return this.medications;
    }

    public List<Prescription> getPrescriptions() {
        return this.prescriptions;
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
            int next_id = (patients.isEmpty()) ? 1 : patients.get(patients.size() - 1).getId() + 1;
            this.patients.add(new Patient(next_id, name, age, phone_number));
            return true;
        }
        return false;
    }
    
    public boolean editPatientById(int patient_id, String name, int age, String phone_number) {
        for (Patient patient : this.patients) {
            if (patient.getId() == patient_id) {
                // try to change the name, age, phone_number
                return patient.updateCommonFields(name, age, phone_number);
            }
        } 
        // looped through all patients, but patient not found
        return false;
    }

    public boolean editPatient(Patient patient, String name, int age, String phone_number) {
        // Check if the patient is in the list
        if (this.patients.contains(patient)) {
        // Update the patient's fields
            return patient.updateCommonFields(name, age, phone_number);
        }
        // Patient not found
        return false;
    }
    
    /**
     * Removes a patient from the system by their instance.
     *
     * @param patient the patient to remove
     * @return true if the patient was found and removed, false otherwise
     */
    public boolean removePatient(Patient patient) {
        if (patient == null) return false;
        return this.patients.remove(patient);
    }

    /**
     * Removes a patient from the system by their ID.
     *
     * @param patient_id the ID of the patient to remove
     * @return true if the patient was found and removed, false otherwise
     */
    public boolean removePatientById(int patient_id) {
        for (Patient patient : this.patients) {
            if (patient.getId() == patient_id) {
                return this.patients.remove(patient);
            }
        }
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
            int next_id = (doctors.isEmpty()) ? 1 : doctors.get(doctors.size() - 1).getId() + 1;
            this.doctors.add(new Doctor(next_id, name, age, phone_number, specialization));
            return true;
        }
        return false;
    }

    public boolean editDoctorById(int doctor_id, String name, int age, String phone_number, String specialization) {
        for (Doctor doctor : this.doctors) {
            if (doctor.getId() == doctor_id) {
                // try to change the name, age, phone_number and specialization
                return doctor.updateFields(name, age, phone_number, specialization);
            }
        } 
        // looped through all doctors, but doctor with this id was not found
        return false;
    }

    public boolean editDoctor(Doctor doctor, String name, int age, String phone_number, String specialization) {
        if (this.doctors.contains(doctor)) {
            // try to change the name, age, phone_number and specialization
            return doctor.updateFields(name, age, phone_number, specialization);
        }
        // doctor was not found
        return false;
    }

    /**
     * Removes a doctor from the system by their instance.
     *
     * @param doctor the doctor to remove
     * @return true if the doctor was found and removed, false otherwise
     */
    public boolean removeDoctor(Doctor doctor) {
        if (doctor == null) return false;
        return this.doctors.remove(doctor);
    }

    /**
     * Removes a doctor from the system by their ID.
     *
     * @param doctor_id the ID of the doctor to remove
     * @return true if the doctor was found and removed, false otherwise
     */
    public boolean removeDoctorById(int doctor_id) {
        for (Doctor doctor : this.doctors) {
            if (doctor.getId() == doctor_id) {
                return this.doctors.remove(doctor);
            }
        }
        return false;
    }

    // assign patient to doctor
    public boolean assignPatientToDoctorById(int doctor_id, int patient_id) {
        Doctor doctor = getDoctorById(doctor_id);
        Patient patient = getPatientById(patient_id);
        if (doctor == null || patient == null) {
            return false; 
        }
        return doctor.addPatient(patient); 
    }

    // assign patient to doctor by reference, but checking that ids are in the system
    // also checking if the objects are in the system
    public boolean assignPatientToDoctor(Patient patient, Doctor doctor) {
        if (patient == null || doctor == null) return false;
        // check that both IDs exist in the system
        if (getPatientById(patient.getId()) == null || getDoctorById(doctor.getId()) == null) {
            // return false if the patient or doctor is not in the system
            return false; 
        }
        return assignPatientToDoctorById(doctor.getId(), patient.getId());
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
            int next_id = (medications.isEmpty()) ? 1 : medications.get(medications.size() - 1).getId() + 1;
            this.medications.add(new Medication(next_id, name, dose, quantity_in_stock, expiry_date));
            return true;
        }
        return false;
    }

    /**
     * Edits a medication record by ID.
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
    public boolean editMedicationById(int medication_id, String name, double dose, int quantity_in_stock, String expiry_date) {
        for (Medication medication : this.medications) {
            if (medication.getId() == medication_id) {
                // update logic is in Medication class
                return medication.updateFields(name, dose, quantity_in_stock, expiry_date);
            }
        }
        // Medication not found
        return false;
    }

    // edit medication by class instance
    public boolean editMedication(Medication medication, String name, double dose, int quantity_in_stock, String expiry_date) {
        if (this.medications.contains(medication)) {
            // update logic is in Medication class
            return medication.updateFields(name, dose, quantity_in_stock, expiry_date);
        }
        // Medication not found
        return false;
    }

    /**
     * Removes a medication from the system by its instance.
     *
     * @param medication the medication to remove
     * @return true if the medication was found and removed, false otherwise
     */
    public boolean removeMedication(Medication medication) {
        if (medication == null) {
            return false;
        }
        return this.medications.remove(medication);
    }

    /**
    * Removes a medication from the system by its ID.
    *
    * @param medication_id the ID of the medication to remove
    * @return true if the medication was found and removed, false otherwise
    */
    public boolean removeMedicationById(int medication_id) {
        for (Medication medication : this.medications) {
            if (medication.getId() == medication_id) {
                return this.medications.remove(medication);
            }
        }
        return false;
    }

    /**
     * Checks for expired medications in the system.
     *
     * @return a list of expired Medication objects
     */
    public List<Medication> checkForExpiredMeds() {
        List<Medication> expired_meds = new ArrayList<>();
        LocalDate current_date = LocalDate.now();
        for (Medication medication : this.medications) {
            if (medication.getExpiryDate().isBefore(current_date)) {
                expired_meds.add(medication); // Add expired medication to the list
            }
        }
        return expired_meds;
    }

    // restock medication by id by adding amount
    public boolean restockMedicationById(int medication_id, int amount) {
        if (amount <= 0) return false; 
        Medication medication = getMedicationById(medication_id);
        if (medication == null) return false;
        medication.setQuantityInStock(medication.getQuantityInStock() + amount);
        return true;
    }

    // restock medication by reference by adding amount
    public boolean restockMedication(Medication medication, int amount) {
        if (medication == null || amount <= 0) return false;
        // check this exact medication object exists in the system
        if (!this.medications.contains(medication)) {
            return false;
        }
        return restockMedicationById(medication.getId(), amount);
    }   

    public void restockAllMedications(int amount) {
        if (amount <= 0) return; 
        for (Medication medication : this.medications) {
            medication.setQuantityInStock(medication.getQuantityInStock() + amount);
        }
    }

    /**
     * Generates a report of expired medications.
     *
     * @return an ArrayList of strings, where each string represents a line in the report
     */
    public List<String> checkForExpiredMedsReport() {
        List<Medication> expired_meds = this.checkForExpiredMeds(); 
        List<String> expired_meds_report = new ArrayList<>();

        // Add a header to the report
        expired_meds_report.add("--> ======= Expired Medications Report =======");
        // Add details for each expired medication
        for (Medication medication : expired_meds) {
            expired_meds_report.add(medication.toString());
        }
        expired_meds_report.add("<-- ======== End Expired Meds Report =========");

        return expired_meds_report;
    }

    // overloaded method with default expiry date in 1 year
    public boolean addPrescription(Doctor doctor, Patient patient, Medication medication) {
        return addPrescription(doctor, patient, medication, LocalDate.now().plusYears(1));
    }
    
    // overloaded method with date as a String ("YYYY-MM-DD")
    public boolean addPrescription(Doctor doctor, Patient patient, Medication medication, String expiry_full_date) {
        if (!MedUtils.validateDateString(expiry_full_date)) return false; 
        LocalDate parsedDate = MedUtils.stringToLocalDate(expiry_full_date);
        return addPrescription(doctor, patient, medication, parsedDate);
    }
    
    // overloaded method with expiry date as a LocalDate
    public boolean addPrescription(Doctor doctor, Patient patient, Medication medication, LocalDate expiry_date) {
        if (doctor == null || patient == null || medication == null || expiry_date == null) return false;
    
        // check references exist in the system
        if (!this.doctors.contains(doctor) || !this.patients.contains(patient) || !this.medications.contains(medication)) {
            return false;
        }
    
        // calculate next prescription ID
        int next_id = (prescriptions.isEmpty()) ? 1 : prescriptions.get(prescriptions.size() - 1).getId() + 1;
    
        // create the prescription with the given expiry date
        Prescription prescription = new Prescription(next_id, doctor, patient, medication, expiry_date);
        this.prescriptions.add(prescription);
    
        return true;
    }

    // get prescriptions by doctor reference
    public List<Prescription> getPrescriptionsByDoctor(Doctor doctor) {
    if (doctor == null || !this.doctors.contains(doctor)) return new ArrayList<>();
    // call overloaded method with doctor ID!
    return getPrescriptionsByDoctor(doctor.getId()); 
    }

    // overloaded method to get prescriptions by doctor ID
    public List<Prescription> getPrescriptionsByDoctor(int doctor_id) {
    List<Prescription> result = new ArrayList<>();
    for (Prescription prescription : this.prescriptions) {
        if (prescription.getDoctor().getId() == doctor_id) {
            result.add(prescription);
        }
    }
    return result;
    }

    // generate report (list of strings) of prescriptions by doctor ID
    public List<String> checkPrescriptionsByDoctorReport(int doctor_id) {
        // check if doctor exists
        if (getDoctorById(doctor_id) == null) {
            return List.of("Invalid Doctor ID: " + doctor_id);
        }
        List<Prescription> prescriptions = getPrescriptionsByDoctor(doctor_id);
        List<String> report = new ArrayList<>();
    
        report.add("--> ======= Prescription Report for Doctor ID: " + doctor_id + " =======");
    
        if (prescriptions.isEmpty()) {
            report.add("No prescriptions found.");
        } else {
            for (Prescription prescription : prescriptions) {
                report.add(prescription.toString());
            }
        }
        report.add("<-- ========== End of Prescription Report ===========");
        return report;
    }
    
    // overloaded version to use Doctor object
    public List<String> checkPrescriptionsByDoctorReport(Doctor doctor) {
        if (doctor == null || !this.doctors.contains(doctor)) {
            return List.of("Invalid Doctor reference.");
        }
        return checkPrescriptionsByDoctorReport(doctor.getId());
    }

    // system report of all medications
    public List<String> medicationReport() {
        List<Medication> medications = searchMedicationsByName(""); // Get all medications
        List<String> report = new ArrayList<>();
    
        report.add("--> ======= Medication Report =======");
    
        if (medications.isEmpty()) {
            report.add("No medications found.");
        } else {
            for (Medication medication : medications) {
                // Collect all prescription IDs where this medication object is used
                List<Integer> prescriptionIds = new ArrayList<>();
                for (Prescription prescription : this.prescriptions) {
                    if (prescription.getMedication() == medication) { // Compare object references
                        prescriptionIds.add(prescription.getId());
                    }
                }
    
                // Format prescription list as [x,x,x] or [none]
                String prescriptionList = prescriptionIds.isEmpty() ? "[none]" : prescriptionIds.toString();
    
                // Add formatted line
                report.add(medication.toString() + " Prescr" + prescriptionList);
            }
        }
    
        report.add("<-- ======= End of Medications Report ========");
    
        return report;
    }
 
    // system report of all patients    
    public List<String> patientReport() {
        List<Patient> patients = searchPatientsByName("");
        List<String> report = new ArrayList<>();
    
        report.add("--> ======= Patient Report =======");
    
        if (patients.isEmpty()) {
            report.add("No patients found.");
        } else {
            for (Patient patient : patients) {
                // get all doctor IDs assigned to this patient
                List<Integer> doctor_ids = new ArrayList<>();
                for (Doctor doctor : this.doctors) {
                    if (doctor.getPatients().contains(patient)) { 
                        doctor_ids.add(doctor.getId());
                    }
                }
    
                // get all medication IDs this patient is using
                List<Integer> medication_ids = new ArrayList<>();
                for (Medication medication : patient.getMedications()) {
                    medication_ids.add(medication.getId());
                }
    
                // get all prescription IDs assigned to this patient
                List<Integer> prescription_ids = new ArrayList<>();
                for (Prescription prescription : this.prescriptions) {
                    if (prescription.getPatient() == patient) { 
                        prescription_ids.add(prescription.getId());
                    }
                }
    
                // gormat Doc[x,x] Med[x,x,x] Prescr.[x,x]
                String doctor_list = doctor_ids.isEmpty() ? "[none]" : doctor_ids.toString();
                String medication_list = medication_ids.isEmpty() ? "[none]" : medication_ids.toString();
                String prescription_list = prescription_ids.isEmpty() ? "[none]" : prescription_ids.toString();
    
                // add formatted patient line to report
                report.add("Patient[" + patient.getId() + ", " + patient.getName() + ", Age: " 
                + patient.getAge() + ", Tel: " + patient.getPhoneNumber() + "] "
                + " Doc" + doctor_list + " Med" + medication_list + " Prescr" + prescription_list);
            }
        }
    
        report.add("<-- ======= End of Patient Report =========");
    
        return report;
    }

    public List<String> doctorReport() {
        List<Doctor> doctors = searchDoctorsByName(""); 
        List<String> report = new ArrayList<>();
    
        report.add("--> =========== Doctor Report ===========");
    
        if (doctors.isEmpty()) {
            report.add("No doctors found.");
        } else {
            for (Doctor doctor : doctors) {
                // get all patient IDs assigned to this doctor
                List<Integer> patient_ids = new ArrayList<>();
                for (Patient patient : doctor.getPatients()) {
                    patient_ids.add(patient.getId());
                }
    
                // Patients[x,x] or Patients[none]
                String patientList = patient_ids.isEmpty() ? "[none]" : patient_ids.toString();
    
                // Manually format doctor details instead of using toString()
                report.add("Doctor[" + doctor.getId() + ", " + doctor.getName() + ", Specialization: " 
                           + doctor.getSpecialization() + "] Patients" + patientList);
            }
        }
    
        report.add("<-- ========= End of Doctor Report ===========");
    
        return report;
    }

    // prescription report
    public List<String> prescriptionReport() {
        List<String> report = new ArrayList<>();
    
        report.add("--> ========== Prescription Report ==========");
    
        if (this.prescriptions.isEmpty()) {
            report.add("No prescriptions found.");
        } else {
            for (Prescription prescription : this.prescriptions) {
                report.add("Prescription[" + prescription.getId() + "] "
                           + "Doc[" + prescription.getDoctor().getName() + ", " + prescription.getDoctor().getId() + "] "
                           + "Patient[" + prescription.getPatient().getName() + ", " + prescription.getPatient().getId() + "] "
                           + "Med[" + prescription.getMedication().getName() + ", " + prescription.getMedication().getId() + "] "
                           + "Exp[" + prescription.getPrescriptionExpiry() + "]");
            }
        }
    
        report.add("<-- ====== End of Prescription Report ======");
    
        return report;
    }
    
    public List<String> fullSystemReport() {
        List<String> report = new ArrayList<>();
    
        report.add("--> ======= Full System Report =======");
        report.add("");
    
        // Add Medication Report
        report.addAll(medicationReport());
        report.add("");
    
        // Add Patient Report
        report.addAll(patientReport());
        report.add("");
    
        // Add Doctor Report
        report.addAll(doctorReport());
        report.add("");
    
        // Add Prescription Report
        report.addAll(prescriptionReport());
        report.add("");

        report.add("<-- ======= End of Full System Report ========");
    
        return report;
    }
    

    // toString() method, shows statistics
    public String toString() {
        return "MedicationTrackingSystem[Patients: " + this.patients.size() +
               ", Doctors: " + this.doctors.size() +
               ", Medications: " + this.medications.size() +
               ", Prescriptions: " + this.prescriptions.size() + "]";
    }
}