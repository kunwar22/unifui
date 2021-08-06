
$(document).on('click').unbind();
$(document).ready(function() {
	searchAllCompletedApprovals();
});

function searchCompletedApprovals(cfdt, ctdt) {
	//debugger;
	var fdt = $('#' + cfdt).val();
	var tdt = $('#' + ctdt).val();
	var url="";
	
	if(fdt!="" && tdt==""){
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"To Date can not be null."	
						
			});
			return;	
	}else if(fdt=="" && tdt!=""){
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"From Date can not be null."	
							
			});
			return;
	}else if(fdt=="" && tdt==""){
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"From Date and To Date can not be null."	
							
			});
			return;
	}else if(fdt!="" && tdt!=""){
		url="/tasks/getWorklistTasks/" + fdt + "/Read/" + tdt;
	

	$.ajax({
		type: 'GET',
		url: url,
		dataSrc: '',
		success: function(data) {
			jsonData = data;
			populateCompletedWorklistDataTable(jsonData);
		},
		error: function(result) {
			console.log("ERROR : " + JSON.stringify(result));
		}
	});
	}
}

function searchAllCompletedApprovals() {
	var fdate = new Date("2020-11-01").toISOString().substr(0, 10);
	var tdate = new Date().toISOString().substr(0, 10);
	var fdt = fdate;
	var tdt = tdate;
	var url = "/tasks/getWorklistTasks/" + fdt + "/Read/" + tdt;
	$.ajax({
		type: 'GET',
		url: url,
		dataSrc: '',
		data: {
		},
		success: function(data) {
			jsonData = data;
			populateCompletedWorklistDataTable(jsonData);
		},
		error: function(result) {
			console.log("ERROR : " + JSON.stringify(result));
		}
	});
}

function populateCompletedWorklistDataTable(data) {
	////debugger;
	$("#completedtaskstbl").DataTable().clear();
	var dataLength = data.length;
	var apppath = "";

	if (dataLength == 0) {
		$('#completedtasks').css('display', 'none');
		$('#noCompletedData').css('display', 'block');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			var modid=parseInt(dat.moduleid);
			
			switch (modid){
				case 1:
					apppath = "/reimbursement/viewteleapprovaldata/" + dat.requestid + "/view";
					break;
				case 2:
					apppath = "/reimbursement/viewtransapprovaldata/" + dat.requestid + "/view";
					break;
				case 3:
					apppath = "/reimbursement/viewApprovalDataChild/" + dat.requestid + "/view_child"+"/Read";
					break;
				case 4:
					apppath = "/reimbursement/viewElectricityReimbursementApproval/" + dat.requestid + "/view"+"/Read";
					break;
				case 5:
					apppath = "/reimbursement/viewentertainmentapprovaldata/" + dat.requestid + "/view"+"/Read";
					break;
				case 6:
					apppath = "/reimbursement/viewadhocdataApproval/" + dat.requestid + "/view"+"/Read";
					break;
				case 7:
					apppath = "/cdaVehicle/viewcdaapproval/" + dat.requestid + "/view";
					break;
				case 9:
					apppath = "/reimbursement/approvalMedicalAdvance/" + dat.requestid + "/view"+"/Read";
					break;
				case 10:
					apppath = "/reimbursement/medireimbApproval/" + dat.requestid + "/view"+"/Read";
					break;
				case 11:
					apppath = "/ctgReimbursement/viewctgapproval/" + dat.requestid + "/view"+"/Read";
					break;
				case 12:
					apppath = "/reimbursement/viewTADKApproval/" + dat.requestid + "/view";
					break;
				case 13:
					apppath = "/miscelleneous/viewapproval/" + dat.requestid + "/view"+"/Read";
					break;
				case 17:
					apppath = "/reimbursement/viewtravelExpenseapprovaldata/"+ dat.requestid+"/view/Read";
					break;
				default:
					apppath="000";
			}
			

			var message = dat.modulename + " reimbursement for " + dat.submittedbypersonname + " is " + "Completed";
			$("#completedtaskstbl").dataTable().fnAddData([
				"<ed rm='"+ apppath+"' onclick='go_approval(this);' style='cursor: pointer;color:blue;'>" + dat.submittedbypersonno + "</ed>",
				dat.submittedbypersonname,
				dat.submittedondate,
				dat.modulename,
				message,
				dat.requestid
				
				
			]);
		}
		$('#completedtasks').css('display', 'block');
		$('#noCompletedData').css('display', 'none');
	}
}
$(document).on('click').unbind();

function go_approval(id) {
	////debugger;
	var rmurl = $(id).attr("rm");
	var url = rmurl;
	//alert("URL ===> " + url);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
	$('#replace_div').load(url);
}