//alert("Hii...I/'m DocRecord JS");


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
	
function loadPayslip(){
	////debugger;
	$("#PAYSLIP_LOADER").css("display","block");
	var paycal=$('#PAY_CALENDAR option:selected').val();
	var bu=$('#busid option:selected').val();
	var pygrp=$('#paygrp option:selected').val();
	var url = "/payslip/"+bu+"/"+paycal+"/"+pygrp;
	$('#div_payslip').load(url);
	$('#downloadpdf').css('display', 'block');
	$("#PAYSLIP_LOADER").css("display","none");
}

function loadSalarycard(){
	//debugger;
		$("#PAYSLIP_LOADER").css("display","block");
	var salpaycalfrom=$('#SAL_CALENDAR_FROM option:selected').val();
	var salpaycalto=$('#SAL_CALENDAR_TO option:selected').val();
	//var salbu=$('#salbusid option:selected').val();
	//var salpygrp=$('#salpaygrp option:selected').val();
	var salurl = "/salarycard/"+salpaycalfrom+"/"+salpaycalto;
	
	var ab = window.open(salurl);
    setTimeout(function () {
        ab.close();
    }, 720000);
	//$('#div_salarycard').load(salurl);
//	$('#downloadpdfsal').css('display', 'block');
	$("#PAYSLIP_LOADER").css("display","none");
}

/*
function PayslipPdf() {
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
        }*/

function PayslipPdf() {
	//debugger;
	var quotes = document.getElementById('payslip');
	html2canvas(quotes)
		.then((canvas) =>
		{
			//! MAKE YOUR PDF
			var pdf = new jsPDF('p', 'pt', 'letter');

			for (var i = 0; i <= quotes.clientHeight/980; i++)
			{
				//! This is all just html2canvas stuff
				var srcImg  = canvas;
				var sX      = 0;
				var sY      = 1150*i; // start 980 pixels down for every new page
				var sWidth  = 920;//900
				var sHeight = 1150;
				var dX      = 0;
				var dY      = 0;
				var dWidth  = 920;
				var dHeight = 1150;

				window.onePageCanvas = document.createElement("canvas");
				onePageCanvas.setAttribute('width', 920);
				onePageCanvas.setAttribute('height', 1150);
				var ctx = onePageCanvas.getContext('2d');
				// details on this usage of this function:
				// https://developer.mozilla.org/en-US/docs/Web/API/Canvas_API/Tutorial/Using_images#Slicing
				ctx.drawImage(srcImg,sX,sY,sWidth,sHeight,dX,dY,dWidth,dHeight);

				// document.body.appendChild(canvas);
				var canvasDataURL = onePageCanvas.toDataURL("image/png", 1.0);

				var width         = onePageCanvas.width;
				var height        = onePageCanvas.clientHeight;

				//! If we're on anything other than the first page,
				// add another page
				if (i > 0)
				{
					pdf.addPage(612, 791); //8.5" x 11" in pts (in*72)
				}
				//! now we declare that we're working on that page
				pdf.setPage(i+1);
				//! now we add content to that page!
				pdf.addImage(canvasDataURL, 'PNG', 20, 40, (width*.62), (height*.62));

			}
			//! after the for loop is finished running, we save the pdf.
			pdf.save('Payslip.pdf');
		});
}

 function SalarycardPdf() {
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



function openTab(evt,legal){
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
	url: "/user/savedocument",
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
	
	$('#replace_div').html(data);
	
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


//"application/x-www-form-urlencoded"
function saveDocsForm()
{
	var fd = new FormData();
	fd.append('file', file);
	
	$.ajax({
		url: "/user/savedocument",
	    type: "POST",
		enctype: 'multipart/form-data',
	    data: fd,
	    cache: false,
        contentType:false,
        processData: false,
		success : function(result){
			
					alert(result);
			createdPlanId = result.match(/(\d+)/)[0];
			alert(createdPlanId);
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
				/*$('#replace_div').html(result);
						if(message!=null){
						alert(message);
						}*/
			//alert(result);
			//$('#replace_div').html(result);
			//createdPlanId = result.match(/(\d+)/)[0];
			//alert(createdPlanId);
			
			});
			
	
};



function downloadpdf()
{
	
	//debugger;	
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
function downloadform16pdf()
{	
	//debugger;	
	var panno = $("#panno").html();
	var finyear = $("#finyear").val();	
	
	var styear = parseInt(finyear.substring(0,5));
	styear++;
		
	var edyear = parseInt(finyear.substring(5));
	edyear++;
	
	var sy = styear.toString();
	var ey = edyear.toString();
	var finyr = (sy.concat("-").concat(ey.substring(2,4)));
	
	var part = $("#formtype").val();
	var filelocation = "";
	var filepath ="";
	if(part == "Part A")
	{
		filelocation = "/form16a/";
		filepath = '/getContent?location='+filelocation+panno+"_"+finyr+".pdf";
	}
	else if(part == "Part B")
	{
		filelocation = "/form16b/";
		filepath = '/getContent?location='+filelocation+panno+"_"+"PARTB_"+finyr+".pdf";
	}
	
	$("#DOWNLOAD_LINKFM16").attr("href",filepath);
	
}
