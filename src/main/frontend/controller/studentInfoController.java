package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class studentInfoController {

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

    }

    @FXML
    protected void btBack(ActionEvent e) {

    }
}
