/**
* The Person class represents a generic person in the Pharmacy Management System.
* It serves as the superclass for both Patient and Doctor classes.
* This class contains common attributes such as ID, name, age, and phone number.
* @author sd12key
*/
public class Person {
    private static final String DEFAULT_NAME = "Unknown Name";
    private static final String DEFAULT_PHONE_NUMBER = "0000000000";
    private static final int DEFAULT_AGE = 0;
    
    /**
     * unique identifier for the person.
     * cannot be changed after creation.
     * usually auto-incremented in the database,
     * serves as primary key
     */
    private final int id;
    /**
     * Name of the person.
     */
    private String name;
    /**
     * Age of the person.
     */
    private int age;
    /**
     * Phone number of the person.
     */
    private String phone_number;

    /**
     * Constructs a new Person with the specified ID, name, age, and phone number.
     * But does validation on the fields.
     * If the name is null or empty, it is set to "Unknown Name".
     * If the age is less than 0 or greater than 150, it is set to 0.
     * If the phone number is null or has less than 10 digits, it is set to "0000000000".
     * 
     * @param id           The unique identifier for the person.
     * @param name         The name of the person.
     * @param age          The age of the person.
     * @param phone_number The phone number of the person.
     */
    public Person(int id, String name, int age, String phone_number) {
        this.id = id;
        // validate and set name, if fields are invalid, set to defaults
        this.name = MedUtils.validateName(name) ? MedUtils.normalizeName(name) : DEFAULT_NAME;
        this.age = MedUtils.validateAge(age) ? age : DEFAULT_AGE; 
        this.phone_number = MedUtils.validatePhoneNumber(phone_number) ? MedUtils.normalizePhoneNumber(phone_number) : DEFAULT_PHONE_NUMBER;
    }
    
    //getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getPhoneNumber() {
        return this.phone_number;
    }

    //setters with validation,
    //return true if the field was updated, false otherwise
    // not validated fields are not updated
    public boolean setName(String name) {
        if (MedUtils.validateName(name)) {
            this.name = MedUtils.normalizeName(name);
            return true;
        }       
        return false;
    }

    public boolean setAge(int age) {
        if (MedUtils.validateAge(age)) {
            this.age = age;
            return true;
        }
        return false;
    }

    public boolean setPhoneNumber(String phone_number) {
        if (MedUtils.validatePhoneNumber(phone_number)) {
            this.phone_number = MedUtils.normalizePhoneNumber(phone_number);
            return true;
        }
        return false;
    }

    /**
     * Updates the common fields (name, age, phoneNumber) if specified.
     *
     * @param name        the new name (null to keep the old name)
     * @param age         the new age (-1 to keep the old age)
     * @param phoneNumber the new phone number (null to keep the old phone number)
     * @return true if all updates were successful, false otherwise
     */
    public boolean updateCommonFields(String name, int age, String phoneNumber) {
        if (name != null && !this.setName(name)) {
            return false;
        }
        if (age != -1 && !this.setAge(age)) {
            return false;
        }
        if (phoneNumber != null && !this.setPhoneNumber(phoneNumber)) {
            return false;
        }
        return true;
    }

    
    // toString() - Skips phone if null, skips age if 0
    public String toString() {
        return ("Person[ID: " + this.id + ", Name: " + this.name + ", Age: " + this.age + ", Tel: " + this.phone_number + "]");
    }
}