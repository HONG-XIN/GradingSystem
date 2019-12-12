package test.example;

import main.backend.*;
import main.debug.Debug;

public class Example {
    public static void main(String[] args) throws CloneNotSupportedException {
        // example of debug print
        Debug.println("Our project name is %1$s, %1$s and %2$s.", "GS", "Grading System");


        /*
        Below is sample demo of backend
         */
        GradingSystem testGradSys = new GradingSystem();
        Debug.println("Grading System is being instantiated");
        demo1(testGradSys);

    }

    public static void demo1 (GradingSystem testGradSys) throws CloneNotSupportedException {
        testGradSys.createCriteriaTemplate("template1");
        Debug.println("Created a new template named template1");
        Criteria template1 = testGradSys.getCriteriaTemplates().get(0);
        String[][] templateList = testGradSys.getCriteriaTemplateList();
        Debug.printList(templateList);

        Debug.println("Adding group name Assignments, weight 50");
        Debug.println("Adding group name Exams, weight 50");
        testGradSys.addGroupInCriteria(template1,"Assignments", 50);
        testGradSys.addGroupInCriteria(template1,"Exams",50);
        String[][] curGroupList = testGradSys.getGroupListByCriteria(template1);
        Debug.printList(curGroupList);

        Debug.println("Adding Category hw1, weight 30 under Assignments");
        Debug.println("Adding Category hw2, weight 70 under Assignments");
        CategoryGroup assignmentGroup = template1.getCategoryGroups().get(0);
        testGradSys.addCategoryInGroup(assignmentGroup, "hw1",100, 30, ScoreType.PERCENTAGE ,1,1,2019,2,2,2019);
        testGradSys.addCategoryInGroup(assignmentGroup, "hw2",50, 70, ScoreType.DEDUCTION, 1,1,2019,2,2,2019);
        String[][] categoryList = testGradSys.getCategoryListByGroup(assignmentGroup);
        Debug.printList(categoryList);

        Debug.println("Adding Category Midterm, weight 40 under Exams");
        Debug.println("Adding Category Final, weight 60 under Exams");
        CategoryGroup examGroup = template1.getCategoryGroups().get(1);
        testGradSys.addCategoryInGroup(examGroup, "Midterm",60, 40, ScoreType.PERCENTAGE,1,1,2019,2,2,2019);
        testGradSys.addCategoryInGroup(examGroup, "Final",80, 60, ScoreType.PERCENTAGE,1,1,2019,2,2,2019);
        categoryList = testGradSys.getCategoryListByGroup(examGroup);
        Debug.printList(categoryList);

        Debug.println("Creating course CS591P1 by template1");
        Semester semester = new Semester("2019Fall",1,1,2019,2,2,2019);
        Course cs591 = testGradSys.createCourseByTemplate(template1,"CS591P1", semester);
        String[][] curCourseList = testGradSys.getCourseList();
        Debug.printList(curCourseList);


        Student dezhou = testGradSys.createStudent("dezhou", "wang", "U00000000", "dezhou",StudentType.GRAD);
        Student kaiyuan = testGradSys.createStudent("kaiyuan", "fan", "U12345678", "fankai", StudentType.UNDERGRAD);
        Debug.println("Adding student dezhou and kaiyuan to cs591p1");
        testGradSys.addStudentInCourse(cs591, dezhou);
        testGradSys.addStudentInCourse(cs591, kaiyuan);
        String[][] studentList = testGradSys.getStudentListByCourse(cs591);
        Debug.printList(studentList);

        Debug.println("Display hw1 grades: ");
        String[][] gradeList = testGradSys.getGradeListInCourseByCategory(cs591, cs591.getCriteria().getCategoryGroups().get(0).getCategories().get(0)); // cs591p1 -> criteria -> assignments -> hw1
        Debug.printList(gradeList);
    }

}
