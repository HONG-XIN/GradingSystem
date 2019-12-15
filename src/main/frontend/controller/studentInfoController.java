package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import main.backend.Course;
import main.backend.Student;
import main.backend.StudentType;
import main.frontend.model.UIStudent;
import main.frontend.model.UIStudentInfo;

import java.awt.*;

public class studentInfoController {

    private String courseId;
    private Course course;
    private Student student;

    @FXML
    javafx.scene.control.TextField tfFirst, tfMiddle, tfLast, tfID, tfEmail;

    @FXML
    ChoiceBox cbType;

    @FXML
    Label info;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("studentInfo")) {

                    courseId = ((UIStudentInfo)userData).getCourseId();
                    student = ((UIStudentInfo)userData).getStudent();
                    course = Main.gs.getCourseById(courseId);
                    cbType.getItems().addAll("graduate", "undergraduate");
                    cbType.setValue("graduate");
                    if (student == null) {
                        tfFirst.setText("");
                        tfMiddle.setText("");
                        tfLast.setText("");
                        tfID.setText("");
                        tfEmail.setText("");
                    } else {
                        tfFirst.setText(student.getName());
                        tfMiddle.setText(student.getName());
                        tfLast.setText(student.getName());
                        tfID.setText(student.getBUID());
                        tfEmail.setText(student.getEmail());
                        cbType.setValue(student.getType().toString()+"uate");
                    }
                }
            }
        });
    }

    @FXML
    protected void btStudents(ActionEvent e) {
        Main.changeScreen("tabStudents");
    }

    @FXML
    protected void btFinalScore(ActionEvent e) {
        Main.changeScreen("tabFinalScore");
    }

    @FXML
    protected void btGradingCriteria(ActionEvent e) {
        Main.changeScreen("tabGradingCriteria");
    }

    @FXML
    protected void btSave(ActionEvent e) {
        String first = tfFirst.getText();
        String middle = tfMiddle.getText();
        String last = tfLast.getText();
        String id = tfID.getText();
        String email = tfEmail.getText();
        String type = cbType.getValue().toString();
        StudentType t = StudentType.UNDERGRAD;
        if (type.equals("graduate")) {
            t = StudentType.GRAD;
        }

        if (student == null) {
            Student s = Main.gs.makeStudent(first, middle, last, id, email, t);
            if (Main.gs.addStudentInCourse(course, s)) {
                Main.changeScreen("tabStudents", courseId);
            } else {
                info.setText("Fail");
            }

        } else {
            student.setStudentName(first, middle, last);
            student.setBUID(id);
            student.setEmail(email);
            student.setStudentType(t);
            info.setText("Fail");
        }

    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("tabStudents", courseId);
    }
}
