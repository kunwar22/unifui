$('#cancilAddPayrollGroup').on('click', function(){
    url = "/setuphrms/payrollsetup/payrollelements/payrollgroups";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});

$('#payrollGroupAdd').on('click', function(){
    var payGroupData = $('#createpaygroupform').serialize();
    url = "/setuphrms/payrollsetup/payrollcalendar/savepayrollgroup";
    $.ajax({
        url: url,
        type: "POST",
        data: payGroupData,
        cache: false,
        processData: false,
        contentType: "application/x-www-form-urlencoded",
        success: function (result) {
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                message: result
            });
            //var redirectURL = "/setuphrms/payrollsetup/payrollelements/payrollgroups";
            //$('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
            //$("#replace_div").load(redirectURL);
        },
        error: function (response) {
            alert(JSON.parse(response.responseText));
        }
    })
});