package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class newSemesterController {

    @FXML
    TextField tfName;

    @FXML
    DatePicker dpStart, dpEnd;

    @FXML
    Label info;

    @FXML
    protected void btSave(ActionEvent e) {
        List<String> startItems = Arrays.asList(dpStart.getEditor().getText().split("/"));

        List<String> endItems = Arrays.asList(dpEnd.getEditor().getText().split("/"));
        if (startItems.size() < 3 || endItems.size() < 3) {
            info.setText("Fail");
            return;
        }
        LocalDate startD = dpStart.getValue();
        LocalDate endD = dpEnd.getValue();
        int m1 = startD.getMonthValue();
        int d1 = startD.getDayOfMonth();
        int y1 = startD.getYear();
//        int m1 = Integer.parseInt(startItems.get(0));
//        int d1 = Integer.parseInt(startItems.get(1));
//        int y1 = Integer.parseInt(startItems.get(2));
        int m2 = endD.getMonthValue();
        int d2 = endD.getDayOfMonth();
        int y2 = endD.getYear();
//        int m2 = Integer.parseInt(endItems.get(0));
//        int d2 = Integer.parseInt(endItems.get(1));
//        int y2 = Integer.parseInt(endItems.get(2));
        if (Main.gs.createSemester(tfName.getText(), d1, m1, y1, d2, m2, y2)) {
            Main.changeScreen("addOneCourse");
        } else {
            info.setText("Fail");
        }
    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }
}
