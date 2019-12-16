package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import main.backend.Course;

import javax.swing.*;

public class exportFinalScoreController {

    private String courseId;
    private Course course;

    @FXML
    CheckBox cbStudentName, cbFinalScore, cbBonus, cbLetter, cbComment, cbBUID, cbEmail;

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
        boolean hasStudentName = cbStudentName.isSelected();
        boolean hasFinalScore = cbFinalScore.isSelected();
        boolean hasBonus = cbBonus.isSelected();
        boolean hasLetter = cbLetter.isSelected();
        boolean hasComment = cbComment.isSelected();
        boolean hasBUID = cbBUID.isSelected();
        boolean hasEmail = cbEmail.isSelected();
    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("tabFinalScore", courseId);
    }
}
