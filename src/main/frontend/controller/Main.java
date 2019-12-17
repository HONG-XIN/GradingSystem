package main.frontend.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import main.backend.GradingSystem;
import main.database.GradingSystemDatabase;
import main.debug.Debug;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    protected static GradingSystem gs;

    private static Stage stage;
    protected static Stage popup;

    private static Scene mainScene;
    private static Scene detailsScene;

    private static Scene loginScene;
    private static Scene coursesListScene;
    private static Scene addOneCourseScene;
    private static Scene exportFinalScoreScene;
    private static Scene newGradingCriteriaScene;
    private static Scene newSemesterScene;
    private static Scene studentInfoScene;
    private static Scene tabFinalScoreScene;
    private static Scene tabGradingCriteriaScene;
    private static Scene tabStudentsScene;
    private static Scene changePasswordScene;
    private static Scene letterGradeScene;

    protected static Scene curveScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        popup = new Stage();

        primaryStage.setTitle("Grading System");

        int width = 1024;
        int height = 768;

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("../view/main_screen.fxml"));
        mainScene = new Scene(fxmlMain, 640, 400);

        Parent fxmlDetails = FXMLLoader.load(getClass().getResource("../view/details_screen.fxml"));
        detailsScene = new Scene(fxmlDetails, 640, 400);

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../view/login_screen.fxml"));
        loginScene = new Scene(fxmlLogin, width, height);

        Parent fxmlCoursesList = FXMLLoader.load(getClass().getResource("../view/coursesList_screen.fxml"));
        coursesListScene = new Scene(fxmlCoursesList, width, height);

        Parent fxmlAddOneCourse = FXMLLoader.load(getClass().getResource("../view/addOneCourse_screen.fxml"));
        addOneCourseScene = new Scene(fxmlAddOneCourse, width, height);

        Parent fxmlExportFinalScore = FXMLLoader.load(getClass().getResource("../view/exportFinalScore_screen.fxml"));
        exportFinalScoreScene = new Scene(fxmlExportFinalScore, width, height);

        Parent fxmlNewGradingCriteria = FXMLLoader.load(getClass().getResource("../view/newGradingCriteria_screen.fxml"));
        newGradingCriteriaScene = new Scene(fxmlNewGradingCriteria, width, height);

        Parent fxmlNewSemester = FXMLLoader.load(getClass().getResource("../view/newSemester_screen.fxml"));
        newSemesterScene = new Scene(fxmlNewSemester, width, height);

        Parent fxmlStudentInfo = FXMLLoader.load(getClass().getResource("../view/studentInfo_screen.fxml"));
        studentInfoScene = new Scene(fxmlStudentInfo, width, height);

        Parent fxmlTabFinalScore = FXMLLoader.load(getClass().getResource("../view/tabFinalScore_screen.fxml"));
        tabFinalScoreScene = new Scene(fxmlTabFinalScore, width, height);

        Parent fxmlTabGradingCriteria = FXMLLoader.load(getClass().getResource("../view/tabGradingCriteria_screen.fxml"));
        tabGradingCriteriaScene = new Scene(fxmlTabGradingCriteria, width, height);

        Parent fxmlTabStudents = FXMLLoader.load(getClass().getResource("../view/tabStudents_screen.fxml"));
        tabStudentsScene = new Scene(fxmlTabStudents, width, height);

        Parent fxmlChangePassword = FXMLLoader.load(getClass().getResource("../view/changePassword_screen.fxml"));
        changePasswordScene = new Scene(fxmlChangePassword, width, height);

        Parent fxmlCurve = FXMLLoader.load(getClass().getResource("../view/curve_screen.fxml"));
        curveScene = new Scene(fxmlCurve, 320, 200);

        Parent fxmlLetterGrade = FXMLLoader.load(getClass().getResource("../view/letterGrade_screen.fxml"));
        letterGradeScene = new Scene(fxmlLetterGrade, width, height);

        primaryStage.setScene(loginScene);
//        primaryStage.setScene(coursesListScene);
        primaryStage.show();
    }

    public static void changeScreen(String scr, Object userData) {
        switch (scr) {
            case "main":
                stage.setScene(mainScene);
                notifyAllListeners("main", userData);
                break;
            case "details":
                stage.setScene(detailsScene);
                notifyAllListeners("details", userData);
                break;
            case "login":
                stage.setScene(loginScene);
                notifyAllListeners("login", userData);
                break;
            case "coursesList":
                stage.setScene(coursesListScene);
                notifyAllListeners("coursesList", userData);
                break;
            case "addOneCourse":
                stage.setScene(addOneCourseScene);
                notifyAllListeners("addOneCourse", userData);
                break;
            case "exportFinalScore":
                stage.setScene(exportFinalScoreScene);
                notifyAllListeners("exportFinalScore", userData);
                break;
            case "newGradingCriteria":
                stage.setScene(newGradingCriteriaScene);
                notifyAllListeners("newGradingCriteria", userData);
                break;
            case "newSemester":
                stage.setScene(newSemesterScene);
                notifyAllListeners("newSemester", userData);
                break;
            case "studentInfo":
                stage.setScene(studentInfoScene);
                notifyAllListeners("studentInfo", userData);
                break;
            case "tabFinalScore":
                stage.setScene(tabFinalScoreScene);
                notifyAllListeners("tabFinalScore", userData);
                break;
            case "tabGradingCriteria":
                stage.setScene(tabGradingCriteriaScene);
                notifyAllListeners("tabGradingCriteria", userData);
                break;
            case "tabStudents":
                stage.setScene(tabStudentsScene);
                notifyAllListeners("tabStudents", userData);
                break;
            case "changePassword":
                stage.setScene(changePasswordScene);
                notifyAllListeners("changePassword", userData);
                break;
            case "letterGrade":
                stage.setScene(letterGradeScene);
                notifyAllListeners("letterGrade", userData);
                break;
        }
    }

    public static void changeScreen(String scr) {
        changeScreen(scr, null);
    }

    public static void closeStage() {
        stage.close();
    }

    public static void main(String[] args) {
        Nitrite db = Nitrite.builder()
                .filePath("./GradingSystem.db")
                .openOrCreate();
        NitriteCollection GradingSystemCollection = db.getCollection("GradingSystem");
        gs = new GradingSystem();
        GradingSystemDatabase.read(gs, GradingSystemCollection);
        db.close();
        File file = new File("./GradingSystem.db");
        if (file.exists()) {
            file.delete();
        }
        launch(args);
        Nitrite db2 = Nitrite.builder()
                .filePath("./GradingSystem.db")
                .openOrCreate();
        NitriteCollection GradingSystemCollection2 = db2.getCollection("GradingSystem");
        GradingSystemCollection2.insert(GradingSystemDatabase.write(gs));
    }


    //--------------------

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public static interface OnChangeScreen {
        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListeners(String newScreen, Object userData) {
        for (OnChangeScreen l : listeners) {
            l.onScreenChanged(newScreen, userData);
        }
    }
}
