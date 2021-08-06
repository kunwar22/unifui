/**
 *
 */
//alert("Houserent");


$(document).ready(function() {
	var allenddateslength = document.getElementsByClassName("endlabel").length;
	showendDate(allenddateslength);
	var startlabellength = document.getElementsByClassName("startlabel").length;
	showstartDate(startlabellength);
});

function showendDate(lengthh){
	for(var z=0;z<lengthh;z++){
		var date = $("#endlabel"+z).text();
		var newdate = date.split("-").reverse().join("-");
		$("#endlabel"+z).text(newdate);
	}
}
function showstartDate(lengthhh){
	for(var x=0;x<lengthhh;x++){
		var date1 = $("#startlabel"+x).text();
		var newdate1 = date1.split("-").reverse().join("-");
		$("#startlabel"+x).text(newdate1);
	}
}


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


function saveHRA(x){
	 $(x).css("display", "none");
	var form = $("#HOUSE_RENT_FORM")[0];
	var data = new FormData(form);
 $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");

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

function savePREV(x){
	 $(x).css("display", "none");
	var form = $("#PREV_INCOME_FORM")[0];
	var data = new FormData(form);
$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");

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



function savePAYTAX(x){
	 $(x).css("display", "none");
	var form = $("#HOME_LOAN_FORM")[0];
	var data = new FormData(form);
$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");

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


function saveChapterVIA(x){
	 $(x).css("display", "none");
	var form = $("#CHAPTER_VIA_FORM")[0];
	var data = new FormData(form);
 $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");

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

$('[name="rent[0].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[0].v_amt"]').val().length == 0 || $('[name="rent[0].v_amt"]').val() == 0) {
		$('[name="rent[0].v_amt"]').val('0.0');
	}
	if($('[name="rent[0].d_amt"]').val() == '0.0' && $('[name="rent[0].v_amt"]').val() != '0.0'){
		$('#status0').text("Verified");
		$('#t_status0').val("Verified");
	}
	if($('[name="rent[0].d_amt"]').val() == '0.0' && $('[name="rent[0].v_amt"]').val() == '0.0'){
		$('#status0').text("");
		$('#t_status0').val("");
	}
	if($('[name="rent[0].d_amt"]').val() == '0.0' && $('[name="rent[0].v_amt"]').val() != '0.0'){
		$('#status0').text("Verified");
		$('#t_status0').val("Verified");
	}
	if($('[name="rent[0].d_amt"]').val() != '0.0' && $('[name="rent[0].v_amt"]').val() == '0.0'){
		$('#status0').text("Declared");
		$('#t_status0').val("Declared");
	}
	if($('[name="rent[0].d_amt"]').val() != '0.0' && $('[name="rent[0].v_amt"]').val() != '0.0'){
		$('#status0').text("Verified");
		$('#t_status0').val("Verified");
	}
});
$('[name="rent[1].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[1].v_amt"]').val().length == 0 || $('[name="rent[1].v_amt"]').val() == 0) {
		$('[name="rent[1].v_amt"]').val('0.0');
	}
	if($('[name="rent[1].d_amt"]').val() == '0.0' && $('[name="rent[1].v_amt"]').val() != '0.0'){
		$('#status1').text("Verified");
		$('#t_status1').val("Verified");
	}
	if($('[name="rent[1].d_amt"]').val() == '0.0' && $('[name="rent[1].v_amt"]').val() == '0.0'){
		$('#status1').text("");
		$('#t_status1').val("");
	}
	if($('[name="rent[1].d_amt"]').val() == '0.0' && $('[name="rent[1].v_amt"]').val() != '0.0'){
		$('#status1').text("Verified");
		$('#t_status1').val("Verified");
	}
	if($('[name="rent[1].d_amt"]').val() != '0.0' && $('[name="rent[1].v_amt"]').val() == '0.0'){
		$('#status1').text("Declared");
		$('#t_status1').val("Declared");
	}
	if($('[name="rent[1].d_amt"]').val() != '0.0' && $('[name="rent[1].v_amt"]').val() != '0.0'){
		$('#status1').text("Verified");
		$('#t_status1').val("Verified");
	}
});
$('[name="rent[2].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[2].v_amt"]').val().length == 0 || $('[name="rent[2].v_amt"]').val() == 0) {
		$('[name="rent[2].v_amt"]').val('0.0');
	}
	if($('[name="rent[2].d_amt"]').val() == '0.0' && $('[name="rent[2].v_amt"]').val() != '0.0'){
		$('#status2').text("Verified");
		$('#t_status2').val("Verified");
	}
	if($('[name="rent[2].d_amt"]').val() == '0.0' && $('[name="rent[2].v_amt"]').val() == '0.0'){
		$('#status2').text("");
		$('#t_status2').val("");
	}
	if($('[name="rent[2].d_amt"]').val() == '0.0' && $('[name="rent[2].v_amt"]').val() != '0.0'){
		$('#status2').text("Verified");
		$('#t_status2').val("Verified");
	}
	if($('[name="rent[2].d_amt"]').val() != '0.0' && $('[name="rent[2].v_amt"]').val() == '0.0'){
		$('#status2').text("Declared");
		$('#t_status2').val("Declared");
	}
	if($('[name="rent[2].d_amt"]').val() != '0.0' && $('[name="rent[2].v_amt"]').val() != '0.0'){
		$('#status2').text("Verified");
		$('#t_status2').val("Verified");
	}
});

$('[name="rent[3].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[3].v_amt"]').val().length == 0 || $('[name="rent[3].v_amt"]').val() == 0) {
		$('[name="rent[3].v_amt"]').val('0.0');
	}
	if($('[name="rent[3].d_amt"]').val() == '0.0' && $('[name="rent[3].v_amt"]').val() != '0.0'){
		$('#status3').text("Verified");
		$('#t_status3').val("Verified");
	}
	if($('[name="rent[3].d_amt"]').val() == '0.0' && $('[name="rent[3].v_amt"]').val() == '0.0'){
		$('#status3').text("");
		$('#t_status3').val("");
	}
	if($('[name="rent[3].d_amt"]').val() == '0.0' && $('[name="rent[3].v_amt"]').val() != '0.0'){
		$('#status3').text("Verified");
		$('#t_status3').val("Verified");
	}
	if($('[name="rent[3].d_amt"]').val() != '0.0' && $('[name="rent[3].v_amt"]').val() == '0.0'){
		$('#status3').text("Declared");
		$('#t_status3').val("Declared");
	}
	if($('[name="rent[3].d_amt"]').val() != '0.0' && $('[name="rent[3].v_amt"]').val() != '0.0'){
		$('#status3').text("Verified");
		$('#t_status3').val("Verified");
	}
});
$('[name="rent[4].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[4].v_amt"]').val().length == 0 || $('[name="rent[4].v_amt"]').val() == 0) {
		$('[name="rent[4].v_amt"]').val('0.0');
	}
	if($('[name="rent[4].d_amt"]').val() == '0.0' && $('[name="rent[4].v_amt"]').val() != '0.0'){
		$('#status4').text("Verified");
		$('#t_status4').val("Verified");
	}
	if($('[name="rent[4].d_amt"]').val() == '0.0' && $('[name="rent[4].v_amt"]').val() == '0.0'){
		$('#status4').text("");
		$('#t_status4').val("");
	}
	if($('[name="rent[4].d_amt"]').val() == '0.0' && $('[name="rent[4].v_amt"]').val() != '0.0'){
		$('#status4').text("Verified");
		$('#t_status4').val("Verified");
	}
	if($('[name="rent[4].d_amt"]').val() != '0.0' && $('[name="rent[4].v_amt"]').val() == '0.0'){
		$('#status4').text("Declared");
		$('#t_status4').val("Declared");
	}
	if($('[name="rent[4].d_amt"]').val() != '0.0' && $('[name="rent[4].v_amt"]').val() != '0.0'){
		$('#status4').text("Verified");
		$('#t_status4').val("Verified");
	}
});

$('[name="rent[5].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[5].v_amt"]').val().length == 0 || $('[name="rent[5].v_amt"]').val() == 0) {
		$('[name="rent[5].v_amt"]').val('0.0');
	}
	if($('[name="rent[5].d_amt"]').val() == '0.0' && $('[name="rent[5].v_amt"]').val() != '0.0'){
		$('#status5').text("Verified");
		$('#t_status5').val("Verified");
	}
	if($('[name="rent[5].d_amt"]').val() == '0.0' && $('[name="rent[5].v_amt"]').val() == '0.0'){
		$('#status5').text("");
		$('#t_status5').val("");
	}
	if($('[name="rent[5].d_amt"]').val() == '0.0' && $('[name="rent[5].v_amt"]').val() != '0.0'){
		$('#status5').text("Verified");
		$('#t_status5').val("Verified");
	}
	if($('[name="rent[5].d_amt"]').val() != '0.0' && $('[name="rent[5].v_amt"]').val() == '0.0'){
		$('#status5').text("Declared");
		$('#t_status5').val("Declared");
	}
	if($('[name="rent[5].d_amt"]').val() != '0.0' && $('[name="rent[5].v_amt"]').val() != '0.0'){
		$('#status5').text("Verified");
		$('#t_status5').val("Verified");
	}
});

$('[name="rent[6].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[6].v_amt"]').val().length == 0 || $('[name="rent[6].v_amt"]').val() == 0) {
		$('[name="rent[6].v_amt"]').val('0.0');
	}
	if($('[name="rent[6].d_amt"]').val() == '0.0' && $('[name="rent[6].v_amt"]').val() != '0.0'){
		$('#status6').text("Verified");
		$('#t_status6').val("Verified");
	}
	if($('[name="rent[6].d_amt"]').val() == '0.0' && $('[name="rent[6].v_amt"]').val() == '0.0'){
		$('#status6').text("");
		$('#t_status6').val("");
	}
	if($('[name="rent[6].d_amt"]').val() == '0.0' && $('[name="rent[6].v_amt"]').val() != '0.0'){
		$('#status6').text("Verified");
		$('#t_status6').val("Verified");
	}
	if($('[name="rent[6].d_amt"]').val() != '0.0' && $('[name="rent[6].v_amt"]').val() == '0.0'){
		$('#status6').text("Declared");
		$('#t_status6').val("Declared");
	}
	if($('[name="rent[6].d_amt"]').val() != '0.0' && $('[name="rent[6].v_amt"]').val() != '0.0'){
		$('#status6').text("Verified");
		$('#t_status6').val("Verified");
	}
});
$('[name="rent[7].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[7].v_amt"]').val().length == 0 || $('[name="rent[7].v_amt"]').val() == 0) {
		$('[name="rent[7].v_amt"]').val('0.0');
	}
	if($('[name="rent[7].d_amt"]').val() == '0.0' && $('[name="rent[7].v_amt"]').val() != '0.0'){
		$('#status7').text("Verified");
		$('#t_status7').val("Verified");
	}
	if($('[name="rent[7].d_amt"]').val() == '0.0' && $('[name="rent[7].v_amt"]').val() == '0.0'){
		$('#status7').text("");
		$('#t_status7').val("");
	}
	if($('[name="rent[7].d_amt"]').val() == '0.0' && $('[name="rent[7].v_amt"]').val() != '0.0'){
		$('#status7').text("Verified");
		$('#t_status7').val("Verified");
	}
	if($('[name="rent[7].d_amt"]').val() != '0.0' && $('[name="rent[7].v_amt"]').val() == '0.0'){
		$('#status7').text("Declared");
		$('#t_status7').val("Declared");
	}
	if($('[name="rent[7].d_amt"]').val() != '0.0' && $('[name="rent[7].v_amt"]').val() != '0.0'){
		$('#status7').text("Verified");
		$('#t_status7').val("Verified");
	}
});

$('[name="rent[8].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[8].v_amt"]').val().length == 0 || $('[name="rent[8].v_amt"]').val() == 0) {
		$('[name="rent[8].v_amt"]').val('0.0');
	}
	if($('[name="rent[8].d_amt"]').val() == '0.0' && $('[name="rent[8].v_amt"]').val() != '0.0'){
		$('#status8').text("Verified");
		$('#t_status8').val("Verified");
	}
	if($('[name="rent[8].d_amt"]').val() == '0.0' && $('[name="rent[8].v_amt"]').val() == '0.0'){
		$('#status8').text("");
		$('#t_status8').val("");
	}
	if($('[name="rent[8].d_amt"]').val() == '0.0' && $('[name="rent[8].v_amt"]').val() != '0.0'){
		$('#status8').text("Verified");
		$('#t_status8').val("Verified");
	}
	if($('[name="rent[8].d_amt"]').val() != '0.0' && $('[name="rent[8].v_amt"]').val() == '0.0'){
		$('#status8').text("Declared");
		$('#t_status8').val("Declared");
	}
	if($('[name="rent[8].d_amt"]').val() != '0.0' && $('[name="rent[8].v_amt"]').val() != '0.0'){
		$('#status8').text("Verified");
		$('#t_status8').val("Verified");
	}
});

$('[name="rent[9].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[9].v_amt"]').val().length == 0 || $('[name="rent[9].v_amt"]').val() == 0) {
		$('[name="rent[9].v_amt"]').val('0.0');
	}
	if($('[name="rent[9].d_amt"]').val() == '0.0' && $('[name="rent[9].v_amt"]').val() != '0.0'){
		$('#status9').text("Verified");
		$('#t_status9').val("Verified");
	}
	if($('[name="rent[9].d_amt"]').val() == '0.0' && $('[name="rent[9].v_amt"]').val() == '0.0'){
		$('#status9').text("");
		$('#t_status9').val("");
	}
	if($('[name="rent[9].d_amt"]').val() == '0.0' && $('[name="rent[9].v_amt"]').val() != '0.0'){
		$('#status9').text("Verified");
		$('#t_status9').val("Verified");
	}
	if($('[name="rent[9].d_amt"]').val() != '0.0' && $('[name="rent[9].v_amt"]').val() == '0.0'){
		$('#status9').text("Declared");
		$('#t_status9').val("Declared");
	}
	if($('[name="rent[9].d_amt"]').val() != '0.0' && $('[name="rent[9].v_amt"]').val() != '0.0'){
		$('#status9').text("Verified");
		$('#t_status9').val("Verified");
	}
});

$('[name="rent[10].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[10].v_amt"]').val().length == 0 || $('[name="rent[10].v_amt"]').val() == 0) {
		$('[name="rent[10].v_amt"]').val('0.0');
	}
	if($('[name="rent[10].d_amt"]').val() == '0.0' && $('[name="rent[10].v_amt"]').val() != '0.0'){
		$('#status10').text("Verified");
		$('#t_status10').val("Verified");
	}
	if($('[name="rent[10].d_amt"]').val() == '0.0' && $('[name="rent[10].v_amt"]').val() == '0.0'){
		$('#status10').text("");
		$('#t_status10').val("");
	}
	if($('[name="rent[10].d_amt"]').val() == '0.0' && $('[name="rent[10].v_amt"]').val() != '0.0'){
		$('#status10').text("Verified");
		$('#t_status10').val("Verified");
	}
	if($('[name="rent[10].d_amt"]').val() != '0.0' && $('[name="rent[10].v_amt"]').val() == '0.0'){
		$('#status10').text("Declared");
		$('#t_status10').val("Declared");
	}
	if($('[name="rent[10].d_amt"]').val() != '0.0' && $('[name="rent[10].v_amt"]').val() != '0.0'){
		$('#status10').text("Verified");
		$('#t_status10').val("Verified");
	}
});

$('[name="rent[11].v_amt"]').on('change keyup paste', function() {
	if($('[name="rent[11].v_amt"]').val().length == 0 || $('[name="rent[11].v_amt"]').val() == 0) {
		$('[name="rent[11].v_amt"]').val('0.0');
	}
	if($('[name="rent[11].d_amt"]').val() == '0.0' && $('[name="rent[11].v_amt"]').val() != '0.0'){
		$('#status11').text("Verified");
		$('#t_status11').val("Verified");
	}
	if($('[name="rent[11].d_amt"]').val() == '0.0' && $('[name="rent[11].v_amt"]').val() == '0.0'){
		$('#status11').text("");
		$('#t_status11').val("");
	}
	if($('[name="rent[11].d_amt"]').val() == '0.0' && $('[name="rent[11].v_amt"]').val() != '0.0'){
		$('#status11').text("Verified");
		$('#t_status11').val("Verified");
	}
	if($('[name="rent[11].d_amt"]').val() != '0.0' && $('[name="rent[11].v_amt"]').val() == '0.0'){
		$('#status11').text("Declared");
		$('#t_status11').val("Declared");
	}
	if($('[name="rent[11].d_amt"]').val() != '0.0' && $('[name="rent[11].v_amt"]').val() != '0.0'){
		$('#status11').text("Verified");
		$('#t_status11').val("Verified");
	}
});

//Chapter VI Declaration


$('[name="taxEmployeeChapter6A[0].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[0].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[0].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[0].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[0].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[0].vamount"]').val() != '0.0'){
		$('#statusVI0').text("Verified");
		$('#t_statusVI0').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[0].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[0].vamount"]').val() == '0.0'){
		$('#statusVI0').text("");
		$('#t_statusVI0').val("");
	}
	if($('[name="taxEmployeeChapter6A[0].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[0].vamount"]').val() != '0.0'){
		$('#statusVI0').text("Verified");
		$('#t_statusVI0').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[0].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[0].vamount"]').val() == '0.0'){
		$('#statusVI0').text("Declared");
		$('#t_statusVI0').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[0].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[0].vamount"]').val() != '0.0'){
		$('#statusVI0').text("Verified");
		$('#t_statusVI0').val("Verified");
	}
});


$('[name="taxEmployeeChapter6A[1].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[1].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[1].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[1].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[1].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[1].vamount"]').val() != '0.0'){
		$('#statusVI1').text("Verified");
		$('#t_statusVI1').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[1].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[1].vamount"]').val() == '0.0'){
		$('#statusVI1').text("");
		$('#t_statusVI1').val("");
	}
	if($('[name="taxEmployeeChapter6A[1].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[1].vamount"]').val() != '0.0'){
		$('#statusVI1').text("Verified");
		$('#t_statusVI1').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[1].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[1].vamount"]').val() == '0.0'){
		$('#statusVI1').text("Declared");
		$('#t_statusVI1').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[1].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[1].vamount"]').val() != '0.0'){
		$('#statusVI1').text("Verified");
		$('#t_statusVI1').val("Verified");
	}
});


$('[name="taxEmployeeChapter6A[2].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[2].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[2].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[2].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[2].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[2].vamount"]').val() != '0.0'){
		$('#statusVI2').text("Verified");
		$('#t_statusVI2').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[2].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[2].vamount"]').val() == '0.0'){
		$('#statusVI2').text("");
		$('#t_statusVI2').val("");
	}
	if($('[name="taxEmployeeChapter6A[2].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[2].vamount"]').val() != '0.0'){
		$('#statusVI2').text("Verified");
		$('#t_statusVI2').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[2].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[2].vamount"]').val() == '0.0'){
		$('#statusVI2').text("Declared");
		$('#t_statusVI2').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[2].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[2].vamount"]').val() != '0.0'){
		$('#statusVI2').text("Verified");
		$('#t_statusVI2').val("Verified");
	}
});



$('[name="taxEmployeeChapter6A[3].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[3].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[3].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[3].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[3].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[3].vamount"]').val() != '0.0'){
		$('#statusVI3').text("Verified");
		$('#t_statusVI3').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[3].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[3].vamount"]').val() == '0.0'){
		$('#statusVI3').text("");
		$('#t_statusVI3').val("");
	}
	if($('[name="taxEmployeeChapter6A[3].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[3].vamount"]').val() != '0.0'){
		$('#statusVI3').text("Verified");
		$('#t_statusVI3').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[3].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[3].vamount"]').val() == '0.0'){
		$('#statusVI3').text("Declared");
		$('#t_statusVI3').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[3].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[3].vamount"]').val() != '0.0'){
		$('#statusVI3').text("Verified");
		$('#t_statusVI3').val("Verified");
	}
});


$('[name="taxEmployeeChapter6A[4].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[4].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[4].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[4].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[4].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[4].vamount"]').val() != '0.0'){
		$('#statusVI4').text("Verified");
		$('#t_statusVI4').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[4].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[4].vamount"]').val() == '0.0'){
		$('#statusVI4').text("");
		$('#t_statusVI4').val("");
	}
	if($('[name="taxEmployeeChapter6A[4].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[4].vamount"]').val() != '0.0'){
		$('#statusVI4').text("Verified");
		$('#t_statusVI4').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[4].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[4].vamount"]').val() == '0.0'){
		$('#statusVI4').text("Declared");
		$('#t_statusVI4').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[4].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[4].vamount"]').val() != '0.0'){
		$('#statusVI4').text("Verified");
		$('#t_statusVI4').val("Verified");
	}
});

$('[name="taxEmployeeChapter6A[5].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[5].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[5].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[5].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[5].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[5].vamount"]').val() != '0.0'){
		$('#statusVI5').text("Verified");
		$('#t_statusVI5').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[5].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[5].vamount"]').val() == '0.0'){
		$('#statusVI5').text("");
		$('#t_statusVI5').val("");
	}
	if($('[name="taxEmployeeChapter6A[5].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[5].vamount"]').val() != '0.0'){
		$('#statusVI5').text("Verified");
		$('#t_statusVI5').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[5].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[5].vamount"]').val() == '0.0'){
		$('#statusVI5').text("Declared");
		$('#t_statusVI5').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[5].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[5].vamount"]').val() != '0.0'){
		$('#statusVI5').text("Verified");
		$('#t_statusVI5').val("Verified");
	}
});


$('[name="taxEmployeeChapter6A[6].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[6].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[6].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[6].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[6].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[6].vamount"]').val() != '0.0'){
		$('#statusVI6').text("Verified");
		$('#t_statusVI6').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[6].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[6].vamount"]').val() == '0.0'){
		$('#statusVI6').text("");
		$('#t_statusVI6').val("");
	}
	if($('[name="taxEmployeeChapter6A[6].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[6].vamount"]').val() != '0.0'){
		$('#statusVI6').text("Verified");
		$('#t_statusVI6').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[6].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[6].vamount"]').val() == '0.0'){
		$('#statusVI6').text("Declared");
		$('#t_statusVI6').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[6].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[6].vamount"]').val() != '0.0'){
		$('#statusVI6').text("Verified");
		$('#t_statusVI6').val("Verified");
	}
});


$('[name="taxEmployeeChapter6A[7].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[7].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[7].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[7].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[7].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[7].vamount"]').val() != '0.0'){
		$('#statusVI7').text("Verified");
		$('#t_statusVI7').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[7].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[7].vamount"]').val() == '0.0'){
		$('#statusVI7').text("");
		$('#t_statusVI7').val("");
	}
	if($('[name="taxEmployeeChapter6A[7].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[7].vamount"]').val() != '0.0'){
		$('#statusVI7').text("Verified");
		$('#t_statusVI7').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[7].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[7].vamount"]').val() == '0.0'){
		$('#statusVI7').text("Declared");
		$('#t_statusVI7').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[7].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[7].vamount"]').val() != '0.0'){
		$('#statusVI7').text("Verified");
		$('#t_statusVI7').val("Verified");
	}
});

$('[name="taxEmployeeChapter6A[8].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[8].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[8].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[8].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[8].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[8].vamount"]').val() != '0.0'){
		$('#statusVI8').text("Verified");
		$('#t_statusVI8').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[8].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[8].vamount"]').val() == '0.0'){
		$('#statusVI8').text("");
		$('#t_statusVI8').val("");
	}
	if($('[name="taxEmployeeChapter6A[8].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[8].vamount"]').val() != '0.0'){
		$('#statusVI8').text("Verified");
		$('#t_statusVI8').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[8].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[8].vamount"]').val() == '0.0'){
		$('#statusVI8').text("Declared");
		$('#t_statusVI8').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[8].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[8].vamount"]').val() != '0.0'){
		$('#statusVI8').text("Verified");
		$('#t_statusVI8').val("Verified");
	}
});


$('[name="taxEmployeeChapter6A[9].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[9].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[9].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[9].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[9].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[9].vamount"]').val() != '0.0'){
		$('#statusVI9').text("Verified");
		$('#t_statusVI9').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[9].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[9].vamount"]').val() == '0.0'){
		$('#statusVI0').text("");
		$('#t_statusVI0').val("");
	}
	if($('[name="taxEmployeeChapter6A[9].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[9].vamount"]').val() != '0.0'){
		$('#statusVI9').text("Verified");
		$('#t_statusVI9').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[9].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[9].vamount"]').val() == '0.0'){
		$('#statusVI9').text("Declared");
		$('#t_statusVI9').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[9].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[9].vamount"]').val() != '0.0'){
		$('#statusVI9').text("Verified");
		$('#t_statusVI9').val("Verified");
	}
});



$('[name="taxEmployeeChapter6A[10].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[10].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[10].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[10].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[10].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[10].vamount"]').val() != '0.0'){
		$('#statusVI10').text("Verified");
		$('#t_statusVI10').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[10].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[10].vamount"]').val() == '0.0'){
		$('#statusVI10').text("");
		$('#t_statusVI10').val("");
	}
	if($('[name="taxEmployeeChapter6A[10].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[10].vamount"]').val() != '0.0'){
		$('#statusVI10').text("Verified");
		$('#t_statusVI10').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[10].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[10].vamount"]').val() == '0.0'){
		$('#statusVI10').text("Declared");
		$('#t_statusVI10').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[10].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[10].vamount"]').val() != '0.0'){
		$('#statusVI10').text("Verified");
		$('#t_statusVI10').val("Verified");
	}
});


$('[name="taxEmployeeChapter6A[11].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[11].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[11].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[11].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[11].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[11].vamount"]').val() != '0.0'){
		$('#statusVI11').text("Verified");
		$('#t_statusVI11').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[11].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[11].vamount"]').val() == '0.0'){
		$('#statusVI11').text("");
		$('#t_statusVI11').val("");
	}
	if($('[name="taxEmployeeChapter6A[11].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[11].vamount"]').val() != '0.0'){
		$('#statusVI11').text("Verified");
		$('#t_statusVI11').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[11].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[11].vamount"]').val() == '0.0'){
		$('#statusVI10').text("Declared");
		$('#t_statusVI10').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[11].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[11].vamount"]').val() != '0.0'){
		$('#statusVI11').text("Verified");
		$('#t_statusVI11').val("Verified");
	}
});



$('[name="taxEmployeeChapter6A[12].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[12].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[12].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[12].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[11].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[12].vamount"]').val() != '0.0'){
		$('#statusVI12').text("Verified");
		$('#t_statusVI12').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[12].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[12].vamount"]').val() == '0.0'){
		$('#statusVI12').text("");
		$('#t_statusVI12').val("");
	}
	if($('[name="taxEmployeeChapter6A[12].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[12].vamount"]').val() != '0.0'){
		$('#statusVI12').text("Verified");
		$('#t_statusVI12').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[12].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[12].vamount"]').val() == '0.0'){
		$('#statusVI12').text("Declared");
		$('#t_statusVI12').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[12].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[12].vamount"]').val() != '0.0'){
		$('#statusVI12').text("Verified");
		$('#t_statusVI12').val("Verified");
	}
});



$('[name="taxEmployeeChapter6A[13].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[13].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[13].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[13].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() != '0.0'){
		$('#statusVI13').text("Verified");
		$('#t_statusVI13').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() == '0.0'){
		$('#statusVI13').text("");
		$('#t_statusVI13').val("");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() != '0.0'){
		$('#statusVI13').text("Verified");
		$('#t_statusVI13').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() == '0.0'){
		$('#statusVI13').text("Declared");
		$('#t_statusVI13').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() != '0.0'){
		$('#statusVI13').text("Verified");
		$('#t_statusVI13').val("Verified");
	}
});

$('[name="taxEmployeeChapter6A[13].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[13].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[13].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[13].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() != '0.0'){
		$('#statusVI13').text("Verified");
		$('#t_statusVI13').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() == '0.0'){
		$('#statusVI13').text("");
		$('#t_statusVI13').val("");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() != '0.0'){
		$('#statusVI13').text("Verified");
		$('#t_statusVI13').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() == '0.0'){
		$('#statusVI13').text("Declared");
		$('#t_statusVI13').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[13].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[13].vamount"]').val() != '0.0'){
		$('#statusVI13').text("Verified");
		$('#t_statusVI13').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[14].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[14].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[14].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[14].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[14].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[14].vamount"]').val() != '0.0'){
		$('#statusVI14').text("Verified");
		$('#t_statusVI14').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[14].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[14].vamount"]').val() == '0.0'){
		$('#statusVI14').text("");
		$('#t_statusVI14').val("");
	}
	if($('[name="taxEmployeeChapter6A[14].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[14].vamount"]').val() != '0.0'){
		$('#statusVI14').text("Verified");
		$('#t_statusVI14').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[14].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[14].vamount"]').val() == '0.0'){
		$('#statusVI14').text("Declared");
		$('#t_statusVI14').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[14].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[14].vamount"]').val() != '0.0'){
		$('#statusVI14').text("Verified");
		$('#t_statusVI14').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[15].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[15].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[15].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[15].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[15].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[15].vamount"]').val() != '0.0'){
		$('#statusVI15').text("Verified");
		$('#t_statusVI15').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[15].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[15].vamount"]').val() == '0.0'){
		$('#statusVI15').text("");
		$('#t_statusVI15').val("");
	}
	if($('[name="taxEmployeeChapter6A[15].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[15].vamount"]').val() != '0.0'){
		$('#statusVI15').text("Verified");
		$('#t_statusVI15').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[15].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[15].vamount"]').val() == '0.0'){
		$('#statusVI15').text("Declared");
		$('#t_statusVI15').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[15].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[15].vamount"]').val() != '0.0'){
		$('#statusVI15').text("Verified");
		$('#t_statusVI15').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[16].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[16].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[16].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[16].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[16].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[16].vamount"]').val() != '0.0'){
		$('#statusVI16').text("Verified");
		$('#t_statusVI16').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[16].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[16].vamount"]').val() == '0.0'){
		$('#statusVI16').text("");
		$('#t_statusVI16').val("");
	}
	if($('[name="taxEmployeeChapter6A[16].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[16].vamount"]').val() != '0.0'){
		$('#statusVI16').text("Verified");
		$('#t_statusVI16').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[16].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[16].vamount"]').val() == '0.0'){
		$('#statusVI16').text("Declared");
		$('#t_statusVI16').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[16].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[16].vamount"]').val() != '0.0'){
		$('#statusVI16').text("Verified");
		$('#t_statusVI16').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[17].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[17].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[17].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[17].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[17].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[17].vamount"]').val() != '0.0'){
		$('#statusVI17').text("Verified");
		$('#t_statusVI17').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[17].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[17].vamount"]').val() == '0.0'){
		$('#statusVI17').text("");
		$('#t_statusVI17').val("");
	}
	if($('[name="taxEmployeeChapter6A[17].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[17].vamount"]').val() != '0.0'){
		$('#statusVI17').text("Verified");
		$('#t_statusVI17').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[17].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[17].vamount"]').val() == '0.0'){
		$('#statusVI17').text("Declared");
		$('#t_statusVI17').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[17].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[17].vamount"]').val() != '0.0'){
		$('#statusVI17').text("Verified");
		$('#t_statusVI17').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[18].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[18].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[18].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[18].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[18].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[18].vamount"]').val() != '0.0'){
		$('#statusVI18').text("Verified");
		$('#t_statusVI18').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[18].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[18].vamount"]').val() == '0.0'){
		$('#statusVI18').text("");
		$('#t_statusVI18').val("");
	}
	if($('[name="taxEmployeeChapter6A[18].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[18].vamount"]').val() != '0.0'){
		$('#statusVI18').text("Verified");
		$('#t_statusVI18').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[18].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[18].vamount"]').val() == '0.0'){
		$('#statusVI18').text("Declared");
		$('#t_statusVI18').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[18].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[18].vamount"]').val() != '0.0'){
		$('#statusVI18').text("Verified");
		$('#t_statusVI18').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[19].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[19].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[19].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[19].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[19].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[19].vamount"]').val() != '0.0'){
		$('#statusVI19').text("Verified");
		$('#t_statusVI19').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[19].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[19].vamount"]').val() == '0.0'){
		$('#statusVI19').text("");
		$('#t_statusVI19').val("");
	}
	if($('[name="taxEmployeeChapter6A[19].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[19].vamount"]').val() != '0.0'){
		$('#statusVI19').text("Verified");
		$('#t_statusVI19').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[19].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[19].vamount"]').val() == '0.0'){
		$('#statusVI19').text("Declared");
		$('#t_statusVI19').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[19].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[19].vamount"]').val() != '0.0'){
		$('#statusVI19').text("Verified");
		$('#t_statusVI19').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[20].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[20].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[20].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[20].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[20].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[20].vamount"]').val() != '0.0'){
		$('#statusVI20').text("Verified");
		$('#t_statusVI20').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[20].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[20].vamount"]').val() == '0.0'){
		$('#statusVI20').text("");
		$('#t_statusVI20').val("");
	}
	if($('[name="taxEmployeeChapter6A[20].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[20].vamount"]').val() != '0.0'){
		$('#statusVI20').text("Verified");
		$('#t_statusVI20').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[20].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[20].vamount"]').val() == '0.0'){
		$('#statusVI20').text("Declared");
		$('#t_statusVI20').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[20].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[20].vamount"]').val() != '0.0'){
		$('#statusVI20').text("Verified");
		$('#t_statusVI20').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[21].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[21].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[21].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[21].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[21].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[21].vamount"]').val() != '0.0'){
		$('#statusVI21').text("Verified");
		$('#t_statusVI21').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[21].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[21].vamount"]').val() == '0.0'){
		$('#statusVI21').text("");
		$('#t_statusVI21').val("");
	}
	if($('[name="taxEmployeeChapter6A[21].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[21].vamount"]').val() != '0.0'){
		$('#statusVI21').text("Verified");
		$('#t_statusVI21').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[21].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[21].vamount"]').val() == '0.0'){
		$('#statusVI21').text("Declared");
		$('#t_statusVI21').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[21].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[21].vamount"]').val() != '0.0'){
		$('#statusVI21').text("Verified");
		$('#t_statusVI21').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[22].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[22].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[22].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[22].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[22].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[22].vamount"]').val() != '0.0'){
		$('#statusVI22').text("Verified");
		$('#t_statusVI22').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[22].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[22].vamount"]').val() == '0.0'){
		$('#statusVI22').text("");
		$('#t_statusVI22').val("");
	}
	if($('[name="taxEmployeeChapter6A[22].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[22].vamount"]').val() != '0.0'){
		$('#statusVI22').text("Verified");
		$('#t_statusVI22').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[22].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[22].vamount"]').val() == '0.0'){
		$('#statusVI22').text("Declared");
		$('#t_statusVI22').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[22].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[22].vamount"]').val() != '0.0'){
		$('#statusVI22').text("Verified");
		$('#t_statusVI22').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[23].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[23].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[23].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[23].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[23].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[23].vamount"]').val() != '0.0'){
		$('#statusVI23').text("Verified");
		$('#t_statusVI23').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[23].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[23].vamount"]').val() == '0.0'){
		$('#statusVI23').text("");
		$('#t_statusVI23').val("");
	}
	if($('[name="taxEmployeeChapter6A[23].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[23].vamount"]').val() != '0.0'){
		$('#statusVI23').text("Verified");
		$('#t_statusVI23').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[23].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[23].vamount"]').val() == '0.0'){
		$('#statusVI23').text("Declared");
		$('#t_statusVI23').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[23].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[23].vamount"]').val() != '0.0'){
		$('#statusVI23').text("Verified");
		$('#t_statusVI23').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[24].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[24].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[24].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[24].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[24].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[24].vamount"]').val() != '0.0'){
		$('#statusVI24').text("Verified");
		$('#t_statusVI24').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[24].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[24].vamount"]').val() == '0.0'){
		$('#statusVI24').text("");
		$('#t_statusVI24').val("");
	}
	if($('[name="taxEmployeeChapter6A[24].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[24].vamount"]').val() != '0.0'){
		$('#statusVI24').text("Verified");
		$('#t_statusVI24').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[24].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[24].vamount"]').val() == '0.0'){
		$('#statusVI24').text("Declared");
		$('#t_statusVI24').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[24].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[24].vamount"]').val() != '0.0'){
		$('#statusVI24').text("Verified");
		$('#t_statusVI24').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[25].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[25].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[25].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[25].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[25].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[25].vamount"]').val() != '0.0'){
		$('#statusVI25').text("Verified");
		$('#t_statusVI25').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[25].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[25].vamount"]').val() == '0.0'){
		$('#statusVI25').text("");
		$('#t_statusVI25').val("");
	}
	if($('[name="taxEmployeeChapter6A[25].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[25].vamount"]').val() != '0.0'){
		$('#statusVI25').text("Verified");
		$('#t_statusVI25').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[25].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[25].vamount"]').val() == '0.0'){
		$('#statusVI25').text("Declared");
		$('#t_statusVI25').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[25].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[25].vamount"]').val() != '0.0'){
		$('#statusVI25').text("Verified");
		$('#t_statusVI25').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[26].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[26].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[26].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[26].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[26].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[26].vamount"]').val() != '0.0'){
		$('#statusVI26').text("Verified");
		$('#t_statusVI26').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[26].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[26].vamount"]').val() == '0.0'){
		$('#statusVI26').text("");
		$('#t_statusVI26').val("");
	}
	if($('[name="taxEmployeeChapter6A[26].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[26].vamount"]').val() != '0.0'){
		$('#statusVI26').text("Verified");
		$('#t_statusVI26').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[26].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[26].vamount"]').val() == '0.0'){
		$('#statusVI26').text("Declared");
		$('#t_statusVI26').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[26].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[26].vamount"]').val() != '0.0'){
		$('#statusVI26').text("Verified");
		$('#t_statusVI26').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[27].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[27].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[27].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[27].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[27].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[27].vamount"]').val() != '0.0'){
		$('#statusVI27').text("Verified");
		$('#t_statusVI27').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[27].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[27].vamount"]').val() == '0.0'){
		$('#statusVI27').text("");
		$('#t_statusVI27').val("");
	}
	if($('[name="taxEmployeeChapter6A[27].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[27].vamount"]').val() != '0.0'){
		$('#statusVI27').text("Verified");
		$('#t_statusVI27').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[27].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[27].vamount"]').val() == '0.0'){
		$('#statusVI27').text("Declared");
		$('#t_statusVI27').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[27].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[27].vamount"]').val() != '0.0'){
		$('#statusVI27').text("Verified");
		$('#t_statusVI27').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[28].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[28].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[28].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[28].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[28].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[28].vamount"]').val() != '0.0'){
		$('#statusVI28').text("Verified");
		$('#t_statusVI28').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[28].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[28].vamount"]').val() == '0.0'){
		$('#statusVI28').text("");
		$('#t_statusVI28').val("");
	}
	if($('[name="taxEmployeeChapter6A[28].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[28].vamount"]').val() != '0.0'){
		$('#statusVI28').text("Verified");
		$('#t_statusVI28').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[28].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[28].vamount"]').val() == '0.0'){
		$('#statusVI28').text("Declared");
		$('#t_statusVI28').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[28].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[28].vamount"]').val() != '0.0'){
		$('#statusVI28').text("Verified");
		$('#t_statusVI28').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[29].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[29].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[29].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[29].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[29].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[29].vamount"]').val() != '0.0'){
		$('#statusVI29').text("Verified");
		$('#t_statusVI29').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[29].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[29].vamount"]').val() == '0.0'){
		$('#statusVI29').text("");
		$('#t_statusVI29').val("");
	}
	if($('[name="taxEmployeeChapter6A[29].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[29].vamount"]').val() != '0.0'){
		$('#statusVI29').text("Verified");
		$('#t_statusVI29').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[29].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[29].vamount"]').val() == '0.0'){
		$('#statusVI29').text("Declared");
		$('#t_statusVI29').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[29].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[29].vamount"]').val() != '0.0'){
		$('#statusVI29').text("Verified");
		$('#t_statusVI29').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[30].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[30].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[30].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[30].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[30].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[30].vamount"]').val() != '0.0'){
		$('#statusVI30').text("Verified");
		$('#t_statusVI30').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[30].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[30].vamount"]').val() == '0.0'){
		$('#statusVI30').text("");
		$('#t_statusVI30').val("");
	}
	if($('[name="taxEmployeeChapter6A[30].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[30].vamount"]').val() != '0.0'){
		$('#statusVI30').text("Verified");
		$('#t_statusVI30').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[30].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[30].vamount"]').val() == '0.0'){
		$('#statusVI30').text("Declared");
		$('#t_statusVI30').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[30].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[30].vamount"]').val() != '0.0'){
		$('#statusVI30').text("Verified");
		$('#t_statusVI30').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[31].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[31].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[31].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[31].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[31].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[31].vamount"]').val() != '0.0'){
		$('#statusVI31').text("Verified");
		$('#t_statusVI31').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[31].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[31].vamount"]').val() == '0.0'){
		$('#statusVI31').text("");
		$('#t_statusVI31').val("");
	}
	if($('[name="taxEmployeeChapter6A[31].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[31].vamount"]').val() != '0.0'){
		$('#statusVI31').text("Verified");
		$('#t_statusVI31').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[31].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[31].vamount"]').val() == '0.0'){
		$('#statusVI31').text("Declared");
		$('#t_statusVI31').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[31].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[31].vamount"]').val() != '0.0'){
		$('#statusVI31').text("Verified");
		$('#t_statusVI31').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[32].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[32].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[32].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[32].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[32].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[32].vamount"]').val() != '0.0'){
		$('#statusVI32').text("Verified");
		$('#t_statusVI32').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[32].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[32].vamount"]').val() == '0.0'){
		$('#statusVI32').text("");
		$('#t_statusVI32').val("");
	}
	if($('[name="taxEmployeeChapter6A[32].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[32].vamount"]').val() != '0.0'){
		$('#statusVI32').text("Verified");
		$('#t_statusVI32').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[32].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[32].vamount"]').val() == '0.0'){
		$('#statusVI32').text("Declared");
		$('#t_statusVI32').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[32].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[32].vamount"]').val() != '0.0'){
		$('#statusVI32').text("Verified");
		$('#t_statusVI32').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[33].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[33].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[33].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[33].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[33].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[33].vamount"]').val() != '0.0'){
		$('#statusVI33').text("Verified");
		$('#t_statusVI33').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[33].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[33].vamount"]').val() == '0.0'){
		$('#statusVI33').text("");
		$('#t_statusVI33').val("");
	}
	if($('[name="taxEmployeeChapter6A[33].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[33].vamount"]').val() != '0.0'){
		$('#statusVI33').text("Verified");
		$('#t_statusVI33').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[33].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[33].vamount"]').val() == '0.0'){
		$('#statusVI33').text("Declared");
		$('#t_statusVI33').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[33].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[33].vamount"]').val() != '0.0'){
		$('#statusVI33').text("Verified");
		$('#t_statusVI33').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[34].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[34].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[34].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[34].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[34].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[34].vamount"]').val() != '0.0'){
		$('#statusVI34').text("Verified");
		$('#t_statusVI34').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[34].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[34].vamount"]').val() == '0.0'){
		$('#statusVI34').text("");
		$('#t_statusVI34').val("");
	}
	if($('[name="taxEmployeeChapter6A[34].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[34].vamount"]').val() != '0.0'){
		$('#statusVI34').text("Verified");
		$('#t_statusVI34').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[34].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[34].vamount"]').val() == '0.0'){
		$('#statusVI34').text("Declared");
		$('#t_statusVI34').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[34].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[34].vamount"]').val() != '0.0'){
		$('#statusVI34').text("Verified");
		$('#t_statusVI34').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[35].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[35].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[35].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[35].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[35].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[35].vamount"]').val() != '0.0'){
		$('#statusVI35').text("Verified");
		$('#t_statusVI35').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[35].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[35].vamount"]').val() == '0.0'){
		$('#statusVI35').text("");
		$('#t_statusVI35').val("");
	}
	if($('[name="taxEmployeeChapter6A[35].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[35].vamount"]').val() != '0.0'){
		$('#statusVI35').text("Verified");
		$('#t_statusVI35').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[35].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[35].vamount"]').val() == '0.0'){
		$('#statusVI35').text("Declared");
		$('#t_statusVI35').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[35].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[35].vamount"]').val() != '0.0'){
		$('#statusVI35').text("Verified");
		$('#t_statusVI35').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[36].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[36].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[36].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[36].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[36].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[36].vamount"]').val() != '0.0'){
		$('#statusVI36').text("Verified");
		$('#t_statusVI36').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[36].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[36].vamount"]').val() == '0.0'){
		$('#statusVI36').text("");
		$('#t_statusVI36').val("");
	}
	if($('[name="taxEmployeeChapter6A[36].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[36].vamount"]').val() != '0.0'){
		$('#statusVI36').text("Verified");
		$('#t_statusVI36').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[36].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[36].vamount"]').val() == '0.0'){
		$('#statusVI36').text("Declared");
		$('#t_statusVI36').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[36].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[36].vamount"]').val() != '0.0'){
		$('#statusVI36').text("Verified");
		$('#t_statusVI36').val("Verified");
	}
});
$('[name="taxEmployeeChapter6A[37].vamount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[37].vamount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[37].vamount"]').val() == 0) {
		$('[name="taxEmployeeChapter6A[37].vamount"]').val('0.0');
	}
	if($('[name="taxEmployeeChapter6A[37].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[37].vamount"]').val() != '0.0'){
		$('#statusVI37').text("Verified");
		$('#t_statusVI37').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[37].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[37].vamount"]').val() == '0.0'){
		$('#statusVI37').text("");
		$('#t_statusVI37').val("");
	}
	if($('[name="taxEmployeeChapter6A[37].damount"]').val() == '0.0' && $('[name="taxEmployeeChapter6A[37].vamount"]').val() != '0.0'){
		$('#statusVI37').text("Verified");
		$('#t_statusVI37').val("Verified");
	}
	if($('[name="taxEmployeeChapter6A[37].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[37].vamount"]').val() == '0.0'){
		$('#statusVI37').text("Declared");
		$('#t_statusVI37').val("Declared");
	}
	if($('[name="taxEmployeeChapter6A[37].damount"]').val() != '0.0' && $('[name="taxEmployeeChapter6A[37].vamount"]').val() != '0.0'){
		$('#statusVI37').text("Verified");
		$('#t_statusVI37').val("Verified");
	}
});
///Intrest on home loan
/*
$('[name="paytaxsave[0].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[0].verAmount"]').val().length == 0 || $('[name="paytaxsave[0].verAmount"]').val() == 0) {
		$('[name="paytaxsave[0].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[0].verAmount"]').val() == '0.0' && $('[name="paytaxsave[0].verAmount"]').val() != '0.0'){
		$('#statusHI0').text("Verified");
		$('#t_statusHI0').val("Verified");
	}
	if($('[name="paytaxsave[0].verAmount"]').val() == '0.0' && $('[name="paytaxsave[0].verAmount"]').val() == '0.0'){
		$('#statusHI0').text("");
		$('#t_statusHI0').val("");
	}
	if($('[name="paytaxsave[0].verAmount"]').val() == '0.0' && $('[name="paytaxsave[0].verAmount"]').val() != '0.0'){
		$('#statusHI0').text("Verified");
		$('#t_statusHI0').val("Verified");
	}
	if($('[name="paytaxsave[0].verAmount"]').val() != '0.0' && $('[name="paytaxsave[0].verAmount"]').val() == '0.0'){
		$('#statusHI0').text("Declared");
		$('#t_statusHI0').val("Declared");
	}
	if($('[name="paytaxsave[0].verAmount"]').val() != '0.0' && $('[name="paytaxsave[0].verAmount"]').val() != '0.0'){
		$('#statusHI0').text("Verified");
		$('#t_statusHI0').val("Verified");
	}
});



$('[name="paytaxsave[1].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[1].verAmount"]').val().length == 0 || $('[name="paytaxsave[1].verAmount"]').val() == 0) {
		$('[name="paytaxsave[1].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[1].verAmount"]').val() == '0.0' && $('[name="paytaxsave[1].verAmount"]').val() != '0.0'){
		$('#statusHI1').text("Verified");
		$('#t_statusHI1').val("Verified");
	}
	if($('[name="paytaxsave[1].verAmount"]').val() == '0.0' && $('[name="paytaxsave[1].verAmount"]').val() == '0.0'){
		$('#statusHI1').text("");
		$('#t_statusHI1').val("");
	}
	if($('[name="paytaxsave[1].verAmount"]').val() == '0.0' && $('[name="paytaxsave[1].verAmount"]').val() != '0.0'){
		$('#statusHI1').text("Verified");
		$('#t_statusHI1').val("Verified");
	}
	if($('[name="paytaxsave[1].verAmount"]').val() != '0.0' && $('[name="paytaxsave[1].verAmount"]').val() == '0.0'){
		$('#statusHI1').text("Declared");
		$('#t_statusHI1').val("Declared");
	}
	if($('[name="paytaxsave[1].verAmount"]').val() != '0.0' && $('[name="paytaxsave[1].verAmount"]').val() != '0.0'){
		$('#statusHI1').text("Verified");
		$('#t_statusHI1').val("Verified");
	}
});


$('[name="paytaxsave[2].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[2].verAmount"]').val().length == 0 || $('[name="paytaxsave[2].verAmount"]').val() == 0) {
		$('[name="paytaxsave[2].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[2].verAmount"]').val() == '0.0' && $('[name="paytaxsave[2].verAmount"]').val() != '0.0'){
		$('#statusHI2').text("Verified");
		$('#t_statusHI2').val("Verified");
	}
	if($('[name="paytaxsave[2].verAmount"]').val() == '0.0' && $('[name="paytaxsave[2].verAmount"]').val() == '0.0'){
		$('#statusHI2').text("");
		$('#t_statusHI2').val("");
	}
	if($('[name="paytaxsave[2].verAmount"]').val() == '0.0' && $('[name="paytaxsave[2].verAmount"]').val() != '0.0'){
		$('#statusHI2').text("Verified");
		$('#t_statusHI2').val("Verified");
	}
	if($('[name="paytaxsave[2].verAmount"]').val() != '0.0' && $('[name="paytaxsave[2].verAmount"]').val() == '0.0'){
		$('#statusHI2').text("Declared");
		$('#t_statusHI2').val("Declared");
	}
	if($('[name="paytaxsave[2].verAmount"]').val() != '0.0' && $('[name="paytaxsave[2].verAmount"]').val() != '0.0'){
		$('#statusHI2').text("Verified");
		$('#t_statusHI2').val("Verified");
	}
});


$('[name="paytaxsave[3].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[3].verAmount"]').val().length == 0 || $('[name="paytaxsave[3].verAmount"]').val() == 0) {
		$('[name="paytaxsave[3].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[3].verAmount"]').val() == '0.0' && $('[name="paytaxsave[3].verAmount"]').val() != '0.0'){
		$('#statusHI3').text("Verified");
		$('#t_statusHI3').val("Verified");
	}
	if($('[name="paytaxsave[3].verAmount"]').val() == '0.0' && $('[name="paytaxsave[3].verAmount"]').val() == '0.0'){
		$('#statusHI3').text("");
		$('#t_statusHI3').val("");
	}
	if($('[name="paytaxsave[3].verAmount"]').val() == '0.0' && $('[name="paytaxsave[3].verAmount"]').val() != '0.0'){
		$('#statusHI3').text("Verified");
		$('#t_statusHI3').val("Verified");
	}
	if($('[name="paytaxsave[3].verAmount"]').val() != '0.0' && $('[name="paytaxsave[3].verAmount"]').val() == '0.0'){
		$('#statusHI3').text("Declared");
		$('#t_statusHI3').val("Declared");
	}
	if($('[name="paytaxsave[3].verAmount"]').val() != '0.0' && $('[name="paytaxsave[3].verAmount"]').val() != '0.0'){
		$('#statusHI3').text("Verified");
		$('#t_statusHI3').val("Verified");
	}
});


$('[name="paytaxsave[4].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[4].verAmount"]').val().length == 0 || $('[name="paytaxsave[4].verAmount"]').val() == 0) {
		$('[name="paytaxsave[4].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[4].verAmount"]').val() == '0.0' && $('[name="paytaxsave[4].verAmount"]').val() != '0.0'){
		$('#statusHI4').text("Verified");
		$('#t_statusHI4').val("Verified");
	}
	if($('[name="paytaxsave[4].verAmount"]').val() == '0.0' && $('[name="paytaxsave[4].verAmount"]').val() == '0.0'){
		$('#statusHI4').text("");
		$('#t_statusHI4').val("");
	}
	if($('[name="paytaxsave[4].verAmount"]').val() == '0.0' && $('[name="paytaxsave[4].verAmount"]').val() != '0.0'){
		$('#statusHI4').text("Verified");
		$('#t_statusHI4').val("Verified");
	}
	if($('[name="paytaxsave[4].verAmount"]').val() != '0.0' && $('[name="paytaxsave[4].verAmount"]').val() == '0.0'){
		$('#statusHI4').text("Declared");
		$('#t_statusHI4').val("Declared");
	}
	if($('[name="paytaxsave[4].verAmount"]').val() != '0.0' && $('[name="paytaxsave[4].verAmount"]').val() != '0.0'){
		$('#statusHI4').text("Verified");
		$('#t_statusHI4').val("Verified");
	}
});

$('[name="paytaxsave[5].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[5].verAmount"]').val().length == 0 || $('[name="paytaxsave[5].verAmount"]').val() == 0) {
		$('[name="paytaxsave[5].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[5].verAmount"]').val() == '0.0' && $('[name="paytaxsave[5].verAmount"]').val() != '0.0'){
		$('#statusHI5').text("Verified");
		$('#t_statusHI5').val("Verified");
	}
	if($('[name="paytaxsave[5].verAmount"]').val() == '0.0' && $('[name="paytaxsave[5].verAmount"]').val() == '0.0'){
		$('#statusHI5').text("");
		$('#t_statusHI5').val("");
	}
	if($('[name="paytaxsave[5].verAmount"]').val() == '0.0' && $('[name="paytaxsave[5].verAmount"]').val() != '0.0'){
		$('#statusHI5').text("Verified");
		$('#t_statusHI5').val("Verified");
	}
	if($('[name="paytaxsave[5].verAmount"]').val() != '0.0' && $('[name="paytaxsave[5].verAmount"]').val() == '0.0'){
		$('#statusHI5').text("Declared");
		$('#t_statusHI5').val("Declared");
	}
	if($('[name="paytaxsave[5].verAmount"]').val() != '0.0' && $('[name="paytaxsave[5].verAmount"]').val() != '0.0'){
		$('#statusHI5').text("Verified");
		$('#t_statusHI5').val("Verified");
	}
});

$('[name="paytaxsave[6].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[6].verAmount"]').val().length == 0 || $('[name="paytaxsave[6].verAmount"]').val() == 0) {
		$('[name="paytaxsave[6].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[6].verAmount"]').val() == '0.0' && $('[name="paytaxsave[6].verAmount"]').val() != '0.0'){
		$('#statusHI6').text("Verified");
		$('#t_statusHI6').val("Verified");
	}
	if($('[name="paytaxsave[6].verAmount"]').val() == '0.0' && $('[name="paytaxsave[6].verAmount"]').val() == '0.0'){
		$('#statusHI6').text("");
		$('#t_statusHI6').val("");
	}
	if($('[name="paytaxsave[6].verAmount"]').val() == '0.0' && $('[name="paytaxsave[6].verAmount"]').val() != '0.0'){
		$('#statusHI6').text("Verified");
		$('#t_statusHI6').val("Verified");
	}
	if($('[name="paytaxsave[6].verAmount"]').val() != '0.0' && $('[name="paytaxsave[6].verAmount"]').val() == '0.0'){
		$('#statusHI6').text("Declared");
		$('#t_statusHI6').val("Declared");
	}
	if($('[name="paytaxsave[6].verAmount"]').val() != '0.0' && $('[name="paytaxsave[6].verAmount"]').val() != '0.0'){
		$('#statusHI6').text("Verified");
		$('#t_statusHI6').val("Verified");
	}
});


$('[name="paytaxsave[7].verAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[7].verAmount"]').val().length == 0 || $('[name="paytaxsave[7].verAmount"]').val() == 0) {
		$('[name="paytaxsave[7].verAmount"]').val('0.0');
	}
	if($('[name="paytaxsave[7].verAmount"]').val() == '0.0' && $('[name="paytaxsave[7].verAmount"]').val() != '0.0'){
		$('#statusHI7').text("Verified");
		$('#t_statusHI7').val("Verified");
	}
	if($('[name="paytaxsave[7].verAmount"]').val() == '0.0' && $('[name="paytaxsave[7].verAmount"]').val() == '0.0'){
		$('#statusHI7').text("");
		$('#t_statusHI7').val("");
	}
	if($('[name="paytaxsave[7].verAmount"]').val() == '0.0' && $('[name="paytaxsave[7].verAmount"]').val() != '0.0'){
		$('#statusHI7').text("Verified");
		$('#t_statusHI7').val("Verified");
	}
	if($('[name="paytaxsave[7].verAmount"]').val() != '0.0' && $('[name="paytaxsave[7].verAmount"]').val() == '0.0'){
		$('#statusHI7').text("Declared");
		$('#t_statusHI7').val("Declared");
	}
	if($('[name="paytaxsave[7].verAmount"]').val() != '0.0' && $('[name="paytaxsave[7].verAmount"]').val() != '0.0'){
		$('#statusHI7').text("Verified");
		$('#t_statusHI7').val("Verified");
	}
});
*/

// Interest on Home Loan and Other  validation start



$('[name="person[0].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[0].verAmount"]').val().length == 0 || $('[name="person[0].verAmount"]').val() == 0) {
		$('[name="person[0].verAmount"]').val('0.0');
	}
	if($('[name="person[0].decAmount"]').val() == '0.0' && $('[name="person[0].verAmount"]').val() != '0.0'){
		$('#statusHI0').text("Verified");
		$('#t_statusHI0').val("Verified");
	}
	if($('[name="person[0].decAmount"]').val() == '0.0' && $('[name="person[0].verAmount"]').val() == '0.0'){
		$('#statusHI0').text("Verified");
		$('#t_statusHI0').val("Verified");
	}
	if($('[name="person[0].decAmount"]').val() == '0.0' && $('[name="person[0].verAmount"]').val() != '0.0'){
		$('#statusHI0').text("Verified");
		$('#t_statusHI0').val("Verified");
	}
	if($('[name="person[0].decAmount"]').val() != '0.0' && $('[name="person[0].verAmount"]').val() == '0.0'){
		$('#statusHI0').text("Declared");
		$('#t_statusHI0').val("Declared");
	}
	if($('[name="person[0].decAmount"]').val() != '0.0' && $('[name="person[0].verAmount"]').val() != '0.0'){
		$('#statusHI0').text("Verified");
		$('#t_statusHI0').val("Verified");
	}
});


$('[name="person[1].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[1].verAmount"]').val().length == 0 || $('[name="person[1].verAmount"]').val() == 0) {
		$('[name="person[1].verAmount"]').val('0.0');
	}
	if($('[name="person[1].decAmount"]').val() == '0.0' && $('[name="person[1].verAmount"]').val() != '0.0'){
		$('#statusHI1').text("Verified");
		$('#t_statusHI1').val("Verified");
	}
	if($('[name="person[1].decAmount"]').val() == '0.0' && $('[name="person[1].verAmount"]').val() == '0.0'){
		$('#statusHI1').text("Verified");
		$('#t_statusHI1').val("Verified");
	}
	if($('[name="person[1].decAmount"]').val() == '0.0' && $('[name="person[1].verAmount"]').val() != '0.0'){
		$('#statusHI1').text("Verified");
		$('#t_statusHI1').val("Verified");
	}
	if($('[name="person[1].decAmount"]').val() != '0.0' && $('[name="person[1].verAmount"]').val() == '0.0'){
		$('#statusHI1').text("Declared");
		$('#t_statusHI1').val("Declared");
	}
	if($('[name="person[1].decAmount"]').val() != '0.0' && $('[name="person[1].verAmount"]').val() != '0.0'){
		$('#statusHI1').text("Verified");
		$('#t_statusHI1').val("Verified");
	}
});




$('[name="person[2].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[2].verAmount"]').val().length == 0 || $('[name="person[2].verAmount"]').val() == 0) {
		$('[name="person[2].verAmount"]').val('0.0');
	}
	if($('[name="person[2].decAmount"]').val() == '0.0' && $('[name="person[2].verAmount"]').val() != '0.0'){
		$('#statusHI2').text("Verified");
		$('#t_statusHI2').val("Verified");
	}
	if($('[name="person[2].decAmount"]').val() == '0.0' && $('[name="person[2].verAmount"]').val() == '0.0'){
		$('#statusHI2').text("Verified");
		$('#t_statusHI2').val("Verified");
	}
	if($('[name="person[2].decAmount"]').val() == '0.0' && $('[name="person[2].verAmount"]').val() != '0.0'){
		$('#statusHI2').text("Verified");
		$('#t_statusHI2').val("Verified");
	}
	if($('[name="person[2].decAmount"]').val() != '0.0' && $('[name="person[2].verAmount"]').val() == '0.0'){
		$('#statusHI2').text("Declared");
		$('#t_statusHI2').val("Declared");
	}
	if($('[name="person[2].decAmount"]').val() != '0.0' && $('[name="person[2].verAmount"]').val() != '0.0'){
		$('#statusHI2').text("Verified");
		$('#t_statusHI2').val("Verified");
	}
});



$('[name="person[3].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[3].verAmount"]').val().length == 0 || $('[name="person[3].verAmount"]').val() == 0) {
		$('[name="person[3].verAmount"]').val('0.0');
	}
	if($('[name="person[3].decAmount"]').val() == '0.0' && $('[name="person[3].verAmount"]').val() != '0.0'){
		$('#statusHI3').text("Verified");
		$('#t_statusHI3').val("Verified");
	}
	if($('[name="person[3].decAmount"]').val() == '0.0' && $('[name="person[3].verAmount"]').val() == '0.0'){
		$('#statusHI3').text("Verified");
		$('#t_statusHI3').val("Verified");
	}
	if($('[name="person[3].decAmount"]').val() == '0.0' && $('[name="person[3].verAmount"]').val() != '0.0'){
		$('#statusHI3').text("Verified");
		$('#t_statusHI3').val("Verified");
	}
	if($('[name="person[3].decAmount"]').val() != '0.0' && $('[name="person[3].verAmount"]').val() == '0.0'){
		$('#statusHI3').text("Declared");
		$('#t_statusHI3').val("Declared");
	}
	if($('[name="person[3].decAmount"]').val() != '0.0' && $('[name="person[3].verAmount"]').val() != '0.0'){
		$('#statusHI3').text("Verified");
		$('#t_statusHI3').val("Verified");
	}
});



$('[name="person[4].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[4].verAmount"]').val().length == 0 || $('[name="person[4].verAmount"]').val() == 0) {
		$('[name="person[4].verAmount"]').val('0.0');
	}
	if($('[name="person[4].decAmount"]').val() == '0.0' && $('[name="person[4].verAmount"]').val() != '0.0'){
		$('#statusHI4').text("Verified");
		$('#t_statusHI4').val("Verified");
	}
	if($('[name="person[4].decAmount"]').val() == '0.0' && $('[name="person[4].verAmount"]').val() == '0.0'){
		$('#statusHI4').text("Verified");
		$('#t_statusHI4').val("Verified");
	}
	if($('[name="person[4].decAmount"]').val() == '0.0' && $('[name="person[4].verAmount"]').val() != '0.0'){
		$('#statusHI4').text("Verified");
		$('#t_statusHI4').val("Verified");
	}
	if($('[name="person[4].decAmount"]').val() != '0.0' && $('[name="person[4].verAmount"]').val() == '0.0'){
		$('#statusHI4').text("Declared");
		$('#t_statusHI4').val("Declared");
	}
	if($('[name="person[4].decAmount"]').val() != '0.0' && $('[name="person[4].verAmount"]').val() != '0.0'){
		$('#statusHI4').text("Verified");
		$('#t_statusHI4').val("Verified");
	}
});



$('[name="person[5].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[5].verAmount"]').val().length == 0 || $('[name="person[5].verAmount"]').val() == 0) {
		$('[name="person[5].verAmount"]').val('0.0');
	}
	if($('[name="person[5].decAmount"]').val() == '0.0' && $('[name="person[5].verAmount"]').val() != '0.0'){
		$('#statusHI5').text("Verified");
		$('#t_statusHI5').val("Verified");
	}
	if($('[name="person[5].decAmount"]').val() == '0.0' && $('[name="person[5].verAmount"]').val() == '0.0'){
		$('#statusHI5').text("Verified");
		$('#t_statusHI5').val("Verified");
	}
	if($('[name="person[5].decAmount"]').val() == '0.0' && $('[name="person[5].verAmount"]').val() != '0.0'){
		$('#statusHI5').text("Verified");
		$('#t_statusHI5').val("Verified");
	}
	if($('[name="person[5].decAmount"]').val() != '0.0' && $('[name="person[5].verAmount"]').val() == '0.0'){
		$('#statusHI5').text("Declared");
		$('#t_statusHI5').val("Declared");
	}
	if($('[name="person[5].decAmount"]').val() != '0.0' && $('[name="person[5].verAmount"]').val() != '0.0'){
		$('#statusHI5').text("Verified");
		$('#t_statusHI5').val("Verified");
	}
});



$('[name="person[6].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[6].verAmount"]').val().length == 0 || $('[name="person[6].verAmount"]').val() == 0) {
		$('[name="person[6].verAmount"]').val('0.0');
	}
	if($('[name="person[6].decAmount"]').val() == '0.0' && $('[name="person[6].verAmount"]').val() != '0.0'){
		$('#statusHI6').text("Verified");
		$('#t_statusHI6').val("Verified");
	}
	if($('[name="person[6].decAmount"]').val() == '0.0' && $('[name="person[6].verAmount"]').val() == '0.0'){
		$('#statusHI6').text("Verified");
		$('#t_statusHI6').val("Verified");
	}
	if($('[name="person[6].decAmount"]').val() == '0.0' && $('[name="person[6].verAmount"]').val() != '0.0'){
		$('#statusHI6').text("Verified");
		$('#t_statusHI6').val("Verified");
	}
	if($('[name="person[6].decAmount"]').val() != '0.0' && $('[name="person[6].verAmount"]').val() == '0.0'){
		$('#statusHI6').text("Declared");
		$('#t_statusHI6').val("Declared");
	}
	if($('[name="person[6].decAmount"]').val() != '0.0' && $('[name="person[6].verAmount"]').val() != '0.0'){
		$('#statusHI6').text("Verified");
		$('#t_statusHI6').val("Verified");
	}
});



$('[name="person[7].verAmount"]').on('change keyup paste', function() {
	if($('[name="person[7].verAmount"]').val().length == 0 || $('[name="person[7].verAmount"]').val() == 0) {
		$('[name="person[7].verAmount"]').val('0.0');
	}
	if($('[name="person[7].decAmount"]').val() == '0.0' && $('[name="person[7].verAmount"]').val() != '0.0'){
		$('#statusHI7').text("Verified");
		$('#t_statusHI7').val("Verified");
	}
	if($('[name="person[7].decAmount"]').val() == '0.0' && $('[name="person[7].verAmount"]').val() == '0.0'){
		$('#statusHI7').text("Verified");
		$('#t_statusHI7').val("Verified");
	}
	if($('[name="person[7].decAmount"]').val() == '0.0' && $('[name="person[7].verAmount"]').val() != '0.0'){
		$('#statusHI7').text("Verified");
		$('#t_statusHI7').val("Verified");
	}
	if($('[name="person[7].decAmount"]').val() != '0.0' && $('[name="person[7].verAmount"]').val() == '0.0'){
		$('#statusHI7').text("Declared");
		$('#t_statusHI7').val("Declared");
	}
	if($('[name="person[7].decAmount"]').val() != '0.0' && $('[name="person[7].verAmount"]').val() != '0.0'){
		$('#statusHI7').text("Verified");
		$('#t_statusHI7').val("Verified");
	}
});


//Interest on Home Loan and Other end


//Previous Employer Income start 

$('[name="v_amt_prv_inc"]').on('change keyup paste', function() {
	if($('[name="v_amt_prv_inc"]').val().length == 0 || $('[name="v_amt_prv_inc"]').val() == 0) {
		$('[name="v_amt_prv_inc"]').val('0.0');
	}
	if($('[name="d_amt_prv_inc"]').val() == '0.0' && $('[name="v_amt_prv_inc"]').val() != '0.0'){
		$('#statusPE0').text("Verified");
		//$('#t_statusPE0').val("Verified");
	}
	if($('[name="d_amt_prv_inc"]').val() == '0.0' && $('[name="v_amt_prv_inc"]').val() == '0.0'){
		$('#statusPE0').text("Verified");
		//$('#t_statusPE0').val("Verified");
	}
	if($('[name="d_amt_prv_inc"]').val() == '0.0' && $('[name="v_amt_prv_inc"]').val() != '0.0'){
		$('#statusPE0').text("Verified");
		//$('#t_statusPE0').val("Verified");
	}
	if($('[name="d_amt_prv_inc"]').val() != '0.0' && $('[name="v_amt_prv_inc"]').val() == '0.0'){
		$('#statusPE0').text("Declared");
		//$('#t_statusPE0').val("Declared");
	}
	if($('[name="d_amt_prv_inc"]').val() != '0.0' && $('[name="v_amt_prv_inc"]').val() != '0.0'){
		$('#statusPE0').text("Verified");
		//$('#t_statusPE0').val("Verified");
	}
});




$('[name="v_amt_prv_tax"]').on('change keyup paste', function() {
	if($('[name="v_amt_prv_tax"]').val().length == 0 || $('[name="v_amt_prv_tax"]').val() == 0) {
		$('[name="v_amt_prv_tax"]').val('0.0');
	}
	if($('[name="d_amt_prv_tax"]').val() == '0.0' && $('[name="v_amt_prv_tax"]').val() != '0.0'){
		$('#statusPE1').text("Verified");
		//$('#t_statusPE0').val("Verified");
	}
	if($('[name="d_amt_prv_tax"]').val() == '0.0' && $('[name="v_amt_prv_tax"]').val() == '0.0'){
		$('#statusPE1').text("Verified");
		//$('#t_statusPE1').val("Verified");
	}
	if($('[name="d_amt_prv_tax"]').val() == '0.0' && $('[name="v_amt_prv_tax"]').val() != '0.0'){
		$('#statusPE1').text("Verified");
		//$('#t_statusPE1').val("Verified");
	}
	if($('[name="d_amt_prv_tax"]').val() != '0.0' && $('[name="v_amt_prv_tax"]').val() == '0.0'){
		$('#statusPE1').text("Declared");
		//$('#t_statusPE1').val("Declared");
	}
	if($('[name="d_amt_prv_tax"]').val() != '0.0' && $('[name="v_amt_prv_tax"]').val() != '0.0'){
		$('#statusPE1').text("Verified");
		//$('#t_statusPE0').val("Verified");
	}
});


//Previous Employer Income end