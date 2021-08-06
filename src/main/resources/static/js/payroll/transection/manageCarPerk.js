function addcarPerk() {
	$('#addCarPer').css('display', 'block');
}
$(document).ready(function() {
	loadDataCarPerq()
	
});


function loadDataCarPerq() {
var jsonUrl = '/getperqList/getdata';
	
	
	$.ajax({
		type: "GET",
		url: jsonUrl,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {			
			populateCarPerqDataTable(result);
		},
		error: function(e) {			
			console.log("ERROR : " + JSON.stringify(e));
		}
	});
}

function populateCarPerqDataTable(data) {
	
	if(data.length!=0){
	
	var dataLength = Object.keys(data).length;
	$("#carPerkList").DataTable().clear();
	if(dataLength == 0) {
		$('#resultSec').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#jsonLoaderPage').css('display', 'none');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#carPerkList").dataTable().fnAddData([
				dat.personNumber,
				dat.name,
				dat.desgination,
				dat.location,
				dat.startdate,
				dat.enddate,
				dat.perkAmt,
				dat.recAmt
			]);
		}
		$('#resultSec').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#jsonLoaderPage').css('display', 'none');
	}
	}
	else{
		$('#resultSec').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#jsonLoaderPage').css('display', 'none');
		//$('#btnexportVIA').css('display', 'none');
	}
}
/******************************************************************/
$('#btnsearchperson').on('click', function(e) {
	$('#id02').css("display", "block");
});
$('#CR_PER_POP_OK').on('click', function(e) {
	$('#id02').css("display", "none");
	$('#resultSecPerson').css("display", "none");
	$('#CR_PER_POP_OK').css("display", "none");
});
$('#CR_PER_POP_CANCEL').on('click', function(e) {
	$('#id02').css("display", "none");
	$('#resultSecPerson').css("display", "none");
	$('#PersonNumber').children('option[id="1"]').prop('selected', true);
	$('#pname').text("");
});
var table = $('#PersonAccomodationList').DataTable();

$('#PersonAccomodationList tbody').on('click', 'tr', function() {
	var tbldata = $(this).children('td').map(function() {
		return $(this).text();
	}).get();
	if($(this).hasClass('selected')) {
	} else {
		table.$('tr.selected').removeClass('selected');
		$(this).addClass('selected');
	}
	var dtData = tbldata[2];
	var dtDataId = tbldata[1];
	$('#PersonNumber').val(dtData);
	$('#PersonNumber').children('option[id="2"]').text(dtDataId);
	$('#PersonNumber').children('option[id="2"]').val(dtDataId);
	$('#PersonNumber').children('option[id="2"]').prop('selected', true);
	$('#personnumber').text(dtDataId);
	$('#personname').text(dtData);
	$('#CR_PER_POP_OK').css('display', 'inline');
});
/********************************************************************/
/**************************Pop-up SEARCH start here****************************************/
var jsonUrlPer = '/accomodation/searchAccomodation/gePersonId';
var personid = '';
var personName = '';
$("#CR_PER_POP_SEARCH").click(function() {
	
	personid = $("#CR_PER_POP_ID").val();
	personName = $("#CR_PER_POP_Name").val();
	$('#resultSecPerson').css('display', 'none');
	$('#noDataPerson').css('display', 'none');
	$('#jsonLoader').css('display', 'block');
	loadLegData();
	
});

function loadLegData() {
	$.ajax({
		type: 'POST',
		url: jsonUrlPer,
		dataSrc: '',
		data: {
			"personNumber": personid,
			"name": personName,
			"personId": '',
			"noe": ''
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populatePersonDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populatePersonDataTable(data) {
	$("#PersonAccomodationList").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0) {
		$('#resultSecPerson').css('display', 'none');
		$('#jsonLoader').css('display', 'none');
		$('#noDataPerson').css('display', 'block');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			//alert(dat.name);
			$("#PersonAccomodationList").dataTable().fnAddData([
				dat.personId,
				dat.personNumber,
				dat.name,
				dat.noe,
			]);
		}
		$('#resultSecPerson').css('display', 'block');
		$('#noDataPerson').css('display', 'none');
		$('#jsonLoader').css('display', 'none');
	}
}
$(document).on('click', 'ed', function(e) {
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#PersonNumber').on('change', function() {
	var selectObject = $(this).children("option:selected").val();
	if(selectObject == 'search') {
		$('#id02').css("display", "block");
	} else if(selectObject != 'search') {
		$('#id02').css("display", "none");
	}
});
var leaseId = '';
/*******************************Popup table selected row END*******************************************/
function savePerq() {
	var fd = $("#carPerqSave").serialize();
	$.ajax({
		url: "/carperq/savecarperq",
		type: "POST",
		data: fd,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(result) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
				message: result,
				callback: function() {
					$('#replace_div').load("/managecarperk");
				}
			});
		},
		error: function(response) {
			alert(JSON.parse(response.responseText));
		}
	});
};

function exportTableToExcel(tableID, filename = "") {
	$("#" + tableID).table2excel({
		exclude: ".noExl",
		filename: filename,
		fileext: ".xls"
	});
}


$(document).ready(function() {
$('#carPerkList').dataTable({
		"bPaginate": false,
		"bLengthChange": false,
		"bFilter": true,
		"bInfo": false,
		"bAutoWidth": false
	});
});