function addDependent()
{
    var url ="/personManagement/managePerson";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
};




$(document).ready(function() {
	loadPersonDependentInformation();


});

function loadPersonDependentInformation(){
		
	$.ajax({
		type: 'GET',
		url: "/personManagement/getDependentDetails",
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populatePersonDependentDataTable(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
};

function populatePersonDependentDataTable(data){
	$("#searchDependenttable").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$("#resultDependent").css('display', 'none');
		$('#resultDependent').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			/*var str="";
			if(dat.status!='Saved'){
				str='<i class="fa fa-pen" id="edit" style="color:grey">';
			}else{
				str='<i class="fa fa-pen" id="edit" style="cursor: pointer" onclick="Viewdata('+dat.dependentdetailsid+',\'edit\')">';
			}*/
			$("#searchDependenttable").dataTable().fnAddData([
				dat.fullname,
				dat.dateofbirth,
				dat.genderdsc,	
				dat.phonenumber,
				dat.relationshipdsc,
				'<i class="fa fa-eye" style="cursor: pointer" id="view" aria-hidden="true" onclick="Viewdata('+dat.dependentdetailsid+',\'view\');"></i>',
				'<i class="fa fa-pen" style="cursor: pointer" id="view" aria-hidden="true" onclick="Viewdata('+dat.dependentdetailsid+',\'edit\');"></i>',
				]);
		}
		$('#resultDependent').css('display', 'block');
		$('#noDependentData').css('display', 'none');
	}
};


function Viewdata(x,m)
{

	$.ajax({
		type:"GET",
		 url :"personManagement/getviewdependentData/"+x+"/"+m,
		success:function(result){
			console.log("Success");
			$('#replace_div').html(result);
		
		}
	});
 
};


		$(document).on('click').unbind();
		$(document).on('click', 'ed', function(e){
			var url = $(this).attr("rm");
			$('#replace_div').load(url);
		});