//bootbox.alert("Hello...");

function searchremtemplempl(){
	$("#REMTEMP_LOADER").css("display","block");
	$('#REM_TMPL_SAVE_BTN').css('display', 'none');
	var remid=$('#REMID').val();
	var bu=$('#BULOV option:selected').val();
	var fyear=$('#FYEARLOV option:selected').val();
	var aurl="/revisedreimbursements/searchremtemplempl/"+remid+"/"+bu+"/"+fyear;
	$.ajax({
		type: 'GET',
		url: aurl,
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateremtempempl(result,remid);
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
}

/*$("#ASSIGN_SEARCH_RES").DataTable({
	'columnDefs': [{
		'targets': [2,3],
		'orderable': false
	}]
});*/

$("#REM_TEMPL_TBL").DataTable({
	'paging':false,
	'ordering':false
});

/*{
	paging: false,
		destroy: true,
	searching: true,
}*/

function populateremtempempl(data,RID){
	$("#REM_TEMPL_TBL").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		//$("#ASGN_SUBMIT_BTN").css("display", "none");
		$("#REMTEMP_LOADER").css("display","none");
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		
	} else {
		//$("#ASGN_SUBMIT_BTN").css("display", "block");
		//console.log(data[0].personnumber);
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			if(RID=="7"){
				$("#REM_TEMPL_TBL").dataTable().fnAddData([
					dat.personnumber+'<input type="hidden" name="remTemplates[' + i + '].personnumber" value="'+dat.personnumber+'">',
					dat.name+'<input type="hidden" name="remTemplates[' + i + '].name" value="'+dat.name+'">',
					dat.hiredate+'<input type="hidden" name="remTemplates[' + i + '].hiredate" value="'+dat.hiredate+'">',
					dat.designation+'<input type="hidden" name="remTemplates[' + i + '].designation" value="'+dat.designation+'">',
					dat.hrstatus+'<input type="hidden" name="remTemplates[' + i + '].hrstatus" value="'+dat.hrstatus+'">',
					dat.payrollstatus+'<input type="hidden" name="remTemplates[' + i + '].payrollstatus" value="'+dat.payrollstatus+'">',
					'<input type="text" name="remTemplates[' + i + '].reimbursementamount" value="'+dat.reimbursementamount+'">',
					'<input type="text" name="remTemplates[' + i + '].vaamt" value="'+dat.vaamt+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].vaarramt" value="'+dat.vaarramt+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].businessunitid" value="'+dat.businessunitid+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].positionid" value="'+dat.positionid+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].tempid" value="'+dat.tempid+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].reimid" value="'+RID+'">'
				]);
			}else{
				$("#REM_TEMPL_TBL").dataTable().fnAddData([
					dat.personnumber+'<input type="hidden" name="remTemplates[' + i + '].personnumber" value="'+dat.personnumber+'">',
					dat.name+'<input type="hidden" name="remTemplates[' + i + '].name" value="'+dat.name+'">',
					dat.hiredate+'<input type="hidden" name="remTemplates[' + i + '].hiredate" value="'+dat.hiredate+'">',
					dat.designation+'<input type="hidden" name="remTemplates[' + i + '].designation" value="'+dat.designation+'">',
					dat.hrstatus+'<input type="hidden" name="remTemplates[' + i + '].hrstatus" value="'+dat.hrstatus+'">',
					dat.payrollstatus+'<input type="hidden" name="remTemplates[' + i + '].payrollstatus" value="'+dat.payrollstatus+'">',
					'<input type="text" name="remTemplates[' + i + '].reimbursementamount" value="'+dat.reimbursementamount+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].businessunitid" value="'+dat.businessunitid+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].positionid" value="'+dat.positionid+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].tempid" value="'+dat.tempid+'">'+
					'<input type="hidden" name="remTemplates[' + i + '].reimid" value="'+RID+'">'
				]);
			}

		}
		$("#REMTEMP_LOADER").css("display","none");
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#REM_TMPL_SAVE_BTN').css('display', 'inline-block');

	}
};

//var table=$('#EnterpriseList').DataTable();






function saveremtemplate() {
			
var value = $('.dataTables_filter input').val();
if(value===null||value==''){			
			
			$("#REMTEMP_LOADER").css("display","block");
			var remtemplformdata = $('#REM_TEMPL_FORM').serialize();
			$.ajax({
				url: "/revisedreimbursements/saveremtempl",
				type: "POST",
				data: remtemplformdata,
				cache: false,
				contentType: "application/x-www-form-urlencoded",
				processData: false,
				success: function (result) {
					$("#REMTEMP_LOADER").css("display","none");
					if(result=="Success"){
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"Template successfully saved.",
							callback: function () {
								$('#replace_div').load("/revisedreimbursements/remtemplate/"+$('#REMID').val());
							}
						});
					}

				},
				error: function (response) {
					$("#REMTEMP_LOADER").css("display","none");
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "You have logged off please login again.",
					});
				}
			});
			}else{
		bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Search criteria should be blank before save or submit.",
			});
}
}


/*
var identifiedEmployeedata = $('#identifiedEmployeeForm').serialize();
$.ajax({
	url: "/payrollprocessing/identifyEmployee/postIdentifiedEmployee",
	type: "POST",
	data: identifiedEmployeedata,
	cache: false,
	contentType: "application/x-www-form-urlencoded",
	processData: false,
	success: function (result) {
	},
	error: function (response) {
		alert(JSON.parse(response.responseText));
	}
});*/
