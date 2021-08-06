package in.co.srdt.unif.model.payroll;

public class SalaryComponentSearch {
    private String salarycompid;
    private String componentid;
    private String componenttype;
    private String name;
    private String descr;
    private String createdby;

    public SalaryComponentSearch() {
    }

    public SalaryComponentSearch(String salarycompid, String componentid, String componenttype, String name, String descr, String createdby) {
        this.salarycompid = salarycompid;
        this.componentid = componentid;
        this.componenttype = componenttype;
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

    public String getComponenttype() {
        return componenttype;
    }

    public void setComponenttype(String componenttype) {
        this.componenttype = componenttype;
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
        return "SalaryComponentSearch{" +
                "salarycompid='" + salarycompid + '\'' +
                ", componentid='" + componentid + '\'' +
                ", componenttype='" + componenttype + '\'' +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", createdby='" + createdby + '\'' +
                '}';
    }
}
