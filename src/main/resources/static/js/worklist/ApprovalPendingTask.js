
$(document).on('click').unbind();

$(document).ready(function(){
  	searchAllPendingApprovals();	
});

function searchPendingApprovals(pfdt,ptdt)
{
	var fdt= $('#'+pfdt).val();
	var tdt= $('#'+ptdt).val(); 
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
	}
	else if(fdt!="" && tdt!=""){
		
	var url ="/tasks/getWorklistTasks/"+fdt+"/Unread/"+tdt;
	$.ajax({
		type: 'GET',
		url:url,
		dataSrc: '',
		data: {
		},
		success:function(data){
			jsonData = data;
			populatePendingWorklistDataTable(jsonData);		
		},
		error:function(result){
			console.log( "ERROR : "+ JSON.stringify(result) );
		}
	});	
	}
}

function searchAllPendingApprovals()
{
	var fdate = new Date("2020-11-01").toISOString().substr(0,10);
	var tdate = new Date().toISOString().substr(0,10);
	var fdt= fdate;
	var tdt= tdate; 
	var url ="/tasks/getWorklistTasks/"+fdt+"/Unread/"+tdt;
	$.ajax({
		type: 'GET',
		url:url,
		dataSrc: '',
		data: {
		},
		success:function(data){
			jsonData = data;
			populatePendingWorklistDataTable(jsonData);			
		},
		error:function(result){
			console.log( "ERROR : "+ JSON.stringify(result) );
		}
	});	
}

function populatePendingWorklistDataTable(data)
{
	//debugger;
	$("#pendingtaskstbl").DataTable().clear();
	var dataLength = data.length;
	var apppath = "";
	if(dataLength == 0){
		$('#pendingtasks').css('display', 'none');
		$('#noPendingData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
						
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
					apppath = "/reimbursement/viewApprovalDataChild/" + dat.requestid + "/view_child";
					break;
				case 4:
					apppath = "/reimbursement/viewElectricityReimbursementApproval/" + dat.requestid + "/view";
					break;
				case 5:
					apppath = "/reimbursement/viewentertainmentapprovaldata/" + dat.requestid + "/view";
					break;
				case 6:
					apppath = "/reimbursement/viewadhocdataApproval/" + dat.requestid + "/view";
					break;
				case 7:
					apppath = "/cdaVehicle/viewcdaapproval/" + dat.requestid + "/view";
					break;
				case 9:
					apppath = "/reimbursement/approvalMedicalAdvance/" + dat.requestid + "/view";
					break;
				case 10:
					apppath = "/reimbursement/medireimbApproval/" + dat.requestid + "/view";
					break;
				case 11:
					apppath = "/ctgReimbursement/viewctgapproval/" + dat.requestid + "/view";
					break;
				case 12:
					apppath = "/reimbursement/viewTADKApproval/" + dat.requestid + "/view";
					break;
				case 13:
					apppath = "/miscelleneous/viewapproval/" + dat.requestid + "/view";
					break;
				case 17:
					apppath = "/reimbursement/viewtravelExpenseapprovaldata/"+ dat.requestid+"/view";
					break;
			default:
					apppath="000";
			}
			
			
			
			
			var message=dat.modulename+" for "+dat.submittedbypersonname+" is "+dat.status;
			$("#pendingtaskstbl").dataTable().fnAddData([
			"<ed rm='" + apppath+ "/Unread' onclick='go_pendingapproval(this);' style='cursor: pointer;color:blue;'>" + dat.submittedbypersonno + "</ed>",
				dat.submittedbypersonname,
				dat.submittedondate,	
				dat.modulename,
				message						
				]);
		}
		$('#pendingtasks').css('display', 'block');
		$('#noPendingData').css('display', 'none');
	}
}
$(document).on('click').unbind();

function go_pendingapproval(id)
{
	//debugger;
	var rmurl = $(id).attr("rm");
	var url=rmurl;
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
    $('#replace_div').load(url);
}