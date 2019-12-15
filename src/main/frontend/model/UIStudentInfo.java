package main.frontend.model;

import main.backend.Student;

public class UIStudentInfo {
    String courseId;
    Student student;

    public UIStudentInfo(String courseId, Student student) {
        this.courseId = courseId;
        this.student = student;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
