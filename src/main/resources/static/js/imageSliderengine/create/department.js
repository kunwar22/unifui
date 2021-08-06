

$('#DeptCancel').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});

var CR_DEPT_EFFDT='';
var CR_DEPT_CODE = '';
var CR_DEPT_DATASET = '';
var CR_DEPT_NAME = '';
var CR_DEPT_STATUS = '';

var CR_DEPT_ID = '';
var CR_DEPT_ACTIONID = '';



var jsonUrl = '/enterprisesetup/createDepartmentResource';

$("#DEPT_CREATE").click(function(){
	
	CR_DEPT_NAME = $('#CR_DEPT_NAME').val();
	CR_DEPT_CODE = $('#CR_DEPT_CODE').val();
	CR_DEPT_DATASET = $('#CR_DEPT_DATASET').val();
	CR_DEPT_EFFDT = $('#CR_DEPT_EFFDT').val();
	CR_DEPT_STATUS=$('#statusList').children("option:selected").val();
	
	CR_DEPT_NAME=CR_DEPT_NAME.replace(/\s+/g," ").trim();
	CR_DEPT_CODE=CR_DEPT_CODE.replace(/\s+/g," ").trim();
	
	CR_DEPT_ID = $('#txtDpid').val();
	CR_DEPT_ACTIONID = $('#txtDpActionid').val();
	
	
	if(validateDepartment(CR_DEPT_NAME,CR_DEPT_CODE,CR_DEPT_DATASET,CR_DEPT_EFFDT,CR_DEPT_STATUS)){
	if(CR_DEPT_ID!=0){
		loadCorrectDepartmentData();
	}
	else if(CR_DEPT_ID==0){
		loadSaveDeptData();
		
	}
	}
	
	
		
	
	
});



function loadSaveDeptData() {
	$.ajax({
		type : 'POST',
		url : jsonUrl,
		dataSrc : '',
		data : {
			"CR_DEPT_ID" : CR_DEPT_ID,
			"CR_DEPT_ACTIONID" : CR_DEPT_ACTIONID,
			"CR_DEPT_NAME" : CR_DEPT_NAME,
			"CR_DEPT_CODE" : CR_DEPT_CODE,
			"CR_DEPT_DATASET": CR_DEPT_DATASET,		
			"CR_DEPT_EFFDT" : CR_DEPT_EFFDT,
			"CR_DEPT_STATUS" : CR_DEPT_STATUS,
						
			
		},
		dataType : 'json',
		success : function(data) {
				if(data.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}
				if(data.status!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
		},
		error : function(e) {
			
			var errors=JSON.parse(e.responseText).errors;
			var msg="";
			var i=0;
			for(;i<errors.length;i++)
				{
				msg=msg+errors[i].defaultMessage+"/n";
				}
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});

}




var DprtCorrectUrl = '/enterprisesetup/edit/EditDepartment/correctDepartment';
function loadCorrectDepartmentData() {
	var flag=$("#CR_DEPT_EFFDT").attr("msg");
	
	$.ajax({
		type : 'POST',
		url : DprtCorrectUrl,
		dataSrc : '',
		data : {
			"CR_DEPT_ID" : CR_DEPT_ID,
			"CR_DEPT_ACTIONID" : CR_DEPT_ACTIONID,			
			"CR_DEPT_NAME" : CR_DEPT_NAME,
			"CR_DEPT_CODE" : CR_DEPT_CODE,
			"CR_DEPT_DATASET": CR_DEPT_DATASET,		
			"CR_DEPT_EFFDT" : CR_DEPT_EFFDT,
			"CR_DEPT_STATUS" : CR_DEPT_STATUS,
			"FLAG":	flag
						
			
		},
		dataType : 'json',
		success : function(data) {
				if(data.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}
				if(data.status!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
		},
		error : function(e) {
			
			var errors=JSON.parse(e.responseText).errors;
			var msg="";
			var i=0;
			for(;i<errors.length;i++)
				{
				msg=msg+errors[i].defaultMessage+"/n";
				}
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});

}

