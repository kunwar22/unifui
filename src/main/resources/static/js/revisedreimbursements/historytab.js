//bootbox.alert("Hello...");

function searchgnrthstry(){

	
	var remid=$('#HSTRY_REMTYPELOV option:selected').val();
	var bu=$('#HSTRY_BULOV option:selected').val();
	var strt=$('#HSTRY_STRT_DT').val();
	var endd=$('#HSTRY_END_DT').val();
	$('#XCLXPRTBTN_1').css('display', 'none');
	if(bu==""||strt==""||endd==""){
		bootbox.alert({
			size: 'medium',
			title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message:"Please fill mandatory criterias to search."
		});
	}else{
		$("#HSTRY_LOADER").css("display","block");
		var aurl="/revisedreimbursements/searchgnrthstry/"+remid+"/"+bu+"/"+strt+"/"+endd;
		$.ajax({
			type: 'GET',
			url: aurl,
			contentType:"application/json",
			dataType:"json",
			success:function(result){
				populategnrthstry(result,remid);
			},
			error:function(e){
				console.log( "ERROR : "+ JSON.stringify(e) );
			}
		});
	}
	

}

/*$("#ASSIGN_SEARCH_RES").DataTable({
	'columnDefs': [{
		'targets': [2,3],
		'orderable': false
	}]
});*/


/*{
	paging: false,
		destroy: true,
	searching: true,
}*/

function populategnrthstry(data,RIDDD){
	createdynhstrytbl(RIDDD);
	$("#HSTRY_REM_VAL_TBL").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		//$("#ASGN_SUBMIT_BTN").css("display", "none");
		$("#HSTRY_LOADER").css("display","none");
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		$('#XCLXPRTBTN_1').css('display', 'none');
	} else {
		//$("#ASGN_SUBMIT_BTN").css("display", "block");
		//console.log(data[0].personnumber);
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			
			var totamount = '';
		
			totamount = parseFloat(dat.reimbursementamount + dat.vaamt + dat.vaarramt + dat.arrearamt - dat.recoveryamt)
			if(dat.description === "") {
				descriptions = dat.description
			} else if(dat.description != null) {
				descriptions = dat.description
			}
			let html='';
			if(totamount>0)
			{
				html = '<span>'+dat.personnumber+'</span>';
			}
			else
			{
				html = '<span class="hideAttr">'+dat.personnumber+'</span>';
			}
			
			
			if(RIDDD=='7'){
				$("#HSTRY_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					dat.hiredate.substring(0, 10),
					//dat.hrstatus,
					moment(dat.hrstatus).format('DD-MM-YYYY'),
					dat.payrollstatus,
					dat.startdate,
					dat.enddate,
					//moment(dat.startdate).format('DD-MM-YYYY'),
					//moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.vaamt,
					dat.vaarramt,
					dat.arrearamt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description,
					
				]);
			}else {
				$("#HSTRY_REM_VAL_TBL").dataTable().fnAddData([
					html,
					dat.name,
					dat.designation,
					//dat.hiredate.substring(0, 10),
					moment(dat.hiredate.substring(0, 10)).format('DD-MM-YYYY'),
					dat.hrstatus,
					dat.payrollstatus,
					dat.startdate,
					dat.enddate,
					//moment(dat.startdate).format('DD-MM-YYYY'),
					//moment(dat.enddate).format('DD-MM-YYYY'),
					dat.reimbursementamount,
					dat.arrearamt,
					dat.recoveryamt,
					dat.appr1status,
					dat.status,
					dat.totalamt,
					dat.description,
					
				]);
			}
			let rows = document.querySelectorAll(".hideAttr");
		
		for(x of rows)
		{
			x.parentElement.parentElement.classList.add('hider');
		}

		}
		$("#HSTRY_LOADER").css("display","none");
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#XCLXPRTBTN_1').css('display', 'inline-block');
	}
};


function exportTableToExcel(tableID, filename = ''){
	$("#"+tableID).table2excel({exclude:".noExl", filename:filename, fileext:".xls"});
}


function createdynhstrytbl(RI){
	var tblinfoo='';
	if(RI==7){
		tblinfoo='<table id="HSTRY_REM_VAL_TBL" class="w3-striped w3-table w3-margin-top">'+
				'<thead>'+
				'<th>Person Number</th>'+
				'<th>Name</th>'+
				'<th>Designation</th>'+
				'<th>HireDate</th>'+ 
				'<th>HR Status</th>'+
				'<th>Payroll Status</th>'+
				'<th>Start Date</th>'+
				'<th>End Date</th>'+
				'<th>Amount</th>'+
				'<th>VA</th>'+
				'<th>VA Arrear</th>'+
				'<th>Arrear Amount</th>'+
				'<th>Recovery Amount</th>'+
				'<th>Approver 1 Status</th>'+
				'<th>Final Status</th>'+
				'<th>Total Amount</th>'+ 
				'<th>Description</th>'+ 
				
				'</thead>'+
				'</table>';
		$("#resultDiv").html(tblinfoo);
	}else{
		tblinfoo='<table id="HSTRY_REM_VAL_TBL" class="w3-striped w3-table w3-margin-top">'+
			'<thead>'+
			'<th>Person Number</th>'+
			'<th>Name</th>'+
			'<th>Designation</th>'+
			'<th>HireDate</th>'+ 
			'<th>HR Status</th>'+
			'<th>Payroll Status</th>'+
			'<th>Start Date</th>'+
			'<th>End Date</th>'+
			'<th>Amount</th>'+
			'<th>Arrear Amount</th>'+
			'<th>Recovery Amount</th>'+
			'<th>Approver 1 Status</th>'+
			'<th>Final Status</th>'+
			'<th>Total Amount</th>'+ 
			'<th>Description</th>'+ 
			
			'</thead>'+
			'</table>';
		$("#resultDiv").html(tblinfoo);
	}
	$("#HSTRY_REM_VAL_TBL").DataTable({
		'paging':false,
		'ordering':false
	});
}
