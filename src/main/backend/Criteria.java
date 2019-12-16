package main.backend;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;

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

    //DB function
    //from RAM to DB
    public Document write(){
        Document CriteriaDoc = new Document();
        CriteriaDoc.put("name", getName());
        if(this.getIdNumberObject() != null){
            CriteriaDoc.put("idNumber", getIdNumberObject().write());
        }
        ArrayList<Document> groupsList = new ArrayList<Document>();
        for(int i = 0; i < groups.size(); i++){
            groupsList.add(groups.get(i).write());
        }
        CriteriaDoc.put("groups", groupsList);
        return CriteriaDoc;
    }

    //FROM DB TO RAM
    public void read(Document doc){
        if(doc != null){
            setName((String) doc.get("name"));
            Document idNumberDoc = (Document) doc.get("idNumber");
            if (idNumberDoc != null){
                IdNumberCriteria idNumber = new IdNumberCriteria();
                idNumber.read(idNumberDoc);
                this.idNumber = idNumber;
            }
            ArrayList<Document> groupsList = (ArrayList<Document>) doc.get("groups");
            if (groupsList != null){
                for(Document group: groupsList){
                    if (group != null){
                        CategoryGroup categoryGroup = new CategoryGroup();
                        categoryGroup.read(group);
                        groups.add(categoryGroup);
                    }
                }
            }
        }
    }
}