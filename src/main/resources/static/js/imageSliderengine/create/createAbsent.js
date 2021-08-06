
var CR_AB_ID = '';
var CR_AB_ACTIONID = '';
var actionidAbsence = '';

CR_AB_ID = $('#txtABid').val();
CR_AB_ACTIONID = $('#txtABActionid').val();

	
$('#AbsenceCancel').on('click', function(e){
				var url = $(this).attr("rm");
				$('#replace_div').load(url);
				});	


var effdt_start = '';
var effdt_end = '';
var view_on=false;



function addRowNowGRADE()
{
	actionidAbsence = $('#actionidAbsence').val();
	
	
	var table=document.getElementById("GRADE_RATE_VALUE_TBL");
	
	var row=table.insertRow(table.rows.length);
	

	
	var cell0 = row.insertCell(0);
	var cell1 = row.insertCell(1);
	var cell2 = row.insertCell(2);
	var cell3 = row.insertCell(3);
	var cell4 = row.insertCell(4);

	
	
	cell0.innerHTML = "<input  class='w3-input w3-border' name='absenceproration["+(table.rows.length-2)+"].fromday' type='text'/>";
	cell1.innerHTML = "<input  class='w3-input w3-border' name='absenceproration["+(table.rows.length-2)+"].today' type='text'/>";
	cell2.innerHTML = "<input  class='w3-input w3-border' name='absenceproration["+(table.rows.length-2)+"].noofleaves' type='text'/>";
	cell3.innerHTML = "<input class='w3-btn  w3-theme' type='button' value='x' onclick='delThisRowGRADE();'/>";
}




function delThisRowGRADE()
{
	var table=document.getElementById("GRADE_RATE_VALUE_TBL");
	
	for(var i=1; i<table.rows.length; i++)
	{
		table.rows[i].cells[4].onclick = function(){
			if(table.rows.length > 2)
			{
				table.deleteRow(this.parentElement.rowIndex);
			}
			else
			{
				cell0.innerHTML = "<select  class='w3-select w3-border gradeSearchLOV' name='graderatevalue[0].gradesid' data-toggle='modal'> <option id='1' value='1' disabled selected></option> <option id='2' value='2'></option> <option value='search' data-toggle='modal' class='btnsearch2' id='btnsearch2'>Search....</option> </select>";
				cell1.innerHTML = "<input  class='w3-input w3-border' name='absenceproration[0].fromday' type='text'/>";
				cell2.innerHTML = "<input  class='w3-input w3-border' name='absenceproration[0].today' type='text'/>";
				cell3.innerHTML = "<input  class='w3-input w3-border' name='absenceproration[0].noofleaves' type='text'/>";
				cell6.innerHTML ="<input value='' id='ENDDATE' class='w3-input w3-border' name='graderatevalue[0].effectenddate'  type='date' data-date='' data-date-format='YYYY MM DD' readonly />";
				cell7.innerHTML ="<input value='' id='STARTDate' class='w3-input w3-border' name='graderatevalue[0].effectstartdate'  type='date' data-date='' data-date-format='YYYY MM DD' readonly />";
			}
		}
	}
}




var dateAccural='';
var dateAccuraltxt='';
var checkBox ='';
 var text='';

$('#CR_ABS_FREQ').on('change',function(){
		
		
		dateAccuraltxt = $('#CR_ABS_FREQ').children("option:selected").text();
		if (checkBox.checked == true && dateAccuraltxt=='Monthly' ){
    text.style.display = "block";
  } else {
  }


	
		
	});



function myFunction() {
   	 checkBox = document.getElementById("myCheck");
 	 text = document.getElementById("text");

  if (checkBox.checked == true && dateAccuraltxt=='Monthly' ){
    text.style.display = "block";
  } else {
     text.style.display = "none";

  }


	
	
	
}	
if($("#txtABid").val()!=0){
	$("#text").css("display","block");
}

/**************************Pop-up section start here****************************************/

		var searchName = '';
		var searchCode = '';
		var jsonUrl = '/absencesetup/seach/Eligibility/searchEligibility';
		
		$("#CR_ABS_POP_SRCH").click(function(){	
				
			searchName = $('#CR_ABS_POP_NAME').val();
			searchCode = $('#CR_ABS_POP_CODE').val();
			loadPopupTableData();
			$('#resultSec').css('display', 'block');
		});
			
			
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
			var dataLength = Object.keys(data).length;
			if(dataLength == 0){
				$('#resultSec').css('display', 'none');
				$('#noData').css('display', 'block');
			} else {
				for(var i=0; i < dataLength; i++){
					var dat = data[i];
					$("#AbsenceSearchList").dataTable().fnAddData([						
						dat.eligibilityid,
						dat.eligibilityname,
						dat.eligibilitydescription	
						
					]);
					
						
					
				}
				$('#resultSec').css('display', 'block');
				$('#noData').css('display', 'none');
			}
		}
	

	$(document).on('click').unbind();
	$(document).on('click', 'ed', function(e){
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});
	
	
	
	
	

	
	
	
	
/**************************Pop-up section End here****************************************/	
	
/*******************************Popup table selected row start*******************************************/
	
	
		$('#CR_ABS_ELIGIBILITY').on('change',function(){
		
		var selectObject=$(this).children("option:selected").val();
		if(selectObject=='search'){
			$('#id01').css("display","block");
			
		}
		else if(selectObject!='search'){
			$('#id01').css("display","none");
		}
	});
	
	
	$(document).on('click').unbind();
	$(document).on('click', 'ed', function(e){
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});
	
	
	
	
	$(document).ready(function(){
		
		
		
		
		$('#btnsearch').on('click', function(e){
			$('#id01').css("display","block");
			
		});
		
		
		
		$('#CR_ABS_POP_OK').on('click', function(e){
			$('#id01').css("display","none");
			
			$('#resultSec').css("display","none");
			$('#CR_ABS_POP_OK').css("display","none");
			
		});
		
		
		$('#CR_ABS_POP_CANCEL').on('click', function(e){
			$('#id01').css("display","none");
			$('#resultSec').css("display","none");
			$('#CR_ABS_ELIGIBILITY').children('option[id="1"]').prop('selected',true);	
			
		});
		
		
		
		
	
		var table=$('#AbsenceSearchList').DataTable();
		
		$('#AbsenceSearchList tbody').on('click','tr',function(){
		
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
			
			var dtData=tbldata[1];
			var dtDataId=tbldata[0];
			$('#CR_ABS_ELIGIBILITY').val(dtData);
			$('#CR_ABS_ELIGIBILITY').children('option[id="2"]').text(dtData);
			$('#CR_ABS_ELIGIBILITY').children('option[id="2"]').val(dtDataId);
			$('#CR_ABS_ELIGIBILITY').children('option[id="2"]').prop('selected',true);
			
			$('#CR_ABS_POP_OK').css('display','inline');
		
		});
		
	
	
	});
/*******************************Popup table selected row END*******************************************/


function ajaxPost()
{
	
	CR_AB_ID = $('#txtABid').val();
	CR_AB_ACTIONID = $('#txtABActionid').val();
	
	if(CR_AB_ID!=0){
		loadCorrectABData();
	}
	else if(CR_AB_ID==0){
		loadSaveABData();
		
	}
	
	
	
	
};



function loadSaveABData() {
var fd = $("#ABSENCE_SAVE").serialize();

$.ajax({
		url: "/absencesetup/saveAbsence",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
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


function loadCorrectABData() {
var flag=$("#CR_ABS_EFFDT_START").attr("msg");

var jsonUrlpo ='/absencesetup/correctAbsence/'+flag;
var fd = $("#ABSENCE_SAVE").serialize();

$.ajax({
		url: jsonUrlpo,
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
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




/*********************************************************************************** */
