$(document).ready(function() {
    $('.payrollGroupSelect').select2({
        placeholder: "Select Payroll Group",
        allowClear: true
    });
    $('.payrollGroupSelect').css("width", "100%");
});

function addNewCalendarPeriod(){
    var newRow = '<tr>' +
                    '<td>' +
                        '<input type="text" name="calPeriods[0].periodcode">'+
                    '</td>'+
                    '<td>' +
                    '<input type="text" name="calPeriods[0].payrollperiod">'+
                    '</td>'+
                    '<td>'+
                        '<input type="date" name="calPeriods[0].startdate" data-date="" data-date-format="DD MM YYY">'+
                    '</td>'+
                    '<td>'+
                        '<input type="date" name="calPeriods[0].enddate" data-date="" data-date-format="DD MM YYY">'+
                    '</td>'+
                    '<td>'+
                        '<input type="text" name="calPeriods[0].dayscount" style="width: 50px;">'+
                    '</td>'+
                    '<td width="80px;">'+
                        '<i onclick="addNewCalendarPeriod()" class="fas fa-plus-circle" style="margin-top: 6px; margin-right: 5px; color: darkgreen; cursor: pointer"></i>'+
                        '<i onclick="removeCalendarPeriod(this)" class="far fa-trash-alt" style="color: red; cursor: pointer; margin-left: 5px;"></i>'+
                    '</td>'+
                '</tr>';

    $(newRow).appendTo('#calendarPeriod');

    $.each($('#calPeriodTable tr'),function(index,val){
        $(this).find("td:eq(0) input[type='text']").attr('name','calPeriods['+(index - 1)+'].periodcode');
        $(this).find("td:eq(1) input[type='text']").attr('name','calPeriods['+(index - 1)+'].payrollperiod');
        $(this).find("td:eq(2) input[type='date']").attr('name','calPeriods['+(index - 1)+'].startdate');
        $(this).find("td:eq(3) input[type='date']").attr('name','calPeriods['+(index - 1)+'].enddate');
        $(this).find("td:eq(4) input[type='text']").attr('name','calPeriods['+(index - 1)+'].dayscount');
    });

}

function removeCalendarPeriod(e) {
    $(e).closest('tr').remove();
    $.each($('#calPeriodTable tr'),function(index,val){
        $(this).find("td:eq(0) input[type='text']").attr('name','calPeriods['+(index - 1)+'].periodcode');
        $(this).find("td:eq(1) input[type='text']").attr('name','calPeriods['+(index - 1)+'].payrollperiod');
        $(this).find("td:eq(2) input[type='date']").attr('name','calPeriods['+(index - 1)+'].startdate');
        $(this).find("td:eq(3) input[type='date']").attr('name','calPeriods['+(index - 1)+'].enddate');
        $(this).find("td:eq(4) input[type='text']").attr('name','calPeriods['+(index - 1)+'].dayscount');
    });
}

$('#savePayrollCalendar').on('click', function(){
    var formData = $('#payrollCalendar').serialize();
    alert(formData.toString());
    url = "../setuphrms/payrollsetup/payrollcalendar/addpayrollcalendar";
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