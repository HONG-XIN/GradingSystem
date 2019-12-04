package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    protected void btNovoAction(ActionEvent e) {
        Main.changeScreen("details", "dedos para a tela detalhes");
    }
}
