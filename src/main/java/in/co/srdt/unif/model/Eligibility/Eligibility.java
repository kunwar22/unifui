package in.co.srdt.unif.model.Eligibility;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;



@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class Eligibility { //  Parent Class *********

	
	private long eligibilityid;

	@NotBlank(message = "Please enter Name")
	private String eligibilityname;

	private String eligibilitydescription;
	
	@NotBlank(message = "Please enter Code")
    private String eligibilitycode;

	private List<EligibilityCriteria> eligibilitycriteria;

    public Eligibility() {
        eligibilitycriteria = new ArrayList<>();
		eligibilitycriteria.add(new EligibilityCriteria());
    }

    public long getEligibilityid() {
        return eligibilityid;
    }

    public void setEligibilityid(long eligibilityid) {
        this.eligibilityid = eligibilityid;
    }

    public String getEligibilityname() {
        return eligibilityname;
    }

    public void setEligibilityname(String eligibilityname) {
        this.eligibilityname = eligibilityname;
    }

    public String getEligibilitydescription() {
        return eligibilitydescription;
    }

    public void setEligibilitydescription(String eligibilitydescription) {
        this.eligibilitydescription = eligibilitydescription;
    }

    public List<EligibilityCriteria> getEligibilitycriteria() {
        return eligibilitycriteria;
    }

    public void setEligibilitycriteria(List<EligibilityCriteria> eligibilitycriteria) {
        this.eligibilitycriteria = eligibilitycriteria;
    }

    public String getEligibilitycode() {
        return eligibilitycode;
    }

    public void setEligibilitycode(String eligibilitycode) {
        this.eligibilitycode = eligibilitycode;
    }

    @Override
    public String toString() {
        return "Eligibility{" +
                "eligibilityid=" + eligibilityid +
                ", eligibilityname='" + eligibilityname + '\'' +
                ", eligibilitydescription='" + eligibilitydescription + '\'' +
                ", eligibilitycode='" + eligibilitycode + '\'' +
                ", eligibilitycriteria=" + eligibilitycriteria +
                '}';
    }
}
