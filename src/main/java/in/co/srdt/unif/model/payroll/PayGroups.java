package in.co.srdt.unif.model.payroll;

public class PayGroups {
    String paygroupid = "";
    String name;
    String descr;
    String createdby;

    public PayGroups() {
    }

    public PayGroups(String paygroupid, String name, String descr, String createdby) {
        this.paygroupid = paygroupid;
        this.name = name;
        this.descr = descr;
        this.createdby = createdby;
    }

    public String getPaygroupid() {
        return paygroupid;
    }

    public void setPayroupid(String paygroupid) {
        this.paygroupid = paygroupid;
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
        return "PayGroups{" +
                "paygroupid='" + paygroupid + '\'' +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", createdby='" + createdby + '\'' +
                '}';
    }
}
