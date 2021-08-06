
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



$("#fromdate").click(function(){
	$("#fromdate").attr("max",maxfy);
	$("#fromdate").attr("min",minfy);
});

$("#todate").click(function(){
	$("#todate").attr("max",maxfy);
	var from_date=$("#fromdate").val();
	if(from_date!=''){
		$("#todate").attr('min',from_date);
	}
});


$("#backbtn").click(function(){
	$('#replace_div').load("/reimbursement/entertainmentSearch");
});

function savedata(mode,apprmode,frmid,x)
{
	if($("#amount").val()==""){
		$("#amount").val(0);
	}
   var personnumber=$("#personnumber").val();
	
	var doctype="EntertainMentReimbursement";
	/*personnumber=personnumber.trim();
	doctype=doctype.trim();*/
	
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	$("#attachments").val("/EmployeeDocs/"+filepath);
	
//debugger;
	if($('#Agree').prop("checked") == true){
		
		if(parseInt($("#amount").val())>parseInt($("#entertain_amt").text())){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
				message:"Claim amount can not be greater than entitled amount.",
			});	
		$("#amount").val(0.0);		
		}
		else{
		$(x).css("display","none");
	
	var form = $("#"+frmid)[0];
		var data = new FormData(form);
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
			url: "reimbursement/saveEntertainmentClaim/"+mode+"/"+apprmode,
			type: "POST",
			enctype: "multipart/form-data",
			data: data,
			cache: false,
			contentType:false,
			processData: false,
			timeout:600000,
			success: function(data){
					
				$('#replace_div').html(data);
				if(mode=='edit'){
					var msg="updated";
				}
				else{
					var msg="saved";
				}
				if( result=="Success" )
				{
					if(apprmode=="save")
					{
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully "+msg+" Entertainment Reimbursement.",
							callback:function(){
							$('#replace_div').load("/reimbursement/entertainmentSearch");
							}
						});						
					}
					else if(apprmode=="submit")
					{
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully submitted Entertainment Reimbursement for approval.",
							callback:function(){
							$('#replace_div').load("/reimbursement/entertainmentSearch");
							}
						});						
					}


				}
/*				else if( result=="MISMATCH" )
				{
					alert("MISMATCH");
				}
				else if( result=="EMPTY_FILE" )
				{
					alert("File is empty");
				}
				else if( result=="WRITE_ERROR" )
				{
					alert("Error in writing file.");
				}
				else if( result=="IOEXCEPTION" )
				{
					alert("IO Exception has occurred.");
				}
				else if( result=="LOG_ERROR" )
				{
					alert("Error while logging file info.");
				}
				else if( result=="ILLEGALARG" )
				{
					alert("Error while posting file log.");
				}*/
				else if(result=="Already applied"){
					bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message:"Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required."						
					});	
				}
				else if(result!=="YY"){
					bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message:"You have already claimed  Entertainmnet reimbursement four time for this year."						
					});	
				}
				console.log("successss: "+result);
				
				},
			error:function(e){
				//alert("Data not submitted");
				console.log( "ERROR : "+ JSON.stringify(e) );
				bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message:"Something went wrong. Please try again."						
					});
				$('#replace_div').html(data);
				}			
			});
			}
			}	
			
else{
	bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'You cannot fill without declaration. Check on the checkbox'				
			});
}	
	
};

//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

/*function approvalSubmit(_status)
{
	var formData = $('#ENTERTAINMENT_EMBRSMNT_FORM').serialize();
	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();
	var check="false";
	var msg;
	//////debugger;
	if(_status=='Approved'){
		msg="You have successfully approved.";
		if(amt=="" || amt=="0"){
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
		$.ajax({
		url: "/reimbursement/entertainmentApproval/"+_status,
		type: "POST",
		cache: false,
		data: formData,
		processData: false,
		contentType: 'application/x-www-form-urlencoded',
		dataType: "json",
		success:function(result){			
					
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message: "You have successfully "+_status+".",
				callback:function(){
					window.location = "/selfservice";
				}
			});			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
		});
	}
}
*/
/*$('#fromdate').on('change', function(e){	
$('#todate').attr("min",from);
});*/

function approvalSubmit(_status)
{

	if($("#txtAmount").val()==''){
		$("#txtAmount").val("0");	
	}
	var formData = $('#ENTERTAINMENT_EMBRSMNT_FORM').serialize();

		var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();

	console.log(formData);
	if(_status=='Approved'){
		if(amt=="" || amt=="0"){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Request cannot be Approved without filling Approved Amount.'				
			});
		}
		else{
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
				url: "/reimbursement/entertainmentApproval/"+_status,
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
							message:"You have successfully Approved.",
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
	else{
		if(cmt==""){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Request cannot be Rejected without filling Comments.'				
			});
		}else{
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
				url: "/reimbursement/entertainmentApproval/"+_status,
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
							message:"You have successfully rejected.",
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
}












$(document).ready(function() {

	var _href = $("#DOWNLOAD_LINK").attr("href");
	if (_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');

		$("#DOWNLOAD_LINK").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINK").attr("href"));
	}
});

function popupDeclearation(claimid)
{
	////debugger;
	window.open("../reimbursement/entertainmentdeclaration/"+claimid,'window','width=600,height=600');
};