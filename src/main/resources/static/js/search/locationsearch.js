	
var newStatebind="";
$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#locationCancel').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});



$('#locCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


$(function() {
	$("#LOCATION_LIST").DataTable({
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
	
	if($('#LOC_SEARCH').val() != ''){
		searchName = $('#LOC_NAME').val();
		searchCode = $('#LOC_CODE').val();
		searchCountry = $('#countrySearch').val();
		searchStatus = $('#CR_STATUS').val();
		$('#resultSec').css('display', 'block');
	}
	
	$("#LOC_SEARCH").click(function(){
		
		searchName = $('#LOC_NAME').val();
		searchCode = $('#LOC_CODE').val();
		searchCountry = $('#countrySearch').val();
		searchStatus = $('#CR_STATUS').val();
		loadData1();
		$('#resultSec').css('display', 'block');
	});
		
	function loadData1(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"name": searchName,
				"code": searchCode,
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
		$("#locationList").DataTable().clear();
		
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#locationList").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.country,
					dat.state,
					dat.status,
					"<select id=edit"+dat.locationid+" onchange=editFunction("+dat.locationid+",edit"+dat.locationid+")><option hidden disabled selected>Edit</option><option>Correct</option><option>Update</option></select>",	
					]);
			}
		
			$('#resultSec').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}
});






$(function() {
	
	
	$("#userList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	var searchUserId = '';
	var searchEmplId = '';
	var searchEmailId = '';
	
	var jsonUrl = '/searchLocation';
	
	if($('#userId').val() != ''){
		
		searchUserId = 'agra';
		searchEmplId = $('#emplId').val();
		searchEmailId = $('#emailId').val();
		}
	
	$("#userSearch").click(function(){
		
		searchUserId = 'agra';
		searchEmplId = $('#emplId').val();
		searchEmailId = $('#emailId').val();
		loadData();
		$('#resultSec').css('display', 'block');
	});
		
	

	
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"userid": searchUserId,
				"emplid": searchEmplId,
				"emailid": searchEmailId
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
		$("#userList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#userList").dataTable().fnAddData([
					dat.loginid,
					dat.emplid,
					dat.emailid,
					dat.isactive,
					"<ed rm='/uniftools/security/userprofiles/userprofile/edit/userprofile/"+dat.loginid+"' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
				]);
			}
			$('#resultSec').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}
});





$('#countryList').on('change',function(){
	var selectCountryId=$(this).children("option:selected").val();
	var jsonUrl = '/enterprisesetup/statebind/' +selectCountryId;
	newStatebind="";
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',
		
		dataType: 'json',
		success: function(data){
			newStatebind+='<p><label>State</label>';
			newStatebind+='<select id="stateList" class="w3-select w3-border" name="option" >'
				newStatebind+='<option value="" disabled selected></option>'
				data.forEach(function(n){
					newStatebind+='<option value="'+n.id+'" >'+n.description+'</option>'
				});
			newStatebind+='</select></p>';
			$('#divState').html(newStatebind);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
});


$('#countryList').on('change',function(){
	var selectCountryId=$(this).children("option:selected").val();
	var jsonUrl = '/enterprisesetup/timezone/' +selectCountryId;
	
	
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',
		
		dataType: 'json',
		success: function(data){
		
			var newtimeZone = '';
			var len = data.length;
			$('select#timezone').empty();
				newtimeZone+='<option  disabled selected ></option>'
			for(var i=0; i<len; i++){
				newtimeZone += '<option value="'+ data[i].id+'"  >'+data[i].description+'</option>';
			}
			
			$('select#timezone').html(newtimeZone);
	
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
	
	
	
});




var CR_LOC_NAME = '';
var CR_LOC_CODE = '';
var CR_EFFDT = '';
var CR_EMAIL = '';
var CR_FAX = '';
var CR_TELEPHONE='';

var CR_selectCountryId = '';
var CR_selectStateId = '';
var CR_selectTimeZoneId = '';
var CR_STATUS = '';
var CR_selectAddressTypeId='';
var checkLocid='';
var actionId='';
var CR_PIN='';

var jsonlocationcreateUrl = '/enterprisesetup/enterpriseCreateLocation';




$("#locationCreate").click(function(){
	
	checkLocid=$('#txtLocid').val();
	
	actionId=$('#txtActionid').val();
	
	
	CR_LOC_NAME = $('#CR_LOC_NAME').val();
	CR_LOC_CODE = $('#CR_LOC_CODE').val();
	
	CR_LOC_NAME=CR_LOC_NAME.replace(/\s+/g," ").trim();
	CR_LOC_CODE=CR_LOC_CODE.replace(/\s+/g," ").trim();
	
	CR_EFFDT = $('#CR_EFFDT').val();
	CR_EMAIL = $('#CR_EMAIL').val();
	CR_FAX = $('#CR_FAX').val();
	CR_TELEPHONE = $('#CR_TELEPHONE').val();
	 CR_selectCountryId=$('#countryList').children("option:selected").val();
	 CR_selectStateId=$('#stateList').children("option:selected").val();
	 CR_selectTimeZoneId=$('#timezone').children("option:selected").val();
	 CR_STATUS=$('#CR_STATUS').children("option:selected").val();
	 CR_selectAddressTypeId=$('#addressType').children("option:selected").val();
	 
	 CR_ADDRESS1 = $('#CR_ADDRESS1').val();
	 CR_ADDRESS3 = $('#CR_ADDRESS3').val();
	 CR_ADDRESS2 = $('#CR_ADDRESS2').val();
	 CR_CITY = $('#CR_CITY').val();
	 CR_PIN=$('#CR_PIN').val();
	
	if(validateLocation(CR_EFFDT,CR_LOC_NAME,CR_LOC_CODE,CR_selectCountryId,CR_STATUS,CR_ADDRESS1,CR_TELEPHONE,CR_PIN,CR_EMAIL,CR_CITY)){
	if(checkLocid!=0){
		loadCorrectLocationData();
	}
	else if(checkLocid==0){
		loadLocationData();
		
	}
	}
	
	

});



function loadLocationData() {
	
	$.ajax({
		type : 'POST',
		url : jsonlocationcreateUrl,
		dataSrc : '',
		data : {
			"CR_LOC_NAME" : CR_LOC_NAME,
			"CR_LOC_CODE" : CR_LOC_CODE,
			"CR_EFFDT": CR_EFFDT,		
			"CR_EMAIL" : CR_EMAIL,
			"CR_FAX" : CR_FAX,
			"CR_TELEPHONE": CR_TELEPHONE,				
			"CR_selectCountryId" : CR_selectCountryId,				
			"CR_selectStateId" : CR_selectStateId,
			"CR_selectTimeZoneId" : CR_selectTimeZoneId,
			"CR_STATUS" : CR_STATUS,
			"CR_ADDRESS1": CR_ADDRESS1,
			"CR_ADDRESS3":CR_ADDRESS3,
			"CR_ADDRESS2":CR_ADDRESS2,
			"CR_CITY":CR_CITY,
			"CR_selectAddressTypeId":CR_selectAddressTypeId,
			"CR_PIN":CR_PIN
			
			
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
					$('#id01').css("display","none");
					});
				}
			
		},
		error : function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});

}

var jsonUrl="/enterprisesetup/enterpriseCorrectLocation";
function loadCorrectLocationData() {
	
	var flag=$("#CR_EFFDT").attr("msg");
	$.ajax({
		type : 'POST',
		url : jsonUrl,
		dataSrc : '',
		data : {
			"CR_LOC_NAME" : CR_LOC_NAME,
			"CR_LOC_CODE" : CR_LOC_CODE,
			"CR_EFFDT": CR_EFFDT,		
			"CR_EMAIL" : CR_EMAIL,
			"CR_FAX" : CR_FAX,
			"CR_TELEPHONE": CR_TELEPHONE,				
			"CR_selectCountryId" : CR_selectCountryId,				
			"CR_selectStateId" : CR_selectStateId,
			"CR_selectTimeZoneId" : CR_selectTimeZoneId,
			"CR_STATUS" : CR_STATUS,
			"CR_ADDRESS1": CR_ADDRESS1,
			"CR_ADDRESS3":CR_ADDRESS3,
			"CR_ADDRESS2":CR_ADDRESS2,
			"CR_CITY":CR_CITY,
			"CR_LOCATIONID":checkLocid,
			"CR_ACTIONID":actionId,
			"CR_selectAddressTypeId":CR_selectAddressTypeId,
			"CR_PIN":CR_PIN,
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
					$('#id01').css("display","none");
					});
				}
		},
		error : function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});

}




/******************************************************************************************/




var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/enterpriseLocation/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
		updateUrl="/enterprisesetup/edit/enterpriseLocation/"+leID+"/";
	
		
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


/*****************************************************************************************/

