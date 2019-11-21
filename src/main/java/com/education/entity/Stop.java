package com.education.entity;

import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;

@Data
public class Stop {
    private String stopName = "default";
    private String direction = "default";
    private Time arrivalTimeOnStop = Time.valueOf(LocalTime.of(0, 0, 0));

    @Override
    public String toString() {
        return String.format("%-15s %15s %30s\n", "Название", "Направление", "Оставшееся время до прибытия")
                + "\n"
                + String.format("%-15s %15s %30s\n", stopName, direction, arrivalTimeOnStop);
    }
}
