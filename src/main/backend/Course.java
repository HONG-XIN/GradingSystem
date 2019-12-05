package main.backend;

public class Course {

    private IdNumberCourse idNumber;
    private String name;
    private Semester semester;
    private Criteria criteria;

    //constructor
    public Course() {
        this.idNumber = new IdNumberCourse();
        this.name = "";
        this.semester = new Semester();
        this.criteria = new Criteria();

    }

    public Course(String name, Semester semester, Criteria criteria) {
        this();
        this.name = name;
        this.semester = semester;
        this.criteria = criteria;
    }

    //accessor
    public String getId() {
        return this.idNumber.getId();
    }

    public String getName() {
        return this.name;
    }

    public Semester getSemester() {
        return this.semester;
    }

    public Criteria getCriteria() {
        return this.criteria;
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
