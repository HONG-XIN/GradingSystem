package main.backend;

public class Student {
    private IdNumberStudent studentIdNumber;
    private Name studentName;
    private StudentType studentType;

    //constructor
    public Student() {
        this.studentIdNumber = new IdNumberStudent();
        this.studentName = new Name();
        this.studentType = StudentType.UNDERGRAD;
    }

    public Student(String studentId, String firstName, String lastName, StudentType studentType) {
        this.studentIdNumber = new IdNumberStudent(studentId);
        this.studentName = new Name(firstName, lastName);
        this.studentType = studentType;
    }

    public Student(String studentId, String firstName, String middleName, String lastName, StudentType studentType) {
        this(studentId, firstName, lastName, studentType);
        this.studentName.setMiddleName(middleName);
    }


    //accessor
    public String getStudentID(){
        return this.studentIdNumber.getId();
    }

    public String getStudentName(){
        return this.studentName.toString();
    }

    public String getStudentType(){
        return this.studentType.toString();
    }

    //mutator
    public void setStudentID(String studentId){
        this.studentIdNumber.setId(studentId);
    }

    public void setStudentName(String firstName, String middleName, String lastName){
        this.studentName.setFirstName(firstName);
        this.studentName.setMiddleName(middleName);
        this.studentName.setLastName(lastName);
    }

    public void setStudentType(StudentType studentType){
        this.studentType = studentType;
    }

    //override functions
    @Override
    public String toString() {
        return studentName.toString();
    }
}
