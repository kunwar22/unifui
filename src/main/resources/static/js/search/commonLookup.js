
function addRowNowLOOKUPCODES()
{	var row=["<input   name='lookup' type='text'/>",
	"<input   name='lookupcode' type='text'/>",
	"<input   name='status' type='text'/>",
	"<input   name='effectstartdate' type='text'/>",
	"<input   name='lookuptypedescription' type='text'/>"];
	
	var table = $('#LOOKUP_CODES_TBL').DataTable();
	table.row.add(row).draw(false);
	table.order([1, 'desc']).draw();
	$('#SUBMIT_BTN').css('display', 'block');
	$('#ADDROW_BTN').css('display', 'none');	
	$('#ADD_LABEL').css('display', 'none');		
}
/*****************************SEARCH START HERE***********************/

var jsonUrlLookup = '/enterprisesetup/lookupSearch';



var lookuptype = '';
var lookupdescr = '';
var lookuptypeselected='';

$('#lookupresult').css('display', 'none');
$('#lookupcodes').css('display', 'none');
			

$("#LOOKUP_BTN").on('click', function(e){

	
	lookupdescr = $('#LOOKUP_DESCR').val();
	lookuptype =  $('#LOOKUP_TYPE').val();
	
	loadLookupData();
	$('#lookupcodes').css('display', 'none');
	$('#SUBMIT_BTN').css('display', 'none');
	$('#ADDROW_BTN').css('display', 'inline');	
	$('#ADD_LABEL').css('display', 'inline');	
	
});

function loadLookupData(){
	$.ajax({
		type: 'POST',
		url: jsonUrlLookup,
		dataSrc: '',
		data: {
			"lookuptype": lookuptype,
			"lookuptypedescription": lookupdescr
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			////debugger;
			//alert(jsonData);
			populateLookupDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function populateLookupDataTable(data){
	$("#SEARCH_RESULT_TBL").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#lookupresult').css('display', 'none');
		$('#noDataLookup').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#SEARCH_RESULT_TBL").dataTable().fnAddData([
				dat.lookuptype,
				dat.lookuptypedescription
			]);
			
		}
		$('#lookupresult').css('display', 'block');
		$('#noDataLookup').css('display', 'none');
	}
}

function loadLookupCodeData(){
			//alert(lookuptypeselected);
			var jsonUrlLookupcode = '/enterprisesetup/lookupCodes/'+lookuptypeselected;
			$("#LOOKUP_TYPE_INPUT").val(lookuptypeselected);
			
			
			$.ajax({
			type: 'GET',
			url: jsonUrlLookupcode,
			dataSrc: '',
		
			dataType: 'json',
			success: function(data){
			jsonData = data;
			populateLookupCodeTable(jsonData);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
			});
}


function populateLookupCodeTable(data){
	$("#LOOKUP_CODES_TBL").DataTable().clear();
	var dataLength = Object.keys(data).length;

	if(dataLength == 0){
		$('#lookupcodes').css('display', 'none');
		$('#noDataLookupCodes').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#LOOKUP_CODES_TBL").dataTable().fnAddData([
			 	dat.lookup,
				dat.lookupcode,
				dat.status,
				dat.effectstartdate,
				dat.lookuptypedescription,
				"<ed rm='/enterprisesetup/getLookupById/"+dat.id+"' style='cursor: pointer;' ><i class='fa fa-edit' aria-hidden='true'></i></ed>"
			]);
			
		}
		$('#lookupcodes').css('display', 'block');
		$('#noDataLookupCodes').css('display', 'none');
	}
}

/*$("#temp_btn").on('click', function(e){

	$('#EDIT_POPUP').css("display","block");
	
});*/

$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var urlrowedit = $(this).attr("rm");
	//alert("|"+urlrowedit+"|");
	$.ajax({
			type: 'GET',
			url: urlrowedit,
			dataSrc: '',
		
			dataType: 'json',
			success: function(data){
			jsonData = data;
			//alert(jsonData.toString());
			$('#EDIT_POPUP').css("display","block");
			$('#POP_DATE').val(jsonData.effectstartdate);
			$('#POP_DESCR').val(jsonData.lookuptypedescription);
			$('#POP_LOOKUPCODE').val(jsonData.lookupcode);
			$('#POP_LOOKUP').val(jsonData.lookup);
			$('#POP_ID').val(jsonData.id);
			$('#POP_HEAD').text('Edit '+jsonData.lookuptype);
			$('#POP_STAT').children('option[id="1"]').text(jsonData.status);
			$('#POP_STAT').children('option[id="1"]').val(jsonData.status);
			$('#POP_STAT').children('option[id="1"]').prop('selected',true);	
			
			
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
			});
	
	
});
$("#POP_LOOKUP_CANCEL").on('click', function(e){

	$('#EDIT_POPUP').css('display', 'none');
	
});



/*******************************Popup table selected row start************************/


	$(document).ready(function(){
	
		var table=$('#SEARCH_RESULT_TBL').DataTable();
		
		$('#SEARCH_RESULT_TBL tbody').on('click','tr',function(){
			
			//alert("ravi");
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
			lookuptypeselected=tbldata[0];	
			//alert('SELECTED LOOKUPTYPE :::: '+lookuptypeselected);
			loadLookupCodeData();
			});
		
	});
	
/*******************************Popup table selected row END****************************/

/*******************************SAVE LOOKUP START***************************************/


function saveLookup()
{
////debugger;
//dynamicNameAttributeGrade();
	
	var fd = $("#LOOKUP_SAVE").serialize();
	
	$.ajax({
		url: "/enterprisesetup/saveLookup",
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
	$('#lookupresult').css('display', 'none');
	$('#lookupcodes').css('display', 'none');
}

/*******************************SAVE LOOKUP END***************************************/
/*******************************EDIT LOOKUP START*************************************/

function editLookup(){
////debugger;
//dynamicNameAttributeGrade();
	
	var fd = $("#POPUP_EDIT_FORM").serialize();
	
	$.ajax({
		url: "/enterprisesetup/editLookup",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			/*alert(result);
			createdPlanId = result.match(/(\d+)/)[0];
			alert(createdPlanId);*/
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});
	
	$('#EDIT_POPUP').css('display', 'none');
	var temp=$('#POP_HEAD').text();
	lookuptypeselected=temp.substring(5,temp.legth);
	loadLookupCodeData();
}
/*******************************EDIT LOOKUP END***************************************/

	