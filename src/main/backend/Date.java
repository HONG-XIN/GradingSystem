package main.backend;

public class Date implements Comparable<Date> , Cloneable{
import main.backend.Course;
import main.backend.GradingSystem;
import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.RecordIterable;

public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    //constructor
    public Date() {
        day = 1;
        month = 1;
        year = 2019;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //accessor
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    //mutator
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    //compare function
    public int compareTo(Date otherDate) {
        if (otherDate.year < year) {
            return -1;
        } else if (otherDate.year > year) {
            return 1;
        } else if (otherDate.month < month) {
            return -1;
        } else if (otherDate.month > month) {
            return 1;
        } else return Integer.compare(otherDate.day, day);
    }

    // override function
    @Override
    public String toString() {
        String dateFormat = "%s/%s/%s";
        return String.format(dateFormat, day, month, year);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    //Database Function
    //from RAM TO DB
    public Document write(){
        Document DateDoc = new Document();
        DateDoc.put("day", getDay());
        DateDoc.put("month", getMonth());
        DateDoc.put("year", getYear());
        return DateDoc;
    }

    //from DB to RAM
    public void read(Document doc){
        if (doc != null) {
            setDay((int) doc.get("day"));
            setMonth((int) doc.get("month"));
            setYear((int) doc.get("year"));
        }
    }
}
