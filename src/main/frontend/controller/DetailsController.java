package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DetailsController {

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {

            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("details"))
                    System.out.println("noa tela:"+newScreen+", "+userData);
            }
        });
    }

    @FXML
    protected void btCancelarAction(ActionEvent e) {
        Main.changeScreen("main");
    }
}
