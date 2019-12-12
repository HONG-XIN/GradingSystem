package main.backend;

import org.dizitart.no2.Document;

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
