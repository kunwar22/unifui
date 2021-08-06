window.globalCounter = 1;
var buId='';
var depid='';
var datasetId='';
var hiredate="";
var legVal="";
var pernum="";

function pnumcheck(){
	var newpnum = $("#CR_HR_PER_NUM").val();
	newpnum=newpnum.trim();
	if (newpnum == "" || newpnum == undefined || newpnum == null){
		$("#HIRE_PNUM_ERROR").text("Please enter Person Number.");
		$("#HIRE_PNUM_AVL").text("");
		return ;
	}
	var purl="../newperson/pnumcheck/"+newpnum;
	$.ajax({
		type: 'GET',
		url: purl,
		success: function(data){
			if (data == "Success"){
				$("#HIRE_PNUM_ERROR").text("");
				$("#HIRE_PNUM_AVL").text("This Person Number available.");
			}else if (data == "AlreadyExists"){
				$("#HIRE_PNUM_AVL").text("");
				$("#HIRE_PNUM_ERROR").text("This Person Number already exist.");
			}
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


$(document).ready(function(){
	//debugger;
	var element = document.getElementById("compensation");
	if(typeof(element) != 'undefined' && element != null) {
		if (natofempl == "Consultant" || natofempl == "Re-Employed") {
			//$("#compensation :input").prop("disabled", true);
			//$('#gradesLOV').children('option[id="1"]').prop('selected',true);
			$('#COMP_GRADE_STEP').children('option[id="1"]').prop('selected',true);
			$('#COMP_TYPE').children('option[id="1"]').prop('selected',true);
			$('#COMP_FREQ').children('option[id="1"]').prop('selected',true);

			$('#gradesLOV').children("option:selected").val("0");
			$('#gradesLOV').children("option:selected").text("");

			$("#gradesLOV").prop("disabled", true);
			$("#COMP_GRADE_STEP").prop("disabled", true);
			$("#COMP_TYPE").prop("disabled", true);
			$("#COMP_FREQ").prop("disabled", true);
		}
		$("#COMP_SALARY_AMT").prop("disabled", false);
		$("#COMP_SALARY_AMT").val("");
	}
});

$('#COMP_GRADE_STEP').on('change',function(){
	var amount = $("#COMP_GRADE_STEP").children("option:selected").attr("amt");
	$('#COMP_SALARY_AMT').val(amount);
});

$('#CR_HR_MARITAL_STATUS').on('change',function(){
	//debugger;
	if(parseInt($('#CR_HR_MARITAL_STATUS').children("option:selected").val())==93){
		$("#MARRIAGE_DATE").css("display","block");
	}else if(parseInt($('#CR_HR_MARITAL_STATUS').children("option:selected").val())!=93){
		$("#MARRIAGE_DATE").css("display","none");
	}
});

$("#CR_HR_DOB").attr("max",new Date().toISOString().substring(0,10));


/**********************************ROW OPERATIONS Start Here******************************************* */
//$("#add").on("click", function () {
//$(document).unbind();
function addrow(){
//debugger;
var data="";
	 data='<tr><td style="width:10%"><input style="zoom: 1.5;" class="w3-input w3-border natCheck" onclick="uncheckAll(this.name)" name="nationaldetails[' + window.globalCounter + '].primary" id="CHECKBOX_NATIDENTIFIER' + window.globalCounter + '" type="checkbox" value="Yes" ></td><td style="width:25%"><select id="CR_HR_COUNTRY' + window.globalCounter + '" class="w3-select w3-border" name="nationaldetails[' + window.globalCounter + '].country">'+$('#CR_HR_COUNTRY').html()+'</select></td><td style="width:25%"><select id="regularTemp' + window.globalCounter + '" class="w3-select w3-border" name="nationaldetails[' + window.globalCounter + '].nationaltype">'+$('#nationType').html()+'</select></td><td style="width:30%"><input value="" class="w3-input w3-border"  type="text"></td><td style="width:5%"><input class="w3-btn w3-theme" id="delete" index="0" flg="national"  type="button" value="x"/></td></tr>';
	 $("#NAT_ID_TBL tbody").append(data);
//window.globalCounter++;

$.each($('#NAT_ID_TBL tr'),function(index,val){
	//debugger;
	$(this).find("td:eq(0) input[type='checkbox']").attr('name','nationaldetails['+(index - 1)+'].primary');

	$(this).find("td:eq(1)").find("select").attr('name','nationaldetails['+(index - 1)+'].countryid');
	$(this).find("td:eq(2)").find("select").attr('name','nationaldetails['+(index - 1)+'].nationaltype'); 
	$(this).find("td:eq(3) input[type='text']").attr('name','nationaldetails['+(index - 1)+'].nationalid');
	$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));

	// $(this).find("td:eq(0)").find("label").attr('name','na['+index+']');
	// $(this).find("td:eq(1)").find("label").attr('name','na['+index+']');
});

}




function uncheckAll(name){
//alert($(this).attr("id"));

    //alert(name);
	var inputs = $(".natCheck");
	var index=name.substring(16,17);
	var flg="natUncheck";
	removnatCheck(index,flg);
	
	//debugger;
	for(i=0;i<inputs.length;i++){
		if(!(inputs[i].name === name))
		{
			inputs[i].checked = false;
		}		
	}
	
}
function removnatCheck(index,flg) {
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

/*
$('input[class="natCheck"]').on('click', function() {
   $(this).siblings('input[class="natCheck"]').prop('checked', false);
});

$(document).unbind();
$(document).on("change",".natCheck",function(){
	var inputs = $(".natCheck");
	for(i=0;i<inputs.length;i++){
	$(inputs[i]).prop('checked', true);
	}
	});
*/
/*
var inputs = $(".natCheck");
$(inputs[0]).prop('checked', true);
*/
//});//
//$(document).ready(function(){
//$(document).unbind();
$(document).on("click","#delete",function(){
	//$("table").row($(this).parents('tr')).remove().draw(false);
	var dex=$(this).attr('index');
	var flg=$(this).attr('flg');
	$(this).parents("tr").remove();
	removeRow(dex,flg);
	$.each($('#NAT_ID_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','nationaldetails['+(index - 1)+'].primary');

		$(this).find("td:eq(1)").find("select").attr('name','nationaldetails['+(index - 1)+'].countryid');
	$(this).find("td:eq(2)").find("select").attr('name','nationaldetails['+(index - 1)+'].nationaltype'); 
	$(this).find("td:eq(3) input[type='text']").attr('name','nationaldetails['+(index - 1)+'].nationalid');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));

	});

	
});
//});
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
function removeManager(index) {
	var jurl="../newperson/removemanager/"+index;
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




function addrowcontact(){
//debugger;
var dataphone="";
	dataphone='<tr><td style="width:10%"><input style="zoom: 1.5;" class="w3-input w3-border phoneCheck" onclick="uncheckAllPhone(this.name)"  id="CHECKBOX_PRIMARY' + window.globalCounter + '" name="contactdetails[' + window.globalCounter + '].primary" type="checkbox" value="Yes" ></td><td style="width:25%"><select id="CR_HR_CONTACTTYPE' + window.globalCounter + '" class="w3-select w3-border" >'+$('#CR_HR_CONTACTTYPE').html()+'</select></td><td style="width:25%"><select id="CR_HR_COUNTRYCODE' + window.globalCounter + '" class="w3-select w3-border" >'+$('#CR_HR_COUNTRYCODE').html()+'</select></td><td style="width:30%"><input value="" class="w3-input w3-border" oninput="this.value = this.value.replace(/[^0-9.]/g, \'\').replace(/(\\..*)\\./g, \'$1\');" type="text"></td><td style="width:5%"><input class="w3-btn w3-theme" id="deletephone" index="0" flg="phone" type="button" value="x"/></td></tr>';
$("#PHONE_DETAIL_TBL tbody").append(dataphone);

	$.each($('#PHONE_DETAIL_TBL tr'),function(index,val){
		//debugger;
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','contactdetails['+(index - 1)+'].primary');

		$(this).find("td:eq(1)").find("select").attr('name','contactdetails['+(index - 1)+'].contacttype');
		$(this).find("td:eq(2)").find("select").attr('name','contactdetails['+(index - 1)+'].countrycode');
		$(this).find("td:eq(3) input[type='text']").attr('name','contactdetails['+(index - 1)+'].contactnumber');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));

		// $(this).find("td:eq(0)").find("label").attr('name','na['+index+']');
		// $(this).find("td:eq(1)").find("label").attr('name','na['+index+']');
	});

}

function uncheckAllPhone(name){
//alert($(this).attr("id"));
	//alert(name);
	var inputss = $(".phoneCheck");
	var index=name.substring(15,16);
	var flg="phoneUncheck";
	removphoneCheck(index,flg);
	//debugger;
	for(i=0;i<inputss.length;i++){
		if(!(inputss[i].name === name))
		{
			inputss[i].checked = false;
		}		
	}
	
}
function removphoneCheck(index,flg) {
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
//$(document).ready(function(){
//$(document).unbind();
$(document).on("click","#deletephone",function(){
	//$("table").row($(this).parents('tr')).remove().draw(false);
	var dex=$(this).attr('index');
	var flg=$(this).attr('flg');
	$(this).parents("tr").remove();
	removeRow(dex,flg);
	$(this).parents("tr").remove();
	$.each($('#PHONE_DETAIL_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','contactdetails['+(index - 1)+'].primary');
		$(this).find("td:eq(1)").find("select").attr('name','contactdetails['+(index - 1)+'].contacttype');
		$(this).find("td:eq(2)").find("select").attr('name','contactdetails['+(index - 1)+'].countrycode');
		$(this).find("td:eq(3) input[type='text']").attr('name','contactdetails['+(index - 1)+'].contactnumber');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));

	});

});
//});


function addrowemail(){
//debugger;
var data="";
	 data='<tr><td style="width:10%"><input style="zoom: 1.5;" class="w3-input w3-border emailCheck" onclick="uncheckAllMail(this.name)" name="emaildetails[' + window.globalCounter + '].primary" id="CHECKBOX_PRIMARY_EMAIL' + window.globalCounter + '" type="checkbox" value="Yes" ></td><td style="width:25%"><select id="CR_HR_EMAILTYPE' + window.globalCounter + '" class="w3-select w3-border" name="email[' + window.globalCounter + '].cont">'+$('#CR_HR_EMAILTYPE').html()+'</select></td><td style="width:30%"><input value="" class="w3-input w3-border" name="email[' + window.globalCounter + '].emailaddress" type="text"></td><td style="width:5%"><input class="w3-btn w3-theme" id="deleteemail"  type="button" value="x"/></td></tr>';
$("#EMAIL_DETAIL_TBL tbody").append(data);

	$.each($('#EMAIL_DETAIL_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','emaildetails['+(index - 1)+'].primary');
		$(this).find("td:eq(1)").find("select").attr('name','emaildetails['+(index - 1)+'].emailtype');
		$(this).find("td:eq(2)").find("select").attr('name','emaildetails['+(index - 1)+'].countrycode');
		$(this).find("td:eq(3) input[type='text']").attr('name','emaildetails['+(index - 1)+'].contactnumber');

	});

}

function uncheckAllMail(name){
//alert($(this).attr("id"));
//alert(name.substring(13,14));
var index=name.substring(13,14);
var flg="mailUncheck";
removemailCheck(index,flg);
	var input = $(".emailCheck");
	//debugger;
	for(i=0;i<input.length;i++){
		if(!(input[i].name === name))
		{
			input[i].checked = false;
		}		
	}
	
}
function removemailCheck(index,flg) {
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


//$(document).ready(function(){
//$(document).unbind();
$(document).on("click","#deleteemail",function(){
	//$("table").row($(this).parents('tr')).remove().draw(false);
	var dex=$(this).attr('index');
	var flg=$(this).attr('flg');
	$(this).parents("tr").remove();
	removeRow(dex,flg);
	$(this).parents("tr").remove();
	$.each($('#EMAIL_DETAIL_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','emaildetails['+(index - 1)+'].primary');
		$(this).find("td:eq(1)").find("select").attr('name','emaildetails['+(index - 1)+'].emailtype');
		$(this).find("td:eq(2)").find("select").attr('name','emaildetails['+(index - 1)+'].countrycode');
		$(this).find("td:eq(3) input[type='text']").attr('name','emaildetails['+(index - 1)+'].contactnumber');

	});

});
//});


//$(document).unbind();
function addrowmanager(){
	//debugger;
	var data="";
	data='<tr><td style="width:25%"><select id="MGR' + window.globalCounter + '" onchange="mgrchange(' + window.globalCounter + ')" class="w3-select w3-border" name="managertypes[' + window.globalCounter + '].supervisorname"><option id="1" value="" selected disabled></option><option id="2" value="" selected disabled></option><option value="search" data-toggle="modal" id="btnsearchLegal">Search....</option></select></td><td style="width:30%;"><input class="w3-input w3-border"  name="managertypes['+window.globalCounter+'].supervisorid" type="text"></td><td style="width:30%"><select id="CR_HR_MGR_TYPE' + window.globalCounter + '" class="w3-select w3-border" name="managertypes[' + window.globalCounter + '].managertype">'+$('#CR_HR_MGR_TYPE').html()+'</select></td><td style="width:5%"><input class="w3-btn w3-theme" id="deletemgr"  type="button" value="x"/></td></tr>';
	$("#MGR_DET_TBL tbody").append(data);
	
	$.each($('#MGR_DET_TBL tr'),function(index,val){
		//debugger;
		$(this).find("td:eq(0)").find("select").attr('name','managertypes['+(index - 1)+'].supervisorname');
		$(this).find("td:eq(1) input[type='text']").attr('name','managertypes['+(index - 1)+'].supervisorid');
		$(this).find("td:eq(0)").find("select").attr('id','MGR'+(index - 1));
		$(this).find("td:eq(1) input[type='text']").attr('id','MNGR_ID'+(index - 1));
		$(this).find("td:eq(0)").find("select").attr('onchange','mgrchange('+(index - 1)+')');
	
	});

}
//$(document).ready(function(){
//$(document).unbind();
$(document).on("click","#deletemgr",function(){
	//$("table").row($(this).parents('tr')).remove().draw(false);
	//debugger;
	var dex=$(this).attr('index');
	//var flg=$(this).attr('flg');
	$(this).parents("tr").remove();
	removeManager(dex);
	$(this).parents("tr").remove();
	$.each($('#MGR_DET_TBL tr'),function(index,val){
		$(this).find("td:eq(0)").find("select").attr('name','managertypes['+(index - 1)+'].supervisorname');
		$(this).find("td:eq(1) input[type='text']").attr('name','managertypes['+(index - 1)+'].supervisorid');
		$(this).find("td:eq(0)").find("select").attr('id','MGR'+(index - 1));
		$(this).find("td:eq(1) input[type='text']").attr('id','MNGR_ID'+(index - 1));
		$(this).find("td:eq(0)").find("select").attr('onchange','mgrchange('+(index - 1)+')');
	});

});
//});


/***********************************ROW OPERATIONS End Here**addrow
**************************************** */

function sendHireData(cmd)
{

	//debugger;
	var currrentcmd = $('#current_cmd').val();
	hiredate = $('#HIRE_DATE').val();
	var leg_Val=$('#legalEmployer').children("option:selected").text();
	$("#hidLegal").val(leg_Val);
	//hidBU hidJOB hidDEPT hidPOS hidLOC hidMNGR hidGRADE
	var bu_Val=$('#businessUnit').children("option:selected").text();
	$("#hidBU").val(bu_Val);
	var job_Val=$('#JOBS_LOV').children("option:selected").text();
	$("#hidJOB").val(job_Val);
	var dept_Val=$('#DEPT_LOV').children("option:selected").text();
	$("#hidDEPT").val(dept_Val);
	var pos_Val=$('#POS_LOV').children("option:selected").text();
	$("#hidPOS").val(pos_Val);
	var loc_Val=$('#CR_ENT_LOC_CODE').children("option:selected").text();
	$("#hidLOC").val(loc_Val);
	var grade_Val=$('#gradesLOV').children("option:selected").text();
	$("#hidGRADE").val(grade_Val);
	var nature_Val=$('#NAT_OF_EMPL').children("option:selected").text();
	$("#hidNatofEmpl").val(nature_Val);
	var paygroup_Val=$('#CR_ENT_WRK_PAY').children("option:selected").text();
	$("#paygroup").val(paygroup_Val);
	
	
	//alert(hiredate);
	if(currrentcmd=="identificationdetail") {

		var legalemp = $('#legalEmployer').children("option:selected").val();
		var workertype = $('#Workertype').children("option:selected").val();

		var action = $('#HIRE_ACTION').children("option:selected").val();
		var actionreason =  $('#ACTION_REASON').children("option:selected").val();
		var personnumber= $('#CR_HR_PER_NUM').val();
		pernum=personnumber;
		var validateresult = validateHire(hiredate, legalemp, workertype, action, actionreason, personnumber);
		if (validateresult == true) {
			$("#HIRE_LOADER").css("display","block");
			//alert(cmd);
			//debugger;
			document.getElementById("cmd").value = cmd;
			//alert($('#frmHire').serialize());
			//$('#fragmaent_container').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
				type: 'POST',
				url: '../newperson/nextandsaveperson',
				data: $('#frmHire').serialize(),
				contentType: "application/x-www-form-urlencoded",
				processData: false,
				catch: false,
				success: function (response) {
					//alert(response);
					//debugger;
					$("#HIRE_LOADER").css("display","none");
					$('#fragmaent_container').html(response);
					var newcmd = $('#current_cmd').val();
					openTabs(newcmd);
					if(prim=="false") {
						bootbox.alert({
							size: 'medium',
							title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
							message: "Atleast one National Identifier must be Primary.",
						});
					}prim="true";
				},
				error: function (e) {
					alert(e);
					console.log("There was an error with request...");
					console.log("error: " + JSON.stringify(e));
				}

			});
		}

	}
	else {
		$("#HIRE_LOADER").css("display","block");
		$('#COMP_FREQ').prop("disabled",false);
		$('#COMP_SALARY_AMT').prop("disabled",false);
		//alert(cmd);
		//debugger;
		document.getElementById("cmd").value = cmd;
		//alert($('#frmHire').serialize());
		//$('#fragmaent_container').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
			type: 'POST',
			url: '../newperson/nextandsaveperson',
			data: $('#frmHire').serialize(),
			contentType: "application/x-www-form-urlencoded",
			processData: false,
			catch: false,
			success: function (response) {
				//alert(response);
				$("#HIRE_LOADER").css("display","none");
				$('#fragmaent_container').html(response);
				var newcmd = $('#current_cmd').val();
				openTabs(newcmd);
				if (resultfinal!=null && resultfinal!=undefined && resultfinal=="1"){
					bootbox.alert({
						size: 'medium',
						title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
						message: "Please enter Person Number.",
					});
				}else if(resultfinal!=null && resultfinal!=undefined && resultfinal!="0" && resultfinal!="1" ){

					bootbox.confirm({
						size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message: resultfinal+" Do you want to assign Elements?",
						buttons: {
							confirm: {
								label: 'Yes',
								className: 'btn-success'
							},
							cancel: {
								label: 'No',
								className: 'btn-danger'
							}
						},
						callback: function (result) {
							if(result){
								$('#replace_div').load("/newperson/openelementassignment");
							}else{
								window.location = "/home";
							}
						}
					});

					/*bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message: resultfinal,
						callback:function(){
							$('#replace_div').load("/newperson/openelementassignment");
						}
						//window.location = "/home";
						//$('#replace_div').load("/home");
					});*/
				}

				
			},
			error: function (e) {
				alert(e);
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}

		});
	}
}

function openTabs(tabname){
	//debugger;
	var i,x,tablinks;
	
	tablinks=document.getElementsByClassName("tablink");
	for(i=0;i<tablinks.length;i++){
		tablinks[i].className=tablinks[i].className.replace("w3-theme","");
	}
	$("#"+tabname).prop("disabled",false);
	$("#"+tabname).addClass("w3-theme");
}






 
var datasetId='';
var buId='';
var posId='';

/****************************CODE FOR SWITCHING TAB START************************/
function openHire(evt,hireEmp){
	var i,x,tablinks;
	x=document.getElementsByClassName("hire");
	for(i=0;i<x.length;i++){
		x[i].style.display="none";
	}
	tablinks=document.getElementsByClassName("tablink");
	for(i=0;i<x.length;i++){
		tablinks[i].className=tablinks[i].className.replace(" w3-theme","");
		
	}
	document.getElementById(hireEmp).style.display="block";
	evt.currentTarget.className+=" w3-theme";	
}
/****************************CODE FOR SWITCHING TAB END************************/

/****************************CODE FOR STATE BIND START************************/
$('#countryList').on('change',function(){
	//alert("hello country "+countryList);
	var selectCountryId=$(this).children("option:selected").val();
	
	var jsonUrl = '/enterprisesetup/statebind/' +selectCountryId;
	
	newStatebind="";
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',
		
		dataType: 'json',
		success: function(data){
			newStatebind+='<p><label>State</label><span style="color:red"> *</span>';
			newStatebind+='<select id="stateList" class="w3-select w3-border" name="addressdetails[0].state" >'
				newStatebind+='<option value="0" disabled selected></option>'
				data.forEach(function(n){
					newStatebind+='<option value="'+n.id+'" >'+n.description+'</option>'
				});
			newStatebind+='</select>'+
				'</p>'+
				'<span style="color: red"'+
				'th:if=\'${bindingResult != null && bindingResult.getFieldError("state")!=null}\''+
				'th:text=\'${bindingResult.getFieldError("state").getDefaultMessage()}\'>'+
				'</span>';
			$('#divStateDisp').css("display","none");
			$('#divState').html(newStatebind);
				
	
		
			//populateDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
});



/****************************CODE FOR STATE BIND END************************/



/**********************state populate start here************************/

$('#CR_HR_PI_COUNTRY').on('change',function(){
	var selectCountryId=$(this).children("option:selected").val();
	var jsonUrl = '/enterprisesetup/statebind/' +selectCountryId;
	newStatebind="";
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',
		
		dataType: 'json',
		success: function(data){
			newStatebind+='<p><label>State</label>';
			newStatebind+='<select id="stateList" class="w3-select w3-border" name="option" >'
				newStatebind+='<option value="" disabled selected></option>'
				data.forEach(function(n){
					newStatebind+='<option value="'+n.id+'" >'+n.description+'</option>'
				});
			newStatebind+='</select></p>';
			$('#CR_HR_divState').html(newStatebind);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
});

/*****************************state populate End here***************************/


/*******************************HIRE EMPLOYEE JS *******************************/




$(document).ready(function(){


	var table=$('#BU_LIST').DataTable();
	//$('#BU_LIST tbody').off('click')
	$('#BU_LIST tbody').on('click','tr',function(){
		//debugger;
		//alert("Inside table");
		var tbldata=$(this).children('td').map(function(){
			return $(this).text();

		}).get();

		if($(this).hasClass('selected')){
			$(this).removeClass('selected');

		}
		else{
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData=tbldata[0];
		var dtDataId=tbldata[4];
		buId=dtDataId;
		datasetId=tbldata[5];
		$('#businessUnit').val(dtData);
		$('#businessUnit').children('option[id="2"]').text(dtData);
		$('#businessUnit').children('option[id="2"]').val(dtDataId);
		$('#businessUnit').children('option[id="2"]').prop('selected',true);
		$('#BU_POP_OK').css("display","inline-block");

	});

	$('#btnsearch2').on('click', function(e){
		$('#id03').css("display","block");

	});


	$('#BU_POP_CANCEL').on('click', function(e){
		$('#id03').css("display","none");
		$('#businessUnit').children('option[id="1"]').prop('selected',true);
		$('#buresultSec').css('display', 'none');
		$('#BU_POP_OK').css("display","none");
	});

	$('#BU_POP_OK').on('click', function(e){
		$('#buresultSec').css('display', 'none');
		$('#id03').css("display","none");
		//alert("Data Set ID :::::: "+datasetId+"  BU ID:::: "+buId);

		$('#POS_LOV').prop("disabled",false);
		//$('#JOBS_LOV').prop("disabled",false);
		$('#DEPT_LOV').prop("disabled",false);
		$('#POS_LOV').children('option[id="1"]').prop('selected',true);
		$('#JOBS_LOV').children('option[id="1"]').prop('selected',true);
		$('#DEPT_LOV').children('option[id="1"]').prop('selected',true);
		$('#buresultSec').css('display', 'none');
		$('#BU_POP_OK').css("display","none");

	});





});

/************************POPUP TABLE SELECTED ROW END***********************/


	
	/****************************CODE FOR ACTION REASON BIND START************************/
//alert("before state function");

$('#HIRE_ACTION').on('change',function(){
	//alert("Inside Function");
	//debugger;
	var selectHireActionId=$(this).children("option:selected").val();
	//alert("hello country "+selectCountryId);
	var jsonUrlAcReason = '/newperson/actionreasonbind/' +selectHireActionId;
	
	newStatebind="";
	$.ajax({
		type: 'GET',
		url: jsonUrlAcReason,
		dataSrc: '',
		
		dataType: 'json',
		
					
		success: function(data){
			newStatebind+='<p><label>Action Reason</label><span style="color: red"> *</span>';
			newStatebind+='<select id="ACTION_REASON" class="w3-select w3-border" name="jobdata.hirereason" >'
				newStatebind+='<option value="" disabled selected></option>'
				data.forEach(function(n){
					newStatebind+='<option value="'+n.id+'" >'+n.description+'</option>'
				});
			newStatebind+='</select></p><span id="HIRE_ACTION_REASON_ERROR" style="color: red"></span>';
			//$('#divStateDisp').css("display","none");
			
			
			$('#ACTION_REASON_BLOCK').html(newStatebind);
				
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
});



/****************************CODE FOR STATE BIND END************************/







/****************************CODE FOR ADDING AND DELETING ROWS IN DIFFERENT TABLES START************************/
function addRowNowNATID(){
	
	$("#NAT_ID_TBL").each(function(){
		
		var tds='<tr>';
		jQuery.each($('tr:last td',this),function(){
			tds+='<td>'+$(this).html()+'</td>';
		});
		tds+='</tr>';
		if($('tbody',this).length>0){
			$('tbody',this).append(tds);
		}else{
			$(this).append(tds);
		}
	});
}


function delThisRowNATID()
{
var table=document.getElementById("NAT_ID_TBL");
	
	for(var i=1; i<table.rows.length; i++)
	{
		table.rows[i].cells[5].onclick = function(){
			if(table.rows.length > 2)
			{
				table.deleteRow(this.parentElement.rowIndex);
							
			}
			
		}
	}
}


function addRowNowPHONE()
{

	$("#PHONE_DETAIL_TBL").each(function(){
		
		var tds='<tr>';
		jQuery.each($('tr:last td',this),function(){
			tds+='<td>'+$(this).html()+'</td>';
		});
		tds+='</tr>';
		if($('tbody',this).length>0){
			$('tbody',this).append(tds);
		}else{
			$(this).append(tds);
		}
	});
}


function delThisRowPHONE()
{

var table=document.getElementById("PHONE_DETAIL_TBL");
	
	for(var i=1; i<table.rows.length; i++)
	{
		table.rows[i].cells[5].onclick = function(){
			if(table.rows.length > 2)
			{
				table.deleteRow(this.parentElement.rowIndex);
							
			}
			
		}
	}
}


function addRowNowMANAGER()
{
$("#MANAGER_TBL").each(function(){
		
		var tds='<tr>';
		jQuery.each($('tr:last td',this),function(){
			tds+='<td>'+$(this).html()+'</td>';
		});
		tds+='</tr>';
		if($('tbody',this).length>0){
			$('tbody',this).append(tds);
		}else{
			$(this).append(tds);
		}
	});}


function delThisRowMANAGER()
{
var table=document.getElementById("MANAGER_TBL");
	
	for(var i=1; i<table.rows.length; i++)
	{
		table.rows[i].cells[3].onclick = function(){
			if(table.rows.length > 2)
			{
				table.deleteRow(this.parentElement.rowIndex);
							
			}
			
		}
	}
}


function addRowNowEMAIL()
{
$("#EMAIL_TBL").each(function(){
		
		var tds='<tr>';
		jQuery.each($('tr:last td',this),function(){
			tds+='<td>'+$(this).html()+'</td>';
		});
		tds+='</tr>';
		if($('tbody',this).length>0){
			$('tbody',this).append(tds);
		}else{
			$(this).append(tds);
		}
	});
	}

function delThisRowEMAIL()
{
var table=document.getElementById("EMAIL_TBL");
	
	for(var i=1; i<table.rows.length; i++)
	{
		table.rows[i].cells[4].onclick = function(){
			if(table.rows.length > 2)
			{
				table.deleteRow(this.parentElement.rowIndex);
							
			}
			
		}
	}
}
/****************************CODE FOR ADDING AND DELETING ROWS IN DIFFERENT TABLES END************************/


/*****************************SEARCH GRADE START HERE***************************/

var jsonUrlGrade = '/enterprisesetup/searchGrade';

var searchdsCode = '';
var searchdsName = '';
var searchstatus='';

$("#GRADE_SEARCH").on('click', function(e){
	
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	//searchstatus=$('#statusList').children("option:selected").val();
	loadGradeData()

});
function loadGradeData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlGrade,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName,
			"statusGrade":"Active"
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			populateGradeDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateGradeDataTable(data){
	$("#GRADE_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#graderesultSec').css('display', 'none');
		$('#noDataGrade').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#GRADE_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.status,
				dat.datasets,
				dat.gradesid
			]);
		}
		$('#graderesultSec').css('display', 'block');
		$('#noDataGrade').css('display', 'none');
	}
}

/************************POPUP TABLE SELECTED ROW START***********************/
	
	
	
	$(document).ready(function(){
	
		$('#btnsearch6').on('click', function(e){
			$('#id06').css("display","block");
			
		});
		
		$('#GRADE_POP_OK').on('click', function(e){
		$('#graderesultSec').css('display', 'none');
			$('#id06').css("display","none");
			
		});
		
		$('#GRADE_POP_CANCEL').on('click', function(e){
			$('#id06').css("display","none");
			$('#graderesultSec').css('display', 'none');
			$('#gradeSearchLOV').children('option[id="1"]').prop('selected',true);
		});
		
		var table=$('#GRADE_LIST').DataTable();
		
		$('#GRADE_LIST tbody').on('click','tr',function(){
			var tbldata=$(this).children('td').map(function(){
				return $(this).text();
				
			}).get();	
			
			if($(this).hasClass('selected')){
				$(this).removeClass('selected');
				
			}
			else{
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
			var dtData=tbldata[0];
			var dtDataId=tbldata[4];
			$('#gradesLOV').val(dtData);
			$('#gradesLOV').children('option[id="2"]').text(dtData);
			$('#gradesLOV').children('option[id="2"]').val(dtDataId);
			$('#gradesLOV').children('option[id="2"]').prop('selected',true);
			stepbind();		
			
		});
		
	});
	
/********************************POPUP TABLE SELECTED ROW END********************************/

/***********************************SEARCH GRADE END HERE************************************/

/***********************************POPUP BU SEARCH START************************************/
	
	var jsonUrlBU = '/enterprisesetup/searchBU';

	var searchdsCode = '';
	var searchdsName = '';
	//$("#BU_SEARCH").on('click', function(e){
		function Busearch(){
		searchdsCode =  $("#SRCH_CODE").val();
		searchdsName = $("#SRCH_NAME").val();
		//searchstatus=$('#statusList').children("option:selected").val();
		loadBUData();
	}
	
	function loadBUData(){
		$.ajax({
			type:'POST',
			url: jsonUrlBU,
			dataSrc:'',
			data: {
				"code": searchdsCode,
				"name": searchdsName,
				"statusBU":"Active"
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateBUDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}

	function populateBUDataTable(data){
		$("#BU_LIST").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#buresultSec').css('display', 'none');
			$('#noDataBU').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#BU_LIST").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.dataset,
					dat.status,
					dat.businessunitid,
					dat.datasetid					
				]);
			}
			$('#buresultSec').css('display', 'block');
			$('#noDataBU').css('display', 'none');
		}
	}




/************************POPUP TABLE SELECTED ROW START***********************/
	
	function buchange() {
	//debugger;
	//$('#id03').css("display","none");
	var selectObject = $('#businessUnit').children("option:selected").val();
	if (selectObject == 'search') {
		$('#id03').css("display", "block");

	} else if (selectObject != 'search') {
		$('#id03').css("display", "none");
	}
}

/**************************************POPUP BU SEARCH END********************************************/

function gradechange() {
	//debugger;
	//$('#id03').css("display","none");
	var selectObject = $('#gradesLOV').children("option:selected").val();
	if (selectObject == 'search') {
		$('#id06').css("display", "block");

	} else if (selectObject != 'search') {
		$('#id06').css("display", "none");
	}
}


/****************************CODE FOR APPROVER BIND START************************/
function stepbind(){
	//debugger;
	//alert("hello country "+countryList);
	var category=$('#gradesLOV').val();
	//alert("Grades Id"+category);
	var jsonUrl1 = '/newperson/gradestepbind/' + category;
	
	newStatebind="";
	$.ajax({
		type: 'GET',
		url: jsonUrl1,
		dataSrc: '',
		
		dataType: 'json',
		success: function(data){
				//alert("Inside Success");
				newStatebind+='<option id="1" value="0" disabled selected></option>';
				data.forEach(function(n){
					newStatebind+='<option value="'+n.gradestepid+'" amt="'+n.stepamount+'" >'+n.stepname+'</option>';
				});
			
			//$('#divStateDisp').css("display","none");
			$('#COMP_GRADE_STEP').html(newStatebind);
			
				
	
		
			//populateDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}




/****************************CODE FOR APPROVER BIND END************************/


/************************POPUP MANAGER TABLE SELECTED ROW START***********************/
var MGR_ID='';

function mgrchange(mgrid) {
	//debugger;
	//$('#id03').css("display","none");
	MGR_ID=mgrid;
	var selectObject = $("#MGR"+mgrid).children("option:selected").val();
	if (selectObject == 'search') {
		$('#id0Manager').css("display", "block");

	} else if (selectObject != 'search') {
		$('#id0Manager').css("display", "none");
	}
}


/***********************************POPUP BU SEARCH START************************************/

var jsonUrlMgr = '/newperson/managerbind';

var searchdsCode = '';
var searchdsName = '';
//var hiredate='';
function mgrsearch(){
	searchdsCode =  $("#MGR_SRCH_CODE").val();
	searchdsName = $("#MGR_SRCH_NAME").val();
	//hiredate = $("#HIRE_DATE").val();
//alert(hiredate);
	loadMGRData();
}

function loadMGRData(){
	$.ajax({
		type:'POST',
		url: jsonUrlMgr,
		dataSrc:'',
		data: {
			"pernbr": searchdsCode,
			"firstname": searchdsName,
			"hiredate":hiredate
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			//alert(JSON.stringify(jsonData));
			populateMGRDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateMGRDataTable(data){
	$("#MGR_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#mgrresultSec').css('display', 'none');
		$('#noDataMGR').css('display', 'block');
	} else {
		//alert("name"+data[0].personname);
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#MGR_LIST").dataTable().fnAddData([
				dat.personname,
				dat.personnumber

			]);
		}
		$('#mgrresultSec').css('display', 'block');
		$('#noDataMGR').css('display', 'none');
	}
}


$(document).ready(function(){
$('#MGR_POP_CANCEL').on('click', function(e){
	$('#id0Manager').css("display","none");
	$('#MANAGERS_LOV').children('option[id="1"]').prop('selected',true);
	$('#mgrresultSec').css('display', 'none');
});

$('#MGR_POP_OK').on('click', function(e){
	//debugger;
	$('#mgrresultSec').css('display', 'none');
	$('#id0Manager').css("display","none");
	//alert("Data Set ID :::::: "+datasetId+"  BU ID:::: "+buId);



});

var table=$('#MGR_LIST').DataTable();

$('#MGR_LIST tbody').on('click','tr',function(){
	var tbldata=$(this).children('td').map(function(){
		return $(this).text();

	}).get();

	if($(this).hasClass('selected')){
		$(this).removeClass('selected');

	}
	else{
		table.$('tr.selected').removeClass('selected');
		$(this).addClass('selected');
	}
	///var mgr_id=$(this).children('select').attr('id');
	//alert(MGR_ID);
	var dtData=tbldata[0];
	var dtDataId=tbldata[1];
	$('#MGR'+MGR_ID).val(dtData);
	$('#MGR'+MGR_ID).children('option[id="2"]').text(dtData);
	$('#MGR'+MGR_ID).children('option[id="2"]').val(dtData);
	$('#MGR'+MGR_ID).children('option[id="2"]').prop('selected',true);
	$('#MNGR_ID'+MGR_ID).val(dtDataId);

});

});
/**************************************POPUP MANAGER SEARCH END********************************************/



/**************************SETTING FIELDS ON POSITION SELECTED START*********************************/
function loadPosDataByPosId(){				
var jsonUrlposById = '/enterprisesetup/possearchbyid/'+posId;
		$.ajax({
			type: 'GET',
			url: jsonUrlposById,
			dataSrc: '',
			dataType: 'json',
			success: function(data){
				jsonData=data;
				setPosDataOnFields(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
}


	function setPosDataOnFields(data){
	//	alert("hi");
		$('#DEPT_LOV').val(data.departmentsname);
		$('#DEPT_LOV').children('option[id="2"]').text(data.departmentsname);
		$('#DEPT_LOV').children('option[id="2"]').val(data.departmentsid);
		$('#DEPT_LOV').children('option[id="2"]').prop('selected',true);
		$('#DEPT_LOV').prop("disabled",false);
		
		$('#JOBS_LOV').val(data.jobsname);
		$('#JOBS_LOV').children('option[id="2"]').text(data.jobsname);
		$('#JOBS_LOV').children('option[id="2"]').val(data.jobsid);
		$('#JOBS_LOV').children('option[id="2"]').prop('selected',true);	
		$('#JOBS_LOV').prop("disabled",false);	
		
		$('#gradeSearchLOV').val(data.gradesname);
		$('#gradeSearchLOV').children('option[id="2"]').text(data.gradesname);
		$('#gradeSearchLOV').children('option[id="2"]').val(data.gradesid);
		$('#gradeSearchLOV').children('option[id="2"]').prop('selected',true);	
		$('#gradeSearchLOV').prop("disabled",false);
		
		/*$('#CR_ENT_LOC_CODE').val(data.locationname);
		$('#CR_ENT_LOC_CODE').children('option[id="2"]').text(data.locationname);
		$('#CR_ENT_LOC_CODE').children('option[id="2"]').val(data.locationid);
		$('#CR_ENT_LOC_CODE').children('option[id="2"]').prop('selected',true);	
		$('#CR_ENT_LOC_CODE').prop("disabled",false);*/
		
		/*$('#ASSIGN_CAT').val(data.assignmentcategoryname);
		$('#ASSIGN_CAT').children('option[id="2"]').text(data.assignmentcategoryname);
		$('#ASSIGN_CAT').children('option[id="2"]').val(data.assignmentcategory);
		$('#ASSIGN_CAT').children('option[id="2"]').prop('selected',true);	
		$('#ASSIGN_CAT').prop("disabled",false);*/
		
		$('#REGorTEMP_LOV').val(data.regulartemporaryname);
		$('#REGorTEMP_LOV').children('option[id="2"]').text(data.regulartemporaryname);
		$('#REGorTEMP_LOV').children('option[id="2"]').val(data.regulartemporary);
		$('#REGorTEMP_LOV').children('option[id="2"]').prop('selected',true);	
		$('#REGorTEMP_LOV').prop("disabled",false);
		
		$('#FULL_PART_TIME_LOV').val(data.fulltimeparttimename);
		$('#FULL_PART_TIME_LOV').children('option[id="2"]').text(data.fulltimeparttimename);
		$('#FULL_PART_TIME_LOV').children('option[id="2"]').val(data.fulltimeparttime);
		$('#FULL_PART_TIME_LOV').children('option[id="2"]').prop('selected',true);	
		$('#FULL_PART_TIME_LOV').prop("disabled",false);
		
		$('#HEAD_CNT_INPUT').val("1");
		$('#HEAD_CNT_INPUT').prop("readonly",true);
		
		$('#FTE_INPUT').val("1");
		$('#FTE_INPUT').prop("readonly",true);
		
		$('#PROBPERIOD_LOV').val(data.probationperiodname);
		$('#PROBPERIOD_LOV').children('option[id="2"]').text(data.probationperiodname);
		$('#PROBPERIOD_LOV').children('option[id="2"]').val(data.probationperiod);
		$('#PROBPERIOD_LOV').children('option[id="2"]').prop('selected',true);	
		$('#PROBPERIOD_LOV').prop("disabled",false);
		
		$('#NOTICEPERIOD_LOV').val(data.noticeperiodtypename);
		$('#NOTICEPERIOD_LOV').children('option[id="2"]').text(data.noticeperiodtypename);
		$('#NOTICEPERIOD_LOV').children('option[id="2"]').val(data.noticeperiodtype);
		$('#NOTICEPERIOD_LOV').children('option[id="2"]').prop('selected',true);	
		$('#NOTICEPERIOD_LOV').prop("disabled",false);
		
		$('#PROB_INPUT').val(data.probationuom);
		$('#PROB_INPUT').prop("disabled",false);
		
		$('#NOTICE_INPUT').val(data.noticeperiod);
		$('#NOTICE_INPUT').prop("disabled",false);
		
	}
	
/**************************SETTING FIELDS ON POSITION SELECTED END***********************************/


/**********************************POPUP POSITION SEARCH BASED ON BU ID*************************************/		
var searchdsCode = '';
var searchdsName = '';
var searchstatus='';

$("#POS_SEARCH").on('click', function(e){
		
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	searchstatus=$('#SRCH_STATUS').children("option:selected").val();

	loadPosData();
		
});				
function loadPosData(){	
	
var jsonUrlpos = '/newperson/posbind';
		
		$.ajax({
			type: 'POST',
			url: jsonUrlpos,
			dataSrc: '',
			data: {
			"code": searchdsCode,
			"name": searchdsName,
			"status":"Active",
			"buId":datasetId,							///////*******buID is DATASET ID OF BU ******//////
			"deptid":depid
			},
			dataType: 'json',
			success: function(data){
				jsonData=data;
				populatePOSDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
}
		

	function populatePOSDataTable(data){
		$("#POS_LIST1").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#posresultSec1').css('display', 'none');
			$('#noDataPOS1').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#POS_LIST1").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.departmentsname,
					dat.jobsname,
					dat.status,
					dat.effectstartdate,
					dat.positionid
					]);
			}
			$('#posresultSec1').css('display', 'block');
			$('#noDataPOS1').css('display', 'none');
		}
	}
	
	/*******************************Popup table selected row start*******************************************/
	
	
	$('#POS_LOV').on('change',function(){
	
	var selectObject=$(this).children("option:selected").val();
	if(selectObject=='search'){
		$('#id07').css("display","block");
		
		
	}
	else if(selectObject!='search'){
		$('#id07').css("display","none");
	}
});


$(document).ready(function(){

	
	$('#OPTIONSEARCH').on('click', function(e){
		$('#id07').css("display","block");
		
	});
	
	$('#POS_POP_OK1').on('click', function(e){
		loadPosDataByPosId();
		$('#posresultSec1').css('display', 'none');
		$('#id07').css("display","none");
		//alert("Position ID:: "+posId);
	});
	
	$('#POS_POP_CANCEL1').on('click', function(e){
			$('#id07').css("display","none");
			$('#posresultSec1').css('display', 'none');
			$('#POS_LOV').children('option[id="1"]').prop('selected',true);
	});
	
	var table=$('#POS_LIST1').DataTable();
	
	$('#POS_LIST1 tbody').on('click','tr',function(){
		
		var tbldata=$(this).children('td').map(function(){
			return $(this).text();
			
		}).get();	
		
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			
		}
		else{
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData=tbldata[0];
		var dtDataId=tbldata[6];
		posId=dtDataId;
		$('#POS_LOV').val(dtData);
		$('#POS_LOV').children('option[id="2"]').text(dtData);
		$('#POS_LOV').children('option[id="2"]').val(dtDataId);
		$('#POS_LOV').children('option[id="2"]').prop('selected',true);		
		
	});
	
});


/*******************************Popup table selected row END*******************************************/

/**************************************POPUP POSITION ON BUID END********************************************/




/**********************************POPUP DEPARTMENT ON BU ID*************************************/		
				
function loadDEPTBUData(){				
var jsonUrldeptbu ='/enterprisesetup/position/getDepartmentByBUId';
		$.ajax({
			type: 'POST',
			url: jsonUrldeptbu,
			 data: {
				 "name": searchdsName,
				 "code": searchdsCode,
				 "dataSets": DEPT_DATASET,
				 "status": "Active"
				},
			dataSrc: '',
			dataType: 'json',
			success: function(data){
				jsonData=data;
				populateDEPTBUDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
}
		

	function populateDEPTBUDataTable(data){
		$("#DEPTBU_LIST").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#deptresultSec').css('display', 'none');
			$('#noDataDEPT').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#DEPTBU_LIST").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.effectstartdate,
					dat.dataset,
					dat.status,
					dat.departmentsid			
					]);
			}
			$('#deptresultSec').css('display', 'block');
			$('#noDataDEPT').css('display', 'none');
		}
	}
	
	/*******************************Popup table selected row start*******************************************/
	
	
	$('#DEPT_LOV').on('change',function(){
	
	var selectObject=$(this).children("option:selected").val();
	if(selectObject=='search'){

		$('#id02').css("display","block");
		$('#DEPT_DATASET').val(datasetId);
		
	}
	else if(selectObject!='search'){
		$('#id02').css("display","none");
	}
});


$(document).ready(function(){
	
	$('#DEPT_SEARCH').on('click', function(e){

		$('#id02').css("display","block");
		DEPT_DATASET = $('#DEPT_DATASET').val();
		searchdsCode =  $("#DEPT_CODE").val();
		searchdsName = $("#DEPT_NAME").val();
		//searchstatus=$('#DEPT_STATUS').children("option:selected").val();
		loadDEPTBUData();
		
	});
	
	$('#CR_DEP_POP_OK').on('click', function(e){
		$('#deptresultSec').css('display', 'none');
		$('#id02').css("display","none");
		
	});
	$('#CR_ENT_DEP_CANCEL').on('click', function(e){
			$('#id02').css("display","none");
			$('#deptresultSec').css('display', 'none');
			$('#DEPT_LOV').children('option[id="1"]').prop('selected',true);
	});
	
	
	var table=$('#DEPTBU_LIST').DataTable();
	
	$('#DEPTBU_LIST tbody').on('click','tr',function(){
		
		var tbldata=$(this).children('td').map(function(){
			return $(this).text();
			
		}).get();	
		
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			
		}
		else{
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData=tbldata[0];
		var dtDataId=tbldata[5];
		//alert(dtDataId);
		depid=dtDataId;
		$('#DEPT_LOV').val(dtData);
		$('#DEPT_LOV').children('option[id="2"]').text(dtData);
		$('#DEPT_LOV').children('option[id="2"]').val(dtDataId);
		$('#DEPT_LOV').children('option[id="2"]').prop('selected',true);

		
	});
	
});


/*******************************Popup table selected row END*******************************************/

/**************************************POPUP DEPT ON BU END********************************************/




/**********************************POPUP JOBS ON BU START*************************************/		
				
function loadJOBSBUData(){				
var jsonUrljobsbu ='/enterprisesetup/jobbind/'+datasetId;
		$.ajax({
			type: 'GET',
			url: jsonUrljobsbu,
			dataSrc: '',
			dataType: 'json',
			success: function(data){
				jsonData=data;
				populateJOBSBUDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
}
		

	function populateJOBSBUDataTable(data){
		$("#JOBSBU_LIST").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#jobsburesultSec').css('display', 'none');
			$('#noDataJOBSBU').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#JOBSBU_LIST").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.effectstartdate,
					dat.dataset,
					dat.status,
					dat.jobsid			
					]);
			}
			$('#jobsburesultSec').css('display', 'block');
			$('#noDataJOBSBU').css('display', 'none');
		}
	}
	
	/*******************************Popup table selected row start*******************************************/
	
	
	$('#JOBS_LOV').on('change',function(){
	
	var selectObject=$(this).children("option:selected").val();
	if(selectObject=='search'){
		loadJOBSBUData();
		$('#id09').css("display","block");
		
		
	}
	else if(selectObject!='search'){
		$('#id09').css("display","none");
	}
});


$(document).ready(function(){
	
	$('#OPTIONSEARCHJOBSBU').on('click', function(e){
		$('#id09').css("display","block");
		
	});
	
	$('#JOBSBU_POP_OK').on('click', function(e){
		$('#jobsburesultSec').css('display', 'none');
		$('#id09').css("display","none");
		
	});
	$('#JOBSBU_POP_CANCEL').on('click', function(e){
			$('#id09').css("display","none");
			$('#jobsburesultSec').css('display', 'none');
			$('#JOBS_LOV').children('option[id="1"]').prop('selected',true);
	});
	
	var table=$('#JOBSBU_LIST').DataTable();
	
	$('#JOBSBU_LIST tbody').on('click','tr',function(){
		
		var tbldata=$(this).children('td').map(function(){
			return $(this).text();
			
		}).get();	
		
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			
		}
		else{
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData=tbldata[0];
		var dtDataId=tbldata[5];
		
		$('#JOBS_LOV').val(dtData);
		$('#JOBS_LOV').children('option[id="2"]').text(dtData);
		$('#JOBS_LOV').children('option[id="2"]').val(dtDataId);
		$('#JOBS_LOV').children('option[id="2"]').prop('selected',true);		
		
	});
	
});


/*******************************Popup table selected row END*******************************************/

/**************************************POPUP JOBS ON BU END********************************************/



/**************************Pop-up LOCATION SEARCH start here****************************************/
	
	$(function() {
		$("#EnterpriseList").DataTable({
			'columnDefs': [{
				'targets': [3,4],
				'orderable': false
			}]
		});
		
		var searchName = '';
		var searchCode = '';
		var searchCountry = '';
		var searchStatus = '';
		var jsonUrl = '/enterprisesetup/edit/enterpriseLocation/searchEnterpriseLocation';
		
		
		
		$("#CR_ENT_POP_SEARCH").click(function(){
			
			searchName = $('#CR_ENT_POP_NAME').val();
			searchCode = $('#CR_ENT_POP_CODE').val();
			searchCountry = $('#CR_ENT_POP_CNTRY').val();
			searchStatus = $('#CR_ENT_POP_STATUS').val();
			
			loadLocTableData();
			$('#resultSec').css('display', 'block');
		});
			
			
		function loadLocTableData(){
			$.ajax({
				type: 'POST',
				url: jsonUrl,
				dataSrc: '',
				data: {
					"name": searchName,
					"code": searchCode,
					"country": searchCountry,
					"status": "Active"
				},
				dataType: 'json',
				success: function(data){
					jsonData = data;
					populateLocDataTable(jsonData);
				},
				error: function(e){
					console.log("There was an error with request...");
					console.log("error: " + JSON.stringify(e));
				}
			});
		}
		
		
		function populateLocDataTable(data){
			$("#EnterpriseList").DataTable().clear();
			var dataLength = Object.keys(data).length;
			if(dataLength == 0){
				$('#resultSec').css('display', 'none');
				$('#noDataLOcation').css('display', 'block');
			} else {
				for(var i=0; i < dataLength; i++){
					var dat = data[i];
					$("#EnterpriseList").dataTable().fnAddData([
						dat.name,
						dat.code,
						dat.country,
						dat.state,
						dat.status,
						"<ed rm='/enterprisesetup/edit/enterpriseLocation/"+dat.locationid+"' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>",
						dat.locationid
					]);
					
					
					
				}
				$('#resultSec').css('display', 'block');
				$('#noDataLOcation').css('display', 'none');
			}
		}
	});

	$(document).on('click', 'ed', function(e){
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});
	
/*******************************Popup table selected row start*******************************************/
	
	$('#CR_ENT_LOC_CODE').on('change',function(){
		
		var selectObject=$(this).children("option:selected").val();
		if(selectObject=='search'){
			$('#id01').css("display","block");
			
		}
		else if(selectObject!='search'){
			$('#id01').css("display","none");
		}
	});
	
	
	$(document).ready(function(){
			
		$('#btnsearch').on('click', function(e){
			$('#id01').css("display","block");
			
		});
		
		$('#CR_ENT_POP_OK').on('click', function(e){
			$('#resultSec').css('display', 'none');
			$('#id01').css("display","none");
			
			
		});
		
		$('#CR_ENT_POP_CANCEL').on('click', function(e){
			$('#id01').css("display","none");
			$('#resultSec').css('display', 'none');
			$('#CR_ENT_LOC_CODE').children('option[id="1"]').prop('selected',true);
		});
		
		var table=$('#EnterpriseList').DataTable();
		
		$('#EnterpriseList tbody').on('click','tr',function(){
			
			var tbldata=$(this).children('td').map(function(){
				return $(this).text();
				
			}).get();	
			
			if($(this).hasClass('selected')){
				$(this).removeClass('selected');
				
			}
			else{
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
			var dtData=tbldata[0];
			var dtDataId=tbldata[6];
			$('#CR_ENT_LOC_CODE').val(dtData);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').text(dtData);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').val(dtDataId);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').prop('selected',true);		
			
		});
		
	});
	
/*******************************Popup table selected row END*******************************************/
/**************************Pop-up LOCATION SEARCH END HERE****************************************/

	

/**************************************POPUP LEGAL SEARCH START********************************************/

	var jsonUrlLEG = '/enterprisesetup/searchLegalEntity/getLegalEntityId';

	var legName = '';
	var legCountry = '';
	var legEntityIdentifier='';
	
	$("#LEGAL_SEARCH").on('click', function(e){
		legName =  $("#LEGAL_NAME").val();
		legCountry = $("#LEGAL_COUNTRY").val();
		legEntityIdentifier=$('#LEGAL_ENTITY_IDENTIFIER').val();
		loadLegData();
		
	});
	function loadLegData(){
		$.ajax({
			type: 'POST',
			url: jsonUrlLEG,
			dataSrc: '',
			data: {
				"COUNTRY": legCountry,
				"ENTITY":legEntityIdentifier,
				"NAME": legName	
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateLegDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}

	function populateLegDataTable(data){
		$("#LegalList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#legresultSec').css('display', 'none');
			$('#noDataLEG').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#LegalList").dataTable().fnAddData([
					dat.name,
					dat.country,
					dat.legalentityidentifier,
					dat.effectstartdate,
					dat.placeofregistration,
					dat.registrationnumber,					
					dat.legalentityid
					
				]);
				
			}
			$('#legresultSec').css('display', 'block');
			$('#noDataLEG').css('display', 'none');
		}
	}
	
	/*******************************Popup table selected row start*******************************************/

	function legempl(){
	//debugger;
	var selectObject=$('#legalEmployer').children("option:selected").val();
	if(selectObject=='search'){
		$('#id05').css("display","block");
		
	}
	else if(selectObject!='search'){
		$('#id05').css("display","none");
	}
	}




$(document).ready(function(){
	
	if(parseInt($('#CR_HR_MARITAL_STATUS').children("option:selected").val())==93){
		$("#MARRIAGE_DATE").css("display","block");
	}else if(parseInt($('#CR_HR_MARITAL_STATUS').children("option:selected").val())!=93){
		$("#MARRIAGE_DATE").css("display","none");
	}
	
	$('#btnsearchLegal').on('click', function(e){
		$('#id05').css("display","block");
		
	});
	
	$('#LEG_POP_OK').on('click', function(e){
		$('#legresultSec').css('display', 'none');
		$('#id05').css("display","none");
	});
	
	$('#LEG_POP_CANCEL').on('click', function(e){
			$('#id05').css("display","none");
			$('#legresultSec').css('display', 'none');
			$('#legalEmployer').children('option[id="1"]').prop('selected',true);
	});
	
	var table=$('#LegalList').DataTable();
	
	$('#LegalList tbody').on('click','tr',function(){
		
		var tbldata=$(this).children('td').map(function(){
			return $(this).text();
			
		}).get();	
		
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			
		}
		else{
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData=tbldata[0];
		var dtDataId=tbldata[6];
		$('#legalEmployer').val(dtData);
		$('#legalEmployer').children('option[id="2"]').text(dtData);
		$('#legalEmployer').children('option[id="2"]').val(dtDataId);
		$('#legalEmployer').children('option[id="2"]').prop('selected',true);
		legVal=dtData;
		
	});
});







/*******************FUNCTION FOR HIRE VALIDATION*****************/

function validateHire(HIRE_DATE,LEGAL_ENTITY,WORKER_TYPE,HIRE_ACTION,HIRE_ACTION_REASON,HIRE_P_NUM){
	//$("#HIRE_LOADER").css("display","none");
	$('#HIRE_ERROR').text("");
	$('#LEGAL_ERROR').text("");
	$('#TYPE_ERROR').text("");
	$('#HIRE_ACTION_ERROR').text("");
	$('#HIRE_ACTION_REASON_ERROR').text("");
	$('#HIRE_PNUM_ERROR').text("");

	if(HIRE_DATE===undefined || HIRE_DATE===""){
		$('#HIRE_ERROR').text("Please select Hire Date.");
		$('#HIRE_ERROR').focus();
		return false;
	}

	if(HIRE_P_NUM===undefined || HIRE_P_NUM===""){
		$('#HIRE_PNUM_ERROR').text("Please enter Person Number.");
		$('#HIRE_PNUM_ERROR').focus();
		return false;
	}

	if(WORKER_TYPE===undefined || WORKER_TYPE===""){
		$('#TYPE_ERROR').text("Please select worker type.");
		$('#TYPE_ERROR').focus();
		return false;
	}

	if(HIRE_ACTION===undefined || HIRE_ACTION===""){
		$('#HIRE_ACTION_ERROR').text("Please select hire action.");
		$('#HIRE_ACTION_ERROR').focus();
		return false;
	}
	if(LEGAL_ENTITY===undefined || LEGAL_ENTITY===""){
		$('#LEGAL_ERROR').text("Please select legal entity.");
		$('#LEGAL_ERROR').focus();
		return false;
	}

	if(HIRE_ACTION_REASON===undefined || HIRE_ACTION_REASON===""){
		$('#HIRE_ACTION_REASON_ERROR').text("Please select Action Reason.");
		$('#HIRE_ACTION_REASON_ERROR').focus();
		return false;
	}

	return true;
}

/*******************FUNCTION FOR LOCATION VALIDATION END*****************/



/********************************Popup table selected row END****************************************/
/***********************************POPUP LEGAL SEARCH END*******************************************/

/*function loadSaveHireData(cmd)
{

	document.getElementById("cmd").value=cmd;
		alert("hii"+cmd);
	$.ajax({
		type: 'POST',
		url: '../newperson/nextandsaveperson',
		data: $('#frmHire').serialize(),
		contentType:"application/x-www-form-urlencoded",
		processData:false,
		catch:false,
		success: function(response){
			alert(response);
			$('#frmHire').html(response);
		},
		error: function(e){
			alert(e);
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
});

}*/