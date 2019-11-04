package entity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class Schedule {
    private int id;
    private Time departureTime = Time.valueOf(LocalTime.of(0, 1, 1));
    private Time arrivalTime = Time.valueOf(LocalTime.of(0, 1, 1));

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
        return String.format("%-3s %15s %20s\n", "ID", "Время прибытия", "Время отправления")
                + "\n"
                + String.format("%-3s %15s %20s\n", id, departureTime, arrivalTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                Objects.equals(departureTime, schedule.departureTime) &&
                Objects.equals(arrivalTime, schedule.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureTime, arrivalTime);
    }
}
