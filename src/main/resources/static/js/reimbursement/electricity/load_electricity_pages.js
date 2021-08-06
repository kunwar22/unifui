$(document).ready(function(){
	$('#electricityreimbursement').DataTable();

});

/* Electricity Reimbursment claim page starts here */
function loadCreateElectricityClaim(){
	/*alert("in");*/
	var url = "/reimbursement/electricityclaim";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
        $('#replace_div').load(url);
}