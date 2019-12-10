package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DetailsController {

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("details")) {
                    System.out.println(newScreen+", "+userData);
                }
            }
        });
    }

    @FXML
    protected void btCancelAction(ActionEvent e) {
        Main.changeScreen("main");
    }
}
