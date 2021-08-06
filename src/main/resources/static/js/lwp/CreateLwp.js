/*******************added by rajat on 28-12-20 start********************************************/

var flagx1 = [];
var flagx2 = [];
var flagDescrx1 = [];


function addMultiledataTable() {


    var iteratorrow = document.getElementsByClassName("newRowClass");
    var myfromdate = document.getElementsByClassName("fromdateClass");
    var mytodate = document.getElementsByClassName("todateClass");

    if (iteratorrow.length > 1) {
        for (var i = 0; i < iteratorrow.length - 1; i++) {
            var fromvalue = myfromdate[i].value;
            var todatevalue = mytodate[i].value;

            if (todatevalue == "" || todatevalue == 'undefined') {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
                    message: "ToDate not found. All ToDate fields should be filled.",
                });
                $("#LWP_FROM_DATE" + (iteratorrow.length-1)).val("");
            } /*else if (todatevalue != "" || todatevalue != 'undefined') {
                addMultipleDate()
            }*/
        }
     //   if (todatevalue != "" || todatevalue != 'undefined') {
            addMultipleDate()
       // }
    }
    else  if(iteratorrow.length <= 1){
        var fromvalue = myfromdate[0].value;
        var todatevalue = mytodate[0].value;
        if (todatevalue == "" || todatevalue == 'undefined') {
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
                message: "ToDate not found. All ToDate fields should be filled.",
            });
            $("#LWP_FROM_DATE0" ).val("");
        }
        else if(todatevalue != "" || todatevalue != 'undefined'){
            addMultipleDate();
        }

    }

}


function addMultipleDate() {


    var j = 1;
    var data = "";
    data = '<tr class="myrowclass newRowClass">' +
        '<td style="width:30%"><input style="height: 38px" onclick="fromclick()" onchange="fromchange()"  id="LWP_FROM_DATE' + window.globalCounter + '" class="w3-input w3-border myclassfromdate validationfrom fromdateClass" name="periodfrom[' + window.globalCounter + ']" type="date" ></td>' +
        '<td style="width:30%"><input style="height: 38px" onclick="toclick()" onchange="tochange()"  id="LWP_TO_DATE' + window.globalCounter + '" class="w3-input w3-border mycolclasstodate validationto todateClass" name="periodto[' + window.globalCounter + ']" type="date" disabled ></td>' +
        '<td style="width:30%"><input style="height: 38px"  id="LWP_DESCR' + window.globalCounter + '" class="w3-input w3-border myclassdesc validationdescr" name="Description[' + window.globalCounter + ']" type="text" ></td>' +
        '<td style="width:5%"><input class="w3-btn w3-theme" id="delete" index="0" flg="date" type="button" value="x"/></td></tr>';
    $("#Add_Multiple_Date_TBL tbody").append(data);

    $.each($('#Add_Multiple_Date_TBL tr'), function (index, val) {
        $(this).find("td:eq(0) input[type='date']").attr('name', 'periodfrom[' + (index - 1) + ']');
        $(this).find("td:eq(0) input[type='date']").attr('onchange', 'fromchange(' + (index - 1) + ')');
        $(this).find("td:eq(0) input[type='date']").attr('onclick', 'fromclick(' + (index - 1) + ')');
        $(this).find("td:eq(1) input[type='date']").attr('name', 'periodto[' + (index - 1) + ']');
        $(this).find("td:eq(1) input[type='date']").attr('onchange', 'tochange(' + (index - 1) + ')');
        $(this).find("td:eq(1) input[type='date']").attr('onclick', 'toclick(' + (index - 1) + ')');
        $(this).find("td:eq(2) input[type='text']").attr('name', 'Description[' + (index - 1) + ']');
        $(this).find("td:eq(3) input[type='button']").attr('index', (index - 1));

        $(this).find("td:eq(0) input[type='date']").attr('id', 'LWP_FROM_DATE' + (index - 1));
        $(this).find("td:eq(1) input[type='date']").attr('id', 'LWP_TO_DATE' + (index - 1));
        $(this).find("td:eq(2) input[type='text']").attr('id', 'LWP_DESCR' + (index - 1));


    });

}

$(document).on("click", "#delete", function () {

    var dex = $(this).attr('index');
    var flg = $(this).attr('flg');
    $(this).parents("tr").remove();
    removeRow(dex, flg);
    $.each($('#Add_Multiple_Date_TBL tr'), function (index, val) {
        $(this).find("td:eq(0) input[type='text']").attr('name', 'periodfrom[' + (index - 1) + ']');
        $(this).find("td:eq(1) input[type='text']").attr('name', 'periodto[' + (index - 1) + ']');
        $(this).find("td:eq(2) input[type='text']").attr('name', 'Description[' + (index - 1) + ']');
        $(this).find("td:eq(3) input[type='button']").attr('index', (index - 1));

    });


});

function removeRow(index, flg) {

    var jurl = "/lwp/removechild/" + index + "/" + flg;
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


/*******************added by rajat on 28-12-20 end*******************************************/









var pnum = pnum;


var maxfy = getMaxfy();
var minfy = getMinfy();

function getMinfy() {
    var today = new Date();
    if ((today.getMonth() + 1) <= 3) {
        var minfy = (today.getFullYear() - 1) + "-04-01";
    } else {
        var minfy = today.getFullYear() + "-04-01";
    }
    return minfy;
}

function getMaxfy() {
    var today = new Date();
    if ((today.getMonth() + 1) <= 3) {
        var maxfy = today.getFullYear() + "-03-31";
    } else {
        var maxfy = (today.getFullYear() + 1) + "-03-31";
    }
    return maxfy;
}


var get_from_month = '';
var get_to_month = '';
var val = '';
var endOfMonth = '';

function fromclick(a) {

    var dt = new Date();
    var mon = dt.getMonth() + 1;
    var yea = dt.getFullYear();
    var dat = dt.getDate();

    if (mon < 10) {
        mon = "0" + mon;
    }
    if (dat < 10) {
        dat = "0" + dat;
    }

    var maxi = yea + "-" + mon + "-" + dat;
    $("#LWP_FROM_DATE" + a).attr("max", maxi);
}

function toclick(b) {
    var dt = new Date();
    var mon = dt.getMonth() + 1;
    var yea = dt.getFullYear();
    var dat = dt.getDate();

    if (mon < 10) {
        mon = "0" + mon;
    }
    if (dat < 10) {
        dat = "0" + dat;
    }

    var maxi = yea + "-" + mon + "-" + dat;
    $("#LWP_TO_DATE" + b).attr("max", maxi);
    var from_date = $("#LWP_FROM_DATE" + b).val();
    if (from_date != '') {
        $("#LWP_TO_DATE" + b).attr('min', from_date);
    }
}

function fromchange(c) {

    //val = $(this).val();
    val = c;
    $("#LWP_TO_DATE" + c).attr("max", endOfMonth);
    if (val != "" || val == 0 || val == '0') {
        $('#LWP_TO_DATE' + c).removeAttr('disabled');
        $("#LWP_TO_DATE" + c).val("");
    } else {
        $('#LWP_TO_DATE' + c).prop('disabled', true);
        $("#LWP_TO_DATE" + c).val("");
    }

    var datess = document.getElementById('LWP_FROM_DATE' + c).value;
    var mdate = datess.toString();
    var yearThen = parseInt(mdate.substring(0, 4), 10);
    var monthThen = parseInt(mdate.substring(5, 7), 10);


    get_from_month = monthThen;

    var iteratorrow = document.getElementsByClassName("newRowClass");
    var myfromdate = document.getElementsByClassName("fromdateClass");
    var mytodate = document.getElementsByClassName("todateClass");

    if (iteratorrow.length > 1) {


        for (var i = 0; i < iteratorrow.length - 1; i++) {
            var fromvalue = myfromdate[i].value;
            var todatevalue = mytodate[i].value;

            if (todatevalue == "" || todatevalue == 'undefined') {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
                    message: "ToDate not found. All ToDate fields should be filled.",
                });
                $("#LWP_FROM_DATE" + 1).val("");
            }

            if (new Date(datess) >= new Date(fromvalue) && new Date(datess) <= new Date(todatevalue)) {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
                    message: "Selected date already exist in the previous rows.",
                });
                $("#LWP_FROM_DATE" + (iteratorrow.length-1)).val("");
                //deleterowlatest(iteratorrow.length)
            }


        }
    }


    // checkdatedublicacy();
}




function tochange(d) {
    from = $('#LWP_FROM_DATE' + d).val();
    to = $('#LWP_TO_DATE' + d).val();


    var dates = document.getElementById('LWP_TO_DATE' + d).value;
    var mdate = dates.toString();
    var yearThen = parseInt(mdate.substring(0, 4), 10);
    var monthThen = parseInt(mdate.substring(5, 7), 10);


    get_to_month = monthThen;


    if (get_from_month != get_to_month) {
        $("#LWP_FROM_DATE" + d).val("");
        $("#LWP_TO_DATE" + d).val("");
        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
            message: "Selected date range should be of same month",
        });

    } else if (get_from_month == get_to_month) {

    }


    if (from == '' || from == null) {

        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
            message: "Please select Period from date first.",
        });

    }




    var iteratorrow = document.getElementsByClassName("newRowClass");
    var myfromdate = document.getElementsByClassName("fromdateClass");
    var mytodate = document.getElementsByClassName("todateClass");

    if (iteratorrow.length > 1) {


        for (var i = 0; i < iteratorrow.length - 1; i++) {
            var fromvalue = myfromdate[i].value;
            var todatevalue = mytodate[i].value;

            if (todatevalue == "" || todatevalue == 'undefined') {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
                    message: "ToDate not found. All ToDate fields should be filled.",
                });
                $("#LWP_FROM_DATE" + 1).val("");
            }

            if (new Date(dates) >= new Date(fromvalue) && new Date(dates) <= new Date(todatevalue)) {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
                    message: "Selected date already exist in the previous rows.",
                });
                $("#LWP_FROM_DATE" + (iteratorrow.length-1)).val("");
                $("#LWP_TO_DATE" + (iteratorrow.length-1)).val("");
                $("#LWP_TO_DATE" + (iteratorrow.length-1)).attr("disabled", "disabled");
                //deleterowlatest(iteratorrow.length)
            }


        }
    }



}


/***********************************************************/

function checkdatedublicacy() {

    var iteratorrow = document.getElementsByClassName("newRowClass");
    var myfromdate = document.getElementsByClassName("fromdateClass");
    var mytodate = document.getElementsByClassName("todateClass");

    for (var i = 0; i < myfromdate.length; i++) {
        var a = myfromdate[i].value;

        for (var j = i + 1; j < myfromdate.length; j++) {
            var b = myfromdate[j].value;

            if (a[i] == b[j]) {
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
                    message: "Selected date already exist in the previous rows.",
                });

                return false;
            }
        }
    }

}

/******************************************************/
/*

$("#LWP_FROM_DATE1").click(function () {
    var dt = new Date();
    var mon = dt.getMonth() + 1;
    var yea = dt.getFullYear();
    var dat = dt.getDate();

    if (mon < 10) {
        mon = "0" + mon;
    }
    if (dat < 10) {
        dat = "0" + dat;
    }

    var maxi = yea + "-" + mon + "-" + dat;
    $("#LWP_FROM_DATE1").attr("max", maxi);
    //$("#LWP_FROM_DATE1").attr("min",minfy);
});
$("#LWP_TO_DATE1").click(function () {
    var dt = new Date();
    var mon = dt.getMonth() + 1;
    var yea = dt.getFullYear();
    var dat = dt.getDate();

    if (mon < 10) {
        mon = "0" + mon;
    }
    if (dat < 10) {
        dat = "0" + dat;
    }

    var maxi = yea + "-" + mon + "-" + dat;
    $("#LWP_TO_DATE1").attr("max", maxi);
    var from_date = $("#LWP_FROM_DATE1").val();
    if (from_date != '') {
        $("#LWP_TO_DATE1").attr('min', from_date);
    }
});
*/


/*
$('#LWP_FROM_DATE1').on('change', function () {

    val = $(this).val();

    $("#LWP_TO_DATE1").attr("max", endOfMonth);
    if (val != "") {
        $('#LWP_TO_DATE1').removeAttr('disabled');
        $("#LWP_TO_DATE1").val("");
    } else {
        $('#LWP_TO_DATE1').prop('disabled', true);
        $("#LWP_TO_DATE1").val("");
    }

    var datess = document.getElementById('LWP_FROM_DATE1').value;
    var mdate = datess.toString();
    var yearThen = parseInt(mdate.substring(0, 4), 10);
    var monthThen = parseInt(mdate.substring(5, 7), 10);


    get_from_month = monthThen;
//	alert("month from"+get_from_month);
});


$('#LWP_TO_DATE1').on('change', function () {
    //alert("in");
    from = $('#LWP_FROM_DATE1').val();
    to = $('#LWP_TO_DATE1').val();
    //alert(from);

    var dates = document.getElementById('LWP_TO_DATE1').value;
    var mdate = dates.toString();
    var yearThen = parseInt(mdate.substring(0, 4), 10);
    var monthThen = parseInt(mdate.substring(5, 7), 10);


    get_to_month = monthThen;
    //alert("month to"+get_to_month);

    if (get_from_month != get_to_month) {
        $("#LWP_FROM_DATE1").val("");
        $("#LWP_TO_DATE1").val("");
        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
            message: "Selected date range should be of same month",
        });

    } else if (get_from_month == get_to_month) {

    }


    if (from == '' || from == null) {

        bootbox.alert({
            size: 'medium',
            title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;"></i>',
            message: "Please select Period from date first.",
        });


    }

})*/

/*******************************************************/
//var from = 0;
//var to = 0;
//var Difference_In_Days = '';
//var dates = [];
//var startDate = '';
//var Difference_In_Time = '';
//var description = '';

/*******************************************************/


/*********************************************************/


function addLwp(x,y) {

    $("#LWP_LOADER").css("display","block");
    $('#save').css("display", "none");
    /*
        from = $('#LWP_FROM_DATE1').val();
        to = $('#LWP_TO_DATE1').val();
        description = $('#LWP_DESCR').val();
    s*/

    var iteratorrow = document.getElementsByClassName("myrowclass");
    var myformdate = document.getElementsByClassName("myclassfromdate");
    var mytodate = document.getElementsByClassName("mycolclasstodate");
    var mydescr = document.getElementsByClassName("myclassdesc");


// Here all the elements of the array is being printed.
    for (l = 0; l < iteratorrow.length; l++) {

        var from = myformdate[l].value;
        var to = mytodate[l].value;

        var date1 = new Date(from);
        var date2 = new Date(to);
        var Difference_In_Time = date2.getTime() - date1.getTime();
        var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);
        Difference_In_Days = Difference_In_Days + 1;
        addrow(Difference_In_Days, from, to, mydescr[l].value);
        diff_months();
    }
    submitdata();

}


function diff_months() {

    var a = moment(to);
    var b = moment(from);
    var months = a.diff(b, 'months');
    return months + 1;
}

/**************Added by rajat end here on 23-11-20***************************/


var GobalVAriableFlag='';

function addrow(days, frm, too, desc) {

    //for (j = 0; j < days; j++) {
    var dates = [];
    var startDate = moment(frm, 'YYYY-MM-DD');
    dates.push(startDate.format('YYYY-MM-DD'));
    while (!startDate.isSame(too)) {
        startDate = startDate.add(1, 'days');
        dates.push(startDate.format('YYYY-MM-DD'));
    }
    for (j = 0; j < dates.length; j++) {
        var addr = '<tr id="tableidlwp" class="tableRowClass">' + '<td><input class="w3-input w3-round w3-border" value="' + dates[j] + '" name="createlwp[i].period"type="date" data-date=""  data-date-format="YYYY MM DD"'
            + 'max="9999-12-31" onclick="checkdate(this)">' + '</td>'
            + '<td><input class="w3-input w3-round w3-border"name="createlwp[i].description" value="' + desc + '" type="text" data-date="" data-date-format="YYYY MM DD"' + 'max="9999-12-31"></td>'

            + '<td><input class="w3-input w3-round w3-border"name="createlwp[i].lwpid" value="0" type="text"></td>'
            + '<td><input class="w3-input w3-round w3-border"name="createlwp[i].personnumber" type="text"></td>'
            + '<td><input class="w3-input w3-round w3-border"name="createlwp[i].status" type="text"></td>'
            + '</tr>';

        $("#MGR_DET_TBL tbody").append(addr);
    }

    GobalVAriableFlag= $('#mydivTableid').attr('id');


}


function delrow(e) {
    $(e).parents("tr").remove();
}

function submitdata(x) {
    $("#LWP_LOADER").css("display","block");


    $('#saveData').css({'display':'none'});
    var chk = "ok";

    $.each($('#MGR_DET_TBL tr'), function (index, val) {
        $(this).find("td:eq(0) input[type='date']").attr('name', 'createlwp[' + (index - 1) + '].period');
        /*if($(this).find("td:eq(0) input[type='date']").val()==""){
            chk="no";
            $(this).find("td:eq(0) input[type='date']").css("background-color","red");
        }
        else{
            $(this).find("td:eq(0) input[type='date']").css("background-color","white");
        }*/

        $(this).find("td:eq(1) input[type='text']").attr('name', 'createlwp[' + (index - 1) + '].description');
        $(this).find("td:eq(2) input[type='text']").attr('name', 'createlwp[' + (index - 1) + '].lwpid');
        $(this).find("td:eq(3) input[type='text']").attr('name', 'createlwp[' + (index - 1) + '].personnumber');
        /*$(this).find("td:eq(3) input[type='text']").val(pnum);*/
        $(this).find("td:eq(4) input[type='text']").attr('name', 'createlwp[' + (index - 1) + '].status');
        if (x != 1) {
            $(this).find("td:eq(4) input[type='text']").val('Active');
        } else {
            $(this).find("td:eq(5) input[type='hidden']").attr('name', 'createlwp[' + (index - 1) + '].leaveflag');
        }


    });

    var fd = $("#LWP_SAVE").serialize();
   // var $tr = $(this).closest('div');

    if (chk != "ok") {
        $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
        $('#lblMsg').text("Date Field is Mandatory! ");
        $('#btnOK').toggleClass("w3-button w3-red");
        $('#btnOK').on('click', function (e) {
            //var url=$(this).attr("rm")+"/"+pnum+"/"+pname.replace(/ /g,"_");
            //$('#replace_div').load(url);
            $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "none");
        });
    } else {

        $.ajax({
            url: "../lwp/saveLWP",
            type: "POST",
            data: fd,
            cache: false,
            contentType: "application/x-www-form-urlencoded",
            processData: false,
            success: function (data) {

                if (data.status == "Success") {
                    $("#LWP_LOADER").css("display","none");
                    $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
                    $('#lblMsg').text("Data updated successfully");
                    $('#btnOK').toggleClass("w3-button w3-green");
                    $('#btnOK').on('click', function (e) {
                        var url = $(this).attr("rm");
                        $('#div_replace').load(url);
                    });

                }
                if (data.status == "AlreadyExist") {
                    $("#LWP_LOADER").css("display","none");
                    $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
                    $('#lblMsg').text("Data already exist.");
                    $('#btnOK').toggleClass("w3-button w3-red");

                   /* $('#btnOK').on('click', function (e) {
                        var url = $(this).attr("rm");
                        $('#div_replace').load(url);
                    });*/
                    $('#btnOK').on('click', function (e) {
                        $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "none");
                        $("tr[id='tableidlwp']").remove();
                        $('#save').css("display", "block");
                        //s$('#GobalVAriableFlag').remove();
                    });
                }
                if (data.status == "RollBack") {
                    $("#LWP_LOADER").css("display","none");
                    $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
                    $('#lblMsg').text("Data rollback.");
                    $('#btnOK').toggleClass("w3-button w3-red");
                    $('#btnOK').on('click', function (e) {
                        $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "none");
                        $("tr[id='tableidlwp']").remove();
                        $('#save').css("display", "block");
                        //s$('#GobalVAriableFlag').remove();
                    });
                }
                /*bootbox.alert({
                size: 'medium',
                title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                message:"LWP Recovery has been created successfully",
                callback:function(){


                }*/

                /*  bootbox.alert("Data updated successfully");
                  var url = $(this).attr("rm");
                  $("#div_replace").load("/personedit/managepersonedit");*/


                /*});*/

                /*$('#btnOK').on('click', function(e){
                //var url = $(this).attr("rm")+"/"+pnum+"/"+pname.replace(/ /g,"_");
                var url = $(this).attr("rm");
                $('#div_replace').load(url);


                });*/

                if (data.status == "Error") {
                    $("#LWP_LOADER").css("display","none");
                    $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
                    $('#lblMsg').text("Somthing went wrong. Click OK to continue.");
                    $('#btnOK').toggleClass("w3-button w3-red");
                    $('#btnOK').on('click', function (e) {
                        $("div[id='mydivTableid']").remove();
                        $('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "none");
                    });
                    /*bootbox.alert("Data has already applied ");
                    var url = $(this).attr("rm");
                    $("#div_replace").load("/personedit/managepersonedit");*/
                    /* bootbox.alert({
                         size: 'medium',
                         title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                         message: "LWP Recovery has been created successfully",
                     })*/
                }

            },
            error: function (response) {
                alert(JSON.parse(response.responseText));
            }

        });
       // debugger;
    /*    $("div[id='GobalVAriableFlag']").remove();
        $('#GobalVAriableFlag').remove();*/
    }
  //  $('#'+GobalVAriableFlag).remove();
}

function checkdate(e) {
    var dt = new Date();
    var dtyr = dt.getFullYear();
    var dtyr2 = parseInt(dt.getFullYear()) + 1;
    var maxdt = dtyr2 + "-03-31";
    var mindt = dtyr + "-04-01";

    $(e).attr("max", maxdt);
    $(e).attr("min", mindt);
}

function checkrow(e) {
    if ($(e).prop("checked")) {
        $(e).closest("tr").find(".lwpsts").val("Void")
    } else {
        $(e).closest("tr").find(".lwpsts").val("Active")
    }
}


function checkrowHalfDay(e) {

    if ($(e).prop("checked")) {
        $(e).closest("tr").find(".lwpleavhalf").val("0.5");
        //alert("halfday"+(e).closest("tr").find(".lwpleavhalf").val("0.5"));
    } else {
        $(e).closest("tr").find(".lwpleavhalf").val("1")
    }
}


