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
	
	var data = "";
	data = '<tr>'+
		'<td style="width: 5%">'+
		'<input type="hidden" name="owner[0].ownerId"  value="0"/>'+
		'<input name="owner[0].fromdate" class="w3-input w3-border own_fromdate0" id="own_fromdate0" type="date" data-date="" data-date-format="YYYY MM DD"/>'+
		'</td>'+
		'<td style="width: 5%">'+
		'<input name="owner[0].todate" class="w3-input w3-border own_todate0" id="own_todate0" type="date" data-date="" data-date-format="YYYY MM DD" />'+
		'</td>'+
		'<td style="width: 15%">'+
		'<input class="w3-input w3-border" name="owner[0].name" id="own_name0" type="text">'+
		'</td>'+
		'<td style="width: 42%">'+
		'<input  class="w3-input w3-border" name="owner[0].address" id="own_address0" type="text">'+
		'</td>'+
		'<td style="width: 10%">'+
		'<input  class="w3-input w3-border" name="owner[0].pan" id="own_pan0" type="text">'+
		'</td>'+
		'<td style="width: 20%">'+
		'<input  class="ownerfiles" name="ownerfiles" id="own_attachment0" type="file">'+
		'</td>'+
		'<td style="width: 3%;">'+
		'<div id="own_btndel0" class="w3-text-black"  onclick="deleteOwnerRow(this)" style="text-align: right;">'+
		'<i class="far fa-times-circle" style="font-size:25px; cursor:pointer;" ></i></div>'+
		'</td>'+
		'</tr>';

	$("#OWNER_INFO_TBL tbody").append(data);
	//window.globalCounter++;

	$.each($('#OWNER_INFO_TBL tr'), function(index, val) {
		
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


function saveHRA(x)
{
	 $(x).css("display", "none");
	 /* var fromdt = $("#own_fromdate0").val();
      var todt = $("#own_todate0").val();


     if (fromdt == null || fromdt == ''||fromdt===undefined) {
       
        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
            message:"From Date cannot be null."		
        });
        $('#own_fromdate0').val();
        return;
    }

	if (todt == null || todt == ''||todt===undefined) {
       
        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
           message:"To Date cannot be null."		
        });
        $('#todt').val();
        return;
    }*/
	
	
	var fromdates=document.getElementsByClassName("own_fromdate0");
	var todates=document.getElementsByClassName("own_todate0");
	
	 for (l = 0; l < fromdates.length ; l++) {

            var fromdate0 = fromdates[l].value;
            var todate0 = todates[l].value;
       if (fromdate0[l] == null || fromdate0[l] == ''||fromdate0===undefined) {
         
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Form date and To date in Owner's information cannot be blank."				
			});
			return;
		}
		 if (todate0[l] == null || todate0[l] == ''||fromdate0===undefined) {
		
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:"Form date and To date in Owner's information cannot be blank."				
			});
			return;
		}
	
       }
	
	
	
	var form = $("#HOUSE_RENT_FORM")[0];
	var data = new FormData(form);
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");

	$.ajax({
		url: "/houserent/hraSave",
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
			}else if(resultfinal=="PANREQUIRED"){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:'Your declared amount is greater than ₹ 1 Lakh, PAN Card is mandatory for all the owners.'//,
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
		url: "/houserent/prevSave",
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
		url: "/houserent/paytaxSave",
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
	//debugger;
	 $(x).css("display", "none");

 $("input[name='taxEmployeeChapter6A[7].damount']").val("0.0");
 $("input[name='taxEmployeeChapter6A[11].damount']").val("0.0");
 $("input[name='taxEmployeeChapter6A[20].damount']").val("0.0");

	var form = $("#CHAPTER_VIA_FORM")[0];
	var data = new FormData(form);
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");

	$.ajax({
		url: "/houserent/chapterVIASave",
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

$('[name="rent[0].d_amt"]').on('change keyup paste', function() {

	if($('[name="rent[0].d_amt"]').val().length == 0 || $('[name="rent[0].d_amt"]').val() == 0){
		$('[name="rent[0].d_amt"]').val('0.0');
		$('#status0').text("");
		$('#t_status0').val("");
	} else {
		$('#status0').text("Declared");
		$('#t_status0').val("Declared");
	}
});


$('[name="rent[1].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[1].d_amt"]').val().length == 0 || $('[name="rent[1].d_amt"]').val() == 0){
		$('[name="rent[1].d_amt"]').val('0.0');
		$('#status1').text("");
		$('#t_status1').val("");
	} else {
		$('#status1').text("Declared");
		$('#t_status1').val("Declared");
	}
});
$('[name="rent[2].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[2].d_amt"]').val().length == 0 || $('[name="rent[2].d_amt"]').val() == 0){
		$('[name="rent[2].d_amt"]').val('0.0');
		$('#status2').text("");
		$('#t_status2').val("");
	} else {
		$('#status2').text("Declared");
		$('#t_status2').val("Declared");
	}
});
$('[name="rent[3].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[3].d_amt"]').val().length == 0 || $('[name="rent[3].d_amt"]').val() == 0){
		$('[name="rent[3].d_amt"]').val('0.0');
		$('#status3').text("");
		$('#t_status3').val("");
	} else {
		$('#status3').text("Declared");
		$('#t_status3').val("Declared");
	}
});
$('[name="rent[4].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[4].d_amt"]').val().length == 0 || $('[name="rent[4].d_amt"]').val() == 0){
		$('[name="rent[4].d_amt"]').val('0.0');
		$('#status4').text("");
		$('#t_status4').val("");
	} else {
		$('#status4').text("Declared");
		$('#t_status4').val("Declared");
	}
});
$('[name="rent[5].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[5].d_amt"]').val().length == 0 || $('[name="rent[5].d_amt"]').val() == 0){
		$('[name="rent[5].d_amt"]').val('0.0');
		$('#status5').text("");
		$('#t_status5').val("");
	} else {
		$('#status5').text("Declared");
		$('#t_status5').val("Declared");
	}
});
$('[name="rent[6].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[6].d_amt"]').val().length == 0 || $('[name="rent[6].d_amt"]').val() == 0){
		$('[name="rent[6].d_amt"]').val('0.0');
		$('#status6').text("");
		$('#t_status6').val("");
	} else {
		$('#status6').text("Declared");
		$('#t_status6').val("Declared");
	}
});
$('[name="rent[7].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[7].d_amt"]').val().length == 0 || $('[name="rent[7].d_amt"]').val() == 0){
		$('[name="rent[7].d_amt"]').val('0.0');
		$('#status7').text("");
		$('#t_status7').val("");
	} else {
		$('#status7').text("Declared");
		$('#t_status7').val("Declared");
	}
});
$('[name="rent[8].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[8].d_amt"]').val().length == 0 || $('[name="rent[8].d_amt"]').val() == 0){
		$('[name="rent[8].d_amt"]').val('0.0');
		$('#status8').text("");
		$('#t_status8').val("");
	} else {
		$('#status8').text("Declared");
		$('#t_status8').val("Declared");
	}
});
$('[name="rent[9].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[9].d_amt"]').val().length == 0 || $('[name="rent[9].d_amt"]').val() == 0){
		$('[name="rent[9].d_amt"]').val('0.0');
		$('#status9').text("");
		$('#t_status9').val("");
	} else {
		$('#status9').text("Declared");
		$('#t_status9').val("Declared");
	}
});
$('[name="rent[10].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[10].d_amt"]').val().length == 0 || $('[name="rent[10].d_amt"]').val() == 0){
		$('[name="rent[10].d_amt"]').val('0.0');
		$('#status10').text("");
		$('#t_status10').val("");
	} else {
		$('#status10').text("Declared");
		$('#t_status10').val("Declared");
	}
});
$('[name="rent[11].d_amt"]').on('change keyup paste', function() {
	if($('[name="rent[11].d_amt"]').val().length == 0 || $('[name="rent[11].d_amt"]').val() == 0){
		$('[name="rent[11].d_amt"]').val('0.0');
		$('#status11').text("");
		$('#t_status11').val("");
	} else {
		$('#status11').text("Declared");
		$('#t_status11').val("Declared");
	}

});

//**********************Added by rajat on 16-12-20
$('[name="taxEmployeeChapter6A[0].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[0].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[0].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[0].damount"]').val('0.0');
		$('#statusVI0').text("");
		$('#t_statusVI0').val("");
	} else {
		$('#statusVI0').text("Declared");
		$('#t_statusVI0').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[1].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[1].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[1].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[1].damount"]').val('0.0');
		$('#statusVI1').text("");
		$('#t_statusVI1').val("");
	} else {
		$('#statusVI1').text("Declared");
		$('#t_statusVI1').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[2].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[2].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[2].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[2].damount"]').val('0.0');
		$('#statusVI2').text("");
		$('#t_statusVI2').val("");
	} else {
		$('#statusVI2').text("Declared");
		$('#t_statusVI2').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[3].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[3].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[3].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[3].damount"]').val('0.0');
		$('#statusVI3').text("");
		$('#t_statusVI3').val("");
	} else {
		$('#statusVI3').text("Declared");
		$('#t_statusVI3').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[4].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[4].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[4].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[4].damount"]').val('0.0');
		$('#statusVI4').text("");
		$('#t_statusVI4').val("");
	} else {
		$('#statusVI4').text("Declared");
		$('#t_statusVI4').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[5].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[5].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[5].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[5].damount"]').val('0.0');
		$('#statusVI5').text("");
		$('#t_statusVI5').val("");
	} else {
		$('#statusVI5').text("Declared");
		$('#t_statusVI5').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[6].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[6].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[6].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[6].damount"]').val('0.0');
		$('#statusVI6').text("");
		$('#t_statusVI6').val("");
	} else {
		$('#statusVI6').text("Declared");
		$('#t_statusVI6').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[7].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[7].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[7].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[7].damount"]').val('0.0');
		$('#statusVI7').text("");
		$('#t_statusVI7').val("");
	} else {
		$('#statusVI7').text("Declared");
		$('#t_statusVI7').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[8].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[8].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[8].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[8].damount"]').val('0.0');
		$('#statusVI8').text("");
		$('#t_statusVI8').val("");
	} else {
		$('#statusVI8').text("Declared");
		$('#t_statusVI8').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[9].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[9].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[9].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[9].damount"]').val('0.0');
		$('#statusVI9').text("");
		$('#t_statusVI9').val("");
	} else {
		$('#statusVI9').text("Declared");
		$('#t_statusVI9').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[10].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[10].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[10].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[10].damount"]').val('0.0');
		$('#statusVI10').text("");
		$('#t_statusVI10').val("");
	} else {
		$('#statusVI10').text("Declared");
		$('#t_statusVI10').val("Declared");
	}
});
/*$('[name="taxEmployeeChapter6A[11].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[11].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[11].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[11].damount"]').val('0.0');
		$('#statusVI11').text("");
		$('#t_statusVI11').val("");
	} else {
		$('#statusVI11').text("Declared");
		$('#t_statusVI11').val("Declared");
	}
});*/
$('[name="taxEmployeeChapter6A[12].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[12].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[12].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[12].damount"]').val('0.0');
		$('#statusVI12').text("");
		$('#t_statusVI12').val("");
	} else {
		$('#statusVI12').text("Declared");
		$('#t_statusVI12').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[13].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[13].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[13].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[13].damount"]').val('0.0');
		$('#statusVI13').text("");
		$('#t_statusVI13').val("");
	} else {
		$('#statusVI13').text("Declared");
		$('#t_statusVI13').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[14].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[14].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[14].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[14].damount"]').val('0.0');
		$('#statusVI14').text("");
		$('#t_statusVI14').val("");
	} else {
		$('#statusVI14').text("Declared");
		$('#t_statusVI14').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[15].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[15].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[15].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[15].damount"]').val('0.0');
		$('#statusVI15').text("");
		$('#t_statusVI15').val("");
	} else {
		$('#statusVI15').text("Declared");
		$('#t_statusVI15').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[16].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[16].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[16].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[16].damount"]').val('0.0');
		$('#statusVI16').text("");
		$('#t_statusVI16').val("");
	} else {
		$('#statusVI16').text("Declared");
		$('#t_statusVI16').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[17].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[17].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[17].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[17].damount"]').val('0.0');
		$('#statusVI17').text("");
		$('#t_statusVI17').val("");
	} else {
		$('#statusVI17').text("Declared");
		$('#t_statusVI17').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[18].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[18].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[18].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[18].damount"]').val('0.0');
		$('#statusVI18').text("");
		$('#t_statusVI18').val("");
	} else {
		$('#statusVI18').text("Declared");
		$('#t_statusVI18').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[19].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[19].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[19].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[19].damount"]').val('0.0');
		$('#statusVI19').text("");
		$('#t_statusVI19').val("");
	} else {
		$('#statusVI19').text("Declared");
		$('#t_statusVI19').val("Declared");
	}
});
/*$('[name="taxEmployeeChapter6A[20].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[20].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[20].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[20].damount"]').val('0.0');
		$('#statusVI20').text("");
		$('#t_statusVI20').val("");
	} else {
		$('#statusVI20').text("Declared");
		$('#t_statusVI20').val("Declared");
	}
});*/
$('[name="taxEmployeeChapter6A[21].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[21].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[21].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[21].damount"]').val('0.0');
		$('#statusVI21').text("");
		$('#t_statusVI21').val("");
	} else {
		$('#statusVI21').text("Declared");
		$('#t_statusVI21').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[22].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[22].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[22].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[22].damount"]').val('0.0');
		$('#statusVI22').text("");
		$('#t_statusVI22').val("");
	} else {
		$('#statusVI22').text("Declared");
		$('#t_statusVI22').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[23].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[23].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[23].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[23].damount"]').val('0.0');
		$('#statusVI23').text("");
		$('#t_statusVI23').val("");
	} else {
		$('#statusVI23').text("Declared");
		$('#t_statusVI23').val("Declared");
	}
});

$('[name="taxEmployeeChapter6A[24].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[24].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[24].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[24].damount"]').val('0.0');
		$('#statusVI24').text("");
		$('#t_statusVI24').val("");
	} else {
		$('#statusVI24').text("Declared");
		$('#t_statusVI24').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[25].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[25].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[25].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[25].damount"]').val('0.0');
		$('#statusVI25').text("");
		$('#t_statusVI25').val("");
	} else {
		$('#statusVI25').text("Declared");
		$('#t_statusVI25').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[26].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[26].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[26].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[26].damount"]').val('0.0');
		$('#statusVI26').text("");
		$('#t_statusVI26').val("");
	} else {
		$('#statusVI26').text("Declared");
		$('#t_statusVI26').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[27].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[27].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[27].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[27].damount"]').val('0.0');
		$('#statusVI27').text("");
		$('#t_statusVI27').val("");
	} else {
		$('#statusVI27').text("Declared");
		$('#t_statusVI27').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[28].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[28].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[28].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[28].damount"]').val('0.0');
		$('#statusVI28').text("");
		$('#t_statusVI28').val("");
	} else {
		$('#statusVI28').text("Declared");
		$('#t_statusVI28').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[29].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[29].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[29].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[29].damount"]').val('0.0');
		$('#statusVI29').text("");
		$('#t_statusVI29').val("");
	} else {
		$('#statusVI29').text("Declared");
		$('#t_statusVI29').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[30].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[30].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[30].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[30].damount"]').val('0.0');
		$('#statusVI30').text("");
		$('#t_statusVI30').val("");
	} else {
		$('#statusVI30').text("Declared");
		$('#t_statusVI30').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[31].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[31].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[31].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[31].damount"]').val('0.0');
		$('#statusVI31').text("");
		$('#t_statusVI31').val("");
	} else {
		$('#statusVI31').text("Declared");
		$('#t_statusVI31').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[32].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[32].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[32].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[32].damount"]').val('0.0');
		$('#statusVI32').text("");
		$('#t_statusVI32').val("");
	} else {
		$('#statusVI32').text("Declared");
		$('#t_statusVI32').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[33].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[33].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[33].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[33].damount"]').val('0.0');
		$('#statusVI33').text("");
		$('#t_statusVI33').val("");
	} else {
		$('#statusVI33').text("Declared");
		$('#t_statusVI33').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[34].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[34].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[34].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[34].damount"]').val('0.0');
		$('#statusVI34').text("");
		$('#t_statusVI34').val("");
	} else {
		$('#statusVI34').text("Declared");
		$('#t_statusVI34').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[35].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[35].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[35].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[35].damount"]').val('0.0');
		$('#statusVI35').text("");
		$('#t_statusVI35').val("");
	} else {
		$('#statusVI35').text("Declared");
		$('#t_statusVI35').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[36].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[36].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[36].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[36].damount"]').val('0.0');
		$('#statusVI36').text("");
		$('#t_statusVI36').val("");
	} else {
		$('#statusVI36').text("Declared");
		$('#t_statusVI36').val("Declared");
	}
});
$('[name="taxEmployeeChapter6A[37].damount"]').on('change keyup paste', function() {
	if($('[name="taxEmployeeChapter6A[37].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[37].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[37].damount"]').val('0.0');
		$('#statusVI37').text("");
		$('#t_statusVI37').val("");
	} else {
		$('#statusVI37').text("Declared");
		$('#t_statusVI37').val("Declared");
	}
});

// Home intrest validation

$('[name="paytaxsave[0].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[0].decAmount"]').val().length == 0 || $('[name="paytaxsave[0].decAmount"]').val() == 0){
		$('[name="paytaxsave[0].decAmount"]').val('0.0');
		$('#statusHL0').text("");
		$('#t_statusHL0').val("");
	} else {
		$('#statusHL0').text("Declared");
		$('#t_statusHL0').val("Declared");
	}
});
$('[name="paytaxsave[1].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[1].decAmount"]').val().length == 0 || $('[name="paytaxsave[1].decAmount"]').val() == 0){
		$('[name="paytaxsave[1].decAmount"]').val('0.0');
		$('#statusHL1').text("");
		$('#t_statusHL1').val("");
	} else {
		$('#statusHL1').text("Declared");
		$('#t_statusHL1').val("Declared");
	}
});
$('[name="paytaxsave[2].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[2].decAmount"]').val().length == 0 || $('[name="paytaxsave[2].decAmount"]').val() == 0){
		$('[name="paytaxsave[2].decAmount"]').val('0.0');
		$('#statusHL2').text("");
		$('#t_statusHL2').val("");
	} else {
		$('#statusHL2').text("Declared");
		$('#t_statusHL2').val("Declared");
	}
});
$('[name="paytaxsave[3].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[3].decAmount"]').val().length == 0 || $('[name="paytaxsave[3].decAmount"]').val() == 0){
		$('[name="paytaxsave[3].decAmount"]').val('0.0');
		$('#statusHL3').text("");
		$('#t_statusHL3').val("");
	} else {
		$('#statusHL3').text("Declared");
		$('#t_statusHL3').val("Declared");
	}
});
$('[name="paytaxsave[4].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[4].decAmount"]').val().length == 0 || $('[name="paytaxsave[4].decAmount"]').val() == 0){
		$('[name="paytaxsave[4].decAmount"]').val('0.0');
		$('#statusHL4').text("");
		$('#t_statusHL4').val("");
	} else {
		$('#statusHL4').text("Declared");
		$('#t_statusHL4').val("Declared");
	}
});
$('[name="paytaxsave[5].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[5].decAmount"]').val().length == 0 || $('[name="paytaxsave[5].decAmount"]').val() == 0){
		$('[name="paytaxsave[5].decAmount"]').val('0.0');
		$('#statusHL5').text("");
		$('#t_statusHL5').val("");
	} else {
		$('#statusHL5').text("Declared");
		$('#t_statusHL5').val("Declared");
	}
});
$('[name="paytaxsave[6].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[6].decAmount"]').val().length == 0 || $('[name="paytaxsave[6].decAmount"]').val() == 0){
		$('[name="paytaxsave[6].decAmount"]').val('0.0');
		$('#statusHL6').text("");
		$('#t_statusHL6').val("");
	} else {
		$('#statusHL6').text("Declared");
		$('#t_statusHL6').val("Declared");
	}
});
$('[name="paytaxsave[7].decAmount"]').on('change keyup paste', function() {
	if($('[name="paytaxsave[7].decAmount"]').val().length == 0 || $('[name="paytaxsave[7].decAmount"]').val() == 0){
		$('[name="paytaxsave[7].decAmount"]').val('0.0');
		$('#statusHL7').text("");
		$('#t_statusHL7').val("");
	} else {
		$('#statusHL7').text("Declared");
		$('#t_statusHL7').val("Declared");
	}
});

//previous employer income

// Home Load and Intrest start here

$('[name="person[0].decAmount"]').on('change keyup paste', function() {
	if($('[name="person[0].decAmount"]').val().length == 0 || $('[name="person[0].decAmount"]').val() == 0){
		$('[name="person[0].decAmount"]').val('0.0');
		$('#statusHL0').text("");
		$('#t_statusHL0').val("");
	} else {
		$('#statusHL0').text("Declared");
		$('#t_statusHL0').val("Declared");
	}
});


$('[name="person[1].decAmount"]').on('change keyup paste', function() {
	if($('[name="person[1].decAmount"]').val().length == 0 || $('[name="person[1].decAmount"]').val() == 0){
		$('[name="person[1].decAmount"]').val('0.0');
		$('#statusHL1').text("");
		$('#t_statusHL1').val("");
	} else {
		$('#statusHL1').text("Declared");
		$('#t_statusHL1').val("Declared");
	}
});

$('[name="person[2].decAmount"]').on('change keyup paste', function() {
	if($('[name="person[2].decAmount"]').val().length == 0 || $('[name="person[2].decAmount"]').val() == 0){
		$('[name="person[2].decAmount"]').val('0.0');
		$('#statusHL2').text("");
		$('#t_statusHL2').val("");
	} else {
		$('#statusHL2').text("Declared");
		$('#t_statusHL2').val("Declared");
	}
});

$('[name="person[3].decAmount"]').on('change keyup paste', function() {
	if($('[name="person[3].decAmount"]').val().length == 0 || $('[name="person[3].decAmount"]').val() == 0){
		$('[name="person[3].decAmount"]').val('0.0');
		$('#statusHL3').text("");
		$('#t_statusHL3').val("");
	} else {
		$('#statusHL3').text("Declared");
		$('#t_statusHL3').val("Declared");
	}
});

$('[name="person[4].decAmount"]').on('change keyup paste', function() {
	if($('[name="person[4].decAmount"]').val().length == 0 || $('[name="person[4].decAmount"]').val() == 0){
		$('[name="person[4].decAmount"]').val('0.0');
		$('#statusHL4').text("");
		$('#t_statusHL4').val("");
	} else {
		$('#statusHL4').text("Declared");
		$('#t_statusHL4').val("Declared");
	}
});

$('[name="person[5].decAmount"]').on('change keyup paste', function() {
	if($('[name="person[5].decAmount"]').val().length == 0 || $('[name="person[5].decAmount"]').val() == 0){
		$('[name="person[5].decAmount"]').val('0.0');
		$('#statusHL5').text("");
		$('#t_statusHL5').val("");
	} else {
		$('#statusHL5').text("Declared");
		$('#t_statusHL5').val("Declared");
	}
});

$('[name="person[6].decAmount"]').on('change keyup paste', function() {
	if($('[name="person[6].decAmount"]').val().length == 0 || $('[name="person[6].decAmount"]').val() == 0){
		$('[name="person[6].decAmount"]').val('0.0');
		$('#statusHL6').text("");
		$('#t_statusHL6').val("");
	} else {
		$('#statusHL6').text("Declared");
		$('#t_statusHL6').val("Declared");
	}
});

//Home Loan and Intrest end here




//Previous Employer Income Start 

$('[name="d_amt_prv_inc"]').on('change keyup paste', function() {
	if($('[name="d_amt_prv_inc"]').val().length == 0 || $('[name="d_amt_prv_inc"]').val() == 0){
		$('[name="d_amt_prv_inc"]').val('0.0');
		$('#statusPE0').text("");
		//$('#t_statusPE0').val("");
	} else {
		$('#statusPE0').text("Declared");
		//$('#t_statusPE0').val("Declared");
	}
});



$('[name="d_amt_prv_tax"]').on('change keyup paste', function() {
	if($('[name="d_amt_prv_tax"]').val().length == 0 || $('[name="d_amt_prv_tax"]').val() == 0){
		$('[name="d_amt_prv_tax"]').val('0.0');
		$('#statusPE1').text("");
		//$('#t_statusPE0').val("");
	} else {
		$('#statusPE1').text("Declared");
		//$('#t_statusPE0').val("Declared");
	}
});

//Previous Employer Income End






/******************** Added on 07-May-2021 *******Rent Declaration***********/



$('[name="rent[0].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[0].d_amt"]').val().length == 0 || $('[name="rent[0].d_amt"]').val() == 0){
		$('[name="rent[0].d_amt"]').val('');
		$('#status0').text("");
		$('#t_status0').val("");
	} else {
		$('#status0').text("Declared");
		$('#t_status0').val("Declared");
	}
}); 
$('[name="rent[0].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[0].d_amt"]').val().length == 0 || $('[name="rent[0].d_amt"]').val() == 0){
		$('[name="rent[0].d_amt"]').val('0.0');
		$('#status0').text("");
		$('#t_status0').val("");
	} else {
		$('#status0').text("Declared");
		$('#t_status0').val("Declared");
	}
});


$('[name="rent[1].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[1].d_amt"]').val().length == 0 || $('[name="rent[1].d_amt"]').val() == 0){
		$('[name="rent[1].d_amt"]').val('');
		$('#status1').text("");
		$('#t_status1').val("");
	} else {
		$('#status1').text("Declared");
		$('#t_status1').val("Declared");
	}
}); 
$('[name="rent[1].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[1].d_amt"]').val().length == 0 || $('[name="rent[1].d_amt"]').val() == 0){
		$('[name="rent[1].d_amt"]').val('0.0');
		$('#status1').text("");
		$('#t_status1').val("");
	} else {
		$('#status1').text("Declared");
		$('#t_status1').val("Declared");
	}
});


$('[name="rent[2].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[2].d_amt"]').val().length == 0 || $('[name="rent[2].d_amt"]').val() == 0){
		$('[name="rent[2].d_amt"]').val('');
		$('#status2').text("");
		$('#t_status2').val("");
	} else {
		$('#status2').text("Declared");
		$('#t_status2').val("Declared");
	}
}); 
$('[name="rent[2].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[2].d_amt"]').val().length == 0 || $('[name="rent[2].d_amt"]').val() == 0){
		$('[name="rent[2].d_amt"]').val('0.0');
		$('#status2').text("");
		$('#t_status2').val("");
	} else {
		$('#status2').text("Declared");
		$('#t_status2').val("Declared");
	}
});


$('[name="rent[3].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[3].d_amt"]').val().length == 0 || $('[name="rent[3].d_amt"]').val() == 0){
		$('[name="rent[3].d_amt"]').val('');
		$('#status3').text("");
		$('#t_status3').val("");
	} else {
		$('#status3').text("Declared");
		$('#t_status3').val("Declared");
	}
}); 
$('[name="rent[3].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[3].d_amt"]').val().length == 0 || $('[name="rent[3].d_amt"]').val() == 0){
		$('[name="rent[3].d_amt"]').val('0.0');
		$('#status3').text("");
		$('#t_status3').val("");
	} else {
		$('#status3').text("Declared");
		$('#t_status3').val("Declared");
	}
});


$('[name="rent[4].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[4].d_amt"]').val().length == 0 || $('[name="rent[4].d_amt"]').val() == 0){
		$('[name="rent[4].d_amt"]').val('');
		$('#status4').text("");
		$('#t_status4').val("");
	} else {
		$('#status4').text("Declared");
		$('#t_status4').val("Declared");
	}
}); 
$('[name="rent[4].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[4].d_amt"]').val().length == 0 || $('[name="rent[4].d_amt"]').val() == 0){
		$('[name="rent[4].d_amt"]').val('0.0');
		$('#status4').text("");
		$('#t_status4').val("");
	} else {
		$('#status4').text("Declared");
		$('#t_status4').val("Declared");
	}
});


$('[name="rent[5].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[5].d_amt"]').val().length == 0 || $('[name="rent[5].d_amt"]').val() == 0){
		$('[name="rent[5].d_amt"]').val('');
		$('#status5').text("");
		$('#t_status5').val("");
	} else {
		$('#status5').text("Declared");
		$('#t_status5').val("Declared");
	}
}); 
$('[name="rent[5].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[5].d_amt"]').val().length == 0 || $('[name="rent[5].d_amt"]').val() == 0){
		$('[name="rent[5].d_amt"]').val('0.0');
		$('#status5').text("");
		$('#t_status5').val("");
	} else {
		$('#status5').text("Declared");
		$('#t_status5').val("Declared");
	}
});


$('[name="rent[6].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[6].d_amt"]').val().length == 0 || $('[name="rent[6].d_amt"]').val() == 0){
		$('[name="rent[6].d_amt"]').val('');
		$('#status6').text("");
		$('#t_status6').val("");
	} else {
		$('#status6').text("Declared");
		$('#t_status6').val("Declared");
	}
}); 
$('[name="rent[6].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[6].d_amt"]').val().length == 0 || $('[name="rent[6].d_amt"]').val() == 0){
		$('[name="rent[6].d_amt"]').val('0.0');
		$('#status6').text("");
		$('#t_status6').val("");
	} else {
		$('#status6').text("Declared");
		$('#t_status6').val("Declared");
	}
});


$('[name="rent[7].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[7].d_amt"]').val().length == 0 || $('[name="rent[7].d_amt"]').val() == 0){
		$('[name="rent[7].d_amt"]').val('');
		$('#status7').text("");
		$('#t_status7').val("");
	} else {
		$('#status7').text("Declared");
		$('#t_status7').val("Declared");
	}
}); 
$('[name="rent[7].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[7].d_amt"]').val().length == 0 || $('[name="rent[7].d_amt"]').val() == 0){
		$('[name="rent[7].d_amt"]').val('0.0');
		$('#status7').text("");
		$('#t_status7').val("");
	} else {
		$('#status7').text("Declared");
		$('#t_status7').val("Declared");
	}
});



$('[name="rent[8].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[8].d_amt"]').val().length == 0 || $('[name="rent[8].d_amt"]').val() == 0){
		$('[name="rent[8].d_amt"]').val('');
		$('#status8').text("");
		$('#t_status8').val("");
	} else {
		$('#status8').text("Declared");
		$('#t_status8').val("Declared");
	}
}); 
$('[name="rent[8].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[8].d_amt"]').val().length == 0 || $('[name="rent[8].d_amt"]').val() == 0){
		$('[name="rent[8].d_amt"]').val('0.0');
		$('#status8').text("");
		$('#t_status8').val("");
	} else {
		$('#status8').text("Declared");
		$('#t_status8').val("Declared");
	}
});



$('[name="rent[9].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[9].d_amt"]').val().length == 0 || $('[name="rent[9].d_amt"]').val() == 0){
		$('[name="rent[9].d_amt"]').val('');
		$('#status9').text("");
		$('#t_status9').val("");
	} else {
		$('#status9').text("Declared");
		$('#t_status9').val("Declared");
	}
}); 
$('[name="rent[9].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[9].d_amt"]').val().length == 0 || $('[name="rent[9].d_amt"]').val() == 0){
		$('[name="rent[9].d_amt"]').val('0.0');
		$('#status9').text("");
		$('#t_status9').val("");
	} else {
		$('#status9').text("Declared");
		$('#t_status9').val("Declared");
	}
});

$('[name="rent[10].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[10].d_amt"]').val().length == 0 || $('[name="rent[10].d_amt"]').val() == 0){
		$('[name="rent[10].d_amt"]').val('');
		$('#status10').text("");
		$('#t_status10').val("");
	} else {
		$('#status10').text("Declared");
		$('#t_status10').val("Declared");
	}
}); 
$('[name="rent[10].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[10].d_amt"]').val().length == 0 || $('[name="rent[10].d_amt"]').val() == 0){
		$('[name="rent[10].d_amt"]').val('0.0');
		$('#status10').text("");
		$('#t_status10').val("");
	} else {
		$('#status10').text("Declared");
		$('#t_status10').val("Declared");
	}
});

$('[name="rent[11].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[11].d_amt"]').val().length == 0 || $('[name="rent[11].d_amt"]').val() == 0){
		$('[name="rent[11].d_amt"]').val('');
		$('#status11').text("");
		$('#t_status11').val("");
	} else {
		$('#status11').text("Declared");
		$('#t_status11').val("Declared");
	}
}); 
$('[name="rent[11].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[11].d_amt"]').val().length == 0 || $('[name="rent[11].d_amt"]').val() == 0){
		$('[name="rent[11].d_amt"]').val('0.0');
		$('#status11').text("");
		$('#t_status11').val("");
	} else {
		$('#status11').text("Declared");
		$('#t_status11').val("Declared");
	}
});
$('[name="rent[12].d_amt"]').on('click', function() {
	//debugger;
	if($('[name="rent[12].d_amt"]').val().length == 0 || $('[name="rent[12].d_amt"]').val() == 0){
		$('[name="rent[12].d_amt"]').val('');
		$('#status12').text("");
		$('#t_status12').val("");
	} else {
		$('#status12').text("Declared");
		$('#t_status12').val("Declared");
	}
}); 
$('[name="rent[12].d_amt"]').on('blur', function() {
	//debugger;
	if($('[name="rent[12].d_amt"]').val().length == 0 || $('[name="rent[12].d_amt"]').val() == 0){
		$('[name="rent[12].d_amt"]').val('0.0');
		$('#status12').text("");
		$('#t_status12').val("");
	} else {
		$('#status12').text("Declared");
		$('#t_status12').val("Declared");
	}
});
/*************************** End Rent Declation Click event and onblur event************/





/******************** Added on 07-May-2021 *******Chapter 6A***********/


$('[name="taxEmployeeChapter6A[0].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[0].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[0].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[0].damount"]').val('');
		$('#statusVI0').text("");
		$('#t_statusVI0').val("");
	} else {
		$('#statusVI0').text("Declared");
		$('#t_statusVI0').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[0].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[0].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[0].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[0].damount"]').val('0.0');
		$('#statusVI0').text("");
		$('#t_statusVI0').val("");
	} else {
		$('#statusVI0').text("Declared");
		$('#t_statusVI0').val("Declared");
	}
});




$('[name="taxEmployeeChapter6A[1].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[1].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[1].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[1].damount"]').val('');
		$('#statusVI1').text("");
		$('#t_statusVI1').val("");
	} else {
		$('#statusVI1').text("Declared");
		$('#t_statusVI1').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[1].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[1].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[1].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[1].damount"]').val('0.0');
		$('#statusVI1').text("");
		$('#t_statusVI1').val("");
	} else {
		$('#statusVI1').text("Declared");
		$('#t_statusVI1').val("Declared");
	}
});

$('[name="taxEmployeeChapter6A[2].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[2].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[2].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[2].damount"]').val('');
		$('#statusVI2').text("");
		$('#t_statusVI2').val("");
	} else {
		$('#statusVI2').text("Declared");
		$('#t_statusVI2').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[2].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[2].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[2].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[2].damount"]').val('0.0');
		$('#statusVI2').text("");
		$('#t_statusVI2').val("");
	} else {
		$('#statusVI2').text("Declared");
		$('#t_statusVI2').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[3].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[3].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[3].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[3].damount"]').val('');
		$('#statusVI3').text("");
		$('#t_statusVI3').val("");
	} else {
		$('#statusVI3').text("Declared");
		$('#t_statusVI3').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[3].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[3].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[3].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[3].damount"]').val('0.0');
		$('#statusVI3').text("");
		$('#t_statusVI3').val("");
	} else {
		$('#statusVI3').text("Declared");
		$('#t_statusVI3').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[4].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[4].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[4].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[4].damount"]').val('');
		$('#statusVI4').text("");
		$('#t_statusVI4').val("");
	} else {
		$('#statusVI4').text("Declared");
		$('#t_statusVI4').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[4].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[4].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[4].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[4].damount"]').val('0.0');
		$('#statusVI4').text("");
		$('#t_statusVI4').val("");
	} else {
		$('#statusVI4').text("Declared");
		$('#t_statusVI4').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[5].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[5].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[5].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[5].damount"]').val('');
		$('#statusVI5').text("");
		$('#t_statusVI5').val("");
	} else {
		$('#statusVI5').text("Declared");
		$('#t_statusVI5').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[5].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[5].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[5].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[5].damount"]').val('0.0');
		$('#statusVI5').text("");
		$('#t_statusVI5').val("");
	} else {
		$('#statusVI5').text("Declared");
		$('#t_statusVI5').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[6].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[6].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[6].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[6].damount"]').val('');
		$('#statusVI6').text("");
		$('#t_statusVI6').val("");
	} else {
		$('#statusVI6').text("Declared");
		$('#t_statusVI6').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[6].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[6].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[6].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[6].damount"]').val('0.0');
		$('#statusVI6').text("");
		$('#t_statusVI6').val("");
	} else {
		$('#statusVI6').text("Declared");
		$('#t_statusVI6').val("Declared");
	}
});

$('[name="taxEmployeeChapter6A[7].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[7].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[7].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[7].damount"]').val('');
		$('#statusVI7').text("");
		$('#t_statusVI7').val("");
	} else {
		$('#statusVI7').text("Declared");
		$('#t_statusVI7').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[7].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[7].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[7].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[7].damount"]').val('0.0');
		$('#statusVI7').text("");
		$('#t_statusVI7').val("");
	} else {
		$('#statusVI7').text("Declared");
		$('#t_statusVI7').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[8].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[8].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[8].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[8].damount"]').val('');
		$('#statusVI8').text("");
		$('#t_statusVI8').val("");
	} else {
		$('#statusVI8').text("Declared");
		$('#t_statusVI8').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[8].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[8].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[8].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[8].damount"]').val('0.0');
		$('#statusVI8').text("");
		$('#t_statusVI8').val("");
	} else {
		$('#statusVI8').text("Declared");
		$('#t_statusVI8').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[9].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[9].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[9].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[9].damount"]').val('');
		$('#statusVI9').text("");
		$('#t_statusVI9').val("");
	} else {
		$('#statusVI9').text("Declared");
		$('#t_statusVI9').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[9].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[9].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[9].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[9].damount"]').val('0.0');
		$('#statusVI9').text("");
		$('#t_statusVI9').val("");
	} else {
		$('#statusVI9').text("Declared");
		$('#t_statusVI9').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[10].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[10].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[10].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[10].damount"]').val('');
		$('#statusVI10').text("");
		$('#t_statusVI10').val("");
	} else {
		$('#statusVI10').text("Declared");
		$('#t_statusVI10').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[10].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[10].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[10].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[10].damount"]').val('0.0');
		$('#statusVI10').text("");
		$('#t_statusVI10').val("");
	} else {
		$('#statusVI10').text("Declared");
		$('#t_statusVI10').val("Declared");
	}
});

/*$('[name="taxEmployeeChapter6A[11].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[11].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[11].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[11].damount"]').val('');
		$('#statusVI11').text("");
		$('#t_statusVI11').val("");
	} else {
		$('#statusVI11').text("Declared");
		$('#t_statusVI11').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[11].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[11].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[11].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[11].damount"]').val('0.0');
		$('#statusVI11').text("");
		$('#t_statusVI11').val("");
	} else {
		$('#statusVI11').text("Declared");
		$('#t_statusVI11').val("Declared");
	}
});*/



$('[name="taxEmployeeChapter6A[12].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[12].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[12].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[12].damount"]').val('');
		$('#statusVI12').text("");
		$('#t_statusVI12').val("");
	} else {
		$('#statusVI12').text("Declared");
		$('#t_statusVI12').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[12].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[12].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[12].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[12].damount"]').val('0.0');
		$('#statusVI12').text("");
		$('#t_statusVI12').val("");
	} else {
		$('#statusVI12').text("Declared");
		$('#t_statusVI12').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[13].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[13].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[13].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[13].damount"]').val('');
		$('#statusVI13').text("");
		$('#t_statusVI13').val("");
	} else {
		$('#statusVI13').text("Declared");
		$('#t_statusVI13').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[13].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[13].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[13].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[13].damount"]').val('0.0');
		$('#statusVI13').text("");
		$('#t_statusVI13').val("");
	} else {
		$('#statusVI13').text("Declared");
		$('#t_statusVI13').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[14].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[14].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[14].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[14].damount"]').val('');
		$('#statusVI14').text("");
		$('#t_statusVI14').val("");
	} else {
		$('#statusVI14').text("Declared");
		$('#t_statusVI14').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[14].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[14].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[14].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[14].damount"]').val('0.0');
		$('#statusVI14').text("");
		$('#t_statusVI14').val("");
	} else {
		$('#statusVI14').text("Declared");
		$('#t_statusVI14').val("Declared");
	}
});





$('[name="taxEmployeeChapter6A[14].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[14].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[14].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[14].damount"]').val('');
		$('#statusVI14').text("");
		$('#t_statusVI14').val("");
	} else {
		$('#statusVI14').text("Declared");
		$('#t_statusVI14').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[14].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[14].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[14].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[14].damount"]').val('0.0');
		$('#statusVI14').text("");
		$('#t_statusVI14').val("");
	} else {
		$('#statusVI14').text("Declared");
		$('#t_statusVI14').val("Declared");
	}
});




$('[name="taxEmployeeChapter6A[15].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[15].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[15].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[15].damount"]').val('');
		$('#statusVI15').text("");
		$('#t_statusVI15').val("");
	} else {
		$('#statusVI15').text("Declared");
		$('#t_statusVI15').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[15].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[15].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[15].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[15].damount"]').val('0.0');
		$('#statusVI15').text("");
		$('#t_statusVI15').val("");
	} else {
		$('#statusVI15').text("Declared");
		$('#t_statusVI15').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[16].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[16].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[16].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[16].damount"]').val('');
		$('#statusVI16').text("");
		$('#t_statusVI16').val("");
	} else {
		$('#statusVI16').text("Declared");
		$('#t_statusVI16').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[16].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[16].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[16].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[16].damount"]').val('0.0');
		$('#statusVI16').text("");
		$('#t_statusVI16').val("");
	} else {
		$('#statusVI16').text("Declared");
		$('#t_statusVI16').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[17].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[17].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[17].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[17].damount"]').val('');
		$('#statusVI17').text("");
		$('#t_statusVI17').val("");
	} else {
		$('#statusVI17').text("Declared");
		$('#t_statusVI17').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[17].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[17].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[17].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[17].damount"]').val('0.0');
		$('#statusVI17').text("");
		$('#t_statusVI17').val("");
	} else {
		$('#statusVI17').text("Declared");
		$('#t_statusVI17').val("Declared");
	}
});

$('[name="taxEmployeeChapter6A[18].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[18].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[18].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[18].damount"]').val('');
		$('#statusVI18').text("");
		$('#t_statusVI18').val("");
	} else {
		$('#statusVI18').text("Declared");
		$('#t_statusVI18').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[18].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[18].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[18].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[18].damount"]').val('0.0');
		$('#statusVI18').text("");
		$('#t_statusVI18').val("");
	} else {
		$('#statusVI18').text("Declared");
		$('#t_statusVI18').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[19].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[19].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[19].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[19].damount"]').val('');
		$('#statusVI19').text("");
		$('#t_statusVI19').val("");
	} else {
		$('#statusVI19').text("Declared");
		$('#t_statusVI19').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[19].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[19].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[19].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[19].damount"]').val('0.0');
		$('#statusVI19').text("");
		$('#t_statusVI19').val("");
	} else {
		$('#statusVI19').text("Declared");
		$('#t_statusVI19').val("Declared");
	}
});



/*
$('[name="taxEmployeeChapter6A[20].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[20].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[20].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[20].damount"]').val('');
		$('#statusVI20').text("");
		$('#t_statusVI20').val("");
	} else {
		$('#statusVI20').text("Declared");
		$('#t_statusVI20').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[20].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[20].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[20].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[20].damount"]').val('0.0');
		$('#statusVI20').text("");
		$('#t_statusVI20').val("");
	} else {
		$('#statusVI20').text("Declared");
		$('#t_statusVI20').val("Declared");
	}
});
*/


$('[name="taxEmployeeChapter6A[21].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[21].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[21].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[21].damount"]').val('');
		$('#statusVI21').text("");
		$('#t_statusVI21').val("");
	} else {
		$('#statusVI21').text("Declared");
		$('#t_statusVI21').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[21].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[21].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[21].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[21].damount"]').val('0.0');
		$('#statusVI21').text("");
		$('#t_statusVI21').val("");
	} else {
		$('#statusVI21').text("Declared");
		$('#t_statusVI21').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[22].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[22].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[22].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[22].damount"]').val('');
		$('#statusVI22').text("");
		$('#t_statusVI22').val("");
	} else {
		$('#statusVI22').text("Declared");
		$('#t_statusVI22').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[22].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[22].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[22].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[22].damount"]').val('0.0');
		$('#statusVI22').text("");
		$('#t_statusVI22').val("");
	} else {
		$('#statusVI22').text("Declared");
		$('#t_statusVI22').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[23].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[23].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[23].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[23].damount"]').val('');
		$('#statusVI23').text("");
		$('#t_statusVI23').val("");
	} else {
		$('#statusVI23').text("Declared");
		$('#t_statusVI23').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[23].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[23].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[23].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[23].damount"]').val('0.0');
		$('#statusVI23').text("");
		$('#t_statusVI23').val("");
	} else {
		$('#statusVI23').text("Declared");
		$('#t_statusVI23').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[24].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[24].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[24].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[24].damount"]').val('');
		$('#statusVI24').text("");
		$('#t_statusVI24').val("");
	} else {
		$('#statusVI24').text("Declared");
		$('#t_statusVI24').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[24].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[24].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[24].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[24].damount"]').val('0.0');
		$('#statusVI24').text("");
		$('#t_statusVI24').val("");
	} else {
		$('#statusVI24').text("Declared");
		$('#t_statusVI24').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[25].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[25].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[25].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[25].damount"]').val('');
		$('#statusVI25').text("");
		$('#t_statusVI25').val("");
	} else {
		$('#statusVI25').text("Declared");
		$('#t_statusVI25').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[25].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[25].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[25].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[25].damount"]').val('0.0');
		$('#statusVI25').text("");
		$('#t_statusVI25').val("");
	} else {
		$('#statusVI25').text("Declared");
		$('#t_statusVI25').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[26].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[26].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[26].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[26].damount"]').val('');
		$('#statusVI26').text("");
		$('#t_statusVI26').val("");
	} else {
		$('#statusVI26').text("Declared");
		$('#t_statusVI26').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[26].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[26].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[26].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[26].damount"]').val('0.0');
		$('#statusVI26').text("");
		$('#t_statusVI26').val("");
	} else {
		$('#statusVI26').text("Declared");
		$('#t_statusVI26').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[27].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[27].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[27].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[27].damount"]').val('');
		$('#statusVI27').text("");
		$('#t_statusVI27').val("");
	} else {
		$('#statusVI27').text("Declared");
		$('#t_statusVI27').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[27].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[27].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[27].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[27].damount"]').val('0.0');
		$('#statusVI27').text("");
		$('#t_statusVI27').val("");
	} else {
		$('#statusVI27').text("Declared");
		$('#t_statusVI27').val("Declared");
	}
});




$('[name="taxEmployeeChapter6A[28].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[28].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[28].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[28].damount"]').val('');
		$('#statusVI28').text("");
		$('#t_statusVI28').val("");
	} else {
		$('#statusVI28').text("Declared");
		$('#t_statusVI28').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[28].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[28].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[28].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[28].damount"]').val('0.0');
		$('#statusVI28').text("");
		$('#t_statusVI28').val("");
	} else {
		$('#statusVI28').text("Declared");
		$('#t_statusVI28').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[29].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[29].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[29].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[29].damount"]').val('');
		$('#statusVI29').text("");
		$('#t_statusVI29').val("");
	} else {
		$('#statusVI29').text("Declared");
		$('#t_statusVI29').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[29].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[29].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[29].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[29].damount"]').val('0.0');
		$('#statusVI29').text("");
		$('#t_statusVI29').val("");
	} else {
		$('#statusVI29').text("Declared");
		$('#t_statusVI29').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[30].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[30].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[30].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[30].damount"]').val('');
		$('#statusVI30').text("");
		$('#t_statusVI30').val("");
	} else {
		$('#statusVI30').text("Declared");
		$('#t_statusVI30').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[30].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[30].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[30].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[30].damount"]').val('0.0');
		$('#statusVI30').text("");
		$('#t_statusVI30').val("");
	} else {
		$('#statusVI30').text("Declared");
		$('#t_statusVI30').val("Declared");
	}
});




$('[name="taxEmployeeChapter6A[31].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[31].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[31].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[31].damount"]').val('');
		$('#statusVI31').text("");
		$('#t_statusVI31').val("");
	} else {
		$('#statusVI31').text("Declared");
		$('#t_statusVI31').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[31].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[31].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[31].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[31].damount"]').val('0.0');
		$('#statusVI31').text("");
		$('#t_statusVI31').val("");
	} else {
		$('#statusVI31').text("Declared");
		$('#t_statusVI31').val("Declared");
	}
});




$('[name="taxEmployeeChapter6A[32].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[32].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[32].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[32].damount"]').val('');
		$('#statusVI32').text("");
		$('#t_statusVI32').val("");
	} else {
		$('#statusVI32').text("Declared");
		$('#t_statusVI32').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[32].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[32].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[32].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[32].damount"]').val('0.0');
		$('#statusVI32').text("");
		$('#t_statusVI32').val("");
	} else {
		$('#statusVI32').text("Declared");
		$('#t_statusVI32').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[33].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[33].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[33].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[33].damount"]').val('');
		$('#statusVI33').text("");
		$('#t_statusVI33').val("");
	} else {
		$('#statusVI33').text("Declared");
		$('#t_statusVI33').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[33].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[33].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[33].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[33].damount"]').val('0.0');
		$('#statusVI33').text("");
		$('#t_statusVI33').val("");
	} else {
		$('#statusVI33').text("Declared");
		$('#t_statusVI33').val("Declared");
	}
});




$('[name="taxEmployeeChapter6A[34].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[34].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[34].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[34].damount"]').val('');
		$('#statusVI34').text("");
		$('#t_statusVI34').val("");
	} else {
		$('#statusVI34').text("Declared");
		$('#t_statusVI34').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[34].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[34].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[34].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[34].damount"]').val('0.0');
		$('#statusVI34').text("");
		$('#t_statusVI34').val("");
	} else {
		$('#statusVI34').text("Declared");
		$('#t_statusVI34').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[35].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[35].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[35].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[35].damount"]').val('');
		$('#statusVI35').text("");
		$('#t_statusVI35').val("");
	} else {
		$('#statusVI35').text("Declared");
		$('#t_statusVI35').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[35].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[35].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[35].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[35].damount"]').val('0.0');
		$('#statusVI35').text("");
		$('#t_statusVI35').val("");
	} else {
		$('#statusVI35').text("Declared");
		$('#t_statusVI35').val("Declared");
	}
});



$('[name="taxEmployeeChapter6A[36].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[36].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[36].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[36].damount"]').val('');
		$('#statusVI33').text("");
		$('#t_statusVI33').val("");
	} else {
		$('#statusVI33').text("Declared");
		$('#t_statusVI33').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[33].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[36].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[36].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[36].damount"]').val('0.0');
		$('#statusVI36').text("");
		$('#t_statusVI36').val("");
	} else {
		$('#statusVI36').text("Declared");
		$('#t_statusVI36').val("Declared");
	}
});


$('[name="taxEmployeeChapter6A[37].damount"]').on('click', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[37].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[37].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[37].damount"]').val('');
		$('#statusVI37').text("");
		$('#t_statusVI37').val("");
	} else {
		$('#statusVI37').text("Declared");
		$('#t_statusVI37').val("Declared");
	}
}); 
$('[name="taxEmployeeChapter6A[37].damount"]').on('blur', function() {
	//debugger;
	if($('[name="taxEmployeeChapter6A[37].damount"]').val().length == 0 || $('[name="taxEmployeeChapter6A[37].damount"]').val() == 0){
		$('[name="taxEmployeeChapter6A[37].damount"]').val('0.0');
		$('#statusVI37').text("");
		$('#t_statusVI37').val("");
	} else {
		$('#statusVI37').text("Declared");
		$('#t_statusVI37').val("Declared");
	}
});



/*************************** End Chapter 6A Click event and onblur event************/


/*************************** Start Interest on Home Loan and Other Click event and onblur event************/
	

$('[name="person[0].decAmount"]').on('click', function() {
	//debugger;
	if($('[name="person[0].decAmount"]').val().length == 0 || $('[name="person[0].decAmount"]').val() == 0){
		$('[name="person[0].decAmount"]').val('');
		$('#statusHL0').text("");
		$('#t_statusHL0').val("");
	} else {
		$('#statusHL0').text("Declared");
		$('#t_statusHL0').val("Declared");
	}
}); 
$('[name="person[0].decAmount"]').on('blur', function() {
	//debugger;
	if($('[name="person[0].decAmount"]').val().length == 0 || $('[name="person[0].decAmount"]').val() == 0){
		$('[name="person[0].decAmount"]').val('0.0');
		$('#statusHL0').text("");
		$('#t_statusHL0').val("");
	} else {
		$('#statusHL0').text("Declared");
		$('#t_statusHL0').val("Declared");
	}
});



$('[name="person[1].decAmount"]').on('click', function() {
	//debugger;
	if($('[name="person[1].decAmount"]').val().length == 0 || $('[name="person[1].decAmount"]').val() == 0){
		$('[name="person[1].decAmount"]').val('');
		$('#statusHL1').text("");
		$('#t_statusHL1').val("");
	} else {
		$('#statusHL1').text("Declared");
		$('#t_statusHL1').val("Declared");
	}
}); 
$('[name="person[1].decAmount"]').on('blur', function() {
	//debugger;
	if($('[name="person[1].decAmount"]').val().length == 0 || $('[name="person[1].decAmount"]').val() == 0){
		$('[name="person[1].decAmount"]').val('0.0');
		$('#statusHL1').text("");
		$('#t_statusHL1').val("");
	} else {
		$('#statusHL1').text("Declared");
		$('#t_statusHL1').val("Declared");
	}
});


$('[name="person[2].decAmount"]').on('click', function() {
	//debugger;
	if($('[name="person[2].decAmount"]').val().length == 0 || $('[name="person[2].decAmount"]').val() == 0){
		$('[name="person[2].decAmount"]').val('');
		$('#statusHL2').text("");
		$('#t_statusHL2').val("");
	} else {
		$('#statusHL2').text("Declared");
		$('#t_statusHL2').val("Declared");
	}
}); 
$('[name="person[2].decAmount"]').on('blur', function() {
	//debugger;
	if($('[name="person[2].decAmount"]').val().length == 0 || $('[name="person[2].decAmount"]').val() == 0){
		$('[name="person[2].decAmount"]').val('0.0');
		$('#statusHL2').text("");
		$('#t_statusHL2').val("");
	} else {
		$('#statusHL2').text("Declared");
		$('#t_statusHL2').val("Declared");
	}
});


$('[name="person[3].decAmount"]').on('click', function() {
	//debugger;
	if($('[name="person[3].decAmount"]').val().length == 0 || $('[name="person[3].decAmount"]').val() == 0){
		$('[name="person[3].decAmount"]').val('');
		$('#statusHL3').text("");
		$('#t_statusHL3').val("");
	} else {
		$('#statusHL3').text("Declared");
		$('#t_statusHL3').val("Declared");
	}
});

$('[name="person[3].decAmount"]').on('blur', function() {
	//debugger;
	if($('[name="person[3].decAmount"]').val().length == 0 || $('[name="person[3].decAmount"]').val() == 0){
		$('[name="person[3].decAmount"]').val('0.0');
		$('#statusHL3').text("");
		$('#t_statusHL3').val("");
	} else {
		$('#statusHL3').text("Declared");
		$('#t_statusHL3').val("Declared");
	}
});



$('[name="person[4].decAmount"]').on('click', function() {
	//debugger;
	if($('[name="person[4].decAmount"]').val().length == 0 || $('[name="person[4].decAmount"]').val() == 0){
		$('[name="person[4].decAmount"]').val('');
		$('#statusHL4').text("");
		$('#t_statusHL4').val("");
	} else {
		$('#statusHL4').text("Declared");
		$('#t_statusHL4').val("Declared");
	}
});
 
$('[name="person[4].decAmount"]').on('blur', function() {
	//debugger;
	if($('[name="person[4].decAmount"]').val().length == 0 || $('[name="person[4].decAmount"]').val() == 0){
		$('[name="person[4].decAmount"]').val('0.0');
		$('#statusHL4').text("");
		$('#t_statusHL4').val("");
	} else {
		$('#statusHL4').text("Declared");
		$('#t_statusHL4').val("Declared");
	}
});


$('[name="person[5].decAmount"]').on('click', function() {
	//debugger;
	if($('[name="person[5].decAmount"]').val().length == 0 || $('[name="person[5].decAmount"]').val() == 0){
		$('[name="person[5].decAmount"]').val('');
		$('#statusHL5').text("");
		$('#t_statusHL5').val("");
	} else {
		$('#statusHL5').text("Declared");
		$('#t_statusHL5').val("Declared");
	}
}); 
$('[name="person[5].decAmount"]').on('blur', function() {
	//debugger;
	if($('[name="person[5].decAmount"]').val().length == 0 || $('[name="person[5].decAmount"]').val() == 0){
		$('[name="person[5].decAmount"]').val('0.0');
		$('#statusHL5').text("");
		$('#t_statusHL5').val("");
	} else {
		$('#statusHL5').text("Declared");
		$('#t_statusHL5').val("Declared");
	}
});


$('[name="person[6].decAmount"]').on('click', function() {
	//debugger;
	if($('[name="person[6].decAmount"]').val().length == 0 || $('[name="person[6].decAmount"]').val() == 0){
		$('[name="person[6].decAmount"]').val('');
		$('#statusHL6').text("");
		$('#t_statusHL6').val("");
	} else {
		$('#statusHL6').text("Declared");
		$('#t_statusHL6').val("Declared");
	}
}); 
$('[name="person[6].decAmount"]').on('blur', function() {
	//debugger;
	if($('[name="person[6].decAmount"]').val().length == 0 || $('[name="person[6].decAmount"]').val() == 0){
		$('[name="person[6].decAmount"]').val('0.0');
		$('#statusHL6').text("");
		$('#t_statusHL6').val("");
	} else {
		$('#statusHL6').text("Declared");
		$('#t_statusHL6').val("Declared");
	}
});

/*************************** End Interest on Home Loan and Other Click event and onblur event************/

/*************************** Start Previous Employer Income Click event and onblur event************/




$('[name="d_amt_prv_inc"]').on('click', function() {
	//debugger;
	if($('[name="d_amt_prv_inc"]').val().length == 0 || $('[name="d_amt_prv_inc"]').val() == 0){
		$('[name="d_amt_prv_inc"]').val('');
		$('#statusPE0').text("");
		//$('#t_statusHL6').val("");
	} else {
		$('#statusPE0').text("Declared");
		//$('#t_statusHL6').val("Declared");
	}
}); 
$('[name="d_amt_prv_inc"]').on('blur', function() {
	//debugger;
	if($('[name="d_amt_prv_inc"]').val().length == 0 || $('[name="d_amt_prv_inc"]').val() == 0){
		$('[name="d_amt_prv_inc"]').val('0.0');
		$('#statusPE0').text("");
		//$('#t_statusHL6').val("");
	} else {
		$('#statusPE0').text("Declared");
		//$('#t_statusHL6').val("Declared");
	}
});



$('[name="d_amt_prv_tax"]').on('click', function() {
	//debugger;
	if($('[name="d_amt_prv_tax"]').val().length == 0 || $('[name="d_amt_prv_tax"]').val() == 0){
		$('[name="d_amt_prv_tax"]').val('');
		$('#statusPE1').text("");
		//$('#t_statusHL6').val("");
	} else {
		$('#statusPE1').text("Declared");
		//$('#t_statusHL6').val("Declared");
	}
}); 
$('[name="d_amt_prv_tax"]').on('blur', function() {
	//debugger;
	if($('[name="d_amt_prv_tax"]').val().length == 0 || $('[name="d_amt_prv_tax"]').val() == 0){
		$('[name="d_amt_prv_tax"]').val('0.0');
		$('#statusPE1').text("");
		//$('#t_statusHL6').val("");
	} else {
		$('#statusPE1').text("Declared");
		//$('#t_statusHL6').val("Declared");
	}
});


/*************************** End Interest on Home Loan and Other Click event and onblur event************/


$(document).ready(function(){
	/* $("input[name='taxEmployeeChapter6A[7].damount']").attr("readonly", true);
     $("input[name='taxEmployeeChapter6A[7].damount']").css('background-color','#d5d2d2');
     $('#lable7').text("( Value will autopick from payroll. )");
	 $('#lable7').css('font-style', 'italic');
	 $('#fileid7').css('display', 'none');*/

	 $('#lable7').text("( Other than deduction from salary. )");
	 $('#lable7').css('font-style', 'italic');


	 $("input[name='taxEmployeeChapter6A[11].damount']").attr("readonly", true);
     $("input[name='taxEmployeeChapter6A[11].damount']").css('background-color','#d5d2d2');
     $('#lable11').text("( Other than deduction from salary. )");
	 $('#lable11').css('font-style', 'italic');
	 $('#fileid11').css('display', 'none');


     $("input[name='taxEmployeeChapter6A[20].damount']").attr("readonly", true);
     $("input[name='taxEmployeeChapter6A[20].damount']").css('background-color','#d5d2d2');
     $('#lable20').text("( Other than deduction from salary. )");
	 $('#lable20').css('font-style', 'italic');
	 $('#fileid20').css('display', 'none');
   

});