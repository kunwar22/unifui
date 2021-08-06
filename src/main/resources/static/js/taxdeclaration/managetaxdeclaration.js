
$( document ).ready(function() {
	//debugger;
	loadpersondata();
	//$(tabname).addClass("w3-theme");
});

	$("#PersonalRecordList").DataTable({
		'columnDefs': [{
			'targets': [1,2,3,4,5,6],
			'orderable': false
		}]
	});

	var jsonUrl = '/approvetaxdeclaration/searchPersonalRecordTax';
	
	
	function loadpersondata(){
		loadData();
		$('#LOADER').css('display', 'block');
		$('#PersonalRecordresultSec').css('display', 'none');
	}
		
	function loadData(){
		$.ajax({
			type: 'POST',
			url: jsonUrl,
			dataSrc: '',
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
		$("#PersonalRecordList").DataTable().clear();
		var dataLength = Object.keys(data).length;
		if(dataLength == 0){
			$('#PersonalRecordresultSec').css('display', 'none');
			$('#LOADER').css('display', 'none');
			$('#noData').css('display', 'block');
		} else {
			for(var i=0; i < dataLength; i++){
				var dat = data[i];
				var btn='';
				//alert(dat.status);
				if(dat.status!=null){
					btn='<div>Declared <i class="fas fa-circle" style="color: red"></i></div>';
				}else {
					btn='';
				}
				$("#PersonalRecordList").dataTable().fnAddData([
					dat.personnumber,
					dat.personname,
					dat.personlocation,
					dat.persondepartment,
					dat.personemail,
					btn,
					'<div class="w3-btn w3-blue w3-round" style="cursor: pointer" id="view" aria-hidden="true" onclick="viewdeclartaion('+dat.personnumber+');">View Declaration</div>'
				//	"<ed rm='/enterprisesetup/edit/EditDepartment/"+dat.personid+"/"dat.personid"' class='editpersonid' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"

					]);
			}
			$('#LOADER').css('display', 'none');
			$('#PersonalRecordresultSec').css('display', 'block');
			$('#noData').css('display', 'none');
		}
	}

	function viewdeclartaion(id){
		var url = "/approvetaxdeclaration/apprmanagehouserent/"+id;
		$('#replace_div').load(url);
	}

$('#NAME_CANCEL_BTN').on('click', function(e){

});




