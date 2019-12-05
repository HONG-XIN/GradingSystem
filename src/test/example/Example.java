package test.example;

import main.backend.GradingSystem;
import main.backend.Semester;
import main.backend.StudentType;
import main.debug.Debug;

public class Example {
    public static void main(String[] args) {
        // example of debug print
        Debug.println("Our project name is %1$s, %1$s and %2$s.", "GS", "Grading System");


        /*
        Below is sample demo of backend
         */
        GradingSystem testGradSys = new GradingSystem();
        Debug.println("Grading System is being instantiated");
        demo1(testGradSys);


    }

    public static void demo1 (GradingSystem testGradSys) {
        testGradSys.createTemplate("template1");
        Debug.println("Created a new template named template1");
        String[][] templateList = testGradSys.getTemplateList();
        String curTemplateId = templateList[0][0];
        Debug.printList(templateList);

        Debug.println("Adding group name Assignments, weight 50");
        Debug.println("Adding group name Exams, weight 50");
        testGradSys.addGroupInTemplate(curTemplateId,"Assignments", 50);
        testGradSys.addGroupInTemplate(curTemplateId,"Exams",50);
        String[][] curGroupList = testGradSys.getGroupListByTemplateId(curTemplateId);
        Debug.printList(curGroupList);

        Debug.println("Creating course CS591P1 by template1");
        Semester semester = new Semester("2019Fall",1,1,2019,2,2,2019);
        testGradSys.createCourseByTemplate(curTemplateId,"CS591P1", semester);
        String[][] curCourseList = testGradSys.getCourseList();
        String courseId = curCourseList[0][0];
        Debug.printList(curCourseList);

        Debug.println("Adding Category hw1, weight 30 under Assignments");
        Debug.println("Adding Category hw2, weight 70 under Assignments");
        String groupId = curGroupList[0][0];
        testGradSys.addCategoryInGroup(groupId, "hw1",100, 30, 1,1,2019,2,2,2019);
        testGradSys.addCategoryInGroup(groupId, "hw2",50, 70, 1,1,2019,2,2,2019);
        String[][] categoryList = testGradSys.getCategoryListByGroupId(groupId);
        Debug.printList(categoryList);

        Debug.println("Adding Category Midterm, weight 40 under Exams");
        Debug.println("Adding Category Final, weight 60 under Exams");
        groupId = curGroupList[1][0];
        testGradSys.addCategoryInGroup(groupId, "Midterm",60, 40, 1,1,2019,2,2,2019);
        testGradSys.addCategoryInGroup(groupId, "Final",80, 60, 1,1,2019,2,2,2019);
        categoryList = testGradSys.getCategoryListByGroupId(groupId);
        Debug.printList(categoryList);

        Debug.println("Adding student dezhou wang, U00000000, Graduate");
        Debug.println("Adding student kaiyuan fan, U12345678, UnderGraduate");
        testGradSys.createStudent("dezhou", "wang", "U00000000", StudentType.GRAD);
        testGradSys.createStudent("kaiyuan", "fan", "U12345678", StudentType.UNDERGRAD);
        String[][] studentList = testGradSys.getStudentList();
        Debug.printList(studentList);
        String studentId1 = studentList[0][0], studentId2 = studentList[1][0];
        testGradSys.addStudentInCourse(courseId,studentId1);
        testGradSys.addStudentInCourse(courseId,studentId2);






    }

}
