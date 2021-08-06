$('#downloadotplink').attr("href", "/downloadopttemplate/"+$('#selectedcalendar').val());

function otpEmployeeSelect(elementid,elementname){
	$("#PAYROLL_LOADER").css("display","block");
    $("#OTPNAME").text(elementname);
    var calendarid = $('#selectedcalendar').val();
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/getidentifiedpersonbyoptelement/' + elementid + '/' + calendarid,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
			$(`#IDENTIFY_TBL tbody`).empty();
			$("#PAYROLL_LOADER").css("display","none");
            $('#otpAmount').html(result.message);
            $('#elementselected').val(elementid);
        },
        error: function(e){
	        $("#PAYROLL_LOADER").css("display","none");
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function pushData(){
    var identifiedpersonbyoptelement = $('#employeeOTPData').serialize();
    var clickedElement = $('#elementselected').val();
    //alert(identifiedpersonbyoptelement);
    $.ajax({
        url: "/payrollprocessing/setidentifiedpersonbyoptelement",
        type: "POST",
        data: identifiedpersonbyoptelement,
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success: function (result) {
            $('#check'+clickedElement).css('display', 'inline-block');
            $('#uncheck'+clickedElement).css('display', 'none');
        },
        error: function (response) {
            alert(JSON.parse(response.responseText));
        }

    });
}

function openUploadOTPModal(){
    document.getElementById('uploadOTPDataModal').style.display='block';
}

function uploadOTPClick(){

    var form = $('#uploadOTPForm')[0];
    var data = new FormData(form);

	var calendarid = $('#selectedcalendar').val();
    $("#PAYROLL_LOADER").css("display","block");
	$.ajax({
        url: "/payrollprocessing/uploadopt/" + calendarid,
        enctype: "multipart/form-data",
        type: "POST",
        //data: document.getElementById("myFile"),
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        timeout:600000,
        success: function (result) {
            if(result=="success"){
                document.getElementById('uploadOTPDataModal').style.display='none';
                $("#PAYROLL_LOADER").css("display","none");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message:'OTP successfully uploaded.'
                });
            }else if(result=="failed"){
                $("#PAYROLL_LOADER").css("display","none");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:'File not found. Please attach file and try again.'
                });
            }
        },
        error: function (response) {
            alert(JSON.parse(response.responseText));
        }

    });
}