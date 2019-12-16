package main.backend;

import org.dizitart.no2.Document;

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

    //Database Function
    //from RAM TO DB
    public Document write(){
        Document weightDoc = new Document();
        weightDoc.put("value", getValue());
        return weightDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null){
            setValue((double) doc.get("value"));
        }
    }
}
