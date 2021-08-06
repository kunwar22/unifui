var vendorid = '';

function vendorElemMapping() {
	vendorid = $('#vendorid').val();
	var curl = "/vendordetails/searchVendorPersonMapping/" + vendorid;
	$.ajax({
		type: 'GET',
		url: curl,
		contentType: "application/json",
		dataType: "json",
		success: function(result) {
			populatevendorelement(result);
		},
		error: function(e) {
			console.log("ERROR : " + JSON.stringify(e));
		}
	});
}
var counter = 0;

function populatevendorelement(data) {
	$("#VENDOR_TBL").DataTable().clear();
	$('#vendorDiv').css('display', 'none');
	$('#saveblock').css('display', 'none');
	var dataLength = data.length;
	counter = dataLength;
	if(dataLength == 0) {
		$('#vendorDiv').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#saveblock').css('display', 'none');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#VENDOR_TBL").dataTable().fnAddData([
				dat.vendorname + '<input type="hidden" name="vendorPersonMapping[' + (i) + '].vendorid" class="vendorIdClass"  value="' + dat.vendorid + '">' + '<input type="hidden" name="vendorPersonMapping[' + (i) + '].vendorname" class="vendorNameClass"  value="' + dat.vendorname + '">',
				dat.personname + '<input type="hidden" name="vendorPersonMapping[' + (i) + '].personnumber" class="personNumberClass"  value="' + dat.personnumber + '">' + '<input type="hidden" name="vendorPersonMapping[' + (i) + '].personname" class="personNameClass"  value="' + dat.personname + '">',
				dat.natureofemployement + '<input type="hidden" name="vendorPersonMapping[' + (i) + '].natureofemployement" class="natureOfEmpClass" value="' + dat.natureofemployement + '">',
				dat.isactive + '<input type="hidden" name="vendorPersonMapping[' + (i) + '].isactive" class="isactiveClass" value="' + dat.isactive + '">' + '<input type="hidden" name="vendorPersonMapping[' + (i) + '].personmappid" class="PersonMappingIdClass" value="' + dat.personmappid + '">',
				//"<ed rm='/vendordetails/editVendorById/"+dat.id+"' onclick='popup_Open();' style='cursor: pointer;' ><i class='fa fa-edit' aria-hidden='true'></i></ed>",
				"<ed id='remove' class='myselect' onclick='mydeleteFunction(this)' style='cursor: pointer;' ><i class='fa fa-trash' aria-hidden='true'></i></ed>"
			]);
		}
		$('#vendorDiv').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#saveblock').css('display', 'block');
		// $('#SUBMIT_BTN').css('display', 'none');
	}
};

function mydeleteFunction(x) {
	//debugger;
	//x.parentElement.parentElement.remove();
	var table = $('#VENDOR_TBL').DataTable();
	var removingRow = $(x).closest('tr');
	table.row(removingRow).remove().draw();
}
/*var elementLov = '<option value="" selected>Select</option>';
for(var z = 0; z < elemlovs.length; z++) {
	elementLov += ' <option value="' + elemlovs[z].elementid + '"> ' + elemlovs[z].name + '</option>';
}*/
var vendorLov = '<option value="" selected>Select</option>';
for(var z = 0; z < venderlov.length; z++) {
	vendorLov += ' <option value="' + venderlov[z].vendorid + '"> ' + venderlov[z].vendorname + '</option>';
}

function addRowVENDOR() {
	var row = ['<select id="vendorid[' + (counter) + ']"  name="vendorPersonMapping[' + (counter) + '].vendorid" class="vendorIdClass w3-select"  onchange="vendorchange(this,' + (counter) + ')" >' + vendorLov + '</select>', '<select id="PersonNumber' + (counter) + '" onchange="PersonNumberchange(this,' + (counter) + ')" class="w3-select w3-border personNumberClass" name="vendorPersonMapping[' + (counter) + '].personnumber"><option id="2" value="" selected></option><option value="search" data-toggle="modal" id="btnsearchPerson">Search....</option></select>', '<input type="text" id="natureofEmp' + (counter) + '"  name="vendorPersonMapping[' + (counter) + '].natureofemployement" class="natureOfEmpClass "  Readonly>', '<input type="text" id="isactive' + (counter) + '  name="vendorPersonMapping[' + (counter) + '].isactive" class="isactiveClass " value="Y" Readonly>' + '<input type="hidden" id="vendorMappingName' + (counter) + '"  name="vendorPersonMapping[' + (counter) + '].vendorname" class="vendorNameClass" >' + '<input type="hidden" id="PersonName' + (counter) + '"  name="vendorPersonMapping[' + (counter) + '].personname" class="personNameClass" >' + '<input type="hidden" id="personmappid' + (counter) + '"  name="vendorPersonMapping[' + (counter) + '].personmappid" class="PersonMappingIdClass" value="0">', '<ed id="remove" class="myselect" onclick="mydeleteFunction(this)" style="cursor: pointer;" ><i class="fa fa-trash" aria-hidden="true"></i></ed>', ];
	var table = $('#VENDOR_TBL').DataTable();
	table.row.add(row).draw(false);
	table.order([1, 'desc']).draw();
	counter++;
	$('#saveblock').css('display', 'block');
}
var selectElementName = "";

function vendorchange(x, id) {
	selectElementName = $(x).children("option:selected").text();
	document.getElementById("vendorMappingName" + id).value = selectElementName;
}
$(document).on('click').unbind();
$(document).ready(function() {
	var table = $('#VENDOR_TBL').DataTable();
	$('#VENDOR_TBL tbody').on('click', 'tr', function() {
		if($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	$('#remove').click(function() {
		table.row('.selected').remove().draw(false);
		counter--;
	})
});
var urlsaveVendor = "/vendordetails/saveVendorPersonMapping";

function saveVendorPersonMapping(x) {
	//debugger;
	$(x).css("display", "none");
	var PersonMappingId = $(".PersonMappingIdClass");
	var vendorId = $(".vendorIdClass");
	var personNumber = $(".personNumberClass");
	var personName = $(".personNameClass");
	var isactive = $(".isactiveClass");
	var natureOfEmp = $(".natureOfEmpClass");
	var vendorName = $(".vendorNameClass");
	for(var i = 0; i < PersonMappingId.length; i++) {
		$(PersonMappingId[i]).attr("name", "vendorPersonMapping[" + i + "].personmappid");
		$(vendorId[i]).attr("name", "vendorPersonMapping[" + i + "].vendorid");
		$(personNumber[i]).attr("name", "vendorPersonMapping[" + i + "].personnumber");
		$(personName[i]).attr("name", "vendorPersonMapping[" + i + "].personname");
		$(isactive[i]).attr("name", "vendorPersonMapping[" + i + "].isactive");
		$(natureOfEmp[i]).attr("name", "vendorPersonMapping[" + i + "].natureofemployement");
		$(vendorName[i]).attr("name", "vendorPersonMapping[" + i + "].vendorname");
	}
	var form_data = $("#VENDOR_PERSON_MAPPING").serialize();
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
		url: urlsaveVendor,
		type: "POST",
		data: form_data,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(data) {
			if(data.status  == "Success"){
			
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Vendor person mapping successfully.",
                    callback:function() {
                        $('#replace_div').load("/vendordetails/searchVendorPerson");
                    }
                });
            }
			if(data.status == "Error"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Unable to map vendor person.",
                    callback:function() {
                        $('#replace_div').load("/vendordetails/searchVendorPerson");
                    }
                });
            }
		},
		error: function(data) {
			 bootbox.alert({
                size: 'medium',
                title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                message:"Something went wrong please try again.",
                callback:function() {
                     $('#replace_div').load("/vendordetails/searchVendorPerson");
                }
            });
		}
	});
}
/****************************************************************************/
$('#PersonNumber').on('change', function() {
	var selectObject = $(this).children("option:selected").val();
	if(selectObject == 'search') {
		$('#id02').css("display", "block");
	} else if(selectObject != 'search') {
		$('#id02').css("display", "none");
	}
});
var jsonUrlPer = '/accomodation/searchAccomodation/gePersonId';
var perid = '';
var perName = '';
$("#CR_PER_POP_SEARCH").click(function() {
	perid = $("#CR_PER_POP_ID").val();
	perName = $("#CR_PER_POP_Name").val();
	$('#resultSecPerson').css('display', 'none');
	$('#noDataPerson').css('display', 'none');
	$('#jsonLoader').css('display', 'block');
	loadPersonData();
	//$('#resultSecPerson').css('display', 'block');
});

function loadPersonData() {
	$.ajax({
		type: 'POST',
		url: jsonUrlPer,
		dataSrc: '',
		data: {
			"personNumber": perid,
			"name": perName,
			"personId": '',
			"noe": ''
		},
		dataType: 'json',
		success: function(data) {
			jsonData = data;
			populatePersonDataTable(jsonData);
		},
		error: function(e) {
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}

function populatePersonDataTable(data) {
	$("#PersonAccomodationList").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0) {
		$('#resultSecPerson').css('display', 'none');
		$('#jsonLoader').css('display', 'none');
		$('#noDataPerson').css('display', 'block');
	} else {
		for(var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#PersonAccomodationList").dataTable().fnAddData([
				dat.personId,
				dat.personNumber,
				dat.name,
				dat.noe,
			]);
		}
		$('#resultSecPerson').css('display', 'block');
		$('#noDataPerson').css('display', 'none');
		$('#jsonLoader').css('display', 'none');
	}
}
$(document).on('click', 'ed', function(e) {
	var url = $(this).attr("rm");
	$('#replace_div').load(url);
});
var PER_ID = '';

function PersonNumberchange(x, prsnid) {
	//$('#id02').css("display", "block");
	//$('#id03').css("display","none");
	PER_ID = prsnid;
	var selectObject = $("#PersonNumber" + prsnid).children("option:selected").val();
	//	var selectObject=$(x+prsnid).children("option:selected").val();
	if(selectObject == 'search') {
		$('#id02').css("display", "block");
		$('#CR_PER_POP_OK').css("display", "none");
	} else if(selectObject != 'search') {
		$('#id02').css("display", "none");
		$('#CR_PER_POP_OK').css("display", "none");
	}
}
$(document).ready(function() {
	$("#CR_PER_POP_CANCEL").on('click', function(e) {
		$('#id02').css("display", "none");
		$('#resultSecPerson').css('display', 'none');
		$("#PersonAccomodationList").DataTable().clear();
		$("#CR_PER_POP_ID").val("");
		$("#CR_PER_POP_Name").val("");
		$('#CR_PER_POP_OK').css("display", "none");
		$('#PersonNumber' + PER_ID).val("");
		$('#PersonName' + PER_ID).val("");
		$('#natureofEmp' + PER_ID).val("");
		$('#PersonNumber' + PER_ID).children('option[id="1"]').prop('selected', true);
	});
	var table = $('#PersonAccomodationList').DataTable();
	$('#PersonAccomodationList tbody').on('click', 'tr', function() {
		var tbldata = $(this).children('td').map(function() {
			return $(this).text();
		}).get();
		if($(this).hasClass('selected')) {
			//$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		///var mgr_id=$(this).children('select').attr('id');
		//alert(MGR_ID);
		var dtData = tbldata[1];
		var dtDataId = tbldata[2];
		var dtDataNOE = tbldata[3];
		$('#PersonNumber' + PER_ID).val(dtData);
		$('#PersonNumber' + PER_ID).children('option[id="2"]').text(dtDataId);
		$('#PersonNumber' + PER_ID).children('option[id="2"]').val(dtData);
		$('#PersonNumber' + PER_ID).children('option[id="2"]').prop('selected', true);
		$('#PersonName' + PER_ID).val(dtDataId);
		$('#natureofEmp' + PER_ID).val(dtDataNOE);
		$('#CR_PER_POP_OK').css("display", "block");
	});
});
$('#CR_PER_POP_OK').on('click', function(e) {
	$('#resultSecPerson').css('display', 'none');
	$('#id02').css("display", "none");
});