package entity;

import java.util.Objects;

public class Route {
    private int routeNumber;
    private String startStation = "default";
    private String endStation = "default";
    private String stops = "default";
    private int distance;

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return routeNumber + " "
                + startStation + " "
                + endStation + " "
                + stops + " "
                + distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return routeNumber == route.routeNumber &&
                distance == route.distance &&
                Objects.equals(startStation, route.startStation) &&
                Objects.equals(endStation, route.endStation) &&
                Objects.equals(stops, route.stops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeNumber, startStation, endStation, stops, distance);
    }
}
