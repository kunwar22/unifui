
var caldata;

function openAddPayCalendar(){
    if($('#paygroupSelect option:selected').val() == ""){
        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-exclamation-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
            message: 'Please select payroll group.'
        });
        return;
    }
    document.getElementById('addNewCalendar').style.display='block';
    $('[name="paygroupid"]').val($('#paygroupSelect option:selected').val());
    $('[name="groupname"]').val($('#paygroupSelect option:selected').text());
    $('[name="status"]').val('save');
    $('[name="createdby"]').val('jaishankar');

    $('[name="calendarcode"]').val('');
    $('[name="calendarperiod"]').val('');
    $('[name="startdate"]').val('');
    $('[name="enddate"]').val('');
    $('[name="days"]').val('');

    $('#days').attr('readonly', true);
}

$('#enddate').on('change', function(){
    durationCal();
    //var startdate = moment($('#startdate').val(), 'YYYY-MM-DD');
    //var enddate = moment($('#enddate').val(), 'YYYY-MM-DD');
   // $('[name="days"]').val(enddate.diff(startdate, 'days')+1);
})

function  durationCal() {
    if($('#startdate').val()=="" || $('#startdate').val()==null || $('#startdate').val()==undefined){
        $('#enddate').val("");
        bootbox.alert({
            size: 'medium',
            title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
            message:"Select Start Date."
        });
        return ;
    }
    var startdate=new Date($('#startdate').val());
    var enddate=new Date($('#enddate').val());
    if(Date.parse(startdate) > Date.parse(enddate))
    {
        $('#enddate').val("");
        bootbox.alert({
            size: 'medium',
            title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
            message:"End Date must be greater than Start Date."
        });
    }
    else
    {
        var duration = ((enddate.getTime()-startdate.getTime())/(86400000))+1;
        $('[name="days"]').val(duration);
    }

}




function saveCalendar(){
    var calendarData = $('#addCalendar').serialize();
    url = "/setuphrms/payrollsetup/payrollcalendar/addpayrollcalendar";
    $.ajax({
        url: url,
        type: "POST",
        data: calendarData,
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
            document.getElementById('addNewCalendar').style.display='none';
            calendarSearch();
        },
        error: function (response) {
            alert(JSON.parse(response.responseText));
        }
    });
}

function calendarSearch(){
    if($('#paygroupSelect option:selected').val() == ""){
        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-exclamation-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
            message: 'Please select payroll group.'
        });
        return;
    }
    if($.fn.DataTable.isDataTable('#payCalanderList')){
        $("#payCalanderList").DataTable().destroy();
    }
    var payCalendarDataTable = $("#payCalanderList").DataTable({
        'columnDefs': [
            {'targets': [3,4,5,6,7,8], 'orderable': false}
            //{'targets':[6], 'className': 'text-center'}
        ]
    });
    getCalendarDataAndPopulate($('#paygroupSelect option:selected').val(), payCalendarDataTable);
    $('#resultSec').css('display', 'none');
    $('#noData').css('display', 'none');
    $('#jsonLoader').css('display', 'block');
}

function getCalendarDataAndPopulate(payGroupId, payCalendarDataTable){
    $.ajax({
        type: 'GET',
        url: '/setuphrms/payrollsetup/payrollcalendar/calendar/'+payGroupId,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            jsonData = result;
            caldata = result;
            populateDataTable(jsonData, payCalendarDataTable);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    })
}

function populateDataTable(data, payCalendarDataTable){
    payCalendarDataTable.clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#resultSec').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            $("#payCalanderList").dataTable().fnAddData([
                dat.groupname,
                dat.calendarcode,
                dat.calendarperiod,
                dat.startdate,
                dat.enddate,
                dat.days,
                dat.selecttype,
                dat.status,
                '<i class="fa fa-eye" style="cursor: pointer" id="view" aria-hidden="true" onclick="viewcal('+i+');">'
                //'<div class="w3-btn w3-blue w3-round" style="cursor: pointer" id="view" aria-hidden="true" onclick="viewcal('+i+');">View</div>'
                /*"<a onClick='displayQuestionDetails(\""+dat.courseid+"\", \""+dat.topicid+"\", \""+dat.typeid+"\")' class='editCourse' style='cursor: pointer; font-size: 20px;'><i class='fa fa-eye' aria-hidden='true'></i></a>"*/
                /*"<a onclick='displayAddQuestionsForm(\""+dat.courseid+"\", \""+dat.topicid+"\", \""+dat.topicdescr+"\")' class='editCourse' style='cursor: pointer; font-size: 20px;'><i class='fa fa-plus-circle' aria-hidden='true'></i></a>"*/
            ]);
        }
       // viewdeclartaion('+dat.personnumber+');
        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
    }
};

function viewcal(dex){
    document.getElementById('viewCalendar').style.display='block';
    $("#vcalendarcode").val(caldata[dex].calendarcode);

    $('#vfinancialyear').children('option[id="1"]').text(caldata[dex].financialyear);
    $('#vfinancialyear').children('option[id="1"]').prop('selected',true);

    // alert("FIN YEAR "+caldata[dex].financialyear+" Radio "+caldata[dex].selecttype);

    $("#vcalendarperiod").val(caldata[dex].calendarperiod);
    $("#vstartdate").val(caldata[dex].startdate);
    $("#venddate").val(caldata[dex].enddate);
    $("#vdays").val(caldata[dex].days);

    $("#"+caldata[dex].selecttype).prop('checked', true);

    $("#vcalendarcode").prop("disabled",true);
    $("#vfinancialyear").prop("disabled",true);
    $("#vcalendarperiod").prop("disabled",true);
    $("#vstartdate").prop("disabled",true);
    $("#venddate").prop("disabled",true);
    $("#vdays").prop("disabled",true);
    $("#ACT").prop("disabled",true);
    $("#RET").prop("disabled",true);
    $("#ADH").prop("disabled",true);

}