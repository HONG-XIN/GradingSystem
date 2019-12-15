package main.backend;

public class CourseGrade {
    private IdNumberCourseGrade courseGradeId;
    private IdNumberCourse courseId;
    private IdNumberStudent studentId;
    private Score finalScore;
    private String letterGrade;
    private int bonus;
    private String comment;

    //constructor
    public CourseGrade() {
        this.courseGradeId = new IdNumberCourseGrade();
        this.courseId = new IdNumberCourse();
        this.studentId = new IdNumberStudent();
        this.finalScore = new Score();
        this.letterGrade = "";
        this.bonus = 0;
        this.comment = null;
    }

    public CourseGrade(String courseId, String studentId) {
        this();
        setCourseId(courseId);
        setStudentId(studentId);
    }
    //accesser
    public String getId() {
        return this.courseGradeId.getId();
    }

    public String getCourseId(){
        return this.courseId.getId();
    }

    public String getStudentId(){
        return this.studentId.getId();
    }

    public double getFinalScore(){
        return this.finalScore.getValue();
    }

    public String getLetterGrade(){
        return this.letterGrade;
    }

    public String getComment(){
        if(comment == null) {
            return "no comments";
        }
        return this.comment;
    }

    public int getBonus(){ return this.bonus;}

    //mutator
    public void setCourseGradeId(String id) {
        this.courseGradeId.setId(id);
    }

    public void setCourseId(IdNumberCourse courseId) {
        this.courseId = courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId.setId(courseId);
    }

    public void setStudentId(IdNumberStudent studentId) {
        this.studentId = studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId.setId(studentId);
    }

    public void setFinalScore(double value) {
        this.finalScore.setValue(value);
    }

    public void setLetterGrade (String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public void setComment (String comment) {
        this.comment = comment;
    }

    public void setBonus(int bonus){ this.bonus = bonus;}

    //function
    public boolean hasComment() {
        return comment != null;
    }

    public boolean checkGradeByCourseIdAndStudentId(String courseId, String studentId){
        return this.courseId.getId().equals(courseId) && this.studentId.getId().equals(studentId);
    }
}
