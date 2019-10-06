package entity;

import java.util.Objects;

public class PublicTransport {
    private int transportNumber;
    private String busBrand = "default";
    private int capacity = 20;
    private int issueYear;

    public int getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(int transportNumber) {
        this.transportNumber = transportNumber;
    }

    public String getBusBrand() {
        return busBrand;
    }

    public void setBusBrand(String busBrand) {
        this.busBrand = busBrand;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    @Override
    public String toString() {
        return transportNumber + " "
                + busBrand + " "
                + capacity + " "
                + issueYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PublicTransport)) return false;
        PublicTransport transport = (PublicTransport) o;
        return transportNumber == transport.transportNumber &&
                capacity == transport.capacity &&
                issueYear == transport.issueYear &&
                Objects.equals(busBrand, transport.busBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportNumber, busBrand, capacity, issueYear);
    }
}
