package main.database;

import org.dizitart.no2.objects.Id;

import java.io.Serializable;

public class Arvore implements Serializable {

    @Id
    private int id;

    private String species;
    private int idade;
    private String color;

    public Arvore(int id, String species, int idade, String color) {
        this.id = id;
        this.species = species;
        this.idade = idade;
        this.color = color;
    }

    public Arvore() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Arvore{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", idade=" + idade +
                ", color='" + color + '\'' +
                '}';
    }
}
