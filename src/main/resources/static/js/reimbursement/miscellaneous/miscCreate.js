$('#returnpage').on('click', function(e){
	//alert("click");
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
function getLocation(id,workloc)
{
	//debugger;
	//var locid = document.getElementById(id);
	var dataloc_ = $("#"+id).children("option:selected").text();
	//alert(dataloc_);
	document.getElementById(workloc).value=dataloc_;
}
function loadclaimform(claimid)
{
	////debugger;
	window.open("../miscelleneous/claimform/"+claimid,'window','width=700,height=700');
}


function loadSaveMisc(str,frmid,x) {
////debugger;
	//alert("Save::::::");
	if(parseInt($("#CR_MIS_CLAIM").val())==0){
		$("#CR_MIS_CLAIM").val("");
	}
	$(x).css("display","none");
	var personnumber=$("#txtpersonNumber").val();
	$("#misstatusid").val(str);
	//alert($("#statusid").val());
	var doctype="MiscellaneousReimbursement";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
	//alert(str);
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	$("#attachment").val("/EmployeeDocs/"+filepath);
	
	//alert("FILEPATH::"+$('#attachment1').val());
	
	//var form = $("#CTG_SAVE");
	
	var data = new FormData(document.getElementById("MIS_SAVE"));
	console.log(data);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
	url: "/miscelleneous/miscellaneousSave",
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data) {

		$('#replace_div').html(data);


		if (resultfinal == "Success") {
			if(str=="save")
			{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"You have successfully saved Miscellaneous Reimbursement.",
					callback:function(){
						$('#replace_div').load("/miscelleneous/manageMiscelleneous");
					}
				});				
			}
			else if(str=="submit")
			{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"You have successfully submitted Miscellaneous Reimbursement for approval.",
					callback:function(){
						$('#replace_div').load("/miscelleneous/manageMiscelleneous");
					}
				});						
			}
		} /*else if (resultfinal == "MISMATCH") {
			alert("MISMATCH");
		} else if (resultfinal == "EMPTY_FILE") {
			alert("File is empty");
		} else if (resultfinal == "WRITE_ERROR") {
			alert("Error in writing file.");
		} else if (resultfinal == "IOEXCEPTION") {
			alert("IO Exception has occurred.");
		} else if (resultfinal == "LOG_ERROR") {
			alert("Error while logging file info.");
		} else if (resultfinal == "ILLEGALARG") {
			alert("Error while posting file log.");
		}*/ else {
			/*bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Something went wrong while uploading the file(s)."				
			});*/
		}
	},
	error: function(data){
		//alert("ERROR : "+JSON.stringify(data));
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Something went wrong. Please try again.'				
			});
		$('#replace_div').html(data);
		
	}
	});
};






function loadupdateMIS(str,frmid,x) {
//debugger;
//alert("update");
if(parseInt($("#CR_MIS_CLAIM").val())==0){
		$("#CR_MIS_CLAIM").val("");
	}
	$(x).css("display","none");
	var personnumber=$("#txtpersonNumber").val();
	$("#misstatusid").val(str);
	//alert($("#statusid").val());
	var doctype="MiscellaneousReimbursement";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
	//alert(str);
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	$("#attachment").val("/EmployeeDocs/"+filepath);
	
	
	var mode='edit';
	var data = new FormData(document.getElementById("MIS_SAVE"));
	console.log(data);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: "/miscelleneous/miscellaneousUpdate",
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
	$('#replace_div').html(data);

	if( resultfinal=="Success" )
	{
		if(str=="save")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully updated Miscellaneous Reimbursement.",
				callback:function(){
					$('#replace_div').load("/miscelleneous/manageMiscelleneous");
				}
			});			
		}
		else if(str=="submit")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully submitted Miscellaneous Reimbursement for approval.",
				callback:function(){
					$('#replace_div').load("/miscelleneous/manageMiscelleneous");
				}
			});			
		}

	}
	/*else if( resultfinal=="MISMATCH" )
	{
		alert("MISMATCH");
	}
	else if( resultfinal=="EMPTY_FILE" )
	{
		alert("File is empty");
	}
	else if( resultfinal=="WRITE_ERROR" )
	{
		alert("Error in writing file.");
	}
	else if( resultfinal=="IOEXCEPTION" )
	{
		alert("IO Exception has occurred.");
	}
	else if( resultfinal=="LOG_ERROR" )
	{
		alert("Error while logging file info.");
	}
	else if( resultfinal=="ILLEGALARG" )
	{
		alert("Error while posting file log.");
	}*/
	else
	{
		/*bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Something went wrong while uploading the file(s)."				
			});*/
	}
	},
	error: function(data){
		//alert("ERROR : "+JSON.stringify(data));
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Something went wrong. Please try again.'				
			});
		$('#replace_div').html(data);
		
	}
	});
};




		
		
		
//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
/*
function approvalSubmitmMisc(_status)
{
	//alert("in");
	var formData = $('#MIS_SAVE').serialize();
	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();
	if($("#txtAmount").val()==''){
		$("#txtAmount").val("0");	
	}
	
	var check="false";
	if(_status=='Approved'){
		if(amt=="" or amt==null ){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Please fill Amount!!"				
			});
		}
		else{
			check="true";
		}
	}
	else{
		if(cmt==""){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Please fill Comment!!"				
			});
		}else{
			check="true";
		}
	}
	
	if(check=="true"){
		$.ajax({
		url: "/miscelleneous/misApproval/"+_status,
		type: "POST",
		cache: false,
		data: formData,
		processData: false,
		contentType: 'application/x-www-form-urlencoded',
		dataType: "json",
		success:function(result){
			//console.log("successss: "+result.telephoneclaimid);
		if(_status=="Approved"){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully approved.",
				callback:function(){
					window.location = "/selfservice";
				}
			});
		}else if(_status=="Rejected"){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"You have successfully rejected.",
					callback:function(){
						window.location = "/selfservice";
					}
				});					
			}
		},
		error:function(e){
			
			console.log( "ERROR : "+ JSON.stringify(e) );
			}
		});
	}
	
}*/








function approvalSubmitmMisc(_status)
{
	var formData = $('#MIS_SAVE').serialize();
	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();
	var check="false";
	var msg;
	////debugger;
	if(_status=='Approved'){
		msg="You have successfully approved.";
		if(amt=="" ){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Request cannot be Approved without filling Approved Amount."				
			});
		}
		else{
			check="true";
		}
	}
	else{
		msg="You have successfully rejected.";
		if(cmt==""){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Request cannot be Rejected without filling Comments."				
			});
		}
		else{
			check="true";
		}
	}
	
	if(check=="true"){
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
		url: "/miscelleneous/misApproval/"+_status,
		type: "POST",
		cache: false,
		data: formData,
		processData: false,
		contentType: 'application/x-www-form-urlencoded',
		dataType: "json",
		success:function(result){
			
			if(result.status=="true"){
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: "You have successfully "+_status+".",
							callback:function(){
								window.location = "/selfservice";
							}
						});	
						}else if(result.status=="false"){
							bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message:"Something went wrong, Please take action again.",
							callback:function(){
								window.location = "/selfservice";
							}
						});
						}				
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
		});
	}
}






//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		

	$('#CR_MIS_ACOUNT_DESCR').on('change',function(){
		
	//alert($( "#CR_MIS_ACOUNT_DESCR option:selected" ).attr('msg')); //alert '2'
	var selectObject=$("#CR_MIS_ACOUNT_DESCR option:selected" ).attr('msg');
		$('#CR_MIS_EXPENCE').val(selectObject);
		
	});


$(document).ready(function() {
//debugger;
	var _href = $("#DOWNLOAD_LINKsp").text();
	//alert(_href);
	if (_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');

		$("#DOWNLOAD_LINK").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINKsp").text());
	}
	//alert($("#DOWNLOAD_LINK").attr("href"));
});




function backBtnFunc(){
	var url = "/miscelleneous/manageMiscelleneous";
	$('#replace_div').load(url);
	}
	
	
	
	
var maxfy=getMaxfy();
var minfy=getMinfy();
function getMinfy(){
  var today = new Date();
  if ((today.getMonth() + 1) <= 3) {
    var minfy=(today.getFullYear() - 1)+"-04-01";
  } else {
    var minfy=today.getFullYear()+"-04-01";
  }
  return minfy;
}
function getMaxfy(){
  var today = new Date();
  if ((today.getMonth() + 1) <= 3) {
    var maxfy=today.getFullYear()+"-03-31";
  } else {
    var maxfy=(today.getFullYear() + 1)+"-03-31";
  }
  return maxfy;
}
function disablefuturedate()
{
	//disabling future date selection
	//debugger;
	var dttoday = new Date();
	var month = dttoday.getMonth()+1;
	var day= dttoday.getDate();
	var year = dttoday.getFullYear();
	if(month<10)
		month='0'+month.toString();
	if(day<10)
		day='0'+day.toString();
	
	var maxdate = year+"-"+month+"-"+day;
	$('#CR_MIS_BILL_DATE').attr('max',maxdate);
	//$('#CR_MIS_BILL_DATE').attr('min',minfy);
}


$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});