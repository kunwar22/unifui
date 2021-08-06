$(document).ready(function() {
	var chekVal = $('#CR_CTG_TRNS_CITY_E').val();
	if(chekVal == 'Yes') {
		$("#CR_CTG_TRANSPORT_ATTACH_E").attr("disabled", true);
		$("#CR_CTG_OWN_VHCL_ATTACH_E").attr("disabled", true);
		$("#CR_CTG_FAIR_ATTACH_C").attr("disabled", true);
	}
	var _href = $("#DOWNLOAD_LINK1").attr("href");
	if(_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');
		$("#DOWNLOAD_LINK1").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINK1").attr("href"));
	}
	var _href = $("#DOWNLOAD_LINK2").attr("href");
	if(_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');
		$("#DOWNLOAD_LINK2").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINK2").attr("href"));
	}
	var _href = $("#DOWNLOAD_LINK3").attr("href");
	if(_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');
		$("#DOWNLOAD_LINK3").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINK3").attr("href"));
	}
	var _href = $("#DOWNLOAD_LINK4").attr("href");
	if(_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');
		$("#DOWNLOAD_LINK4").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINK4").attr("href"));
	}
	var _href = $("#DOWNLOAD_LINK5").attr("href");
	if(_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');
		$("#DOWNLOAD_LINK5").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINK5").attr("href"));
	}
	/**************************validation start here***************************************** */
	$('#CR_CTG_JOIN_ENCL_C').on('change', function() {
		if($('#CR_CTG_JOIN_ENCL_C').val() != '') $("#filepres1").val("Y");
		else $("#filepres1").val("");
	});
	$('#CR_CTG_TRANSPORT_ATTACH_C').on('change', function() {
		if($('#CR_CTG_TRANSPORT_ATTACH_C').val() != '') $("#filepres2").val("Y");
		else $("#filepres2").val("");
	});
	$('#CR_CTG_OWN_VHCL_ATTACH_C').on('change', function() {
		if($('#CR_CTG_OWN_VHCL_ATTACH_C').val() != '') $("#filepres3").val("Y");
		else $("#filepres3").val("");
	});
	$('#CR_CTG_OWN_VHCL_ATTACH_E').on('change', function() {
		if($('#CR_CTG_OWN_VHCL_ATTACH_E').val() != '') $("#filepres3").val("Y");
		else $("#filepres3").val("");
	});
	$('#CR_CTG_TRANSPORT_ATTACH_E').on('change', function() {
		if($('#CR_CTG_TRANSPORT_ATTACH_E').val() != '') $("#filepres2").val("Y");
		else $("#filepres2").val("");
	});
	$('#CR_CTG_JOIN_ENCL_E').on('change', function() {
		if($('#CR_CTG_JOIN_ENCL_E').val() != '') $("#filepres1").val("Y");
		else $("#filepres1").val("");
	});
	$('#attachment5').on('change', function() {
		if($('#attachment5').val() != '') $("#filepres1").val("Y");
		else $("#filepres1").val("");
	});
	$('#CR_CTG_FAIR_ATTACH_C').on('change', function() {
		if($('#CR_CTG_FAIR_ATTACH_C').val() != '') $("#filepres5").val("Y");
		else $("#filepres5").val("");
	});
	$('#attachment4').on('change', function() {
		if($('#attachment4').val() != '') $("#filepres4").val("Y");
		else $("#filepres4").val("");
	});
	$('#CR_CTG_CLAIM_C').on('change', function() {
		var val = $(this).val();
		if(val == "Yes") {
			$("#CR_CTG_SALARY_SLIP_C").html("<option value='Yes'>Yes</option>");
			$('#attachment5').removeAttr('disabled');
			$('#ctgclaimamount').prop('readOnly', false);
			$("#ctgclaimamount").val(0);
		} else {
			$("#CR_CTG_SALARY_SLIP_C").html("<option id='1' value='No' selected>No</option>");
			$('#attachment5').prop('disabled', true);
			$('#ctgclaimamount').prop('readOnly', true);
			$("#ctgclaimamount").val(0);
		}
	});
	$('#CR_CTG_TRNS_CHAIN_C').on('change', function() {
		var val = $(this).val();
		if(val == "Yes") {
			$("#CR_CTG_RCPT_ENCL_C").html("<option value='Yes'>Yes</option>");
			$('#CR_CTG_TRANSPORT_ATTACH_C').removeAttr('disabled');
		} else {
			$("#CR_CTG_RCPT_ENCL_C").html("<option id='1' value='No' selected>No</option>");
			$('#CR_CTG_TRANSPORT_ATTACH_C').prop('disabled', true);
		}
	});
	$('#CR_CTG_TRANS_CONVYNCE_C').on('change', function() {
		var val = $(this).val();
		if(val == "Yes") {
			$("#CR_CTG_OWN_VEHCL_C").html("<option value='Yes'>Yes</option>");
			$('#CR_CTG_OWN_VHCL_ATTACH_C').removeAttr('disabled');
		} else {
			$("#CR_CTG_OWN_VEHCL_C").html("<option id='1' value='No' selected>No</option>");
			$('#CR_CTG_OWN_VHCL_ATTACH_C').prop('disabled', true);
		}
	});
	$('#CR_CTG_CLAIM_E').on('change', function() {
		var val = $(this).val();
		if(val == "Yes") {
			$("#CR_CTG_SALARY_SLIP_E").html("<option value='Yes'>Yes</option>");
			$('#CR_CTG_JOIN_ENCL_E').removeAttr('disabled');
		} else {
			$("#CR_CTG_SALARY_SLIP_E").html("<option id='1' value='No' selected>No</option>");
			//$('#CR_CTG_JOIN_ENCL_E').addAttr('disabled');
			$("#CR_CTG_JOIN_ENCL_E").attr("disabled", true);
		}
	});
	$('#CR_CTG_TRNS_CHAIN_E').on('change', function() {
		var val = $(this).val();
		if(val == "Yes") {
			$("#CR_CTG_RCPT_ENCL_E").html("<option value='Yes'>Yes</option>");
			$('#CR_CTG_TRANSPORT_ATTACH_E').removeAttr('disabled');
		} else {
			$("#CR_CTG_RCPT_ENCL_E").html("<option id='1' value='No' selected>No</option>");
			$('#CR_CTG_TRANSPORT_ATTACH_E').prop('disabled', true);
		}
	});
	$('#CR_CTG_TRANS_CONVYNCE_E').on('change', function() {
		var val = $(this).val();
		if(val == "Yes") {
			$("#CR_CTG_OWN_VEHCL_E").html("<option value='Yes'>Yes</option>");
			$('#CR_CTG_OWN_VHCL_ATTACH_E').removeAttr('disabled');
		} else {
			$("#CR_CTG_OWN_VEHCL_E").html("<option id='1' value='No' selected>No</option>");
			$('#CR_CTG_OWN_VHCL_ATTACH_E').prop('disabled', true);
		}
	});
	var maxfy = getMaxfy();
	var minfy = getMinfy();

	function getMinfy() {
		var today = new Date();
		if((today.getMonth() + 1) <= 3) {
			var minfy = (today.getFullYear() - 1) + "-04-01";
		} else {
			var minfy = today.getFullYear() + "-04-01";
		}
		return minfy;
	}

	function getMaxfy() {
		var today = new Date();
		if((today.getMonth() + 1) <= 3) {
			var maxfy = today.getFullYear() + "-03-31";
		} else {
			var maxfy = (today.getFullYear() + 1) + "-03-31";
		}
		return maxfy;
	}
	$('#CR_CTG_TRNSFR_C').on('change', function(e) {
		var from = $('#CR_CTG_TRNSFR_C').val();
		if(from < minfy) {
			from = minfy;
		}
		//$('#CR_CTG_JOURNEY_C').attr("min",from);
		$('#CR_CTG_JOURNEY_C').attr("max", maxfy);
	});
	$('#transferdate').on('change', function(e) {
		var from = $('#transferdate').val();
		if(from < minfy) {
			from = minfy;
		}
		$('#journeydate').attr("min", from);
		$('#journeydate').attr("max", maxfy);
	});
	$('#CR_CTG_TRNSFR_E').on('change', function(e) {
		var from = $('#CR_CTG_TRNSFR_E').val();
		if(from < minfy) {
			from = minfy;
		}
		$('#CR_CTG_JOURNEY_E').attr("min", from);
		$('#CR_CTG_JOURNEY_E').attr("max", maxfy);
	});
	$('#CR_CTG_JOURNEY_C').on('click', function(e) {
		var from = $('#CR_CTG_TRNSFR_C').val();
		if(from < minfy) {
			from = minfy;
		}
		//$('#CR_CTG_JOURNEY_C').attr("min",from);
		$('#CR_CTG_JOURNEY_C').attr("max", maxfy);
	});
	$('#journeydate').on('click', function(e) {
		var from = $('#transferdate').val();
		if(from < minfy) {
			from = minfy;
		}
		$('#journeydate').attr("min", from);
		$('#journeydate').attr("max", maxfy);
		alert(maxfy);
	});
	$('#CR_CTG_JOURNEY_E').on('click', function(e) {
		var from = $('#CR_CTG_TRNSFR_E').val();
		if(from < minfy) {
			from = minfy;
		}
		//$('#CR_CTG_JOURNEY_E').attr("min",from);
		$('#CR_CTG_JOURNEY_E').attr("max", maxfy);
	});
	/***************************validation end here*******************************************/
});
$('#CTG_REMB_Cancel_V').on('click', function(e) {
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#CTG_REMB_Cancel_E').on('click', function(e) {
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#CDA_CTG_Cancel').on('click', function(e) {
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
var CR_CTG_ID = '';

function loadSaveCTG(str, frmid, x) {
	////debugger
	if($("#disclaimer:checked").length > 0 && $("#disclaimer1:checked").length > 0) {
		$(x).css("display", "none");
		var personnumber = $("#txtpersonNumber").val();
		$("#ctgstatusid").val(str);
		////alert($("#statusid").val());
		var doctype = "CTGReimbursement";
		personnumber = personnumber.trim();
		doctype = doctype.trim();
		////alert(str);
		var filepath = "";
		filepath += personnumber + "/" + doctype;
		////alert(filepath);
		$("#attachmentsss").val("/EmployeeDocs/" + filepath);
		////alert("FILEPATH::"+$('#attachment1').val());
		//var form = $("#CTG_SAVE");
		var data = new FormData(document.getElementById("CTG_SAVE"));
		//console.log(data);
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
			url: "/ctgReimbursement/saveCtgReimbursement",
			type: "POST",
			enctype: "multipart/form-data",
			data: data,
			cache: false,
			contentType: false,
			processData: false,
			timeout: 600000,
			success: function(data) {
				$('#replace_div').html(data);
				if(resultfinal == "Success") {
					if(str == "save") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: "You have successfully saved CTG Reimbursement.",
							callback: function() {
								$('#replace_div').load("/ctgReimbursement/manageCtgReimb");
							}
						});
					} else if(str == "submit") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: "You have successfully submitted CTG Reimbursement for approval.",
							callback: function() {
								$('#replace_div').load("/ctgReimbursement/manageCtgReimb");
							}
						});
					}
				}
				/* else if (resultfinal == "MISMATCH") {
							//alert("MISMATCH");
						} else if (resultfinal == "EMPTY_FILE") {
							//alert("File is empty");
						} else if (resultfinal == "WRITE_ERROR") {
							//alert("Error in writing file.");
						} else if (resultfinal == "IOEXCEPTION") {
							//alert("IO Exception has occurred.");
						} else if (resultfinal == "LOG_ERROR") {
							//alert("Error while logging file info.");
						} else if (resultfinal == "ILLEGALARG") {
							//alert("Error while posting file log.");
						}*/
				else if(resultfinal == "TransferDateAlreadyExist") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Transfer Date Already Exist!!!"
					});
				} else if(resultfinal == "JourneyDateAlreadyExist") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Journey Date Already Exists!!!"
					});
				} else {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Fill all the mandatory fields and attach the file again, if required."
					});
				}
			},
			error: function(data) {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Something went wrong. Please try again."
				});
				$('#replace_div').html(data);
			}
		});
	} else {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: "You cannot proceed without disclaimer."
		});
	}
};

function loadupdateCTG(str, frmid, x) {
	////debugger;
	if($("#disclaimer:checked").length > 0 && $("#disclaimer1:checked").length > 0) {
		$(x).css("display", "none");
		var personnumber = $("#txtpersonNumber").val();
		$("#ctgstatusid").val(str);
		////alert($("#statusid").val());
		var doctype = "CTGReimbursement";
		personnumber = personnumber.trim();
		doctype = doctype.trim();
		var filepath = "";
		filepath += personnumber + "/" + doctype;
		////alert(filepath);
		$("#attachmentsss").val("/EmployeeDocs/" + filepath);
		//alert($("#attachmentsss").val());
		////alert("FILEPATH::"+$('#attachment1').val());
		//var form = $("#CTG_SAVE");
		var mode = 'edit';
		var data = new FormData(document.getElementById("CTG_SAVE"));
		//console.log(data);
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
			url: "/ctgReimbursement/updateCtgReimbursement",
			type: "POST",
			enctype: "multipart/form-data",
			data: data,
			cache: false,
			contentType: false,
			processData: false,
			timeout: 600000,
			success: function(data) {
				$('#replace_div').html(data);
				if(resultfinal == "Success") {
					if(str == "save") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: "You have successfully update CTG Reimbursement.",
							callback: function() {
								$('#replace_div').load("/ctgReimbursement/manageCtgReimb");
							}
						});
					} else if(str == "submit") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: "You have successfully submitted CTG Reimbursement for approval.",
							callback: function() {
								$('#replace_div').load("/ctgReimbursement/manageCtgReimb");
							}
						});
					}
				}
				/*else if (resultfinal == "MISMATCH") {
			//alert("MISMATCH");
		} else if (resultfinal == "EMPTY_FILE") {
			//alert("File is empty");
		} else if (resultfinal == "WRITE_ERROR") {
			//alert("Error in writing file.");
		} else if (resultfinal == "IOEXCEPTION") {
			//alert("IO Exception has occurred.");
		} else if (resultfinal == "LOG_ERROR") {
			//alert("Error while logging file info.");
		} else if (resultfinal == "ILLEGALARG") {
			//alert("Error while posting file log.");
		}*/
				else if(resultfinal == "TransferDateAlreadyExist") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Transfer Date Already Exist!!!"
					});
				} else if(resultfinal == "JourneyDateAlreadyExist") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Journey Date Already Exists!!!"
					});
				} else {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Fill all the mandatory fields and attach the file again, if required."
					});
				}
			},
			error: function(data) {
				$('#replace_div').html(data);
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Something went wrong. Please try again."
				});
			}
		});
	} else {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: "You cannot proceed without disclaimer."
		});
	}
};
var currentbasicpay = '';
var previousbasicpay = '';
var flag = '';
var totamt = '';
var totamt_diff_city = '';
currentbasicpay = parseFloat($("#txtCurrentBasicPay").val());
previousbasicpay = parseFloat($("#txtPreviousBasicPay").val());
//alert(currentbasicpay+" "+previousbasicpay);
if(previousbasicpay < currentbasicpay) {
	flag = previousbasicpay;
	//console.log("less"+flag);
} else { //if(currentbasicpay<=previousbasicpay){
	flag = currentbasicpay;
	//console.log("less"+flag);
}
$('#CR_CTG_TRNS_CITY_C').on('change', function() {
	var val = $(this).val();
	if(val == "Yes") {
		////alert(flag);
		totamt = flag * 0.8 * 1 / 3;
		//console.log("Same city"+totamt);
		var z = Math.round(totamt);
		$('#CR_CTG_CLAIM_AMNT_C').val(z);
		$('#CR_CTG_TRNS_CHAIN_C').prop('disabled', true);
		$('#CR_CTG_TRANS_CONVYNCE_C').prop('disabled', true);
		$('#CR_CTG_FAIR').prop('disabled', true);
		$('#ctgclaimamount').prop({
			value: 0
		});
		$('#personaleffectclaimamount').prop('disabled', true);
		$('#vehicleclaimamount').prop('disabled', true);
		$('#fairclaimamount').prop('disabled', true);
		// $('#CR_CTG_OWN_VHCL_ATTACH_E').prop('disabled', true);
	} else if(val == "No") {
		tot_exp = parseFloat(0);
		totamt_diff_city = flag * 0.8;
		//console.log("Different city"+totamt);
		var x = Math.round(totamt_diff_city);
		$('#CR_CTG_CLAIM_AMNT_C').val(x);
		$('#personaleffectclaimamount').val('0');
		$('#vehicleclaimamount').val('0');
		$('#fairclaimamount').val('0');
		$('#ctgclaimamount').val('0');
		$('#CR_CTG_ACTUL_AMNT').val('0');
		$('#CR_CTG_TRNS_CHAIN_C').prop('disabled', false);
		$('#CR_CTG_TRANS_CONVYNCE_C').prop('disabled', false);
		$('#CR_CTG_FAIR').prop('disabled', false);
		$('#personaleffectclaimamount').prop('disabled', false);
		$('#vehicleclaimamount').prop('disabled', false);
		$('#fairclaimamount').prop('disabled', false);
		//		 $('#CR_CTG_OWN_VHCL_ATTACH_E').removeAttr('disabled');
		/*	CLAIM_AMT1 = $('#ctgclaimamount').val();
			CLAIM_AMT2 = $('#personaleffectclaimamount').val();
			CLAIM_AMT3 = $('#vehicleclaimamount').val();
			CLAIM_AMT4 = $('#fairclaimamount').val();
			
			TOTAL_EXPEND= parseInt(CLAIM_AMT1)+ parseInt(CLAIM_AMT2)+ parseInt(CLAIM_AMT3)+ parseInt(CLAIM_AMT4);

			//alert("total="+TOTAL_EXPEND);
*/
	}
});
/*

$('#CR_CTG_TRNS_CITY_E').on('change',function(){	
var val = $(this).val();
    if (val == "Yes") {
	////alert(flag);
			totamt=flag*0.8*1/3;
			//console.log("Same city"+totamt);
			var a = Math.round(totamt);			
			$('#CR_CTG_CLAIM_AMNT_E').val(a);
	
   } else  if (val == "No"){
			totamt_diff_city=flag*0.8;
			//console.log("Different city"+totamt);
			var b = Math.round(totamt_diff_city);
			$('#CR_CTG_CLAIM_AMNT_E').val(b);
     }
	
});

	*/
//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
function approvalSubmitCtg(_status) {
	if($("#txtAmount").val() == '') {
		$("#txtAmount").val("0");
	}
	var check = "false";
	var formData = $('#CTG_SAVE').serialize();
	var amt = $("#txtAmount").val();
	var cmt = $("#txtComment").val();
	if(_status == 'Approved') {
		if(amt == "" ) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Request cannot be Approved without filling Approved Amount."
			});
		} else {
			check = "true";
		}
	} else {
		if(cmt == "") {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Request cannot be Rejected without filling Comments."
			});
		} else {
			check = "true";
		}
	}
	if(check == "true") {
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
			url: "/ctgReimbursement/ctgApproval/" + _status,
			type: "POST",
			cache: false,
			data: formData,
			processData: false,
			contentType: 'application/x-www-form-urlencoded',
			dataType: "json",
			success: function(result) {
				if(result.status == "true") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message: "You have successfully " + _status + ".",
						callback: function() {
							window.location = "/selfservice";
						}
					});
				} else if(result.status == "false") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Something went wrong, Please take action again.",
						callback: function() {
							window.location = "/selfservice";
						}
					});
				}
			},
			error: function(e) {
				//console.log( "ERROR : "+ JSON.stringify(e) );
			}
		});
	}
}

function totalExpence(id) {
	var tot_exp = 0;
	/*if(id== "ctgclaimamount")
	{*/
	if($('#ctgclaimamount').val() == "") {
		parseFloat($('#ctgclaimamount').prop({
			value: 0
		}));
		tot_exp += parseFloat($('#ctgclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		tot_exp += parseFloat($('#ctgclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);	
	}
	//}	
	/*else if(id== "personaleffectclaimamount")
	{*/
	if($('#personaleffectclaimamount').val() == "") {
		parseFloat($('#personaleffectclaimamount').prop({
			value: 0
		}));
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp += parseFloat($('#personaleffectclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp += parseFloat($('#personaleffectclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	}
	//}
	/*else if(id== "vehicleclaimamount")
	{*/
	if($('#vehicleclaimamount').val() == "") {
		parseFloat($('#vehicleclaimamount').prop({
			value: 0
		}));
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp += parseFloat($('#vehicleclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp += parseFloat($('#vehicleclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	}
	//}
	/*else if(id== "fairclaimamount")
	{*/
	if($('#fairclaimamount').val() == "") {
		parseFloat($('#fairclaimamount').prop({
			value: 0
		}));
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp += parseFloat($('#fairclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp += parseFloat($('#fairclaimamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	}
	//}
	$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
}
$('#CR_CTG_TRNS_CITY_E').on('change', function() {
	var val = $(this).val();
	if(val == "Yes") {
		//alert(flag);
		totamt = flag * 0.8 * 1 / 3;
		console.log("Same city" + totamt);
		var z = Math.round(totamt);
		$('#CR_CTG_CLAIM_AMNT_E').val(z);
		$('#CR_CTG_TRNS_CHAIN_E').prop('disabled', true);
		$('#CR_CTG_TRANS_CONVYNCE_E').prop('disabled', true);
		$('#CR_CTG_FAIR_E').prop('disabled', true);
		//$('#ctgclaimamount').prop({value : 0});
		$('#personaleffectclaimamount').prop('disabled', true);
		$('#vehicleclaimamount').prop('disabled', true);
		$('#fairclaimamount').prop('disabled', true);
		$('#personaleffectclaimamount').val("0.0");
		$('#vehicleclaimamount').val("0.0");
		$('#fairclaimamount').val("0.0");
		$('#CR_CTG_CITYTO').val($('#CR_CTG_CITY_FRM_E').val());
		// $('#CR_CTG_OWN_VHCL_ATTACH_E').prop('disabled', true);
	} else if(val == "No") {
		tot_exp = parseFloat(0);
		totamt_diff_city = flag * 0.8;
		console.log("Different city" + totamt);
		var x = Math.round(totamt_diff_city);
		$('#CR_CTG_CLAIM_AMNT_C').val(x);
		$('#personaleffectclaimamount').val('0');
		$('#vehicleclaimamount').val('0');
		$('#fairclaimamount').val('0');
		$('#ctgclaimamount').val('0');
		$('#CR_CTG_ACTUL_AMNT').val('0');
		$('#CR_CTG_CITYTO').val("");
		$('#CR_CTG_TRNS_CHAIN_E').prop('disabled', false);
		$('#CR_CTG_TRANS_CONVYNCE_E').prop('disabled', false);
		$('#CR_CTG_FAIR_E').prop('disabled', false);
		$('#personaleffectclaimamount').prop('disabled', false);
		$('#vehicleclaimamount').prop('disabled', false);
		$('#fairclaimamount').prop('disabled', false);
	}
});
//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
var CLAIM_AMT1;
var CLAIM_AMT2;
var CLAIM_AMT3;
var CLAIM_AMT4;
var TOTAL_EXPEND;
$('#CR_CTG_TRNS_JOIN').on('change', function() {
	var val = $(this).val();
	if(val == "Yes") {
		$('#attachment4').removeAttr('disabled');
	} else {
		$('#attachment4').prop('disabled', true);
	}
});
$('#CR_CTG_FAIR_E').on('change', function() {
	var val = $(this).val();
	if(val == "Yes") {
		$('#CR_CTG_FAIR_ATTACH_C').removeAttr('disabled');
	} else {
		$('#CR_CTG_FAIR_ATTACH_C').prop('disabled', true);
	}
});
$('#CR_CTG_FAIR').on('change', function() {
	var val = $(this).val();
	if(val == "Yes") {
		$('#CR_CTG_FAIR_ATTACH_C').removeAttr('disabled');
	} else {
		$('#CR_CTG_FAIR_ATTACH_C').prop('disabled', true);
	}
});
/*
function getData(id)
{
	//debugger;
	//console.log("id= "+id);
	var dt = $("#"+id).val();
	if(dt.length<1)
	{
		tot_exp=parseFloat(0);
		$('#CR_CTG_ACTUL_AMNT').val(0);
	}
	else
	{
	var data = parseFloat(dt);
	totalExpence(data);
	}
}
function totalExpence(d)
{
	//debugger;
	if(tot_exp == NaN || tot_exp==undefined)
	{
		tot_exp=parseFloat(0);
	}
	else if(tot_exp != NaN || tot_exp !=undefined)
	{
		
		tot_exp = tot_exp+d;
		////alert("tot_exp ===> "+tot_exp);
		$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
		
	}
}

*/
/*
function totalExpence() {
            CLAIM_AMT1 = $('#ctgclaimamount').val();			
			$('#CR_CTG_ACTUL_AMNT').val(CLAIM_AMT1);
			
}

function totalExpence1() {
            CLAIM_AMT1 = $('#ctgclaimamount').val();
			CLAIM_AMT2 = $('#personaleffectclaimamount').val();	
			TOTAL_EXPEND= parseInt(CLAIM_AMT1)+ parseInt(CLAIM_AMT2);
			
			 if (isNaN(TOTAL_EXPEND)) {
  			 $('#CR_CTG_ACTUL_AMNT').val(0);
  			}else {
			$('#CR_CTG_ACTUL_AMNT').val(TOTAL_EXPEND);
			
			$('#vehicleclaimamount').val('');
			$('#fairclaimamount').val('');
			
			}
			
			//$('#CR_CTG_ACTUL_AMNT').val(TOTAL_EXPEND);
			////alert("total="+CLAIM_AMT1);
}

function totalExpence2() {
            CLAIM_AMT1 = $('#ctgclaimamount').val();
			CLAIM_AMT2 = $('#personaleffectclaimamount').val();	
			CLAIM_AMT3 = $('#vehicleclaimamount').val();
			TOTAL_EXPEND= parseInt(CLAIM_AMT1)+ parseInt(CLAIM_AMT2)+ parseInt(CLAIM_AMT3);
			
			//$('#CR_CTG_ACTUL_AMNT').val(TOTAL_EXPEND);
			////alert("total="+CLAIM_AMT1);
			if (isNaN(TOTAL_EXPEND)) {
  			 $('#CR_CTG_ACTUL_AMNT').val(0);
  			}else {
			$('#CR_CTG_ACTUL_AMNT').val(TOTAL_EXPEND);
			}
}


function totalExpence3() {
            CLAIM_AMT1 = $('#ctgclaimamount').val();
			CLAIM_AMT2 = $('#personaleffectclaimamount').val();	
			CLAIM_AMT3 = $('#vehicleclaimamount').val();
			CLAIM_AMT4 = $('#fairclaimamount').val();
			TOTAL_EXPEND= parseInt(CLAIM_AMT1)+ parseInt(CLAIM_AMT2)+ parseInt(CLAIM_AMT3)+ parseInt(CLAIM_AMT4);
			//$('#CR_CTG_ACTUL_AMNT').val(TOTAL_EXPEND);
			////alert("total="+CLAIM_AMT1);
			if (isNaN(TOTAL_EXPEND)) {
  			 $('#CR_CTG_ACTUL_AMNT').val(0);
  			}else{
			$('#CR_CTG_ACTUL_AMNT').val(TOTAL_EXPEND);
			}
}
*/
function totalExpenceApproved(id) {
	
	var tot_exp_approved = 0;
	/*if(id== "ctgclaimamount")
	{*/
	if($('#ctgapproveamount').val() == "") {
		parseFloat($('#ctgapproveamount').prop({
			value: 0
		}));
		tot_exp_approved += parseFloat($('#ctgapproveamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		tot_exp_approved += parseFloat($('#ctgapproveamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);	
	}
	//}	
	/*else if(id== "personaleffectclaimamount")
	{*/
	if($('#personaleffectapproveamounts').val() == "") {
		parseFloat($('#personaleffectapproveamounts').prop({
			value: 0
		}));
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp_approved += parseFloat($('#personaleffectapproveamounts').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp_approved += parseFloat($('#personaleffectapproveamounts').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	}
	//}
	/*else if(id== "vehicleclaimamount")
	{*/
	if($('#vehicleapproveamount').val() == "") {
		parseFloat($('#vehicleapproveamount').prop({
			value: 0
		}));
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp_approved += parseFloat($('#vehicleapproveamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp_approved += parseFloat($('#vehicleapproveamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	}
	//}
	/*else if(id== "fairclaimamount")
	{*/
	if($('#fairapproveamount').val() == "") {
		parseFloat($('#fairapproveamount').prop({
			value: 0
		}));
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp_approved += parseFloat($('#fairapproveamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	} else {
		//tot_exp = parseFloat($('#ctgclaimamount').val()) + parseFloat($('#personaleffectclaimamount').val()) + parseFloat($('#vehicleclaimamount').val()) + parseFloat($('#fairclaimamount').val());
		tot_exp_approved += parseFloat($('#fairapproveamount').val());
		//$('#CR_CTG_ACTUL_AMNT').val(tot_exp);
	}
	//}
	$('#txtAmount').val(tot_exp_approved);
}
$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});