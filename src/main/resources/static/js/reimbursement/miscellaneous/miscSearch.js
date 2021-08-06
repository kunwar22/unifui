//alert("hi ctg");
$(document).ready(function(){
	//console.log("calling searchdata...");
  	searchDataMis();
});

/*$('#MisCreate').on('click', function(e){
	//alert("click");
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});*/

function addmisBtnClick(){
	//console.log("Add button click...");
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/miscelleneous/CreateMiscelleneous",
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



function searchDataMis(){
	console.log("in searchmis...");
	$.ajax({
		type:"GET",
		url:"/miscelleneous/getMissearchdata",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			//console.log("in search data...success....");
			populateMisSearchTable(result);			
		},
		error:function(e){
			//console.log("in search data...error....");
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

function populateMisSearchTable(data){
	
	$("#searchMisctable").DataTable().clear();
	var dataLength = data.length;	
	//console.log("Here------------"+dataLength);
	if(dataLength == 0){
		$('#resultMis').css('display', 'none');
		$('#noMiscData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str='';
			var str1='';
			if(dat.status == "Saved" || dat.status == "saved"){
				str1='<i class="fa fa-pen" id="edit" onclick="viewBtnFuncMIS('+dat.claimid+',\'edit\')"></i>';
			}else{
				str1='<i class="fa fa-pen" id="edit" style="color:grey"></i>';
			}			
			$("#searchMisctable").dataTable().fnAddData([
				dat.claimid,
				dat.billno,
				dat.billdate,
				dat.claimamount,
				dat.paidto,
				dat.expenseaccountcode,
				dat.accountdescription,
				dat.vehicleused,
				dat.status,
				dat.approvedamt,
				'<i class="fa fa-eye w3-padding" id="view" onclick="viewBtnFuncMIS('+dat.claimid+',\'view\');"></i>',
				str1

			]);
		}
		$('#resultMis').css('display', 'block');
		$('#noMiscData').css('display', 'none');
	}
}

function viewBtnFuncMIS(x,_mode){
//	var m=event.toElement.id;
	console.log("MIS: "+x);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"miscelleneous/edit/editMis/correctMis/"+x+"/"+_mode,
		success:function(result){

			$('#replace_div').html(result);
			var _href=$("#DOWNLOAD_LINK").attr("href");
			if(_href!=undefined && _href!="" && _href!=null){
				_href=_href.replaceAll('/', "FORWARD_SLASH");
				_href=_href.replaceAll('\\','BACKWARD_SLASH');
				_href=_href.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK").attr("href","/getContent?location="+$("#DOWNLOAD_LINK").attr("href"));
			}
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	

}






