$(document).ready(function() {
	$('#childreimbursement').DataTable();
	loadChildMainData();
	var _hrefappr=$("#DOWNLOAD_LINK_APPR").attr("href");
			if(_hrefappr!=undefined && _hrefappr!="" && _hrefappr!=null){
				_hrefappr=_hrefappr.replaceAll('/', "FORWARD_SLASH");
				_hrefappr=_hrefappr.replaceAll('\\','BACKWARD_SLASH');
				_hrefappr=_hrefappr.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK_APPR").attr("href","/getContent?location="+_hrefappr);
			}
	
});


/* Child Reimbursment claim page starts here */
function loadCreatefunction(){
	/*alert("in");*/
	var url = "/reimbursement/childclaim";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$('#replace_div').load(url);
}


/* Search Data JS starts*/

var jsonSearchUrl = '/reimbursement/getChildReimbursement/'


function loadChildMainData(){
		//alert("1"+jsonUrl);
		//debugger;
	$.ajax({
		type: 'GET',
		url: jsonSearchUrl,
		dataSrc: '',
		data: {
				
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;			
			populateChildMainDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateChildMainDataTable(data){
		//alert("2");
	$("#childreimbursement").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#search_result').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			var view='<i id="view_child" class="fa fa-eye" onclick="view_more_data('+dat.childclaimid+',\'view_child\',\''+dat.status+'\');"></i>';
			if(dat.status == "Saved" || dat.status == "saved"){
				str='<i class="fa fa-pen w3-padding" id="edit" onclick="view_more_data('+dat.childclaimid+',\'edit_child\',\''+dat.status+'\')"/>';
			}else{
				str='<i class="fa fa-pen w3-padding" id="edit" style="color:grey"/>';
			}
			$("#childreimbursement").dataTable().fnAddData([				
				dat.childclaimid,
				dat.acadyear,	
				dat.feetype,				
				dat.totalfees,
				dat.dates,				
				dat.apramt,
				dat.status,
				view,
				str
				]);
		}
		$('#search_result').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}

/* Child Reimbursment VIEW MORE page load starts here */
function view_more_data(childclaimid,display_mode,status)
{
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	////debugger;
	$.ajax({
		type:"GET",
		url : "/reimbursement/editReimbursementDetails/"+childclaimid+"/"+display_mode,
		success:function(result){
			//console.log("sucesssssss");
			$('#replace_div').html(result);
			/*var _href=$("#DOWNLOAD_LINK").attr("href");
			if(_href!=undefined && _href!="" && _href!=null){
				_href=_href.replaceAll('/', "FORWARD_SLASH");
				_href=_href.replaceAll('\\','BACKWARD_SLASH');
				_href=_href.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK").attr("href","/getContent?location="+_href);
			}*/
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

/* Child Reimbursment VIEW MORE page load ends here */



/* Search Data JS Ends*/

/* Child Reimbursment claim page ends here */