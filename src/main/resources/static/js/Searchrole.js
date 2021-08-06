
$('#roleCreate').on('click', function (e) {

    var url = $(this).attr("rm");
    $('#replace_div').load(url);

});

$('#permroleCreate').on('click', function (e) {

    var url = $(this).attr("rm");
    $('#replace_div').load(url);

});
$('#navbarSearch').on('click', function (e) {

    var url = $(this).attr("rm");
    $('#replace_div').load(url);

});
$('#navbarmapSearch').on('click', function (e) {

    var url = $(this).attr("rm");
    $('#replace_div').load(url);

});


var roleName='';
$("#RoleSearch").click(function(){
    roleName = $('#SRCH_ROLE_NAME').val();
    loadRoleData();
    $('#resultSec').css('display', 'block');
});


    function loadRoleData(){
        $.ajax({
            type: 'POST',
            url: "/uniftools/security/permissionsroles/getRolesList",
            dataSrc: '',
            data: {
                "roleName": roleName,
            },
            dataType: 'json',
            success: function(data){
                jsonData = data;
                populateRoleSearchTable(jsonData);
            },
            error: function(e){
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }
function populateRoleSearchTable(data) {
    $("#ROLE_LIST").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultRoles').css('display', 'none');
        $('#noDataRoles').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#ROLE_LIST").dataTable().fnAddData([
                dat.roleId,
                dat.rolename,
                dat.isactive,
            ]);
        }
        $('#resultRoles').css('display', 'block');
        $('#noDataRoles').css('display', 'none');
    }

}
