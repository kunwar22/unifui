window.globalCounter = 1;
$(document).unbind();

function addrow() {

    var data = "";
    data = '<tr><td style="width:10%"><input  class="w3-input w3-border" name="createpermission[' + window.globalCounter + '].permissionname" id="txtName' + window.globalCounter + '" type="text" ></td><td style="width:10%"><input  class="w3-input w3-border" name="createpermission[' + window.globalCounter + '].createdby" id="txtName' + window.globalCounter + '" type="text" ></td><td style="width:5%"><input class="w3-btn w3-theme" id="delete" index="0" flg="permision"  type="button" value="x"/></td></tr>';
    $("#PERMSN_TBL tbody").append(data);


    $.each($('#PERMSN_TBL tr'), function (index, val) {

        $(this).find("td:eq(0) input[type='text']").attr('name', 'createpermission[' + (index - 1) + '].permissionname');
        $(this).find("td:eq(1) input[type='text']").attr('name', 'createpermission[' + (index - 1) + '].createdby');

        $(this).find("td:eq(2) input[type='button']").attr('index', (index - 1));

    });
}


function sendPermissionData() {

    $.ajax({
        type: 'POST',
        url: '../uniftools/security/permissionsroles/savePermission',
        data: $('#frmPerm').serialize(),
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        catch: false,
        success: function (result) {

            if(result.message == "Success"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Permission created Successfully.",
                    callback:function() {
                        $('#replace_div').load("/uniftools/security/permissionsroles/managepermissions");
                    }
                })
            }
            if(result.errorMessage == "Error"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Unable to create Permission.",
                    callback:function() {
                        $('#replace_div').load("/uniftools/security/permissionsroles/managepermissions");
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
                    $('#replace_div').load("/uniftools/security/permissionsroles/managepermissions");
                }
            })
        }

    });

}


////////////////*********************role Creation START*********************//////////////////////////
/*

function addRolerow() {

    var data = "";
    data = '<tr><td style="width:10%"><input style="zoom: 1.5;" class="w3-input w3-border" name="createrole[' + window.globalCounter + '].rolename" id="txtName' + window.globalCounter + '" type="text" ></td><td style="width:10%"><input style="zoom: 1.5;" class="w3-input w3-border" name="createrole[' + window.globalCounter + '].createdby" id="txtName' + window.globalCounter + '" type="text" ></td><td style="width:5%"><input class="w3-btn w3-theme" id="deleteRoleId" index="0" flg="role"  type="button" value="x"/></td></tr>';
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
        success: function (response) {
            alert("SUccess");
            // $('#fragmaent_container').html(response);
        },
        error: function (e) {
            alert(e);
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }

    });

}*/

////////////////*********************role Creation End*********************//////////////////////////

////////////////*********************Permission List Start***************/////////////////////////


function addrowpermissionlist() {

    var data = "";

    data = '<tr><td style="width:100%"><select id="CR_HR_COUNTRY' + window.globalCounter + '" class="w3-select w3-border" name="permissionid">' + $("#permissionList").html() + '</select><td style="width:5%"><input class="w3-btn w3-theme" id="deletepermissionlist" index="0" flg="prmsn"  type="button" value="x"/></td></tr>';
    $("#PERMSN_TBL tbody").append(data);

    $.each($('#PERMSN_TBL tr'), function (index, val) {

        $(this).find("select").attr('name', 'permissionid');

    });
}

$(document).unbind();
$(document).on("click", "#deletepermissionlist", function () {
    //$("table").row($(this).parents('tr')).remove().draw(false);
    $(this).parents("tr").remove();

    $.each($('#PERMSN_TBL tr'), function (index, val) {

        $(this).find("select").attr('name', 'permissionid');

    });


});


function sendPermissionMapData() {

    alert($('#frmPermRoleMapCreate').serialize());
    $.ajax({
        type: 'POST',
        url: '../uniftools/security/permissionsrolesmapping/savePermissionrolesmapping',
        data: $('#frmPermRoleMapCreate').serialize(),
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        catch: false,
        success: function (result) {

            if(result.message == "Success"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Permission role mapping created Successfully.",
                    callback:function() {
                        $('#replace_div').load("/uniftools/security/permissionsrolesmapping/managepermissionsrolesmapping");
                    }
                })
            }
            if(result.errorMessage == "Error"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Unable to create Permission role mapping.",
                    callback:function() {
                        $('#replace_div').load("/uniftools/security/permissionsrolesmapping/managepermissionsrolesmapping");
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
                    $('#replace_div').load("/uniftools/security/permissionsrolesmapping/managepermissionsrolesmapping");
                }
            })
        }

    });

}


$('#permCreate').on('click', function (e) {

    var url = $(this).attr("rm");
    $('#replace_div').load(url);

});

$('#permrolemapCreate').on('click', function (e) {

    var url = $(this).attr("rm");
    $('#replace_div').load(url);

});

function go_searchh(e) {

    var url = $(e).attr("rm");
    $('#replace_div').load(url);
}



$('#PERMISSION_ROLE_CANCEL').on('click', function () {
    url = '/uniftools/security/permissionsrolesmapping/managepermissionsrolesmapping';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});



/***********************added by rajat start here on 14-12-2020***************************************/
$(document).on("click","#delete",function(){
    //$("table").row($(this).parents('tr')).remove().draw(false);
    var dex=$(this).attr('index');
    var flg=$(this).attr('flg');

    $(this).parents("tr").remove();
    removeRow(dex,flg);
    $(this).parents("tr").remove();
    $.each($('#PERMSN_TBL tr'), function (index, val) {

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




$(document).ready(function () {

    loadPermissionData();
    loadRoleData()
});


/*$(function() {
    $("#PERMISSION_LIST").DataTable({
        'columnDefs': [{
            'targets': [3,4],
            'orderable': false
        }]
    });*/

function loadPermissionData() {

    $.ajax({
        type: "GET",
        url: "/uniftools/security/permissionsroles/getPermissionList",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            //console.log("in search data...success....");
            populatePermissionSearchTable(result);
        },
        error: function (e) {
            //console.log("in search data...error....");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}

function populatePermissionSearchTable(data) {
    $("#PERMISSION_LIST").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultPermission').css('display', 'none');
        $('#noDataPermission').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#PERMISSION_LIST").dataTable().fnAddData([
                dat.permissionid,
                dat.permissionname,
                dat.isactive,
            ]);
        }
        $('#resultPermission').css('display', 'block');
        $('#noDataPermission').css('display', 'none');
    }

}

$('#PERM_CANCEL').on('click', function () {
    url = '/uniftools/security/permissionsroles/managepermissions';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});




/// Role search and create start here

function loadRoleData() {

    $.ajax({
        type: "GET",
        url: "/uniftools/security/permissionsroles/getPermissionList",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            //console.log("in search data...success....");
            populatePermissionSearchTable(result);
        },
        error: function (e) {
            //console.log("in search data...error....");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}

function populateRoleSearchTable(data) {
    $("#PERMISSION_LIST").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultPermission').css('display', 'none');
        $('#noDataPermission').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#PERMISSION_LIST").dataTable().fnAddData([
                dat.permissionid,
                dat.permissionname,
                dat.isactive,
            ]);
        }
        $('#resultPermission').css('display', 'block');
        $('#noDataPermission').css('display', 'none');
    }

}



/******************************added by rajat end here on 14-12-2020*********************************/