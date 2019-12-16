package main.frontend.model;

import javafx.scene.control.Button;

public class UIStudent {
    String id, buID, email, score;
    Button name;

    public UIStudent(String id, String buID, String email, String score, Button name) {
        this.id = id;
        this.buID = buID;
        this.email = email;
        this.score = score;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuID() {
        return buID;
    }

    public void setBuID(String buID) {
        this.buID = buID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Button getName() {
        return name;
    }

    public void setName(Button name) {
        this.name = name;
    }
}
