package main.backend;

public class Category {
    private IdNumberCategory idNumber;
    private String name;
    private Score totalScore;
    private Weight weight;
    private Date assignDate;
    private Date dueDate;

    //constructor
    public Category() {
        idNumber = new IdNumberCategory();
        name = "";
        totalScore = new Score();
        weight = new Weight();
        assignDate = new Date();
        dueDate = new Date();
    }

    public Category(String name, double totalScore, double weight) {
        this();
        setName(name);
        setTotalScore(totalScore);
        setWeight(weight);
    }

    public Category(String name, double totalScore, double weight, Date assignDate, Date dueDate) {
        this();
        setName(name);
        setTotalScore(totalScore);
        setWeight(weight);
        this.assignDate = assignDate;
        this.dueDate = dueDate;
    }

    public Category(String name, double totalScore, double weight,
                    int day, int month, int year,
                    int dueDay, int dueMonth, int dueYear) {
        this(name, totalScore, weight);
        setAssignDate(day, month, year);
        setDueDate(dueDay, dueMonth, dueYear);
    }
    //accessor
    public IdNumberCategory getIdNumber() {
        return idNumber;
    }

    public String getId() {
        return idNumber.getId();
    }

    public String getName() {
        return name;
    }

    public double getTotalScore() {
        return totalScore.getValue();
    }

    public double getWeight() {
        return weight.getValue();
    }

    public String getAssignDate() {
        return assignDate.toString();
    }

    public String getDueDate() {
        return dueDate.toString();
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setTotalScore(double value) {
        this.totalScore.setValue(value);
    }

    public void setWeight (double value) {
        this.weight.setValue(value);
    }

    public void setAssignDate (int day, int month, int year) {
        this.assignDate.setDate(day, month, year);
    }

    public void setDueDate (int day, int month, int year) {
        this.dueDate.setDate(day, month, year);
    }

    public void setAssignDate (Date date) {
        this.assignDate = date;
    }

    public void setDueDate (Date date) {
        this.dueDate = date;
    }
}
