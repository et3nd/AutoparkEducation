package com.education.entity;

import lombok.Data;

@Data
public class PublicTransport {
    private int transportNumber;
    private String busBrand = "default";
    private int capacity;
    private int issueYear;

    @Override
    public String toString() {
        return String.format("%-5s %10s %15s %12s\n", "Номер", "Марка", "Вместительность", "Год выпуска")
                + "\n"
                + String.format("%-5s %10s %15s %12s\n", transportNumber, busBrand, capacity, issueYear);
    }
}
