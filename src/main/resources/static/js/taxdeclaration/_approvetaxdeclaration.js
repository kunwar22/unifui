/**
 * 
 */
//alert("Houserent");


function openTab(legal){
	//debugger;
	var i,x,tablinks;
	x=document.getElementsByClassName("legal");
	for(i=0;i<x.length;i++){
		x[i].style.display="none";
	}
	tablinks=document.getElementsByClassName("tablink");
	for(i=0;i<tablinks.length;i++){
		tablinks[i].className=tablinks[i].className.replace("w3-light-grey","");
	}
	document.getElementById(legal).style.display="block";
	$('button[name =\"'+legal+'\"]').addClass("w3-light-grey");
	//evt.currentTarget.className+=" w3-theme";
	
}

function addrowOwner() {
	//debugger;
	var data = "";
	data = '<tr>'+
'<td style="width: 15%">'+
'<input type="hidden" name="owner[0].ownerId"  value="0"/>'+
'<input name="owner[0].fromdate" class="w3-input w3-border" id="own_fromdate0" type="date" data-date="" data-date-format="YYYY MM DD"/>'+
'</td>'+
'<td style="width: 15%">'+
'<input name="owner[0].todate" class="w3-input w3-border" id="own_todate0" type="date" data-date="" data-date-format="YYYY MM DD" />'+
'</td>'+
'<td style="width: 15%">'+
'<input class="w3-input w3-border" name="owner[0].name" id="own_name0" type="text">'+
'</td>'+
'<td style="width: 15%">'+
'<input  class="w3-input w3-border" name="owner[0].address" id="own_address0" type="text">'+
'</td>'+
'<td style="width: 15%">'+
'<input  class="w3-input w3-border" name="owner[0].pan" id="own_pan0" type="text">'+
'</td>'+
'<td style="width: 15%">'+
'<input  class="ownerfiles" name="ownerfiles" id="own_attachment0" type="file">'+
'</td>'+
'<td style="width:10%;">'+
'<div id="own_btndel0" class="w3-text-black" style="text-align: right;"  onclick="deleteOwnerRow(this)">'+
'<i class="far fa-times-circle" style="font-size:25px; cursor:pointer;" ></i></div>'+
'</td>'+
'</tr>';

	$("#OWNER_INFO_TBL tbody").append(data);
	//window.globalCounter++;

	$.each($('#OWNER_INFO_TBL tr'), function(index, val) {
		//debugger; 
		$(this).find("td:eq(0) input[type='date']").attr('id', 'own_fromdate' + (index - 1));
		$(this).find("td:eq(0) input[type='date']").attr('name', 'owner[' + (index - 1) + '].fromdate');
		$(this).find("td:eq(0) input[type='hidden']").attr('name', 'owner[' + (index - 1) + '].ownerId');
		
		$(this).find("td:eq(1) input[type='date']").attr('id', 'own_todate' + (index - 1));
		$(this).find("td:eq(1) input[type='date']").attr('name', 'owner[' + (index - 1) + '].todate');
		
		$(this).find("td:eq(2) input[type='text']").attr('id', 'own_name' + (index - 1));
		$(this).find("td:eq(2) input[type='text']").attr('name', 'owner[' + (index - 1) + '].name');
		
		$(this).find("td:eq(3) input[type='text']").attr('id', 'own_address' + (index - 1));
		$(this).find("td:eq(3) input[type='text']").attr('name', 'owner[' + (index - 1) + '].address');
		
		$(this).find("td:eq(4) input[type='text']").attr('id', 'own_pan' + (index - 1));
		$(this).find("td:eq(4) input[type='text']").attr('name', 'owner[' + (index - 1) + '].pan');
		
		$(this).find("td:eq(5) input[type='file']").attr('id', 'own_attachment' + (index - 1));
		//$(this).find("td:eq(5) input[type='file']").attr('name', 'owner[' + (index - 1) + '].ownfile');
		
		$(this).find("td:eq(6) div").attr('id', 'own_btndel' + (index - 1));
		
	});

}


function deleteOwnerRow(e) {
	
	$(e).parent().parent().remove();

	$.each($('#OWNER_INFO_TBL tr'), function(index, val) {
		//debugger; 
		$(this).find("td:eq(0) input[type='date']").attr('id', 'own_fromdate' + (index - 1));
		$(this).find("td:eq(0) input[type='date']").attr('name', 'owner[' + (index - 1) + '].fromdate');
		$(this).find("td:eq(0) input[type='hidden']").attr('name', 'owner[' + (index - 1) + '].ownerId');
		
		$(this).find("td:eq(1) input[type='date']").attr('id', 'own_todate' + (index - 1));
		$(this).find("td:eq(1) input[type='date']").attr('name', 'owner[' + (index - 1) + '].todate');
		
		$(this).find("td:eq(2) input[type='text']").attr('id', 'own_name' + (index - 1));
		$(this).find("td:eq(2) input[type='text']").attr('name', 'owner[' + (index - 1) + '].name');
		
		$(this).find("td:eq(3) input[type='text']").attr('id', 'own_address' + (index - 1));
		$(this).find("td:eq(3) input[type='text']").attr('name', 'owner[' + (index - 1) + '].address');
		
		$(this).find("td:eq(4) input[type='text']").attr('id', 'own_pan' + (index - 1));
		$(this).find("td:eq(4) input[type='text']").attr('name', 'owner[' + (index - 1) + '].pan');
		
		$(this).find("td:eq(5) input[type='file']").attr('id', 'own_attachment' + (index - 1));
		$(this).find("td:eq(5) input[type='file']").attr('name', 'owner[' + (index - 1) + '].ownfile');
		
		$(this).find("td:eq(6) div").attr('id', 'own_btndel' + (index - 1));
		
	});
	
}


function saveHRA()
{
	var form = $("#HOUSE_RENT_FORM")[0];
	var data = new FormData(form);
	
	$.ajax({
	url: "/approvetaxdeclaration/apprhraSave",
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
	$('#replace_div').html(data);	
		if(resultfinal=="Success" || resultfinal=="success" || resultfinal=="saved"){
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:'Successfully saved.'//,
					/*callback:function(){
					$('#replace_div').load("/reimbursement/telephoneSearch");
					}*/
				});
		}
		else if( resultfinal=="MISMATCH" )
		{
			bootbox.alert("MISMATCH");
		}
		else if( resultfinal=="EMPTY_FILE" )
		{
			bootbox.alert("File is empty");
		}
		else if( resultfinal=="WRITE_ERROR" )
		{
			bootbox.alert("Error in writing file.");
		}
		else if( resultfinal=="IOEXCEPTION" )
		{
			bootbox.alert("IO Exception has occurred.");
		}
		else if( resultfinal=="LOG_ERROR" )
		{
			bootbox.alert("Error while logging file info.");
		}
		else if( resultfinal=="ILLEGALARG" )
		{
			bootbox.alert("Error while posting file log.");
		}
		}
	});
}

function savePREV(){
	var form = $("#PREV_INCOME_FORM")[0];
	var data = new FormData(form);
	
	$.ajax({
	url: "/approvetaxdeclaration/apprprevSave",
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
	$('#replace_div').html(data);	
		if(resultfinal=="Success" || resultfinal=="success" || resultfinal=="saved"){
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:'Successfully saved.'//,
					/*callback:function(){
					$('#replace_div').load("/reimbursement/telephoneSearch");
					}*/
				});
		}
		else if( resultfinal=="MISMATCH" )
		{
			bootbox.alert("MISMATCH");
		}
		else if( resultfinal=="EMPTY_FILE" )
		{
			bootbox.alert("File is empty");
		}
		else if( resultfinal=="WRITE_ERROR" )
		{
			bootbox.alert("Error in writing file.");
		}
		else if( resultfinal=="IOEXCEPTION" )
		{
			bootbox.alert("IO Exception has occurred.");
		}
		else if( resultfinal=="LOG_ERROR" )
		{
			bootbox.alert("Error while logging file info.");
		}
		else if( resultfinal=="ILLEGALARG" )
		{
			bootbox.alert("Error while posting file log.");
		}
		}
	});
}



function savePAYTAX(){
	var form = $("#HOME_LOAN_FORM")[0];
	var data = new FormData(form);
	
	$.ajax({
	url: "/approvetaxdeclaration/apprpaytaxSave",
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
	$('#replace_div').html(data);	
		if(resultfinal=="Success" || resultfinal=="success" || resultfinal=="saved"){
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:'Successfully saved.'//,
					/*callback:function(){
					$('#replace_div').load("/reimbursement/telephoneSearch");
					}*/
				});
		}
		else if( resultfinal=="MISMATCH" )
		{
			bootbox.alert("MISMATCH");
		}
		else if( resultfinal=="EMPTY_FILE" )
		{
			bootbox.alert("File is empty");
		}
		else if( resultfinal=="WRITE_ERROR" )
		{
			bootbox.alert("Error in writing file.");
		}
		else if( resultfinal=="IOEXCEPTION" )
		{
			bootbox.alert("IO Exception has occurred.");
		}
		else if( resultfinal=="LOG_ERROR" )
		{
			bootbox.alert("Error while logging file info.");
		}
		else if( resultfinal=="ILLEGALARG" )
		{
			bootbox.alert("Error while posting file log.");
		}
		}
	});
}


function saveChapterVIA(){
	var form = $("#CHAPTER_VIA_FORM")[0];
	var data = new FormData(form);

	$.ajax({
		url: "/approvetaxdeclaration/apprchapterVIASave",
		type: "POST",
		enctype: "multipart/form-data",
		data: data,
		cache: false,
		contentType:false,
		processData: false,
		timeout:600000,
		success: function(data){
			$('#replace_div').html(data);
			if(resultfinal=="Success" || resultfinal=="success" || resultfinal=="saved"){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:'Successfully saved.'//,
					/*callback:function(){
					$('#replace_div').load("/reimbursement/telephoneSearch");
					}*/
				});
			}
			else if( resultfinal=="MISMATCH" )
			{
				bootbox.alert("MISMATCH");
			}
			else if( resultfinal=="EMPTY_FILE" )
			{
				bootbox.alert("File is empty");
			}
			else if( resultfinal=="WRITE_ERROR" )
			{
				bootbox.alert("Error in writing file.");
			}
			else if( resultfinal=="IOEXCEPTION" )
			{
				bootbox.alert("IO Exception has occurred.");
			}
			else if( resultfinal=="LOG_ERROR" )
			{
				bootbox.alert("Error while logging file info.");
			}
			else if( resultfinal=="ILLEGALARG" )
			{
				bootbox.alert("Error while posting file log.");
			}
		}
	});
}