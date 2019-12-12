package main.backend;

import org.dizitart.no2.Document;

import java.util.Random;

/**
 * 9 digit code
 */
public class IdNumber {
    protected String id;

    //constructor
    public IdNumber() {
        this.id = generateRandomNumber();
    }

    public IdNumber(String id) {
        this.id = id;
    }

    //accessor
    public String getId() {
        return id;
    }

    //mutator
    public void setId(String id) {
        this.id = id;
    }

    //generate function
    public static String generateRandomNumber() {
        Random rand = new Random();
        int numberInt = rand.nextInt(1000000000);
        return String.format("%09d", numberInt);
    }

    //Database Function
    //from RAM TO DB
    public Document write(){
        Document IdDoc = new Document();
        IdDoc.put("id", getId());
        return IdDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setId((String) doc.get("id"));
        }
    }
}
