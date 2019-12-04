package main.backend;

import java.util.Random;

/**
 * 9 digit code
 */
public class IdNumber {
    protected String idNumber;

    //constructor
    public IdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    public IdNumber() {
        this.idNumber = "";
    }

    //accessor
    public String getId() {
        return idNumber;
    }

    //mutator
    public void setId(String idNumber) {
        this.idNumber = idNumber;
    }

    //generate function
    public static String generateRandomNumber() {
        Random rand = new Random();
        int numberInt = rand.nextInt(1000000000);
        return String.format("%09d", numberInt);
    }
}
