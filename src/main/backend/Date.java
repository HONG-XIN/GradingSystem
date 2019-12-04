package main.backend;

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
        } else if (otherDate.day < day) {
            return -1;
        } else if (otherDate.day > day) {
            return 1;
        } else {
            return 0;
        }
    }

    // override function
    @Override
    public String toString() {
        String dateFormat = "%s/%s/%s";
        return String.format(dateFormat, day, month, year);
    }
}
