
function loadCreatefunction() {

    var url = "/rfa/createnew/personRfa";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
    $('#replace_div').load(url);
}



var jsonUrlRfa = '/accomodation/search/personaccomodation';
var searchrfaNumber = '';
var searchrfaName = '';
var selectedtype='';
var fromdate='';
var todate='';


$("#RFASearch").on('click', function(e){
	
	searchrfaNumber =  $("#SRCH_Number").val();
	searchrfaName = $("#SRCH_Name").val();
	selectedtype=$("#rfa_type option:selected").val();
	fromdate =  $("#SRCH_FRM_DT").val();
	todate = $("#SRCH_TO_DT").val();
	loadRfaData();
	$('#resultRfa').css('display', 'none');
	$('#noData').css('display', 'none');
	$('#jsonLoaderPage').css('display', 'block');
	
});

function handleKeyPress(e){
	
	var key=e.keyCode || e.which;
	if (key==13){
    searchrfaNumber =  $("#SRCH_Number").val();
	searchrfaName = $("#SRCH_Name").val();
	selectedtype=$("#rfa_type option:selected").val();
		loadRfaData();
		$('#resultRfa').css('display', 'none');
		$('#noData').css('display', 'none');
		$('#jsonLoaderPage').css('display', 'block');

	}
}


function loadRfaData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlRfa,
		dataSrc: '',
		data: {
			"personnumber": searchrfaNumber,
			"personname": searchrfaName,
			"rfatype": selectedtype,
			"fromdate":fromdate,
			"todate":todate,
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			populaterfaDataTable(jsonData);
			$('#jsonLoaderPage').css('display', 'none');
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populaterfaDataTable(data){

	$("#Rfa_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#resultRfa').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#jsonLoaderPage').css('display', 'none');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#Rfa_LIST").dataTable().fnAddData([
				dat.leaseId,
				dat.personNumber,
                dat.personName,
                dat.accomodationType,
                dat.startdate,
                dat.enddate,
                dat.pmtToVendor_amt,
                dat.leaseRecovery_amt,
                dat.qtrRentRecovery_amt,			
				"<ed rm='/accomodation/rent/createaccommodation/"+dat.leaseId+"/"+dat.personNumber+"' id='EDIT_Rfa' class='editUser' style='cursor: pointer'><i class='fas fa-tools'></i></ed>",
			]);
		}
		$('#resultRfa').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#jsonLoaderPage').css('display', 'none');
	}
}

$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').html("<div style='margin-left:-16px; margin-right:-16px; margin-top:0px; width: 100%; margin-top: 200px; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: gray;'></i></div>");
	$('#replace_div').load(url);
});
