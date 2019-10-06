package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Drivers {
    private int license = 111111;
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
        return license + " "
                + fio + " "
                + salary + " "
                + address + " "
                + birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drivers)) return false;
        Drivers drivers = (Drivers) o;
        return license == drivers.license &&
                salary == drivers.salary &&
                Objects.equals(fio, drivers.fio) &&
                Objects.equals(address, drivers.address) &&
                Objects.equals(birthDate, drivers.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(license, fio, salary, address, birthDate);
    }
}
