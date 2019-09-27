package entity;

public class Stops {
    private String stopName;
    private String direction;
    private int arrivalTimeOnStop;

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

    public int getArrivalTimeOnStop() {
        return arrivalTimeOnStop;
    }

    public void setArrivalTimeOnStop(int arrivalTimeOnStop) {
        this.arrivalTimeOnStop = arrivalTimeOnStop;
    }

    @Override
    public String toString() {
        return "Stops{" +
                "stopName='" + stopName + '\'' +
                ", direction='" + direction + '\'' +
                ", arrivalTimeOnStop=" + arrivalTimeOnStop +
                '}';
    }
}
