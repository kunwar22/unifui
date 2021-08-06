$(document).ready(function() {
    $('.payrollGroupSelect').select2({
        placeholder: "Select Payroll Group",
        allowClear: true
    });
    $('.payrollGroupSelect').css("width", "100%");
});

function openAddSavedCalculation() {
    var url = "/setuphrms/payrollsetup/payrollelements/addsavedcalculations";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
}