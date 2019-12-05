package main.backend;

import java.util.ArrayList;

public class GradingSystem {

    private ArrayList<Course> courses;
    private ArrayList<Template> templates;
    private ArrayList<CategoryGroup> groups;
    private ArrayList<Category> categories;
    private ArrayList<Student> students;
    private ArrayList<CategoryGrade> categoryGrades;
    private ArrayList<CourseGrade> courseGrades;

    public GradingSystem() {
        this.courses = new ArrayList<>();
        this.templates = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.students = new ArrayList<>();
        this.categoryGrades = new ArrayList<>();
        this.courseGrades = new ArrayList<>();
    }

    //accessor
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Template> getTemplates() {
        return templates;
    }

    public ArrayList<CategoryGroup> getGroups() {
        return groups;
    }

    public ArrayList<Category> getCategories() {
        return categories;
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
    public void createTemplate(String name) {
        templates.add(new Template(name));
    }

    public void createCourseByTemplate(String templateId, String name, Semester semester) {
        if(getTemplateById(templateId)!=null) {
            Course course = new Course(name, semester, templateId);
            courses.add(course);
        }
    }

    public void createStudent(String firstName, String lastName, String BUID, StudentType type) {
        students.add(new Student(firstName, lastName, BUID, type));
    }

    public void createStudent(String firstName, String middleName, String lastName, String BUID, StudentType type) {
        students.add(new Student(firstName, middleName, lastName, BUID, type));
    }

    //add functions
    public void addGroupInTemplate(String templateId, String name, double weight) {
        Template template = getTemplateById(templateId);
        if(template != null) {
            CategoryGroup group = new CategoryGroup(name, weight);
            groups.add(group);
            template.addGroupByGroupId(group.getId());
        }
    }

    public void addCategoryInGroup(String groupId, String name, double totalScore, double weight,
                                   int assignDay, int assignMonth, int assignYear,
                                   int dueDay, int dueMonth, int dueYear) {
        CategoryGroup group = getGroupById(groupId);
        if(group != null) {
            Category category = new Category(name, totalScore, weight, assignDay, assignMonth, assignYear, dueDay, dueMonth, dueYear);
            categories.add(category);
            group.addCategoryByCategoryId(category.getId());
        }
    }

    public void addStudentInCourse(String courseId, String studentId) {
        courseGrades.add(new CourseGrade(courseId,studentId));
        Course course = getCourseById(courseId);
        String templateId = course.getTemplateId();
        Template template = getTemplateById(templateId);
        String[] groupIdList = template.getCategoryGroupIdList();
        for(int i = 0; i < groupIdList.length; i++) {
            CategoryGroup group = getGroupById(groupIdList[i]);
            String[] categoryIdList = group.getCategoryIdList();
            for (int j = 0; j < categoryIdList.length; j++) {
                categoryGrades.add(new CategoryGrade(courseId, categoryIdList[i], studentId));
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

    private Template getTemplateById (String id) {
        for(Template template : templates) {
            if(template.getId().equals(id)) return template;
        }
        return null;
    }

    private CategoryGroup getGroupById(String id) {
        for(CategoryGroup group : groups) {
            if(group.getId().equals(id)) return group;
        }
        return null;
    }

    private Category getCategoryById(String id) {
        for(Category category : categories) {
            if(category.getId().equals(id)) return category;
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

    public String[][] getTemplateList() {
        int n = templates.size();
        if(n == 0) return null;
        String[][] templateList = new String[n][2];
        for(int i = 0; i < n; i++) {
            templateList[i][0] = templates.get(i).getId();
            templateList[i][1] = templates.get(i).getName();
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

    public String[][] getGroupListByTemplateId(String templateId) {
        Template template = getTemplateById(templateId);
        String[] groupIdList = template.getCategoryGroupIdList();
        int n = groupIdList.length;
        if(n == 0) return null;
        String[][] groupList = new String[n][2];
        for(int i = 0; i < n; i++) {
            CategoryGroup group = getGroupById(groupIdList[i]);
            groupList[i][0] = group.getId();
            groupList[i][1] = group.getName();
        }
        return groupList;
    }

    public String[][] getCategoryListByGroupId(String groupId) {
        CategoryGroup group = getGroupById(groupId);
        String[] categoryIdList = group.getCategoryIdList();
        int n = categoryIdList.length;
        if(n == 0) return null;
        String[][] categoryList = new String[n][2];
        for(int i = 0; i < n; i++) {
            Category category = getCategoryById(categoryIdList[i]);
            categoryList[i][0] = category.getId();
            categoryList[i][1] = category.getName();
        }
        return categoryList;

    }
}
