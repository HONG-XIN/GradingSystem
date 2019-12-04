package main.backend;
import java.util.ArrayList;

public class Template {
    private IdNumberTemplate idNumber;
    private String name;
    private ArrayList<IdNumberCategoryGroup> groupIdNumbers;

    //constructor
    public Template() {
        idNumber = new IdNumberTemplate();
        name = "";
        groupIdNumbers = new ArrayList<>();
    }

    public Template (String id, String name) {
        this();
        setId(id);
        setName(name);
    }

    //accessor
    public String getId() {
        return this.idNumber.getId();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<IdNumberCategoryGroup> getCategoryGroups() {
        return this.groupIdNumbers;
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    //functions
    public void addGroupByGroupNumber(IdNumberCategoryGroup groupIdNumber) {
        this.groupIdNumbers.add(groupIdNumber);
    }

    public void removeGroupByGroupNumber(IdNumberCategoryGroup groupIdNumber) {
        this.groupIdNumbers.remove(groupIdNumber);
    }

    public void addGroupByGroupId(String groupId) {
        this.groupIdNumbers.add(new IdNumberCategoryGroup(groupId));
    }

    public void removeGroupByGroupId(String groupId) {
        this.groupIdNumbers.remove(new IdNumberCategoryGroup(groupId));
    }
}