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
import main.backend.CategoryGrade;
import main.backend.Course;
import main.backend.CourseGrade;
import main.backend.Student;
import main.frontend.model.LabelWeight;
import main.frontend.model.UIFinalStatistics;
import main.frontend.model.UIStudentFinal;

public class tabFinalScoreController {

    private String courseId;
    private Course course;

    private Button prevComment;

    @FXML
    TableView<UIFinalStatistics> tableStatistics;

    @FXML
    TableColumn<UIFinalStatistics, String> colMin, colMax, colAvg, colStd;

    @FXML
    TableView<UIStudentFinal> tableFinal;

    @FXML
    TableColumn<UIStudentFinal, String> colSName, colBonus, colFinalScore, colLetter;

    @FXML
    TableColumn<UIStudentFinal, Button> colComment, colFreeze, colDelete;

    @FXML
    TextField tfCurve;

    @FXML
    Label info;

    @FXML
    ChoiceBox cbFilter;

    @FXML
    TextArea taComment;

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
                    cbFilter.getItems().clear();
                    cbFilter.getItems().addAll("all", "graduate", "undergraduate");
                    cbFilter.setValue("all");
                    loadStatistics();
                    cbFilter.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                            cbFilter.getSelectionModel().select(t1.intValue());
                            loadStatistics();
                        }
                    });


                    int c = course.getCurveValue();
                    tfCurve.setText(Integer.toString(c));

                }
            }
        });
    }

    private void initTable() {
        initCols();
        loadData();
    }

    private void initCols() {
        // min, max, avg, med, std;
        colMin.setCellValueFactory(new PropertyValueFactory<>("min"));
        colMax.setCellValueFactory(new PropertyValueFactory<>("max"));
        colAvg.setCellValueFactory(new PropertyValueFactory<>("avg"));
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
        colBonus.setCellFactory(TextFieldTableCell.forTableColumn());
        colBonus.setOnEditCommit(e->{
            try {
                String cid = e.getTableView().getItems().get(e.getTablePosition().getRow()).getId();
                CourseGrade cg = Main.gs.getCourseGradeById(cid);
                int bonus = Integer.parseInt(e.getNewValue());
                if (Main.gs.changeCourseGradeBonus(cg, bonus)) {
                    info.setText("Change Bonus Success");
                    loadData();
                } else {
                    info.setText("Change Bonus Fail");
                    loadData();
                }
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setBonus(e.getNewValue());
            } catch (Exception ex) {
                loadData();
                info.setText("Change Bonus Fail");
            }
        });

        colLetter.setCellFactory(TextFieldTableCell.forTableColumn());
        colLetter.setOnEditCommit(e->{
            String cid = e.getTableView().getItems().get(e.getTablePosition().getRow()).getId();
            CourseGrade cg = Main.gs.getCourseGradeById(cid);
            String letter = e.getNewValue();
            cg.setLetterGrade(letter);
            info.setText("Change LetterGrade Success");
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLetter(e.getNewValue());
        });

        tableFinal.setEditable(true);

    }

    private void loadStatistics() {
        String filter = cbFilter.getValue().toString();
        System.out.println("Choose to show statistics in :"+filter);
        String[][] statistics = Main.gs.getStatisticListInCourse(course);
        if (filter.equals("graduate")) {
            statistics = Main.gs.getGradStatisticListInCourse(course);
        } else if (filter.equals("undergraduate")) {
            statistics = Main.gs.getUnderGradStatisticListInCourse(course);
        }
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

            CourseGrade cg = Main.gs.getCourseGradeById(gradeId);
            String sid = cg.getStudentId();
            Student student = course.getStudentById(sid);

            Button comment = new Button("null");
            if (cg.hasComment()) {
                comment.setText("has comment");
            }
            comment.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (comment.getText().equals("editing")) {
                        String c = taComment.getText().trim();
                        cg.setComment(c);
                        if (c.length() == 0) {
                            comment.setText("null");
                        } else {
                            comment.setText("has comment");
                        }
                        prevComment = null;
                        taComment.setText("");
                        taComment.setDisable(true);
                    } else if (prevComment != null) {
                        info.setText("Please commit comment before editing another student.");
                        return;
                    } else {
                        taComment.setText(cg.getComment());
                        comment.setText("editing");
                        taComment.setDisable(false);
                        prevComment = comment;
                    }
                }
            });


            Button freeze = new Button("#");
            freeze.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (Main.gs.freeStudentByStudentId(course, sid)) {
                        info.setText("Freeze Student Success");
                    } else {
                        info.setText("Freeze Student Fail");
                    }
                    loadStatistics();
                    loadData();
                }
            });

            Button delete = new Button("x");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (Main.gs.deleteStudentByStudentId(course, sid)) {
                        info.setText("Delete Student Success");
                    } else {
                        info.setText("Delete Student Fail");
                    }
                    loadStatistics();
                    loadData();
                }
            });
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
        Main.changeScreen("letterGrade", courseId);
    }

    @FXML
    protected void btCalFinal(ActionEvent e) {

    }

    @FXML
    protected void btCurve(ActionEvent e) {
        try {
            int c = Integer.parseInt(tfCurve.getText());
            if (Main.gs.changeCourseCurve(course, c)) {
                info.setText("Set Curve Success");
            } else {
                info.setText("Set Curve Fail");
            }
            loadData();
            loadStatistics();
        } catch (Exception ex) {
            info.setText("Set Curve Fail");
            loadData();
            loadStatistics();
        }
    }

    @FXML
    protected void btExport(ActionEvent e) {
        Main.changeScreen("exportFinalScore", courseId);
    }
}
