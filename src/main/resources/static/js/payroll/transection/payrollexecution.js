var myVar;
function runPayroll(){
    document.getElementById('runpayrollbtn').style.pointerEvents = 'none';
    document.getElementById('nx').style.pointerEvents = 'none';
    document.getElementById('bk').style.pointerEvents = 'none';
    $('#runpayrollbtn').fadeTo('slow', .6);
    $('#nx').fadeTo('slow', .6);
    $('#bk').fadeTo('slow', .6);
    $('#PYRL_LOADER').css("display", "block");
    var calid = $('#selectedcalendar').val();

    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/executePayroll/runpayroll/' + calid,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            $('#processid').text(result.runcontrolid);
            //getStatus();
            myVar=setInterval(getStatus, 10000);
            //$('#getstatusbtn').addClass('fa-spin');
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};



function getStatus(){
    $('#PYRL_LOADER').css("display", "none");
    $('#runcontrolblock').css("display", "block");
    var calcodee=$('#selectedcalendarcode').val();
    //$('#getstatusbtn').addClass('fa-spin');
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/executePayroll/getrunstatus/' + $('#processid').text()+"/"+calcodee,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            $('#runstatus').text(result.message);
            pyrlstat(result.totalcount, result.completed, result.message);
           // $('#getstatusbtn').removeClass('fa-spin');
            $('#runcontrolblock').css("display", "block");
            if(result.message == "Success"){
                //$('#getstatusbtn').removeClass('fa-spin');
            } else {
                $('#bk').fadeTo('slow', 1);
                document.getElementById('bk').style.pointerEvents = 'auto';
                //$('#getstatusbtn').removeClass('fa-spin');
            }
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};

/*
$( document ).ready(function() {

});
*/

var count=0;
function pyrlstat(total,current,msg){

        var $circle = $('#svg #bar');

        var r = $circle.attr('r');
        var c = Math.PI*(r*2);

        var pct = (((total-current)/total))*c;

        $circle.css({ strokeDashoffset: pct});

        $('#cont').attr('data-pct',current+"/"+total);

        if(total==current && msg=="Success"){
            $("#bar").css("stroke","green");
            $('#nx').fadeTo('slow', 1);
            $('#bk').fadeTo('slow', 1);
            document.getElementById('nx').style.pointerEvents = 'auto';
            document.getElementById('bk').style.pointerEvents = 'auto';
            clearInterval(myVar);
        }

}