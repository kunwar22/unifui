$('#cancilAddComponentType').on('click', function(){
    url = '/setuphrms/payrollsetup/salarycomponents/componentstype';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});

$('#componentTypeAdd').on('click', function(){
   var fieldData = $('#createComponentType').serialize();
   var url = "/setuphrms/payrollsetup/salarycomponents/addcomponentstype";

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

$('#componentTypeAddMore').on('click', function(){
    var url = "/setuphrms/payrollsetup/salarycomponents/addcomponentstype";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});