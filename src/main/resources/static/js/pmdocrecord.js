//alert("Hii...I/'m DocRecord JS");

/*$('#PAY_CALENDAR').on('change', function(e){
    var paycal=$('#PAY_CALENDAR').children("option:selected").val();
    var url = "/payslip/"+paycal;
    $('#div_payslip').load(url);
});
*/


var myVar;

	function myFunction() {			
			document.getElementById("REPORTS_LOADER").style.display = "block";
  			myVar = setTimeout(showPage, 3000);
		}

	function showPage() {		
			  document.getElementById("REPORTS_LOADER").style.display = "none";			
}

$(document).ready(function(){	
	myFunction();
});



$('#PAY_CALENDAR').on('change', function(e){
	$('#busid').prop("disabled",false);
});
$('#busid').on('change', function(e){
	$('#paygrp').prop("disabled",false);
});

$('#SAL_CALENDAR').on('change', function(e){
	$('#salbusid').prop("disabled",false);
});
$('#salbusid').on('change', function(e){
	$('#salpaygrp').prop("disabled",false);
});

function loadSalaryycardd(){
	//debugger;
	$("#PAYSLIP_LOADERR").css("display","block");
	var salpaycal=$('#SAL_CALENDAR option:selected').val();
	var salbu=$('#salbusid option:selected').val();
	var salpygrp=$('#salpaygrp option:selected').val();
	var salurl = "/personedit/pmsalarycard/"+salbu+"/"+salpaycal+"/"+salpygrp;
	$('#div_salarycard').load(salurl);
	$('#downloadpdfsal').css('display', 'block');
	$("#PAYSLIP_LOADERR").css("display","none");
}


function loaddPayslipp(){
	//debugger;
	$("#PAYSLIP_LOADERR").css("display","block");
	var paycal=$('#PAY_CALENDAR option:selected').val();
	var bu=$('#busid option:selected').val();
	var pygrp=$('#paygrp option:selected').val();
	var url = "/personedit/payslip/"+bu+"/"+paycal+"/"+pygrp;
	$('#div_payslip').load(url);
	$('#downloadpdf').css('display', 'block');
	$("#PAYSLIP_LOADERR").css("display","none");
}

function payslipPdf() {
            html2canvas(document.getElementById('payslip'), {
                onrendered: function (canvas) {
                    var data = canvas.toDataURL();
                    var docDefinition = {
                        content: [{
                            image: data,
                            width: 500
                        }]
                    };
                    pdfMake.createPdf(docDefinition).download("Payslip.pdf");
                }
            });
        }




 function salarycardPdf() {
            html2canvas(document.getElementById('salcard'), {
                onrendered: function (canvas) {
                    var data = canvas.toDataURL();
                    var docDefinition = {
                        content: [{
                            image: data,
                            width: 500
                        }]
                    };
                    pdfMake.createPdf(docDefinition).download("Salarycard.pdf");
                }
            });
        }




$('#ADD_DOC_BTN').on('click', function(e){
    $('#ADD_DOC_BTN').css("display","none");
    $('#ADD_DOC_BLOCK').css("display","block");
});
$('#CANCEL_BTN').on('click', function(e){
    $('#ADD_DOC_BTN').css("display","block");
    $('#ADD_DOC_BLOCK').css("display","none");
});



function openTabs(evt,legal){
	//debugger;
    var i,x,tablinks;
    x=document.getElementsByClassName("legal");
    for(i=0;i<x.length;i++){
        x[i].style.display="none";
    }
    tablinks=document.getElementsByClassName("tablink");
    for(i=0;i<x.length;i++){
        tablinks[i].className=tablinks[i].className.replace("w3-theme","");
    }
    document.getElementById(legal).style.display="block";
    evt.currentTarget.className+=" w3-theme";

}
/*

function loadpayslip(){
	var url=
}
*/

//alert($("#PRSN_NBR").val());

function saveDocuForm()
{
    var personnumber=$("#PRSN_NBR").val();
    var doctype=$("#DOC_TYPE option:selected").text();
    personnumber=personnumber.trim();
    doctype=doctype.trim();

    var filepath="";
    filepath+=personnumber+"/"+doctype;
    //alert(filepath);
    $("#filepath").val("/EmployeeDocs/"+filepath);
    //alert("DOCRECORD"+$("#filepath").val());
    var form = $("#DOCUMENT")[0];

    var data = new FormData(form);

    $.ajax({
        url: "/personedit/pmsavedocument",
        type: "POST",
        enctype: "multipart/form-data",
        data: data,
        cache: false,
        contentType:false,
        processData: false,
        timeout:600000,
        success: function(data){

            $('#div_replace').html(data);

            if( res=="Success" )
            {
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message:"Document successfully uploaded."
                });
            }
            else if( res=="MISMATCH" )
            {
                //alert("MISMATCH");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:"Mismatch."
                });
            }
            else if( res=="EMPTY_FILE" )
            {
                //alert("File is empty");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:"File is empty."
                });
            }
            else if( res=="WRITE_ERROR" )
            {
                //alert("Error in writing file.");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:"Error in writing file."
                });
            }
            else if( res=="IOEXCEPTION" )
            {
                //alert("IO Exception has occurred.");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:"IO Exception has occurred."
                });
            }
            else if( res=="LOG_ERROR" )
            {
                //alert("Error while logging file info.");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:"Error while logging file info."
                });
            }
            else if( res=="ILLEGALARG" )
            {
                alert("Error while posting file log.");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:"Error while posting file log."
                });
            }
            /*else
            {
                alert("Something went wrong while uploading the file(s).");
            }*/
        },
        error: function(data){
            alert("ERROR : "+JSON.stringify(data));
        }


        /*,
        xhr: function(){
            var xhr = $.ajaxSettings.xhr();
            xhr.upload.onprogress = function(evt){

            var percent = Math.floor((evt.loaded / evt.total)*100);

            $("#progressPercent").text(percent+"%");
            $("#progressBarFill").css("width",percent+"%");
            };
            xhr.upload.onload = function(){
            $("#progressPercent").text("0%");
            $("#progressBarFill").css("width","0%");
            $("#addFileRowBtn").prop('disabled',false);
            $("#deleteFileRowBtn").prop('disabled',false);
            //obj=null;
            //modal.style.display = "none";
            };
            return xhr;
        }*/
    });
}
$(document).on('click').unbind();

function loadSalaryycardd(){
	debugger;
	$("#PAYSLIP_LOADERR").css("display","block");
	var salpaycalfrom=$('#SAL_CALENDAR_FROM option:selected').val();
	var salpaycalto=$('#SAL_CALENDAR_TO option:selected').val();
	//var salbu=$('#salbusid option:selected').val();
	//var salpygrp=$('#salpaygrp option:selected').val();
	var salurl = "/personedit/pmsalarycard/"+salpaycalfrom+"/"+salpaycalto;
	
	var ab = window.open(salurl);
    setTimeout(function () {
        ab.close();
    }, 720000);
	//$('#div_salarycard').load(salurl);
//	$('#downloadpdfsal').css('display', 'block');
	$("#PAYSLIP_LOADERR").css("display","none");
}


function downloadpdf()
{
	
	debugger;	
	var calcode = $("#PAY_CALENDAR").children("option:selected").text();
	var persono = $("#PRSN_NBR").val();
	//var downloadurl = "/personedit/download/";
	// var filelocation = "/ReportSalrySlip/EmployeeDocs/LMRC_Payslips/";
	//var filelocation = "var/lib/unifui/ReportSalrySlip/EmployeeDocs/LMRC_Payslips/";
	//var filelocation = "var/lib/ReportSalrySlip/EmployeeDocs/LMRC_Payslips/";
	var filelocation = "/EmployeePayslipsUI/";
	var filepath = '/getContent?location='+filelocation+persono+"_"+calcode.substring(4)+".pdf";
	
	//$("#DOWNLOAD_LINKPY").attr("href","/getContent?location="+filepath);
	
	$("#DOWNLOAD_LINKPY").attr("href",filepath);
	
}
