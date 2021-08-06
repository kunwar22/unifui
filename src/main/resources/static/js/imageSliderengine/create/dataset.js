$('#datasetCreate').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#DatasetCancel').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

var dscode='';
var dsname='';
var dsdescr='';
var dscheckactionid='';
var dscheckid=0;

var dsurlSave="/enterprisesetup/saveDataset";

$("#DS_CREATE").click(function(){
	
	    dscheckactionid = $('#txtDatasetActionid').val();
	    dscheckid = $('#txtDatasetid').val();
	
		 dscode=$('#CR_DS_CODE').val();
		 dsname=$('#CR_DS_NAME').val();
		 dsdescr=$('#CR_DS_DESCR').val();
		 dsname=dsname.replace(/\s+/g," ").trim();
		 dscode=dscode.replace(/\s+/g," ").trim();
	
		if(validateDataset(dscode,dsname)){
		
		 if(dscheckid!=0){
				loadCorrectDatasetData();
				
			}
			else if(dscheckid==0){
				loadCreatedatasetData();
				}
				
			}
				
		
	});



function loadCreatedatasetData() {
	
	$.ajax({
		type: 'POST',
		url: dsurlSave,
		dataSrc: '',
		data: {
			"code": dscode,
			"name": dsname,
			"description": dsdescr
			
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


var dsurlcorrect="/enterprisesetup/editdataset/correctdataset/savedataset";
function loadCorrectDatasetData() {
	
	
	$.ajax({
		type: 'POST',
		url: dsurlcorrect,
		dataSrc: '',
		data: {		
			"actionid": dscheckactionid,
			"datasetsid": dscheckid,
			"code": dscode,
			"name": dsname,
			"description": dsdescr
			
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

