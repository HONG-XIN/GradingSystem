package main.backend;

import org.dizitart.no2.Document;

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

    public Student(String firstName, String lastName, String BUID, StudentType type) {
        this.idNumber = new IdNumberStudent();
        this.name = new Name(firstName, lastName);
        this.BUID = BUID;
        this.type = type;
    }

    public Student(String firstName, String middleName, String lastName, String BUID, StudentType type) {
        this(firstName, lastName, BUID, type);
        this.name.setMiddleName(middleName);
    }

    //accessor
    public String getId(){
        return this.idNumber.getId();
    }

    public IdNumberStudent getIdNumberObject(){
        return this.idNumber;
    }

    public String getName(){
        return this.name.toString();
    }

    public Name getNameObject(){
        return this.name;
    }

    public String getBUID(){
        return this.BUID;
    }

    public String getType(){
        return this.type.toString();
    }

    public StudentType getStudentTypeObject(){
        return this.type;
    }

    //mutator
    public void setId(String id){
        this.idNumber.setId(id);
    }

    public void setStudentName(String firstName, String middleName, String lastName){
        this.name.setName(firstName, middleName, lastName);
    }

    public void setNameObject(Name name){ this.name = name;}

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

    //DB function
    //from RAM to DB
    public Document write(){
        Document StudentDoc = new Document();
        StudentDoc.put("BUID", getBUID());
        if(this.getNameObject() != null){
            StudentDoc.put("name", getNameObject().write());
        }
        if(this.getIdNumberObject() != null){
            StudentDoc.put("idNumber", getIdNumberObject().write());
        }
        /*if(this.getStudentTypeObject() != null){
            StudentDoc.put("type", getStudentTypeObject());
        }*/
        return StudentDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setBUID((String) doc.get("BUID"));
            Document NameDoc = (Document) doc.get("name");
            if(NameDoc != null){
                Name name = new Name();
                name.read(NameDoc);
                this.name = name;
            }
            Document idNumberDoc = (Document) doc.get("idNumber");
            if(idNumberDoc != null){
                IdNumberStudent idNumber = new IdNumberStudent();
                idNumber.read(idNumberDoc);
                this.idNumber = idNumber;
            }
        }
    }


}
