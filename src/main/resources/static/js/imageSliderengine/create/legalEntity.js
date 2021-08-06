

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
	
	CR_LEGAL_ID = $('#txtLEid').val();
	CR_LEGAL_ACTIONID = $('#txtLEActionid').val();
	
	
	
	if(CR_LEGAL_ID!=0){
		loadCorrectLegalEntityData();
	}
	else if(CR_LEGAL_ID==0){
		loadSaveLegalEntityData();
		
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
			"CR_LE_PAN" : CR_LE_PAN
						
			
		},
		dataType : 'json',
		success : function(data) {

			$('#id02').css("display","block");
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


var jsonCorrectUrl = '/enterprisesetup/LegalEntity/CorrectLegalEntity';
function loadCorrectLegalEntityData() {
	
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
			"CR_LE_PAN" : CR_LE_PAN
						
			
		},
		dataType : 'json',
		success : function(data) {

			$('#id02').css("display","block");
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
					"status": searchStatus
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
						"<ed rm='/enterprisesetup/edit/enterpriseLocation/"+dat.legaladdressid+"' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>",
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
			$('#id01').css("display","none");
			
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
			var dtDataId=tbldata[7];
			$('#CR_LE_ADDRESS').val(dtData);
			$('#CR_LE_ADDRESS').children('option[id="2"]').text(dtData);
			$('#CR_LE_ADDRESS').children('option[id="2"]').val(dtDataId);
			$('#CR_LE_ADDRESS').children('option[id="2"]').prop('selected',true);
			
		
		});
		
	});
	
	
/*******************************Popup table selected row END*******************************************/


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
				$('input#CR_LE_TIN_PAN').attr("style","display:block");
				$('input#CR_LE_IDJURI').val(legalJurisdi);
				$('input#CR_LE_TIN_PAN').val(tinOrPan);
				
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
		
		
		
	});


/****************************************************************************************************/




	var jsonUrlLEG = '/enterprisesetup/searchLegalEntity/getLegalEntityId';



	var legName = '';
	var legCountry = '';
	var legEntityIdentifier='';
	
	$("#LEGAL_SEARCH").on('click', function(e){
		legName =  $("#LEGAL_NAME").val();
		legCountry = $("#LEGAL_COUNTRY").val();
		legEntityIdentifier=$('#LEGAL_ENTITY_IDENTIFIER').val();
		loadLegData();
		
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
				//debugger;
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
					dat.effectstartdate,
					dat.placeofregistration,
					dat.registrationnumber,		
				    "<ed rm='/enterprisesetup/edit/EditLegalEntity/"+dat.legalentityid+"' class='editLegalEntity' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>",
					dat.legalentityid
				]);
			}
			$('#legresultSec').css('display', 'block');
			$('#noDataLEG').css('display', 'none');
		}
	}



