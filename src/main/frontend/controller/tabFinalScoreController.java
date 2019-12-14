package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class tabFinalScoreController {

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
