//alert("Hii...I/'m DocRecord JS");
$('#AB_TYPE').on('change', function(e){
		$('#WHEN_BLOCK').css("display","block");
		$('#COMMENT_BLOCK').css("display","block");
		$('#BTN_BLOCK').css("display","block");
		
});
var flag;
$('#ABS_HALF_FULL_START').on('change', function(e){
		
	var ABS_START_DATE=$('#ABS_START_DATE').val();
	var WEEK=getWeekByDate(ABS_START_DATE);
	var DAY=moment(ABS_START_DATE).format('dddd');
	var HALF=$("#ABS_HALF_FULL_START option:selected").val();
	var YEAR=moment(ABS_START_DATE).year();
	var ABSTYPEID=$("#AB_TYPE option:selected").val();
	var REPPERIODID=$("#AB_TYPE option:selected").attr("rep");
	alert(DAY+" of Week "+WEEK+" of "+YEAR+" where Repeating Period is "+REPPERIODID+" and Half Day is "+HALF);
	
	$("#START_DATE_ERROR").text("");
	flag=0;
	checkDateForWeeklyOff(WEEK,DAY,HALF,REPPERIODID,ABSTYPEID,YEAR,ABS_START_DATE);
});

$('#ABS_START_DATE').on('change', function(e){
	$('#ABS_HALF_FULL_START').prop("disabled",false);
});


$('#ABS_HALF_FULL_END').on('change', function(e){
		
	var ABS_END_DATE=$('#ABS_END_DATE').val();
	var WEEK=getWeekByDate(ABS_END_DATE);
	var DAY=moment(ABS_END_DATE).format('dddd');
	var HALF=$("#ABS_HALF_FULL_END option:selected").val();
	var YEAR=moment(ABS_END_DATE).year();
	var ABSTYPEID=$("#AB_TYPE option:selected").val();
	var REPPERIODID=$("#AB_TYPE option:selected").attr("rep");
	alert(DAY+" of Week "+WEEK+" of "+YEAR+" where Repeating Period is "+REPPERIODID+" and Half Day is "+HALF);
	
	$("#END_DATE_ERROR").text("");
	flag=1;
	checkDateForWeeklyOff(WEEK,DAY,HALF,REPPERIODID,ABSTYPEID,YEAR,ABS_END_DATE);
});

$('#ABS_END_DATE').on('change', function(e){
		$('#ABS_HALF_FULL_END').prop("disabled",false);
});



/**********ON GIVEN DATE, FUNCTIONS TO FIND WEEK OF MONTH WHERE FIRST WEEK WILL START FROM FIRST MONDAY OTHERWISE DATE WILL LIE IN LAST WEEK OF PREVIOUS MONTH START***********/
function getWeekByDate(date) {
	   var d = new Date(date),
	       month = d.getMonth(),
			year=d.getFullYear(),
	       mondays = [];
	   d.setDate(1);
	   // Get the first Monday in the month
	   while (d.getDay() !== 1) {
	       d.setDate(d.getDate() + 1);
	   }
	
	   // Get all the other Mondays in the month
	   while (d.getMonth() === month) {
	       mondays.push(new Date(d.getTime()).toISOString().substring(0,10));
	       d.setDate(d.getDate() + 7);
	   }
		var totalweeks=mondays.length;
		var week=0;
		//alert(week);
		for(i=0; i<totalweeks;i++){
			if(new Date(date)>=new Date(mondays[i])){
				week=i+1;
			}
		}
		//alert("Month "+month);
		//alert("Year "+year);
		if(week==0){
			week=weeksinmonth(year, month-1);
		}
	   return week;
}

function weeksinmonth(year, month){
	 var d = new Date(year,month,1),
	       month = d.getMonth(),
	       mondays = [];
	
	   d.setDate(1);
	
	   // Get the first Monday in the month
	   while (d.getDay() !== 1) {
	       d.setDate(d.getDate() + 1);
	   }
	
	   // Get all the other Mondays in the month
	   while (d.getMonth() === month) {
	       mondays.push(new Date(d.getTime()).toISOString().substring(0,10));
	       d.setDate(d.getDate() + 7);
	   }
		var totalweeks=mondays.length;
		return totalweeks;
}
/**********ON GIVEN DATE, FUNCTIONS TO FIND WEEK OF MONTH WHERE FIRST WEEK WILL START FROM FIRST MONDAY OTHERWISE DATE WILL LIE IN LAST WEEK OF PREVIOUS MONTH END***********/



/******FUNCTION TO CHECK LEAVE APPLYING DATE IS VALID FOR LEAVE OR NOT START******/

function checkDateForWeeklyOff(WEEK,DAY,HALF,REPPERIODID,ABSTYPEID,YEAR,DATE){	
	//debugger;
var jsonUrlweek = '/user/dateValidator';
		
		$.ajax({
			type: 'GET',
			url: jsonUrlweek,
			dataSrc: '',
			data: {
			"week": WEEK,
			"day": DAY,
			"rep":REPPERIODID,
			"half":HALF,
			"abstype": ABSTYPEID,
			"year": YEAR,
			"date":DATE
			
			},
			dataType: 'json',
			success: function(data){
				var message=data.description;
				if(message!=null){
					if(flag==0){
						$("#START_DATE_ERROR").text(message);
					}
					if(flag==1){
						$("#END_DATE_ERROR").text(message);
					}
				
				}
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
}
		
/******FUNCTION TO CHECK LEAVE APPLYING DATE IS VALID FOR LEAVE OR NOT END******/


function calculateDays(){
	var LEAVES=[];
	
	var START_DATE=$('#ABS_START_DATE').val();
	var END_DATE=$('#ABS_END_DATE').val();
	var start = new Date(START_DATE);
	var end = new Date(END_DATE);
	var newend = end.setDate(end.getDate()+1);
	end = new Date(newend);
	while(start < end){
	   //console.log(start.toISOString().substring(0,10));      
	    var element_date=start.toISOString().substring(0,10);
		var element_week=getWeekByDate(element_date);
		var element_day=moment(element_date).format('dddd');
		var element_year=moment(element_date).year();
		var element={date:element_date,week:element_week,day:element_day,year:element_year,half:"No"};
		LEAVES.push(element);
	    var newDate = start.setDate(start.getDate() + 1);
	   start = new Date(newDate);
	}
	LEAVES[0].half=$("#ABS_HALF_FULL_START option:selected").val();
	LEAVES[LEAVES.length-1].half=$("#ABS_HALF_FULL_END option:selected").val();
	calculateLeaves(LEAVES);
	for(i=0;i<LEAVES.length;i++){
		console.log(LEAVES[i]);
	}

}

/******FUNCTION TO SEND DATE LIST FOR CALCULATION AND RETURN TOTAL CALCULATED LEAVES START******/

function calculateLeaves(LEAVES){	
	//debugger;
var jsonUrlcalc = '/user/leaveCalculator';
		
		$.ajax({
			type: 'POST',
			url: jsonUrlcalc,
			data: JSON.stringify(LEAVES),
			dataType: 'json',
			success: function(data){
				var message=data.description;
				//alert(message);
				$("#AB_DURATION").text(message);
			},
			error: function(e){
				console.log("There was an error with request...");
				console.log("error: " + JSON.stringify(e));
			}
		});
}
		
/******FUNCTION TO SEND DATE LIST FOR CALCULATION AND RETURN TOTAL CALCULATED LEAVES END******/






