function openpage(id){
	//debugger;
	var url = $("#"+id).attr("rm");
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$('#replace_div').load(url);
	tablinks=document.getElementsByClassName("abcd");
	for(i=0;i<tablinks.length;i++){
		/*tablinks[i].className=tablinks[i].className.replace("sideselect","");
	}
	$("#"+id).addClass("sideselect");*/
	tablinks[i].className=tablinks[i].className.replace("w3-theme-l3","");	
	}
	$("#"+id).addClass("w3-theme-l3");
}


	//CHANGED BY SNIGDHAA 10-NOV-2020 START	
function opendashboard(id){	
	////debugger;	
	var url = $("#"+id).attr("rm");
	$('#mydashboard').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$('#mydashboard').load(url);
	tablinks=document.getElementsByClassName("dsh");	
	for(i=0;i<tablinks.length;i++){	
		tablinks[i].className=tablinks[i].className.replace("w3-theme-l3","");	
	}	
	$("#"+id).addClass("w3-theme-l3");	
}

$(document).ready(function(){

	$('#replace_div').load("/user/personalDetails");
	/*var id="claimedreimbursement";
	var url = $("#"+id).attr("rm");
	$('#mydashboard').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$('#mydashboard').load(url);
	tablinks=document.getElementsByClassName("dsh");
	for(i=0;i<tablinks.length;i++){
		tablinks[i].className=tablinks[i].className.replace("w3-theme-l3","");
	}
	$("#"+id).addClass("w3-theme-l3");*/


});
//CHANGED BY SNIGDHAA 10-NOV-2020 END



/*$(document).ready(function(){
   


  $("#transportReimbursement").click(function(){
		$("#transportReimbursement").addClass("sideselect");
        var url = "/reimbursement/transportManager";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });



});

$(document).ready(function(){
    $("#teleReimbursement").click(function(){
        var url = "/reimbursement/telephoneSearch";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });
})

$(document).ready(function(){
    $("#telephoneClaim").click(function(){
        var url = "/reimbursement/telephone";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });
})

$(document).ready(function(){
    $("#adhocLocalSearch").click(function(){
        var url = "/reimbursement/adhoclocalSearch";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });
})*/


/*
$("#bankInfo").click(function(){
        var url = "/bankAccount/manageBankAccount";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
});
*/
/*
$("#resignation").click(function(){
        var url = "/user/resignation";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
});

$('#ctgReimbursement').on('click', function(e){
	var url ="/ctgReimbursement/manageCtgReimb";;
	$('#replace_div').load(url);
});

$('#cdaReimbursement').on('click', function(e){
	var url ="/cdaVehicle/manageCdaTelephone";;
	$('#replace_div').load(url);
});



$('#documentRecords').on('click', function(e){
	var url ="/user/docrecord";;
	$('#replace_div').load(url);
});

$('#personalDetails').on('click', function(e){
	var url ="/user/personalDetails";;
	$('#replace_div').load(url);
});

$('#applyNewLeave').on('click', function(e){
	var url ="/user/addabsence";;
	$('#replace_div').load(url);
});

$('#employmentInfo').on('click', function(e){
	var url ="/user/employmentInfo";
	$('#replace_div').load(url);
});

*/
/*Child Reimbursment search page starts here */
/*z
	$("#childEducation").click(function(){
        var url = "/reimbursement/child";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });*/
/* Child Reimbursment search page ends here */

/* electricity Reimbursment search page load starts here */
/*$("#electricityReimbursement").click(function(){
        var url = "/reimbursement/electricity";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });*/
/* electricity Reimbursment search page load ends here */
/*
$("#entertainmentReimbursement").click(function(){
        var url = "/reimbursement/entertainmentSearch";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
});


$("#adhocLocalSearch").click(function(){
    var url = "/reimbursement/adhoclocalSearch";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
    $('#replace_div').load(url);
});
*/
/*TADK Reimbursement page loads here*/
/*$("#tadkReimbursement").click(function(){
	//debugger;
        var url = "/reimbursement/tadk";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });
*/
/*TADK Reimbursement page loads here*/

/* Miscellaneous Reimbursment search page load ends here */
/*
$("#expenseReimbursement").click(function(){
        var url = "/miscelleneous/manageMiscelleneous";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
});
*/
/* Medical Advance Search Page Starts here */
/*
$("#medicalAdvance").click(function(){
	var url = "/reimbursement/medicalAdvance";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
});
*/
/* Medical Advance Search Page Ends here */

/*
$("#familyDependents").click(function(){
	//debugger;
        var url = "/personManagement/managePerson";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
    });

$("#medicalReimbursement").click(function(){
		    var url = "/reimbursement/medireimbsearch";
	        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
	        $('#replace_div').load(url);
});
*/
	function getNotify()
{
    $("#notifyid").slideToggle();
}
function getNotify2(){	
	$("#notifyid").slideUp();
}
