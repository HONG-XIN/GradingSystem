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

    private CourseGrade getCourseGradeById(String id) {
        for(CourseGrade grade : this.courseGrades) {
            if(grade.getId().equals(id)){
                return grade;
            }
        }
        return null;
    }

    private CategoryGrade getCategoryGradeById(String id) {
        for(CategoryGrade grade : this.categoryGrades) {
            if(grade.getId().equals(id)) {
                return grade;
            }
        }
        return null;
    }

    //mutator helper
    private void setPassword(String password) {
        this.password = password;
    }

    private void setCategoryGroupWeight(CategoryGroup group, double value) {
        group.setWeight(value);
    }

    private void setCategoryWeight(Category category, double value) {
        category.setWeight(value);
    }

    private void setCategoryGradeScore(CategoryGrade grade, double value) {
        grade.setScore(value);
    }

    private void setCourseGradeScore(CourseGrade grade, double value) {
        grade.setFinalScore(value);
    }

    private void setCourseGradeLetterGrade(CourseGrade grade, String letterGrade) {
        grade.setLetterGrade(letterGrade);
    }

    //validation helper
    private boolean isStudentGradeInCourseInCategory(CategoryGrade grade, Course course, Category category, Student student) {
        String courseId = course.getId();
        String categoryId = category.getId();
        String studentId = student.getId();
        return grade.getCourseId().equals(courseId) && grade.getCategoryId().equals(categoryId) && grade.getStudentId().equals(studentId);
    }

    private boolean isStudentCourseGrade(CourseGrade grade, Course course, Student student){
        return grade.getCourseId().equals(course.getId()) && grade.getStudentId().equals(student.getId());
    }
    //creation helper
    private Criteria makeCriteriaTemplate(String name) {
        Criteria criteria = new Criteria(name);
        criteriaTemplates.add(criteria);
        return criteria;
    }

    private Course makeCourseByTemplate(Criteria criteriaTemplate, String name, Semester semester) throws CloneNotSupportedException {
            Criteria criteria = (Criteria) criteriaTemplate.clone(); // get a copy of template
            Course course = new Course(name, semester, criteria);
            courses.add(course);
            return course;
    }

    private Semester makeSemester(String name, Date startDate, Date endDate) {
        return new Semester(name, startDate, endDate);
    }

    public Student makeStudent(String firstName, String lastName, String BUID, String email, StudentType type) {
        return new Student(firstName, lastName, BUID, email, type);
    }

    public Student makeStudent(String firstName, String middleName, String lastName, String BUID, String email, StudentType type) {
        return new Student(firstName, middleName, lastName, BUID, email, type);
    }
    //create functions
    public boolean createCriteriaTemplate(String name) {
        for(Criteria criteria : criteriaTemplates) {
            if(criteria.getName().equals(name)) return false;
        }
        makeCriteriaTemplate(name);
        return true;
    }

    public boolean createCourseByTemplate(Criteria criteriaTemplate, String name, Semester semester) throws CloneNotSupportedException {
        for(Course course : courses) {
            if(course.getName().equals(name) && course.getSemester().equals(semester)) return false;
        }
        makeCourseByTemplate(criteriaTemplate, name, semester);
        return true;
    }

    public boolean createSemester(String name,
                                  int startDay, int startMonth, int startYear,
                                  int endDay, int endMonth, int endYear) {
        Date startDate = new Date(startDay, startMonth, startYear);
        Date endDate = new Date(endDay, endMonth, endYear);
        if(startDate.compareTo(endDate) > 0) {
            for(Semester semester : semesters) {
                if(semester.getName().equals(name)) return false;
            }
            makeSemester(name, startDate, endDate);
            return true;
        }
        return false;
    }
    //add functions
    public boolean addGroupInCriteria(Criteria criteria, String name, double weight) {
        if(weight >= 0) {
            CategoryGroup group = new CategoryGroup(name, weight);
            criteria.addGroup(group);
            return true;
        } else{
            return false;
        }
    }

    public boolean addCategoryInGroup(CategoryGroup group, String name, double totalScore, double weight,
                                   int assignDay, int assignMonth, int assignYear,
                                   int dueDay, int dueMonth, int dueYear) {
        Date assignDate = new Date(assignDay, assignMonth, assignYear);
        Date dueDate = new Date(dueDay,dueMonth, dueYear);
        if(assignDate.compareTo(dueDate) >= 0  && totalScore > 0) {
            Category category = new Category(name, totalScore, weight, assignDate, dueDate);
            group.addCategory(category);
            return true;
        }
        return false;
    }

    public boolean addStudentInCourse(Course course, Student student) {
        ArrayList<Student> curStudentList = course.getStudents();
        for(Student curStudent : curStudentList) {
            if(curStudent.getId().equals(student.getId())) return false;
        }
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
        return true;
    }

    //password function
    public boolean isPasswordValid(String password) {
        return this.password.equals(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if(this.password.equals(oldPassword)) {
            setPassword(newPassword);
            return true;
        }
        return false;
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
                courseGrade.setLetterGrade("W");
                break;
            }
        }
        student.setStudentState(StudentState.FREEZE);
        return true;
    }

    //statistics functions
    private double calFinalScoreByStudent(Course course, Student student) {
        double finalScore = 0.0;
        double groupScore = 0.0;
        double categoryScore = 0.0;
        Criteria criteria = course.getCriteria();
        ArrayList<CategoryGroup> groups = criteria.getCategoryGroups();
        for(CategoryGroup group : groups) {
            ArrayList<Category> categories = group.getCategories();
            for(Category category : categories) {
                for(CategoryGrade grade : this.categoryGrades) {
                    if(isStudentGradeInCourseInCategory(grade, course, category, student)) {
                        double score = grade.getScore();
                        categoryScore = score < 0 ? grade.gradeConvert(category.getTotalScore(), score) : score;
                        break;
                    }
                }
                groupScore += categoryScore * category.getWeight();
                categoryScore = 0.0;
            }
            finalScore += groupScore * group.getWeight();
            groupScore = 0.0;
        }
        return finalScore;
    }

    private double getActiveStudentsNumber(Course course) {
        double count = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if (student.getState() == StudentState.ACTIVE) {
                count += 1;
            }
        }
        return count;
    }

    public double getAvgFinalScore(Course course) {
        double totalFinalScore = 0.0;
        double studentCount = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE) {
                studentCount += 1;
                for(CourseGrade grade : courseGrades) {
                    if(isStudentCourseGrade(grade, course, student)) {
                        totalFinalScore += grade.getFinalScore();
                        break;
                    }
                }
            }
        }
        return totalFinalScore / studentCount;
    }

    public double getMaxFinalScore(Course course) {
        double maxFinalScore = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE) {
                for(CourseGrade grade : courseGrades) {
                    if(isStudentCourseGrade(grade, course, student)) {
                        maxFinalScore = Math.max(grade.getFinalScore(), maxFinalScore);
                        break;
                    }
                }
            }
        }
        return maxFinalScore;
    }

    public double getMinFinalScore(Course course) {
        double minFinalScore = 100.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE) {
                for(CourseGrade grade : courseGrades) {
                    if(isStudentCourseGrade(grade, course, student)) {
                        minFinalScore = Math.min(grade.getFinalScore(), minFinalScore);
                        break;
                    }
                }
            }
        }
        return minFinalScore == 100.0 ? 0.0 : minFinalScore;
    }

    public double getSdFinalScore(Course course) {
        double averageFinalScore = getAvgFinalScore(course);
        double activeStudentCount = getActiveStudentsNumber(course);
        double sd = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE) {
                for(CourseGrade grade : courseGrades) {
                    if(isStudentCourseGrade(grade, course, student)) {
                        sd += Math.pow(grade.getFinalScore() - averageFinalScore, 2) / activeStudentCount;
                        break;
                    }
                }
            }
        }
        return Math.sqrt(sd);
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

    public String[][] getSemesterList() {
        int n = semesters.size();
        if(n == 0) return null;
        String[][] semesterList = new String[n][2];
        for(int i = 0; i < n; i++) {
            semesterList[i][0] = semesters.get(i).getId();
            semesterList[i][1] = semesters.get(i).getName();
        }
        return semesterList;
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

}
