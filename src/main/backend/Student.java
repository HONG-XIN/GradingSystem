package main.backend;

public class Student {
    private IdNumberStudent idNumber;
    private Name name;
    private String BUID;
    private StudentType type;

    //constructor
    public Student() {
        this.idNumber = new IdNumberStudent();
        this.name = new Name();
        this.BUID = "";
        this.type = StudentType.UNDERGRAD;
    }

    public Student(String id, String firstName, String lastName, String BUID, StudentType type) {
        this.idNumber = new IdNumberStudent(id);
        this.name = new Name(firstName, lastName);
        this.BUID = BUID;
        this.type = type;
    }

    public Student(String id, String firstName, String middleName, String lastName, String BUID, StudentType type) {
        this(id, firstName, lastName, BUID, type);
        this.name.setMiddleName(middleName);
    }

    //accessor
    public String getId(){
        return this.idNumber.getId();
    }

    public String getName(){
        return this.name.toString();
    }

    public String getBUID(){
        return this.BUID;
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

    public void setBUID(String BUID){
        this.BUID = BUID;
    }

    public void setStudentType(StudentType type){
        this.type = type;
    }

    //check function
    public static boolean isValidBUID(String buID) { // U12345678
        if(buID.length() != 9 && buID.charAt(0) != 'U'){
            return false;
        } else {
            for (int i = 1; i < buID.length(); i++){
                if(!Character.isDigit(buID.charAt(i))) return false;
            }
        }
        return true;
    }
    //override functions
    @Override
    public String toString() {
        return name.toString();
    }
}
