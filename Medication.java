import java.time.LocalDate;

public class Medication {
    private static final String DEFAULT_NAME = "Unknown Medication";
    private static final double DEFAULT_DOSE = 0.0;
    private static final int DEFAULT_STOCK = 0;

    // id is final once set
    private final int id;
    private String name;
    private double dose;
    private int quantity_in_stock;
    private LocalDate expiry_date;

    // full constructor with specific values, but does validation
    // if values are invalid, set to default values
    // expiry_date must be string in YYYY-MM format
    public Medication(int id, String name, double dose, int quantity_in_stock, String expiry_date) {
        this.id = id;
        this.name = MedUtils.validateName(name) ? MedUtils.normalizeName(name) : DEFAULT_NAME;
        this.dose = MedUtils.validateDose(dose) ? dose : DEFAULT_DOSE;
        this.quantity_in_stock = MedUtils.validateStockQty(quantity_in_stock) ? quantity_in_stock : DEFAULT_STOCK;
        this.expiry_date = MedUtils.validateYearMonthString(expiry_date) ? MedUtils.stringMedExpToLocalDate(expiry_date) : MedUtils.localDateToMedExp(LocalDate.now());
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getDose() {
        return this.dose;
    }

    public int getQuantityInStock() {
        return this.quantity_in_stock;
    }

    public String getExpiryDateString() {
        return this.expiry_date.toString().substring(0, 7);
    }

    public LocalDate getExpiryDate() {
        return this.expiry_date;
    }

    // setter for name with validation
    // return true if the field was updated, false otherwise
    public boolean setName(String name) {
        if (MedUtils.validateName(name)) {
            this.name = MedUtils.normalizeName(name);
            return true;
        }       
        return false;
    }

    public boolean setDose(double dose) {
        if (MedUtils.validateDose(dose)) {
            this.dose = dose;
            return true;
        }
        return false;
    }

    public boolean setQuantityInStock(int quantity_in_stock) {
        if (MedUtils.validateStockQty(quantity_in_stock)) {
            this.quantity_in_stock = quantity_in_stock;
            return true;
        }
        return false;
    }

    public boolean setExpiryDateFromString(String expiry_date) {
        if (MedUtils.validateYearMonthString(expiry_date)) {
            this.expiry_date = MedUtils.stringMedExpToLocalDate(expiry_date);
            return true;
        }
        return false;
    }
    
    /**
     * Updates the medication's fields if new values are specified.
     * If a new value is not specified (null for name/expiry_date, -1 for dose/quantity_in_stock),
     * the old value is retained.
     *
     * @param name              the new name (null to keep the old name)
     * @param dose              the new dose (-1 to keep the old dose)
     * @param quantity_in_stock the new quantity in stock (-1 to keep the old quantity)
     * @param expiry_date       the new expiry date in "YYYY-MM" format (null to keep the old expiry date)
     * @return true if all updates were successful, false otherwise
     */
    public boolean updateFields(String name, double dose, int quantity_in_stock, String expiry_date) {
        // Update name if specified
        if (name != null && !this.setName(name)) {
            return false;
        }
        // Update dose if specified
        if (dose != -1 && !this.setDose(dose)) {
            return false;
        }
        // Update quantity in stock if specified
        if (quantity_in_stock != -1 && !this.setQuantityInStock(quantity_in_stock)) {
            return false;
        }
        // Update expiry date if specified
        if (expiry_date != null && !this.setExpiryDateFromString(expiry_date)) {
            return false;
        }
        // All updates were successful
        return true;
    }

    public String toString() {
        return "Medication[" + this.id + ", " + this.name + ", " + MedUtils.formatDoseNumber(this.dose, 3) + ", Stock: " 
                + this.quantity_in_stock + ", Expiry: " + this.getExpiryDateString() + "]";
    }
}
