package main.frontend.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class coursesListController {

    @FXML
    ChoiceBox cbSemester;

    @FXML
    protected void initialize() {
        cbSemester.setItems(FXCollections.observableArrayList("1", "2"));
        cbSemester.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println(t1.intValue());
            }
        });
    }

    @FXML
    protected void btNewCourse(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }
}
