/*$(document).ready(function(){
	//debugger;
	var roleurl = "/managesynchronization/getRoleInfo";
	
	$.ajax({
		type: 'GET',
		url: roleurl,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			populateRoles(result);
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
		}
	});
});
*/

var clickedboxes = 0;
var inputs ="";
function populateRoles(data) {
	//debugger;
	$("#allottedroles").DataTable().clear();
	$("#divRole").css('display', 'none');
	var dataLength = data.length;
	if(dataLength == 0) {
		$('#divRole').css('display', 'none');
		$('#bulkRoleSave').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			var chkbtn = '';
			chkbtn = '<input  type="checkbox"  class="w3-check checkClickOk" onclick="handleClick(this)" name="userrolemapping['+i+']" value="on" checked ></td>';
			$("#allottedroles").dataTable({
				scrollY: '50vh',
				scrollCollapse: true,
				paging: false,
				destroy: true,
				searching: false
			}).fnAddData([
				chkbtn +'<input type="hidden" name="createUserRole[' + i + '].rolemapid" value="' + dat.rolemapid + '">',
				dat.loginid + '<input type="hidden" name="createUserRole[' + i + '].loginid" value="' + dat.loginid + '">',				
				dat.roleid + '<input type="hidden" name="createUserRole[' + i + '].roleid" value="' + dat.roleid + '">', 
				dat.rolename + '<input type="hidden" name="createUserRole[' + i + '].rolename" value="' + dat.rolename + '">' +(dat.createdby == null ? "" : dat.createdby) + '<input type="hidden" name="createUserRole[' + i + '].createdby" value="' + dat.createdby + '">'
			]);
		}
		$(".dataTables_scrollHeadInner").removeAttr("style");
		$(".dataTable").removeAttr("style");
		$('#divRole').css('display', 'block');	
		$('#bulkRoleSave').css('display', 'block');	
		$('#noData').css('display', 'none');
	}
}

function checkuncheckAll(obj) {
	debugger
	clickedboxes = 0;
	if(!obj.checked) {
		clickCount = true;
	} else {
		clickCount = false;
	}
	inputs = $(".checkClickOk");
	if($('#checkuncheckRolebtn').prop("checked")) {
		for(i = 0; i < inputs.length; i++) {
			inputs[i].checked = true;
			
		}
	} else {
		for(i = 0; i < inputs.length; i++) {
			inputs[i].checked = false;			
		}
		$("#bulkRoleSave").css('display','none');
	}

for(var j = 0;j<inputs.length;j++)
{
	//debugger;
	if(inputs[j].checked == true)
	{
		clickedboxes++;
	}
}

console.log("clicked boxes count ===> "+clickedboxes);

if(clickedboxes == 0)
{
	$("#bulkRoleSave").css('display','none');
}
else
{
	$("#bulkRoleSave").css('display','block');
}
}

function handleClick(obj) {
	//debugger;
	
	$("#checkuncheckRolebtn").prop('checked',false) ;
	
	/*var numberOfChecked = $('input:checkbox:checked').length;
	
	if(numberOfChecked < 1) {
		$("#bulkRoleSave").css('display','none');
		}
		else
		{
			$("#bulkRoleSave").css('display','block');
		}
		*/
	if(!obj.checked) {
		obj.value = "off";
	} else {
		obj.value = "on";
	}
}

function openTab(evt, rightpane) {
    //debugger;

    x = document.getElementsByClassName("rightpane");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace("w3-theme", "");
    }
    document.getElementById(rightpane).style.display = "block";
    evt.currentTarget.className += " w3-theme";
}

function disableerr()
{
	$("#mapnameerr").css("display","none");
}


function validate()
{
	var mapname = $("#mappingname option:selected").text().trim();
	
	if(mapname == "")
	{
		$("#mapnameerr").css("display","inline");
	}
}

function bulkRoleSave()
{
	//debugger;
	
	//var createdBy = $("#createdbyuser").val();
	//var checkClick = document.getElementsByClassName("checkClickOk");
	
	var numberOfChecked = $('input:checkbox:checked').length;
	
	if(numberOfChecked < 1) {
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Alert</i>',
			message: 'Please select any Role before Saving.',
		});
		return;
	}
	
	var formData = $('#roleMappings').serialize();
	console.log(formData);
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	
	$.ajax({
		url: "/managesynchronization/submitRoles",
		type: "POST",
		cache: false,
		data: formData,
		processData: false,
		dataType: "json",
		success: function(result) {
				if(result == "Success")
				{
					bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message: 'Role Assigned Successfully.',
					callback: function() {
						window.location = "/selfservice";
						//$('#replace_div').load("/mss/manageapprovalactions");
					}
				});
			}
			
		},
		error: function(e) {
			bootbox.alert({
				size: 'medium',
				title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message: "Something went wrong Please try again.",
			});
		}
	});
	
	
}

var myVar;

function myFunction() {
    //debugger;
    document.getElementById("SYNC_LOADER").style.display = "block";
    myVar = setTimeout(syncRole, 3000);
}

function syncRole()
{	//debugger;	
	validate();	
			
	var mapname = $("#mappingname option:selected").text().trim();
	
	$.ajax({
		type: 'GET',
		url: '/managesynchronization/syncRole/'+mapname,		
		processData:false,
		catch:false,
		success: function(data){
			document.getElementById("SYNC_LOADER").style.display = "none";		
			populateRoles(data);
		},
		error: function(e){
			document.getElementById("SYNC_LOADER").style.display = "none";	
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Unable to synchronize role"				
				});
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
});
}

function syncLogin()
{
	//debugger;
	//var fd = $("#SYNC_LOGIN").serialize();
	var id = new Array();
	var loginid = new Array();
	var emplid = new Array();
	var emailid = new Array();
	var createdby = new Array();
	
	var url = "";
	
	
	$.each($("#usrdet tbody tr"), function (index) {
		id.push($("#id"+index).val());
		loginid.push($("#loginid"+index).val());
		emplid.push($("#emplid"+index).val());
		emailid.push($("#emailid"+index).val());
		createdby.push($("#createdby"+index).val());
		});
	
	/*alert(id.length);
	alert(loginid);
	alert(emplid);
	alert(emailid);
	alert(createdby);
*/	

url = "/managesynchronization/syncLogin/"+id+"/"+loginid+"/"+emplid+"/"+emailid+"/"+createdby;

$.ajax({
		url: url,
	    type: "POST",
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data)
		{
			//debugger;
			console.log(data.length);
			if(data.length == 0)
				$('#usrdet').css('display','none');
			else
				$('#usrdet').css('display','none');
			for(var i = 0; i<data.length;i++)
			{
				bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:data[i].message
							/*,
							callback:function(){
								$('#replace_div').load("/manageroleassign/searchrolemapping");
							}*/
						});
				console.log();
				
			}
		},
		error: function(response)
		{
			console.log(JSON.parse(response.responseText));
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Something went wrong. Please try again."				
				});
		}
	});
}