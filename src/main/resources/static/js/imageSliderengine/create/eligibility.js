
/*
$("input[name='criteria']").on('click', function(e){
		$('#btnAdd').css('display', 'inline-block');
});
*/

function sendEligibleData()
{
	//alert(cmd);
	//debugger;
	///document.getElementById("cmd").value=cmd;
	//alert($('#frmEligible').serialize());
	$.ajax({
		type: 'POST',
		url: '../eligibility/nextandsaveperson',
		data: $('#frmEligible').serialize(),
		contentType:"application/x-www-form-urlencoded",
		processData:false,
		catch:false,
		success: function(response){
			//alert(response);
			$('#fragmaent_container').html(response);
		},
		error: function(e){
			alert(e);
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
});

}
function bindCriteria()
{
	
	var criteria=$("#CR_ELIG_CRI option:selected").val().trim();
	document.getElementById("cmd").value=criteria;
	var str="";
	$.ajax({
		type: 'GET',
		url: '../eligibility/getCriteriavalues',
		data: $('#frmEligible').serialize(),
		contentType:"application/x-www-form-urlencoded",
		processData:false,
		catch:false,
		success: function(data){
			data.forEach(function(n){
				str=str+'<label><input class="w3-check" type="checkbox" tvalue="'+n.id+'" value="'+n.description+'" name="criteria">'+n.description+'</label></br>'
			});
			
			$('#CRITERIA_BLOCK').css('display', 'block');
			$('#divcriteria').css('display', 'block');
			$('#divcriteria').html(str);
		
		},
		error: function(e){
			alert(e);
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
});


	
}
$(document).unbind();
$("#btnAdd").click(function(){

$('#mytable').css('display', 'block');
	var criteria=$("#CR_ELIG_CRI option:selected").text().trim();

var favorite=[];
	var favoriteId=[];


$.each($("input[name='criteria']:checked"),function(){
favorite.push($(this).val());
});
	$.each($("input[name='criteria']:checked"),function(){
		favoriteId.push($(this).attr('tvalue'));

	});
	//alert(favoriteId.join(","));
	if(favoriteId.length>0)
	{
var markup="<tr><td><input type='text' readonly='true' value='"+criteria+"' class='lblCriteria'/></td><td><input type='text' readonly='true' value='"+favorite.join(",")+"' class='lblCriteria'/></td><td><input type='hidden'  value='"+favoriteId.join(",")+"'/><input type='button' value='Delete' id='btnDell' class='btnDelete'/></td></tr>";
$("table tbody").append(markup);
$.each($('#mytable tr'),function(index,val){
	//debugger;
	$(this).find("td:eq(0) input[type='text']").attr('name','eligibilitycriteria['+(index-1)+'].eligibilitycriterianame');
	$(this).find("td:eq(1) input[type='text']").attr('name','eligibilitycriteria['+(index-1)+'].criteriavaluename');
	$(this).find("td:eq(2) input[type='hidden']").attr('name','eligibilitycriteria['+(index-1)+'].criteriavalueid');
	$(this).find("td:eq(3) input[type='button']").attr('name',  + (index - 1));

});
//alert("values:"+favorite.join(", "));
$('#CRITERIA_BLOCK').css('display', ' none');
}
else if(favoriteId.length<=0)
{
	alert ("Please Select Criteria Type.");
}
});

$(document).ready(function () {
	$(document).unbind();
$(document).on("click","#btnDell",function(){
	//debugger;
	var dex=$(this).attr('name');

	removeRow(dex);
	$(this).parents("tr").remove();

$.each($('#mytable tr'),function(index,val){
	//debugger;
	$(this).find("td:eq(0) input[type='text']").attr('name','eligibilitycriteria['+(index-1)+'].eligibilitycriterianame');
	$(this).find("td:eq(1) input[type='text']").attr('name','eligibilitycriteria['+(index-1)+'].criteriavaluename');
	$(this).find("td:eq(2) input[type='hidden']").attr('name','eligibilitycriteria['+(index-1)+'].criteriavalueid');
	$(this).find("td:eq(3) input[type='button']").attr('name',  + (index - 1));

});

});
});
function removeRow(index) {
	var jurl = "../eligibility/removechild/" + index;
	$.ajax({
		type: 'GET',
		url: jurl,
		success: function (data) {

		},
		error: function (e) {
			//alert(JSON.stringify(e));
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
					}
	});
}

$('#CANCEL_BTN_ELIG').click(function(e){
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});



/****************************************************************** */

// $(".delete-row").click(function(){

// 	$("table tbody").find('input[name="record"]').each(function(){
// 		if($(this).is(":checked")){
// 			$(this).parents("tr").remove();
// 		}

// 	});
	
// });


$('#SAVE_BTN_ELIG').click(function(e){
	//alert($('#CR_ELIG_CRI').val());
	saveEligibility();	
});
/*if($('#CR_ELIG_CRI').val()!="" && $('#CR_ELIG_CRI').val()!=undefined){
	saveEligibility();	
	}else{
		$('#CRITERIA_ERROR').text("Please Select Criteria");
	}
*/
function saveEligibility()
{
	var eligId=$("#cmdElig").val();
	var postUrl="";
	if(eligId==0) {
		postUrl = "../eligibility/setEligibility";
	}
	else if(eligId!=0)
	{
		postUrl = "../eligibility/correctEligibility";
	}
		var criteria=$("#CR_ELIG_CRI option:selected").text().trim();
	document.getElementById("cmd").value=criteria;
	var str="";
	$.ajax({
		type: 'POST',
		url: postUrl,
		data: $('#frmEligible').serialize(),
		contentType:"application/x-www-form-urlencoded",
		processData:false,
		catch:false,
		success: function(result){
			////debugger;
			//alert(result);
			//alert(result);
			
			$('#replace_div').html(result);
			if(res.status!=null && res.status!=""){
			if (res.status == "Success") {
				$('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
				$('#lblMsg').text(res.message + ". Click OK to continue.");
				$('#btnOK').toggleClass("w3-button w3-green");
				$('#btnOK').on('click', function (e) {
					var url = $(this).attr("rm");
					$('#replace_div').load(url);
				});
			}
			if (res.status != "Success") {
				$('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
				$('#lblMsg').text(res.message + ". Click OK to continue.");
				$('#btnOK').toggleClass("w3-button w3-red");
				$('#btnOK').on('click', function (e) {
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "none");
				});
			}
			}
			
		},
		error: function(e){
			alert(e);
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	
	});
}