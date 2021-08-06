package in.co.srdt.unif.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationGateway {
    @Value("${in.co.srdt.unif.zoolserver}")
    private String appgateway;
    
    @Value("${in.co.srdt.unif.zoolservernavbar}")
    private String appgatewaynbr;
    
    @Value("${in.co.srdt.unif.zoolserverabs}") 
    private String appgatewayabs;

    @Value("${in.co.srdt.unif.zoolserveradons}")
    private String appgatewaypyrl_sandhya;

    @Value("${in.co.srdt.unif.zoolserver_payroll}")
    private String appgateway_payroll;

    @Value("${in.co.srdt.unif.zoolserverebspyrlprcs}")
    private String appgatewayebspyrlprcs;
    
    
    @Value("${in.co.srdt.unif.payrollmod3InputFilePath}")
    private String payrollmod3InputFilePath;

    @Value("${in.co.srdt.unif.payrollmod3OutputFilePath}")
    private String payrollmod3OutputFilePath;
    
    @Value("${in.co.srdt.unif.payrollregInputFilePath}")
    private String payrollregInputFilePath;

    @Value("${in.co.srdt.unif.payrollregOutputFilePath}")
    private String payrollregOutputFilePath;
    
    @Value("${in.co.srdt.unif.employeedataInputFilePath}")
    private String employeedataInputFilePath;

    @Value("${in.co.srdt.unif.employeedataOutputFilePath}")
    private String employeedataOutputFilePath;
    
    @Value("${in.co.srdt.unif.employeemodInputFilePath}")
    private String employeemodInputFilePath;

    @Value("${in.co.srdt.unif.employeemodOutputFilePath}")
    private String employeemodOutputFilePath;
    
    @Value("${in.co.srdt.unif.bankmodInputFilePath}")
    private String bankmodInputFilePath;

    @Value("${in.co.srdt.unif.bankmodOutputFilePath}")
    private String bankmodOutputFilePath;
    
    @Value("${in.co.srdt.unif.birthdayInputFilePath}")
    private String birthdayInputFilePath;

    @Value("${in.co.srdt.unif.birthdayOutputFilePath}")
    private String birthdayOutputFilePath;
    
    @Value("${in.co.srdt.unif.travelInputFilePath}")
    private String travelInputFilePath;

    @Value("${in.co.srdt.unif.travelOutputFilePath}")
    private String travelOutputFilePath;
    
    

//  ========= REPORT URLS ===============  //
    
  @Value("${in.co.srdt.unif.salbillURL}")
  private String salbillURL;
  
  @Value("${in.co.srdt.unif.unfinalizedsalbillURL}")
  private String unfinalizedsalbillURL;
  
  @Value("${in.co.srdt.unif.salcardURL}")
  private String salcardURL;
  
  @Value("${in.co.srdt.unif.lwpURL}")
  private String lwpURL;
  
  @Value("${in.co.srdt.unif.payrollMODUrl}")
  private String payrollMODUrl;
  
  @Value("${in.co.srdt.unif.payslipAllUrl}")
  private String payslipAllUrl;
  
  @Value("${in.co.srdt.unif.payslipUrl}")
  private String payslipUrl;

public String getAppgateway() {
	return appgateway;
}

public void setAppgateway(String appgateway) {
	this.appgateway = appgateway;
}

public String getAppgatewaynbr() {
	return appgatewaynbr;
}

public void setAppgatewaynbr(String appgatewaynbr) {
	this.appgatewaynbr = appgatewaynbr;
}

public String getAppgatewayabs() {
	return appgatewayabs;
}

public void setAppgatewayabs(String appgatewayabs) {
	this.appgatewayabs = appgatewayabs;
}

public String getAppgatewaypyrl_sandhya() {
	return appgatewaypyrl_sandhya;
}

public void setAppgatewaypyrl_sandhya(String appgatewaypyrl_sandhya) {
	this.appgatewaypyrl_sandhya = appgatewaypyrl_sandhya;
}

public String getAppgateway_payroll() {
	return appgateway_payroll;
}

public void setAppgateway_payroll(String appgateway_payroll) {
	this.appgateway_payroll = appgateway_payroll;
}

public String getAppgatewayebspyrlprcs() {
	return appgatewayebspyrlprcs;
}

public void setAppgatewayebspyrlprcs(String appgatewayebspyrlprcs) {
	this.appgatewayebspyrlprcs = appgatewayebspyrlprcs;
}

public String getPayrollmod3InputFilePath() {
	return payrollmod3InputFilePath;
}

public void setPayrollmod3InputFilePath(String payrollmod3InputFilePath) {
	this.payrollmod3InputFilePath = payrollmod3InputFilePath;
}

public String getPayrollmod3OutputFilePath() {
	return payrollmod3OutputFilePath;
}

public void setPayrollmod3OutputFilePath(String payrollmod3OutputFilePath) {
	this.payrollmod3OutputFilePath = payrollmod3OutputFilePath;
}

public String getPayrollregInputFilePath() {
	return payrollregInputFilePath;
}

public void setPayrollregInputFilePath(String payrollregInputFilePath) {
	this.payrollregInputFilePath = payrollregInputFilePath;
}

public String getPayrollregOutputFilePath() {
	return payrollregOutputFilePath;
}

public void setPayrollregOutputFilePath(String payrollregOutputFilePath) {
	this.payrollregOutputFilePath = payrollregOutputFilePath;
}

public String getEmployeedataInputFilePath() {
	return employeedataInputFilePath;
}

public void setEmployeedataInputFilePath(String employeedataInputFilePath) {
	this.employeedataInputFilePath = employeedataInputFilePath;
}

public String getEmployeedataOutputFilePath() {
	return employeedataOutputFilePath;
}

public void setEmployeedataOutputFilePath(String employeedataOutputFilePath) {
	this.employeedataOutputFilePath = employeedataOutputFilePath;
}

public String getEmployeemodInputFilePath() {
	return employeemodInputFilePath;
}

public void setEmployeemodInputFilePath(String employeemodInputFilePath) {
	this.employeemodInputFilePath = employeemodInputFilePath;
}

public String getEmployeemodOutputFilePath() {
	return employeemodOutputFilePath;
}

public void setEmployeemodOutputFilePath(String employeemodOutputFilePath) {
	this.employeemodOutputFilePath = employeemodOutputFilePath;
}

public String getBankmodInputFilePath() {
	return bankmodInputFilePath;
}

public void setBankmodInputFilePath(String bankmodInputFilePath) {
	this.bankmodInputFilePath = bankmodInputFilePath;
}

public String getBankmodOutputFilePath() {
	return bankmodOutputFilePath;
}

public void setBankmodOutputFilePath(String bankmodOutputFilePath) {
	this.bankmodOutputFilePath = bankmodOutputFilePath;
}

public String getBirthdayInputFilePath() {
	return birthdayInputFilePath;
}

public void setBirthdayInputFilePath(String birthdayInputFilePath) {
	this.birthdayInputFilePath = birthdayInputFilePath;
}

public String getBirthdayOutputFilePath() {
	return birthdayOutputFilePath;
}

public void setBirthdayOutputFilePath(String birthdayOutputFilePath) {
	this.birthdayOutputFilePath = birthdayOutputFilePath;
}

public String getSalbillURL() {
	return salbillURL;
}

public void setSalbillURL(String salbillURL) {
	this.salbillURL = salbillURL;
}

public String getUnfinalizedsalbillURL() {
	return unfinalizedsalbillURL;
}

public void setUnfinalizedsalbillURL(String unfinalizedsalbillURL) {
	this.unfinalizedsalbillURL = unfinalizedsalbillURL;
}

public String getSalcardURL() {
	return salcardURL;
}

public void setSalcardURL(String salcardURL) {
	this.salcardURL = salcardURL;
}

public String getLwpURL() {
	return lwpURL;
}

public void setLwpURL(String lwpURL) {
	this.lwpURL = lwpURL;
}

public String getPayrollMODUrl() {
	return payrollMODUrl;
}

public void setPayrollMODUrl(String payrollMODUrl) {
	this.payrollMODUrl = payrollMODUrl;
}

public String getPayslipAllUrl() {
	return payslipAllUrl;
}

public void setPayslipAllUrl(String payslipAllUrl) {
	this.payslipAllUrl = payslipAllUrl;
}

public String getPayslipUrl() {
	return payslipUrl;
}

public void setPayslipUrl(String payslipUrl) {
	this.payslipUrl = payslipUrl;
}

public String getTravelInputFilePath() {
	return travelInputFilePath;
}

public void setTravelInputFilePath(String travelInputFilePath) {
	this.travelInputFilePath = travelInputFilePath;
}

public String getTravelOutputFilePath() {
	return travelOutputFilePath;
}

public void setTravelOutputFilePath(String travelOutputFilePath) {
	this.travelOutputFilePath = travelOutputFilePath;
}
  

//========= REPORT URLS ===============  //
  
	
}