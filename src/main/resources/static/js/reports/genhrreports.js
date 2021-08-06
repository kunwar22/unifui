var reporttype = "";
var per_no = "";
var btn_name = "";
var bus_id = "";
var bus_name = "";
var paygroupid = "";
var cal_code = "";
var natureemp = "";
var natemp = "";


var myVar;

function myFunction() {
    //debugger;
    document.getElementById("REPORTS_LOADER").style.display = "block";
    myVar = setTimeout(showButton, 3000);
}

function showButton() {
    //debugger;
    document.getElementById("REPORTS_LOADER").style.display = "none";
	if (report_type == 5) {
		btn_name = "btnexportempperexcel";		
	} else if(report_type ==6){
		btn_name = "btnexportbankmod";
	} else  if(report_type == 8){
		btn_name = "btnexportbirthdayexcel";	
	} else if (report_type == 11) {
        btn_name = "btnexportempsaltrans";
    } else if (report_type == 12) {
        btn_name = "btnexportbankadvice";
    } else if (report_type == 13) {
        btn_name = "btnexportbankrembadvice";
    }else if (report_type == 14) {
        btn_name = "btnexportepfreport";
    } else if (report_type == 15) {
        btn_name = "btnexportnpsreport";
    } else if (report_type == 17) {
        btn_name = "btnexportvendordet";
    }
    enable_export_btn(btn_name);
}

function getData()
{
	
	reporttype = $("#reporttype").val();
    bus_id = $("#busunitid").val();
    bus_name = $("#busunitid option:selected").text();
    paygroupid = $("#paygroup").val();    
    calid = $("#calendarcode").val();
	calcode = $("#calendarcode").children("option:selected").attr("cal_code");    
    natureemp = $("#natureofemployment").val();
    natemp = $("#natureofemployment").children("option:selected").text();
    per_no = $("#personno").val();
}

function removepaygroup() {
     
    $("#paygroup").val("");
    $("#calendarcode").val("");
    $("#natureofemployment").val("");
    $("#personno").val("");
    $("#buerr").css("display", "none");
    $("#pgerr").css("display", "none");
    $("#frmcalcodeerr").css("display", "none");
    $("#noeerr").css("display", "none");    
    $("#pernoerr").css("display", "none");
    

    var select_bu = $("#busunitid").val();
    var paygrp = $("#paygroup");
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
    
    
    
    var caldata = "";
    
        var calurl = "/mod/getCalendars/" + pgrpid;
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
					$("#calendarcode").html(caldata);
                }
            }
        });

        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");       
        $("#noeerr").css("display", "none");        
        $("#pernoerr").css("display", "none");
    }

/* LOADING Calendar on basis of PAY GROUP ENDS*/

function validate()
{
	
	if (reporttype == null && bus_id == null  && paygroupid == null && calid == null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id == null  && paygroupid == null && calid == null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype == null && bus_id == null  && paygroupid == null && calid != null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id == null  && paygroupid == null && calid != null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype == null && bus_id == null  && paygroupid != null && calid == null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id == null  && paygroupid != null && calid == null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype == null && bus_id == null  && paygroupid != null && calid != null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id == null  && paygroupid != null && calid != null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype == null && bus_id != null  && paygroupid == null && calid == null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id != null  && paygroupid == null && calid == null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype == null && bus_id != null  && paygroupid == null && calid != null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id != null  && paygroupid == null && calid != null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype == null && bus_id != null  && paygroupid != null && calid == null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id != null  && paygroupid != null && calid == null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype == null && bus_id != null  && paygroupid != null && calid != null && natureemp == null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype == null && bus_id != null  && paygroupid != null && calid != null && natureemp != null) {
        $("#rterr").css("display", "block");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id == null  && paygroupid == null && calid == null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id == null  && paygroupid == null && calid == null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id == null  && paygroupid == null && calid != null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id == null  && paygroupid == null && calid != null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id == null  && paygroupid != null && calid == null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id == null  && paygroupid != null && calid == null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id == null  && paygroupid != null && calid != null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id == null  && paygroupid != null && calid != null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "block");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id != null  && paygroupid == null && calid == null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id != null  && paygroupid == null && calid == null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id != null  && paygroupid == null && calid != null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id != null  && paygroupid == null && calid != null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "block");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id != null  && paygroupid != null && calid == null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id != null  && paygroupid != null && calid == null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "block");
        $("#noeerr").css("display", "none");        
    }
else if (reporttype != null && bus_id != null  && paygroupid != null && calid != null && natureemp == null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "block");        
    }
else if (reporttype != null && bus_id != null  && paygroupid != null && calid != null && natureemp != null) {
        $("#rterr").css("display", "none");
        $("#buerr").css("display", "none");
        $("#pgerr").css("display", "none");
        $("#frmcalcodeerr").css("display", "none");
        $("#noeerr").css("display", "none");        
    }
}
function generateReport() {
    
    getData();
	validate();
	var url = "";
	
	if(per_no == "")
	{
		url = "/mod/creategenerateurl/"+reporttype+"/"+bus_id+"/"+bus_name.substring(0,bus_name.indexOf(" "))+"/"+paygroupid+"/"+calid+"/"+calcode.substring(4)+"/"+natureemp+"/"+natemp+"/x";
	}
	else
	{
		url = "/mod/creategenerateurl/"+reporttype+"/"+bus_id+"/"+bus_name.substring(0,bus_name.indexOf(" "))+"/"+paygroupid+"/"+calid+"/"+calcode.substring(4)+"/"+natureemp+"/"+natemp+"/"+per_no;
	}
	$.ajax({
            type: "GET",
            url: url,
            dataSrc: "",
            success: function (data) {
				
                //alert("DATA ::: "+data);
				$("#btngenerate").attr("href", data);
            },
            error: function (result) {
                console.log("ERROR : " + JSON.stringify(result));
            },
        });
    }


// Enable Component

function enableComponent()
{
	
	reporttype = $("#reporttype").val();
	
	$('#busunitid').val("");
	$('#paygroup').val("");
	$('#calendarcode').val("");
	$('#natureofemployment').val("");
	$('#personno').val("");
	
	if(reporttype == 2 || reporttype == 3)
	{
		$('#personnodiv').css('display','none');
	}
	else
	{
		$('#personnodiv').css('display','block');
	}
}

