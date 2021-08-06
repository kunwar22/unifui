//bootbox.alert("Hello...");
var monthhh;
var yearrr;
var remmm;
$(document).ready(function() {
	
	
$('#GNRT_REM_VAL_TBL').dataTable({
    "bPaginate": false,
    "bLengthChange": false,
    "bFilter": true,
    "bInfo": false,
    "bAutoWidth": false });


	
})




function searchgnrtempl() {
	$("#GNRT_LOADER").css("display", "block");
	$('#GNRT_TMPL_SAVE_BTN').css('display', 'none');
	$('#GNRT_TMPL_SBMT_BTN').css('display', 'none');
	$('#GNRT_TMPL_APPR').css('display', 'none');
	$('#GNRT_TMPL_APPR1').css('display', 'none');
	$('#GNRT_TMPL_APPR1_SAVED').css('display', 'none');
	$('#GNRT_TMPL_APPR2_SAVED').css('display', 'none');
	$('#XCLXPRTBTN').css('display', 'none');
	var remid = $('#REMTYPELOV option:selected').val();
	var bu = $('#BULOV option:selected').val();
	var monthh = $('#MONTHLOV option:selected').val();
	var yearr = new Date().getFullYear();
	monthhh = monthh;
	yearrr = yearr;
	remmm = remid;
	var aurl = "/revisedreimbursements/searchgnrtempl/" + remid + "/" + bu + "/" + monthh + "/" + yearr + "/" + acttmod;
	$.ajax({
		type: 'GET',
		url: aurl,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
		
			if(acttmod == "GEN") {
				populategnrtempl(result, remid);
			} else if(acttmod == "APPR") {
				populateapprtbl(result, apprlvl, remid);
			}
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
		}
	});
}
/*$("#ASSIGN_SEARCH_RES").DataTable({
	'columnDefs': [{
		'targets': [2,3],
		'orderable': false
	}]
});*/
$("#GNRT_REM_VAL_TBL").DataTable({
	'paging': false,
	'ordering': false
});
/*{
	paging: false,
		destroy: true,
	searching: true,
}*/
function populateapprtbl(data, apprlvll, RID) {
	
	createdapprtbl(RID);
	$("#GNRT_REM_VAL_TBL").DataTable().clear();
	var dataLength = data.length;
	if(dataLength == 0) {
		//$("#ASGN_SUBMIT_BTN").css("display", "none");
		$("#GNRT_LOADER").css("display", "none");
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#XCLXPRTBTN').css('display', 'none');
	} else if(RID == '7') {
		if(RID == '7' && apprlvll == "APPR1" && data[0].status == "Approved") {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
                    moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.arrearamt,
					dat.vaamt,
					dat.vaarramt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description
				]);
			}
		let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
			$('#XCLXPRTBTN').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR1').css('display', 'none');			
			$('#GNRT_TMPL_APPR1_SAVED').css('display', 'none');
			$('#GNRT_TMPL_APPR2_SAVED').css('display', 'none');
		} else if(RID == '7' && apprlvll == "APPR1" && (data[0].appr1status == null || data[0].appr1status == "" || data[0].appr1status == undefined && data[0].status == "Submitted")) {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				if(dat.description === "") {
					descriptions = dat.description
				} else if(dat.description != null) {
					descriptions = dat.description
				}
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
                    moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					'<input type="text" id="reimbursementamount'+i+'" onchange="totalmountcal(' + i + ');" class="reimbursementamountClass" style="width: 80px;" name="remTemplates[' + i + '].reimbursementamount" value="' + dat.reimbursementamount + '">',
					'<input type="text" id="arrearamt'+i+'"  onchange="totalmountcal(' + i + ');" class="arrearamtClass" style="width: 80px;" name="remTemplates[' + i + '].arrearamt" value="' + dat.arrearamt + '">',
					'<input type="text" id="vaamt'+i+'" onchange="totalmountcal(' + i + ');" class="vaamtClass" style="width: 80px;" name="remTemplates[' + i + '].vaamt" value="' + dat.vaamt + '">',
					'<input type="text" id="vaarramt'+i+'" onchange="totalmountcal(' + i + ');" class="vaarramtClass" style="width: 80px;" name="remTemplates[' + i + '].vaarramt" value="' + dat.vaarramt + '">',
					'<input type="text" id="recoveryamt'+i+'" onchange="totalmountcal(' + i + ');" class="recoveryamtClass" style="width: 80px;" name="remTemplates[' + i + '].recoveryamt" value="' + dat.recoveryamt + '">',
					"Not Approved",
					dat.status,
					'<input type="text" id="totalamtid'+i+'" class="totalamtClass" style="width: 80px;" name="remTemplates[' + i + '].totalamt" value="' + dat.totalamt + '" readonly>',
					'<input type="text" style="width: 150px;" name="remTemplates[' + i + '].description" value="' + descriptions + '">',
				]);
			}
			let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
			$('#XCLXPRTBTN').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR1').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR1_SAVED').css('display', 'inline-block');
			//$('#GNRT_TMPL_APPR2_SAVED').css('display', 'inline-block');
		} else if(RID == '7' && apprlvll == "APPR1" && data[0].appr1status == "Approved") {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
                    moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.arrearamt,
					dat.vaamt,
					dat.vaarramt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description
				]);
			}
			let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
			$('#XCLXPRTBTN').css('display', 'inline-block');
		} else if(RID == '7' && apprlvll == "APPR2" && data[0].status == "Submitted") {
			var apstat = '';
			if(data[0].appr1status == null || data[0].appr1status == "" || data[0].appr1status == undefined) {
				apstat = 'Not Approved';
			} else {
				apstat = data[0].appr1status;
			}
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				if(dat.description === "") {
					descriptions = dat.description
				} else if(dat.description != null) {
					descriptions = dat.description
				}
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
                    moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					'<input type="text" id="reimbursementamount'+i+'" onchange="totalmountcal(' + i + ');" class="reimbursementamountClass"  style="width: 80px;" name="remTemplates[' + i + '].reimbursementamount" value="' + dat.reimbursementamount + '">',
					'<input type="text" id="arrearamt'+i+'"  onchange="totalmountcal(' + i + ');" class="arrearamtClass"  style="width: 80px;" name="remTemplates[' + i + '].arrearamt" value="' + dat.arrearamt + '">',
					'<input type="text" id="vaamt'+i+'" onchange="totalmountcal(' + i + ');" class="vaamtClass" style="width: 80px;" name="remTemplates[' + i + '].vaamt" value="' + dat.vaamt + '">',
					'<input type="text" id="vaarramt'+i+'" onchange="totalmountcal(' + i + ');" class="vaarramtClass" style="width: 80px;" name="remTemplates[' + i + '].vaarramt" value="' + dat.vaarramt + '">',
					'<input type="text" id="recoveryamt'+i+'" onchange="totalmountcal(' + i + ');" class="recoveryamtClass" style="width: 80px;" name="remTemplates[' + i + '].recoveryamt" value="' + dat.recoveryamt + '">',
					dat.appr1status,
					dat.status,
					'<input type="text" id="totalamtid'+i+'" class="totalamtClass" style="width: 80px;" name="remTemplates[' + i + '].totalamt" value="' + dat.totalamt + '" readonly>',
					'<input type="text" style="width: 150px;" name="remTemplates[' + i + '].description" value="' + descriptions + '">',
				]);
			}
			
		let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
			
			$('#XCLXPRTBTN').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR2_SAVED').css('display', 'inline-block');
		} else if(RID == '7' && apprlvll == "APPR2" && (data[0].status == "Processed" || data[0].status == "Approved")) {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
					moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.arrearamt,
					dat.vaamt,
					dat.vaarramt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description
				]);
			}
			$('#XCLXPRTBTN').css('display', 'inline-block');
		}
		let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
		$("#GNRT_LOADER").css("display", "none");
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
	} else if(RID != '7') {
		if(RID != '7' && apprlvll == "APPR1" && data[0].status == "Approved") {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
					moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.arrearamt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description
				]);
			}
			let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
			$('#XCLXPRTBTN').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR1').css('display', 'none');
			$('#GNRT_TMPL_APPR1_SAVED').css('display', 'none');
			$('#GNRT_TMPL_APPR2_SAVED').css('display', 'none');
		
		} else if(RID != '7' && apprlvll == "APPR1" && (data[0].appr1status == null || data[0].appr1status == "" || data[0].appr1status == undefined && data[0].status == "Submitted")) {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				if(dat.description === "") {
					descriptions = dat.description
				} else if(dat.description != null) {
					descriptions = dat.description
				}
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
					moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					'<input type="text" id="reimbursementamount'+i+'" onchange="totalmountcal(' + i + ');" class="reimbursementamountClass" style="width: 80px;" name="remTemplates[' + i + '].reimbursementamount" value="' + dat.reimbursementamount + '">',
					'<input type="text" id="arrearamt'+i+'" onchange="totalmountcal(' + i + ');" class="arrearamtClass" style="width: 80px;" name="remTemplates[' + i + '].arrearamt" value="' + dat.arrearamt + '">',
					'<input type="text" id="recoveryamt'+i+'" onchange="totalmountcal(' + i + ');" class="recoveryamtClass" style="width: 80px;" name="remTemplates[' + i + '].recoveryamt" value="' + dat.recoveryamt + '">',
					"Not Approved",
					dat.status,
					'<input type="text" id="totalamtid'+i+'" class="totalamtClass" style="width: 80px;" name="remTemplates[' + i + '].totalamt" value="' + dat.totalamt + '" readonly>',
					'<input type="text" style="width: 150px;" name="remTemplates[' + i + '].description" value="' + descriptions + '">',
				]);
			}
			$('#XCLXPRTBTN').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR1').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR1_SAVED').css('display', 'inline-block');
		    //$('#GNRT_TMPL_APPR2_SAVED').css('display', 'inline-block');
		} else if(apprlvll == "APPR1" && data[0].appr1status == "Approved") {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
					moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.arrearamt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description
				]);
			}
			let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
			$('#XCLXPRTBTN').css('display', 'inline-block');
		} else if(apprlvll == "APPR2" && data[0].status == "Submitted") {
			var apstat = '';
			if(data[0].appr1status == null || data[0].appr1status == "" || data[0].appr1status == undefined) {
				apstat = 'Not Approved';
			} else {
				apstat = data[0].appr1status;
			}
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				if(dat.description === "") {
					descriptions = dat.description
				} else if(dat.description != null) {
					descriptions = dat.description
				}
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
					moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'), 
					'<input type="text"  id="reimbursementamount'+i+'" onchange="totalmountcal(' + i + ');" class="reimbursementamountClass" style="width: 80px;" name="remTemplates[' + i + '].reimbursementamount" value="' + dat.reimbursementamount + '">',
					'<input type="text"  id="arrearamt'+i+'" onchange="totalmountcal(' + i + ');" class="arrearamtClass" style="width: 80px;" name="remTemplates[' + i + '].arrearamt" value="' + dat.arrearamt + '">',
					'<input type="text"  id="recoveryamt'+i+'" onchange="totalmountcal(' + i + ');" class="recoveryamtClass" style="width: 80px;" name="remTemplates[' + i + '].recoveryamt" value="' + dat.recoveryamt + '">',
					apstat,
					dat.status,
					'<input type="text" id="totalamtid'+i+'" class="totalamtClass" style="width: 80px;" name="remTemplates[' + i + '].totalamt" value="' + totamount + '" readonly>',
					'<input type="text" style="width: 150px;" name="remTemplates[' + i + '].description" value="' + descriptions + '">',
				]);
			}
			$('#XCLXPRTBTN').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR').css('display', 'inline-block');
			$('#GNRT_TMPL_APPR2_SAVED').css('display', 'inline-block');
		} else if(apprlvll == "APPR2" && (data[0].status == "Processed" || data[0].status == "Approved")) {
			for(var i = 0; i < dataLength; i++) {
				var dat = data[i];
				var totamount = '';
				var descriptions = "";
				totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
				
				let html='';
				if(totamount>0 || totamount<0)
				{
					html = '<span>'+dat.personnumber+'</span>';
				}
				else
				{
					html = '<span class="hideAttr">'+dat.personnumber+'</span>';
				}
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
					moment(dat.startdate).format('DD-MM-YYYY'),
					moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.arrearamt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description
				]);
			}
			$('#XCLXPRTBTN').css('display', 'inline-block');
			let rows = document.querySelectorAll(".hideAttr");
		
			for(x of rows)
			{
				x.parentElement.parentElement.classList.add('hider');
			}
		}
		let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
		
		$("#GNRT_LOADER").css("display", "none");
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}

function createdapprtbl(RIDD) {
	var tblinfo = '';
	if(RIDD == 7) {
		tblinfo = '<table id="GNRT_REM_VAL_TBL" class="w3-striped w3-margin-top">' 
		+ '<thead>' + '<th>Person Number</th>' 
		+ '<th>Name</th>' 
		+ '<th>Designation</th>' 
		+ '<th>Hire Date</th>'  
		+ '<th>HR Status</th>' 
		+ '<th>Payroll Status</th>' 
		+ '<th>Start Date</th>' 
		+ '<th>End Date</th>' 
		+ '<th>CDA Amount</th>' 
		+ '<th>Arrear Amount</th>' 
		+ '<th>VA</th>' 
		+ '<th>VA Arrear</th>' 
		+ '<th>Recovery Amount</th>' 
		+ '<th>Approver 1 Status</th>' 
		+ '<th>Final Status</th>' 
		+ '<th>Total Amount</th>' 
		+ '<th>Description</th>' 
		+ '</thead>'  
		+ '<tbody id="storage">'
		+ '</tbody>'
		+ '</table>';
		$("#resultDiv").html(tblinfo);
	} else {
		tblinfo = '<table id="GNRT_REM_VAL_TBL" class="w3-striped w3-margin-top">' 
		+ '<thead>' 
		+ '<th>Person Number</th>' 
		+ '<th>Name</th>' 
		+ '<th>Designation</th>' 
		+ '<th>Hire Date</th>'  
		+ '<th>HR Status</th>' 
		+ '<th>Payroll Status</th>' 
		+ '<th>Start Date</th>' 
		+ '<th>End Date</th>' 
		+ '<th>Amount</th>' 
		+ '<th>Arrear Amount</th>' 
		+ '<th>Recovery Amount</th>' 
		+ '<th>Approver 1 Status</th>' 
		+ '<th>Final Status</th>' 
		+ '<th>Total Amount</th>' 
		+ '<th>Description</th>' 
		+ '</thead>'		 
		+ '<tbody id="storage">'
		+ '</tbody>' 
		+ '</table>';
		$("#resultDiv").html(tblinfo);
		
	}
	$("#GNRT_REM_VAL_TBL").DataTable({
		'paging': false,
		'ordering': false
	});
}

 var table = $('#GNRT_REM_VAL_TBL').DataTable();

function populategnrtempl(data, RID) {

	createdyntbl(RID);
	$("#GNRT_REM_VAL_TBL").DataTable().clear();
	var dataLength = data.length;
	if(dataLength == 0) {
		//$("#ASGN_SUBMIT_BTN").css("display", "none");
		$("#GNRT_LOADER").css("display", "none");
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#XCLXPRTBTN').css('display', 'none');
	} else {
		var j = 0;
		for(var i = 0; i < dataLength; i++)
		{
			var dat = data[i];
			var totamount = '';
			var descriptions = "";
			totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
			if(dat.description === "") {
				descriptions = dat.description
			} else if(dat.description != null) {
				descriptions = dat.description
			}
			let html='';
			if(totamount>0 || totamount<0)
			{
				html = '<span>'+dat.personnumber+'</span>';
			}
			else
			{
				html = '<span class="hideAttr">'+dat.personnumber+'</span>';
			}
				//table.columns(1).search('^(?:(?!61).)*$\r?\n?', true, false).draw();
			 
			if(RID == '7') {
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus, 
					'<input type="date" class="startdateClass" style="width: 120px;" name="remTemplates[' + j + '].startdate" value="' + dat.startdate + '">', 
					'<input type="date" class="enddateClass" style="width: 120px;" name="remTemplates[' + j + '].enddate" value="' + dat.enddate + '">', 
					'<input type="text" id="reimbursementamount'+j+'" onchange="totalmountcal(' + j + ');" class="reimbursementamountClass" style="width: 80px;" name="remTemplates[' + j + '].reimbursementamount" value="' + dat.reimbursementamount + '">',
					'<input type="text" id="vaamt'+j+'" onchange="totalmountcal(' + j + ');" class="vaamtClass" style="width: 80px;" name="remTemplates[' + j + '].vaamt" value="' + dat.vaamt + '">',
					'<input type="text" id="vaarramt'+j+'" onchange="totalmountcal(' + j + ');" class="vaarramtClass" style="width: 80px;" name="remTemplates[' + j + '].vaarramt" value="' + dat.vaarramt + '">',
					'<input type="text" id="arrearamt'+j+'"  onchange="totalmountcal(' + j + ');" class="arrearamtClass" style="width: 80px;" name="remTemplates[' + j + '].arrearamt" value="' + dat.arrearamt + '">',
					'<input type="text" id="recoveryamt'+j+'" onchange="totalmountcal(' + j + ');" class="recoveryamtClass" style="width: 80px;" name="remTemplates[' + j + '].recoveryamt" value="' + dat.recoveryamt + '">',
					'<input type="text" id="totalamtid'+j+'" class="totalamtClass" style="width: 80px;" name="remTemplates[' + j + '].totalamt" value="' + totamount + '" readonly>',
					'<input type="text" class="descriptionClass" style="width: 150px;" name="remTemplates[' + j + '].description" value="' + descriptions + '">'
				]);
			} else {
				$("#GNRT_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					moment(dat.hiredate).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus, 
					'<input type="date" class="startdateClass"  style="width: 120px;" name="remTemplates[' + j + '].startdate" value="' + dat.startdate + '">',
					'<input type="date" class="enddateClass" style="width: 120px;" name="remTemplates[' + j + '].enddate" value="' + dat.enddate + '">', 
					'<input type="text" id="reimbursementamount'+j+'" class="reimbursementamountClass" onchange="totalmountcal(' + j + ');" style="width: 80px;" name="remTemplates[' + j + '].reimbursementamount" value="' + dat.reimbursementamount + '">',
					'<input type="text" id="arrearamt'+j+'" class="arrearamtClass" onchange="totalmountcal(' + j + ');" style="width: 80px;" name="remTemplates[' + j + '].arrearamt" value="' + dat.arrearamt + '">',
					'<input type="text" id="recoveryamt'+j+'" class="recoveryamtClass" onchange="totalmountcal(' + j + ');" style="width: 80px;" name="remTemplates[' + j + '].recoveryamt" value="' + dat.recoveryamt + '">',
					'<input type="text" id="totalamtid'+j+'" class="totalamtClass"  style="width: 80px;" name="remTemplates[' + j + '].totalamt" value="' + totamount + '" readonly>',
					'<input type="text" class="descriptionClass" style="width: 150px;" name="remTemplates[' + j + '].description" value="' + descriptions + '" >'
				]);


			}
			j = j + 1;
		
		}
		
		let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}
		
		$("#GNRT_LOADER").css("display", "none");
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#GNRT_TMPL_SAVE_BTN').css('display', 'inline-block');
		$('#GNRT_TMPL_SBMT_BTN').css('display', 'inline-block');
		$('#XCLXPRTBTN').css('display', 'inline-block');




	}
};

function totalmountcal(j) {
	
	var amt = 0;
	var val1 = parseFloat($("#reimbursementamount"+j).val());
	var val2 = parseFloat($("#arrearamt"+j).val());
	var val3 = parseFloat($("#recoveryamt"+j).val());
	var val4 = parseFloat($("#vaamt"+j).val());
	var val5 = parseFloat($("#vaarramt"+j).val());

	if (val1 == ""||val1=="NaN"||val1==isNaN||val1==NaN||isNaN(val1) ) {
		val1=0;
	}if (val2 == ""||val2=="NaN" ||val2==isNaN||val2==NaN||isNaN(val2)) {
		val2=0;
	}if (val3 == ""||val3=="NaN"||val3==isNaN||val3==NaN||isNaN(val3)) {
		val3=0;
	}
	if (val4 == ""||val4=="NaN"||val4==isNaN||val4==NaN||isNaN(val4)) {
		val4=0;
	}
	if (val5 == ""||val5=="NaN"||val5==isNaN||val5==NaN||isNaN(val5)) {
		val5=0;
	}
	amt += parseFloat(val1+val2+val4+val5-val3);


	$("#totalamtid"+j).val(amt);
}



function createdyntbl(RIDD) {
	var tblinfo = '';
	if(RIDD == 7) {
		tblinfo = '<form id="GNRT_TEMPL_FORM1">' 
		+ '<table id="GNRT_REM_VAL_TBL" class="w3-striped w3-margin-top">' 
		+ '<thead>' 
		+ '<th>Person Number</th>' 
		+ '<th>Name</th>' 
		+ '<th>Designation</th>' 
		+ '<th>Hire Date</th>'  
		+ '<th>HR Status</th>' 
		+ '<th>Payroll Status</th>' 
		+ '<th>Start Date</th>' 
		+ '<th>End Date</th>' 
		+ '<th>CDA Amount</th>' 
		+ '<th>VA</th>' 
		+ '<th>VA Arrear</th>' 
		+ '<th>Arrear Amount</th>' 
		+ '<th>Recovery Amount</th>' 
		+ '<th>Total Amount</th>' 
		+ '<th>Description</th>' 
		+ '</thead>' 
		+ '<tbody id="storage">'
		+ '</tbody>'
		+ '</table>' 
		+ '</form>';
		$("#resultDiv").html(tblinfo);
	} else {
		tblinfo = '<form id="GNRT_TEMPL_FORM1">' 
		+ '<table id="GNRT_REM_VAL_TBL" class="w3-striped w3-margin-top">' 
		+ '<thead>' 
		+ '<th>Person Number</th>' 
		+ '<th>Name</th>' 
		+ '<th>Designation</th>' 
		+ '<th>Hire Date</th>'  
		+ '<th>HR Status</th>' 
		+ '<th>Payroll Status</th>' 
		+ '<th>Start Date</th>' 
		+ '<th>End Date</th>' 
		+ '<th>Amount</th>' 
		+ '<th>Arrear Amount</th>' 
		+ '<th>Recovery Amount</th>' 
		+ '<th>Total Amount</th>' 
		+ '<th>Description</th>' 
		+ '</thead>' 
		+ '<tbody id="storage">'
		+ '</tbody>'
		+ '</table>' 
		+ '</form>';
		$("#resultDiv").html(tblinfo);
	}
	
	$("#GNRT_REM_VAL_TBL").DataTable({
		'paging': false,
		'ordering': false
	});
}

function savegnrtvalue(mode) {

var value = $('.dataTables_filter input').val();
if(value===null||value==''){
	
	
	
	$("#GNRT_LOADER").css("display", "block");
	var gnrtformdata = $('#GNRT_TEMPL_FORM1').serialize();

	$.ajax({
		url: "/revisedreimbursements/savegnrtvalue/" + mode,
		type: "POST",
		data: gnrtformdata,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(result) {
		
			$("#GNRT_LOADER").css("display", "none");
			if(result == "Success" && mode == "Saved") {
				//$('#GNRT_TMPL_SAVE_BTN').css('display', 'none');
				//$('#GNRT_TMPL_SBMT_BTN').css('display', 'none');
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: "Reimbursement value successfully saved.",
					callback: function() {
						searchgnrtempl();
						//$('#generatediv').load("/revisedreimbursements/managegeneratetab/GEN");
					}
				});
			} else if(result == "Success" && mode == "Submitted") {
				$('#GNRT_TMPL_SAVE_BTN').css('display', 'none');
				$('#GNRT_TMPL_SBMT_BTN').css('display', 'none');
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: "Reimbursement value successfully submitted for approval.",
					callback: function() {
						$('#generatediv').load("/revisedreimbursements/managegeneratetab/GEN");
					}
				});
			} else if(result == "Success" && (mode == "APPR1" || mode == "APPR2")) {
				$('#GNRT_TMPL_APPR').css('display', 'none');
				$('#GNRT_TMPL_APPR2_SAVED').css('display', 'none');
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: "Reimbursement value successfully approved.",
					callback: function() {
						$('#div_approve').load("/revisedreimbursements/managegeneratetab/APPR");
					}
				});
			}
			 else if(result == "Success" && (mode == "SavedApprover1" || mode == "SavedApprover2")) {
				$('#GNRT_TMPL_APPR').css('display', 'none');
				$('#GNRT_TMPL_APPR2_SAVED').css('display', 'none');
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: "Reimbursement value successfully saved.",
					callback: function() {
						searchgnrtempl();
						//$('#generatediv').load("/revisedreimbursements/managegeneratetab/GEN");
					}
				});
			}
		},
		error: function(response) {
			
			$("#GNRT_LOADER").css("display", "none");
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "You have logged off please login again.",
			});
		}
	});
	
	}else{
		bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Search criteria should be blank before save or submit.",
			});
	}
	
}

function exportTableToExcel(tableID, filename = '') {
	
	let rows = document.querySelectorAll(".hider");
	let storage = [];
	for(x of rows)
	{
		storage.push(x);
		x.remove();
	}
	
	$("#" + tableID).table2excel({
		exclude: ".noExl",
		filename: filename,
		fileext: ".xls"
	});
	
	let tbody = document.getElementById("storage");
	
	
	tbody.append(...storage);
	
}
/*

success: function(data)
{
	console.log("RETURNED DATA  ::::   "+data);
	var jsondata = JSON.parse(data);


	var table_ = $('<table border="1" id="resultset" class="w3-table w3-border w3-margin-top"></table>').css('width','100%');

	$(results_div).append(table_);
	var my_columns = [];

	$.each( jsondata[0], function( key, value ) {
		var my_item = {};
		my_item.data = key;

		my_item.title = key;

		my_columns.push(my_item);

	});

	$('#resultset').DataTable({
		data: jsondata,
		"columns": my_columns
	});

//populateResultSet(jsondata);

}*/

