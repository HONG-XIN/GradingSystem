package main.backend;

public class Name {
    String Lastname;
    String Firstname;
    String Middlename;
    public Name(String Firstname, String Middlename, String Lastname){
        this.Lastname = Lastname;
        this.Firstname = Firstname;
        this.Middlename = Middlename;
    }
    public Name(String Firstname, String Lastname){
        this.Lastname = Lastname;
        this.Firstname = Firstname;
    }
}
