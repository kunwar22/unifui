function addClaimEntertainment()
{
    var url ="/reimbursement/entertainment";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
};




$(document).ready(function() {
	loadEntertainmentClaimData();


});

function loadEntertainmentClaimData(){
		
	$.ajax({
		type: 'GET',
		url: "/reimbursement/getEntertainmentsearchdata",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateTransportDataTable(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
};

function populateTransportDataTable(data){
	$("#Entertainment_Claim_History").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$("#Entertainment_Claim_History_div").css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			if(dat.status == "Saved" || dat.status == "saved"){
				str='<i class="fa fa-pen" id="edit" style="cursor: pointer" onclick="Viewdata('+dat.claimid+',\'edit\')">';
			}else{
				str='<i class="fa fa-pen" id="edit" style="color:grey">';
			}
			$("#Entertainment_Claim_History").dataTable().fnAddData([
				dat.claimid,
				dat.createdDate.substring(0,10),
				dat.amount,	
				dat.approvedAmt,
				dat.status,
				'<i class="fa fa-eye" style="cursor: pointer" id="view" aria-hidden="true" onclick="Viewdata('+dat.claimid+',\'view\');"></i>',
				str
				]);
		}
		$('#Entertainment_Claim_History').css('display', 'block');
		$('#noData').css('display', 'none');
	}
};


function Viewdata(x,m)
{
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		 url :"reimbursement/getviewEntertainmentdata/"+x+"/"+m,
		success:function(result){
			console.log("Success");
			$('#replace_div').html(result);
		
		}
	});
 
};


		$(document).on('click').unbind();
		$(document).on('click', 'ed', function(e){
			var url = $(this).attr("rm");
			$('#replace_div').load(url);
		});