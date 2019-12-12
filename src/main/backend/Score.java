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

    public void setType(ScoreType type){
        this.type = type;
    }

    //DB function
    //from RAM to DB
    public Document write(){
        Document ScoreDoc = new Document();
        ScoreDoc.put("value", getValue());
        if (this.type.equals(ScoreType.PERCENTAGE)){
            ScoreDoc.put("type", "PERCENTAGE");
        }
        else if (this.type.equals(ScoreType.DEDUCTION)){
            ScoreDoc.put("type", "DEDUCTION");
        }
        return ScoreDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if(doc != null){
            setValue((double) doc.get("value"));
            String t = (String)doc.get("type");
            if (t.equals("PERCENTAGE")){
                this.type = ScoreType.PERCENTAGE;
            }
            else if (t.equals("DEDUCTION")){
                this.type = ScoreType.DEDUCTION;
            }
        }
    }
}
