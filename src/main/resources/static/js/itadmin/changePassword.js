$(document).on('click').unbind();

$("#PersonalRecordList").DataTable({
    'columnDefs': [{
        'targets': [3, 4],
        'orderable': false
    }]
});


var PERSON_DEPT = '';
var PERSON_ID = '';
var PERSON_LEGAL_ENTITY = '';
var PERSON_NAME = '';
var PERSON_NUMBER = '';

var jsonUrl = '/managePersaon/searchPersonal/getpersonalChangePassword';


function handleKeyPress(e) {

    var key = e.keyCode || e.which;
    if (key == 13) {
        PERSON_DEPT = $('#PERSON_DEPT').val();
        /*PERSON_ID = $('#PERSON_ID').val();*/
        PERSON_LEGAL_ENTITY = $('#PERSON_LEGAL_ENTITY').val();
        PERSON_NAME = $('#PERSON_NAME').val();
        PERSON_NUMBER = $('#PERSON_NUMBER').val();
        //alert("hi "+DEPT_NAME+" "+DEPT_CODE+" "+DEPT_DATASET+" "+DEPT_STATUS+" END here");
        loadData();
        $('#PersonalRecordresultSec').css('display', 'none');
        $('#noData').css('display', 'none');
        $('#jsonLoaderPage').css('display', 'block');

    }
}

$("#Personal_SEARCH").click(function () {
    PERSON_DEPT = $('#PERSON_DEPT').val();
    /*PERSON_ID = $('#PERSON_ID').val();*/
    PERSON_LEGAL_ENTITY = $('#PERSON_LEGAL_ENTITY').val();
    PERSON_NAME = $('#PERSON_NAME').val();
    PERSON_NUMBER = $('#PERSON_NUMBER').val();
    //alert("hi "+DEPT_NAME+" "+DEPT_CODE+" "+DEPT_DATASET+" "+DEPT_STATUS+" END here");
    loadData();
    $('#PersonalRecordresultSec').css('display', 'none');
    $('#noData').css('display', 'none');
    $('#jsonLoaderPage').css('display', 'block');
});

function loadData() {
    $.ajax({
        type: 'POST',
        url: jsonUrl,
        dataSrc: '',
        data: {
            "PERSON_DEPT": PERSON_DEPT,
            /*"PERSON_ID": PERSON_ID,*/
            "PERSON_NAME": PERSON_NAME,
            "PERSON_LEGAL_ENTITY": PERSON_LEGAL_ENTITY,
            "PERSON_NUMBER": PERSON_NUMBER
        },
        dataType: 'json',
        success: function (data) {
            jsonData = data;
            populateDataTable(jsonData);
            $('#jsonLoaderPage').css('display', 'none');
        },
        error: function (e) {
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function populateDataTable(data) {
    $("#PersonalRecordList").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#PersonalRecordresultSec').css('display', 'none');
        $('#noData').css('display', 'block');
        $('#jsonLoaderPage').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#PersonalRecordList").dataTable().fnAddData([
                dat.personnumber,
                dat.personname,
                dat.personlocation,
                dat.persondepartment,
                dat.personemail,
                dat.personlegalentity,
                '<div class="w3-btn w3-blue w3-round" style="cursor: pointer" id="view" aria-hidden="true" onclick="viewpasswordReset(' + dat.personnumber + ',\'' + dat.personname + '\');"><i class="fas fa-unlock-alt"></i>&nbsp;Change Password</div>'
                //	"<ed rm='/enterprisesetup/edit/EditDepartment/"+dat.personid+"/"dat.personid"' class='editpersonid' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"

            ]);
        }
        $('#PersonalRecordresultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoaderPage').css('display', 'none');
    }
}


$(document).on('click').unbind();

$(document).on('click', 'ed', function (e) {
    var url = $(this).attr("rm");
    $('#replace_div').load(url);
});


function viewpasswordReset(id, name) {

    $('#personnumber').text(id);
    $('#personname').text(name);
	$('#loginid').val(id);
    $('#changePasswordEmployee').css('display', 'block');


}


/*************************************************************************/






/************************************************************************/

/**********************added by rajat on 08/01/2021 start ******************************************/


var jurlChangePasswod = "/uniftools/security/userprofiles/password/changePassword";

function savePassword() {

	var loginid = $('#loginid').val();
	var confpass = $('#confpass').val();
	var newpass = $('#newpass').val();

	if (confpass == null || confpass == '') {

		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please enter the confirm password.",
		});
		return;
	}
	else if (newpass == null || newpass == '') {

		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "Please enter the new password.",
		});
		return;
	}

	else if (newpass == confpass) {
		$.ajax({
			type: 'POST',
			url: jurlChangePasswod,
			dataSrc: '',
			data: {
				"loginid": loginid,
				"confpass": confpass,
			},
			dataType: 'json',
			success: function (data) {
				$('#changePasswordEmployee').css('display', 'none');
				$("#confpass").val("");
				$("#newpass").val("");
				if (data.message == "success") {
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
						message: "Password change Successfully."
					})
					return;
				}
				if (data.errorMessage == "failed") {
					bootbox.alert({
						size: 'medium',
						title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
						message: "Unable to change Password."
					})
					return;
				}
			},
			error: function (e) {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Somthing went wrong please try again. Click OK to continue.",
					callback: function () {
						$('#replace_div').load("/uniftools/security/userprofiles/userprofiles");
					}
				});
			}
		});
	}
	else{
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: 'New Password and Confirm Password do not match.'
		});
		return ;
	}
}

function RestrictSpace() {
	if (event.keyCode == 32) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: 'Password does not contain space.'
		});
		$("#confpass").val("");
		$("#newpass").val("");
		return false
	}
}

function matchPass() {
	var newpass = document.getElementById("newpass").value;
	var conpass = document.getElementById("confpass").value;



	if (newpass.length >= 5) {
		if (true) {
			if (newpass == conpass) {
				//document.getElementById("submitbtn").disabled = false;
				return;
			} else {
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: 'New Password and Confirm Password do not match.'
				});
				//document.getElementById("submitbtn").disabled = true;
				$("#confpass").val("");
				$("#newpass").val("");
				return false;

			}
		}
	} else {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message: 'Password must contain atleast 6 letters.'
		});
		//document.getElementById("submitbtn").disabled = true;
		$("#confpass").val("");
		$("#newpass").val("");
		return false;
	}
}


/**********************added by rajat on 08/01/2021 end ******************************************/







