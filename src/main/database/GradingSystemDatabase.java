package main.database;

import main.backend.*;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.RecordIterable;

import java.util.ArrayList;

public interface GradingSystemDatabase {

    //from RAM to DB
    public static Document write(GradingSystem gradingSystem) {
        Document GradingSystemDoc = new Document();
        ArrayList<Document> CoursesList = new ArrayList<Document>();
        ArrayList<Document> StudentsList = new ArrayList<Document>();
        ArrayList<Document> criteriaTemplatesList = new ArrayList<Document>();
        ArrayList<Document> categoryGradesList = new ArrayList<Document>();
        ArrayList<Document> courseGradesList = new ArrayList<Document>();
        if (gradingSystem.getCourses() != null) {
            for (int i = 0; i < gradingSystem.getCourses().size(); i++) {
                CoursesList.add(gradingSystem.getCourses().get(i).write());
            }
        }
        if(gradingSystem.getStudents() != null){
            for (int i = 0; i < gradingSystem.getStudents().size(); i++) {
                StudentsList.add(gradingSystem.getStudents().get(i).write());
            }
        }
        if (gradingSystem.getCriteriaTemplates() != null) {
            for (int i = 0; i < gradingSystem.getCriteriaTemplates().size(); i++) {
                criteriaTemplatesList.add(gradingSystem.getCriteriaTemplates().get(i).write());
            }
        }
        if (gradingSystem.getCategoryGrades() != null) {
            for (int i = 0; i < gradingSystem.getCategoryGrades().size(); i++) {
                categoryGradesList.add(gradingSystem.getCategoryGrades().get(i).write());
            }
        }
        if (gradingSystem.getCourseGrades() != null) {
            for (int i = 0; i < gradingSystem.getCourseGrades().size(); i++) {
                courseGradesList.add(gradingSystem.getCourseGrades().get(i).write());
            }
        }
        GradingSystemDoc.put("courses", CoursesList);
        GradingSystemDoc.put("students", StudentsList);
        GradingSystemDoc.put("criteriaTemplates", criteriaTemplatesList);
        GradingSystemDoc.put("categoryGrades", categoryGradesList);
        GradingSystemDoc.put("courseGrades", courseGradesList);
        return GradingSystemDoc;
    }

    //from DB to RAM
    public static void read(GradingSystem gradingSystem, Nitrite db) {
        RecordIterable<Document> result = db.getCollection("GradingSystem").find();
        for(Document GradingSystemDoc: result){
            if(GradingSystemDoc != null) {
                for (Document CourseDoc : (ArrayList<Document>) GradingSystemDoc.get("courses")) {
                    if (CourseDoc != null) {
                        Course course = new Course();
                        course.read(CourseDoc);
                        gradingSystem.getCourses().add(course);
                    }
                }
                for (Document StudentDoc : (ArrayList<Document>) GradingSystemDoc.get("students")) {
                    if (StudentDoc != null) {
                        Student student = new Student();
                        student.read(StudentDoc);
                        gradingSystem.getStudents().add(student);
                    }
                }
                for (Document criteriaTemplatesDoc : (ArrayList<Document>) GradingSystemDoc.get("criteriaTemplates")) {
                    if (criteriaTemplatesDoc != null) {
                        Criteria criteria = new Criteria();
                        criteria.read(criteriaTemplatesDoc);
                        gradingSystem.getCriteriaTemplates().add(criteria);
                    }
                }
                for (Document categoryGradesDoc : (ArrayList<Document>) GradingSystemDoc.get("categoryGrades")) {
                    if (categoryGradesDoc != null) {
                        CategoryGrade categoryGrade = new CategoryGrade();
                        categoryGrade.read(categoryGradesDoc);
                        gradingSystem.getCategoryGrades().add(categoryGrade);
                    }
                }
                for (Document courseGradesDoc : (ArrayList<Document>) GradingSystemDoc.get("courseGrades")) {
                    if (courseGradesDoc != null) {
                        CourseGrade courseGrade = new CourseGrade();
                        courseGrade.read(courseGradesDoc);
                        gradingSystem.getCourseGrades().add(courseGrade);
                    }
                }
            }
        }
    }
}
