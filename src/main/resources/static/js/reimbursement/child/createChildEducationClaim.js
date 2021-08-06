var C_CLAIM_ID = '';
$(document).ready(function(){
	 var feetypeval = $('#FEESELECTOR').val();
    if (feetypeval == "School" || feetypeval == "Hostel") {
	$('#CHILD_BLOCK').show();
	}
	
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

var limit = 3;
var child_Counter = 1;
var claimid = '';

/* Child reimbursement done by Asmita on 18-09 */

$(document).ready(function() {
  generate_acad_year();
});

function backBtnFunc() {
  var url = "/reimbursement/child";
  $('#replace_div').load(url);
}

/* generation of academic year dropdown starts */
function generate_acad_year() {
  //debugger;
  var today = new Date();
  var current_year = today.getFullYear();
  var fiscal_year = "";
  var year = [];
  for (var i = 2; i >= 1; i--) {
    fiscal_year = (current_year - i) + "-" + (current_year - (i - 1));
    year.push(fiscal_year);
    /*console.log("year : "+year);*/
  }

  for (const y of year) {
	$('#acadyear').append($(document.createElement('option')).prop({
      value: y,
      text: y.charAt(0) + y.slice(1)
    }));
	//alert(y);

    var yr=$("#acadyear1 option:selected").val();
    if(!y.includes(yr))
    {
       $('#acadyear1').append($(document.createElement('option')).prop({
          value: y,
          text: y.charAt(0) + y.slice(1)
        }));
    }
  }
}
/* generation of academic year dropdown ends */


/* Function for Adding Child Block starts */
function addchildnew() {
  //debugger;

  var newrow = '<div class="childrow"><div class="w3-container w3-border w3-padding-large">' +
    '<div class="w3-bar w3-theme-l3">' +
    '<button onclick="remove_Child(this)" class="w3-button w3-theme w3-right delchild" style="margin-top: 8px;margin-right:8px;">' +
    '<i class="fa fa-trash"></i></button><h5 class="w3-bar-item">Child ' + (
      child_Counter + 1) + '</h5></div>' +
    '<div class="w3-row"><div class="w3-half w3-container">' +
    '<p><label>Child Name </label> <span style="color: red;">*</span>' +
    '<select name="childs[0].childname" id="CHILD_NAME" ' +
    'class="w3-select w3-border w3-round" onchange="loadChildDetails(event,\'CHILD_NAME\',\'CHILD_DOB\',\'CHILD_GENDER\',\'CHILD_TWINS\',\'CHILD_DISABILITY\')">' +
    $('#CHILD_NAME').html() + '</select>'+
	'<span style="color: #ff0000" th:if=\'${bindingResult != null && bindingResult.getFieldError("childname")!=null && childreimbursemodel.getchilds().get('+child_Counter+').getChildname()==""}\' th:text=\'${bindingResult.getFieldError("childname").getDefaultMessage()}\'></span>'+
	'</p></div><div class="w3-half w3-container dob">' +
    '<p><label>Date of Birth </label> <span style="color: red;">*</span><input type="text" id="CHILD_DOB" name="childs[0].dob" class="w3-input w3-border w3-round birthday" readonly />' +
    '</p></div></div><div class="w3-row"><div class="w3-half w3-container gender">' +
    '<p><label>Gender </label> <span style="color: red;">*</span> ' +
    '<input type="text" id="CHILD_GENDER" name="childs[0].gender" class="w3-input w3-border w3-round" readonly />' +
    '</p></div><div class="w3-half w3-container">' +
    '<p><label>Class</label> <span style="color: red;">*</span> ' +
    '<select id="CHILD_CLASS" name="childs[0].childclass" class="w3-select w3-border w3-round">' +
    $('#CHILD_CLASS').html() + '</select></p></div></div><div class="w3-row">' +
    '<div class="w3-half w3-container"><p><label>School Name</label> <span style="color: red;">*</span>' +
    '<input id="SCHOOL_NAME" type="text" name="childs[0].schoolname" class="w3-input w3-border w3-round"></p>' +
    '</div><div class="w3-half w3-container"><p><label>School Address</label><br />' +
    '<textarea rows="1" cols="30" name="childs[0].schooladdr" id="SCHOOL_ADDR" class="w3-input w3-border w3-round"></textarea>' +
    '</p></div></div><div class="w3-row"><div class="w3-half w3-container"><p>' +
    '<label>Amount Claimed</label> <span style="color: red;">*</span>' +
    '<input type="text" name="childs[0].fees" id="CLAIM_AMT" value="0" class="w3-input w3-border w3-round">' +
    '</p></div><div class="w3-half w3-container twin"><p>' +
    '<label>If Twin</label> <span style="color: red;">*</span><br />' +
    '<input type="text" id="CHILD_TWINS" name="childs[0].istwins" readonly class="w3-input w3-border w3-round">' +
    '</p></div></div><div class="w3-row"><div class="w3-half w3-container disab">' +
    '<p><label>If Disabled</label> <span style="color: red;">*</span><br />' +
    '<input type="text" id="CHILD_DISABILITY" name="childs[0].disability" readonly class="w3-input w3-border w3-round">' +
    '</p></div><div class="w3-half w3-container"><p><label>Receipt Enclosed </label>' +
    '<input type="hidden" name="childs[0].filepres" class="filepres"><input type="file" name="file" />' +
    '</p></div></div></div></div>';


  $('#childrows').append(newrow);
  child_Counter++;
  if (child_Counter > 2) {
    bootbox.alert({
      size: 'medium',
      title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
      message: 'You cannot claim this reimbursement for more than three childs!!'
    });
  }
  $.each($('#childrows .childrow'), function(index) {
    ////debugger;
    $(this).find('#CHILD_NAME').attr('name', 'childs[' + (index) + '].childname');
	$(this).find('#CHILD_DOB').attr('name', 'childs[' + (index) + '].dob');
    $(this).find('#CHILD_GENDER').attr('name', 'childs[' + (index) + '].gender');
    $(this).find('#CHILD_AGE').attr('name', 'childs[' + (index) + '].age');
    $(this).find('#CHILD_CLASS').attr('name', 'childs[' + (index) + '].childclass');
    $(this).find('#SCHOOL_NAME').attr('name', 'childs[' + (index) + '].schoolname');
    $(this).find('#SCHOOL_ADDR').attr('name', 'childs[' + (index) + '].schooladdr');
    $(this).find('#CLAIM_AMT').attr('name', 'childs[' + (index) + '].fees');
    $(this).find('#CHILD_TWINS').attr('name', 'childs[' + (index) + '].istwins');
    $(this).find('#CHILD_DISABILITY').attr('name', 'childs[' + (index) + '].disability');
    $(this).find('#RECPT_ENCL').attr('name', 'childs[' + (index) + '].feereceipt');
	$(this).find('.filepres').attr('name', 'childs[' + (index) + '].filepres');
	$(this).find('.atthi').attr('name', 'childs[' + (index) + '].attachhidden');
  });

}

function names(){
	$.each($('#childrows .childrow'), function(index) {
    ////debugger;
    $(this).find('#CHILD_NAME').attr('name', 'childs[' + (index) + '].childname');
	$(this).find('#CHILD_DOB').attr('name', 'childs[' + (index) + '].dob');
    $(this).find('#CHILD_GENDER').attr('name', 'childs[' + (index) + '].gender');
    $(this).find('#CHILD_AGE').attr('name', 'childs[' + (index) + '].age');
    $(this).find('#CHILD_CLASS').attr('name', 'childs[' + (index) + '].childclass');
    $(this).find('#SCHOOL_NAME').attr('name', 'childs[' + (index) + '].schoolname');
    $(this).find('#SCHOOL_ADDR').attr('name', 'childs[' + (index) + '].schooladdr');
    $(this).find('#CLAIM_AMT').attr('name', 'childs[' + (index) + '].fees');
    $(this).find('#CHILD_TWINS').attr('name', 'childs[' + (index) + '].istwins');
    $(this).find('#CHILD_DISABILITY').attr('name', 'childs[' + (index) + '].disability');
    $(this).find('#RECPT_ENCL').attr('name', 'childs[' + (index) + '].feereceipt');
	$(this).find('.filepres').attr('name', 'childs[' + (index) + '].filepres');
	$(this).find('.atthi').attr('name', 'childs[' + (index) + '].attachhidden');
  });
}
/* Function for Adding Child Block ends */

/* Function For removing the new child block starts */

function remove_Child(e) {
  //debugger;
  $(e).closest(".childrow").remove();

  $.each($('#childrows .childrow'), function(index) {
    //debugger;
    $(this).find('#CHILD_NAME').attr('name', 'childs[' + (index) + '].childname');
    $(this).find('#CHILD_DOB').attr('name', 'childs[' + (index) + '].dob');
    $(this).find('#CHILD_GENDER').attr('name', 'childs[' + (index) + '].gender');
    $(this).find('#CHILD_AGE').attr('name', 'childs[' + (index) + '].age');
    $(this).find('#CHILD_CLASS').attr('name', 'childs[' + (index) + '].childclass');
    $(this).find('#SCHOOL_NAME').attr('name', 'childs[' + (index) + '].schoolname');
    $(this).find('#SCHOOL_ADDR').attr('name', 'childs[' + (index) + '].schooladdr');
    $(this).find('#CLAIM_AMT').attr('name', 'childs[' + (index) + '].fees');
    $(this).find('#CHILD_TWINS').attr('name', 'childs[' + (index) + '].istwins');
    $(this).find('#CHILD_DISABILITY').attr('name', 'childs[' + (index) + '].disability');
    $(this).find('#RECPT_ENCL').attr('name', 'childs[' + (index) + '].feereceipt');
  });
  //document.getElementById(id).remove();
}

/* Function For removing the new child block ends */

/* Function For displaying the 'add child' block starts */
$(function() {
  ////debugger;
  $('#FEESELECTOR').change(function() {
    $('#CHILD_BLOCK').show();
    var feetype = $('#FEESELECTOR').val();
    if (feetype == "School") {
      $('span.feelimit').html("Rs. 2250 per child per month");
	$('span.feelimithandicapped').html("Rs. 4500 per child per month");
    } else if (feetype == "Hostel") {
      $('span.feelimit').html("Rs. 6750 per child per month");
    }
  });

  var feetype1 = $('#FEESELECTOR1').val();
  if (feetype1 == "School") {
    $('#CHILD_BLOCK').show();
    $('span.feelimit').html("Rs. 2250 per child per month");
$('span.feelimithandicapped').html("Rs. 4500 per child per month");
  } else if (feetype1 == "Hostel") {
    $('#CHILD_BLOCK').show();
    $('span.feelimit').html("Rs. 6750 per child per month");
  }
});

/* Function For displaying the 'add child' block ends */

/* function for calculating age starts */

/*function calculateAge()
{
	//debugger;
	
	$.each($('#child_data .w3-row'),function(index){
		var dob = $(".birthday").val();
		
		var today = new Date();
		var dob = new Date(dob);
		var age = today.getFullYear() - dob.getFullYear();
		
		$(".age").attr("value",age);
		
	});
	
	$.each($('#child_data1 .w3-row'),function(index){
		var dob = $(".birthday").val();
		
		var today = new Date();
		var dob = new Date(dob);
		var age = today.getFullYear() - dob.getFullYear();
		
		$(".age").attr("value",age);
		
	});
	
}*/

/* function for calculating age ends */


/* Function For changing DOB on select of name of child from dropdown starts */
function loadChildDetails(e, childname, childdob, childgender, childtwin, childdisability) {
  //debugger;

  var child_name_select = document.getElementById(childname);

  $.each($('#childrows .childrow'), function(index) {
    //debugger;
    var datadob_ = $(this).find("select").children("option:selected").attr("data-child-dob");
    var datagender_ = $(this).find("select").children("option:selected").attr("data-child-gender");
    var datatwin_ = $(this).find("select").children("option:selected").attr("data-child-twins");
    var datadisab_ = $(this).find("select").children("option:selected").attr("data-child-disability");

    $(this).find(".dob input[type='text']").attr("value", datadob_);
    $(this).find(".gender input[type='text']").attr("value", datagender_);
    $(this).find(".twin input[type='text']").attr("value", datatwin_);
    $(this).find(".disab input[type='text']").attr("value", datadisab_);
  });
}

/* Function For changing DOB on select of name of child from dropdown ends */

/* JS For Form Submit Starts */

function ajaxPost(vmode, status,x) {
  ////debugger;
	$(x).css("display","none");
	names();
  var url = "";
  if (vmode == "save") {
    url = "/reimbursement/saveChildEducationClaim";
  }else if (vmode == "Save&Submit") {
    url = "/reimbursement/saveChildEducationClaim";
  } else if (vmode == "update" || vmode == "submit") {
    url = "/reimbursement/updateChildEducaticonReimbursement";
  }
  /*else if(status=="submit")
  {
  	
  }*/

var totalamt = parseFloat($("#CLAIM_AMT").val());
	if(totalamt == 0.0 || totalamt == 0) {
		flag = 'no';
		bootbox.alert({
			size: 'medium',
			title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
			message: "You can not claim with 0 amt. ",
		});
		$('#CLAIM_AMT').val(0);
		return;
	}

  var files=document.getElementsByName("file");
	var filepres=$(".filepres");
	//alert(files.length+" "+filepres.length+" "+atthi.length);
	for(i=0;i<files.length;i++){
		if($(files[i]).val()!=''){
			$(filepres[i]).val("Y");
		}
	}	
	
  var personnumber = $("#txtPersonNumber").val();
  $("#status_id").val(status);
  var doctype = "ChildReimbursement";
  personnumber = personnumber.trim();
  doctype = doctype.trim();
  var filepath = "";
  filepath += personnumber + "/" + doctype;
  console.log(filepath);
  $("#feereceipt").val("/EmployeeDocs/" + filepath);
  var form = $("#CER_SAVE")[0];
  console.log(form);
  var data = new FormData(form);
  $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
  $.ajax({
    url: url,
    type: "POST",
    enctype: "multipart/form-data",
    data: data,
    cache: false,
    contentType: false,
    processData: false,
    timeout: 600000,
    success: function(data) {
      console.log(data);
      $('#replace_div').html(data);
      if (finalresult == "Success") {
        if (vmode == "save") {
          bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
            message: "You have successfully saved Child Education Reimbursement.",
            callback: function() {
              $('#replace_div').load("/reimbursement/child");
            }
          });
        } else if (vmode == "update") {
          bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
            message: "You have successfully updated Child Education Reimbursement.",
            callback: function() {
              $('#replace_div').load("/reimbursement/child");
            }
          });
        }else if (vmode == "Save&Submit") {
          bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
            message: "You have successfully submitted Child Education Reimbursement for approval.",
            callback: function() {
              $('#replace_div').load("/reimbursement/child");
            }
          });
        }else if (vmode == "submit") {
          bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
            message: "You have successfully submitted Child Education Reimbursement for approval.",
            callback: function() {
              $('#replace_div').load("/reimbursement/child");
            }
          });
        }
      }
      /*else if( finalresult=="MISMATCH" )
      {
      	alert("MISMATCH");
      }
      else if( finalresult=="EMPTY_FILE" )
      {
      	alert("File is empty");
      }
      else if( finalresult=="WRITE_ERROR" )
      {
      	alert("Error in writing file.");
      }
      else if( finalresult=="IOEXCEPTION" )
      {
      	alert("IO Exception has occurred.");
      }
      else if( finalresult=="LOG_ERROR" )
      {
      	alert("Error while logging file info.");
      }
      else if( finalresult=="ILLEGALARG" )
      {
      	alert("Error while posting file log.");
      }*/
      else if (finalresult == "alreadyApplied") {
        bootbox.alert({
          size: 'medium',
          title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
          message: "Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required",
		  callback: function() {
              $('#replace_div').load("/reimbursement/child");
            }
        });
      } else {
        bootbox.alert({
          size: 'medium',
          title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
          message: "Fill all the mandatory fields and attach the file again, if required."
        });
      }
    },
    error: function(data) {
      console.log("ERROR : " + JSON.stringify(data));
      bootbox.alert({
        size: 'medium',
        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
        message: "You have already claimed the reimbursement for this year!!",
		callback: function() {
              $('#replace_div').load("/reimbursement/child");
            }
      });
      $('#replace_div').html(data);
    }
  });

}
/* Updating data 
var jsonUpdateUrl="/reimbursement/updateChildEducaticonReimbursement";
function updateDataFunc()
{
	alert("save");
	//debugger;
	var fd = $("#CER_UPDATE").serialize();
	console.log("Serialized Data ==> "+fd);
	$.ajax({  	
		url:jsonUpdateUrl,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		dataType: "json",
		success : function(result){
			if(result.status=="Success")
				{
					console.log("save2");
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(result.message+"!!  Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}
				if(result.status!="Success")
				{
					console.log("save3");
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(result.message+"!! Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
			},
			error: function(response)
			{
				alert(JSON.parse(response.responseText));
		   	}
	});
			
}
*/

/* OLD CODE FOR SAVING DATA
var jsonUrl="/reimbursement/saveChildEducaticonReimbursement";


function loadSaveCEdCData() 
{
	alert("save");
	//debugger;
	var fd = $("#CER_SAVE").serialize();
	console.log("Serialized Data ==> "+fd);
	$.ajax({  	
		url:jsonUrl,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			if(result.status=="Success")
				{
					alert("save2");
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(result.message+"!! Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}
				else if(result.status!="Success")
				{
					alert("save3");
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(result.message+"!! Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

};*/
/* JS For Form Submit ends */




//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

function approvalSubmit(_status)
{
//alert("in");
	if($("#txtAmount").val()==''){
		$("#txtAmount").val("0");	
	}
	var formData = $('#CER_SAVE').serialize();

	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();
//debugger;
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
				url: "/reimbursement/childApproval/"+_status,
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
				url: "/reimbursement/childApproval/"+_status,
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
							message:"You have successfully Rejected.",
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

//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});
