package main.frontend.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import main.backend.Semester;

import java.util.ArrayList;

public class coursesListController {

    private String[] sList;

    @FXML
    ChoiceBox cbSemester;

    @FXML
    protected void initialize() {

        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("coursesList")) {
                    loadSemesterData();
                }
            }
        });


    }

    private void loadSemesterData() {
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
                int index = t1.intValue();
                loadCoursesData(index);
            }
        });
        sList = semesterIDs;
    }

    private void loadCoursesData(int i) {
        if (i < 0) {
            return;
        }
        String sID = sList[i];
        Semester s = Main.gs.getSemesterById(sID);
        String[][] data = Main.gs.getCourseListBySemester(s);

        if (data == null) {
            return;
        }

        for (int j = 0; j < data.length; j++) {

        }
    }

    @FXML
    protected void btNewCourse(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }
}
