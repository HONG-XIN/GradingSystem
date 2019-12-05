package main.backend;

public class Course {

    private IdNumberCourse idNumber;
    private String name;
    private Semester semester;
    private IdNumberTemplate templateId;

    //constructor
    public Course() {
        this.idNumber = new IdNumberCourse();
        this.name = "";
        this.semester = new Semester();
        this.templateId = new IdNumberTemplate();

    }

    public Course(String name, Semester semester, String templateId) {
        this();
        this.name = name;
        this.semester = semester;
        setTemplateId(templateId);
    }

    //accessor
    public String getId() {
        return this.idNumber.getId();
    }

    public String getName() {
        return name;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getTemplateId() {
        return templateId.getId();
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

    public void setTemplateId(String templateId) {
        this.templateId.setId(templateId);
    }
}
