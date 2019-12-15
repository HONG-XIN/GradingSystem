package main.backend;

import org.dizitart.no2.Document;
import org.dizitart.no2.sync.ReplicationType;

public class Score {

    private double value;

    public Score() {
        this.value = 0;
    }

    public Score(double value){
        setValue(value);
    }
    //accessor
    public double getValue() {
        return value;
    }

    //mutator
    public void setValue(double value){
        this.value = value;
    }


    //DB function
    //from RAM to DB
    public Document write(){
        Document ScoreDoc = new Document();
        ScoreDoc.put("value", getValue());
        return ScoreDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if(doc != null){
            setValue((double) doc.get("value"));
        }
    }
}
