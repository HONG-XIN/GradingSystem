package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javax.swing.*;

public class exportFinalScoreController {

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
    protected void btExport(ActionEvent e) {

    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("tabFinalScore");
    }
}
