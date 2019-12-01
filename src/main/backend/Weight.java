package main.backend;

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
}
