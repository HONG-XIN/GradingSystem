package main.frontend.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


        primaryStage.setTitle("ExampleLOO");

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));


        primaryStage.setScene(new Scene(fxmlMain, 640, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}
