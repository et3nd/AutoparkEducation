package com.education.entity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class Stop {
    private String stopName = "default";
    private String direction = "default";
    private Time arrivalTimeOnStop = Time.valueOf(LocalTime.of(0, 0, 0));

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Time getArrivalTimeOnStop() {
        return arrivalTimeOnStop;
    }

    public void setArrivalTimeOnStop(Time arrivalTimeOnStop) {
        this.arrivalTimeOnStop = arrivalTimeOnStop;
    }

    @Override
    public String toString() {
        return String.format("%-15s %15s %30s\n", "Название", "Направление", "Оставшееся время до прибытия")
                + "\n"
                + String.format("%-15s %15s %30s\n", stopName, direction, arrivalTimeOnStop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stop)) return false;
        Stop stop = (Stop) o;
        return Objects.equals(stopName, stop.stopName) &&
                Objects.equals(direction, stop.direction) &&
                Objects.equals(arrivalTimeOnStop, stop.arrivalTimeOnStop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopName, direction, arrivalTimeOnStop);
    }
}
