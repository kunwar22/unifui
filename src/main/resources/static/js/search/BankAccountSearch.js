
var jsonUrl = '/bankAccount/getBankDetails';

$(document).ready(function() {
    // document is loaded and DOM is ready
   // alert("document is ready");

	loadBankData();


});

function createBankAccount()
{
		var url ="/bankAccount/CreateBankAccount";
		$('#replace_div').load(url);
}


function loadBankData(){
		//alert("1"+jsonUrl);
	$.ajax({
		type: 'POST',
		url: jsonUrl,
		dataSrc: '',
		data: {
				
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			populateBankDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateBankDataTable(data){
		//alert("2");
	$("#BankList").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#resultSec').css('display', 'none');
		$('#noDataBank').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var view='<i class="fa fa-eye w3-padding" id="view" onclick="bank_details('+dat.bankaccountsid+',\'view\');"/>';
			//var str='<i class="fa fa-pen w3-padding" id="edit" onclick="bank_details('+dat.bankaccountsid+',\'edit\');"/>';
			$("#BankList").dataTable().fnAddData([
				/*dat.personid,
				dat.personnumber,*/
				dat.bankaccountnumber,
				dat.accountholdername,	
				dat.branchifsccode,
				dat.accounttype,	
				view,
				//str
				]);
		}
		$('#BankList').css('display', 'block');
		$('#noDataBank').css('display', 'none');
	}
}

function bank_details(bankid,mode)
{
	var bank_url="/bankAccount/edit/EditBankDetails/"+bankid+"/"+mode;
	console.log("BANK URL ====> "+bank_url);
	$('#replace_div').load(bank_url);
}

		/*$(document).on('click').unbind();
		$(document).on('click', 'ed', function(e){
			var url = $(this).attr("rm");
			$('#replace_div').load(url);
		});*/