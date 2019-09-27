package entity;

import java.sql.Date;

public class Stops {
    private String stopName;
    private String direction;
    private Date arrivalTimeOnStop;

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

    public Date getArrivalTimeOnStop() {
        return arrivalTimeOnStop;
    }

    public void setArrivalTimeOnStop(Date arrivalTimeOnStop) {
        this.arrivalTimeOnStop = arrivalTimeOnStop;
    }
}
