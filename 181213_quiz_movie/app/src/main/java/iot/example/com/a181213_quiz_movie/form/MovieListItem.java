package iot.example.com.a181213_quiz_movie.form;

import java.util.Date;

/**
 * Created by student on 2018-12-14.
 */

public class MovieListItem {
    int year, month, day, hour, min;
    int runningTime;
    String title;
    int allSeat, resSeat;

    public MovieListItem(int year, int month, int day, int hour, int min, int runningTime, String title, int allSeat, int resSeat) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.runningTime = runningTime;
        this.title = title;
        this.allSeat = allSeat;
        this.resSeat = resSeat;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAllSeat() {
        return allSeat;
    }

    public void setAllSeat(int allSeat) {
        this.allSeat = allSeat;
    }

    public int getResSeat() {
        return resSeat;
    }

    public void setResSeat(int resSeat) {
        this.resSeat = resSeat;
    }
}
