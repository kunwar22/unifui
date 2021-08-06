$(document).ready(function(){
	var down=$(".DOWNLOAD_LINK");
	for(i=0;i<down.length;i++){
		var _href = $(down[i]).attr("href");
		/*if (_href != undefined && _href != "" && _href != null) {
			_href = _href.replaceAll('/', "FORWARD_SLASH");
			_href = _href.replaceAll('\\', 'BACKWARD_SLASH');
			_href = _href.replaceAll('.', 'EXT_DOT');
	
			$("#DOWNLOAD_LINK1").attr("href", "/getContent?location=" + _href);
		}*/
		if(_href!=''){
			$(down[i]).attr("href", "/getContent?location=" + _href);
		}
		//$("#DOWNLOAD_LINK").attr("href", "/getContent?location=" + _href);
	}	
});

//var maxfy=getMaxfy();
var minfy=getMinfy();
function getMinfy(){
  var today = new Date();
  if ((today.getMonth() + 1) <= 3) {
    var minfy=(today.getFullYear() - 1)+"-04-01";
  } else {
    var minfy=today.getFullYear()+"-04-01";
  }
  return minfy;
}
function func(){
	var dt=new Date();
	var mon=dt.getMonth()+1;
	var dat=dt.getDate();
	var yea=dt.getFullYear();
	
	if(mon<10){
		mon="0"+mon;	
	}
	if(dat<10){
		dat="0"+dat;
	}
	
	var maxi=yea+"-"+mon+"-"+dat;
	//$(".date").attr("max",maxi);
	//$(".date").attr("min",minfy);	
}

function backBtnFunc(){
	var url = "/reimbursement/adhoclocalSearch";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

function submitbtn(){
	var url = "../reimbursement/adhoclocalSubmit/"+clid;
	/*console.log("submit");
    $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	*/
	window.open(url,'window','width=1000,height1000');
}

function addrow(){
	if($("#checkmode").val()=="edit"){
		e="!=";i="2";
	}
	else{
		e="==";i="1";
	}
	var trans=$(".transport");
	var station=$(".station");
	var tostation=$(".tostation");
	var vehicle=$(".vehicle");
	
	var addr='<div class="adhocaddrow">'
+'<div th:if="${adhocdata.getClaimid() '+e+' 0}">'
+'<input type="text" class="adhocid" name="travelbillclaimdetails[i].travelclaimdetailsid" value="0" style="display:none">'
+'<div class="w3-row w3-container"><div class="w3-quarter w3-container">'
+'<label>Date:&nbsp;</label><span style="color:red">*</span>'
+'<input type="date" name="travelbillclaimdetails[i].date" class="w3-input w3-border w3-round date" onclick="func()">'
+'<span style="color:red" class="datesp"></span>'
+'<span style="color:red" th:if="${em.getDate().length()==0 && bindingResult != null && bindingResult.getFieldError("date")!=null}"th:text="${bindingResult.getFieldError("date").getDefaultMessage()}">'
+'</span></div><div class="w3-quarter w3-container"><p>'
+'<label>From Location:&nbsp;</label><span style="color:red">*</span>'
+'<input type="text" name="travelbillclaimdetails[i].fromlocation" class="w3-input w3-border w3-round fromloc">'
+'<span style="color:red" class="fromsp"></span>'
+'<span style="color:red" th:if="${em.getFromlocation().length()==0 && bindingResult != null && bindingResult.getFieldError("fromlocation")!=null}" th:text="${bindingResult.getFieldError("fromlocation").getDefaultMessage()}"></span>'
+'</p></div><div class="w3-quarter w3-container"><p><label>To Location:&nbsp;</label><span style="color:red">*</span>'
+'<input type="text" name="travelbillclaimdetails[i].tolocation" class="w3-input w3-border w3-round toloc"><span style="color:red" class="tosp"></span>'
+'<span style="color:red" th:if="${em.getTolocation().length()==0 && bindingResult != null && bindingResult.getFieldError("tolocation")!=null}"	th:text="${bindingResult.getFieldError("tolocation").getDefaultMessage()}"></span>'
+'</p></div>'
+'<div class="w3-quarter w3-center" style="margin-top:23px"><p>'
+'<button class="w3-button w3-theme addrow" style="margin-left:5px"onclick="addrow()">'
+'<i class="fas fa-plus"></i></button><button class="w3-button w3-theme delrow" onclick="delrow(this)"  style="margin-left:3px">'
+'<i class="fa fa-trash"></i></button></p></div></div>'
+'<div class="w3-row w3-container"><div class="w3-quarter w3-container">'
+'<p><label>Transport Mode:&nbsp;</label><span style="color:red">*</span>'
+'<select name="travelbillclaimdetails[i].transportmodeid" class="w3-input w3-border w3-round transport">'
+$(trans[0]).html()+'</select>'
+'<input type="text" name="travelbillclaimdetails[i].transportmode" class="transname" style="display:none">'
+'<span style="color:red" class="transsp"></span>'
+'<span style="color:red" th:if="${em.getTransportmodeid()==0 && bindingResult != null && bindingResult.getFieldError("transportmodeid")!=null}"th:text="${bindingResult.getFieldError("transportmodeid").getDefaultMessage()}"></span>'
+'</p></div>'
+'<div class="w3-quarter w3-container"><p>'
+'<label>From Nearest Station:&nbsp;</label>'
+'<select name="travelbillclaimdetails[i].fromneareststationid" class="w3-input w3-border w3-round station">'
+$(station[0]).html()+'</select>'
+'<input type="text" name="travelbillclaimdetails[i].fromneareststation" class="nearname" style="display:none">'
+'</p></div>'
+'<div class="w3-quarter w3-container"><p>'
+'<label>To Nearest Station:&nbsp;</label>'
+'<select name="travelbillclaimdetails[i].toneareststationid" class="w3-input w3-border w3-round tostation">'
+$(tostation[0]).html()+'</select>'
+'<input type="text" name="travelbillclaimdetails[i].toneareststation" class="tonearname" style="display:none">'
+'</p></div>'
+'</div>'
+'<div class="w3-row w3-container" style="margin-bottom:25px">'
+'<div class="w3-quarter w3-container"><p><label>Claim Amount:&nbsp;</label><span style="color:red">*</span>'
+'<input type="text" name="travelbillclaimdetails[i].claimamt" class="w3-input w3-border w3-round requestedamt" value="0">'
+'<span style="color:red" class="claim"></span>'
+'<span style="color:red" th:if="${em.getTransportmodeid()==0 && bindingResult != null && bindingResult.getFieldError("claimamt")!=null}"th:text="${bindingResult.getFieldError("claimamt").getDefaultMessage()}"></span>'
+'</p></div>'
+'<div class="w3-quarter w3-container"><p><label>Travel Purpose:&nbsp;</label><span style="color:red">*</span>'
+'<input type="text" name="travelbillclaimdetails[i].travelpurpose" class="w3-input w3-border w3-round travel">'
+'<span style="color:red" class="travelsp"></span>'
+'<span style="color:red" th:if="${em.getTravelpurpose().length()==0 && bindingResult != null && bindingResult.getFieldError("travelpurpose")!=null}"th:text="${bindingResult.getFieldError("travelpurpose").getDefaultMessage()}"></span>'
+'</p></div>'
+'<div class="w3-quarter w3-container"><p><label>Vehicle Used:&nbsp;</label><span style="color:red">*</span>	'
+'<select name="travelbillclaimdetails[i].vehicleusedid" class="w3-input w3-border w3-round vehicle">'
+$(vehicle[0]).html()+'</select>'
+'<input type="text" name="travelbillclaimdetails[i].vehicleused" class="vehiclename" style="display:none">'
+'<span style="color:red" class="vehiclesp"></span>'
+'<span style="color:red" th:if="${em.getVehicleusedid()==0 && bindingResult != null && bindingResult.getFieldError("vehicleusedid")!=null}"th:text="${bindingResult.getFieldError("vehicleusedid").getDefaultMessage()}"></span>'
+'</p></div>'
+'</div>'
+'<div class="w3-row w3-container" style="margin-bottom:25px">'
+'<div class="w3-quarter w3-container"  id="adhocfiles">'
+'<input class="attachments" name="travelbillclaimdetails[i].attachments" type="hidden"/>'
+'<input type="hidden" name="travelbillclaimdetails[i].filepres" class="filepres">'
+'<p>'
+'<label>File : </label> '
+'<input type="file" name="file" class="file"/>'
+'</p>'
+'</div>'
+'</div>'
+'</div>'
+'</div>';
	
	$('#adhocrows').append(addr);			
		
}

function delrow(x){
	if(modee=='edit'){
		var childnum=$(".adhocaddrow").length;
		var m=1;
	}
	else{
		var childnum=$("#adhocrows").children().length;
		var m=1;
	}	
	if (childnum>m){
		$(x).parentsUntil("#adhocrows") .remove( ".adhocaddrow" );	
	}	
	//alert(childnum);	
}



function seri(x){
	
	var claimFinal=0;
	
	var date=$(".date");
	var from=$(".fromloc");
	var to=$(".toloc");
	var tran=$(".transport");
	var trann=$(".transname");
	var claim=$(".requestedamt");
	var travel=$(".travel");
	var nearest=$(".station");
	var nearestn=$(".nearname");
	var tonearest=$(".tostation");
	var tonearestn=$(".tonearname");
	var vehicle=$(".vehicle");
	var vehiclen=$(".vehiclename");
	var att=$(".attachments");
	var adhocid=$(".adhocid");
	
	for(var i=0;i<date.length;i++){
		$(date[i]).attr("name","travelbillclaimdetails["+i+"].date");
		$(from[i]).attr("name","travelbillclaimdetails["+i+"].fromlocation");
		$(to[i]).attr("name","travelbillclaimdetails["+i+"].tolocation");
		$(tran[i]).attr("name","travelbillclaimdetails["+i+"].transportmodeid");
		$(trann[i]).attr("name","travelbillclaimdetails["+i+"].transportmode");
		$(trann[i]).val($(tran[i]).children("option").filter(":selected").text());
		$(claim[i]).attr("name","travelbillclaimdetails["+i+"].claimamt");
		$(travel[i]).attr("name","travelbillclaimdetails["+i+"].travelpurpose");
		$(nearest[i]).attr("name","travelbillclaimdetails["+i+"].fromneareststationid");
		$(nearestn[i]).attr("name","travelbillclaimdetails["+i+"].fromneareststation");
		$(tonearest[i]).attr("name","travelbillclaimdetails["+i+"].toneareststationid");
		$(tonearestn[i]).attr("name","travelbillclaimdetails["+i+"].toneareststation");
		$(nearestn[i]).val($(nearest[i]).children("option").filter(":selected").text());
		$(vehicle[i]).attr("name","travelbillclaimdetails["+i+"].vehicleusedid");
		$(vehiclen[i]).attr("name","travelbillclaimdetails["+i+"].vehicleused");
		$(vehiclen[i]).val($(vehicle[i]).children("option").filter(":selected").text());
		$(att[i]).attr("name","travelbillclaimdetails["+i+"].attachments");	
		$(adhocid[i]).attr("name","travelbillclaimdetails["+i+"].travelclaimdetailsid");	
		
		 claimFinal=claimFinal+ parseFloat($(claim[i]).val());
	
	}
	//alert(claimFinal);

	$("#claimAmt").val(claimFinal);	
	
	
	var files=document.getElementsByName("file");
	var filepres=$(".filepres");
	var atthi=$(".atthi");
	//alert(files.length+" "+filepres.length+" "+atthi.length);
	for(i=0;i<files.length;i++){
		$(filepres[i]).attr("name","travelbillclaimdetails["+i+"].filepres");
		$(atthi[i]).attr("name","travelbillclaimdetails["+i+"].attachhidden");
		if($(files[i]).val()!=''){
			$(filepres[i]).val("Y");
		}
	}
	
	datavalidation(x);
}

function datavalidation(x){
	var from=$(".fromloc");
	var fromsp=$(".fromsp");
	var to=$(".toloc");
	var tosp=$(".tosp");
	var cmode=$("#checkmode");
	var chk="ok";
	var chckd="N";
	
	for(i=0;i<from.length;i++){
		if($(from[i]).val()==$(to[i]).val()){
			chk="not";
			$(fromsp[i]).text("From Location cannot be equal to To Location");
			$(tosp[i]).text("To Location cannot be equal to From Location");	
		}
		else{
			$(fromsp[i]).text("");
			$(tosp[i]).text("");
		}		
	}
	
	if($("#declaration:checked").length>0){
		if(chk=="ok" && x==0){
			//alert("1");
			saveDataFunc('Save','saveformadhoc');
		}
		else if(chk=="ok" && x==1){
			//alert("2");
			saveDataFunc('Update','saveformadhoc');
		}
		else if(chk=="ok" && x==2){
			//alert("2");
			saveDataFunc('Submit','saveformadhoc');
		}
		else if(chk=="ok" && x==3){
			//alert("2");
			saveDataFunc('Save&Submit','saveformadhoc');
		}
	}
	else{
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"You cannot save without declaration."				
				});
	}	
}

function saveDataFunc(str,frmid)
{
	//alert(str);
	var attachm=$(".attachments");
	var personnumber=document.getElementsByName("personnumber")[0].value;
	//alert(personnumber);
	$("#statusid").val(str);
	var doctype="ContingentReimbursement";
	
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	//alert(filepath);
	for(i=0;i<attachm.length;i++){
		$(attachm[i]).val("/EmployeeDocs/"+filepath);	
		//alert($(attachm[i]).val());	
	}
	
	var data = new FormData(document.getElementById(frmid));
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
	url: "/reimbursement/adhocSaveBtn/"+str,
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){			
		$("#replace_div").html(data);
		//alert("success");
		if( resultfinal=="Success"){
			/*$('#submitblock').css("display","block");
			$('#lblMsg').text(str+" success for Claim ID: "+resultfinal.substr(7,resultfinal.length)+". Click OK to continue.");
			$('#okbtn').toggleClass("w3-button w3-green");
			$('#okbtn').on('click', function(e){
				var url = $(this).attr("rm");
				$('#replace_div').load(/reimbursement/adhoclocalSearch);
			});*/
			if(str=="Update"){
				var msg="You have successfully updated Contingent Claim";
			}
			else if(str=="Submit"){
				var msg="You have successfully submitted Contingent Claim";
			}
			else if(str=="Save&Submit"){
				var msg="You have successfully submitted Contingent Claim";
			}
			else{
				var msg="You have successfully saved Contingent Claim";
			}
			bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message:msg,
						callback:function(){
							$('#replace_div').load("/reimbursement/adhoclocalSearch")
						}
					});
		}
		else if(resultfinal=="WRITE_ERROR"){
			//alert("Something went wrong while uploading the file(s).");
		}else{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Fill all the mandatory fields and re-upload attachments, if required."				
				});
		}
	},
	error: function(data){
		console.log("ERROR : "+JSON.stringify(data));
	}
	});
}

function download(x){
	var str=$(x).parent().text();
	alert(str);
	
	/*$.ajax({
	url: "/reimbursement/downloadf/"+str,
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){			
		alert(data);
	},
	error: function(data){
		alert("ERROR : "+JSON.stringify(data));
	}
	});*/
}








function approvalSubmit(_status)
{

	if($("#txtAmount").val()==''){
		$("#txtAmount").val("0");	
	}
	var formData = $('#saveformadhoc').serialize();
	

	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();

	console.log(formData);
	if(_status=='Approved'){
		if(amt=="" ){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Request cannot be Approved without filling Approved Amount.'				
			});
		}
		else{
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
				url: "/reimbursement/addhoclocalApproval/"+_status,
				type: "POST",
				cache: false,
				data: formData,
				processData: false,
				contentType: 'application/x-www-form-urlencoded',
				dataType: "json",
				success:function(result){
					if(result.status=="true"){
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully Approved.",
							callback:function(){
								window.location = "/selfservice";
							}
						});	
						}else if(result.status=="false"){
							bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message:"Something went wrong, Please take action again.",
							callback:function(){
								window.location = "/selfservice";
							}
						});
						}	
				},
				error:function(e){			
					console.log( "ERROR : "+ JSON.stringify(e) );
				}
			});
		}
	}
	else{
		if(cmt==""){
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Request cannot be Rejected without filling Comments.'				
			});
		}else{
			$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
				url: "/reimbursement/addhoclocalApproval/"+_status,
				type: "POST",
				cache: false,
				data: formData,
				processData: false,
				contentType: 'application/x-www-form-urlencoded',
				dataType: "json",
				success:function(result){
					if(result.status=="true"){
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully rejected.",
							callback:function(){
								window.location = "/selfservice";
							}
						});	
						}else if(result.status=="false"){
							bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message:"Something went wrong, Please take action again.",
							callback:function(){
								window.location = "/selfservice";
							}
						});
						}	
				},
				error:function(e){			
					console.log( "ERROR : "+ JSON.stringify(e) );
				}
			});
		}
	}
}

$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});