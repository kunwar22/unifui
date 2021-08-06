//alert("hi");'
$(document).ready(function(){
	//console.log("calling searchdata...");
  	searchData();

/* CLAIM FORM WINDOW OPEN CODE  */



	/*var selectedtype=$("#POS_TYPE option:selected").val();
	if(selectedtype==14){
		$('#POS_FTE').val(1);
		$('#POS_FTE').prop('readonly',true);
		$('#POS_HEAD').val(1);
		$('#POS_HEAD').prop('readonly',true);
	}else{
		$('#POS_FTE').val(0);
		$('#POS_FTE').prop('readonly',false);
		$('#POS_HEAD').val(0);
		$('#POS_HEAD').prop('readonly',false);
	}*/



/*var _hrefappr=$("#DOWNLOAD_LINK_APPR").attr("href");
alert(_hrefappr);
			if(_hrefappr!=undefined && _hrefappr!="" && _hrefappr!=null){
				_hrefappr=_hrefappr.replaceAll('/', "FORWARD_SLASH");
				_hrefappr=_hrefappr.replaceAll('\\','BACKWARD_SLASH');
				_hrefappr=_hrefappr.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK_APPR").attr("href","/getContent?location="+_hrefappr);
			}
alert($("#DOWNLOAD_LINK_APPR").attr("href"));*/
			
var _hrefappr=$("#DOWNLOAD_LINKsp").text();
			if(_hrefappr!=undefined && _hrefappr!="" && _hrefappr!=null){
				_hrefappr=_hrefappr.replaceAll('/', "FORWARD_SLASH");
				_hrefappr=_hrefappr.replaceAll('\\','BACKWARD_SLASH');
				_hrefappr=_hrefappr.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK").attr("href","/getContent?location="+$("#DOWNLOAD_LINKsp").text());
			}
});

$("#TELE_TO_DATE").change(function(){
	$("#requestedamt").prop("readonly",false);
});

 function amtvalidation(){
	////debugger;
	var months=diff_months();
	/*if(months==0){
		months=1;
	}*/
	//var entitlement=((parseInt($("#ENTITLEAMT").text())*12)/365)*months;

/**************** SNIGDHAA 12-NOV-2020 ******** START ********/	
	var entamt=parseInt($("#ENTITLEAMT").text());
	var from=new Date($("#TELE_FROM_DATE").val());
	var to=new Date($("#TELE_TO_DATE").val());
	
	var dt1=from.getDate();
	var dt2=to.getDate();
	var mon1=from.getMonth()+1;
	var mon2=to.getMonth()+1;
	var yea1=from.getFullYear();
	var yea2=to.getFullYear();
	var dat1=new Date(yea1, mon1, 0).getDate();
	var dat2=new Date(yea2, mon2, 0).getDate();
	var dtdif1=dat1-dt1+1;
	var dtdif2=dt2;
	
	//alert(entamt+" "+dat1+" "+dtdif1+" "+(entamt/dat1*dtdif1));
	//alert(entamt+" "+dat2+" "+dtdif2+" "+(entamt/dat2*dtdif2));
	var entitlement;
	if(mon1!=mon2){
		entitlement=(entamt/dat1*dtdif1)+(entamt/dat2*dtdif2);
	}
	else{
		entitlement=(entamt/dat2*dtdif2);
	}
	
	var mondif=Math.abs(mon2-mon1);
	for(i=1;i<mondif;i++){
		entitlement+=entamt;
	}
/**************** SNIGDHAA 12-NOV-2020 ********* END *******/
	
	//alert("ELIGIBLE AMOUNT "+entitlement);
	var claimamt=parseInt($("#requestedamt").val());
	
	 if (isNaN(claimamt) ||  claimamt > entitlement) {
		
	   // bootbox.alert("Amount can not be greater than entitled amount.");
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-ban" style="font-size:25px; color:red;">&nbsp;&nbsp;</i>',
				message:"Amount can not be greater than entitled amount."
			});
	  	$("#requestedamt").val("");
		}
}

function diff_months() {
	from = $('#TELE_FROM_DATE').val();
	to = $('#TELE_TO_DATE').val();

    /*var dt1 = new Date(from);
    var dt2 = new Date(to);
    var diff = (dt2.getTime() - dt1.getTime()) / 1000;
    diff /= (60 * 60 * 24 * 7 * 4);*/

	var a = moment(to);
	var b = moment(from);
	//alert(a+" :: "+b);
	var months = a.diff(b, 'days');
	//alert(months + ' months ');
	//alert("Days :: "+months);
    return months;
}


function loadClaimPage(claimid)
{
	window.open("../reimbursement/claimTelephone/"+claimid,'window','width=600,height=600');
}

function searchData(){
	//console.log("in search data...");
	$.ajax({
		type:"GET",
		url:"/reimbursement/gettelesearchdata",
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

$("#TELE_FROM_DATE").change(function(){
	$("#TELE_TO_DATE").val("");
/**************** SNIGDHAA 12-NOV-2020 ********* START *******/	
	$("#requestedamt").val("");
/**************** SNIGDHAA 12-NOV-2020 ********* END *******/	
});
/**************** SNIGDHAA 12-NOV-2020 ********* START *******/
$("#TELE_TO_DATE").change(function(){
	$("#requestedamt").val("");
});
/**************** SNIGDHAA 12-NOV-2020 ********* END *******/
$("#TELE_FROM_DATE").click(function(){
	var dt=new Date();
	var mon=dt.getMonth();
	var yea=dt.getFullYear();
	var dat=new Date(yea, mon, 0).getDate();
	
	if(mon<10){
		mon="0"+mon;	
	}
	if(dat<10){
		dat="0"+dat;
	}
	
	var maxi=yea+"-"+mon+"-"+dat;	
	$("#TELE_FROM_DATE").attr("max",maxi);
	$("#TELE_FROM_DATE").attr("min",minfy);	
});
$("#TELE_TO_DATE").click(function(){	
	var dt=new Date();
	var mon=dt.getMonth();
	var yea=dt.getFullYear();
	var dat=new Date(yea, mon, 0).getDate();
	
	if(mon<10){
		mon="0"+mon;	
	}
	if(dat<10){
		dat="0"+dat;
	}
	
	var maxi=yea+"-"+mon+"-"+dat;
	$("#TELE_TO_DATE").attr("max",maxi);
	var from_date=$("#TELE_FROM_DATE").val();
	if(from_date!=''){
		$("#TELE_TO_DATE").attr('min',from_date);
		//$("#TELE_TO_DATE").attr('max',maxfy);	
	}	
});

function populateSearchTable(data){
	//console.log(data);
	//console.log("in search data...populate search table....");
	$("#searchtable").DataTable().clear();
	var dataLength = data.length;	
	//console.log("Here------------"+dataLength);
	if(dataLength == 0){
		$('#resultTele').css('display', 'none');
		$('#noTeleData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			if(dat.status == "Saved" || dat.status == "saved"){
				str='<i class="fa fa-pen" id="edit" onclick="viewBtnFunc(this.id,'+dat.telephoneclaimid+',\'edit\')">';
			}else{
				str='<i class="fa fa-pen" id="edit" style="color:grey">';
			}
			$("#searchtable").dataTable().fnAddData([
				dat.telephoneclaimid,	
				dat.submiteddates,
				dat.fromdate,
				dat.todate,
				dat.requestedamt,	
				dat.approvedamt,
				dat.status,
				'<i class="fa fa-eye" id="view" onclick="viewBtnFunc(this.id,'+dat.telephoneclaimid+',\'view\')">',
				str
			]);
		}
		
		$('#resultTele').css('display', 'block');
		$('#noTeleData').css('display', 'none');
	}
}

function viewBtnFunc(y,x,z){
	//var m=event.toElement.id;
	
	console.log("ClaimID: "+x+" Mode: "+y);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/viewteledata/"+x+"/"+y,
		success:function(result){
			//debugger;
			//console.log("sucesssssss");
			$('#replace_div').html(result);	
			var _href=$("#DOWNLOAD_LINK").attr("href");
			if(_href!=undefined && _href!="" && _href!=null){
			_href=_href.replaceAll('/', "FORWARD_SLASH");
		  	_href=_href.replaceAll('\\','BACKWARD_SLASH');
	  		_href=_href.replaceAll('.','EXT_DOT');	
			
			$("#DOWNLOAD_LINK").attr("href","/getContent?location="+$("#DOWNLOAD_LINK").attr("href"));	
			}
			//alert("HREF_2"+_href);
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	

}

function addBtnClick(){
	//console.log("Add button click...");
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		type:"GET",
		url:"/reimbursement/telephone",
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

function updateDataFunc(){
	if($("#requestedamt").val()=="0"){
		$("#requestedamt").val("");
	}
	console.log("hit on update: ");
	var periodfrom=document.getElementsByName("fromdate")[0].value;
	var periodto=document.getElementsByName("todate")[0].value;
	
	var formData = $('#teleformupdate').serialize();
	console.log(formData);		
	if('saveupdate'=='saveupdate'){
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
        url: "/reimbursement/telephoneUpdate",
        type: "POST",
		cache: false,
        data: formData,
		processData: false,
        contentType: 'application/x-www-form-urlencoded',
		dataType: "json",
		success:function(result){
				console.log("successss: "+result.message);
				$('#submitblock').css("display","block");
				$('#lblMsg').text(result.message+". Click OK to continue.");
				$('#okbtn').toggleClass("w3-button w3-green");
				$('#okbtn').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				},
		error:function(e){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Data already submitted for time period '"+periodfrom+"' - '"+periodto+"'"
				
			});
				console.log( "ERROR : "+ JSON.stringify(e) );
				}		
		});
	}
	else{
		bootbox.alert("You are not allowed to save in this mode");
	}
}
/*$('#DOWNLOAD_LINK').on('click', function(e){
	var filepath = $(this).attr("rm");
	alert(filepath);
	console.log("hit on download");
		$.ajax({
        url: "/reimbursement/downloadfile/abc",
        type: "POST",
		cache: false,
		processData: false,
		success:function(result){
				alert(result);
				},
		error:function(e){
				bootbox.alert("Uable to Download file.");
				console.log( "ERROR : "+ JSON.stringify(e) );
				}		
		});
});
*/



//alert($("#personnumber").val());

function saveDataFunc(str,frmid,x)
{
	if($("#requestedamt").val()=="0"){
		$("#requestedamt").val("");
	}
	//debugger;
	$(x).css("display","none");	
	////debugger;
	var personnumber=$("#personnumber").val();
	$("#statusid").val(str);
	//bootbox.alert($("#statusid").val());
	var doctype="TelephoneReimbursement";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
	
	var filepath;
	filepath=personnumber+"/"+doctype;
	$("#attachments").val("/EmployeeDocs/"+personnumber+"/"+doctype);
	var form = $("#"+frmid)[0];
	
	var data = new FormData(form);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
	url: "/reimbursement/telephoneSave/"+frmid,
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
	//alert(data);	
	$('#replace_div').html(data);
	//alert(resultfinal);	
	if(resultfinal=="Success" && frmid=="teleformupdate" && str=="saved"){
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:'You have successfully updated Telephone Reimbursement.',
				callback:function(){
				$('#replace_div').load("/reimbursement/telephoneSearch");
				}
			});
	}
	else if(resultfinal=="Success" && frmid=="teleformupdate" && str=="submit"){
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:'You have successfully submitted Telephone Reimbursement.',
				callback:function(){
				$('#replace_div').load("/reimbursement/telephoneSearch");
				}
			});
	}
	else if( resultfinal=="" && str=="saved" )	{
		//bootbox.alert("");
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:'You have successfully saved Telephone Reimbursement.',
				callback:function(){
				$('#replace_div').load("/reimbursement/telephoneSearch");
				}
			});
			
		
	}
	else if(resultfinal=="" && str=="submit"){
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully submitted Telephone Reimbursement for approval.",
				callback:function(){
				$('#replace_div').load("/reimbursement/telephoneSearch");
				}
			});		
	}
	else if(resultfinal=="alreadyApplied"){
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Claim for the specified period has already been raised.<br> Please re-check your dates and attach the file again, if required.'				
			});
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
	else
	{
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Fill all the mandatory fields and attach the file again, if required.",
				callback:function(){				
				}
			});
	}
	},
	error: function(data){
		//alert("ERROR : "+JSON.stringify(data));
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Claim for the specified period has already been raised. <br> Please re-check your dates.'				
			});
		$('#replace_div').html(data);
		
	}/*,
	xhr: function(){
		var xhr = $.ajaxSettings.xhr();
		xhr.upload.onprogress = function(evt){
		
		var percent = Math.floor((evt.loaded / evt.total)*100);
		
		$("#progressPercent").text(percent+"%");
		$("#progressBarFill").css("width",percent+"%");
		};
		xhr.upload.onload = function(){
		$("#progressPercent").text("0%");
		$("#progressBarFill").css("width","0%");
		$("#addFileRowBtn").prop('disabled',false);
		$("#deleteFileRowBtn").prop('disabled',false);
		//obj=null;
		//modal.style.display = "none";
		};
		return xhr;
	}*/
	});
}


//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

function approvalSubmit(_status)
{

	if($("#txtAmount").val()==''){
		$("#txtAmount").val("0");	
	}
	var formData = $('#teleaprvlform').serialize();

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
				url: "/reimbursement/telephoneApproval/"+_status,
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
				url: "/reimbursement/telephoneApproval/"+_status,
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

//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

function dataValidation(i){
	var mo=document.getElementById("checkmode").value;
	var check="correct";
	/*var phone=document.getElementsByName("phoneno")[i].value;
	var periodfrom=document.getElementsByName("fromdate")[i].value;
	var periodto=document.getElementsByName("todate")[i].value;
	var omproject=document.getElementsByName("omproject")[i].value;
	var amount=document.getElementsByName("requestedamt")[i].value;
	var from = new Date(Date.parse(periodfrom));
	var claimid=document.getElementById("claimid").value;
	var to = new Date(Date.parse(periodto));
	var d=new Date();
	console.log(phone+" 1 "+periodfrom+" 2 "+periodto+" 3 "+omproject+" 4 "+amount+" 5 "+claimid);
	if(phone==''||periodfrom==''||periodto==''||omproject=='none'||amount==''){
		//console.log("Enter all the mandatory fields");
		if(phone==''){
			//console.log("phone");
			check="fail";
			phoneSpan('empty');
		}
		if(periodfrom==''){
			//console.log("periodfrom");
			check="fail";
			fromSpan('empty');
		}
		if(periodto==''){
			//console.log("periodto");
			check="fail";
			toSpan('empty');
		}
		if(omproject=='none'){
			//console.log("omproject");
			check="fail";
			omSpan('empty');
		}
		if(amount==''){
			//console.log("amount");
			check="fail";
			amountSpan('empty');
		}
	}
	else if(phone.length!=10){
		//console.log("elseif 1");
		check="fail";
		if( phone.length=11){
			//console.log("elseif 1.1");
			if(phone.substring(0,1)!='0'){
				//console.log("phone length");
				phoneSpan("error");
			}
			else{
				check="correct";
			}
		}
		else{
			//console.log("phone length 10");
			check="fail";
			phoneSpan("error");	
		}		
	}
	else if(from>d || to>d){
		//console.log("future calling");
		check="fail";
		fromSpan("future");
		toSpan("future");		
	}
	else if(d.getMonth()==from.getMonth() && d.getYear()==from.getYear()){
		//console.log("date1");
		check="fail";
		fromSpan("error");
	}
	else if(d.getMonth()==to.getMonth() && d.getYear()==from.getYear()){
		//console.log("date2");
		check="fail";
		toSpan("month");		
	}
	else if(periodto<periodfrom){
		//console.log("date3");
		check="fail";
		toSpan("error");
	}*/
	if(check=="correct"){
		//console.log("All correct");
		if(claimid==''){
			//console.log("Save");
			if(mo==''){
				saveDataFunc();	
			}else{
				bootbox.alert("Action not allowed");
			}
		}
		else{
			//console.log("Update");
			if(mo=='edit'){
				updateDataFunc();	
			}else{
				bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Action not allowed"
				
				});
			}	
		}		
	}
	//console.log("outside else if");
}

/*function phoneSpan(x){
	//console.log("phone span: "+x);
	if(x=="error"){		
		//console.log("phone "+x);
		document.getElementById("phonespan").textContent ="Phone format not correct";
		document.getElementById("phonesp").textContent ="Phone format not correct";
	}
	else if(x=='empty'){		
		document.getElementById("phonespan").textContent ="This field is mandatory";
		document.getElementById("phonesp").textContent ="This field is mandatory";
	}
	else{
		document.getElementById("phonespan").textContent ="";
		document.getElementById("phonesp").textContent ="";
	}
}

function fromSpan(x){
	//console.log("in from"+x);
	if(x=="error"){
		document.getElementById("fromspan").textContent ="Cannot fill for current month";
		document.getElementById("fromsp").textContent ="Cannot fill for current month";
	}
	else if(x=='empty'){
		document.getElementById("fromspan").textContent ="This field is mandatory";
		document.getElementById("fromsp").textContent ="This field is mandatory";
	}
	else if(x=='future'){
		document.getElementById("fromspan").textContent ="Cannot fill future date";
		document.getElementById("fromsp").textContent ="Cannot fill future date";
	}
	else{
		document.getElementById("fromspan").textContent ="";
		document.getElementById("fromsp").textContent ="";	
	}	
}

function toSpan(x){
	if(x=="error"){
		document.getElementById("tospan").textContent ="'Period To' should not be less than 'Period From'";
		document.getElementById("tosp").textContent ="'Period To' should not be less than 'Period From'";	
	}
	else if(x=='empty'){
		document.getElementById("tospan").textContent ="This field is mandatory";
		document.getElementById("tosp").textContent ="This field is mandatory";
	}
	else if(x=="month"){
		document.getElementById("tospan").textContent ="Cannot fill for current month";
		document.getElementById("tosp").textContent ="Cannot fill for current month";
	}
	else if(x=="future"){
		document.getElementById("tospan").textContent ="Cannot fill future date";
		document.getElementById("tosp").textContent ="Cannot fill future date";
	}
	else{
		document.getElementById("tospan").textContent ="";
		document.getElementById("tosp").textContent ="";	
	}
}

function omSpan(x){
	if(x=="error"){
		document.getElementById("omspan").textContent ="Select correct value";
		document.getElementById("omsp").textContent ="Select correct value";	
	}
	else if(x=='empty'){
		document.getElementById("omspan").textContent ="This field is mandatory";
		document.getElementById("omsp").textContent ="This field is mandatory";
	}
	else{
		document.getElementById("omspan").textContent ="";
		document.getElementById("omsp").textContent ="";	
	}
}

function amountSpan(x){
	if(x=="error"){
		document.getElementById("amountspan").textContent ="Amount not correct";
		document.getElementById("amountsp").textContent ="Amount not correct";
	}
	else if(x=='empty'){
		document.getElementById("amountspan").textContent ="This field is mandatory";
		document.getElementById("amountsp").textContent ="This field is mandatory";
	}
	else{
		document.getElementById("amountspan").textContent ="";
		document.getElementById("amountsp").textContent ="";		
	}
}*/


function backBtnFunc(){
	var url = "/reimbursement/telephoneSearch";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			bootbox.alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}




/*********************************** */
