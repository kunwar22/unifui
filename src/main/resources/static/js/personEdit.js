/**********************CODE FOR OPEN CURRENT TAB AFTER EDIT***********************/
$( document ).ready(function() {
	//debugger;
    openTab(tabname);
   myFunction();
	//$(tabname).addClass("w3-theme");
});

var myVar;

	function myFunction() {
		//	debugger;
			document.getElementById("REPORTS_LOADER").style.display = "block";
  			myVar = setTimeout(showPage, 3000);
		}

	function showPage() {
	//	debugger;
			  document.getElementById("REPORTS_LOADER").style.display = "none";
			 // document.getElementById("myDiv").style.display = "block";
}


/******************************************************************/

$('#NAME_CANCEL_BTN').on('click', function(e){
	 var url = "/personedit/managepersonedit";
  	 $('#div_replace').load(url);
});
$('#ADDR_CANCEL_BTN').on('click', function(e){
	 var url = "/personedit/managepersonedit";
  	 $('#div_replace').load(url);
});

$('#PHONE_CANCEL_BTN').on('click', function(e){
	 var url = "/personedit/managepersonedit";
  	 $('#div_replace').load(url);
});
$('#EMAIL_CANCEL_BTN').on('click', function(e){
	var url = "/personedit/managepersonedit";
  	 $('#div_replace').load(url);
});
$('#MARITAL_CANCEL_BTN').on('click', function(e){
	 var url = "/personedit/managepersonedit";
  	 $('#div_replace').load(url);
});
$('#NAT_CANCEL_BTN').on('click', function(e){
	 var url = "/personedit/managepersonedit";
  	 $('#div_replace').load(url);
});
$('#BIO_CANCEL_BTN').on('click', function(e){
	 var url = "/personedit/managepersonedit";
  	 $('#div_replace').load(url);
});

/*********************CODE FOR SWITCHING TABS*********************/
var mode="";

function openTab(legal){
	//debugger;
	var i,x,tablinks;
	x=document.getElementsByClassName("legal");
	for(i=0;i<x.length;i++){
		x[i].style.display="none";
	}
	tablinks=document.getElementsByClassName("tablink");
	for(i=0;i<tablinks.length;i++){
		tablinks[i].className=tablinks[i].className.replace("w3-theme","");
	}
	document.getElementById(legal).style.display="block";
	$('button[name =\"'+legal+'\"]').addClass("w3-theme");
	//evt.currentTarget.className+=" w3-theme";
	
}
/******************************************************************/


/************************ALL CODE FOR NAME TAB************************/


$('#EDIT_NAME_TAB').on('change', function(e){
	//debugger;
	var date=$("#PD_START_DATE").val();
	
	if($("#EDIT_NAME_TAB option:selected").val()=="Correct"){
	$('#NAME_TAB_SUBMIT_BTN').css("display","inline-block");
	$('#NAME_CANCEL_BTN').css("display","inline-block");
	$("#NAME_DETAILS :input").prop("disabled",false);
	$("#PD_START_DATE").prop("readonly",true);
	}
	if($("#EDIT_NAME_TAB option:selected").val()=="Update"){
	$('#NAME_TAB_SUBMIT_BTN').css("display","inline-block");
	$('#NAME_CANCEL_BTN').css("display","inline-block");
	$("#NAME_DETAILS :input").prop("disabled",false);
	$("#PD_START_DATE").val("");
	$("#PD_START_DATE").attr("min",date);
	}
});

function correctupdateNameTab() {
if($("#PD_START_DATE").val()=="" ||$("#PD_START_DATE").val()==null || $("#PD_START_DATE").val()==undefined ){
	$("#DATE_ERROR").css("display","block");
	return;
}
var fd = $("#NAME_DETAILS").serialize();
$.ajax({
		url: "/personedit/nametabcorrectupdate",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			//alert(response.message+"||"+response.status);
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

}
/**********************************************************************/

/***********************ALL CODE FOR ADDRESS TAB************************/

function editfunction(id, mode){
	$('#SUBMIT_ADDR_TAB_BTN').css("display","inline-block");
	$('#ADDR_CANCEL_BTN').css("display","inline-block");
	//debugger;
	mode=$('#'+id).children("option:selected").val();
	
	var actionid = id.substring(id.lastIndexOf("_") +1);
	//alert("Action Id :: "+actionid+"  ID :: "+id+" Name :: "+mode);
	          $('#addrpopup').css('display','block'); 

	$("#mode").val(mode); 
                                                  
	$.ajax({
		type: 'GET',
		url: "/personedit/getaddressbyactionid/"+actionid,
		dataSrc: '',
		dataType: 'json',
		success: function(data){
			
			$('#POP_DATE').val(data.effectivestartdate);
			$('#POP_ACTIONID').val(data.actionid);
			$('#POP_PERSONID').val(data.personid);
			$('#POP_PERSONNBR').val(data.personnumber);
			
			$('#POP_COUNTRY').children('option[id="1"]').text(data.countrydsc);
			$('#POP_COUNTRY').children('option[id="1"]').val(data.country);
			$('#POP_COUNTRY').children('option[id="1"]').prop('selected',true);
			
			$('#POP_STATE').children('option[id="1"]').text(data.statedsc);
			$('#POP_STATE').children('option[id="1"]').val(data.state);
			$('#POP_STATE').children('option[id="1"]').prop('selected',true);
			
			$('#POP_ADDR_TYPE').children('option[id="1"]').val(data.addresstype);
			$('#POP_ADDR_TYPE').children('option[id="1"]').text(data.addresstypedsc);
			$('#POP_ADDR_TYPE').children('option[id="1"]').prop('selected',true);
			
			$('#POP_ADDRID').val(data.addressid);
			
			$('#POP_ADDR1').val(data.addressline1);
			$('#POP_ADDR2').val(data.addressline2);
			
			$('#POP_CITY').val(data.citytown);
			$('#POP_PIN').val(data.pinCode);
			
			//alert(mode);
			if(mode=="Correct"){
				$("#POP_SUBMIT_BTN").css("display","inline-block");
			}else if(mode=="Update"){
				$('#POP_DATE').prop('disabled',false);
				var date=$("#POP_DATE").val();
				$("#POP_DATE").val("");
				$("#POP_DATE").attr("min",date);
			} 
	

		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
	
	
}

$('#POP_DATE').on('change', function(e){
	$("#POP_SUBMIT_BTN").css("display","inline-block");
});


$('#POP_CANCEL_BTN').on('click', function(e){
	$('#addrpopup').css("display","none");
});

function correctupdateAddr() {
	
$('#POP_DATE').prop('disabled',false);
var fd = $("#ADDR_POP_FORM").serialize();
//alert(mode);
var urladdr="/personedit/addresstabcorrectupdate";
//alert(urladdr);
$.ajax({
		url: urladdr,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

}

/***********************************************************************/


/****************ALL CODE FOR PHONE TAB*****************/

$('#EDIT_PHONE_TAB').on('change', function(e){
	//debugger;
	
	
	if($("#EDIT_PHONE_TAB option:selected").val()=="Correct"){
	$('#PHONE_SUBMIT_BTN').css("display","inline-block");
	$('#PHONE_CANCEL_BTN').css("display","inline-block");
	$("#PHONE_DETAILS :input").prop("disabled",false);
	$('#ADD_ROW_PHONE').css("display","inline-block");
	
	}
	if($("#EDIT_PHONE_TAB option:selected").val()=="Update"){
	$('#PHONE_SUBMIT_BTN').css("display","inline-block");
	$('#PHONE_CANCEL_BTN').css("display","inline-block");
	$("#PHONE_DETAILS :input").prop("disabled",false);
	$('#ADD_ROW_PHONE').css("display","inline-block");
	}
});

function addrowcontact(){
//debugger;
var date=new Date();
date=date.toISOString().substring(0,10);
var dataphone="";
	dataphone='<tr><td style="width:5%"><input  name="actionid"  id="actionid" value="0" type="hidden" /><input name="personid" id="personid"  type="hidden" /><input name="personnumber" id="personnumber" value="'+pnum+'" type="hidden" /><input name="contactid" id="contactid" value="0" type="hidden" /><input value="'+date+'" name="contactdetails[1].effectivestartdate" id="effectivestartdate" type="hidden" /><input style="zoom: 1.5;" class="w3-input w3-border phoneCheck" onclick="uncheckAllPhone(this.name)"  id="CHECKBOX_PRIMARY' + window.globalCounter + '" name="contactdetails[' + window.globalCounter + '].primary" type="checkbox" value="Yes" ></td><td style="width:10%"><select id="CR_HR_CONTACTTYPE' + window.globalCounter + '" class="w3-select w3-border" >'+$('#CR_HR_CONTACTTYPE').html()+'</select></td><td style="width:7%"><select id="CR_HR_COUNTRYCODE' + window.globalCounter + '" class="w3-select w3-border" >'+$('#CR_HR_COUNTRYCODE').html()+'</select></td><td style="width:50%"><input value="" class="w3-input w3-border"  type="text"></td><td style="width:5%"><input class="w3-btn w3-theme" id="deletephone" index="0" flg="phone" type="button" value="x"/></td></tr>';
$("#PHONE_DETAIL_TBL tbody").append(dataphone);

	$.each($('#PHONE_DETAIL_TBL tr'),function(index,val){
		//debugger;
	$(this).find("td:eq(0) input[id='actionid']").attr('name','contactdetails['+(index - 1)+'].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name','contactdetails['+(index - 1)+'].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name','contactdetails['+(index - 1)+'].personnumber');
		$(this).find("td:eq(0) input[id='contactid']").attr('name','contactdetails['+(index - 1)+'].contactid');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name','contactdetails['+(index - 1)+'].effectivestartdate');
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','contactdetails['+(index - 1)+'].primary');
		
		$(this).find("td:eq(1)").find("select").attr('name','contactdetails['+(index - 1)+'].contacttype');
		$(this).find("td:eq(2)").find("select").attr('name','contactdetails['+(index - 1)+'].countrycode');
		$(this).find("td:eq(3) input[type='text']").attr('name','contactdetails['+(index - 1)+'].contactnumber');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));
});

}

function uncheckAllPhone(name){
	var inputss = $(".phoneCheck");
	//debugger;
	for(i=0;i<inputss.length;i++){
		if(!(inputss[i].name === name))
		{
			inputss[i].checked = false;
		}		
	}
	
}



$(document).on("click","#deletephone",function(){
	$(this).parents("tr").remove();
	$.each($('#PHONE_DETAIL_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[id='actionid']").attr('name','contactdetails['+(index - 1)+'].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name','contactdetails['+(index - 1)+'].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name','contactdetails['+(index - 1)+'].personnumber');
		$(this).find("td:eq(0) input[id='contactid']").attr('name','contactdetails['+(index - 1)+'].contactid');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name','contactdetails['+(index - 1)+'].effectivestartdate');

		$(this).find("td:eq(0) input[type='checkbox']").attr('name','contactdetails['+(index - 1)+'].primary');
		
		$(this).find("td:eq(1)").find("select").attr('name','contactdetails['+(index - 1)+'].contacttype');
		$(this).find("td:eq(2)").find("select").attr('name','contactdetails['+(index - 1)+'].countrycode');
		$(this).find("td:eq(3) input[type='text']").attr('name','contactdetails['+(index - 1)+'].contactnumber');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));


	});

});

function phonecorrect() {

var fd = $("#PHONE_DETAILS").serialize();
//alert(mode);
var urladdr="/personedit/phonecorrect";
//alert(urladdr);
$.ajax({
		url: urladdr,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			
				
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

}
/*************************************************************************/

/************************ALL CODE FOR EMAIL TAB**************************/

$('#EDIT_EMAIL_TAB').on('change', function(e){
	//debugger;
	
	if($("#EDIT_EMAIL_TAB option:selected").val()=="Correct"){
	$('#EMAIL_SUBMIT_BTN').css("display","inline-block");
	$('#EMAIL_CANCEL_BTN').css("display","inline-block");
	$("#EMAIL_DETAILS :input").prop("disabled",false);
	$('#ADD_ROW_EMAIL').css("display","inline-block");
	}
});

function addrowemail(){
//debugger;
var date=new Date();
date=date.toISOString().substring(0,10);
var data="";
	 data='<tr><td style="width:10%"><input  name="actionid" value="0" id="actionid" type="hidden" /><input name="personid" id="personid"  type="hidden" /><input name="personnumber" id="personnumber" value="'+pnum+'" type="hidden" /><input name="emaildetailsid" id="emaildetailsid" value="0" type="hidden" /><input value="'+date+'" name="emailDetails[1].effectivestartdate" id="effectivestartdate" type="hidden" /><input style="zoom: 1.5;" class="w3-input w3-border emailCheck" onclick="uncheckAllMail(this.name)" name="emaildetails[' + window.globalCounter + '].primary" id="CHECKBOX_PRIMARY_EMAIL' + window.globalCounter + '" type="checkbox" value="Yes" ></td><td style="width:25%"><select id="CR_HR_EMAILTYPE' + window.globalCounter + '" class="w3-select w3-border" name="email[' + window.globalCounter + '].cont">'+$('#CR_HR_EMAILTYPE').html()+'</select></td><td style="width:30%"><input value="" class="w3-input w3-border" name="email[' + window.globalCounter + '].emailaddress" type="text"></td><td style="width:5%"><input class="w3-btn w3-theme" id="deleteemail"  type="button" value="x"/></td></tr>';
$("#EMAIL_DETAIL_TBL tbody").append(data);

	$.each($('#EMAIL_DETAIL_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[id='actionid']").attr('name','emailDetails['+(index - 1)+'].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name','emailDetails['+(index - 1)+'].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name','emailDetails['+(index - 1)+'].personnumber');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name','emailDetails['+(index - 1)+'].effectivestartdate');
		$(this).find("td:eq(0) input[id='emaildetailsid']").attr('name','emailDetails['+(index - 1)+'].emaildetailsid');
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','emailDetails['+(index - 1)+'].primary');
		$(this).find("td:eq(1)").find("select").attr('name','emailDetails['+(index - 1)+'].emailtype');
		$(this).find("td:eq(2) input[type='text']").attr('name','emailDetails['+(index - 1)+'].emailaddress');
		$(this).find("td:eq(3) input[type='button']").attr('index',(index - 1));
	});

}

function uncheckAllMail(name){
	var input = $(".emailCheck");
	//debugger;
	for(i=0;i<input.length;i++){
		if(!(input[i].name === name))
		{
			input[i].checked = false;
		}		
	}
	
}

$(document).on("click","#deleteemail",function(){
	$(this).parents("tr").remove();
	$.each($('#EMAIL_DETAIL_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[id='actionid']").attr('name','emailDetails['+(index - 1)+'].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name','emailDetails['+(index - 1)+'].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name','emailDetails['+(index - 1)+'].personnumber');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name','emailDetails['+(index - 1)+'].effectivestartdate');
		$(this).find("td:eq(0) input[id='emaildetailsid']").attr('name','emailDetails['+(index - 1)+'].emaildetailsid');
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','emailDetails['+(index - 1)+'].primary');
		$(this).find("td:eq(1)").find("select").attr('name','emailDetails['+(index - 1)+'].emailtype');
		$(this).find("td:eq(2) input[type='text']").attr('name','emailDetails['+(index - 1)+'].emailaddress');
		$(this).find("td:eq(3) input[type='button']").attr('index',(index - 1));
	});

});

function emailcorrect() {

var fd = $("#EMAIL_DETAILS").serialize();
//alert(mode);
var urladdr="/personedit/emailcorrect";
//alert(urladdr);
$.ajax({
		url: urladdr,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			
				
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

}

/************************************************************************/

/*************************ALL NATIONAL DETAILS CODE***********************/
$('#EDIT_NAT_TAB').on('change', function(e){
	//debugger;
	
	if($("#EDIT_NAT_TAB option:selected").val()=="Correct"){
	$('#NAT_SUBMIT_BTN').css("display","inline-block");
	$('#NAT_CANCEL_BTN').css("display","inline-block");
	$("#NAT_DETAILS :input").prop("disabled",false);
	$('#ADD_ROW_NAT').css("display","inline-block");
	}
});

function addrow(){
//debugger;
var date=new Date();
date=date.toISOString().substring(0,10);
var data="";
	 data='<tr><td style="width:10%"><input  name="actionid"  id="actionid" value="0" type="hidden" /><input name="personid" id="personid"  type="hidden" /><input name="personnumber" id="personnumber" value="'+pnum+'" type="hidden" /><input value="'+date+'" name="emailDetails[1].effectivestartdate" id="effectivestartdate" type="hidden" /><input name="nationaldetailsid" id="nationaldetailsid" value="0" type="hidden" /><input style="zoom: 1.5;" class="w3-input w3-border natCheck" onclick="uncheckAll(this.name)" name="nationaldetails[' + window.globalCounter + '].primary" id="CHECKBOX_NATIDENTIFIER' + window.globalCounter + '" type="checkbox" value="Yes" ></td><td style="width:25%"><select id="CR_HR_COUNTRY' + window.globalCounter + '" class="w3-select w3-border" name="nationaldetails[' + window.globalCounter + '].country">'+$('#CR_HR_COUNTRY').html()+'</select></td><td style="width:25%"><select id="regularTemp' + window.globalCounter + '" class="w3-select w3-border" name="nationaldetails[' + window.globalCounter + '].nationaltype">'+$('#nationType').html()+'</select></td><td style="width:30%"><input value="" class="w3-input w3-border"  type="text"></td><td style="width:5%"><input class="w3-btn w3-theme" id="delete" index="0" flg="national"  type="button" value="x"/></td></tr>';
	 $("#NAT_ID_TBL tbody").append(data);

$.each($('#NAT_ID_TBL tr'),function(index,val){
	//debugger;
		$(this).find("td:eq(0) input[id='actionid']").attr('name','natdetails['+(index - 1)+'].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name','natdetails['+(index - 1)+'].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name','natdetails['+(index - 1)+'].personnumber');
		$(this).find("td:eq(0) input[id='nationaldetailsid']").attr('name','natdetails['+(index - 1)+'].nationaldetailsid');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name','natdetails['+(index - 1)+'].effectivestartdate');
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','natdetails['+(index - 1)+'].primary');
		$(this).find("td:eq(1)").find("select").attr('name','natdetails['+(index - 1)+'].countryid');
		$(this).find("td:eq(2)").find("select").attr('name','natdetails['+(index - 1)+'].nationaltype');
		$(this).find("td:eq(3) input[type='text']").attr('name','natdetails['+(index - 1)+'].nationalid');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));
});

}


function uncheckAll(name){
	var inputs = $(".natCheck");
	//debugger;
	for(i=0;i<inputs.length;i++){
		if(!(inputs[i].name === name))
		{
			inputs[i].checked = false;
		}		
	}
}

$(document).on("click","#delete",function(){
	$(this).parents("tr").remove();
	$.each($('#NAT_ID_TBL tr'),function(index,val){
		$(this).find("td:eq(0) input[id='actionid']").attr('name','natdetails['+(index - 1)+'].actionid');
		$(this).find("td:eq(0) input[id='personid']").attr('name','natdetails['+(index - 1)+'].personid');
		$(this).find("td:eq(0) input[id='personnumber']").attr('name','natdetails['+(index - 1)+'].personnumber');
		$(this).find("td:eq(0) input[id='nationaldetailsid']").attr('name','natdetails['+(index - 1)+'].nationaldetailsid');
		$(this).find("td:eq(0) input[id='effectivestartdate']").attr('name','natdetails['+(index - 1)+'].effectivestartdate');
		$(this).find("td:eq(0) input[type='checkbox']").attr('name','natdetails['+(index - 1)+'].primary');
		$(this).find("td:eq(1)").find("select").attr('name','natdetails['+(index - 1)+'].countryid');
		$(this).find("td:eq(2)").find("select").attr('name','natdetails['+(index - 1)+'].nationaltype');
		$(this).find("td:eq(3) input[type='text']").attr('name','natdetails['+(index - 1)+'].nationalid');
		$(this).find("td:eq(4) input[type='button']").attr('index',(index - 1));
	});

	
});

function natcorrect() {

var fd = $("#NAT_DETAILS").serialize();
//alert(mode);
var urladdr="/personedit/natcorrect";
//alert(urladdr);
$.ajax({
		url: urladdr,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

}
/*************************************************************************/

$('#EDIT_MARITAL_DETAILS').on('change', function(e){	
	$('#MARITAL_SUBMIT_BTN').css("display","inline-block");
	$('#MARITAL_CANCEL_BTN').css("display","inline-block");
	$("#MARITAL_DETAILS :input").prop("disabled",false);
});

function maritalcorrect() {

var fd = $("#MARITAL_DETAILS").serialize();
//alert(mode);
var urladdr="/personedit/maritalcorrect";
//alert(urladdr);
$.ajax({
		url: urladdr,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

}

/**************************************************************/

$('#EDIT_BIO_DETAILS').on('change', function(e){	
	$('#BIO_SUBMIT_BTN').css("display","inline-block");
	$('#BIO_CANCEL_BTN').css("display","inline-block");
	$("#BIO_DETAILS :input").prop("disabled",false);
});


function biocorrect() {

var fd = $("#BIO_DETAILS").serialize();
//alert(mode);
var urladdr="/personedit/biocorrect";
//alert(urladdr);
$.ajax({
		url: urladdr,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			//alert(result);
			$('#div_replace').html(result);
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

}














$('#NAME_EDIT_BTN').on('click', function(e){
			$('#id01').css("display","block");
});

$('#ADDR_1_EDIT_BTN').on('click', function(e){
	$('#id02').css("display","block");
});


$('#GENDR_MARITAL_STATUS_EDIT_BTN').on('click', function(e){
	$('#id03').css("display","block");
});

$('#BIOGRAPHICAL_INFO_EDIT_BTN').on('click', function(e){
	$('#id04').css("display","block");
});

$('#COMM_METHOD_EDIT_BTN').on('click', function(e){
	$('#id05').css("display","block");
});



$("#AddRowEmail").click(function(){
	
	$("#EmailId").each(function(){
		
		var tds='<tr>';
		jQuery.each($('tr:last td',this),function(){
			tds+='<td>'+$(this).html()+'</td>';
		});
		tds+='</tr>';
		if($('tbody',this).length>0){
			$('tbody',this).append(tds);
		//	$('#resultSec').css('display', 'block');
		}else{
			$(this).append(tds);
		}
		
	});
});

