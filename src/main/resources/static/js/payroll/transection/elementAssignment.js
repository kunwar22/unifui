
$(document).ready(function(){
    //alert("PERNUM :: "+pernum);
    if (pernum!='' && pernum!=null && pernum!=undefined){
        editElementAssignment(pernum);
    }
});


$('#paygroupLoader').css('display', 'inline-block');
var jsonUrl = '/payrollprocessing/payeedata/getpaygroups';
var payGroupList = "";
$.ajax({
    type: 'GET',
    url: jsonUrl,
    dataSrc: '',
    dataType: 'json',
    success: function(data){
        payGroupList+='<option value="">--Select Paygroup--</option>'
        data.forEach(function(n){
            payGroupList+='<option value="'+n.paygroupid+'" >'+n.descr+'</option>'
        });
        $('#payGroupSelect').html(payGroupList);
        $('#paygroupLoader').css('display', 'none');
    },
    error: function(e){
        console.log("There was an error with request...");
        console.log("error: " + JSON.stringify(e));
    }
});

$('#payGroupSelect').on('change', function(){
    getEmployees($(this).val());
})

function getEmployees(paygroupid){
    if($.fn.DataTable.isDataTable('#employeeList')){
        $("#employeeList").DataTable().destroy();
    }
    var employeeDataTable = $("#employeeList").DataTable({
        'columnDefs': [
            {'targets': [3,4,5], 'orderable': false},
            {'targets':[5], 'className': 'text-center'}
        ]
    });
    getEmployeeDataAndPopulate(paygroupid, employeeDataTable);
    $('#resultSec').css('display', 'none');
    $('#noData').css('display', 'none');
    $('#jsonLoader').css('display', 'block');
};

function getEmployeeDataAndPopulate(paygroupid, employeeDataTable){
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/payeedata/employeelist/'+paygroupid,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            jsonData = result;
            populateDataTable(jsonData, employeeDataTable);
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};

var person_number='';
var person_name='';
var flag='';

function populateDataTable(data, employeeDataTable){
    employeeDataTable.clear();
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#resultSec').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
        $('#noData').css('display', 'block');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            $("#employeeList").dataTable().fnAddData([
                dat.personnumber,
                dat.personname,
                dat.hrstatus,
                dat.payrollstatus,
                dat.hiredate,
                dat.natureofemployement,
                dat.employeecategory,
                "<a onclick='displayElementAssignment(\""+dat.personnumber+"\")' style='cursor: pointer; font-size: 14px;'><i class='fa fa-eye' aria-hidden='true'></i></a>&nbsp;&nbsp;&nbsp;" +
                "<a onclick='editElementAssignment(\""+dat.personnumber+"\")' style='cursor: pointer; font-size: 14px;'><i class='fas fa-edit' aria-hidden='true'></i></a>"
           		
           
            ]);
        }

        $('#resultSec').css('display', 'block');
        $('#noData').css('display', 'none');
        $('#jsonLoader').css('display', 'none');
    }
};


function displayElementAssignment(personnumber){

$('#viewAssignedElement').css('display', 'block');
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/payeedata/employeedetails/'+personnumber,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            $('#personnumber').text(result.personnumber);
            $('#personname').text(result.personname);
            $('#hrstatus').text(result.hrstatus);
            $('#payrollstatus').text(result.payrollstatus);
            $('#businessunit').text(result.businessunit);
            $('#location').text(result.worklocation);
            $('#department').text(result.department);
            $('#job').text(result.job);
            $('#designation').text(result.designation);

            $('#data1').css('display', 'none');
            $('#noData1').css('display', 'none');
            $('#jsonLoader1').css('display', 'block');

            populateElementAssignmentGrid(personnumber,'view');

            person_number=result.personnumber;
            person_name=result.personname;

        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};

function editElementAssignment(personnumber){

	$('#editAssignedElement').css('display', 'block');
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/payeedata/employeedetails/'+personnumber,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
            $('#personnumber1').text(result.personnumber);
            $('#personname1').text(result.personname);
            $('#hrstatus1').text(result.hrstatus);
            $('#payrollstatus1').text(result.payrollstatus);
            $('#businessunit1').text(result.businessunit);
            $('#location1').text(result.worklocation);
            $('#department1').text(result.department);
            $('#job1').text(result.job);
            $('#designation1').text(result.designation);

            $('#data2').css('display', 'none');
            $('#noData2').css('display', 'none');
            $('#jsonLoader2').css('display', 'block');

            populateElementAssignmentGrid(personnumber,'edit');

            person_number=result.personnumber;
            person_name=result.personname;

        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};

function populateElementAssignmentGrid(personnumber,m){
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/payeedata/elementbypersonid/' + personnumber,
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
        	if(m=='view'){
        		populateGrid(result);
        	}else{
        		populateGrid2(result);
        	}
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};

function populateGrid(data){
    var assignmentRow = '';
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#data1').css('display', 'none');
        $('#noData1').css('display', 'block');
        $('#jsonLoader1').css('display', 'none');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            assignmentRow += '<tr>' +
                '<td>' +
                '<input type="hidden" name="elementMapping['+i+'].mappingid" value="'+dat.mappingid+'">' +
                '<select class="w3-select w3-border" name="elementMapping['+i+'].elementid" disabled><option selected value="'+dat.elementid+'">'+dat.elementname+'</option>'+dat.optionlist+'</select>' +
                '</td>' +
                '<td><input type="date" class="w3-input w3-border" name="elementMapping['+i+'].startdt" value="'+dat.startdt+'" readonly></td>' +
                '<td><input type="date" class="w3-input w3-border" name="elementMapping['+i+'].enddt" value="'+dat.enddt+'" readonly>' +
                '<td><input type="text" class="w3-input w3-border" name="elementMapping['+i+'].defaultamt" value="'+dat.defaultamt+'" readonly>' +
                '<input type="hidden" name="elementMapping['+i+'].personnumber" value="'+dat.personnumber+'">' +
                '</td>' +
                '</tr>';
        }
        $('#data1').css('display', 'block');
        $('#noData1').css('display', 'none');
        $('#jsonLoader1').css('display', 'none');
    }
    $('#mappedElementData').html(assignmentRow);
};

function populateGrid2(data){
    var assignmentRow = '';
    var dataLength = Object.keys(data).length;
    if(dataLength == 0){
        $('#data2').css('display', 'none');
        $('#noData2').css('display', 'block');
        $('#jsonLoader2').css('display', 'none');
    } else {
        for(var i=0; i < dataLength; i++){
            var dat = data[i];
            assignmentRow += '<tr>' +
                '<td>' +
                '<input type="hidden" name="elementMapping['+i+'].mappingid" value="'+dat.mappingid+'">' +
                '<select onchange="enablesave();" class="w3-select w3-border" name="elementMapping['+i+'].elementid"><option selected value="'+dat.elementid+'">'+dat.elementname+'</option>'+dat.optionlist+'</select>' +
                '</td>' +
                '<td><input onchange="enablesave();" type="date" class="w3-input w3-border" name="elementMapping['+i+'].startdt" value="'+dat.startdt+'"></td>' +
                '<td><input onchange="enablesave();" type="date" class="w3-input w3-border" name="elementMapping['+i+'].enddt" value="'+dat.enddt+'">' +
                '<td><input onchange="enablesave();" type="text" class="w3-input w3-border" name="elementMapping['+i+'].defaultamt" value="'+dat.defaultamt+'">' +
                '<input type="hidden" name="elementMapping['+i+'].personnumber" value="'+dat.personnumber+'">' +
                '</td>' +
                '</tr>';
        }

        $('#data2').css('display', 'block');
        $('#noData2').css('display', 'none');
        $('#jsonLoader2').css('display', 'none');
    }
    $('#mappedElementDatae').html(assignmentRow);
};

function enablesave(){
    $("#SAVEEDITBTN").css("display","block");
}

function saveAssignments(){
    var assignmentData = $('#assignmentForm').serialize();
    var url = "/payrollprocessing/payeedata/elementassignment";
    $.ajax({
        type: 'POST',
        url: url,
        dataSrc: '',
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded',
        processData: false,
        data: assignmentData,
        success: function(result){
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
                message: JSON.stringify(result.message)
            });
            $("#SAVEEDITBTN").css("display","none");
        },
        error: function(e){
            alert(JSON.stringify(e));
            bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-exclamation-circle" style="font-size: 25px; color: red;">&nbsp;&nbsp;Error</i>',
                message: JSON.stringify(e)
            });
            console.log("error: " + JSON.stringify(e));
        }
    });
}

/********************code added by rajat start here*************************** */

function assignNewElement(){

$('#addAssignedElement').css('display', 'block');
    $.ajax({
        type: 'GET',
        url: '/payrollprocessing/payeedata/getelements',
        datasrc: '',
        dataType: 'JSON',
        success: function(result){
			var elementSelectOptions = '';
			var len = result.length;
            elementSelectOptions = '<option disabled selected></option>'
			for(var i=0; i<len; i++){
				var dat = result[i];
				elementSelectOptions += '<option value="'+ dat.elementid+'"  >'+dat.name+'</option>';
			}
			$('select#elementSelect').html(elementSelectOptions);
			$('#personnumber2').text(person_number);
            $('#personname2').text(person_name);
			$('input#personnumbertext1').val(person_number);
			
			
        },
        error: function(e){
            console.log("There was an error with request...");
            console.log("error: " + JSON.stringify(e));
        }
    });
};

function saveElementMapping(){

var x=person_number;
	var fd = $("#elementMapping").serialize();
	$.ajax({
		url: "/setuphrms/payrollsetup/payrollelements/saveelementmapping",
	    type: "POST",
	    data: fd,
	    cache: false,
        contentType: "application/x-www-form-urlencoded",
        processData: false,
		success: function (result) {
			bootbox.alert({
	            size: 'medium',
	            title: '<i class="fa fa-check" style="font-size: 25px; color: green;">&nbsp;&nbsp;Success</i>',
	            message: result
            });
       //     alert(x);
             editElementAssignment(x);
            $("#ELMNT_STRT_DT").val("");
            $("#ELMNT_DFLT_AMT").val(0);
             $('#addAssignedElement').css('display', 'none');
            
        },
        error: function (response) {
            alert("errorrrr "+response.responseText);
        }
		
	});

};

/*********************code added by rajat end here***************************** */
