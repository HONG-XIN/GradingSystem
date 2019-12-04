package main.backend;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

@Indices({
        @Index(value = "value", type = IndexType.NonUnique),
        @Index(value = "type", type = IndexType.NonUnique)
})
public class Score {
    @Id
    private double value;
    private int type;       //0 is normal score  1 is deduction score

    public Score() {
        this.value = 0;
        this.type = 0;
    }

    public Score(double value, int type){
        this.value = value;
        this.type = type;
    }
    //accessor
    public double getScoreValue() {
        return value;
    }

    public int getScoreType(){
        return this.type;
    }

    //mutator
    public void setScoreValue(double value){
        this.value = value;
    }

    public void setScoreType(int type){
        this.type = type;
    }
}
