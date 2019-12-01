package main.backend;

import java.util.ArrayList;

public class CategoryGroup {
    private IdNumberCategoryGroup groupIdNumber;
    private String groupName;
    private Weight groupWeight;
    private ArrayList<IdNumberCategory> idNumberCategories;

    //constructor
    public CategoryGroup() {
        groupIdNumber = new IdNumberCategoryGroup();
        groupName = "";
        groupWeight = new Weight();
        idNumberCategories = new ArrayList<>();
    }

    public CategoryGroup(String groupIdNumber, String groupName, double groupWeight) {
        this();
        setGroupIdNumber(groupIdNumber);
        setGroupName(groupName);
        setGroupWeight(groupWeight);
    }
    //accessor
    public String getGroupName() {
        return this.groupName;
    }

    public double getGroupWeight() {
        return this.groupWeight.getPercentage();
    }

    public ArrayList<IdNumberCategory> getCategoryIds() {
        return this.idNumberCategories;
    }

    //mutator
    public void setGroupIdNumber(String groupIdNumber) {
        this.groupIdNumber.setCode(groupIdNumber);
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupWeight(double percentage) {
        this.groupWeight.setPercentage(percentage);
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

    public void removeCategoryByCategoryId(String categoryId) {
        this.idNumberCategories.remove(new IdNumberCategory(categoryId));
    }
}
