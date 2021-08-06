$.ajax({
    type: 'GET',
    url: '/setuphrms/payrollsetup/payrollelements/getAllComponentType',
    dataType: 'json',
    success: function(data){
        let componentTypeList = [];
        $.each(data, function (i, value) {
            componentTypeList.push({id : value.componentid, text : value.name});
        });
        $('.componentTypeSelect').select2({
            placeholder: "Select Component Type",
            allowClear: true,
            data: componentTypeList
        });
        $('.componentTypeSelect').css("width", "100%");
    },
    error: function(e){
        console.log("There was an error with request...");
        console.log("error: " + JSON.stringify(e));
    }
});

$('#componentAdd').on('click', function(){
    var fieldData = $('#createComponent').serialize();
    var url = "/setuphrms/payrollsetup/salarycomponents/addsalarycomponent";

    $.ajax({
        url: url,
        type: "POST",
        data: fieldData,
        cache: false,
        //contentType: "application/x-www-form-urlencoded",
        processData: false,
        success : function(result){
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                message: result
                //message: "Course Plan Generated Successfully, with Plan ID : " + createdPlanId
            });
            //var url = "/administration/manageCoursePlan";
            //$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
            //$('#replace_div').load(url);
        },
        error: function(response){
            alert(JSON.parse(response.responseText));
        }
    });
});

$('#cancilAddComponent').on('click', function(){
    url = '/setuphrms/payrollsetup/salarycomponents/salarycomponent';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});