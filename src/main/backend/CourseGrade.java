package main.backend;

import org.dizitart.no2.Document;

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
        this.comment = "";
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

    public IdNumberCourse getCourseIdObject() {return this.courseId;}

    public String getStudentId(){
        return this.studentId.getId();
    }

    public IdNumberStudent getStudentIdObject(){return this.studentId;}

    public double getFinalScore(){
        return this.finalScore.getValue();
    }

    public String getLetterGrade(){
        return this.letterGrade;
    }
    public Score getFinalScoreObject(){return this.finalScore;}


    public String getComment(){
        return this.comment;
    }

    public int getBonus(){ return this.bonus;}

    public IdNumberCourseGrade getCourseGradeIdObject(){
        return this.courseGradeId;
    }

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
        return !comment.equals("");
    }

    public boolean checkGradeByCourseIdAndStudentId(String courseId, String studentId){
        return this.courseId.getId().equals(courseId) && this.studentId.getId().equals(studentId);
    }

    //Database Function
    //from RAM TO DB
    public Document write(){
        Document courseGradeDoc = new Document();
        courseGradeDoc.put("comment", getComment());
        courseGradeDoc.put("letterGrade", getLetterGrade());
        courseGradeDoc.put("bonus", getBonus());
        if (getFinalScoreObject() != null){
            courseGradeDoc.put("finalScore", getFinalScoreObject().write());
        }
        if (getCourseIdObject() != null){
            courseGradeDoc.put("courseId", getCourseIdObject().write());
        }
        if (getStudentIdObject() != null){
            courseGradeDoc.put("studentId", getStudentIdObject().write());
        }
        if (getCourseGradeIdObject() != null){
            courseGradeDoc.put("courseGradeId", getCourseGradeIdObject().write());
        }
        return courseGradeDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setComment((String) doc.get("comment"));
            setLetterGrade((String) doc.get("letterGrade"));
            setBonus((int) doc.get("bonus"));
            Document finalScoreDoc = (Document) doc.get("finalScore");
            if(finalScoreDoc != null){
                Score finalScore = new Score();
                finalScore.read(finalScoreDoc);
                this.finalScore = finalScore;
            }
            Document courseIdDoc = (Document) doc.get("courseId");
            if(courseIdDoc != null){
                IdNumberCourse courseId = new IdNumberCourse();
                courseId.read(courseIdDoc);
                this.courseId = courseId;
            }
            Document studentIdDoc = (Document) doc.get("studentId");
            if (studentIdDoc != null){
                IdNumberStudent studentId = new IdNumberStudent();
                studentId.read(studentIdDoc);
                this.studentId = studentId;
            }
            Document courseGradeIdDoc = (Document) doc.get("courseGradeId");
            if (courseGradeIdDoc != null){
                IdNumberCourseGrade courseGradeId = new IdNumberCourseGrade();
                courseGradeId.read(courseGradeIdDoc);
                this.courseGradeId = courseGradeId;
            }
        }
    }
}
