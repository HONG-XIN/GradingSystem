package main.backend;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoryGroup implements Cloneable{
    private IdNumberCategoryGroup idNumber;
    private String name;
    private Weight weight;
    private ArrayList<Category> categories;

    //constructor
    public CategoryGroup() {
        idNumber = new IdNumberCategoryGroup();
        name = "";
        weight = new Weight();
        categories = new ArrayList<>();
    }

    public CategoryGroup(String name, double weight) {
        this();
        setName(name);
        setWeight(weight);
    }

    public CategoryGroup(String id, String name, double weight, ArrayList<Category> categories){
        this();
        setId(id);
        setName(name);
        setWeight(weight);
        setCategories(categories);
    }
    //accessor
    public IdNumberCategoryGroup getIdNumber() {
        return this.idNumber;
    }

    public String getId() {
        return this.idNumber.getId();
    }

    public String getName() {
        return this.name;
    }

    public double getWeight() {
        return this.weight.getValue();
    }

    public Weight getWeightObject() {
        return this.weight;
    }

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setIdNumber(IdNumberCategoryGroup idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double value) {
        this.weight.setValue(value);
    }

    public void setWeightObject(Weight weight) {
        this.weight = weight;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    //functions
    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CategoryGroup cloned = new CategoryGroup();

        cloned.setIdNumber((IdNumberCategoryGroup) cloned.getIdNumber().clone());
        cloned.setWeightObject((Weight) cloned.getWeightObject().clone());
        ArrayList<Category> newCategories = new ArrayList<>();
        for (Category category : cloned.getCategories()) {
            newCategories.add((Category) category.clone());
        }
        cloned.setCategories(newCategories);
        return cloned;
    }
}
