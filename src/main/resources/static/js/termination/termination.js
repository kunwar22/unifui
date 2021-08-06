
$( document ).ready(function() {
    //debugger;
    if($("#hirereasondsc").val()!='' && $("#hirereasondsc").val()!=undefined && $("#hirereasondsc").val()!=null) {
        actionchange();
    }
});


function  actionchange() {
    var selectHireActionId = $("#TER_ACTION option:selected").val();
    if(selectHireActionId!='' && selectHireActionId!=undefined && selectHireActionId!=null) {
        var jsonUrlAcReason = '/newperson/actionreasonbind/' + selectHireActionId;

        newStatebind = '<option id="1" value="0" ></option>';
        $.ajax({
            type: 'GET',
            url: jsonUrlAcReason,
            dataSrc: '',
            dataType: 'json',
            success: function (data) {
                data.forEach(function (n) {
                    newStatebind += '<option value="' + n.id + '" >' + n.description + '</option>'
                });
                $('#TER_REASON').html(newStatebind);
            },
            error: function (e) {
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });
    }
}

function cancelResignation(){
    var url = "/termination/resignation";
    $('#div_replace').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: white;'></i></div>");
    $('#div_replace').load(url);
}

function saveResignation() {
    if($("#TER_REASON option:selected").text()!='Hire To Fill Vacant Position'){
        $("#hirereasondsc").val($("#TER_REASON option:selected").text());
    }
    var fd = $("#TERMINATION_FORM").serialize();
    $.ajax({
        url: "/termination/savetermination",
        type: "POST",
        data: fd,
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success : function(result){
            //alert(result);
            $('#div_replace').html(result);
            if(response=="Success"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message: "Successfully terminated."
                    /*callback: function() {
                        $('#replace_div').load("/reimbursement/transportManager");
                    }*/
                });
            }else if(response=="rollback"){
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                    message:"Something went wrong, Please take action again."
                   /* callback:function(){
                        window.location = "/selfservice";
                    }*/
                });
            }
        },
        error: function(response){
            alert(JSON.parse(response.responseText));
        }
    });

}


$('#TERMNOTIDATE').on('change',function(){
    $('#TERMACTDATE').val($('#TERMNOTIDATE').val());
});

