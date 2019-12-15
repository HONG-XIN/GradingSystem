package main.backend;

public class BUemail {
    private String email;

    //constructor
    public BUemail() {
        email = " ";
    }

    public BUemail(String email) {
        setEmail(email);
    }

    //accessor
    public String getEmail() {
        return email;
    }

    //mutator
    public void  setEmail(String email) {
        if(email.indexOf('@') >= 0) {
            this.email = email;
        } else {
            this.email = email + "@bu.edu";
        }
    }
}
