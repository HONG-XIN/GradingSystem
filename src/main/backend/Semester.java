package main.backend;

import org.dizitart.no2.Document;

public class Semester {
    private IdNumberSemester idNumber;
    private String name;
    private Date startDate;
    private Date endDate;

    public Semester() {
        this.idNumber = new IdNumberSemester();
        this.name = "";
        startDate = new Date();
        endDate = new Date();
    }

    public Semester(String name, Date startDate, Date endDate) {
        this();
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Semester(String name,
                    int startDay, int startMonth, int startYear,
                    int endDay, int endMonth, int endYear) {
        this();
        this.name = name;
        this.startDate.setDate(startDay, startMonth, startYear);
        this.endDate.setDate(endDay, endMonth, endYear);
    }

    //accessor
    public String getId() {
        return idNumber.getId();
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate.toString();
    }

    public String getEndDate() {
        return endDate.toString();
    }

    public Date getStartDateObject() {return this.startDate;}

    public Date getEndDateObject() {return this.endDate;}

    public IdNumberSemester getIdNumberObject() {return this.idNumber;}

    //mutator
    public void setId(String id) {
        this.idNumber.setId(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(int startDay, int startMonth, int startYear) {
        this.startDate.setDate(startDay, startMonth, startYear);
    }

    public void setEndDate(int endDay, int endMonth, int endYear) {
        this.endDate.setDate(endDay, endMonth, endYear);
    }

    //check function
    public boolean checkDateInRange(Date date) {
        return startDate.compareTo(date) >= 0 && endDate.compareTo(date) <= 0;
    }

    public boolean checkSemesterSame(Semester filter){
        return this.name.equals(filter.getName());
    }

    //DB function
    //from RAM to DB
    public Document write(){
        Document SemesterDoc = new Document();
        SemesterDoc.put("name", getName());
        if(this.getStartDateObject() != null){
            SemesterDoc.put("startDate", getStartDateObject().write());
        }
        if (this.getEndDateObject() != null){
            SemesterDoc.put("endDate", getEndDateObject().write());
        }
        if (this.getIdNumberObject() != null){
            SemesterDoc.put("idNumber", getIdNumberObject().write());
        }
        return SemesterDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setName((String) doc.get("name"));
            Document startDateDoc = (Document) doc.get("startDate");
            if(startDateDoc != null){
                Date startDate = new Date();
                startDate.read(startDateDoc);
                this.startDate = startDate;
            }
            Document endDateDoc = (Document) doc.get("endDate");
            if(endDateDoc != null){
                Date endDate = new Date();
                endDate.read(endDateDoc);
                this.endDate = endDate;
            }
            Document IdNumberSemesterDoc = (Document) doc.get("idNumber");
            if (IdNumberSemesterDoc != null){
                IdNumberSemester idNumber = new IdNumberSemester();
                idNumber.read(IdNumberSemesterDoc);
                this.idNumber = idNumber;
            }
        }
    }
}
