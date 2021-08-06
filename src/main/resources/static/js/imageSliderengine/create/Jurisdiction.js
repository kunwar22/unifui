

$('#juriCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#JURI_CANCEL').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});




var CR_JUDIS_EFFDT='';
var CR_JUDIS_LEGISLATIVE_CODE = '';
var CR_JUDIS_COUNTRY = '';
var CR_JUDIS_STATUS = '';
var CR_JUDIS_EFFT_ENDDATE = '';
var CR_JUDIS_LEG_CATOG = '';
var CR_JUDIS_NAME = '';
var CR_JUDIS_IDENTIFYING = '';

var CR_JUDIS_ID = '';
var CR_JUDIS_ACTIONID = '';


var jsonUrl = '/enterprisesetup/createJurisdictionResource';




$("#JURI_CREATE").click(function(){
	
//alert("in");
	
	CR_JUDIS_ID = $('#txtJuriid').val();
	CR_JUDIS_ACTIONID = $('#txtJuriActionid').val();
	
	CR_JUDIS_EFFDT = $('#CR_JUDIS_EFFDT').val();
	CR_JUDIS_EFFT_ENDDATE = $('#CR_JUDIS_EFFT_ENDDATE').val();
	CR_JUDIS_LEG_CATOG = $('#CR_JUDIS_LEG_CATOG').children("option:selected").val();
	CR_JUDIS_NAME = $('#CR_JUDIS_NAME').val();
	CR_JUDIS_NAME=CR_JUDIS_NAME.replace(/\s+/g," ").trim();
	CR_JUDIS_LEGISLATIVE_CODE = $('#CR_JUDIS_LEGISLATIVE_CODE').children("option:selected").val();
	CR_JUDIS_IDENTIFYING=$('#CR_JUDIS_IDENTIFYING').children("option:selected").val();
	CR_JUDIS_COUNTRY=$('#CR_JUDIS_COUNTRY').children("option:selected").val();
	CR_JUDIS_STATUS=$('#CR_JUDIS_STATUS').children("option:selected").val();
	
	if(validateJurisdiction(CR_JUDIS_EFFDT,CR_JUDIS_LEGISLATIVE_CODE,CR_JUDIS_LEG_CATOG,CR_JUDIS_COUNTRY,CR_JUDIS_NAME,CR_JUDIS_STATUS,CR_JUDIS_IDENTIFYING)){
	if(CR_JUDIS_ID!=0){
		loadCorrectDeptData();
	}
	else if(CR_JUDIS_ID==0){
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
			"CR_JUDIS_EFFDT" : CR_JUDIS_EFFDT,
			"CR_JUDIS_EFFT_ENDDATE" : CR_JUDIS_EFFT_ENDDATE,
			"CR_JUDIS_LEG_CATOG": CR_JUDIS_LEG_CATOG,		
			"CR_JUDIS_NAME" : CR_JUDIS_NAME,
			"CR_JUDIS_LEGISLATIVE_CODE" : CR_JUDIS_LEGISLATIVE_CODE,
			"CR_JUDIS_IDENTIFYING": CR_JUDIS_IDENTIFYING,		
			"CR_JUDIS_COUNTRY" : CR_JUDIS_COUNTRY,
			"CR_JUDIS_STATUS" : CR_JUDIS_STATUS,
						
			
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


var juriCorrectUrl = '/enterprisesetup/edit/EditJurisdiction/correctJurisdiction';

function loadCorrectDeptData() {
	$.ajax({
		type : 'POST',
		url : juriCorrectUrl,
		dataSrc : '',
		data : {
			"CR_JUDIS_ID" : CR_JUDIS_ID,
			"CR_JUDIS_ACTIONID" : CR_JUDIS_ACTIONID,			
			"CR_JUDIS_EFFDT" : CR_JUDIS_EFFDT,
			"CR_JUDIS_EFFT_ENDDATE" : CR_JUDIS_EFFT_ENDDATE,
			"CR_JUDIS_LEG_CATOG": CR_JUDIS_LEG_CATOG,		
			"CR_JUDIS_NAME" : CR_JUDIS_NAME,
			"CR_JUDIS_LEGISLATIVE_CODE" : CR_JUDIS_LEGISLATIVE_CODE,
			"CR_JUDIS_IDENTIFYING": CR_JUDIS_IDENTIFYING,		
			"CR_JUDIS_COUNTRY" : CR_JUDIS_COUNTRY,
			"CR_JUDIS_STATUS" : CR_JUDIS_STATUS,
						
			
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












