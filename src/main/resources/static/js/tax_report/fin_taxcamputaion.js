var Serial_no=1;

function openTab(legal) {
	var i, x, tablinks;
	x = document.getElementsByClassName("legal");
	for(i = 0; i < x.length; i++) {
		x[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablink");
	for(i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace("w3-light-grey", "");
	}
	document.getElementById(legal).style.display = "block";
	$('button[name =\"' + legal + '\"]').addClass("w3-light-grey");
	//evt.currentTarget.className+=" w3-theme";
	/*if(legal == 'Deduction_Declaration') {
	    load_Declaration_Details();				
		load_Lease_Declaration_Details();
		load_Car_Declaration_Details();
		load_Quarter_Declaration_Details();
		load_Declaration_Details2();
	}*/
}

function myFunction(id) {
	var x = document.getElementById(id);
	if(x.className.indexOf("w3-show") == -1) {
		x.className += " w3-show";
	} else {
		x.className = x.className.replace(" w3-show", "");
	}
}
var fin_year = '';
$('#Financial_year').on('change', function(e) {
	fin_year = $("#Financial_year option:selected").text();
	$("#finyear_lbl").val(fin_year);
	//load_tax_report(fin_year);
	//load_perq_tax_report(fin_year);
	//load_TaxCal80c_tax_report(fin_year);
	//load_chapterVIA_tax_report(fin_year);
	//load_TotalCal_tax_report(fin_year);
});

function load_tax_report(x) {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/TaxCalTotalEarning/" + x,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			//alert(JSON.stringify(result));
			$("#REPORTS_LOADER").css("display", "none");
			populateearningDataTable(result)
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function populateearningDataTable(data) {
	// $("#Tax_REPORT").DataTable().clear();
	$(`#Tax_REPORT tbody`).empty()
	let tbl = '';
	if(data['Basic Salary'] != "0.0") {
		tbl += ' <tr>  <td>Basic Salary</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right"> ' + data['Basic Salary'] + '</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Basic Salary'] + '</td>  </tr>'
	}
	if(data['House Rent Allowance'] != "0.0") {
		tbl += ' <tr>  <td>House Rent Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['House Rent Allowance'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected House Rent Allowance'] + '</td>  </tr>'
	}
	if(data['Dearness Allowanc'] != "0.0") {
		tbl += ' <tr>  <td>Dearness Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Dearness Allowance'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Dearness Allowance'] + '</td>  </tr>'
	}
	if(data['National Holiday Allowance'] != "0.0") {
		tbl += ' <tr>  <td>National Holiday Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['National Holiday Allowance'] + '</td>   <td></td> <td></td>  </tr>'
	}
	if(data['Family Planning Allowance'] != "0.0") {
		tbl += ' <tr>  <td>Family Planning Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Family Planning Allowance'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Family Planning Allowance'] + '</td>  </tr>'
	}
	if(data['Honorarium Pay'] != "0.0") {
		tbl += ' <tr>  <td>Honorarium Pay</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Honorarium Pay'] + '</td>   <td></td> <td></td>  </tr>'
	}
	if(data['Medical Allowance'] != "0.0") {
		tbl += ' <tr>  <td>Medical Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Medical Allowance'] + '</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Medical Allowance'] + '</td>  </tr>'
	}
	if(data['Miscellaneous Payment'] != "0.0") {
		tbl += ' <tr>  <td>Miscellaneous Payment</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Miscellaneous Payment'] + '</td>   <td></td> <td></td>  </tr>'
	}
	if(data['Personal Pay'] != "0.0") {
		tbl += ' <tr>  <td>Personal Pay</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Personal Pay'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Personal Pay'] + '</td>  </tr>'
	}
	if(data['Special Pay'] != "0.0") {
		tbl += ' <tr>  <td>Special Pay</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Special Pay'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Special Pay'] + '</td>  </tr>'
	}
	if(data['Cafeteria Allowance'] != "0.0") {
		tbl += ' <tr>  <td>Cafeteria Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Cafeteria Allowance'] + '</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Cafeteria Allowance'] + '</td>  </tr>'
	}
	if(data['Medical Benefit'] != "0.0") {
		tbl += ' <tr>  <td>Medical Benefit</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Medical Benefit'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Medical Benefit'] + '</td>  </tr>'
	}
	if(data['Transport Allowance'] != "0.0") {
		tbl += ' <tr>  <td>Transport Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Transport Allowance'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Transport Allowance'] + '</td>  </tr>'
	}
	if(data['Deputation Allowance'] != "0.0") {
		tbl += ' <tr>  <td>Deputation Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Deputation Allowance'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Deputation Allowance'] + '</td>  </tr>'
	}
	$("#Tax_REPORT tbody").append(tbl);
}
/*******************************************************************************************************/
function load_perq_tax_report(x) {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/TaxCalTotalPerquisites/" + x,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			populateperqDataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function populateperqDataTable(data) {
	let tbl = '';
	$(`#PERQ_Tax_REPORT tbody`).empty()
	if(data['Perquisites Value - RFA(Lease)'] != "0.0") {
		tbl += ' <tr>  <td>Perquisites Value - RFA(Lease)</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Perquisites Value - RFA(Lease)'] + '</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Perquisites Value - RFA(Lease)'] + '</td>  </tr>'
	}
	if(data['Perquisites Value - Car'] != "0.0") {
		tbl += ' <tr>  <td>Perquisites Value - Car</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Perquisites Value - Car'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Perquisites Value - Car'] + '</td>  </tr>'
	}
	if(data['Perquisites Value - RFA(Quarter)'] != "0.0") {
		tbl += ' <tr>  <td>Perquisites Value - RFA(Quarter)</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Perquisites Value - RFA(Quarter)'] + '</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Projected Perquisites Value - RFA(Quarter)'] + '</td>  </tr>'
	}
	$("#PERQ_Tax_REPORT tbody").append(tbl);
}
/*******************************************************************************************************/
function load_TaxCal80c_tax_report() {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/TaxCal80c",
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			populateTaxCal80cDataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function populateTaxCal80cDataTable(data) {
	let tbl = '';
	$(`#TaxCal80c tbody`).empty()
	if(data['Public Provident Fund'] != "0.0") {
		tbl += ' <tr>  <td>Public Provident Fund</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Public Provident Fund'] + '</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Public Provident Fund'] + '</td>  </tr>'
	}
	$("#TaxCal80c tbody").append(tbl);
}
/*******************************************************************************************************/
function load_chapterVIA_tax_report(x) {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/chapter6A/" + x,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			populateChapter6ADataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function populateChapter6ADataTable(data) {
	var dataLength = Object.keys(data).length;
	// alert(dataLength)
	$(`#chapterVIA tbody`).empty()
	let tbl = '';
	if(dataLength != 0) {
		tbl += ' <tr>  <td>80C</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['80C'] + '</td>   </tr>'
		tbl += ' <tr>  <td>80D</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['80D'] + '</td>   </tr>'
		tbl += ' <tr>  <td>80DD</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['80DD'] + '</td>   </tr>'
		tbl += ' <tr>  <td>80DDB</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['80DDB'] + '</td>   </tr>'
		tbl += ' <tr>  <td>80E</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['80E'] + '</td>   </tr>'
		tbl += ' <tr>  <td>80U</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['80U'] + '</td>   </tr>'
		tbl += ' <tr>  <td>Total</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">' + data['Total'] + '</td>   </tr>'
	}
	$("#chapterVIA tbody").append(tbl);
}
/*******************************************************************************************************/
var per_num = '';
var fin_year = '';

function load_TotalCal_tax_report() {
	
	per_num = $("#PersonNumber").val();
	fin_year = $("#Financial_year").val();
	if(per_num == '' || per_num == "" || per_num == null) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: "Please select Person Number."
		});
		return;
	} else if(fin_year == '' || fin_year == "" || fin_year == null) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: "Please select Financial Year."
		});
		return;
	} else {
		
		$("#REPORTS_LOADER").css("display", "block");
		$.ajax({
			type: "GET",
			url: "/fin/tax/manage/TotalCalculation/" + fin_year + "/" + per_num,
			contentType: "application/json",
			dataType: "json",
			success: function(result) {
				$("#REPORTS_LOADER").css("display", "block");
				populateTotalCalculationDataTable(result)
				load_salary_details();
				load_Declaration_Details();				
				load_Lease_Declaration_Details();
				load_Car_Declaration_Details();
				load_Quarter_Declaration_Details();
				load_Declaration_Details2();
				$("#fin_tax_tab").css("display", "block");
				$("#REPORTS_LOADER").css("display", "none");
			},
			error: function(e) {
				console.log("ERROR : " + JSON.stringify(e));
				$("#REPORTS_LOADER").css("display", "none");
			}
		});
		
		
	}
}

function populateTotalCalculationDataTable(data) {
	$("#REPORTS_LOADER").css("display", "block");
	let tbl = '';
	$(`#TotalTaxCal`).empty();
	tbl += '<table style="width: 100%;">';
	tbl += '  <thead>';
	tbl += '      <tr style="border-bottom: 1px solid #cdd0d4; padding-left: 16px; padding-right: 16px;">';
	tbl += '          <th style="width: 55%; padding-left: 16px;"><h6 style="margin-bottom: 0px;">Particulars</h6></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 7px;"><h6 style="margin-bottom: 0px;">Actual</h6></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 12px;"><h6 style="margin-bottom: 0px;">Projected</h6></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"><h6 style="margin-bottom: 0px;">Total</h6></th>';
	tbl += '      </tr>';
	tbl += '  </thead>';
	tbl += '  <tbody>';
	tbl += '  </tbody>';
	tbl += '</table>';
	tbl += '<button onclick="myFunction(`Demo1`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td colspan="3">Income Tax Computation For FY 2021-2022</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo1" class="w3-show">';
	// 1.1 Salary (Section 17(1))
	tbl += '<button onclick="myFunction(`Demo2`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo2arr" class="fas fa-angle-double-right"></i> Salary (Section 17(1))</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">' + data['sum_act'] + '</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">' + data['sum_tot'] + '</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">' + data['sum_prj'] + '</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo2" class="w3-hide">';
	tbl += '<table style="width: 100%;">';
	tbl += '  <thead>';
	tbl += '      <tr>';
	tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '      </tr>';
	tbl += '  </thead>';
	tbl += '<tbody>';
	if(data['act_basic_salary'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Basic Salary</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 48 + ',\'Basic Salary (Actual)\',\'A\')"> ' + data['act_basic_salary'] + ' </a> </td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 48 + ',\'Basic Salary (Projected)\',\'P\')">' + data['tot_basic_salary'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Basic Salary'] + '</td></tr>';
	}
	if(data['act_grade_pay'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Grade Pay</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 50 + ',\'Grade Pay (Actual)\',\'A\')">' + data['act_grade_pay'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 50 + ',\'Grade Pay (Projected)\',\'P\')">' + data['tot_grade_pay'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Grade Pay'] + '</td></tr>';
	}
	if(data['act_dearness_allowance'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Dearness Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 54 + ',\'Dearness Allowance (Actual)\',\'A\')">' + data['act_dearness_allowance'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 54 + ',\'Dearness Allowance (Projected)\',\'P\')">' + data['tot_dearness_allowance'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Dearness Allowance'] + '</td></tr>';
	}
	if(data['act_house_rent_allowance'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">House Rent Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 52 + ',\'House Rent Allowance (Actual)\',\'A\')">' + data['act_house_rent_allowance'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 52 + ',\'House Rent Allowance (Projected)\',\'P\')">' + data['tot_house_rent_allowance'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected House Rent Allowance'] + '</td></tr>';
	}
	if(data['act_cafeteria_allowance'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Cafeteria Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 74 + ',\'Cafeteria Allowance (Actual)\',\'A\')">' + data['act_cafeteria_allowance'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#"style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 74 + ',\'Cafeteria Allowance (Projected)\',\'P\')">' + data['tot_cafeteria_allowance'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Cafeteria Allowance'] + '</td></tr>';
	}
	if(data['act_deputation_allowance'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Deputation Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 1 + ',\'Deputation Allowance (Actual)\',\'A\')">' + data['act_deputation_allowance'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#"style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 1 + ',\'Deputation Allowance (Projected)\',\'P\')">' + data['tot_deputation_allowance'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Deputation Allowance'] + '</td></tr>';
	}
	if(data['act_honorarium_pay'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Honorarium Pay</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 60 + ',\'Honorarium Pay (Actual)\',\'A\')">' + data['act_honorarium_pay'] + '</a></td><td style="text-align: right; padding-right: 12px;">--</td><td style="text-align: right; padding-right: 16px;">' + data['act_honorarium_pay'] + '</td></tr>';
	}
	if(data['act_medical_allowance'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Medical Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 64 + ',\'Medical Allowance (Actual)\',\'A\')">' + data['act_medical_allowance'] + '</td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 64 + ',\'Medical Allowance(Projected)\',\'P\')">' + data['tot_medical_allowance'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Medical Allowance'] + '</td></tr>';
	}
	if(data['act_personal_pay'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Personal Pay</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 70 + ',\'Personal Pay (Actual)\',\'A\')">' + data['act_personal_pay'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 70 + ',\'Personal Pay (Projected)\',\'P\')">' + data['tot_personal_pay'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Personal Pay'] + '</td></tr>';
	}
	if(data['act_special_pay'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Special Pay</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 72 + ',\'Special Pay (Actual)\',\'A\')">' + data['act_special_pay'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 54 + ',\'Special Pay (Projected)\',\'P\')">' + data['tot_special_pay'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Special Pay'] + '</td></tr>';
	}
	if(data['act_national_holiday_allowance'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">National Holiday Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 56 + ',\'National Holiday Allowance (Actual)\',\'A\')">' + data['act_national_holiday_allowance'] + '</a></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['act_national_holiday_allowance'] + '</td></tr>';
	}
	if(data['act_family_planning_allowance'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Family Planning Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 58 + ',\'Family Planning Allowance (Actual)\',\'A\')">' + data['act_family_planning_allowance'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 58 + ',\'Family Planning Allowance (Projected)\',\'P\')">' + data['tot_family_planning_allowance'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Family Planning Allowance'] + '</td></tr>';
	}
	if(data['act_leave_encashment'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Leave Encashment</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 66 + ',\'Leave Encashment (Actual)\',\'A\')">' + data['act_leave_encashment'] + '</a></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['act_leave_encashment'] + '</td></tr>';
	}
	if(data['act_miscellaneous_payment'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Miscellaneous Payment</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 68 + ',\'Miscellaneous Payment (Actual)\',\'A\')">' + data['act_miscellaneous_payment'] + '</a></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['act_miscellaneous_payment'] + '</td></tr>';
	}
	if(data['act_empr_nps_cont'] != "0.0") {
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Employer NPS Contribution</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 19 + ',\'Employer NPS Contribution (Actual)\',\'A\')">' + data['act_empr_nps_cont'] + '</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 19 + ',\'Employer NPS Contribution (Projected)\',\'P\')">' + data['tot_empr_nps_cont'] + '</a></td><td style="text-align: right; padding-right: 16px;">' + data['prj_empr_nps_cont'] + '</td></tr>';
	}
	tbl += '</tbody>';
	tbl += '</table>';
	tbl += '</div>';
	// 1.2 Value of Perquisites (Section 17(2))
	if(data['sum_prj_perq'] != "0.0") {
		tbl += '<button onclick="myFunction(`Demo3`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo3arr" class="fas fa-angle-double-right"></i> Value of Perquisites (Section 17(2))</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['sum_prj_perq'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div id="Demo3" class="w3-hide">';
		tbl += '<table style="width: 100%;">';
		tbl += '  <thead>';
		tbl += '      <tr>';
		tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '      </tr>';
		tbl += '  </thead>';
		tbl += '<tbody>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Perquisites Value - Car</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Perquisites Value - Car'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Perquisites Value - RFA(Lease)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Perquisites Value - RFA(Lease)'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Perquisites Value - RFA(Quarter)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Projected Perquisites Value - RFA(Quarter)'] + '</td></tr>';
		tbl += '</tbody>';
		tbl += '</table>';
		tbl += '</div>';
	}
	// 1.3 Profit in lieu of salary (Section 17(3))
	tbl += '<button onclick="myFunction(`Demo4`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 16px;">Profit in lieu of salary (Section 17(3))</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">0.0</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo4" class="w3-hide">';
	tbl += '</div>';
	// 1.4 Gross Salary
	tbl += '<button onclick="myFunction(`Demo4`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <th style="width: 55%; padding-left: 16px;">Gross Salary</th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;">' + data['gross_salary'] + '</th>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo4" class="w3-hide">';
	tbl += '</div>';
	// 1.5 Allowances under Sec 10
	tbl += '<button onclick="myFunction(`Demo5`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo5arr" class="fas fa-angle-double-right"></i> Allowances exempted under Sec 10</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">(' + data['Exemption HRA'] + ')</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo5" class="w3-hide">';
	tbl += '<table style="width: 100%;">';
	tbl += '  <thead>';
	tbl += '      <tr>';
	tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '      </tr>';
	tbl += '  </thead>';
	tbl += '<tbody>';
	tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px; padding-top: 8px; padding-bottom: 8px;">Exemption HRA</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Exemption HRA'] + '</td></tr>';
	tbl += '</tbody>';
	tbl += '</table>';
	tbl += '</div>';
	// 1.6 Deductions under Sec 16
	tbl += '<button onclick="myFunction(`Demo6`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo6arr" class="fas fa-angle-double-right"></i> Deductions under Sec 16</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right; ">(' + data['Standard Deduction'] + ')</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo6" class="w3-hide">';
	tbl += '<table style="width: 100%;">';
	tbl += '  <thead>';
	tbl += '      <tr>';
	tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '      </tr>';
	tbl += '  </thead>';
	tbl += '<tbody>';
	tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px; padding-top: 8px; padding-bottom: 8px;">Standard Deduction</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Standard Deduction'] + '</td></tr>';
	tbl += '</tbody>';
	tbl += '</table>';
	tbl += '</div>';
	// 1.7 Income Chargeable Under head Salaries
	tbl += '<button onclick="myFunction(`Demo7`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <th style="width: 55%; padding-left: 16px;">Income Chargeable Under head Salaries</th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;">' + data['income_chargeable_under_heade_salary'] + '</th>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo7" class="w3-hide">';
	tbl += '</div>';
	// 1.8 Income from House Property
	if(Math.abs(data['cal_rental_Income_less_30_int']) != "0.0") {
		tbl += '<button onclick="myFunction(`Demo8`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo8arr" class="fas fa-angle-double-right"></i> Income from House Property </td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">(' + Math.abs(data['cal_rental_Income_less_30_int']) + ')</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div id="Demo8" class="w3-hide">';
		tbl += '<table style="width: 100%;">';
		tbl += '  <thead>';
		tbl += '      <tr>';
		tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '      </tr>';
		tbl += '  </thead>';
		tbl += '<tbody>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">Rental Income</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Rental Income'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">Less: Standard Deduction @ 30%</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['cal_rental_Income_less_30'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">Less: Interest on capital borrowed U/s 24(b)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Interest on home loan'] + '</td></tr>';
		tbl += '</tbody>';
		tbl += '</table>';
		tbl += '</div>';
	}
	// 1.9 Income from other sources
	if(data['cal_total_income_other_sources'] != "0.0") {
		tbl += '<button onclick="myFunction(`Demo9`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo9arr" class="fas fa-angle-double-right"></i> Income from other sources</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['cal_total_income_other_sources'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div id="Demo9" class="w3-hide">';
		tbl += '<table style="width: 100%;">';
		tbl += '  <thead>';
		tbl += '      <tr>';
		tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
		tbl += '      </tr>';
		tbl += '  </thead>';
		tbl += '<tbody>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Interest on FD</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Income from FD'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Interest on RD</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Income from RD'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Saving Bank Interest</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Saving Bank Interest'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Income from Pension</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Income from Pension'] + '</td></tr>';
		tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Others Income</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Others'] + '</td></tr>';
		tbl += '</tbody>';
		tbl += '</table>';
		tbl += '</div>';
	}
	// 1.10 Gross Total Income
	tbl += '<button onclick="myFunction(`Demo10`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <th style="width: 55%; padding-left: 16px;">Gross Total Income</th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;">' + data['gross_total_income'] + '</th>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo10" class="w3-hide">';
	tbl += '</div>';
	// 1.11 Chapter VI A Deductions
	tbl += '<button onclick="myFunction(`Demo11`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo11arr" class="fas fa-angle-double-right"></i> Chapter VI A Deductions</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">' + data['Total Chapter6a'] + '</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo11" class="w3-hide">';
	// 1.11.1 Deductions under Sections 80C, 80CCC and 80CCD
	tbl += '<button onclick="myFunction(`Demo12`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 32px;"><i id="Demo12arr" class="fas fa-angle-double-right"></i> Deductions under Sections 80C, 80CCC and 80CCD</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">' + data['ded_80c_80ccc_80ccd'] + '</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo12" class="w3-hide">';
	// 1.11.1.1 Deductions under Section 80C and 80CCC
	tbl += '<button onclick="myFunction(`Demo13`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 64px;"><i id="Demo13arr" class="fas fa-angle-double-right"></i> Deductions under Section 80C and 80CCC</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80C'] + '</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo13" class="w3-hide">';
	tbl += '<table style="width: 100%;">';
	tbl += '  <thead>';
	tbl += '      <tr>';
	tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '      </tr>';
	tbl += '  </thead>';
	tbl += '<tbody>';
	if(typeof(data['5 Years Fixed Deposit_80C']) !== "undefined"  ){
		if(data['5 Years Fixed Deposit_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C 5 Years Fixed Deposit</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['5 Years Fixed Deposit_80C'] + '</td></tr>';
	}
	if(typeof(data['ELSS_80C']) !== "undefined"  ){
		if(data['ELSS_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C ELSS</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['ELSS_80C'] + '</td></tr>';
	}
	if(typeof(data['Infrastructure Bond_80C']) !== "undefined"  ){
		if(data['Infrastructure Bond_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Infrastructure Bond</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Infrastructure Bond_80C'] + '</td></tr>';
	}
	if(typeof(data['LIC_80C']) !== "undefined"  ){
		if(data['LIC_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C LIC</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['LIC_80C'] + '</td></tr>';
	}
	if(typeof(data['Mutual Funds_80C']) !== "undefined"  ){
		if(data['Mutual Funds_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Mutual Funds</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Mutual Funds_80C'] + '</td></tr>';
	}
	if(typeof(data['NSC_80C']) !== "undefined"  ){
		if(data['NSC_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C NSC</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['NSC_80C'] + '</td></tr>';
	}
	if(typeof(data['NSS_80C']) !== "undefined"  ){
		if(data['NSS_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C NSS</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['NSS_80C'] + '</td></tr>';
	}
	if(typeof(data['Public Provident Fund_80C']) !== "undefined"  ){
		if(data['Public Provident Fund_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Public Provident Fund</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Public Provident Fund_80C'] + '</td></tr>';
	}
	if(typeof(data['Pension Linked Insurance_80C']) !== "undefined"  ){
		if(data['Pension Linked Insurance_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Pension Linked Insurance</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Pension Linked Insurance_80C'] + '</td></tr>';
	}
	if(typeof(data['Post Office Fixed Deposit Schemes_80C']) !== "undefined"  ){
		if(data['Post Office Fixed Deposit Schemes_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Post Office Fixed Deposit Schemes</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Post Office Fixed Deposit Schemes_80C'] + '</td></tr>';
	}
	if(typeof(data['Sukanya Samriddhi Yojana_80C']) !== "undefined"  ){
		if(data['Sukanya Samriddhi Yojana_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Sukanya Samriddhi Yojana</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Sukanya Samriddhi Yojana_80C'] + '</td></tr>';
	}
	if(typeof(data['Tution Fee_80C']) !== "undefined"  ){
		if(data['Tution Fee_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Tution Fee</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Tution Fee_80C'] + '</td></tr>';
	}
	if(typeof(data['Unit Linked Insurance Plan_80C']) !== "undefined"  ){
		if(data['Unit Linked Insurance Plan_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Unit Linked Insurance Plan</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Unit Linked Insurance Plan_80C'] + '</td></tr>';
	}
	if(typeof(data['SIP - More than 3 Years Lock-in Period_80C']) !== "undefined"  ){
		if(data['SIP - More than 3 Years Lock-in Period_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C SIP - More than 3 Years Lock-in Period</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['SIP - More than 3 Years Lock-in Period_80C'] + '</td></tr>';
	}
	if(typeof(data['Other Savings_80C']) !== "undefined"  ){
		if(data['Other Savings_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Other Savings Covered under 80C</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['Other Savings_80C'] + '</td></tr>';
	}
	if(typeof(data['cal_projected_empl_vpf_cont']) !== "undefined"  ){
		if(data['cal_projected_empl_vpf_cont'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Empl VPF Contribution</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['cal_projected_empl_vpf_cont'] + '</td></tr>';
	}
	if(typeof(data['cal_projected_epf']) !== "undefined"  ){
		if(data['cal_projected_epf'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Empl SPF Contribution</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['cal_projected_epf'] + '</td></tr>';
	}
	if(typeof(data['cal_projected_empl_gpf_cont']) !== "undefined"  ){
		if(data['cal_projected_empl_gpf_cont'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Empl GPF Contribution</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['cal_projected_empl_gpf_cont'] + '</td></tr>';
	}
	if(typeof(data['cal_projected_empl_gis_cont']) !== "undefined"  ){
		if(data['cal_projected_empl_gis_cont'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Empl GIS Contribution</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['cal_projected_empl_gis_cont'] + '</td></tr>';
	}
	if(typeof(data['cal_projected_empl_gsli_cont']) !== "undefined"  ){
		if(data['cal_projected_empl_gsli_cont'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80C Empl GSLI Contribution</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['cal_projected_empl_gsli_cont'] + '</td></tr>';
	}//if(data['cal_projected_empl_snps'] != "0.0")
	//    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px;">80C Empl SNPS Contribution</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['cal_projected_empl_snps']+'</td></tr>';
	if(typeof(data['80CCC-Premium Paid for Annuity Plan of LIC or Other Insurer_80C']) !== "undefined"  ){
		if(data['80CCC-Premium Paid for Annuity Plan of LIC or Other Insurer_80C'] != "0.0") tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">80CCC Premium Paid for Annuity Plan of LIC or Other Insurer</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['80CCC-Premium Paid for Annuity Plan of LIC or Other Insurer_80C'] + '</td></tr>';
	}
	tbl += '</tbody>';
	tbl += '</table>';
	tbl += '</div>';
	// 1.11.1.2 Deductions under Section 80CCD
	tbl += '<button onclick="myFunction(`Demo14`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <td style="width: 55%; padding-left: 64px;"><i id="Demo14arr" class="fas fa-angle-double-right"></i> Deductions under Section 80CCD</td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
	tbl += '     	   <td style="width: 15%; text-align: right;">' + data['ded_80ccd'] + '</td>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div id="Demo14" class="w3-hide">';
	tbl += '<table style="width: 100%;">';
	tbl += '  <thead>';
	tbl += '      <tr>';
	tbl += '          <th style="width: 55%; padding-left: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
	tbl += '      </tr>';
	tbl += '  </thead>';
	tbl += '<tbody>';
	tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">Deductions under Section 80CCD(1B)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['80CCD-1B'] + '</td></tr>';
	tbl += '<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">Deductions under Section 80CCD2</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">' + data['prj_empr_nps_cont'] + '</td></tr>';
	tbl += '</tbody>';
	tbl += '</table>';
	tbl += '</div>';
	tbl += '</div>';
	// 1.11.2 Deductions under Section 80D
	if(data['80D'] != "0.0") {
		tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80D</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80D'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div class="w3-hide">';
		tbl += '</div>';
	}
	// 1.11.2 Deductions under Section 80DD
	if(data['80DD'] != "0.0") {
		tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80DD</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80DD'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div class="w3-hide">';
		tbl += '</div>';
	}
	// 1.11.2 Deductions under Section 80DDB
	if(data['80DDB'] != "0.0") {
		tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80DDB</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80DDB'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div class="w3-hide">';
		tbl += '</div>';
	}
	// 1.11.2 Deductions under Section 80E
	if(data['80E'] != "0.0") {
		tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80E</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80E'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div class="w3-hide">';
		tbl += '</div>';
	}
	// 1.11.2 Deductions under Section 80U
	if(data['80U'] != "0.0") {
		tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80U</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80U'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div class="w3-hide">';
		tbl += '</div>';
	}
	// 1.11.2 Deductions under Section 80TTA
	if(data['80TTA'] != "0.0" && data['80TTA'] != undefined) {
		tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80TTA</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80TTA'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div class="w3-hide">';
		tbl += '</div>';
	}
	// 1.11.2 Deductions under Section 80TTB
	if(data['80TTB'] != "0.0" && data['80TTB'] != undefined) {
		tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
		tbl += '  <table style="width: 100%;">';
		tbl += ' 	    <tr style="">';
		tbl += '     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80TTB</td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;"></td>';
		tbl += '     	   <td style="width: 15%; text-align: right;">' + data['80TTB'] + '</td>';
		tbl += '      </tr>';
		tbl += '  </table>';
		tbl += '</button>';
		tbl += '<div class="w3-hide">';
		tbl += '</div>';
	}
	tbl += '</div>';
	// 1.11.2 Total Income
	tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <th style="width: 55%; padding-left: 16px;">Total Taxable Income</th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;">' + data['total_taxable_income'] + '</th>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div class="w3-hide">';
	tbl += '</div>';
	// 1.11.2 Total Tax Payable
	tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <th style="width: 55%; padding-left: 16px;">Total Income Tax Payable</th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;">' + data['Tax on Total Income'] + '</th>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div class="w3-hide">';
	tbl += '</div>';
	// 1.11.2 Total Tax deducted Till Date
	tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <th style="width: 55%; padding-left: 16px;">Tax Deducted Till Date</th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails(' + 80 + ',\'Tax Deducted Till Date (Actual)\',\'A\')">' + data['Income Tax Till Date'] + '</a></th>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div class="w3-hide">';
	tbl += '</div>';
	// 1.11.2 Balance Tax
	tbl += '<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
	tbl += '  <table style="width: 100%;">';
	tbl += ' 	    <tr style="">';
	tbl += '     	   <th style="width: 55%; padding-left: 16px;">Balance Tax</th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;"></th>';
	tbl += '     	   <th style="width: 15%; text-align: right;">' + data['Balance Tax'] + '</th>';
	tbl += '      </tr>';
	tbl += '  </table>';
	tbl += '</button>';
	tbl += '<div class="w3-hide">';
	tbl += '</div>';
	tbl += '</div>';
	$("#TotalTaxCal").append(tbl);
	//load_salary_details();	
}
/********************************* */
function taxamountdetails(x, y, z) {
	$("#REPORTS_LOADER").css("display", "block");
	$(`#Tax_REPORT`).empty();
	var tbl = '';
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/manageTaxCalElementDataByYear/" + fin_year + "/" + x + "/" + z,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			$("#REPORTS_LOADER").css("display", "none");
			$('#headdingid').text(y);
			$('#TaxDetailsPopup').css('display', 'block');
			tbl += '<table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';
			tbl += '  <thead><tr class="w3-theme-l3">';
			tbl += '          <th style="width: 8.33%;">April</th>';
			tbl += '          <th style="width: 8.33%;">May</th>';
			tbl += '          <th style="width: 8.33%;">June</th>';
			tbl += '          <th style="width: 8.33%;">July</th>';
			tbl += '          <th style="width: 8.33%;">August</th>';
			tbl += '          <th style="width: 8.33%;">September</th>';
			tbl += '          <th style="width: 8.33%;">October</th>';
			tbl += '          <th style="width: 8.33%;">November</th>';
			tbl += '          <th style="width: 8.33%;">December</th>';
			tbl += '          <th style="width: 8.33%;">January</th>';
			tbl += '          <th style="width: 8.33%;">February</th>';
			tbl += '          <th style="width: 8.33%;">March</th>';
			tbl += '      </tr>';
			tbl += '  </thead>';
			tbl += '  <tbody>';
			tbl += '          <td style="width: 8.33%;">' + data['APR'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['MAY'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['JUN'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['JUL'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['AUG'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['SEP'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['OCT'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['NOV'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['DEC'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['JAN'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['FEB'] + '</td>';
			tbl += '          <td style="width: 8.33%;">' + data['MAR'] + '</td>';
			tbl += '  </tbody>';
			tbl += '</table>';
			$("#Tax_REPORT").append(tbl);
		},
		error: function(e) {
			$("#REPORTS_LOADER").css("display", "block");
			console.log("ERROR : " + JSON.stringify(e));
		}
	});
}
$('#PersonNumber').on('change', function() {
	var selectObject = $(this).children("option:selected").val();
	if(selectObject == 'search') {
		$('#id02').css("display", "block");
	} else if(selectObject != 'search') {
		$('#id02').css("display", "none");
	}
});
/**************************Pop-up  SEARCH start here****************************************/
var jsonUrlPer = '/accomodation/searchAccomodation/gePersonId';
var personid = '';
var personName = '';
$("#CR_PER_POP_SEARCH").click(function() {
	personid = $("#CR_PER_POP_ID").val();
	personName = $("#CR_PER_POP_Name").val();
	$('#resultSecPerson').css('display', 'none');
	$('#noDataPerson').css('display', 'none');
	$('#jsonLoader').css('display', 'block');
	loadLegData();
	//$('#resultSecPerson').css('display', 'block');
});

function loadLegData() {
	$.ajax({
		type: 'POST',
		url: jsonUrlPer,
		dataSrc: '',
		data: {
			"personNumber": personid,
			"name": personName,
			"personId": '',
			"noe": ''
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populatePersonDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populatePersonDataTable(data) {
	$("#PersonAccomodationList").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0) {
		$('#resultSecPerson').css('display', 'none');
		$('#jsonLoader').css('display', 'none');
		$('#noDataPerson').css('display', 'block');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#PersonAccomodationList").dataTable().fnAddData([
				//dat.personId,
				dat.personNumber,
				dat.name,
				dat.noe,
			]);
		}
		$('#resultSecPerson').css('display', 'block');
		$('#noDataPerson').css('display', 'none');
		$('#jsonLoader').css('display', 'none');
	}
}
$(document).on('click', 'ed', function(e) {
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
/************************************************** */
$(document).ready(function() {
	$('#CR_PER_POP_CANCEL').on('click', function(e) {
		$('#id02').css("display", "none");
		$('#resultSecPerson').css("display", "none");
		$('#PersonNumber').children('option[id="1"]').prop('selected', true);
		$('#pname').text("");
	});
	var table = $('#PersonAccomodationList').DataTable();
	$('#PersonAccomodationList tbody').on('click', 'tr', function() {
		var tbldata = $(this).children('td').map(function() {
			return $(this).text();
		}).get();
		if($(this).hasClass('selected')) {
			// $(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
			var dtData = tbldata[1];
			var dtDataId = tbldata[0];
			$('#PersonNumber').val(dtData);
			$('#PersonNumber').children('option[id="2"]').text(dtData);
			$('#PersonNumber').children('option[id="2"]').val(dtDataId);
			$('#PersonNumber').children('option[id="2"]').prop('selected', true);
			$('#pno').text(dtDataId);
			$('#pname').text(dtData);
			$('#personName').val(dtData);
			$('#CR_PER_POP_OK').css('display', 'inline');
			PersonIDs = dtDataId;
		}
	});
	$('#CR_PER_POP_OK').on('click', function(e) {
		$('#id02').css("display", "none");
		$('#resultSecPerson').css("display", "none");
		$('#CR_PER_POP_OK').css("display", "none");
	});
});
/*********************************************************/
function load_salary_details() {
	
	$(`#Salary_Details`).empty();
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/salary_details/" + fin_year + "/" + per_num,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			let tbl = '';
			tbl += '<table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';
			tbl += '  <thead><tr class="w3-theme-l3">';
			tbl += '          <th >S.No</th>';
			tbl += '          <th style="width:8%;">Particular</th>';
			if(data['APR_Basic Salary_Projeted'] === 'undefined' || data['APR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >April</th>';
			} else {
				tbl += '          <th >Projected April</th>';
			}
			if(data['MAY_Basic Salary_Projeted'] === 'undefined' || data['MAY_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >May</th>';
			} else {
				tbl += '          <th >Projected May</th>';
			}
			if(data['JUN_Basic Salary_Projeted'] === 'undefined' || data['JUN_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th>June</th>';
			} else {
				tbl += '          <th >Projected June</th>';
			}
			if(data['JUL_Basic Salary_Projeted'] === 'undefined' || data['JUL_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >July</th>';
			} else {
				tbl += '          <th >Projected July</th>';
			}
			if(data['AUG_Basic Salary_Projeted'] === 'undefined' || data['AUG_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >August</th>';
			} else {
				tbl += '          <th>Projected August</th>';
			}
			if(data['SEP_Basic Salary_Projeted'] === 'undefined' || data['SEP_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >September</th>';
			} else {
				tbl += '          <th >Projected September</th>';
			}
			if(data['OCT_Basic Salary_Projeted'] === 'undefined' || data['OCT_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >October</th>';
			} else {
				tbl += '          <th >Projected September</th>';
			}
			if(data['NOV_Basic Salary_Projeted'] === 'undefined' || data['NOV_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >November</th>';
			} else {
				tbl += '          <th >Projected November</th>';
			}
			if(data['DEC_Basic Salary_Projeted'] === 'undefined' || data['DEC_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >December</th>';
			} else {
				tbl += '          <th >Projected December</th>';
			}
			if(data['JAN_Basic Salary_Projeted'] === 'undefined' || data['JAN_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >January</th>';
			} else {
				tbl += '          <th >Projected January</th>';
			}
			if(data['FEB_Basic Salary_Projeted'] === 'undefined' || data['FEB_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >February</th>';
			} else {
				tbl += '          <th >Projected February</th>';
			}
			if(data['MAR_Basic Salary_Projeted'] === 'undefined' || data['MAR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <th >March</th>';
			} else {
				tbl += '          <th >Projected March</th>';
			}
			tbl += '          <th >Total</th>';
			tbl += '      </tr>';
			tbl += '  </thead>';
			tbl += '  <tbody>';
			/***************************************************************************************************/
			
			if(typeof(data['APR_Basic Salary']) !== "undefined"  ){	
				
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Basic Pay</td>';
			if(data['APR_Basic Salary_Projeted'] === 'undefined' || data['APR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['MAY_Basic Salary_Projeted'] === 'undefined' || data['MAY_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Basic Salary_Projeted'] + '</td>';
			}
			if(data['JUN_Basic Salary_Projeted'] === 'undefined' || data['JUN_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Basic Salary_Projeted'] + '</td>';
			}
			if(data['JUL_Basic Salary_Projeted'] === 'undefined' || data['JUL_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Basic Salary_Projeted'] + '</td>';
			}
			if(data['AUG_Basic Salary_Projeted'] === 'undefined' || data['AUG_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Basic Salary_Projeted'] + '</td>';
			}
			if(data['SEP_Basic Salary_Projeted'] === 'undefined' || data['SEP_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Basic Salary_Projeted'] + '</td>';
			}
			if(data['OCT_Basic Salary_Projeted'] === 'undefined' || data['OCT_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Basic Salary_Projeted'] + '</td>';
			}
			if(data['NOV_Basic Salary_Projeted'] === 'undefined' || data['NOV_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Basic Salary_Projeted'] + '</td>';
			}
			if(data['DEC_Basic Salary_Projeted'] === 'undefined' || data['DEC_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Basic Salary_Projeted'] + '</td>';
			}
			if(data['JAN_Basic Salary_Projeted'] === 'undefined' || data['JAN_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Basic Salary_Projeted'] + '</td>';
			}
			if(data['FEB_Basic Salary_Projeted'] === 'undefined' || data['FEB_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Basic Salary_Projeted'] + '</td>';
			}
			if(data['MAR_Basic Salary_Projeted'] === 'undefined' || data['MAR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Basic Salary_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Basic Salary_Total'] + '</td>';
			tbl += '      </tr>';
			
			Serial_no++;
			}
			/***************************************************************************************************/
			
			if(typeof(data['APR_ARR_Basic Salary']) !== "undefined"  ){	
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Basic Arr</td>';
			if(data['APR_ARR_Basic Salary_Projeted'] === 'undefined' || data['APR_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Basic Salary_Projeted'] === 'undefined' || data['MAY_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Basic Salary_Projeted'] === 'undefined' || data['JUN_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Basic Salary_Projeted'] === 'undefined' || data['JUL_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Basic Salary_Projeted'] === 'undefined' || data['AUG_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Basic Salary_Projeted'] === 'undefined' || data['SEP_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Basic Salary_Projeted'] === 'undefined' || data['OCT_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Basic Salary_Projeted'] === 'undefined' || data['NOV_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Basic Salary_Projeted'] === 'undefined' || data['DEC_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Basic Salary_Projeted'] === 'undefined' || data['JAN_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Basic Salary_Projeted'] === 'undefined' || data['FEB_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Basic Salary_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Basic Salary_Projeted'] === 'undefined' || data['MAR_ARR_Basic Salary_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Basic Salary'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Basic Salary_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Basic Salary_Total'] + '</td>';
			tbl += '      </tr>';
			
			Serial_no++;
			}
			/***************************************************************************************************/
			
			
			if(typeof(data['APR_Grade Pay']) !== "undefined"  ){		
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Grade Pay </td>';
			if(data['APR_Grade Pay_Projeted'] === 'undefined' || data['APR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['MAY_Grade Pay_Projeted'] === 'undefined' || data['MAY_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Grade Pay_Projeted'] + '</td>';
			}
			if(data['JUN_Grade Pay_Projeted'] === 'undefined' || data['JUN_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Grade Pay_Projeted'] + '</td>';
			}
			if(data['JUL_Grade Pay_Projeted'] === 'undefined' || data['JUL_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['AUG_Grade Pay_Projeted'] === 'undefined' || data['AUG_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Grade Pay_Projeted'] + '</td>';
			}
			if(data['SEP_Grade Pay_Projeted'] === 'undefined' || data['SEP_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Grade Pay_Projeted'] + '</td>';
			}
			if(data['OCT_Grade Pay_Projeted'] === 'undefined' || data['OCT_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Grade Pay_Projeted'] + '</td>';
			}
			if(data['NOV_Grade Pay_Projeted'] === 'undefined' || data['NOV_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Grade Pay_Projeted'] + '</td>';
			}
			if(data['DEC_Grade Pay_Projeted'] === 'undefined' || data['DEC_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Grade Pay_Projeted'] + '</td>';
			}
			if(data['JAN_Grade Pay_Projeted'] === 'undefined' || data['JAN_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Grade Pay_Projeted'] + '</td>';
			}
			if(data['FEB_Grade Pay_Projeted'] === 'undefined' || data['FEB_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Grade Pay_Projeted'] + '</td>';
			}
			if(data['MAR_Grade Pay_Projeted'] === 'undefined' || data['MAR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Grade Pay_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Grade Pay_Total'] + '</td>';
			tbl += '      </tr>';
			
			Serial_no++;
			}
			/***************************************************************************************************/
			
			if(typeof(data['APR_ARR_Grade Pay']) !== "undefined"  ){		
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Grade Pay Arr</td>';
			if(data['APR_ARR_Grade Pay_Projeted'] === 'undefined' || data['APR_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Grade Pay_Projeted'] === 'undefined' || data['MAY_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Grade Pay_Projeted'] === 'undefined' || data['JUN_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Grade Pay_Projeted'] === 'undefined' || data['JUL_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Grade Pay_Projeted'] === 'undefined' || data['AUG_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Grade Pay_Projeted'] === 'undefined' || data['SEP_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Grade Pay_Projeted'] === 'undefined' || data['OCT_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Grade Pay_Projeted'] === 'undefined' || data['NOV_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Grade Pay_Projeted'] === 'undefined' || data['DEC_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Grade Pay_Projeted'] === 'undefined' || data['JAN_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Grade Pay_Projeted'] === 'undefined' || data['FEB_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Grade Pay_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Grade Pay_Projeted'] === 'undefined' || data['MAR_ARR_Grade Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Grade Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Grade Pay_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Grade Pay_Total'] + '</td>';
			tbl += '      </tr>';
						Serial_no++;
			}
			/***************************************************************************************************/
			
			if(typeof(data['APR_Personal Pay']) !== "undefined"  ){			
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Personal Pay</td>';
			if(data['APR_Personal Pay_Projeted'] === 'undefined' || data['APR_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Personal Pay_Projeted'] + '</td>';
			}
			if(data['MAY_Personal Pay_Projeted'] === 'undefined' || data['MAY_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Personal Pay_Projeted'] + '</td>';
			}
			if(data['JUN_Personal Pay_Projeted'] === 'undefined' || data['JUN_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Personal Pay_Projeted'] + '</td>';
			}
			if(data['JUL_Personal Pay_Projeted'] === 'undefined' || data['JUL_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Personal Pay_Projeted'] + '</td>';
			}
			if(data['AUG_Personal Pay_Projeted'] === 'undefined' || data['AUG_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Personal Pay_Projeted'] + '</td>';
			}
			if(data['SEP_Personal Pay_Projeted'] === 'undefined' || data['SEP_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Personal Pay_Projeted'] + '</td>';
			}
			if(data['OCT_Personal Pay_Projeted'] === 'undefined' || data['OCT_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Personal Pay_Projeted'] + '</td>';
			}
			if(data['NOV_Personal Pay_Projeted'] === 'undefined' || data['NOV_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Personal Pay_Projeted'] + '</td>';
			}
			if(data['DEC_Personal Pay_Projeted'] === 'undefined' || data['DEC_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Personal Pay_Projeted'] + '</td>';
			}
			if(data['JAN_Personal Pay_Projeted'] === 'undefined' || data['JAN_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Personal Pay_Projeted'] + '</td>';
			}
			if(data['FEB_Personal Pay_Projeted'] === 'undefined' || data['FEB_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Personal Pay_Projeted'] + '</td>';
			}
			if(data['MAR_Personal Pay_Projeted'] === 'undefined' || data['MAR_Personal Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Personal Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Personal Pay_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Personal Pay_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			/***************************************************************************************************/
		
			if(typeof(data['APR_Special Pay']) !== "undefined"  ){			
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Special Pay</td>';
			if(data['APR_Special Pay_Projeted'] === 'undefined' || data['APR_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Special Pay_Projeted'] + '</td>';
			}
			if(data['MAY_Special Pay_Projeted'] === 'undefined' || data['MAY_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Special Pay_Projeted'] + '</td>';
			}
			if(data['JUN_Special Pay_Projeted'] === 'undefined' || data['JUN_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Special Pay_Projeted'] + '</td>';
			}
			if(data['JUL_Special Pay_Projeted'] === 'undefined' || data['JUL_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Special Pay_Projeted'] + '</td>';
			}
			if(data['AUG_Special Pay_Projeted'] === 'undefined' || data['AUG_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Special Pay_Projeted'] + '</td>';
			}
			if(data['SEP_Special Pay_Projeted'] === 'undefined' || data['SEP_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Special Pay_Projeted'] + '</td>';
			}
			if(data['OCT_Special Pay_Projeted'] === 'undefined' || data['OCT_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Special Pay_Projeted'] + '</td>';
			}
			if(data['NOV_Special Pay_Projeted'] === 'undefined' || data['NOV_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Special Pay_Projeted'] + '</td>';
			}
			if(data['DEC_Special Pay_Projeted'] === 'undefined' || data['DEC_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Special Pay_Projeted'] + '</td>';
			}
			if(data['JAN_Special Pay_Projeted'] === 'undefined' || data['JAN_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Special Pay_Projeted'] + '</td>';
			}
			if(data['FEB_Special Pay_Projeted'] === 'undefined' || data['FEB_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Special Pay_Projeted'] + '</td>';
			}
			if(data['MAR_Special Pay_Projeted'] === 'undefined' || data['MAR_Special Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Special Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Special Pay_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Special Pay_Total'] + '</td>';
			tbl += '      </tr>';
			
			Serial_no++;
			}
			/***************************************************************************************************/
			
			if(typeof(data['APR_Dearness Allowance']) !== "undefined"  ){	
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">D A</td>';
			if(data['APR_Dearness Allowance_Projeted'] === 'undefined' || data['APR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_Dearness Allowance_Projeted'] === 'undefined' || data['MAY_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_Dearness Allowance_Projeted'] === 'undefined' || data['JUN_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_Dearness Allowance_Projeted'] === 'undefined' || data['JUL_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_Dearness Allowance_Projeted'] === 'undefined' || data['AUG_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_Dearness Allowance_Projeted'] === 'undefined' || data['SEP_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_Dearness Allowance_Projeted'] === 'undefined' || data['OCT_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_Dearness Allowance_Projeted'] === 'undefined' || data['NOV_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_Dearness Allowance_Projeted'] === 'undefined' || data['DEC_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_Dearness Allowance_Projeted'] === 'undefined' || data['JAN_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_Dearness Allowance_Projeted'] === 'undefined' || data['FEB_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_Dearness Allowance_Projeted'] === 'undefined' || data['MAR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Dearness Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Dearness Allowance_Total'] + '</td>';
			tbl += '      </tr>';
						Serial_no++;
			}
			/***************************************************************************************************/
			
			if(typeof(data['APR_ARR_Dearness Allowance']) !== "undefined"  ){	
			
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">D A Arr</td>';
			if(data['APR_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['APR_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['MAY_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['JUN_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['JUL_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['AUG_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['SEP_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['OCT_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['NOV_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['DEC_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['JAN_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['FEB_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Dearness Allowance_Projeted'] === 'undefined' || data['MAR_ARR_Dearness Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Dearness Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Dearness Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Dearness Allowance_Total'] + '</td>';
			tbl += '      </tr>';
			Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_House Rent Allowance']) !== "undefined"  ){	

			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">H R A</td>';
			if(data['APR_House Rent Allowance_Projeted'] === 'undefined' || data['APR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_House Rent Allowance_Projeted'] === 'undefined' || data['MAY_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_House Rent Allowance_Projeted'] === 'undefined' || data['JUN_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_House Rent Allowance_Projeted'] === 'undefined' || data['JUL_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_House Rent Allowance_Projeted'] === 'undefined' || data['AUG_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_House Rent Allowance_Projeted'] === 'undefined' || data['SEP_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_House Rent Allowance_Projeted'] === 'undefined' || data['OCT_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_House Rent Allowance_Projeted'] === 'undefined' || data['NOV_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_House Rent Allowance_Projeted'] === 'undefined' || data['DEC_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_House Rent Allowance_Projeted'] === 'undefined' || data['JAN_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_House Rent Allowance_Projeted'] === 'undefined' || data['FEB_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_House Rent Allowance_Projeted'] === 'undefined' || data['MAR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_House Rent Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['House Rent Allowance_Total'] + '</td>';
			tbl += '      </tr>';
			
						Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_ARR_House Rent Allowance']) !== "undefined"  ){	
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">H R A Arr</td>';
			if(data['APR_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['APR_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['MAY_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['JUN_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['JUL_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['AUG_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['SEP_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['OCT_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['NOV_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['DEC_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['JAN_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['FEB_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_House Rent Allowance_Projeted'] === 'undefined' || data['MAR_ARR_House Rent Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_House Rent Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_House Rent Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_House Rent Allowance_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_Deputation Allowance']) !== "undefined"  ){	
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Deputaion Allow</td>';
			if(data['APR_Deputation Allowance_Projeted'] === 'undefined' || data['APR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_Deputation Allowance_Projeted'] === 'undefined' || data['MAY_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_Deputation Allowance_Projeted'] === 'undefined' || data['JUN_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_Deputation Allowance_Projeted'] === 'undefined' || data['JUL_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_Deputation Allowance_Projeted'] === 'undefined' || data['AUG_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_Deputation Allowance_Projeted'] === 'undefined' || data['SEP_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_Deputation Allowance_Projeted'] === 'undefined' || data['OCT_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_Deputation Allowance_Projeted'] === 'undefined' || data['NOV_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_Deputation Allowance_Projeted'] === 'undefined' || data['DEC_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_Deputation Allowance_Projeted'] === 'undefined' || data['JAN_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_Deputation Allowance_Projeted'] === 'undefined' || data['FEB_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_Deputation Allowance_Projeted'] === 'undefined' || data['MAR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Deputation Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Deputation Allowance_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_ARR_Deputation Allowance']) !== "undefined"  ){	
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Deputaion Allow Arr</td>';
			if(data['APR_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['APR_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['MAY_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['JUN_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['JUL_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['AUG_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['SEP_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['OCT_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['NOV_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['DEC_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['JAN_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['FEB_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Deputation Allowance_Projeted'] === 'undefined' || data['MAR_ARR_Deputation Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Deputation Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Deputation Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Deputation Allowance_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

			/***************************************************************************************************/
			if(typeof(data['APR_Cafeteria Allowance']) !== "undefined"  ){	

			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Cafe Allow </td>';
			if(data['APR_Cafeteria Allowance_Projeted'] === 'undefined' || data['APR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_Cafeteria Allowance_Projeted'] === 'undefined' || data['MAY_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_Cafeteria Allowance_Projeted'] === 'undefined' || data['JUN_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_Cafeteria Allowance_Projeted'] === 'undefined' || data['JUL_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_Cafeteria Allowance_Projeted'] === 'undefined' || data['AUG_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_Cafeteria Allowance_Projeted'] === 'undefined' || data['SEP_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_Cafeteria Allowance_Projeted'] === 'undefined' || data['OCT_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_Cafeteria Allowance_Projeted'] === 'undefined' || data['NOV_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_Cafeteria Allowance_Projeted'] === 'undefined' || data['DEC_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_Cafeteria Allowance_Projeted'] === 'undefined' || data['JAN_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_Cafeteria Allowance_Projeted'] === 'undefined' || data['FEB_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_Cafeteria Allowance_Projeted'] === 'undefined' || data['MAR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Cafeteria Allowance_Total'] + '</td>';
			tbl += '      </tr>';
				Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_ARR_Cafeteria Allowance']) !== "undefined"  ){	
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Cafe Allow Arr</td>';
			if(data['APR_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['APR_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['MAY_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['JUN_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['JUL_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['AUG_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['SEP_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['OCT_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['NOV_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['DEC_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['JAN_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['FEB_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Cafeteria Allowance_Projeted'] === 'undefined' || data['MAR_ARR_Cafeteria Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Cafeteria Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Cafeteria Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Cafeteria Allowance_Total'] + '</td>';
			tbl += '      </tr>';
				Serial_no++;
			}

			/***************************************************************************************************/
			if(typeof(data['APR_Family Planning Allowance']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Family Plng</td>';
			if(data['APR_Family Planning Allowance_Projeted'] === 'undefined' || data['APR_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_Family Planning Allowance_Projeted'] === 'undefined' || data['MAY_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_Family Planning Allowance_Projeted'] === 'undefined' || data['JUN_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_Family Planning Allowance_Projeted'] === 'undefined' || data['JUL_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_Family Planning Allowance_Projeted'] === 'undefined' || data['AUG_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_Family Planning Allowance_Projeted'] === 'undefined' || data['SEP_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_Family Planning Allowance_Projeted'] === 'undefined' || data['OCT_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_Family Planning Allowance_Projeted'] === 'undefined' || data['NOV_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_Family Planning Allowance_Projeted'] === 'undefined' || data['DEC_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_Family Planning Allowance_Projeted'] === 'undefined' || data['JAN_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_Family Planning Allowance_Projeted'] === 'undefined' || data['FEB_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Family Planning Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_Family Planning Allowance_Projeted'] === 'undefined' || data['MAR_Family Planning Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Family Planning Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Family Planning Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Family Planning Allowance_Total'] + '</td>';
			tbl += '      </tr>';
						Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_ARR_Transport Allowance']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Transport Allow Arr</td>';
			if(data['APR_ARR_Transport Allowance_Projeted'] === 'undefined' || data['APR_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Transport Allowance_Projeted'] === 'undefined' || data['MAY_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Transport Allowance_Projeted'] === 'undefined' || data['JUN_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Transport Allowance_Projeted'] === 'undefined' || data['JUL_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Transport Allowance_Projeted'] === 'undefined' || data['AUG_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Transport Allowance_Projeted'] === 'undefined' || data['SEP_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Transport Allowance_Projeted'] === 'undefined' || data['OCT_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Transport Allowance_Projeted'] === 'undefined' || data['NOV_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Transport Allowance_Projeted'] === 'undefined' || data['DEC_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Transport Allowance_Projeted'] === 'undefined' || data['JAN_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Transport Allowance_Projeted'] === 'undefined' || data['FEB_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Transport Allowance_Projeted'] === 'undefined' || data['MAR_ARR_Transport Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Transport Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Transport Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Transport Allowance_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

			/***************************************************************************************************/
			
			if(typeof(data['APR_Medical Allowance']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Medical Allow </td>';
			if(data['APR_Medical Allowance_Projeted'] === 'undefined' || data['APR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_Medical Allowance_Projeted'] === 'undefined' || data['MAY_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_Medical Allowance_Projeted'] === 'undefined' || data['JUN_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_Medical Allowance_Projeted'] === 'undefined' || data['JUL_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_Medical Allowance_Projeted'] === 'undefined' || data['AUG_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_Medical Allowance_Projeted'] === 'undefined' || data['SEP_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_Medical Allowance_Projeted'] === 'undefined' || data['OCT_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_Medical Allowance_Projeted'] === 'undefined' || data['NOV_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_Medical Allowance_Projeted'] === 'undefined' || data['DEC_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_Medical Allowance_Projeted'] === 'undefined' || data['JAN_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_Medical Allowance_Projeted'] === 'undefined' || data['FEB_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_Medical Allowance_Projeted'] === 'undefined' || data['MAR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Medical Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Medical Allowance_Total'] + '</td>';
			tbl += '      </tr>';
						Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_ARR_Medical Allowance']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Medical Allow Arr</td>';
			if(data['APR_ARR_Medical Allowance_Projeted'] === 'undefined' || data['APR_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Medical Allowance_Projeted'] === 'undefined' || data['MAY_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Medical Allowance_Projeted'] === 'undefined' || data['JUN_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Medical Allowance_Projeted'] === 'undefined' || data['JUL_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Medical Allowance_Projeted'] === 'undefined' || data['AUG_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Medical Allowance_Projeted'] === 'undefined' || data['SEP_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Medical Allowance_Projeted'] === 'undefined' || data['OCT_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Medical Allowance_Projeted'] === 'undefined' || data['NOV_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Medical Allowance_Projeted'] === 'undefined' || data['DEC_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Medical Allowance_Projeted'] === 'undefined' || data['JAN_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Medical Allowance_Projeted'] === 'undefined' || data['FEB_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Medical Allowance_Projeted'] === 'undefined' || data['MAR_ARR_Medical Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Medical Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Medical Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Medical Allowance_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			/***************************************************************************************************/
			
			if(typeof(data['APR_ARR_Consultant Fee']) !== "undefined"  ){
				
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Arr Consultant Fee</td>';
			if(data['APR_ARR_Consultant Fee_Projeted'] === 'undefined' || data['APR_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Consultant Fee_Projeted'] === 'undefined' || data['MAY_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Consultant Fee_Projeted'] === 'undefined' || data['JUN_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Consultant Fee_Projeted'] === 'undefined' || data['JUL_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Consultant Fee_Projeted'] === 'undefined' || data['AUG_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Consultant Fee_Projeted'] === 'undefined' || data['SEP_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Consultant Fee_Projeted'] === 'undefined' || data['OCT_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Consultant Fee_Projeted'] === 'undefined' || data['NOV_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Consultant Fee_Projeted'] === 'undefined' || data['DEC_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Consultant Fee_Projeted'] === 'undefined' || data['JAN_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Consultant Fee_Projeted'] === 'undefined' || data['FEB_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Consultant Fee_Projeted'] === 'undefined' || data['MAR_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Consultant Fee'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Consultant Fee_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Consultant Fee_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_Honorarium Pay']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Honorarium Pay</td>';
			if(data['APR_Honorarium Pay_Projeted'] === 'undefined' || data['APR_ARR_Consultant Fee_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['MAY_Honorarium Pay_Projeted'] === 'undefined' || data['MAY_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['JUN_Honorarium Pay_Projeted'] === 'undefined' || data['JUN_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['JUL_Honorarium Pay_Projeted'] === 'undefined' || data['JUL_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['AUG_Honorarium Pay_Projeted'] === 'undefined' || data['AUG_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['SEP_Honorarium Pay_Projeted'] === 'undefined' || data['SEP_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['OCT_Honorarium Pay_Projeted'] === 'undefined' || data['OCT_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['NOV_Honorarium Pay_Projeted'] === 'undefined' || data['NOV_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['DEC_Honorarium Pay_Projeted'] === 'undefined' || data['DEC_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['JAN_Honorarium Pay_Projeted'] === 'undefined' || data['JAN_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['FEB_Honorarium Pay_Projeted'] === 'undefined' || data['FEB_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['MAR_Honorarium Pay_Projeted'] === 'undefined' || data['MAR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Honorarium Pay_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Honorarium Pay_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

			/***************************************************************************************************/
			if(typeof(data['APR_ARR_Honorarium Pay']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Arr Honorarium Pay</td>';
			if(data['APR_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['APR_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['MAY_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['JUN_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['JUL_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['AUG_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['SEP_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['OCT_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['NOV_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['DEC_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['JAN_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['FEB_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_ARR_Honorarium Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Honorarium Pay_Projeted'] === 'undefined' || data['MAR_ARR_Honorarium Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_ARR_Honorarium Pays'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_ARR_Honorarium Pay_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Honorarium Pay_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_Miscellaneous Payment']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Miscellaneous Payment</td>';
			if(data['APR_Miscellaneous Payment_Projeted'] === 'undefined' || data['APR_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['MAY_Miscellaneous Payment_Projeted'] === 'undefined' || data['MAY_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['JUN_Miscellaneous Payment_Projeted'] === 'undefined' || data['JUN_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['JUL_Miscellaneous Payment_Projeted'] === 'undefined' || data['JUL_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['AUG_Miscellaneous Payment_Projeted'] === 'undefined' || data['AUG_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['SEP_Miscellaneous Payment_Projeted'] === 'undefined' || data['SEP_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['OCT_Miscellaneous Payment_Projeted'] === 'undefined' || data['OCT_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['NOV_Miscellaneous Payment_Projeted'] === 'undefined' || data['NOV_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['DEC_Miscellaneous Payment_Projeted'] === 'undefined' || data['DEC_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['JAN_Miscellaneous Payment_Projeted'] === 'undefined' || data['JAN_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['FEB_Miscellaneous Payment_Projeted'] === 'undefined' || data['FEB_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Miscellaneous Payment_Projeted'] + '</td>';
			}
			if(data['MAR_Miscellaneous Payment_Projeted'] === 'undefined' || data['MAR_Miscellaneous Payment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Miscellaneous Payment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Miscellaneous Payment_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Miscellaneous Payment_Total'] + '</td>';
			tbl += '      </tr>';
						Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_National Holiday Allowance']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%;  text-align:left; color:blue;">National Holiday Allowance</td>';
			if(data['APR_National Holiday Allowance_Projeted'] === 'undefined' || data['APR_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['MAY_National Holiday Allowance_Projeted'] === 'undefined' || data['MAY_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['JUN_National Holiday Allowance_Projeted'] === 'undefined' || data['JUN_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['JUL_National Holiday Allowance_Projeted'] === 'undefined' || data['JUL_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['AUG_National Holiday Allowance_Projeted'] === 'undefined' || data['AUG_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['SEP_National Holiday Allowance_Projeted'] === 'undefined' || data['SEP_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['OCT_National Holiday Allowance_Projeted'] === 'undefined' || data['OCT_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['NOV_National Holiday Allowance_Projeted'] === 'undefined' || data['NOV_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['DEC_National Holiday Allowance_Projeted'] === 'undefined' || data['DEC_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['JAN_National Holiday Allowance_Projeted'] === 'undefined' || data['JAN_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['FEB_National Holiday Allowance_Projeted'] === 'undefined' || data['FEB_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_National Holiday Allowance_Projeted'] + '</td>';
			}
			if(data['MAR_National Holiday Allowance_Projeted'] === 'undefined' || data['MAR_National Holiday Allowance_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_National Holiday Allowance'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_National Holiday Allowance_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['National Holiday Allowance_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			/***************************************************************************************************/
			if(typeof(data['APR_Leave Encashment']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Leave Encashment</td>';
			if(data['APR_Leave Encashment_Projeted'] === 'undefined' || data['APR_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['APR_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['APR_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['MAY_Leave Encashment_Projeted'] === 'undefined' || data['MAY_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAY_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAY_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['JUN_Leave Encashment_Projeted'] === 'undefined' || data['JUN_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUN_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUN_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['JUL_Leave Encashment_Projeted'] === 'undefined' || data['JUL_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JUL_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JUL_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['AUG_Leave Encashment_Projeted'] === 'undefined' || data['AUG_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['AUG_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['AUG_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['SEP_Leave Encashment_Projeted'] === 'undefined' || data['SEP_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['SEP_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['SEP_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['OCT_Leave Encashment_Projeted'] === 'undefined' || data['OCT_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['OCT_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['OCT_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['NOV_Leave Encashment_Projeted'] === 'undefined' || data['NOV_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['NOV_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['NOV_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['DEC_Leave Encashment_Projeted'] === 'undefined' || data['DEC_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['DEC_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['DEC_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['JAN_Leave Encashment_Projeted'] === 'undefined' || data['JAN_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['JAN_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['JAN_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['FEB_Leave Encashment_Projeted'] === 'undefined' || data['FEB_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['FEB_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['FEB_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['MAR_Leave Encashment_Projeted'] === 'undefined' || data['MAR_Leave Encashment_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #d9f7da;">' + data['MAR_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#f9e2d2;">' + data['MAR_Leave Encashment_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Leave Encashment_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			
			
			/***************************************************************************************************/
	/*		if(typeof(data['APR_Total Earning']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Total Earning</td>';
			if(data['APR_Total Earning_Projeted'] === 'undefined' || data['APR_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Total Earning_Projeted'] + '</td>';
			}
			if(data['MAY_Total Earning_Projeted'] === 'undefined' || data['MAY_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Total Earning_Projeted'] + '</td>';
			}
			if(data['JUN_Total Earning_Projeted'] === 'undefined' || data['JUN_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Total Earning_Projeted'] + '</td>';
			}
			if(data['JUL_Total Earning_Projeted'] === 'undefined' || data['JUL_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Total Earning_Projeted'] + '</td>';
			}
			if(data['AUG_Total Earning_Projeted'] === 'undefined' || data['AUG_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Total Deduction_Projeted'] + '</td>';
			}
			if(data['SEP_Total Earning_Projeted'] === 'undefined' || data['SEP_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Total Earning_Projeted'] + '</td>';
			}
			if(data['OCT_Total Earning_Projeted'] === 'undefined' || data['OCT_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Total Earning_Projeted'] + '</td>';
			}
			if(data['NOV_Total Earning_Projeted'] === 'undefined' || data['NOV_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Total Earning_Projeted'] + '</td>';
			}
			if(data['DEC_Total Earning_Projeted'] === 'undefined' || data['DEC_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Total Earning_Projeted'] + '</td>';
			}
			if(data['JAN_Total Earning_Projeted'] === 'undefined' || data['JAN_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Total Earning_Projeted'] + '</td>';
			}
			if(data['FEB_Total Earning_Projeted'] === 'undefined' || data['FEB_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Total Earning_Projeted'] + '</td>';
			}
			if(data['MAR_Total Earning_Projeted'] === 'undefined' || data['MAR_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Total Earning_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Total Earning_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

*/
			
			
		/***************************************************************************************************/
			if(typeof(data['APR_Total Earning']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Gross Salary</td>';
			if(data['APR_Total Earning_Projeted'] === 'undefined' || data['APR_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['APR_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['APR_Total Earning_Projeted'] + '</td>';
			}
			if(data['MAY_Total Earning_Projeted'] === 'undefined' || data['MAY_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['MAY_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['MAY_Total Earning_Projeted'] + '</td>';
			}
			if(data['JUN_Total Earning_Projeted'] === 'undefined' || data['JUN_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['JUN_Leave Encashment'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['JUN_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['JUL_Total Earning_Projeted'] === 'undefined' || data['JUL_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['JUL_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['JUL_Total Earning_Projeted'] + '</td>';
			}
			if(data['AUG_Total Earning_Projeted'] === 'undefined' || data['AUG_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['AUG_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['AUG_Leave Encashment_Projeted'] + '</td>';
			}
			if(data['SEP_Total Earning_Projeted'] === 'undefined' || data['SEP_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['SEP_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['SEP_Total Earning_Projeted'] + '</td>';
			}
			if(data['OCT_Total Earning_Projeted'] === 'undefined' || data['OCT_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['OCT_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['OCT_Total Earning_Projeted'] + '</td>';
			}
			if(data['NOV_Total Earning_Projeted'] === 'undefined' || data['NOV_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['NOV_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['NOV_Total Earning_Projeted'] + '</td>';
			}
			if(data['DEC_Total Earning_Projeted'] === 'undefined' || data['DEC_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['DEC_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['DEC_Total Earning_Projeted'] + '</td>';
			}
			if(data['JAN_Total Earning_Projeted'] === 'undefined' || data['JAN_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['JAN_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['JAN_Total Earning_Projeted'] + '</td>';
			}
			if(data['FEB_Total Earning_Projeted'] === 'undefined' || data['FEB_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['FEB_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['FEB_Total Earning_Projeted'] + '</td>';
			}
			if(data['MAR_Total Earning_Projeted'] === 'undefined' || data['MAR_Total Earning_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #038207;">' + data['MAR_Total Earning'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#038207;">' + data['MAR_Total Earning_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Total Earning_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
			
			
			/***************************************************************************************************/
			if(typeof(data['APR_Empl SPF Contribution']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">EMPL SPF Cont</td>';
			if(data['APR_Empl SPF Contribution_Projeted'] === 'undefined' || data['APR_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['MAY_Empl SPF Contribution_Projeted'] === 'undefined' || data['MAY_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['JUN_Empl SPF Contribution_Projeted'] === 'undefined' || data['JUN_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['JUL_Empl SPF Contribution_Projeted'] === 'undefined' || data['JUL_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['AUG_Empl SPF Contribution_Projeted'] === 'undefined' || data['AUG_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['SEP_Empl SPF Contribution_Projeted'] === 'undefined' || data['SEP_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['OCT_Empl SPF Contribution_Projeted'] === 'undefined' || data['OCT_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['NOV_Empl SPF Contribution_Projeted'] === 'undefined' || data['NOV_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['DEC_Empl SPF Contribution_Projeted'] === 'undefined' || data['DEC_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['JAN_Empl SPF Contribution_Projeted'] === 'undefined' || data['JAN_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['FEB_Empl SPF Contribution_Projeted'] === 'undefined' || data['FEB_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Empl SPF Contribution_Projeted'] + '</td>';
			}
			if(data['MAR_Empl SPF Contribution_Projeted'] === 'undefined' || data['MAR_Empl SPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Empl SPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Empl SPF Contribution_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Empl SPF Contribution_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}


	/***************************************************************************************************/
			if(typeof(data['APR_Empl SNPS Contribution']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">EMPL SNPS Cont</td>';
			if(data['APR_Empl SNPS Contribution_Projeted'] === 'undefined' || data['APR_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['MAY_Empl SNPS Contribution_Projeted'] === 'undefined' || data['MAY_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['JUN_Empl SNPS Contribution_Projeted'] === 'undefined' || data['JUN_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['JUL_Empl SNPS Contribution_Projeted'] === 'undefined' || data['JUL_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['AUG_Empl SNPS Contribution_Projeted'] === 'undefined' || data['AUG_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['SEP_Empl SNPS Contribution_Projeted'] === 'undefined' || data['SEP_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['OCT_Empl SNPS Contribution_Projeted'] === 'undefined' || data['OCT_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['NOV_Empl SNPS Contribution_Projeted'] === 'undefined' || data['NOV_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['DEC_Empl SNPS Contribution_Projeted'] === 'undefined' || data['DEC_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['JAN_Empl SNPS Contribution_Projeted'] === 'undefined' || data['JAN_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['FEB_Empl SNPS Contribution_Projeted'] === 'undefined' || data['FEB_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			if(data['MAR_Empl SNPS Contribution_Projeted'] === 'undefined' || data['MAR_Empl SNPS Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Empl SNPS Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Empl SNPS Contribution_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Empl SNPS Contribution_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}


	/***************************************************************************************************/
			if(typeof(data['APR_Empl Vol PF Contribution']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">EMPL VOL PF Cont</td>';
			if(data['APR_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['APR_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['MAY_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['MAY_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['JUN_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['JUN_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['JUL_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['JUL_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['AUG_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['AUG_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['SEP_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['SEP_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['OCT_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['OCT_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['NOV_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['NOV_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['DEC_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['DEC_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['JAN_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['JAN_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['FEB_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['FEB_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			if(data['MAR_Empl Vol PF Contribution_Projeted'] === 'undefined' || data['MAR_Empl Vol PF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Empl Vol PF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Empl Vol PF Contribution_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Empl Vol PF Contribution_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

	/***************************************************************************************************/
			if(typeof(data['APR_Empl GPF Contribution']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">EMPL  GPF Cont</td>';
			if(data['APR_Empl GPF Contribution_Projeted'] === 'undefined' || data['APR_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['MAY_Empl GPF Contribution_Projeted'] === 'undefined' || data['MAY_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['JUN_Empl GPF Contribution_Projeted'] === 'undefined' || data['JUN_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['JUL_Empl GPF Contribution_Projeted'] === 'undefined' || data['JUL_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['AUG_Empl GPF Contribution_Projeted'] === 'undefined' || data['AUG_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['SEP_Empl GPF Contribution_Projeted'] === 'undefined' || data['SEP_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['OCT_Empl GPF Contribution_Projeted'] === 'undefined' || data['OCT_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['NOV_Empl GPF Contribution_Projeted'] === 'undefined' || data['NOV_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['DEC_Empl GPF Contribution_Projeted'] === 'undefined' || data['DEC_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['JAN_Empl GPF Contribution_Projeted'] === 'undefined' || data['JAN_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['FEB_Empl GPF Contribution_Projeted'] === 'undefined' || data['FEB_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Empl GPF Contribution_Projeted'] + '</td>';
			}
			if(data['MAR_Empl GPF Contribution_Projeted'] === 'undefined' || data['MAR_Empl GPF Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Empl GPF Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Empl GPF Contribution_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Empl GPF Contribution_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

/***************************************************************************************************/
			if(typeof(data['APR_Arrear GPF']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">EMPL Arr GPF</td>';
			if(data['APR_Arrear GPF_Projeted'] === 'undefined' || data['APR_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['MAY_Arrear GPF_Projeted'] === 'undefined' || data['MAY_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['JUN_Arrear GPF_Projeted'] === 'undefined' || data['JUN_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['JUL_Arrear GPF_Projeted'] === 'undefined' || data['JUL_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['AUG_Arrear GPF_Projeted'] === 'undefined' || data['AUG_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['SEP_Arrear GPF_Projeted'] === 'undefined' || data['SEP_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['OCT_Arrear GPF_Projeted'] === 'undefined' || data['OCT_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['NOV_Arrear GPF_Projeted'] === 'undefined' || data['NOV_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['DEC_Arrear GPF_Projeted'] === 'undefined' || data['DEC_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['JAN_Arrear GPF_Projeted'] === 'undefined' || data['JAN_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['FEB_Arrear GPF_Projeted'] === 'undefined' || data['FEB_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Arrear GPF_Projeted'] + '</td>';
			}
			if(data['MAR_Arrear GPF_Projeted'] === 'undefined' || data['MAR_Arrear GPF_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Arrear GPF'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Arrear GPF_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Arrear GPF_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

		/***************************************************************************************************/
			if(typeof(data['APR_Empl GIS Cont']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">EMPL GIS Cont</td>';
			if(data['APR_Empl GIS Cont_Projeted'] === 'undefined' || data['APR_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['MAY_Empl GIS Cont_Projeted'] === 'undefined' || data['MAY_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['JUN_Empl GIS Cont_Projeted'] === 'undefined' || data['JUN_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['JUL_Empl GIS Cont_Projeted'] === 'undefined' || data['JUL_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['AUG_Empl GIS Cont_Projeted'] === 'undefined' || data['AUG_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['SEP_Empl GIS Cont_Projeted'] === 'undefined' || data['SEP_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['OCT_Empl GIS Cont_Projeted'] === 'undefined' || data['OCT_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['NOV_Empl GIS Cont_Projeted'] === 'undefined' || data['NOV_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['DEC_Empl GIS Cont_Projeted'] === 'undefined' || data['DEC_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['JAN_Empl GIS Cont_Projeted'] === 'undefined' || data['JAN_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['FEB_Empl GIS Cont_Projeted'] === 'undefined' || data['FEB_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Empl GIS Cont_Projeted'] + '</td>';
			}
			if(data['MAR_Empl GIS Cont_Projeted'] === 'undefined' || data['MAR_Empl GIS Cont_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Empl GIS Cont_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Empl GIS Cont_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

	       /***************************************************************************************************/
			if(typeof(data['APR_Empl GSLI Contribution']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">EMPL GSLI Cont</td>';
			if(data['APR_Empl GSLI Contribution_Projeted'] === 'undefined' || data['APR_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['MAY_Empl GSLI Contribution_Projeted'] === 'undefined' || data['MAY_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['JUN_Empl GSLI Contribution_Projeted'] === 'undefined' || data['JUN_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['JUL_Empl GSLI Contribution_Projeted'] === 'undefined' || data['JUL_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['AUG_Empl GSLI Contribution_Projeted'] === 'undefined' || data['AUG_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['SEP_Empl GSLI Contribution_Projeted'] === 'undefined' || data['SEP_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Empl GIS Cont'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['OCT_Empl GSLI Contribution_Projeted'] === 'undefined' || data['OCT_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['NOV_Empl GSLI Contribution_Projeted'] === 'undefined' || data['NOV_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['DEC_Empl GSLI Contribution_Projeted'] === 'undefined' || data['DEC_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['JAN_Empl GSLI Contribution_Projeted'] === 'undefined' || data['JAN_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['FEB_Empl GSLI Contribution_Projeted'] === 'undefined' || data['FEB_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			if(data['MAR_Empl GSLI Contribution_Projeted'] === 'undefined' || data['MAR_Empl GSLI Contribution_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Empl GSLI Contribution'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Empl GSLI Contribution_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Empl GSLI Contribution_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}


   /***************************************************************************************************/
			if(typeof(data['APR_HRA Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">HRA Recovery</td>';
			if(data['APR_HRA Recovery_Projeted'] === 'undefined' || data['APR_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_HRA Recovery_Projeted'] === 'undefined' || data['MAY_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_HRA Recovery_Projeted'] === 'undefined' || data['JUN_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_HRA Recovery_Projeted'] === 'undefined' || data['JUL_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_HRA Recovery_Projeted'] === 'undefined' || data['AUG_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_HRA Recovery_Projeted'] === 'undefined' || data['SEP_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_HRA Recovery_Projeted'] === 'undefined' || data['OCT_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_HRA Recovery_Projeted'] === 'undefined' || data['NOV_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_HRA Recovery_Projeted'] === 'undefined' || data['DEC_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_HRA Recovery_Projeted'] === 'undefined' || data['JAN_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_HRA Recovery_Projeted'] === 'undefined' || data['FEB_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_HRA Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_HRA Recovery_Projeted'] === 'undefined' || data['MAR_HRA Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_HRA Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_HRA Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['HRA Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

 /***************************************************************************************************/
			if(typeof(data['APR_Income Tax']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Income Tax</td>';
			if(data['APR_Income Tax_Projeted'] === 'undefined' || data['APR_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Income Tax_Projeted'] + '</td>';
			}
			if(data['MAY_Income Tax_Projeted'] === 'undefined' || data['MAY_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Income Tax_Projeted'] + '</td>';
			}
			if(data['JUN_Income Tax_Projeted'] === 'undefined' || data['JUN_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Income Tax_Projeted'] + '</td>';
			}
			if(data['JUL_Income Tax_Projeted'] === 'undefined' || data['JUL_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Income Tax_Projeted'] + '</td>';
			}
			if(data['AUG_Income Tax_Projeted'] === 'undefined' || data['AUG_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Income Tax_Projeted'] + '</td>';
			}
			if(data['SEP_Income Tax_Projeted'] === 'undefined' || data['SEP_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Income Tax_Projeted'] + '</td>';
			}
			if(data['OCT_Income Tax_Projeted'] === 'undefined' || data['OCT_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Income Tax_Projeted'] + '</td>';
			}
			if(data['NOV_Income Tax_Projeted'] === 'undefined' || data['NOV_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Income Tax_Projeted'] + '</td>';
			}
			if(data['DEC_Income Tax_Projeted'] === 'undefined' || data['DEC_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Income Tax_Projeted'] + '</td>';
			}
			if(data['JAN_Income Tax_Projeted'] === 'undefined' || data['JAN_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Income Tax_Projeted'] + '</td>';
			}
			if(data['FEB_Income Tax_Projeted'] === 'undefined' || data['FEB_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Income Tax_Projeted'] + '</td>';
			}
			if(data['MAR_Income Tax_Projeted'] === 'undefined' || data['MAR_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Income Tax'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Income Tax_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Income Tax_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
 /***************************************************************************************************/
			if(typeof(data['APR_Lease Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Lease Recovery</td>';
			if(data['APR_Lease Recovery_Projeted'] === 'undefined' || data['APR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_Lease Recovery_Projeted'] === 'undefined' || data['MAY_Income Tax_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_Lease Recovery_Projeted'] === 'undefined' || data['JUN_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_Lease Recovery_Projeted'] === 'undefined' || data['JUL_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_Lease Recovery_Projeted'] === 'undefined' || data['AUG_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_Lease Recovery_Projeted'] === 'undefined' || data['SEP_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_Lease Recovery_Projeted'] === 'undefined' || data['OCT_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_Lease Recovery_Projeted'] === 'undefined' || data['NOV_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_Lease Recovery_Projeted'] === 'undefined' || data['DEC_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_Lease Recovery_Projeted'] === 'undefined' || data['JAN_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_Lease Recovery_Projeted'] === 'undefined' || data['FEB_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_Lease Recovery_Projeted'] === 'undefined' || data['MAR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Lease Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Lease Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

 /***************************************************************************************************/
			if(typeof(data['APR_ARR_Lease Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Lease Recovery Arr</td>';
			if(data['APR_ARR_Lease Recovery_Projeted'] === 'undefined' || data['APR_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Lease Recovery_Projeted'] === 'undefined' || data['MAY_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Lease Recovery_Projeted'] === 'undefined' || data['JUN_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Lease Recovery_Projeted'] === 'undefined' || data['JUL_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Lease Recovery_Projeted'] === 'undefined' || data['AUG_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Lease Recovery_Projeted'] === 'undefined' || data['SEP_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Lease Recovery_Projeted'] === 'undefined' || data['OCT_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Lease Recovery_Projeted'] === 'undefined' || data['NOV_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Lease Recovery_Projeted'] === 'undefined' || data['DEC_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Lease Recovery_Projeted'] === 'undefined' || data['JAN_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Lease Recovery_Projeted'] === 'undefined' || data['FEB_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Lease Recovery_Projeted'] === 'undefined' || data['MAR_ARR_Lease Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_ARR_Lease Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_ARR_Lease Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['ARR_Lease Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}


    /***************************************************************************************************/
			if(typeof(data['APR_Veh Excess Use Recv']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Veh Excess Use Recv</td>';
			if(data['APR_Veh Excess Use Recv_Projeted'] === 'undefined' || data['APR_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['MAY_Veh Excess Use Recv_Projeted'] === 'undefined' || data['MAY_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['JUN_Veh Excess Use Recv_Projeted'] === 'undefined' || data['JUN_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['JUL_Veh Excess Use Recv_Projeted'] === 'undefined' || data['JUL_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['AUG_Veh Excess Use Recv_Projeted'] === 'undefined' || data['AUG_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['SEP_Veh Excess Use Recv_Projeted'] === 'undefined' || data['SEP_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['OCT_Veh Excess Use Recv_Projeted'] === 'undefined' || data['OCT_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['NOV_Veh Excess Use Recv_Projeted'] === 'undefined' || data['NOV_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['DEC_Veh Excess Use Recv_Projeted'] === 'undefined' || data['DEC_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['JAN_Veh Excess Use Recv_Projeted'] === 'undefined' || data['JAN_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['FEB_Veh Excess Use Recv_Projeted'] === 'undefined' || data['FEB_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['MAR_Veh Excess Use Recv_Projeted'] === 'undefined' || data['MAR_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Veh Excess Use Recv_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

       /***************************************************************************************************/
			if(typeof(data['APR_Quarter Rent Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Quarter Rent Recovery</td>';
			if(data['APR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['APR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_Quarter Rent Recovery_Projeted'] === 'undefined' || data['MAY_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_Quarter Rent Recovery_Projeted'] === 'undefined' || data['JUN_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_Quarter Rent Recovery_Projeted'] === 'undefined' || data['JUL_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_Quarter Rent Recovery_Projeted'] === 'undefined' || data['AUG_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_Quarter Rent Recovery_Projeted'] === 'undefined' || data['SEP_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_Quarter Rent Recovery_Projeted'] === 'undefined' || data['OCT_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_Quarter Rent Recovery_Projeted'] === 'undefined' || data['NOV_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_Quarter Rent Recovery_Projeted'] === 'undefined' || data['DEC_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_Quarter Rent Recovery_Projeted'] === 'undefined' || data['JAN_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_Quarter Rent Recovery_Projeted'] === 'undefined' || data['FEB_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['MAR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Quarter Rent Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

 /***************************************************************************************************/
			if(typeof(data['APR_ARR_Quarter Rent Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Arr Quarter Rent Recovery</td>';
			if(data['APR_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['APR_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['MAY_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['JUN_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['JUL_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['AUG_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['SEP_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['OCT_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['NOV_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['DEC_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['JAN_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['FEB_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_ARR_Quarter Rent Recovery_Projeted'] === 'undefined' || data['MAR_ARR_Quarter Rent Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_ARR_Quarter Rent Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_ARR_Quarter Rent Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Quarter Rent Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

 /***************************************************************************************************/
			if(typeof(data['APR_Electricity Bill Deduction']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Electricity Bill Deduction</td>';
			if(data['APR_Electricity Bill Deduction_Projeted'] === 'undefined' || data['APR_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['MAY_Electricity Bill Deduction_Projeted'] === 'undefined' || data['MAY_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['JUN_Electricity Bill Deduction_Projeted'] === 'undefined' || data['JUN_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['JUL_Electricity Bill Deduction_Projeted'] === 'undefined' || data['JUL_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['AUG_Electricity Bill Deduction_Projeted'] === 'undefined' || data['AUG_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['SEP_Electricity Bill Deduction_Projeted'] === 'undefined' || data['SEP_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['OCT_Electricity Bill Deduction_Projeted'] === 'undefined' || data['OCT_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['NOV_Electricity Bill Deduction_Projeted'] === 'undefined' || data['NOV_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['DEC_Electricity Bill Deduction_Projeted'] === 'undefined' || data['DEC_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['JAN_Electricity Bill Deduction_Projeted'] === 'undefined' || data['JAN_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['FEB_Electricity Bill Deduction_Projeted'] === 'undefined' || data['FEB_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			if(data['MAR_Electricity Bill Deduction_Projeted'] === 'undefined' || data['MAR_Electricity Bill Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Electricity Bill Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Electricity Bill Deduction_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Electricity Bill Deduction_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

 /***************************************************************************************************/
			if(typeof(data['APR_Miscellaneous Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Miscellaneous Recovery</td>';
			if(data['APR_Miscellaneous Recovery_Projeted'] === 'undefined' || data['APR_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_Miscellaneous Recovery_Projeted'] === 'undefined' || data['MAY_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_Miscellaneous Recovery_Projeted'] === 'undefined' || data['JUN_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_Miscellaneous Recovery_Projeted'] === 'undefined' || data['JUL_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_Miscellaneous Recovery_Projeted'] === 'undefined' || data['AUG_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_Miscellaneous Recovery_Projeted'] === 'undefined' || data['SEP_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_Miscellaneous Recovery_Projeted'] === 'undefined' || data['OCT_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_Miscellaneous Recovery_Projeted'] === 'undefined' || data['NOV_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_Miscellaneous Recovery_Projeted'] === 'undefined' || data['DEC_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_Miscellaneous Recovery_Projeted'] === 'undefined' || data['JAN_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_Miscellaneous Recovery_Projeted'] === 'undefined' || data['FEB_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_Miscellaneous Recovery_Projeted'] === 'undefined' || data['MAR_Miscellaneous Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Miscellaneous Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Miscellaneous Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Miscellaneous Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

 /***************************************************************************************************/
			if(typeof(data['APR_Loan Advance Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Loan Advance Recovery</td>';
			if(data['APR_Loan Advance Recovery_Projeted'] === 'undefined' || data['APR_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_Loan Advance Recovery_Projeted'] === 'undefined' || data['MAY_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_Loan Advance Recovery_Projeted'] === 'undefined' || data['JUN_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_Loan Advance Recovery_Projeted'] === 'undefined' || data['JUL_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_Loan Advance Recovery_Projeted'] === 'undefined' || data['AUG_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_Loan Advance Recovery_Projeted'] === 'undefined' || data['SEP_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_Loan Advance Recovery_Projeted'] === 'undefined' || data['OCT_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_Loan Advance Recovery_Projeted'] === 'undefined' || data['NOV_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_Loan Advance Recovery_Projeted'] === 'undefined' || data['DEC_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_Loan Advance Recovery_Projeted'] === 'undefined' || data['JAN_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_Loan Advance Recovery_Projeted'] === 'undefined' || data['FEB_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Loan Advance Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_Loan Advance Recovery_Projeted'] === 'undefined' || data['MAR_Loan Advance Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Loan Advance Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Loan Advance Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Miscellaneous Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}


         /***************************************************************************************************/
			if(typeof(data['APR_Electricity Charge']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Electricity Charge</td>';
			if(data['APR_Electricity Charge_Projeted'] === 'undefined' || data['APR_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['MAY_Electricity Charge_Projeted'] === 'undefined' || data['MAY_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['JUN_Electricity Charge_Projeted'] === 'undefined' || data['JUN_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['JUL_Electricity Charge_Projeted'] === 'undefined' || data['JUL_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['AUG_Electricity Charge_Projeted'] === 'undefined' || data['AUG_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['SEP_Electricity Charge_Projeted'] === 'undefined' || data['SEP_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['OCT_Electricity Charge_Projeted'] === 'undefined' || data['OCT_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['NOV_Electricity Charge_Projeted'] === 'undefined' || data['NOV_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['DEC_Electricity Charge_Projeted'] === 'undefined' || data['DEC_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['JAN_Electricity Charge_Projeted'] === 'undefined' || data['JAN_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['FEB_Electricity Charge_Projeted'] === 'undefined' || data['FEB_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Electricity Charge_Projeted'] + '</td>';
			}
			if(data['MAR_Electricity Charge_Projeted'] === 'undefined' || data['MAR_Electricity Charge_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Electricity Charge_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Electricity Charge_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}
    /***************************************************************************************************/
			if(typeof(data['APR_Food Recovery']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Food Recovery</td>';
			if(data['APR_Food Recovery_Projeted'] === 'undefined' || data['APR_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Electricity Charge'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Food Recovery_Projeted'] + '</td>';
			}
			if(data['MAY_Food Recovery_Projeted'] === 'undefined' || data['MAY_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Food Recovery_Projeted'] + '</td>';
			}
			if(data['JUN_Food Recovery_Projeted'] === 'undefined' || data['JUN_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Food Recovery_Projeted'] + '</td>';
			}
			if(data['JUL_Food Recovery_Projeted'] === 'undefined' || data['JUL_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Food Recovery_Projeted'] + '</td>';
			}
			if(data['AUG_Food Recovery_Projeted'] === 'undefined' || data['AUG_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Food Recovery_Projeted'] + '</td>';
			}
			if(data['SEP_Food Recovery_Projeted'] === 'undefined' || data['SEP_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Food Recovery_Projeted'] + '</td>';
			}
			if(data['OCT_Food Recovery_Projeted'] === 'undefined' || data['OCT_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Food Recovery_Projeted'] + '</td>';
			}
			if(data['NOV_Food Recovery_Projeted'] === 'undefined' || data['NOV_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Food Recovery_Projeted'] + '</td>';
			}
			if(data['DEC_Food Recovery_Projeted'] === 'undefined' || data['DEC_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Food Recovery_Projeted'] + '</td>';
			}
			if(data['JAN_Food Recovery_Projeted'] === 'undefined' || data['JAN_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Food Recovery_Projeted'] + '</td>';
			}
			if(data['FEB_Food Recovery_Projeted'] === 'undefined' || data['FEB_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Food Recovery_Projeted'] + '</td>';
			}
			if(data['MAR_Food Recovery_Projeted'] === 'undefined' || data['MAR_Food Recovery_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Food Recovery'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Food Recovery_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Food Recovery_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

/***************************************************************************************************/
			if(typeof(data['APR_Veh Excess Use Recv']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Veh Excess Use Recv</td>';
			if(data['APR_Veh Excess Use Recv_Projeted'] === 'undefined' || data['APR_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['APR_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['APR_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['MAY_Veh Excess Use Recv_Projeted'] === 'undefined' || data['MAY_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAY_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAY_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['JUN_Veh Excess Use Recv_Projeted'] === 'undefined' || data['JUN_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUN_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUN_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['JUL_Veh Excess Use Recv_Projeted'] === 'undefined' || data['JUL_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JUL_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JUL_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['AUG_Veh Excess Use Recv_Projeted'] === 'undefined' || data['AUG_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['AUG_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['AUG_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['SEP_Veh Excess Use Recv_Projeted'] === 'undefined' || data['SEP_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['SEP_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['SEP_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['OCT_Veh Excess Use Recv_Projeted'] === 'undefined' || data['OCT_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['OCT_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['OCT_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['NOV_Veh Excess Use Recv_Projeted'] === 'undefined' || data['NOV_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['NOV_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['NOV_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['DEC_Veh Excess Use Recv_Projeted'] === 'undefined' || data['DEC_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['DEC_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['DEC_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['JAN_Veh Excess Use Recv_Projeted'] === 'undefined' || data['JAN_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['JAN_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['JAN_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['FEB_Veh Excess Use Recv_Projeted'] === 'undefined' || data['FEB_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['FEB_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['FEB_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			if(data['MAR_Veh Excess Use Recv_Projeted'] === 'undefined' || data['MAR_Veh Excess Use Recv_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffeb93;">' + data['MAR_Veh Excess Use Recv'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#dcbfff;">' + data['MAR_Veh Excess Use Recv_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Veh Excess Use Recv_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}


/***************************************************************************************************/
			if(typeof(data['APR_Total Deduction']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Total Deduction</td>';
			if(data['APR_Total Deduction_Projeted'] === 'undefined' || data['APR_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['APR_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['APR_Total Deduction_Projeted'] + '</td>';
			}
			if(data['MAY_Total Deduction_Projeted'] === 'undefined' || data['MAY_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['MAY_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['MAY_Total Deduction_Projeted'] + '</td>';
			}
			if(data['JUN_Total Deduction_Projeted'] === 'undefined' || data['JUN_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['JUN_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['JUN_Total Deduction_Projeted'] + '</td>';
			}
			if(data['JUL_Total Deduction_Projeted'] === 'undefined' || data['JUL_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['JUL_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['JUL_Total Deduction_Projeted'] + '</td>';
			}
			if(data['AUG_Total Deduction_Projeted'] === 'undefined' || data['AUG_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['AUG_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['AUG_Total Deduction_Projeted'] + '</td>';
			}
			if(data['SEP_Total Deduction_Projeted'] === 'undefined' || data['SEP_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['SEP_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['SEP_Total Deduction_Projeted'] + '</td>';
			}
			if(data['OCT_Total Deduction_Projeted'] === 'undefined' || data['OCT_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['OCT_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['OCT_Total Deduction_Projeted'] + '</td>';
			}
			if(data['NOV_Total Deduction_Projeted'] === 'undefined' || data['NOV_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['NOV_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['NOV_Total Deduction_Projeted'] + '</td>';
			}
			if(data['DEC_Total Deduction_Projeted'] === 'undefined' || data['DEC_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['DEC_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['DEC_Total Deduction_Projeted'] + '</td>';
			}
			if(data['JAN_Total Deduction_Projeted'] === 'undefined' || data['JAN_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['JAN_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['JAN_Total Deduction_Projeted'] + '</td>';
			}
			if(data['FEB_Total Deduction_Projeted'] === 'undefined' || data['FEB_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['FEB_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['FEB_Total Deduction_Projeted'] + '</td>';
			}
			if(data['MAR_Total Deduction_Projeted'] === 'undefined' || data['MAR_Total Deduction_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #ffb25a;">' + data['MAR_Total Deduction'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#ffb25a;">' + data['MAR_Total Deduction_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Total Deduction_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}

/***************************************************************************************************/
			if(typeof(data['APR_Net Pay']) !== "undefined"  ){
			tbl += '          <td style="width: 3%;">'+Serial_no+'</td>';
			tbl += '          <td style="width:5%; text-align:left; color:blue;">Net Pay</td>';
			if(data['APR_Net Pay_Projeted'] === 'undefined' || data['APR_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['APR_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['APR_Net Pay_Projeted'] + '</td>';
			}
			if(data['MAY_Net Pay_Projeted'] === 'undefined' || data['MAY_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['MAY_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['MAY_Net Pay_Projeted'] + '</td>';
			}
			if(data['JUN_Net Pay_Projeted'] === 'undefined' || data['JUN_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['JUN_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['JUN_Net Pay_Projeted'] + '</td>';
			}
			if(data['JUL_Net Pay_Projeted'] === 'undefined' || data['JUL_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['JUL_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['JUL_Net Pay_Projeted'] + '</td>';
			}
			if(data['AUG_Net Pay_Projeted'] === 'undefined' || data['AUG_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['AUG_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['AUG_Net Pay_Projeted'] + '</td>';
			}
			if(data['SEP_Net Pay_Projeted'] === 'undefined' || data['SEP_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['SEP_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['SEP_Net Pay_Projeted'] + '</td>';
			}
			if(data['OCT_Net Pay_Projeted'] === 'undefined' || data['OCT_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['OCT_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['OCT_Total Deduction_Projeted'] + '</td>';
			}
			if(data['NOV_Net Pay_Projeted'] === 'undefined' || data['NOV_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['NOV_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['NOV_Net Pay_Projeted'] + '</td>';
			}
			if(data['DEC_Net Pay_Projeted'] === 'undefined' || data['DEC_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['DEC_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['DEC_Net Pay_Projeted'] + '</td>';
			}
			if(data['JAN_Net Pay_Projeted'] === 'undefined' || data['JAN_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['JAN_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['JAN_Net Pay_Projeted'] + '</td>';
			}
			if(data['FEB_Net Pay_Projeted'] === 'undefined' || data['FEB_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['FEB_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['FEB_Net Pay_Projeted'] + '</td>';
			}
			if(data['MAR_Net Pay_Projeted'] === 'undefined' || data['MAR_Net Pay_Projeted'] === undefined) {
				tbl += '          <td style="width: 5%;background-color: #89c5ff;">' + data['MAR_Net Pay'] + '</td>';
			} else {
				tbl += '          <td style="width: 5%;background-color:#89c5ff;">' + data['MAR_Net Pay_Projeted'] + '</td>';
			}
			tbl += '          <td style="width: 5%;">' + data['Net Pay_Total'] + '</td>';
			tbl += '      </tr>';
					Serial_no++;
			}


			
			/***************************************************************************************************/
			tbl += '  </tbody>';
			tbl += '</table>';
			Serial_no=1;
			$("#Salary_Details").append(tbl);
			//$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

/******************************************************************************************** */
function load_Declaration_Details() {
	debugger;
	$('#Declaration_Details').empty();
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/declaration_details/" + fin_year + "/" + per_num,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			$("#REPORTS_LOADER").css("display", "block");
			debugger;
			let tbl_dec = '';
			let total_excemption=0;
			total_excemption = 
			  parseFloat(data['Exmp_APR']) + parseFloat(data['Exmp_MAY'])+ parseFloat(data['Exmp_JUN'])+ parseFloat(data['Exmp_JUL'])
			+ parseFloat(data['Exmp_AUG'])+ parseFloat(data['Exmp_SEP'])+ parseFloat(data['Exmp_OCT'])+ parseFloat(data['Exmp_NOV'])
			+ parseFloat(data['Exmp_DEC'])+ parseFloat(data['Exmp_JAN'])+ parseFloat(data['Exmp_FEB'])+ parseFloat(data['Exmp_MAR']);
			if(total_excemption === 'undefined' || isNaN(total_excemption)){
				
			}else {			
			tbl_dec+='             <h4>Calculation of HRA Excemption</h4>';			
			tbl_dec += '          <table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';
			tbl_dec += '          <thead><tr class="w3-theme-l3">';
			tbl_dec += '          <th >S.No</th>';
			tbl_dec += '          <th style="width:8%;">Particular</th>';
			tbl_dec += '          <th >April</th>';
			tbl_dec += '          <th >May</th>';
			tbl_dec += '          <th>June</th>';
			tbl_dec += '          <th >July</th>';
			tbl_dec += '          <th >August</th>';
			tbl_dec += '          <th >September</th>';
			tbl_dec += '          <th >October</th>';
			tbl_dec += '          <th >November</th>';
			tbl_dec += '          <th >December</th>';
			tbl_dec += '          <th >January</th>';
			tbl_dec += '          <th >February</th>';
			tbl_dec += '          <th >March</th>';
			//tbl_dec += '          <th >Total</th>';
			tbl_dec += '      </tr>';
			tbl_dec += '  </thead>';
			tbl_dec += '  <tbody>';
			if(typeof(data['DEC_HRA_APR']) !== "undefined"  ){
			tbl_dec += '  <tr >';
			
			tbl_dec += '          <td style="width: 3%;">1</td>';
			tbl_dec += '          <td style="width:5%; text-align:left; color:blue;">Rent Paid</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_APR'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_MAY'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_JUN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_JUL'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_AUG'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_SEP'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_OCT'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_NOV'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_DEC'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_JAN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_FEB'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['DEC_HRA_MAR'] + '</td>';
			
			tbl_dec += '      </tr>';
			}
			if(typeof(data['Val1_APR']) !== "undefined"  ){
			tbl_dec += '      <tr >';
			tbl_dec += '          <td style="width: 3%;">2</td>';
			tbl_dec += '          <td style="width:5%; text-align:left; color:blue;">Rent Paid - (Basic + DA) x 10%</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_APR'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_MAY'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_JUN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_JUL'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_AUG'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_SEP'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_OCT'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_NOV'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_DEC'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_JAN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_FEB'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val1_MAR'] + '</td>';
	
			tbl_dec += '      </tr>';
			}
			if(typeof(data['Val2_APR']) !== "undefined"  ){
			tbl_dec += '      <tr >';
			tbl_dec += '          <td style="width: 3%;">3</td>';
			tbl_dec += '          <td style="width:5%; text-align:left; color:blue;">(Basic + DA) x 40%</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_APR'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_MAY'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_JUN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_JUL'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_AUG'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_SEP'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_OCT'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_NOV'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_DEC'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_JAN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_FEB'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Val2_MAR'] + '</td>';
			
			
			tbl_dec += '      </tr>';
			}
			if(typeof(data['Exmp_APR']) !== "undefined"  ){
			tbl_dec += '      <tr >';
			tbl_dec += '          <td style="width: 3%;">4</td>';
			tbl_dec += '          <td style="width:5%; text-align:left; color:blue;">Excemption</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_APR'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_MAY'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_JUN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_JUL'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_AUG'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_SEP'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_OCT'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_NOV'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_DEC'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_JAN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_FEB'] + '</td>';
			tbl_dec += '          <td style="width: 5%;background-color: #d9f7da;">' + data['Exmp_MAR'] + '</td>';
		
			tbl_dec += '      </tr>';
			}
			tbl_dec += '  </tbody>';
			tbl_dec += '</table>';
			
			tbl_dec += '<br>';
			 
			
			//tbl_dec += '<h6>Total Excemption : '+total_excemption+'</h6>';
			if(total_excemption === 'undefined' || isNaN(total_excemption))
				tbl_dec += '<h6>Total Excemption : : 0</h6>';
			else
				tbl_dec += '<h6>Total Excemption : : '+Math.round(total_excemption)+'</h6>';
				tbl_dec += '<br>';
			}
			
			
			$("#Declaration_Details").append(tbl_dec);
			//$("#REPORTS_LOADER").css("display", "block");
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

/******************************************************************************************** */
function load_Lease_Declaration_Details() {
	
	$(`#Declaration_Details_lease`).empty();
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/lease_declaration_details/" + fin_year + "/" + per_num + "/" + 41,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			debugger;
			let tbl_dec = '';
			let total_lease=0;
			total_lease = 
			  parseFloat(data['Lease_APR']) + parseFloat(data['Lease_MAY'])+ parseFloat(data['Lease_JUN'])+ parseFloat(data['Lease_JUL'])
			+ parseFloat(data['Lease_AUG'])+ parseFloat(data['Lease_SEP'])+ parseFloat(data['Lease_OCT'])+ parseFloat(data['Lease_NOV'])
			+ parseFloat(data['Lease_DEC'])+ parseFloat(data['Lease_JAN'])+ parseFloat(data['Lease_FEB'])+ parseFloat(data['Lease_MAR']); 
			
			/*if(total_lease === 'undefined' || isNaN(total_lease)){
				
			}else {	*/	
			tbl_dec+='             <h4>Perqusite value RFA (Lease)</h4>';			
			tbl_dec += '          <table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';
			tbl_dec += '          <thead><tr class="w3-theme-l3">';
			tbl_dec += '          <th>S.No</th>';
			tbl_dec += '          <th style="width:8%;">Particular</th>';
			tbl_dec += '          <th>April</th>';
			tbl_dec += '          <th>May</th>';
			tbl_dec += '          <th>June</th>';
			tbl_dec += '          <th>July</th>';
			tbl_dec += '          <th>August</th>';
			tbl_dec += '          <th>September</th>';
			tbl_dec += '          <th>October</th>';
			tbl_dec += '          <th>November</th>';
			tbl_dec += '          <th>December</th>';
			tbl_dec += '          <th>January</th>';
			tbl_dec += '          <th>February</th>';
			tbl_dec += '          <th>March</th>';
			
			tbl_dec += '      </tr>';
			tbl_dec += '  </thead>';
			tbl_dec += '  <tbody>';
			tbl_dec += '  <tr >';
			tbl_dec += '          <td style="width: 3%;">1</td>';
			tbl_dec += '          <td style="width:5%; text-align:left; color:blue;">Lease Perqusite</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_APR'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_MAY'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JUN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JUL'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_AUG'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_SEP'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_OCT'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_NOV'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_DEC'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JAN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_FEB'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_MAR'] + '</td>';
			
			tbl_dec += '      </tr>';			
			tbl_dec += '  </tbody>';
			tbl_dec += '</table>';	
			
			tbl_dec += '<br>';
			
			
			if(total_lease === 'undefined' || isNaN(total_lease))
				tbl_dec += '<h6>Total Perqusite value RFA (Lease) : 0</h6>';
			else
				tbl_dec += '<h6>Total Perqusite value RFA (Lease) : '+Math.round(total_lease)+'</h6>';
			//}
					
			$("#Declaration_Details_lease").append(tbl_dec);
			//$("#REPORTS_LOADER").css("display", "block");
			//$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

/******************************************************************************************** */

/******************************************************************************************** */
function load_Car_Declaration_Details() {
	
	$(`#Declaration_Details_car`).empty();
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/car_declaration_details/" + fin_year + "/" + per_num + "/" + 42,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			
			let tbl_dec = '';
			let total_Car=0;
			total_Car = 
			  parseFloat(data['Lease_APR']) + parseFloat(data['Lease_MAY'])+ parseFloat(data['Lease_JUN'])+ parseFloat(data['Lease_JUL'])
			+ parseFloat(data['Lease_AUG'])+ parseFloat(data['Lease_SEP'])+ parseFloat(data['Lease_OCT'])+ parseFloat(data['Lease_NOV'])
			+ parseFloat(data['Lease_DEC'])+ parseFloat(data['Lease_JAN'])+ parseFloat(data['Lease_FEB'])+ parseFloat(data['Lease_MAR']); 
			
			/*if(total_Car === 'undefined' || isNaN(total_Car)){
				
			}else {	*/
			
			tbl_dec+='             <h4>Perqusite value RFA (Car)</h4>';			
			tbl_dec += '          <table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';
			tbl_dec += '          <thead><tr class="w3-theme-l3">';
			tbl_dec += '          <th>S.No</th>';
			tbl_dec += '          <th style="width:8%;">Particular</th>';
			tbl_dec += '          <th>April</th>';
			tbl_dec += '          <th>May</th>';
			tbl_dec += '          <th>June</th>';
			tbl_dec += '          <th>July</th>';
			tbl_dec += '          <th>August</th>';
			tbl_dec += '          <th>September</th>';
			tbl_dec += '          <th>October</th>';
			tbl_dec += '          <th>November</th>';
			tbl_dec += '          <th>December</th>';
			tbl_dec += '          <th>January</th>';
			tbl_dec += '          <th>February</th>';
			tbl_dec += '          <th>March</th>';
			
			tbl_dec += '      </tr>';
			tbl_dec += '  </thead>';
			tbl_dec += '  <tbody>';
			tbl_dec += '  <tr >';
			tbl_dec += '          <td style="width: 3%;">1</td>';
			tbl_dec += '          <td style="width:5%; text-align:left; color:blue;">Car Perqusite</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_APR'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_MAY'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JUN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JUL'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_AUG'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_SEP'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_OCT'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_NOV'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_DEC'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JAN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_FEB'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_MAR'] + '</td>';
			
			tbl_dec += '      </tr>';			
			tbl_dec += '  </tbody>';
			tbl_dec += '</table>';			
			
			tbl_dec += '<br>';
			
			if(total_Car === 'undefined' || isNaN(total_Car))
				tbl_dec += '<h6>Total Perqusite value RFA (Car) : 0</h6>';
			else
				tbl_dec += '<h6>Total Perqusite value RFA (Car) : '+Math.round(total_Car)+'</h6>';
			// tbl_dec += (typeof total_Car === 'undefined') ? +'<h6>Total Perqusite value RFA (Lease) 0' : parseInt(total_Car)+'</h6>';
			tbl_dec += '<br>';
			//}
			
			$("#Declaration_Details_car").append(tbl_dec);
			//$("#REPORTS_LOADER").css("display", "block");
			//$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

/******************************************************************************************** */

/******************************************************************************************** */
function load_Quarter_Declaration_Details() {
	
	$(`#Declaration_Details_quarter`).empty();
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/quarter_declaration_details/" + fin_year + "/" + per_num + "/" + 43,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			
			let tbl_dec = '';
			let total_Quarter=0;
			total_Quarter = 
			  parseFloat(data['Lease_APR']) + parseFloat(data['Lease_MAY'])+ parseFloat(data['Lease_JUN'])+ parseFloat(data['Lease_JUL'])
			+ parseFloat(data['Lease_AUG'])+ parseFloat(data['Lease_SEP'])+ parseFloat(data['Lease_OCT'])+ parseFloat(data['Lease_NOV'])
			+ parseFloat(data['Lease_DEC'])+ parseFloat(data['Lease_JAN'])+ parseFloat(data['Lease_FEB'])+ parseFloat(data['Lease_MAR']); 
			
			/*if(total_Quarter === 'undefined' || isNaN(total_Quarter)){
				
			}else {	
			*/
			tbl_dec+='             <h4>Perqusite value RFA (Quarter)</h4>';			
			tbl_dec += '          <table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';
			tbl_dec += '          <thead><tr class="w3-theme-l3">';
			tbl_dec += '          <th>S.No</th>';
			tbl_dec += '          <th style="width:8%;">Particular</th>';
			tbl_dec += '          <th>April</th>';
			tbl_dec += '          <th>May</th>';
			tbl_dec += '          <th>June</th>';
			tbl_dec += '          <th>July</th>';
			tbl_dec += '          <th>August</th>';
			tbl_dec += '          <th>September</th>';
			tbl_dec += '          <th>October</th>';
			tbl_dec += '          <th>November</th>';
			tbl_dec += '          <th>December</th>';
			tbl_dec += '          <th>January</th>';
			tbl_dec += '          <th>February</th>';
			tbl_dec += '          <th>March</th>';
			
			tbl_dec += '      </tr>';
			tbl_dec += '  </thead>';
			tbl_dec += '  <tbody>';
			tbl_dec += '  <tr >';
			tbl_dec += '          <td style="width: 3%;">1</td>';
			tbl_dec += '          <td style="width:5%; text-align:left; color:blue;">Quarter Perqusite</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_APR'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_MAY'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JUN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JUL'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_AUG'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_SEP'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_OCT'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_NOV'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_DEC'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_JAN'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_FEB'] + '</td>';
			tbl_dec += '          <td style="width: 5%;">' + data['Lease_MAR'] + '</td>';
			
			tbl_dec += '      </tr>';			
			tbl_dec += '  </tbody>';
			tbl_dec += '</table>';		
			
			tbl_dec += '<br>';
			
			//tbl_dec += '<h6>Total Perqusite value RFA (Quarter) : '+parseInt(total_Quarter)+'</h6>';
			
			if(total_Quarter === 'undefined' || isNaN(total_Quarter))
				tbl_dec += '<h6>Total Perqusite value RFA (Quarter) : 0</h6>';
			else
				tbl_dec += '<h6>Total Perqusite value RFA (Quarter) : '+Math.round(total_Quarter)+'</h6>';
				
			//}
				tbl_dec += '<br>';
			$("#Declaration_Details_quarter").append(tbl_dec);
			
			
			//$("#REPORTS_LOADER").css("display", "block");
			//$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

/******************************************************************************************** */






/******************************************************************************************** */
function load_Declaration_Details2() {
$("#REPORTS_LOADER").css("display", "block");
	$(`#Declaration_Details2`).empty();
	
	$.ajax({
		type: "GET",
		url: "/fin/tax/manage/SectionDescriptionAndAmmount/" + fin_year + "/" + per_num,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
			var dataLength = Object.keys(data).length;
			let tbl_dec1 = '';
			if(dataLength===0){
				
			}else{		
			
			tbl_dec1+='            <h4>Chapter 6A Declaration</h4>'	;		
			tbl_dec1 += '          <table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';			
			tbl_dec1 += '          <thead>';
			tbl_dec1 += '          <tr class="w3-theme-l3">';
			tbl_dec1 += '          <th style="width: 0.5%;">S.No</th>';
			tbl_dec1 += '          <th style="width: 0.5%;">Section</th>';
			tbl_dec1 += '          <th style="width: 2%;">Description</th>';
			tbl_dec1 += '          <th style="width: 2%;">Ammount</th>';
			tbl_dec1 += '          </tr>';
			tbl_dec1 += '          </thead>';
			tbl_dec1 += '          <tbody>';
			 for (var i = 0; i < dataLength; i++) {
            	var dat = data[i];
				if(dat.ammount!='0'){				
				
                tbl_dec1 += '          <tr>';
	            tbl_dec1 += '          <td >'+i+'</td>';
				tbl_dec1 += '          <td style="text-align:left; color:blue;">'+dat.section+'</td>';
				tbl_dec1 += '          <td style="text-align:left">'+dat.description+'</td>';
				tbl_dec1 += '          <td >'+dat.ammount+'</td>';
                tbl_dec1 += '         </tr>';
          		}
       		 }
			
			tbl_dec1 += '  </tbody>';
			tbl_dec1 += '</table>';
			}
			
			
			$("#Declaration_Details2").append(tbl_dec1);
			//$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}