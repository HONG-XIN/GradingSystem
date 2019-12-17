package main.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.backend.Course;
import main.backend.CourseGrade;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class exportFinalScoreController {

    private String courseId;
    private Course course;

    @FXML
    CheckBox cbStudentName, cbFinalScore, cbBonus, cbLetter, cbComment, cbBUID, cbEmail;

    @FXML
    Label info;

    @FXML
    protected void initialize() {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            public void onScreenChanged(String newScreen, Object userData) {
                if (newScreen.equals("exportFinalScore")) {
                    courseId = userData.toString();
                    course = Main.gs.getCourseById(courseId);
                }
            }
        });
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
    protected void btExport(ActionEvent e) {
        boolean hasStudentName = cbStudentName.isSelected();
        boolean hasFinalScore = cbFinalScore.isSelected();
        boolean hasBonus = cbBonus.isSelected();
        boolean hasLetter = cbLetter.isSelected();
        boolean hasComment = cbComment.isSelected();
        boolean hasBUID = cbBUID.isSelected();
        boolean hasEmail = cbEmail.isSelected();

        int colnum = 0;
        if (hasStudentName) {
            colnum++;
        }
        if (hasFinalScore) {
            colnum++;
        }
        if (hasBonus) {
            colnum++;
        }
        if (hasLetter) {
            colnum++;
        }
        if (hasComment) {
            colnum++;
        }
        if (hasBUID) {
            colnum++;
        }
        if (hasEmail) {
            colnum++;
        }

        String[][] data = Main.gs.getFinalGradeListByCourse(course);
        if (data == null) {
            info.setText("Export Failed");
            return;
        }
        List<String> titles = new ArrayList<>();
        if (hasStudentName) {
            titles.add("Student Name");
        }
        if (hasBUID) {
            titles.add("BU ID");
        }
        if (hasEmail) {
            titles.add("Email");
        }
        if (hasBonus) {
            titles.add("Bonus");
        }
        if (hasFinalScore) {
            titles.add("Final Score");
        }
        if (hasLetter) {
            titles.add("Letter Grade");
        }
        if (hasComment) {
            titles.add("Comment");
        }
//        String[][] wdata = new String[data.length][colnum];
        List<List<String>> wdata = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {

            String gid = data[i][0];
            String sname = data[i][1];
            String buid = data[i][2];
            String email = data[i][3];
            String bonus = data[i][4];
            String fscore = data[i][5];
            String letter = data[i][6];
            CourseGrade cg = Main.gs.getCourseGradeById(gid);
            String comment = cg.getComment();

            List<String> oneLine = new ArrayList<>();
            if (hasStudentName) {
                oneLine.add(sname);
            }
            if (hasBUID) {
                oneLine.add(buid);
            }
            if (hasEmail) {
                oneLine.add(email);
            }
            if (hasBonus) {
                oneLine.add(bonus);
            }
            if (hasFinalScore) {
                oneLine.add(fscore);
            }
            if (hasLetter) {
                oneLine.add(letter);
            }
            if (hasComment) {
                oneLine.add(comment);
            }
            wdata.add(oneLine);


        }
        String path = "";
        System.out.println(System.getProperty("os.name"));
        if (System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("windows")) {
            path = System.getProperty("user.home") + "/Desktop/export_data.csv";
        } else if (System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("Mac OS X")) {
            path = System.getProperty("user.home") + "/Desktop/export_data.csv";
        }
        System.out.println(path);
        Main.gs.exportTable(path, "Sheet1", titles, wdata);
        info.setText("Export Data Success : "+path);
    }

    @FXML
    protected void btBack(ActionEvent e) {
        Main.changeScreen("tabFinalScore", courseId);
    }
}
