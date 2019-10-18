package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Driver {
    private int license;
    private String fio = "default";
    private int salary = 20000;
    private String address = "default";
    private Date birthDate = Date.valueOf(LocalDate.of(1900, 1, 1));

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("%-10s %30s %10s %20s %15s\n", "Лицензия", "ФИО", "Зарплaта", "Адрес", "Дата рождения")
                + "\n"
                + String.format("%-10s %30s %10s %20s %15s\n", license, fio, salary, address, birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return license == driver.license &&
                salary == driver.salary &&
                Objects.equals(fio, driver.fio) &&
                Objects.equals(address, driver.address) &&
                Objects.equals(birthDate, driver.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(license, fio, salary, address, birthDate);
    }
}
