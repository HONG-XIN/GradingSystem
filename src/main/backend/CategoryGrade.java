package main.backend;

import org.dizitart.no2.Document;

public class CategoryGrade {
    private IdNumberCourseGrade courseGradeId;
    private IdNumberCourse courseId;
    private IdNumberCategory categoryId;
    private IdNumberStudent studentId;
    private Score score;

    //constructor
    public CategoryGrade() {
        this.courseGradeId = new IdNumberCourseGrade();
        this.courseId = new IdNumberCourse();
        this.categoryId = new IdNumberCategory();
        this.studentId = new IdNumberStudent();
        this.score = new Score();

    }
    CategoryGrade(String courseId, String categoryId, String studentId){
        this();
        this.courseId.setId(courseId);
        this.categoryId.setId(categoryId);
        this.studentId.setId(studentId);
    }
    CategoryGrade(String courseId, String categoryId, String studentId, Score score){
        this(courseId, categoryId, studentId);
        this.score = score;
   }

    //accesser
    public String getId() {
        return courseGradeId.getId();
    }

    public String getCourseId() {
        return this.courseId.getId();
    }

    public IdNumberCourse getCourseIdObject() {return this.courseId;}

    public String getCategoryId(){
        return this.categoryId.getId();
    }

    public IdNumberCategory getCategoryIdObject(){return this.categoryId;}

    public String getStudentId(){
        return this.studentId.getId();
    }

    public IdNumberStudent getStudentIdObject() {return this.studentId;}

    public double getScore(){
        return this.score.getValue();
    }

    public Score getScoreObject(){return this.score;}


    // mutator
    public void setId(String id) {
        this.courseGradeId.setId(id);
    }

    public void setCourseId(String id) {
        this.courseId.setId(id);
    }

    public void setCategoryId(String id) {
        this.categoryId.setId(id);
    }

    public void setStudentId(String id) {
        this.studentId.setId(id);
    }

    public void setScore(double value){
        this.score.setValue(value);
    }


    //function
    public boolean checkGradeByCourseIdAndStudentId(String courseId, String studentId){
        return this.courseId.getId().equals(courseId) && this.studentId.getId().equals(studentId);
    }

    public double gradeConvert(double total, double deduction) {
        double score = (total + deduction) / total * 100.0;
        return Math.max(score, 0.0);
    }

    //DB function
    //from RAM to DB
    public Document write(){
        Document CategoryGradeDoc = new Document();
        if(this.getCourseIdObject() != null){
            CategoryGradeDoc.put("courseId", getCourseIdObject().write());
        }
        if(this.getCategoryIdObject() != null){
            CategoryGradeDoc.put("categoryId", getCategoryIdObject().write());
        }
        if(this.getStudentIdObject() != null){
            CategoryGradeDoc.put("studentId", getStudentIdObject().write());
        }
        if(this.getScoreObject() != null){
            CategoryGradeDoc.put("score", getScoreObject().write());
        }
        return CategoryGradeDoc;
    }

    //FROM DB TO RAM
    public void read(Document doc){
        if(doc != null){
            Document courseIdDoc = (Document) doc.get("courseId");
            if (courseIdDoc != null){
                IdNumberCourse courseId = new IdNumberCourse();
                courseId.read(courseIdDoc);
                this.courseId = courseId;
            }
            Document scoreDoc = (Document) doc.get("score");
            if(scoreDoc != null){
                Score score = new Score();
                score.read(scoreDoc);
                this.score = score;
            }
            Document categoryIdDoc = (Document) doc.get("categoryId");
            if(categoryIdDoc != null){
                IdNumberCategory categoryId = new IdNumberCategory();
                categoryId.read(categoryIdDoc);
                this.categoryId = categoryId;
            }
            Document studentIdDoc = (Document) doc.get("studentId");
            if(studentIdDoc != null){
                IdNumberStudent studentId = new IdNumberStudent();
                studentId.read(studentIdDoc);
                this.studentId = studentId;
            }
        }
    }
}
