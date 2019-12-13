package main.backend;

import java.util.ArrayList;

public class GradingSystem {
    private String password;
    private ArrayList<Course> courses;
    private ArrayList<Criteria> criteriaTemplates;
    private ArrayList<Semester> semesters;
    private ArrayList<CategoryGrade> categoryGrades;
    private ArrayList<CourseGrade> courseGrades;

    public GradingSystem() {
        this.password = "";
        this.courses = new ArrayList<>();
        this.criteriaTemplates = new ArrayList<>();
        this.semesters = new ArrayList<>();
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

    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public ArrayList<CategoryGrade> getCategoryGrades() {
        return categoryGrades;
    }

    public ArrayList<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    //mutator
    public void setPassword(String password) {
        this.password = password;
    }

    //password function
    public boolean changePassword(String oldPassword, String newPassword) {
        if(this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    //create functions
    public Criteria createCriteriaTemplate(String name) {
        Criteria criteria = new Criteria(name);
        criteriaTemplates.add(criteria);
        return criteria;
    }

    public Course createCourseByTemplate(Criteria criteriaTemplate, String name, Semester semester) throws CloneNotSupportedException {
            Criteria criteria = (Criteria) criteriaTemplate.clone(); // get a copy of template
            Course course = new Course(name, semester, criteria);
            courses.add(course);
            return course;
    }

    public Student createStudent(String firstName, String lastName, String BUID, String email, StudentType type) {
        return new Student(firstName, lastName, BUID, email, type);
    }

    public Student createStudent(String firstName, String middleName, String lastName, String BUID, String email, StudentType type) {
        return new Student(firstName, middleName, lastName, BUID, email, type);
    }

    public Semester createSemester(String name,
                                   int startDay, int startMonth, int startYear,
                                   int endDay, int endMonth, int endYear) {
        return new Semester(name, startDay, startMonth, startYear, endDay, endMonth, endYear);
    }

    //add functions
    public boolean addSemester(String name,
                            int startDay, int startMonth, int startYear,
                            int endDay, int endMonth, int endYear) {
        Semester semester = createSemester(name, startDay, startMonth, startYear, endDay, endMonth, endYear);
        semesters.add(semester);
        return true;
    }

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

    private Semester getSemesterById (String id) {
        for(Semester semester : this.semesters) {
            if(semester.getId().equals(id)) return semester;
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

    public String[][] getStudentListByCourse(Course course) {
        ArrayList<Student> students = course.getStudents();
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
                gradeList[i][1] = course.getStudentById(categoryGrade.getStudentId()).getName();
                gradeList[i][2] = Double.toString(categoryGrade.getScore());
                i++;
            }
        }
        return gradeList;
    }

    //delete functions
    public boolean deleteCourseByCourse(Course course){
        ArrayList<Student> students = course.getStudents();
        for(Student student : students) {
            deleteStudentInCourse(course, student);
        }
        courses.remove(course);
        return true;
    }

    public boolean deleteCourseByCourseId(String courseId){
        for(Course course : this.courses){
            if(course.checkCourseById(courseId)){
                deleteCourseByCourse(course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudentByStudentId(Course course, String studentId) {
        return deleteStudentInCourse(course, course.getStudentById(studentId));
    }

    public boolean deleteStudentInCourse(Course course, Student student) {
        if(course == null || student == null) {
            return false;
        }
        course.removeStudent(student);
        for (CourseGrade courseGrade : courseGrades) {
            if(courseGrade.checkGradeByCourseIdAndStudentId(course.getId(),student.getId())){
                courseGrades.remove(courseGrade);
                break;
            }
        }
        ArrayList<CategoryGrade> removeList = new ArrayList<>();
        for(CategoryGrade categoryGrade : categoryGrades){
            if(categoryGrade.checkGradeByCourseIdAndStudentId(course.getId(),student.getId())){
                removeList.add(categoryGrade);
            }
        }
        categoryGrades.removeAll(removeList);
        return true;
    }

    public boolean freeStudentByStudentId(Course course, String studentId) {
        return freezeStudentInCourse(course, course.getStudentById(studentId));
    }

    public boolean freezeStudentInCourse(Course course, Student student){
        if(course == null || student == null) {
            return false;
        }
        for (CourseGrade courseGrade : courseGrades) {
            if(courseGrade.checkGradeByCourseIdAndStudentId(course.getId(),student.getId())){
                courseGrade.setLetterGrade('W');
                break;
            }
        }
        for(CategoryGrade categoryGrade : categoryGrades){
            if(categoryGrade.checkGradeByCourseIdAndStudentId(course.getId(), student.getId())){
                categoryGrade.freezeGrade();
            }
        }
        return true;
    }
}
