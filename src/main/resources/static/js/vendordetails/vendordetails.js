
function backBtnFunc() {
    var url = "/vendordetails/searchVendor";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
        type: "GET",
        url: url,
        success: function (result) {
            $('#replace_div').html(result);
        },
        error: function (e) {
            bootbox.alert("ERROR : " + JSON.stringify(e));
        }
    });
}



function createVendor(){
	//debugger;
	var fd = $("#CREATEVENDOR").serialize();
	
	var curl="/vendordetails/saveVendor";
	$.ajax({
		url:curl,
		type: "POST",	 
	    data: fd,
	    contentType: "application/x-www-form-urlencoded",
		//dataType:"json",	
		processData: false,    
		success:function(data){
		$('#replace_div').html(data);
		
	
		if(result.status == "Success"){
			
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Vendor Created Successfully.",
                    callback:function() {
                        $('#replace_div').load("/vendordetails/searchVendor");
                    }
                });
            }

			if(result.status == "Update"){
			
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Vendor Updated Successfully.",
                    callback:function() {
                        $('#replace_div').load("/vendordetails/searchVendor");
                    }
                });
            }

             if(result.status == "Error"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Unable to create Vendor.",
                    callback:function() {
                        $('#replace_div').load("/vendordetails/createVendor");
                    }
                });
            }

        },
         error: function (e) {
            bootbox.alert({
                size: 'medium',
                title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                message:"Something went wrong please try again.",
                callback:function() {
                     $('#replace_div').load("/vendordetails/createVendor");
                }
            });
        }
    });

}






function addrowhtml(){
	var addr='<div class="medrdeprows">'
			+'<div class="w3-row">'
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Dependent Name:&nbsp;</label>'
			+'<input type="text" class="w3-input w3-border w3-round depname" id="a"/>'
			+'</p>'+'</div>'
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Gender:&nbsp;</label>'
			+'<input type="text" class="w3-input w3-border w3-round gen" id="b"/>'
			+'</p>'+'</div>'
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Date of Birth:&nbsp;</label>'
			+'<input type="text" class="w3-input w3-border w3-round dateofbirth1" id="c"/>'
			+'</p>'+'</div>'
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Age:&nbsp;</label>'
			+'<input type="text" class="w3-input w3-border w3-round age" id="d"/>'
			+'</p>'+'</div>'+'</div>'
			+'<div class="w3-row">'
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Relationship:&nbsp;</label>'
			+'<input type="text" class="w3-input w3-border w3-round rel" id="e"/>'
			+'</p>'+'</div>'
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Marital Status:&nbsp;</label>'
			+'<input type="text" class="w3-input w3-border w3-round mar" id="f"/>'
			+'</p>'+'</div>'
			+'</div>'+'</div>'+'</div>';
	
	return addr;	
}
