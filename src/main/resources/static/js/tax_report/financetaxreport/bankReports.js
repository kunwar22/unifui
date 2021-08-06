var report_type = "";
var btn_name = "";
var bus_id = "";
var bus_name = "";
var paygroupid = "";
var cal = "";
var calper = "";
var calper1 = "";
var fromcal = "";
var tocal = "";
var natureemp = "";
var natemp = "";
var cal_code = "";
var tocal_code = "";
var banktype = "";

var myVar;

function myFunction1() {
			//debugger;
			document.getElementById("REPORTS_LOADER").style.display = "block";
  			myVar = setTimeout(showButton, 3000);
		}

		function myFunction() {
			//debugger;
			document.getElementById("REPORTS_LOADER").style.display = "block";
  			myVar = setTimeout(showPage, 3000);
		}

function showButton() {
		//debugger;
			  document.getElementById("REPORTS_LOADER").style.display = "none";
			  if (report_type == 11) {
                     btn_name = "btnexportempsaltrans";
			} else if (report_type == 12) {
            btn_name = "btnexportbankadvice";
        }
			enable_export_btn(btn_name);
}
	function showPage() {
		//debugger;
			  document.getElementById("REPORTS_LOADER").style.display = "none";
			  document.getElementById("myDiv").style.display = "block";
}

$(document).ready(function()
{
	//debugger;
	myFunction();
});


function removepaygroup() {
    //debugger;    
    $("#btnexportempsaltrans").css("display", "none");
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");
    $("#txtmsg").css("display", "none");   
    $("#paygrp").val("");
    $("#fromcalcode").val("");
    $("#tocalcode").val("");
    $("#natemp").val("");
    $("#banktype").val("");
    $("#buerr").css("display", "none");
    $("#pgerr").css("display", "none");
    $("#frmcalcodeerr").css("display", "none");
    $("#tocalcodeerr").css("display", "none");
    $("#noeerr").css("display", "none");
    $("#bnktypeerr").css("display", "none");
    $("#frmdterr").css("display", "none");
    $("#todterr").css("display", "none");    

    var select_bu = $("#busid").val();
    var paygrp = $("#paygrp");
    if (select_bu == 20001) {
        paygrp.find("option[value=2]").css("display", "block");
        paygrp.find("option[value=3]").css("display", "none");
        paygrp.find("option[value=4]").css("display", "none");
    } else if (select_bu == 20002) {
        paygrp.find("option[value=2]").css("display", "none");
        paygrp.find("option[value=3]").css("display", "none");
        paygrp.find("option[value=4]").css("display", "block");
    } else if (select_bu == 20003) {
        paygrp.find("option[value=2]").css("display", "none");
        paygrp.find("option[value=3]").css("display", "block");
        paygrp.find("option[value=4]").css("display", "none");
    }
}

/* LOADING Calendar on basis of PAY GROUP STARTS*/
function loadCalendars(e, pgrpid) {
    //debugger;
    var caldata = "";
    if (report_type == 18 || report_type == 19 || report_type == 20) {
        //var stcalcode = $("#staticcalcode");
        var paygrp = $("#paygrp").val();

        if (paygrp == 2) {
            caldata = '<option disabled selected></option><option value="44" calper="2">LPY-MAY-2021</option>';
            $("#staticcalcode").html(caldata);
        } else if (paygrp == 3) {
            caldata = '<option disabled selected></option><option value="83" calper="2">APY-MAY-2021</option>';
            $("#staticcalcode").html(caldata);
        } else if (paygrp == 4) {
            caldata = '<option disabled selected></option><option value="79" calper="2">KPY-MAY-2021</option>';
            $("#staticcalcode").html(caldata);
        }
    } else {
        var calurl = "/finreports/getCalendars/" + pgrpid;
        //	console.log("URL for loading Calendars ==> "+calurl);
        $.ajax({
            type: "GET",
            url: calurl,
            dataSrc: "",
            dataType: "json",
            success: function (data) {
                var dataLength = data.length;
                if (dataLength != 0) {
                    //debugger;
                    caldata += "<option selected disabled></option>";
                    for (var i = 0; i < dataLength; i++) {
                        caldata += '<option value="' + data[i].calendarid + '" cal_code="' + data[i].calendarcode + '" calper="' + data[i].calendarperiod + '">' + data[i].calendarcode + "</option>";
                    }
                    if (report_type == 7) {
                        $("#fromcalcode").html(caldata);
                        $("#tocalcode").html(caldata);
                    } else {
                        $("#fromcalcode").html(caldata);
                    }
                }
            },
        });

        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#tocalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");
        $("#bnktypeerr").css("display", "none");
        $("#frmdterr").css("display", "none");
        $("#todterr").css("display", "none");
        $("#vendorerr").css("display", "none");
        $("#mntherr").css("display", "none");
        $("#pernoerr").css("display", "none");
    }
}

/* LOADING Calendar on basis of PAY GROUP ENDS*/

function enabledisable(id) {
    //debugger;
    $("#natemp").val("");
    $("#banktype").val("");
    $("#btnexportempsaltrans").css("display", "none");
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");   
    $("#buerr").css("display", "none");
    $("#pgerr").css("display", "none");
    $("#frmcalcodeerr").css("display", "none");
    $("#noeerr").css("display", "none");
    $("#bnktypeerr").css("display", "none");
    $("#frmdterr").css("display", "none");
    $("#todterr").css("display", "none");
}


function searchPayroll() {
    //debugger;
    report_type = $("#reptype").val();
    bus_id = parseInt($("#busid").val());
    bus_name = $("#busid option:selected").text();
    paygroupid = $("#paygrp").val();
    fromcal = $("#fromcalcode").val();
    tocal = $("#tocalcode").val();
    cal_code = $("#fromcalcode").children("option:selected").attr("cal_code");
    tocal_code = $("#tocalcode").children("option:selected").attr("cal_code");
    calper = $("#fromcalcode").children("option:selected").attr("calper");
    calper1 = $("#staticcalcode").children("option:selected").attr("calper");
    natureemp = $("#natemp").val();
    natemp = $("#natemp").children("option:selected").text();
    per_no = $("#personno").val();    
    banktype = $("#banktype").val();

    if (validateForm(report_type) == false) {
        bootbox.alert({
            size: "medium",
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
            message: "You cannot fetch the details without selecting required fields!!! <br/>Please try again after selecting necessary fields",
        });
    } else {
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#tocalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");
        $("#frmdterr").css("display", "none");
        $("#todterr").css("display", "none");
        $("#bnktypeerr").css("display", "none");

       if (report_type == 11) {
	myFunction1();
} else if (report_type == 12) {
            myFunction1();
        } 
    }
}

/* ================= Enable-Disable Functions Starts ================ */

function enbledisble(id) {
    //debugger;    
    $("#btnexportempsaltrans").css("display", "none");
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");
    
    report_type = $(id).val();
    if (report_type != null) {
        $("#rterr").css("display", "none");
    }
    $("#buerr").css("display", "none");
    $("#pgerr").css("display", "none");
    $("#frmcalcodeerr").css("display", "none");
    $("#noeerr").css("display", "none");
    $("#bnktypeerr").css("display", "none");
    $("#frmdterr").css("display", "none");
    $("#todterr").css("display", "none");
    
	if (report_type == 11) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");		
		$("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#banktypeblock").css("display", "block");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#banktype").val("");
    } else if (report_type == 12) {
        $("#payroll_div").html("");
        $("#lftpane").css("margin-top", "-10px");
        $("#busunitblock").css("display", "block");
        $("#paygrpblock").css("display", "block");
        $("#fromcalcblock").css("display", "block");
        $("#tocalcblock").css("display", "none");
        $("#natureempblock").css("display", "none");
        $("#banktypeblock").css("display", "block");
        $("#busid").val("");
        $("#paygrp").val("");
        $("#fromcalcode").val("");
        $("#tocalcode").val("");
        $("#natemp").val("");
        $("#personno").val("");
        $("#banktype").val("");
    } 
}

function disablebtn() {
    //debugger;  
    $("#btnexportempsaltrans").css("display", "none");       
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");
    $("#buerr").css("display", "none");
    $("#pgerr").css("display", "none");
    $("#frmcalcodeerr").css("display", "none");
    $("#noeerr").css("display", "none");
    $("#bnktypeerr").css("display", "none");
    $("#frmdterr").css("display", "none");
    $("#todterr").css("display", "none");
    
}

/* code for enabling export buttons starts */
function enable_export_btn(btn) {
    //debugger;

 if (btn == "btnexportempsaltrans") {
       
        $("#" + btn).css("display", "block");
        $("#btnexportbankadvice").css("display", "none");
        $("#btnexportbankrembadvice").css("display", "none");        
    } else if (btn == "btnexportbankadvice") {        
        $("#btnexportempsaltrans").css("display", "none");
        $("#" + btn).css("display", "block");
        $("#btnexportbankrembadvice").css("display", "none");        
    } else if (btn == "btnexportbankrembadvice") {
        $("#btnexportempsaltrans").css("display", "none");
        $("#btnexportbankadvice").css("display", "none");
        $("#" + btn).css("display", "block");        
    } 
}

/* code for enabling export buttons ends */

/* ================= Enable-Disable Functions Ends ================ */

/* ================= Validation Starts =================== */

/* VALIDATION OF FORM STARTS 02-04-2021 */

function validateForm(report_type) {
    ////debugger;
    if (report_type == "" || report_type == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");
        return false;
    }
    switch (report_type) {        
        case "11":
        case "12":
        case "13":
            if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype == null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype != null) {
                $("#buerr").css("display", "block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && banktype == null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (Number.isNaN(bus_id) && paygroupid != null && fromcal != null && banktype != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype == null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal == null && banktype != null) {
                $("#buerr").css("display", "block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype == null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid == null && fromcal != null && banktype != null) {
                //$("#buerr").css("display","block");
                $("#pgerr").css("display", "block");
                //$("#frmcalcodeerr").css("display","block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                $("#bnktypeerr").css("display", "block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal == null && banktype != null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                $("#frmcalcodeerr").css("display", "block");
                //$("#bnktypeerr").css("display","block");
                return false;
            } else if (!Number.isNaN(bus_id) && paygroupid != null && fromcal != null && banktype == null) {
                //$("#buerr").css("display","block");
                //$("#pgerr").css("display","block");
                //$("#frmcalcodeerr").css("display","block");
                $("#bnktypeerr").css("display", "block");
                return false;
            }
            break;        
    }
}

/* ================== Validation Ends ================= */

/* ================= Excel Functions Starts ================== */


/* Jasper Report for LWPReport ends */

function loadEmpSalarydetails() {
    //debugger;
    /*var bus_id = $("#busid").val();
	var bus_name = $("#busid option:selected").text();*/

    report_url = "/finreports/bankwiseexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + banktype + "/XLSX";

    var ab = window.open(report_url);

    setTimeout(function () {
        ab.close();
    }, 720000);
}

function loadBankwiseempdetails() {
    //debugger;

    report_url = "/finreports/bankadviceexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + fromcal + "/" + cal_code + "/" + banktype;

    var ab = window.open(report_url);
    setTimeout(function () {
        ab.close();
    }, 1000000);
}

function loadEmpRembdetails() {
    //debugger;
    /*var bus_id = $("#busid").val();
	var bus_name = $("#busid option:selected").text();*/

    report_url = "/mod/bankadviceempremb/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + fstdate + "/" + feddate + "/" + banktype;

    window.open(report_url);
}

/* ================= Excel Functions Ends ================== */

