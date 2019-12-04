package main.backend;

public class Score {

    private double value;
    private int type;       //0 is percent score  1 is deduction score

    public Score() {
        this.value = 0;
        this.type = 0;
    }

    public Score(double value, int type){
        this.value = value;
        this.type = type;
    }
    //accessor
    public double getValue() {
        return value;
    }

    public int getType(){
        return this.type;
    }

    //mutator
    public void setValue(double value){
        this.value = value;
    }

    public void setType(int type){
        this.type = type;
    }
}
