package main.backend;

public class CourseGrade {
    private int courseID;
    private int studentID;
    private int finalscore;
    private String letter;
    private int bonus;
    private String comment;
    //constructer
    CourseGrade(int courseID, int studentID){
        this.courseID = courseID;
        this.studentID = studentID;
        this.finalscore = 0;
        this.letter = "";
        this.bonus = 0;
        this.comment = "";
    }
}
