package in.co.srdt.unif.model.payroll;

import java.util.ArrayList;
import java.util.List;

public class IdentifiedEmployeesWrapper {
    List<IdentifiedEmployees> identifiedEmployees;

    public IdentifiedEmployeesWrapper() {
        identifiedEmployees = new ArrayList<>();
        identifiedEmployees.add(new IdentifiedEmployees());
    }

    public IdentifiedEmployeesWrapper(List<IdentifiedEmployees> identifiedEmployees) {
        this.identifiedEmployees = identifiedEmployees;
    }

    public List<IdentifiedEmployees> getIdentifiedEmployees() {
        return identifiedEmployees;
    }

    public void setIdentifiedEmployees(List<IdentifiedEmployees> identifiedEmployees) {
        this.identifiedEmployees = identifiedEmployees;
    }

    @Override
    public String toString() {
        return "IdentifiedEmployeesWrapper{" +
                "identifiedEmployees=" + identifiedEmployees +
                '}';
    }
}
