
var msg1='';

$(document).ready(function(){
	 msg1=result;	
var url='/login';
	if(msg1=="failed"){
			/*bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
				message:'Error resetting password Please contact to the administrator.',
				callback:function(){
				$('#replace_div').load("/login");
				}				
			});*/
			alert("Error resetting password Please contact to the administrator.");
			window.location.href = "/login";
			}
			else if(msg1=="ERROR"){
				/*bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message:'Service Error Password is not changed Please contact to the administrator.',
						callback:function(){
						$('#replace_div').load("/login");
						}				
					});*/
			  alert("Service Error Password is not changed Please contact to the administrator.");
			  window.location.href = "/login";
			}
			else if(msg1=="success"){
			  alert("Password is successfully changed kindly login with new password.");
			  window.location.href = "/login";
					
			
			}	
	
});

