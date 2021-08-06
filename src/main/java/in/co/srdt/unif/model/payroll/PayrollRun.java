package in.co.srdt.unif.model.payroll;

public class PayrollRun {
    String runid;
    String message;
    long totalcount;
    long completed;

    public PayrollRun() {
    }

    public PayrollRun(String runid, String message, long totalcount, long completed) {
        this.runid = runid;
        this.message = message;
        this.totalcount = totalcount;
        this.completed = completed;
    }

    public long getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(long totalcount) {
        this.totalcount = totalcount;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public String getRunid() {
        return runid;
    }

    public void setRunid(String runid) {
        this.runid = runid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PayrollRun{" +
                "runid='" + runid + '\'' +
                ", message='" + message + '\'' +
                ", totalcount=" + totalcount +
                ", completed=" + completed +
                '}';
    }
}
