package main.backend;
import java.util.ArrayList;
import java.util.Iterator;

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

    public Template (String name) {
        this();
        setName(name);
    }

    public Template (String id, String name, ArrayList<IdNumberCategoryGroup> groupIdNumbers) {
        this();
        setId(id);
        setName(name);
        addGroupByGroupList(groupIdNumbers);
    }

    //accessor
    public String getId() {
        return this.idNumber.getId();
    }

    public String getName() {
        return this.name;
    }

    public String[] getCategoryGroupIds() {
        int n = groupIdNumbers.size();
        String[] groupIdList = new String[n];
        for(int i = 0; i < n; i++) {
            groupIdList[i] = groupIdNumbers.get(i).getId();
        }
        return groupIdList;
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

    public void addGroupByGroupList(ArrayList<IdNumberCategoryGroup> groupIdNumbers) {
        Iterator<IdNumberCategoryGroup> iterator = groupIdNumbers.iterator();
        while(iterator.hasNext()){
            IdNumberCategoryGroup next = iterator.next();
            this.addGroupByGroupNumber(next);
        }
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