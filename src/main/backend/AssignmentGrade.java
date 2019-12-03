package main.backend;

public class AssignmentGrade {
    private int assignmentID;
    private int studentID;
    //private Score score;
    private double percent;
    //constructer
    AssignmentGrade(int assignmentID, int studentID){
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        //this.score = new Score(0);
        this.percent = 0;
    }
    AssignmentGrade(int assignmentID, int studentID, /*Score score,*/ double percent){
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        //this.score = score;
        this.percent = percent;
    }
    private boolean checkPercent(Double percent){
        if(percent <0 || percent > 1){
            return false;
        }
        return true;
    }
    //mutator
    /*
    public void setScore(Score score){
        this.score = score;
    }
    public boolean setScore(Double score){
        if (score < -100 || score > 100){
            return false;
        }
        else if(score < 0){
            this.score = this.score.set(100 - score);
            return true;
        }
        this.score.set(score);
        return true;
    }
    public boolean setScore(String letter){
        if(letter == 'A'){
            this.score.set(100);
            return true;
        }
        else if(letter == 'B'){
            this.score.set(75);
            return true;
        }
        else if(letter == 'C'){
            this.score.set(50);
            return true;
        }
        else if(letter == 'D'){
            this.score.set(25);
            return true;
        }
        return false;
    }
     */
    public boolean setPercent(double percent){
        if(checkPercent(percent)){
            this.percent = percent;
            return true;
        }
        return false;
    }
    //accesser
    public int getAssignmentID(){
        return this.assignmentID;
    }
    public int getStudentID(){
        return this.studentID;
    }
    /*
    public Score getScore(){
        return this.score;
    }
    public Score getScoreDouble(){
        return this.score.get();
    }
    */
}
