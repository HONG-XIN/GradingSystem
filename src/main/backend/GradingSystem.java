package main.backend;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

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
    public Course getCourseById(String id) {
        for(Course course : courses) {
            if(course.getId().equals(id)) return course;
        }
        return null;
    }

    public Criteria getCriteriaTemplateById (String id) {
        for(Criteria criteria : this.criteriaTemplates) {
            if(criteria.getId().equals(id)) return criteria;
        }
        return null;
    }

    public Criteria getCriteriaInCourse(Course course) {
        return course.getCriteria();
    }

    public CourseGrade getCourseGradeById(String id) {
        for(CourseGrade grade : this.courseGrades) {
            if(grade.getId().equals(id)){
                return grade;
            }
        }
        return null;
    }

    public CategoryGrade getCategoryGradeById(String id) {
        for(CategoryGrade grade : this.categoryGrades) {
            if(grade.getId().equals(id)) {
                return grade;
            }
        }
        return null;
    }

    public Semester getSemesterById (String id) {
        for(Semester semester : this.semesters) {
            if(semester.getId().equals(id)) return semester;
        }
        return null;
    }

    public Criteria getNewCriteriaTemplate() {
        for(Criteria criteria : criteriaTemplates) {
            if(criteria.getName().equals("New")){
                return criteria;
            }
        }
        return null;
    }

    public CategoryGroup getCategoryGroupByIdInCriteria(Criteria criteria, String id) {
        ArrayList<CategoryGroup> groups = criteria.getCategoryGroups();
        for(CategoryGroup group : groups) {
            if(group.getId().equals(id))
                return group;
        }
        return null;
    }

    public Category getCategoryByIdInCategoryGroup(CategoryGroup group, String id) {
        ArrayList<Category> categories = group.getCategories();
        for(Category category : categories) {
            if(category.getId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    public String getCommentById(String id) {
        return Objects.requireNonNull(getCourseGradeById(id)).getComment();
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

    private void updateCriteriaTemplate() {
        ArrayList<Criteria> removeList = new ArrayList<>();
        ArrayList<Criteria> addList = new ArrayList<>();
        for(Course course : courses) {
            String criteriaId = course.getCriteria().getId();
            if(isCriteriaInCriteriaTemplates(criteriaId)) {
                //need update
                removeList.add(getCriteriaTemplateById(criteriaId));
            }
            addList.add(course.getCriteria());
        }
        criteriaTemplates.removeAll(removeList);
        criteriaTemplates.addAll(addList);
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

    private boolean isCategoryGradeInCourse(CategoryGrade grade, Course course, Category category) {
        String courseId = course.getId();
        String categoryId = category.getId();
        return grade.getCourseId().equals(courseId) && grade.getCategoryId().equals(categoryId);
    }

    public boolean isCategoryGradeEdible(CategoryGrade grade) {
        String courseId = grade.getCourseId();
        String studentId = grade.getStudentId();
        Course course = getCourseById(courseId);
        if(course == null) return false;
        Student student = course.getStudentById(studentId);
        if(student == null) return false;
        return student.getState() != StudentState.FREEZE;
    }

    public boolean isCourseGradeEdible(CourseGrade grade) {
        String courseId = grade.getCourseId();
        String studentId = grade.getStudentId();
        Course course = getCourseById(courseId);
        if(course == null) return false;
        Student student = course.getStudentById(studentId);
        if(student == null) return false;
        return student.getState() != StudentState.FREEZE;
    }

    public boolean hasComment(CourseGrade grade) {
        return grade.hasComment();
    }

    public boolean isCriteriaInCriteriaTemplates(String id) {
        for(Criteria criteria : criteriaTemplates) {
            if(criteria.getId().equals(id)) return true;
        }
        return false;
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
            course.getCriteria().setName(semester.getName() + "-" + name);
            course.getCriteria().setId(IdNumber.generateRandomNumber());
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
            Semester semester = makeSemester(name, startDate, endDate);
            semesters.add(semester);
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

    public boolean addCategoryInCourse(Course course, CategoryGroup group, String name, double totalScore, double weight,
                                       int assignDay, int assignMonth, int assignYear,
                                       int dueDay, int dueMonth, int dueYear) {
        if (addCategoryInGroup(group, name, totalScore, weight, assignDay, assignMonth, assignYear, dueDay, dueMonth, dueYear)) {
            Category newAdded = group.getCategories().get(group.getCategories().size() - 1);
            for (Student student : course.getStudents()) {
                categoryGrades.add(new CategoryGrade(course.getId(), newAdded.getId(), student.getId()));
            }
            return true;
        } else {
            return false;
        }
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
    public void deleteNewCriteriaTemplate() {
        for(Criteria criteria : criteriaTemplates) {
            if(criteria.getName().equals("New")){
                criteriaTemplates.remove(criteria);
                break;
            }
        }
    }

    public boolean deleteCategoryInGroup(CategoryGroup group, Category category) {
        for(Category categoryInGroup : group.getCategories()) {
            if(categoryInGroup.getId().equals(category.getId())){
                group.removeCategory(category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategoryInCourse(Course course, CategoryGroup group, Category category) {
        ArrayList<CategoryGrade> removeList = new ArrayList<>();
        for(CategoryGrade grade : categoryGrades) {
            if(isCategoryGradeInCourse(grade, course, category)) {
                removeList.add(grade);
            }
        }
        categoryGrades.removeAll(removeList);
        return deleteCategoryInGroup(group, category);
    }

    public boolean deleteCategoryGroupInCriteria(Criteria criteria, CategoryGroup group) {
        for(CategoryGroup groupInCriteria : criteria.getCategoryGroups()) {
            if(groupInCriteria.getId().equals(group.getId())){
                criteria.removeGroup(group);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategoryGroupInCourse(Course course, Criteria criteria, CategoryGroup group) {
        ArrayList<CategoryGrade> removeGradeList = new ArrayList<>();
        ArrayList<Category> removeCategoryList = new ArrayList<>();
        for(Category category : group.getCategories()) {
            for(CategoryGrade grade : categoryGrades) {
                if(isCategoryGradeInCourse(grade, course, category)) {
                    removeGradeList.add(grade);
                }
            }
            removeCategoryList.add(category);
        }
        categoryGrades.removeAll(removeGradeList);
        group.getCategories().removeAll(removeCategoryList);
        return deleteCategoryGroupInCriteria(criteria, group);
    }

    public boolean deleteCourseByCourse(Course course){
        updateCriteriaTemplate();
        ArrayList<CourseGrade> courseGradeRemoveList = new ArrayList<>();
        ArrayList<CategoryGrade> categoryGradeRemoveList = new ArrayList<>();
        for(CourseGrade grade : courseGrades) {
            if(grade.getCourseId().equals(course.getId())){
                courseGradeRemoveList.add(grade);
            }
        }
        for(CategoryGrade grade : categoryGrades) {
            if(grade.getCourseId().equals(course.getId())){
                categoryGradeRemoveList.add(grade);
            }
        }
        courseGrades.removeAll(courseGradeRemoveList);
        categoryGrades.removeAll(categoryGradeRemoveList);
        courses.remove(course);
        return true;
    }

    public boolean deleteCourseByCourseId(String courseId){
        for(Course course : this.courses){
            if(course.checkCourseById(courseId)){
                return deleteCourseByCourse(course);
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

    //mutator functions
    public boolean changeCategoryGroupName(CategoryGroup group, String name) {
        if (name != null && !name.equals("")) {
            group.setName(name);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeCategoryGroupWeight(CategoryGroup group, double value) {
        if (value >= 0 && value <= 100) {
            setCategoryGroupWeight(group, value);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeCategoryName(Category category, String name) {
        if (name != null && !name.equals("")) {
            category.setName(name);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeCategoryWeight(Category category, double value) {
        if (value >= 0 && value <= 100) {
            setCategoryWeight(category, value);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeCategoryDate(Category category, int assignDay, int assignMonth, int assignYear,
                                      int dueDay, int dueMonth, int dueYear) {
        Date assignDate = new Date(assignDay, assignMonth, assignYear);
        Date dueDate = new Date(dueDay,dueMonth, dueYear);
        if(assignDate.compareTo(dueDate) >= 0) {
            category.setAssignDate(assignDate);
            category.setDueDate(dueDate);
            return true;
        }
        return false;
    }

    public boolean changeCategoryTotalScore(Category category, double value) {
        if(value >= 0) {
            category.setTotalScore(value);
            return true;
        }
        return false;
    }

    public boolean changeCategoryGradeValue(CategoryGrade grade, double value) {
        if(value <= 100 && isCategoryGradeEdible(grade)) {
            grade.setScore(value);
            return true;
        } else {
            return false;
        }

    }

    public boolean changeCourseGradeComment(CourseGrade grade, String comment) {
        grade.setComment(comment);
        return hasComment(grade);
    }

    public boolean changeCourseGradeBonus(CourseGrade grade, int value) {
        if(value >= 0) {
            grade.setBonus(value);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeCourseCurve(Course course, int value) {
        if(value >= 0) {
            course.setCurveValue(value);
            return true;
        } else {
            return false;
        }
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
        return finalScore + course.getCurveValue();
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

    public double getUnderGradAvgFinalScore(Course course) {
        double totalFinalScore = 0.0;
        double studentCount = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.UNDERGRAD)) {
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

    public double getGradAvgFinalScore(Course course) {
        double totalFinalScore = 0.0;
        double studentCount = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.GRAD)) {
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

    public double getUnderGradMaxFinalScore(Course course) {
        double maxFinalScore = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.UNDERGRAD)) {
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

    public double getGradMaxFinalScore(Course course) {
        double maxFinalScore = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.GRAD)) {
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

    public double getUnderGradMinFinalScore(Course course) {
        double minFinalScore = 100.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.UNDERGRAD)) {
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

    public double getGradMinFinalScore(Course course) {
        double minFinalScore = 100.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.GRAD)) {
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

    public double getUnderGradSdFinalScore(Course course) {
        double averageFinalScore = getAvgFinalScore(course);
        double activeStudentCount = getActiveStudentsNumber(course);
        double sd = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.UNDERGRAD)) {
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

    public double getGradSdFinalScore(Course course) {
        double averageFinalScore = getAvgFinalScore(course);
        double activeStudentCount = getActiveStudentsNumber(course);
        double sd = 0.0;
        ArrayList<Student> activeStudentList = course.getStudents();
        for(Student student : activeStudentList) {
            if(student.getState() == StudentState.ACTIVE && student.getType().equals(StudentType.GRAD)) {
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
        String[][] courseList = new String[n][5];
        for(int i = 0; i < n; i++) {
            Course course = courses.get(i);
            courseList[i][0] = course.getId();
            courseList[i][1] = course.getName();
            courseList[i][2] = course.getSemester().getName();
            courseList[i][3] = course.getSemester().getStartDate();
            courseList[i][4] = course.getSemester().getEndDate();
        }
        return courseList;
    }

    public String[][] getCourseListBySemester(Semester semester) {
        int count = 0;
        for(Course course : courses) {
            if(course.getSemester().getId().equals(semester.getId())){
                count ++;
            }
        }
        if(count == 0) return null;
        String[][] courseList = new String[count][5];
        int i = 0;
        for(Course course : courses) {
            if(course.getSemester().getId().equals(semester.getId())){
                courseList[i][0] = course.getId();
                courseList[i][1] = course.getName();
                courseList[i][2] = course.getSemester().getName();
                courseList[i][3] = course.getSemester().getStartDate();
                courseList[i][4] = course.getSemester().getEndDate();
                i++;
            }
        }
        return courseList;
    }

    public String[][] getCriteriaTemplateList() {
        updateCriteriaTemplate();
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
            studentList[i][1] = students.get(i).getNameString();
        }
        return studentList;
    }

    public String[][] getGroupListByCriteria(Criteria criteria) {
        ArrayList<CategoryGroup> groups = criteria.getCategoryGroups();
        int n = groups.size();
        if(n == 0) return null;
        String[][] groupList = new String[n][3];
        for(int i = 0; i < n; i++) {
            CategoryGroup group = groups.get(i);
            groupList[i][0] = group.getId();
            groupList[i][1] = group.getName();
            groupList[i][2] = Double.toString(group.getWeight());
        }
        return groupList;
    }

    public String[][] getCategoryListByGroup(CategoryGroup group) {
        ArrayList<Category> categories = group.getCategories();
        int n = categories.size();
        if(n == 0) return null;
        String[][] categoryList = new String[n][3];
        for(int i = 0; i < n; i++) {
            Category category = categories.get(i);
            categoryList[i][0] = category.getId();
            categoryList[i][1] = category.getName();
            categoryList[i][2] = Double.toString(category.getWeight());
        }
        return categoryList;
    }

    public String[][] getGradeListInCourseByCategory (Course course, Category category) {
        int n = course.getStudents().size();
        if(n == 0) return null;
        String[][] gradeList = new String[n][5];
        int i = 0;
        for(CategoryGrade categoryGrade : categoryGrades) {
            if(categoryGrade.getCourseId().equals(course.getId()) && categoryGrade.getCategoryId().equals(category.getId())){
                Student curStudent = course.getStudentById(categoryGrade.getStudentId());
                gradeList[i][0] = categoryGrade.getId();
                gradeList[i][1] = curStudent.getNameString();
                gradeList[i][2] = curStudent.getBUID();
                gradeList[i][3] = curStudent.getEmail();
                gradeList[i][4] = Double.toString(categoryGrade.getScore());
                i++;
            }
        }
        return gradeList;
    }

    public String[][] getFinalGradeListByCourse(Course course) {
        int n = course.getStudents().size();
        if(n == 0) return null;
        String[][] gradeList = new String[n][7];
        int i = 0;
        for(CourseGrade courseGrade : courseGrades) {
            if(courseGrade.getCourseId().equals(course.getId())) {
                Student student = course.getStudentById(courseGrade.getStudentId());
                courseGrade.setFinalScore(calFinalScoreByStudent(course, student));
                gradeList[i][0] = courseGrade.getId();
                gradeList[i][1] = student.getNameString();
                gradeList[i][2] = student.getBUID();
                gradeList[i][3] = student.getEmail();
                gradeList[i][4] = Integer.toString(courseGrade.getBonus());
                gradeList[i][5] = Double.toString(courseGrade.getFinalScore());
                gradeList[i][6] = courseGrade.getLetterGrade();
                i++;
            }
        }
        return gradeList;
    }

    public String[][] getStatisticListInCourse(Course course) {
        String[][] statisticList = new String[1][4];
        statisticList[0][0] = Double.toString(getAvgFinalScore(course));
        statisticList[0][1] = Double.toString(getMinFinalScore(course));
        statisticList[0][2] = Double.toString(getMaxFinalScore(course));
        statisticList[0][3] = Double.toString(getSdFinalScore(course));
        return  statisticList;
    }

    public String[][] getGradStatisticListInCourse(Course course) {
        String[][] statisticList = new String[1][4];
        statisticList[0][0] = Double.toString(getGradAvgFinalScore(course));
        statisticList[0][1] = Double.toString(getGradMinFinalScore(course));
        statisticList[0][2] = Double.toString(getGradMaxFinalScore(course));
        statisticList[0][3] = Double.toString(getGradSdFinalScore(course));
        return  statisticList;
    }

    public String[][] getUnderGradStatisticListInCourse(Course course) {
        String[][] statisticList = new String[1][4];
        statisticList[0][0] = Double.toString(getUnderGradAvgFinalScore(course));
        statisticList[0][1] = Double.toString(getUnderGradMinFinalScore(course));
        statisticList[0][2] = Double.toString(getUnderGradMaxFinalScore(course));
        statisticList[0][3] = Double.toString(getUnderGradSdFinalScore(course));
        return  statisticList;
    }

    /**
     *
     * @param path path to dictionary
     * @param sheetname name of sheet
     * @param headtable a list of first row, name of each column
     * @param value
     */
    public void exportTable(String path, String sheetname, List<String> headtable, List<List<String>> value){
        ExcelUtils.exportExcel(path,sheetname,headtable,value);
    }

}
