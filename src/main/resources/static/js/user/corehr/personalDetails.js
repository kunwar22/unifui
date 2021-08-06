
/*$(".upload-button").on('click', function() {
	$(".file-upload").click();
});*/

function openpopup(){
	$('#picpop').css("display","block");
}

function uploadpic(){
	var form = $("#PIC_POP_FORM")[0];
	var data = new FormData(form);

	$.ajax({
		url: "/user/propicupload",
		type: "POST",
		enctype: "multipart/form-data",
		data: data,
		cache: false,
		contentType:false,
		processData: false,
		timeout:600000,
		success: function(result){
			//$('#replace_div').html(data);
			if(result=="Success" || resultfinal=="success"){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:'Profile picture successfully uploaded.',
					callback:function(){
					$('#replace_div').load("/user/personalDetails");
					}
				});
			}
			else if( resultfinal=="MISMATCH" )
			{
				//bootbox.alert("MISMATCH");
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Mismatch Occurred. <br> Please attach the file again, and retry."
				});
			}
			else if( resultfinal=="EMPTY_FILE" )
			{
				//bootbox.alert("File is empty");
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "File is empty. <br> Please attach the file, and retry."
				});
			}
			else if( resultfinal=="WRITE_ERROR" )
			{
				//bootbox.alert("Error in writing file.");
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Error in writing file. <br> Please attach the file again, and retry."
				});
			}
			else if( resultfinal=="IOEXCEPTION" )
			{
				//bootbox.alert("IO Exception has occurred.");
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "IO Exception has occurred. <br> Please attach the file again, and retry."
				});
			}
			else if( resultfinal=="LOG_ERROR" )
			{
				//bootbox.alert("Error while logging file info.");
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Error while logging file info. <br> Please attach the file again, and retry."
				});
			}
			else if( resultfinal=="ILLEGALARG" )
			{
				//bootbox.alert("Error while posting file log.");
				bootbox.alert({
					size: 'medium',
					title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message: "Error while posting file log. <br> Please attach the file again, and retry."
				});
			}
		}
	});
}

$( document ).ready(function() {
	//debugger;
	var dob=$("#PD_BIRTH_DATE").val();
	if(dob!=null && dob!=undefined && dob!="" ){
	var dobdate = new Date(dob);
	var today = new Date();
	var age = Math.floor((today-dobdate) / (365.25 * 24 * 60 * 60 * 1000));
	//alert("AGE :: "+age);
	$("#EI_AGE").val(age);
	//$('#age').html(age+' years old');
	}
});

//alert("Hii..");
var nameblock_disp=true;
var demographic_disp=true;
var nat_disp=true;
var addr_disp=true;
var phone_disp=true;
var mail_disp=true;


$('#VIEW_NAME_BLOCK').on('click', function(e){
	if(nameblock_disp==false){
		$('#NAME_BLOCK').css("display","none");
		$('#EDIT_NAM_BLOCK').css("display","none");
		nameblock_disp=true;
	}else if(nameblock_disp==true){
		$('#NAME_BLOCK').css("display","block");
		$('#EDIT_NAM_BLOCK').css("display","block");
		nameblock_disp=false;
	}
});

$('#VIEW_DEMOGRAPHIC_BLOCK').on('click', function(e){
	if(demographic_disp==false){
		$('#DEMOGRAPHIC_BLOCK').css("display","none");
		$('#EDIT_DEMOGRAPHIC_BLOCK').css("display","none");
		demographic_disp=true;
	}else if(demographic_disp==true){
		$('#DEMOGRAPHIC_BLOCK').css("display","block");
		$('#EDIT_DEMOGRAPHIC_BLOCK').css("display","block");
		demographic_disp=false;
	}
});

$('#VIEW_NAT_BLOCK').on('click', function(e){
	if(nat_disp==false){
		$('#NAT_BLOCK').css("display","none");
		$('#EDIT_NAT_BLOCK').css("display","none");
		nat_disp=true;
	}else if(nat_disp==true){
		$('#NAT_BLOCK').css("display","block");
		$('#EDIT_NAT_BLOCK').css("display","block");
		nat_disp=false;
	}
});

$('#VIEW_ADDR_BLOCK').on('click', function(e){
	if(addr_disp==false){
		$('#ADDR_BLOCK').css("display","none");
		addr_disp=true;
	}else if(addr_disp==true){
		$('#ADDR_BLOCK').css("display","block");
		addr_disp=false;
	}
});

$('#VIEW_PHONE_BLOCK').on('click', function(e){
	if(phone_disp==false){
		$('#PHONE_BLOCK').css("display","none");
		phone_disp=true;
	}else if(phone_disp==true){
		$('#PHONE_BLOCK').css("display","block");
		phone_disp=false;
	}
});

$('#VIEW_EMAIL_BLOCK').on('click', function(e){
	if(mail_disp==false){
		$('#EMAIL_BLOCK').css("display","none");
		mail_disp=true;
	}else if(mail_disp==true){
		$('#EMAIL_BLOCK').css("display","block");
		mail_disp=false;
	}
});

/*
$('#EDIT_NAME_BLOCK').on('click', function(e){
	$('#PD_TITLE').prop("disabled",false);
	$('#PD_FIRST_NAME').prop("disabled",false);
	$('#PD_MIDDLE_NAME').prop("disabled",false);
	$('#PD_LAST_NAME').prop("disabled",false);
	$('#SUBMIT_BTN').css('display', 'block');
});*/

/*
$('#EDIT_NAM_BLOCK').on('click', function(e){
	$('#PD_TITLE').prop("disabled",false);
	$('#PD_FIRST_NAME').prop("disabled",false);
	$('#PD_MIDDLE_NAME').prop("disabled",false);
	$('#PD_LAST_NAME').prop("disabled",false);
	$('#SUBMIT_BTN').css('display', 'block');
});
*/

$('#EDIT_DEMOGRAPHIC_BLOCK').on('click', function(e){
	//$('#PD_BIRTHCOUNTRY').prop("disabled",false);
	$('#PD_MARITAL_STATUS').prop("disabled",false);
	$('#PD_MARRIAGE_DATE').prop("disabled",false);
	//$('#PD_GENDER').prop("disabled",false);
	//$('#PD_RELIGION').prop("disabled",false);
	//$('#PD_RELIGION_CHANGE_DATE').prop("disabled",false);
	//$('#PD_BIRTH_DATE').prop("disabled",false);
	$('#SUBMIT_BTN').css('display', 'block');
});

/*
$('#EDIT_NAT_BLOCK').on('click', function(e){
	$('#PD_NAT_COUNTRY').prop("disabled",false);
	$('#PD_NAT_ID').prop("disabled",false);
	$('#SUBMIT_BTN').css('display', 'block');
});
*/
function savePersonlDetails()
{

	var fd = $("#PERSONAL_DETAILS_EDIT").serialize();
	
	$.ajax({
		url: "/user/savepersonaledit",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(result){
			$('#replace_div').html(result);
			
			},
		error: function(response){
			alert(JSON.parse(response.responseText));
		   	}
	});
};

/*	PD_START_DATE
	PD_TITLE
	PD_FIRST_NAME
	PD_LAST_NAME
	PD_BIRTHCOUNTRY
	PD_RELIGION
	PD_MARITAL_STATUS
	PD_MARRIAGE_STAT_DATE
	PD_START_DATE2
	PD_GENDER
	PD_HIGHEST_ED
	PD_MARRIAGE_DATE
	PD_NAT_COUNTRY
	PD_NAT_ID*/