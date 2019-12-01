package main.backend;
import java.util.ArrayList;

public class Template {
    private IdNumberTemplate templateIdNumber;
    private String templateName;
    private ArrayList<IdNumberCategoryGroup> groupIdNumbers;

    //constructor
    public Template() {
        templateIdNumber = new IdNumberTemplate();
        templateName = "";
        groupIdNumbers = new ArrayList<>();
    }

    public Template (String templateId, String templateName) {
        this();
        setTemplateIdNumber(templateId);
        setTemplateName(templateName);
    }

    //accessor
    public String getTemplateName() {
        return this.templateName;
    }

    public ArrayList<IdNumberCategoryGroup> getCategoryGroups() {
        return this.groupIdNumbers;
    }

    //mutator
    public void setTemplateIdNumber(String templateId) {
        this.templateIdNumber.setCode(templateId);
    }
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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