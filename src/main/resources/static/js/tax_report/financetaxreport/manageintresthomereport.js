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
$("#HomeSearch").on('click', function(e){
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
			HOME_LOAN(fin_year, groupId)			
	  }
});	

function HOME_LOAN(x, y) {
	
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/finreports/manage/HOME_LOAN/" + x + "/" + y,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			//alert(typeof(JSON.parse(result)));
			populateHOME_LOANDataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
			$('#btnexportHOMELOAN').css('display', 'none');
			$('#resultSec_HOMELOAN').css('display', 'none');
			$('#noData_HOMELOAN').css('display', 'block');
			$('#jsonLoaderPage_HOMELOAN').css('display', 'none');
		}
	});
}

function populateHOME_LOANDataTable(data) {
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
	$("#HOMELOAN_REPORT").dataTable({
		"destroy": true,
		"columns": colJson,
		"pageLength": 2000,
	});
	var dataLength = Object.keys(data).length;
	$("#HOMELOAN_REPORT").DataTable().clear();
	if(dataLength == 0) {
		$('#resultSec_HOMELOAN').css('display', 'none');
		$('#noData_HOMELOAN').css('display', 'block');
		$('#jsonLoaderPage_HOMELOAN').css('display', 'none');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#HOMELOAN_REPORT").dataTable().fnAddData([
				dat[columns[0]],
				dat[columns[1]],
				dat[columns[2]],
				dat[columns[3]],
				dat[columns[4]],
				dat[columns[5]],
				dat[columns[6]],
				dat[columns[7]],
				
				
			]);
		}
		$('#resultSec_HOMELOAN').css('display', 'block');
		$('#noData_HOMELOAN').css('display', 'none');
		$('#jsonLoaderPage_HOMELOAN').css('display', 'none');
		$('#btnexportHOMELOAN').css('display', 'block');
	}
	}
	else{
	    $('#resultSec_HOMELOAN').css('display', 'none');
		$('#noData_HOMELOAN').css('display', 'block');
		$('#jsonLoaderPage_HOMELOAN').css('display', 'none');
		$('#btnexportHOMELOAN').css('display', 'none');
	}
}

/* code for exporting to excel starts */

function exportHOMELOANTableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

/* code for exporting to excel ends */