
	var CR_ENT_EFFDT = '';
	var CR_ENT_EFFENDDT = '';
	var CR_ENT_STATUS = '';
	var CR_ENT_NAME = '';
	var CR_ENT_LOC_CODE = '';
	var CR_ENT_ID='';
	var CR_ENT_ACTION_ID='';
	var jsonUrl = '/enterprisesetup/entpriseSaveCorrectUpdateLocation';
	
	$('#SAVE_OPTION_LOV').on('change',function(){
		var selectObject=$(this).children("option:selected").val();
		if(selectObject=='Correct'){
		
			$('#CR_ENT_EFFENDDT').prop("disabled",false);
			$('#CR_ENT_STATUS').prop("disabled",false);
			$('#CR_ENT_NAME').prop("disabled",false);
			$('#CR_ENT_LOC_CODE').prop("disabled",false);
			$('#ENTERPRISE_SAVE').css("display","inline");
		}
		else if(selectObject=='Update'){
			CR_ENT_EFFDT_RESTRICT = $('#CR_ENT_EFFDT').val();
			var d = new Date(CR_ENT_EFFDT_RESTRICT);
			d.setDate(d.getDate()+1);
			var n = d.toISOString();
			n = n.substring(0, 10);
			
			$('#CR_ENT_EFFDT').prop("disabled",false);
			$('#CR_ENT_EFFENDDT').prop("disabled",false);
			$('#CR_ENT_STATUS').prop("disabled",false);
			$('#CR_ENT_NAME').prop("disabled",false);
			$('#CR_ENT_LOC_CODE').prop("disabled",false);
			$('#ENTERPRISE_SAVE').css("display","inline");
			$('#CR_ENT_EFFDT').attr("min",n);
		}
	});
	
		var CR_ENT_EFFENDDT_RESTRICT = $('#CR_ENT_EFFENDDT').val();		
		if(CR_ENT_EFFENDDT_RESTRICT!=null ||CR_ENT_EFFENDDT_RESTRICT!=''||CR_ENT_EFFENDDT_RESTRICT!=undefined){
		
			if(new Date(CR_ENT_EFFENDDT_RESTRICT)< new Date()){
				$('#ENTERPRISE_SAVE').css("display","none");
				$('#SAVE_OPTION_LOV').prop('disabled',true);
				
			}else{
				$('#ENTERPRISE_SAVE').css("display","inline");
				$('#SAVE_OPTION_LOV').prop('disabled',false);
			}
		}
		

	CR_ENT_ID_TEST=$('#ENT_ID').val();
	if(CR_ENT_ID_TEST==0 || CR_ENT_ID_TEST==null ){
			$('#CR_ENT_EFFDT').prop("disabled",false);
			$('#CR_ENT_EFFENDDT').prop("disabled",false);
			$('#CR_ENT_STATUS').prop("disabled",false);
			$('#CR_ENT_NAME').prop("disabled",false);
			$('#CR_ENT_LOC_CODE').prop("disabled",false);
			$('#ENTERPRISE_SAVE').css("display","inline");
			$('#SAVE_OPTION_LOV').prop("disabled",true);
			
	}
	
	
	if($('#ENTERPRISE_SAVE').val() != ''){
		CR_ENT_EFFDT = $('#CR_ENT_EFFDT').val();
		CR_ENT_EFFENDDT = $('#CR_ENT_EFFENDDT').val();
		CR_ENT_STATUS = $('#CR_ENT_STATUS').val();
		CR_ENT_NAME = $('#CR_ENT_NAME').val();
		CR_ENT_LOC_CODE = $('#CR_ENT_LOC_CODE').val();
		loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#ENTERPRISE_SAVE").click(function(){
		
		CR_ENT_EFFDT = $('#CR_ENT_EFFDT').val();
		CR_ENT_EFFENDDT = $('#CR_ENT_EFFENDDT').val();
		CR_ENT_STATUS = $('#CR_ENT_STATUS').val();
		CR_ENT_NAME = $('#CR_ENT_NAME').val();
		CR_ENT_LOC_CODE = $('#CR_ENT_LOC_CODE').children('option:selected').val();
		CR_ENT_ID=$('#ENT_ID').val();
		CR_ENT_ACTION_ID=$('#ENT_ACTION_ID').val();
		CR_SAVE_TYPE=$('#SAVE_OPTION_LOV').val();
		
				
		CR_ENT_NAME=CR_ENT_NAME.replace(/\s+/g," ").trim();
		
		if(validateEnterprise(CR_ENT_EFFDT,CR_ENT_NAME,CR_ENT_LOC_CODE,CR_ENT_STATUS)){
			loadData();
			
		}
		
		$('#resultSec').css('display', 'block');
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"enterpriseid":CR_ENT_ID,
				"actionid":CR_ENT_ACTION_ID,
				"savetype":CR_SAVE_TYPE,
				"name": CR_ENT_NAME,
				"location": CR_ENT_LOC_CODE,
				"status": CR_ENT_STATUS,
				"effectStartDate": CR_ENT_EFFDT,
				"effectEndDate": CR_ENT_EFFENDDT
			},
			dataType: 'json',
			success: function(data){
				if(data.status=="Success")
				{
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display","block");
					$('#lblMsg').text(data.message+". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					
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
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
	}


/**************************Pop-up section start here****************************************/

	$(function() {
		$("#EnterpriseList").DataTable({
			'columnDefs': [{
				'targets': [3,4],
				'orderable': false
			}]
		});
		
		var searchName = '';
		var searchCode = '';
		var searchCountry = '';
		var searchStatus = '';
		var jsonUrl = '/enterprisesetup/edit/enterpriseLocation/searchEnterpriseLocation';
		
		
		
		
		if($('#CR_ENT_POP_SEARCH').val() != ''){
			searchName = $('#CR_ENT_POP_NAME').val();
			searchCode = $('#CR_ENT_POP_CODE').val();
			searchCountry = $('#CR_ENT_POP_CNTRY').val();
			searchStatus = $('#CR_ENT_POP_STATUS').val();
			$('#resultSec').css('display', 'block');
		}
		
		$("#CR_ENT_POP_SEARCH").click(function(){
			
			searchName = $('#CR_ENT_POP_NAME').val();
			searchCode = $('#CR_ENT_POP_CODE').val();
			searchCountry = $('#CR_ENT_POP_CNTRY').val();
			searchStatus = $('#CR_ENT_POP_STATUS').val();
			
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
					"code": searchCode,
					"country": searchCountry,
					"status": "Active"
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
			$("#EnterpriseList").DataTable().clear();
			var dataLength = Object.keys(data).length;
			if(dataLength == 0){
				$('#resultSec').css('display', 'none');
				$('#noData').css('display', 'block');
			} else {
				for(var i=0; i < dataLength; i++){
					var dat = data[i];
					$("#EnterpriseList").dataTable().fnAddData([
						dat.name,
						dat.code,
						dat.country,
						dat.state,
						dat.status,
						dat.locationid
						]);
					
				}
				
				$('#resultSec').css('display', 'block');
				$('#noData').css('display', 'none');
			}
		}
	});

	$(document).on('click', 'ed', function(e){
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});
	
	
/**************************Pop-up section End here****************************************/	
	
/*******************************Popup table selected row start*******************************************/
	
	
		$('#CR_ENT_LOC_CODE').on('change',function(){
		
		var selectObject=$(this).children("option:selected").val();
		if(selectObject=='search'){
			$('#id01').css("display","block");
			
		}
		else if(selectObject!='search'){
			$('#id01').css("display","none");
		}
	});
	
	
	
	
	$(document).ready(function(){
		
		
		
		
		$('#btnsearch').on('click', function(e){
			$('#id01').css("display","block");
			
		});
		
		$('#CR_ENT_POP_CANCEL').on('click', function(e){
			$('#id01').css("display","none");
			$('#CR_ENT_LOC_CODE').children('option[id="1"]').prop('selected',true);
			$('#resultSec').css('display', 'none');
			$('#CR_ENT_POP_OK').css("display","none");
		});
		
		
		
		$('#CR_ENT_POP_OK').on('click', function(e){
			$('#resultSec').css('display', 'none');
			$('#id01').css("display","none");
			$('#CR_ENT_POP_OK').css("display","none");
			
			
		});
		
		
		var table=$('#EnterpriseList').DataTable();
		
		$('#EnterpriseList tbody').on('click','tr',function(){
			
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
			var dtDataId=tbldata[5];
			$('#CR_ENT_LOC_CODE').val(dtData);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').text(dtData);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').val(dtDataId);
			$('#CR_ENT_LOC_CODE').children('option[id="2"]').prop('selected',true);
			$('#CR_ENT_POP_OK').css("display","inline-block");		
			
		});
		
	});
	
	
/*******************************Popup table selected row END*******************************************/
	
	
	
