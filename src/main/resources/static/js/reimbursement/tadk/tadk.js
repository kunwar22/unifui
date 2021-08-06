
$(document).ready(function(){
	//debugger;

	//$('#tadkreimbursement').DataTable();
	loadTADKReimbursementData();
	
	
	
});

/* Electricity Reimbursment claim page loads here */
function loadCreateTADKClaim(){
	/*alert("in");*/
	var url = "/reimbursement/tadkclaim";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
}


//setting eligible units based on designation
$('#person_designation').on('input paste',function()
{
	var designation = $('#person_designation').val();
	if(designation == "Deputy General Manager" || designation =="Deputy HOD" || designation=="HOD" || designation=="Managing Director" || designation=="Functional Director"|| designation=="Project Director")
	{
		$('#tadk_amount').prop({value:8000});
	}	
});

/* code for saving tadk reimbursement claim  WITHOUT FILE UPLOAD */
function ajaxsave(view_mode,mode,x)
{
	$(x).css("display","none");
	$("#btnSave").prop("disabled",true);
	$("#btnSave").prop("disabled",true);
	//debugger;	
	
	$("#statusid").val(mode);
	//alert(mode);
	saveTADKData(view_mode,mode);
};


/* code for saving tadk reimbursement claim  WITH FILE UPLOAD */
function saveTADKData(vmode,mode)
{
	if(parseInt($("#tadk_amount").val())==0){
		$("#tadk_amount").val("");
	}
	if(parseInt($("#tadk_amount").val())>parseInt($("#tadk_entitle").text())){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
				message:"Claim amount can not be greater than entitled amount.",
			});	
		$("#tadk_amount").val("");	
		$("#btnSave").css("display","inline-block");
		}else{
			
	//debugger;
	var jsontadksaveUrl="/reimbursement/saveTadkReimbursement/"+mode; 
	var personnumber=$("#tadk_personnumber").val();
	//$("#statusid").val(mode);
	//alert($("#statusid").val());
	var doctype="TADKReimbursement";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	$("#tadkrcpt").val("/EmployeeDocs/"+filepath);
	//alert("FILEPATH::"+$('#tadkrcpt').val());
	var form = $("#TADK_SAVE")[0];
	//alert(form);
	var data = new FormData(form);
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: jsontadksaveUrl,
		type: "POST",
		enctype: "multipart/form-data",
		data: data,
		cache: false,
		contentType:false,
		processData: false,
		timeout:600000,
		success: function(data)
		{
			//alert(data);
			//debugger;
			$('#replace_div').html(data);
			if( resultfinal=="Success")
			{
				if(vmode=="save")
				{
					bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message:"You have successfully saved TADK Reimbursement details.",
						callback:function(){
							$('#replace_div').load("/reimbursement/tadk");
						}
					});					
				}
				else if(vmode=="update")
				{
					bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message:"You have successfully updated TADK Reimbursement.",
						callback:function(){
							$('#replace_div').load("/reimbursement/tadk");
						}
					});					
				}
				else if(vmode=="submit")
				{
					bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message:"You have successfully submitted TADK Reimbursement for approval.",
						callback:function(){
							$('#replace_div').load("/reimbursement/tadk");
						}
					});					
				}
			}
			/*else if( resultfinal=="MISMATCH" )
			{
				bootbox.alert("MISMATCH");
			}
			else if( resultfinal=="EMPTY_FILE" )
			{
				bootbox.alert("File is empty");
			}
			else if( resultfinal=="WRITE_ERROR" )
			{
				bootbox.alert("Error in writing file.");
			}
			else if( resultfinal=="IOEXCEPTION" )
			{
				bootbox.alert("IO Exception has occurred.");
			}
			else if( resultfinal=="LOG_ERROR" )
			{
				bootbox.alert("Error while logging file info.");
			}
			else if( resultfinal=="ILLEGALARG" )
			{
				bootbox.alert("Error while posting file log.");
			}*/
			else if(resultfinal=="alreadyApplied")
			{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required"				
				});
			}
			else{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Fill all the mandatory fields and attach the file again, if required."				
				});
			}
		},
		error: function(data)
		{
			console.log("ERROR : "+JSON.stringify(data));
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





var jsonSearchtadkUrl = '/reimbursement/getTADKReimbursement/';

function loadTADKReimbursementData()
{
	//debugger;
	$.ajax({
		type: 'GET',
		url: jsonSearchtadkUrl,
		dataSrc: '',
		data: {
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;			
			populateTADKReimbursementDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateTADKReimbursementDataTable(data)
{
	$("#tadkreimbursement").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#search_result').css('display', 'none');
		$('#noDataTadk').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			var view='<i class="fa fa-eye w3-padding" id="view" onclick="tadk_details('+dat.claimid+',\'view\',\''+dat.status+'\');"/>';
			if(dat.status == "Saved" || dat.status == "saved"){
				str='<i class="fa fa-pen w3-padding" id="edit" onclick="tadk_details('+dat.claimid+',\'edit\',\''+dat.status+'\')"/>';
			}else{
				str='<i class="fa fa-pen w3-padding" id="edit" style="color:grey"/>';
			}
			$("#tadkreimbursement").dataTable().fnAddData([				
				dat.claimid,
				dat.tadkname,	
				dat.month,
				dat.claimamt,								
				dat.createddate,
				dat.approvedamt,
				dat.status,	
				view,	
				str				
				]);
		}
		$('#search_result').css('display', 'block');
		$('#noDataTadk').css('display', 'none');
	}
}

function backBtnFunc(){
	var url = "/reimbursement/tadk";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$('#replace_div').load(url);
	}
   /* $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);

		},
		error:function(e){
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}*/

function tadk_details(tadkclaimid,display_mode,status)
{
	//debugger;
	console.log("ClaimID: "+tadkclaimid+" Mode: "+display_mode);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/editTADKReimbursement/"+tadkclaimid+"/"+display_mode,
		success:function(result){
			//console.log("sucesssssss");
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
	
	/*var url = "/reimbursement/editTADKReimbursement/"+tadkclaimid+"/"+display_mode;
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);*/
}





//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

$(document).ready(function(){	
	var _hrefappr=$("#DOWNLOAD_LINK_APPR").attr("href");
			if(_hrefappr!=undefined && _hrefappr!="" && _hrefappr!=null){
				_hrefappr=_hrefappr.replaceAll('/', "FORWARD_SLASH");
				_hrefappr=_hrefappr.replaceAll('\\','BACKWARD_SLASH');
				_hrefappr=_hrefappr.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK_APPR").attr("href","/getContent?location="+$("#DOWNLOAD_LINK_APPR").attr("href"));
			}
});

function approvalSubmit(_status)
{

	//debugger;
	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();
	//debugger;
	if(_status=="Approved")
	{
		if(amt=="" || amt==null || amt==undefined )
			{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Request cannot be Approved without filling Approve Amount."
				});
			}
			else{
				var formData = $('#TADK_SAVE').serialize();
				//console.log(formData);
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
				$.ajax({
					url: "/reimbursement/tadkApproval/" + _status,
					type: "POST",
					cache: false,
					data: formData,
					processData: false,
					contentType: 'application/x-www-form-urlencoded',
					dataType: "json",
					success: function (result) {
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
				error: function (e) {
					//alert("Data already submitted for time period '"+periodfrom+"' - '"+periodto+"'");
					console.log("ERROR : " + JSON.stringify(e));
				}
				});	
			}
			}
	
	else if(_status=="Rejected")
	{
		if( cmt=="")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Request cannot be Rejected without filling Comments."			
			}); 
		}
		else{
			 $('#txtAmount').val("0.0");
			var formData = $('#TADK_SAVE').serialize();
				//console.log(formData);
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
				$.ajax({
					url: "/reimbursement/tadkApproval/" + _status,
					type: "POST",
					cache: false,
					data: formData,
					processData: false,
					contentType: 'application/x-www-form-urlencoded',
					dataType: "json",
					success: function (result) {
						if(result.status=="true"){
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully Rejected.",
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
				error: function (e) {
					//alert("Data already submitted for time period '"+periodfrom+"' - '"+periodto+"'");
					console.log("ERROR : " + JSON.stringify(e));
				}
				});	
		}
	}
	
}


//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

//************ASMITA----------loading declaration in popup
function loadPopup(claimid)
{
	//debugger;
	window.open("../reimbursement/declarationTADK/"+claimid,'window','width=600,height=600');
}
