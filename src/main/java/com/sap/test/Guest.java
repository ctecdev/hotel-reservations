package com.sap.test;

public class Guest {

    private String name;
    private int startDay;
    private int endDay;

    public Guest(String name, int startDay, int endDay) {
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    @Override
    public String toString() {
        return name + " | " + startDay + " | " + endDay + " | ";
    }
}
