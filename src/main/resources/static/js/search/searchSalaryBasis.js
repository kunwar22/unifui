

$('#LoadSalaryBasis').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$('#SalCancel').on('click', function(e){
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
		JOB_EFFDT = $('#JOB_EFFDT').val();
		loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#JOB_SEARCH").click(function(){
		JOB_NAME = $('#JOB_NAME').val();
		JOB_CODE = $('#JOB_CODE').val();
		JOB_DATASET = $('#JOB_DATASET').val();
		JOB_STATUS = $('#JOB_STATUS').val();
		JOB_EFFDT = $('#JOB_EFFDT').val();
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
				"effectStartDate": JOB_EFFDT,
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
					dat.dataSets,
					dat.effectStartDate,
					dat.status
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




var CR_JOB_NAME='';
var CR_JOB_EFFDT = '';
var CR_JOBID = '';
var CR_JOB_CODE = '';
var CR_JOB_DATASET = '';
var CR_STATUS = '';


var jsonUrl = '/enterprisesetup/createJobResource';




$("#JOB_CREATE").click(function(){
	
	
	CR_JOB_NAME = $('#CR_JOB_NAME').val();
	CR_JOB_EFFDT = $('#CR_JOB_EFFDT').val();
	CR_JOBID = $('#CR_JOBID').val();
	CR_JOB_CODE = $('#CR_JOB_CODE').val();
	CR_JOB_DATASET = $('#CR_JOB_DATASET').val();
	CR_STATUS=$('#statusList').children("option:selected").val();
	
	
	loadSaveJobData();

});



function loadSaveJobData() {
	
	$.ajax({
		type : 'POST',
		url : jsonUrl,
		dataSrc : '',
		data : {
			"CR_JOB_NAME" : CR_JOB_NAME,
			"CR_JOB_EFFDT" : CR_JOB_EFFDT,
			"CR_JOBID": CR_JOBID,		
			"CR_JOB_CODE" : CR_JOB_CODE,
			"CR_JOB_DATASET" : CR_JOB_DATASET,
			"CR_STATUS": CR_STATUS				
			
		},
		dataType : 'json',
		success : function(data) {

			$('#id01').css("display","block");
			$('#btnOK').on('click', function(e){
				var url = $(this).attr("rm");
				$('#replace_div').load(url);
			});
		},
		error : function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});

}



/************************************** GRADERATE SEARCH START********************************************/




var jsonUrlGradeRate = '/enterprisesetup/searchGradeRate';



var searchGRADERATERatetype = '';
var searchGRADERATEName = '';
var searchGRADERATEstatus='';

$("#GRADERATE_SEARCH").on('click', function(e){
	
	searchGRADERATERatetype =$("#SRCH_RATETYPE").children("option:selected").val();
	searchGRADERATEName = $("#SRCH_NAME1").val();
	searchGRADERATEstatus=$("#statusListGRADERATE").children("option:selected").val();

	loadGradeRATEData()
	
});
function loadGradeRATEData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlGradeRate,
		dataSrc: '',
		data: {
			"name": searchGRADERATEName,
			"ratetype": searchGRADERATERatetype,
			"status":searchGRADERATEstatus
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			
			populateGradeRateDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateGradeRateDataTable(data){
	$("#GRADERATE_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#graderateresultSec').css('display', 'none');
		$('#noDataGradeRate').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#GRADERATE_LIST").dataTable().fnAddData([
				dat.name,
				dat.effectstartdate,
				dat.ratetype,
				dat.ratefrequency,
				dat.currancycode,
				dat.status,
				dat.graderateid
			]);
		}
		$('#graderateresultSec').css('display', 'block');
		$('#noDataGradeRate').css('display', 'none');
	}
}

/*******************************Popup table selected row start*******************************************/


$('#GradeRateSearch').on('change',function(){

var selectObject=$(this).children("option:selected").val();
if(selectObject=='search'){
	$('#graderateresultSec').css('display', 'none');
	$('#id01').css("display","block");
	
}
else if(selectObject!='search'){
	$('#graderateresultSec').css('display', 'none');
	$('#id01').css("display","none");
}
});




$(document).ready(function(){




$('#btnsearch2').on('click', function(e){
	$('#id01').css("display","block");
	
});



$('#BU_POP_OK').on('click', function(e){
	$('#id01').css("display","none");
	
});


var table=$('#GRADERATE_LIST').DataTable();

$('#GRADERATE_LIST tbody').on('click','tr',function(){
	
	var tbldata=$(this).children('td').map(function(){
		return $(this).text();
		
	}).get();	
	
	if($(this).hasClass('selected')){
		$(this).removeClass('selected');
		
	}
	else{
		table.$('tr.selected').removeClass('selected');
		$(this).addClass('selected');
	}
	var dtData=tbldata[0];
	var dtDataId=tbldata[6];
	$('#GradeRateSearch').val(dtData);
	$('#GradeRateSearch').children('option[id="2"]').text(dtData);
	$('#GradeRateSearch').children('option[id="2"]').val(dtDataId);
	$('#GradeRateSearch').children('option[id="2"]').prop('selected',true);		
	
});

});


/*******************************Popup table selected row END*******************************************/

/*******************************SALARY BASIS SAVE START HERE*******************************************/



var CR_SAL_ID = '';
var CR_SAL_ACTIONID = '';

function ajaxPost()
{

	CR_SAL_ID = $('#txtSalaryid').val();
	CR_SAL_ACTIONID = $('#txtSalaryActionid').val();
	
	if(CR_SAL_ID!=0){
		loadCorrectSALData();
	}
	else if(CR_SAL_ID==0){
		loadSaveSALData();
		
	}
	
	
};



function loadSaveSALData() {
alert("save");

var fd = $("#SALARY_BASIS").serialize();
	
	var selectgrade=$("#GradeRateSearch option:selected").text();
	$.ajax({
		url: "/enterprisesetup/saveSalaryBasis",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
		$('#replace_div').html(result);
			$('#GradeRateSearch').children('option[id="2"]').text(selectgrade);
			$('#GradeRateSearch').children('option[id="2"]').prop('selected',true);	
			if(message!=null || message!="" ){
			alert(message);
			}
			},
			error: function(response){
		  	}
	});

};




function loadCorrectSALData() {
	var selectgrade=$("#GradeRateSearch option:selected").text();
	var fd = $("#SALARY_BASIS").serialize();
	
	$.ajax({
		url: "/enterprisesetup/correctSalaryBasis",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			$('#replace_div').html(result);
			
			$('#GradeRateSearch').children('option[id="2"]').text(selectgrade);
			$('#GradeRateSearch').children('option[id="2"]').prop('selected',true);	
			if(message!=null || message!="" ){
			alert(message);
			}
			},
			error: function(response){
			}
	});

};





/*******************************SALARY BASIS SAVE END HERE*******************************************/



/*******************************SEARCH SLARAY BASIS START HERE*****************************************/

var jsonUrlGrade = '/enterprisesetup/searchSalaryBasis';



var searchdsCode = '';
var searchdsName = '';
var searchstatus='';

$("#SALARY_SEARCH").on('click', function(e){
	
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	searchstatus=$('#statusList').children("option:selected").val();

	loadSalaryData()
	
});
function loadSalaryData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlGrade,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"status":searchstatus
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			
			populateSalaryDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateSalaryDataTable(data){
	$("#SALARY_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#salaryresultSec').css('display', 'none');
		$('#noDataSalary').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#SALARY_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.status,
				dat.graderateid,
				"<ed rm='/enterprisesetup/edit/EditSalarybasis/"+dat.salarybasesid+"' class='editSalary' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
				]);
		}
		$('#salaryresultSec').css('display', 'block');
		$('#noDataSalary').css('display', 'none');
	}
}

/*******************************SEARCH SLARAY BASIS END HERE*****************************************/

var CR_SALARY = '';

$('#CR_SALARY_BASIS').on('change', function(e){
	var selectedtype=$("#CR_SALARY_BASIS option:selected").val();
	if(selectedtype==37){		
		$('#CR_COMP_FTE').children('option[id="2"]').val(5);
		$('#CR_COMP_FTE').children('option[id="2"]').text('Monthly');
		$('#CR_COMP_FTE').children('option[id="2"]').prop('selected',true);
		$('#CR_ANNUAL_FACTOR').val(12.0);
		$('#CR_ANNUAL_FACTOR').prop('readonly',true);
	}
	else if(selectedtype==38){		
		$('#CR_COMP_FTE').children('option[id="2"]').val(4);
		$('#CR_COMP_FTE').children('option[id="2"]').text('Annually');
		$('#CR_COMP_FTE').children('option[id="2"]').prop('selected',true);
		$('#CR_ANNUAL_FACTOR').val(1.0);
		$('#CR_ANNUAL_FACTOR').prop('readonly',true);
	}
});





