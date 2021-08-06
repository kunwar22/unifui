function addrowmanager() {
	var data = "";
	//data='<tr><td style="width:25%"><select id="MGR' + window.globalCounter + '" onchange="mgrchange(' + window.globalCounter + ')" class="w3-select w3-border" name="createlwp[' + window.globalCounter + '].personnumber"><option id="1" value="" selected disabled></option><option id="2" value="" selected ></option><option value="search" data-toggle="modal" id="btnsearchLegal">Search....</option></select></td><td style="width:30%;"><input class="w3-input w3-border"  name="createlwp['+window.globalCounter+'].lwp" id="LWP['+window.globalCounter+']" type="text"></td><td style="width:30%;"><input class="w3-input w3-border"  name="createlwp['+window.globalCounter+'].lwp" id="LWP['+window.globalCounter+']" type="text"></td>    <td style="width:5%"><input class="w3-btn w3-theme" id="deletemgr"  type="button" value="x"/></td></tr>';
	data = '<tr class="myrowclass" >' + ' <td style="width:30%"><select id="expensetypevalue' + window.globalCounter + '" class="w3-select w3-border expensetypeClass" ' + 'name="expensedetails[' + window.globalCounter + '].expencetype">' + '<option  value="0" selected>select</option>' + optionsString + '</select></td> ' + '<td style="width:30%;"><input class="w3-input w3-border traveldate traveldateClass"  ' + 'name="expensedetails[' + window.globalCounter + '].traveldate" id="expensedetails[' + window.globalCounter + '].traveldate" ' + 'type="date" type="date" data-date="" data-date-format="YYYY MM DD"> </td>' + '<td style="width:30%;"><input class="w3-input w3-border expenseDescrClass"  ' + 'name="expensedetails[' + window.globalCounter + '].expencedescription" id="expensedetails[' + window.globalCounter + '].expencedescription" ' + 'type="text"></td>' + '<td style="width:30%;"><input oninput="this.value=this.value.replace(/[^0-9.]/g,\'\').replace(/(\\..*)\\./g,\'$1\');" class="w3-input w3-border billamount amountpaidClass"  ' + 'onchange="billcount()" value=0 ' + 'name="expensedetails[' + window.globalCounter + '].amountpaid" id="expensedetails[' + window.globalCounter + '].amountpaid" ' + 'type="text"></td>' + '<td style="width:10%;"><input  type="file" id="file"  name="file" class="file"/></td>' + '<td style="width:10%"><input class="w3-btn w3-theme" id="deletemgr" flg="claim"   type="button" value="x"/></td></tr>';
	$("#REIMBURSEMENT_TYPE_TBL tbody").prepend(data);
	$.each($('#REIMBURSEMENT_TYPE_TBL tr'), function(index, val) {
		$(this).find("td:eq(0)").find("select").attr('name', 'expensedetails[' + (index - 1) + '].expencetype');
		$(this).find("td:eq(0)").find("select").attr('id', 'expensetypevalue' + (index - 1));
		$(this).find("td:eq(1) input[type='date']").attr('id', 'expensedetails' + (index - 1));
		$(this).find("td:eq(1) input[type='date']").attr('name', 'expensedetails[' + (index - 1) + '].traveldate');
		$(this).find("td:eq(1) input[type='date']").attr('onclick', 'daterange(' + (index - 1) + ')');
		$(this).find("td:eq(2) input[type='text']").attr('id', 'expensedetails' + (index - 1));
		$(this).find("td:eq(2) input[type='text']").attr('name', 'expensedetails[' + (index - 1) + '].expencedescription');
		$(this).find("td:eq(3) input[type='text']").attr('id', 'expensedetails' + (index - 1));
		$(this).find("td:eq(3) input[type='text']").attr('name', 'expensedetails[' + (index - 1) + '].amountpaid');
		$(this).find("td:eq(3) input[type='text']").attr('onchange', 'billcount(' + (index - 1) + ')');
		$(this).find("td:eq(4) input[type='file']").attr('id', 'file');
		$(this).find("td:eq(4) input[type='file']").attr('name', 'file');
	});
}
$(document).on("click", "#deletemgr", function() {
	billcount()
		//$("table").row($(this).parents('tr')).remove().draw(false);
	var dex = $(this).attr('index');
	var flg = $(this).attr('flg');
	$(this).parents("tr").remove();
	removeclaim(dex, flg);
	$(this).parents("tr").remove();
	$.each($('#REIMBURSEMENT_TYPE_TBL tr'), function(index, val) {
		$(this).find("td:eq(0)").find("select").attr('name', 'expensedetails[' + (index - 1) + '].expencetype');
		$(this).find("td:eq(0)").find("select").attr('id', 'expensetypevalue' + (index - 1));
		$(this).find("td:eq(1) input[type='date']").attr('id', 'expensedetails' + (index - 1));
		$(this).find("td:eq(1) input[type='date']").attr('name', 'expensedetails[' + (index - 1) + '].traveldate');
		$(this).find("td:eq(2) input[type='text']").attr('id', 'expensedetails' + (index - 1));
		$(this).find("td:eq(2) input[type='text']").attr('name', 'expensedetails[' + (index - 1) + '].expencedescription');
		$(this).find("td:eq(3) input[type='text']").attr('id', 'expensedetails' + (index - 1));
		$(this).find("td:eq(3) input[type='text']").attr('name', 'expensedetails[' + (index - 1) + '].amountpaid');
		$(this).find("td:eq(3) input[type='text']").attr('onchange', 'billcount(' + (index - 1) + ')');
		$(this).find("td:eq(4) input[type='hidden']").attr('id', 'attachments' + (index - 1));
		$(this).find("td:eq(4) input[type='hidden']").attr('name', 'expensedetails[' + (index - 1) + '].attachments');
		$(this).find("td:eq(4) input[type='file']").attr('id', 'file');
		$(this).find("td:eq(4) input[type='file']").attr('name', 'file');
		//  $(this).find("td:eq(5) input[type='file']").attr('id', 'file');
		// $(this).find("td:eq(5) input[type='file']").attr('name', 'file');
		$(this).find("td:eq(5) input[type='button']").attr('index', (index - 1));
		$(this).find("td:eq(6) input[type='hidden']").attr('id', 'id' + (index - 1));
		$(this).find("td:eq(6) input[type='hidden']").attr('name', 'expensedetails[' + (index - 1) + '].id');
	});
});
//});
function removeclaim(index, flg) {
	var jurl = "../reimbursement/travelexpense/removemanager/" + index + "/" + flg;
	$.ajax({
		type: 'GET',
		url: jurl,
		success: function(data) {
			console.log("Success...");
		},
		error: function(e) {
			//alert(JSON.stringify(e));
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}
$("#deletemgr").unbind().click(function() {
	//Stuff
})

function billcount() {
	var tamt = $("#totalamount");
	var bamt = $(".billamount");
	var amt = 0;
	for(i = 0; i < bamt.length; i++) {
		if($(bamt[i]).val() == "") {
			$(bamt[i]).val("0")
		}
		amt += parseFloat($(bamt[i]).val());
	}
	$(tamt).val(amt);
}

function daterange() {
	var maxi = $("#travelingdatefrom").val();
	var mini = $("#travelingdateto").val();
	var travdt = $(".traveldateClass");
	if(!maxi || !mini) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "please select Traveling Out and return date. ",
		});
	} else {
		for(i = 0; i < travdt.length; i++) {
			$(travdt[i]).attr("max", mini);
			$(travdt[i]).attr("min", maxi);
		}
	}
}

function billcountApprver() {
	var tamt = $("#txtAmount");
	var bamt = $(".amountpaidClass2");
	var amt = 0;
	for(i = 0; i < bamt.length; i++) {
		if($(bamt[i]).val() == "") {
			$(bamt[i]).val("0")
		}
		amt += parseFloat($(bamt[i]).val());
	}
	$(txtAmount).val(amt);
}

function saveDataFunc(str, frmid, x) {
	billcount()
	var totalamt = parseFloat($("#totalamount").val());
	if(totalamt == 0.0 || totalamt == 0) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "You can not claim with 0 amt. ",
		});
		$('#totalamount').val();
		return;
	}
	var iteratorrow = document.getElementsByClassName("myrowclass");
	var myexpensetype = document.getElementsByClassName("expensetypeClass");
	var mytraveldate = document.getElementsByClassName("traveldateClass");
	var myexpenseDescr = document.getElementsByClassName("expenseDescrClass");
	var myamountpaid = document.getElementsByClassName("amountpaidClass");
	var parent = x.parentNode;
	if(parent.id == "saveData") {
		for(l = 0; l < iteratorrow.length - 1; l++) {
			var expensetype = myexpensetype[l].value;
			var traveldate = mytraveldate[l].value;
			var expenseDescr = myexpenseDescr[l].value;
			var amountpaid = parseFloat(myamountpaid[l].value);
			//  if ( traveldate[l] == ''||traveldate[l] ===null||traveldate[l] ==="") {
			if(!traveldate) {
				flag = 'no';
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
					message: "Please enter the travel date.",
				});
				$('#travelDate' + l).val();
				return;
			}
			if(expensetype == '' || expensetype == "select" || expensetype == 'select' || expensetype == '0') {
				flag = 'no';
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
					message: "Please enter the expense type.",
				});
				$('#expensetypevalue' + l).val();
				return;
			}
			/*if (expenseDescr[l] == "" ) {
			    flag = 'no';
			    bootbox.alert({
			        size: 'medium',
			        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			        message: "Please enter the expense description.",
			    });
			    $('#expenceDescr' + l).val("");
			    return;
			}*/
			/* if ( amountpaid[l] == '0.0' || amountpaid[l] == "0" ) {
			     flag = 'no';
			     bootbox.alert({
			         size: 'medium',
			         title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			         message: "Paid amount should not be zero or blank.",
			     });
			     //$('#AmountPaid' + l).val(0.0);
			     return;
			 }*/
			if(amountpaid[l] == 0 || amountpaid[l] == "0" || amountpaid[l] == NaN || amountpaid[l] == '' || amountpaid[l] == 0.0) {
				flag = 'no';
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
					message: "Paid amount should not be zero or blank.",
				});
				$('#AmountPaid' + l).val(0);
				return;
			}
		}
	}
	if(parent.id == "updateSubmit") {
		//  UrlTravel='/reimbursement/travelExpenseSave/';
		for(l = 0; l < iteratorrow.length; l++) {
			var expensetype = myexpensetype[l].value;
			var traveldate = mytraveldate[l].value;
			var expenseDescr = myexpenseDescr[l].value;
			var amountpaid = parseFloat(myamountpaid[l].value);
			if(!traveldate) {
				flag = 'no';
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
					message: "Please enter the travel date.",
				});
				$('#travelDate' + l).val();
				return;
			}
			if(expensetype == '' || expensetype === "select" || expensetype === 'select' || expensetype == 0) {
				flag = 'no';
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
					message: "Please enter the expense type.",
				});
				$('#expensetypevalue' + l).val();
				return;
			}
			/*if ( expenseDescr[l] == '' || expenseDescr[l] == null|| expenseDescr[l] == undefined ) {

			    console.log(l+"::::"+expenseDescr[l])
			    flag = 'no';
			    bootbox.alert({
			        size: 'medium',
			        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			        message: "Please enter the expense description.",
			    });
			    $('#expenceDescr' + l).val();
			    return;
			}*/
			if(amountpaid[l] == 0) {
				flag = 'no';
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
					message: "Paid amount should not be zero or blank.",
				});
				$('#AmountPaid' + l).val(0);
				return;
			}
			if(amountpaid[l] == "") {
				flag = 'no';
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
					message: "Paid amount should not be zero or blank.",
				});
				$('#AmountPaid' + l).val(0);
				return;
			}
		}
	}
	if($("#requestedamt").val() == "0") {
		$("#requestedamt").val("");
	}
	$(x).css("display", "none");
	var attachm = $(".attachments");
	var personnumber = $("#personnumber").val();
	$("#statusid").val(str);
	var doctype = "TravelExpense";
	personnumber = personnumber.trim();
	doctype = doctype.trim();
	var filepath;
	filepath = personnumber + "/" + doctype;
	var form = $("#Travel_Remb_Form")[0];
	var data = new FormData(form);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: "/reimbursement/travelExpenseSave/" + frmid,
		type: "POST",
		data: data,
		enctype: "multipart/form-data",
		cache: false,
		contentType: false,
		processData: false,
		timeout: 600000,
		success: function(data) {
			$('#replace_div').html(data);
			if(resultfinal == "Update" && frmid == "travelformUpdate" && str == "saved") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'You have successfully updated Travel Expense Reimbursement.',
					callback: function() {
						$('#replace_div').load("/reimbursement/travelExpense");
					}
				});
			} else if(resultfinal == "saved" && str == "submit") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'You have successfully submitted Travel Expense Reimbursement.',
					callback: function() {
						$('#replace_div').load("/reimbursement/travelExpense");
					}
				});
			} else if(resultfinal == "Update" && str == "submit") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'You have successfully submitted Travel Expense Reimbursement.',
					callback: function() {
						$('#replace_div').load("/reimbursement/travelExpense");
					}
				});
			} else if(resultfinal == "saved" && str == "saved") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'You have successfully saved Travel Expense Reimbursement.',
					callback: function() {
						$('#replace_div').load("/reimbursement/travelExpense");
					}
				});
			} else if(resultfinal == "" && str == "submit") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: "You have successfully submitted Travel Expense Reimbursement for approval.",
					callback: function() {
						$('#replace_div').load("/reimbursement/travelExpense");
					}
				});
			} else if(resultfinal == "alreadyApplied") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: 'Claim for the specified period has already been raised.<br> Please re-check your dates and attach the file again, if required.'
				});
				$('#travelingdateto').prop("disabled", false);
			} else {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Fill all the mandatory fields and attach the file again, if required.",
				});
				$('#travelingdateto').prop("disabled", false);
			}
		},
		error: function(data) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: 'Something went wrong please try again. '
			});
			$('#replace_div').html(data);
		}
	});
}
/***************************************************/
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
$('#travelingdatefrom').on('change', function(e) {
	var from = $('#travelingdatefrom').val();
	var todate = $('#travelingdateto').val();
	// if (from < minfy) {
	//    from = minfy;
	// }
	$('#travelingdateto').attr("min", from);
	$('#travelingdateto').attr("max", maxfy);
	$('#travelingdateto').prop("disabled", false);
	$('#travelingdateto').val("");
	var travdt = $(".traveldateClass");
	for(i = 0; i < travdt.length; i++) {
		$(travdt[i]).val("");
	}
});
$('#travelingdateto').on('click', function(e) {
	var from = $('#travelingdatefrom').val();
	if(from < minfy) {
		from = minfy;
	}
	// $('#travelingdateto').attr("min", from);
	//$('#travelingdateto').attr("max", maxfy);
});

function backBtnFunc() {
	var url = "/reimbursement/travelExpense";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type: "GET",
		url: url,
		success: function(result) {
			$('#replace_div').html(result);
		},
		error: function(e) {
			bootbox.alert("ERROR : " + JSON.stringify(e));
		}
	});
}
/**************************************************/
function approvalSubmit(_status) {
	var calimamt = document.getElementsByClassName("amountpaidClass");
	var approvedamt = document.getElementsByClassName("amountpaidClass2");
	for(l = 0; l < calimamt.length; l++) {
		var calimamtapprover = parseFloat(calimamt[l].value);
		var approvedamtapprover = parseFloat(approvedamt[l].value);
		/*if (approvedamtapprover ==""||approvedamtapprover ==null||approvedamtapprover ==0||approvedamtapprover =="0") {
		    bootbox.alert({
		        size: 'medium',
		        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
		        message: 'Can not approved 0 or Null.'
		    });
		    return true;
		}*/
		if(approvedamtapprover > calimamtapprover) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: 'Can not approved more than claim amount.'
			});
			return true;
		}
	}
	if($("#txtAmount").val() == '') {
		$("#txtAmount").val("0");
	}
	var formData = $('#Travel_Remb_Form').serialize();
	var amt = $("#txtAmount").val();
	var cmt = $("#txtComment").val();
	console.log(formData);
	if(_status == 'Approved') {
		if(amt == "" ) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: 'Request cannot be Approved without filling Approved Amount.'
			});
		} else {
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
				url: "/reimbursement/travelExpenseApproval/" + _status,
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
							message: "You have successfully Approved.",
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
					console.log("ERROR : " + JSON.stringify(e));
				}
			});
		}
	} else {
		if(cmt == "") {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: 'Request cannot be Rejected without filling Comments.'
			});
		} else {
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
				url: "/reimbursement/travelExpenseApproval/" + _status,
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
							message: "You have successfully rejected.",
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
					console.log("ERROR : " + JSON.stringify(e));
				}
			});
		}
	}
}

function travelExpenseDeclearation() {
	var claimid = $("#txtclaimidid").val();
	window.open("../reimbursement/travelExpenseDeclaration/" + claimid, 'window', 'width=600,height=600');
};


$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});


