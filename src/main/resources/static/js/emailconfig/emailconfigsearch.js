$(document).ready(function(){
	loadEmailConfigData();

});

function loadEmailConfigurationPage()
{
	var url = "/email/configEmail";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
}

var searchemailconfigUrl = '/email/getEmailConfigs';

function loadEmailConfigData()
{

	$.ajax({
		type: 'GET',
		url: searchemailconfigUrl,
		dataSrc: '',
		data: {
		},
		success: function(result){
			//alert("RESPONSE DATA :::: "+result);
			jsonData = result;			
			populateEmailConfigDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function populateEmailConfigDataTable(data)
{
	$("#emailconfigurations").DataTable().clear();
	var dataLength = data.length;
	if(dataLength == 0){
		$('#search_result').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var view='<i class="fa fa-eye w3-padding" id="view" onclick="getEmailConfig('+dat.id+',\'view\');"/>';
			var str='<i class="fa fa-pen w3-padding" id="edit" onclick="getEmailConfig('+dat.id+',\'edit\');"/>';
			$("#emailconfigurations").dataTable().fnAddData([				
				dat.id,
				dat.smtphost,
				dat.portno,	
				dat.emailid,
				dat.pasresetkey,
				dat.status,	
				view,	
				str				
				]);
		}
		$('#search_result').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}

function getEmailConfig(configid,display_mode)
{
	//debugger;
	console.log("ID: "+configid+" Mode: "+display_mode);
	$.ajax({
		type:"GET",
		url:"/email/editEmailConfig/"+configid+"/"+display_mode,
		success:function(result){
			//console.log("sucesssssss");
			$('#replace_div').html(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
	
	/*var url = "/reimbursement/editTADKReimbursement/"+tadkclaimid+"/"+display_mode;
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);*/
}
