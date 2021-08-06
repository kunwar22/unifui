$('#ROLE_CANCEL').on('click', function () {
    url = '/uniftools/security/permissionsroles/manageroles';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});



function addRolerow() {

    var data = "";
    data = '<tr><td style="width:50%"><input  class="w3-input w3-border" name="createrole[' + window.globalCounter + '].rolename" id="txtName' + window.globalCounter + '" type="text" ></td><td style="width:50%"><input  class="w3-input w3-border" name="createrole[' + window.globalCounter + '].createdby" id="txtName' + window.globalCounter + '" type="text" ></td><td style="width:5%"><input class="w3-btn w3-theme" id="deleteRoleId" index="0" flg="role"  type="button" value="x"/></td></tr>';
    $("#ROLE_TBL tbody").append(data);

    $.each($('#ROLE_TBL tr'), function (index, val) {

        $(this).find("td:eq(0) input[type='text']").attr('name', 'createrole[' + (index - 1) + '].rolename');
        $(this).find("td:eq(1) input[type='text']").attr('name', 'createrole[' + (index - 1) + '].createdby');

        $(this).find("td:eq(2) input[type='button']").attr('index', (index - 1));

    });
}

function sendRoleData() {

    $.ajax({
        type: 'POST',
        url: '../uniftools/security/permissionsroles/saveRole',
        data: $('#frmRole').serialize(),
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        catch: false,
        success: function (result) {

            if(result.message == "Success"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Roles created Successfully.",
                    callback:function() {
                        $('#replace_div').load("/uniftools/security/permissionsroles/manageroles");
                    }
                })
            }
            if(result.errorMessage == "Error"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Unable to create Roles.",
                    callback:function() {
                        $('#replace_div').load("/uniftools/security/permissionsroles/manageroles");
                    }
                })
            }

        },
        error: function (e) {
            bootbox.alert({
                size: 'medium',
                title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                message:"Something went wrong please try again.",
                callback:function() {
                    $('#replace_div').load("/uniftools/security/permissionsroles/manageroles");
                }
            })
        }

    });

}

$(document).on("click","#deleteRoleId",function(){
    //$("table").row($(this).parents('tr')).remove().draw(false);
    var dex=$(this).attr('index');
    var flg=$(this).attr('flg');

    $(this).parents("tr").remove();
    removeRow(dex,flg);
    $(this).parents("tr").remove();
    $.each($('#ROLE_TBL tr'), function (index, val) {

        $(this).find("td:eq(0) input[type='text']").attr('name', 'createrole[' + (index - 1) + '].rolename');
        $(this).find("td:eq(1) input[type='text']").attr('name', 'createrole[' + (index - 1) + '].createdby');

        $(this).find("td:eq(2) input[type='button']").attr('index', (index - 1));

    });

});


function removeRow(index,flg) {
    var jurl="../newperson/removechild/"+index+"/"+flg;
    $.ajax({
        type: 'GET',
        url: jurl,
        success: function(data){

        },
        error: function(e){
            //alert(JSON.stringify(e));
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}


