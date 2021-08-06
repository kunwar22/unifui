package in.co.srdt.unif.model.payroll;

public class PersonSearch {
    private String personId;
    private String personNumber;
    private String name;
    private String noe;


    public PersonSearch() {
        super();
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public String getNoe() {
        return noe;
    }

    public void setNoe(String noe) {
        this.noe = noe;
    }

    @Override
    public String toString() {
        return "PersonSearch [personId=" + personId + ", personNumber=" + personNumber + ", name=" + name + ", noe="
                + noe + "]";
    }


}
