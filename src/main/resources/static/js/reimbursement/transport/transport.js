var maxfy=getMaxfy();
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
function getMaxfy(){
  var today = new Date();
  if ((today.getMonth() + 1) <= 3) {
    var maxfy=today.getFullYear()+"-03-31";
  } else {
    var maxfy=(today.getFullYear() + 1)+"-03-31";
  }
  return maxfy;
}

function setdaterange(e){
	$(e).attr("max",maxfy);
	$(e).attr("min",minfy);
}
function setdate(e){
	
}
/************** SNIGDHAA 11-NOV-2020 *******************/
function addNewDestinationVisit() {
    var newRow = '<div id="childrow" class="w3-row w3-container">' +
        '<div class="w3-col m10">' +
        '<div class="w3-quarter">' +
        '<div class="w3-container">' +
        '<p>' +
        '<label>S.No:</label> <span style="color: red"> *</span> <input id="Sno" class="w3-input w3-border" type="text">' +
        '</p>' +
        '</div>' +
        '</div>' +
        '<div class="w3-quarter" >' +
        '<div class="w3-container">' +
        '<p>' +
        '<label>From Date:</label> <span style="color: red"> *</span> <input id="fromdate" onclick="setdaterange(this)" onchange="setdate(this)" name="fromdate" class="w3-input w3-border fromdate" type="date" data-date-format="YYYY MM DD">' +
        '</p>' +
        '</div>' +
        '</div>' +
        '<div class="w3-quarter" >' +
        '<div class="w3-container">' +
        '<p>' +
        '<label>To Date:</label> <span style="color: red"> *</span> <input id="todate" onclick="setdaterange(this)" onchange="setdate(this)" name="todate" class="w3-input w3-border" type="date" data-date-format="YYYY MM DD">' +
        '</p>' +
        '</div>' +
        '</div>' +
        '<div class="w3-quarter" >' +
        '<div class="w3-container">' +
        '<p>' +
        '<label>Place:</label> <span style="color: red">*</span><input id="placeVisit" name="visitedplace" class="w3-input w3-border" type="text">' +
        '</p>' +
        '</div>' +
        '</div>' +
        '</div>' +

        '<div class="w3-col m2" style="margin-top: 21px;">' +
        '<div class="w3-row w3-center">' +
        '<button onClick="addNewDestinationVisit()" class="w3-button w3-theme w3-center" type="button">' +
        '<i class="fas fa-plus"></i>' +
        '</button>' +
        '&nbsp' +
        '<button id="del" onClick="deleteCurrentRow(this)" class="w3-button w3-theme" type="button">' +
        '<i class="fa fa-trash"></i>' +
        '</button>' +
        '</div>' +
        '</div>' +
        '</div>';

    $('#mainRow').append(newRow);
    $.each($('#addDestinationVisit #childrow'), function(index) {
        //debugger;

		$(this).find('#delete').attr('name','del'+ (index));
		//$(this).find('#del').attr('id','del'+ (index));
        $(this).find('#Sno').attr('value', index + 1);
        $(this).find('#fromdate').attr('name', 'histories[' + (index) + '].fromdate');
        $(this).find('#todate').attr('name', 'histories[' + (index) + '].todate');
        $(this).find('#placeVisit').attr('name', 'histories[' + (index) + '].visitedplace');
    });

};

function deleteCurrentRow(e) {
	//debugger;
	alert("delete button id ==> "+e);
    $(e).closest("#childrow").remove();
    $.each($('#addDestinationVisit #childrow'), function(index) {
        $(this).find('#delete').attr('name','del'+ (index));
		//$(this).find('#del').attr('id','del'+ (index));
        $(this).find('#Sno').attr('value', index + 1);
        $(this).find('#fromdate').attr('name', 'histories[' + (index) + '].fromdate');
        $(this).find('#todate').attr('name', 'histories[' + (index) + '].todate');
        $(this).find('#placeVisit').attr('name', 'histories[' + (index) + '].placeVisit');
    });


};

/* code for deleting travel history from model starts */ 

$(document).on("click","#delete",function(){
	//debugger;
	var dex=$(this).attr('index');
	$(this).closest("#childrow").remove();
	removeHistory(dex);
	
	$.each($('#addDestinationVisit #childrow'), function(index) {
        $(this).find('#delete').attr('name','delete'+ (index-1));
		//$(this).find('#del').attr('id','del'+ (index-1));
        $(this).find('#Sno').attr('value', index + 1);
        $(this).find('#fromdate').attr('name', 'histories[' + (index) + '].fromdate');
        $(this).find('#todate').attr('name', 'histories[' + (index) + '].todate');
        $(this).find('#placeVisit').attr('name', 'histories[' + (index) + '].placeVisit');
    });
	
	
});


function removeHistory(index) {
	//debugger;
	var jurl="../reimbursement/transporthistory/removehistory/"+index;
	$.ajax({
		type: 'GET',
		url: jurl,
		success: function(data){
			console.log("DATA AFTER DELETING TRAVEL HISTORY ======> "+data);
		},
		error: function(e){
			//alert(JSON.stringify(e));
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

/* code for deleting travel history from model starts */



function ajaxPost(_status) {
    //alert("hello May i come in");
    //debugger;
    savedata(_status);

};

function savedata(_status) {
    //debugger;
    $("#statusid").val(_status);
    var totalFuelExpence = parseInt($('#totalFuelExpence').val());
    var totalOfChauffeur = parseInt($('#salaryofchauffeur').val());
    var CarInsurance = parseInt($('#carinsurance').val());
    var repairMaintain = parseInt($('#repairandmaintain').val());
    var totalTax = parseInt($('#tolltax').val());
    var otherExpences = parseInt($('#otherexpences').val());
    var CheckBox = $('#Agree').val();


    var personnumber = $("#personnumber").val();
    //$("#statusid").val(str);
    //	alert($("#statusid").val());
    var doctype = "TransportReimbursement";
    personnumber = personnumber.trim();
    doctype = doctype.trim();

    var filepath = "";
    filepath += personnumber + "/" + doctype;
    //alert(filepath);
	//alert("FILEPATH :: "+filepath);
    $("#attachments").val("/EmployeeDocs/" + filepath);
	//alert("ATTACHMENTS :: "+ $("#attachments").val());
    //alert("FILEPATH::"+$('#attachments').val());



    if ($('#Agree').prop("checked") == true) {
	//debugger;
        if (totalFuelExpence == "" || isNaN(totalFuelExpence)) {
            totalFuelExpence = 0;
			$('#totalFuelExpence').val(0);
        }
        if (totalOfChauffeur == "" || isNaN(totalOfChauffeur)) {
            totalOfChauffeur = 0;
			$('#salaryofchauffeur').val(0);
        }
        if (CarInsurance == "" || isNaN(CarInsurance)) {
            CarInsurance = 0;
			$('#carinsurance').val(0);
        }
        if (totalTax == "" || isNaN(totalTax)) {
            totalTax = 0;
			$('#tolltax').val(0);
        }
        if (otherExpences == "" || isNaN(otherExpences)) {
            otherExpences = 0;
			$('#otherexpences').val(0);
        }
		if (repairMaintain == "" || isNaN(repairMaintain)) {
            repairMaintain = 0;
			$('#repairandmaintain').val(0);
        }
        var total = parseInt(totalFuelExpence) + parseInt(totalOfChauffeur) + parseInt(CarInsurance) + parseInt(repairMaintain) + parseInt(totalTax) + parseInt(otherExpences);
        var totalamount = parseInt($('#totalAmount').val());
        console.log(total);
        console.log(totalamount);

        if (totalamount == total) {

            var form = $("#TRANSPORT_EMBRSMNT_FORM")[0];
            var data = new FormData(form);
            $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
            $.ajax({
                url: "reimbursement/saveTransportClaim",
                type: "POST",
                enctype: "multipart/form-data",
                data: data,
                cache: false,
                contentType: false,
                processData: false,
                timeout: 600000,
                success: function(data) {
                    //alert(data);	
                    $('#replace_div').html(data);

                    if (result == "Success" && _status == "save") {
                        bootbox.alert({
                            size: 'medium',
                            title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                            message: "You have successfuly saved Transport Reimbursement.",
                            callback: function() {
                                $('#replace_div').load("/reimbursement/transportManager");
                            }
                        });
                    }
                    /*else if( result=="MISMATCH" )
                    {
                    	bootbox.alert("MISMATCH");
                    }
                    else if( result=="EMPTY_FILE" )
                    {
                    	bootbox.alert("File is empty");
                    }
                    else if( result=="WRITE_ERROR" )
                    {
                    	bootbox.alert("Error in writing file.");
                    }
                    else if( result=="IOEXCEPTION" )
                    {
                    	bootbox.alert("IO Exception has occurred.");
                    }
                    else if( result=="LOG_ERROR" )
                    {
                    	bootbox.alert("Error while logging file info.");
                    }
                    else if( result=="ILLEGALARG" )
                    {
                    	bootbox.alert("Error while posting file log.");
                    }
                    */
                     else if (result == "DATEERROR") {
                        bootbox.alert({
                            size: 'medium',
                            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                            message: "From Date is greater than To Date. <br> Please re-check your dates and attach the file again, if required"
                        });
                    }
                    else if (result == "alreadyApplied") {
                        bootbox.alert({
                            size: 'medium',
                            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                            message: "Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required"
                        });
                    } else {
                        bootbox.alert({
                            size: 'medium',
                            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                            message: "Fill all the mandatory fields and attach the file again, if required."
                        });
                    }

                    console.log("successss: " + result);
                },
                error: function(e) {
                    bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Unable to save data!!<br/> Please try again after sometime"				
				});
                    console.log("ERROR : " + JSON.stringify(e));
                }
            });
        } else {
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Amount " + totalamount + " should be equal to Fuel Expense and Other Expenses.<br>Please Enter The Correct Amount."				
				});
        }

    } else {
        bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"You can not save data without declaration!!"				
				});
    }

};




$('.fromdate').on('change', function(e) {
    var from = $('#fromdate').val();
    $('#todate').attr("min", from);

});


/*$("input[name=histories[0].fromdate]").each(function(e) {
                //total += $(this).val() * $("input[name=amount2]:eq(" + idx + ")").val();
                 var from=$('#fromdate').val();
                 $("input[name=histories[0].todate]").attr("min",from);

});*/


function updateDataFunc(_status) {
	//debugger;
	var totalFuelExpence = parseInt($('#totalFuelExpence').val());
    var totalOfChauffeur = parseInt($('#salaryofchauffeur').val());
    var CarInsurance = parseInt($('#carinsurance').val());
    var repairMaintain = parseInt($('#repairandmaintain').val());
    var totalTax = parseInt($('#tolltax').val());
    var otherExpences = parseInt($('#otherexpences').val());
    var CheckBox = $('#Agree').val();
    console.log("hit on update");
	var updateurl = "/reimbursement/transportUpdate"
   	console.log("URL ====>  "+updateurl);
	var personnumber = $("#personnumber").val();
 	$("#statusid").val(_status);
    //$("#statusid").val(str);
    console.log($("#statusid").val());
    var doctype = "TransportReimbursement";
	personnumber=personnumber.trim();
	doctype=doctype.trim();
    var filepath = "";
    filepath += personnumber + "/" + doctype;
    console.log(filepath);
    $("#attachments").val("/EmployeeDocs/" + filepath);

    console.log("FILEPATH::"+$('#attachments').val());

    if ($('#Agree').prop("checked") == true) {
        if (totalFuelExpence == "" || isNaN(totalFuelExpence)) {
            totalFuelExpence = 0;
			$('#totalFuelExpence').val(0);
        }
        if (totalOfChauffeur == "" || isNaN(totalOfChauffeur)) {
            totalOfChauffeur = 0;
			$('#salaryofchauffeur').val(0);
        }
        if (CarInsurance == "" || isNaN(CarInsurance)) {
            CarInsurance = 0;
			$('#carinsurance').val(0);
        }
        if (totalTax == "" || isNaN(totalTax)) {
            totalTax = 0;
			$('#tolltax').val(0);
        }
        if (otherExpences == "" || isNaN(otherExpences)) {
            otherExpences = 0;
			$('#otherexpences').val(0);
        }
		if (repairMaintain == "" || isNaN(repairMaintain)) {
            repairMaintain = 0;
			$('#repairandmaintain').val(0);
        }
        var total = parseInt(totalFuelExpence) + parseInt(totalOfChauffeur) + parseInt(CarInsurance) + parseInt(repairMaintain) + parseInt(totalTax) + parseInt(otherExpences);
        var totalamount = parseInt($('#totalAmount').val());
        console.log(total);
        console.log(totalamount);

        if (totalamount == total) {



    var formData = $('#TRANSPORT_EMBRSMNT_EDIT_FORM')[0];
    var frdata = new FormData(formData);
	console.log("UPDATE DATA =====> "+frdata);
            $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
            url: updateurl,
            type: "POST",
            enctype: "multipart/form-data",
            data: frdata,
            cache: false,
            contentType: false,
            processData: false,
            timeout: 600000,
            success: function(data) {
                console.log("SUCCESS DATA ============> "+data);	
                $('#replace_div').html(data);
				console.log("SUCCESS RESULT ======> "+result);
				console.log("STATUS ======> "+_status);
                if (result == "Success" && _status == "save") {
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message: "You have successfully updated Transport Reimbursement.",
                        callback: function() {
                            $('#replace_div').load("/reimbursement/transportManager");
                        }
                    });

                } else if (result == "Success" && _status == "submit") {
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Submit</i>',
                        message: "You have successfully submitted Transport Reimbursement for approval.",
                        callback: function() {
                            $('#replace_div').load("/reimbursement/transportManager");
                        }
                    });
                }                
                else if (result == "alreadyApplied") {
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message: "Claim for the specified period has already been raised. <br> Please re-check your dates and attach the file again, if required"
                    });
                } else {
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message: "Fill all the mandatory fields and attach the file again, if required."
                    });
                }             
        },
        error: function(e) {
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Unable to save data!!<br/> Please try again after sometime"
            });
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
} else {
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Amount " + totalamount + " should be equal to Fuel Expense and Other Expenses.<br>Please Enter The Correct Amount."				
				});
        }
}else {
        bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"You can not save data without declaration!!"				
				});
    }
	
}



//////////////***********Ravi updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

function approvalSubmit(_status) {

    var formData = $('#TRANSPORT_EMBRSMNT_FORM_VIEW').serialize();
    var amt = $("#txtAmount").val();
    var cmt = $("#txtComment").val();
	if(_status=="Approved")
	{
    if (amt == "") {
       bootbox.alert({
			size: 'medium',
			title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message:"Request cannot be Approved without filling Approve Amount."
		});
    }else{
    //console.log(formData);
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
        url: "/reimbursement/transportApproval/" + _status,
        type: "POST",
        cache: false,
        data: formData,
        processData: false,
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: function(result) {
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
        error: function(e) {
            //alert("Data already submitted for time period '"+periodfrom+"' - '"+periodto+"'");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}
}else if(_status=="Rejected")
	{
		if( cmt=="")
		{
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Request cannot be Rejected without filling Comments."			
			}); 
		}else{
            $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
			$.ajax({
        url: "/reimbursement/transportApproval/" + _status,
        type: "POST",
        cache: false,
        data: formData,
        processData: false,
        contentType: 'application/x-www-form-urlencoded',
        dataType: "json",
        success: function(result) {
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
        error: function(e) {
            //alert("Data already submitted for time period '"+periodfrom+"' - '"+periodto+"'");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}
}
}
//////////////***********END: Ravi  updated\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

$(document).ready(function() {
	//debugger;
	$("#DOWNLOAD_LINK").attr("href","/getContent?location="+$("#attachhidden").val());
	/*var _href=$("#DOWNLOAD_LINK").attr("href");
	if(_href!=undefined && _href!="" && _href!=null){
		_href=_href.replaceAll('/', "FORWARD_SLASH");
		_href=_href.replaceAll('\\','BACKWARD_SLASH');
		_href=_href.replaceAll('.','EXT_DOT');

		$("#DOWNLOAD_LINK").attr("href",$("#DOWNLOAD_LINK").attr("href"));
	}*/
	
	
    var dtToday = new Date();

    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if (month < 10)
        month = '0' + month.toString();
    if (day < 10)
        day = '0' + day.toString();

    var maxDate = year + '-' + month + '-' + day;
    $('#validity').attr('min', maxDate);

});

function backBtnFunc() {
    var url = "/reimbursement/transportManager";
    $('#replace_div').load(url);
}
/////*************************************loading declaration in tab Print Annexure Start by @Suraj*****************//

function popupDeclearation(travelid) {
    //debugger;
    window.open("../reimbursement/declarationTransport/" + travelid, 'window', 'width=600,height=600');
};