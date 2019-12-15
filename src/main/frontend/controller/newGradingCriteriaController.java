package main.frontend.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.backend.Category;
import main.backend.CategoryGroup;
import main.backend.Criteria;
import main.frontend.model.LabelWeight;

import java.time.LocalDate;

public class newGradingCriteriaController {

    private Criteria newC;
    private CategoryGroup cGroup;
    private Category Cat;

    @FXML
    Button btAddGroup, btDelGroup, btAddCategory, btDelCategory, btSaveGroup, btSaveCategory;

    @FXML
    TableView<LabelWeight> tvGroup, tvCategory;

    @FXML
    TableColumn<LabelWeight, String> tcLabel1, tcWeight1, tcLabel2, tcWeight2;

    @FXML
    TableColumn<LabelWeight, Button> tcDel1, tcDel2;

    @FXML
    Label info;

    @FXML
    DatePicker dpIssue, dpDue;

    @FXML
    TextField tfScore;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("newGradingCriteria")) {
                    newC = Main.gs.getNewCriteriaTemplate();
//                    System.out.println(newScreen+", "+userData);
                    initTable();
                    btSaveCategory.setDisable(true);
                    btAddCategory.setDisable(true);
                }
            }
        });
    }

    private void initTable() {
        initCols();
        loadData1();
    }

    private void initCols() {
        tcLabel1.setCellValueFactory(new PropertyValueFactory<>("label"));
        tcWeight1.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tcDel1.setCellValueFactory(new PropertyValueFactory<>("delete"));
        tcLabel2.setCellValueFactory(new PropertyValueFactory<>("label"));
        tcWeight2.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tcDel2.setCellValueFactory(new PropertyValueFactory<>("delete"));
        editableCols();
    }

    private void editableCols() {
        tcLabel1.setCellFactory(TextFieldTableCell.forTableColumn());
        tcLabel1.setOnEditStart(e->{
            String id = e.getTableView().getItems().get(e.getTablePosition().getRow()).getId();
            cGroup = Main.gs.getCategoryGroupByIdInCriteria(newC, id);
            loadData2();
            btAddCategory.setDisable(false);
        });
        tcLabel1.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLabel(e.getNewValue());
        });

        tcWeight1.setCellFactory(TextFieldTableCell.forTableColumn());
        tcWeight1.setOnEditStart(e->{
            String id = e.getTableView().getItems().get(e.getTablePosition().getRow()).getId();
            cGroup = Main.gs.getCategoryGroupByIdInCriteria(newC, id);
            loadData2();
            btAddCategory.setDisable(false);
        });
        tcWeight1.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setWeight(e.getNewValue());
        });

        tvGroup.setEditable(true);

        tcLabel2.setCellFactory(TextFieldTableCell.forTableColumn());
        tcLabel2.setOnEditStart(e->{
            String id = e.getTableView().getItems().get(e.getTablePosition().getRow()).getId();
            Cat = Main.gs.getCategoryByIdInCategoryGroup(cGroup, id);
            loadData3();
        });
        tcLabel2.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLabel(e.getNewValue());
        });

        tcWeight2.setCellFactory(TextFieldTableCell.forTableColumn());
        tcLabel2.setOnEditStart(e->{
            String id = e.getTableView().getItems().get(e.getTablePosition().getRow()).getId();
            Cat = Main.gs.getCategoryByIdInCategoryGroup(cGroup, id);
            loadData3();
        });
        tcWeight2.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setWeight(e.getNewValue());
        });

        tvCategory.setEditable(true);
    }

    private void loadData1() {
        ObservableList<LabelWeight> table_data1 = FXCollections.observableArrayList();

        String[][] groups = Main.gs.getGroupListByCriteria(newC);

        if (groups == null) {
            tvGroup.setItems(table_data1);
            return;
        }

        for (int i = 0; i < groups.length; i++) {
            String id = groups[i][0];
            String name = groups[i][1];
            String weight = groups[i][2];
            CategoryGroup cg = Main.gs.getCategoryGroupByIdInCriteria(newC, id);
            Button delete = new Button("x");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println("try to delete " + name);
                    if (Main.gs.deleteCategoryGroupInCriteria(newC, cg)) {
                        loadData1();
                        cGroup = null;
                        loadData2();
                        info.setText("Delete Success");
                    } else {
                        info.setText("Delete Fail");
                    }
                }
            });
            table_data1.add(new LabelWeight(id, name, weight, delete));
        }
        tvGroup.setItems(table_data1);
    }

    private void loadData2() {
        ObservableList<LabelWeight> table_data2 = FXCollections.observableArrayList();

        if (cGroup == null) {
            tvCategory.setItems(table_data2);
            btSaveCategory.setDisable(true);
            btAddCategory.setDisable(true);
            return;
        } else {
            btSaveCategory.setDisable(false);
        }

        String[][] categories = Main.gs.getCategoryListByGroup(cGroup);

        if (categories == null) {
            tvCategory.setItems(table_data2);
            return;
        }

        for (int i = 0; i < categories.length; i++) {
            String id = categories[i][0];
            String name = categories[i][1];
            String weight = categories[i][2];
            Category cat = Main.gs.getCategoryByIdInCategoryGroup(cGroup, id);
            Button delete = new Button("x");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    if (Main.gs.deleteCategoryInGroup(cGroup, cat)) {
                        loadData2();
                        info.setText("Delete Category Success");
                    } else {
                        info.setText("Delete Category Fail");
                    }
                }
            });
            table_data2.add(new LabelWeight(id, name, weight, delete));
        }

        tvCategory.setItems(table_data2);
    }

    private void loadData3() {
        if (Cat == null) {
            dpIssue.setValue(null);
            dpDue.setValue(null);
            tfScore.setText("");
            return;
        }


        double score = Cat.getTotalScore();

        dpIssue.setValue(LocalDate.of(2000,1,1));
        dpDue.setValue(null);
        tfScore.setText(Double.toString(score));
    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }

    @FXML
    protected void btSaveGroup(ActionEvent e) {
        double sum = 0;
        for (LabelWeight item: tvGroup.getItems()) {
            double weight = Double.valueOf(item.getWeight());
            if (weight < 0) {
                info.setText("Fail");
                loadData1();
                return;
            }
            sum += weight;
        }
        if (sum == 100) {
            for (LabelWeight item: tvGroup.getItems()) {
                String name = item.getLabel();
                String id = item.getId();
                double weight = Double.valueOf(item.getWeight());
                CategoryGroup cg = Main.gs.getCategoryGroupByIdInCriteria(newC, id);
                Main.gs.changeCategoryGroupWeight(cg, weight);
                Main.gs.changeCategoryGroupName(cg, name);
            }
            info.setText("Success");
            btAddGroup.setDisable(false);
        } else {
            loadData1();
            info.setText("Fail");
        }
    }

    @FXML
    protected void btAddGroup(ActionEvent e) {
        String weight = "0";
        if (tvGroup.getItems().size() == 0) {
            weight = "100";
        }
        Main.gs.addGroupInCriteria(newC, "Default", Double.valueOf(weight));
        loadData1();
        btAddGroup.setDisable(true);
    }

    @FXML
    protected void btdelGroup(ActionEvent e) {

    }

    @FXML
    protected void btSaveCategory(ActionEvent e) {
        double sum = 0;
        for (LabelWeight item: tvCategory.getItems()) {
            double weight = Double.valueOf(item.getWeight());
            if (weight < 0) {
                info.setText("Save Category Fail");
                loadData1();
                return;
            }
            sum += weight;
        }
        if (sum == 100) {
            for (LabelWeight item: tvCategory.getItems()) {
                String name = item.getLabel();
                String id = item.getId();
                double weight = Double.parseDouble(item.getWeight());
                Category cat = Main.gs.getCategoryByIdInCategoryGroup(cGroup, id);
                Main.gs.changeCategoryName(cat, name);
                Main.gs.changeCategoryWeight(cat, weight);
            }
            info.setText("Save Category Success");
            btAddCategory.setDisable(false);
        } else {
            loadData2();
            info.setText("Save Category Fail");
        }
    }

    @FXML
    protected void btAddCategory(ActionEvent e) {
        String weight = "0";
        if (tvCategory.getItems().size() == 0) {
            weight = "100";
        }
        Main.gs.addCategoryInGroup(cGroup, "Default", 100, Double.valueOf(weight),1,1,2000,2,1,2000);
        loadData2();
        btAddCategory.setDisable(true);
    }

    @FXML
    protected void btdelCategory(ActionEvent e) {

    }

    @FXML
    protected void btSaveDetail(ActionEvent e) {

    }
}
