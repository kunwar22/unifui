
window.globalCounter = 1;
$(document).unbind();
function addrownavbar(){

    var data="";
    data='<tr><td><input value="" class="w3-input w3-border" name="createnavbar[0].navname" id="NAVBAR_NAME" type="text">'
        +'</td>'
        +'<td><input value="" class="w3-input w3-border" name="createnavbar[0].navdescr" id="NAVBAR_DSCR" type="text">'
        +'</td>'
        +'<td><select id="PARENT_TYPE" class="w3-select w3-border" onchange="parentchange()">'
        +'<option selected></option>'
        +'<option value="ROOT">Root</option>'
        +'<option value="LEAF">Leaf</option>'
        +'</select></td>'
        +'<td><select id="PARENT_ID" class="w3-select w3-border" name="createnavbar[0].parentid">'+$("#PARENT_LIST").html()+''
        +'</select></td>'
        +'<td><select id="isparentID" class="w3-select w3-border" name="createnavbar[0].isparent">'
        +'<option value="Y">Yes</option>'
        +'<option selected value="N">No</option>'
        +'</select>'
        +'</td>'
        +'<td><input value="" class="w3-input w3-border" name="createnavbar[0].requestaddr" id="REQ_ADDR" type="text">'
        +'</td>'
        +'<td>'
        +'<input class="w3-input w3-border"'
        +'name="createnavbar[0].createdby"'
        +'id="txtCreatedby" type="text" >'
        +'</td>'
        +'<td>'
        +'<select id="NAVBAR_STATUS" class="w3-select w3-border" name="createnavbar[0].status">'
        +$("#NAVBAR_STATUS").html()
        +'</select>'
        +'</td>'
        +'<td  style="width: 5%">'
        +'<input th:index="0"  class="w3-btn w3-theme" id="deletenavbar" type="button" value="x">'
        +'</td>'
        +'</tr>'
    $("#NAVBAR_TBL tbody").append(data);


    $.each($('#NAVBAR_TBL tr'),function(index,val){

        $(this).find("td:eq(0) input[type='text']").attr('name','createnavbar['+(index - 1)+'].navname');
        $(this).find("td:eq(1) input[type='text']").attr('name','createnavbar['+(index - 1)+'].navdescr');
        $(this).find("td:eq(2)").find("select").attr('id','Node'+(index - 1));
        $(this).find("td:eq(2)").find("select").attr('onchange','parentchange('+(index - 1)+')');

        $(this).find("td:eq(3)").find("select").attr('name','createnavbar['+(index - 1)+'].parentid');
        $(this).find("td:eq(3)").find("select").attr('Id','PARENT_LIST'+(index - 1));

        $(this).find("td:eq(4)").find("select").attr('name','createnavbar['+(index - 1)+'].isparent');
        $(this).find("td:eq(5) input[type='text']").attr('name','createnavbar['+(index - 1)+'].requestaddr');
        $(this).find("td:eq(6) input[type='text']").attr('name','createnavbar['+(index - 1)+'].createdby');
        $(this).find("td:eq(7)").find("select").attr('name','createnavbar['+(index - 1)+'].isactive');

        //$(this).find("td:eq(7) input[type='button']").attr('index',(index - 1));

    });
}

$(document).unbind();
$(document).on("click","#deletenavbar",function(){
    //$("table").row($(this).parents('tr')).remove().draw(false);

    $(this).parents("tr").remove();

    $.each($('#NAVBAR_TBL tr'),function(index,val){

        $(this).find("td:eq(0) input[type='text']").attr('name','createnavbar['+(index - 1)+'].navname');
        $(this).find("td:eq(1) input[type='text']").attr('name','createnavbar['+(index - 1)+'].navdescr');
        $(this).find("td:eq(2)").find("select").attr('id','Node'+(index - 1));

        $(this).find("td:eq(3)").find("select").attr('name','createnavbar['+(index - 1)+'].parentid');
        $(this).find("td:eq(3)").find("select").attr('Id','PARENT_LIST'+(index - 1));

        $(this).find("td:eq(4)").find("select").attr('name','createnavbar['+(index - 1)+'].isparent');
        $(this).find("td:eq(5) input[type='text']").attr('name','createnavbar['+(index - 1)+'].requestaddr');
        $(this).find("td:eq(6) input[type='text']").attr('name','createnavbar['+(index - 1)+'].createdby');
        $(this).find("td:eq(7)").find("select").attr('name','createnavbar['+(index - 1)+'].isactive');

        //$(this).find("td:eq(7) input[type='button']").attr('index',(index - 1));

    });


});

function parentchange(_val)
{
    var parentBind="";

    var parentval=$('#Node'+_val).children("option:selected").val();

        var jurl="../uniftools/security/parentbind/"+parentval;
        $.ajax({
            type: 'GET',
            url: jurl,
            success: function(data){

                parentBind+='<option value="" disabled selected></option>'
                data.forEach(function(n){
                    parentBind+='<option value="'+n.navbarid+'" >'+n.navname+'</option>'
                });
                $('#PARENT_LIST'+_val).html(parentBind);
            },
            error: function(e){
                alert(JSON.stringify(e));
                console.log("There was an error with request...");
                console.log("error: " + JSON.stringify(e));
            }
        });

}

function sendNavbarData()
{

        $.ajax({
            type: 'POST',
            url: '../uniftools/security/navbar/saveNavbar',
            data: $('#frmNavbar').serialize(),
            contentType: "application/x-www-form-urlencoded",
            processData: false,
            catch: false,
            success: function (result) {

                if(result.message == "Success"){
                    bootbox.alert({
                        size: 'medium',
                        title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                        message:"Navbar created Successfully.",
                        callback:function() {
                            $('#replace_div').load("../uniftools/security/navbar/managenavbar");
                        }
                    })
                }
                if(result.errorMessage == "Error"){
                    bootbox.alert({
                        size: 'medium',
                        title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                        message:"Unable to create Navbar.",
                        callback:function() {
                            $('#replace_div').load("../uniftools/security/navbar/managenavbar");
                        }
                    })
                }

            },
            error: function (e) {
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Something went wrong please try again.",
                    callback:function() {
                        $('#replace_div').load("../uniftools/security/navbar/managenavbar");
                    }
                })
            }

        });

}


//////*******************Navabr Mapping*****************///////////////////////////


function addrownavbarlist(){
    //
    // var data="";
    // data='<tr>'
    //     +'<td style="width: 50%">'
    //     +'<select id="navbarList" class="w3-select w3-border" name="navbarid">'+$("#navbarList").html()+''
    //     +'</select></td>'
    //     +'<td  style="width: 5%">'
    //     +'<input th:index="0"  class="w3-btn w3-theme" id="deletenavbarmap" type="button" value="x">'
    //     +'</td>'
    //     +'</tr>'
    // $("#NAVBARMAP_TBL tbody").append(data);
    //
    //
    // $.each($('#NAVBARMAP_TBL tr'),function(index,val){
    //
    //
    //     $(this).find("td:eq(0)").find("select").attr('name','navbarid');
    //
    // });
    var data = "";

    data = '<tr><td style="width:100%"><select id="navbarid' + window.globalCounter + '" class="w3-select w3-border" name="navbarid">' + $("#navbarid").html() + '</select><td style="width:5%"><input class="w3-btn w3-theme" id="deletenavbarmap" index="0" flg="prmsn"  type="button" value="x"/></td></tr>';
    $("#NAVBARMAP_TBL tbody").append(data);

    $.each($('#NAVBARMAP_TBL tr'), function (index, val) {

        $(this).find("select").attr('name', 'navbarid');

    });


}




$(document).unbind();
$(document).on("click","#deletenavbarmap",function(){
    //$("table").row($(this).parents('tr')).remove().draw(false);

    $(this).parents("tr").remove();

    $.each($('#NAVBARMAP_TBL tr'),function(index,val){

        $(this).find("select").attr('name', 'navbarid');

    });


});




function saveNavbarMappingData()
{

    $.ajax({
        type: 'POST',
        url: '../uniftools/security/navbarmapping/navbarMapping/saveMapNavbar',
        data: $('#frmNavbarMapCreate').serialize(),
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        catch: false,
        success: function (result) {

            if(result.message == "Success"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                    message:"Navbar Mapping created Successfully.",
                    callback:function() {
                        $('#replace_div').load("../uniftools/security/navbarmapping/navbarmapping");
                    }
                })
            }
            if(result.errorMessage == "Error"){
                bootbox.alert({
                    size: 'medium',
                    title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                    message:"Unable to create Navbar Mapping.",
                    callback:function() {
                        $('#replace_div').load("../uniftools/security/navbarmapping/navbarmapping");
                    }
                })
            }

        },
        error: function (e) {
            bootbox.alert({
                size: 'medium',
                title: '<i  class="fa fa-times-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                message:"Something went wrong please try again.",
                callback:function() {
                    $('#replace_div').load("../uniftools/security/navbarmapping/navbarmapping");
                }
            })
        }

    });

}



$('#navbarmapCreate').on('click', function (e) {

    var url = $(this).attr("rm");
    $('#replace_div').load(url);

});





$('#NAVBAR_CANCEL').on('click', function () {
    url = '../uniftools/security/navbar/managenavbar';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});


$('#MAP_PERM_CANCEL').on('click', function () {
    url = '../uniftools/security/navbarmapping/navbarmapping';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
});

