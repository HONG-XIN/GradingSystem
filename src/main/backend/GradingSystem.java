package main.backend;

import java.util.ArrayList;

public class GradingSystem {

    private ArrayList<Course> courses;
    private ArrayList<Template> templates;
    private ArrayList<CategoryGrade> categoryGrades;
    private ArrayList<CourseGrade> courseGrades;

    public GradingSystem() {
        this.courses = new ArrayList<>();
        this.templates = new ArrayList<>();
        this.categoryGrades = new ArrayList<>();
        this.courseGrades = new ArrayList<>();
    }


}
