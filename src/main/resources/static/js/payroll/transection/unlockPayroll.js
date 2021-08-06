var  calid = $('#calenderId').val();
var  calcode = $('#calendarcode').val();

$('#downloadLink').attr("href", "/payrollprocessing/confirmPayroll/downloadpayrollregister/"+calid+"/"+calcode);

$(document).ready(function(){
   //unlockPayroll()

    if(calid=="0"||calid==0||calid==null){
        document.getElementById('Finalizepayrollbtn').style.pointerEvents = 'none';
        $('#Finalizepayrollbtn').fadeTo('slow', .6);

        document.getElementById('Unlockpayrollbtn').style.pointerEvents = 'none';
        $('#Unlockpayrollbtn').fadeTo('slow', .6);
        $("#infomsg").show();
        $('#download').hide();

		 $('#LockedPayroll').prop('disabled', true);

    }else if(calid!=0||calid!="0"){
        $("#infomsg").hide();
		 $('#LockedPayroll').removeAttr('disabled');
    }


});
/*

function unlockPayroll(){
 
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/unlockfinalizecalendar',
        datasrc: '',
        dataType: 'JSON',
        success: function(result){

            if (result.lockStatus == "success") {

            }
        },
        error: function(e){
            console.log("There was an error with request...");
        }
    });
};

*/

function unlockPayrollcalender(){
	
var calidnew =$('#LockedPayroll').children("option:selected").val();
if(calidnew==""||calidnew==''||calidnew==null)
{
	bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Please Select Calender to unlock",
				/*callback:function(){
			    $('#replace_div').load("/finalizeunlockcalendar");
				}	*/
	});	
	return;	
}else{
$("#REPORTS_LOADER").css("display", "block");	
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/unlockPayrollCalender/' + calidnew,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){

            if (result.unlockPayrollCalender == "success") {
                document.getElementById('Unlockpayrollbtn').style.pointerEvents = 'none';
                $('#Unlockpayrollbtn').fadeTo('slow', .6);


                document.getElementById('Finalizepayrollbtn').style.pointerEvents = 'none';
                $('#Finalizepayrollbtn').fadeTo('slow', .6);
                console.log("Success unlock...");

				$('#LockedPayroll').prop('disabled', true);

            }
         $("#REPORTS_LOADER").css("display", "none");
        },
        error: function(e){
			$("#REPORTS_LOADER").css("display", "none");
            console.log("There was an error with request...");
        }
    });
}
};


function finalizedPayrollCalender(){

var calidnew =$('#LockedPayroll').children("option:selected").val();
if(calidnew==""||calidnew==''||calidnew==null)
{
	bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Please Select Calender to unlock",
				/*callback:function(){
			    $('#replace_div').load("/finalizeunlockcalendar");
				}	*/
	});	
	return;	
}else{
	$("#REPORTS_LOADER").css("display", "block");	
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/finalizePayrollCalender/' + calidnew,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){

            if (result.finalizePayrollCalender == "success") {
                document.getElementById('Unlockpayrollbtn').style.pointerEvents = 'none';
                $('#Unlockpayrollbtn').fadeTo('slow', .6);
                document.getElementById('Finalizepayrollbtn').style.pointerEvents = 'none';
                $('#Finalizepayrollbtn').fadeTo('slow', .6);
                console.log("Success unlock...");

				$('#LockedPayroll').prop('disabled', true);

            }
         $("#REPORTS_LOADER").css("display", "none");	
        },
        error: function(e){
	        $("#REPORTS_LOADER").css("display", "none");	
            console.log("There was an error with request...");
        }
    });
}
};