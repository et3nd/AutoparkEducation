package entity;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

public class Stops {
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
        return stopName + " "
                + direction + " "
                + arrivalTimeOnStop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stops)) return false;
        Stops stops = (Stops) o;
        return Objects.equals(stopName, stops.stopName) &&
                Objects.equals(direction, stops.direction) &&
                Objects.equals(arrivalTimeOnStop, stops.arrivalTimeOnStop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopName, direction, arrivalTimeOnStop);
    }
}
