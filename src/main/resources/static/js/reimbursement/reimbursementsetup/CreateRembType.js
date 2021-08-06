//alert("hi remb_type");

$('#Remb_type_Cancel').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


/**************************************POPUP BU SEARCH START********************************************/

var searchName = '';
var searchCode = '';
var jsonUrl = '/absencesetup/seach/Eligibility/searchEligibility';

//var hiredate='';
function lwpsearch(){
	searchName = $('#REMB_TYPE_ELIG_NAME').val();
	searchCode = $('#REMB_TYPE_ELIG_CODE').val();
	
	
	//hiredate = $("#HIRE_DATE").val();
//alert(hiredate);
	loadPopupTableData();
}

function loadPopupTableData(){
			
			$.ajax({
				type: 'POST',
				url: jsonUrl,
				dataSrc: '',
				data: {
					"name": searchName,
					"code": searchCode
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
	$("#REMB_TYPE_LIST").DataTable().clear();
			var dataLength = Object.keys(data).length;
			if(dataLength == 0){
				$('#REMB_TYPE_resultSec').css('display', 'none');
				$('#noDataREMB_TYPE').css('display', 'block');
			} else {
				for(var i=0; i < dataLength; i++){
					var dat = data[i];
					$("#REMB_TYPE_LIST").dataTable().fnAddData([						
						dat.eligibilityid,
						dat.eligibilityname,
						dat.eligibilitydescription	
						
					]);
					
						
					
				}
				$('#REMB_TYPE_resultSec').css('display', 'block');
				$('#noDataREMB_TYPE').css('display', 'none');
			}
		}

$(document).ready(function(){
$('#REMB_TYPE_POP_CANCEL').on('click', function(e){
	$('#id0Manager').css("display","none");
	$('#MANAGERS_LOV').children('option[id="1"]').prop('selected',true);
	$('#REMB_TYPE_resultSec').css('display', 'none');
});

$('#REMB_TYPE_POP_OK').on('click', function(e){
	//debugger;
	$('#REMB_TYPE_resultSec').css('display', 'none');
	$('#id0Manager').css("display","none");
	//alert("Data Set ID :::::: "+datasetId+"  BU ID:::: "+buId);



});

var table=$('#REMB_TYPE_LIST').DataTable();

$('#REMB_TYPE_LIST tbody').on('click','tr',function(){
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
	///var mgr_id=$(this).children('select').attr('id');
	//alert(MGR_ID);
	var dtData=tbldata[1];
	var dtDataId=tbldata[0];
	$('#MGR'+MGR_ID).val(dtData);
	$('#MGR'+MGR_ID).children('option[id="2"]').text(dtData);
	$('#MGR'+MGR_ID).children('option[id="2"]').val(dtDataId);
	$('#MGR'+MGR_ID).children('option[id="2"]').prop('selected',true);
	$('#MNGR_ID'+MGR_ID).val(dtDataId);

});

});


$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


/**************************************POPUP BU SEARCH END********************************************/



/************************POPUP MANAGER TABLE SELECTED ROW START***********************/

var MGR_ID='';

function mgrchange(mgrid) {
	//debugger;
	//$('#id03').css("display","none");
	MGR_ID=mgrid;
	var selectObject = $("#MGR"+mgrid).children("option:selected").val();
	if (selectObject == 'search') {
		$('#id0Manager').css("display", "block");

	} else if (selectObject != 'search') {
		$('#id0Manager').css("display", "none");
	}
}

/************************************************************************************* */




//$(document).unbind();
function addrowmanager(){
	////debugger;
	var data="";

	//data='<tr><td style="width:25%"><select id="MGR' + window.globalCounter + '" onchange="mgrchange(' + window.globalCounter + ')" class="w3-select w3-border" name="createlwp[' + window.globalCounter + '].personnumber"><option id="1" value="" selected disabled></option><option id="2" value="" selected ></option><option value="search" data-toggle="modal" id="btnsearchLegal">Search....</option></select></td><td style="width:30%;"><input class="w3-input w3-border"  name="createlwp['+window.globalCounter+'].lwp" id="LWP['+window.globalCounter+']" type="text"></td><td style="width:30%;"><input class="w3-input w3-border"  name="createlwp['+window.globalCounter+'].lwp" id="LWP['+window.globalCounter+']" type="text"></td>    <td style="width:5%"><input class="w3-btn w3-theme" id="deletemgr"  type="button" value="x"/></td></tr>';
	data='<tr><td style="width:25%"><select id="MGR' + window.globalCounter + '" onchange="mgrchange(' + window.globalCounter + ')" class="w3-select w3-border" name="rembursementceilinglimit[' + window.globalCounter + '].eligibilityid"><option id="1" value="" selected disabled></option><option id="2" value="" selected ></option><option value="search" data-toggle="modal" id="btnsearchLegal">Search....</option></select></td><td style="width:30%;"><input class="w3-input w3-border"  name="rembursementceilinglimit['+window.globalCounter+'].cielingmin" id="rembursementceilinglimit['+window.globalCounter+'].cielingmin" type="text"></td><td style="width:30%;"><input class="w3-input w3-border"  name="rembursementceilinglimit['+window.globalCounter+'].cielingmax" id="LWP['+window.globalCounter+'].cielingmin" type="text"></td><td style="width:5%"><input class="w3-btn w3-theme" id="deletemgr"  type="button" value="x"/></td></tr>';
	
	$("#REIMBURSEMENT_TYPE_TBL tbody").append(data);
	
	$.each($('#REIMBURSEMENT_TYPE_TBL tr'),function(index,val){
		////debugger;
		$(this).find("td:eq(0)").find("select").attr('name','rembursementceilinglimit['+(index - 1)+'].eligibilityid');
		$(this).find("td:eq(0)").find("select").attr('onchange','mgrchange('+(index - 1)+')');
		$(this).find("td:eq(0)").find("select").attr('id','MGR'+(index - 1));
		
		
		$(this).find("td:eq(1) input[type='text']").attr('id','rembursementceilinglimit'+(index - 1));
		$(this).find("td:eq(1) input[type='text']").attr('name','rembursementceilinglimit['+(index - 1)+'].cielingmin');
		
		$(this).find("td:eq(2) input[type='text']").attr('id','rembursementceilinglimit'+(index - 1));
		$(this).find("td:eq(2) input[type='text']").attr('name','rembursementceilinglimit['+(index - 1)+'].cielingmax');
		
		
		
		

	
	});

}
//$(document).ready(function(){
//$(document).unbind();
$(document).on("click","#deletemgr",function(){
	//$("table").row($(this).parents('tr')).remove().draw(false);
	//debugger;
	var dex=$(this).attr('index');
	//var flg=$(this).attr('flg');
	$(this).parents("tr").remove();
	removeManager(dex);
	$(this).parents("tr").remove();
	$.each($('#REIMBURSEMENT_TYPE_TBL tr'),function(index,val){
	    
		$(this).find("td:eq(0)").find("select").attr('name','rembursementceilinglimit['+(index - 1)+'].eligibilityid');
		$(this).find("td:eq(0)").find("select").attr('onchange','mgrchange('+(index - 1)+')');
		$(this).find("td:eq(0)").find("select").attr('id','MGR'+(index - 1));
		
		
		$(this).find("td:eq(1) input[type='text']").attr('id','rembursementceilinglimit'+(index - 1));
		$(this).find("td:eq(1) input[type='text']").attr('name','rembursementceilinglimit['+(index - 1)+'].cielingmin');
		
		$(this).find("td:eq(2) input[type='text']").attr('id','rembursementceilinglimit'+(index - 1));
		$(this).find("td:eq(2) input[type='text']").attr('name','rembursementceilinglimit['+(index - 1)+'].cielingmax'); 
	});

});
//});

function removeManager(index) {
	var jurl="../newperson/removemanager/"+index;
	$.ajax({
		type: 'GET',
		url: jurl,
		success: function(data){

		},
		error: function(e){
			//alert(JSON.stringify(e));
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


/***********************************ROW OPERATIONS End Here**addrow*************/

var CR_REMB_ID='';

function ajaxPost()
{
	
	CR_REMB_ID = $('#txtactionid').val();
	
	if(CR_REMB_ID!=0){
		loadREMB_TYPE_CORRECT_Data();
	}
	else if(CR_REMB_ID==0){
		loadREMB_TYPE_SAVE_Data();
		
	}
	
		
		
};


function loadREMB_TYPE_SAVE_Data() {
var fd = $("#REMB_TYPE_SAVE").serialize();
//alert(fd.toString());
$.ajax({
		url: "../reimbursementType/saveRembursementType",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
		//	alert(data);
			if(data.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}
				if(data.status!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

};
	


function loadREMB_TYPE_CORRECT_Data() {
var flag=$("#CR_REMB_TYPE_START_DT").attr("msg");
//var jsonUrlRMB ='/reimbursementType/correctRembursementType/'+flag;	

//alert(flag)
//alert("Correct"+jsonUrlRMB);
var fd = $("#REMB_TYPE_SAVE").serialize();
//alert(fd.toString());
//debugger;
$.ajax({
	//	url: jsonUrlRMB,
		url: "../reimbursementType/correctRembursementType/"+flag,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
		//	alert(data);
			if(data.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function(e){
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
					});
				}
				if(data.status!="Success"){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function(e){
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","none");
					});
				}
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});

};



	$('#CR_REMB_TYPE_NAME').on('change',function(){
		//alert($( "#CR_REMB_TYPE_NAME option:selected" ).attr('msg'));
		var selectObject=$( "#CR_REMB_TYPE_NAME option:selected" ).attr('msg');
		$('#txtrembursementtypeid').val(selectObject);
	});
	
	
	
