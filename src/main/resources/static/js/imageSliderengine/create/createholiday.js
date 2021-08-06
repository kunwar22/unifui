
var CR_HLDY_ID = '';
var CR_HLDY_ACTIONID = '';

function ajaxPost()
{

	CR_HLDY_ID = $('#txthldyid').val();
	CR_HLDY_ACTIONID = $('#txthldyActionid').val();
	
	if(CR_HLDY_ID!=0){
		loadCorrectHldyData();
	}
	else if(CR_HLDY_ID==0){
		//alert(checkBuid);
		loadSaveHldyData();
		
	}
	
};

$('#holidayCancel').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});	

function loadSaveHldyData() {
var fd = $("#HOLIDAY_SAVE").serialize();

$.ajax({
		url: "/holiday/saveHoliday",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
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
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

};




function loadCorrectHldyData() {
var fd = $("#HOLIDAY_SAVE").serialize();

$.ajax({
		url: "/holiday/correctHoliday",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
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
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});
};


		