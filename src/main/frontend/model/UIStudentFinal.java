package main.frontend.model;

import javafx.scene.control.Button;

public class UIStudentFinal {
    String id, name, bonus, score, letter;
    Button comment, freeze, delete;

    public UIStudentFinal(String id, String name, String bonus, String score, String letter, Button comment, Button freeze, Button delete) {
        this.id = id;
        this.name = name;
        this.bonus = bonus;
        this.score = score;
        this.letter = letter;
        this.comment = comment;
        this.freeze = freeze;
        this.delete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Button getComment() {
        return comment;
    }

    public void setComment(Button comment) {
        this.comment = comment;
    }

    public Button getFreeze() {
        return freeze;
    }

    public void setFreeze(Button freeze) {
        this.freeze = freeze;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
