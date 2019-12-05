package main.backend;

public class CategoryGrade {
    private IdNumberCourse courseId;
    private IdNumberCategory categoryId;
    private IdNumberStudent studentId;
    private Score score;

    //constructor
    public CategoryGrade() {
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
}
