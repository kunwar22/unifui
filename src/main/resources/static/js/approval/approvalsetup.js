function addrow() {
	//debugger;
	var data = "";
	data = '<tr>' +
		'<td style="width: 11%;">' +
		'<input type="hidden" id="approvalID" name="approvalsave[0].approvalId" value=0>' +		
		'<select id="APPR_SETUP_ID" class="w3-select w3-border" name="approvalsave[0].reimburseType">' +
		'<option value="0" selected disabled></option>' + $('#APPR_SETUP_ID0').html() +
		'</select>' +
		'</td>' +
		'<td style="width: 11%;">' +
		'<input type="hidden" id="modulename" name="approvalsave[0].modulename" value="' + $('#modulename0').val() + '" >' +
		'<select id="APPR_SETUP_CATEGORY" onchange="categorybind();" class="w3-select w3-border" name="approvalsave[0].category">' +
		'<option selected disabled></option>' +$('#APPR_SETUP_CATEGORY0').html()+
		'</select>' +
		'</td>' +
		'<td style="width: 11%;">' +
		'<select id="APPR_ONE" class="w3-select w3-border" name="approvalsave[0].approve1">' +
		//'<option value="0" selected></option>' + 
		$('#APPR_ONE0').html() +
		'</select>' +
		'</td>' +
		'<td style="width: 11%;">' +
		'<select id="APPR_TWO" class="w3-select w3-border" name="approvalsave[0].approve2">' +
		//'<option value="0" selected></option>' + 
		$('#APPR_TWO0').html() +
		'</select>' +
		'</td>' +
		'<td style="width: 11%;">' +
		'<select id="APPR_THREE" class="w3-select w3-border" name="approvalsave[0].approve3">' +
		//'<option value="0" selected></option>' + 
		$('#APPR_THREE0').html() +
		'</select>' +
		'</td>' +
		'<td style="width: 11%;">' +
		'<select id="APPR_FOUR" class="w3-select w3-border" name="approvalsave[0].approve4">' +
		//'<option value="0" selected></option>' +
		$('#APPR_FOUR0').html() +
		'</select>' +
		'</td>' +
		'<td style="width: 11%;">' +
		'<select id="APPR_FIVE" class="w3-select w3-border" name="approvalsave[0].approve5">' +
		//'<option value="0" selected></option>' + 
		$('#APPR_FIVE0').html() +
		'</select>' +
		'</td>' +
		'<td style="width: 11%;">' +
		'<select id="APPR_SIX" class="w3-select w3-border" name="approvalsave[0].approve6">' +
		//'<option value="0" selected></option>' + 
		$('#APPR_SIX0').html() +
		'</select>' +
		'</td><td style="width: 4%;">' +
		'<div id="btndel" class="w3-btn w3-theme" onclick="deleteRow(this)"><i class="fa fa-trash"></i></div>' +		
		'</tr>';

	$("#REIMBURSE_SETUP_TBL tbody").append(data);
	//window.globalCounter++;

	$.each($('#REIMBURSE_SETUP_TBL tr'), function(index, val) {
		//debugger;
		$(this).find("td:eq(0) input[type='hidden']").attr('id', 'approvalID' + (index - 1));
		$(this).find("td:eq(0) input[type='hidden']").attr('name', 'approvalsave[' + (index - 1) + '].approvalId');
		$(this).find("td:eq(1) input[type='hidden']").attr('id', 'modulename' + (index - 1));
		$(this).find("td:eq(1) input[type='hidden']").attr('name', 'approvalsave[' + (index - 1) + '].modulename');
		$(this).find("td:eq(0)").find("select").attr('id', 'APPR_SETUP_ID' + (index - 1));
		$(this).find("td:eq(0)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].reimburseType');
		$(this).find("td:eq(1)").find("select").attr('id', 'APPR_SETUP_CATEGORY' + (index - 1));
		$(this).find("td:eq(1)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].category');
		$(this).find("td:eq(1)").find("select").attr('onchange', 'categorybind(' + (index - 1) + ');');
		$(this).find("td:eq(2)").find("select").attr('id', 'APPR_ONE' + (index - 1));
		$(this).find("td:eq(2)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve1');
		$(this).find("td:eq(3)").find("select").attr('id', 'APPR_TWO' + (index - 1));
		$(this).find("td:eq(3)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve2');
		$(this).find("td:eq(4)").find("select").attr('id', 'APPR_THREE' + (index - 1));
		$(this).find("td:eq(4)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve3');
		$(this).find("td:eq(5)").find("select").attr('id', 'APPR_FOUR' + (index - 1));
		$(this).find("td:eq(5)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve4');
		$(this).find("td:eq(6)").find("select").attr('id', 'APPR_FIVE' + (index - 1));
		$(this).find("td:eq(6)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve5');
		$(this).find("td:eq(7)").find("select").attr('id', 'APPR_SIX' + (index - 1));
		$(this).find("td:eq(7)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve6');		

		// $(this).find("td:eq(0)").find("label").attr('name','na['+index+']');
		// $(this).find("td:eq(1)").find("label").attr('name','na['+index+']');
	});

}


function deleteRow(e, approvalid) {
	////debugger;
	//alert("approvalid: "+approvalid);
	if ($("#REIMBURSE_SETUP_TBL tr").length <= 2) {

	}
	else {
		if (approvalid == null) {
			$(e).parent().parent().remove();

			$.each($('#REIMBURSE_SETUP_TBL tr'), function(index, val) {
				$(this).find("td:eq(0) input[type='hidden']").attr('id', 'approvalID' + (index - 1));
				$(this).find("td:eq(0) input[type='hidden']").attr('name', 'approvalsave[' + (index - 1) + '].approvalId');
				$(this).find("td:eq(1) input[type='hidden']").attr('id', 'modulename' + (index - 1));
				$(this).find("td:eq(1) input[type='hidden']").attr('name', 'approvalsave[' + (index - 1) + '].modulename');
				$(this).find("td:eq(0)").find("select").attr('id', 'APPR_SETUP_ID' + (index - 1));
				$(this).find("td:eq(0)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].reimburseType');
				$(this).find("td:eq(1)").find("select").attr('id', 'APPR_SETUP_CATEGORY' + (index - 1));
				$(this).find("td:eq(1)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].category');
				$(this).find("td:eq(1)").find("select").attr('onchange', 'categorybind(' + (index - 1) + ');');
				$(this).find("td:eq(2)").find("select").attr('id', 'APPR_ONE' + (index - 1));
				$(this).find("td:eq(2)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve1');
				$(this).find("td:eq(3)").find("select").attr('id', 'APPR_TWO' + (index - 1));
				$(this).find("td:eq(3)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve2');
				$(this).find("td:eq(4)").find("select").attr('id', 'APPR_THREE' + (index - 1));
				$(this).find("td:eq(4)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve3');
				$(this).find("td:eq(5)").find("select").attr('id', 'APPR_FOUR' + (index - 1));
				$(this).find("td:eq(5)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve4');
				$(this).find("td:eq(6)").find("select").attr('id', 'APPR_FIVE' + (index - 1));
				$(this).find("td:eq(6)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve5');
				$(this).find("td:eq(7)").find("select").attr('id', 'APPR_SIX' + (index - 1));
				$(this).find("td:eq(7)").find("select").attr('name', 'approvalsave[' + (index - 1) + '].approve6');				
				// $(this).find("td:eq(0)").find("label").attr('name','na['+index+']');
				// $(this).find("td:eq(1)").find("label").attr('name','na['+index+']');
			});
		}
		else {
			var result;
			var deleteurl = "/approvalsetup/deleteApproval/" + approvalid;
			bootbox.confirm("Are you sure you want to delete?", function(result) {
				if (result) {
					$.ajax({
						url: deleteurl,
						type: 'POST',
						dataSrc: '',
						dataType: 'json',
						cache: false,
						contentType: "application/x-www-form-urlencoded",
						processData: false,
						success: function(data) {
							console.log("result=====> " + data.status);
							if (data.status == "Success") {
								bootbox.alert({
									size: 'medium',
									title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
									message: "You have successfully deleted approval data.",
									callback: function() {
										$('#replace_div').load("/approvalsetup/manageapprovalsetup/" + $('#modulename0').val());
									}
								});
							}
							else if (data.status != "Success") {
								bootbox.alert({
									size: 'medium',
									title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
									message: "Unable to delete approval data.",
									callback: function() {
										$('#replace_div').load("/approvalsetup/manageapprovalsetup/" + $('#modulename0').val());
									}
								});
							}
						}, error: function(response) {
							console.log(JSON.parse(response.responseText));
							bootbox.alert({
								size: 'medium',
								title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
								message: "Something went wrong while deleting approval.<br/> Please try again after some time.",
								callback: function() {
									$('#replace_div').load("/approvalsetup/manageapprovalsetup/" + $('#modulename0').val());
								}
							});
						}

					});
				}
			});
		}
	}

}

function editdata() {
	//alert("hi");
	$('select').removeAttr("disabled");
	$('button').removeAttr("disabled");
	$('#add').removeAttr("disabled");
}


/****************************CODE FOR APPROVER BIND START************************/
function categorybind(id) {
	////debugger;
	//alert("hello country "+countryList);
	var category = $('#APPR_SETUP_CATEGORY' + id).val();
	//alert(category);
	var jsonUrl = '/approvalsetup/categorybind/' + category;
	//alert("url ====> "+jsonUrl);
	newStatebind = "";
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',

		dataType: 'json',
		success: function(data) {
			//alert("Inside Success");
			newStatebind += '<option value="" disabled selected></option>';
			data.forEach(function(n) {
				newStatebind += '<option value="' + n.id + '" >' + n.name + '</option>';
			});

			//$('#divStateDisp').css("display","none");
			$('#APPR_ONE' + id).html(newStatebind);
			$('#APPR_TWO' + id).html(newStatebind);
			$('#APPR_THREE' + id).html(newStatebind);
			$('#APPR_FOUR' + id).html(newStatebind);
			$('#APPR_FIVE' + id).html(newStatebind);
			$('#APPR_SIX' + id).html(newStatebind);



			//populateDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}




/****************************CODE FOR APPROVER BIND END************************/



function ajaxPost() {
	////debugger;
	var fd = $("#APPR_FORM").serialize();
	console.log("dataaaaaaaaaaaa " + fd);

	$.ajax({
		url: "/approvalsetup/saveApproval",
		type: "POST",
		data: fd,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(data) {
			//$('#replace_div').html(data);
			//console.log("result=====>"+resultfinal);
			if (data.status == "Success") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: "You have successfully saved approval data.",
					callback: function() {
						$('#replace_div').load("/approvalsetup/manageapprovalsetup/" + $('#modulename0').val());
					}
				});
			}
			else if (data.status != "Success") {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Something went wrong while saving approval data!!<br/>Please Try again",
					callback: function() {
						$('#replace_div').load("/approvalsetup/manageapprovalsetup/" + $('#modulename0').val());
					}
				});
			}
		},
		error: function(response) {
			console.log(JSON.parse(response.responseText));
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Unable to save approval data.",
				callback: function() {
					$('#replace_div').load("/approvalsetup/manageapprovalsetup/" + $('#modulename0').val());
				}
			});
		}
	});
};


/*$(document).on("click","#delete",function(){
	//$("table").row($(this).parents('tr')).remove().draw(false);
	var dex=$(this).attr('index');
	var flg=$(this).attr('flg');
	$(this).parents("tr").remove();
	removeRow(dex,flg);
	$.each($('#NAT_ID_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','nationaldetails['+(index - 1)+'].primary');

		$(this).find("td:eq(1)").find("select").attr('name','nationaldetails['+(index - 1)+'].countryid');
	$(this).find("td:eq(2)").find("select").attr('name','nationaldetails['+(index - 1)+'].nationaltype');
	$(this).find("td:eq(3) input[type='text']").attr('name','nationaldetails['+(index - 1)+'].nationalid');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));

	});


});*/



