package main.frontend.controller;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;

public class MainController {

    @FXML
    protected void btNovoAction(ActionEvent e) {
        System.out.println("bt Novo!");

        Main.changeScreen("details");
    }
}
