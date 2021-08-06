var remType = '';
var fromDate = '';
var toDate = '';
var action = '';
var buid = '';

function validate() {
	if(remType == null && action == null && buid == null) {
		$("#remTyperr").css('display', 'block');
		$("#actionerr").css('display', 'block');
		$("#buiderr").css('display', 'block');
	} else if(remType == null && action == null && buid != null) {
		$("#remTyperr").css('display', 'block');
		$("#actionerr").css('display', 'block');
		$("#buiderr").css('display', 'none');
	} else if(remType == null && action != null && buid == null) {
		$("#remTyperr").css('display', 'block');
		$("#actionerr").css('display', 'none');
		$("#buiderr").css('display', 'block');
	} else if(remType == null && action != null && buid != null) {
		$("#remTyperr").css('display', 'block');
		$("#actionerr").css('display', 'none');
		$("#buiderr").css('display', 'none');
	} else if(remType != null && action == null && buid == null) {
		$("#remTyperr").css('display', 'none');
		$("#actionerr").css('display', 'block');
		$("#buiderr").css('display', 'block');
	} else if(remType != null && action == null && buid != null) {
		$("#remTyperr").css('display', 'none');
		$("#actionerr").css('display', 'block');
		$("#buiderr").css('display', 'none');
	} else if(remType != null && action != null && buid == null) {
		$("#remTyperr").css('display', 'none');
		$("#actionerr").css('display', 'none');
		$("#buiderr").css('display', 'block');
	}
}

function loadapprovalactions() {
	
	var curl = "";
	remType = $('#remtype').val();
	fromDate = $('#fromdate').val();
	toDate = $('#todate').val();
	action = $('#action').val();
	buid = $('#buid').val();
	validate();
	$('#Reimbursement_History_Approve').css('width', '100%');
	$('#Reimbursement_History_Reject').css('width', '100%');
	$('#Reimbursement_Approve').css('width', '100%');
	if(fromDate == "" && toDate == "") {
		curl = "/mss/getapprovalactions/" + remType + "/" + action + "/x/x/" + buid;
	} else {
		curl = "/mss/getapprovalactions/" + remType + "/" + action + "/" + fromDate + "/" + toDate + "/" + buid;
	}
	if(action == 'Approved') {
		$('#Reimbursement_History_Approve').css('width', '100%');
		showApprovedPendingData(curl);
	} else if(action == 'Rejected') {
		$('#Reimbursement_History_Reject').css('width', '100%');
		showRejectedPendingData(curl);
	} else if(action == 'Submitted') {
		$('#Reimbursement_Approve').css('width', '100%');
		showSubmittedData(curl);
	}
};

function showApprovedPendingData(curl) {
	
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: 'GET',
		url: curl,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			$('#Reimbursement_History_Approve').css('width', '100%');
			populateapprovalaction(result);
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function showRejectedPendingData(curl) {
	
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: 'GET',
		url: curl,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			$('#Reimbursement_History_Reject').css('width', '100%');
			populaterejectionaction(result);
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function showSubmittedData(curl) {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: 'GET',
		url: curl,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			$('#Reimbursement_Approve').css('width', '100%');
			populatesubmittedapprovalaction(result);
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function populateapprovalaction(data) {
	$('#Reimbursement_History_Approve').css('width', '100%');
	$("#Reimbursement_History_Approve").DataTable().clear();
	//$('#resultDivApprove').css('display', 'block');
	$('#resultDivApprove').css('display', 'none');
	// $('#Reimbursement_History').dataTable(  );
	var dataLength = data.length;
	if(dataLength == 0) {
		$('#resultDivApprove').css('display', 'none');
		$('#resultDivReject').css('display', 'none');
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#Reimbursement_History_Approve").dataTable({
				scrollY: '50vh',
				scrollCollapse: true,
				paging: false,
				destroy: true,
				searching: false,
				dom: 'Bfrtip',
				buttons: [{
					extend: 'copy',
					text: 'Copy to Clipboard'
				}, {
					extend: 'csv',
					className: 'excelButton',
					text: 'Download to Excel'
				}, {
					extend: 'print',
					className: 'printButton'
				}]
			}).fnAddData([
				dat.requesterpersonnumber,
				dat.requesterpersonname,
				/*	dat.requestid,*/
				dat.requestdate, /** when person submit the reimbursement */
				dat.submitdate, /** when goes to approver person */
				dat.buname,
				dat.climedamt,
				dat.approvedamt,
				dat.message
				//dat.finalapprovedamt,
				//dat.finalstatus
			]);
		}
		$(".dataTables_scrollHeadInner").removeAttr("style");
		$(".dataTable").removeAttr("style");
		$('#resultDivApprove').css('display', 'block');
		$('#resultDivReject').css('display', 'none');
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'none');
		$('#bulkDataSave').css('display', 'none');
	}
};

function populaterejectionaction(data) {
	$('#Reimbursement_History_Reject').css('width', '100%');
	$("#Reimbursement_History_Reject").DataTable().clear();
	//$('#resultDivApprove').css('display', 'block');
	$('#resultDivReject').css('display', 'none');
	// $('#Reimbursement_History').dataTable(  );
	var dataLength = data.length;
	if(dataLength == 0) {
		$('#resultDivApprove').css('display', 'none');
		$('#resultDivReject').css('display', 'none');
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#Reimbursement_History_Reject").dataTable({
				scrollY: '50vh',
				scrollCollapse: true,
				paging: false,
				destroy: true,
				searching: false,
				dom: 'Bfrtip',
				buttons: [{
					extend: 'copy',
					text: 'Copy to Clipboard'
				}, {
					extend: 'csv',
					className: 'excelButton',
					text: 'Download to Excel'
				}, {
					extend: 'print',
					className: 'printButton'
				}]
			}).fnAddData([
				dat.requesterpersonnumber,
				dat.requesterpersonname,
				/*	dat.requestid,*/
				dat.requestdate, /** when person submit the reimbursement */
				dat.submitdate, /** when goes to approver person */
				dat.buname,
				dat.climedamt,
				dat.approvedamt,
				dat.message
				//dat.finalapprovedamt,
				//dat.finalstatus
			]);
		}
		$(".dataTables_scrollHeadInner").removeAttr("style");
		$(".dataTable").removeAttr("style");
		$('#resultDivApprove').css('display', 'none');
		$('#resultDivReject').css('display', 'block');
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'none');
		$('#bulkDataSave').css('display', 'none');
	}
};

function populatesubmittedapprovalaction(data) {
	$('#Reimbursement_Approve').css('width', '100%');
	$("#Reimbursement_Approve").DataTable().clear();
	$('#resultDivApprove').css('display', 'none');
	$('#resultDivReject').css('display', 'none');
	$('#resultDiv').css('display', 'none');
	$("#REMB_BULK_LOADER").css("display", "block");
	// $('#Reimbursement_History').dataTable(  );
	var dataLength = data.length;
	if(dataLength == 0) {
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		$("#REMB_BULK_LOADER").css("display", "none");
		$('#bulkDataSave').css('display', 'none');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			var chkbtn = '';
			chkbtn = '<input  type="checkbox"  class="w3-check checkClickOk" onclick="handleClick(this)" name="notificationModels[' + i + '].flag" value="on" checked ></td>';
			$("#Reimbursement_Approve").dataTable({
				scrollY: '50vh',
				scrollCollapse: true,
				paging: false,
				destroy: true,
				searching: false,
				className: 'select-checkbox',
				targets: 0,
				dom: 'Bfrtip',
				buttons: [{
					extend: 'copy',
					text: 'Copy to Clipboard'
				}, {
					extend: 'csv',
					className: 'excelButton',
					text: 'Download to Excel',
					exportOptions: {
						format: {
							body: function(data, row, column, node) {
								var retorno = "",
									tag, respuesta = "",
									reponer = [];
								tag = $(node).find('input[type="text"]');
								if(tag.length > 0) {
									retorno = retorno + ($(tag).map(function() {
										return $(this).val();
									}).get().join(','));
								}
								respuesta = (retorno != "") ? retorno : $.trim($(node).text());
								for(i = 0; i < reponer.length; i++) {
									$(node).append(reponer[i]);
								}
								return respuesta;
							}
						}
					}
				}, {
					extend: 'print',
					className: 'printButton'
				}]
			}).fnAddData([
				chkbtn, // '<input  type="checkbox"  class="w3-check checkClickOk" onclick="handleClick(this)" name="notificationModels[' + i + '].requestid" value="' + dat.requestid + '" >',
				dat.requesterpersonnumber + '<input type="hidden" name="notificationModels[' + i + '].submittedbypersonno" value="' + dat.requesterpersonnumber + '">',
				dat.requesterpersonname + '<input type="hidden" name="notificationModels[' + i + '].requesterpersonname" value="' + dat.requesterpersonname + '">',
				dat.requestdate+'<input  type="hidden" name="notificationModels[' + i + '].notificationid" value="' + dat.approvalid + '">' + '<input type="hidden" name="notificationModels[' + i + '].submittedtopersonname" value="' + dat.approverpersonname + '">' + '<input type="hidden" name="notificationModels[' + i + '].submittedtopersonno" value="' + dat.approverpersonnumber + '">' + '<input type="hidden" name="notificationModels[' + i + '].moduleid" value="' + dat.moduleid + '">' + '<input type="hidden" name="notificationModels[' + i + '].approvallevel" value="' + dat.approvallevel + '">' + '<input type="hidden" name="notificationModels[' + i + '].requestid" value="' + dat.requestid + '">',
				dat.submitdate,
				dat.buname,
				dat.modulename + '<input type="hidden" name="notificationModels[' + i + '].modulename" value="' + dat.modulename + '">',
				dat.climedamt, 
				'<input class="myapprovedAmt"  type="text" name="notificationModels[' + i + '].approvedamount" value="' + dat.approvedamt + '">', 
				'<input class="myapprovedMsg" type="text" name="notificationModels[' + i + '].message" value="" >', 
				
				//dat.submitdate,
				//dat.finalapprovedamt,
				//dat.finalstatus,
			]);
		}
		$(".dataTables_scrollHeadInner").removeAttr("style");
		$(".dataTable").removeAttr("style");
		$("#REMB_BULK_LOADER").css("display", "none");
		$('#resultDiv').css('display', 'block');
		$('#resultDivApprove').css('display', 'none');
		$('#resultDivReject').css('display', 'none');
		$('#noData').css('display', 'none');
		$('#bulkDataSave').css('display', 'block');
	}
};

function disablebtn()
{
	$('#bulkDataSave').css('display', 'none');
}

function status() {
	$("#action").prop('disabled', false);
}

function fromdate() {
	$("#fromdate").prop('disabled', false);
}

function bussinessunit() {
	$("#buid").prop('disabled', false);
}

function todate() {
	$("#todate").prop('disabled', false);
	$("#todate").val('');
}

function search() {
	$("#searchbtn").prop('disabled', false);
}
$('#fromdate').on('change', function(e) {
	var from = $('#fromdate').val();
	$('#todate').attr("min", from);
});

function handleClick(obj) {

	if(!obj.checked) {
		obj.value = "off";
	} else {
		obj.value = "on";
	}
}

function checkuncheckAll(obj) {
	
	if(!obj.checked) {
		clickCount = true;
	} else {
		clickCount = false;
	}
	var inputs = $(".checkClickOk");
	if($('#checkuncheckRembbtn').prop("checked")) {
		for(i = 0; i < inputs.length; i++) {
			inputs[i].checked = true;
		}
	} else {
		for(i = 0; i < inputs.length; i++) {
			inputs[i].checked = false;
		}
	}
}

function bulkApprovalSave(val) {

	
	// var textamt = document.getElementsByClassName("myapprovedAmt");
	var checkClick = document.getElementsByClassName("checkClickOk");
	//	var numberNotChecked = $('input:checkbox:not(":checked")').length;
	var numberOfChecked = $('input:checkbox:checked').length;
	//alert(numberOfChecked);
	/*

	     for (l = 0; l < checkClick.length; l++) {
	    var click = checkClick[l].value;


	    if ($(click).is(':checked').length<1){
	        bootbox.alert({
	            size: 'medium',
	            title: '<i class="fa fa-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
	            message: 'Please select any claim before Submit.',
	        });
	        return;
	        }
	    }
	*/
	if(numberOfChecked < 1) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
			message: 'Please select any claim before Submit.',
		});
		return;
	}
	var status = val;
	
	if(status=="Rejected"){
			bootbox.confirm({
			message: "Do you want to Reject Reimbursement ?",
			buttons: {
				confirm: {
					label: 'Yes',
					className: 'btn-success'
				},
				cancel: {
					label: 'No',
					className: 'btn-danger'
				}
			},
			callback: function(result) {
				if(result == true) {
					
	var formData = $('#dataMappings').serialize();
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: "/mss/submittedApprovalData/" + status,
		type: "POST",
		cache: false,
		data: formData,
		processData: false,
		dataType: "json",
		success: function(result) {
			//	alert(JSON.stringify(result));
			$('#replace_div').html(result);
			// alert(status)
			if(status == "Approved") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'Claim Approved Successfully.',
					callback: function() {
						window.location = "/selfservice";
						//$('#replace_div').load("/mss/manageapprovalactions");
					}
				});
			}
			if(status == "Rejected") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'Claim Reject Successfully.',
					callback: function() {
						$('#replace_div').load("/mss/manageapprovalactions");
					}
				});
			}
		},
		error: function(e) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Something went wrong Please try again.",
			});
		}
	});
					} else {
						return;
					}
				}		
		});
	
	}else{
		
		

	
	var formData = $('#dataMappings').serialize();
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: "/mss/submittedApprovalData/" + status,
		type: "POST",
		cache: false,
		data: formData,
		processData: false,
		dataType: "json",
		success: function(result) {
			//	alert(JSON.stringify(result));
			$('#replace_div').html(result);
			// alert(status)
			if(status == "Approved") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'Claim Approved Successfully.',
					callback: function() {
						window.location = "/selfservice";
						//$('#replace_div').load("/mss/manageapprovalactions");
					}
				});
			}
			if(status == "Rejected") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'Claim Reject Successfully.',
					callback: function() {
						$('#replace_div').load("/mss/manageapprovalactions");
					}
				});
			}
		},
		error: function(e) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Something went wrong Please try again.",
			});
		}
	});
	}
}