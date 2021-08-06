function addrowmanager(tableid) {
    //debugger;
    var data = "";

	if(tableid == 'criteria_table')
	{
		data = '<tr class="mycrivalrow"><td class="thstyle">'+
		'<select id="criteria0" class="condtype w3-select w3-round w3-border"'+
		' onchange="bindCriteriaValues(this.id);"><option disabled selected></option>'+
		$(".condtype").html()+
		'</select><input type="hidden" name ="automappcriteria[0].criterianame" id = "condtypename0"/>'+
		'<input type="hidden" name="automappcriteria[0].criteriaid" id="condtype0" value="0"/>'+
		'<input type="hidden" name="automappcriteria[0].criteriacolumnname" id="condcol0"/></td><td class="thstyle">'+
		'<select name = "automappcriteria[0].criteriavalueid" id = "values" class = "values w3-select w3-round w3-border" onchange="getCriValName(this.id);" onload="bindCriteriaValues(\'criteria\');"></select>'+
		'<input type="hidden" name ="automappcriteria[0].criteriavaluename" id = "condtypevalue0"/></td>'+
		'<td style="width:20%"><input class="w3-btn w3-theme" id="deletecrit" type="button" value="x" onclick="deleterow(this,\'criteria_table\');"/></td></tr>';
		$("#"+tableid+" tbody").append(data);
		
		$.each($("#"+tableid+" tr"), function (index) {
	        //debugger;	        
	        $(this).find("td:eq(0)").find("select").attr('id', 'criteria' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(0)").attr('name', 'automappcriteria[' + (index - 1)+'].criterianame');
			$(this).find("td:eq(0)").find("input:eq(0)").attr('id', 'condtypename' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(1)").attr('name', 'automappcriteria[' + (index - 1)+'].criteriaid');
			$(this).find("td:eq(0)").find("input:eq(1)").attr('id', 'condtype' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(2)").attr('name', 'automappcriteria[' + (index - 1)+'].criteriacolumnname');
			$(this).find("td:eq(0)").find("input:eq(2)").attr('id', 'condcol' + (index - 1));
			$(this).find("td:eq(0)").find("select").attr('onchange','bindCriteriaValues(this.id,'+(index - 1)+')');

			$(this).find("td:eq(1)").find("select").attr('name', 'automappcriteria[' + (index - 1)+'].criteriavalueid');
	        $(this).find("td:eq(1)").find("select").attr('id', 'values' + (index - 1));
			$(this).find("td:eq(1)").find("input").attr('name', 'automappcriteria[' + (index - 1)+'].criteriavaluename');
			$(this).find("td:eq(1)").find("input").attr('id', 'condtypevalue' + (index - 1));
			$(this).find("td:eq(1)").find("select").attr('onchange','getCriValName(this.id,'+(index - 1)+')');
			$(this).find("td:eq(1)").find("select").attr('onload','bindCriteriaValues(\'criteria'+(index - 1)+'\','+(index - 1)+')');
	    });
	}
	else if(tableid = 'role_table')
	{
		data = '<tr class="myrolerow">'+
				'<td><select name="automappingrole[0].rolesid" id="usrroles" class="w3-select w3-round w3-border" onchange="getRoleName(this.id);">'+
				'<option disabled selected></option>'+
				$(".usrroles").html()+
				'</select><input type="hidden" name ="automappingrole[0].rolename" id = "usrrolesname0"/>'+
				'<input type="hidden" name="automappingrole[0].automapproleid" id = "automapproleid0" value="0"/></td>'+
				'<td style="width:10%"><input class="w3-btn w3-theme" id="deleterol" type="button" value="x" onclick="deleterow(this,\'role_table\');"/></td></tr>';

	    $("#"+tableid+" tbody").append(data);
	
	    $.each($("#"+tableid+" tr"), function (index) {
	        //debugger;
	        $(this).find("td:eq(0)").find("select").attr('name', 'automappingrole[' + (index - 1)+'].rolesid');
	        $(this).find("td:eq(0)").find("select").attr('id', 'usrrolesid' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(0)").attr('name', 'automappingrole[' + (index - 1)+'].rolename');
	        $(this).find("td:eq(0)").find("input:eq(0)").attr('id', 'usrrolesname' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(1)").attr('name', 'automappingrole[' + (index - 1)+'].automapproleid');
	        $(this).find("td:eq(0)").find("input:eq(1)").attr('id', 'automapproleid' + (index - 1));
			$(this).find("td:eq(0)").find("select").attr('onchange','getRoleName(this.id,'+(index - 1)+')');
	    });
	}

}

function deleterow(id,tableid)
{
	//debugger;
	var dex = $(id).attr("dex");
	
	if(tableid == 'criteria_table')
	{
		$(id).parents("tr").remove();
		//removedata(dex,'criteria_table');
		//$(id).parents("tr").remove();
		$.each($("#"+tableid+" tr"), function (index) {
	        //debugger;
	        $(this).find("td:eq(0)").find("select").attr('id', 'criteria' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(0)").attr('name', 'automappcriteria[' + (index - 1)+'].criterianame');
			$(this).find("td:eq(0)").find("input:eq(0)").attr('id', 'condtypename' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(1)").attr('name', 'automappcriteria[' + (index - 1)+'].criteriaid');
			$(this).find("td:eq(0)").find("input:eq(1)").attr('id', 'condtype' + (index - 1));
			$(this).find("td:eq(0)").find("input:eq(2)").attr('name', 'automappcriteria[' + (index - 1)+'].criteriacolumnname');
			$(this).find("td:eq(0)").find("input:eq(2)").attr('id', 'condcol' + (index - 1));
			$(this).find("td:eq(0)").find("select").attr('onchange','bindCriteriaValues(this.id,'+(index - 1)+')');
			
			$(this).find("td:eq(1)").find("select").attr('name', 'automappcriteria[' + (index - 1)+'].criteriavalueid');
	        $(this).find("td:eq(1)").find("select").attr('id', 'values' + (index - 1));
			$(this).find("td:eq(1)").find("input").attr('name', 'automappcriteria[' + (index - 1)+'].criteriavaluename');
			$(this).find("td:eq(1)").find("input").attr('id', 'condtypevalue' + (index - 1));
			$(this).find("td:eq(1)").find("select").attr('onchange','getCriValName(this.id,'+(index - 1)+')');
			
		});	
	}
	else if(tableid == 'role_table')
	{
		$(id).parents("tr").remove();
		//removedata(dex,'role_table');
		//$(id).parents("tr").remove();
		$.each($('#'+tableid+' tr'), function (index) {
			$(this).find("td:eq(0)").find("select").attr('name', 'automappingrole[' + (index - 1)+'].rolesid');
	        $(this).find("td:eq(0)").find("select").attr('id', 'usrrolesid' + (index - 1));
			$(this).find("td:eq(0)").find("select").attr('onchange','getRoleName(this.id,'+(index - 1)+')');
			$(this).find("td:eq(0)").find("input").attr('name', 'automappingrole[' + (index - 1)+'].rolename');
	        $(this).find("td:eq(0)").find("input").attr('id', 'usrrolesname' + (index - 1));
			
    	});
	}
	
}

function removedata(index,tabname)
{
	//debugger;
	var delurl = "";
	if(tabname == 'criteria_table')
	{
		delurl = "/manageroleassign/removecriteria/" + index;
	}
	else if(tabname == 'role_table')
	{
		delurl = "/manageroleassign/removerole/" + index;
	}
	
	
    $.ajax({
        type: 'GET',
        url: delurl,
        success: function (data) {
			//debugger;
            console.log("Success...");
        },
        error: function (e) {
            //alert(JSON.stringify(e));
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}
function bindCriteriaValues(id,num)
{
	//debugger;
//	var criteriaid = $("#"+id+" option:selected").val();	
	var criterianame=$("#"+id+" option:selected").text().trim();
	var criteriacolumnname = $("#"+id+" option:selected").attr("ctcol");
	
	var str="<option selected disabled></option>";
	$.ajax({
		type: 'GET',
		url: '/manageroleassign/getCriteriavals/'+criterianame,		
		processData:false,
		catch:false,
		success: function(data){
			/*console.log("Data ::: "+data);*/
			//debugger;
			data.forEach(function(n)
			{
				str+="<option value='"+n.id+"'>"+n.description+'</option>';
			});
			
			/*$('#CRITERIA_BLOCK').css('display', 'block');
			$('#divcriteria').css('display', 'block');*/
			$('#values'+num).html(str);
			$("#condtypename"+num).val(criterianame);
			$("#condcol"+num).val(criteriacolumnname);
			
		
		},
		error: function(e){
			alert(e);
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
});	
}

function getCriValName(id,num)
{
	//debugger;
	var criteriavaluename=$("#"+id+" option:selected").text().trim();
	$("#condtypevalue"+num).val(criteriavaluename);
}

function getRoleName(id,num)
{
	//debugger;
	var rolename=$("#"+id+" option:selected").text().trim();
	$("#usrrolesname"+num).val(rolename);
}

function addCriteria()
{
	//debugger;
		
	$.each($("#criteria_table tr"), function (index) {
	        //debugger;
	        var crt = $(this).find("td:eq(0)").find("select").children("option:selected").text();
			
			var val = $(this).find("td:eq(1)").find("select").children("option:selected").text();
			
			var str = "";
			
			if(crt!="" && val!="")
			{
				str = '<tr><td>'+crt+'</td><td>'+val+'</td><td style="width:10%"><input class="w3-btn w3-theme" id="deletemgr" type="button" value="x" onclick="deleterow(this,\'crvaldata\');"/></td></tr>';
				
				$("#crvaldata tbody").append(str);
			}
	        /*$(this).find("td:eq(0)").find("select").attr('id', 'condtype' + (index - 1));

			$(this).find("td:eq(1)").find("select").attr('name', 'values[' + (index - 1)+']');
	        $(this).find("td:eq(1)").find("select").attr('id', 'values' + (index - 1));

			$(this).find("td:eq(0)").find("select").attr('onchange','bindCriteriaValues(this.id,'+(index - 1)+')');*/
		});
}

// function for saving role mapping

function saveMapping(save_mode)
{
	//debugger;
	
	var fd = $("#ROLE_MAPPING").serialize();
	var url = "";
	if(save_mode==='save')
	{
		url = "/manageroleassign/saveRoleMapping"
	}
	else if(save_mode==="update")
	{
		url = "/manageroleassign/updateRoleMapping";
	}
	console.log("Serialized Data=====> "+fd);
	$.ajax({
		url: url,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data)
		{
			$('#replace_div').html(data);
			if($("#status").val()=="Success")
			{
				bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully saved Role Mapping",
							callback:function(){
								$('#replace_div').load("/manageroleassign/searchrolemapping");
							}
						});
			}
			else
			{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Fill all the mandatory fields and try again"				
				});		
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


function newMapping(){
	/*alert("in");*/
	var url = "/manageroleassign/assignrole";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
        $('#replace_div').load(url);
}

function backBtnFunc(){
	var url = "/manageroleassign/searchrolemapping";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

/* Search Data JS starts*/


function loadRoleMappingData()
{
	//debugger;
	var mappingname = $("#mappingname").val();
	var jsonSearchRoleUrl = "";
	if(mappingname == "")
	{
		jsonSearchRoleUrl = '/manageroleassign/getRoleMappingData/x';
	}
	else
	{
		jsonSearchRoleUrl = '/manageroleassign/getRoleMappingData/'+mappingname;
	}
	

	//alert(mappingname);
	
	$.ajax({
		type: 'GET',
		url: jsonSearchRoleUrl,
		dataSrc: '',
		data: {
		},
		dataType: 'json',
		success: function(data){
			console.log("Data Received ::: "+data);
			jsonData = data;			
			populateRoleMappingDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateRoleMappingDataTable(data)
{
	$("#rolemappingdt").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#search_result').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var str='<i class="fa fa-eye w3-padding" id="view" onclick="rolemapping_details('+dat.automappid+',\'view\');"></i>';
			var str1='<i class="fa fa-pen" id="edit" onclick="rolemapping_details('+dat.automappid+',\'edit\')"></i>';
			
			$("#rolemappingdt").dataTable().fnAddData([				
				dat.automappid,
				dat.mappingname,	
				dat.status,				
				str,
				str1
			]);
		}
		$('#search_result').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}
/* Search Data JS ends*/

function rolemapping_details(automappid,display_mode)
{
	//debugger;
	
	var url = "/manageroleassign/editRoleMapping/"+automappid+"/"+display_mode;
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
}

