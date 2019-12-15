package main.backend;

public class IdNumberCategoryGroup extends IdNumber implements Cloneable{
    public IdNumberCategoryGroup() {
        super();
    }

    public IdNumberCategoryGroup(String groupId) {
        super(groupId);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
