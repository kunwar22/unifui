

function getapprovalhistory(rid,claimid){
	$("#apprhistorypopup").css("display","block");
	//alert(rid+"||"+claimid);
	var jsonurlappr ='/approvalhistory/viewapprovalhistory';
		$.ajax({
			type: 'POST',
			url: jsonurlappr,
			 data: {
				 "rid": rid,
				 "claimid": claimid,
				},
			dataSrc: '',
			dataType: 'json',
			success: function(data){
				jsonData=data;
				populateAPPRTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
}


	function populateAPPRTable(data){
		$("#APPROVAL_HISTORY").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#apprresultSec').css('display', 'none');
			$('#noDataAPPR').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#APPROVAL_HISTORY").dataTable().fnAddData([
					dat.approvallevel,
					dat.submitdate,
					dat.approverpersonname,
					dat.approvedamt,
					dat.status,
					dat.message			
					]);
			}
			$('#apprresultSec').css('display', 'block');
			$('#noDataAPPR').css('display', 'none');
		}
	}
	