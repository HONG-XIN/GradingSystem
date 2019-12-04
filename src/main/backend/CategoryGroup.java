package main.backend;

import java.util.ArrayList;
import java.util.Iterator;

public class CategoryGroup {
    private IdNumberCategoryGroup idNumber;
    private String name;
    private Weight weight;
    private ArrayList<IdNumberCategory> idNumberCategories;

    //constructor
    public CategoryGroup() {
        idNumber = new IdNumberCategoryGroup();
        name = "";
        weight = new Weight();
        idNumberCategories = new ArrayList<>();
    }

    public CategoryGroup(String id, String name, double weight) {
        this();
        setId(id);
        setName(name);
        setWeight(weight);
    }
    public CategoryGroup(String id, String name, double weight, ArrayList<IdNumberCategory> idNumberCategories){
        this();
        setId(id);
        setName(name);
        setWeight(weight);
        addCategoryByCategoryList(idNumberCategories);
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

    public ArrayList<IdNumberCategory> getCategoryIds() {
        return this.idNumberCategories;
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

    //functions
    public void addCategoryByCategoryNumber(IdNumberCategory categoryIdNumber) {
        this.idNumberCategories.add(categoryIdNumber);
    }

    public void removeCategoryByCategoryNumber(IdNumberCategory categoryIdNumber) {
        this.idNumberCategories.remove(categoryIdNumber);
    }

    public void addCategoryByCategoryId(String categoryId) {
        this.idNumberCategories.add(new IdNumberCategory(categoryId));
    }

    public void addCategoryByCategoryList(ArrayList<IdNumberCategory> idNumberCategories){
        Iterator<IdNumberCategory> iterator = idNumberCategories.iterator();
        while(iterator.hasNext()){
            IdNumberCategory next = iterator.next();
            this.addCategoryByCategoryId(next.getId());
        }
    }

    public void removeCategoryByCategoryId(String categoryId) {
        this.idNumberCategories.remove(new IdNumberCategory(categoryId));
    }
}
