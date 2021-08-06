$(document).ready(function(){
var filelocation = "/bulkuploadtemplate/NhaBuklTemplate.xlsx";	
$("#downloadnhaTemplate").attr("href","/getContent?location="+filelocation);
});



function initializeFileUploads() {
    $('.file-upload').change(function () {
        var file = $(this).val();
        $(this).closest('.input-group').find('.file-upload-text').val(file);
    });
    $('.file-upload-btn').click(function () {
        $(this).find('.file-upload').trigger('click');
    });
    $('.file-upload').click(function (e) {
        e.stopPropagation();
    });
}


// On document load:
$(function() {
    initializeFileUploads();
});

/*$(document).ready(function(){
    var _hrefappr=$("#downloadLwpTemplate").text();
    if(_hrefappr!=undefined && _hrefappr!="" && _hrefappr!=null){
        _hrefappr=_hrefappr.replaceAll('/', "FORWARD_SLASH");
        _hrefappr=_hrefappr.replaceAll('\\','BACKWARD_SLASH');
        _hrefappr=_hrefappr.replaceAll('.','EXT_DOT');
        $("#downloadLwpTemplate").attr("href","/getContent?location="+"/src/main/resources/static/bulkuploadtemplate/NhaBuklTemplate.xlsx");

    }
});*/


function uploadNhaBulkData() {
    debugger;
     var form = $('#NHA_BULK_UPLOAD')[0];
    var data = new FormData(form);
    var file = $('#myFile').val();

    if (file == "" || file == null) {
        flag = 'no';
        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
            message: "Please attached the template before upload.",
        });
        return;
    }
    $("#NHA_BULK_LOADER").css("display","block");
    $.ajax({
        url: "/bulkupload/uploadNha/BulkData/",
        enctype: "multipart/form-data",
        type: "POST",
        //data: document.getElementById("myFile"),
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 600000,
        success: function (data) {
            $("#NHA_BULK_LOADER").css("display","none");
            if (data.status == "Success") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message: " NHA Successfully uploaded.",
                    callback:function(){
                        $('#replace_div').load("/bulkupload/nha");
                    }
                });

            }
            if (data.status == "AlreadyExist") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
                    message: "Some of the Period in Excel is already applied in NHA.",
                    /*callback: function () {
                        window.location.assign("/home");
                    }*/
                    callback:function(){
                        $('#replace_div').load("/bulkupload/nha");
                    }

                });

            }
            if (data.status == "RollBack") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
                    message: "NHA Roll back.",
                    /*callback: function () {
                        window.location.assign("/home");
                    }*/
                    callback:function(){
                        $('#replace_div').load("/bulkupload/nha");
                    }
                });

            }

            if (data.status == "Error") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
                    message: "Something went wrong please try again.",
                    callback: function () {
                        window.location.assign("/home");
                    }
                });
            }

        },
        error: function (response) {
            $("#NHA_BULK_LOADER").css("display","none");
            alert(JSON.parse(response.responseText));
        }

    });
}