package main.frontend.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.backend.Criteria;
import main.backend.Semester;

import java.util.ArrayList;

public class addOneCourseController {

    private String[] sList;
    private String[] cList;

    @FXML
    TextField tfName;

    @FXML
    ChoiceBox cbSemester;

    @FXML
    ComboBox cbCriteria;

    @FXML
    Label info;

    @FXML
    protected void initialize() {

        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("addOneCourse")) {
//                    System.out.println(newScreen+", "+userData);
                    loadSemesterData();
                    loadGradingCriteriaData();
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
        sList = semesterIDs;
    }

    private void loadGradingCriteriaData() {
        String[][] gradingCriteriaData = Main.gs.getCriteriaTemplateList();
        if (gradingCriteriaData == null) {
            return;
        }
        String[] gcIDs = new String[gradingCriteriaData.length];
        String[] gcNames = new String[gradingCriteriaData.length];
        for (int i = 0; i < gradingCriteriaData.length; i++) {
            gcIDs[i] = gradingCriteriaData[i][0];
            gcNames[i] = gradingCriteriaData[i][1];
        }
        cbCriteria.setItems(FXCollections.observableArrayList(gcNames));
        cList = gcIDs;
    }

    @FXML
    protected void btNewSemester(ActionEvent e) {
        Main.changeScreen("newSemester");
    }

    @FXML
    protected void btNewGradingCriteria(ActionEvent e) {
        if (Main.gs.createCriteriaTemplate("New")) {
            Main.changeScreen("newGradingCriteria");
        } else {
            info.setText("New Grading Criteria has been created.");
        }
    }

    @FXML
    protected void btCreate(ActionEvent e) {
        int a = cbSemester.getSelectionModel().getSelectedIndex();
        int b = cbCriteria.getSelectionModel().getSelectedIndex();
        if (a < 0 || b < 0) {
            info.setText("Fail to Create");
            return;
        }
        String sID = sList[a];
        Semester s = Main.gs.getSemesterById(sID);
        String cID = cList[b];
        Criteria c = Main.gs.getCriteriaTemplateById(cID);
        String name = tfName.getText();
        try  {
            if (Main.gs.createCourseByTemplate(c, name, s)) {
                Main.changeScreen("coursesList");
            } else {
                info.setText("Fail to Create");
            }
        } catch (Exception ex) {
            info.setText("Fail to Create");
        }

//        Main.changeScreen("coursesList");
    }

    @FXML
    protected void btCancel(ActionEvent e) {
        Main.gs.deleteNewCriteriaTemplate();
        Main.changeScreen("coursesList");
    }
}
