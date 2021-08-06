/*$("#floatingmenu").click(function () {

    // Set the effect type
    var effect = 'slide';

    // Set the options for the effect type chosen
    var options = {
        direction: 'left'
    };

    // Set the duration (default: 400 milliseconds)
    var duration = 900;

    $('#myDiv').toggle(effect, options, duration);
});*/


$(document).ready(function(){
 	var url = "/personedit/managepersonedit";
        $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#myDiv').css('display','none');
		$('#div_replace').load(url);
});




$("#floatingmenu1").click(function () {
	$("#floatingmenu1").css("display","none");
	$("#myDiv").animate({
	    width: 'toggle'
	 },500);
	$("#floatingmenu2").animate({marginLeft: "+=210px", width: 'toggle'},210);
	
	//$('#myDiv').fadeIn("slow");
});

$("#floatingmenu2").click(function () {
	$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
	$("#floatingmenu1").css("display","block");
	$("#myDiv").animate({
	    width: 'toggle'
	 },500);
});

$("#PM_DONE_BTN").click(function(){
        var url = "/personmanage/personsearch";
       // $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
       // $('#myDiv').css('display','none');
		$('#replace_div').load(url);
		
});

$("#personedit").click(function(){
	 $("#REPORTS_LOADER").css("display","block");
		$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
		$("#floatingmenu1").css("display","block");
        var url = "/personedit/managepersonedit";
        $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#myDiv').css('display','none');
		$('#div_replace').load(url);
		$("#REPORTS_LOADER").css("display","block");
		
});

$("#pmdocrecord").click(function(){
	 $("#REPORTS_LOADER").css("display","block");
	$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
	$("#floatingmenu1").css("display","block");
	var url = "/personedit/pmdocrecord";
	$('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
	$('#myDiv').css('display','none');
	$('#div_replace').load(url);
	 $("#REPORTS_LOADER").css("display","block");

});



$('#termination').on('click', function(e){
	$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
	$("#floatingmenu1").css("display","block");
	var url = "/termination/resignation";
	$('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
	$('#myDiv').css('display','none');
	$('#div_replace').load(url);
});


$("#aor").click(function(){
	 $("#REPORTS_LOADER").css("display","block");
		$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
		$("#floatingmenu1").css("display","block");
        var url = "/aor/manageAor";
        $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#myDiv').css('display','none');
		$('#div_replace').load(url);
		 $("#REPORTS_LOADER").css("display","block");
		
});
$("#employeedetailedit").click(function(){
	 $("#REPORTS_LOADER").css("display","block");
		$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
		$("#floatingmenu1").css("display","block");
        var url = "/employee_edit/manageemployeedetail/z/z/z";
        $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#myDiv').css('display','none');
		$('#div_replace').load(url);
		$("#REPORTS_LOADER").css("display","block");
		
});

$("#lwp").click(function(){
		$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
		$("#floatingmenu1").css("display","block");
        var url = "/lwp/manageLwp";
        $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#myDiv').css('display','none');
		$('#div_replace').load(url);	
});

$("#natholdyall").click(function(){
		$("#floatingmenu2").animate({marginLeft: "-=210px", width: 'toggle'},50);
		$("#floatingmenu1").css("display","block");
         var url = "/nha/manageNha/"+pnum;
        $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#myDiv').css('display','none');
		$('#div_replace').load(url);	
});
