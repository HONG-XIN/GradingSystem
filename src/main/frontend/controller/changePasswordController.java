package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class changePasswordController {

    @FXML
    protected void btSave(ActionEvent e) {

    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("login");
    }
}
