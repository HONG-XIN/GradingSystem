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
    double ScoreValue;
    int ScoreType;       //0 is total score  1 is percentage score
    public Score(){
        this.ScoreValue = 0;
        this.ScoreType = 0;
    }
    public Score(double ScoreValue, int ScoreType){
        this.ScoreType = ScoreType;
        this.ScoreValue = ScoreValue;
    }
    public double getScoreValue() {
        return ScoreValue;
    }
    public void setScoreValue(double ScoreValue){
        this.ScoreValue = ScoreValue;
    }
    public int getScoreType(){
        return this.ScoreType;
    }
    public void setScoreType(int ScoreType){
        this.ScoreType = ScoreType;
    }
}
