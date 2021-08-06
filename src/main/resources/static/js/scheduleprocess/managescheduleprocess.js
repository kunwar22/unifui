$("#AUTOASSIGN").click(function(){
		var url = "/autoassign/loadautoassign";
        $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
        $('#replace_div').load(url);
});
$("#AUTOINCREMENT").click(function(){
    var url = "/scheduleprocess/manageemployeeautoincrement/NXT";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
    $('#replace_div').load(url);
});