package main.backend;

public class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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
}
