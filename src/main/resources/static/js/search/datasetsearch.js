
$('#datasetCreate').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
$('#EDIT_DATASET').on('click', function(e){

	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});


var jsonUrldataset = '/enterprisesetup/searchdataset';



var searchdsCode = '';
var searchdsName = '';

$("#dsSearch").on('click', function(e){
	searchdsCode =  $("#SRCH_CODE").val();
	searchdsName = $("#SRCH_NAME").val();
	loadDatasetData();
	
});
function loadDatasetData(){
	$.ajax({
		type: 'POST',
		url: jsonUrldataset,
		dataSrc: '',
		data: {
			"code": searchdsCode,
			"name": searchdsName
			
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;
			populateDSDataTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateDSDataTable(data){
	$("#DATASET_LIST").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#resultDataset').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#DATASET_LIST").dataTable().fnAddData([
				dat.name,
				dat.code,
				dat.description,				
				"<ed rm='enterprisesetup/edit/editDataset/correctdataset/"+dat.datasetsid+"' id='EDIT_DATASET' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"
			]);
		}
		$('#resultDataset').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}
