package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.backend.Course;

public class tabFinalScoreController {

    private String courseId;
    private Course course;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("tabFinalScore")) {
                    courseId = userData.toString();
                    course = Main.gs.getCourseById(courseId);
//                    loadGroupData();
                    initTable();
//                    loadDate();
                }
            }
        });
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        editableCols();
    }

    private void editableCols() {

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
    protected void btCalLetter(ActionEvent e) {

    }

    @FXML
    protected void btCalFinal(ActionEvent e) {

    }

    @FXML
    protected void btCurve(ActionEvent e) {
        Main.popup.setScene(Main.curveScene);
        Main.popup.show();
    }

    @FXML
    protected void btExport(ActionEvent e) {
        Main.changeScreen("exportFinalScore");
    }
}
