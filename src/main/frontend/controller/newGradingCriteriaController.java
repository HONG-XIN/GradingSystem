package main.frontend.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
//                    newC = Main.gs.;
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
    }

    private void loadData() {
        ObservableList<LabelWeight> table_data1 = FXCollections.observableArrayList();

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
        System.out.println("add group");
        tvGroup.getItems().add(new LabelWeight("Default", "100", new Button("x")));
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

    }

    @FXML
    protected void btdelCategory(ActionEvent e) {

    }

    @FXML
    protected void btSaveDetail(ActionEvent e) {

    }
}
