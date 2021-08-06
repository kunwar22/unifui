

$('#createDBI').on('click', function (e) {
    var url = $(this).attr("rm");
    $('#replace_div').html("<div style='margin-left:-16px; margin-right:-16px; margin-top:0px; width: 100%; margin-top: 200px; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: gray;'></i></div>");
    $('#replace_div').load(url);
});

$("#DBI_SEARCH").on('click', function (e) {
    searchtitle = $("#SRCH_TITLE").val();
    loadDBIData();
    $('#dbiresultSec').css('display', 'none');
    $('#noDataDBI').css('display', 'none');
    $('#jsonLoaderPage').css('display', 'block');

});

$("#DBI_LIST").DataTable({
    'paging': false,
    'ordering': false
});

function loadDBIData() {
    $.ajax({
        type: 'POST',
        url: '/dbi/searchdbi',
        dataSrc: '',
        data: {
            "title": searchtitle
        },
        dataType: 'json',
        success: function (data) {
            jsonData = data;
            //console.log(jsonData);
            populateDBIDataTable(jsonData);
            $('#jsonLoaderPage').css('display', 'none');
        },
        error: function (e) {
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function populateDBIDataTable(data) {
    $("#DBI_LIST").DataTable().clear();
    var dataLength = Object.keys(data).length;
    //alert(dataLength);
    if (dataLength == 0) {
        $('#dbiresultSec').css('display', 'none');
        $('#noDataDBI').css('display', 'block');
        $('#jsonLoaderPage').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#DBI_LIST").dataTable().fnAddData([
                dat.title,
                dat.squery,
                dat.status,
                "<i class='fa fa-edit' aria-hidden='true' style='cursor: pointer' onclick=editDBIFunction(" + dat.id + ")></i>"
            ]);
        }
        $('#dbiresultSec').css('display', 'block');
        $('#noDataDBI').css('display', 'none');
        $('#jsonLoaderPage').css('display', 'none');
    }
}

function editDBIFunction(dbiid){
        var editurl='/dbi/getDBIById/'+dbiid;
        $('#replace_div').load(editurl);
}
