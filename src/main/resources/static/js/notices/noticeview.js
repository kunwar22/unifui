
$( document ).ready(function() {
    loadnoticedata();
});
/*

$("#NOTICE_TBL").DataTable({
    'columnDefs': [{
        'targets': [0,1,2,3,4],
        'orderable': false
    }]
});
*/

$("#NOTICE_TBL").DataTable({
    'ordering':false
});

var jsonUrl = '/notices/getallnotices';

function loadnoticedata(){
    loadNData();
    $('#NOTICE_BLOCK').css('display', 'none');
}

function loadNData(){
    $.ajax({
        type: 'POST',
        url: jsonUrl,
        dataSrc: '',
        dataType: 'json',
        success: function(data){
            jsonData = data;
            populateNDataTable(jsonData);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function populateNDataTable(data){
    $("#NOTICE_TBL").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#NOTICE_BLOCK').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            var btn='';
            if(dat.status!=null){
                btn='<div>Declared <i class="fas fa-circle" style="color: red"></i></div>';
            }else {
                btn='';
            }
            $("#NOTICE_TBL").dataTable().fnAddData([
                dat.orderno,
                dat.subject,
                dat.published_date,
                dat.type,
                dat.department,
                dat.status,
                '<a id="DOWNLOAD_LINK" href="/getContent?location='+dat.fileattachement+'" target="_blank" download><i class="fa fa-download" aria-hidden="true"></i></a>'
            ]);
        }
        $('#NOTICE_BLOCK').css('display', 'block');
        $('#noData').css('display', 'none');
    }
}
