$('#createGradeRate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#commonLookup').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#GradeRAteCancel').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

/*
$('#ESI').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#PF').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#PRAN').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});

$('#UAN').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});*/

var effdt_start = '';
var effdt_end = '';
var view_on=false;




function addRowNowGRADE()
{
	var table=document.getElementById("GRADE_RATE_VALUE_TBL");
	var row=table.insertRow(table.rows.length);
	
	
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	var cell5 = row.insertCell(5);
	var cell6 = row.insertCell(6);
	var cell7 = row.insertCell(7);
	
	cell0.innerHTML = "<select  class='w3-select w3-border gradeSearchLOV' id='graderateselect"+(table.rows.length-2)+"' onchange='popoupSelect(graderateselect"+(table.rows.length-2)+")' name='graderatevalue["+(table.rows.length-2)+"].gradesid' data-toggle='modal'> <option id='1' value='1' disabled selected></option> <option id='2' value='2'></option> <option value='search' class='btnsearch2' data-toggle='modal' id='btnsearch2'>Search....</option> </select>";
	cell1.innerHTML = "<input  class='w3-input w3-border' name='graderatevalue["+(table.rows.length-2)+"].min' type='text'/>";
	cell2.innerHTML = "<input  class='w3-input w3-border' name='graderatevalue["+(table.rows.length-2)+"].max' type='text'/>";
	cell3.innerHTML = "<input  class='w3-input w3-border' name='graderatevalue["+(table.rows.length-2)+"].midvalue' type='text'/>";
	cell4.innerHTML = "<input class='w3-btn  w3-theme' type='button' value='+' onclick='addRowNowGRADE();'/>";
	cell5.innerHTML = "<input class='w3-btn  w3-theme' type='button' value='x' onclick='delThisRowGRADE();'/>";
	cell6.innerHTML ="<input value='' style='display:none' id='ENDDATE"+(table.rows.length-2)+"' class='w3-input w3-border' name='graderatevalue["+(table.rows.length-2)+"].effectenddate'  type='date' data-date='' data-date-format='YYYY MM DD' readonly />";
	cell7.innerHTML ="<input value='' style='display:none' id='STARTDATE"+(table.rows.length-2)+"' class='w3-input w3-border' name='graderatevalue["+(table.rows.length-2)+"].effectstartdate'  type='date' data-date='' data-date-format='YYYY MM DD' readonly />";
	
	
	$('input#ENDDATE'+(table.rows.length-2)+'').val($('#CR_GRADE_EFFDT_END').val());
	$('input#STARTDATE'+(table.rows.length-2)+'').val($('#CR_GRADE_EFFDT_START').val());
	
}

function delThisRowGRADE()
{
	var table=document.getElementById("GRADE_RATE_VALUE_TBL");
	
	for(var i=1; i<table.rows.length; i++)
	{
		table.rows[i].cells[5].onclick = function(){
			if(table.rows.length > 2)
			{
				table.deleteRow(this.parentElement.rowIndex);
			}
			else
			{
				cell0.innerHTML = "<select  class='w3-select w3-border gradeSearchLOV' name='graderatevalue[0].gradesid' data-toggle='modal'> <option id='1' value='1' disabled selected></option> <option id='2' value='2'></option> <option value='search' data-toggle='modal' class='btnsearch2' id='btnsearch2'>Search....</option> </select>";
				cell1.innerHTML = "<input  class='w3-input w3-border' name='graderatevalue[0].min' type='text'/>";
				cell2.innerHTML = "<input  class='w3-input w3-border' name='graderatevalue[0].max' type='text'/>";
				cell3.innerHTML = "<input  class='w3-input w3-border' name='graderatevalue[0].midvalue' type='text'/>";
				cell6.innerHTML ="<input value='' id='ENDDATE' class='w3-input w3-border' name='graderatevalue[0].effectenddate'  type='date' data-date='' data-date-format='YYYY MM DD' readonly />";
				cell7.innerHTML ="<input value='' id='STARTDate' class='w3-input w3-border' name='graderatevalue[0].effectstartdate'  type='date' data-date='' data-date-format='YYYY MM DD' readonly />";
			}
		}
	}
}


function ajaxPost()
{
	dynamicNameAttributeGrade();
	
	var fd = $("#GRADE_RATE").serialize();
	
	$.ajax({
		url: "/enterprisesetup/saveGradeRate",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			alert(result);
			createdPlanId = result.match(/(\d+)/)[0];
			alert(createdPlanId);
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});
};


function dynamicNameAttributeGrade()
{
	var table=document.getElementById("GRADE_RATE_VALUE_TBL");
	var rCount = table.rows.length;
	for(var i=1; i<rCount; i++)
	{
		table.rows[i].cells[0].children[0].setAttribute('name','graderatevalue['+(i-1)+'].gradesid');
		table.rows[i].cells[1].children[0].setAttribute('name','graderatevalue['+(i-1)+'].min');
		table.rows[i].cells[2].children[0].setAttribute('name','graderatevalue['+(i-1)+'].max');
		table.rows[i].cells[3].children[0].setAttribute('name','graderatevalue['+(i-1)+'].midvalue');
		table.rows[i].cells[6].children[0].setAttribute('name','graderatevalue['+(i-1)+'].effectenddate');
		table.rows[i].cells[7].children[0].setAttribute('name','graderatevalue['+(i-1)+'].effectstartdate');
	}
}



/**************************************POPUP GRADE SEARCH START********************************************/




	var jsonUrlGrade = '/enterprisesetup/searchGrade';



	var searchdsCode = '';
	var searchdsName = '';
	var searchstatus='';
	
	$("#GRADE_SEARCH").on('click', function(e){
		
		searchdsCode =  $("#SRCH_CODE").val();
		searchdsName = $("#SRCH_NAME").val();
		searchstatus=$('#statusList').children("option:selected").val();
	
		loadGradeData()
		
	});
	function loadGradeData(){
		$.ajax({
			type: 'POST',
			url: jsonUrlGrade,
			dataSrc: '',
			data: {
				"code": searchdsCode,
				"name": searchdsName,
				"statusGrade":searchstatus
				
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				
				populateGradeDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}

	function populateGradeDataTable(data){
		$("#GRADE_LIST").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#graderesultSec').css('display', 'none');
			$('#noDataGrade').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#GRADE_LIST").dataTable().fnAddData([
					dat.name,
					dat.code,
					dat.status,
					dat.datasets,
					dat.gradesid
				]);
			}
			$('#graderesultSec').css('display', 'block');
			$('#noDataGrade').css('display', 'none');
		}
	}
	
	/*******************************Popup table selected row start*******************************************/
	
	
	
	
var gradeID="";
	
	function popoupSelect(id)
	{
		
		var selectObject=$(id).children("option:selected").val();
		//alert(selectObject);
		if(selectObject=='search'){
			$('#graderesultSec').css('display', 'none');
			$('#id01').css("display","block");
		
	
		}
		else if(selectObject!='search'){
			$('#id01').css("display","none");
			
		}
		gradeID=id;
		
	}

	

$(document).ready(function(){
	
	
	
	
	$('#BU_POP_OK').on('click', function(e){
		$('#id01').css("display","none");
		
	});
	
	
	var table=$('#GRADE_LIST').DataTable();
	
	$('#GRADE_LIST tbody').on('click','tr',function(){
		var tbldata=$(this).children('td').map(function(){
			return $(this).text();
			
		}).get();	
		
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			
		}
		else{
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		var dtData=tbldata[0];
		var dtDataId=tbldata[4];
		$('#'+gradeID.id).children('option[id="2"]').text(dtData);
		$('#'+gradeID.id).children('option[id="2"]').val(dtDataId);
		$('#'+gradeID.id).children('option[id="2"]').prop('selected',true);		
		
	});
	
});


/*******************************Popup table selected row END*******************************************/

/**************************************POPUP GRADE SEARCH END********************************************/

/************************************** GRADERATE SEARCH START********************************************/




var jsonUrlGradeRate = '/enterprisesetup/searchGradeRate';



var searchGRADERATERatetype = '';
var searchGRADERATEName = '';
var searchGRADERATEstatus='';

$("#GRADERATE_SEARCH").on('click', function(e){
	
	searchGRADERATERatetype =$("#SRCH_RATETYPE").children("option:selected").val();
	searchGRADERATEName = $("#SRCH_NAME1").val();
	searchGRADERATEstatus=$("#statusListGRADERATE").children("option:selected").val();

	loadGradeRATEData()
	
});
function loadGradeRATEData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlGradeRate,
		dataSrc: '',
		data: {
			"name": searchGRADERATEName,
			"ratetype": searchGRADERATERatetype,
			"status":searchGRADERATEstatus
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			
			populateGradeRateDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateGradeRateDataTable(data){
	$("#GRADERATE_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#graderateresultSec').css('display', 'none');
		$('#noDataGradeRate').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#GRADERATE_LIST").dataTable().fnAddData([
				dat.name,
				dat.effectstartdate.substring(0,10),
				dat.ratetype,
				dat.ratefrequency,
				dat.currancycode,
				dat.status,
				dat.graderateid
			]);
		}
		$('#graderateresultSec').css('display', 'block');
		$('#noDataGradeRate').css('display', 'none');
	}
}



/************************************** GRADERATE SEARCH START********************************************/

