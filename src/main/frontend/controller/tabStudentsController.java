package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class tabStudentsController {

    @FXML
    protected void btLeave(ActionEvent e) {
        Main.changeScreen("coursesList");
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
    protected void btNewStudent(ActionEvent e) {
        Main.changeScreen("studentInfo");
    }
}
