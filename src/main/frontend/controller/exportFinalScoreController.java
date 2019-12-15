package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.backend.Course;

import javax.swing.*;

public class exportFinalScoreController {

    private String courseId;
    private Course course;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("exportFinalScore")) {
                    courseId = userData.toString();
                    course = Main.gs.getCourseById(courseId);
                }
            }
        });
    }

    @FXML
    protected void btLeave(ActionEvent e) {
        Main.changeScreen("coursesList");
    }

    @FXML
    protected void btStudents(ActionEvent e) {
        Main.changeScreen("tabStudents", courseId);
    }

    @FXML
    protected void btFinalScore(ActionEvent e) {
        Main.changeScreen("tabFinalScore", courseId);
    }

    @FXML
    protected void btGradingCriteria(ActionEvent e) {
        Main.changeScreen("tabGradingCriteria", courseId);
    }

    @FXML
    protected void btExport(ActionEvent e) {

    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("tabFinalScore", courseId);
    }
}
