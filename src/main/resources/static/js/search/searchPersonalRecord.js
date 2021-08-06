
$(document).on('click').unbind();
$('#AorCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});






	$("#PersonalRecordList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	
	
	
	var PERSON_DEPT = '';
	var PERSON_ID = '';
	var PERSON_LEGAL_ENTITY = '';
	var PERSON_NAME = '';
	var PERSON_NUMBER = '';
	
	var jsonUrl = '/personmanage/searchPersonalRecord/getpersonalRecordId';



function handleKeyPress(e) {

	var key = e.keyCode || e.which;
	if (key == 13) {
		PERSON_DEPT = $('#PERSON_DEPT').val();
		/*PERSON_ID = $('#PERSON_ID').val();*/
		PERSON_LEGAL_ENTITY = $('#PERSON_LEGAL_ENTITY').val();
		PERSON_NAME = $('#PERSON_NAME').val();
		PERSON_NUMBER = $('#PERSON_NUMBER').val();
		//alert("hi "+DEPT_NAME+" "+DEPT_CODE+" "+DEPT_DATASET+" "+DEPT_STATUS+" END here");
		loadData();
		$('#PersonalRecordresultSec').css('display', 'none');
		$('#noData').css('display', 'none');
		$('#jsonLoaderPage').css('display', 'block');

	}
}
	
	$("#Personal_SEARCH").click(function(){
		PERSON_DEPT = $('#PERSON_DEPT').val();
		/*PERSON_ID = $('#PERSON_ID').val();*/
		PERSON_LEGAL_ENTITY = $('#PERSON_LEGAL_ENTITY').val();
		PERSON_NAME = $('#PERSON_NAME').val();
		PERSON_NUMBER = $('#PERSON_NUMBER').val();
		//alert("hi "+DEPT_NAME+" "+DEPT_CODE+" "+DEPT_DATASET+" "+DEPT_STATUS+" END here");
		loadData();
		$('#PersonalRecordresultSec').css('display', 'none');
		$('#noData').css('display', 'none');
		$('#jsonLoaderPage').css('display', 'block');
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"PERSON_DEPT": PERSON_DEPT,
				/*"PERSON_ID": PERSON_ID,*/
				"PERSON_NAME": PERSON_NAME,
				"PERSON_LEGAL_ENTITY": PERSON_LEGAL_ENTITY,
				"PERSON_NUMBER": PERSON_NUMBER
			},
			dataType: 'json',
			success: function(data){
				jsonData = data;
				populateDataTable(jsonData);
				$('#jsonLoaderPage').css('display', 'none');
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}
	
	function populateDataTable(data){
		$("#PersonalRecordList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#PersonalRecordresultSec').css('display', 'none');
			$('#noData').css('display', 'block');
			$('#jsonLoaderPage').css('display', 'none');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#PersonalRecordList").dataTable().fnAddData([
					"<ed rm='/personedit/employeedetail/"+dat.personnumber+"/"+dat.personname.replace(/ /g,"_")+"/"+dat.personid+"' style='cursor: pointer;color:blue;'>"+dat.personname+"</ed>",
					dat.personnumber,
					dat.personlocation,
					dat.persondepartment,
					dat.personemail,
					dat.personlegalentity
							
				//	"<ed rm='/enterprisesetup/edit/EditDepartment/"+dat.personid+"/"dat.personid"' class='editpersonid' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
					
					]);
			}
			$('#PersonalRecordresultSec').css('display', 'block');
			$('#noData').css('display', 'none');
			$('#jsonLoaderPage').css('display', 'none');
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
		$('#idUD').css("display","none");
		var correctUrl="/enterprisesetup/edit/EditDepartment/"+leID+"/1900-01-01";
		
		lid=leID;
		var selectObject=$(editID).children("option:selected").val();
		//alert(selectObject);
	//debugger;
		if(selectObject=='Correct'){
			
			$("#replace_div").load(correctUrl);
		}
		else if(selectObject=='Update'){
		$('#idUD').css("display","block");
		//debugger;
		
	updateUrl="/enterprisesetup/edit/EditDepartment/"+leID+"/";
	
		
		}
}

$('#btnOK').on('click', function(e){
effdt=$('#CR_DEPT_EFFDT').val();

updateUrl=updateUrl+effdt;
//alert(updateUrl);
$("#replace_div").load(updateUrl);
	//	editLegData(updateUrl,);
});


	$('#btnerrorOK').on('click', function(e){
		//debugger;
		//alert("sd");
		var urll = $(this).attr("rm");
		$('#replace_div').load(urll);
	//alert(data.message);


		});




/************************************************************************/









