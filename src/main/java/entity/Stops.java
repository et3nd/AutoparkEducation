package entity;

import java.sql.Time;

public class Stops {
    private String stopName;
    private String direction;
    private Time arrivalTimeOnStop;

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
