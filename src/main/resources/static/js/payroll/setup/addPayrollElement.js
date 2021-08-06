$('.payrollElementTypeSelect').select2({
    placeholder: "Select Element Type",
    allowClear: true
});

/*
$('#componentAdd').on('click', function(){
    var formData = $('#createElement').serialize();
   // alert(formData.toString());
    url = "/setuphrms/payrollsetup/payrollelements/savepayrollelement";
    $.ajax({
        url: url,
        type: "POST",
        data: formData,
        cache: false,
        processData: false,
        contentType: "application/x-www-form-urlencoded",
        success: function (result) {
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                message: result
                //message: "Course Plan Generated Successfully, with Plan ID : " + createdPlanId
            });
        },
        error: function (response) {
            alert(JSON.parse(response.responseText));
        }
    })
});
*/


function ajaxPost() {
    CR_PE_ID = $('#elementid').val();
    if (CR_PE_ID != 0) {
        loadUpdatePayrollElement();
    } else if (CR_PE_ID == 0) {
        //alert(checkBuid);
        loadSavePayrollElement();

    }
};


/// var urlsave="/setuphrms/payrollsetup/payrollelements/savepayrollelement";
function loadSavePayrollElement() {


    var fd = $("#createElement").serialize();

    $.ajax({
        url: "/setuphrms/payrollsetup/payrollelements/savepayrollelement",
        type: "POST",
        data: fd,
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success: function (result) {
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                message: result
                //message: "Course Plan Generated Successfully, with Plan ID : " + createdPlanId
            });
        },
        error: function (response) {
            alert(JSON.parse(response.responseText));
        }

    });
};

var urlupdate = "/setuphrms/payrollsetup/payrollelements/updatepayrollelement";

function loadUpdatePayrollElement() {

   
    var fd = $("#createElement").serialize();

    $.ajax({
        url: urlupdate,
        type: "POST",
        data: fd,
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success: function (result) {
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                message: result
                //message: "Course Plan Generated Successfully, with Plan ID : " + createdPlanId
            });
        },
        error: function (response) {
            alert(JSON.parse(response.responseText));
        }

    });
};


$('#cancilAddComponent').on('click', function () {
    url = "/setuphrms/payrollsetup/payrollelements/payrollelements";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});
