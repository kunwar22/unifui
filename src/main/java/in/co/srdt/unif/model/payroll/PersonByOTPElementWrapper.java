package in.co.srdt.unif.model.payroll;

import java.util.ArrayList;
import java.util.List;

public class PersonByOTPElementWrapper {
    List<PersonByOTPElement> personByOTPElements;

    public PersonByOTPElementWrapper() {
        personByOTPElements = new ArrayList<>();
        personByOTPElements.add(new PersonByOTPElement());
    }

    public PersonByOTPElementWrapper(List<PersonByOTPElement> personByOTPElements) {
        this.personByOTPElements = personByOTPElements;
    }

    public List<PersonByOTPElement> getPersonByOTPElements() {
        return personByOTPElements;
    }

    public void setPersonByOTPElements(List<PersonByOTPElement> personByOTPElements) {
        this.personByOTPElements = personByOTPElements;
    }

    @Override
    public String toString() {
        return "PersonByOTPElementWrapper{" +
                "personByOTPElements=" + personByOTPElements +
                '}';
    }
}
