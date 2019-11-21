package com.education.entity;

import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;

@Data
public class Schedule {
    private int id;
    private Time departureTime = Time.valueOf(LocalTime.of(0, 1, 1));
    private Time arrivalTime = Time.valueOf(LocalTime.of(0, 1, 1));

    @Override
    public String toString() {
        return String.format("%-3s %15s %20s\n", "ID", "Время прибытия", "Время отправления")
                + "\n"
                + String.format("%-3s %15s %20s\n", id, departureTime, arrivalTime);
    }
}
