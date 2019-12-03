package main.backend;

public class Student {
    String StudentID;
    Name StudentName;
    public Student(String StudentID, String Firstname, String Middlename, String Lastname){
        this.StudentID = StudentID;
        StudentName = new Name(Firstname, Middlename, Lastname);
    }
    public Student(String StudentID, String Firstname, String Lastname){
        this.StudentID = StudentID;
        StudentName = new Name(Firstname, Lastname);
    }
    public String getStudentID(){
        return this.StudentID;
    }
    public void setStudentID(String StudentId){
        this.StudentID = StudentId;
    }
}
