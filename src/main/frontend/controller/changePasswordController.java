package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class changePasswordController {

    @FXML
    TextField tfOld, tfNew;
    @FXML
    Label info;

    @FXML
    protected void btSave(ActionEvent e) {
        if (Main.gs.changePassword(tfOld.getText(), tfNew.getText())) {
            info.setText("Success");
        } else {
            info.setText("Fail");
        }
    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("login");
    }
}
