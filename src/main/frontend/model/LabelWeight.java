package main.frontend.model;

import javafx.scene.control.Button;

public class LabelWeight {
    String label, weight, id;

    Button delete;

    public LabelWeight(String id, String label, String weight, Button delete) {
        this.id = id;
        this.label = label;
        this.weight = weight;
        this.delete = delete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
