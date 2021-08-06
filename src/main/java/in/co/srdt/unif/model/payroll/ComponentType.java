package in.co.srdt.unif.model.payroll;


public class ComponentType {
    private String componentid;
    private String code;
    private String name;
    private String createdby;

    public ComponentType() {

    }

    public ComponentType(String componentid, String code, String name, String createdby) {
        this.componentid = componentid;
        this.code = code;
        this.name = name;
        this.createdby = createdby;
    }

    public String getComponentid() {
        return componentid;
    }

    public void setComponentid(String componentid) {
        this.componentid = componentid;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    @Override
    public String toString() {
        return "ComponentType{" +
                "componentid='" + componentid + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", createdby='" + createdby + '\'' +
                '}';
    }
}
