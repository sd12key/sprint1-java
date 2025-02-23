import java.time.LocalDate;

public class Medication {
    // id is final once set
    private final int id;
    private String name;
    private double dose;
    private int quantity_in_stock;
    private LocalDate expiry_date;

    public Medication(int id, String name, double dose, int quantity_in_stock, LocalDate expiry_date) {
        this.id = id;
        this.name = name.trim().replaceAll("\\s+", " ");
        this.dose = dose;
        this.quantity_in_stock = quantity_in_stock;
        this.expiry_date = expiry_date; 
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

    public LocalDate getExpiryDate() {
        return this.expiry_date;
    }

    // not sure if we need all these setters
    public void setName(String name) {
        this.name = name.trim().replaceAll("\\s+", " ");
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public void setQuantityInStock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public void setExpiryDate(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String toString() {
        return "Medication[" + this.id + ", " + this.name + ", " + Utilities.formatNumber(this.dose, 3) + ", Stock: " 
                + this.quantity_in_stock + ", Expiry: " + this.expiry_date + "]";
    }
}
