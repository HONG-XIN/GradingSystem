package main.backend;

public class Category {
    private IdNumberCategory categoryIdNumber;
    private String categoryName;
    private Weight categoryWeight;
    private Date assignDate;
    private Date dueDate;

    //constructor
    public Category() {
        categoryIdNumber = new IdNumberCategory();
        categoryName = "";
        categoryWeight = new Weight();
        assignDate = new Date();
        dueDate = new Date();
    }

    public Category(String categoryId, String categoryName, double percentage) {
        this();
        setCategoryId(categoryId);
        setCategoryName(categoryName);
        setCategoryWeight(percentage);
    }

    public Category(String categoryId, String categoryName, double percentage,
                    int day, int month, int year,
                    int dueDay, int dueMonth, int dueYear) {
        this(categoryId, categoryName, percentage);
        setAssignDate(day, month, year);
        setDueDate(dueDay, dueMonth, dueYear);
    }
    //accessor
    public IdNumberCategory getCategoryNumber() {
        return categoryIdNumber;
    }

    public String getCategoryId() {
        return categoryIdNumber.getId();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getCategoryWeight() {
        return categoryWeight.getPercentage();
    }

    public String getAssignDate() {
        return assignDate.toString();
    }

    public String getDueDate() {
        return dueDate.toString();
    }

    //mutator
    public void setCategoryId(String categoryId) {
        this.categoryIdNumber.setId(categoryId);
    }

    public void setCategoryName (String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryWeight (double percentage) {
        this.categoryWeight.setPercentage(percentage);
    }

    public void setAssignDate (int day, int month, int year) {
        this.assignDate.setDate(day, month, year);
    }

    public void setDueDate (int day, int month, int year) {
        this.dueDate.setDate(day, month, year);
    }


}
