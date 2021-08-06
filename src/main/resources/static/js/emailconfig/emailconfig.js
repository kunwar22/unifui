function saveEmailConfig(view_mode)
{

	var fd = $("#EMAIL_CONFIG").serialize();
	console.log("Serialized Data=====> "+fd);
	$.ajax({
		url: "/email/saveEmailConfigs",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data)
		{
			$('#replace_div').html(data);
			if($("#status").val()=="Success")
			{
				if(view_mode=="save")
				{
					bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully saved Email Configuration.",
							callback:function(){
								$('#replace_div').load("/email/searchConfigEmail");
							}
						});	
				}
				else if(view_mode=="update")
				{
					bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully updated Email Configuration.",
							callback:function(){
								$('#replace_div').load("/email/searchConfigEmail");
							}
						});	
				}	
			}
			else
			{
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Fill all the mandatory fields and attach the file again, if required."				
				});			
			}
		},
		error: function(response)
		{
			//alert("error");
			console.log(JSON.parse(response.responseText));
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Something went wrong. Please try again."				
				});
				//$('#replace_div').html(response);
		}
	});
}


function ValidateEmail(email) {
        var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return expr.test(email);
    };
function emailValidation(){
        if (!ValidateEmail($("#emailid").val())) {
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Invalid email address!!!"				
				});
			$('#emailid').val("");
        }
    };

function backBtnFunc(){
	var url = "/email/searchConfigEmail";
	$('#replace_div').load(url);
	}