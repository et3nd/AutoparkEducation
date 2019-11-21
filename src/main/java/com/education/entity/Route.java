package com.education.entity;

import lombok.Data;

@Data
public class Route {
    private int routeNumber;
    private String startStation = "default";
    private String endStation = "default";
    private String stops = "default";
    private int distance;

    @Override
    public String toString() {
        return String.format("%-5s %15s %15s %30s %10s\n", "Номер", "Начальная", "Конечная", "Остановки", "Дистанция")
                + "\n"
                + String.format("%-5s %15s %15s %30s %10s\n", routeNumber, startStation, endStation, stops, distance);
    }
}
