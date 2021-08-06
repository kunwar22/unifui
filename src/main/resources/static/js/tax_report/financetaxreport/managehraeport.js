var tab = '';

var fin_year = '';
var groupId = '';


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
$("#HRASearch").on('click', function(e){

  fin_year = $("#Financial_year option:selected").text();
  groupId = $("#paygrp option:selected").val();

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
		message: "Please select Pay Group ."
	});
	return;
}
 else {
	
			HRA_DECLARATION_DATA(fin_year, groupId)			
	  }
});	


function HRA_DECLARATION_DATA(x, y) {

	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/finreports/manage/HRATaxReportData/" + x + "/" + y,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			populateHRADataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			//console.log("in search data...error....");
			console.log("ERROR : " + JSON.stringify(e));
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
			$('#jsonLoaderPage').css('display', 'none');
			$('#btnexportempper').css('display', 'none');
		}
	});
}

function populateHRADataTable(data) {
	//debugger;
	if(data.length!=0){
		$(`#HRA_TAX_REPORT tbody`).empty();
		
	var columns = Object.keys(data[0]);
	
	var colJson = [];
	for(var j = 0; j < columns.length; j++) {
		//console.log(columns.length);	
		colJson.push({
			title: columns[j]
		});
	}
	$("#HRA_TAX_REPORT").dataTable({
		"destroy": true,
		"columns": colJson,
		//"bPaginate": false,
		//"bLengthChange": false,
		//"bFilter": true,
		//"bInfo": false,
		"pageLength": 2000,
		//"bAutoWidth": false 
	});
	 $("#HRA_TAX_REPORT").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0) {
		$('#resultSec').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#jsonLoaderPage').css('display', 'none');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#HRA_TAX_REPORT").dataTable().fnAddData([
				dat[columns[0]],
				dat[columns[1]],
				dat[columns[2]],
				dat[columns[3]],
				dat[columns[4]],
				dat[columns[5]],
				dat[columns[6]],
				dat[columns[7]],
				dat[columns[8]],
				dat[columns[9]],
				dat[columns[10]],
				dat[columns[11]],
				dat[columns[12]]
			]);
		}
		$('#resultSec').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#jsonLoaderPage').css('display', 'none');
		$('#btnexportempper').css('display', 'block');
	}
	}
	else{
		$('#resultSec').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#jsonLoaderPage').css('display', 'none');
		$('#btnexportempper').css('display', 'none');
	}
}
/* code for exporting to excel starts */

function exportHRA_DECLARATIONTableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

/* code for exporting to excel ends */
