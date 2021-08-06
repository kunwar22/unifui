$(function() {
	$("#userList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	var searchUserId = '';
	var searchEmplId = '';
	var searchEmailId = '';
	var jsonUrl = '/uniftools/security/userprofiles/userprofile/search/getUserId';
	
	if($('#userId').val() != ''){
		searchUserId = $('#userId').val();
		searchEmplId = $('#emplId').val();
		searchEmailId = $('#emailId').val();
		loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#userSearch").click(function(){
		searchUserId = $('#userId').val();
		searchEmplId = $('#emplId').val();
		searchEmailId = $('#emailId').val();
		loadData();
		$('#resultSec').css('display', 'block');
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"userid": searchUserId,
				"emplid": searchEmplId,
				"emailid": searchEmailId
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
		$("#userList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#userList").dataTable().fnAddData([
					dat.loginid,
					dat.emplid,
					dat.emailid,
					dat.isactive,
					"<ed rm='/uniftools/security/userprofiles/userprofile/edit/userprofile/"+dat.loginid+"/"+dat.emplid+"/"+dat.emailid+"' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
				]);
			}
			$('#resultSec').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}
});

$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});