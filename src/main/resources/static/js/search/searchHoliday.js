
$(document).on('click').unbind();
$('#holidayCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});





$(function() {
	$("#HolidayList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	
	
	
	var FROM_DATE = '';
	var HOLIDAY_NAME = '';
	var TO_DATE = '';
	var HOLY_STATUS = '';
	
	var jsonUrl = '/holiday/searchholiday/getHolidayById';
	
	if($('#HOLIDAY_SEARCH').val() != ''){
		FROM_DATE = $('#FROM_DATE').val();
		HOLIDAY_NAME = $('#HOLIDAY_NAME').val();
		TO_DATE = $('#TO_DATE').val();
		HOLY_STATUS = $('#statusList').val();
		
		loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#HOLIDAY_SEARCH").click(function(){
		FROM_DATE = $('#FROM_DATE').val();
		HOLIDAY_NAME = $('#HOLIDAY_NAME').val();
		TO_DATE = $('#TO_DATE').val();
		HOLY_STATUS = $('#statusList').val();
		if(FROM_DATE!="" && TO_DATE!=""){
			loadData();
		}else{
			alert("Please enter suitable From Date and To Date.");
		}
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"fromdate": FROM_DATE,
				"holidayname": HOLIDAY_NAME,
				"todate": TO_DATE,
				"status": HOLY_STATUS,
				
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
		$("#HolidayList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#HolidayList").dataTable().fnAddData([
					dat.name,
					dat.holidaytypename,
					dat.date.substring(0,10),
					dat.description,
					dat.status,
									
					"<ed rm='/holiday/edit/EditHolidayById/"+dat.holidayid+"' class='editDepartment' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
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
















