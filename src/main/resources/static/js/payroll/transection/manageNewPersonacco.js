
$(document).ready(function(){
	$('#011').css("display", "none");
        $('#012').css("display", "none");
        $('#013').css("display", "none");
        $('#QuarterRecovery_amt').val("");
		$('#leaseRecovery_amt').val("");
		$('#pmtToVendor_amt').val("");
   
});



var PersonIDs='';

$('#cancilAccomodation').on('click', function () {
    url = '/manageaccommodation';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});

function loadCreatefunction() {

    var url = "/accomodation/rent/createaccommodation";
    $('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
    $('#replace_div').load(url);
}

/*var PERSON_NUM = '';
var RFS_TYP = '';
var STRT_DT = '';*/

var jsonUrl = '';

jsonUrl = '/seach/accomodation/searchRentAccomodation';

/*$("#btnSearchaccomodation").on('click', function () {
    // PERSON_NUM = $('#PersonNumber').val();
    // RFS_TYP = $('#rfa_type').val();
    // STRT_DT = $("#Start_Date").val();


    /!*  $('#resultSec').css('display', 'none');
      $('#noData').css('display', 'none');
      $('#jsonLoaderPage').css('display', 'block');*!/
    // getaccomodationlist();

});*/

function getaccomodationlist() {

    var PERSON_NUM = $('#PersonNumber').val();
    var RFS_TYP = $('#rfa_type').val();
    var STRT_DT = $('#Start_Datess').val();

    if (STRT_DT == null || STRT_DT == '') {

        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
            message: "Please select the mandatory field before search.",

        });
        return;


        // return;
    } else if (STRT_DT != null || STRT_DT != '') {
        $('#resultSec').css('display', 'none');
        $('#noData').css('display', 'none');
        $('#jsonLoaderPage').css('display', 'block');

        $.ajax({
            type: 'POST',
            url: jsonUrl,
            dataSrc: '',
            data: {
                "personnumber": PERSON_NUM,
                "rfaid": RFS_TYP,
                "startdt": STRT_DT

            },
            dataType: 'json',
            success: function (data) {
                jsonData = data;

                populateAccomodationDataTable(jsonData);
                $('#paygroupLoader').css('display', 'none');
            },
            error: function (e) {
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }
}

// className: 'excelButton',
function populateAccomodationDataTable(data) {
    $("#accomodationList").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultSec').css('display', 'none');
        $('#noData').css('display', 'block');
        $('#jsonLoaderPage').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#accomodationList").dataTable().fnAddData([
                dat.personNumber,
                dat.personName,
                dat.accomodationType,
                dat.startdate,
                dat.enddate,
                dat.pmtToVendor_amt,
                dat.leaseRecovery_amt,
                dat.qtrRentRecovery_amt,

            ]);
        }
        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoaderPage').css('display', 'none');
    }
}


/**************************Pop-up  SEARCH start here****************************************/




var jsonUrlPer = '/accomodation/searchAccomodation/gePersonId';

var personid = '';
var personName = '';

$("#CR_PER_POP_SEARCH").click(function () {
    personid = $("#CR_PER_POP_ID").val();
    personName = $("#CR_PER_POP_Name").val();
    $('#resultSecPerson').css('display', 'none');
    $('#noDataPerson').css('display', 'none');
    $('#jsonLoader').css('display', 'block');
    loadLegData();
    //$('#resultSecPerson').css('display', 'block');
});


function loadLegData() {

    $.ajax({
        type: 'POST',
        url: jsonUrlPer,
        dataSrc: '',
        data: {
            "personNumber": personid,
            "name": personName,
            "personId": '',
            "noe": ''

        },
        dataType: 'json',
        success: function (data) {
            jsonData = data;
            populatePersonDataTable(jsonData);
        },
        error: function (e) {
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}


function populatePersonDataTable(data) {

    $("#PersonAccomodationList").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#resultSecPerson').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noDataPerson').css('display', 'block');
    } else {
        for (var i = 0; i < dataLength; i++) {
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


$(document).on('click', 'ed', function (e) {
    var url = $(this).attr("rm");
    $('#replace_div').load(url);
});


/************************************************** */
$('#PersonNumber').on('change', function () {

    var selectObject = $(this).children("option:selected").val();
    if (selectObject == 'search') {
        $('#id02').css("display", "block");
    } else if (selectObject != 'search') {
        $('#id02').css("display", "none");
    }
});

$('#accomodationTypeId').on('change', function () {

    var rfatypes = $(this).children("option:selected").val();
     if (rfatypes == '208') {
        $('#011').css("display", "block");
        $('#012').css("display", "block");
        $('#013').css("display", "none");
        $('#QuarterRecovery_amt').val("0.0");
        $('#leaseRecovery_amt').val("0.0");
		$('#pmtToVendor_amt').val("0.0");

    }
else if (rfatypes == '209') {
        $('#011').css("display", "none");
        $('#012').css("display", "none");
        $('#013').css("display", "block");
        $('#QuarterRecovery_amt').val("0.0");
		$('#leaseRecovery_amt').val("0.0");
		$('#pmtToVendor_amt').val("0.0");

    }

else if (rfatypes == '210') {
        $('#011').css("display", "none");
        $('#012').css("display", "none");
        $('#013').css("display", "none");
        $('#QuarterRecovery_amt').val("0.0");
		$('#leaseRecovery_amt').val("0.0");
		$('#pmtToVendor_amt').val("0.0");

    }
});

var leaseId = '';
/*******************************Popup table selected row END*******************************************/
function ajaxPost() {
    leaseId = $('#Start_Date').val();

    if (leaseId == 0) {
        loadSavelease();
    }

};

function loadSavelease(x,mode) {
	
	$(x).css("display","none");	
	$("#callculateamt").val("0.0");
	$("#overrideamt").val("0.0");
	$('#enddate').val("4712-12-31");
	/*let calamt=$("#callculateamt").val();
	let overrideamt=$("#overrideamt").val();
	
	if(calamt==""||calamt==''||calamt==null){
		 bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message: 'Plese click on calculate button before submit.',
                    
                });
            $(x).css("display","block");	
			return;
	}
	
	if(overrideamt==""||overrideamt==''||overrideamt==null){
		$('#overrideamt').val("0.0");	
	}*/

	
    var fd = $("#Accomodation_save").serialize();
 $("#REPORTS_LOADER").css("display", "block");
    $.ajax({
        url: "/accomodation/saveAccomodation/"+mode,
        type: "POST",
        data: fd,
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success: function (data) {
            $('#replace_div').html(data);
            if (resultfinal == "success") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message: 'You have successfuly Save Rent Accomodation .',
                    callback: function () {
                        $('#replace_div').load("/manageaccommodation");
                    }
                });
            } else if (resultfinal == "data already exist") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message: 'Accomodation already applied for this period.',
                    callback: function () {
                        $('#replace_div').load("/accomodation/rent/createaccommodation");
                    }
                });
            } /*else if (resultfinal != "success") {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message: 'You have Unable to Save Rent Accomodation.',
                    callback: function () {
                        $('#replace_div').load("/accomodation/rent/createaccommodation");
                    }
                });
            }*/
			 $("#REPORTS_LOADER").css("display", "none");
        },
        error: function (data) {
	 $("#REPORTS_LOADER").css("display", "none");
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Something went wrong. Please try again."
            });
            $('#replace_div').html(data);
        }
    });

};
/*********************************************************************************************************************/
$(document).ready(function () {

    $('#013').css("display", "none");
    $('#13').val("");
    var Rfa = $('#accomodationTypeId').val();

    if (Rfa == '210') {
        $('#pmtToVendor_amt').prop('disabled', true);
        $('#leaseRecovery_amt').prop('disabled', true);
        $('#013').css("display", "none");
        $('#013').prop('disabled', true);
    }


    $('#btnsearchperson').on('click', function (e) {
        $('#id02').css("display", "block");

    });


    $('#CR_PER_POP_CANCEL').on('click', function (e) {
        $('#id02').css("display", "none");
        $('#resultSecPerson').css("display", "none");
        $('#PersonNumber').children('option[id="1"]').prop('selected', true);
        $('#pname').text("");
		$('#personName').val("");
    });

    var table = $('#PersonAccomodationList').DataTable();



    $('#PersonAccomodationList tbody').on('click', 'tr', function () {

        var tbldata = $(this).children('td').map(function () {
            return $(this).text();
        }).get();

        if ($(this).hasClass('selected')) {
            // $(this).removeClass('selected');

        } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');


            var dtData = tbldata[2];
            var dtDataId = tbldata[1];
            $('#PersonNumber').val(dtData);
            $('#PersonNumber').children('option[id="2"]').text(dtDataId);
            $('#PersonNumber').children('option[id="2"]').val(dtDataId);
            $('#PersonNumber').children('option[id="2"]').prop('selected', true);

            $('#pno').text(dtDataId);
            $('#pname').text(dtData);
            $('#personName').val(dtData);
            $('#personName').val(dtData);
            $('#CR_PER_POP_OK').css('display', 'inline');

            PersonIDs=dtDataId;
        }

    });

    $('#accomodationList').DataTable( {
        dom: 'Bfrtip',
        buttons: [{
            "extend": 'excel',
            "text": 'Export to Excel',
            'className': 'btn btn-success'
        }],
        "columnDefs": [
            { "orderable": false, "targets": 0 },
            { "orderable": false, "targets": 7 },
        ],
        destroy: true,
    } );

    $('#CR_PER_POP_OK').on('click', function (e) {
        $('#id02').css("display", "none");
        $('#resultSecPerson').css("display", "none");
        $('#CR_PER_POP_OK').css("display", "none");

        getaccomoList(PersonIDs);

    });

});



var PERSON_NUM1 = "";
var RFS_TYP1 = "";
var STRT_DT1 ="";




function getaccomoList(x) {

     PERSON_NUM1 = x;
     RFS_TYP1 = "";
     STRT_DT1 ="";

    $.ajax({
        type: 'POST',
        url: '/seach/accomodation/searchRentAccomodation/addnewperson',
        dataSrc: '',
        data: {
            "personnumber": PERSON_NUM1,
            "rfaid": RFS_TYP1,
            "startdt": STRT_DT1

        },
        dataType: 'json',
        success: function (data) {
            jsonData = data;

            populateAccoTable(jsonData);
            $('#jsonLoaderPage').css('display', 'none');
        },
        error: function (e) {
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });

}


function populateAccoTable(data) {

    $("#accoHistoryList").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if (dataLength == 0) {
        $('#HistoryresultSec').css('display', 'none');
        $('#HistorynoData').css('display', 'block');
        $('#jsonLoaderPage').css('display', 'none');
    } else {
        for (var i = 0; i < dataLength; i++) {
            var dat = data[i];
            $("#accoHistoryList").dataTable().fnAddData([
                dat.personNumber,
                dat.personName,
                dat.accomodationType,
                dat.startdate,
                dat.enddate,
                dat.pmtToVendor_amt,
                dat.leaseRecovery_amt,
                dat.qtrRentRecovery_amt,

            ]);
        }

        $('#HistoryresultSec').css('display', 'block');
        $('#HistorynoData').css('display', 'none');
        $('#jsonLoaderPage').css('display', 'none');
    }

}
