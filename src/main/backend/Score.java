package main.backend;

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
}
