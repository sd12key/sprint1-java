/**
 * the Specialization class represents a doctor's 
 * medical specialization in the pharmacy management system.
 * @author sd12key
 */
public class Specialization {
    private final int id;
    private String name; 
    
    public Specialization(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Specialization[" + this.id + ", " + this.name + "]";
    }
}
