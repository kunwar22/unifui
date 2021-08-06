$(document).ready(function(){
  	searchData();	
});

function searchData(){
	$.ajax({
		type:"GET",
		url:"/mydashboard/getHolidayData",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateSearchTable(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

function populateSearchTable(data){
	$("#holidaytable").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$('#holidayanalytics').css('display', 'none');
		$('#noHolidayData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#holidaytable").dataTable().fnAddData([
				dat.location,
				dat.holidaydate,
				dat.holidayname,	
				dat.description,
			]);
		}
		$('#resultAdhoc').css('display', 'block');
		$('#noAdhocData').css('display', 'none');
	}
}
