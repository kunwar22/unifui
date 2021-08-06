//alert("hi Reimbursement Search");
$(document).on('click').unbind();


$('#RembTypeCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});





//alert("0");



	var REMB_NAME = '';
	
	var REMB_STATUS = '';

	var jsonUrl = '/reimbursementType/seach/Rembursement/searchRembursement';
		
	if($('#REMB_TYPE_SEARCH').val() != ''){
		//alert("1");
		REMB_NAME = $('#REMB_TYPE_NAME').val();
		REMB_STATUS = $('#REMB_TYPE_STATS').val();
		loadData1();
		$('#REMB_TYPE_resultSec').css('display', 'block');
	}
	
	$("#REMB_TYPE_SEARCH").click(function(){
		//alert("2");
		REMB_NAME = $('#REMB_TYPE_NAME').val();
		REMB_STATUS = $('#REMB_TYPE_STATS').val();
		loadData1();
		$('#REMB_TYPE_resultSec').css('display', 'block');
	});
		
	function loadData1(){
		//alert("3");
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"REMB_NAME": REMB_NAME,
				"REMB_STATUS": REMB_STATUS
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateRembDataTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}
	
	function populateRembDataTable(data){
		//alert("4");
		$("#REMB_TYPE_List").DataTable().clear();
		
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			//alert("5");
			$('#REMB_TYPE_resultSec').css('display', 'none');
			$('#REMB_TYPE_noData').css('display', 'block');
		} else {
			//alert("6");
			
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#REMB_TYPE_List").dataTable().fnAddData([						 
				     dat.rembursementname,
				     dat.effectivestartdate.substring(0,10),				    
				     dat.status, 	
					 dat.rembursementtypeid,			
					
					//"<select id=edit"+dat.rembursementtypeid+" onchange=editFunction("+dat.rembursementtypeid+",edit"+dat.rembursementtypeid+")><option hidden disabled selected>Edit</option><option>Correct</option><option>Update</option></select>"		
					"<select id=edit"+dat.rembursementtypeid+" onchange=editFunction("+dat.rembursementtypeid+",edit"+dat.rembursementtypeid+")><option hidden disabled selected>Edit</option><option >Correct</option></select>"	
					]);
			}
		
			$('#REMB_TYPE_resultSec').css('display', 'block');
			$('#REMB_TYPE_noData').css('display', 'none');
		}
	}





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
	//alert("in");
	//alert("leID"+leID);
	//alert("editID"+editID);
	
		$('#idUD').css("display","none");
		var correctUrl="/reimbursementType/edit/editReimbursement/correctReimbursement/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		
	updateUrl="/reimbursementType/edit/editReimbursement/correctReimbursement/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_REMB_TYPE_START_DT').val();

updateUrl=updateUrl+effdt;
$("#replace_div").load(updateUrl);
});


	$('#btnerrorOK').on('click', function(e){
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);
		});




/************************************************************************/









