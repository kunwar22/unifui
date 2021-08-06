var datasetId='';
var buId='';
var dataset='';

ID = $('#txtPoid').val();
ENDDATE = $('#CR_GRADE_EFFDT_END').val();
		if(ID!=undefined && ID!=0 && ENDDATE!=undefined && ENDDATE!=''){
			if(new Date(ENDDATE)< new Date()){
						var strflag=$("#CR_GRADE_EFFDT_START").attr("msg");
						if(strflag=='Correct'){
						$('#positionCreate').css("display","none");
						}
						else if(strflag!='Correct'){
							$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
							$('#lblMsg').text("Can't update expired Position. Click OK to continue.");
							$('#btnOK1').toggleClass("w3-button w3-red");
							$('#btnOK1').on('click', function(e){
							var url = $(this).attr("rm");
							$('#replace_div').load(url);
							});
						}
				}
		}



$(document).on('click').unbind();

$('#createPosition').click(function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});



$('#PosCancelB').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});


$('#POS_TYPE').on('change', function(e){
	var selectedtype=$("#POS_TYPE option:selected").val();
	if(selectedtype==14){
		$('#POS_FTE').val(1);
		$('#POS_FTE').prop('readonly',true);
		$('#POS_HEAD').val(1);
		$('#POS_HEAD').prop('readonly',true);
	}else{
		$('#POS_FTE').val(0);
		$('#POS_FTE').prop('readonly',false);
		$('#POS_HEAD').val(0);
		$('#POS_HEAD').prop('readonly',false);
	}
});

$('#gradestepsid_disp').on('click', function(e){
	var selectEntryGradeId=$('#ENTRY_GRADE').val();
	var jsonUrl = '/enterprisesetup/entrygradebind/' +selectEntryGradeId;

	newStatebind="";
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',
		
		dataType: 'json',
		success: function(data){
				data.forEach(function(n){
					newStatebind+='<option value="'+n.gradestepid+'" >'+n.stepname+'</option>'
				});
			$('#gradestepsid_disp').html(newStatebind);
				
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
	
});

/*****************************SEARCH POSITION START HERE***********************/

var jsonUrlPos = '/enterprisesetup/searchPosition';



var searchdsCode = '';
var searchdsName = '';
var searchstatus='';

$("#POS_SEARCH").on('click', function(e){
	
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	searchstatus=$('#statusList').children("option:selected").val();

	loadPositionData();
	
});

function loadPositionData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlPos,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"statusGrade":searchstatus
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			
			populatePositionDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populatePositionDataTable(data){
	$("#POS_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#posresultSec').css('display', 'none');
		$('#noDataPos').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#POS_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.effectstartdate.slice(0,10),
				dat.effectenddate.substring(0,10),
				dat.status,
				dat.businessunitid,
			    "<select id=edit"+dat.positionid+" onchange=editFunction("+dat.positionid+",edit"+dat.positionid+")><option hidden disabled selected>Edit</option><option>Correct</option><option>Update</option></select>"		
			    
			]);
		}
		$('#posresultSec').css('display', 'block');
		$('#noDataPos').css('display', 'none');
	}
}

/******************************************************************************************************/

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



function loadSavePOData() {

var fd = $("#POSITION_SAVE").serialize();
var selectloc=$("#CR_ENT_LOC_CODE option:selected").text();
var selectbu=$("#businessUnit option:selected").text();
var selectjob=$("#POP_JOB_SEARCH option:selected").text();
var selectdept=$("#CR_ENT_DEP_CODE option:selected").text();
var selectgrade=$("#ENTRY_GRADE option:selected").text();
var selectgrade=$("#ENTRY_GRADE option:selected").text();
var selectgradestep=$("#ENTRY_STEP option:selected").text();
var startdate=$('#CR_GRADE_EFFDT_START').val();
$.ajax({
		url: "/enterprisesetup/savePosition",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			$('#replace_div').html(result);
			$('input#CR_GRADE_EFFDT_START').val(startdate);
			
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').text(selectloc);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').prop('selected',true);	
			
			$('#businessUnit').children('option[id="2"]').text(selectbu);
			$('#businessUnit').children('option[id="2"]').prop('selected',true);	
			
			$('#POP_JOB_SEARCH').children('option[id="2"]').text(selectjob);
			$('#POP_JOB_SEARCH').children('option[id="2"]').prop('selected',true);	
			
			$('#CR_ENT_DEP_CODE').children('option[id="2"]').text(selectdept);
			$('#CR_ENT_DEP_CODE').children('option[id="2"]').prop('selected',true);	
			
			$('#ENTRY_GRADE').children('option[id="2"]').text(selectgrade);
			$('#ENTRY_GRADE').children('option[id="2"]').prop('selected',true);	
			
			$('#gradestepsid_disp').children('option[id="2"]').text(selectgradestep);
			$('#gradestepsid_disp').children('option[id="2"]').prop('selected',true);
			
				if(message.status!=null && message.status!=""){
				if(message.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(message.message+". Click OK to continue.");
					$('#btnOK1').toggleClass("w3-button w3-green");
					$('#btnOK1').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}else if(message.status!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(message.message+". Click OK to continue.");
					$('#btnOK1').toggleClass("w3-button w3-red");
					$('#btnOK1').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
			}
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

};




function loadCorrectPOData() {
var flag=$("#CR_GRADE_EFFDT_START").attr("msg");
var fd = $("#POSITION_SAVE").serialize();
var selectloc=$("#CR_ENT_LOC_CODE option:selected").text();
var selectbu=$("#businessUnit option:selected").text();
var selectjob=$("#POP_JOB_SEARCH option:selected").text();
var selectdept=$("#CR_ENT_DEP_CODE option:selected").text();
var selectgrade=$("#ENTRY_GRADE option:selected").text();
var selectgrade=$("#ENTRY_GRADE option:selected").text();
var selectgradestep=$("#ENTRY_STEP option:selected").text();
var startdate=$('#CR_GRADE_EFFDT_START').val();
var jsonUrlpo ='/enterprisesetup/correctPosition/'+flag;

$.ajax({
		url: jsonUrlpo,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			
			$('#replace_div').html(result);
			
			$('input#CR_GRADE_EFFDT_START').val(startdate);
			
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').text(selectloc);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').prop('selected',true);	
			
			$('#businessUnit').children('option[id="2"]').text(selectbu);
			$('#businessUnit').children('option[id="2"]').prop('selected',true);	
			
			$('#POP_JOB_SEARCH').children('option[id="2"]').text(selectjob);
			$('#POP_JOB_SEARCH').children('option[id="2"]').prop('selected',true);	
			
			$('#CR_ENT_DEP_CODE').children('option[id="2"]').text(selectdept);
			$('#CR_ENT_DEP_CODE').children('option[id="2"]').prop('selected',true);	
			
			$('#ENTRY_GRADE').children('option[id="2"]').text(selectgrade);
			$('#ENTRY_GRADE').children('option[id="2"]').prop('selected',true);	
			
			$('#gradestepsid_disp').children('option[id="2"]').text(selectgradestep);
			$('#gradestepsid_disp').children('option[id="2"]').prop('selected',true);
			
			
			if(message.status!=null && message.status!=""){
				if(message.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					
					$('#lblMsg').text(message.message+". Click OK to continue.");
					$('#btnOK1').toggleClass("w3-button w3-green");
					$('#btnOK1').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}else if(message.status!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(message.message+". Click OK to continue.");
					$('#btnOK1').toggleClass("w3-button w3-red");
					$('#btnOK1').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
			}
			
			},
			error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

};




/**************************Pop-up LOCATION SEARCH start here****************************************/
	

	
	
	$(function() {
		$("#EnterpriseList").DataTable({
			'columnDefs': [{
				'targets': [3,4],
				'orderable': false
			}]
		});
		
		var searchName = '';
		var searchCode = '';
		var searchCountry = '';
		var searchStatus = '';
		var jsonUrl = '/enterprisesetup/edit/enterpriseLocation/searchEnterpriseLocation';
		
		
		
		
		
		$("#CR_ENT_POP_SEARCH").click(function(){
			
			searchName = $('#CR_ENT_POP_NAME').val();
			searchCode = $('#CR_ENT_POP_CODE').val();
			searchCountry = $('#CR_ENT_POP_CNTRY').val();
			searchStatus = $('#CR_ENT_POP_STATUS').val();
			
			loadLocTableData();
			$('#resultSec').css('display', 'block');
		});
			
			
		function loadLocTableData(){
			$.ajax({
				type: 'POST',
				url: jsonUrl,
				dataSrc: '',
				data: {
					"name": searchName,
					"code": searchCode,
					"country": searchCountry,
					"status": "Active"
				},
				dataType: 'json',
				success: function(data){
					jsonData = data;
					populateLocDataTable(jsonData);
				},
				error: function(e){
					console.log("There was an error with request...");
					console.log("error: " + JSON.stringify(e));
				}
			});
		}
		
		
		function populateLocDataTable(data){
			$("#EnterpriseList").DataTable().clear();
			var dataLength = Object.keys(data).length;
			if(dataLength == 0){
				$('#resultSec').css('display', 'none');
				$('#noDataLOcation').css('display', 'block');
			} else {
				for(var i=0; i < dataLength; i++){
					var dat = data[i];
					$("#EnterpriseList").dataTable().fnAddData([
						dat.name,
						dat.code,
						dat.country,
						dat.state,
						dat.status,
						dat.locationid
					]);
					
					
					
				}
				$('#resultSec').css('display', 'block');
				$('#noDataLOcation').css('display', 'none');
			}
		}
	});

	$(document).on('click', 'ed', function(e){
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});
	
	
	
	
	

	
	
	
	
/**************************Pop-up section End here****************************************/	
	
/*******************************Popup table selected row start*******************************************/
	
	
		$('#CR_ENT_LOC_CODE').on('change',function(){
		
		var selectObject=$(this).children("option:selected").val();
		if(selectObject=='search'){
			$('#id01').css("display","block");
		}
		else if(selectObject!='search'){
			$('#id01').css("display","none");
		}
	});
	
	
	
	
	$(document).ready(function(){
		
		
		
		
		$('#btnsearch').on('click', function(e){
			$('#id01').css("display","block");
			
		});
		
		
		
		$('#CR_ENT_POP_OK').on('click', function(e){
			$('#id01').css("display","none");
			$('#resultSec').css("display","none");
			$('#CR_ENT_POP_OK').css("display","none");
			
		});
		$('#CR_ENT_POP_CANCEL').on('click', function(e){
			$('#id01').css("display","none");
			$('#resultSec').css("display","none");
			$('#CR_ENT_LOC_CODE').children('option[id="1"]').prop('selected',true);	
			
		});

		
		
		var table=$('#EnterpriseList').DataTable();
		
		$('#EnterpriseList tbody').on('click','tr',function(){
			
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
			var dtDataId=tbldata[5];
			$('#CR_ENT_LOC_CODE').val(dtData);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').text(dtData);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').val(dtDataId);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').prop('selected',true);	
			$('#CR_ENT_POP_OK').css('display','inline');	
			
		});
		
	});
	
	
	
	
/*******************************Popup table selected row END*******************************************/
	
	
	

/**************************************POPUP DEPARTMENT SEARCH START********************************************/

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
	
	var jsonUrl = '/enterprisesetup/position/getDepartmentByBUId';
	
	if($('#DEPT_SEARCH').val() != ''){
		DEPT_NAME = $('#DEPT_NAME').val();
		DEPT_CODE = $('#DEPT_CODE').val();
		DEPT_DATASET = $('DEPT_DATASET').val();
		DEPT_STATUS = $('#DEPT_STATUS').val();
		
		$('#deptresultSec').css('display', 'block');
	}
	
	$("#DEPT_SEARCH").click(function(){
		DEPT_NAME = $('#DEPT_NAME').val();
		DEPT_CODE = $('#DEPT_CODE').val();
		if($('#DEPT_DATASET').val()==""){
		DEPT_DATASET = $('#DEPT_DATASET').attr("value");
		}else{
		DEPT_DATASET = $('#DEPT_DATASET').val();
		}
		
		DEPT_STATUS = $('#DEPT_STATUS').val();
		loadDeptData();
		$('#deptresultSec').css('display', 'block');
	});
		
	function loadDeptData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"name": DEPT_NAME,
				"code": DEPT_CODE,
				"dataSets": DEPT_DATASET,
				"status": "Active"
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateDeptDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}
	
	function populateDeptDataTable(data){
		$("#DepartmentList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#deptresultSec').css('display', 'none');
			$('#noDataDEPT').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#DepartmentList").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.dataset,
					dat.status,
					dat.effectstartdate.substring(0,10),
					dat.departmentsid
				]);
			}
			$('#deptresultSec').css('display', 'block');
			$('#noDataDEPT').css('display', 'none');
		}
	}
});

$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


/*******************************Popup table selected row start*******************************************/
	
	
		$('#CR_ENT_DEP_CODE').on('change',function(){
		
		var selectObject=$(this).children("option:selected").val();
		if(selectObject=='search'){
			$('#id02').css("display","block");
			$('#DEPT_DATASET').val(datasetId);
			$('#deptresultSec').css('display', 'none');
			
			
		}
		else if(selectObject!='search'){
			$('#id02').css("display","none");
		}
	});
	
	
	
	
	$(document).ready(function(){
		
		
		
		
		$('#btnsearch1').on('click', function(e){
			$('#id02').css("display","block");
			
		});
		
		
		
		$('#CR_DEP_POP_OK').on('click', function(e){
			$('#id02').css("display","none");
			$('#CR_DEP_POP_OK').css('display','none');	
			
		});
		
		$('#CR_ENT_DEP_CANCEL').on('click', function(e){
	$('#id02').css("display","none");
	$('#deptresultSec').css("display","none");
	$('#CR_ENT_DEP_CODE').children('option[id="1"]').prop('selected',true);	
});
		
		
		var table=$('#DepartmentList').DataTable();
		
		$('#DepartmentList tbody').on('click','tr',function(){
			
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
			var dtDataId=tbldata[5];
			$('#CR_ENT_DEP_CODE').val(dtData);
			$('#CR_ENT_DEP_CODE').children('option[id="2"]').text(dtData);
			$('#CR_ENT_DEP_CODE').children('option[id="2"]').val(dtDataId);
			$('#CR_ENT_DEP_CODE').children('option[id="2"]').prop('selected',true);	
			$('#CR_DEP_POP_OK').css('display','inline');	
			
			
		});
		
	});
	
	
/*******************************Popup table selected row END*******************************************/
	
/**************************************POPUP DEPARTMENT SEARCH END********************************************/

/**************************************POPUP BU SEARCH START********************************************/

	var jsonUrlBU = '/enterprisesetup/searchBU';



	var searchdsCode = '';
	var searchdsName = '';
	$("#BU_SEARCH").on('click', function(e){
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
				"statusBU":"Active"
				
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				//debugger;
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
			$('#buresultSec').css('display', 'none');
			$('#noDataBU').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#BU_LIST").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.dataset,
					dat.status,
					dat.businessunitid,
					dat.datasetid,					
					"<ed rm='/uniftools/security/userprofiles/userprofile/edit/userprofile/"+dat.businessunitid+"' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
				]);
			}
			$('#buresultSec').css('display', 'block');
			$('#noDataBU').css('display', 'none');
		}
	}
	
	/*******************************Popup table selected row start*******************************************/
	
	
	$('#businessUnit').on('change',function(){
	var selectObject=$(this).children("option:selected").val();
	if(selectObject=='search'){
		$('#id03').css("display","block");
		
	}
	else if(selectObject!='search'){
		$('#id03').css("display","none");
	}
});




$(document).ready(function(){
	
	
	
	
	$('#btnsearch2').on('click', function(e){
		$('#id03').css("display","block");
		
	});
	
	
	$('#BU_POP_CANCEL').on('click', function(e){
		$('#id03').css("display","none");
		$('#businessUnit').children('option[id="1"]').prop('selected',true);
		$('#buresultSec').css('display', 'none');
		dataset='';
	});
	
	$('#BU_POP_OK').on('click', function(e){
		$('#id03').css("display","none");
		$('#CR_ENT_DEP_CODE').prop('disabled',false);	
		$('#POP_JOB_SEARCH').prop('disabled',false);
		$('#CR_ENT_DEP_CODE').children('option[id="1"]').prop('selected',true);
		$('#POP_JOB_SEARCH').children('option[id="1"]').prop('selected',true);
		
		$('#buresultSec').css('display', 'none');
		$('#BU_POP_OK').css('display','none');	
		
		
	});
	
	
	var table=$('#BU_LIST').DataTable();
	
	$('#BU_LIST tbody').on('click','tr',function(){
		
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
		var dtDataId=tbldata[4];
		buId=dtDataId;
		datasetId=tbldata[5];
		dataset=tbldata[2];
		$('#businessUnit').val(dtData);
		$('#businessUnit').children('option[id="2"]').text(dtData);
		$('#businessUnit').children('option[id="2"]').val(dtDataId);
		$('#businessUnit').children('option[id="2"]').prop('selected',true);	
		
		$('#BU_POP_OK').css('display','inline');		
		
		
	});
	
});


/*******************************Popup table selected row END*******************************************/

/**************************************POPUP BU SEARCH END********************************************/



/**************************************POPUP JOB SEARCH START********************************************/

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
	var jsonUrl = '/enterprisesetup/position/getJobsByBUId';
	
	if($('#JOB_SEARCH').val() != ''){
		JOB_NAME = $('#JOB_NAME').val();
		JOB_CODE = $('#JOB_CODE').val();
		JOB_DATASET = $('#JOB_DATASET').val();
		JOB_STATUS = $('#JOB_STATUS').val();
		$('#jobresultSec').css('display', 'block');
	}
	
	$("#JOB_SEARCH").click(function(){
		JOB_NAME = $('#JOB_NAME').val();
		JOB_CODE = $('#JOB_CODE').val();
		if($('#JOB_DATASET').val()==""){
		JOB_DATASET = $('#JOB_DATASET').attr("value");
		}else{
		JOB_DATASET = $('#JOB_DATASET').val();
		}
		JOB_STATUS = $('#JOB_STATUS').val();
		loadData();
		$('#jobresultSec').css('display', 'block');
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
				"status": "Active"
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
			$('#jobresultSec').css('display', 'none');
			$('#noDataJOB').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#JobList").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.dataset,
					dat.effectstartdate.substring(0,10),
					dat.status,
					dat.jobsid
					]);
			}
			$('#jobresultSec').css('display', 'block');
			$('#noDataJOB').css('display', 'none');
		}
	}
});

$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

/*******************************Popup table selected row start*******************************************/


$('#POP_JOB_SEARCH').on('change',function(){

var selectObject=$(this).children("option:selected").val();
if(selectObject=='search'){
	$('#id04').css("display","block");
	$('#JOB_DATASET').val(datasetId);	
	$('#jobresultSec').css('display', 'none');
}

else if(selectObject!='search'){
	$('#id04').css("display","none");
}
});




$(document).ready(function(){




$('#btnsearch3').on('click', function(e){
	$('#id04').css("display","block");
	
});



$('#JOB_POP_OK').on('click', function(e){
	$('#id04').css("display","none");
	$('#JOB_POP_OK').css("display","none");
});

$('#JOB_POP_CANCEL').on('click', function(e){
	$('#id04').css("display","none");
	$('#jobresultSec').css("display","none");
	$('#POP_JOB_SEARCH').children('option[id="1"]').prop('selected',true);	
});



var table=$('#JobList').DataTable();

$('#JobList tbody').on('click','tr',function(){
	
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
	var dtDataId=tbldata[5];
	$('#POP_JOB_SEARCH').val(dtData);
	$('#POP_JOB_SEARCH').children('option[id="2"]').text(dtData);
	$('#POP_JOB_SEARCH').children('option[id="2"]').val(dtDataId);
	$('#POP_JOB_SEARCH').children('option[id="2"]').prop('selected',true);		
	$('#JOB_POP_OK').css("display","inline");
});

});


/*******************************Popup table selected row END*******************************************/




/*****************************POPUP GRADE START HERE***********************/

var jsonUrlGrade = '/enterprisesetup/searchGradePos';



var searchdsCode = '';
var searchdsName = '';
var searchstatus='';

$("#GRADE_SEARCH").on('click', function(e){
	
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	searchstatus=$('#statusList').children("option:selected").val();

	loadGradeData()
	
});
function loadGradeData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlGrade,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"statusGrade":"Active"
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			populateGradeDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateGradeDataTable(data){
	$("#GRADE_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#graderesultSec').css('display', 'none');
		$('#noDataGrade').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#GRADE_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.status,
				dat.datasets,
				dat.gradesid,
				dat.actionid
			]);
		}
		$('#graderesultSec').css('display', 'block');
		$('#noDataGrade').css('display', 'none');
	}
}
/*******************************Popup table selected row start*******************************************/


$('#ENTRY_GRADE').on('change',function(){

var selectObject=$(this).children("option:selected").val();
if(selectObject=='search'){
	$('#id05').css("display","block");
	
}
else if(selectObject!='search'){
	$('#id05').css("display","none");
}
});




$(document).ready(function(){




$('#btnsearchEntryGrade').on('click', function(e){
	$('#id05').css("display","block");
	
});


$('#GRADE_POP_CANCEL').on('click', function(e){
	$('#id05').css("display","none");
	$('#graderesultSec').css("display","none");
	$('#ENTRY_GRADE').children('option[id="1"]').prop('selected',true);	
});


$('#GRADE_POP_OK').on('click', function(e){
	$('#id05').css("display","none");
	$('#divHide').css("display","none");

	var selectEntryGradeId=$('#ENTRY_GRADE').children("option:selected").val();
	if(selectEntryGradeId=='search'){
		$('#ENTRY_GRADE').children('option[id="1"]').prop('selected',true);	
	}else if(selectEntryGradeId!='search'){
	var jsonUrl = '/enterprisesetup/entrygradebind/' +selectEntryGradeId;
	newStatebind="";
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',
		
		dataType: 'json',
		success: function(data){
			newStatebind+='<p><label>Entry Step</label>';
			newStatebind+='<select id="ENTRY_STEP" class="w3-select w3-border" name="gradestepsid" >'
				newStatebind+='<option value="" disabled selected></option>'
				data.forEach(function(n){
					newStatebind+='<option value="'+n.gradestepid+'" >'+n.stepname+'</option>'
				});
			newStatebind+='</select></p>';
			$('#divEntryStep').html(newStatebind);
				
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
	}
});




var table=$('#GRADE_LIST').DataTable();

$('#GRADE_LIST tbody').on('click','tr',function(){
	
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
	var dtDataId=tbldata[4];
	$('#ENTRY_GRADE').val(dtData);
	$('#ENTRY_GRADE').children('option[id="2"]').text(dtData);
	$('#ENTRY_GRADE').children('option[id="2"]').val(dtDataId);
	$('#ENTRY_GRADE').children('option[id="2"]').prop('selected',true);		
	
});

});


/*******************************Popup table selected row END*******************************************/

/*****************************POPUP GRADE END HERE***********************/




var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/EditPosition/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
	updateUrl="/enterprisesetup/edit/EditPosition/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_GRADE_EFFDT_START').val();

updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);
});


	$('#btnerrorOK').on('click', function(e){
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);
	});




					


/************************************************************************/


/******************************************************************************************************/





/******************************************************************************************************/