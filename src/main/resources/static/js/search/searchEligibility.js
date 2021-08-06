
$('#eligiCreate').on('click', function(e){
	//debugger;
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});



$('#manageAnonymous').on('click', function(e){
	
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$('#personManagement').on('click', function(e){
	
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#AorManage').on('click', function(e){
	
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$('#PersonalRecord').on('click', function(e){
	
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$('#BankAccount').on('click', function(e){
	
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});



$(function() {
	$("#JobList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	var ELIG_NAME = '';
	var ELIG_CODE = '';	
	
	
	var jsonUrl = '/eligibility/searchEligibility/getEligibilityId';
	
	if($('#ELIG_SEARCH').val() != ''){
		ELIG_NAME = $('#CR_ELIG_NAME').val();
		ELIG_CODE = $('#CR_ELIG_CODE').val();
		
		
		//loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#ELIG_SEARCH").click(function(){
		ELIG_NAME = $('#CR_ELIG_NAME').val();
		ELIG_CODE= $('#CR_ELIG_CODE').val();
		loadData();
		$('#resultSec').css('display', 'block');
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"name": ELIG_NAME,
				"code": ELIG_CODE				
				
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}
	
	function populateDataTable(data){
		$("#EligibilityList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#EligibilityList").dataTable().fnAddData([
					dat.eligibilityid,
					dat.eligibilityname,					
					dat.eligibilitydescription,
					"<ed rm='/eligibility/editEligibility/"+dat.eligibilityid+"' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"

				]);
			}
			$('#resultSec').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}
});

$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

