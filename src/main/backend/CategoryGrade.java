package main.backend;

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

    public String getCategoryId(){
        return this.categoryId.getId();
    }

    public String getStudentId(){
        return this.studentId.getId();
    }

    public double getScore(){
        return this.score.getValue();
    }


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
}
