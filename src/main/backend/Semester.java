package main.backend;

public class Semester {
    private String name;
    private Date startDate;
    private Date endDate;

    public Semester() {
        this.name = "";
        startDate = new Date();
        endDate = new Date();
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
    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate.toString();
    }

    public String getEndDate() {
        return endDate.toString();
    }

    //mutator
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

    public boolean checkSemsterSame(Semester filter){ return this.name == filter.name; }
}
