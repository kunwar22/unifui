//alert("hi bank");


var CR_BNK_AC_ID = '';


function ajaxPost(view_mode)
{

	CR_BNK_AC_ID = $('#txtbankaccountsid').val();

	
	if(CR_BNK_AC_ID!=0){
		loadCorrectBankData(view_mode);
	}
	else if(CR_BNK_AC_ID==0){
		//alert(checkBuid);
		loadSaveBankData();
		
	}
	
};

/*$('#AccountCancel').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});*/	

function loadSaveBankData() {
//alert("save");
//debugger;
var fd = $("#BANK_ACCOUNT_SAVE").serialize();
document.getElementById('btnSave').style.pointerEvents="none";
$.ajax({
		url: "/bankAccount/saveBankAccount",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
			$('#replace_div').html(data);
			if (resultfinal == "Saved") {
			
				bootbox.alert({size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message:"You have successfully saved Bank Details.", 
						callback:function(){
						$('#replace_div').load("/bankAccount/manageBankAccount");
					}
				});
				
				
			}
			else if (resultfinal == "AlreadyExists") {
					bootbox.alert({size: 'medium',
						title:'<i class="fa fa-exclamation-triangle" style="font-size:25px; color:orange;">&nbsp;&nbsp;Information</i>',
						message:"You already have a salary account!!!"
						/*callback:function(){
						/*$('#replace_div').load("/bankAccount/manageBankAccount");
						
					}*/
				});
			}
			else if (resultfinal != "Saved") {	
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Fill all the mandatory fields and try again!!"				
				});
			}			
			
		},
		error: function(response){
			console.log(JSON.parse(response.responseText));
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Something went wrong!!! <br/>Please try again after some time!!"				
				});
		   	}
	});

};




function loadCorrectBankData(mode) {
//alert("correct");
//debugger;
var fd = $("#BANK_ACCOUNT_SAVE").serialize();
document.getElementById('btnUpdate').style.pointerEvents="none";
$.ajax({
		url: "/bankAccount/correctBankAccount/"+mode,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
			//alert("update"+data);
			$('#replace_div').html(data);
			if (resultfinal == "Correct") {
			
				bootbox.alert({size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message:"You have successfully updated Bank Details.", 
						callback:function(){
						$('#replace_div').load("/bankAccount/manageBankAccount");
					}
				});
			}
			else if (resultfinal == "AlreadyExists") {
					bootbox.alert({size: 'medium',
						title:'<i class="fa fa-exclamation-triangle" style="font-size:25px; color:orange;">&nbsp;&nbsp;Information</i>',
						message:"You already have a bank account with same details!!!"
						/*callback:function(){
						$('#replace_div').load("/bankAccount/CreateBankAccount");
					}*/
				});
			}
			else 
			if (resultfinal != "Correct") {	
				
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Fill all the mandatory fields and try again!!"				
				});
			}			
			
		},
		error: function(response){
			console.log(JSON.parse(response.responseText));
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Something went wrong!!! <br/>Please try again after some time!!"				
				});
		   	}
	});

};

function backBtnFunc(){
	var url = "/bankAccount/manageBankAccount";
	$('#replace_div').load(url);
	}
	
	
		