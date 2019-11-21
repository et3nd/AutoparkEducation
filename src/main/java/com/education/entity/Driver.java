package com.education.entity;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class Driver {
    private int license;
    private String fio = "default";
    private int salary;
    private String address = "default";
    private Date birthDate = Date.valueOf(LocalDate.of(1900, 1, 1));

    @Override
    public String toString() {
        return String.format("%-10s %30s %10s %20s %15s\n", "Лицензия", "ФИО", "Зарплaта", "Адрес", "Дата рождения")
                + "\n"
                + String.format("%-10s %30s %10s %20s %15s\n", license, fio, salary, address, birthDate);
    }
}
