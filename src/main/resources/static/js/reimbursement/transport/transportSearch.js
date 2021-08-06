function addClaim()
{
    var url = "/reimbursement/transport";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $('#replace_div').load(url);
};

var jsonUrl = '/reimbursement/gettransportsearchdata';

$(document).ready(function() {
	loadTransportClaimData();


});

function loadTransportClaimData(){
		
	$.ajax({
		type: 'GET',
		url: "/reimbursement/getsearchdata",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			//alert("Loading Data................");
			populateTransportDataTable(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
};

function populateTransportDataTable(data){
	$("#Transport_Claim_History").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$('#search_result').css('display', 'none');
		$('#noDataTransport').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			var view='<i class="fa fa-eye w3-padding" id="view" onclick="Viewdata('+dat.travelid+',\'view\',\''+dat.status+'\');"/>';
			if(dat.status=="submitted" || dat.status=="Submitted" || dat.status=="Approved" || dat.status=="approved" || dat.status=="rejected" || dat.status=="Rejected" ){
				str='<i class="fa fa-pen w3-padding" id="edit" style="color:grey">';
			}else{
				str='<i class="fa fa-pen w3-padding" id="edit" onclick="Viewdata('+dat.travelid+',\'edit\',\''+dat.status+'\');"/>';
			}
			$("#Transport_Claim_History").dataTable().fnAddData([
				dat.travelid,
				dat.submiteddate,
				dat.claimedamt,
				dat.requestedamt,
				dat.status,
				view,
				str
				]);
		}
		$('#search_result').css('display', 'block');
		$('#noDataTransport').css('display', 'none');
	}
};

function Viewdata(x,_mode)
{
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		 url :"reimbursement/getviewdata/"+x+"/"+_mode,
		success:function(result){
			console.log("Success");
			$('#replace_div').html(result);
			//debugger;
			$("#DOWNLOAD_LINK").attr("href","/getContent?location="+$("#attachhidden").val());
			/*if(_href!=undefined && _href!="" && _href!=null){
				_href=_href.replaceAll('/', "FORWARD_SLASH");
				_href=_href.replaceAll('\\','BACKWARD_SLASH');
				_href=_href.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK").attr("href","/getContent?location="+$("#DOWNLOAD_LINK").attr("href"));
			}*/
		
		}
	});
 
};


		$(document).on('click').unbind();
		$(document).on('click', 'ed', function(e){
			var url = $(this).attr("rm");
			$('#replace_div').load(url);
		});