package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class loginController {

    @FXML
    protected void btChangePassword(ActionEvent e) {
        Main.changeScreen("changePassword");
    }

    @FXML
    protected void btLogin(ActionEvent e) {
        // check password
        // if true
//        Main.changeScreen();
        //
        Main.changeScreen("coursesList");
    }

    @FXML
    protected void btExit(ActionEvent e) {
        Main.closeStage();
    }
}
