
$('#CANCEL_BTN_REP').on('click', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


var CR_RP_ID = '';
var CR_RP_ACTIONID = '';

function ajaxPost()
{
	
	CR_RP_ID = $('#txtRPid').val();
	CR_RP_ACTIONID = $('#txtRPActionid').val();
	
	if(CR_RP_ID!=0){
		loadSaveRPData();
	}
	else if(CR_RP_ID==0){
		loadSaveRPData();
		
	}
	
};

$('#CR_RPT_EFFDT').on('change', function(e){
	var n = $('#CR_RPT_EFFDT').val();
	alert(n);
	$('#CR_RPT_EFFENDDT').attr("min",n);
});





function loadSaveRPData() {
//debugger;
var fd = $("#frmRptprd").serialize();

$.ajax({
		url: "/repeatingperiod/saveRepeating",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success :function(result){
			$('#replace_div').html(result);
			
				if(message.status!=null && message.status!=""){
				if(message.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(message.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}else if(message.status!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(message.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
			}
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

};

