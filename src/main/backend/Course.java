package main.backend;

import org.dizitart.no2.Document;

import javax.print.Doc;
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

    public IdNumberCourse getIdNumberObject() {return  this.idNumber;}

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

    //DB function
    //FROM RAM TO DB
    public Document write(){
        Document CourseDoc = new Document();
        CourseDoc.put("name", getName());
        if(this.getSemester() != null){
            CourseDoc.put("semester", getSemester().write());
        }
        if(this.getIdNumberObject() != null){
            CourseDoc.put("idNumber", getIdNumberObject().write());
        }
        if (this.getStudents().size() > 0 ){
            ArrayList<Document> StudentsListDoc = new ArrayList<Document>();
            for (Student student: this.getStudents()){
                StudentsListDoc.add(student.write());
            }
            CourseDoc.put("students", StudentsListDoc);
        }
        if (this.getCriteria() != null){
            CourseDoc.put("criteria", getCriteria().write());
        }
        return CourseDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setName((String) doc.get("name"));
            Document docId = (Document) doc.get("idNumber");
            if(docId != null){
                IdNumberCourse idNumber = new IdNumberCourse();
                idNumber.read(docId);
                this.idNumber = idNumber;
            }
            Document semesterDoc = (Document) doc.get("semester");
            if(semesterDoc != null){
                Semester semester = new Semester();
                semester.read(semesterDoc);
                this.semester = semester;
            }
            ArrayList<Document> StudentsListDoc = (ArrayList<Document>) doc.get("students");
            if (StudentsListDoc != null){
                for (Document studentDoc:StudentsListDoc){
                    if (studentDoc != null){
                        Student student = new Student();
                        student.read(studentDoc);
                        students.add(student);
                    }
                }
            }
            Document criteriaDoc = (Document) doc.get("criteria");
            if (criteriaDoc != null){
                Criteria criteria = new Criteria();
                criteria.read(criteriaDoc);
                this.criteria = criteria;
            }
        }
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

    //DB function
    //FROM RAM TO DB
    public Document write(){
        Document CourseDoc = new Document();
        CourseDoc.put("name", getName());
        if(this.getSemester() != null){
            CourseDoc.put("semester", getSemester().write());
        }
        if(this.getIdNumberObject() != null){
            CourseDoc.put("idNumber", getIdNumberObject().write());
        }
        if (this.getStudents().size() > 0 ){
            ArrayList<Document> StudentsListDoc = new ArrayList<Document>();
            for (Student student: this.getStudents()){
                StudentsListDoc.add(student.write());
            }
            CourseDoc.put("students", StudentsListDoc);
        }
        if (this.getCriteria() != null){
            CourseDoc.put("criteria", getCriteria().write());
        }
        return CourseDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setName((String) doc.get("name"));
            Document docId = (Document) doc.get("idNumber");
            if(docId != null){
                IdNumberCourse idNumber = new IdNumberCourse();
                idNumber.read(docId);
                this.idNumber = idNumber;
            }
            Document semesterDoc = (Document) doc.get("semester");
            if(semesterDoc != null){
                Semester semester = new Semester();
                semester.read(semesterDoc);
                this.semester = semester;
            }
            ArrayList<Document> StudentsListDoc = (ArrayList<Document>) doc.get("students");
            if (StudentsListDoc != null){
                for (Document studentDoc:StudentsListDoc){
                    if (studentDoc != null){
                        Student student = new Student();
                        student.read(studentDoc);
                        students.add(student);
                    }
                }
            }
            Document criteriaDoc = (Document) doc.get("criteria");
            if (criteriaDoc != null){
                Criteria criteria = new Criteria();
                criteria.read(criteriaDoc);
                this.criteria = criteria;
            }
        }
    }
}
