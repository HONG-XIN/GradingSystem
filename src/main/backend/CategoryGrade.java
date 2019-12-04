package main.backend;

public class CategoryGrade {

    private IdNumberCategory categoryId;
    private IdNumberStudent studentId;
    private Score score;

    //constructor
    public CategoryGrade() {
        this.categoryId = new IdNumberCategory();
        this.studentId = new IdNumberStudent();
        this.score = new Score();
    }
    CategoryGrade(String categoryId, String studentId){
        this();
        this.categoryId.setId(categoryId);
        this.studentId.setId(studentId);
    }
    CategoryGrade(String categoryId, String studentId, Score score){
        this(categoryId, studentId);
        this.score = score;
   }

    //accesser
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
