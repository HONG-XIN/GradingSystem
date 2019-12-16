package main.backend;

import org.dizitart.no2.Document;

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

    //DB function
    //from RAM to DB
    public Document write(){
        Document NameDoc = new Document();
        NameDoc.put("firstName", getFirstName());
        NameDoc.put("middleName", getMiddleName());
        NameDoc.put("lastName", getLastName());
        return NameDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setFirstName((String) doc.get("firstName"));
            setMiddleName((String) doc.get("middleName"));
            setLastName((String) doc.get("lastName"));
        }
    }
}
