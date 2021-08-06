var  calid = $('#selectedcalendar').val();
var  calcode = $('#selectedcalendarcode').val();
$('#downloadLink').attr("href", "/payrollprocessing/confirmPayroll/downloadpayrollregister/"+calid+"/"+calcode+"/HR");
$('#downloadLink1').attr("href", "/payrollprocessing/confirmPayroll/downloadpayrollregister/"+calid+"/"+calcode+"/FINANCE");
if($.fn.DataTable.isDataTable('#calculatedSalary')){
    $("#calculatedSalary").DataTable().destroy();
}
var calculatedSalaryDataTable = $("#calculatedSalary").DataTable({
    'columnDefs': [
        {'targets': [3,4], 'orderable': false},
        {'targets':[3], 'className': 'text-right'},
        {'targets':[4], 'className': 'text-center'}
    ]
});
loadData();
$('#resultSec').css('display', 'none');
$('#noData').css('display', 'none');
$('#jsonLoader').css('display', 'block');

function loadData() {
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/executePayroll/getpayrollprocesseddata/'+calid,
        dataSrc: '',
        dataType: 'json',
        success: function(data){
            jsonData = data;
            populateDataTable(jsonData);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function populateDataTable(data){
    calculatedSalaryDataTable.clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#resultSec').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#calculatedSalary").dataTable().fnAddData([
                dat.calendarcode,
                dat.personnumber,
                dat.personname,
                dat.payamt,
                "<a id='"+dat.personnumber+"' onclick='displayCalculationDetails(this.id)' style='cursor: pointer;'><i class='fas fa-eye' aria-hidden='true'></i></a>"
            ]);
        }

        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
    }
}

function displayCalculationDetails(personnumber){
    //alert(personnumber);
    $('#viewSalaryBrakeup').css('display', 'block');
    $('#employee').html(personnumber);
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/executePayroll/getelementwisepayrollprocesseddata/'+ personnumber +'/'+ $('#selectedcalendar').val(),
        dataSrc: '',
        dataType: 'json',
        success: function(data){
            $('#elementvalueblock').html(data.elementvalue);
            $('#employee').html(data.name);
            $('#viewSalaryBrakeup').css('display', 'block');
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function lockPayroll(){
    //alert(resultfinal);
    //debugger;
    bootbox.confirm({
        message: "Are you sure to lock the Payroll? If locked, you won't be able to run the payroll of this calendar month unless unlocked.",
        buttons: {
            confirm: {
                label: 'Yes',
                className: 'btn-success'
            },
            cancel: {
                label: 'No',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if(result){
                $.ajax({
                    type: 'GET',
                    url: '/payrollprocessing/confirmPayroll/lockpayroll/' + calid,
                    datasrc: '',
                    dataType: 'JSON',
                    success: function(res){
                        if (res.lockStatus == "success") {
                            $("#lockpayrollbtn").delay(200).fadeOut(800);
                            $("#bk").delay(200).fadeOut(800);
                            $("#nx").delay(200).fadeOut(800);
                            $("#lockpayrollbtn").attr("disabled", true);
                            $('#replace_div').load("/finalizeunlockcalendar");
                        }
                    },
                    error: function(e){
                        console.log("There was an error with request...");
                    }
                });
            }
        }
    });

};