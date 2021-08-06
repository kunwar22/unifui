
var maxfy = getMaxfy();
var minfy = getMinfy();
function getMinfy() {
	var today = new Date();
	if ((today.getMonth() + 1) <= 3) {
		var minfy = (today.getFullYear() - 1) + "-04-01";
	} else {
		var minfy = today.getFullYear() + "-04-01";
	}
	return minfy;
}
function getMaxfy() {
	var today = new Date();
	if ((today.getMonth() + 1) <= 3) {
		var maxfy = today.getFullYear() + "-03-31";
	} else {
		var maxfy = (today.getFullYear() + 1) + "-03-31";
	}
	return maxfy;
}
$("#CR_CDS_PRD_FROM_NW").change(function() {
	$("#CR_CDS_PRD_TO_NW").val("");
	$("#CR_CDS_NO_DYS_NW").val(0);
	$("#CR_CDS_NO_MNTS_NW").val(0);
});
$("#CR_CDS_PRD_FROM_NW").click(function() {
	$("#CR_CDS_PRD_FROM_NW").attr("max", maxfy);
	$("#CR_CDS_PRD_FROM_NW").attr("min", minfy);
});
$("#CR_CDS_PRD_TO_NW").click(function() {
	$("#CR_CDS_PRD_TO_NW").attr("max", maxfy);
	var from_date = $("#CR_CDS_PRD_FROM_NW").val();
	if (from_date != '') {
		$("#CR_CDS_PRD_TO_NW").attr('min', from_date);
	}
});
$("#CR_CDS_PRD_FROM_ED").change(function() {
	$("#CR_CDS_PRD_TO_ED").val("");
	$("#CR_CDS_NO_DYS_ED").val(0);
	$("#CR_CDS_NO_MNTS_ED").val(0);
});
$("#CR_CDS_PRD_FROM_ED").click(function() {
		
	$("#CR_CDS_PRD_FROM_ED").attr("max", maxfy);
	$("#CR_CDS_PRD_FROM_ED").attr("min", minfy);
});
$("#CR_CDS_PRD_TO_ED").click(function() {
	$("#CR_CDS_PRD_TO_ED").attr("max", maxfy);
	var from_date = $("#CR_CDS_PRD_FROM_ED").val();
	if (from_date != '') {
		$("#CR_CDS_PRD_TO_ED").attr('min', from_date);
	}
});

$("#CR_CDS_PRD_FROM").change(function() {
	$("#CR_CDS_PRD_TO").val("");
	$("#CR_CDS_NO_DYS_NW").val(0);
	$("#CR_CDS_NO_MNTS_NW").val(0);
});
$("#CR_CDS_PRD_FROM").click(function() {
	$("#CR_CDS_PRD_FROM").attr("max", maxfy);
	$("#CR_CDS_PRD_FROM").attr("min", minfy);
});
$("#CR_CDS_PRD_TO").click(function() {
	$("#CR_CDS_PRD_TO").attr("max", maxfy);
	var from_date = $("#CR_CDS_PRD_FROM").val();
	if (from_date != '') {
		$("#CR_CDS_PRD_TO").attr('min', from_date);
	}
});



$("#CR_CDS_PRD_TO_ED").click(function() {
	$("#CR_CDS_PRD_TO_ED").attr("max", maxfy);
	var from_date = $("#CR_CDS_PRD_FROM_ED").val();
	if (from_date != '') {
		$("#CR_CDS_PRD_TO_ED").attr('min', from_date);
	}
});
/*********** SNIGDHAA 11-NOV-2020 ********************************/



var diffDays = '';
var file_flag = 1;
var file_flag2 = 1;
var attach_flag = 1;
var file_attach = '';

file_attach = $('#filepres2').val();


$(document).ready(function() {


	//var _href=$("#DOWNLOAD_LINKsp").attr("href");
	var _href = $("#DOWNLOAD_LINKsp").text();
	if (_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');

		$("#DOWNLOAD_LINK").attr("href", "/getContent?location=" + _href);
	}
	//var _href1=$("#DOWNLOAD_LINK1sp").attr("href");
	var _href1 = $("#DOWNLOAD_LINK1sp").text();
	if (_href1 != undefined && _href1 != "" && _href1 != null) {
		_href1 = _href1.replaceAll('/', "FORWARD_SLASH");
		_href1 = _href1.replaceAll('\\', 'BACKWARD_SLASH');
		_href1 = _href1.replaceAll('.', 'EXT_DOT');

		$("#DOWNLOAD_LINK1").attr("href", "/getContent?location=" + _href1);
	}


	/*****************code added by rajat on 12-11-2020 start************************ */

	var my_hod_link1 = fileatach;
	//alert(my_hod_link1);

	// var approval_of_hod_Certificate ='2019-10-15';
	var approval_of_hod_Certificate = datess;
	//alert(approval_of_hod_Certificate);
	var dateFirst = new Date(approval_of_hod_Certificate);
	var dateSecond = new Date();
	var timeDiff = 0;
	if (approval_of_hod_Certificate == null) {
		timeDiff = 0;
	}
	else {


		// time difference
		timeDiff = Math.abs(dateSecond.getTime() - dateFirst.getTime());
	}
	// days difference
	diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

	// difference
	// alert(diffDays);

	if (diffDays > 365 || diffDays == 0) {
		/*bootbox.alert({
		  size: 'medium',
		  title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
		  message:"Your Approval of Hod certificate expire. please attach valid certificate."				
		  });*/
		$('#CR_CDS_HOD_ATCHMNT').prop('disabled', true);

		$('#CR_CDA_APROVAL').attr("disabled", false);

	}
	else if (diffDays <= 365) {

		$('#CR_CDS_HOD_ATCHMNT').prop('disabled', true);
		$("#CR_CDA_APROVAL").html("<option value='Yes'>Yes</option>");
		$('#CR_CDA_APROVAL').attr("disabled", true);
		$('#CR_CDS_HOD_ATCHMNT').prop('disabled', true);
		// alert(fileatach);
		//$('#CR_CDS_HOD_ATCHMNT').value(fileatach);
		file_flag2 = 2;
		if (file_attach == '' || file_attach == null) {
			file_attach = 0;
			//alert(file_attach);
		}

	}

	//	$('#filepres2').val("");

	//var hodcertificate= $('#CR_CDA_APROVAL').children("option:selected").val();
	//if(hodcertificate=='Yes'){
	//	 $('#CR_CDS_HOD_ATCHMNT').removeAttr('disabled');
	//}

	if (my_hod_link1 == "" || my_hod_link1 == null) {
		$('#my_hod_certificate').css('display', 'none');
	}
	else if (my_hod_link1 != "" || my_hod_link1 != null) {
		$('#my_hod_certificate').css('display', 'block');

     var _href = my_hod_link1;
	if (_href != undefined && _href != "" && _href != null) {
		_href = _href.replaceAll('/', "FORWARD_SLASH");
		_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
		_href = _href.replaceAll('.', 'EXT_DOT');

		$("#my_hod_certificate_link1").attr("href", "/getContent?location=" + _href);
	}
		/*$('#my_hod_certificate a').each(function() {
			this.href += my_hod_link1;
		})*/

	}
	
	
	


	/******************code added by rajat on 12-11-2020 start********************** */
});
//alert("hi cda create");
var from = '';
var to = '';
/*******************************************************/
from = $('#CR_CDS_PRD_FROM_NW').val();

to = $('#CR_CDS_PRD_TO_NW').val();
//alert("date"+to);

$('#CR_CDS_PRD_TO_NW').on('change', function() {
	//alert("in");
	from = $('#CR_CDS_PRD_FROM_NW').val();
	to = $('#CR_CDS_PRD_TO_NW').val();
	//alert(from);
	$("#CR_CDS_NO_DYS_NW").val(0);
	$("#CR_CDS_NO_MNTS_NW").val(0);

	if (from == '' || from == null) {
		$("#CR_CDS_PRD_TO_NW").val("");
		$("#CR_CDS_NO_DYS_NW").val(0);
		$("#CR_CDS_NO_MNTS_NW").val(0);
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please select Period from date first.",
		});

	}
	else if (from != '' || from != null) {


		var date1 = new Date(from);
		var date2 = new Date(to);
		var Difference_In_Time = date2.getTime() - date1.getTime();

		var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
		Difference_In_Days = Difference_In_Days + 1;
		$('#CR_CDS_NO_DYS_NW').val(Difference_In_Days);


		diff_months()
		
		$('#CR_CDS_NO_MNTS_NW').val(diff_months(date1));
	}
});

$('#CR_CDS_ATTCHMENT').on('change', function() {
	if ($('#CR_CDS_ATTCHMENT').val != null) {
		$('#filepres1').val("Y");
	} else {
		$('#filepres1').val("");
	}
});




$('#CR_CDS_HOD_ATCHMNT').on('change', function() {
	if ($('#CR_CDS_HOD_ATCHMNT').val != null) {
		$('#filepres2').val("Y");
		file_flag = 2;
		//alert(file_flag);
	}
	else {
		$('#filepres2').val("");
	}

});
/*******************************************************/
/*******************************************************/
from = $('#CR_CDS_PRD_FROM_ED').val();
to = $('#CR_CDS_PRD_TO_ED').val();

$('#CR_CDS_PRD_TO_ED').on('change', function() {

	$("#CR_CDS_NO_DYS_ED").val(0);
	$("#CR_CDS_NO_MNTS_ED").val(0);

	from = $('#CR_CDS_PRD_FROM_ED').val();
	to = $('#CR_CDS_PRD_TO_ED').val();

	if (from == '' || from == null) {
		$("#CR_CDS_PRD_TO_ED").val("");
		$("#CR_CDS_NO_DYS_ED").val(0);
		$("#CR_CDS_NO_MNTS_ED").val(0);
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please select Period from date first.",
		});

	}
	else if (from != '' || from != null) {


		var date1 = new Date(from);
		var date2 = new Date(to);
		var Difference_In_Time = date2.getTime() - date1.getTime();

		var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
		Difference_In_Days + 1;
		$('#CR_CDS_NO_DYS_ED').val(Difference_In_Days);


		diff_months()
		
		$('#CR_CDS_NO_MNTS_ED').val(diff_months(date1));
	}
});
/*******************************************************/

/****************************************************************************************/
function diff_months() {
	//from = $('#TELE_FROM_DATE').val();
	//to = $('#TELE_TO_DATE').val();

    /*var dt1 = new Date(from);
    var dt2 = new Date(to);
    var diff = (dt2.getTime() - dt1.getTime()) / 1000;
    diff /= (60 * 60 * 24 * 7 * 4);*/

	var a = moment(to);
	var b = moment(from);
	//alert(a+" :: "+b);
	var months = a.diff(b, 'months');
	//alert(months + ' months ');

	return months + 1;
}

/*function diff_months() {
    var dt1 = new Date(from);
    var dt2 = new Date(to);
    var diff = (dt2.getTime() - dt1.getTime()) / 1000;
    diff /= (60 * 60 * 24 * 7 * 4);
    return Math.abs(Math.ceil(diff));

}*/

function backBtnFunc() {
	//debugger;
	var url = "/cdaVehicle/manageCdaTelephone";
	$('#replace_div').load(url);
}


/***************************************************************************************/

var CR_CDA_VHCL_ID = '';




/*
function ajaxPost()
{

	CR_CDA_VHCL_ID = $('#txtcdavehicleidid').val();

	
	if(CR_CDA_VHCL_ID!=0){
		loadSaveCdaVehicle();
	}
	else if(CR_CDA_VHCL_ID==0){
		//alert(checkBuid);
		loadSaveCdaVehicle();
		
	}
	
};*/

/*$('#CDA_REMB_Cancel').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});	


$('#CDA_REMB_Cancel_V').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});	


$('#CDA_REMB_Cancel_E').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});	
*/

var certified_C = '';
var checkBox_c = '';
function myFunction_C() {
	checkBox_c = document.getElementById("CR_CDS_CERTIFY_C");

	certified_C = 1;
	if (checkBox_c.checked == true) {
		certified_C = 2;
	} else {
		certified_C = 1;

	}
}


function loadSaveCdaVehicle(str, frmid, x) {
var no_of_day = $('#CR_CDS_NO_DYS_NW').val();
var no_of_month = $('#CR_CDS_NO_MNTS_NW').val();
	//debugger;
	var flag = 'Yes'
	var hodcerti = $('#CR_CDA_APROVAL').children("option:selected").val();
	var approvalAttach = $('#CR_CDS_HOD_ATCHMNT').val();
	//alert(approvalAttach);
	
	var attachment1= $('#CR_CDS_ATTCHMENT').val();

	if (parseInt($("#CR_CDS_AMNT").val()) > parseInt($("#cda").text())) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "CDA Claim amount can not be greater than entitled amount.",
		});
		$("#CR_CDS_AMNT").val(0.0);
		return;
	}
	else if (parseInt($("#CR_CDS_VHICL_AMNT").val()) > parseInt($("#vehical").text())) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Vehicle Claim amount can not be greater than entitled amount.",
		});
		$("#CR_CDS_VHICL_AMNT").val(0.0);
	}
	//else if(hodcerti=='Yes' && approvalAttach==null && ||approvalAttach==''){
	else if (hodcerti == 'Yes' && file_flag == 2 && attach_flag == 1) {

		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please attach the Hod Certificate.",
		});
		$('#CR_CTG_OWN_VHCL_ATTACH_C').removeAttr('disabled');
	}
	else if (hodcerti == null || hodcerti == '' || hodcerti == 0) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please attach the Hod Certificate.",
		});
		$('#CR_CTG_OWN_VHCL_ATTACH_C').removeAttr('disabled');
	}
	
	else if (attachment1 == null || attachment1 == '' || attachment1 == 0) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please attach the attachment first.",
		});

	}
	
		else if ( no_of_day==NaN||no_of_day=="NaN") {	
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Number of days Shound not be blank or Zero."
		});	
		$("#CR_CDS_NO_DYS_NW").val(0);
		$("#CR_CDS_NO_MNTS_NW").val(0);	
	}
	
	else if ( no_of_month==NaN||no_of_month=="NaN") {	
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Number of Months Shound not be blank or Zero."
		});	
		$("#CR_CDS_NO_DYS_NW").val(0);
		$("#CR_CDS_NO_MNTS_NW").val(0);	
	}


	else if (file_flag2 == 1 && file_flag == 1 && file_attach == '') {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please attach the Hod Certificate.",
		});
		$('#CR_CTG_OWN_VHCL_ATTACH_C').removeAttr('disabled');
	}


	//if (($('#CR_CDS_CERTIFY_C').is(':checked'))&& flag=='Yes' ) 
	else if (certified_C == 2 && flag == 'Yes') {
		$(x).css('display', 'none');
		var periodfrom = document.getElementsByName("periodfrom")[0].value;
		var periodto = document.getElementsByName("periodto")[0].value;
		var personnumber = $("#txtpersonNumber").val();
		$("#statusid").val(str);

		var doctype = "CDAReimbursement";
		personnumber = personnumber.trim();
		doctype = doctype.trim();

		var filepath = "";
		filepath += personnumber + "/" + doctype;

		$("#attachment1").val("/EmployeeDocs/" + filepath);

		var data = new FormData(document.getElementById("CDA_SAVE"));

		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax(
			{
				url: "/cdaVehicle/saveCdaVehicle",
				type: "POST",
				enctype: "multipart/form-data",
				data: data,
				cache: false,
				contentType: false,
				processData: false,
				timeout: 600000,
				success: function(data) {
					$('#replace_div').html(data);
					if (resultfinal == "Success") {

						if(str=="save")
						{
							bootbox.alert({
								size: 'medium',
								title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
								message:"You have successfully updated CDA Reimbursement.",
								callback:function(){
									$('#replace_div').load("/cdaVehicle/manageCdaTelephone");
								}
							});
						}
						else if(str=="submit")
						{
							bootbox.alert({
								size: 'medium',
								title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
								message:"You have successfully submitted CDA Reimbursement for approval.",
								callback:function(){
									$('#replace_div').load("/cdaVehicle/manageCdaTelephone");
								}
							});
						}


					}
					else if (resultfinal == "MISMATCH") {
						bootbox.alert("MISMATCH");
					}
					else if (resultfinal == "EMPTY_FILE") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message: 'Attachment Missing!!. <br> Please Upload your Attachments.'
						});
					}
					else if (resultfinal == "WRITE_ERROR") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message: 'Error while writing files!!'
						});
					} else if (resultfinal == "IOEXCEPTION") {
						bootbox.alert("IO Exception has occurred.");
					}
					else if (resultfinal == "LOG_ERROR") {
						bootbox.alert("Error while logging file info.");
					}
					else if (resultfinal == "ILLEGALARG") {
						bootbox.alert("Error while posting file log.");
					}
					else if (resultfinal == "already applied") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message: "Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required"
						});
					}
				},
				error: function(data){
					$(x).css('display', 'block');
					console.log("ERROR : "+JSON.stringify(data));
					bootbox.alert({
									size: 'medium',
									title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
									message:"Something went wrong Please fill all the mandatory fields and try again."					
								});
							//$('#replace_div').html(data);
				}
			});
	} else if ($('#CR_CDS_CERTIFY_C').is(':not(:checked)')) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: 'You cannot proceed without declaration!',
			callback: function() {
				$('.bootbox').on('hidden.bs.modal', function() {
					$('input[id="CR_CDS_CERTIFY_C"]:first').focus();
				});
			}

		});
	}
}

var certified_E = '';
function myFunction() {
	checkBox = document.getElementById("CR_CDS_CERTIFY_E");

	certified_E = 1;
	if (checkBox.checked == true) {
		certified_E = 2;
	} else {
		certified_E = 1;

	}
}

function loadUpdateCdaVehicle(str, frmid, x) {
var no_of_day = $('#CR_CDS_NO_DYS_ED').val();
var no_of_month = $('#CR_CDS_NO_MNTS_ED').val();


	var periodfrom = document.getElementsByName("periodfrom")[0].value;
	var periodto = document.getElementsByName("periodto")[0].value;
	var personnumber = $("#txtpersonNumber").val();
	$("#statusid").val(str);

	var doctype = "CDAReimbursement";
	personnumber = personnumber.trim();
	doctype = doctype.trim();

	var filepaths = "";
	filepaths += personnumber + "/" + doctype;

	$("#attachment1").val("/EmployeeDocs/" + filepaths);

	var data = new FormData(document.getElementById("CDA_SAVE"));
	

	var amount_flg=parseInt($("#CR_CDS_AMNT").text());



	var flag = 'Yes'
	var hodcerti = $('#CR_CDA_APROVAL').children("option:selected").val();
	var approvalAttach = $('#CR_CDS_HOD_ATCHMNT').val();

	var filepath = '';
	//alert(approvalAttach);

	if (parseInt($("#CR_CDS_AMNT").val()) > parseInt($("#cda").text())) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "CDA Claim amount can not be greater than entitled amount.",
		});
		$("#CR_CDS_AMNT").val(0.0);
		return;
	}
	else if (parseInt($("#CR_CDS_VHICL_AMNT").val()) > parseInt($("#vehical").text())) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Vehicle Claim amount can not be greater than entitled amount.",
		});
		$("#CR_CDS_VHICL_AMNT").val(0.0);
	}
	
	
	else if ( amount_flg==0 ||amount_flg==0.0|| amount_flg==null||amount_flg==''||amount_flg==NaN||amount_flg=="NaN") {
	
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "CDA Claim amount can not be zero."
		});
		$("#CR_CDS_AMNT").val(0.0);
	}
	
	else if ( no_of_day==NaN||no_of_day=="NaN") {	
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Number of days Shound not be blank or Zero."
		});	
		$("#CR_CDS_NO_DYS_ED").val(0);
		$("#CR_CDS_NO_MNTS_ED").val(0);	
	}
	
	else if ( no_of_month==NaN||no_of_month=="NaN") {	
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Number of Months Shound not be blank or Zero."
		});	
		$("#CR_CDS_NO_DYS_ED").val(0);
		$("#CR_CDS_NO_MNTS_ED").val(0);	
	}
	
	
	
	//else if(hodcerti=='Yes' && approvalAttach==null && ||approvalAttach==''){
	else if (hodcerti == 'Yes' && file_flag == 2 && attach_flag == 1) {

		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please attach the Hod Certificate.",
		});
		$('#CR_CTG_OWN_VHCL_ATTACH_C').removeAttr('disabled');
	}
	else if (hodcerti == null || hodcerti == '' || hodcerti == 0) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please attach the Hod Certificate.",
		});
		$('#CR_CTG_OWN_VHCL_ATTACH_C').removeAttr('disabled');
	}


	else if (file_flag2 == 1 && file_flag == 1 && file_attach == '') {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please attach the Hod Certificate.",
		});
		$('#CR_CTG_OWN_VHCL_ATTACH_C').removeAttr('disabled');
	}


	else if (certified_E == 2 && flag == 'Yes') {

		$(x).css('display', 'none');
		/*var periodfrom=document.getElementsByName("periodfrom")[0].value;
		var periodto=document.getElementsByName("periodto")[0].value;
        var personnumber = $("#txtpersonNumber").val();
        $("#statusid").val(str);
       
        var doctype = "CDAReimbursement";
        personnumber = personnumber.trim();
        doctype = doctype.trim();

        var filepath = "";
        filepath += personnumber + "/" + doctype;
        
        $("#attachment1").val("/EmployeeDocs/" + filepath);       

        var data = new FormData(document.getElementById("CDA_SAVE"));
        */


		var data = new FormData(document.getElementById("CDA_SAVE"));
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax(
			{
				url: "/cdaVehicle/updateCdaVehicle",
				type: "POST",
				enctype: "multipart/form-data",
				data: data,
				cache: false,
				contentType: false,
				processData: false,
				timeout: 600000,
				success: function(data) {
					$('#replace_div').html(data);
					if (resultfinal == "Success") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: 'You have successfuly claimed CDA Reimbursement for approval.',
							callback: function() {
								$('#replace_div').load("/cdaVehicle/manageCdaTelephone");
							}
						});
					}
					else if (resultfinal == "MISMATCH") {
						bootbox.alert("MISMATCH");
					}
					else if (resultfinal == "EMPTY_FILE") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message: 'Attachment Missing!!. <br> Please Upload your Attachments.'
						});
					}
					else if (resultfinal == "WRITE_ERROR") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message: 'Error while writing files!!'
						});
					} else if (resultfinal == "IOEXCEPTION") {
						bootbox.alert("IO Exception has occurred.");
					}
					else if (resultfinal == "LOG_ERROR") {
						bootbox.alert("Error while logging file info.");
					}
					else if (resultfinal == "ILLEGALARG") {
						bootbox.alert("Error while posting file log.");
					}
					else if (resultfinal == "already applied") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message: "Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required"
						});
					}
					else
					{					
						bootbox.alert({
									size: 'medium',
									title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
									message:"Please fill all the mandatory fields."/*,
									callback: function() {
										$('#replace_div').load("/cdaVehicle/manageCdaTelephone");
									}						*/
								});
					}
				},
				error: function(data){
					$(x).css('display', 'block');
					console.log("ERROR : "+JSON.stringify(data));
					bootbox.alert({
									size: 'medium',
									title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
									message:"Something went wrong Please fill all the mandatory fields and try again."						
								});
							//$('#replace_div').html(data);
				}
				
			});
	} else if ($('#CR_CDS_CERTIFY_E').is(':not(:checked)')) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: 'You cannot proceed without declaration!',
			callback: function() {
				$('.bootbox').on('hidden.bs.modal', function() {
					$('input[id="CR_CDS_CERTIFY_C"]:first').focus();
				});
			}

		});
	}
}




$('#CR_CDS_PRD_FROM_ED').on('change', function(e) {
	var from = $('#CR_CDS_PRD_FROM_ED').val();
	$('#CR_CDS_PRD_TO_ED').attr("min", from);
});



$('#CR_CDS_PRD_FROM_NW').on('change', function(e) {
	var from = $('#CR_CDS_PRD_FROM_NW').val();
	$('#CR_CDS_PRD_TO_NW').attr("min", from);
});

//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

function approvalSubmit(_status) {
	var amt = $("#txtAmount").val();
	var cmt = $("#txtComment").val();
	if ($("#txtAmount").val() == '') {
		$("#txtAmount").val("0");
	}
	var check = "false";
	var msg;
	var formData = $('#CDA_Approval').serialize();

	if (_status == 'Approved') {
		if (amt == "" || amt == "0") {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Request cannot be Approved without filling Approved Amount."
			});
		}
		else {
			check = "true";
		}
	}
	else {
		if (cmt == "") {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Request cannot be Rejected without filling Comments."
			});
		}
		else {
			check = "true";
		}
	}

	if (check == "true") {
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
			url: "/cdaVehicle/CDAApprovalUpdate/" + _status,
			type: "POST",
			cache: false,
			data: formData,
			processData: false,
			contentType: 'application/x-www-form-urlencoded',
			dataType: "json",
			success: function(result) {
				if (result.status == "true") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message: "You have successfully " + _status + ".",
						callback: function() {
							window.location = "/selfservice";
						}
					});
				} else if (result.status == "false") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message: "Something went wrong, Please take action again.",
						callback: function() {
							window.location = "/selfservice";
						}
					});
				}

			},
			error: function(e) {
				//alert("Data already submitted for time period '"+periodfrom+"' - '"+periodto+"'");
				console.log("ERROR : " + JSON.stringify(e));
			}
		});
	}
}

//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



$('#CR_CDA_APROVAL').on('change', function() {

	var val = $(this).val();
	if (val == "Yes") {
		$('#CR_CDS_HOD_ATCHMNT').removeAttr('disabled');
		attach_flag = 2;
	} else {
		$('#CR_CDS_HOD_ATCHMNT').prop('disabled', true);
	}

});

