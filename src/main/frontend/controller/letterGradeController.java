package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.backend.Course;

public class letterGradeController {

    private String courseId;
    private Course course;

    @FXML
    TextField tfAp, tfA, tfAm, tfBp, tfB, tfBm, tfCp, tfC, tfCm, tfDp, tfD, tfDm;

    @FXML
    Label info;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("letterGrade")) {
                    courseId = userData.toString();
                    course = Main.gs.getCourseById(courseId);
                    tfAp.setText("97");
                    tfA.setText("93");
                    tfAm.setText("90");
                    tfBp.setText("87");
                    tfB.setText("83");
                    tfBm.setText("80");
                    tfCp.setText("77");
                    tfC.setText("73");
                    tfCm.setText("70");
                    tfDp.setText("67");
                    tfD.setText("63");
                    tfDm.setText("60");
                }
            }
        });
    }

    @FXML
    protected void btConfirm(ActionEvent e) {
        try {
            double Ap = Double.parseDouble(tfAp.getText());
            double A = Double.parseDouble(tfA.getText());
            double Am = Double.parseDouble(tfAm.getText());
            double Bp = Double.parseDouble(tfBp.getText());
            double B = Double.parseDouble(tfB.getText());
            double Bm = Double.parseDouble(tfBm.getText());
            double Cp = Double.parseDouble(tfCp.getText());
            double C = Double.parseDouble(tfC.getText());
            double Cm = Double.parseDouble(tfCm.getText());
            double Dp = Double.parseDouble(tfDp.getText());
            double D = Double.parseDouble(tfD.getText());
            double Dm = Double.parseDouble(tfDm.getText());
            if (Main.gs.updateFinalLetterGrade(course, Ap, A, Am, Bp, B, Bm, Cp, C, Cm, Dp, D, Dm)) {
                info.setText("Set LetterGrade Success");
            } else {
                info.setText("Set LetterGrade Fail");
            }
        } catch (Exception ex) {
            info.setText("Set LetterGrade Fail");
        }
    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("tabFinalScore", courseId);
    }

}
