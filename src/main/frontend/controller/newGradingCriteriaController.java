package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class newGradingCriteriaController {

    @FXML
    Button btAddGroup, btDelGroup, btAddCategory, btDelCategory;

    @FXML
    TableView tvGroup;

    @FXML
    protected void initialize() {
//        btAddGroup.setDisable(true);
        TableColumn<Item, String> group1 = new TableColumn<>("Label");
        group1.setCellValueFactory(new PropertyValueFactory("label"));
        group1.setCellFactory(TextFieldTableCell.forTableColumn());
        TableColumn<Item, String> group2 = new TableColumn<>("Weight");
        group2.setCellValueFactory(new PropertyValueFactory("weight"));
        group2.setCellFactory(TextFieldTableCell.forTableColumn());
        tvGroup.getColumns().addAll(group1, group2);
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
        tvGroup.getItems().add(new Item("123", "100"));
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

    public static class Item {
        private String label;
        private String weight;

        public Item(String label, String weight) {
            this.label = label;
            this.weight = weight;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
