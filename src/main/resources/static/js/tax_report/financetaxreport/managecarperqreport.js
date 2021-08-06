


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
$("#carperqSearch").on('click', function(e){

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
		message: "Please select Business unit ."
	});
	return;
}
 else {
			carperq(fin_year, groupId)			
	  }
});	

function carperq(x, y) {
	debugger
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/finreports/manage/carperq/" + x + "/" + y,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			//alert(typeof(JSON.parse(result)));
			populatecarperqDataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
			$('#btnexport_carpeq').css('display', 'none');
		}
	});
}



function populatecarperqDataTable(data) {
    $("#_carpeq_REPORT").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultSec_carpeq').css('display', 'none');
        $('#noData_carpeq').css('display', 'block');
        $('#jsonLoaderPage_carpeq').css('display', 'none');
        $('#btnexport_carpeq').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#_carpeq_REPORT").dataTable().fnAddData([
                dat.person_nbr,
 				dat.attribute1,
			    moment(dat.start_date).format('DD-MM-YYYY'),
                moment(dat.end_date).format('DD-MM-YYYY'),               
                dat.amount1,
                dat.amount2,
               
            ]);
        }
        $('#resultSec_carpeq').css('display', 'block');
        $('#noData_carpeq').css('display', 'none');
        $('#jsonLoaderPage_carpeq').css('display', 'none');
        $('#btnexport_carpeq').css('display', 'block');
    }
}

/*function populatecarperqDataTable(data) {
	var columns = Object.keys(data[0]);
	
	var colJson = [];
	for(var j = 0; j < columns.length; j++) {
		//console.log(columns.length);	
		colJson.push({
			title: columns[j]
		});
	}
	$("#PREV_INCOME_REPORT").dataTable({
		"columns": colJson
	});
	var dataLength = Object.keys(data).length;
	if(dataLength == 0) {
		$('#resultSec_PREV_INCOME').css('display', 'none');
		$('#noData_PREV_INCOME').css('display', 'block');
		$('#jsonLoaderPage_PREV_INCOME').css('display', 'none');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#PREV_INCOME_REPORT").dataTable().fnAddData([
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
		$('#resultSec_PREV_INCOME').css('display', 'block');
		$('#noData_PREV_INCOME').css('display', 'none');
		$('#jsonLoaderPage_PREV_INCOME').css('display', 'none');
		$('#btnexportPREV_INCOME').css('display', 'block');
	}
}
*/
/* code for exporting to excel starts */

function export_carpeqTableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

/* code for exporting to excel ends */

$(document).ready(function() {
	//debugger;

$('#_carpeq_REPORT').dataTable({
    "bPaginate": false,
    "bLengthChange": false,
    "bFilter": true,
    "bInfo": false,
    "bAutoWidth": false });


	
})