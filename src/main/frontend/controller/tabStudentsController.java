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
import javafx.scene.control.cell.TextFieldTableCell;
import main.backend.*;
import main.frontend.model.LabelWeight;
import main.frontend.model.UIStudent;
import main.frontend.model.UIStudentInfo;

public class tabStudentsController {

    private String courseId;
    private Course course;

    private String[] gIDs;
    private String groupId;
    private String categoryId;

    @FXML
    private TableColumn<UIStudent, Button> colName;

    @FXML
    private ChoiceBox cBox, cbCategory;

    @FXML
    private TableColumn<UIStudent, String> colBUid, colCategory, colEmail;

    @FXML
    private TableView<UIStudent> table;

    @FXML
    Label info;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("tabStudents")) {
                    courseId = userData.toString();
                    course = Main.gs.getCourseById(courseId);
                    loadGroupData();
                    initTable();
                    loadDate();
                }
            }
        });
    }

    private void loadGroupData() {
        Criteria criteria = Main.gs.getCriteriaInCourse(course);
        String[][] groups = Main.gs.getGroupListByCriteria(criteria);

        if (groups == null) {
            return;
        }

        String[] groupIDs = new String[groups.length];
        String[] groupNames = new String[groups.length];
        for (int i = 0; i < groups.length; i++) {
            groupIDs[i] = groups[i][0];
            groupNames[i] = groups[i][1];
        }
        cBox.setItems(FXCollections.observableArrayList(groupNames));
        cBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                categoryId = null;
                int index = t1.intValue();
                if (index < 0) {
                    groupId = null;
                } else {
                    groupId = groupIDs[index];
                }
                loadCategoryData(index);
            }
        });
        gIDs = groupIDs;
    }

    private void loadCategoryData(int index) {
        if (index < 0) {
            cbCategory.setItems(FXCollections.observableArrayList());
            return;
        }
        Criteria criteria = Main.gs.getCriteriaInCourse(course);
        String id = gIDs[index];
        CategoryGroup cg = Main.gs.getCategoryGroupByIdInCriteria(criteria, id);
        String[][] categories = Main.gs.getCategoryListByGroup(cg);
        if (categories == null) {
            return;
        }
        String[] ids = new String[categories.length];
        String[] names = new String[categories.length];
        for (int i = 0; i < categories.length; i++) {
            ids[i] = categories[i][0];
            names[i] = categories[i][1];
        }
        cbCategory.setItems(FXCollections.observableArrayList(names));
        cbCategory.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                int a = t1.intValue();
                if (a < 0) {
                    categoryId = null;
                } else {
                    categoryId = ids[a];
                    loadDate();
                }
            }
        });
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBUid.setCellValueFactory(new PropertyValueFactory<>("buID"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("score"));
        editableCols();
    }

    private void editableCols() {
        colCategory.setCellFactory(TextFieldTableCell.forTableColumn());
        colCategory.setOnEditCommit(e->{
            try {
                double score = Double.parseDouble(e.getNewValue());
                String id = e.getTableView().getItems().get(e.getTablePosition().getRow()).getId();
                CategoryGrade grade = Main.gs.getCategoryGradeById(id);
                grade.setScore(score);
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setScore(e.getNewValue());
                info.setText("Success");
            } catch (Exception ex) {
                info.setText("Fail");
                loadDate();
            }
        });
        table.setEditable(true);
    }

    private void loadDate() {
        ObservableList<UIStudent> table_data = FXCollections.observableArrayList();

        if (categoryId == null) {
            table.setItems(table_data);
            return;
        }
        Criteria criteria = Main.gs.getCriteriaInCourse(course);
        CategoryGroup cg = Main.gs.getCategoryGroupByIdInCriteria(criteria, groupId);
        Category cat = Main.gs.getCategoryByIdInCategoryGroup(cg, categoryId);
        String[][] data = Main.gs.getGradeListInCourseByCategory(course, cat);
        if (data == null) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            String id = data[i][0];
            String name = data[i][1];
            String buId = data[i][2];
            String email = data[i][3];
            String score = data[i][4];
            Button student = new Button(name);
            student.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    CategoryGrade grade = Main.gs.getCategoryGradeById(id);
                    Student s = course.getStudentById(grade.getStudentId());
                    Main.changeScreen("studentInfo", new UIStudentInfo(courseId, s));

                }
            });
            table_data.add(new UIStudent(id, buId, email, score, student));
        }
        table.setItems(table_data);

    }

    @FXML
    protected void btLeave(ActionEvent e) {
        Main.changeScreen("coursesList");
    }

    @FXML
    protected void btStudents(ActionEvent e) {
        Main.changeScreen("tabStudents", courseId);
    }

    @FXML
    protected void btFinalScore(ActionEvent e) {
        Main.changeScreen("tabFinalScore", courseId);
    }

    @FXML
    protected void btGradingCriteria(ActionEvent e) {
        Main.changeScreen("tabGradingCriteria", courseId);
    }

    @FXML
    protected void btNewStudent(ActionEvent e) {
        Main.changeScreen("studentInfo", new UIStudentInfo(courseId, null));
    }
}
