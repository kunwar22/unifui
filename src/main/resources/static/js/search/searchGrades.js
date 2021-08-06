var createdPlanId = 0;

$('#createGrade').click(function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});



function sendHireData(cmd)
{
	
	//alert(cmd);
	document.getElementById("cmd").value=cmd;
	$.ajax({
		type: 'POST',
		url: '../enterprisesetup/nextandprev',
		data: $('#GRADE_DETAILS').serialize(),
		contentType:"application/x-www-form-urlencoded",
		processData:false,
		catch:false,
		success: function(response){
			//alert(response);
			$('#FRAG').html(response);
			
		},
		error: function(e){
			alert(e);
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
});

}


var x,tablinks;
x=document.getElementsByClassName("grade");
tablinks=document.getElementsByClassName("tablink");



function openTab2(evt,hireEmp){
	
	x[0].style.display="none";
	
	document.getElementById(hireEmp).style.display="block";
	tablinks[0].className="w3-bar-item w3-button tablink";
	tablinks[1].className="w3-bar-item w3-button tablink w3-theme";
}

function openTab1(evt,hireEmp){
	
	x[1].style.display="none";
	
	document.getElementById(hireEmp).style.display="block";
	tablinks[1].className="w3-bar-item w3-button tablink";
	tablinks[0].className="w3-bar-item w3-button tablink w3-theme";
}



$("#ADD_ROW_BTN").click(function(){
	
	$("#GRADE_STEP_TBL").each(function(){
		
		var tds='<tr>';
		jQuery.each($('tr:last td',this),function(){
			tds+='<td>'+$(this).html()+'</td>';
		});
		tds+='</tr>';
		if($('tbody',this).length>0){
			$('tbody',this).append(tds);
		}else{
			$(this).append(tds);
		}
		
	});
});


var effdt_disp = '';
var gradeset_disp = '';

$("#GRADE_NEXT").click(function(){
	
	gradeset_disp=$("#CR_GRADE_SET option:selected").text();	
	effdt_disp = $('#CR_GRADE_EFFDT').val();
	
	$('input#CR_GRADE_EFFDT_DISP').val(effdt_disp);
	$('input#CR_GRADE_SET_DISP').val(gradeset_disp);
});



function addRowNowGRADE()
{
	var table=document.getElementById("GRADE_STEP_TBL");
	var row=table.insertRow(table.rows.length);
	
	
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);
	
	cell0.innerHTML = "<input class='w3-input w3-border' name='gradesteps["+(table.rows.length-2)+"].stepno' type='text'/>";
	cell1.innerHTML = "<input class='w3-input w3-border' name='gradesteps["+(table.rows.length-2)+"].stepname' type='text'/>";
	cell2.innerHTML = "<input class='w3-input w3-border' name='gradesteps["+(table.rows.length-2)+"].stepamount' type='text'/>";
	cell3.innerHTML = "<input class='w3-btn  w3-theme' type='button' value='+' onclick='addRowNowGRADE();'/>";
	cell4.innerHTML = "<input class='w3-btn  w3-theme' type='button' value='x' onclick='delThisRowGRADE();'/>";
}

function delThisRowGRADE()
{
	var table=document.getElementById("GRADE_STEP_TBL");
	
	for(var i=1; i<table.rows.length; i++)
	{
		table.rows[i].cells[4].onclick = function(){
			if(table.rows.length > 2)
			{
				table.deleteRow(this.parentElement.rowIndex);
			}
			else
			{
				table.rows[this.parentElement.rowIndex].cells[0].innerHTML = "<input  class='w3-input w3-border' name='gradesteps[0].stepno' type='text'/>";
				table.rows[this.parentElement.rowIndex].cells[1].innerHTML = "<input  class='w3-input w3-border' name='gradesteps[0].stepname' type='text'/>";
				table.rows[this.parentElement.rowIndex].cells[2].innerHTML = "<input class='w3-input w3-border' name='gradesteps[0].stepamount' type='text'/>";
			}
		}
	}
}


function ajaxPost()
{
//debugger;
	dynamicNameAttributeGrade();
	
	var fd = $("#GRADE_DETAILS").serialize();
	
	$.ajax({
		url: "/enterprisesetup/saveGrade",
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
	var table=document.getElementById("GRADE_STEP_TBL");
	var rCount = table.rows.length;
	for(var i=1; i<rCount; i++)
	{
		table.rows[i].cells[0].children[0].setAttribute('name','gradesteps['+(i-1)+'].stepno');
		table.rows[i].cells[1].children[0].setAttribute('name','gradesteps['+(i-1)+'].stepname');
	}
}

/*****************************SEARCH GRADE START HERE***********************/

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

/******************************************************************************************************/

