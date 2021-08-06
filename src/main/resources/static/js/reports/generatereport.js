function downloadSalaryBillSummary()
{
	//SalaryBillSummary_Agra_APY-APR-2021_Consultant.pdf
	//debugger;	
    var bus_name = $("#busid option:selected").text();
	
	bus_name = bus_name.substring(0,bus_name.indexOf(" "));
	
	var cal_code = $("#fromcalcode").children("option:selected").attr("cal_code");
	var natemp = $("#natemp").children("option:selected").text();
	//var downloadurl = "/personedit/download/";
	// var filelocation = "/ReportSalrySlip/EmployeeDocs/LMRC_Payslips/";
	//var filelocation = "var/lib/unifui/ReportSalrySlip/EmployeeDocs/LMRC_Payslips/";
	//var filelocation = "var/lib/ReportSalrySlip/EmployeeDocs/LMRC_Payslips/";
	var filelocation = "/FinalizedSalaryBillSummary/SalaryBillSummary_";
	var filepath = '/getContent?location='+filelocation+bus_name+"_"+cal_code+"_"+natemp+".pdf";
	
	//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
	
	$("#btnexportsalbill").attr("href",filepath);
	
}

function downloadPaySlip()
{
	//debugger;
    var bus_name = $("#busid option:selected").text();
	
	bus_name = bus_name.substring(0,bus_name.indexOf(" "));
	
	var cal_code = $("#fromcalcode").children("option:selected").attr("cal_code").substring(4);
	var natemp = $("#natemp").children("option:selected").text();
	var per_no = $("#personno").val();
	
	/*var filelocation = "/PaySlip/";
	var filepath = "";
	if(personno == "")
	{
		filepath= '/getContent?location='+filelocation+"Payslip_"+bus_name+"_"+cal_code+"_"+natemp+".pdf";
	}
	else
	{
		filepath= '/getContent?location='+filelocation+personno+"_"+cal_code+".pdf";
	}
	
	/*var filelocation = "/PaySlip/SalaryBillSummary_";
	var filepath = '/getContent?location='+filelocation+bus_name+"_"+cal_code+"_"+natemp+".pdf";*/
	
	//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);*/
	
	
	var filelocation = "";
	var filepath = "";
	if(per_no == "")
	{
		//filelocation = "/payslipall/PaySlip/"
		filelocation = "/PaySlip/";
		filepath = filelocation + "Payslip_" + bus_name + "_" + cal_code + "_" + natemp + ".pdf";
	}
    else
	{
		filelocation = "/EmployeePayslipsUI/";
		filepath = filelocation + per_no + "_" + cal_code + ".pdf";
	} 
                            

    //$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);

    $("#btnexportpayslip").attr("href", "/getContent?location=" + filepath);
	
	
	/*$("#btnexportpayslip").attr("href",filepath);*/
	
}


/* FUNCTION BELOW FOR PAYROLL REGISTER REPORT 15-FEB-2021 STARTS */

function loadPayrollRegexcel() {
    //debugger;

    //report_url ="/mod/getpayroll_regexcel/"+fromcal+"/"+bus_id+"/"+bus_name.substring(0, bus_name.indexOf(" "));
    report_url = "/mod/getpayroll_regexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" "));

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 3000000);

 setTimeout(downloadmod3, 10000)

/*$("#REPORTS_LOADER").css("display","block");
 $.ajax({
        type: "GET",
        url: report_url,
        dataSrc: "",
        success: function (data) {
	//alert(JSON.stringify(data))
       $("#REPORTS_LOADER").css("display","none");     
			downloadmod3();
        },
        error: function (result) {
	  $("#REPORTS_LOADER").css("display","none");     
            console.log("ERROR : " + JSON.stringify(result));
        },
    });
 */
}

function downloadmod3()
{
	//debugger;		
	//var downloadurl = "/personedit/download/";
	 //var filelocation = "/EmployeeDocs/LMRC_Payslips/2021-05-07-16-07-08199.jpg";
	//var filelocation = "var/lib/ReportSalrySlip/EmployeeDocs/LMRC_Payslips/";
	
	var filelocation = "/reports/output/payrollmod3/PayrollRegisterMOD";
	
	var filepath = filelocation+"_"+cal_code.substring(4)+"_"+bus_name.substring(0, bus_name.indexOf(" "))+".xls";
	
	//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
	
	$("#btnexportpayregmod").attr("href","/getContent?location="+filepath);
	
}


function loadPayrollRegexcel_current_Mod3() {
    //debugger;

    //report_url ="/mod/getpayroll_regexcel/"+fromcal+"/"+bus_id+"/"+bus_name.substring(0, bus_name.indexOf(" "));
    report_url = "/mod/getpayroll_regexcel/" + staticcalcode + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/XLSX";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 3000000);
}

/* FUNCTION BELOW FOR MOD-III REPORT */
function loadModReportexcel() {
    //debugger;

    //report_url = "/mod/payrollmodsummaryexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/XLSX";
    report_url = "/mod/payrollmodsummaryexcel/" + cal_code + "/" + calper + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" "));

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 10000);
}

function loadModReportpdf() {
    //debugger;
    /*cal_code = $("#fromcalcode").children("option:selected").attr("cal_code");
	bus_id = $("#busid").val();
	bus_name = $("#busid option:selected").text();*/

    report_url = "/mod/payrollmodsummarypdf/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/PDF";

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 10000);
}

/* Employee MOD Excel */

/* FUNCTION BELOW FOR CURRENT MOD REPORT */
function loadCurrentModReportexcel() {
    //debugger;

    //report_url = "/mod/payrollmodsummaryexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/XLSX";
    report_url = "/mod/payrollmodsummaryexcel/" + staticcalcode + "/" + calper1 + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" "));

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 10000);
}

function loadCurrentModReportpdf() {
    //debugger;
    /*cal_code = $("#fromcalcode").children("option:selected").attr("cal_code");
	bus_id = $("#busid").val();
	bus_name = $("#busid option:selected").text();*/

    report_url = "/mod/payrollmodsummarypdf/" + staticcalcode + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/PDF";

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 10000);
}

/* Employee MOD Excel */

function loadEmployeeMODExcel() {
	
    report_url = "/mod/employeemodexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid;
   
	var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 30000);
}

/* FUNCTION BELOW FOR EMPLOYEE PERSONAL DATA REPORT STARTS */
function loadEmpdetails() {
    //debugger;
    report_url = "/mod/empdataexcel";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 120000);
}

/* FUNCTION ABOVE FOR EMPLOYEE PERSONAL DATA REPORT ENDS */

/* JASPER FUNCTION BELOW FOR BANK DETAILS REPORT STARTS */
function loadBankdetailspdf() {
    //debugger;
    var bus_id = $("#busid").val();
    var bus_name = $("#busid option:selected").text();

    if (natureemp == "0") {
        report_url = "/mod/bankmodpdf/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/0/" + natemp + "/" + banktype + "/PDF";
    } else {
        report_url = "/mod/bankmodpdf/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + natureemp + "/" + natemp + "/" + banktype + "/PDF";
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 30000);
}
/* JASPER FUNCTION BELOW FOR BANK DETAILS REPORT ENDS */

/* FUNCTION BELOW FOR BANK DETAILS REPORT STARTS */
function loadBankdetailsexcel() {
    //debugger;
    var bus_id = $("#busid").val();
    var bus_name = $("#busid option:selected").text();
    if (natureemp == "0") {
        report_url = "/mod/bankmodexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/0/" + natemp + "/" + banktype;
    } else {
        report_url = "/mod/bankmodexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + natureemp + "/" + natemp + "/" + banktype;
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 15000);
}

/* FUNCTION BELOW FOR BANK DETAILS REPORT ENDS */

/* FUNCTION FOR Payroll Register Report STARTS */
function loadPayrollRegister() {
    //debugger;
    //report_url = "/mod/payrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp + "/" + paygroupid;

    if (natureemp == "0") {
        report_url = "/mod/payrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/0/" +natemp ;
    } else {
        report_url = "/mod/payrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp +"/"+natemp;
    }

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 720000);

downloadPayrollRegister();
}
function downloadPayrollRegister()
{
	//debugger;	
	
	var filelocation = "/reports/output/payrollregister/PayrollRegister";
	
	var filepath = filelocation+"_"+cal_code.substring(4)+"_"+bus_name.substring(0, bus_name.indexOf(" "))+".xls";
	
	//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
	
	$("#btnexportpayrollregister").attr("href","/getContent?location="+filepath);
	
}
/* FUNCTION FOR Payroll Register Report ENDS */

/* FUNCTION FOR CURRENT Payroll Register Report STARTS */

function loadCurrentPayrollRegister() {
    //debugger;
    if(natureemp == "0")
{
	report_url = "/mod/curentpayrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + staticcal + "/"+staticcalcode+"/" + natureemp;
}
else
{
	report_url = "/mod/curentpayrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + staticcal + "/"+staticcalcode+"/" + natemp;
}
    
$("#btnexportcurrentpayrollregister").css("pointer-events","none");
    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 720000);
}

/*$.ajax({
		type: 'GET',
		url: report_url,		
		processData:false,
		catch:false,
		success: function(data){
			//debugger;
			//console.log("Data ::: "+data);
			if(data == "500")
			{
				bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Data Not Found!!"				
			});
			
			}
			else
			{
				var ab = window.open(report_url);

			    setTimeout(function () {
			        ab.close();
			    }, 720000);
			}		
		},
		error: function(e){
			alert(e);
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
});
}*/

/* FUNCTION FOR CURRENT Payroll Register Report ENDS */

/* Birthday Reports of Jasper for PDF and EXCEL STARTS */

function loadBirthdayspdf() {
    //debugger;
    /*mnth = $("#birthmonth").val();
	bus_id = parseInt($("#busid").val());
	bus_name = $("#busid option:selected").text();*/
    report_url = "/mod/loadBirthdayspdf/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + bus_id + "/" + mnth + "/PDF";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 30000);
}

function loadBirthdaysexcel() {
    //debugger;
    /*mnth = $("#birthmonth").val();
	bus_id = parseInt($("#busid").val());
	bus_name = $("#busid option:selected").text();*/
    report_url = "/mod/loadBirthdaysexcel/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + bus_id + "/" + mnth + "/XLSX";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 30000);
}

/* Birthday Reports of Jasper for PDF and Excel ends */

/* populates LWP Data Table before saving */
/*function populateLwpDataTable(data) {
    //debugger;
    $("#lwpreporttbl").DataTable().clear();
    $("#lwpreportdiv").css("display", "none");
    $("#LWP_LOADER").css("display", "none");
    $("#lwpexport").css("display", "none");
    var dataLength = data.length;
    if (dataLength == 0) {
        $("#lwpreportdiv").css("display", "none");
        $("#LWP_LOADER").css("display", "block ");
        $("#noData").css("display", "block");
        $("#lwpexport").css("display", "none");
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#lwpreporttbl")
                .dataTable({
                    paging: false,
                    destroy: true,
                    searching: true,
                })
                .fnAddData([dat.personNumber, dat.personName, dat.businessunit, dat.lwpstatus, dat.lwpdate, dat.lwpdescription]);
        }
        $("#lwpreportdiv").css("display", "block");
        $("#noData").css("display", "none");
        $("#LWP_LOADER").css("display", "none");
        $("#lwpexport").css("display", "block");
    }
}*/

/* Jasper Function for LWPReport STARTS */
function loadLwpreportpdf() {
    //debugger;
    report_url = "/mod/loadLwppdf/" + fdt + "/" + tdt + "/PDF";

    window.open(report_url);
}

function loadLwpreportexcel() {
    //debugger;
    report_url = "/mod/loadLwpxls/" + fdt + "/" + tdt;

    window.open(report_url);
}

/* Jasper Report for LWPReport ends */

function loadEmpSalarydetails() {
    //debugger;
    /*var bus_id = $("#busid").val();
	var bus_name = $("#busid option:selected").text();*/

    report_url = "/mod/bankwiseexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + banktype + "/XLSX";

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 720000);
}

function loadBankwiseempdetails() {
    //debugger;

    report_url = "/mod/bankadviceexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + fromcal + "/" + cal_code + "/" + banktype;

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 700000);
}

function loadEmpRembdetails() {
    //debugger;
    /*var bus_id = $("#busid").val();
	var bus_name = $("#busid option:selected").text();*/

    report_url = "/mod/bankadviceempremb/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + fstdate + "/" + feddate + "/" + banktype;

    window.open(report_url);
}

function loadEPFReport() {
    //debugger;
    if (natureemp == "0") {
        report_url = "/mod/getepfreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/0/" + paygroupid;
    } else {
        report_url = "/mod/getepfreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp + "/" + paygroupid;
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 600000);
}

function loadNPSReport() {
    //debugger;
    if (natureemp == "0") {
        report_url = "/mod/getnpsreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/0" + "/" + paygroupid;
    } else {
        report_url = "/mod/getnpsreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp + "/" + paygroupid;
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 600000);
}

function loadVendorData() {
    //debugger;
    report_url = "/mod/getvendordetails/" + paygroupid + "/" + cal_code;

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 500000);
}


function loadSalaryBill() {
    //debugger;
    if (per_no != "") 
		report_url = "/mod/salarycard/" + cal_code + "/" + tocal_code + "/" + bus_id + "/" +bus_name + "/" + paygroupid + "/" + per_no ;
    else 
		report_url = "/mod/salarycard/" + cal_code + "/" + tocal_code + "/" + bus_id + "/" +bus_name + "/" + paygroupid + "/0";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 720000);
}


function loadtravelreport()
{
	//debugger;
	report_url = "/mod/getTravelReport/"+bus_id+"/"+bus_name.substring(0, bus_name.indexOf(" "))+"/"+fdt+"/"+tdt;
	
	var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 720000);
}