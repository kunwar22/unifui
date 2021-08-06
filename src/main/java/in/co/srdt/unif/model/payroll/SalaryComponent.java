package in.co.srdt.unif.model.payroll;

public class SalaryComponent {
    private String salarycompid;
    private String componentid;
    private String name;
    private String descr;
    private String createdby;

    public SalaryComponent() {
    }

    public SalaryComponent(String salarycompid, String componentid, String name, String descr, String createdby) {
        this.salarycompid = salarycompid;
        this.componentid = componentid;
        this.name = name;
        this.descr = descr;
        this.createdby = createdby;
    }

    public String getSalarycompid() {
        return salarycompid;
    }

    public void setSalarycompid(String salarycompid) {
        this.salarycompid = salarycompid;
    }

    public String getComponentid() {
        return componentid;
    }

    public void setComponentid(String componentid) {
        this.componentid = componentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    @Override
    public String toString() {
        return "SalaryComponent{" +
                "salarycompid='" + salarycompid + '\'' +
                ", componentid='" + componentid + '\'' +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", createdby='" + createdby + '\'' +
                '}';
    }
}
