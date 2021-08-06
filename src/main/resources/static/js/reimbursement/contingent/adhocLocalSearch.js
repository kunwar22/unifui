$(document).ready(function(){
  	searchData();	
});

function searchData(){
	$.ajax({
		type:"GET",
		url:"/reimbursement/getAdhocSearchData",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			//console.log("in search data...success....");
			populateSearchTable(result);			
		},
		error:function(e){
			//console.log("in search data...error....");
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

function populateSearchTable(data){
	$("#searchtable").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$('#resultAdhoc').css('display', 'none');
		$('#noAdhocData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			if(dat.status == "Saved" || dat.status == "saved"){
				str='<i class="fa fa-pen" id="edit" style="cursor: pointer" onclick="viewBtnFunc('+dat.claimid+',\'edit\')">';
			}else{
				str='<i class="fa fa-pen" id="edit" style="color:grey">';
			}
			$("#searchtable").dataTable().fnAddData([
				dat.claimid,
				dat.submitdate,
				dat.claimamt,	
				dat.approvedamt,
				dat.status,
				'<i class="fa fa-eye" style="cursor: pointer" id="view" aria-hidden="true" onclick="viewBtnFunc('+dat.claimid+',\'view\');"></i>',
				str			
			]);
		}
		$('#resultAdhoc').css('display', 'block');
		$('#noAdhocData').css('display', 'none');
	}
}

function viewBtnFunc(x,m){
	//var m=event.toElement.id;
	console.log("ClaimID: "+x+" Mode: "+m);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/viewadhocdata/"+x+"/"+m,
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
	//console.log("Add button click...");
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/adhocLocal",
		success:function(result){
			//console.log("loading add page");
			$('#replace_div').html(result);			
		},
		error:function(e){
			//console.log("error...");
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}