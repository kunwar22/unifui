$(document).ready(function(){
  	searchData();	
});

function searchData(){
	$.ajax({
		type:"GET",
		url:"/reimbursement/getMedicReimbSearchData",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateSearchTable(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

function populateSearchTable(data){
	$("#searchtable").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$('#resultMedReim').css('display', 'none');
		$('#noMedReimData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			if(dat.status == "Saved" || dat.status == "saved"){
				var str='<i class="fa fa-pen" id="edit" onclick="viewBtnFunc(' + dat.claimid + ',\'edit\')">';
				}
				else{
				var str='<i class="fa fa-pen" style="color:grey" >';
				}
			$("#searchtable").dataTable().fnAddData([
				dat.claimid,
				//dat.createdat,
				dat.createdat.substring(0,10),
				dat.claimamount,	
				dat.approvedamt,
				dat.status,

				'<i class="fa fa-eye" id="view" onclick="viewBtnFunc('+dat.claimid+',\'view\')"></i>',
				str				
			]);
		}
		$('#resultMedReim').css('display', 'block');
		$('#noMedReimData').css('display', 'none');
	}
}

function viewBtnFunc(x,m){
	//var m=event.toElement.id;
	console.log("ClaimID: "+x+" Mode: "+m);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/viewmedrdata/"+x+"/"+m,
		success:function(result){
			//console.log("sucesssssss");
			$('#replace_div').html(result);			
		},
		error:function(e){
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	

}

function addBtnClick(){
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/medicReimb",
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}