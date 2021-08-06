
$('#legaddrCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

//Start Legal address search//

var searchCountry = '';
var searchStatus = '';


var jsonUrl1 = '/enterprisesetup/searchlegaladdress';
$("#LA_LIST").DataTable({
	'columnDefs': [{
		'targets': [3,4],
		'orderable': false
	}]
});


$("#legaladdrSearch").on('click', function(e){
	searchCountry =  $("#countryList option:selected").text().trim();
	searchStatus = $("#statusList option:selected").text().trim();
	loadLegalAddressData();
	$('#resultLA').css('display', 'block');
});
function loadLegalAddressData(){
	$.ajax({
		type: 'POST',
		url: jsonUrl1,
		dataSrc: '',
		data: {
			"country": searchCountry,
			"status": searchStatus
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			
			populateLADataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateLADataTable(data){
	$("#LA_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#resultLA').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#LA_LIST").dataTable().fnAddData([
				dat.country,
				dat.addressline1,
				dat.pincode,
				dat.status,
				"<ed rm='/enterprisesetup/edit/legaladdress/correctlegaladdress/"+dat.legaladdressid+"' id='correctAddr' class='editAddress' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
				]);
		}
		$('#resultLA').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}

//End Legal address search//