package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class loginController {

    @FXML
    TextField tfPassword;

    @FXML
    protected void btChangePassword(ActionEvent e) {
        Main.changeScreen("changePassword");
    }

    @FXML
    protected void btLogin(ActionEvent e) {
        if (Main.gs.isPasswordValid(tfPassword.getText())) {
            Main.changeScreen("coursesList");
        } else {
            tfPassword.setText("Password Incorrect");
        }
    }

    @FXML
    protected void btExit(ActionEvent e) {
        Main.closeStage();
    }
}
