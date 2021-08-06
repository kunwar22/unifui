//alert("hi AOR Search"+pnum);
$(document).on('click').unbind();

$(document).ready(function()
{
	//debugger;
	myFunction();
});

var myVar;

	function myFunction() {
			//debugger;
			document.getElementById("REPORTS_LOADER").style.display = "block";
  			myVar = setTimeout(showPage, 3000);
		}

	function showPage() {
		//debugger;
			  document.getElementById("REPORTS_LOADER").style.display = "none";
			 // document.getElementById("myDiv").style.display = "block";
}

$('#AorCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#div_replace').load(url);
});


$(function() {
	$("#AOR_LIST").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	var AOR_NAME = '';
	var AOR_TYPE = '';
	var AOR_PNUM = pnum;
	
	//alert(AOR_PNUM);

	var jsonUrl = '/aor/searchAor/getAorId';
		
	if($('#AOR_SEARCH').val() != ''){
		//debugger;
		AOR_NAME = $('#Aor_NAME').val();
		AOR_TYPE = $('#Aor_Type').children("option:selected").text();
		AOR_PNUM ;
		//loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#AOR_SEARCH").click(function(){
		//debugger;
		AOR_NAME = $('#Aor_NAME').val();
		AOR_TYPE = $('#Aor_Type').children("option:selected").text();
		AOR_PNUM ;
		loadData1();
		$('#resultSec').css('display', 'block');
	});
		
	function loadData1(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"AOR_NAME": AOR_NAME,
				"AOR_TYPE": AOR_TYPE,
				"AOR_PNUM": AOR_PNUM
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}
	
	function populateDataTable(data){
		$("#AorList").DataTable().clear();
		
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#AorList").dataTable().fnAddData([
					
	                dat.legalemployer,
					dat.location,
					dat.personid,
					dat.personnumber,
					dat.fromdate,
					dat.todate,
					dat.responsibilityname,		
					dat.responsibilitytype,					
					//"<ed rm='/aor/edit/editAreaOfRes/correctAOR/"+dat.aorid+"' class='editAor' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
					'<i class="fa fa-pen" style="cursor: pointer" id="view" aria-hidden="true" onclick="viewdata('+dat.aorid+',\'edit\');"></i>',
					//"<select id=edit"+dat.locationid+" onchange=editFunction("+dat.locationid+",edit"+dat.locationid+")><option disabled selected>Edit</option><option>Correct</option><option>Update</option></select>",	
					]);
			}
		
			$('#resultSec').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}
});


function viewdata(x,m)
{
	$.ajax({
		type:"GET",
		 url :"/aor/edit/editAreaOfRes/correctAOR/"+x,
		success:function(result){
			console.log("Success");
			$('#div_replace').html(result);
		
		}
	});
 
};




$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});






/*************************************************************************/




var updateUrl="";
var effdt="";
var lid="";
function editFunction(leID,editID)
{
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/EditDepartment/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		//alert(selectObject);
	////debugger;
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		////debugger;
		
	updateUrl="/enterprisesetup/edit/EditDepartment/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_DEPT_EFFDT').val();
updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);

});


$('#btnerrorOK').on('click', function(e){
	var urll = $(this).attr("rm");
	$('#replace_div').load(urll);
	});




/************************************************************************/









