

$('#juriCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});





$(function() {
	$("#JurisdictionList").DataTable({
		'columnDefs': [{
			'targets': [3,4],
			'orderable': false
		}]
	});
	
	
	
	var JURI_NAME = '';
	var JURI_COUNTRY = '';
	var JURI_IDENTI = '';
	var JURI_STATUS = '';
	
	var jsonUrl = '/enterprisesetup/searchJurisdiction/getJurisdictionId';
	
	if($('#JURI_SEARCH').val() != ''){
		JURI_NAME = $('#JURI_NAME').val();
		JURI_COUNTRY = $('#countrySearch').children("option:selected").val();
		JURI_IDENTI = $('#JURI_IDENTI').val();
		JURI_STATUS = $('#JURI_STATUS').children("option:selected").val();
		
		loadData();
		$('#resultSec').css('display', 'block');
	}
	
	$("#JURI_SEARCH").click(function(){
		JURI_NAME = $('#JURI_NAME').val();
		JURI_COUNTRY = $('#countrySearch').children("option:selected").val();
		JURI_IDENTI = $('#JURI_IDENTI').val();
		JURI_STATUS = $('#JURI_STATUS').children("option:selected").val();
		loadData();
		$('#resultSec').css('display', 'block');
	});
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
			data: {
				"name": JURI_NAME,
				"country": JURI_COUNTRY,
				"identifying": JURI_IDENTI,
				"status": JURI_STATUS
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
		$("#JurisdictionList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#resultSec').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				$("#JurisdictionList").dataTable().fnAddData([
					dat.name,
					dat.country,
					dat.legislativecategories,
					dat.identifying,
					dat.legalentityregncode,
					dat.effectstartdate.substring(0,10),
					dat.status,			
					"<ed rm='/enterprisesetup/edit/EditJurisdiction/"+dat.legaljurisdictionid+"' class='editJurisdiction' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
					
					]);
			}
			$('#resultSec').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}
});

$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});















