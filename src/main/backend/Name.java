package main.backend;

public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    //constructor
    public Name() {
        firstName = "";
        middleName = "";
        lastName = "";
    }

    public Name(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(String firstName, String middleName, String lastName){
        this(firstName, lastName);
        this.middleName = middleName;
    }

    //accessor
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    //mutator
    public void setFirstName(String firstName) {
        if(firstName.equals("")){
            this.firstName ="firstName";
        } else {
            this.firstName = firstName;
        }
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        if(lastName.equals("")){
            this.lastName = "lastName";
        } else {
            this.lastName = lastName;
        }
    }

    public void setName(String firstName, String middleName, String lastName) {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
    }

    // override function
    @Override
    public String toString() {
        return lastName + "," +
                middleName + " " +
                firstName;
    }
}
