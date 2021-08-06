package in.co.srdt.unif.model.payroll;

public class ManageCarPerk {
    private long carId;
    private String personNumber;
    private String name;
    private String desgination;
    private String location;
    private String startdate;
    private String enddate;
    private double perkAmt;
    private double recAmt;
    private String createdBy;

    public ManageCarPerk() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesgination() {
        return desgination;
    }

    public void setDesgination(String desgination) {
        this.desgination = desgination;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public double getPerkAmt() {
        return perkAmt;
    }

    public void setPerkAmt(double perkAmt) {
        this.perkAmt = perkAmt;
    }

    public double getRecAmt() {
        return recAmt;
    }

    public void setRecAmt(double recAmt) {
        this.recAmt = recAmt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "ManageCarPerk{" +
                "carId=" + carId +
                ", personNumber='" + personNumber + '\'' +
                ", name='" + name + '\'' +
                ", desgination='" + desgination + '\'' +
                ", location='" + location + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", perkAmt=" + perkAmt +
                ", recAmt=" + recAmt +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
