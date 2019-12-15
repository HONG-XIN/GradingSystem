package main.frontend.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import main.backend.Course;
import main.frontend.model.LabelWeight;
import main.frontend.model.UIFinalStatistics;
import main.frontend.model.UIStudentFinal;

public class tabFinalScoreController {

    private String courseId;
    private Course course;

    @FXML
    TableView<UIFinalStatistics> tableStatistics;

    @FXML
    TableColumn<UIFinalStatistics, String> colMin, colMax, colAvg, colMed, colStd;

    @FXML
    TableView<UIStudentFinal> tableFinal;

    @FXML
    TableColumn<UIStudentFinal, String> colSName, colBonus, colFinalScore, colLetter;

    @FXML
    TableColumn<UIStudentFinal, Button> colComment, colFreeze, colDelete;

    @FXML
    TextField tfCurve;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("tabFinalScore")) {
                    courseId = userData.toString();
                    course = Main.gs.getCourseById(courseId);
//                    loadGroupData();
                    initTable();
//                    loadDate();
                    loadStatistics();
                }
            }
        });
    }

    private void initTable() {
        initCols();
    }

    private void initCols() {
        // min, max, avg, med, std;
        colMin.setCellValueFactory(new PropertyValueFactory<>("min"));
        colMax.setCellValueFactory(new PropertyValueFactory<>("max"));
        colAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
        colMed.setCellValueFactory(new PropertyValueFactory<>("med"));
        colStd.setCellValueFactory(new PropertyValueFactory<>("std"));
        // id, name, bonus, score, letter;
        // comment, freeze, delete
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        colFinalScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        colLetter.setCellValueFactory(new PropertyValueFactory<>("letter"));
        colComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        colFreeze.setCellValueFactory(new PropertyValueFactory<>("freeze"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        editableCols();
    }

    private void editableCols() {

    }

    private void loadStatistics() {
        String[][] statistics = Main.gs.getStatisticListInCourse(course);
        String min = statistics[0][1];
        String max = statistics[0][2];
        String avg = statistics[0][0];
        String med = "0";
        String std = statistics[0][3];

        ObservableList<UIFinalStatistics> table_data = FXCollections.observableArrayList();
        table_data.add(new UIFinalStatistics(min, max, avg, med, std));
        tableStatistics.setItems(table_data);
    }

    private void loadData() {
        ObservableList<UIStudentFinal> table_data = FXCollections.observableArrayList();
        String[][] data = Main.gs.getFinalGradeListByCourse(course);
        if (data == null) {
            tableFinal.setItems(table_data);
            return;
        }
        for (int i = 0; i < data.length; i++) {
            String gradeId = data[i][0];
            String name = data[i][1];
            String buID = data[i][2];
            String email = data[i][3];
            String bonus = data[i][4];
            String finalScore = data[i][5];
            String letter = data[i][6];
            Button comment = new Button("-");
            Button freeze = new Button("#");
            Button delete = new Button("x");
            table_data.add(new UIStudentFinal(gradeId, name, bonus, finalScore, letter, comment, freeze, delete));
        }

        tableFinal.setItems(table_data);

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
    protected void btCalLetter(ActionEvent e) {

    }

    @FXML
    protected void btCalFinal(ActionEvent e) {

    }

    @FXML
    protected void btCurve(ActionEvent e) {
        Main.popup.setScene(Main.curveScene);
        Main.popup.show();
    }

    @FXML
    protected void btExport(ActionEvent e) {
        Main.changeScreen("exportFinalScore");
    }
}
