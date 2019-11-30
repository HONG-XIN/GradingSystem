package main.backend;

public class Score {
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
