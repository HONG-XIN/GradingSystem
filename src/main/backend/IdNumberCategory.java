package main.backend;

public class IdNumberCategory extends IdNumber implements Cloneable{
    public IdNumberCategory() {
        super();
    }

    public IdNumberCategory(String categoryId) {
        super(categoryId);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
