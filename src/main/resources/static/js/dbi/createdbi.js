
$('#DBICancel').on('click', function (e) {
    var url = $(this).attr("rm");
    $('#replace_div').html("<div style='margin-left:-16px; margin-right:-16px; margin-top:0px; width: 100%; margin-top: 200px; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: gray;'></i></div>");
    $('#replace_div').load(url);
});

function submitdbi(){
    var fd = $("#DBI_FORM").serialize();
    if(validateDBI()){
        $.ajax({
            url: "/dbi/submitdbi",
            type: "POST",
            data: fd,
            cache: false,
            contentType: "application/x-www-form-urlencoded",
            processData: false,
            success: function (result) {
                //alert(result);
                if (result == "Success") {
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message: "Database item created successfully.",
                        callback: function () {
                            $('#replace_div').load("/dbi/managedbi");
                        }
                    });
                } else if (result == "Update") {
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message: "Database item updated successfully.",
                        callback: function () {
                            $('#replace_div').load("/dbi/managedbi");
                        }
                    });
                } else {
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message: "Something went wrong."
                    });
                }

            },
            error: function (response) {
                alert(JSON.parse(response.responseText));
            }
        });
    }
}



function validateDBI(){

    var TITLE=$('#DBI_TITLE').val().trim();
    var QUERY=$('#DBI_QUERY').val().trim();
    var STATUS=$('#DBI_STATUS option:selected').val();


    $('#ERR_TITLE').text("");
    $('#ERR_STATUS').text("");
    $('#ERR_QUERY').text("");


    var c=0;

    if(TITLE===undefined || TITLE==="" || TITLE===null){
        $('#ERR_TITLE').text("Please enter Title.");
        c+=1;
    }
    if(QUERY===undefined || QUERY==="" || QUERY===null){
        $('#ERR_QUERY').text("Please enter Query.");
        c+=1;
    }
    if(STATUS===undefined || STATUS==0 || STATUS===null){
        $('#ERR_STATUS').text("Please select Status.");
        c+=1;
    }
    if(c!=0){
        //alert(c);
        return false;
    }
    return true;
}

