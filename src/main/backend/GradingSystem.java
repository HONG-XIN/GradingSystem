package main.backend;

import java.util.ArrayList;

public class GradingSystem {

    private ArrayList<Course> courses;
    private ArrayList<Criteria> criteriaTemplates;
    private ArrayList<Student> students;
    private ArrayList<CategoryGrade> categoryGrades;
    private ArrayList<CourseGrade> courseGrades;

    public GradingSystem() {
        this.courses = new ArrayList<>();
        this.criteriaTemplates = new ArrayList<>();
        this.students = new ArrayList<>();
        this.categoryGrades = new ArrayList<>();
        this.courseGrades = new ArrayList<>();
    }

    //accessor
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Criteria> getCriteriaTemplates() {
        return criteriaTemplates;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<CategoryGrade> getCategoryGrades() {
        return categoryGrades;
    }

    public ArrayList<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    //create functions
    public void createCriteriaTemplate(String name) {
        criteriaTemplates.add(new Criteria(name));
    }

    public void createCourseByTemplate(Criteria criteriaTemplate, String name, Semester semester) throws CloneNotSupportedException {
            Criteria criteria = (Criteria) criteriaTemplate.clone(); // get a copy of template
            Course course = new Course(name, semester, criteria);
            courses.add(course);
    }

    public void createStudent(String firstName, String lastName, String BUID, StudentType type) {
        students.add(new Student(firstName, lastName, BUID, type));
    }

    public void createStudent(String firstName, String middleName, String lastName, String BUID, StudentType type) {
        students.add(new Student(firstName, middleName, lastName, BUID, type));
    }

    //add functions
    public void addGroupInCriteria(Criteria criteria, String name, double weight) {
        CategoryGroup group = new CategoryGroup(name, weight);
        criteria.addGroup(group);
    }

    public void addCategoryInGroup(CategoryGroup group, String name, double totalScore, double weight, ScoreType type,
                                   int assignDay, int assignMonth, int assignYear,
                                   int dueDay, int dueMonth, int dueYear) {
        Category category = new Category(name, totalScore, weight, type, assignDay, assignMonth, assignYear, dueDay, dueMonth, dueYear);
        group.addCategory(category);
    }

    public void addStudentInCourse(Course course, Student student) {
        course.addStudent(student);
        courseGrades.add(new CourseGrade(course.getId(),student.getId()));
        Criteria criteria = course.getCriteria();
        ArrayList<CategoryGroup> groups = criteria.getCategoryGroups();
        for (CategoryGroup group : groups) {
            ArrayList<Category> categories = group.getCategories();
            for (Category category : categories) {
                categoryGrades.add(new CategoryGrade(course.getId(), category.getId(), student.getId()));
            }
        }

    }

    //helper accessor
    private Course getCourseById(String id) {
        for(Course course : courses) {
            if(course.getId().equals(id)) return course;
        }
        return null;
    }

    private Criteria getCriteriaTemplateById (String id) {
        for(Criteria criteria : this.criteriaTemplates) {
            if(criteria.getId().equals(id)) return criteria;
        }
        return null;
    }

    private Student getStudentById (String id) {
        for(Student student : this.students) {
            if(student.getId().equals(id)) return student;
        }
        return null;
    }

    /*
    For all String[][] first element is Id, Second element is name
     */
    public String[][] getCourseList() {
        int n = courses.size();
        if(n == 0) return null;
        String[][] courseList = new String[n][2];
        for(int i = 0; i < n; i++) {
            courseList[i][0] = courses.get(i).getId();
            courseList[i][1] = courses.get(i).getName();
        }
        return courseList;
    }

    public String[][] getCriteriaTemplateList() {
        int n = criteriaTemplates.size();
        if(n == 0) return null;
        String[][] templateList = new String[n][2];
        for(int i = 0; i < n; i++) {
            templateList[i][0] = criteriaTemplates.get(i).getId();
            templateList[i][1] = criteriaTemplates.get(i).getName();
        }
        return templateList;
    }

    public String[][] getStudentList() {
        int n = students.size();
        if(n == 0) return null;
        String[][] studentList = new String[n][2];
        for(int i = 0; i < n; i++) {
            studentList[i][0] = students.get(i).getId();
            studentList[i][1] = students.get(i).getName();
        }
        return studentList;
    }

    public String[][] getGroupListByCriteria(Criteria criteria) {
        ArrayList<CategoryGroup> groups = criteria.getCategoryGroups();
        int n = groups.size();
        if(n == 0) return null;
        String[][] groupList = new String[n][2];
        for(int i = 0; i < n; i++) {
            CategoryGroup group = groups.get(i);
            groupList[i][0] = group.getId();
            groupList[i][1] = group.getName();
        }
        return groupList;
    }

    public String[][] getCategoryListByGroup(CategoryGroup group) {
        ArrayList<Category> categories = group.getCategories();
        int n = categories.size();
        if(n == 0) return null;
        String[][] categoryList = new String[n][2];
        for(int i = 0; i < n; i++) {
            Category category = categories.get(i);
            categoryList[i][0] = category.getId();
            categoryList[i][1] = category.getName();
        }
        return categoryList;
    }

    public String[][] getGradeListInCourseByCategory (Course course, Category category) {
        int n = course.getStudents().size();
        if(n == 0) return null;
        String[][] gradeList = new String[n][3];
        int i = 0;
        for(CategoryGrade categoryGrade : categoryGrades) {
            if(categoryGrade.getCourseId().equals(course.getId())  && categoryGrade.getCategoryId().equals(category.getId())){
                gradeList[i][0] = categoryGrade.getId();
                gradeList[i][1] = getStudentById(categoryGrade.getStudentId()).getName();
                gradeList[i][2] = Double.toString(categoryGrade.getScore());
                i++;
            }
        }
        return gradeList;
    }



    public void deleteCourseByCourse(Course course){ courses.remove(course); }

    public boolean deleteCourseByCourseID(IdNumberCourse id){
        for(Course course : this.courses){
            if(course.checkCourseByID(id)){
                courses.remove(course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudentByStudentID(String courseId, String studentId){
        Student student = this.getStudentById(studentId);
        Course course = this.getCourseById(courseId);
        if(student == null || course == null)
            return false;
        this.courses.get(courses.indexOf(course)).removeStudent(student);
        for(CategoryGrade grade : categoryGrades){
            if(grade.checkGradeByCourseIDAndStudentID(courseId,studentId)){
                grade.freezeGrade();
                break;
            }
        }
        return true;
    }
}
