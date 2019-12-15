package main.frontend.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.backend.Semester;
import main.frontend.model.Course;
import main.frontend.model.LabelWeight;

import java.util.ArrayList;

public class coursesListController {

    private String[] sList;

    @FXML
    Label info;

    @FXML
    ChoiceBox cbSemester;

    @FXML
    TableView<Course> table;

    @FXML
    TableColumn<Course, String> colSemester, colStart, colEnd;

    @FXML
    TableColumn<Course, Button> colCourseName, colAction;

    @FXML
    protected void initialize() {

        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("coursesList")) {
                    loadSemesterData();
                    initTable();
                    loadCoursesData(-1);
                }
            }
        });


    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
        editableCols();
    }

    private void editableCols() {

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

    private void loadDummyData() {
        ObservableList<Course> table_data = FXCollections.observableArrayList();
        table_data.add(new Course("1", new Button("hi"), "fall", "20", "25", new Button("x")));
        table.setItems(table_data);
    }

    private void loadCoursesData(int i) {
        ObservableList<Course> table_data = FXCollections.observableArrayList();

        String[][] data = null;

        if (i < 0) {
            data = Main.gs.getCourseList();
        } else {
//            table.setItems(table_data);
//            return;
            String sID = sList[i];
            System.out.println("sID :"+sID);
            Semester s = Main.gs.getSemesterById(sID);
            data = Main.gs.getCourseListBySemester(s);
        }

        if (data == null) {
            table.setItems(table_data);
            return;
        }

        for (int j = 0; j < data.length; j++) {
            String id = data[j][0];
            String name = data[j][1];
            String semester = data[j][2];
            String start = data[j][3];
            String end = data[j][4];
            Button course = new Button(name);
            course.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Main.changeScreen("tabStudents", id);
                }
            });

            Button delete = new Button("DELETE");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (Main.gs.deleteCourseByCourseId(id)) {
                        info.setText("Delete course Success");
                        loadCoursesData(i);
                    } else {
                        info.setText("Delete course Fail");
                    }
                }
            });

            table_data.add(new Course(id, course, semester, start, end, delete));

        }

        table.setItems(table_data);
    }

    @FXML
    protected void btNewCourse(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }
}
