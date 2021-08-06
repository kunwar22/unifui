function openTab(evt,legal){
	var i,x,tablinks;
	x=document.getElementsByClassName("legal");
	for(i=0;i<x.length;i++){
		x[i].style.display="none";
	}
	tablinks=document.getElementsByClassName("tablink");
	for(i=0;i<x.length;i++){
		tablinks[i].className=tablinks[i].className.replace("w3-theme","");
	}
	document.getElementById(legal).style.display="block";
	evt.currentTarget.className+=" w3-theme";
	
}

$('#LegCancel').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#LECreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


var countryList='';
var CR_LE_EFFDT = '';
var CR_LE_EFFENDDT = '';
var CR_LE_ADDRESS = '';
var CR_LE_PLCREG = '';
var CR_LE_IDJURI = '';
var CR_LE_NAME = '';
var CR_LE_IDNTI = '';
var CR_LE_PAN = '';
var CR_LE_UNIT = '';
var CR_LE_PAYROLL = '';
var CR_LE_EMPLOYER='';
var CR_LE_REGNO='';
var CR_LE_TIN_PAN='';


var CR_LEGAL_ID = '';
var CR_LEGAL_ACTIONID = '';


ID = $('#txtLEid').val();
ENDDATE = $('#CR_LE_EFFENDDT').val();
		if(ID!=undefined && ID!=0 && ENDDATE!=undefined && ENDDATE!=''){
			if(new Date(ENDDATE)< new Date()){
						var strflag=$("#CR_LE_EFFDT").attr("msg");
						if(strflag=='Correct'){
						$('#LE_CREATE').css("display","none");
						}
						else if(strflag!='Correct'){
							$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
							$('#lblMsg').text("Can't update expired Legal Entity. Click OK to continue.");
							$('#btnOK').toggleClass("w3-button w3-red");
							$('#btnOK').on('click', function(e){
							var url = $(this).attr("rm");
							$('#replace_div').load(url);
							});
						}
				}
		}


var jsonUrl = '/enterprisesetup/LegalEntity/CreateLegalEntity';


$("#LE_CREATE").click(function(){

CR_LE_COUNTRY=$('#countryList').children("option:selected").val();	
CR_LE_EFFDT = $('#CR_LE_EFFDT').val();
CR_LE_EFFENDDT = $('#CR_LE_EFFENDDT').val();
CR_LE_IDJURI = $('#CR_LE_IDJURI').val();	
CR_LE_PAYROLL = $('#CR_LE_PAYROLL').val();
CR_LE_EMPLOYER = $('#CR_LE_EMPLOYER').val();
CR_LE_ADDRESS=$('#CR_LE_ADDRESS').children("option:selected").val();	
CR_LE_IDNTI = $('#CR_LE_IDNTI').val();
CR_LE_NAME = $('#CR_LE_NAME').val();
CR_LE_PLCREG = $('#CR_LE_PLCREG').val();
CR_LE_UNIT = $('#CR_LE_UNIT').val();
CR_LE_PAN = $('#CR_LE_PAN').val();

CR_LE_REGNO = $('#CR_LE_REGNO').val();
CR_LE_TIN_PAN = $('#CR_LE_TIN_PAN').val();
		CR_LE_REGN_DATE =  $('#REGN_DATE').val();
		CR_LE_PF_REGN_DATE = $('#PF_REGN_DATE').val() ;
		CR_LE_PF_SIGNATORY_NAME =$('#PF_SIGNATORY_NAME').val() ;
		CR_LE_PF_NUMBER = $('#PF_NUMBER').val();
		CR_LE_PFRULES_CHECKBOX = $('#PFRULES_CHECKBOX').val();
		CR_LE_ESI_REGN_DATE = $('#ESI_REGN_DATE').val();
		CR_LE_ESI_SIGNATORY_NAME =$('#ESI_SIGNATORY_NAME').val() ;
		CR_LE_ESI_NUMBER = $('#ESI_NUMBER').val();
		CR_LE_PI_REGN_DATE = $('#PI_REGN_DATE').val();
		CR_LE_PI_SIGNATORY_NAME =$('#PI_SIGNATORY_NAME').val() ;
		CR_LE_PI_NUMBER = $('#PI_NUMBER').val();
		CR_LE_IT_CIT_TDS = $('#IT_CIT_TDS').val();
		CR_LE_IT_TAN_NUMBER =$('#IT_TAN_NUMBER').val() ;
		CR_LE_IT_TAN_CIRCLE =$('#IT_TAN_CIRCLE').val() ;
		CR_LE_LAB_SIGNATORY_NAME =$('#LAB_SIGNATORY_NAME').val() ;
		CR_LE_LAB_ADDRESS =$('#LAB_ADDRESS').val();
		
		
		CR_LE_NAME=CR_LE_NAME.replace(/\s+/g," ").trim();
		CR_LE_IDNTI=CR_LE_IDNTI.replace(/\s+/g," ").trim();
		CR_LE_PLCREG=CR_LE_PLCREG.replace(/\s+/g," ").trim();
		
		CR_LE_TIN_PAN=CR_LE_TIN_PAN.replace(/\s+/g," ").trim();
		CR_LE_IT_TAN_NUMBER=CR_LE_IT_TAN_NUMBER.replace(/\s+/g," ").trim();
		CR_LE_IT_TAN_CIRCLE=CR_LE_IT_TAN_CIRCLE.replace(/\s+/g," ").trim();
		
	
CR_LEGAL_ID = $('#txtLEid').val();
CR_LEGAL_ACTIONID = $('#txtLEActionid').val();
if(validateLegal(CR_LE_COUNTRY,CR_LE_NAME,CR_LE_ADDRESS,CR_LE_IDNTI,CR_LE_PLCREG,CR_LE_TIN_PAN,CR_LE_IT_TAN_NUMBER,CR_LE_IT_TAN_CIRCLE,CR_LE_EFFDT)){
if(CR_LEGAL_ID!=0){
	loadCorrectLegalEntityData();
}
else if(CR_LEGAL_ID==0){
	loadSaveLegalEntityData();	
}
}



});



function loadSaveLegalEntityData() {
$.ajax({
	type : 'POST',
	url : jsonUrl,
	dataSrc : '',
	data : {
		
		"CR_LE_REGNO" : CR_LE_REGNO,
		"CR_LE_TIN_PAN" : CR_LE_TIN_PAN,			
		"CR_LE_COUNTRY" : CR_LE_COUNTRY,
		"CR_LE_EFFDT" : CR_LE_EFFDT,
		"CR_LE_EFFENDDT": CR_LE_EFFENDDT,		
		"CR_LE_IDJURI" : CR_LE_IDJURI,
		"CR_LE_PAYROLL" : CR_LE_PAYROLL,
		"CR_LE_EMPLOYER" : CR_LE_EMPLOYER,
		"CR_LE_ADDRESS" : CR_LE_ADDRESS,
		"CR_LE_IDNTI": CR_LE_IDNTI,		
		"CR_LE_NAME" : CR_LE_NAME,
		"CR_LE_PLCREG" : CR_LE_PLCREG,
		"CR_LE_UNIT" : CR_LE_UNIT,
		"CR_LE_PAN" : CR_LE_PAN,
		"CR_LE_REGN_DATE" : CR_LE_REGN_DATE,
		"CR_LE_PF_REGN_DATE" : CR_LE_PF_REGN_DATE,
		"CR_LE_PF_SIGNATORY_NAME" : CR_LE_PF_SIGNATORY_NAME,
		"CR_LE_PF_NUMBER" : CR_LE_PF_NUMBER,
		"CR_LE_PFRULES_CHECKBOX" : CR_LE_PFRULES_CHECKBOX,
		"CR_LE_ESI_REGN_DATE" : CR_LE_ESI_REGN_DATE,
		"CR_LE_ESI_SIGNATORY_NAME" : CR_LE_ESI_SIGNATORY_NAME,
		"CR_LE_ESI_NUMBER" : CR_LE_ESI_NUMBER,
		"CR_LE_PI_REGN_DATE" : CR_LE_PI_REGN_DATE,
		"CR_LE_PI_SIGNATORY_NAME" : CR_LE_PI_SIGNATORY_NAME,
		"CR_LE_PI_NUMBER" : CR_LE_PI_NUMBER,
		"CR_LE_IT_CIT_TDS" : CR_LE_IT_CIT_TDS,
		"CR_LE_IT_TAN_NUMBER" : CR_LE_IT_TAN_NUMBER,
		"CR_LE_IT_TAN_CIRCLE" : CR_LE_IT_TAN_CIRCLE,
		"CR_LE_LAB_SIGNATORY_NAME" : CR_LE_LAB_SIGNATORY_NAME,
		"CR_LE_LAB_ADDRESS" : CR_LE_LAB_ADDRESS
		
					
		
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


var jsonCorrectUrl = '/enterprisesetup/LegalEntity/CorrectLegalEntity';
function loadCorrectLegalEntityData() {
var flag=$("#CR_LE_EFFDT").attr("msg");
$.ajax({
	type : 'POST',
	url : jsonCorrectUrl,
	dataSrc : '',
	data : {
		"CR_LEGAL_ID" : CR_LEGAL_ID,
		"CR_LEGAL_ACTIONID" : CR_LEGAL_ACTIONID,			
		"CR_LE_REGNO" : CR_LE_REGNO,
		"CR_LE_TIN_PAN" : CR_LE_TIN_PAN,			
		"CR_LE_COUNTRY" : CR_LE_COUNTRY,
		"CR_LE_EFFDT" : CR_LE_EFFDT,
		"CR_LE_EFFENDDT": CR_LE_EFFENDDT,		
		"CR_LE_IDJURI" : CR_LE_IDJURI,
		"CR_LE_PAYROLL" : CR_LE_PAYROLL,
		"CR_LE_EMPLOYER" : CR_LE_EMPLOYER,
		"CR_LE_ADDRESS" : CR_LE_ADDRESS,
		"CR_LE_IDNTI": CR_LE_IDNTI,		
		"CR_LE_NAME" : CR_LE_NAME,
		"CR_LE_PLCREG" : CR_LE_PLCREG,
		"CR_LE_UNIT" : CR_LE_UNIT,
		"CR_LE_PAN" : CR_LE_PAN,
		"CR_LE_REGN_DATE" : CR_LE_REGN_DATE,
		"CR_LE_PF_REGN_DATE" : CR_LE_PF_REGN_DATE,
		"CR_LE_PF_SIGNATORY_NAME" : CR_LE_PF_SIGNATORY_NAME,
		"CR_LE_PF_NUMBER" : CR_LE_PF_NUMBER,
		"CR_LE_PFRULES_CHECKBOX" : CR_LE_PFRULES_CHECKBOX,
		"CR_LE_ESI_REGN_DATE" : CR_LE_ESI_REGN_DATE,
		"CR_LE_ESI_SIGNATORY_NAME" : CR_LE_ESI_SIGNATORY_NAME,
		"CR_LE_ESI_NUMBER" : CR_LE_ESI_NUMBER,
		"CR_LE_PI_REGN_DATE" : CR_LE_PI_REGN_DATE,
		"CR_LE_PI_SIGNATORY_NAME" : CR_LE_PI_SIGNATORY_NAME,
		"CR_LE_PI_NUMBER" : CR_LE_PI_NUMBER,
		"CR_LE_IT_CIT_TDS" : CR_LE_IT_CIT_TDS,
		"CR_LE_IT_TAN_NUMBER" : CR_LE_IT_TAN_NUMBER,
		"CR_LE_IT_TAN_CIRCLE" : CR_LE_IT_TAN_CIRCLE,
		"CR_LE_LAB_SIGNATORY_NAME" : CR_LE_LAB_SIGNATORY_NAME,
		"CR_LE_LAB_ADDRESS" : CR_LE_LAB_ADDRESS,
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


/*****************************************************************************************************/



/**************************Pop-up section start here****************************************/
	

	
	
	$(function() {
		$("#LegalEntitySearchList").DataTable({
			'columnDefs': [{
				'targets': [3,4],
				'orderable': false
			}]
		});
		

		var searchCountry = '';
		var searchStatus = '';
		var jsonUrl = '/enterprisesetup/seach/LegalAddress/searchLegalAddress';
		
		
		
		
		if($('#CR_LE_POP_SEARCH').val() != ''){			
			searchCountry = $('#CR_LE_POP_CNTRY').val();
			searchStatus = $('#CR_LE_POP_STATUS').val();
			loadPopupTableData();
			$('#resultSec').css('display', 'block');
		}
		
		$("#CR_LE_POP_SEARCH").click(function(){			
			searchCountry = $('#CR_LE_POP_CNTRY').val();
			searchStatus = $('#CR_LE_POP_STATUS').val();
			
			loadPopupTableData();
			$('#resultSec').css('display', 'block');
		});
			
			
		function loadPopupTableData(){
			$.ajax({
				type: 'POST',
				url: jsonUrl,
				dataSrc: '',
				data: {
					"country": searchCountry,
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
			$("#LegalEntitySearchList").DataTable().clear();
			var dataLength = Object.keys(data).length;
			if(dataLength == 0){
				$('#resultSec').css('display', 'none');
				$('#noData').css('display', 'block');
			} else {
				for(var i=0; i < dataLength; i++){
					var dat = data[i];
					$("#LegalEntitySearchList").dataTable().fnAddData([						
						dat.country,
						dat.addressline1,
						dat.citytown,
						dat.pincode,
						dat.state,
						dat.status,
						dat.legaladdressid
						
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
	
	
	
	
	

	
	
	
	
/**************************Pop-up section End here****************************************/	
	
/*******************************Popup table selected row start*******************************************/
	
	
		$('#CR_LE_ADDRESS').on('change',function(){
		
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
		
		
		
		$('#CR_LE_POP_OK').on('click', function(e){
			$('#resultSec').css("display","none");
			$('#id01').css("display","none");
			$('#CR_LE_POP_OK').css("display","none");
		});
		$('#CR_LE_POP_CANCEL').on('click', function(e){
			$('#resultSec').css("display","none");
			$('#id01').css("display","none");
			$('#CR_LE_ADDRESS').children('option[id="1"]').prop('selected',true);
			$('#CR_LE_POP_OK').css("display","none");	
		});
		
		
	
		var table=$('#LegalEntitySearchList').DataTable();
		
		$('#LegalEntitySearchList tbody').on('click','tr',function(){
		
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
			
			var dtData=tbldata[1];
			var dtDataId=tbldata[6];
			$('#CR_LE_ADDRESS').val(dtData);
			$('#CR_LE_ADDRESS').children('option[id="2"]').text(dtData);
			$('#CR_LE_ADDRESS').children('option[id="2"]').val(dtDataId);
			$('#CR_LE_ADDRESS').children('option[id="2"]').prop('selected',true);
			$('#CR_LE_POP_OK').css("display","inline-block");
			
		
		});
		
	});
	
	
/*******************************Popup table selected row END*******************************************/




	//time zone start


	$('#countryList').on('change',function(){
		var selectCountryId=$(this).children("option:selected").val();
		var jsonUrl = '/enterprisesetup/seach/LegalJurisdiction/searchLegalJurisdiction/' +selectCountryId;
		
		
		$.ajax({
			type: 'GET',
			url: jsonUrl,
			dataSrc: '',
			
			dataType: 'json',
			success: function(data){
			
				var legalJurisdiction = '';
				var legalJurisdi = '';
				var tinOrPan='';
				
				var len = data.length;
				$('label#Identi').empty();
				legalJurisdiction += data.identifyingjurisdiction;
				legalJurisdi+=data.legaljurisdictionid;
				tinOrPan+=data.tinorpan;
				$('label#Identi').text(legalJurisdiction);
				$('label#tinpan').text(tinOrPan);
				$('#SPAN_DISP').text(" *");
				$('input#CR_LE_TIN_PAN').attr("style","display:block");
				$('input#CR_LE_IDJURI').val(legalJurisdi);
				},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
				alert("First create Legal Jurisdiction for the country you have selected.");
				var url = "enterprisesetup/managelegaljurisdiction";
				$('#replace_div').load(url);
				
			}
		});
		
		
		
	});




	//time zone end


/****************************************************************************************************/




	var jsonUrlLEG = '/enterprisesetup/searchLegalEntity/getLegalEntityId';



	var legName = '';
	var legCountry = '';
	var legEntityIdentifier='';
	
	$("#LEGAL_SEARCH").on('click', function(e){
		legName =  $("#LEGAL_NAME").val();
		legCountry = $("#LEGAL_COUNTRY").val();
		legEntityIdentifier=$('#LEGAL_ENTITY_IDENTIFIER').val();
		loadLegData()
		
	});
	
	function loadLegData(){
		$.ajax({
			type: 'POST',
			url: jsonUrlLEG,
			dataSrc: '',
			data: {
				
				"COUNTRY": legCountry,
				"ENTITY":legEntityIdentifier,
				"NAME": legName
				
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateLegDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}

	function populateLegDataTable(data){
		$("#LegalList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		
		if(dataLength == 0){
			$('#legresultSec').css('display', 'none');
			$('#noDataLEG').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#LegalList").dataTable().fnAddData([
					dat.name,
					dat.country,
					dat.legalentityidentifier,
					dat.effectstartdate.substring(0,10),
					dat.effectenddate.substring(0,10),
					"<select id=edit"+dat.legalentityid+" onchange=editFunction("+dat.legalentityid+",edit"+dat.legalentityid+")><option hidden disabled selected>Edit</option><option>Correct</option><option>Update</option></select>",	
				  	dat.legalentityid
				]);
			}
			$('#legresultSec').css('display', 'block');
			$('#noDataLEG').css('display', 'none');
		}
	}
	
var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/EditLegalEntity/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		if(selectObject=='Correct'){
			$("#replace_div").load(correctUrl);
			
		
			
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
	updateUrl="/enterprisesetup/edit/EditLegalEntity/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#UP_LE_EFFDT').val();

updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);
});


	$('#btnerrorOK').on('click', function(e){
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);
	});
		