package main.backend;

public enum StudentType {
    UNDERGRAD("undergrad"), GRAD ("grad");

    //constructor
    String typeName;
    StudentType(String typeName) {
        this.typeName = typeName;
    }

    //override function
    @Override
    public String toString(){
        return typeName;
    }
}
