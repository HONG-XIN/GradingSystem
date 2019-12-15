package main.frontend.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML

class tabGradingCriteriaController {

    @FXML
    protected fun btLeave(e: ActionEvent) {
        Main.changeScreen("coursesList")
    }

    @FXML
    protected fun btStudents(e: ActionEvent) {
        Main.changeScreen("tabStudents")
    }

    @FXML
    protected fun btFinalScore(e: ActionEvent) {
        Main.changeScreen("tabFinalScore")
    }

    @FXML
    protected fun btGradingCriteria(e: ActionEvent) {
        Main.changeScreen("tabGradingCriteria")
    }

    @FXML
    protected fun btSaveGroup(e: ActionEvent) {

    }

    @FXML
    protected fun btAddGroup(e: ActionEvent) {

    }

    @FXML
    protected fun btSaveCategory(e: ActionEvent) {

    }

    @FXML
    protected fun btAddCategory(e: ActionEvent) {

    }

    @FXML
    protected fun btSaveDetail(e: ActionEvent) {

    }
}
