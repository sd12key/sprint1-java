/**
* The Person class represents a generic person in the Pharmacy Management System.
* It serves as the superclass for both Patient and Doctor classes.
* This class contains common attributes such as ID, name, age, and phone number.
* 
* @author YourName
* @version 1.0
* @since 2025-02-20
*/
public class Person {
    
    /**
     * Unique identifier for the person.
     */
    private int id;
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
        this.name = name;
        this.age = age;
        this.phone_number = phone_number;
    }

    /**
     * Constructs a new Person with the specified ID and name.
     * The age is set to 0, and the phone number is set to null.
     * This constructor is useful when only the ID and name are known.
     * 
     * @param id   The unique identifier for the person.
     * @param name The name of the person.
     */
    public Person(int id, String name) {
        this(id, name, 0, null);
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
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    // toString() - Skips phone if null, skips age if 0
    public String toString() {
        String result = "Person[" + id + ", " + name;
        if (age != 0) result += ", " + age;
        if (phone_number != null) result += ", " + phone_number;
        result += "]";
        return result;
    }

}