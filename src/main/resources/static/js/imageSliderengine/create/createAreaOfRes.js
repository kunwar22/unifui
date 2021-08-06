//$('#replace_div').html(data);
//alert("hi person"+pnum);

//$('#txtAORPersonNumber').val(personnum);


$('#AORCancel').on('click', function(e){
//var urlcancel = $(this).attr("rm")+"/"+pnum+"/"+pname.replace(/ /g,"_");
var urlcancel = $(this).attr("rm");
$('#div_replace').load(urlcancel);
});	


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
		
		
		
		
		
		$("#CR_AOR_POP_SEARCH").click(function(){
			
			searchName = $('#CR_AOR_POP_NAME').val();
			searchCode = $('#CR_AOR_POP_CODE').val();
			searchCountry = $('#CR_AOR_POP_CNTRY').val();
			searchStatus = $('#CR_AOR_POP_STATUS').val();
			
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
	
	
		$('#CR_AOR_LOC_CODE').on('change',function(){
		
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
		
		
		
		$('#CR_AOR_POP_OK').on('click', function(e){
			$('#id01').css("display","none");
			$('#resultSec').css("display","none");
			$('#CR_AOR_POP_OK').css("display","none");
			
		});
		$('#CR_AOR_POP_CANCEL').on('click', function(e){
			$('#id01').css("display","none");
			$('#resultSec').css("display","none");
			$('#CR_AOR_LOC_CODE').children('option[id="1"]').prop('selected',true);	
			
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
			$('#CR_AOR_LOC_CODE').val(dtData);
			$('#CR_AOR_LOC_CODE').children('option[id="2"]').text(dtData);
			$('#CR_AOR_LOC_CODE').children('option[id="2"]').val(dtDataId);
			$('#CR_AOR_LOC_CODE').children('option[id="2"]').prop('selected',true);	
			$('#CR_AOR_POP_OK').css('display','inline');	
			
		});
		
	});
	
	
	
	
/*******************************Popup table selected row END*******************************************/






/******************************************************************************************************/



function ajaxPost()
{
//debugger;
//	dynamicNameAttributeGrade();
	
	//alert("button click");
	
	
	CR_AOR_ID = $('#txtAORActionid').val();
	CR_AOR_ACTIONID = $('#txtAORid').val();
	
//	alert($('#CR_EFFDT').val());
	
	if(CR_AOR_ID!=0){
		loadCorrectAORData();
	}
	else if(CR_AOR_ID==0){
		//alert(checkBuid);
		loadSaveAORData();
		
	}
	
	
	
	
};



function loadSaveAORData() {
//alert("save");
//debugger;
var fd = $("#AOR_SAVE").serialize();

$.ajax({
		url: "/aor/saveAreaOfResponsibility",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
			 $('#div_replace').html(data);
			if(resultfinal=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text("Aor created sucessfully Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					//var url = $(this).attr("rm")+"/"+pnum+"/"+pname.replace(/ /g,"_");
					var url = $(this).attr("rm");
				//	alert(url);
					$('#div_replace').load(url);
					});
				}
				if(resultfinal!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text("Faild to Create Aor Click OK to continue again.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
				if(resultfinal=="error"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text("Aor name is already exist.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
				
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
			/*if(resultfinal!="error"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text("Faild to Create Aor Click OK to continue again.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}*/
		   	}
	});

};

function loadCorrectAORData(){
	//debugger;
var fd = $("#AOR_SAVE").serialize();
//alert("correct");
$.ajax({
		url: "/aor/correctAreaOfResponsibility",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
			 $('#div_replace').html(data);
			if(resultfinal=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text("Aor created sucessfully Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					//var url = $(this).attr("rm")+"/"+pnum+"/"+pname.replace(/ /g,"_");
					var url = $(this).attr("rm");
				//	alert(url);
					$('#div_replace').load(url);
					});
				}
				if(resultfinal!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text("Faild to Create Aor Click OK to continue again.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
				if(resultfinal=="error"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text("Aor name is already exist.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
				
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});
};



/*********************************************************************************** */


/**************************Pop-up Legal Entity SEARCH start here****************************************/
	

	
	
	$(function() {
		$("#LegalEntityList").DataTable({
			'columnDefs': [{
				'targets': [3,4],
				'orderable': false
			}]
		});
		
   var jsonUrlLEG = '/enterprisesetup/searchLegalEntity/getLegalEntityId';

	var legName = '';
	var legCountry = '';
	var legEntityIdentifier='';
		
		
		
		
		
	$("#CR_AOR_LEG_POP_SEARCH").click(function(){
		legName =  $("#CR_AOR_LEG_POP_NAME").val();
		legCountry = $("#CR_AOR_LEG_POP_COUNTRY").val();
		legEntityIdentifier=$('#CR_AOR_LEG_POP_ENTITY').val();
			//alert(""+legName+""+legCountry+""+legEntityIdentifier);
			loadLegData();
			$('#LegalEntityresultSec').css('display', 'block');
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
			
			
		$("#LegalEntityList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#LegalEntityresultSec').css('display', 'none');
			$('#noDataLegalEntity').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				//alert(dat.name);
				$("#LegalEntityList").dataTable().fnAddData([
					dat.name,
					dat.country,
					dat.legalentityidentifier,
					dat.effectstartdate,
					dat.placeofregistration,
					dat.registrationnumber,					
					dat.legalentityid
					
				]);
			//	alert("in"+dat.name);
			}
			$('#LegalEntityresultSec').css('display', 'block');
			$('#noDataLegalEntity').css('display', 'none');
		}
	}
	});

	$(document).on('click', 'ed', function(e){
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});
	
	
	
	
	

	
	
	
	
/**************************Pop-up section End here****************************************/	
	
/*******************************Popup table selected row start*******************************************/
	
	
		$('#legalEmployer').on('change',function(){
		
		var selectObject=$(this).children("option:selected").val();
		if(selectObject=='search'){
			$('#id02').css("display","block");
		}
		else if(selectObject!='search'){
			$('#id02').css("display","none");
		}
	});
	
	
	
	
	$(document).ready(function(){
		
		
		
		
		$('#btnsearch').on('click', function(e){
			$('#id02').css("display","block");
			
		});
		
		
		
		$('#CR_AOR_LEG_POP_OK').on('click', function(e){
			$('#id02').css("display","none");
			$('#LegalEntityresultSec').css("display","none");
			$('#CR_AOR_LEG_POP_OK').css("display","none");
			
		});
		$('#CR_AOR_LEG_POP_CANCEL').on('click', function(e){
			$('#id02').css("display","none");
			$('#resultSec').css("display","none");
			$('#legalEmployer').children('option[id="1"]').prop('selected',true);	
			
		});

		
		
		var table=$('#LegalEntityList').DataTable();
		
		$('#LegalEntityList tbody').on('click','tr',function(){
			
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
			$('#legalEmployer').val(dtData);
			$('#legalEmployer').children('option[id="2"]').text(dtData);
			$('#legalEmployer').children('option[id="2"]').val(dtDataId);
			$('#legalEmployer').children('option[id="2"]').prop('selected',true);	
			$('#CR_AOR_LEG_POP_OK').css('display','inline');	
			
		});
		
	});
	
	
	
	
/*******************************Popup table selected row END*******************************************/
