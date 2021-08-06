//alert("hi ctg");
$(document).ready(function(){
	//console.log("calling searchdata...");
  	searchDataCtg();
});

$('#CTGCreate').on('click', function(e){
	//alert("click");
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});



function searchDataCtg(){
	console.log("in search ctg...");
	$.ajax({
		type:"GET",
		url:"/ctgReimbursement/getCtgsearchdata",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateCtgSearchTable(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

function populateCtgSearchTable(data){
	
	$("#searchCtgtable").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$('#resultCtg').css('display', 'none');
		$('#noCtgData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			 if(dat.status == "Saved" || dat.status == "saved"){
				 str='<i class="fa fa-pen" id="view" onclick="viewBtnFuncCTG('+dat.claimid+',\'edit\')"></i>';
			}else{
				 str='<i class="fa fa-pen" id="edit" style="color:grey">';
			}
			$("#searchCtgtable").dataTable().fnAddData([
				dat.claimid,
				dat.cityfrom,
				dat.cityto,
				dat.transferdate,	
				dat.journeydate,
				dat.totalactualpaidamount,
				dat.approvedamt,
				dat.status,
				'<i class="fa fa-eye" id="edit" onclick="viewBtnFuncCTG('+dat.claimid+',\'view\');">',
				str
			]);
		}
		$('#resultCtg').css('display', 'block');
		$('#noCtgData').css('display', 'none');
	}
}

function viewBtnFuncCTG(x,_mode){
//	var m=event.toElement.id;
	console.log("CTG: "+x);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"ctgReimbursement/edit/editCtg/correctctg/"+x+"/"+_mode,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	

}