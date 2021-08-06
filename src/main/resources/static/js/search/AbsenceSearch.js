
$('#absCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});





/*****************************SEARCH ABSENCE START HERE***********************/

var jsonUrlPos = '/absencesetup/searchAbsence';



var searchdsCode = '';
var searchdsName = '';
var searchstatus='';

$("#ABS_SEARCH").on('click', function(e){
	
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	searchstatus=$('#statusList').children("option:selected").val();

	loadAbsenceData();
	
});
function loadAbsenceData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlPos,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"statusAbs":searchstatus
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			
			populateAbsenceDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateAbsenceDataTable(data){
	$("#ABS_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#AbsresultSec').css('display', 'none');
		$('#noDataAbs').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#ABS_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.description,
				dat.accrualfrequncy,
				dat.accrualon,
				dat.eligibilityid,
				dat.accrualtype,
				dat.status,				
			      "<select id=edit"+dat.absencetypeid+" onchange=editFunction("+dat.absencetypeid+",edit"+dat.absencetypeid+")><option disabled selected>Edit</option><option>Correct</option><option>Update</option></select>"		
			    
			]);
		}
		$('#AbsresultSec').css('display', 'block');
		$('#noDataAbs').css('display', 'none');
	}
}

/******************************************************************************************************/

var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/absencesetup/edit/EditAbsence/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
	updateUrl="/absencesetup/edit/EditAbsence/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_ABS_EFFDT_START').val();

updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);
});


	$('#btnerrorOK').on('click', function(e){
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);


		});





/************************************************************************/

var CR_PO_ID = '';
var CR_PO_ACTIONID = '';

function ajaxPost()
{
	
	CR_PO_ID = $('#txtPoid').val();
	CR_PO_ACTIONID = $('#txtPoActionid').val();
	
	
	
	if(CR_PO_ID!=0){
		loadCorrectPOData();
	}
	else if(CR_PO_ID==0){
		loadSavePOData();
		
	}
	
	
	
	
};



