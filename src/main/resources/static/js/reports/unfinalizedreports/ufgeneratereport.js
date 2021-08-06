/* FUNCTION BELOW FOR PAYROLL REGISTER REPORT 15-FEB-2021 STARTS */
function loadPayrollRegexcel() {
   //debugger;;

    //report_url ="/unfinalized/getpayroll_regexcel/"+fromcal+"/"+bus_id+"/"+bus_name.substring(0, bus_name.indexOf(" "));
    report_url = "/unfinalized/getpayroll_regexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/XLSX";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 3000000);
}

function loadPayrollRegexcel_current_Mod3() {
   //debugger;;

    //report_url ="/unfinalized/getpayroll_regexcel/"+fromcal+"/"+bus_id+"/"+bus_name.substring(0, bus_name.indexOf(" "));
    report_url = "/unfinalized/getpayroll_regexcel/" + staticcalcode + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/XLSX";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 3000000);
}

/* FUNCTION BELOW FOR MOD-III REPORT */
function loadModReportexcel() {
   //debugger;;

    //report_url = "/unfinalized/payrollmodsummaryexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/XLSX";
    report_url = "/unfinalized/payrollmodsummaryexcel/" + cal_code + "/" + calper + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" "));

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 10000);
}

function loadModReportpdf() {
   //debugger;;
    /*cal_code = $("#fromcalcode").children("option:selected").attr("cal_code");
	bus_id = $("#busid").val();
	bus_name = $("#busid option:selected").text();*/

    report_url = "/unfinalized/payrollmodsummarypdf/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/PDF";

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 10000);
}

/* Employee MOD Excel */

/* FUNCTION BELOW FOR CURRENT MOD REPORT */
function loadCurrentModReportexcel() {
   //debugger;;

    //report_url = "/unfinalized/payrollmodsummaryexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/XLSX";
    report_url = "/unfinalized/payrollmodsummaryexcel/" + staticcalcode + "/" + calper1 + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" "));

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 10000);
}

function loadCurrentModReportpdf() {
   //debugger;;
    /*cal_code = $("#fromcalcode").children("option:selected").attr("cal_code");
	bus_id = $("#busid").val();
	bus_name = $("#busid option:selected").text();*/

    report_url = "/unfinalized/payrollmodsummarypdf/" + staticcalcode + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/PDF";

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 10000);
}

/* Employee MOD Excel */

function loadEmployeeMODExcel() {
    if (per_no != "") report_url = "/unfinalized/employeemodexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + per_no + "/" + paygroupid;
    else report_url = "/unfinalized/employeemodexcel/" + cal_code + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/x/" + paygroupid;
    window.open(report_url);
}

/* FUNCTION BELOW FOR EMPLOYEE PERSONAL DATA REPORT STARTS */
function loadEmpdetails() {
   //debugger;;
    report_url = "/unfinalized/empdataexcel";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 120000);
}

/* FUNCTION ABOVE FOR EMPLOYEE PERSONAL DATA REPORT ENDS */

/* JASPER FUNCTION BELOW FOR BANK DETAILS REPORT STARTS */
function loadBankdetailspdf() {
   //debugger;;
    var bus_id = $("#busid").val();
    var bus_name = $("#busid option:selected").text();

    if (natureemp == "0") {
        report_url = "/unfinalized/bankmodpdf/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/0/" + natemp + "/" + banktype + "/PDF";
    } else {
        report_url = "/unfinalized/bankmodpdf/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + natureemp + "/" + natemp + "/" + banktype + "/PDF";
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 30000);
}
/* JASPER FUNCTION BELOW FOR BANK DETAILS REPORT ENDS */

/* FUNCTION BELOW FOR BANK DETAILS REPORT STARTS */
function loadBankdetailsexcel() {
   //debugger;;
    var bus_id = $("#busid").val();
    var bus_name = $("#busid option:selected").text();
    if (natureemp == "0") {
        report_url = "/unfinalized/bankmodexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/0/" + natemp + "/" + banktype;
    } else {
        report_url = "/unfinalized/bankmodexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + natureemp + "/" + natemp + "/" + banktype;
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 15000);
}

/* FUNCTION BELOW FOR BANK DETAILS REPORT ENDS */

/* FUNCTION FOR Payroll Register Report STARTS */

function loadPayrollRegister() {
   //debugger;;
    //report_url = "/unfinalized/payrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp + "/" + paygroupid;

    if (natureemp == "0") {
        report_url = "/unfinalized/payrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/0/" + paygroupid;
    } else {
        report_url = "/unfinalized/payrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp + "/" + paygroupid;
    }

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 720000);
}

/* FUNCTION FOR Payroll Register Report ENDS */

/* FUNCTION FOR CURRENT Payroll Register Report STARTS */

function loadCurrentPayrollRegister() {
   //debugger;;
    if(natureemp == "0")
{
	report_url = "/unfinalized/getufpayrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + staticcal + "/"+staticcalcode+"/" + natureemp;
}
else
{
	report_url = "/unfinalized/getufpayrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + staticcal + "/"+staticcalcode+"/" + natemp;
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
			//debugger;;
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
   //debugger;;
    /*mnth = $("#birthmonth").val();
	bus_id = parseInt($("#busid").val());
	bus_name = $("#busid option:selected").text();*/
    report_url = "/unfinalized/loadBirthdayspdf/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + bus_id + "/" + mnth + "/PDF";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 30000);
}

function loadBirthdaysexcel() {
   //debugger;;
    /*mnth = $("#birthmonth").val();
	bus_id = parseInt($("#busid").val());
	bus_name = $("#busid option:selected").text();*/
    report_url = "/unfinalized/loadBirthdaysexcel/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + bus_id + "/" + mnth + "/XLSX";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 30000);
}

/* Birthday Reports of Jasper for PDF and Excel ends */

/* populates LWP Data Table before saving */
function populateLwpDataTable(data) {
   //debugger;;
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
}

/* Jasper Function for LWPReport STARTS */
function loadLwpreportpdf() {
   //debugger;;
    report_url = "/unfinalized/loadLwppdf/" + fdt + "/" + tdt + "/PDF";

    window.open(report_url);
}

function loadLwpreportexcel() {
   //debugger;;
    report_url = "/unfinalized/loadLwpxls/" + fdt + "/" + tdt;

    window.open(report_url);
}

/* Jasper Report for LWPReport ends */

function loadEmpSalarydetails() {
   //debugger;;
    /*var bus_id = $("#busid").val();
	var bus_name = $("#busid option:selected").text();*/

    report_url = "/unfinalized/bankwiseexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + banktype + "/XLSX";

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 720000);
}

function loadBankwiseempdetails() {
   //debugger;;

    report_url = "/unfinalized/bankadviceexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + fromcal + "/" + cal_code + "/" + banktype;

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 700000);
}

function loadEmpRembdetails() {
   //debugger;;
    /*var bus_id = $("#busid").val();
	var bus_name = $("#busid option:selected").text();*/

    report_url = "/unfinalized/bankadviceempremb/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + fstdate + "/" + feddate + "/" + banktype;

    window.open(report_url);
}

function loadEPFReport() {
   //debugger;;
    if (natureemp == "0") {
        report_url = "/unfinalized/getepfreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/0/" + paygroupid;
    } else {
        report_url = "/unfinalized/getepfreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp + "/" + paygroupid;
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 600000);
}

function loadNPSReport() {
   //debugger;;
    if (natureemp == "0") {
        report_url = "/unfinalized/getnpsreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/0" + "/" + paygroupid;
    } else {
        report_url = "/unfinalized/getnpsreport/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + cal_code + "/" + natureemp + "/" + paygroupid;
    }

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 600000);
}

function loadVendorData() {
   //debugger;;
    report_url = "/unfinalized/getvendordetails/" + paygroupid + "/" + cal_code;

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 500000);
}


function loadSalaryBill() {
   //debugger;;
    if (per_no != "") 
		report_url = "/unfinalized/salarycard/" + cal_code + "/" + tocal_code + "/" + bus_id + "/" +bus_name + "/" + paygroupid + "/" + per_no ;
    else 
		report_url = "/unfinalized/salarycard/" + cal_code + "/" + tocal_code + "/" + bus_id + "/" +bus_name + "/" + paygroupid + "/0";

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 720000);
}