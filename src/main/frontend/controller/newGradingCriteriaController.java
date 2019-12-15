package main.frontend.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.backend.CategoryGroup;
import main.backend.Criteria;
import main.frontend.model.LabelWeight;

public class newGradingCriteriaController {

    private Criteria newC;

    @FXML
    Button btAddGroup, btDelGroup, btAddCategory, btDelCategory;

    @FXML
    TableView<LabelWeight> tvGroup, tvCategory;

    @FXML
    TableColumn<LabelWeight, String> tcLabel1, tcWeight1, tcLabel2, tcWeight2;

    @FXML
    TableColumn<LabelWeight, Button> tcDel1, tcDel2;

    @FXML
    protected void initialize() {
        initTable();
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("newGradingCriteria")) {
                    newC = Main.gs.getNewCriteriaTemplate();
//                    System.out.println(newScreen+", "+userData);
                    initTable();
                }
            }
        });
    }

    private void initTable() {
        initCols();
        loadData();
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
        tcLabel1.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLabel(e.getNewValue());
        });

        tcWeight1.setCellFactory(TextFieldTableCell.forTableColumn());
        tcWeight1.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setWeight(e.getNewValue());
        });

        tvGroup.setEditable(true);

        tcLabel2.setCellFactory(TextFieldTableCell.forTableColumn());
        tcLabel2.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setLabel(e.getNewValue());
        });

        tcWeight2.setCellFactory(TextFieldTableCell.forTableColumn());
        tcWeight2.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setWeight(e.getNewValue());
        });

        tvCategory.setEditable(true);
    }

    private void loadData() {
        ObservableList<LabelWeight> table_data1 = FXCollections.observableArrayList();

        String[][] groups = Main.gs.getGroupListByCriteria(newC);

        for (int i = 0; i < groups.length; i++) {
            String id = groups[i][0];
            String name = groups[i][1];
            CategoryGroup cg = Main.gs.getCategoryGroupByIdInCriteria(newC, id);
            Button delete = new Button("x");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Main.gs.deleteCategoryGroupInCriteria(newC, cg);
                }
            });
        }

        for (int i = 0; i < 3; i++) {
            table_data1.add(new LabelWeight(String.valueOf(i), "weight "+i, new Button("x")));
        }

        tvGroup.setItems(table_data1);
    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("addOneCourse");
    }

    @FXML
    protected void btSaveGroup(ActionEvent e) {

    }

    @FXML
    protected void btAddGroup(ActionEvent e) {
        String weight = "0";
        if (tvGroup.getItems().size() == 0) {
            weight = "100";
        }
        Main.gs.addGroupInCriteria(newC, "Default", Double.valueOf(weight));
        CategoryGroup newG = Main.gs.getCategoryGroupByIdInCriteria(newC, "sss");
        Button delete = new Button("x");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.gs.deleteCategoryGroupInCriteria(newC, null);
            }
        });
        tvGroup.getItems().add(new LabelWeight("Default", weight, delete));
        btAddGroup.setDisable(true);
    }

    @FXML
    protected void btdelGroup(ActionEvent e) {

    }

    @FXML
    protected void btSaveCategory(ActionEvent e) {

    }

    @FXML
    protected void btAddCategory(ActionEvent e) {
        tvCategory.getItems().add(new LabelWeight("Default", "0", new Button("x")));
        btAddCategory.setDisable(true);
    }

    @FXML
    protected void btdelCategory(ActionEvent e) {

    }

    @FXML
    protected void btSaveDetail(ActionEvent e) {

    }
}
