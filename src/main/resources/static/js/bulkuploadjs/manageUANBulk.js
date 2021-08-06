$(document).ready(function(){
var filelocation = "/bulkuploadtemplate/UANBuklTemplate.xlsx";	
$("#downloadUANTemplate").attr("href","/getContent?location="+filelocation);
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



function uploadUANBulkData() {
    
     var form = $('#UAN_BULK_UPLOAD')[0];
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
    $("#UAN_BULK_LOADER").css("display","block");
    $.ajax({
        url: "/Hirebulkupload/uploadUANumber/BulkData",
        enctype: "multipart/form-data",
        type: "POST",
        //data: document.getElementById("myFile"),
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 600000,
        success: function (data) {
            $("#UAN_BULK_LOADER").css("display","none");
            if (data.status == "Success") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message: " UAN Number Successfully uploaded.",
                    callback:function(){
                        $('#replace_div').load("/Hirebulkupload/UANNumberUpload");
                    }
                });

            }
            if (data.status == "AlreadyExist") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
                    message: "Some of the Period in Excel is already applied in UAN Number.",
                    /*callback: function () {
                        window.location.assign("/home");
                    }*/
                    callback:function(){
                         $('#replace_div').load("/Hirebulkupload/UANNumberUpload");
                    }

                });

            }
            if (data.status == "RollBack") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
                    message: "UAN Roll back.",
                    /*callback: function () {
                        window.location.assign("/home");
                    }*/
                    callback:function(){
                         $('#replace_div').load("/Hirebulkupload/UANNumberUpload");
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
            $("#UAN_BULK_LOADER").css("display","none");
            alert(JSON.parse(response.responseText));
        }

    });
}