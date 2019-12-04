package main.backend;

import java.text.DecimalFormat;

public class Weight {
    private double percentage;

    //constructor
    public Weight(double percentage) {
        this.percentage = percentage;
    }

    public Weight() {
        this(0);
    }

    //accessor
    public double getPercentage(){
        return this.percentage;
    }

    //mutator
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    //override function
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(percentage) + "%";
    }
}
