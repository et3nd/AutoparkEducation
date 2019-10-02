package entity;

import java.sql.Time;
import java.time.LocalTime;

public class Schedule {
    private int id;
    private Time departureTime = Time.valueOf(LocalTime.of(0, 0, 0));
    private Time arrivalTime = Time.valueOf(LocalTime.of(0, 0, 0));

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return id + " "
                + departureTime + " "
                + arrivalTime;
    }
}
