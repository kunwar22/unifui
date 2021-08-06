package in.co.srdt.unif.model.revisedreimbursements;

import in.co.srdt.unif.model.scheduleprocess.EmployeeAutoIncrement;

import java.util.ArrayList;
import java.util.List;

public class RemTemplateWrapper {

    List<RemTemplate> remTemplates;

    public RemTemplateWrapper() {
        remTemplates = new ArrayList<>();
        remTemplates.add(new RemTemplate());
    }

    public RemTemplateWrapper(List<RemTemplate> remTemplates) {
        this.remTemplates = remTemplates;
    }

    public List<RemTemplate> getRemTemplates() {
        return remTemplates;
    }

    public void setRemTemplates(List<RemTemplate> remTemplates) {
        this.remTemplates = remTemplates;
    }

    @Override
    public String toString() {
        return "RemTemplateWrapper{" +
                "remTemplates=" + remTemplates +
                '}';
    }
}
