package main.backend;

import java.util.ArrayList;

public class Course {

    private IdNumberCourse idNumber;
    private String name;
    private Semester semester;
    private Criteria criteria;
    private ArrayList<Student> students;

    //constructor
    public Course() {
        this.idNumber = new IdNumberCourse();
        this.name = "";
        this.semester = new Semester();
        this.criteria = new Criteria();
        this.students = new ArrayList<>();

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

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public Student getStudentById(String studentId) {
        for(Student student : students) {
            if(student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
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

    public void setStudent(ArrayList<Student> students) {
        this.students = students;
    }

    //functions
    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public boolean checkCourseById(String id){
        return getId().equals(id);
    }
}
