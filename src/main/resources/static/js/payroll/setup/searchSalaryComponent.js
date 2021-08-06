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

$('#componentSearch').on('click', function () {
    if($.fn.DataTable.isDataTable('#salaryComponentList')){
        $("#salaryComponentList").DataTable().destroy();
    }
    var salaryComponentList = $("#salaryComponentList").DataTable({
        'columnDefs': [
            {'targets': [4], 'orderable': false},
            {'targets': [4], 'className': 'text-center'}
        ]
    });
    loadComponentType(salaryComponentList);
    $('#resultSec').css('display', 'none');
    $('#noData').css('display', 'none');
    $('#jsonLoader').css('display', 'block');
});

function loadComponentType(salaryComponentList){
    var jsonUrl = "/setuphrms/payrollsetup/salarycomponents/searchsalarycomponent";
    $.ajax({
        type: 'POST',
        url: jsonUrl,
        dataSrc: '',
        data: {
            "componentid": $('#componentTypeSelect').val(),
            "name": $('#componentName').val()
        },
        dataType: 'json',
        success: function(data){
            jsonData = data;
            populateDataTable(jsonData, salaryComponentList);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};

function populateDataTable(data, salaryComponentList){
    salaryComponentList.clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#resultSec').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            $("#salaryComponentList").dataTable().fnAddData([
                dat.salarycompid,
                dat.name,
                dat.componenttype,
                dat.descr,
                "<a class='editCourse' style='cursor: pointer; font-size: 15px;'><i class='fas fa-edit' aria-hidden='true'></i></a>"
                //"<a class='editCourse' style='cursor: pointer; font-size: 20px;'><i class='fa fa-plus-circle' aria-hidden='true'></i></a>"
            ]);
        }

        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
    }
};

function openAddComponent(){
    var url = "/setuphrms/payrollsetup/salarycomponents/addsalarycomponent";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
}