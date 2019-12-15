package main.backend;

public class IdNumberCriteria extends IdNumber implements Cloneable{
    public IdNumberCriteria(){
        super();
    }

    public IdNumberCriteria(String criteriaId) {
        super(criteriaId);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
