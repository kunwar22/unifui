$("#componentTypeSearch").on("click", function(){
    if($.fn.DataTable.isDataTable('#componentTypeList')){
        $("#componentTypeList").DataTable().destroy();
    }
    var componentTypeList = $("#componentTypeList").DataTable({
        'columnDefs': [
            {'targets': [3], 'orderable': false},
            {'targets': [3], 'className': 'text-center'}
        ]
    });
    loadComponentType(componentTypeList);
    $('#resultSec').css('display', 'none');
    $('#noData').css('display', 'none');
    $('#jsonLoader').css('display', 'block');
});
function loadComponentType(componentTypeList){
    var jsonUrl = "/setuphrms/payrollsetup/salarycomponents/searchcomponentstype";
    $.ajax({
        type: 'POST',
        url: jsonUrl,
        dataSrc: '',
        data: {
            "componentCode": $('#componentCode').val(),
            "componentName": $('#componentName').val()
        },
        dataType: 'json',
        success: function(data){
            jsonData = data;
            populateDataTable(jsonData, componentTypeList);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};
function populateDataTable(data, componentTypeList){
    componentTypeList.clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#resultSec').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            $("#componentTypeList").dataTable().fnAddData([
                dat.componentid,
                dat.code,
                dat.name,
                "<a class='editCourse' style='cursor: pointer; font-size: 15px;'><i class='fas fa-edit' aria-hidden='true'></i></a>"
                //"<a class='editCourse' style='cursor: pointer; font-size: 20px;'><i class='fa fa-plus-circle' aria-hidden='true'></i></a>"
            ]);
        }

        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
    }
};

function openAddComponentType() {
    var url = "/setuphrms/payrollsetup/salarycomponents/addcomponentstype";
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
}