var addresshtml='';

$(document).ready(function() {
 addresshtml=$("#ASSIGN_BLOCK12").html();
$("#ASSIGN_BLOCK12_E").hide();
$("#ASSIGN_BLOCK12_V").hide();
$("#ASSIGN_BLOCK12_C").hide();


$("#CR_PR_TWINS").hide();
//debugger;
/*if(document.getElementById('CR_PM_DEFT_ADD_E').checked) {

    $("#ASSIGN_BLOCK12").hide();
} else {
    $("#ASSIGN_BLOCK12").show();
}*/
/*if(document.getElementById('CR_PM_DEFT_ADD_V').checked) {

    $("#ASSIGN_BLOCK12").hide();
} else {
    $("#ASSIGN_BLOCK12").show();
}*/

    if($('#CR_PM_DEFT_ADD_V').prop("checked") == true){
        $("#ASSIGN_BLOCK12_V").hide();
    }
    else if($('#CR_PM_DEFT_ADD_V').prop("checked") == false){
        $("#ASSIGN_BLOCK12_V").show();
    }
    
     if($('#CR_PM_DEFT_ADD_E').prop("checked") == true){
        $("#ASSIGN_BLOCK12_E").hide();
    }
    else if($('#CR_PM_DEFT_ADD_E').prop("checked") == false){
        $("#ASSIGN_BLOCK12_E").show();
    }
    
    
     if($('#CR_PM_DEFT_ADD_C').prop("checked") == true){
        $("#ASSIGN_BLOCK12_C").hide();
    }
    else if($('#CR_PM_DEFT_ADD_C').prop("checked") == false){
        $("#ASSIGN_BLOCK12_C").show();
    }

var dateAccuraltxt='';
dateAccuraltxt = $('#CR_PM_CONTACTTYPE').children("option:selected").val();
 if( dateAccuraltxt=='33'){
      $("#CR_PR_TWINS").show();
    }
    else if(dateAccuraltxt!='33'){
         $("#CR_PR_TWINS").hide();
    }
       
   

});




$('#countryList1').on('change',function(){
	var selectCountryId=$(this).children("option:selected").val();
	
	var jsonUrl = '/personManagement/statebind/' +selectCountryId;
	newStatebind="";
	//alert(selectCountryId);
	$.ajax({
		type: 'GET',
		url: jsonUrl,
		dataSrc: '',		
		dataType: 'json',
		success: function(data){		
			newStatebind+='<p><label>State</label>';
			newStatebind+='<select id="stateList" class="w3-select w3-border" name="state" >'
				newStatebind+='<option value="" disabled selected></option>'
				data.forEach(function(n){
					newStatebind+='<option value="'+n.id+'" >'+n.description+'</option>'
				});
			newStatebind+='</select></p>';
			$('#divState1').html(newStatebind);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
});
/******************************************************************************************************/
function ajaxPost()
{	
	CR_PM_ID = $('#txtPMid').val();
	CR_PM_ACTIONID = $('#txtPMActionid').val();
	
	if(CR_PM_ID!=0){
		loadUpdatePMData();
	}
	else if(CR_PM_ID==0){
		loadSavePMData();
		}
	
};

function loadSavePMData() {
var fd = $("#PersonManagement").serialize();
//debugger;
$.ajax({
		url: "/personManagement/savePersonManagement",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
			 $('#replace_div').html(data);
			 if (resultfinal == "Success") 
				{
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message: 'You have successfuly Save Dependents information.',
                        callback:function(){
						$('#replace_div').load("/personManagement/SearchDependent");
					   }
                    });
                } 
				else if (resultfinal != "Success") 
				{
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message: 'You have Unable to Save Dependents information.'
                    });
                } 			
			},
		error:function(data){
		bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message:"Something went wrong. Please try again."						
					});
				$('#replace_div').html(data);
	}
	});

};


function loadUpdatePMData() {
var fd = $("#PersonManagement").serialize();
//debugger;
$.ajax({
		url: "/personManagement/updatePersonManagement",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data){
			 $('#replace_div').html(data);
			 if (resultfinal == "Success") 
				{
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message: 'You have successfuly Save Dependents information.',
                        callback:function(){
						$('#replace_div').load("/personManagement/SearchDependent");
					   }
                    });
                } 
				else if (resultfinal != "Success") 
				{
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message: 'Please fill all the mandatory fields before submit.'
                    });
                } 			
			},
		error:function(data){
		bootbox.alert({
						size: 'medium',
						title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
						message:"Something went wrong. Please try again."						
					});
				$('#replace_div').html(data);
	}
  });
};



$('#PMCancelB').on('click', function(e){
var url = $(this).attr("rm");
$('#replace_div').load(url);
});	

$('#AddressDetails').on('click', function(e){
// $("#ASSIGN_BLOCK12").fadeToggle();
 // $('#CR_PM_DEFT_ADD').val('0');
//$('#correspondenceAddress').html(addresshtml);
 // $('#correspondenceAddress').css('display', 'block');
// $('#ASSIGN_BLOCK12').css('display', 'none');
});	



var CR_CONTACTTYPE='';
var checkBox ='';
 var text='';

$('#CR_PM_CONTACTTYPE').on('change',function(){
		CR_CONTACTTYPE = $('#CR_PM_CONTACTTYPE').children("option:selected").text();
		if (CR_CONTACTTYPE=='Child' ){
   
 $('#CR_PR_TWINS').css('display', 'block');
  } else {
	
 $('#CR_PR_TWINS').css('display', 'none');
  }
	
});

function uncheckAll(name){
	var inputs = $(".natCheck");

	for(i=0;i<inputs.length;i++){
		if(!(inputs[i].name === name))
		{
			inputs[i].checked = false;
		}		
	}
	
}


$("#backbtn").click(function(){
	$('#replace_div').load("/personManagement/SearchDependent");
});




function ValidateEmail(email) {
        var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        return expr.test(email);
    };
function emailValidation(){
        if (!ValidateEmail($("#CR_PM_EMAIL").val())) {
            alert("Invalid email address.");
			$('#CR_PM_EMAIL').val("");
        }
        else {
            //alert("Valid email address.");
        }
    };
/************************************************************************************/


$('#CR_PM_DEFT_ADD_E').click(function() {	
if ($(this).is(':checked')) {
$('#ASSIGN_BLOCK12_E').css('display', 'none');
$('#ASSIGN_BLOCK12_E :input').attr('disabled', true);	     
		
} else {    
	$('#ASSIGN_BLOCK12_E').css('display', 'block');
	$('#ASSIGN_BLOCK12_E :input').attr('disabled', false);
 }
    });
    
    
    
    
$('#CR_PM_DEFT_ADD_C').click(function() {
 if ($(this).is(':checked')) {
	$('#ASSIGN_BLOCK12_C').css('display', 'none');
    //$('#ASSIGN_BLOCK12_C :input').attr('disabled', true);	   		
        } else {    
          $('#ASSIGN_BLOCK12_C').css('display', 'block');
		   //$('#ASSIGN_BLOCK12_C :input').attr('disabled', false);
        }
    });



/***********calculate date of birth************************/

/*	$('#CR_PM_DOB').on('change',function(){
   var birthDate =document.getElementById('CR_PM_DOB').value;
    var d = new Date(birthDate);

        var mdate = birthDate.toString();
        var yearThen = parseInt(mdate.substring(0,4), 10);
        var monthThen = parseInt(mdate.substring(5,7), 10);
        var dayThen = parseInt(mdate.substring(8,10), 10);        
        var today = new Date();
        var birthday = new Date(yearThen, monthThen-1, dayThen);
        var differenceInMilisecond = today.valueOf() - birthday.valueOf();
        var year_age = Math.floor(differenceInMilisecond / 31536000000);
        var day_age = Math.floor((differenceInMilisecond % 31536000000) / 86400000);              
        var month_age = Math.floor(day_age/30);        
        day_age = day_age % 30;        
        var tMnt= (month_age + (year_age*12));
        var tDays =(tMnt*30) + day_age;
     
//alert(year_age);

});*/
/*************************************/