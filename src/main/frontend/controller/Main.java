package main.frontend.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    private static Scene mainScene;
    private static Scene detailsScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        stage.setTitle("ExampleLOO");

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        mainScene = new Scene(fxmlMain, 640, 400);

        Parent fxmlDetails = FXMLLoader.load(getClass().getResource("../view/details_screen.fxml"));
        detailsScene = new Scene(fxmlDetails, 640, 400);

        stage.setScene(mainScene);
        stage.show();
    }

    public static void changeScreen(String scr){
        switch (scr){
            case "main":
                stage.setScene(mainScene);
                break;
            case "details":
                stage.setScene(detailsScene);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
