
$(document).ready(function(){
	

	var locName = '';
		var locCode = '';

		var jsonUrl = '/enterpriseCreateLocation';
	$("#locCreate").click(function(){
		
		
		locName = $('#locName').val();
		locCode = $('#locCode').val();

		
		loadData();

	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"locname": locName,
				"loccode": locCode,

			},
			dataType: 'json',
			success: function(){


			},
			error: function(e){
				console.log("There was an error with request...");
			}
		});
	}

	
});