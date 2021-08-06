//alert(pnum);
//alert(pname);

$(document).ready(function(){
	$("#modalpopupsts").css("display","block");
});

function lwpPage(e){
	var str;
	if(e=='Active'){
		str='activeLwp';
	}else if(e=='Void'){
		str='voidLwp/'+pnum;
	}
	$.ajax('/lwp/'+str,{		
		success:function(data){
			$("#loadlwppage").html(data);
			$("#modalpopupsts").css("display","none");
		}		
	});
}

