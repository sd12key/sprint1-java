/**
* The Person class represents a generic person in the Pharmacy Management System.
* It serves as the superclass for both Patient and Doctor classes.
* This class contains common attributes such as ID, name, age, and phone number.
* @author sd12key
*/
public class Person {
    
    /**
     * unique identifier for the person.
     * immutable, cannot be changed after creation.
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
     * 
     * @param id           The unique identifier for the person.
     * @param name         The name of the person.
     * @param age          The age of the person.
     * @param phone_number The phone number of the person.
     */
    public Person(int id, String name, int age, String phone_number) {
        this.id = id;
        this.name = name.trim().replaceAll("\\s+", " ");
        this.age = age;
        phone_number = phone_number.trim().replaceAll("\\D", "");
        this.phone_number = phone_number.substring(0, Math.min(10, phone_number.length()));
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

    //setters
    public void setName(String name) {
        this.name = name.trim().replaceAll("\\s+", " ");
    }

    public void setAge(int age) {
        this.age = age;

    }

    public void setPhoneNumber(String phone_number) {
        phone_number = phone_number.trim().replaceAll("\\D", "");
        this.phone_number = phone_number.substring(0, Math.min(10, phone_number.length()));
    }

    // toString() - Skips phone if null, skips age if 0
    public String toString() {
        return ("Person[ID: " + this.id + ", Name: " + this.name + ", Age: " + this.age + ", Tel: " + this.phone_number + "]");
    }
}