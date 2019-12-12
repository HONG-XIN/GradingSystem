package test.example;

import main.backend.*;
import main.database.GradingSystemDatabase;
import main.debug.Debug;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

public class Example implements GradingSystemDatabase {
    public static void main(String[] args) throws CloneNotSupportedException {
        // example of debug print
        Debug.println("Our project name is %1$s, %1$s and %2$s.", "GS", "Grading System");


        /*
        Below is sample demo of backend
         */
        GradingSystem testGradSys = new GradingSystem();
        Nitrite db = Nitrite.builder()
                .filePath("./GradingSystem.db")
                .openOrCreate();
        //NitriteCollection GradingSystemCollection = db.getCollection("GradingSystem");
        Debug.println("Grading System is being instantiated");
        //demo1(testGradSys);
        GradingSystemDatabase.read(testGradSys, db);
        System.out.println(testGradSys.getStudents().get(0).getName().toString());
        System.out.println(testGradSys.getCriteriaTemplates().get(0).getId());
        //GradingSystemCollection.insert(GradingSystemDatabase.write(testGradSys));
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
        testGradSys.createCourseByTemplate(template1,"CS591P1", semester);
        String[][] curCourseList = testGradSys.getCourseList();
        Debug.printList(curCourseList);

        Debug.println("Adding student dezhou wang, U00000000, Graduate");
        Debug.println("Adding student kaiyuan fan, U12345678, UnderGraduate");
        testGradSys.createStudent("dezhou", "wang", "U00000000", StudentType.GRAD);
        testGradSys.createStudent("kaiyuan", "fan", "U12345678", StudentType.UNDERGRAD);
        String[][] studentList = testGradSys.getStudentList();
        Debug.printList(studentList);
        Course cs591p1 = testGradSys.getCourses().get(0);
        Student dezhou = testGradSys.getStudents().get(0), kaiyuan = testGradSys.getStudents().get(1);
        testGradSys.addStudentInCourse(cs591p1,dezhou);
        testGradSys.addStudentInCourse(cs591p1,kaiyuan);


    }

}
