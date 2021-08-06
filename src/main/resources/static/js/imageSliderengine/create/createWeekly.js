/******************************************************************* */
var rptPrd="";
function addrow() {
	//debugger;
	var _day = $("#DayList option:selected").text().trim();
	var favorite = [];
	var _year="";

	$.each($("input[name='week']:checked"), function () {
		favorite.push($(this).val());
	});
var half_day = appendHalfDay();
_year=$("#CR_WKLY_PRD option:selected").text().substring(0,4);
 rptPrd=$("#CR_WKLY_PRD option:selected").val();

	var markup = '<tr><td><input type="text" readonly="true" value="' + _day + '" class="lblTxt"/></td><td><input type="text" readonly="true" value="' + favorite.join(",") + '" class="lblTxt"/></td><td><input type="text" readonly="true" value="' + half_day+ '" class="lblTxt"/></td><td><input type="text" readonly="true" value="' + _year+ '" class="lblTxt"/></td><td><input type="text" readonly="true" value="' + rptPrd+ '" class="lblTxt" style="display:none"/></td><td><input type="button" value="Delete" id="btnDell" class="btnDelete"  /></td></tr>';
	//alert(_year+"||"+_day+"||"+favorite);
	$("#REP_PRD_ERROR").text("");
	$("#REP_DAYS_ERROR").text("");
		
	if(_year!="" && _year!=undefined && _day!="" && _day!=undefined && favorite.length>0 ){
		$("table tbody").append(markup);
	}
		else{
				if(_year == "" || _year == undefined){
					$("#REP_PRD_ERROR").text("Please select Repeating Period.");
				}
				if(_day == "" || _day == undefined){
					$("#REP_DAYS_ERROR").text("Please select Days.");
				}
				if(favorite.length<=0){
					alert("Atleast one week should be selected.");
				}
			}	
	
	$.each($('#mytable tr'), function (index, val) {
		////debugger;
		$(this).find("td:eq(0) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].day');
		$(this).find("td:eq(1) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].weeks');
		$(this).find("td:eq(2) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].halfday');
		$(this).find("td:eq(3) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].year');
		$(this).find("td:eq(4) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].repeatingperiodid');
		$(this).find("td:eq(5) input[type='button']").attr('name',  + (index - 1));

	});
//alert("values:"+favorite.join(", "));
}
$(document).ready(function () {


	$(document).on("click", "#btnDell", function () {
		//$("#btnDell").click(function(){
//function delRow(attr_val){
		//debugger;
//$("table").row($(this).parents('tr')).remove().draw(false);
		var dex=$(this).attr('name');
		//var dex = attr_val;
		//var flg=$(this).attr('flg');
		$(this).parents("tr").remove();
		removeRow(dex);

		$.each($('#mytable tr'), function (index, val) {
			//debugger;
			$(this).find("td:eq(0) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].day');
			$(this).find("td:eq(1) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].weeks');
			$(this).find("td:eq(2) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].halfday');
			$(this).find("td:eq(3) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].year');
			$(this).find("td:eq(4) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].repeatingperiodid');
			$(this).find("td:eq(5) input[type='button']").attr('name',  + (index - 1));

		});

	});
});
	function removeRow(index) {
		var jurl = "../weekly/removechild/" + index;
		$.ajax({
			type: 'GET',
			url: jurl,
			success: function (data) {

			},
			error: function (e) {
				alert(JSON.stringify(e));
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
				}
		});
	}

	/****************************************************************** */

//alert("hi weekly");
	var year = '';
	var dtDataId = '';

	var repeatingid = '';


	function appendHalfDay() {
		var half = "";
		var checkBox1 = document.getElementById("CR_WKLY_HF_DY");
		if (checkBox1.checked == true) {
			half += 'Yes';

		} else {
			half += 'No';
		}
		return half;

	}

	function appendWeeks() {
		var combinedweeks = "";
		var checkBox1 = document.getElementById("CR_WKLY_1_WK");
		var checkBox2 = document.getElementById("CR_WKLY_2_WK");
		var checkBox3 = document.getElementById("CR_WKLY_3_WK");
		var checkBox4 = document.getElementById("CR_WKLY_4_WK");
		var checkBox5 = document.getElementById("CR_WKLY_5_WK");

		if (checkBox1.checked == true) {
			combinedweeks += $('#CR_WKLY_1_WK').val() + ", ";
		}
		if (checkBox2.checked == true) {
			combinedweeks += $('#CR_WKLY_2_WK').val() + ", ";
		}
		if (checkBox3.checked == true) {
			combinedweeks += $('#CR_WKLY_3_WK').val() + ", ";
		}
		if (checkBox4.checked == true) {
			combinedweeks += $('#CR_WKLY_4_WK').val() + ", ";
		}
		if (checkBox5.checked == true) {
			combinedweeks += $('#CR_WKLY_5_WK').val() + ", ";
		}

		return combinedweeks;
	}



	/**************************Pop-up section start here****************************************/


	$(function () {
		$("#WeeklySearchList").DataTable({
			'columnDefs': [{
				'targets': 0,
				'orderable': false
			}]
		});


		var searchName = '';
		var searchCode = '';
		var searchStatus = '';
		var jsonUrl = '/weekly/seach/RepeatingPeriod/RepeatingPeriodSearchList';


		/*if($('#CR_ABS_POP_SEARCHS').val() != ''){	
			alert("in 1");		
			searchName = $('#CR_ABS_POP_NAME').val();
			searchDescr = $('#CR_ABS_POP_DESCR').val();
			loadPopupTableData();
			$('#resultSec').css('display', 'block');
		}*/

		$("#CR_WKLY_POP_SRCH").click(function () {

			searchName = $('#CR_WLKY_POP_NAME').val();
			searchCode = $('#CR_WKLY_POP_CODE').val();
			searchStatus=$('#CR_WKLY_POP_STAT').val();
			loadPopupTableData();
			$('#resultSec').css('display', 'block');
		});
		

		function loadPopupTableData() {

			$.ajax({
				type: 'POST',
				url: jsonUrl,
				dataSrc: '',
				data: {
					"name": searchName,
					"code": searchCode,
					"status": searchStatus
				},
				dataType: 'json',
				success: function (data) {
					jsonData = data;
					populateDataTable(jsonData);
				},
				error: function (e) {
					console.log("There was an error with request...");
					console.log("error: " + JSON.stringify(e));
				}
			});
		}


		function populateDataTable(data) {
			$("#WeeklySearchList").DataTable().clear();
			var dataLength = Object.keys(data).length;
			if (dataLength == 0) {
				$('#resultSec').css('display', 'none');
				$('#noData').css('display', 'block');
			} else {
				for (var i = 0; i < dataLength; i++) {
					var dat = data[i];
					$("#WeeklySearchList").dataTable().fnAddData([
						dat.repeatingperiodid,
						dat.name,
						dat.description,
						dat.periodtypename,
						dat.periodlengthname,
						dat.previewperiodstartdate,
						dat.previewperiodenddate

					]);


				}
				$('#resultSec').css('display', 'block');
				$('#noData').css('display', 'none');
			}
		}
	});

	$(document).on('click').unbind();
	$(document).on('click', 'ed', function (e) {
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});


	/**************************Pop-up section End here****************************************/

	/*******************************Popup table selected row start*******************************************/


	$('#CR_WKLY_PRD').on('change', function () {
		//alert("hi");
		var selectObject = $(this).children("option:selected").val();
		//alert(selectObject);
		if (selectObject == 'search') {
			$('#id01').css("display", "block");

		} else if (selectObject != 'search') {
			$('#id01').css("display", "none");
		}

	});
function loadSearchWeeklyData() {
//alert("save");
	//debugger;
	var reapetingperiodid=$("#CR_WKLY_PRD option:selected").val();
	var jurll='/weekly/WeeklyHoliday/getRepPeriodId/'+reapetingperiodid;

	$.ajax({
		url: jurll,
		type: "GET",
		dataSrc: '',

		dataType: 'json',
		success: function (result) {

			var dataBind ="";
			$("#mytable tbody").empty();
			if(result.length>0) {
				for (var i = 0; i < result.length; i++) {
					dataBind = dataBind+'<tr><td><input type="text" readonly="true" value="' + result[i].day + '" class="lblTxt"/></td><td><input type="text" readonly="true" value="' + result[i].weeks + '" class="lblTxt"/></td><td><input type="text" readonly="true" value="' + result[i].halfday+ '" class="lblTxt"/></td><td><input type="text" readonly="true" value="' + result[i].year+ '" class="lblTxt"/></td><td><input type="text" style="display: none" readonly="true" value="' + result[i].repeatingperiodid+ '" class="lblTxt" /></td><td><input type="button" value="Delete" id="btnDell" class="btnDelete"  /></td></tr>';
				}
				$("#mytable tbody").append(dataBind);
				$.each($('#mytable tr'), function (index, val) {
					////debugger;
					$(this).find("td:eq(0) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].day');
					$(this).find("td:eq(1) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].weeks');
					$(this).find("td:eq(2) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].halfday');
					$(this).find("td:eq(3) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].year');
					$(this).find("td:eq(4) input[type='text']").attr('name', 'weeklyholiday[' + (index - 1) + '].repeatingperiodid');
					$(this).find("td:eq(5) input[type='button']").attr('name', +(index - 1));

				});
			}
			else
			{
				$("#mytable tbody").append("No result found!!");
			}

		},
		error: function (response) {
			alert("error"+JSON.parse(response.responseText));
		}
	});

}

	$(document).on('click').unbind();
	$(document).on('click', 'ed', function (e) {
		var url = $(this).attr("rm");
		$('#replace_div').load(url);
	});


	$(document).ready(function () {


		$('#btnsearch').on('click', function (e) {
			$('#id01').css("display", "block");

		});


		$('#CR_WKL_POP_OK').on('click', function (e) {
			loadSearchWeeklyData();
			$('#CR_WKL_POP_OK').css("display", "none");
			$('#id01').css("display", "none");

		});
		
		$("#CR_WKL_POP_CANCEL").click(function () {
			$('#CR_WKL_POP_OK').css("display", "none");
			$('#id01').css('display', 'none');
			$('#resultSec').css('display', 'none');
			$('#CR_WKLY_PRD').children('option[id="1"]').prop('selected',true);
			
		});

		function loadRepatingPeriodId() {

			var jsonUrlposById = '/weekly/WeeklyHoliday/getRepPeriodId/' + repeatingid;
			//alert("url"+jsonUrlposById);
			$.ajax({
				type: 'GET',
				url: jsonUrlposById,
				dataSrc: '',
				dataType: 'json',
				success: function (data) {
					jsonData = data;
					//setWeeklyData(jsonData);alert

					for (var i = 0; i < jsonData.length; i++) {
						popRowWeekly(jsonData[i]);
					}

				},
				error: function (e) {
					console.log("There was an error with request...");
					console.log("error: " + JSON.stringify(e));
				}
			});
		}


		function popRowWeekly(obj) {
			//alert("Hello");


			var table = document.getElementById("GRADE_RATE_VALUE_TBL");
			//var row=table.insertRow(x.parentNode.rowIndex+1);
			var row = table.insertRow(table.rows.length);

			//alert(table.rows.length);

			var cell0 = row.insertCell(0);
			var cell1 = row.insertCell(1);
			var cell2 = row.insertCell(2);
			var cell3 = row.insertCell(3);
			var cell4 = row.insertCell(4);
			var cell5 = row.insertCell(5);


			cell0.innerHTML = "<input  class='w3-input w3-border' value='" + obj.day + "' name='weeklyholiday[" + (table.rows.length - 2) + "].day' type='text'/>";
			cell1.innerHTML = "<input  class='w3-input w3-border' value='" + obj.weeks + "' name='weeklyholiday[" + (table.rows.length - 2) + "].weeks' type='text'/>";
			cell2.innerHTML = "<input  class='w3-input w3-border' value='" + obj.halfday + "' name='weeklyholiday[" + (table.rows.length - 2) + "].halfday' type='text'/>";
			cell3.innerHTML = "<input class='w3-btn  w3-theme' type='button' value='x' onclick='delThisRowGRADE();'/>";
			cell4.innerHTML = "<input  class='w3-input w3-border' value='" + obj.year + "' name='weeklyholiday[" + (table.rows.length - 2) + "].year' type='text' style='display:none'/>";
			cell5.innerHTML = "<input  class='w3-input w3-border' value='" + obj.repeatingperiodid + "' name='weeklyholiday[" + (table.rows.length - 2) + "].repeatingperiodid' type='text' style='display:none' />";


		}


		var table = $('#WeeklySearchList').DataTable();

		$('#WeeklySearchList tbody').on('click', 'tr', function () {

			//alert("ravi");
			var tbldata = $(this).children('td').map(function () {
				return $(this).text();

			}).get();

			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');

			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}

			var dtData0 = tbldata[5].substring(0, 10);
			var dtData1 = tbldata[6].substring(0, 10);
			var combineddate = dtData0 + "-" + dtData1;

			dtDataId = tbldata[0];
			repeatingid = tbldata[0];
			//alert("id"+repeatingid);
			year = dtData0.substring(0, 4);

			$('#weeklyyear').val(year);

			//alert("value"+dtData+" "dtData1);
			//$('#CR_ENT_LOC_CODE').val(dtData);
			$('#CR_WKLY_PRD').val(combineddate);
			$('#CR_WKLY_PRD').children('option[id="2"]').text(combineddate);
			$('#CR_WKLY_PRD').children('option[id="2"]').val(dtDataId);
			$('#CR_WKLY_PRD').children('option[id="2"]').prop('selected', true);
			$('#CR_WKL_POP_OK').css("display", "inline-block");
			//$('#CR_ABS_ELIGID').val(dtDataId);
			//$('#CR_ABS_ELIGNAME').val(dtData);

		});


	});
	/*******************************Popup table selected row END*******************************************/


	/******************************************************************************************************/

	var CR_WKLY_ID = '';



	function ajaxPost() {
////debugger;
//	dynamicNameAttributeGrade();

		//alert("button click");
		//alert(t.length);
		
		CR_WKLY_ID = $('#weeklyholidayid').val();
		var tbody_mytable = $("#mytable tbody");

		if (tbody_mytable.children().length != 0) {
			if (CR_WKLY_ID != 0) {
			loadCorrectWeeklyData();
		} else if (CR_WKLY_ID == 0) {
			//alert(checkBuid);
			loadSaveWeeklyData();

		}
		}else{
			alert("Nothing to save. Please insert Data and try again.");
		}
		
	


	}


	function loadSaveWeeklyData() {
//alert("save");
		//debugger;
		var fd = $("#WEEKLY_SAVE").serialize();

		$.ajax({
			url: "/weekly/saveWeekly",
			type: "POST",
			data: fd,
			cache: false,
			contentType: "application/x-www-form-urlencoded",
			processData: false,
			success: function (result) {
				//alert(result);
				if (result.status == "Success") {
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
					$('#lblMsg').text(result.message + ". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-green");
					$('#btnOK').on('click', function (e) {
						var url = $(this).attr("rm");
						$('#replace_div').load(url);
					});
				}
				if (result.status != "Success") {
					$('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "block");
					$('#lblMsg').text(result.message + ". Click OK to continue.");
					$('#btnOK').toggleClass("w3-button w3-red");
					$('#btnOK').on('click', function (e) {
						$('#AFTER_SUBMIT_STATUS_BLOCK').css("display", "none");
					});
				}

			},
			error: function (response) {
				alert(JSON.parse(response.responseText));
			}
		});

	}



/************************************************************************* */

/*********************************************************************************** */
