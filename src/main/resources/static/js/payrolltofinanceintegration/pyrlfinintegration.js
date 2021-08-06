
$(document).ready(function(){
    loadPyrlPrepData();
    loadPayablePrepData();
});

function openTab(evt,legal){
    var i,x,tablinks;
    x=document.getElementsByClassName("legal");
    for(i=0;i<x.length;i++){
        x[i].style.display="none";
    }
    tablinks=document.getElementsByClassName("tablink");
    for(i=0;i<x.length;i++){
        tablinks[i].className=tablinks[i].className.replace("w3-theme","");
    }
    document.getElementById(legal).style.display="block";
    evt.currentTarget.className+=" w3-theme";

}

function preparedata(){
    $("#PYRLINTGR_LOADER").css("display","block");
        var calcode=$('#CALCODELOV option:selected').val();
        var noeval=$('#NATLOV option:selected').text();
        var noeid=$('#NATLOV option:selected').val();
        //var elemtype=$('#ELMTYP option:selected').val();
        var curl="/pyrlfinintgr/preparedata/"+calcode+"/"+noeid+"/"+noeval;
 
        $.ajax({
            type: 'GET',
            url: curl,
            contentType:"application/json",
            dataType:"json",
            success:function(result){
                $("#PYRLINTGR_LOADER").css("display","none");
                if (result.status == "Success" || result.status == "success"){
                    $("#INTEGRATE_BTN").css("display", "inline-block");
                    bootbox.alert({
                        size: 'medium',
                        title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                        message:"Data has been prepared, ready to transfer.",
                        callback: function () {
                            loadPyrlPrepData();
                        }
                    });
                }
            },
            error:function(e){
                console.log( "ERROR : "+ JSON.stringify(e) );
            }
        });
}


function integratepayroll(){
    $("#PYRLINTGR_LOADER").css("display","block");
    var aurl="/pyrlfinintgr/integratetofin";
 
    $.ajax({
        url: aurl,
        type: 'POST',
        data: '',
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success:function(result){
            if (result=="Success" || result=="success"){
                $("#PYRLINTGR_LOADER").css("display","none");
                $("#INTEGRATE_BTN").css("display", "none");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message:"Data successfully processed."
                });
            }
        },
        error:function(e){
            console.log( "ERROR : "+ JSON.stringify(e) );
        }
    });
}


function preparedata1(){
   
    $("#PYRLINTGR_LOADER").css("display","block");
    var calcode1=$('#CALCODELOV1 option:selected').val();
    var curl="/pyrlfinintgr/payablepreparedata/"+calcode1;

    $.ajax({
        type: 'GET',
        url: curl,
        contentType:"application/json",
        dataType:"json",
        success:function(result){
            $("#PYRLINTGR_LOADER").css("display","none");
            if (result.message == "Success" || result.message == "success"){
                $("#INTEGRATE_BTN1").css("display", "inline-block");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message:"Data has been prepared, ready to transfer.",
                    callback: function () {
                        loadPayablePrepData();
                    }
                });
            }
        },
        error:function(e){
            console.log( "ERROR : "+ JSON.stringify(e) );
        }
    });
}


function integratepayroll1(){
    $("#PYRLINTGR_LOADER").css("display","block");
    var aurl="/pyrlfinintgr/payableintegratetofin";

    $.ajax({
        url: aurl,
        type: 'POST',
        data: '',
        cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success:function(result){
            if (result=="Success" || result=="success"){
                $("#PYRLINTGR_LOADER").css("display","none");
                $("#INTEGRATE_BTN1").css("display", "none");
                bootbox.alert({
                    size: 'medium',
                    title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
                    message:"Data successfully processed."
                });
            }
        },
        error:function(e){
            console.log( "ERROR : "+ JSON.stringify(e) );
        }
    });
}


function loadPyrlPrepData(){
    $.ajax({
        type: 'GET',
        url: '/pyrlfinintgr/getPyrlPrepData',
        dataSrc: '',
        dataType: 'json',
        success: function(data){
	//alert(JSON.stringify(data));
            jsonData = data;
            populatePyrlPrepData(jsonData);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function populatePyrlPrepData(data){
    $("#PREPPYRL_TBL").DataTable().clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#PREPPAYROLL').css('display', 'none');
        $('#noDataPREP').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            $("#PREPPYRL_TBL").dataTable().fnAddData([
				dat.payrollebssyncid,
				dat.ledgerid,
				dat.accountingdate,
				dat.datecreated,
				dat.actualflag,
				dat.userjecategoryname,
				dat.userjesourcename,
				dat.enteredcr,
				dat.accounteddr,
				dat.accountedcr,
				dat.transactiondate,
				dat.reference1,
				dat.reference10,
				dat.periodname,
				dat.functionalcurrencycode,
				dat.codecombinationid,
				dat.datecreatedingl,
				dat.enteredDr,
				dat.status
            ]);
        }
        $('#PREPPAYROLL').css('display', 'block');
        $('#noDataPREP').css('display', 'none');
    }
}



function loadPayablePrepData(){
    $.ajax({
        type: 'GET',
        url: '/pyrlfinintgr/getPayablePrepData',
        dataSrc: '',
        dataType: 'json',
        success: function(data){
	
            jsonData = data;
            populatePayablePrepData(jsonData);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
}

function populatePayablePrepData(data){
	
    $("#PREPPAYABLE_TBL").DataTable().clear();
    var dataLength = Object.keys(data).length;
   
    if(dataLength == 0){
        $('#PREPPAYABLE').css('display', 'none');
        $('#noDataPYBL').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
	       
                 var dat = data[i];	
		
  for(var j=0; j < dat.lineInvoice.length; j++){	
            $("#PREPPAYABLE_TBL").dataTable().fnAddData([
			  dat.vendornum	,  
			  dat.invoiceamount,
			  dat.description,
			  dat.source,
			  dat.paymentmethodlookupcode,
			  dat.gldate,
			  dat.operatingunit, 			 
		      dat.lineInvoice[j].amount,
			  dat.lineInvoice[j].accountingdate,
			  dat.lineInvoice[j].description,			
			  dat.lineInvoice[j].distcodeconcatenated,
			  
			  dat.srdtstatus
           ]);
}
        }
        $('#PREPPAYABLE').css('display', 'block');
        $('#noDataPYBL').css('display', 'none');
    }
}
