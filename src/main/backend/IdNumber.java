package main.backend;

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
}
