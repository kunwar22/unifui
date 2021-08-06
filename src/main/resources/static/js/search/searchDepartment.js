
$(document).on('click').unbind();
$('#deptCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});





$(function() {
	$("#DepartmentList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	
	
	
	var DEPT_NAME = '';
	var DEPT_CODE = '';
	var DEPT_DATASET = '';
	var DEPT_STATUS = '';
	
	var jsonUrl = '/enterprisesetup/searchDepartment/getDepartmentId';
	
	if($('#DEPT_SEARCH').val() != ''){
		DEPT_NAME = $('#DEPT_NAME').val();
		DEPT_CODE = $('#DEPT_CODE').val();
		DEPT_DATASET = $('DEPT_DATASET').val();
		DEPT_STATUS = $('#DEPT_STATUS').val();
		
		loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#DEPT_SEARCH").click(function(){
		DEPT_NAME = $('#DEPT_NAME').val();
		DEPT_CODE = $('#DEPT_CODE').val();
		DEPT_DATASET = $('#DEPT_DATASET').val();
		DEPT_STATUS = $('#DEPT_STATUS').val();
		loadData();
		$('#resultSec').css('display', 'block');
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"name": DEPT_NAME,
				"code": DEPT_CODE,
				"dataSets": DEPT_DATASET,
				"status": DEPT_STATUS
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
		$("#DepartmentList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#DepartmentList").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.effectstartdate.substring(0,10),
					dat.status,				
					"<select id=edit"+dat.departmentsid+" onchange=editFunction("+dat.departmentsid+",edit"+dat.departmentsid+")><option hidden disabled selected>Edit</option><option>Correct</option><option>Update</option></select>"		
					
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






/*************************************************************************/




var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/EditDepartment/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
	updateUrl="/enterprisesetup/edit/EditDepartment/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_DEPT_EFFDT').val();

updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);
});


	$('#btnerrorOK').on('click', function(e){
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);
		});




/************************************************************************/









