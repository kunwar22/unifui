$(document).ready(function(){
	//debugger;
	var _hrefappr=$("#DOWNLOAD_LINKsp").text();
	/*if(_hrefappr!=undefined && _hrefappr!="" && _hrefappr!=null){
		_hrefappr=_hrefappr.replaceAll('/', "FORWARD_SLASH");
		_hrefappr=_hrefappr.replaceAll('\\','BACKWARD_SLASH');
		_hrefappr=_hrefappr.replaceAll('.','EXT_DOT');

		$("#DOWNLOAD_LINK").attr("href","/getContent?location="+_hrefappr);
	}*/
	$("#DOWNLOAD_LINK").attr("href","/getContent?location="+_hrefappr);
	
	
});

function errorfunc(){
	//debugger;
	bootbox.alert({
				size: 'medium',
				title:'<i class="fa fa-exclamation-triangle"  style="font-size:25px; color:#ff9d0a;">&nbsp;&nbsp;Information</i>',
				message:'Approval setup is missing for this reimbursement, to proceed further please configure the setup.'				
			});
}

var maxfy=getMaxfy();	
var minfy=getMinfy();	

function getMinfy(){	
	//debugger;
  var today = new Date();	
  if ((today.getMonth() + 1) <= 3) {	
    var minfy=(today.getFullYear() - 1)+"-04-01";	
  } else {	
    var minfy=today.getFullYear()+"-04-01";	
  }	
  return minfy;	
}	
function getMaxfy(){
	//debugger;	
  var today = new Date();	
  if ((today.getMonth() + 1) <= 3) {	
    var maxfy=today.getFullYear()+"-03-31";	
  } else {	
    var maxfy=(today.getFullYear() + 1)+"-03-31";	
  }	
  return maxfy;	
}


$("#periodfrom").change(function(){
	//debugger;
	$("#periodto").val("");
	/*var from_date=$("#TELE_FROM_DATE").val();		
	$("#TELE_TO_DATE").attr('min',from_date);*/
});

$("#periodfrom").click(function(){
	//debugger;
	var dt=new Date();
	var mon=dt.getMonth()+1;
	var yea=dt.getFullYear();
	var dat=dt.getDate();
	
	if(mon<10){
		mon="0"+mon;	
	}
	if(dat<10){
		dat="0"+dat;
	}
	
	var maxi=yea+"-"+mon+"-"+dat;
	$("#periodfrom").attr("max",maxi);
	//$("#periodfrom").attr("min",minfy);		
});
$("#periodto").click(function(){
	//debugger;
	var dt=new Date();
	var mon=dt.getMonth()+1;
	var yea=dt.getFullYear();
	var dat=dt.getDate();
	
	if(mon<10){
		mon="0"+mon;	
	}
	if(dat<10){
		dat="0"+dat;
	}
	
	var maxi=yea+"-"+mon+"-"+dat;
	$("#periodto").attr("max",maxi);
	var from_date=$("#periodfrom").val();
	if(from_date!=''){
		$("#periodto").attr('min',from_date);	
	}	
});

function addrowhtml(){
	var addr='<div class="medrdeprows">'
			+'<input type="text" name="medicaldependent[i].dependentid" class="depid" value="0" style="display:none">'
			+'<div th:if="${medrdata.getClaimid() == 0 and stat != "Save"}">'
			+'<div class="w3-row">'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Dependent Name:&nbsp;</label>'
			+'<select name="medicaldependent[i].dependentid" class="w3-input w3-border w3-round depenid" onchange="dependetail(this.value)">'+$("#depenid").html()+'</select>'
			+'<input type="text" name="medicaldependent[i].dependentname" class="w3-input w3-border w3-round dependentname" style="display:none">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("dependentid")!=null}" th:text="${bindingResult.getFieldError("dependentid").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Gender:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].gender" class="w3-input w3-border w3-round gender" readonly>'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("gender")!=null}" th:text="${bindingResult.getFieldError("gender").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Date of Birth:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].dateofbirth" class="w3-input w3-border w3-round dateofbirth" readonly>'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("dateofbirth")!=null}" th:text="${bindingResult.getFieldError("dateofbirth").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Age:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].age" class="w3-input w3-border w3-round age" value="0" readonly>'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("age")!=null}" th:text="${bindingResult.getFieldError("age").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'</div>'+'<div class="w3-row">'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Relationship:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].relationship" class="w3-input w3-border w3-round relationship" readonly>'
			+'<input type="text" name="medicaldependent[i].relationshipid" class="w3-input w3-border w3-round relationshipid" value="0" style="display:none" readonly>'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("relationship")!=null}" th:text="${bindingResult.getFieldError("relationship").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Marital Status:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].maritalstatus" class="w3-input w3-border w3-round maritalstatus">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("maritalstatus")!=null}" th:text="${bindingResult.getFieldError("maritalstatus").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'</div>'+'</div>'+'</div>';
	
	return addr;	
}

function addrowdep(){
	var addr='<div class="diveach2" th:each="em, stats: ${medrdata.getMedicaldependent()}">'+'<div class="medrdeprows">'
			+'<input type="text" name="medicaldependent[i].dependentid" class="depid" value="0" style="display:none">'
			+'<div class="w3-row">'+'<div class="w3-quarter w3-container" th:if="${mode == "edit"}">'+'<p>'
			+'<label>Dependent Name:&nbsp;</label>'
			+'<select name="medicaldependent[i].dependentnameid" class="w3-input w3-border w3-round depenid" onchange="dependetail(this.value)">'
			+$("#depenid").html()+'</select>'+'<input type="text" name="medicaldependent[i].dependentname" class="w3-input w3-border w3-round dependentname" style="display:none">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("dependentid")!=null}" th:text="${bindingResult.getFieldError("dependentid").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Gender:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].gender" class="w3-input w3-border w3-round gender" readonly>'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("gender")!=null}" th:text="${bindingResult.getFieldError("gender").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'<div class="w3-quarter w3-container">'+'<p>'+'<label>Date of Birth:&nbsp;</label>'
			+'<input type="text" class="w3-input w3-border w3-round dateofbirth1" readonly>'			
			+'<input type="text" name="medicaldependent[i].dateofbirth" class="w3-input w3-border w3-round dateofbirth" readonly style="display:none">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("dateofbirth")!=null}" th:text="${bindingResult.getFieldError("dateofbirth").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'<div class="w3-quarter w3-container">'+'<p>'+'<label>Age:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].age" class="w3-input w3-border w3-round age" readonly>'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("age")!=null}" th:text="${bindingResult.getFieldError("age").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'</div>'+'<div class="w3-row">'
			
			+'<div class="w3-quarter w3-container">'+'<p>'+'<label>Relationship:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].relationship" class="w3-input w3-border w3-round relationship" readonly>'
			+'<input type="text" name="medicaldependent[i].relationshipid" class="w3-input w3-border w3-round relationshipid" value="0" style="display:none" readonly>'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("relationship")!=null}" th:text="${bindingResult.getFieldError("relationship").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'<div class="w3-quarter w3-container" th:if="${mode == "edit"}">'+'<p>'+'<label>Marital Status:&nbsp;</label>'
			+'<input type="text" name="medicaldependent[i].maritalstatus" class="w3-input w3-border w3-round maritalstatus">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("maritalstatus")!=null}" th:text="${bindingResult.getFieldError("maritalstatus").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'+'</div>'+'</div>'+'</div>';
			
	return addr;
}

function addrowbill(){
	var addr='<div class="diveach" th:each="em, stats: ${medrdata.getMedicalbill()}">'+'<div class="medrbilrows">'
			+'<input type="text" name="medicalbill[i].billid" class="billid" style="display:none" value="0">'
			+'<div class="w3-row">'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Description of Expenses/Bill:&nbsp;</label>'
			+'<input type="text" name="medicalbill[i].billdescription" class="w3-input w3-border w3-round billdescription">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("billdescription")!=null}" th:text="${bindingResult.getFieldError("billdescription").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Bill Number:&nbsp;</label>'
			+'<input type="text" name="medicalbill[i].billnumber" class="w3-input w3-border w3-round billnumber" >'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("billnumber")!=null}" th:text="${bindingResult.getFieldError("billnumber").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Bill Date:&nbsp;</label>'
			+'<input type="date" name="medicalbill[i].billDate" class="w3-input w3-border w3-round billDate">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("billDate")!=null}" th:text="${bindingResult.getFieldError("billDate").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Bill Amount:&nbsp;</label>'
			+'<input type="text" name="medicalbill[i].billamount" class="w3-input w3-border w3-round billamount" value="0" onchange="billcount()">'
			+'<span style="color:red" th:if="${bindingResult != null && bindingResult.getFieldError("billamount")!=null}" th:text="${bindingResult.getFieldError("billamount").getDefaultMessage()}"></span>'
			+'</p>'+'</div>'
			
			+'</div>'+'<div class="w3-row">'+'<div class="w3-quarter w3-container">'+'<p>'
			+'<label>Remarks(if any):&nbsp;</label>'
			+'<input type="text" name="medicalbill[i].remark" class="w3-input w3-border w3-round remark">'
			+'</p>'+'</div>'+'</div>'+'</div>'+'</div>';
	
	return addr;	
}

function addrow(f){
	if(f==1){
		var cl2=document.getElementsByClassName("medrbilrows");
		if(mode!='edit'){
			var addr='<div class="medrbilrows">'+$(cl2[0]).html()+'</div>';	
		}
		else{
			var addr=addrowbill();
		}
		
		var visrow=document.getElementsByClassName("medrbilrows");
		for(i=0;i<visrow.length;i++){
			visrow[i].style.display="none";
		}
		$('#medbillrows').append(addr);
		$("#countrow").text(visrow.length+" of "+visrow.length);	
	}
	else{
		var cl2=document.getElementsByClassName("medrdeprows");
		if(mode!='edit'){
			var addr=addrowhtml();	
		}
		else{
			var addr=addrowdep();
		}
		
		var visrow=document.getElementsByClassName("medrdeprows");
		for(i=0;i<visrow.length;i++){
			visrow[i].style.display="none";
		}
		$('#meddeprows').append(addr);
		$("#countrow2").text(visrow.length+" of "+visrow.length);	
	}	
}

function delrow(f){
	if(f==1){
		var childnum=$("#medbillrows").children().length;	
	}
	else{
		var childnum=$("#meddeprows").children().length;
	}
	if (childnum>1){
		if(f==1){
			if(mode!='edit'){
				var rows=document.getElementsByClassName("medrbilrows");	
			}
			else{
				var rows=document.getElementsByClassName("diveach");
			}
		}
		else{
			if(mode!='edit'){
				var rows=document.getElementsByClassName("medrdeprows");	
			}
			else{
				var rows=document.getElementsByClassName("diveach2");
			}
		}
			
		for(i=0;i<rows.length;i++){
			if(mode!='edit'){
				if($(rows[i]).css('display')=="block"){
					var cur=$(rows[i]);
					break;
				}	
			}
			else{
				if($(rows[i]).children().css('display')=="block"){
					var cur=$(rows[i]);
					break;
				}
			}				
		}
		
		if(mode!='edit'){
			if($(cur).prev().length>0){
				$(cur).prev().css("display","block");	
			}
			else{
				$(cur).next().css("display","block");
			}	
		}
		else{
			if($(cur).prev().children().length>0){
				$(cur).prev().children().css("display","block");	
			}
			else{
				$(cur).next().children().css("display","block");
			}
		}	
		
		$(cur).remove();				
	}
	if(i==0){
		i=1;
	}
	if(f==1){
		var visrow=document.getElementsByClassName("medrbilrows");
		$("#countrow").text(i+" of "+visrow.length);
		billcount();	
	}
	else{
		var visrow=document.getElementsByClassName("medrdeprows");
		$("#countrow2").text(i+" of "+visrow.length);
	}	
}

function changerow(f,m){	
	if(f==1){
		var rows=document.getElementsByClassName("medrbilrows");	
	}
	else{
		var rows=document.getElementsByClassName("medrdeprows");
	}
	
	var a=0;
	for(i=0;i<rows.length;i++){
		if($(rows[i]).css('display')=="block"){
			var cur=$(rows[i]);
			a=i;
			break;
		}		
	}
	
	if(m=="P"){
		if(mode!='edit' && mode!='view'){
			if($(cur).prev().length>0){
				$(cur).css("display","none");
				$(cur).prev().css("display","block");
				if(a>0){
					if(f==1){
						$("#countrow").text(a+" of "+rows.length);	
					}
					else{
						$("#countrow2").text(a+" of "+rows.length);
					}
				}
			}
			else{
				bootbox.alert({
	                size: 'medium',	                
	                message:'No previous rows exist!!'               
            	});
			}	
		}
		else{
			if($(cur).parent().prev().length>0){
				$(cur).css("display","none");
				$(cur).parent().prev().children().css("display","block");
				if(a>0){
					if(f==1){
						$("#countrow").text(a+" of "+rows.length);	
					}
					else{
						$("#countrow2").text(a+" of "+rows.length);
					}
				}
			}
			else{
				bootbox.alert({
	                size: 'medium',	                
	                message:'No previous rows exist!!'               
            	});
			}
		}	
	}
	
	if(m=="N"){
		if(mode !='edit' && mode!='view'){
			if($(cur).next().length>0){
				$(cur).css("display","none");
				$(cur).next().css("display","block");
				if(f==1){
					$("#countrow").text(a+2+" of "+rows.length);	
				}
				else{
					$("#countrow2").text(a+2+" of "+rows.length);
				}
					
			}
			else{
				bootbox.alert({
	                size: 'medium',	                
	                message:'No next rows exist!!'               
            	});
			}
		}
		else{	
			if($(cur).parent().next().length>0){
				$(cur).css("display","none");
				$(cur).parent().next().children().css("display","block");
				if(f==1){
					$("#countrow").text(a+2+" of "+rows.length);	
				}
				else{
					$("#countrow2").text(a+2+" of "+rows.length);
				}
					
			}
			else{
				bootbox.alert({
	                size: 'medium',	                
	                message:'No next rows exist!!'               
            	});
			}
		}	
	}
}

function billcount(){
	//debugger;
	var nob=$("#noofbills");
	var bil=$(".billnumber");
	var cnt=0;
	
	for(i=0;i<bil.length;i++){
		if($(bil[i]).val()!=0){		
			cnt+=1;
		}
	}
	$(nob).val(cnt);
	
	var tamt=$("#totalamount");
	var bamt=$(".billamount");
	var amt=0;
	
	for(i=0;i<bamt.length;i++){
		if($(bamt[i]).val()==""){
			$(bamt[i]).val("0")
		}		
		amt+=parseFloat($(bamt[i]).val());
	}
	$(tamt).val(amt);
}

function changehosname(id){
	//debugger;
	var cltype=$("#claimtype");
	//var hstype=$("#hospitaltype");
	var clid=$(cltype).children("option").filter(":selected").val();
	//var hosid=$(hstype).children("option").filter(":selected").val();
	
	if(clid==0){
		$("#claimsp").text("Select Claim Type First");
	}	
	else{
		var url = '/reimbursement/medicHosName/'+id;
		namelov="";

			$.ajax({
				type: 'GET',
				url: url,
				dataSrc: '',
				
				dataType: 'json',
				success: function(data){
					namelov+='<option value="0">Select</option>';
					for(i=0;i<data.hospitalnamelov.length;i++){
						namelov+='<option value="'+data.hospitalnamelov[i].hospitalnamelovid+'">'+data.hospitalnamelov[i].name+'</option>';
					}
					$('#hospitalname').html(namelov);
					$('#hospitalname').prop("disabled",false);
				},
				error: function(e){
					console.log("ERROR: " + JSON.stringify(e));
				}
			});	
		
		if(id!='1'){
			$("#hospitalnametext").css("display","block");
			$('#hospitalnametext').prop("disabled",false);
			$("#hospitalname").css("display","none");
			$('#hospitalname').prop("disabled",true);
			
			if(id=='2'){
				$("#emergencycerti").val("Yes");
			}
			else{
				$("#emergencycerti").val("No");
			}		
		}
		else{
			$("#hospitalnametext").css("display","none");
			$('#hospitalnametext').prop("disabled",true);
			$("#hospitalname").css("display","block");
			
			$('#hospitalname').prop("disabled",false);
			$("#emergencycerti").val("No");
		}
		
		}		
}

function dependetail(id){
	var rows=document.getElementsByClassName("medrdeprows");
	var a=0;
	for(i=0;i<rows.length;i++){
		if($(rows[i]).css('display')=="block"){
			var cur=$(rows[i]);
			a=i;
			break;
		}		
	}
	
	var dep=$(".depenid");
	var gen=$(".gender");
	var dob=$(".dateofbirth");
	var dob1=$(".dateofbirth1");
	var age=$(".age");
	var relid=$(".relationshipid");
	var reln=$(".relationship");
	
	if(id=="0"){
		$(dep[a]).val("");
		$(gen[a]).val("");
		$(dob[a]).val("");
		$(dob1[a]).val("");
		$(age[a]).val("");
		$(relid[a]).val("");
		$(reln[a]).val("");
	}
	else{
		var url = '/reimbursement/medicDepDet/'+id;
		namelov="";
		
		$.ajax({
			type: 'GET',
			url: url,
			dataSrc: '',
			
			dataType: 'json',
			success: function(data){
				var d=new Date(data.dateofbirth);
				var month = d.getMonth() + 1;
				if(month<10){
					month="0"+month;
				}
				var date = d.getDate();
				var year = d.getFullYear();
				var da=month + '/' + date + '/' + year;
				if(date<10){
					date="0"+date;
				}
				var da1=year+'-'+month+'-'+date;
				
				$(gen[a]).val(data.genderdsc);
				$(dob[a]).val(da1);
				$(dob1[a]).val(da);
				//$(age[a]).val(data.age);
					
				var cd=new Date();					
				var aged=cd.getFullYear()-d.getFullYear();	
				var m=cd.getMonth()-d.getMonth();	
				if(m<0 || (m==0 && cd.getDate<	d.getDate())){	
					aged--;	
				}	
				$(age[a]).val(aged);	
				
				$(relid[a]).val(data.relationship);
				$(reln[a]).val(data.relationshipdsc);		
			},
			error: function(e){
				console.log("ERROR: " + JSON.stringify(e));
			}
		});	
	}
}

function claimedforchange(id){
	delallrows();
	if(id=="Self"){
		$("#patientdiv").hide();
		var dep=$(".dependentname");
		var depid=$(".depenid");
		var gen=$(".gender");
		var dob=$(".dateofbirth");
		
		var dob1=$(".dateofbirth1");
		var age=$(".age");
		var relid=$(".relationshipid");
		var reln=$(".relationship");
		var mar=$(".maritalstatus");	
		var a=0;
		
		$(dep[a]).css("display","block");
		$(depid[a]).css("display","none");
		
		var url = '/reimbursement/medicselfdtl';
		$.ajax({
			type: 'GET',
			url: url,
			dataSrc: '',
			
			dataType: 'json',
			success: function(data){
				
				var dateString = data.dateofbirth; // Oct 23
				var dateParts = dateString.split("-");
				//var dateObject = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]);
				var dateObject = new Date(+ dateParts[0] ,dateParts[1] - 1, dateParts[2]  );
				var d=new Date(dateObject);
				var month = d.getMonth() + 1;
				if(month<10){
					month="0"+month;
				}
				var date = d.getDate();
				var year = d.getFullYear();
				var da=month + '/' + date + '/' + year;
				if(date<10){
					date="0"+date;
				}
				var da1=year+'-'+month+'-'+date;
				
				var cd=new Date();
				var aged=cd.getFullYear()-d.getFullYear();
				var m=cd.getMonth()-d.getMonth();
				if(m<0 || (m==0 && cd.getDate<	d.getDate())){
					aged--;
				}
				
				$(dep[a]).val(data.personname);
				$(gen[a]).val(data.gender);
				$(dob[a]).val(da1);
				$(dob1[a]).val(da);
				$(age[a]).val(aged);
				$(relid[a]).val(0);		
				$(reln[a]).val("Self");	
				var datamar=data.maritalstatus;			
				if(datamar==''||datamar==null){	
					var datamar='NA';	
				}	
				$(mar[a]).val(datamar);
			},
			error: function(e){
				console.log("ERROR: " + JSON.stringify(e));
			}
		});		
	}
	else{
		$("#patientdiv").show();
	}	
		
}

function delallrows(){
	if(mode!='edit'){
		var rows=document.getElementsByClassName("medrdeprows");	
	}
	else{
		var rows=document.getElementsByClassName("diveach2");
	}
	$(rows).remove();
	$("#countrow2").text("1 of 1");
	addrow('2');
}

function claimchange(id){
	//debugger;
	$("#claimsp").text("");
	$("#admisp").text("");
	$("#dissp").text("");
	var bildt=$(".billDate");
	bildt.removeAttr('readonly');
	for(i=0;i<bildt.length;i++){
		$(bildt[i]).val("");
	}
	$("#hospitaltype").val("0");
	$("#hospitalname").val("0");
	$("#hospitalnametext").val("");
	
	$("#hospitalnametext").css("display","none");
	$('#hospitalnametext').prop("disabled",true);
	$("#hospitalname").css("display","block");
	$('#hospitalname').prop("disabled",true);
	
	if(id=='132' || id=='130'){
		$("#admissiondate").removeAttr('readonly');
		$("#dischargedate").removeAttr('readonly');		
	}
	else{
		$("#admissiondate").val("");
		$("#dischargedate").val("");
		$("#admissiondate").attr('readonly','readonly');
		$("#dischargedate").attr('readonly','readonly');
	}
	
	if(id=='283'){
		$("#hospitalnametext").css("display","block");
		$('#hospitalnametext').prop("disabled",false);
		$("#hospitalname").css("display","none");
		$('#hospitalname').prop("disabled",true);				
	}
}

function checkdate(){
	var from=$("#admissiondate").val();	
	var to=$("#dischargedate").val();
	
	if(from!="" && to!=""){
		$("#admisp").text("");
		$("#dissp").text("");
		if(from>to){
			$("#admisp").text("Admission Date cannot be less than Discharged Date");
			$("#dissp").text("Discharged Date cannot be greater than Admission Date");
		}
	}	
}

function setdaterange(){
	//debugger;
	var bildt=$(".billDate");
	var id=$("#claimtype").val();	
	if(id=="0"){
		//alert("Fill Claim Type.");
		/*bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Please Select Claim Type before Proceeding"                
                });
		$("#claimsp").text("Fill Claim Type.");
		$(".billDate").val("");*/
	}
	else if(id=='130' || id=='132'){
		var from=$("#admissiondate").val();
		var to=$("#dischargedate").val();
		if(from==""||to==""){
			//alert("Fill admission date and discharged date.");
			/*bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Please Select Admission Date and Discharge Date before Proceeding"                
                });*/
			$("#admisp").text("Fill admission date and discharged date.");
			$("#dissp").text("Fill admission date and discharged date.");
			$(".billDate").val("");
		}
		else{
			from=new Date(from);
			to=new Date(to);
			if(from==""||to==""){
				//alert("Fill admission date and discharged date.");
				$("#admisp").text("Fill admission date and discharged date.");
				$("#dissp").text("Fill admission date and discharged date.");
			}
			else{				
				var minmon=from.getMonth()-2;
				var minyear=from.getFullYear();
				var mindate=from.getDate();
				if(minmon==0){
					minmon=1;
				}
				if(minmon<0){
					minmon=12+minmon;
					minyear=minyear-1;
				}
				if(minmon<10){
					minmon="0"+minmon;	
				}
				if(mindate<10){
					mindate="0"+mindate;
				}
				
				var maxmon=to.getMonth()+4;
				var maxyear=to.getFullYear();
				var maxdate=to.getDate();
				if(maxmon>12){
					maxmon=maxmon-12;
					maxyear=maxyear+1;
				}
				if(maxmon<10){
					maxmon="0"+maxmon;	
				}
				if(maxdate<10){
					maxdate="0"+maxdate;
				}
				
				var mini=minyear+"-"+minmon+"-"+mindate;
				var maxi=maxyear+"-"+maxmon+"-"+maxdate;
				
				$(".date").attr("max",maxi);
				/*for(i=0;i<bildt.length;i++){
					$(bildt[i]).attr("max",maxi);
					$(bildt[i]).attr("min",mini);
				}*/	
			}
		}		
	}
}

function seri(x,_id){	
	//debugger;
	if($("#declaration:checked").length>0){
		//var m=event.toElement.id;
		
		var m=_id;
		$("#"+m).css("display","none");
	}
	var from=$("#admissiondate").val();
	var to=$("#dischargedate").val();
	//alert(from+" "+to);
		
	var id=$(".billid");
	var descr=$(".billdescription");
	var num=$(".billnumber");
	var date=$(".billDate");
	var amt=$(".billamount");
	var remark=$(".remark");
	var hosty=$("#hospitaltype");
	var hosid=$(hosty).children("option").filter(":selected").val();
		
	for(var i=0;i<id.length;i++){
		$(id[i]).attr("name","medicalbill["+i+"].billid");
		$(descr[i]).attr("name","medicalbill["+i+"].billdescription");
		$(num[i]).attr("name","medicalbill["+i+"].billnumber");
		$(date[i]).attr("name","medicalbill["+i+"].billDate");
		$(amt[i]).attr("name","medicalbill["+i+"].billamount");
		$(remark[i]).attr("name","medicalbill["+i+"].remark");		
	}
	
	var id=$(".depid");
	var name=$(".dependentname");
	var gen=$(".gender");
	var dob=$(".dateofbirth");
	var age=$(".age");
	var rel=$(".relationship");
	var mar=$(".maritalstatus");
	var depen=$(".depenid");
	var relid=$(".relationshipid");
	var reln=$(".relationship");
	
	for(var i=0;i<id.length;i++){
		$(id[i]).attr("name","medicaldependent["+i+"].dependentid");
		$(name[i]).attr("name","medicaldependent["+i+"].dependentname");
		$(gen[i]).attr("name","medicaldependent["+i+"].gender");
		$(dob[i]).attr("name","medicaldependent["+i+"].dateofbirth");
		$(age[i]).attr("name","medicaldependent["+i+"].age");
		$(rel[i]).attr("name","medicaldependent["+i+"].relationship");
		$(mar[i]).attr("name","medicaldependent["+i+"].maritalstatus");
		$(depen[i]).attr("name","medicaldependent["+i+"].dependentnameid");
		$(relid[i]).attr("name","medicaldependent["+i+"].relationshipid");
		$(reln[i]).attr("name","medicaldependent["+i+"].relationship");		
		if($("#selfordependent").val()!="Self"){
			$(name[i]).val($(depen[i]).children("option").filter(":selected").text());	
		}						
	}
	
	$("#claimtypevalue").val($("#claimtype").children("option").filter(":selected").text());
	$("#hospitaltypevalue").val($("#hospitaltype").children("option").filter(":selected").text());
	
	var cltype=$("#claimtype");
	var clid=$(cltype).children("option").filter(":selected").val();
	var hname=document.getElementsByName("hospitalname");
	if(hosid=='1'){
		$('#hospitalnametext').prop("disabled",false);
		$("#hospitalnametext").val($("#hospitalname").children("option").filter(":selected").text());		
	}	
	$("#precriptionenclosename").val($("#prescriptionenclose").children("option").filter(":selected").text());
	$("#illnesstypevalues").val($("#illnesstype").children("option").filter(":selected").text());
	
	if(x=='2'){
		var sts="Submitted";
	}
	/*else if(x=='1'){
		var sts="Updated";
	}*/
	
	else{
		var sts="Saved";
	}
	if($("#emergencycerti").val()=="Yes" && ($("#fi").val()=="" || $("#fi").val()==null)){
		$("#"+m).css("display","block");
	}
	
	datavalidation(x,sts);
}

function datavalidation(x,sts){
	//debugger;
	var cmode=$("#checkmode");
	var chk="ok";
	var from=$("#periodfrom").val();
	var to=$("#periodto").val();
	var fil=$("#fi").val();
	var emer=$("#emergencycerti").val();
		
	if(emer=="Yes" && (fil=="" || fil==null)){
		/*chk="no";
		bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Upload attachment first."                
                });*/
// comment by rajat on 25-may-2021 
	}
	
	if(chk=="ok"){
		if($("#declaration:checked").length>0){
			if(x==0){
				saveDataFunc('Save',sts);
			}
			else if(x==1){
				saveDataFunc('Update',sts);
			}
			else if(x==2){
				saveDataFunc('Submit',sts);
			}
			
		}
		else{
			bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "You cannot proceed without declaration."                
                });
		}
	}	
}

function saveDataFunc(str,sts)
{	
	//debugger;
	var frmid="savemedreim";
	var attachm=$("#attachment");
	var personnumber=$("#pnum").val();
	$("#statusid").val(str);
	$("#sts").val(sts);
	//alert($("#statusid").val()+" "+$("#sts").val());
	var doctype="MedicalReimbursement";
	
	// done by asmita on 28-06-2021 
	/*var claimtype = $("#claimtype").val();
	alert(claimtype);*/
	
	if(str=="Save"){
		sts="saved";
	}
	else if(str=="Update"){
		sts="updated";
	}
	
	else{
		sts="submitted";
	}
	
	var filepath="";
	filepath+=personnumber+"/"+doctype;
	if($("#fi").val()!=""){
		$(attachm).val("/EmployeeDocs/"+filepath);	
	}		
	
	var data = new FormData(document.getElementById(frmid));
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
	$.ajax({
	url: "/reimbursement/medrSaveBtn/"+str,
	type: "POST",
	enctype: "multipart/form-data",
	data: data,
	cache: false,
	contentType:false,
	processData: false,
	timeout:600000,
	success: function(data){
		
		$("#replace_div").html(data);
		if( resultfinal.includes("Success")){
			bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
					message:"You have successfully "+sts+" Medical Reimbursement details.",
					callback:function(){
						$("#replace_div").load("/reimbursement/medireimbsearch");
					}
				});
			}			
		else if(resultfinal=="alreadyApplied"){
			bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Claim for specified period has already been raised.<br>Please re-check the dates and try again."                
            });
		}
		else{
			bootbox.alert({
                size: 'medium',
                title: '<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
                message: "Fill all the mandatory fields and re-upload attachment.",
				callback:function(){
					$('.depenid').css('display','block');
					var claimtype = $("#claimtype").val();
					if(claimtype=='132' || claimtype=='130'){
						$("#admissiondate").removeAttr('readonly');
						$("#dischargedate").removeAttr('readonly');
						$("#adminsp").css('display','block');
						$("#dischsp").css('display','block');	
					}
					else{
						$("#admissiondate").val("");
						$("#dischargedate").val("");
						$("#admissiondate").attr('readonly','readonly');
						$("#dischargedate").attr('readonly','readonly');
						$("#adminsp").css('display','none');
						$("#dischsp").css('display','none');
					}
				}                
            });			
		}
	},
	error: function(data){
		$("#replace_div").html(data);
		console.log("ERROR : "+JSON.stringify(data));
	}
	});
}

function backBtnFunc(){
	var url = "/reimbursement/medireimbsearch";
	$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
    $.ajax({
		type:"GET",
		url:url,
		success:function(result){
			$('#replace_div').html(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});	
}

function submitbtn(){
	window.open("../reimbursement/medicreimbAnnexure/"+clid,'window','width=1000,height=1000');
}




//////////////////////////APPROVAL\\\\\\\\\\\\\\\\\\\\\\\\\\\\

$('#taxableincome').on('change', function(e){
	if(parseFloat($('#taxableincome').val())<=parseFloat($('#txtAmount').val())){
		var a = parseFloat($('#txtAmount').val())-parseFloat($('#taxableincome').val());
		$('#nonTaxAmount').val(a);
	}else{
		bootbox.alert({
			size: 'medium',
			title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
			message:"Taxable Amount cannot be greater than approved amount."
		});
		$('#taxableincome').val("0.0");
	}
});


function approvalSubmit(_status)
{
	//debugger;
	if($("#txtAmount").val().trim()==''){
		$("#txtAmount").val("0.0");
	}
	if ($('#taxableincome').val().trim()==''){
		$('#taxableincome').val("0.0");
	}
	if ($('#nonTaxAmount').val().trim()==''){
		$('#nonTaxAmount').val("0.0");
	}
	if($('#taxableincome').val()==0.0){
		$('#nonTaxAmount').val($("#txtAmount").val());
	}
	
	var check="false";
	var formData = $('#medr_SAVE').serialize();
	
	var amt=$("#txtAmount").val();
	var cmt=$("#txtComment").val();

	if(_status=='Approved'){
			if(amt=="" ){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Request cannot be Approved without filling Approved Amount."				
				});
			}
			else{
				check="true";
			}
		}
		else{
			if(cmt==""){
				bootbox.alert({
					size: 'medium',
					title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
					message:"Request cannot be Rejected without filling Comments."				
				});
			}
			else{
				check="true";
			}
	}
	if(check=="true"){
		$('#replace_div').html("<div class='w3-container' style='margin-left:100px;margin-top:20px; width: 100%; text-align: center'><i class='fa fa-spinner fa-pulse' style='font-size: 70px; color: grey;'></i></div>");
		$.ajax({
		url: "/reimbursement/mediRApproval/"+_status,
		type: "POST",
		cache: false,
		data: formData,
		processData: false,
		contentType: 'application/x-www-form-urlencoded',
		dataType: "json",
		success:function(result){
			
			if(result.status=="true"){
						bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-check" style="font-size:25px; color:green;">&nbsp;&nbsp;Success</i>',
							message:"You have successfully "+_status+".",
							callback:function(){
								window.location = "/selfservice";
							}
						});	
						}else if(result.status=="false"){
							bootbox.alert({
							size: 'medium',
							title:'<i class="fa fa-times-circle" style="font-size:25px; color:red;">&nbsp;&nbsp;Error</i>',
							message:"Something went wrong, Please take action again.",
							callback:function(){
								window.location = "/selfservice";
							}
						});
						}			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
		});
	}

}



function getapprovalhistory(rid,claimid){
	$("#apprhistorypopup").css("display","block");
	//alert(rid+"||"+claimid);
	var jsonurlappr ='/approvalhistory/viewapprovalhistory';
	$.ajax({
		type: 'POST',
		url: jsonurlappr,
		data: {
			"rid": rid,
			"claimid": claimid,
		},
		dataSrc: '',
		dataType: 'json',
		success: function(data){
			jsonData=data;
			populateAPPRTable(jsonData);
		},
		error: function(e){
			console.log("There was an error with request...");
			console.log("error: " + JSON.stringify(e));
		}
	});
}


function populateAPPRTable(data){
	$("#APPROVAL_HISTORY").DataTable().clear();
	var dataLength = Object.keys(data).length;
	if(dataLength == 0){
		$('#apprresultSec').css('display', 'none');
		$('#noDataAPPR').css('display', 'block');
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#APPROVAL_HISTORY").dataTable().fnAddData([
				dat.approvallevel,
				dat.submitdate,
				dat.approverpersonname,
				dat.approvedamt,
				dat.taxableincome,
				dat.nontaxableincome,
				dat.status,
				dat.message
			]);
		}
		$('#apprresultSec').css('display', 'block');
		$('#noDataAPPR').css('display', 'none');
	}
}

$('#btnBack').on('click', function(e) {
	var url = $(this).attr("rm");
     $('#replace_div').load(url);  
});