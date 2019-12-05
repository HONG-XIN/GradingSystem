package main.backend;

public class CourseGrade {
    private IdNumberCourse courseId;
    private IdNumberStudent studentId;
    private Score finalScore;
    private char letterGrade;
    private String comment;

    //constructor
    public CourseGrade() {
        this.courseId = new IdNumberCourse();
        this.studentId = new IdNumberStudent();
        this.finalScore = new Score();
        this.letterGrade = ' ';
        this.comment = null;
    }

    //accesser
    public String getCourseId(){
        return this.courseId.getId();
    }

    public String getStudentId(){
        return this.studentId.getId();
    }

    public double getFinalScore(){
        return this.finalScore.getValue();
    }

    public char getLetterGrade(){
        return this.letterGrade;
    }

    public String getComment(){
        return this.comment;
    }

    //mutator
    public void setCourseId(IdNumberCourse courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(IdNumberStudent studentId) {
        this.studentId = studentId;
    }

    public void setFinalScore(double value) {
        this.finalScore.setValue(value);
    }

    public void setLetterGrade (char letterGrade) {
        this.letterGrade = letterGrade;
    }

    public void setComment (String comment) {
        this.comment = comment;
    }
}
