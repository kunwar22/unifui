function myFunction(id) {
	var x = document.getElementById(id);
	if(x.className.indexOf("w3-show") == -1) {
		x.className += " w3-show";
	} else {
		x.className = x.className.replace(" w3-show", "");
	}
}
var fin_year='';
$('#Financial_year').on('change', function(e) {
	fin_year = $("#Financial_year option:selected").text();
	$("#finyear_lbl").val(fin_year);
	//load_tax_report(fin_year);
	//load_perq_tax_report(fin_year);
	//load_TaxCal80c_tax_report(fin_year);
	//load_chapterVIA_tax_report(fin_year);
	load_TotalCal_tax_report(fin_year);
});

function load_tax_report(x) {
	
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/person/tax/manage/TaxCalTotalEarning/" + x,
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
	let tbl='';	
	
	if(data['Basic Salary']!="0.0"){
		tbl+=' <tr>  <td>Basic Salary</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right"> '+data['Basic Salary']+'</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Basic Salary']+'</td>  </tr>'
	}
	if(data['House Rent Allowance']!="0.0"){
		tbl+=' <tr>  <td>House Rent Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['House Rent Allowance']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected House Rent Allowance']+'</td>  </tr>'
	}
	if(data['Dearness Allowanc']!="0.0"){
		tbl+=' <tr>  <td>Dearness Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Dearness Allowance']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Dearness Allowance']+'</td>  </tr>'
	}
	if(data['National Holiday Allowance']!="0.0"){
		tbl+=' <tr>  <td>National Holiday Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['National Holiday Allowance']+'</td>   <td></td> <td></td>  </tr>'
	}
	if(data['Family Planning Allowance']!="0.0"){
		tbl+=' <tr>  <td>Family Planning Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Family Planning Allowance']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Family Planning Allowance']+'</td>  </tr>'
	}
	if(data['Honorarium Pay']!="0.0"){
		tbl+=' <tr>  <td>Honorarium Pay</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Honorarium Pay']+'</td>   <td></td> <td></td>  </tr>'
	}
	if(data['Medical Allowance']!="0.0"){
		tbl+=' <tr>  <td>Medical Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Medical Allowance']+'</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Medical Allowance']+'</td>  </tr>'
	}
	if(data['Miscellaneous Payment']!="0.0"){
		tbl+=' <tr>  <td>Miscellaneous Payment</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Miscellaneous Payment']+'</td>   <td></td> <td></td>  </tr>'
	}
	if(data['Personal Pay']!="0.0"){
		tbl+=' <tr>  <td>Personal Pay</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Personal Pay']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Personal Pay']+'</td>  </tr>'
	}
	if(data['Special Pay']!="0.0"){
		tbl+=' <tr>  <td>Special Pay</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Special Pay']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Special Pay']+'</td>  </tr>'
	}
	if(data['Cafeteria Allowance']!="0.0"){
		tbl+=' <tr>  <td>Cafeteria Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Cafeteria Allowance']+'</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Cafeteria Allowance']+'</td>  </tr>'
	}
	if(data['Medical Benefit']!="0.0"){
		tbl+=' <tr>  <td>Medical Benefit</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Medical Benefit']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Medical Benefit']+'</td>  </tr>'
	}
	if(data['Transport Allowance']!="0.0"){
		tbl+=' <tr>  <td>Transport Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Transport Allowance']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Transport Allowance']+'</td>  </tr>'
	}
	if(data['Deputation Allowance']!="0.0"){
		tbl+=' <tr>  <td>Deputation Allowance</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Deputation Allowance']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Deputation Allowance']+'</td>  </tr>'
	}
	
	
	$("#Tax_REPORT tbody").append(tbl);
	
	
}



/*******************************************************************************************************/
function load_perq_tax_report(x) {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/person/tax/manage/TaxCalTotalPerquisites/" + x,
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
	let tbl='';	
	
    $(`#PERQ_Tax_REPORT tbody`).empty()
	if(data['Perquisites Value - RFA(Lease)']!="0.0"){
		tbl+=' <tr>  <td>Perquisites Value - RFA(Lease)</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Perquisites Value - RFA(Lease)']+'</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Perquisites Value - RFA(Lease)']+'</td>  </tr>'
	}
	if(data['Perquisites Value - Car']!="0.0"){
		tbl+=' <tr>  <td>Perquisites Value - Car</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Perquisites Value - Car']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Perquisites Value - Car']+'</td>  </tr>'
	}
	if(data['Perquisites Value - RFA(Quarter)']!="0.0"){
		tbl+=' <tr>  <td>Perquisites Value - RFA(Quarter)</td>   <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Perquisites Value - RFA(Quarter)']+'</td>    <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Projected Perquisites Value - RFA(Quarter)']+'</td>  </tr>'
	}
	
	
	
	
	$("#PERQ_Tax_REPORT tbody").append(tbl);
	
}


/*******************************************************************************************************/

function load_TaxCal80c_tax_report() {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/person/tax/manage/TaxCal80c",
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
	let tbl='';	
	 
    $(`#TaxCal80c tbody`).empty()
	if(data['Public Provident Fund']!="0.0"){
		tbl+=' <tr>  <td>Public Provident Fund</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Public Provident Fund']+'</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Public Provident Fund']+'</td>  </tr>'
	}	
	 	
	$("#TaxCal80c tbody").append(tbl);
	
}

/*******************************************************************************************************/



function load_chapterVIA_tax_report(x) {
	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/person/tax/manage/chapter6A/"+x,
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
	let tbl='';	
	if(dataLength!=0){
		
		tbl+=' <tr>  <td>80C</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['80C']+'</td>   </tr>'
		tbl+=' <tr>  <td>80D</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['80D']+'</td>   </tr>'
		tbl+=' <tr>  <td>80DD</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['80DD']+'</td>   </tr>'
		tbl+=' <tr>  <td>80DDB</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['80DDB']+'</td>   </tr>'
		tbl+=' <tr>  <td>80E</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['80E']+'</td>   </tr>'
		tbl+=' <tr>  <td>80U</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['80U']+'</td>   </tr>'
		tbl+=' <tr>  <td>Total</td>  <td style="border-left: 1px solid #cdd0d4; text-align: right">'+data['Total']+'</td>   </tr>'
	}
		
	$("#chapterVIA tbody").append(tbl);
	
}



/*******************************************************************************************************/

function load_TotalCal_tax_report(x) {

	$("#REPORTS_LOADER").css("display", "block");
	$.ajax({
		type: "GET",
		url: "/person/tax/manage/TotalCalculation/"+x,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			
			populateTotalCalculationDataTable(result)
			$("#REPORTS_LOADER").css("display", "none");
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
			$("#REPORTS_LOADER").css("display", "none");
		}
	});
}

function populateTotalCalculationDataTable(data) {
	let tbl='';
	$(`#TotalTaxCal`).empty();

	tbl+='<table style="width: 100%;">';
    tbl+='  <thead>';
    tbl+='      <tr style="border-bottom: 1px solid #cdd0d4; padding-left: 16px; padding-right: 16px;">';
    tbl+='          <th style="width: 55%; padding-left: 16px;"><h6 style="margin-bottom: 0px;">Particulars</h6></th>';
    tbl+='          <th style="text-align: right; width: 15%; padding-right: 7px;"><h6 style="margin-bottom: 0px;">Actual</h6></th>';
    tbl+='          <th style="text-align: right; width: 15%; padding-right: 12px;"><h6 style="margin-bottom: 0px;">Projected</h6></th>';
    tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"><h6 style="margin-bottom: 0px;">Total</h6></th>';
    tbl+='      </tr>';
    tbl+='  </thead>';
    tbl+='  <tbody>';
    tbl+='  </tbody>';
    tbl+='</table>';

	tbl+='<button onclick="myFunction(`Demo1`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
    tbl+='  <table style="width: 100%;">';
    tbl+=' 	    <tr style="">';
    tbl+='     	   <td colspan="3">Income Tax Computation For FY 2021-2022</td>';
    tbl+='      </tr>';
    tbl+='  </table>';
    tbl+='</button>';
    tbl+='<div id="Demo1" class="w3-show">';
        // 1.1 Salary (Section 17(1))
        tbl+='<button onclick="myFunction(`Demo2`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo2arr" class="fas fa-angle-double-right"></i> Salary (Section 17(1))</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">'+data['sum_act']+'</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">'+data['sum_tot']+'</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">'+data['sum_prj']+'</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo2" class="w3-hide">';
                tbl+='<table style="width: 100%;">';
                tbl+='  <thead>';
                tbl+='      <tr>';
                tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='      </tr>';
                tbl+='  </thead>';
                tbl+='<tbody>';
                if(data['act_basic_salary']!="0.0" ){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Basic Salary</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+48+',\'Basic Salary (Actual)\',\'A\')"> '+data['act_basic_salary']+' </a> </td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+48+',\'Basic Salary (Projected)\',\'P\')">'+data['tot_basic_salary']+'</a></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Basic Salary']+'</td></tr>';
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
                if(data['act_deputation_allowance']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Deputation Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+1+',\'Deputation Allowance (Actual)\',\'A\')">'+data['act_deputation_allowance']+'</a></td><td style="text-align: right; padding-right: 12px;"><a href="#"style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+1+',\'Deputation Allowance (Projected)\',\'P\')">'+data['tot_deputation_allowance']+'</a></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Deputation Allowance']+'</td></tr>';
                }
                if(data['act_honorarium_pay']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Honorarium Pay</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+60+',\'Honorarium Pay (Actual)\',\'A\')">'+data['act_honorarium_pay']+'</a></td><td style="text-align: right; padding-right: 12px;">--</td><td style="text-align: right; padding-right: 16px;">'+data['act_honorarium_pay']+'</td></tr>';
                }
                if(data['act_medical_allowance']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Medical Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+64+',\'Medical Allowance (Actual)\',\'A\')">'+data['act_medical_allowance']+'</td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+64+',\'Medical Allowance(Projected)\',\'P\')">'+data['tot_medical_allowance']+'</a></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Medical Allowance']+'</td></tr>';
                }
                if(data['act_personal_pay']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Personal Pay</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+70+',\'Personal Pay (Actual)\',\'A\')">'+data['act_personal_pay']+'</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+70+',\'Personal Pay (Projected)\',\'P\')">'+data['tot_personal_pay']+'</a></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Personal Pay']+'</td></tr>';
                }
                if(data['act_special_pay']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Special Pay</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+72+',\'Special Pay (Actual)\',\'A\')">'+data['act_special_pay']+'</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+54+',\'Special Pay (Projected)\',\'P\')">'+data['tot_special_pay']+'</a></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Special Pay']+'</td></tr>';
                }
                if(data['act_national_holiday_allowance']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">National Holiday Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+56+',\'National Holiday Allowance (Actual)\',\'A\')">'+data['act_national_holiday_allowance']+'</a></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['act_national_holiday_allowance']+'</td></tr>';
                }
                if(data['act_family_planning_allowance']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Family Planning Allowance</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+58+',\'Family Planning Allowance (Actual)\',\'A\')">'+data['act_family_planning_allowance']+'</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+58+',\'Family Planning Allowance (Projected)\',\'P\')">'+data['tot_family_planning_allowance']+'</a></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Family Planning Allowance']+'</td></tr>';
                }
                if(data['act_leave_encashment']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Leave Encashment</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+66+',\'Leave Encashment (Actual)\',\'A\')">'+data['act_leave_encashment']+'</a></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['act_leave_encashment']+'</td></tr>';
                }
                if(data['act_miscellaneous_payment']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Miscellaneous Payment</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+68+',\'Miscellaneous Payment (Actual)\',\'A\')">'+data['act_miscellaneous_payment']+'</a></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['act_miscellaneous_payment']+'</td></tr>';
                }
                if(data['act_empr_nps_cont']!="0.0"){
                    tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Employer NPS Contribution</td><td style="text-align: right; padding-right: 7px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+19+',\'Employer NPS Contribution (Actual)\',\'A\')">'+data['act_empr_nps_cont']+'</a></td><td style="text-align: right; padding-right: 12px;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+19+',\'Employer NPS Contribution (Projected)\',\'P\')">'+data['tot_empr_nps_cont']+'</a></td><td style="text-align: right; padding-right: 16px;">'+data['prj_empr_nps_cont']+'</td></tr>';
                }
                tbl+='</tbody>';
                tbl+='</table>';
        tbl+='</div>';

        // 1.2 Value of Perquisites (Section 17(2))
        if(data['sum_prj_perq'] != "0.0"){
        tbl+='<button onclick="myFunction(`Demo3`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo3arr" class="fas fa-angle-double-right"></i> Value of Perquisites (Section 17(2))</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">'+data['sum_prj_perq']+'</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo3" class="w3-hide">';
                tbl+='<table style="width: 100%;">';
                tbl+='  <thead>';
                tbl+='      <tr>';
                tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='      </tr>';
                tbl+='  </thead>';
                tbl+='<tbody>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Perquisites Value - Car</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Perquisites Value - Car']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Perquisites Value - RFA(Lease)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Perquisites Value - RFA(Lease)']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;padding-top: 8px; padding-bottom: 8px;">Perquisites Value - RFA(Quarter)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Projected Perquisites Value - RFA(Quarter)']+'</td></tr>';
                tbl+='</tbody>';
                tbl+='</table>';
        tbl+='</div>';
        }

        // 1.3 Profit in lieu of salary (Section 17(3))
        tbl+='<button onclick="myFunction(`Demo4`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;">Profit in lieu of salary (Section 17(3))</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">0.0</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo4" class="w3-hide">';
        tbl+='</div>';

        // 1.4 Gross Salary
        tbl+='<button onclick="myFunction(`Demo4`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <th style="width: 55%; padding-left: 16px;">Gross Salary</th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;">'+data['gross_salary']+'</th>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo4" class="w3-hide">';
        tbl+='</div>';

        // 1.5 Allowances under Sec 10
        tbl+='<button onclick="myFunction(`Demo5`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo5arr" class="fas fa-angle-double-right"></i> Allowances exempted under Sec 10</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">('+data['Exemption HRA']+')</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo5" class="w3-hide">';
                tbl+='<table style="width: 100%;">';
                tbl+='  <thead>';
                tbl+='      <tr>';
                tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='      </tr>';
                tbl+='  </thead>';
                tbl+='<tbody>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px; padding-top: 8px; padding-bottom: 8px;">Exemption HRA</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Exemption HRA']+'</td></tr>';
                tbl+='</tbody>';
                tbl+='</table>';
        tbl+='</div>';

        // 1.6 Deductions under Sec 16
        tbl+='<button onclick="myFunction(`Demo6`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo6arr" class="fas fa-angle-double-right"></i> Deductions under Sec 16</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right; ">('+data['Standard Deduction']+')</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo6" class="w3-hide">';
                tbl+='<table style="width: 100%;">';
                tbl+='  <thead>';
                tbl+='      <tr>';
                tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='      </tr>';
                tbl+='  </thead>';
                tbl+='<tbody>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px; padding-top: 8px; padding-bottom: 8px;">Standard Deduction</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Standard Deduction']+'</td></tr>';
                tbl+='</tbody>';
                tbl+='</table>';
        tbl+='</div>';

        // 1.7 Income Chargeable Under head Salaries
        tbl+='<button onclick="myFunction(`Demo7`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <th style="width: 55%; padding-left: 16px;">Income Chargeable Under head Salaries</th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;">'+data['income_chargeable_under_heade_salary']+'</th>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo7" class="w3-hide">';

        tbl+='</div>';

        // 1.8 Income from House Property
        if(Math.abs(data['cal_rental_Income_less_30_int']) != "0.0"){
        tbl+='<button onclick="myFunction(`Demo8`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo8arr" class="fas fa-angle-double-right"></i> Income from House Property </td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">('+Math.abs(data['cal_rental_Income_less_30_int'])+')</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo8" class="w3-hide">';
                tbl+='<table style="width: 100%;">';
                tbl+='  <thead>';
                tbl+='      <tr>';
                tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='      </tr>';
                tbl+='  </thead>';
                tbl+='<tbody>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Rental Income</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Rental Income']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Less: Standard Deduction @ 30%</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['cal_rental_Income_less_30']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Less: Interest on capital borrowed U/s 24(b)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Interest on home loan']+'</td></tr>';
                tbl+='</tbody>';
                tbl+='</table>';
        tbl+='</div>';
        }

        // 1.9 Income from other sources
        if(data['cal_total_income_other_sources']!="0.0") {
        tbl+='<button onclick="myFunction(`Demo9`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo9arr" class="fas fa-angle-double-right"></i> Income from other sources</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">'+data['cal_total_income_other_sources']+'</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo9" class="w3-hide">';
                tbl+='<table style="width: 100%;">';
                tbl+='  <thead>';
                tbl+='      <tr>';
                tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                tbl+='      </tr>';
                tbl+='  </thead>';
                tbl+='<tbody>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Interest on FD</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Income from FD']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Interest on RD</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Income from RD']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Saving Bank Interest</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Saving Bank Interest']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Income from Pension</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Income from Pension']+'</td></tr>';
                tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 48px;">Others Income</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['Others']+'</td></tr>';
                tbl+='</tbody>';
                tbl+='</table>';
        tbl+='</div>';
        }

        // 1.10 Gross Total Income
        tbl+='<button onclick="myFunction(`Demo10`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <th style="width: 55%; padding-left: 16px;">Gross Total Income</th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;">'+data['gross_total_income']+'</th>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo10" class="w3-hide">';

        tbl+='</div>';




        // 1.11 Chapter VI A Deductions
        tbl+='<button onclick="myFunction(`Demo11`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <td style="width: 55%; padding-left: 16px;"><i id="Demo11arr" class="fas fa-angle-double-right"></i> Chapter VI A Deductions</td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
        tbl+='     	   <td style="width: 15%; text-align: right;">'+data['Total Chapter6a']+'</td>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div id="Demo11" class="w3-hide">';
            // 1.11.1 Deductions under Sections 80C, 80CCC and 80CCD
            tbl+='<button onclick="myFunction(`Demo12`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
            tbl+='  <table style="width: 100%;">';
            tbl+=' 	    <tr style="">';
            tbl+='     	   <td style="width: 55%; padding-left: 32px;"><i id="Demo12arr" class="fas fa-angle-double-right"></i> Deductions under Sections 80C, 80CCC and 80CCD</td>';
            tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
            tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
            tbl+='     	   <td style="width: 15%; text-align: right;">'+data['ded_80c_80ccc_80ccd']+'</td>';
            tbl+='      </tr>';
            tbl+='  </table>';
            tbl+='</button>';
            tbl+='<div id="Demo12" class="w3-hide">';
                // 1.11.1.1 Deductions under Section 80C and 80CCC
                tbl+='<button onclick="myFunction(`Demo13`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 64px;"><i id="Demo13arr" class="fas fa-angle-double-right"></i> Deductions under Section 80C and 80CCC</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80C']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div id="Demo13" class="w3-hide">';
                    tbl+='<table style="width: 100%;">';
                    tbl+='  <thead>';
                    tbl+='      <tr>';
                    tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                    tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                    tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                    tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                    tbl+='      </tr>';
                    tbl+='  </thead>';
                        tbl+='<tbody>';
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
                        tbl+='</tbody>';
                    tbl+='</table>';
                tbl+='</div>';


                // 1.11.1.2 Deductions under Section 80CCD
                tbl+='<button onclick="myFunction(`Demo14`)" class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 64px;"><i id="Demo14arr" class="fas fa-angle-double-right"></i> Deductions under Section 80CCD</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['ded_80ccd']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div id="Demo14" class="w3-hide">';
                    tbl+='<table style="width: 100%;">';
                    tbl+='  <thead>';
                    tbl+='      <tr>';
                    tbl+='          <th style="width: 55%; padding-left: 16px;"></th>';
                    tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                    tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                    tbl+='          <th style="text-align: right; width: 15%; padding-right: 16px;"></th>';
                    tbl+='      </tr>';
                    tbl+='  </thead>';
                        tbl+='<tbody>';
                            tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">Deductions under Section 80CCD(1B)</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['80CCD-1B']+'</td></tr>';
                            tbl+='<tr style="border-bottom: 1px solid #becbd2;"><td style="padding-left: 96px; padding-top: 8px; padding-bottom: 8px;">Deductions under Section 80CCD2</td><td style="text-align: right; padding-right: 7px;"></td><td style="text-align: right; padding-right: 12px;"></td><td style="text-align: right; padding-right: 16px;">'+data['prj_empr_nps_cont']+'</td></tr>';
                        tbl+='</tbody>';
                    tbl+='</table>';
                tbl+='</div>';
            tbl+='</div>';

            // 1.11.2 Deductions under Section 80D
            if(data['80D'] != "0.0"){
                tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80D</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80D']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div class="w3-hide">';
                tbl+='</div>';
            }


            // 1.11.2 Deductions under Section 80DD
            if(data['80DD'] != "0.0"){
                tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80DD</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80DD']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div class="w3-hide">';
                tbl+='</div>';
            }


            // 1.11.2 Deductions under Section 80DDB
            if(data['80DDB'] != "0.0"){
                tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80DDB</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80DDB']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div class="w3-hide">';
                tbl+='</div>';
            }


            // 1.11.2 Deductions under Section 80E
            if(data['80E'] != "0.0"){
                tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80E</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80E']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div class="w3-hide">';
                tbl+='</div>';
            }


            // 1.11.2 Deductions under Section 80U
            if(data['80U'] != "0.0"){
                tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80U</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80U']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div class="w3-hide">';
                tbl+='</div>';
            }


            // 1.11.2 Deductions under Section 80TTA
            if(data['80TTA'] != "0.0" && data['80TTA'] != undefined){
                tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80TTA</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80TTA']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div class="w3-hide">';
                tbl+='</div>';
            }

            // 1.11.2 Deductions under Section 80TTB
            if(data['80TTB'] != "0.0" && data['80TTB'] != undefined){
                tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
                tbl+='  <table style="width: 100%;">';
                tbl+=' 	    <tr style="">';
                tbl+='     	   <td style="width: 55%; padding-left: 32px;">Deductions under Section 80TTB</td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;"></td>';
                tbl+='     	   <td style="width: 15%; text-align: right;">'+data['80TTB']+'</td>';
                tbl+='      </tr>';
                tbl+='  </table>';
                tbl+='</button>';
                tbl+='<div class="w3-hide">';
                tbl+='</div>';
            }
        tbl+='</div>';

        // 1.11.2 Total Income
        tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <th style="width: 55%; padding-left: 16px;">Total Taxable Income</th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;">'+data['total_taxable_income']+'</th>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div class="w3-hide">';
        tbl+='</div>';

        // 1.11.2 Total Tax Payable
        tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <th style="width: 55%; padding-left: 16px;">Total Income Tax Payable</th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;">'+data['Tax on Total Income']+'</th>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div class="w3-hide">';
        tbl+='</div>';

        // 1.11.2 Total Tax deducted Till Date
        tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <th style="width: 55%; padding-left: 16px;">Tax Deducted Till Date</th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"><a href="#" style=" text-decoration:none; color:blue;" onclick="return taxamountdetails('+80+',\'Tax Deducted Till Date (Actual)\',\'A\')">'+data['Income Tax Till Date']+'</a></th>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div class="w3-hide">';
        tbl+='</div>';

        // 1.11.2 Balance Tax
        tbl+='<button class="w3-button w3-block w3-sand w3-left-align" style="border-bottom: 1px solid #cdd0d4;">';
        tbl+='  <table style="width: 100%;">';
        tbl+=' 	    <tr style="">';
        tbl+='     	   <th style="width: 55%; padding-left: 16px;">Balance Tax</th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;"></th>';
        tbl+='     	   <th style="width: 15%; text-align: right;">'+data['Balance Tax']+'</th>';
        tbl+='      </tr>';
        tbl+='  </table>';
        tbl+='</button>';
        tbl+='<div class="w3-hide">';
        tbl+='</div>';
    tbl+='</div>';

	$("#TotalTaxCal").append(tbl);
	
}




/********************************* */

function taxamountdetails(x, y,z) {
$("#REPORTS_LOADER").css("display", "block");	
	$(`#Tax_REPORT`).empty();
	var tbl='';
	$.ajax({
		type: "GET",
		url: "/person/tax/manage/manageTaxCalElementDataByYear/" + fin_year + "/" + x+ "/" + z,
		contentType: "application/json",
		dataType: "json",
		success: function(data) {
		$("#REPORTS_LOADER").css("display", "none");	
		    $('#headdingid').text(y);
			$('#TaxDetailsPopup').css('display', 'block');
			
			tbl+='<table border="1" style="width: 100%;" class="w3-centered w3-table-all compact">';
			    tbl+='  <thead><tr class="w3-theme-l3">';			 
			    tbl+='          <th style="width: 8.33%;">April</th>';
			    tbl+='          <th style="width: 8.33%;">May</th>';
			    tbl+='          <th style="width: 8.33%;">June</th>';
				tbl+='          <th style="width: 8.33%;">July</th>';
				tbl+='          <th style="width: 8.33%;">August</th>';
				tbl+='          <th style="width: 8.33%;">September</th>';
				tbl+='          <th style="width: 8.33%;">October</th>';
				tbl+='          <th style="width: 8.33%;">November</th>';
				tbl+='          <th style="width: 8.33%;">December</th>';
				tbl+='          <th style="width: 8.33%;">January</th>';
				tbl+='          <th style="width: 8.33%;">February</th>';
				tbl+='          <th style="width: 8.33%;">March</th>';
																	 
			    tbl+='      </tr>';                                  
			    tbl+='  </thead>';                                   
																	 
			    tbl+='  <tbody>';                                    
				tbl+='          <td style="width: 8.33%;">'+data['APR']+'</td>';
			    tbl+='          <td style="width: 8.33%;">'+data['MAY']+'</td>';
			    tbl+='          <td style="width: 8.33%;">'+data['JUN']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['JUL']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['AUG']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['SEP']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['OCT']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['NOV']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['DEC']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['JAN']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['FEB']+'</td>';
				tbl+='          <td style="width: 8.33%;">'+data['MAR']+'</td>';

			    tbl+='  </tbody>';
			    tbl+='</table>';		
				$("#Tax_REPORT").append(tbl);
		},
		error: function(e) {
			$("#REPORTS_LOADER").css("display", "block");
			console.log("ERROR : " + JSON.stringify(e));			
			
		}
	});
}
