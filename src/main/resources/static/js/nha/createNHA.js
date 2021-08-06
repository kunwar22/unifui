//alert("hi nha");

$('#pno').val(pnum);

function setStatus(chkid,txtid) {
	////debugger;
  var checkBox = document.getElementById(chkid);
  var text = document.getElementById(txtid);
  if (checkBox.checked == true){
    text.value = "Active";
  } else {
    text.value = "Inactive";
  }
}


function saveNha()
{
	//debugger;
	var nhasaveurl = "/nha/saveNha";
	var checkstatus = document.getElementsByClassName("nhacheck");
	var statusbox = document.getElementsByClassName("status");
	var form_data ="";
	/*for(var i = 0;i<checkstatus.length;i++)
	{
		if(checkstatus[i].checked == true)
		{
			statusbox[i].value = "Active";
			checkstatus[i].checked = true;
			checkstatus[i].disabled = true;			
		}
		else if(checkstatus[i].checked == false)
		{
			statusbox[i].value = "Inactive";
		}
	}*/
	
	
	var form_data = $("#NHA_SAVE").serialize();
	console.log("form-data ::::   "+form_data);
	$.ajax({  	
		url:nhasaveurl,
	    type: "POST",
	    data: form_data,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success : function(data)
		{
			console.log("success data ::: "+data);
			bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
				message:"National Holiday Allowance saved!!!",
				callback:function(){
				$('#div_replace').load("/nha/manageNha/"+pnum);
				//populateNhaHistory(data);
				}
			});
		}
	});
}


/* search for nha history */
/*var jsonNHASearchUrl = '/nha/getNhaHistory/pnum';

function loadNHAHistory()
{
	//debugger;
	$.ajax({
		type: 'GET',
		url: jsonNHASearchUrl,
		dataSrc: '',
		data: {
		},
		dataType: 'json',
		success: function(data){
			jsonData = data;			
			populateNhaHistory(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populateNhaHistory(data)
{
	console.log("DATA ====> "+data);
	$("#nhaHistory").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#search_results').css('display', 'none');
		$('#noData').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			
			$("#nhaHistory").dataTable().fnAddData([				
				dat.holidayname,
				dat.period
			]);
		}
		$('#search_results').css('display', 'block');
		$('#noData').css('display', 'none');
	}
}*/
/* Search Data JS ends*/