package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class coursesListController {

    @FXML
    protected void btNewCourse(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }
}
