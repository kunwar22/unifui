

$('#JobCancel').on('click', function(e){
				var url = $(this).attr("rm");
				$('#replace_div').load(url);
				});

var CR_JOB_NAME='';
var CR_JOB_EFFDT = '';
var CR_JOBID = '';
var CR_JOB_CODE = '';
var CR_JOB_DATASET = '';
var CR_STATUS = '';

var CR_JOB_ID='';
var CR_JOB_ACTIONID='';

var jsonUrl = '/enterprisesetup/createJobResource';



$("#JOB_CREATE").click(function(){
	
	
	CR_JOB_NAME = $('#CR_JOB_NAME').val();
	CR_JOB_EFFDT = $('#CR_JOB_EFFDT').val();
	CR_JOBID = $('#CR_JOBID').val();
	CR_JOB_CODE = $('#CR_JOB_CODE').val();
	CR_JOB_DATASET = $('#CR_JOB_DATASET').val();
	CR_STATUS=$('#statusList').children("option:selected").val();
	
	CR_JOB_ID = $('#txtJbid').val();
	CR_JOB_ACTIONID = $('#txtJbActionid').val();
	
	CR_JOB_NAME=CR_JOB_NAME.replace(/\s+/g," ").trim();
	CR_JOB_CODE=CR_JOB_CODE.replace(/\s+/g," ").trim();
	
	if(validateJob(CR_JOB_NAME,CR_JOB_EFFDT,CR_JOBID,CR_JOB_CODE,CR_JOB_DATASET,CR_STATUS)){
	if(CR_JOB_ID!=0){
		loadCorrectJobData();
	}
	else if(CR_JOB_ID==0){
		loadSaveJobData();
		
	}
	}
	

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
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});

}









var JobCorrectUrl = '/enterprisesetup/edit/EditJob/correctJob';

function loadCorrectJobData() {
	
	var flag=$("#CR_JOB_EFFDT").attr("msg");
	
	
	$.ajax({
		type : 'POST',
		url : JobCorrectUrl,
		dataSrc : '',
		data : {
			"CR_JOB_ID" : CR_JOB_ID,
			"CR_JOB_ACTIONID" : CR_JOB_ACTIONID,			
			"CR_JOB_NAME" : CR_JOB_NAME,
			"CR_JOB_EFFDT" : CR_JOB_EFFDT,
			"CR_JOBID": CR_JOBID,		
			"CR_JOB_CODE" : CR_JOB_CODE,
			"CR_JOB_DATASET" : CR_JOB_DATASET,
			"CR_STATUS": CR_STATUS,
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
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});

}







