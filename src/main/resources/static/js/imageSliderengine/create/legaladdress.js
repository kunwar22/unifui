

$('#LegAddrCancel').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

var addr1='';
var addr2='';
var addr3='';
var selectCountryId='';
var selectStateId='';
var cityval='';
var pinval='';
var pinval='';
var timeZoneval='';
var jurl='';

var lacheckactionid='';
var lacheckid=0;
var status='';



	
$("#LA_CREATE").click(function(){
	     lacheckid=$('#txtLegaladdressid').val();
	     lacheckactionid=$('#txtLegalActionid').val();
	
		 addr1=$('#CR_LA_ADDR1').val();
		 addr2=$('#CR_LA_ADDR2').val();
		 addr3=$('#CR_LA_ADDR3').val();
		 selectCountryId=$('#countryList').children("option:selected").val();
		 selectStateId=$('#stateList').children("option:selected").val();
		 cityval=$('#CR_LA_CITY').val();
		 pinval=$('#CR_LA_PIN').val();
		 timeZoneval=$('#timezone').children("option:selected").val();
		 status=$('#statusList').val();
		
		addr1=addr1.replace(/\s+/g," ").trim();
		pinval=pinval.replace(/\s+/g," ").trim();
		 
	if(validateLegalAddress(addr1,pinval,selectCountryId,status,cityval)){
		 if(lacheckid!=0){
				loadCorrectlegaladdressData();
			}
			else if(lacheckid==0){
		  	loadCreatelegaladdressData();
				
			}
			}
		
		
	
	});
	


jurl="/enterprisesetup/saveLegalAddress";
function loadCreatelegaladdressData() {
	
	$.ajax({
		type: 'POST',
		url: jurl,
		dataSrc: '',
		data: {
			"legaladdressid": lacheckid,
			"actionid": lacheckactionid,			
			"addressLine1": addr1,
			"addressLine2": addr2,
			"addressLine3": addr3,
			"country": selectCountryId,
			"state": selectStateId,
			"cityTown": cityval,
			"pinCode": pinval,
			"status": status,
			"timeZone": timeZoneval
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



correctlegaladdress="/enterprisesetup/edit/legaladdress/correctlegalAddress";

function loadCorrectlegaladdressData() {
	
	$.ajax({
		type: 'POST',
		url: correctlegaladdress,
		dataSrc: '',
		data: {
			"legaladdressid": lacheckid,
			"actionid": lacheckactionid,
			"addressLine1": addr1,
			"addressLine2": addr2,
			"addressLine3": addr3,
			"country": selectCountryId,
			"state": selectStateId,
			"cityTown": cityval,
			"pinCode": pinval,
			"status": status,
			"timeZone": timeZoneval
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
			newStatebind+='<select id="stateList" class="w3-select w3-border" name="option"  tabindex="9" >'
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
			alert("error");
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
	
	
	
});


