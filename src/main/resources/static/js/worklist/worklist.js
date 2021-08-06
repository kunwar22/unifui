$(document).on('click').unbind();


$(document).ready(function(){
    var id="completed";
	var url = $("#" + id).attr("rm");
    $("#myworklist").html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw' style='font-size: 70px; color: grey;'></i></div>");
    $("#myworklist").load(url);
    tablinks = document.getElementsByClassName("wrklst");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace("w3-theme-l3", "");
    }
    $("#" + id).addClass("w3-theme-l3");
	});
	//openWorklist("pending");


function openWorklist(id) 
{
   // debugger;
    var url = $("#" + id).attr("rm");
    $("#myworklist").html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse fa-3x fa-fw' style='font-size: 70px; color: grey;'></i></div>");
    $("#myworklist").load(url);
    tablinks = document.getElementsByClassName("wrklst");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace("w3-theme-l3", "");
    }
    $("#" + id).addClass("w3-theme-l3");
}

