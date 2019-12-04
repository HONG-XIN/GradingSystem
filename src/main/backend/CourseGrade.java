package main.backend;

public class CourseGrade {
    private ID courseID;
    private ID studentID;
    private int finalscore;
    private String letter;
    private int bonus;
    private String comment;
    //constructer
    CourseGrade(ID courseID, ID studentID){
        this.courseID = courseID;
        this.studentID = studentID;
        this.finalscore = 0;
        this.letter = "";
        this.bonus = 0;
        this.comment = "";
    }
    CourseGrade(ID courseID, ID studentID, int finalscore){
        this.courseID = courseID;
        this.studentID = studentID;
        this.finalscore = finalscore;
        if(finalscore > 75){
            this.letter = "A";
        }
        else if(finalscore > 50){
            this.letter = "B";
        }
        else if(finalscore > 25){
            this.letter = "C";
        }
        else {
            this.letter = "D";
        }
        this.bonus = 0;
        this.comment = "";
    }
    CourseGrade(ID courseID, ID studentID, String letter){
        this.courseID = courseID;
        this.studentID = studentID;
        this.letter = letter;
        if(letter == "A"){
            this.finalscore = 100;
        }
        else if(letter == "B"){
            this.finalscore = 75;
        }
        else if(letter == "C"){
            this.finalscore = 50;
        }
        else {
            this.finalscore = 25;
        }
        this.bonus = 0;
        this.comment = "";
    }
    CourseGrade(ID courseID, ID studentID, int finalscore, String letter){
        this.courseID = courseID;
        this.studentID = studentID;
        this.finalscore = finalscore;
        this.letter = letter;
        this.bonus = 0;
        this.comment = "";
    }
    CourseGrade(ID courseID, ID studentID, int finalscore, String letter, int bonus){
        this.courseID = courseID;
        this.studentID = studentID;
        this.finalscore = finalscore;
        this.letter = letter;
        this.bonus = bonus;
        this.comment = "";
    }
    CourseGrade(ID courseID, ID studentID, int finalscore, String letter, int bonus, String comment){
        this.courseID = courseID;
        this.studentID = studentID;
        this.finalscore = finalscore;
        this.letter = letter;
        this.bonus = bonus;
        this.comment = comment;
    }
    //mutator

    //accesser
    public String getCourseID(){
        return this.courseID.getID();
    }
    public String getStudentID(){
        return this.studentID.getID();
    }
    public int getFinalscore(){
        return this.finalscore;
    }
    public String getLetter(){
        return this.letter;
    }
    public int getBonus(){
        return this.bonus;
    }
    public String getComment(){
        return this.comment;
    }
}
