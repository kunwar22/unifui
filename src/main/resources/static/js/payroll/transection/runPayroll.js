//debugger;
var seq = 1;
colorButton(seq);
function nextClk(tagId){
    saveFormData(seq);
    seq++;
    $('#nextbackflag').val(1);
    if(seq == 7){
        seq--;
    }
    colorButton(seq);
}

function backClk(tagId){
    seq--;
    $('#nextbackflag').val(0);
    if(seq == 0){
        seq++;
    }
    fetchData(seq);
    colorButton(seq);
}

function saveFormData(seq){
    if(seq == 1){
        var form1data = $('#paygroupcalselect').serialize();
        $.ajax({
            url: "/payrollprocessing/selectPaygroup/paygroupcalendar",
            type: "POST",
            data: form1data,
            cache: false,
            contentType: "application/x-www-form-urlencoded",
            processData: false,
            success: function (result) {
            },
            error: function (response) {
                alert(JSON.parse(response.responseText));
            }

        });
    } else if(seq == 2){
        var identifiedEmployeedata = $('#identifiedEmployeeForm').serialize();
        $.ajax({
            url: "/payrollprocessing/identifyEmployee/postIdentifiedEmployee",
            type: "POST",
            data: identifiedEmployeedata,
            cache: false,
            contentType: "application/x-www-form-urlencoded",
            processData: false,
            success: function (result) {
            },
            error: function (response) {
                alert(JSON.parse(response.responseText));
            }
        });
    }
}

function fetchData(seq){
    if(seq == 1){
        $.ajax({
            type: 'GET',
            url: '/payrollprocessing/selectPaygroup/_paygroupcalendar',
            datasrc: '',
            dataType: 'JSON',
            success: function(result){
                $('#paygroupid').html(result.paygroupString);
                $('#calendarid').html(result.calendarString);
            },
            error: function(e){
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    } else if(seq == 2){
        var calid = $('#selectedcalendar').val();
        $("#PAYROLL_LOADER").css("display","block");
        $.ajax({
            type: 'GET',
            url: '/payrollprocessing/identifyEmployee/_getIdentifiedEmployee/' + calid,
            datasrc: '',
            dataType: 'JSON',
            success: function(result){
                identifydataa=result;
                populateIdentifyTable(identifydataa,calid,1);
                //$('#personDetails').html(result.message);
                //$('#emptbl').css('display', 'block');
            },
            error: function(e){
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    } else if(seq == 3){

    }
}



function loadPageComponentsNext(seq){
    if(seq == 1){
        $.ajax({
            type: 'GET',
            url: '/payrollprocessing/selectPaygroup/paygroupcalendar',
            datasrc: '',
            dataType: 'JSON',
            success: function(result){
                $('#paygroupid').html(result.paygroupString);
                $('#calendarid').html(result.calendarString);
            },
            error: function(e){
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    } else if(seq == 2){
        var payrollgroup = $('#selectedpaygroup').val();
        var calid = $('#selectedcalendar').val();
        $("#PAYROLL_LOADER").css("display","block");
        $.ajax({
            type: 'GET',
            url: '/payrollprocessing/identifyEmployee/getIdentifiedEmployee/' + payrollgroup +'/'+ calid,
            datasrc: '',
            dataType: 'JSON',
            success: function(result){
                identifydata=result;
                populateIdentifyTable(identifydata,calid,0);
                //$('#personDetails').html(result.message);
            },
            error: function(e){
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    } else if(seq == 3){
        var calid = $('#selectedcalendar').val();
        var nextbackflag = $('#nextbackflag').val();
        var url = "";
        if(nextbackflag == 1){
            url = '/payrollprocessing/getOTPElements/' + calid + '/1';
        } else {
            url = '/payrollprocessing/getOTPElements/' + calid + '/0';
        }

        $.ajax({
            type: 'GET',
            url: url,
            datasrc: '',
            dataType: 'JSON',
            success: function(result){
                $('#elementSelect').html(result.message);
            },
            error: function(e){
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }
}

//////////////ADDED BY UTSAV FOR DATATABLE POPULATE\\\\\\\\\\\\
function populateIdentifyTable(data,calidd,flg){
    //console.log(calidd);
    $("#PAYROLL_LOADER").css("display","none");

    $("#IDENTIFY_TBL").DataTable({
        "paging": false
    }).clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#emptbl').css('display', 'none');
        //$('#LOADER').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
		$(`#IDENTIFY_TBL tbody`).empty();
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            var chkbtn='';
            if(dat.status=="on" || flg==0){
                chkbtn='<input class="identifyCheck" onclick="handleClick(this)" type="checkbox" class="w3-check" name="identifiedEmployees[' + i + '].status" value="on" checked></td>';
            }else{
                chkbtn='<input class="identifyCheck" onclick="handleClick(this)" type="checkbox" class="w3-check" name="identifiedEmployees[' + i + '].status"></td>';
            }

            $("#IDENTIFY_TBL").dataTable().fnAddData([
                chkbtn,
                dat.personnumber+'<input type="hidden" name="identifiedEmployees[' + i + '].personnumber" value="'+dat.personnumber+'">',
                dat.personname+'<input type="hidden" name="identifiedEmployees[' + i + '].personname" value="'+dat.personname+'">',
                dat.hrstatus+'<input type="hidden" name="identifiedEmployees[' + i + '].hrstatus" value="'+dat.hrstatus+'">',
                dat.payrollstatus+'<input type="hidden" name="identifiedEmployees[' + i + '].payrollstatus" value="'+dat.payrollstatus+'">',
                dat.job+'<input type="hidden" name="identifiedEmployees[' + i + '].job" value="'+dat.job+'">',
                dat.position+'<input type="hidden" name="identifiedEmployees[' + i + '].position" value="'+dat.position+'">'+'<input type="hidden" name="identifiedEmployees[' + i + '].calendarid" value="' + calidd + '">'
            ]);
        }
        //$('#LOADER').css('display', 'none');
        $('#emptbl').css('display', 'block');
        $('#noData').css('display', 'none');
    }
}

function checkuncheckAll(){
    var inputs = $(".identifyCheck");
    /*var index=name.substring(16,17);
    var flg="natUncheck";
    removnatCheck(index,flg);*/
    //debugger;
    if($('#checkuncheckbtn').prop("checked")){
        for(i=0;i<inputs.length;i++){
            inputs[i].checked = true;
        }
    }else{
        for(i=0;i<inputs.length;i++){
            inputs[i].checked = false;
        }
    }
}


//////////////**************\\\\\\\\\\\\

function colorButton(seq){
    if(seq == 1){
        $('#btnWht1').css({'display':'none'});
        $('#btnGry1').css({'display':'none'});
        $('#btnGrn1').css({'display':'inline-block'});

        $('#btnGrn2').css({'display':'none'});
        $('#btnWht2').css({'display':'inline-block'});
        $('#btnWht3').css({'display':'inline-block'});
        $('#btnWht4').css({'display':'inline-block'});
        $('#btnWht5').css({'display':'inline-block'});
        $('#btnWht6').css({'display':'inline-block'});

        url = "/payrollprocessing/selectPaygroup";
        $('#runPayrollBody').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
        $("#runPayrollBody").load(url);
        loadPageComponentsNext(seq);

    } else if(seq == 2){
        $('#btnWht2').css({'display':'none'});
        $('#btnGry2').css({'display':'none'});
        $('#btnGrn2').css({'display':'inline-block'});
        $('#btnGry1').css({'display':'inline-block'});
        $('#btnGrn1').css({'display':'none'});

        $('#btnGrn3').css({'display':'none'});
        $('#btnWht3').css({'display':'inline-block'});
        $('#btnWht4').css({'display':'inline-block'});
        $('#btnWht5').css({'display':'inline-block'});
        $('#btnWht6').css({'display':'inline-block'});

        url = "/payrollprocessing/identifyEmployee";
        $('#runPayrollBody').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
        $("#runPayrollBody").load(url);
        //loadPageComponentsNext(seq);

    } else if(seq == 3){

        $('#btnWht3').css({'display':'none'});
        $('#btnGry3').css({'display':'none'});
        $('#btnGrn3').css({'display':'inline-block'});
        $('#btnGry2').css({'display':'inline-block'});
        $('#btnGrn2').css({'display':'none'});
        $('#btnGry1').css({'display':'inline-block'});

        $('#btnGrn4').css({'display':'none'});
        $('#btnWht4').css({'display':'inline-block'});
        $('#btnWht5').css({'display':'inline-block'});
        $('#btnWht6').css({'display':'inline-block'});

        url = "/payrollprocessing/manageOTP";
        $('#runPayrollBody').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
        $("#runPayrollBody").load(url);
        loadPageComponentsNext(seq);
		
		/**********Added by rajat on 03 july 2021*********** */
	       //document.getElementById('nx').style.pointerEvents = 'none';
          // $("#nx").removeClass("w3-blue");
          // $("#nx").addClass("w3-gray");
	       //$("#nx").css('background-color','#d5d2d2');
	      // document.getElementById('nx').style.pointerEvents = 'auto'; 
		/*******************end******************************* */

    } else if(seq == 4){
        $('#btnWht4').css({'display':'none'});
        $('#btnGry4').css({'display':'none'});
        $('#btnGrn4').css({'display':'inline-block'});
        $('#btnGrn3').css({'display':'none'});
        $('#btnGry3').css({'display':'inline-block'});
        $('#btnGry2').css({'display':'inline-block'});
        $('#btnGry1').css({'display':'inline-block'});

        $('#btnGrn5').css({'display':'none'});
        $('#btnWht5').css({'display':'inline-block'});
        $('#btnWht6').css({'display':'inline-block'});

        url = "/payrollprocessing/executePayroll";
        $('#runPayrollBody').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
        $("#runPayrollBody").load(url);

    } else if(seq == 5){
        $('#btnWht5').css({'display':'none'});
        $('#btnGry5').css({'display':'none'});
        $('#btnGrn5').css({'display':'inline-block'});
        $('#btnGry4').css({'display':'inline-block'});
        $('#btnGrn4').css({'display':'none'});
        $('#btnGry3').css({'display':'inline-block'});
        $('#btnGry2').css({'display':'inline-block'});
        $('#btnGry1').css({'display':'inline-block'});

        $('#btnGrn6').css({'display':'none'});
        $('#btnWht6').css({'display':'inline-block'});

        url = "/payrollprocessing/confirmPayroll";
        $('#runPayrollBody').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
        $("#runPayrollBody").load(url);

    } else if(seq == 6){
        $('#btnWht6').css({'display':'none'});
        $('#btnGry6').css({'display':'none'});
        $('#btnGrn6').css({'display':'inline-block'});
        $('#btnGry5').css({'display':'inline-block'});
        $('#btnGrn5').css({'display':'none'});
        $('#btnGry4').css({'display':'inline-block'});
        $('#btnGry3').css({'display':'inline-block'});
        $('#btnGry2').css({'display':'inline-block'});
        $('#btnGry1').css({'display':'inline-block'});

        url = "/payrollprocessing/finalizePayroll";
        $('#runPayrollBody').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
        $("#runPayrollBody").load(url);
    }
}

//////////////ADDED BY UTSAV FOR CALENDAR VALUES BASED ON PAYGROUP ID\\\\\\\\\\\\
function setpaygroupval() {
    $('#selectedpaygroup').val($('#paygroupid').children("option:selected").val());
    $('#selectedpaygroupcode').val($('#paygroupid').children("option:selected").text());
    var paygrpid =  $("#paygroupid option:selected").val();
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/selectPaygroup/paygroupcalendarbypaygroupid/'+paygrpid,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            $('#calendarid').html(result.calendarString);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });

}
//////////////****************************************************\\\\\\\\\\\\

function setcalendarval() {
    $('#selectedcalendar').val($('#calendarid').children("option:selected").val());
    $('#selectedcalendarcode').val($('#calendarid').children("option:selected").text());
}

function employeeSelectionBasis(){
    if($('#empSelectionBasis').children("option:selected").val() == "pg"){
        loadPageComponentsNext(seq);
        //$('#emptbl').css('display', 'block');
    }
}

function handleClick(obj){
    if(!obj.checked) {
        obj.value = "off";
    } else {
        obj.value = "on";
    }
}


/************added by rajat on 01-12-2020 start**************************/
$(document).ready(function(){
    checkLockStatus();
});
function checkLockStatus(){
   
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/getPayrollStatus' ,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            if (result.lockStatus == "true") {
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-exclamation-triangle" style="font-size:25px; color:red;">&nbsp;&nbsp;Info</i>',
                    message:'A calendar is already in processing state and has been locked, please unlock it or finalize it to process another calendar.',
                    callback:function(){
                        $('#replace_div').load("/finalizeunlockcalendar");
                    }
                });

            }
           else if (result.lockStatus == "false") {
                console.log("Run payroll...");
            }
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}


/*************added by rajat on 01-12-2020 end************************/

