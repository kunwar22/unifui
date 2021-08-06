$(function() {
	/*$('#providedRoleList').DataTable({
		'columnDefs': [{
			'targets': [0,1],
			'orderable': false
		}]
	});*/

	
	function populateDataTable(data){
		$("#providedRoleList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			//alert("no roles assigned yet");
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#providedRoleList").dataTable().fnAddData([
					dat.roleId,
					dat.rolename,
					dat.createdby,
					dat.userid,
					"<ed rm='/uniftools/security/userprofiles/userprofile/edit/userprofile/"+dat.roleId+"' class='editUser' style='cursor: pointer'><i class='fa fa-edit' ></i></ed>"

				]);
			}
		}
	}
	
});

/*************added by rajat strat here ******************************** */
function assignUserRoles(){
$('#addUserRole').css('display', 'block');
 $.ajax({
        type: 'GET',
        url: '/uniftools/security/userprofiles/userprofiles/getuserRoles',
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
			populateRolesPopupTable(result)    
			//mydata(result) 
			  $('#roles').html(result.message);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });

}
function populateRolesPopupTable(data) {

    $("#popupRoleList").DataTable().clear();
    $("#popupRoleList").DataTable().columns.adjust();
   

    var dataLength = data.length;
    //console.log("Here------------"+dataLength);
    if (dataLength == 0) {
        $('#resultpopup').css('display', 'none');
        $('#noDatapopup').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
             var dat = data[i];
            $("#popupRoleList").dataTable().fnAddData([
               	'<input  type="checkbox"  class="w3-check " name="actionids" value="' + dat.roleId + '" >',
				dat.roleId,
                dat.rolename
            ]);
        }
        $('#resultpopup').css('display', 'block');
        $('#noDatapopup').css('display', 'none');
    }
}

$("#CR_ROL_POP_CNCL").click(function() {
	$("#actionids").val("");
	
});

   

/********************************************************* */
$(document).ready(function(){
   loadUsersRoles()

});




var userid=$('#userid').val();

function loadUsersRoles() {
 
    $.ajax({
        type: "GET",
        url: "/uniftools/security/userprofiles/userroles/loaduserRoles/"+userid,
        contentType: "application/json",
        dataType: "json",
        success: function(result) {
            //console.log("in search data...success....");
            populateuserRolesTable(result);
        },
        error: function(e) {
            //console.log("in search data...error....");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}



function populateuserRolesTable(data) {
 
    $("#providedRoleList").DataTable().clear();
    $("#providedRoleList").DataTable().columns.adjust();
   

    var dataLength = data.length;

    if (dataLength == 0) {
        $('#resultroles').css('display', 'none');
        $('#noDatauser').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
             var dat = data[i];
            $("#providedRoleList").dataTable().fnAddData([
                dat.roleId,
                dat.rolename,           
               
            ]);
        }
        $('#resultroles').css('display', 'block');
        $('#noDatauser').css('display', 'none');
    }
}




function addroleUser(){

	
	var formData = $('#roleMapping').serialize();
	
		$.ajax({
        url: "/uniftools/security/userprofiles/userprofiles/addroles",
        type: "POST",
		cache: false,
        data: formData,
		processData: false,
		dataType: "json",
		success:function(result){
			//	alert(JSON.stringify(result));
			//$('#replace_div').html(result);
			if(result.message){	
					bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message: 'Role assign successfully.',
							callback: function() {
								$('#replace_div').load("/uniftools/security/userprofiles/userprofiles");
							}						
						});
				}
				},
		error:function(e){
			//alert(JSON.stringify(e));
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Something went wrong Please try again.",
					callback: function() {
						$('#replace_div').load("/uniftools/security/userprofiles/userprofiles");
					}						
				});
				}		
		});
	
}