function openTab(evt, optTabName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("optTab");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-border-blue-grey w3-theme-l3", "");
    }
    document.getElementById(optTabName).style.display = "block";
    evt.currentTarget.firstElementChild.className += " w3-border-blue-grey w3-theme-l3";
}

function showHodeContent(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className = x.className.replace("w3-hide", "w3-show");
    } else {
        x.className = x.className.replace("w3-show", "w3-hide");
    }
}

function showHidePwdBlock(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-hide") == -1) {
        x.className = x.className.replace("w3-show", "w3-hide");
    } else {
        x.className = x.className.replace("w3-hide", "w3-show");
    }
}

$("#returnToUserSearch").click(function () {
    var retUrl = '/uniftools/security/userprofiles/userprofiles/' + $('#userId').text();
    $('#replace_div').load(retUrl);
});


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
            if (data.message == "success") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message: "Password change Successfully."
                })
				return ;
            }
            if (data.errorMessage == "failed") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message: "Unable to change Password."
                })
				return ;
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


function matchPass() {
    var newpass = document.getElementById("newpass").value;
    var conpass = document.getElementById("confpass").value;

    if (newpass.length >= 5) {
        if (true) {
            if (newpass == conpass) {
                //document.getElementById("submitbtn").disabled = false;
                return true;
            } else {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message: 'New Password and Confirm Password do not match.'
                });
                //document.getElementById("submitbtn").disabled = true;
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
        return false;
    }
}


/**********************added by rajat on 08/01/2021 end ******************************************/