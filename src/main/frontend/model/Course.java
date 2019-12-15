package main.frontend.model;

import javafx.scene.control.Button;

public class Course {
    String id, semester, start, end;
    Button name;
    Button delete;

    public Course(String id, Button name, String semester, String start, String end, Button delete) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.start = start;
        this.end = end;
        this.delete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Button getName() {
        return name;
    }

    public void setName(Button name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
