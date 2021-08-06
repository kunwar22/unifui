//bootbox.alert("Hello...");

$( document ).ready(function() {
	if (openmode=="BCK"){
		$("#AUTO_INCR_LOADER").css("display","block");
		searchemplbck();
	}
});


function checkuncheckAll(){
	var inputs = $(".elemCheck");
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

function searchempl(){
	$("#AUTO_INCR_LOADER").css("display","block");
	var natempl=$('#ASGN_NATEMPL option:selected').val();
	var bu=$('#BULOV option:selected').val();
	var monthh=$('#MONTHLOV option:selected').val();
	var yearr=$('#YEARLOV option:selected').val();
	var aurl="/scheduleprocess/searincrempl/"+bu+"/"+natempl+"/"+monthh+"/"+yearr;
	$.ajax({
		type: 'GET',
		url: aurl,
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateincrempl(result);
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
}

function searchemplbck(){
	$("#AUTO_INCR_LOADER").css("display","block");
	var aurl="/scheduleprocess/searchemplbck";
	$.ajax({
		type: 'GET',
		url: aurl,
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateincremplbck(result);
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
}
/*$("#ASSIGN_SEARCH_RES").DataTable({
	'columnDefs': [{
		'targets': [2,3],
		'orderable': false
	}]
});*/

$("#INCR_RES").DataTable({
	'paging': false,
	'ordering':false
});

function populateincrempl(data){
	$("#INCR_RES").DataTable().clear();
	var dataLength = data.length;

	const datearr = ["-01-01", "-02-01", "-03-01", "-04-01", "-05-01", "-06-01",
		"-07-01", "-08-01", "-09-01", "-10-01", "-11-01", "-12-01"
	];
	var yearr=new Date().getFullYear();
	var monthh=new Date().getMonth();
	var datee= yearr+datearr[monthh];

	if(dataLength == 0){
		$("#INCR_SUBMIT_BTN").css("display", "none");
		$("#AUTO_INCR_LOADER").css("display","none");
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		
	} else {
		$("#INCR_SUBMIT_BTN").css("display", "block");
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#INCR_RES").dataTable().fnAddData([
       			'<input class="elemCheck" onclick="" type="checkbox" class="w3-check" name="employeeAutoIncrements[' + i + '].flag" value="on"></td>',
       			dat.personnumber+'<input type="hidden" name="employeeAutoIncrements[' + i + '].personnumber" value="'+dat.personnumber+'">',
				dat.personname,
				dat.departments,
				dat.designation,
				dat.lastincrementeffectivedate,
				dat.gradename,
				dat.rop
			]);
		}
		$("#AUTO_INCR_LOADER").css("display","none");
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}

function populateincremplbck(data){
	$("#INCR_RES").DataTable().clear();
	var dataLength = data.length;

	if(dataLength == 0){
		$("#INCR_SUBMIT_BTN").css("display", "none");
		$("#AUTO_INCR_LOADER").css("display","none");
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		$("#INCR_SUBMIT_BTN").css("display", "block");
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			var abcd='';
			if(dat.flag=="on"){
				abcd='<input class="elemCheck" onclick="" type="checkbox" class="w3-check" name="employeeAutoIncrements[' + i + '].flag" value="on" checked></td>';
			}else{
				abcd='<input class="elemCheck" onclick="" type="checkbox" class="w3-check" name="employeeAutoIncrements[' + i + '].flag" value="on"></td>';
			}
			$("#INCR_RES").dataTable().fnAddData([
				abcd,
				dat.personnumber+'<input type="hidden" name="employeeAutoIncrements[' + i + '].personnumber" value="'+dat.personnumber+'">',
				dat.personname,
				dat.departments,
				dat.designation,
				dat.lastincrementeffectivedate,
				dat.gradename,
				dat.rop
			]);
		}
		$("#AUTO_INCR_LOADER").css("display","none");
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}



 	/*function hirechange() {
		$("#INCR_SEARCH_BTN").prop('disabled', false);
	}*/


	function saveautoincr() {
		var autoincrformdata = $('#autoincrform').serialize();
		$.ajax({
			url: "/scheduleprocess/saveautoincr",
			type: "POST",
			data: autoincrformdata,
			cache: false,
			contentType: "application/x-www-form-urlencoded",
			processData: false,
			success: function (result) {
				$('#replace_div').html(result);
			},
			error: function (response) {
				alert(JSON.parse(response.responseText));
			}
		});
	}

				
/*				
$(document).ready(function() {
	//debugger;
	dateAccuraltxt = $('#remtype').children("option:selected").val();
	alert("hi"+dateAccuraltxt);
		if (dateAccuraltxt==16 ){
		
     $('#remtype').children('option[value="16"]').css('display','none');
  } else {
	alert("nothing");
  }

  });
				
*/

/*
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
});*/
