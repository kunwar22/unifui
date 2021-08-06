var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}



$('#EMPL_TAB_CANCEL_BTN').on('click', function(e) {
	var url = "/employee_edit/manageemployeedetail/z/z/z";
	$('#div_replace').load(url);
});
$('#MGR_CANCEL_BTN').on('click', function(e) {
	var url = "/employee_edit/manageemployeedetail/z/z/z";
	$('#div_replace').load(url);
});

$('#ADDITIONAL_CANCEL_BTN').on('click', function(e) {
	var url = "/employee_edit/manageemployeedetail/z/z/z";
	$('#div_replace').load(url);
});

$('#POP_DATE').on('change', function(e) {
	$("#POP_SUBMIT_BTN").css("display", "inline-block");
});

$('#POP_CANCEL').on('click', function(e) {
	$('#UPDATE_POPUP').css("display", "none");
	$('#EDIT_EMPL_TAB').children('option[id="edit"]').prop('selected', true);
});


$('#POP_CANCEL_BANK').on('click', function(e){
	$('#addrpopup').css("display","none");
});

$('#POP_EFF_DATE').on('change', function(e) {
	$("#POP_SUBMIT").css("display", "inline-block");
});
function editbank(dex, name) {
	//debugger;
	var mode = $("#EDITBANKTAB_" + dex).val();
	var bankaccountid = $("#bankaccountsids").val();


	$.ajax({
		type: 'GET',
		url: "/employee_edit/getbankdetailsUpdate/" + bankaccountid,
		dataSrc: '',
		dataType: 'json',
		success: function(data) {

			$('#personnumber_popBank').val(data.personnumber);
			$('#personid_popBank').val(data.personid);
			$('#bankaccountsid_popBank').val(data.bankaccountsid);
			$('#status_popBank').val(data.status);


			if (mode == "Correct") {

				$("#BANK_DTL_FORM_" + dex + " :input").prop("disabled", false);
				$("#BANK_CANCEL_BTN" + dex).css("display", "inline-block");
				$("#BANK_SUBMIT_BTN" + dex).css("display", "inline-block");

			} else if (mode == "Update") {
				$("#BANK_DTL_FORM_" + dex + " :input").prop("disabled", true);
				$("#BANK_CANCEL_BTN" + dex).css("display", "none");
				$("#BANK_SUBMIT_BTN" + dex).css("display", "none");
				$("#EDITBANKTAB_" + dex).prop("disabled", false);

				$('#addrpopup').css('display', 'block');
				$("#POP_EFF_DATE").prop("disabled", false);

			}
		}, error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
	/*bankcancel
	editbank
	EDITBANKTAB_
	BANK_DTL_FORM_
	BANK_CANCEL_BTN
	BANK_SUBMIT_BTN*/

}

function bankcancel(dexx) {
	var url = "/employee_edit/manageemployeedetail/z/z/z";
	$('#div_replace').load(url);
}


function bankcorrectupdate(dexxx) {
	/*$("#HIRE_DATE").prop("disabled",false);
	$("#paygroup").val($("#PAYGRP_LOV option:selected").text());
*/
	var fd = $("#BANK_DTL_FORM_" + dexxx).serialize();
	$.ajax({
		url: "/employee_edit/addcorrectbank",
		type: "POST",
		data: fd,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(result) {
			//alert(result);
			$('#div_replace').html(result);
			bootbox.alert(response.status + "\n" + response.message);
			/* bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: response.message
							callback: function() {
								$('#replace_div').load("/reimbursement/transportManager");
							}
						});*/
		},
		error: function(response) {
			alert(JSON.parse(response.responseText));
		}
	});

}




/*
$('#POP_CANCEL_BTN').on('click', function(e){
	$('#addrpopup').css("display","none");
});
*/


/***************************  CODE FOR VIEW HISTORY START  *****************************/
var d_length='';
function viewHistory() {
	$("#VIEW_HISTORY_POPUP").css("display", "block");
	var jsonUrl_VH = '/employee_edit/viewhistory';
	$.ajax({
		type: 'GET',
		url: jsonUrl_VH,
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populateVHDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});


	function populateVHDataTable(data) {
		$("#VHList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		d_length= Object.keys(data).length;
		if (dataLength == 0) {
			$('#vhresultSec').css('display', 'none');
			$('#noDataVH').css('display', 'block');
		} else {
			for (var i = 0; i < dataLength; i++) {
				var dat = data[i];
				
				
				$("#VHList").dataTable().fnAddData([
					"<ed onclick='vhLoad(this.id);' id='VH_REF" + i + "' em='/employee_edit/manageemployeedetail/z/" + dat.actionid + "/" + dat.effectivestartdate + "' style='cursor: pointer;color:blue;'>" + dat.effectivestartdate + "</ed>",
					dat.effectiveenddate,
					dat.hireactiondsc,
					//dat.hirereasondsc,
					dat.rateofpay,
					dat.departmentsdsc,
					dat.positionnumberdsc,
					dat.natureofemployementdsc,
				]);
				
			}
			$('#vhresultSec').css('display', 'block');
			
			$('#noDataVH').css('display', 'none');
		}
	}
}
$('#VH_POP_CANCEL').on('click', function(e) {
	$("#VIEW_HISTORY_POPUP").css("display", "none");
});

function vhLoad(id) {
	
	var url = $('#' + id).attr("em");
	//alert(url);
	//$('#EMPL_DISPLAY').css('display', 'block');
	$('#div_replace').load(url);
	$('#tabdisplay').css('display', 'block');
	
}

/*$('#VH_REF').on('click', function(e){
	var url = $(this).attr("em");
	alert(url);
		$('#div_replace').load(url);
});*/

/***************************   CODE FOR VIEW HISTORY END   *****************************/

/**********************CODE FOR OPEN CURRENT TAB AFTER EDIT***********************/
var effdt_check='';
$(document).ready(function() {
	//$('#EMPL_DISPLAY').css('display', 'none');
	effdt_check=$('#EMP_END_DATE').val();
    viewHistory();
	openTab(tabname_);
	//alert($('#EMP_END_DATE').val());
	////debugger;

	if ($('#EMP_END_DATE').val() == "4712-12-31" || $('#EMP_END_DATE').val() == "4712-12-30") {
		$('#EMP_END_DATE').val("");
		//$('#EMP_END_DATE').prop("disabled",true);
	}
	//$(tabname).addClass("w3-theme");
	
	
	
	//debugger;
	$('#VHList').DataTable({
    
    "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
	for (var i = 0; i < d_length; i++) {
		  if (aData[i] == effdt_check) {
		    $('td', nRow).css('background-color', '#5eff24');
		  } 
	}
    }
  });
		
		
});

var buId = '';
var depid = '';
var datasetId = $("#BU_SET_ID_").val();
var hiredate = "";
var legVal = "";



/******************************************************************/

/*********************CODE FOR SWITCHING TABS*********************/
var mode = "";

function openTab(legal) {
	var i, x, tablinks;
	x = document.getElementsByClassName("legal");
	for (i = 0; i < x.length; i++) {
		x[i].style.display = "none";
	}
	tablinks = document.getElementsByClassName("tablink");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
	}
	document.getElementById(legal).style.display = "block";
	$('button[name =\"' + legal + '\"]').addClass("w3-theme");

}

var hiredate = $("#HIRE_DATE").val();
var probperiod = $("#PROB_INPUT").val();
//alert("PROB"+probperiod);
//alert(hiredate+" :::: "+probperiod);
if (probperiod != "" && probperiod != undefined && probperiod != null) {
	var d = new Date(hiredate);
	d.setDate(d.getDate() + parseInt(probperiod));
	d = d.toISOString().substring(0, 10);
	//alert(":::: "+d);
	$("#COMP_PROB_END_DATE").val(d);
}

/******************************************************************/

$('#EDIT_EMPL_TAB').on('change', function(e) {
	////debugger;
	if ($("#EDIT_EMPL_TAB option:selected").val() == "Correct") {
		$("#EMPLOYEE_DETAILS :input").prop("disabled", false);
		$("#COMP_EFFDT").prop("readonly", true);
		$("#HIRE_DATE").prop("disabled", true);
		$("#ASSGN_NUM").prop("disabled", true);
		$("#EMP_END_DATE").prop("disabled", true);
		$('#HIRE_ACTION').prop("disabled", true);
		$('#ACTION_REASON').prop("disabled", true);
		$("#EMP_EDIT_EFFDT").prop("disabled", true);
		$("#legalEmployer").prop("disabled", true);
		$("#businessUnit").prop("disabled", true);
		$("#FTE_INPUT").prop("disabled", true);
		$("#HEAD_CNT_INPUT").prop("disabled", true);
		$("#EMPL_TAB_SUBMIT_BTN").css("display", "inline-block");
		$("#EMPL_TAB_CANCEL_BTN").css("display", "inline-block");
	}
	if ($("#EDIT_EMPL_TAB option:selected").val() == "Update") {
		$('#UPDATE_POPUP').css('display', 'block');
		$("#EMPLOYEE_DETAILS :input").prop("disabled", false);
		$("#COMP_EFFDT").prop("readonly", true);
		$("#HIRE_DATE").prop("disabled", true);
		$("#ASSGN_NUM").prop("disabled", true);
		$("#EMP_END_DATE").prop("disabled", true);
		$('#HIRE_ACTION').prop("disabled", true);
		$('#ACTION_REASON').prop("disabled", true);
		$("#EMP_EDIT_EFFDT").prop("disabled", true);
		$("#FTE_INPUT").prop("disabled", true);
		$("#HEAD_CNT_INPUT").prop("disabled", true);
		$("#EMPL_TAB_SUBMIT_BTN").css("display", "inline-block");
		$("#EMPL_TAB_CANCEL_BTN").css("display", "inline-block");
	}
});

function setupdatedetails() {
	var actionval = $("#POP_HIRE_ACTION option:selected").val();
	var actiontext = $("#POP_HIRE_ACTION option:selected").text();
	var reasonval = $("#POP_ACTION_REASON option:selected").val();
	var reasontext = $("#POP_ACTION_REASON option:selected").text();
	var date = $("#POP_DATE").val();

	if (actionval == "18") {
		$("#EMP_END_DATE").prop("disabled", false);
	}

	$('#HIRE_ACTION').children('option[id="1"]').text(actiontext);
	$('#HIRE_ACTION').children('option[id="1"]').val(actionval);
	$('#HIRE_ACTION').children('option[id="1"]').prop('selected', true);

	$('#ACTION_REASON').children('option[id="1"]').text(reasontext);
	$('#ACTION_REASON').children('option[id="1"]').val(reasonval);
	$('#ACTION_REASON').children('option[id="1"]').prop('selected', true);

	$('#EMP_EDIT_EFFDT').val(date);

	$('#HIRE_ACTION').prop("disabled", true);
	$('#ACTION_REASON').prop("disabled", true);
	$('#EMP_EDIT_EFFDT').prop("disabled", true);

	$('#UPDATE_POPUP').css('display', 'none');
	//$('#EDIT_EMPL_TAB').children('option[id="edit"]').prop('selected',true);	

}

function correctupdateEMPL() {
	$("#FTE_INPUT").prop("disabled", false);
	$("#HEAD_CNT_INPUT").prop("disabled", false);
	$("#HIRE_DATE").prop("disabled", false);
	$("#ASSGN_NUM").prop("disabled", false);
	$('#HIRE_ACTION').prop("disabled", false);
	$('#ACTION_REASON').prop("disabled", false);
	$("#EMP_EDIT_EFFDT").prop("disabled", false);
	$("#legalEmployer").prop("disabled", false);
	$("#businessUnit").prop("disabled", false);
	$("#paygroup").val($("#PAYGRP_LOV option:selected").text());

	var fd = $("#EMPLOYEE_DETAILS").serialize();
	$.ajax({
		url: "/employee_edit/correctupdateEMPL",
		type: "POST",
		data: fd,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(result) {
			//alert(result);
			$('#div_replace').html(result);
			bootbox.alert(response.status + "\n" + response.message);
			/* bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: response.message
							callback: function() {
								$('#replace_div').load("/reimbursement/transportManager");
							}
						});*/
		},
		error: function(response) {
			alert(JSON.parse(response.responseText));
		}
	});

}

/******************************************************************/

function legempl() {
	////debugger;
	var selectObject = $('#legalEmployer').children("option:selected").val();
	if (selectObject == 'search') {
		$('#id05').css("display", "block");

	}
	else if (selectObject != 'search') {
		$('#id05').css("display", "none");
	}
}




$(document).ready(function() {

	$('#btnsearchLegal').on('click', function(e) {
		$('#id05').css("display", "block");

	});

	$('#LEG_POP_OK').on('click', function(e) {
		$('#legresultSec').css('display', 'none');
		$('#id05').css("display", "none");
		$("#LEGAL_NAME").val("");
		$("#LEGAL_COUNTRY").val("");
		$('#LEGAL_ENTITY_IDENTIFIER').val("");
	});

	$('#LEG_POP_CANCEL').on('click', function(e) {
		$('#id05').css("display", "none");
		$('#legresultSec').css('display', 'none');
		$('#legalEmployer').children('option[id="1"]').prop('selected', true);
		$("#LEGAL_NAME").val("");
		$("#LEGAL_COUNTRY").val("");
		$('#LEGAL_ENTITY_IDENTIFIER').val("");
	});

	var table = $('#LegalList').DataTable();

	$('#LegalList tbody').on('click', 'tr', function() {

		var tbldata = $(this).children('td').map(function() {
			return $(this).text();

		}).get();

		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData = tbldata[0];
		var dtDataId = tbldata[6];
		$('#legalEmployer').val(dtData);
		$('#legalEmployer').children('option[id="2"]').text(dtData);
		$('#legalEmployer').children('option[id="2"]').val(dtDataId);
		$('#legalEmployer').children('option[id="2"]').prop('selected', true);
		legVal = dtData;

	});
});

var jsonUrlLEG = '/enterprisesetup/searchLegalEntity/getLegalEntityId';

var legName = '';
var legCountry = '';
var legEntityIdentifier = '';

$("#LEGAL_SEARCH").on('click', function(e) {
	legName = $("#LEGAL_NAME").val();
	legCountry = $("#LEGAL_COUNTRY").val();
	legEntityIdentifier = $('#LEGAL_ENTITY_IDENTIFIER').val();
	loadLegData();

});

function loadLegData() {
	$.ajax({
		type: 'POST',
		url: jsonUrlLEG,
		dataSrc: '',
		data: {
			"COUNTRY": legCountry,
			"ENTITY": legEntityIdentifier,
			"NAME": legName
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populateLegDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateLegDataTable(data) {
	$("#LegalList").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if (dataLength == 0) {
		$('#legresultSec').css('display', 'none');
		$('#noDataLEG').css('display', 'block');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#LegalList").dataTable().fnAddData([
				dat.name,
				dat.country,
				dat.legalentityidentifier,
				dat.effectstartdate,
				dat.placeofregistration,
				dat.registrationnumber,
				dat.legalentityid

			]);

		}
		$('#legresultSec').css('display', 'block');
		$('#noDataLEG').css('display', 'none');
	}
}
/************************************************************************/

$('#HIRE_ACTION').on('change', function() {
	var selectHireActionId = $(this).children("option:selected").val();
	var jsonUrlAcReason = '/newperson/actionreasonbind/' + selectHireActionId;

	newStatebind = "";
	$.ajax({
		type: 'GET',
		url: jsonUrlAcReason,
		dataSrc: '',
		dataType: 'json',
		success: function(data) {
			data.forEach(function(n) {
				newStatebind += '<option value="' + n.id + '" >' + n.description + '</option>'
			});
			$('#ACTION_REASON').html(newStatebind);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
});

$('#POP_HIRE_ACTION').on('change', function() {
	var selectHireActionId = $(this).children("option:selected").val();
	var jsonUrlAcReason = '/newperson/actionreasonbind/' + selectHireActionId;

	var hirereason = "";
	$.ajax({
		type: 'GET',
		url: jsonUrlAcReason,
		dataSrc: '',
		dataType: 'json',
		success: function(data) {			
			data.forEach(function(n) {
				hirereason += '<option value="' + n.id + '" >' + n.description + '</option>'
			});
			$('#POP_ACTION_REASON').html(hirereason);
			
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
});
/**************************************************************************/
function buchange() {
	////debugger;
	//$('#id03').css("display","none");
	var selectObject = $('#businessUnit').children("option:selected").val();
	if (selectObject == 'search') {
		$('#id03').css("display", "block");

	} else if (selectObject != 'search') {
		$('#id03').css("display", "none");
	}
}


var jsonUrlBU = '/enterprisesetup/searchBU';

var searchdsCode = '';
var searchdsName = '';
//$("#BU_SEARCH").on('click', function(e){
function Busearch() {
	searchdsCode = $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	//searchstatus=$('#statusList').children("option:selected").val();
	loadBUData();
}

function loadBUData() {
	$.ajax({
		type: 'POST',
		url: jsonUrlBU,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"statusBU": "Active"
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populateBUDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateBUDataTable(data) {
	$("#BU_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if (dataLength == 0) {
		$('#buresultSec').css('display', 'none');
		$('#noDataBU').css('display', 'block');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#BU_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.dataset,
				dat.status,
				dat.businessunitid,
				dat.datasetid
			]);
		}
		$('#buresultSec').css('display', 'block');
		$('#noDataBU').css('display', 'none');
	}
}

$(document).ready(function() {


	var table = $('#BU_LIST').DataTable();
	//$('#BU_LIST tbody').off('click')
	$('#BU_LIST tbody').on('click', 'tr', function() {
		////debugger;
		//alert("Inside table");
		var tbldata = $(this).children('td').map(function() {
			return $(this).text();

		}).get();

		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData = tbldata[0];
		var dtDataId = tbldata[4];
		buId = dtDataId;
		datasetId = tbldata[5];
		$('#businessUnit').val(dtData);
		$('#businessUnit').children('option[id="2"]').text(dtData);
		$('#businessUnit').children('option[id="2"]').val(dtDataId);
		$('#businessUnit').children('option[id="2"]').prop('selected', true);
		$('#BU_POP_OK').css("display", "inline-block");

	});

});

$('#btnsearch2').on('click', function(e) {
	$('#id03').css("display", "block");

});


$('#BU_POP_CANCEL').on('click', function(e) {
	$('#id03').css("display", "none");
	$('#businessUnit').children('option[id="1"]').prop('selected', true);
	$('#buresultSec').css('display', 'none');
	$('#BU_POP_OK').css("display", "none");
	$("#SRCH_CODE").val("");
	$("#SRCH_NAME").val("");
});

$('#BU_POP_OK').on('click', function(e) {
	$('#buresultSec').css('display', 'none');
	$('#id03').css("display", "none");
	//alert("Data Set ID :::::: "+datasetId+"  BU ID:::: "+buId);
	$("#SRCH_CODE").val("");
	$("#SRCH_NAME").val("");
	$('#POS_LOV').prop("disabled", false);
	//$('#JOBS_LOV').prop("disabled",false);
	$('#DEPT_LOV').prop("disabled", false);
	$('#POS_LOV').children('option[id="1"]').prop('selected', true);
	$('#JOBS_LOV').children('option[id="1"]').prop('selected', true);
	$('#DEPT_LOV').children('option[id="1"]').prop('selected', true);
	$('#buresultSec').css('display', 'none');
	$('#BU_POP_OK').css("display", "none");

});

/********************************************************************/
$('#DEPT_LOV').on('change', function() {

	var selectObject = $(this).children("option:selected").val();
	if (selectObject == 'search') {

		$('#id02').css("display", "block");
		$('#DEPT_DATASET').val(datasetId);

	}
	else if (selectObject != 'search') {
		$('#id02').css("display", "none");
	}
});


$(document).ready(function() {

	$('#DEPT_SEARCH').on('click', function(e) {

		$('#id02').css("display", "block");
		DEPT_DATASET = $('#DEPT_DATASET').val();
		searchdsCode = $("#DEPT_CODE").val();
		searchdsName = $("#DEPT_NAME").val();
		//searchstatus=$('#DEPT_STATUS').children("option:selected").val();
		loadDEPTBUData();

	});

	$('#CR_DEP_POP_OK').on('click', function(e) {
		$('#deptresultSec').css('display', 'none');
		$('#id02').css("display", "none");
		$('#DEPT_DATASET').val("");
		$("#DEPT_CODE").val("");
		$("#DEPT_NAME").val("");

	});
	$('#CR_ENT_DEP_CANCEL').on('click', function(e) {
		$('#id02').css("display", "none");
		$('#deptresultSec').css('display', 'none');
		$('#DEPT_LOV').children('option[id="1"]').prop('selected', true);
		$('#DEPT_DATASET').val("");
		$("#DEPT_CODE").val("");
		$("#DEPT_NAME").val("");
	});


	var table = $('#DEPTBU_LIST').DataTable();

	$('#DEPTBU_LIST tbody').on('click', 'tr', function() {

		var tbldata = $(this).children('td').map(function() {
			return $(this).text();

		}).get();

		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData = tbldata[0];
		var dtDataId = tbldata[5];
		//alert(dtDataId);
		depid = dtDataId;
		$('#DEPT_LOV').val(dtData);
		$('#DEPT_LOV').children('option[id="2"]').text(dtData);
		$('#DEPT_LOV').children('option[id="2"]').val(dtDataId);
		$('#DEPT_LOV').children('option[id="2"]').prop('selected', true);


	});

});

function loadDEPTBUData() {
	var jsonUrldeptbu = '/enterprisesetup/position/getDepartmentByBUId';
	$.ajax({
		type: 'POST',
		url: jsonUrldeptbu,
		data: {
			"name": searchdsName,
			"code": searchdsCode,
			"dataSets": DEPT_DATASET,
			"status": "Active"
		},
		dataSrc: '',
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populateDEPTBUDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function populateDEPTBUDataTable(data) {
	$("#DEPTBU_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if (dataLength == 0) {
		$('#deptresultSec').css('display', 'none');
		$('#noDataDEPT').css('display', 'block');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#DEPTBU_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.effectstartdate,
				dat.dataset,
				dat.status,
				dat.departmentsid
			]);
		}
		$('#deptresultSec').css('display', 'block');
		$('#noDataDEPT').css('display', 'none');
	}
}



/*******************************Popup table selected row END*******************************************/

$('#POS_LOV').on('change', function() {

	var selectObject = $(this).children("option:selected").val();
	if (selectObject == 'search') {
		$('#id07').css("display", "block");


	}
	else if (selectObject != 'search') {
		$('#id07').css("display", "none");
	}
});


$(document).ready(function() {


	$('#OPTIONSEARCH').on('click', function(e) {
		$('#id07').css("display", "block");

	});

	$('#POS_POP_OK1').on('click', function(e) {
		loadPosDataByPosId();
		$('#posresultSec1').css('display', 'none');
		$('#id07').css("display", "none");
		$("#POS_SRCH_CODE").val("");
		$("#POS_SRCH_NAME").val("");

		//alert("Position ID:: "+posId);
	});

	$('#POS_POP_CANCEL1').on('click', function(e) {
		$('#id07').css("display", "none");
		$('#posresultSec1').css('display', 'none');
		$('#POS_LOV').children('option[id="1"]').prop('selected', true);
		$("#POS_SRCH_CODE").val("");
		$("#POS_SRCH_NAME").val("");
	});

	var table = $('#POS_LIST1').DataTable();

	$('#POS_LIST1 tbody').on('click', 'tr', function() {

		var tbldata = $(this).children('td').map(function() {
			return $(this).text();

		}).get();

		if ($(this).hasClass('selected')) {
		//	$(this).removeClass('selected');

		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData = tbldata[0];
		var dtDataId = tbldata[6];
		posId = dtDataId;
		$('#POS_LOV').val(dtData);
		$('#POS_LOV').children('option[id="2"]').text(dtData);
		$('#POS_LOV').children('option[id="2"]').val(dtDataId);
		$('#POS_LOV').children('option[id="2"]').prop('selected', true);

	});

});

var searchdsCode = '';
var searchdsName = '';
var searchstatus = '';

$("#POS_SEARCH").on('click', function(e) {

	searchdsCode = $("#POS_SRCH_CODE").val();
	searchdsName = $("#POS_SRCH_NAME").val();
	searchstatus = $('#SRCH_STATUS').children("option:selected").val();

	loadPosData();

});
function loadPosData() {

	var buId1 = $('#businessUnit').children("option:selected").val();

	var depid1 = $('#DEPT_LOV').children("option:selected").val();

	var jsonUrlpos = '/newperson/posbind';

	$.ajax({
		type: 'POST',
		url: jsonUrlpos,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"status": "Active",
			"buId": datasetId,                ///////*******buID is DATASET ID OF BU ******//////
			"deptid": depid1
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populatePOSDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function populatePOSDataTable(data) {
	$("#POS_LIST1").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if (dataLength == 0) {
		$('#posresultSec1').css('display', 'none');
		$('#noDataPOS1').css('display', 'block');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#POS_LIST1").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.departmentsname,
				dat.jobsname,
				dat.status,
				dat.effectstartdate,
				dat.positionid
			]);
		}
		$('#posresultSec1').css('display', 'block');
		$('#noDataPOS1').css('display', 'none');
	}
}

/**************************SETTING FIELDS ON POSITION SELECTED START*********************************/
function loadPosDataByPosId() {
	var jsonUrlposById = '/enterprisesetup/possearchbyid/' + posId;
	$.ajax({
		type: 'GET',
		url: jsonUrlposById,
		dataSrc: '',
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			setPosDataOnFields(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function setPosDataOnFields(data) {
	//	alert("hi");
	$('#DEPT_LOV').val(data.departmentsname);
	$('#DEPT_LOV').children('option[id="2"]').text(data.departmentsname);
	$('#DEPT_LOV').children('option[id="2"]').val(data.departmentsid);
	$('#DEPT_LOV').children('option[id="2"]').prop('selected', true);
	$('#DEPT_LOV').prop("disabled", false);

	$('#JOBS_LOV').val(data.jobsname);
	$('#JOBS_LOV').children('option[id="2"]').text(data.jobsname);
	$('#JOBS_LOV').children('option[id="2"]').val(data.jobsid);
	$('#JOBS_LOV').children('option[id="2"]').prop('selected', true);
	$('#JOBS_LOV').prop("disabled", false);


	/*$('#CR_ENT_LOC_CODE').val(data.locationname);
	$('#CR_ENT_LOC_CODE').children('option[id="2"]').text(data.locationname);
	$('#CR_ENT_LOC_CODE').children('option[id="2"]').val(data.locationid);
	$('#CR_ENT_LOC_CODE').children('option[id="2"]').prop('selected',true);	
	$('#CR_ENT_LOC_CODE').prop("disabled",false);*/

	$('#ASSIGN_CAT').val(data.assignmentcategoryname);
	$('#ASSIGN_CAT').children('option[id="2"]').text(data.assignmentcategoryname);
	$('#ASSIGN_CAT').children('option[id="2"]').val(data.assignmentcategory);
	$('#ASSIGN_CAT').children('option[id="2"]').prop('selected', true);
	$('#ASSIGN_CAT').prop("disabled", false);

	$('#REGorTEMP_LOV').val(data.regulartemporaryname);
	$('#REGorTEMP_LOV').children('option[id="2"]').text(data.regulartemporaryname);
	$('#REGorTEMP_LOV').children('option[id="2"]').val(data.regulartemporary);
	$('#REGorTEMP_LOV').children('option[id="2"]').prop('selected', true);
	$('#REGorTEMP_LOV').prop("disabled", false);

	$('#FULL_PART_TIME_LOV').val(data.fulltimeparttimename);
	$('#FULL_PART_TIME_LOV').children('option[id="2"]').text(data.fulltimeparttimename);
	$('#FULL_PART_TIME_LOV').children('option[id="2"]').val(data.fulltimeparttime);
	$('#FULL_PART_TIME_LOV').children('option[id="2"]').prop('selected', true);
	$('#FULL_PART_TIME_LOV').prop("disabled", false);

	$('#HEAD_CNT_INPUT').val(data.headcount);
	$('#HEAD_CNT_INPUT').prop("disabled", false);

	$('#FTE_INPUT').val(data.fte);
	$('#FTE_INPUT').prop("disabled", false);

	$('#PROBPERIOD_LOV').val(data.probationperiodname);
	$('#PROBPERIOD_LOV').children('option[id="2"]').text(data.probationperiodname);
	if (data.probationuom != null && data.probationuom != "" && data.probationuom != undefined) {
		$('#PROBPERIOD_LOV').children('option[id="2"]').val(data.probationuom);
	} else {
		$('#PROBPERIOD_LOV').children('option[id="2"]').val(0);
	}
	$('#PROBPERIOD_LOV').children('option[id="2"]').prop('selected', true);
	$('#PROBPERIOD_LOV').prop("disabled", false);

	$('#NOTICEPERIOD_LOV').val(data.noticeperiodtypename);
	$('#NOTICEPERIOD_LOV').children('option[id="2"]').text(data.noticeperiodtypename);
	$('#NOTICEPERIOD_LOV').children('option[id="2"]').val(data.noticeperiodtype);
	$('#NOTICEPERIOD_LOV').children('option[id="2"]').prop('selected', true);
	$('#NOTICEPERIOD_LOV').prop("disabled", false);

	$('#PROB_INPUT').val(data.probationuom);
	$('#PROB_INPUT').prop("disabled", false);

	$('#NOTICE_INPUT').val(data.noticeperiod);
	$('#NOTICE_INPUT').prop("disabled", false);

	var hire_date = $("#HIRE_DATE").val();
	var prob_period = $("#PROB_INPUT").val();
	//alert("PROB"+prob_period);
	//alert(hire_date+" :::: "+prob_period);
	if (prob_period != "" && prob_period != undefined && prob_period != null) {
		var d = new Date(hire_date);
		d.setDate(d.getDate() + parseInt(prob_period));
		d = d.toISOString().substring(0, 10);
		//alert(":::: "+d);
		$("#COMP_PROB_END_DATE").val(d);
	}

}

/**************************SETTING FIELDS ON POSITION SELECTED END***********************************/
$('#JOBS_LOV').on('change', function() {

	var selectObject = $(this).children("option:selected").val();
	if (selectObject == 'search') {
		loadJOBSBUData();
		$('#id09').css("display", "block");


	}
	else if (selectObject != 'search') {
		$('#id09').css("display", "none");
	}
});


$(document).ready(function() {

	$('#OPTIONSEARCHJOBSBU').on('click', function(e) {
		$('#id09').css("display", "block");

	});

	$('#JOBSBU_POP_OK').on('click', function(e) {
		$('#jobsburesultSec').css('display', 'none');
		$('#id09').css("display", "none");

	});
	$('#JOBSBU_POP_CANCEL').on('click', function(e) {
		$('#id09').css("display", "none");
		$('#jobsburesultSec').css('display', 'none');
		$('#JOBS_LOV').children('option[id="1"]').prop('selected', true);
	});

	var table = $('#JOBSBU_LIST').DataTable();

	$('#JOBSBU_LIST tbody').on('click', 'tr', function() {

		var tbldata = $(this).children('td').map(function() {
			return $(this).text();

		}).get();

		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData = tbldata[0];
		var dtDataId = tbldata[5];

		$('#JOBS_LOV').val(dtData);
		$('#JOBS_LOV').children('option[id="2"]').text(dtData);
		$('#JOBS_LOV').children('option[id="2"]').val(dtDataId);
		$('#JOBS_LOV').children('option[id="2"]').prop('selected', true);

	});

});


function loadJOBSBUData() {
	var jsonUrljobsbu = '/enterprisesetup/jobbind/' + datasetId;
	$.ajax({
		type: 'GET',
		url: jsonUrljobsbu,
		dataSrc: '',
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populateJOBSBUDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function populateJOBSBUDataTable(data) {
	$("#JOBSBU_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if (dataLength == 0) {
		$('#jobsburesultSec').css('display', 'none');
		$('#noDataJOBSBU').css('display', 'block');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#JOBSBU_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.effectstartdate,
				dat.dataset,
				dat.status,
				dat.jobsid
			]);
		}
		$('#jobsburesultSec').css('display', 'block');
		$('#noDataJOBSBU').css('display', 'none');
	}
}
/*************************************************************/
function gradechange() {
	////debugger;
	//$('#id03').css("display","none");
	var selectObject = $('#gradesLOV').children("option:selected").val();
	if (selectObject == 'search') {
		$('#id06').css("display", "block");

	} else if (selectObject != 'search') {
		$('#id06').css("display", "none");
	}
}

var jsonUrlGrade = '/enterprisesetup/searchGrade';

var searchdsCode = '';
var searchdsName = '';
var searchstatus = '';

$("#GRADE_SEARCH").on('click', function(e) {

	searchdsCode = $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	//searchstatus=$('#statusList').children("option:selected").val();
	loadGradeData()

});
function loadGradeData() {
	$.ajax({
		type: 'POST',
		url: jsonUrlGrade,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"statusGrade": "Active"
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populateGradeDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateGradeDataTable(data) {
	$("#GRADE_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if (dataLength == 0) {
		$('#graderesultSec').css('display', 'none');
		$('#noDataGrade').css('display', 'block');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#GRADE_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.status,
				dat.datasets,
				dat.gradesid
			]);
		}
		$('#graderesultSec').css('display', 'block');
		$('#noDataGrade').css('display', 'none');
	}
}

/************************POPUP TABLE SELECTED ROW START***********************/



$(document).ready(function() {

	$('#btnsearch6').on('click', function(e) {
		$('#id06').css("display", "block");

	});

	$('#GRADE_POP_OK').on('click', function(e) {
		$('#graderesultSec').css('display', 'none');
		$('#id06').css("display", "none");
		$("#SRCH_CODE").val("");
		$("#SRCH_NAME").val("");

	});

	$('#GRADE_POP_CANCEL').on('click', function(e) {
		$('#id06').css("display", "none");
		$('#graderesultSec').css('display', 'none');
		$('#gradeSearchLOV').children('option[id="1"]').prop('selected', true);
		$("#SRCH_CODE").val("");
		$("#SRCH_NAME").val("");
	});

	var table = $('#GRADE_LIST').DataTable();

	$('#GRADE_LIST tbody').on('click', 'tr', function() {
		var tbldata = $(this).children('td').map(function() {
			return $(this).text();

		}).get();

		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData = tbldata[0];
		var dtDataId = tbldata[4];
		$('#gradesLOV').val(dtData);
		$('#gradesLOV').children('option[id="2"]').text(dtData);
		$('#gradesLOV').children('option[id="2"]').val(dtDataId);
		$('#gradesLOV').children('option[id="2"]').prop('selected', true);
		stepbind();

	});

});

function stepbind() {
	////debugger;
	//alert("hello country "+countryList);
	var category = $('#gradesLOV').val();
	//alert("Grades Id"+category);
	var jsonUrl1 = '/employee_edit/gradestepbind/' + category;

	newStatebind = "";
	$.ajax({
		type: 'GET',
		url: jsonUrl1,
		dataSrc: '',

		dataType: 'json',
		success: function(data) {
			//alert("Inside Success");
			newStatebind += '<option value="" disabled selected></option>';
			data.forEach(function(n) {
				newStatebind += '<option value="' + n.gradestepid + '" amt="' + n.stepamount + '" >' + n.stepname + '</option>';
			});

			//$('#divStateDisp').css("display","none");
			$('#COMP_GRADE_STEP').html(newStatebind);




			//populateDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

$('#COMP_GRADE_STEP').on('change', function() {
	var amount = $("#COMP_GRADE_STEP").children("option:selected").attr("amt");
	$('#COMP_SALARY_AMT').val(amount);
});



/**************************************************************/

function addrowmanager() {
	////debugger;
	var date = new Date();
	date = date.toISOString().substring(0, 10);
	var data = "";
	data = '<tr><td style="width:5%"><input  name="actionid"  id="actionid" value="0" type="hidden" /><input name="personid" id="personid"  type="hidden" /><input name="personnumber" id="personnumber" value="' + pnum + '" type="hidden" /><input name="managertypeid" id="managertypeid" value="0" type="hidden" /><input value="' + date + '" name="managerdetails[1].effectivestartdate" id="effectivestartdate" type="hidden" /><select id="MGR' + window.globalCounter + '" onchange="mgrchange(' + window.globalCounter + ')" class="w3-select w3-border" name="managertypes[' + window.globalCounter + '].supervisorname"><option id="1" value="" selected disabled></option><option id="2" value="" selected disabled></option><option value="search" data-toggle="modal" id="btnsearchLegal">Search....</option></select></td><td style="width:30%;"><input class="w3-input w3-border"  name="managertypes[' + window.globalCounter + '].supervisorid" type="text"></td><td style="width:30%"><select id="CR_HR_MGR_TYPE' + window.globalCounter + '" class="w3-select w3-border" name="managertypes[' + window.globalCounter + '].managertype">' + $('#CR_HR_MGR_TYPE').html() + '</select></td><td style="width:5%"><input class="w3-btn w3-theme" id="deletemgr"  type="button" value="x"/></td></tr>';
	$("#MGR_DET_TBL tbody").append(data);

	$.each($('#MGR_DET_TBL tr'), function(index, val) {
		////debugger;
		$(this).find("td:eq(0) input[id='actionid']").attr('name', 'contactdetails[' + (index - 1) + '].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name', 'managerdetails[' + (index - 1) + '].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name', 'managerdetails[' + (index - 1) + '].personnumber');
		$(this).find("td:eq(0) input[id='managertypeid']").attr('name', 'managerdetails[' + (index - 1) + '].managertypeid');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name', 'managerdetails[' + (index - 1) + '].effectivestartdate');
		$(this).find("td:eq(0)").find("select").attr('name', 'managerdetails[' + (index - 1) + '].supervisorname');
		$(this).find("td:eq(1) input[type='text']").attr('name', 'managerdetails[' + (index - 1) + '].supervisorid');
		$(this).find("td:eq(0)").find("select").attr('id', 'MGR' + (index - 1));
		$(this).find("td:eq(1) input[type='text']").attr('id', 'MNGR_ID' + (index - 1));
		$(this).find("td:eq(0)").find("select").attr('onchange', 'mgrchange(' + (index - 1) + ')');
		$(this).find("td:eq(2)").find("select").attr('name', 'managerdetails[' + (index - 1) + '].managertype');

	});

}


$(document).on("click", "#deletemgr", function() {
	//$("table").row($(this).parents('tr')).remove().draw(false);
	////debugger;
	var dex = $(this).attr('index');
	//var flg=$(this).attr('flg');
	$(this).parents("tr").remove();
	$.each($('#MGR_DET_TBL tr'), function(index, val) {
		$(this).find("td:eq(0) input[id='actionid']").attr('name', 'contactdetails[' + (index - 1) + '].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name', 'managerdetails[' + (index - 1) + '].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name', 'managerdetails[' + (index - 1) + '].personnumber');
		$(this).find("td:eq(0) input[id='managertypeid']").attr('name', 'managerdetails[' + (index - 1) + '].managertypeid');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name', 'managerdetails[' + (index - 1) + '].effectivestartdate');
		$(this).find("td:eq(0)").find("select").attr('name', 'managerdetails[' + (index - 1) + '].supervisorname');
		$(this).find("td:eq(1) input[type='text']").attr('name', 'managerdetails[' + (index - 1) + '].supervisorid');
		$(this).find("td:eq(0)").find("select").attr('id', 'MGR' + (index - 1));
		$(this).find("td:eq(1) input[type='text']").attr('id', 'MNGR_ID' + (index - 1));
		$(this).find("td:eq(0)").find("select").attr('onchange', 'mgrchange(' + (index - 1) + ')');
		$(this).find("td:eq(2)").find("select").attr('name', 'managerdetails[' + (index - 1) + '].managertype');

	});

});

$('#EDIT_MNGR_TAB').on('change', function(e) {
	////debugger;



	$('#ADD_ROW_MNGR').css("display", "inline-block");
	$("#MNGR_TAB_FORM :input").prop("disabled", false);
	$('#SUBMIT_MNGR_TAB_BTN').css("display", "inline-block");
	$('#MGR_CANCEL_BTN').css("display", "inline-block");



});

function mgrchange(mgrid) {
	////debugger;
	//$('#id03').css("display","none");
	MGR_ID = mgrid;
	var selectObject = $("#MGR" + mgrid).children("option:selected").val();
	if (selectObject == 'search') {
		$('#id0Manager').css("display", "block");

	} else if (selectObject != 'search') {
		$('#id0Manager').css("display", "none");
	}
}


var jsonUrlMgr = '/employee_edit/managerbind';

var searchdsCode = '';
var searchdsName = '';
//var hiredate='';
function mgrsearch() {
	searchdsCode = $("#MGR_SRCH_CODE").val();
	searchdsName = $("#MGR_SRCH_NAME").val();
	hiredate = $("#HIRE_DATE").val();
	//alert(hiredate);
	loadMGRData();
}

function loadMGRData() {
	$.ajax({
		type: 'POST',
		url: jsonUrlMgr,
		dataSrc: '',
		data: {
			"personnumber": searchdsCode,
			"firstname": searchdsName,
			"hiredate": hiredate
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			//alert(JSON.stringify(jsonData));
			populateMGRDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateMGRDataTable(data) {
	$("#MGR_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if (dataLength == 0) {
		$('#mgrresultSec').css('display', 'none');
		$('#noDataMGR').css('display', 'block');
	} else {
		//alert("name"+data[0].personname);
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#MGR_LIST").dataTable().fnAddData([
				dat.personname,
				dat.personnumber

			]);
		}
		$('#mgrresultSec').css('display', 'block');
		$('#noDataMGR').css('display', 'none');
	}
}


$(document).ready(function() {
	$('#MGR_POP_CANCEL').on('click', function(e) {
		$('#id0Manager').css("display", "none");
		$('#MANAGERS_LOV').children('option[id="1"]').prop('selected', true);
		$('#mgrresultSec').css('display', 'none');
		$("#MGR_SRCH_CODE").val("");
		$("#MGR_SRCH_NAME").val("");
	});


	$('#MGR_POP_OK').on('click', function(e) {
		////debugger;
		$('#mgrresultSec').css('display', 'none');
		$('#id0Manager').css("display", "none");
		$("#MGR_SRCH_CODE").val("");
		$("#MGR_SRCH_NAME").val("");
		//alert("Data Set ID :::::: "+datasetId+"  BU ID:::: "+buId);
	});

	var table = $('#MGR_LIST').DataTable();

	$('#MGR_LIST tbody').on('click', 'tr', function() {
		var tbldata = $(this).children('td').map(function() {
			return $(this).text();

		}).get();

		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');

		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		///var mgr_id=$(this).children('select').attr('id');
		//alert(MGR_ID);
		var dtData = tbldata[0];
		var dtDataId = tbldata[1];
		$('#MGR' + MGR_ID).val(dtData);
		$('#MGR' + MGR_ID).children('option[id="2"]').text(dtData);
		$('#MGR' + MGR_ID).children('option[id="2"]').val(dtData);
		$('#MGR' + MGR_ID).children('option[id="2"]').prop('selected', true);
		$('#MNGR_ID' + MGR_ID).val(dtDataId);

	});

});

function managercorrect() {
	var fd = $("#MNGR_TAB_FORM").serialize();
	//alert(mode);
	var urladdr = "/employee_edit/managercorrect";
	//alert(urladdr);
	$.ajax({
		url: urladdr,
		type: "POST",
		data: fd,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(result) {
			//alert(result);
			$('#div_replace').html(result);
		},
		error: function(response) {
			alert(JSON.parse(response.responseText));
		}
	});
}



/******************************************************************************/
/*

$('#EDIT_ADDITIONAL_TAB').on('change', function(e){
	////debugger;
	if($("#EDIT_ADDITIONAL_TAB option:selected").val()=="Correct"){	
	$("#ADDITIONAL_DTL_FORM :input").prop("disabled",false);
	$("#ADDITIONAL_SUBMIT_BTN").css("display","inline-block");
	$("#ADDITIONAL_CANCEL_BTN").css("display","inline-block");
	}
	if($("#EDIT_ADDITIONAL_TAB option:selected").val()=="Update"){
		
	}
});


function additionalcorrectupdate() {
var fd = $("#ADDITIONAL_DTL_FORM").serialize();
//alert(mode);
var urladdr="/employee_edit/additionalcorrectupdate";
//alert(urladdr);
$.ajax({
		url: urladdr,
		type: "POST",
		data: fd,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
				  }
	});
}
*/



function correctupdateBankDetails() {

	$('#POP_DATE').prop('disabled', false);
	var fd = $("#BANK_POP_FORM").serialize();
	//alert(mode);
	var urladdr = "/employee_edit/correctupdateBankDetails";
	//alert(urladdr);
	$.ajax({
		url: urladdr,
		type: "POST",
		data: fd,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(result) {
			//alert(result);
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message: "Bank Details Updated Sucessfully.",
				callback: function() {
					$('#div_replace').html(result);
				}
			});

		},
		error: function(response) {
			alert(JSON.parse(response.responseText));
		}
	});

}

