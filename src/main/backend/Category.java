package main.backend;

import org.dizitart.no2.Document;

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

    public Category(String name, double totalScore, double weight, ScoreType type) {
        this();
        setName(name);
        setTotalScore(totalScore);
        setWeight(weight);
        setScoreType(type);
    }

    public Category(String name, double totalScore, double weight, ScoreType type,
                    int day, int month, int year,
                    int dueDay, int dueMonth, int dueYear) {
        this(name, totalScore, weight, type);
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

    public Score getTotalScoreObject() { return totalScore;}

    public double getWeight() {
        return weight.getValue();
    }

    public Weight getWeightObject() { return weight;}

    public ScoreType getScoreType() {
        return totalScore.getType();
    }

    public String getAssignDate() {
        return assignDate.toString();
    }

    public Date getAssignDateObject() {return assignDate;}

    public String getDueDate() {
        return dueDate.toString();
    }

    public Date getDueDateObject() { return dueDate;}

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setName (String name) {
        this.name = name;
    }

    private void setTotalScore(double value) {
        this.totalScore.setValue(value);
    }

    public void setWeight (double value) {
        this.weight.setValue(value);
    }

    public void setScoreType(ScoreType type) {
        this.totalScore.setType(type);
    }

    public void setAssignDate (int day, int month, int year) {
        this.assignDate.setDate(day, month, year);
    }

    public void setDueDate (int day, int month, int year) {
        this.dueDate.setDate(day, month, year);
    }

    //DB function
    //from RAM to DB
    public Document write(){
        Document CategoryDoc = new Document();
        CategoryDoc.put("name", getName());
        if(this.getIdNumber() != null){
            CategoryDoc.put("idNumber", getIdNumber().write());
        }
        if(this.getTotalScoreObject() != null){
            CategoryDoc.put("totalScore", getTotalScoreObject().write());
        }
        if(this.getWeightObject() != null){
            CategoryDoc.put("weight", getWeightObject().write());
        }
        if(this.getAssignDateObject() != null){
            CategoryDoc.put("assignDate", getAssignDateObject().write());
        }
        if(this.getDueDateObject() != null){
            CategoryDoc.put("dueDate", getDueDateObject().write());
        }
        return CategoryDoc;
    }

    //FROM DB TO RAM
    public void read(Document doc){
        if(doc != null){
            setName((String) doc.get("name"));
            Document idNumberDoc = (Document) doc.get("idNumber");
            if (idNumberDoc != null){
                IdNumberCategory idNumber = new IdNumberCategory();
                idNumber.read(idNumberDoc);
                this.idNumber = idNumber;
            }
            Document scoreDoc = (Document) doc.get("totalScore");
            if(scoreDoc != null){
                Score totalScore = new Score();
                totalScore.read(scoreDoc);
                this.totalScore = totalScore;
            }
            Document weightDoc = (Document) doc.get("weight");
            if(weightDoc != null){
                Weight weight = new Weight();
                weight.read(weightDoc);
                this.weight = weight;
            }
            Document assignDateDoc = (Document) doc.get("assignDate");
            if(assignDateDoc != null){
                Date assignDate = new Date();
                assignDate.read(assignDateDoc);
                this.assignDate = assignDate;
            }
            Document dueDateDoc = (Document) doc.get("dueDate");
            if(dueDateDoc != null){
                Date dueDate = new Date();
                dueDate.read(dueDateDoc);
                this.dueDate = dueDate;
            }
        }
    }
}
