var tab = '';

var fin_year = '';
var groupId = '';
var rfatype='';

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
$("#leaseQuarterSearch").on('click', function(e){
	
  fin_year = $("#Financial_year option:selected").text();
  groupId = $("#paygrp option:selected").val();
 rfatype = $("#rfa_type option:selected").val();

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
else if(rfatype ==''|| rfatype ==""||rfatype ==null) {
	
	bootbox.alert({
		size: 'medium',
		title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
		message: "Please select RFA Type ."
	});
	return;
}
 else {
			leaseQuarter(fin_year, groupId,rfatype)			
	  }
});	

function leaseQuarter(x, y,z) {
	debugger
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/finreports/manage/leaseQuart/" + x + "/" + z + "/" +y,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			//alert(typeof(JSON.parse(result)));
			if(z==208){
				populateleaseDataTable(result)
			}
			else{
				populateQuarterDataTable(result)
			}
			
			
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
			$('#resultSec_Quarter').css('display', 'none');
       	    $('#noData_Quarter').css('display', 'block');
            $('#jsonLoaderPage_Quarter').css('display', 'none');
			$('#btnexport_Quarter').css('display', 'none');
		}
	});
}



function populateleaseDataTable(data) {
	//debugger;
	//$(`#leaseQtr_REPORT tbody`).empty();
	$('#Quarter_Data').css('display', 'none');
	$('#Lease_Data').css('display', 'block');
   $("#leaseQtr_REPORT").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultSec_leaseQtr').css('display', 'none');
        $('#noData_leaseQtr').css('display', 'block');
        $('#jsonLoaderPage_leaseQtr').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#leaseQtr_REPORT").dataTable().fnAddData([
                dat.person_nbr,
				dat.attribute1,
				moment(dat.start_date).format('DD-MM-YYYY'),
                moment(dat.end_date).format('DD-MM-YYYY'), 
                dat.amount1,
                dat.amount2,
                           
            ]);
        }
        $('#resultSec_leaseQtr').css('display', 'block');
        $('#noData_leaseQtr').css('display', 'none');
        $('#jsonLoaderPage_leaseQtr').css('display', 'none');
        $('#btnexport_leaseQtr').css('display', 'block');
    }
}


/* code for exporting to excel starts */

function export_leaseQtrTableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

/* code for exporting to excel ends */




function populateQuarterDataTable(data) {
	//$(`#_Quarter_REPORT tbody`).empty();
	$('#Lease_Data').css('display', 'none');
	$('#Quarter_Data').css('display', 'block');
    $("#_Quarter_REPORT").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultSec_Quarter').css('display', 'none');
        $('#noData_Quarter').css('display', 'block');
        $('#jsonLoaderPage_Quarter').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#_Quarter_REPORT").dataTable().fnAddData([
                dat.person_nbr,
				dat.attribute1,
				moment(dat.start_date).format('DD-MM-YYYY'),
                moment(dat.end_date).format('DD-MM-YYYY'), 
                dat.amount1,
              
                           
            ]);
        }
        $('#resultSec_Quarter').css('display', 'block');
        $('#noData_Quarter').css('display', 'none');
        $('#jsonLoaderPage_Quarter').css('display', 'none');
        $('#btnexport_Quarter').css('display', 'block');
    }
}


/* code for exporting to excel starts */

function export__QuarterTableToExcel(tableID, filename = "") {
    $("#" + tableID).table2excel({ exclude: ".noExl", filename: filename, fileext: ".xls" });
}

/* code for exporting to excel ends */
$(document).ready(function() {
	//debugger;
	
$('#_Quarter_REPORT').dataTable({
    "bPaginate": false,
    "bLengthChange": false,
    "bFilter": true,
    "bInfo": false,
    "bAutoWidth": false });

	
})
$(document).ready(function() {
$('#leaseQtr_REPORT').dataTable({
    "bPaginate": false,
    "bLengthChange": false,
    "bFilter": true,
    "bInfo": false,
    "bAutoWidth": false });

})