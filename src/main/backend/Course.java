package main.backend;

public class Course {
    private int id;
    private String name;
    private Semester semester;
    private String gradingCriteria;
    private String template;

    public Course() {
        id = 0;
    }

    public Course(int id, String name, Semester semester, String gradingCriteria) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.gradingCriteria = gradingCriteria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getGradingCriteria() {
        return gradingCriteria;
    }

    public void setGradingCriteria(String gradingCriteria) {
        this.gradingCriteria = gradingCriteria;
    }
}
