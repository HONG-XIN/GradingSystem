package main.backend;

public class CategoryGrade {
    public static final int GRADE_INI = 0;
    public static final int GRADE_FREEZE = 1;
    public static final int GRADE_DROP = 2;
    private IdNumberCourseGrade courseGradeId;
    private IdNumberCourse courseId;
    private IdNumberCategory categoryId;
    private IdNumberStudent studentId;
    private Score score;
    private int step;

    //constructor
    public CategoryGrade() {
        this.courseGradeId = new IdNumberCourseGrade();
        this.courseId = new IdNumberCourse();
        this.categoryId = new IdNumberCategory();
        this.studentId = new IdNumberStudent();
        this.score = new Score();
        this.step = GRADE_INI;
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

    public int getStep() {
        return this.step;
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

    public void setStep(int step) {
        this.step = step;
    }

    //function
    public void freezeGrade(){
        setStep(GRADE_FREEZE);
    }

    public boolean checkGradeByCourseIdAndStudentId(String courseId, String studentId){
        return this.courseId.getId().equals(courseId) && this.studentId.getId().equals(studentId);
    }
}
