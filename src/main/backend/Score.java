package main.backend;

public class Score implements Cloneable{

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
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
