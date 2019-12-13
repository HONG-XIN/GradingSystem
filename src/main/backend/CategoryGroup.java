package main.backend;

import java.util.ArrayList;

public class CategoryGroup {
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

    public ArrayList<Category> getCategories() {
        return this.categories;
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double value) {
        this.weight.setValue(value);
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

    public double getGroupValue(){
        double total = 0;
        for(Category category : this.categories){
            total = total + category.getCategoryValue();
        }
        return total*this.weight.getValue();
    }
}
