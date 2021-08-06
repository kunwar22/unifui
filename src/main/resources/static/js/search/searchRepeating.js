
$('#repeatingCreate').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$('#approvalSetup').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#empldetailedit').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#personedit').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$(function() {
	$("#repeatingList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	var REPI_NAME = '';
	var REPT_CODE = '';
	var REPT_STAT = '';	
	
	
	var jsonUrl = '/repeatingperiod/searchRepeating/getRepeatingId';

	
	$("#REP_SEARCH").click(function(){
		REPI_NAME = $('#REPT_NAME').val();
		REPT_CODE = $('#REPT_CODE').val();
		REPT_STAT = $('#REP_STATUS').val();
		
		loadData();
		});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"name": REPI_NAME,
				"code": REPT_CODE,
				"status": REPT_STAT
				
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
		$("#repeatingList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSecRep').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#repeatingList").dataTable().fnAddData([
					dat.name,
					dat.code,		
					dat.status,			
					dat.periodtypename,	
					dat.periodlengthname,
					dat.previewperiodstartdate,
					dat.repeatingperiodid
										
					]);
			}
			$('#resultSecRep').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}
});

$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

