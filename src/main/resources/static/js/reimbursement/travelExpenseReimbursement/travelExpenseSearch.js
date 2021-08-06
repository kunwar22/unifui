
$(document).ready(function() {
   
    searchDatatravelExpense();
});

function addBtnClick() {
    //debugger;
    var url = "/reimbursement/travelExpenseClaim";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $('#replace_div').load(url);
}

function searchDatatravelExpense() {
 
    $.ajax({
        type: "GET",
        url: "/reimbursement/getTravelSearchData",
        contentType: "application/json",
        dataType: "json",
        success: function(result) {
            //console.log("in search data...success....");
            populateTravelExpenseSearchTable(result);
        },
        error: function(e) {
            //console.log("in search data...error....");
            console.log("ERROR : " + JSON.stringify(e));
        }
    });
}

function populateTravelExpenseSearchTable(data) {
 
    $("#searchtraveltable").DataTable().clear();
    $("#searchtraveltable").DataTable().columns.adjust();
    /*$("#searchtraveltable").DataTable({columnDefs:[{targets:-1,className: 'dt-body-center'}]});*/

    var dataLength = data.length;
    //console.log("Here------------"+dataLength);
    if (dataLength == 0) {
        $('#resultTravelSec').css('display', 'none');
        $('#noDataTravel').css('display', 'block');
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
            $("#searchtraveltable").dataTable().fnAddData([
                dat.claimid,
                dat.travelingdatefrom,
                dat.travelingdateto,
                dat.firstcountry,
                dat.secondcountry,
                dat.firstcity,
                dat.secondcity,
                dat.claimamount,
                dat.approvedamt,
                dat.status,
                '<i class="fa fa-eye" id="view" onclick="viewBtnFunc(' + dat.claimid + ',\'view\')">',
                str

            ]);
        }
        $('#resultTravelSec').css('display', 'block');
        $('#noDataTravel').css('display', 'none');
    }
}

function viewBtnFunc(x, m) {
    //debugger;
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
        type: "GET",
        url: "/reimbursement/viewtravelExpensedata/byclaimId/" + x + "/" + m,
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