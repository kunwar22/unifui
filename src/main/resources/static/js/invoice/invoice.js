$("#startdt").click(function(){
	disablefuturedate();
});
$("#enddt").click(function(){
	disablefuturedate();
});

function disablefuturedate()
{
	//disabling future date selection
	////debugger;
	var dttoday = new Date();
	var month = dttoday.getMonth()+1;
	var day= dttoday.getDate();
	var year = dttoday.getFullYear();
	if(month<10)
		month='0'+month.toString();
	if(day<10)
		day='0'+day.toString();
	
	var maxdate = year+"-"+month+"-"+day;
	$('#startdt').attr('max',maxdate);
	$('#enddt').attr('max',maxdate);
}

function searchinvoice(stdt,edt,buid,reid)
{
	
	var stdtval = $("#"+stdt).val();
	var edtval = $("#"+edt).val();
	var buid = $("#"+buid).val();
	var remid = $("#"+reid).val();
	var invurl="";
	
	if(remid == null)
	{
		invurl="/get/getInvoices/"+stdtval+"/"+edtval+"/"+buid+"/0";
	}
	else
	{
		invurl="/get/getInvoices/"+stdtval+"/"+edtval+"/"+buid+"/"+remid;
	}	
		
	if(stdtval == "" && edtval =="" && buid == null)
	{
		$("#fd").css('display','block');
		$("#td").css('display','block');
		$("#bu").css('display','block');
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Fill all required fields then try again!!!"				
			});
	}
	else if(stdtval == "")
	{
		$("#fd").css('display','block');
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"First Select From Date!!!"				
			});
	}
	else if(edtval == "")
	{
		$("#td").css('display','block');
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"First Select To Date!!!"				
			});
	}
	else if(buid == null)
	{
		$("#bu").css('display','block');
		bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"First Select Business Unit!!!"				
			});
	}
	
	//console.log("URL for loading Invoices ==> "+invurl);
	
	$.ajax({
		
			type: 'GET',
			url: invurl,
			dataType: 'json',
			success: function(data)
			{			
				jsonData = data;
				
				$("#fd").css('display','none');
				$("#td").css('display','none');
				$("#bu").css('display','none');
				populateInvoiceDataTable(jsonData);
				/*$('#btnexport').css('display','inline-block');*/
				
			}
	});
}

function populateInvoiceDataTable(data)
{
	//debugger;
	//console.log(data);
	
	$("#invoice").DataTable().clear();
	var dataLength = data.length;
	if(dataLength == 0){
		$('#invsearch_result').css('display', 'none');
		$('#noinvData').css('display', 'block');
		$('#btnexport').css('display','none');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];			
			$("#invoice").dataTable().fnAddData([	
				dat.ebsinvoiceid,			
				dat.invoiceno,				
				dat.personnumber,
				dat.personname,
				dat.reimbursename,				
				dat.venderid,								
				dat.siteid,				
				dat.approvedamt,	
				dat.businessunitname,	
				dat.status,
				dat.processdate,	
				]);
		}
		$('#btnexportxl').css('display','block');
		$('#btnexportpdf').css('display','block');
		$('#invsearch_result').css('display', 'block');
		$('#noinvData').css('display', 'none');
	}
}

/* code for exporting to excel starts */


function exportTableToExcel(tableID, filename = ''){
	$("#"+tableID).table2excel({exclude:".noExl", filename:filename, fileext:".xls"});
}

/*
function exportTableToExcel(tableID, filename = ''){
	debugger;
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    // Specify file name
    filename = filename?filename+'.xls':'excel_data.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
}
*/
/* code for exporting to excel ends */



/* Code for Exporting Data in PDF STARTS */ 

function exportToPdf()
{
	var stdtval = $("#startdt").val();
	var edtval = $("#enddt").val();
	var buid = $("#busid").val();
	var remid = $("#remid").val();
	
	var pdfurl = "";
	
	if(remid == null)
	{
		pdfurl="/get/pdfreport/"+stdtval+"/"+edtval+"/"+buid+"/0";
	}
	else
	{
		pdfurl="/get/getInvoices/"+stdtval+"/"+edtval+"/"+buid+"/"+remid;
	}
	
	
	
	window.open(pdfurl, 'window', 'width=800,height=800');
}

/* Code for Exporting Data in PDF ENDS */