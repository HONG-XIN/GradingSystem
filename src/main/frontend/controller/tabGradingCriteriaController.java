package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class tabGradingCriteriaController {

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
    protected void btSaveGroup(ActionEvent e) {

    }

    @FXML
    protected void btAddGroup(ActionEvent e) {

    }

    @FXML
    protected void btSaveCategory(ActionEvent e) {

    }

    @FXML
    protected void btAddCategory(ActionEvent e) {

    }

    @FXML
    protected void btSaveDetail(ActionEvent e) {

    }
}
