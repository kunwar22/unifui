//bootbox.alert("Hello...");
function searchassign(){

	var pygrp=$('#ASGN_PAYGRP').val();
	var natempl=$('#ASGN_NATEMPL').val();
	var hrdate=$('#ASGN_HIREDATE').val();
	var aurl="/autoassign/searchassign/"+pygrp+"/"+natempl+"/"+hrdate;
	$.ajax({
		type: 'GET',
		url: aurl,
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateassign(result);
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
};

/*$("#ASSIGN_SEARCH_RES").DataTable({
	'columnDefs': [{
		'targets': [2,3],
		'orderable': false
	}]
});*/

function populateassign(data){
	$("#ASSIGN_SEARCH_RES").DataTable().clear();
	// $('#Reimbursement_History').dataTable(  );
	var dataLength = data.length;	
	if(dataLength == 0){
		$("#ASGN_SUBMIT_BTN").css("display", "none");
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		
	} else {
		$("#ASGN_SUBMIT_BTN").css("display", "block");
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#ASSIGN_SEARCH_RES").dataTable({
			paging: false,
			destroy: true,
    		searching: true,
       		}).fnAddData([
       			dat.personnumber,
				dat.hiredate,
				dat.personname,
				dat.payrollstatus
				]);
		}
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
	}
};



 	function hirechange() {
		$("#ASGN_searchbtn").prop('disabled', false);
	}


	function saveautoasgn(){
		var surl="/autoassign/autoassignsave";
		$.ajax({
			type: 'POST',
			url: surl,
			processData: false,
			catch: false,
			success:function(data){
				$('#replace_div').html(data);
				if(responseresult == "Success" || responseresult == "success"){
					bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
						message: 'Elements assigned successfully.',
						callback:function(){
							window.location = "/home";
							//$('#replace_div').load("/newperson/openelementassignment");
						}
						//window.location = "/home";
						//$('#replace_div').load("/home");
					});
				}
			},
			error:function(e){
				console.log( "ERROR : "+ JSON.stringify(e) );
			}
		});
	}

				
/*				
$(document).ready(function() {
	//debugger;
	dateAccuraltxt = $('#remtype').children("option:selected").val();
	alert("hi"+dateAccuraltxt);
		if (dateAccuraltxt==16 ){
		
     $('#remtype').children('option[value="16"]').css('display','none');
  } else {
	alert("nothing");
  }

  });
				
*/				