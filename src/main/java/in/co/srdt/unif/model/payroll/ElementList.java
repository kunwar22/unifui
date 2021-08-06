package in.co.srdt.unif.model.payroll;

public class ElementList {
    long elementid;
    String name;

    public ElementList() {
    }

    public ElementList(long elementid, String name) {
        this.elementid = elementid;
        this.name = name;
    }

    public long getElementid() {
        return elementid;
    }

    public void setElementid(long elementid) {
        this.elementid = elementid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ElementList{" +
                "elementid=" + elementid +
                ", name='" + name + '\'' +
                '}';
    }
}
