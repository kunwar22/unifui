var tab = '';

var fin_year = '';
var groupId = '';
var buid='';

var myVar;

		function myFunction() {
			//debugger;
			document.getElementById("REPORTS_LOADER").style.display = "block";
  			myVar = setTimeout(showPage, 3000);
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

/********************************************************************************************************/

/*$('#buid').on('change', function(e){
//debugger;
var select_bu = $("#buid option:selected").val();
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
});*/

$("#medicalSearch").on('click', function(e){

  fin_year = $("#Financial_year option:selected").text();
  groupId = $("#paygrp option:selected").val();
  buid = $("#buid option:selected").val();

 

if(fin_year ==''|| fin_year ==""||fin_year ==null) {
	
	bootbox.alert({
		size: 'medium',
		title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
		message: "Please select Financial year."
	});
	return;
}
else if(groupId ==''|| groupId ==""||groupId ==null) {
	
	bootbox.alert({
		size: 'medium',
		title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
		message: "Please select Paygrop."
	});
	return;
}
else if(buid ==''|| buid ==""||buid ==null) {
	
	bootbox.alert({
		size: 'medium',
		title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
		message: "Please select Business unit."
	});
	return;
}
 else {
			medicalSearch(fin_year, groupId,buid)			
	  }
});	

function medicalSearch(x, y,z) {
	debugger
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/finreports/manage/medicalreport/" + x + "/" + y+"/"+z,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			//alert(typeof(JSON.parse(result)));
			populatemedicalDataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
			$('#resultSec_medical').css('display', 'none');
	        $('#noData_medical').css('display', 'block');
	        $('#jsonLoaderPage_medical').css('display', 'none');
			$('#btnexport_medical').css('display', 'none');
		}
	});
	
}



function populatemedicalDataTable(data) {
	//debugger;
    $("#medical_REPORT").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultSec_medical').css('display', 'none');
        $('#noData_medical').css('display', 'block');
        $('#jsonLoaderPage_medical').css('display', 'none');
        $('#btnexport_medical').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#medical_REPORT").dataTable().fnAddData([
                dat.person_nbr,
 				dat.attribute1,
			    moment(dat.request_date).format('DD-MM-YYYY'),
                moment(dat.apprival_date).format('DD-MM-YYYY'),               
                dat.claimed_amt,
                dat.approved_amt,
               
            ]);
        }
        $('#resultSec_medical').css('display', 'block');
        $('#noData_medical').css('display', 'none');
        $('#jsonLoaderPage_medical').css('display', 'none');
        $('#btnexport_medical').css('display', 'block');
    }
}


/* code for exporting to excel starts */

function export_medicalTableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

/* code for exporting to excel ends */


$(document).ready(function() {
	//debugger;
	
$('#medical_REPORT').dataTable({
    "bPaginate": false,
    "bLengthChange": false,
    "bFilter": true,
    "bInfo": false,
    "bAutoWidth": false });


	
})