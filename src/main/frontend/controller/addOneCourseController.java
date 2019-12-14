package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class addOneCourseController {

    @FXML
    protected void btNewSemester(ActionEvent e) {
        Main.changeScreen("newSemester");
    }

    @FXML
    protected void btNewGradingCriteria(ActionEvent e) {
        Main.changeScreen("newGradingCriteria");
    }

    @FXML
    protected void btCreate(ActionEvent e) {
        Main.changeScreen("coursesList");
    }

    @FXML
    protected void btCancel(ActionEvent e) {
        Main.changeScreen("coursesList");
    }
}
