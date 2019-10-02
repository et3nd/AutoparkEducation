package entity;

import java.sql.Time;
import java.time.LocalTime;

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
}
