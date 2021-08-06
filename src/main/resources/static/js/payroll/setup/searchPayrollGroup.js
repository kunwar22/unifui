function openAddPayrollGroup(){
    url = "/setuphrms/payrollsetup/payrollelements/addpayrollgroups";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
}

function payGroupSearch(){
    if($.fn.DataTable.isDataTable('#payGroupList')){
        $("#payGroupList").DataTable().destroy();
    }
    var payGroupDataTable = $("#payGroupList").DataTable({
        'columnDefs': [
            {'targets': [1,2,3], 'orderable': false}
            //{'targets':[6], 'className': 'text-center'}
        ]
    });
    getPayGroupDataAndPopulate(payGroupDataTable);
    $('#resultSec').css('display', 'none');
    $('#noData').css('display', 'none');
    $('#jsonLoader').css('display', 'block');
}

function getPayGroupDataAndPopulate(payGroupDataTable){
    $.ajax({
        type: 'GET',
        url: '/setuphrms/payrollsetup/payrollelements/getpayrollgroup',
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            jsonData = result;
            populateDataTable(jsonData, payGroupDataTable);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    })
}

function populateDataTable(data, payGroupDataTable){
    payGroupDataTable.clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#resultSec').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            $("#payGroupList").dataTable().fnAddData([
                dat.paygroupid,
                dat.name,
                dat.descr,
                dat.createdby
                /*"<a onClick='displayQuestionDetails(\""+dat.courseid+"\", \""+dat.topicid+"\", \""+dat.typeid+"\")' class='editCourse' style='cursor: pointer; font-size: 20px;'><i class='fa fa-eye' aria-hidden='true'></i></a>"*/
            ]);
        }

        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
    }
};