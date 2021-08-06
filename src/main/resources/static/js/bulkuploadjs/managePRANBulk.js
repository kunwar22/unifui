$(document).ready(function(){
var filelocation = "/bulkuploadtemplate/PRANBuklTemplate.xlsx";	
$("#downloadPRANTemplate").attr("href","/getContent?location="+filelocation);
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



function uploadPRANBulkData() {
    debugger;
     var form = $('#PRAN_BULK_UPLOAD')[0];
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
    $("#PRAN_BULK_LOADER").css("display","block");
    $.ajax({
        url: "/Hirebulkupload/uploadPRANNumber/BulkData",
        enctype: "multipart/form-data",
        type: "POST",
        //data: document.getElementById("myFile"),
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        timeout: 600000,
        success: function (data) {
            $("#PRAN_BULK_LOADER").css("display","none");
            if (data.status == "Success") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message: " PRAN Number Successfully uploaded.",
                    callback:function(){
                        $('#replace_div').load("/Hirebulkupload/PRANNumberUpload");
                    }
                });

            }
            if (data.status == "AlreadyExist") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
                    message: "Some of the Period in Excel is already applied in PRAN Number.",
                    /*callback: function () {
                        window.location.assign("/home");
                    }*/
                    callback:function(){
                          $('#replace_div').load("/Hirebulkupload/PRANNumberUpload");
                    }

                });

            }
            if (data.status == "RollBack") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
                    message: "PRAN Number Roll back.",
                    /*callback: function () {
                        window.location.assign("/home");
                    }*/
                    callback:function(){
                        $('#replace_div').load("/Hirebulkupload/PRANNumberUpload");
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
            $("#PRAN_BULK_LOADER").css("display","none");
            alert(JSON.parse(response.responseText));
        }

    });
}