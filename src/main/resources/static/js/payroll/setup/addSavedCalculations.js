$(document).ready(function(){
    $('.componentSelect').select2();
    $('.payrollElementSelect').select2();
    $('.payrollGroupSelect').select2();
})

$('#cancilAddSavedCalculation').on('click', function(){
    var url = "/setuphrms/payrollsetup/payrollelements/savedcalculations";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
})