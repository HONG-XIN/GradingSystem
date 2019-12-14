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
        String[][] semesterList = Main.gs.getSemesterList();
        if (semesterList == null) {
            return;
        }
        String[] semesterIDs = new String[semesterList.length];
        String[] semesterNames = new String[semesterList.length];
        for (int i=0; i<semesterList.length; i++) {
            semesterIDs[i] = semesterList[i][0];
            semesterNames[i] = semesterList[i][1];
        }
        cbSemester.setItems(FXCollections.observableArrayList(semesterNames));
        cbSemester.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String semesterId = semesterIDs[t1.intValue()];
                System.out.println(semesterId);
            }
        });
    }

    @FXML
    protected void btNewCourse(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }
}
