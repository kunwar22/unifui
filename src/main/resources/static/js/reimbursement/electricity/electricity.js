
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

$("#from_date").click(function(){
	$("#from_date").attr("max",maxfy);
	//$("#from_date").attr("min",minfy);	
});
$("#to_date").click(function(){
	disablefuturedate();
	//$("#to_date").attr("max",maxfy);
	var from_date=$("#from_date").val();
	if(from_date!=''){
		$("#to_date").attr('min',from_date);
	}	
});




$(document).ready(function(){
	//debugger;
	durationCal();
	Unitvaluecalculation();
	
	loadElectricityReimbursementData();
	//$('#electricityreimbursement').DataTable();

	//disablefuturedate();
	/*$('#btnSearch').on('click', function(e){
		//alert("click");
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});*/
});

function loadclaimform(claimid)
{
	////debugger;
	window.open("../reimbursement/claimform/"+claimid,'window','width=700,height=700');
}


/* Electricity Reimbursment claim page starts here */
function loadCreateElectricityClaim(){
	/*alert("in");*/
	var url = "/reimbursement/electricityclaim";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
}

function disablefuturedate()
{
	//disabling future date selection
	////debugger;
	var dttoday = new Date();
	var month = dttoday.getMonth()+1;
	var day= dttoday.getDate();
	var year = dttoday.getFullYear();
	if(month<10)
		month='0'+month.toString();
	if(day<10)
		day='0'+day.toString();
	
	var maxdate = year+"-"+month+"-"+day;
	$('#to_date').attr('max',maxdate);
}


//calculating bill duration
$('#to_date').on('change',function(e) {
	durationCal();
	Unitvaluecalculation();
});

$('#from_date').on('change',function(e) {
	$("#to_date").val("");
	durationCal();
	Unitvaluecalculation();
});

function  durationCal() {

	var fromdate=new Date($('#from_date').val());	
	var todate=new Date($('#to_date').val());
	if(Date.parse(fromdate) > Date.parse(todate))
	{
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"To Date must be greater than From Date."				
			});
	}
	else
	{
		var bill_duration = ((todate.getTime()-fromdate.getTime())/(86400000))+1;
		$('#bill_duration').attr("value",bill_duration);		
	}
	
}


//calculating eligible units
$('#claimed_units').on('blur',function() {
	if($('#bill_duration').val()==""  || $('#bill_duration').val()=="NaN")
	{
		////debugger;
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Please fill dates."				
			});		
		$('#claimed_units').val("0");
		return false;
	}
	Unitvaluecalculation();
});

$('#claimed_amt').on('blur',function() {
	if(parseInt($('#claimed_amt').val()) > parseInt($('#billed_amt').val()))
	{
		$('#claimed_amt').val("0");
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Claimed Amount can not be greater than Billed Amount."			
			});		
	}
});




function Unitvaluecalculation()
{	//debugger;
	////debugger;
	var eligibility =0;
	var perc_60=0;
	var prorata_units=0;
	var duration = parseInt($('#bill_duration').val());	
	var claimed_units = parseInt($('#claimed_units').val());
	var billed_units = parseInt($('#billed_units').val());
	
	if(claimed_units > billed_units)
	{
		$('#claimed_units').val("0");
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Claimed Units can not be greater than Billed Units."			
			});	
			return;	
	}
	/////Temporary-start\\\
	var ceiling_units = parseInt($('#ceiling_units').val());
	var ceiling_units_temp=(parseInt($('#ceiling_units').val())/30)*duration;
	var designation = $('#EL_JOB').val();
	/////Temporary-end\\\
		
	if(designation == "MD" || designation =="Managing Director" || designation == "FD" || designation =="Functional Director" )
	{
		if(duration <= 30 && claimed_units<= ceiling_units)
		{
			eligibility=claimed_units;
			$('#eligible_units').val(eligibility);
			$('#units_eligible').val(ceiling_units);
			//$('#eligible_units').prop({value:eligibility});
			//$('#units_eligible').prop({value:parseInt(ceiling_units)});
		}
		else if(duration <= 30 && claimed_units> ceiling_units)
		{
			prorata_units = (ceiling_units*duration)/30;
			perc_60 = claimed_units*0.6;
			$('#billed_units_perc').val(perc_60);
			$('#units_eligible').val(prorata_units);
			//$('#billed_units_perc').prop({value:perc_60});
			//$('#units_eligible').prop({value:parseInt(prorata_units)});
			if(perc_60>prorata_units)
			{
				eligibility = perc_60;
				//$('#eligible_units').prop({value:eligibility});
				$('#eligible_units').val(eligibility);
			}
			else if(perc_60<prorata_units)
			{
				eligibility = prorata_units;
				//$('#eligible_units').prop({value:eligibility});
				$('#eligible_units').val(eligibility);
			}
			//eligibility = parseInt(ceiling_units);
			
			
		}
		else if(duration > 30 && claimed_units<=ceiling_units_temp)
		{
			prorata_units = (ceiling_units*duration)/30;
			eligibility=prorata_units;
			$('#units_eligible').val(eligibility);
			$('#eligible_units').val(claimed_units);
			//$('#units_eligible').prop({value:eligibility});
			//$('#eligible_units').prop({value:parseInt(claimed_units)});
		}
		else if(duration > 30 && (claimed_units>ceiling_units_temp))
		{
			prorata_units = (ceiling_units*duration)/30;			
			perc_60 = claimed_units*0.6;
			$('#billed_units_perc').val(perc_60);
			//$('#billed_units_perc').prop({value:perc_60});
			if(perc_60 > prorata_units)
			{
				eligibility = perc_60;
				$('#units_eligible').val(prorata_units);
				$('#eligible_units').val(eligibility);
				//$('#units_eligible').prop({value:prorata_units});
				//$('#eligible_units').prop({value:eligibility});
			}
			else if(perc_60<prorata_units)
			{
				eligibility = claimed_units;
				$('#units_eligible').val(prorata_units);
				$('#eligible_units').val(eligibility);
				//$('#units_eligible').prop({value:prorata_units});
				//$('#eligible_units').prop({value:eligibility});
			}				
		}
	}
	else if(designation == "PD" || designation =="Project Director" || designation=="HOD" || designation=="Head of Department")
	{
		//debugger;
		if(parseInt(duration) <= 30 && parseInt(claimed_units)<=parseInt(ceiling_units))
		{
			eligibility=claimed_units;
			$('#units_eligible').val(ceiling_units);
			$('#eligible_units').val(eligibility);
			//$('#units_eligible').prop({value:parseInt(ceiling_units)});
			//$('#eligible_units').prop({value:eligibility});

		}
		else if(parseInt(duration) <= 30 && parseInt(claimed_units)>parseInt(ceiling_units))
		{
			eligibility=ceiling_units;
			$('#units_eligible').val(ceiling_units);
			$('#eligible_units').val(eligibility);
			//$('#units_eligible').prop({value:parseInt(ceiling_units)});
			//$('#eligible_units').prop({value:eligibility});
			
		}
		else if(duration > 30 && parseInt(claimed_units)<=parseInt(ceiling_units_temp))
		{
			prorata_units = (ceiling_units*duration)/30;
			$('#units_eligible').val(prorata_units);
			//$('#units_eligible').prop({value:prorata_units});
			eligibility=claimed_units;
			$('#eligible_units').val(eligibility);
			//$('#eligible_units').prop({value:eligibility});			
		}
		else if(duration > 30 && parseInt(claimed_units)>parseInt(ceiling_units_temp))
		{
			prorata_units = (ceiling_units*duration)/30;
			perc_60 = claimed_units*0.6;
			//$('#units_eligible').prop({value:prorata_units});
			$('#units_eligible').val(prorata_units);
			if(perc_60>prorata_units)
			{
				eligibility = perc_60;
				//$('#eligible_units').prop({value:eligibility});
				$('#eligible_units').val(eligibility);
			}
			else if(perc_60<prorata_units)
			{
				eligibility = prorata_units;
				//$('#eligible_units').prop({value:eligibility});
				$('#eligible_units').val(eligibility);
			}
			
			/*eligibility=parseInt(prorata_units);
			$('#eligible_units').val(eligibility);*/
			//$('#eligible_units').prop({value:eligibility});			
		}
	}
	$('#units_eligible').val(parseInt($('#units_eligible').val()));
	$('#eligible_units').val(parseInt($('#eligible_units').val()));
	//alert(num);
	//var n = num.toFixed(2);
}


/* code for validation between billed amount and claimed amount */
function validateAmount()
{
	var claimed_amount = parseInt($('#claimed_amt').val());
	var billed_amount = parseInt($('#billed_amt').val());
	
	if(claimed_amount > billed_amount)
	{
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Claimed Amount can not be greater than Billed Amount."				
			});		
	}
}


/*code for saving electricity claim WITH ATTACHMENT starts here */

function ajaxsave(mode,frmid,sus)
{
	if($("#billed_units").val()==""){
		$("#billed_units").val(0);
	}
	if($("#billed_amt").val()==""){
		$("#billed_amt").val(0);
	}
	if($("#claimed_units").val()==""){
		$("#claimed_units").val(0);
	}
	if($("#claimed_amt").val()==""){
		$("#claimed_amt").val(0);
	}
	////debugger;
	var personnumber=$("#elec_personnumber").text();
	$("#statusid").val(mode);
	//alert($("#statusid").val());
	var doctype="ElectricityReimbursement";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
	
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	$("#attachments").val("/EmployeeDocs/"+filepath);
	
	//alert("FILEPATH::"+$('#attachments').val());
	
	var form = $("#"+frmid)[0];
	//alert(form);
	
	var data = new FormData(form);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
	url: "/reimbursement/saveElectricityReimbursement",
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
	//alert(data);
		//debugger;
	$('#replace_div').html(data);
	
	if( resultfinal=="Success" )
	{

		if(mode=="save" && stat_submit == "" && sus == "save")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully saved Electricity Reimbursement.",
				callback:function(){
					$('#replace_div').load("/reimbursement/electricity");
				}
			});			
		}
		else if(mode=="save" && stat_submit == "" && sus=="update")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully updated Electricity Reimbursement.",
				callback:function(){
					$('#replace_div').load("/reimbursement/electricity");
				}
			});
		}else if(mode=="Save&Submit" && stat_submit == "")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully submitted Electricity Reimbursement for approval.",
				callback:function(){
					$('#replace_div').load("/reimbursement/electricity");
				}
			});			
		}
		else if(mode=="submit" && stat_submit == "")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"You have successfully submitted Electricity Reimbursement for approval.",
				callback:function(){
					$('#replace_div').load("/reimbursement/electricity");
				}
			});			
		}else if(stat_submit == "alreadyApplied"){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required."
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
		bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message:"Fill all the mandatory fields and attach the file again, if required."				
					});
	}
	},
	error: function(data){
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


/* Search Data JS starts*/

var jsonSearchElecUrl = '/reimbursement/getElectricityReimbursement/';

function loadElectricityReimbursementData()
{
	//debugger;
	
	$.ajax({
		type: 'GET',
		url: jsonSearchElecUrl,
		dataSrc: '',
		data: {
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;			
			populateElectricReimbursementDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateElectricReimbursementDataTable(data)
{
	$("#electricityreimbursement").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#search_result').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str="";
			var str1="";
			if(dat.status == "Saved" || dat.status == "saved"){
				str1='<i class="fa fa-pen" id="edit" onclick="electricity_details('+dat.electricityid+',\'edit\')"></i>';
			}else{
				str1='<i class="fa fa-pen" id="edit" style="color:grey"></i>';
			}
			$("#electricityreimbursement").dataTable().fnAddData([				
				dat.electricityid,
				dat.fromdate,	
				dat.todate,				
				dat.billedunits,
				dat.billedamt,
				dat.claimedunit,
				dat.claimedamt,
				dat.status,
				dat.dates,
				dat.apramt,
				'<i class="fa fa-eye w3-padding" id="view" onclick="electricity_details('+dat.electricityid+',\'view\');"></i>',
				str1
			]);
		}
		$('#electricityreimbursement').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}
/* Search Data JS ends*/

function electricity_details(electricityclaimid,display_mode)
{
	//debugger;
	
	var url = "/reimbursement/editElectricityReimbursement/"+electricityclaimid+"/"+display_mode;
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
}

function backBtnFunc(){
	var url = "/reimbursement/electricity";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}


//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

function approvalSubmit(_status)
{
	
	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();
	if($("#txtAmount").val()==''){
		$("#txtAmount").val("0");	
	}
	var check="false";
	var msg;
	var formData = $('#ELEC_SAVE').serialize();
		
	if(_status=='Approved'){
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
		url: "/reimbursement/electricityApproval/"+_status,
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
		error:function(e){
			//alert("Data already submitted for time period '"+periodfrom+"' - '"+periodto+"'");
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
		});
	}
}

//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

$(document).ready(function() {

	var _href = $("#DOWNLOAD_LINK").attr("href");
	if (_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');

		$("#DOWNLOAD_LINK").attr("href", "/getContent?location=" + $("#DOWNLOAD_LINK").attr("href"));
	}
});

$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});