var tab = '';

var finans_year = '';
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
$("#VIASearch").on('click', function(e){
	
  finans_year = $("#Financial_year option:selected").text();
  groupId = $("#paygrp option:selected").val();

if(finans_year ==''|| finans_year ==""||finans_year ==null) {
	
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
			CHAPTER_VIA(finans_year, groupId)			
	  }
});	

function CHAPTER_VIA(x, y) {
	
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/finreports/manage/chapterVIAReportData/" + x + "/" + y,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			populateChapterVIADataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			//console.log("in search data...error....");
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
			$('#btnexportVIA').css('display', 'none');
		}
	});
}

function populateChapterVIADataTable(data) {
	if(data.length!=0){
		
	$(`#HRA_TAX_REPORT tbody`).empty();
	var columns = Object.keys(data[0]);
	// $("#HRA_TAX_REPORT").DataTable().clear();
	var colJson = [];
	for(var j = 0; j < columns.length; j++) {
		//console.log(columns.length);	
		colJson.push({
			title: columns[j]
		});
	}
	$("#VIA_REPORT").dataTable({
		"destroy": true,
		"columns": colJson,
		"defaultContent": "-",
        "targets": "_all",
		//"bPaginate": false,
		//"bLengthChange": false,
		//"bFilter": true,
		//"bInfo": false,
		"pageLength": 2000,
		//"bAutoWidth": false 
	});
	var dataLength = Object.keys(data).length;
	$("#VIA_REPORT").DataTable().clear();
	if(dataLength == 0) {
		$('#resultSec_VI').css('display', 'none');
		$('#noData_VI').css('display', 'block');
		$('#jsonLoaderPage_VI').css('display', 'none');
		$('#btnexportVIA').css('display', 'none');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#VIA_REPORT").dataTable().fnAddData([
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
				dat[columns[12]],
				dat[columns[13]],
				dat[columns[14]],
				dat[columns[15]],
				dat[columns[16]],
				dat[columns[17]],
				dat[columns[18]],
				dat[columns[19]],
				dat[columns[20]],
				dat[columns[21]],
				dat[columns[22]],
				dat[columns[23]],
				dat[columns[24]],
				dat[columns[25]],
				dat[columns[26]],
				dat[columns[27]],
				dat[columns[28]],
				dat[columns[29]],
				dat[columns[23]],
				dat[columns[31]],
				dat[columns[32]],
				dat[columns[33]],
				dat[columns[34]],
				dat[columns[35]],
				dat[columns[36]],
				dat[columns[37]],
				dat[columns[38]],
				dat[columns[39]],
				dat[columns[40]],
				dat[columns[41]],
			]);
		}
		$('#resultSec_VI').css('display', 'block');
		$('#noData_VI').css('display', 'none');
		$('#jsonLoaderPage_VI').css('display', 'none');
		$('#btnexportVIA').css('display', 'block');
	}
	
	}
	else{
		$('#resultSec_VI').css('display', 'none');
		$('#noData_VI').css('display', 'block');
		$('#jsonLoaderPage_VI').css('display', 'none');
		$('#btnexportVIA').css('display', 'none');
	}
}

/* code for exporting to excel starts */

function exportCHAPTER_VI_ATableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

/* code for exporting to excel ends */