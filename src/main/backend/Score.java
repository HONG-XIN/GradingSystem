package main.backend;

import org.dizitart.no2.Document;

public class Score {

    private double value;
    private ScoreType type;

    public Score() {
        this.value = 0;
        this.type = ScoreType.PERCENTAGE;
    }

    public Score(double value, ScoreType type){
        this.value = value;
        this.type = type;
    }
    //accessor
    public double getValue() {
        return value;
    }

    public ScoreType getType() {
        return type;
    }

    //mutator
    public boolean setValue(double value){
        if(type == ScoreType.PERCENTAGE) {
            if(value < 0)
                return false;
            else
                this.value = value;
        } else if(type == ScoreType.DEDUCTION) {
            if(value > 0)
                return false;
            else
                this.value = value;
        }
        return true;
    }

    public void setType(ScoreType type){
        this.type = type;
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
