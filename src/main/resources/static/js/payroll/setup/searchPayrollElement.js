$('.payrollElementTypeSelect').select2({
    placeholder: "Select Element Type",
    allowClear: true
});

function openAddPayrollElement(){
    var url = '/setuphrms/payrollsetup/payrollelements/addpayrollelements';
    $('#replace_div').html("<div class='w3-container' style='margin-top:10%; text-align: center'><i class='fa fa-cog fa-spin' style='font-size: 70px; color: black;'></i></div>");
    $("#replace_div").load(url);
}

function elementSearch(){
    if($.fn.DataTable.isDataTable('#elementList')){
        $("#elementList").DataTable().destroy();
    }
    var elementListTable = $("#elementList").DataTable({
        'columnDefs': [
            {'targets': [1], 'orderable': false}
            //{'targets':[6], 'className': 'text-center'}
        ]
    });
    getElementListAndPopulate(elementListTable);
    $('#resultSec').css('display', 'none');
    $('#noData').css('display', 'none');
    $('#jsonLoader').css('display', 'block');
}

function getElementListAndPopulate(elementListTable){
	var fd = $("#payrollsearchelem").serialize();
    var url1='/setuphrms/payrollsetup/payrollelements/searchpayrollelements';    	
        
    $.ajax({
    	url:url1,
        type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
        success: function(result){
            var jsonData = result;
            populateDataTable(jsonData, elementListTable);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });    	
};

function populateDataTable(data, elementListTable){
    elementListTable.clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#resultSec').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            $("#elementList").dataTable().fnAddData([
                dat.name,
                dat.abbreviation,
				dat.effdt,
				'<i class="fa fa-eye" id="view" onclick="view_element_details('+dat.elementid+');">',
                //'<i class="fa fa-pen" id="edit" onclick="updateBtnFuncElement('+dat.elementid+');">'
				 '<i class="fa fa-pen" id="edit" onclick="updateBtnFuncElement('+dat.elementid+',\''+dat.effdt+'\');">'
		 ]);
        }

        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
    }
}

/*function updateBtnFuncElement(x){

	console.log("Element ID: "+x);	
	$.ajax({
		type:"GET",
		url:"/setuphrms/payrollsetup/payrollelements/editpayrollelement/"+x,
		success:function(result){
			console.log("sucesssssss");
			$('#replace_div').html(result);			
		},
		error:function(e){
			//alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	

}*/


/*********************************************************************/

var updateUrl="";
var effdt="";
var lid="";
//function editFunction(leID,editID)
function updateBtnFuncElement(x,dateeff)
{
	
		//alert("hi"+dateeff);
		$('#idUD').css("display","block");
		updateUrl="/setuphrms/payrollsetup/payrollelements/editpayrollelement/"+x+"/";
		
		
var from = dateeff;
//var from=$('#fromdate').val();

//$('#startdate').attr("min",from);
}

$('#btnOK').on('click', function(e){
effdt=$('#startdate').val();

updateUrl=updateUrl+effdt;
console.log(updateUrl);
$("#replace_div").load(updateUrl);
});


$('#btnerrorOK').on('click', function(e){
	var urll = $(this).attr("rm");
	$('#replace_div').load(urll);
	});


/******************************************************************/


function view_element_details(elementid)
{
	//debugger;
	//alert("view");
	
     $.ajax({
		type:"GET",
		url:"/setuphrms/payrollsetup/payrollelements/viewpayrollelement/"+elementid,
		success:function(result){
			console.log("sucesssssss");
			$('#replace_div').html(result);			
		},
		error:function(e){
			//alert( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

/******************************************/

