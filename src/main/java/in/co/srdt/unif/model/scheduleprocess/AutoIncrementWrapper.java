package in.co.srdt.unif.model.scheduleprocess;

import in.co.srdt.unif.model.payroll.IdentifiedEmployees;

import java.util.ArrayList;
import java.util.List;

public class AutoIncrementWrapper {
    List<EmployeeAutoIncrement> employeeAutoIncrements;

    public AutoIncrementWrapper() {
        employeeAutoIncrements = new ArrayList<>();
        employeeAutoIncrements.add(new EmployeeAutoIncrement());
    }

    public AutoIncrementWrapper(List<EmployeeAutoIncrement> employeeAutoIncrements) {
        this.employeeAutoIncrements = employeeAutoIncrements;
    }

    public List<EmployeeAutoIncrement> getEmployeeAutoIncrements() {
        return employeeAutoIncrements;
    }

    public void setEmployeeAutoIncrements(List<EmployeeAutoIncrement> employeeAutoIncrements) {
        this.employeeAutoIncrements = employeeAutoIncrements;
    }

    @Override
    public String toString() {
        return "AutoIncrementWrapper{" +
                "employeeAutoIncrements=" + employeeAutoIncrements +
                '}';
    }
}
