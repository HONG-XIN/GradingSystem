package main.frontend.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class addOneCourseController {

    @FXML
    ChoiceBox cbSemester;

    @FXML
    protected void initialize() {

        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("addOneCourse")) {
//                    System.out.println(newScreen+", "+userData);
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
        for (int i = 0; i < semesterNames.length; i++) {
            System.out.println(semesterNames[i]);
        }
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
    protected void btNewSemester(ActionEvent e) {
        Main.changeScreen("newSemester");
    }

    @FXML
    protected void btNewGradingCriteria(ActionEvent e) {
        Main.changeScreen("newGradingCriteria");
    }

    @FXML
    protected void btCreate(ActionEvent e) {
        Main.changeScreen("coursesList");
    }

    @FXML
    protected void btCancel(ActionEvent e) {
        Main.changeScreen("coursesList");
    }
}
