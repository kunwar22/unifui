$(document).ready(function() 
{
	//debugger;
	//calculate_hospitalization_duration();	
	var _hrefappr=$("#DOWNLOAD_LINKsp").val();
	/*if(_hrefappr!=undefined && _hrefappr!="" && _hrefappr!=null){
		_hrefappr=_hrefappr.replaceAll('/', "FORWARD_SLASH");
		_hrefappr=_hrefappr.replaceAll('\\','BACKWARD_SLASH');
		_hrefappr=_hrefappr.replaceAll('.','EXT_DOT');

		$("#DOWNLOAD_LINK").attr("href","/getContent?location="+_hrefappr);
	}*/
	$("#DOWNLOAD_LINK").attr("href","/getContent?location="+_hrefappr);		
	disablepastdates();
});
			
/* Medical Advance Request page loads below */
function loadMediAdvReq(){
	/*alert("in");*/
	var url = "/reimbursement/medicaladvancerequest";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
}
/* Loading Relationship */
function loadRelation(e,patient_name,patient_relation,relid)
{
	//debugger;
	var patient_name_select = document.getElementById(patient_name);
	
	var datarelation_ =$(patient_name_select).children("option:selected").attr("data-dep-relationship");
	var datarelationid_ =$(patient_name_select).children("option:selected").attr("data-dep-relationid");
	
	var relationshipdes = document.getElementById(patient_relation);
	var relationshipid = document.getElementById(relid);
	
	relationshipid.value = datarelationid_;
	relationshipdes.value = datarelation_;
}

/* Loading Hospital Names */
function loadHospitalNames(e,hospid)
{
	//debugger;
	if(hospid==0)
	{
		$('#hosname').prop("disabled",true);
		$('#hosname').val("0");
	}	
	else
	{
		var hospitalnameurl="/reimbursement/getHospitalNames/"+hospid;
		console.log("URL for loading HOspital Names ==> "+hospitalnameurl);
		hosnamelov="";
		
		$.ajax({
			type: 'GET',
			url: hospitalnameurl,
			dataSrc: '',		
			dataType: 'json',
			success: function(data)
			{
				hospitalData = data.hospitalnamelov;
				var dataLength = hospitalData.length;
				if(hospid != 1)
				{			
					$("#hospname").css("display","block");
					$("#hosname").css("display","none");		
					//$('#hosname').prop("disabled",true);					
				}
				else
				{
					$('#hosname').prop("disabled",false);
					$("#hospname").css("display","none");
					$("#hosname").css("display","block");
					//alert("data length : "+dataLength);
					hosnamelov+='<option value="0" selected disabled>Select</option>';
					for(var i=0; i < dataLength; i++)
					{
						hosnamelov+='<option value="'+data.hospitalnamelov[i].hospitalnamelovid+'">'+data.hospitalnamelov[i].name+'</option>';						
					}
					$('#hosname').html(hosnamelov);
					
				}	
			},
			error: function(e)
			{
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}
}

//calculating hospitalization duration
function calculate_hospitalization_duration()
{
	var treatmentStartDate=new Date($('#treatmentStartDate').val());	
	var treatmentEndDate=new Date($('#treatmentEndDate').val());
	if(Date.parse(treatmentStartDate) > Date.parse(treatmentEndDate))
	{
		alert("To Date must be greater than From Date");
		$('#treatmentStartDate').prop({value:""});
		$('#treatmentEndDate').prop({value:""});
		$('#treatmentStartDate').focus();
	}
	else
	{
		var duration = ((treatmentEndDate.getTime()-treatmentStartDate.getTime())/(86400000))+1;
		$('#duration').attr("value",duration);		
	}
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
//disabling past dates
$("#treatmentStartDate").change(function(){
	$("#treatmentEndDate").val("");	
});
$("#treatmentEndDate").click(function(){	
	var from_date=$("#treatmentStartDate").val();
	if(from_date!=''){
		$("#treatmentEndDate").attr('min',from_date);
	}	
});
function disablepastdates()
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
	
	var mindate = year+"-"+month+"-"+day;
	$('#treatmentStartDate').attr('min',mindate);
	$('#treatmentEndDate').attr('min',mindate);
	$('#treatmentStartDate').attr('max',maxfy);
	$('#treatmentEndDate').attr('max',maxfy);
}

/* Activate Save Button */
/*function activate()
{
	//debugger;
	if(document.getElementById("chkdec").checked == true)
	{
		document.getElementById("btnsave").disabled=false;
	}
	else if(document.getElementById("chkdec").checked == false)
	{
		document.getElementById("btnsave").disabled=true;
	}
}
*/
/* below code is for saving medical advance data with attachments */
function ajaxsave(mode,view_mode,x)
{	
	//debugger;	
	if(document.getElementById("chkdec").checked == false)
	{
		bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "You cannot proceed without declaration."                
           	});
	}
	else if($("#selfordependent").children("option").filter(":selected").text()!='Self'
			&& $("#DEP_PATIENT").children("option").filter(":selected").text()=='Select'){						
			bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Select Patient Name first."                
           	});
		}
	else{
		$(x).css("display","none");
		$("#statusid").val(mode);
		console.log("SAVE MODE : "+mode);
		console.log("VIEW MODE : "+view_mode);
				
		if(mode=="save")
		{			
			saveMediAdvData(view_mode);
		}
		else
		{
			updateMediAdvData(view_mode);
		}	
	}	
};

function saveMediAdvData(mode)
{
	var jsonmediadvsaveUrl="/reimbursement/requestMedicalAdvance"; 
	var personnumber=$("#mediadv_personnumber").val();
	$("#statusid").val(mode);
	var doctype="MedicalAdvance";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	$("#mediadvestimate").val("/EmployeeDocs/"+filepath);
	console.log("PATH : "+$('#mediadvestimate').val());
	console.log("FILEPATH::"+$('#mediadvestimate').val());
	
	var hostyid = $("#hostype").children("option").filter(":selected").val();
	if(hostyid=="1"){
		$("#hospname").val($("#hosname").children("option").filter(":selected").text());
	}
	$("#hosptype").val($("#hostype").children("option").filter(":selected").text());
	if($("#selfordependent").children("option").filter(":selected").text()!='Self'){
		$("#patientname").val($("#DEP_PATIENT").children("option").filter(":selected").text());
	}
	$("#relationshipid").val($("#DEP_RELATION").children("option").filter(":selected").text());
	
	
	var form = $("#ADV_SAVE")[0];
	//alert(form);
	var data = new FormData(form);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: jsonmediadvsaveUrl,
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
			$('#replace_div').html(data);
			if( resultfinal=="Success")
			{
					bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"You have successfully saved Medical Advance details.",
					callback:function(){
						$("#replace_div").load("/reimbursement/medicalAdvance");
					}
				});
			}
			else
			{
				bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Fill all the mandatory fields and re-upload attachment."                
           		 });		
			}
		},
		error: function(data)
		{
			alert("ERROR : "+JSON.stringify(data));
		}
	});
}

/* FOR FORM UPDATE */

function updateMediAdvData(mode)
{
	//debugger;
	var jsonmediadvupdateUrl="/reimbursement/updateMedicalAdvance/"+mode; 
	
	//alert("URL ====>  "+jsonmediadvupdateUrl);
	var personnumber=$("#mediadv_personnumber").val();
	$("#statusid").val(mode);
	var doctype="MedicalAdvance";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	$("#mediadvestimate").val("/EmployeeDocs/"+filepath);
	console.log("PATH : "+$('#mediadvestimate').val());
	console.log("FILEPATH::"+$('#mediadvestimate').val());
	
	$("#hosptype").val($("#hostype").children("option").filter(":selected").text());
	var hostyid = $("#hostype").children("option").filter(":selected").val();
	if(hostyid=="1"){
		$("#hospname").val($("#hosname").children("option").filter(":selected").text());
	}
	if($("#selfordependent").children("option").filter(":selected").text()!='Self'){
		$("#patientname").val($("#DEP_PATIENT").children("option").filter(":selected").text());
	}
	$("#relationshipid").val($("#DEP_RELATION").children("option").filter(":selected").text());
	
	
	var form = $("#ADV_SAVE")[0];
	//alert(form);
	var data = new FormData(form);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: jsonmediadvupdateUrl,
		type: "POST",
		enctype: "multipart/form-data",
		data: data,
		cache: false,
		contentType:false,
		processData: false,
		timeout:600000,
		success: function(data)
		{
			$('#replace_div').html(data);			
			if( resultfinal=="Success" && mode=="Saved")
			{
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"You have successfully updated Medical Advance details.",
					callback:function(){
						$("#replace_div").load("/reimbursement/medicalAdvance");
					}
				});
			}else if( resultfinal=="Success" && mode=="Submitted"){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"You have successfully submitted Medical Advance for approval.",
					callback:function(){
						$("#replace_div").load("/reimbursement/medicalAdvance");
					}
				});
			}
			else
			{
				bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Fill all the mandatory fields and re-upload attachment."                
           		 });		
			}
		},
		error: function(data)
		{
			alert("ERROR : "+JSON.stringify(data));
			$('#replace_div').html(data);
		}
	});
}


function backBtnFunc(){
	var url = "/reimbursement/medicalAdvance";
	$('#replace_div').load(url);
	}

function checkamt(){
	if($("#amt").val()==""){
		$("#amt").val("0");
	}
}

function approvalSubmit(_status)
{

	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();
	var chk="no";
	if(amt==""){
		$('#txtAmount').val("0.0");		
	}
	var formData = $('#ADV_SAVE').serialize();
	
	if(_status=="Approved")
	{
		if(amt=="" )
			{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Request cannot be Approved without filling Approve Amount."
				});
			}
			else{	
				chk="ok";				
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
			chk="ok";	
		}
	}
	
	if(chk=="ok"){
		//console.log(formData);
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
			url: "/reimbursement/mediadvApproval/" + _status,
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
							message:"You have successfully "+_status+".",
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

function claimforchange(e){
	$("#DEP_PATIENT").val("0");
	$("#patientname").val("");
	$("#DEP_RELATION").val("");
	$("#RELID").val("0");
	if(e=='Self'){
		$("#patientrow").css("display","none");
		$("#DEP_RELATION").val("Self");	
		$("#patientname").val(pname);
	}
	else{
		$("#patientrow").css("display","block");
	}
}


$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});