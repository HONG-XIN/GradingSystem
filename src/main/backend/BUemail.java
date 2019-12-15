package main.backend;

import org.dizitart.no2.Document;

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

    //Database Function
    //from RAM TO DB
    public Document write(){
        Document BUemailDoc = new Document();
        BUemailDoc.put("email", getEmail());
        return BUemailDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setEmail((String) doc.get("email"));
        }
    }
}
