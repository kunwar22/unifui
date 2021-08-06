
$( document ).ready(function() {
    //debugger;
    loadnoticedata();
    //$(tabname).addClass("w3-theme");
});

/*$("#NOTICE_TBL").DataTable({
    'columnDefs': [{
        'targets': [0,1,2,3,4,5,6],
        'orderable': false
    }]
});*/
$("#NOTICE_TBL").DataTable({
   'ordering' : false
});

var jsonUrl = '/notices/getallnotices';

function loadnoticedata(){
    loadNData();
    //$('#LOADER').css('display', 'block');
    $('#NOTICE_BLOCK').css('display', 'none');
}

function loadNData(){
    $.ajax({
        type: 'POST',
        url: jsonUrl,
        dataSrc: '',
        dataType: 'json',
        success: function(data){
            jsonData = data;
            populateNDataTable(jsonData);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function populateNDataTable(data){
    $("#NOTICE_TBL").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#NOTICE_BLOCK').css('display', 'none');
        //$('#LOADER').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            var btn='';
            //alert(dat.status);
            if(dat.status!=null){
                btn='<div>Declared <i class="fas fa-circle" style="color: red"></i></div>';
            }else {
                btn='';
            }
            $("#NOTICE_TBL").dataTable().fnAddData([
                dat.orderno,
                dat.subject,
                dat.published_date,
                dat.type,
                dat.department,
                dat.status,
                '<a id="DOWNLOAD_LINK" href="/getContent?location='+dat.fileattachement+'" target="_blank" download><i class="fa fa-download" aria-hidden="true"></i></a>'
                //	"<ed rm='/enterprisesetup/edit/EditDepartment/"+dat.personid+"/"dat.personid"' class='editpersonid' style='cursor: pointer'><i class='fa fa-edit' aria-hidden='true'></i></ed>"

            ]);
        }
        //$('#LOADER').css('display', 'none');
        $('#NOTICE_BLOCK').css('display', 'block');
        $('#noData').css('display', 'none');
    }
}


function saveNotice(){
    if(validateNotice()){
        var form = $("#NOTICE_FORM")[0];
        var data = new FormData(form);

        $.ajax({
            url: "/notices/saveNotice",
            type: "POST",
            enctype: "multipart/form-data",
            data: data,
            cache: false,
            contentType:false,
            processData: false,
            timeout:600000,
            success: function(data){
                $('#replace_div').html(data);
                if( resultfinal=="Success")
                {
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message:"Document successfully uploaded."
                    });
                }
                if( resultfinal=="Failed")
                {
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message:"Document Failed uploaded."
                    });
                }
                else if( resultfinal=="MISMATCH" )
                {
                    //alert("MISMATCH");
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message:"Mismatch."
                    });
                }
                else if( resultfinal=="EMPTY_FILE" )
                {
                    //alert("File is empty");
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message:"File is empty."
                    });
                }
                else if( resultfinal=="WRITE_ERROR" )
                {
                    //alert("Error in writing file.");
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message:"Error in writing file."
                    });
                }
                else if( resultfinal=="IOEXCEPTION" )
                {
                    //alert("IO Exception has occurred.");
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message:"IO Exception has occurred."
                    });
                }
                else if( resultfinal=="LOG_ERROR" )
                {
                    //alert("Error while logging file info.");
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message:"Error while logging file info."
                    });
                }
                else if( resultfinal=="ILLEGALARG" )
                {
                    alert("Error while posting file log.");
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                        message:"Error while posting file log."
                    });
                }
                /*else
                {
                    alert("Something went wrong while uploading the file(s).");
                }*/
            },
            error: function(data){
                alert("ERROR : "+JSON.stringify(data));
            }
        });
    }
}

function validateNotice(){

    var ORDRNUM=$('#N_ORDERNUM').val().trim();
    var PUBLISH=$('#N_PUBLSH_DT').val();
    var STATUS=$('#N_STATUS option:selected').val();
    var DEPT=$('#N_DEPARTMENT option:selected').val();
    var SUBJ=$('#N_SUBJECT').val().trim();
    var CIRC=$('#N_FILE').val();
    var TYPE=$('#N_TYPE option:selected').val();
    var EXPRY=$('#N_EXP_DT').val();

    $('#N_ORDERNUM_ERR').text("");
    $('#N_PUBLSH_DT_ERR').text("");
    $('#N_STATUS_ERR').text("");
    $('#N_DEPARTMENT_ERR').text("");
    $('#N_SUBJECT_ERR').text("");
    $('#N_FILE_ERR').text("");
    $('#N_TYPE_ERR').text("");
    $('#N_EXP_DT_ERR').text("");

    var c=0;

    if(ORDRNUM===undefined || ORDRNUM==="" || ORDRNUM===null){
        $('#N_ORDERNUM_ERR').text("Please enter Order Number.");
        c+=1;
    }
    if(PUBLISH===undefined || PUBLISH==="" || PUBLISH===null){
        $('#N_PUBLSH_DT_ERR').text("Please select Publish Date.");
        c+=1;
    }
    if(STATUS===undefined || STATUS==0 || STATUS===null){
        $('#N_STATUS_ERR').text("Please select Status.");
        c+=1;
    }
    if(DEPT===undefined || DEPT==0 || DEPT===null){
        $('#N_DEPARTMENT_ERR').text("Please select Department.");
        c+=1;
    }
    if(SUBJ===undefined || SUBJ=="" || SUBJ===null){
        $('#N_SUBJECT_ERR').text("Please enter Subject.");
        c+=1;
    }
    if(TYPE===undefined || TYPE==0 || TYPE===null){
        $('#N_TYPE_ERR').text("Please select Type.");
        c+=1;
    }
    if(CIRC===undefined || CIRC=="" || CIRC===null){
        $('#N_FILE_ERR').text("Please select File.");
        c+=1;
    }
    if(EXPRY===undefined || EXPRY==="" || EXPRY===null){
        $('#N_EXP_DT_ERR').text("Please select Expiry Date.");
        c+=1;
    }
    if(c!=0){
        //alert(c);
        return false;
    }
    return true;
}

