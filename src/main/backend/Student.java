package main.backend;

public class Student {
    private IdNumberStudent idNumber;
    private Name name;
    private StudentType type;

    //constructor
    public Student() {
        this.idNumber = new IdNumberStudent();
        this.name = new Name();
        this.type = StudentType.UNDERGRAD;
    }

    public Student(String id, String firstName, String lastName, StudentType type) {
        this.idNumber = new IdNumberStudent(id);
        this.name = new Name(firstName, lastName);
        this.type = type;
    }

    public Student(String id, String firstName, String middleName, String lastName, StudentType type) {
        this(id, firstName, lastName, type);
        this.name.setMiddleName(middleName);
    }

    //accessor
    public String getId(){
        return this.idNumber.getId();
    }

    public String getName(){
        return this.name.toString();
    }

    public String getType(){
        return this.type.toString();
    }

    //mutator
    public void setId(String id){
        this.idNumber.setId(id);
    }

    public void setStudentName(String firstName, String middleName, String lastName){
        this.name.setName(firstName, middleName, lastName);
    }

    public void setStudentType(StudentType type){
        this.type = type;
    }

    //override functions
    @Override
    public String toString() {
        return name.toString();
    }
}
