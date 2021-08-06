//alert("hi cda search");
$(document).ready(function() {
   
    searchDataCda();
});

function loadCreateCDA() {
    //debugger;
    var url = "/cdaVehicle/CreateCda";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $('#replace_div').load(url);
}

function searchDataCda() {
 
    $.ajax({
        type: "GET",
        url: "/cdaVehicle/getCdasearchdataList",
        contentType: "application/json",
        dataType: "json",
        success: function(result) {
            //console.log("in search data...success....");
            populateCdaSearchTable(result);
        },
        error: function(e) {
            //console.log("in search data...error....");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}

function populateCdaSearchTable(data) {
 
    $("#searchCdatable").DataTable().clear();
    $("#searchCdatable").DataTable().columns.adjust();
    /*$("#searchCdatable").DataTable({columnDefs:[{targets:-1,className: 'dt-body-center'}]});*/

    var dataLength = data.length;
    //console.log("Here------------"+dataLength);
    if (dataLength == 0) {
        $('#resultCda').css('display', 'none');
        $('#noCdaData').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            var str = "";
            var view = '<i class="fa fa-eye w3-padding" id="view" onclick="viewBtnFunc(' + dat.claimid + ',\'view\')">';
            if (dat.status == "Saved" || dat.status == "saved") {
                str = '<i class="fa fa-pen" id="edit" onclick="viewBtnFunc(' + dat.claimid + ',\'edit\')">';
            } else {
                str = '<i class="fa fa-pen" id="edit" style="color:grey">';
            }
            $("#searchCdatable").dataTable().fnAddData([
                dat.createddate.substring(0, 10),
                dat.claimid,
                dat.cdaclaimamount,
                dat.vehicleallowanceamount,
                dat.vehicleused,
                dat.noofmonths,
                dat.approvedamt,
                dat.status,
                '<i class="fa fa-eye" id="view" onclick="viewBtnFunc(' + dat.claimid + ',\'view\')">',
                str
                //'<i class="fa fa-pen" id="edit" onclick="viewBtnFunc('+dat.claimid+',\'edit\')">',
                //"<ed rm='cdaTelephone/edit/editCda/correctcda/"+dat.cdavehicleid+"' id='EDIT_CDA' class='editUser' style='cursor: pointer'><i class='fa fa-pen' aria-hidden='true'></i></ed>"


            ]);
        }
        $('#resultCda').css('display', 'block');
        $('#noCdaData').css('display', 'none');
    }
}

function viewBtnFunc(x, m) {
    //debugger;
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
        type: "GET",
        url: "/cdaVehicle/edit/editCda/correctcda/" + x + "/" + m,
        success: function(result) {
            console.log("sucesssssss");
            $('#replace_div').html(result);
			var _href=$("#DOWNLOAD_LINK").attr("href");
			if(_href!=undefined && _href!="" && _href!=null){
				_href=_href.replaceAll('/', "FORWARD_SLASH");
				_href=_href.replaceAll('\\','BACKWARD_SLASH');
				_href=_href.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK").attr("href","/getContent?location="+$("#DOWNLOAD_LINK").attr("href"));
			}
			var _href1=$("#DOWNLOAD_LINK1").attr("href");
			if(_href1!=undefined && _href1!="" && _href1!=null){
				_href1=_href1.replaceAll('/', "FORWARD_SLASH");
				_href1=_href1.replaceAll('\\','BACKWARD_SLASH');
				_href1=_href1.replaceAll('.','EXT_DOT');

				$("#DOWNLOAD_LINK1").attr("href","/getContent?location="+$("#DOWNLOAD_LINK1").attr("href"));
			}
        },
        error: function(e) {
            //alert( "ERROR : "+ JSON.stringify(e) );
        }
    });

}