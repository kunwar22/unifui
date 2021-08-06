

function backAutoIncr(){
	var url = "/scheduleprocess/manageemployeeautoincrement/BCK";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
	$('#replace_div').load(url);
}

function saveAutoIncrfinal(){
	var autoincrformdataa = $('#autoincrformfinal').serialize();
	$.ajax({
		url: "/scheduleprocess/saveautoincrfinal",
		type: "POST",
		data: autoincrformdataa,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function (result) {
			if(result=="Success"){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"Successfully saved.",
					callback: function () {
						$('#replace_div').load("/scheduleprocess/manageemployeeautoincrement/NXT");
					}

				});
			}
		},
		error: function (response) {
			alert(JSON.parse(response.responseText));
		}
	});
}