
$(document).ready(function() {
   
    searchDataVendor();
});

function addBtnClick() {
    //debugger;
    var url = "/vendordetails/createVendor";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $('#replace_div').load(url);
}

function searchDataVendor() {
 
    $.ajax({
        type: "GET",
        url: "/vendordetails/getAllVenderSearchListData",
        contentType: "application/json",
        dataType: "json",
        success: function(result) {
            //console.log("in search data...success....");
            populateVendorSearchTable(result);
        },
        error: function(e) {
            //console.log("in search data...error....");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}

function populateVendorSearchTable(data) {
 
    $("#searchVendortable").DataTable().clear();
    $("#searchVendortable").DataTable().columns.adjust();
    /*$("#searchVendortable").DataTable({columnDefs:[{targets:-1,className: 'dt-body-center'}]});*/

    var dataLength = data.length;
    //console.log("Here------------"+dataLength);
    if (dataLength == 0) {
        $('#resultVendorSec').css('display', 'none');
        $('#noDataVandor').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];           
            $("#searchVendortable").dataTable().fnAddData([
                dat.vendorid,
                dat.vendorname,
                dat.vendorno,
                dat.vendorcode,
                dat.vendoraddress,
                dat.status,
				"<ed rm='/vendordetails/edit/editVendor/correctVendor/"+dat.id+"' id='EDIT_VENDOR' class='editUser' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>",               

            ]);
        }
        $('#resultVendorSec').css('display', 'block');
        $('#noDataVandor').css('display', 'none');
    }
}


$(document).on('click').unbind();
$(document).on('click', 'ed', function(e){
	var url = $(this).attr("rm");
	$('#replace_div').html("<div style='margin-left:-16px; margin-right:-16px; margin-top:0px; width: 100%; margin-top: 200px; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: gray;'></i></div>");
	$('#replace_div').load(url);
});

