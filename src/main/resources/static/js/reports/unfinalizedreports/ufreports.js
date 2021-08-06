var report_type = "";
var per_no = "";
var btn_name = "";
var bus_id = "";
var bus_name = "";
var mnth = "";
var paygroupid = "";
var cal = "";
var calper = "";
var calper1 = "";
var staticcal = "";
var fromcal = "";
var tocal = "";
var fstdate = "";
var feddate = "";
var tstdate = "";
var teddate = "";
var fdt = "";
var tdt = "";
var natureemp = "";
var natemp = "";
var vendormonth = "";
var cal_code = "";
var tocal_code = "";
var banktype = "";
var i, x, tablinks;

var myVar;

function myFunction() {
    //////debugger;;
    document.getElementById("REPORTS_LOADER").style.display = "block";
    myVar = setTimeout(showButton, 3000);
}

function showButton() {
    ////debugger;;
    document.getElementById("REPORTS_LOADER").style.display = "none";
	if (report_type == 14) {
        btn_name = "btnexportepfreport";
    } else if (report_type == 15) {
        btn_name = "btnexportnpsreport";
    } else if (report_type == 17) {
        btn_name = "btnexportvendordet";
    }
    enable_export_btn(btn_name);
}

function openTab(evt, rightpane) {
   ////debugger;;

    x = document.getElementsByClassName("rightpane");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
    }
    document.getElementById(rightpane).style.display = "block";
    evt.currentTarget.className += " w3-theme";
}
function removepaygroup() {
   ////debugger;;
    $("#btnexportpayregmod").css("display", "none");
    $("#btnexportpaymod3").css("display", "none");
    $("#btnexportpaymod3pdf").css("display", "none");
    $("#btnexportsalbill").css("display", "none");
    $("#btnexportsalbillpdf").css("display", "none");
    $("#btnexportempmod").css("display", "none");
    $("#btnexportempperexcel").css("display", "none");
    $("#btnexportbankmod").css("display", "none");
    $("#btnexportbankpdf").css("display", "none");
    $("#btnufexportsalcard").css("display", "none");
    $("#btnexportpayrollregister").css("display", "none");	
    $("#btnexportLWPReportxls").css("display", "none");
    $("#btnexportLWPReportpdf").css("display", "none");
    $("#btnexportbirthdayexcel").css("display", "none");
    $("#btnexportbirthdaypdf").css("display", "none");
    $("#btnexportempsaltrans").css("display", "none");
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");
    $("#btnexportepfreport").css("display", "none");
    $("#btnexportnpsreport").css("display", "none");
    $("#btnexportpayslip").css("display", "none");
    $("#btnexportvendordet").css("display", "none");
	$("#btnexportcurrentpayrollregister").css("display", "none");
	$("#btnexportpayregmod_3_static").css("display", "none");
	$("#btnexportpayregmodxl_static").css("display", "none");
	$("#btnexportpayregmodpdf_static").css("display", "none");
    
    
    $("#paygrp").val("");
    $("#staticcalcode").val("");
	$("#fromcalcode").val("");
	$("#tocalcode").val("");
    $("#natemp").val("");
    $("#banktype").val("");
    $("#birthmonth").val("");
    $("#vendormonth").val("");
	$("#personno").val("");
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
   ////debugger;;
    var caldata = "";
	/*if(report_type == 18 || report_type == 19 || report_type == 20)
	{
		//var stcalcode = $("#staticcalcode");
		var paygrp = $("#paygrp").val();
		
	    if (paygrp == 2) {
	        caldata = '<option disabled selected></option><option value="44" calper="2">LPY-MAY-2021</option>' ;
			$("#staticcalcode").html(caldata);
	    } else if (paygrp == 3) {
	        caldata = '<option disabled selected></option><option value="83" calper="2">APY-MAY-2021</option>' ;
			$("#staticcalcode").html(caldata);
	    } else if (paygrp == 4) {
	        caldata = '<option disabled selected></option><option value="79" calper="2">KPY-MAY-2021</option>' ;
			$("#staticcalcode").html(caldata);
	    }
	}
	else
	{*/
		
    	var calurl = "/unfinalized/getCalendars/" + pgrpid;
    //	console.log("URL for loading Calendars ==> "+calurl);
    $.ajax({
        type: "GET",
        url: calurl,
        dataSrc: "",
        dataType: "json",
        success: function (data) {
            var dataLength = data.length;
            if (dataLength != 0) {
               ////debugger;;
                caldata += "<option selected disabled></option>";
                for (var i = 0; i < dataLength; i++) {
                    caldata += '<option value="' + data[i].calendarid + '" cal_code="' + data[i].calendarcode + '" calper="' + data[i].calendarperiod + '">' + data[i].calendarcode + "</option>";
                }
                if (report_type == 3 || report_type == 7 || report_type ==14 || report_type == 15 || report_type == 17/* || report_type == 18 || report_type == 19 || report_type == 20*/) {
                    $("#fromcalcode").html(caldata);
                    $("#tocalcode").html(caldata);
                } else {
                    $("#staticcalcode").html(caldata);
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
	//}
}

/* LOADING Calendar on basis of PAY GROUP ENDS*/

/* Getting Start Date aand End Date from Calendar Codes starts */

function getfromfirstndlastdateofmonth(id) {
   ////debugger;;
    $("#natemp").val("");
    $("#banktype").val("");
    $("#personno").val("");
    $("#btnexportpayregmod").css("display", "none");
    $("#btnexportpaymod3").css("display", "none");
    $("#btnexportpaymod3pdf").css("display", "none");
    $("#btnexportsalbill").css("display", "none");
    $("#btnexportsalbillpdf").css("display", "none");
    $("#btnexportempmod").css("display", "none");
    $("#btnexportempperexcel").css("display", "none");
    $("#btnexportbankmod").css("display", "none");
    $("#btnexportbankpdf").css("display", "none");
    $("#btnufexportsalcard").css("display", "none");
    $("#btnexportpayrollregister").css("display", "none");
    $("#btnexportLWPReportxls").css("display", "none");
    $("#btnexportLWPReportpdf").css("display", "none");
    $("#btnexportbirthdayexcel").css("display", "none");
    $("#btnexportbirthdaypdf").css("display", "none");
    $("#btnexportempsaltrans").css("display", "none");
    $("#btnexportbankadvice").css("display", "none");
    $("#btnexportbankrembadvice").css("display", "none");
    $("#btnexportepfreport").css("display", "none");
    $("#btnexportnpsreport").css("display", "none");
    $("#btnexportpayslip").css("display", "none");
    $("#btnexportvendordet").css("display", "none");
    $("#btnexportcurrentpayrollregister").css("display", "none");
    $("#btnexportpayregmod_3_static").css("display", "none");
    $("#btnexportpayregmodxl_static").css("display", "none");
	$("#btnexportpayregmodpdf_static").css("display", "none");
    
    

	$("#tocalcode").val("");
    $("#personno").val("");
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

    cal = $(id).children("option:selected").attr("cal_code");

    var year = cal.slice(-4);
    var month = cal.slice(4, 7);
    var m = "";
    var stdate = "";
    var eddate = "";
    switch (month) {
        case "JAN":
            m = "01";
            dt = "31";
            break;
        case "FEB":
            m = "02";
            dt = "29";
            break;
        case "MAR":
            m = "03";
            dt = "31";
            break;
        case "APR":
            m = "04";
            dt = "30";
            break;
        case "MAY":
            m = "05";
            dt = "31";
            break;
        case "JUN":
            m = "06";
            dt = "30";
            break;
        case "JUL":
            m = "07";
            dt = "31";
            break;
        case "AUG":
            m = "08";
            dt = "31";
            break;
        case "SEP":
            m = "09";
            dt = "30";
            break;
        case "OCT":
            m = "10";
            dt = "31";
            break;
        case "NOV":
            m = "11";
            dt = "30";
            break;
        case "DEC":
            m = "12";
            dt = "31";
            break;
    }

    stdate = year + "-" + m + "-01";
    eddate = year + "-" + m + "-" + dt;

    $("#fromcalcode").children("option:selected").attr("startdt", stdate);
    $("#fromcalcode").children("option:selected").attr("enddt", eddate);
}

function gettofirstndlastdateofmonth(id) {
   ////debugger;;
	$("#personno").val("");
    cal = $(id).children("option:selected").attr("cal_code");

    var year = cal.slice(-4);
    var month = cal.slice(4, 7);
    var m = "";
    var stdate = "";
    var eddate = "";
    switch (month) {
        case "JAN":
            m = "01";
            dt = "31";
            break;
        case "FEB":
            m = "02";
            dt = "29";
            break;
        case "MAR":
            m = "03";
            dt = "31";
            break;
        case "APR":
            m = "04";
            dt = "30";
            break;
        case "MAY":
            m = "05";
            dt = "31";
            break;
        case "JUN":
            m = "06";
            dt = "30";
            break;
        case "JUL":
            m = "07";
            dt = "31";
            break;
        case "AUG":
            m = "08";
            dt = "31";
            break;
        case "SEP":
            m = "09";
            dt = "30";
            break;
        case "OCT":
            m = "10";
            dt = "31";
            break;
        case "NOV":
            m = "11";
            dt = "30";
            break;
        case "DEC":
            m = "12";
            dt = "31";
            break;
    }

    stdate = year + "-" + m + "-01";
    eddate = year + "-" + m + "-" + dt;

    $("#tocalcode").children("option:selected").attr("startdt", stdate);
    $("#tocalcode").children("option:selected").attr("enddt", eddate);
}

/* Getting Start Date and End Date from Calendar Codes ends */

/* Getting Details of Birthday Report starts */

function openBirthdayReport() {
   ////debugger;;
    mnth = $("#birthmonth").val();
    bus_id = parseInt($("#busid").val());
    bus_name = $("#busid option:selected").text();
    if (validateForm(report_type) == false) {
        bootbox.alert({
            size: "medium",
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
            message: "You cannot fetch the details without selecting required fields!!! <br/>Please try again after selecting necessary fields",
        });
    } else {
        $("#buerr").css("display", "none");
        $("#mntherr").css("display", "none");
        $("#txtbdmsg").css("display", "block");
        enable_export_btn("btnexportbirthdayexcel");
    }
}

/* Getting Details of Birthday Report ends */

function searchPayroll() {
   ////debugger;;
    report_type = $("#reptype").val();
    bus_id = parseInt($("#busid").val());
    bus_name = $("#busid option:selected").text();
    paygroupid = $("#paygrp").val();
	staticcal = $("#staticcalcode").val();
	staticcalcode = $("#staticcalcode").children("option:selected").text();
    fromcal = $("#fromcalcode").val();
    tocal = $("#tocalcode").val();
    cal_code = $("#fromcalcode").children("option:selected").attr("cal_code");
	tocal_code= $("#tocalcode").children("option:selected").attr("cal_code");
    calper = $("#fromcalcode").children("option:selected").attr("calper");
	calper1 = $("#staticcalcode").children("option:selected").attr("calper");
    natureemp = $("#natemp").val();
    natemp = $("#natemp").children("option:selected").text();
    per_no = $("#personno").val();
    fstdate = $("#fromcalcode").children("option:selected").attr("startdt");
    feddate = $("#fromcalcode").children("option:selected").attr("enddt");

    tstdate = $("#tocalcode").children("option:selected").attr("startdt");
    teddate = $("#tocalcode").children("option:selected").attr("enddt");
    fdt = $("#fdt").val();
    tdt = $("#tdt").val();
    banktype = $("#banktype").val();
    vendormonth = $("#vendormonth").children("option:selected").text();

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
        $("#vendorerr").css("display", "none");
		$("#pernoerr").css("display", "none");

        if (report_type == 1) {
            btn_name = "btnexportpayregmod";           
            

            enable_export_btn(btn_name);
        } else if (report_type == 2) {
            
            enable_export_btn(btn_name);
        } else if (report_type == 3) {
            btn_name = "btnexportsalbillpdf";

            if (per_no != "" && natureemp != "") report_url = "/unfinalized/salarysummarybill/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + fromcal + "/" + cal_code + "/" + paygroupid + "/" + natureemp + "/"+natemp+"/" + per_no;
            else if (per_no == "" && natureemp != "") report_url = "/unfinalized/salarysummarybill/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + fromcal + "/" + cal_code + "/" + paygroupid + "/" + natureemp+"/"+natemp + "/x";
            else if (per_no != "" && natureemp == "") report_url = "/unfinalized/salarysummarybill/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + fromcal + "/" + cal_code + "/" + paygroupid + "/x/"+natemp + "/" + per_no;
            else if (per_no == "" && natureemp == "") report_url = "/unfinalized/salarysummarybill/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + fromcal + "/" + cal_code + "/" + paygroupid + "/x/"+natemp + "/x";

			$("#REPORTS_LOADER").css("display","block");
           $.ajax({
                type: "GET",
                url: report_url,
                dataSrc: "",
                success: function (data) {
                    if(data == "Success" || data == "success")
					{
						$("#REPORTS_LOADER").css("display","none");
						var filelocation = "/UnfinalizedSalaryBillSummary/UnfinalizedSalaryBillSummary_"+bus_name.substring(0, bus_name.indexOf(" "))+"_"+cal_code+"_"+natemp+".pdf";
						$("#btnexportsalbillpdf").attr("href","/getContent?location="+filelocation);
							
						enable_export_btn(btn_name);
					}
                },
                error: function (result) {
	$("#REPORTS_LOADER").css("display","none");
                    console.log("ERROR : " + JSON.stringify(result));
                },
            });

            /*$("#payroll_div").html("<div class='w3-container' style='margin-left:10px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: black;'></i></div>");
            $("#payroll_div").load(report_url);*/      
            /*
            enable_export_btn(btn_name);*/

			
			
        } else if (report_type == 4) {
            btn_name = "btnexportempmod";
           
            
            enable_export_btn(btn_name);
            
        } else if (report_type == 5) {
            btn_name = "btnexportempperexcel";
           
            
            enable_export_btn(btn_name);

            /*Getting Employees Personal Data Report Ends */
        } else if (report_type == 6) {
            btn_name = "btnexportbankmod";
            
            
            enable_export_btn(btn_name);
         
        } else if (report_type == 7) {
            //Given By pooja on 12/01/2021

            btn_name = "btnufexportsalcard";
			report_url = "/unfinalized/salarycard/"+cal_code+"/"+tocal_code+ "/" + bus_id + "/" +bus_name + "/" + paygroupid + "/" + per_no ;
			$("#REPORTS_LOADER").css("display","block");
           $.ajax({
                type: "GET",
                url: report_url,
                dataSrc: "",
                success: function (data) {
                    if(data == "Success" || data == "success")
					{
						$("#REPORTS_LOADER").css("display","none");
						//alert(data);
						
						var filelocationxl = "/SalaryCardReport/SalaryCard_"+per_no+".xls";
						var filelocationpdf = "/SalaryCardReport/SalaryCard_"+per_no+".pdf";
	
			//var filepath = filelocation+"_"+staticcalcode.substring(4)+"_"+bus_name.substring(0, bus_name.indexOf(" "))+".xls";
			
			//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
			
				$("#btnufexportsalcard").attr("href","/getContent?location="+filelocationxl);
				$("#btnufexportsalcardpdf").attr("href","/getContent?location="+filelocationpdf);
				
	
				enable_export_btn(btn_name);
					}
                },
                error: function (result) {
	$("#REPORTS_LOADER").css("display","none");
                    console.log("ERROR : " + JSON.stringify(result));
                },
            });
        } else if (report_type == 9) {
            btn_name = "btnexportpayrollregister";
            
            enable_export_btn(btn_name);

        } else if (report_type == 10) {
            btn_name = "btnexportLWPReportxls";
            report_url = "/unfinalized/lwpreport/" + fdt + "/" + tdt;
            
            enable_export_btn(btn_name);

            $.ajax({
                type: "GET",
                url: report_url,
                dataSrc: "",
                success: function (data) {
                    jsonData = data;
                    populateLwpDataTable(jsonData);
                },
                error: function (result) {
                    console.log("ERROR : " + JSON.stringify(result));
                },
            });
        } else if (report_type == 11) {
            btn_name = "btnexportempsaltrans";
            
            enable_export_btn(btn_name);

        } else if (report_type == 12) {
            btn_name = "btnexportbankadvice";
            
            enable_export_btn(btn_name);

        } else if (report_type == 13) {
            btn_name = "btnexportbankrembadvice";
            
            enable_export_btn(btn_name);

        } else if (report_type == 14) {
           myFunction();           
            $("#btnsearch").css("pointer-events", "none");
            $("#wrapper").css("cursor", "not-allowed");

        } else if (report_type == 15) {
           myFunction();           
            $("#btnsearch").css("pointer-events", "none");
            $("#wrapper").css("cursor", "not-allowed");

        } else if (report_type == 16) {
            btn_name = "btnexportpayslip";

            if (per_no != "" && natureemp != "")
                report_url = "/unfinalized/getpayslip/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + fromcal + "/" + natureemp + "/" + natemp + "/" + per_no;
            else if (per_no == "" && natureemp != "") report_url = "/unfinalized/getpayslip/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + fromcal + "/" + natureemp + "/" + natemp + "/x";
            else if (per_no != "" && natureemp == "") report_url = "/unfinalized/getpayslip/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + fromcal + "/x" + "/" + natemp + "/" + per_no;
            else if (per_no == "" && natureemp == "") report_url = "/unfinalized/getpayslip/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + paygroupid + "/" + cal_code + "/" + fromcal + "/x" + "/" + natemp + "/x";
            $("#payroll_div").html("<div class='w3-container' style='margin-left:10px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: black;'></i></div>");
            $("#payroll_div").load(report_url);
            enable_export_btn(btn_name);
        } else if (report_type == 17) {
           myFunction();           
            $("#btnsearch").css("pointer-events", "none");
            $("#wrapper").css("cursor", "not-allowed");
        }else if (report_type == 18) {
            btn_name = "btnexportcurrentpayrollregister";
			if (natureemp == "0") {
		        report_url = "/unfinalized/getufpayrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + staticcalcode + "/0/" +natemp ;
		    } else {
		        report_url = "/unfinalized/getufpayrollregister/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + staticcalcode + "/" + natureemp +"/"+natemp;
		    }
$("#REPORTS_LOADER").css("display","block");
           $.ajax({
                type: "GET",
                url: report_url,
                dataSrc: "",
                success: function (data) {
                    if(data == "Success" || data == "success")
					{
						$("#REPORTS_LOADER").css("display","none");
						//alert(data);
						

            			enable_export_btn(btn_name);
						var filelocation = "/reports/output/payrollregister/UnfinalizedPayrollRegister";
	
						var filepath = filelocation+"_"+staticcalcode.substring(4)+"_"+bus_name.substring(0, bus_name.indexOf(" "))+".xls";
						
						//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
						
						$("#btnexportcurrentpayrollregister").attr("href","/getContent?location="+filepath);
//						$("#btnexportcurrentpayrollregister").css("pointer-events","auto");
						
					}
                },
                error: function (result) {
	$("#REPORTS_LOADER").css("display","none");
                    console.log("ERROR : " + JSON.stringify(result));
                },
            });
			/*$("#btnexportcurrentpayrollregister").css("pointer-events","auto");
            
            enable_export_btn(btn_name);*/
        }
		else if (report_type == 19) {
			$("#REPORTS_LOADER").css("display","block");
			btn_name = "btnexportpayregmod_3_uf";
            report_url = "/unfinalized/getufpayroll_regexcel/" + staticcalcode + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" "));

			$.ajax({
                type: "GET",
                url: report_url,
                dataSrc: "",
                success: function (data) {
					////debugger;;
                    if(data == "Success" || data == "success")
					{
						$("#REPORTS_LOADER").css("display","none");
						//alert(data);
						

            			enable_export_btn(btn_name);
						var filelocation = "/reports/output/payrollmod3/UnfinalizedPayrollRegisterMOD3";
	
						var filepath = filelocation+"_"+staticcalcode.substring(4)+"_"+bus_name.substring(0, bus_name.indexOf(" "))+".xls";
	
						//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
		
					$("#btnexportpayregmod_3_uf").attr("href","/getContent?location="+filepath);
					}
					else
					{
						bootbox.alert({
				      size: 'medium',
				      title: '<i class="fa fa-info-circle" style="font-size:25px; color:orange;">&nbsp;&nbsp;Information</i>',
				      message: 'No Data can be found'
				    });
					}
                },
                error: function (result) {
	$("#REPORTS_LOADER").css("display","none");
                    console.log("ERROR : " + JSON.stringify(result));
                },
            });
        }
		else if (report_type == 20) {
			$("#REPORTS_LOADER").css("display","block");
            btn_name = "btnexportpayregmodxl_static";
			report_url = "/unfinalized/payrollmodsummaryexcel/" + staticcalcode + "/" + calper1 + "/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" "));
			
			$.ajax({
                type: "GET",
                url: report_url,
                dataSrc: "",
                success: function (data) {
	////debugger;;
                    if(data == "Success" || data == "success")
					{
						$("#REPORTS_LOADER").css("display","none");
						//alert(data);
						

            			enable_export_btn(btn_name);
						var filelocation = "/PayrollModReport/PayrollMOD";
	
						var xlfilepath = filelocation+"_"+bus_name.substring(0, bus_name.indexOf(" "))+"_"+staticcalcode.substring(4)+".xls";
						
						var pdffilepath = filelocation+"_"+bus_name.substring(0, bus_name.indexOf(" "))+staticcalcode.substring(4)+"_"+".pdf";
	
						//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
		
					$("#btnexportpayregmodxl_static").attr("href","/getContent?location="+xlfilepath);
					
					$("#btnexportpayregmodpdf_static").attr("href","/getContent?location="+pdffilepath);
					}
                },
                error: function (result) {
	$("#REPORTS_LOADER").css("display","none");
                    console.log("ERROR : " + JSON.stringify(result));
                },
            });
        }
    }
}


function loadSalarySummaryBill() {
   ////debugger;;
    if (per_no != "") report_url = "/unfinalized/salarysummarybillexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + fromcal + "/" + cal_code + "/" + paygroupid + "/" + per_no;
    else report_url = "/unfinalized/salarysummarybillexcel/" + bus_id + "/" + bus_name.substring(0, bus_name.indexOf(" ")) + "/" + fromcal + "/" + cal_code + "/" + paygroupid + "/x";

    window.open(report_url);
}

function exportTableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

function exportTableToExcel2(tableID, filename = "") {
   ////debugger;;
    var downloadLink;
    var dataType = "application/vnd.ms-excel";
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, "%20");

    // Specify file name
    filename = filename ? filename + ".xls" : "excel_data.xls";

    // Create download link element
    downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);

    if (navigator.msSaveOrOpenBlob) {
        var blob = new Blob(["\ufeff", tableHTML], {
            type: dataType,
        });
        navigator.msSaveOrOpenBlob(blob, filename);
    } else {
        // Create a link to the file
        downloadLink.href = "data:" + dataType + ", " + tableHTML;

        // Setting the file name
        downloadLink.download = filename;

        //triggering the function
        downloadLink.click();
    }
}

function PayslipPdf() {
   ////debugger;;
    html2canvas(document.getElementById("payslipall"), {
        onrendered: function (canvas) {
            var data = canvas.toDataURL();
            var docDefinition = {
                content: [
                    {
                        image: data,
                        width: 500,
                    },
                ],
            };
            pdfMake.createPdf(docDefinition).download("Payslip_all_employees.pdf");
        },
    });
}

function makePayslipPdf() {
   ////debugger;;
    var quotes = document.getElementById("payslipall");
    html2canvas(quotes).then((canvas) => {
        //! MAKE YOUR PDF
        var pdf = new jsPDF("p", "pt", "letter");

        for (var i = 0; i <= quotes.clientHeight / 980; i++) {
            //! This is all just html2canvas stuff
            var srcImg = canvas;
            var sX = 0;
            var sY = 1150 * i; // start 980 pixels down for every new page
            var sWidth = 920; //900
            var sHeight = 1150;
            var dX = 0;
            var dY = 0;
            var dWidth = 920;
            var dHeight = 1150;

            window.onePageCanvas = document.createElement("canvas");
            onePageCanvas.setAttribute("width", 920);
            onePageCanvas.setAttribute("height", 1150);
            var ctx = onePageCanvas.getContext("2d");
            // details on this usage of this function:
            // https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API/Tutorial/Using_images#Slicing
            ctx.drawImage(srcImg, sX, sY, sWidth, sHeight, dX, dY, dWidth, dHeight);

            // document.body.appendChild(canvas);
            var canvasDataURL = onePageCanvas.toDataURL("image/png", 1.0);

            var width = onePageCanvas.width;
            var height = onePageCanvas.clientHeight;

            //! If we're on anything other than the first page,
            // add another page
            if (i > 0) {
                pdf.addPage(612, 791); //8.5" x 11" in pts (in*72)
            }
            //! now we declare that we're working on that page
            pdf.setPage(i + 1);
            //! now we add content to that page!
            pdf.addImage(canvasDataURL, "PNG", 20, 40, width * 0.62, height * 0.62);
        }
        //! after the for loop is finished running, we save the pdf.
        pdf.save("Payslips.pdf");
    });
}

function makeSalarySummaryPdf() {
    var quotes = document.getElementById("salsummtab");
    html2canvas(quotes).then((canvas) => {
        //! MAKE YOUR PDF
        var pdf = new jsPDF("l", "pt", "a4");

        for (var i = 0; i <= quotes.clientHeight / 730; i++) {
            //! This is all just html2canvas stuff
            var srcImg = canvas;
            var sX = 0;
            var sY = 730 * i; // start 980 pixels down for every new page

            var sWidth = 1514.118;
            var sHeight = 730;

            //var sWidth  = 1020;//920
            //var sHeight = 1190;
            var dX = 0;
            var dY = 0;
            var dWidth = 1614.118;
            var dHeight = 730;

            window.onePageCanvas = document.createElement("canvas");
            onePageCanvas.setAttribute("width", 1614.118);
            onePageCanvas.setAttribute("height", 730);
            var ctx = onePageCanvas.getContext("2d");
            // details on this usage of this function:
            // https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API/Tutorial/Using_images#Slicing
            ctx.drawImage(srcImg, sX, sY, sWidth, sHeight, dX, dY, dWidth, dHeight);

            // document.body.appendChild(canvas);
            var canvasDataURL = onePageCanvas.toDataURL("image/jpg", 1.0);

            var width = onePageCanvas.width;
            var height = onePageCanvas.clientHeight;

            //! If we're on anything other than the first page,
            // add another page
            if (i > 0) {
                //pdf.addPage(595, 842); //8.27" x 11.69" in pts (in*72)
                pdf.addPage("l", "pt", "a4");
            }
            //! now we declare that we're working on that page
            pdf.setPage(i + 1);
            //! now we add content to that page!
            pdf.addImage(canvasDataURL, "jpg", 20, 40, width * 0.62, height * 0.62);
        }
        //! after the for loop is finished running, we save the pdf.
        pdf.save("SalarySummaryBill.pdf");
    });
}
