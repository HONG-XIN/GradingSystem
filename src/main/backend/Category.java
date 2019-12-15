package main.backend;

public class Category implements Cloneable{
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

    public Score getTotalScoreObject() {
        return this.totalScore;
    }

    public double getTotalScore() {
        return totalScore.getValue();
    }

    public double getWeight() {
        return weight.getValue();
    }

    public Weight getWeightObject() {
        return this.weight;
    }

    public Date getAssignDate() {
        return this.assignDate;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public String getAssignDateString() {
        return assignDate.toString();
    }

    public String getDueDateString() {
        return dueDate.toString();
    }

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setIdNumber(IdNumberCategory idNumber) {
        this.idNumber = idNumber;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setTotalScoreObject (Score totalScore) {
        this.totalScore = totalScore;
    }

    public void setTotalScore(double value) {
        this.totalScore.setValue(value);
    }

    public void setWeightObject(Weight weight) {
        this.weight = weight;
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        Category cloned = new Category();

        cloned.setIdNumber((IdNumberCategory) cloned.getIdNumber().clone());
        cloned.setTotalScoreObject((Score) cloned.getTotalScoreObject().clone());
        cloned.setWeightObject((Weight) cloned.getWeightObject().clone());
        cloned.setAssignDate((Date) cloned.getAssignDate().clone());
        cloned.setDueDate((Date) cloned.getDueDate().clone());
        return super.clone();
    }
}
