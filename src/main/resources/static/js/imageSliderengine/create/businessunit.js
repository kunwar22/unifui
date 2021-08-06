
$('#BusinessUnitCancel').on('click', function(e){
				var url = $(this).attr("rm");
				$('#replace_div').load(url);
				});

var bustatus="";
var checkBuid='';
var BuactionId='';
var code='';
var name='';
var datasetid='';
var effdt='';
var jurl="/enterprisesetup/saveBusinessUnit";

$("#BU_CREATE").click(function(){
	

	checkBuid = $('#txtBuid').val();
	BuactionId = $('#txtBuActionid').val();
	
	code = $('#CR_BU_CODE').val();
	name = $('#CR_BU_NAME').val();
	bustatus = $('#statusList').children("option:selected").val();
	datasetid = $('#datasetList').children("option:selected").val();
	effdt = $('#CR_EFFDT').val();
	
	
	code=code.replace(/\s+/g," ").trim();
	name=name.replace(/\s+/g," ").trim();
	
	if(validateBusinessUnit(effdt,name,code,datasetid,bustatus)){
	if(checkBuid!=0){
		loadCorrectBusinessUnitData();
	}
	else if(checkBuid==0){
		
		loadBusinessUnitData();
		
	}
	}
		
	
	});



function loadBusinessUnitData() {

	$.ajax({
		type: 'POST',
		url: jurl,
		dataSrc: '',
		data: {
			"code": code,
			"effectStartDate":effdt,
			"name": name,
			"referenceDataSets": datasetid,
			"statusBU":bustatus
			
		},
		dataType: 'json',
		success: function(data){
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
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

var jsonBusinessUnitUrl="/enterprisesetup/correct/BusinessUnit";


function loadCorrectBusinessUnitData() {
	
	var flag=$("#CR_EFFDT").attr("msg");
	
	$.ajax({
		type: 'POST',
		url: jsonBusinessUnitUrl,
		dataSrc: '',
		data: {
			"actionid": BuactionId,
			"businessunitid":checkBuid,
			"code": code,
			"effectStartDate":effdt,
			"name": name,
			"referenceDataSets": datasetid,
			"statusBU":bustatus,
			"FLAG":	flag
			
		},
		dataType: 'json',
		success: function(data){
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
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


