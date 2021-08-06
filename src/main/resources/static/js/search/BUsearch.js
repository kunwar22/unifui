
$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$('#businessunitCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});





var jsonUrlBU = '/enterprisesetup/searchBU';



var searchdsCode = '';
var searchdsName = '';
$("#businessunitSearch").on('click', function(e){
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	searchstatus=$('#statusList').children("option:selected").val();
	loadBUData();
	
});
function loadBUData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlBU,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"statusBU":searchstatus
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			populateBUDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateBUDataTable(data){
	$("#BU_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#resultBU').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#BU_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.dataset,
				dat.status,				
				"<select id=edit"+dat.businessunitid+" onchange=editFunction("+dat.businessunitid+",edit"+dat.businessunitid+")><option hidden disabled selected>Edit</option><option>Correct</option><option>Update</option></select>"	
				
				]);
		}
		$('#resultBU').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}



$(document).on('click').unbind();

/***********************************************************************/

var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/EditBusinessUnit/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
	updateUrl="/enterprisesetup/edit/EditBusinessUnit/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_EFFDT').val();

updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);
	
});


	$('#btnerrorOK').on('click', function(e){
		
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);
	

		});



/**********************************************************************/

