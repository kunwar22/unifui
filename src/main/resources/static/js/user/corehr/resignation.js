function saveResgnForm()
{
	
	// $('input#selectedlovname').val($("#RESGN_REASON option:selected").text());
    
	var fd = $("#RESIGNATION").serialize();
	
	$.ajax({
		url: "/user/saveresignation",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false
		,success : function(result){
				
						$('#replace_div').html(result);
						if(message!=null){
						alert(message);
						}
				}
			//alert(result);
			//$('#replace_div').html(result);
			//createdPlanId = result.match(/(\d+)/)[0];
			//alert(createdPlanId);
			
			});
			
	
};

//$('#error').text(result);
			//}
			/*,
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}*/