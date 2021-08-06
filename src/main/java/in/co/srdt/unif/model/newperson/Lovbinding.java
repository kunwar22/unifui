package in.co.srdt.unif.model.newperson;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import in.co.srdt.unif.model.CommonLOV;
import in.co.srdt.unif.model.payroll.PaygroupLOV;
import in.co.srdt.unif.model.search.GradeStepByGradeId;
import in.co.srdt.unif.model.search.LocationSearchResult;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode =ScopedProxyMode.TARGET_CLASS)

public class Lovbinding {


    CommonLOV[] titleLov=null;
    CommonLOV[] countrylov=null;
    CommonLOV[] nationaltypelov=null;       
    CommonLOV[] workertypelov=null;
    CommonLOV[] genderlov=null;
    CommonLOV[] hireactionlov=null;
    CommonLOV[] legalentitylov=null;    
    CommonLOV[] religionlov=null;
    CommonLOV[] Maritalstatuslov=null;
    CommonLOV[] phonetypelov=null;
    CommonLOV[] assignmentstatuslov=null;
    CommonLOV[] persontypelov=null;
    CommonLOV[] workercatlov=null;
    CommonLOV[] assignmentcatlov=null;
    CommonLOV[] regulartemplov=null;
    CommonLOV[] fullpartlov=null;
    CommonLOV[] probationlov=null;
    CommonLOV[] noticelov=null;
    CommonLOV[] payrollnamelov=null;
    CommonLOV[] cntrycode=null;
    CommonLOV[] emailtypelov=null;
    CommonLOV[] managertypelov=null;
    CommonLOV[] statelov=null;
    CommonLOV[] hirereasonlov=null;
    CommonLOV[] workingmanagerlov=null;
    CommonLOV[] nature_of_employment=null;
    CommonLOV[] employee_category=null;
    CommonLOV[] staff=null;
    CommonLOV[] projectom=null;
    CommonLOV[] hrstatuslov=null;
    GradeStepByGradeId[] gradesteplov=null;
    PaygroupLOV[] paygroupLOV=null;
    LocationSearchResult[] locationLOV=null;
    CommonLOV[] saltypeLOV=null;
    CommonLOV[] freqLOV=null;

    
    
    public Lovbinding(){
        super();
    }
	
    
	public CommonLOV[] getSaltypeLOV() {
		return saltypeLOV;
	}


	public void setSaltypeLOV(CommonLOV[] saltypeLOV) {
		this.saltypeLOV = saltypeLOV;
	}


	public CommonLOV[] getFreqLOV() {
		return freqLOV;
	}


	public void setFreqLOV(CommonLOV[] freqLOV) {
		this.freqLOV = freqLOV;
	}


	public LocationSearchResult[] getLocationLOV() {
		return locationLOV;
	}


	public void setLocationLOV(LocationSearchResult[] locationLOV) {
		this.locationLOV = locationLOV;
	}


	public PaygroupLOV[] getPaygroupLOV() {
		return paygroupLOV;
	}




	public void setPaygroupLOV(PaygroupLOV[] paygroupLOV) {
		this.paygroupLOV = paygroupLOV;
	}




	public GradeStepByGradeId[] getGradesteplov() {
		return gradesteplov;
	}



	public void setGradesteplov(GradeStepByGradeId[] gradesteplov) {
		this.gradesteplov = gradesteplov;
	}



	public CommonLOV[] getHrstatuslov() {
		return hrstatuslov;
	}



	public void setHrstatuslov(CommonLOV[] hrstatuslov) {
		this.hrstatuslov = hrstatuslov;
	}



	
	
	
	public CommonLOV[] getNature_of_employment() {
		return nature_of_employment;
	}



	public void setNature_of_employment(CommonLOV[] nature_of_employment) {
		this.nature_of_employment = nature_of_employment;
	}



	public CommonLOV[] getEmployee_category() {
		return employee_category;
	}



	public void setEmployee_category(CommonLOV[] employee_category) {
		this.employee_category = employee_category;
	}



	public CommonLOV[] getStaff() {
		return staff;
	}



	public void setStaff(CommonLOV[] staff) {
		this.staff = staff;
	}



	public CommonLOV[] getProjectom() {
		return projectom;
	}



	public void setProjectom(CommonLOV[] projectom) {
		this.projectom = projectom;
	}



	public CommonLOV[] getWorkingmanagerlov() {
		return workingmanagerlov;
	}



	public void setWorkingmanagerlov(CommonLOV[] workingmanagerlov) {
		this.workingmanagerlov = workingmanagerlov;
	}



	public CommonLOV[] getHirereasonlov() {
		return hirereasonlov;
	}



	public void setHirereasonlov(CommonLOV[] hirereasonlov) {
		this.hirereasonlov = hirereasonlov;
	}



	public CommonLOV[] getStatelov() {
		return statelov;
	}



	public void setStatelov(CommonLOV[] statelov) {
		this.statelov = statelov;
	}



	public CommonLOV[] getManagertypelov() {
		return managertypelov;
	}


	public void setManagertypelov(CommonLOV[] managertypelov) {
		this.managertypelov = managertypelov;
	}


	public CommonLOV[] getEmailtypelov() {
		return emailtypelov;
	}



	public void setEmailtypelov(CommonLOV[] emailtypelov) {
		this.emailtypelov = emailtypelov;
	}



	public CommonLOV[] getCntrycode() {
		return cntrycode;
	}


	public void setCntrycode(CommonLOV[] cntrycode) {
		this.cntrycode = cntrycode;
	}


	public CommonLOV[] getPayrollnamelov() {
		return payrollnamelov;
	}




	public void setPayrollnamelov(CommonLOV[] payrollnamelov) {
		this.payrollnamelov = payrollnamelov;
	}




	public CommonLOV[] getNoticelov() {
		return noticelov;
	}




	public void setNoticelov(CommonLOV[] noticelov) {
		this.noticelov = noticelov;
	}




	public CommonLOV[] getProbationlov() {
		return probationlov;
	}




	public void setProbationlov(CommonLOV[] probationlov) {
		this.probationlov = probationlov;
	}




	public CommonLOV[] getFullpartlov() {
		return fullpartlov;
	}




	public void setFullpartlov(CommonLOV[] fullpartlov) {
		this.fullpartlov = fullpartlov;
	}




	public CommonLOV[] getRegulartemplov() {
		return regulartemplov;
	}


	public void setRegulartemplov(CommonLOV[] regulartemplov) {
		this.regulartemplov = regulartemplov;
	}

	public CommonLOV[] getAssignmentcatlov() {
		return assignmentcatlov;
	}




	public void setAssignmentcatlov(CommonLOV[] assignmentcatlov) {
		this.assignmentcatlov = assignmentcatlov;
	}




	public CommonLOV[] getWorkercatlov() {
		return workercatlov;
	}



	public void setWorkercatlov(CommonLOV[] workercatlov) {
		this.workercatlov = workercatlov;
	}



	public CommonLOV[] getPersontypelov() {
		return persontypelov;
	}



	public void setPersontypelov(CommonLOV[] persontypelov) {
		this.persontypelov = persontypelov;
	}



	public CommonLOV[] getAssignmentstatuslov() {
		return assignmentstatuslov;
	}



	public void setAssignmentstatuslov(CommonLOV[] assignmentstatuslov) {
		this.assignmentstatuslov = assignmentstatuslov;
	}



	public CommonLOV[] getPhonetypelov() {
		return phonetypelov;
	}



	public void setPhonetypelov(CommonLOV[] phonetypelov) {
		this.phonetypelov = phonetypelov;
	}



	public CommonLOV[] getMaritalstatuslov() {
		return Maritalstatuslov;
	}


	public void setMaritalstatuslov(CommonLOV[] maritalstatuslov) {
		Maritalstatuslov = maritalstatuslov;
	}


	public CommonLOV[] getReligionlov() {
		return religionlov;
	}
	
	public void setReligionlov(CommonLOV[] religionlov) {
		this.religionlov = religionlov;
	}

	public CommonLOV[] getLegalentitylov() {
		return legalentitylov;
	}


	public void setLegalentitylov(CommonLOV[] legalentitylov) {
		this.legalentitylov = legalentitylov;
	}


	public CommonLOV[] getHireactionlov() {
		return hireactionlov;
	}

	public void setHireactionlov(CommonLOV[] hireactionlov) {
		this.hireactionlov = hireactionlov;
	}

	public CommonLOV[] getGenderlov() {
		return genderlov;
	}


	public void setGenderlov(CommonLOV[] genderlov) {
		this.genderlov = genderlov;
	}

	
	
	public CommonLOV[] getWorkertypelov() {
		return workertypelov;
	}


	public void setWorkertypelov(CommonLOV[] workertypelov) {
		this.workertypelov = workertypelov;
	}


	public CommonLOV[] getTitleLov() {
        return titleLov;
    }

    public void setTitleLov(CommonLOV[] titleLov) {
        this.titleLov = titleLov;
    }

   

    public CommonLOV[] getCountrylov() {
		return countrylov;
	}




	public void setCountrylov(CommonLOV[] countrylov) {
		this.countrylov = countrylov;
	}




	public CommonLOV[] getNationaltypelov() {
        return nationaltypelov;
    }

    public void setNationaltypelov(CommonLOV[] nationaltypelov) {
        this.nationaltypelov = nationaltypelov;
    }

    
    
}