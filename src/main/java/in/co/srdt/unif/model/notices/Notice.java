package in.co.srdt.unif.model.notices;

public class Notice {

    private long id;
    private String published_date;
    private String subject;
    private String fileattachement;
    private String createdby;
    private String modifiedby;
    private String department;
    private String status;
    private String type;
    private String orderno;
    private String expirydate;

    public Notice() {
    }

    public Notice(long id, String published_date, String subject, String fileattachement, String createdby, String modifiedby, String department, String status, String type, String orderno, String expirydate) {
        this.id = id;
        this.published_date = published_date;
        this.subject = subject;
        this.fileattachement = fileattachement;
        this.createdby = createdby;
        this.modifiedby = modifiedby;
        this.department = department;
        this.status = status;
        this.type = type;
        this.orderno = orderno;
        this.expirydate = expirydate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFileattachement() {
        return fileattachement;
    }

    public void setFileattachement(String fileattachement) {
        this.fileattachement = fileattachement;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", published_date='" + published_date + '\'' +
                ", subject='" + subject + '\'' +
                ", fileattachement='" + fileattachement + '\'' +
                ", createdby='" + createdby + '\'' +
                ", modifiedby='" + modifiedby + '\'' +
                ", department='" + department + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", orderno='" + orderno + '\'' +
                ", expirydate='" + expirydate + '\'' +
                '}';
    }
}
