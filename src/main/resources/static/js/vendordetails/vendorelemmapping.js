var vendorid = '';

function vendorElemMapping() {
	vendorid = $('#vendorid').val();
	var curl = "/vendordetails/searchVendorElement/" + vendorid;
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
	//counter = dataLength;
	if (dataLength == 0) {
		$('#vendorDiv').css('display', 'block');
		$('#noData').css('display', 'none');
		$('#saveblock').css('display', 'none');
	} else {
		for (var i = 0; i < dataLength; i++) {
			var dat = data[i];
			$("#VENDOR_TBL").dataTable().fnAddData([
				dat.vendorname + '<input type="hidden" name="vendorMapping[' + (i) + '].vendorid" class="vendordetailsClass"  value="' + dat.vendorid + '">' + '<input type="hidden" name="vendorMapping[' + (i) + '].vendorname" class="vendorNameClass"  value="' + dat.vendorname + '">',
				dat.elementname + '<input type="hidden" name="vendorMapping[' + (i) + '].element" class="elementClass"  value="' + dat.element + '">' + '<input type="hidden" name="vendorMapping[' + (i) + '].elementname" class="elementnameClass" value="' + dat.elementname + '">' + '<input type="hidden" name="vendorMapping[' + (i) + '].elementmappid" class="elementmappidClass" value="' + dat.elementmappid + '">',
				dat.isactive + '<input type="hidden" name="vendorMapping[' + (i) + '].isactive" class="isactiveClass" value="' + dat.isactive + '">',
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
	var t = $("#VENDOR_TBL").dataTable();
	//	alert("bbbb"+t.fnGetData().length);
	//x.parentElement.parentElement.remove();
	//	alert("aaa"+t.fnGetData().length);
	var table = $('#VENDOR_TBL').DataTable();
	var removingRow = $(x).closest('tr');
	table.row(removingRow).remove().draw();
}
var elementLov = '<option value="" selected>Select</option>';
for (var z = 0; z < elemlovs.length; z++) {
	elementLov += ' <option value="' + elemlovs[z].elementid + '"> ' + elemlovs[z].name + '</option>';
}
var vendorLov = '<option value="" selected>Select</option>';
for (var z = 0; z < venderlov.length; z++) {
	vendorLov += ' <option value="' + venderlov[z].vendorid + '"> ' + venderlov[z].vendorname + '</option>';
}

function addRowVENDOR() {
	var row = [
		'<select name="vendorMapping[' + (counter) + '].vendordetails" class="vendordetailsClass w3-select" onchange="elementchange(this,' + (counter) + ')" >' + vendorLov + '</select>',
		'<select id="vendorMappingId[' + (counter) + ']"  name="vendorMapping[' + (counter) + '].element" class="elementClass w3-select"  onchange="elementchange(this,' + (counter) + ')" >' + elementLov + '</select>',
		'<input type="text" id="isactive' + (counter) + '  name="vendorMapping[' + (counter) + '].isactive" class="isactiveClass " value="Y" Readonly>',
		'<ed id="remove" class="myselect" onclick="mydeleteFunction(this)" style="cursor: pointer;" ><i class="fa fa-trash" aria-hidden="true"></i></ed>' + '<input type="hidden" id=vendorMappingName' + (counter) + '  name="vendorMapping[' + (counter) + '].elementname" class="elementnameClass" >' + '<input type="hidden" id=vendorName' + (counter) + '  name="vendorMapping[' + (counter) + '].vendorname" class="vendorNameClass" >',
		'<input type="hidden" id="MappingId' + (counter) + '  name="vendorMapping[' + (counter) + '].elementmappid" class="elementmappidClass" value="0">',
	];
	var table = $('#VENDOR_TBL').DataTable();
	table.row.add(row).draw(false);
	table.order([1, 'desc']).draw();
	counter++;
	$('#saveblock').css('display', 'block');
}
var selectElementName = "";

function elementchange(x, id) {
	selectElementName = $(x).children("option:selected").text();
	document.getElementById("vendorMappingName" + id).value = selectElementName;
}

var selectvendorName = "";
function vendorchange(x, id) {
	selectvendorName = $(x).children("option:selected").text();
	document.getElementById("vendorName" + id).value = selectvendorName;
}


$(document).on('click').unbind();

$(document).ready(function() {
	var table = $('#VENDOR_TBL').DataTable();
	$('#VENDOR_TBL tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
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

var urlsaveVendor = "/vendordetails/saveVendorMapping";
function saveVendorElem(x) {
//debugger;
	$(x).css("display", "none");
	var velndorDetails = $(".vendordetailsClass");
	var element = $(".elementClass");
	var elementMapId = $(".elementmappidClass");
	var elementname = $(".elementnameClass");
	var isactive = $(".isactiveClass");
	var vendorNameClass = $(".vendorNameClass");

	for (var i = 0; i < velndorDetails.length; i++) {
		$(velndorDetails[i]).attr("name", "vendorMapping[" + i + "].vendorid");
		$(element[i]).attr("name", "vendorMapping[" + i + "].element");
		$(elementMapId[i]).attr("name", "vendorMapping[" + i + "].elementmappid");
		$(elementname[i]).attr("name", "vendorMapping[" + i + "].elementname");
		$(isactive[i]).attr("name", "vendorMapping[" + i + "].isactive");
		$(vendorNameClass[i]).attr("name", "vendorMapping[" + i + "].vendorname");
	}
	var form_data = $("#VENDORMapping").serialize();
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");

	$.ajax({
		url: urlsaveVendor,
		type: "POST",
		data: form_data,
		cache: false,
		contentType: "application/x-www-form-urlencoded",
		processData: false,
		success: function(data) {	
			
			if(data.status == "Success"){			
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Vendor element mapping successfully.",
                    callback:function() {
                       $('#replace_div').load("/vendordetails/searchVendorElement");
                    }
                });
            }
			if(data.status == "Error"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Unable to map vendor element.",
                    callback:function() {
                       $('#replace_div').load("/vendordetails/searchVendorElement");
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
                      $('#replace_div').load("/vendordetails/searchVendorElement");
                }
            });
		}
	});
}