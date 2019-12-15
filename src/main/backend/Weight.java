package main.backend;

import java.text.DecimalFormat;

public class Weight implements Cloneable{
    private double value;

    //constructor
    public Weight(double value) {
        this.value = value;
    }

    public Weight() {
        this(0);
    }

    //accessor
    public double getValue(){
        return this.value;
    }

    //mutator
    public void setValue(double value) {
        this.value = value;
    }

    //override function
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(value) + "%";
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
