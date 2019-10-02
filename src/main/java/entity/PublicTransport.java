package entity;

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
}
