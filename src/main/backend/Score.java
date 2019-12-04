package main.backend;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

@Indices({
        @Index(value = "ScoreValue", type = IndexType.NonUnique),
        @Index(value = "ScoreType", type = IndexType.NonUnique)
})
public class Score {
    @Id
    private double scoreValue;
    private int scoreType;       //0 is normal score  1 is deduction score

    public Score() {
        this.scoreValue = 0;
        this.scoreType = 0;
    }

    public Score(double scoreValue, int scoreType){
        this.scoreValue = scoreValue;
        this.scoreType = scoreType;
    }
    //accessor
    public double getScoreValue() {
        return scoreValue;
    }

    public int getScoreType(){
        return this.scoreType;
    }

    //mutator
    public void setScoreValue(double scoreValue){
        this.scoreValue = scoreValue;
    }

    public void setScoreType(int scoreType){
        this.scoreType = scoreType;
    }
}
