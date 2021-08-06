$(document).ready(function(){
	
	$('#mediadvance').DataTable();
	loadMedicalAdvances()
});

/* Medical Advance Request page loads below */
function loadMediAdvReq(){
	/*alert("in");*/
	var url = "/reimbursement/medicaladvancerequest";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$('#replace_div').load(url);
}

/* Code for search page starts */

var jsonSearchMediadvUrl = '/reimbursement/getMedicalAdvances/';
function loadMedicalAdvances()
{
	//debugger;
	$.ajax({
		type: 'GET',
		url: jsonSearchMediadvUrl,
		dataSrc: '',
		data: {
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;			
			populateMediAdvDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function populateMediAdvDataTable(data)
{
	$("#mediadvance").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){		
		$('#search_result').css('display', 'none');
		$('#noDataMediAdv').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			if(dat.status == "Saved" || dat.status == "saved"){
				str = '<i class="fa fa-pen" id="edit" onclick="mediadv_details(' + dat.claimid + ',\'edit\')">';
			}else{
				str='<i class="fa fa-pen" id="edit" style="color:grey">';
			}
			$("#mediadvance").dataTable().fnAddData([				
				dat.claimid,
				dat.patientname,	
				dat.illnessdescription,
				dat.treatmentfrom,
				dat.treatmentto,
				dat.hospitalname,
				dat.hospitaltypename,
				dat.claimamt,
				dat.payadvanceto,
				/*dat.createddate,*/
				dat.status,	
				dat.approvedamt,				
				"",
				'<i id="view" class="fa fa-eye" onclick="mediadv_details('+dat.claimid+',\'view\')"></i>',
				str
				]);
		}
		$('#electricityreimbursement').css('display', 'block');
		$('#noDataMediAdv').css('display', 'none');
	}
}

function mediadv_details(mediclaimid,display_mode)
{
	//var display_mode=event.toElement.id;
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/modifyMedicalAdvance/"+mediclaimid+"/"+display_mode,
		success:function(result){
			//console.log("sucesssssss");
			$('#replace_div').html(result);			
		},
		error:function(e){
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
	
	/*var url = "/reimbursement/editTADKReimbursement/"+tadkclaimid+"/"+display_mode;
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);*/
}

/* Code for search page ends */

function backBtnFunc(){
	var url = "/reimbursement/medicalAdvance";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}