
$('#jobCreate').on('click', function(e){

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
	var JOB_NAME = '';
	var JOB_CODE = '';
	var JOB_DATASET = '';
	var JOB_STATUS = '';
	var JOB_EFFDT = '';
	var jsonUrl = '/enterprisesetup/searchJob/getJobId';
	
	if($('#JOB_SEARCH').val() != ''){
		JOB_NAME = $('#JOB_NAME').val();
		JOB_CODE = $('#JOB_CODE').val();
		JOB_DATASET = $('#JOB_DATASET').val();
		JOB_STATUS = $('#JOB_STATUS').val();
		$('#resultSec').css('display', 'block');
	}
	
	$("#JOB_SEARCH").click(function(){
		JOB_NAME = $('#JOB_NAME').val();
		JOB_CODE = $('#JOB_CODE').val();
		JOB_DATASET = $('#JOB_DATASET').val();
		JOB_STATUS = $('#JOB_STATUS').val();
		loadData();
		$('#resultSec').css('display', 'block');
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"name": JOB_NAME,
				"code": JOB_CODE,
				"dataSets": JOB_DATASET,
				"status": JOB_STATUS
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
		$("#JobList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#JobList").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.effectstartdate.substring(0,10),
					dat.status,					
					"<select id=edit"+dat.jobsid+" onchange=editFunction("+dat.jobsid+",edit"+dat.jobsid+")><option hidden disabled selected>Edit</option><option>Correct</option><option>Update</option></select>",
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


/*********************************************************************/




var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/EditJobResource/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
	updateUrl="/enterprisesetup/edit/EditJobResource/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_JOB_EFFDT').val();

updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);
});


	$('#btnerrorOK').on('click', function(e){
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);
		});


/******************************************************************/

















