package main.backend;
import java.util.ArrayList;
import java.util.Iterator;

public class Criteria implements Cloneable{
    private IdNumberCriteria idNumber;
    private String name;
    private ArrayList<CategoryGroup> groups;

    //constructor
    public Criteria() {
        idNumber = new IdNumberCriteria();
        name = "";
        groups = new ArrayList<>();
    }

    public Criteria(String name) {
        this();
        setName(name);
    }

    //accessor
    public String getId() {
        return this.idNumber.getId();
    }

    public IdNumberCriteria getIdNumber() {
        return this.idNumber;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<CategoryGroup> getCategoryGroups() {
        return this.groups;
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setIdNumber(IdNumberCriteria idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryGroups(ArrayList<CategoryGroup> groups) {
        this.groups = groups;
    }

    //functions
    public void addGroup(CategoryGroup group) {
        this.groups.add(group);
    }

    public void removeGroup(CategoryGroup group) {
        this.groups.remove(group);
    }

    public Object clone() throws
            CloneNotSupportedException
    {
         Criteria cloned = (Criteria) super.clone();

        cloned.setIdNumber((IdNumberCriteria) cloned.getIdNumber().clone());

        ArrayList<CategoryGroup> newCategoryGroups = new ArrayList<>();
        for (CategoryGroup group : cloned.getCategoryGroups()) {
            newCategoryGroups.add((CategoryGroup) group.clone());
        }
        cloned.setCategoryGroups(newCategoryGroups);
        return cloned;
    }
}