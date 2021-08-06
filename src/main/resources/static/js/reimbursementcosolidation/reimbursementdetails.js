
function loadReimbursementDetailsData(){
	var remType=$('#remtype').val();
	var fromDate=$('#fromdate').val();
	var toDate=$('#todate').val();
	var remStatus=$('#status').val();
	var curl="/consolidate/getReimbursementDetailsdata/"+remType+"/"+fromDate+"/"+toDate+"/"+remStatus;
	$.ajax({
		
		type: 'GET',
		url: curl,		
		contentType:"application/json",
		dataType:"json",
		success:function(result){
			populateTransportDataTable(result);			
		},
		error:function(e){
			console.log( "ERROR : "+ JSON.stringify(e) );
		}
	});
};

function populateTransportDataTable(data){
	$("#Reimbursement_History").DataTable().clear();
	var dataLength = data.length;	
	if(dataLength == 0){
		$('#resultDiv').css('display', 'none');
		$('#noData').css('display', 'block');
		
	} else {
		for(var i=0; i < dataLength; i++){
			var dat = data[i];
			$("#Reimbursement_History").dataTable({
				scrollY:'50vh',
        		scrollCollapse: true,
				paging: false,
				destroy: true,
				searching: false,
				dom: 'Bfrtip'
				/*buttons: [{
					extend: 'copy',
					text: 'Copy to Clipboard'
				}, {
					extend: 'csv',
					className: 'excelButton',
					text: 'Download to Excel'
				}, {
					extend: 'print',
					className: 'printButton'
				}]*/
			}).fnAddData([
				dat.claimid,
				dat.personname,
				dat.reimbursename,
				dat.claimedamount,
				dat.approvedamount,
				dat.status,
				dat.createddate.substring(0,10)
				]);
		}
		$('#resultDiv').css('display', 'block');
		$('#noData').css('display', 'none');
	}
};
                function status() {
					$("#status").prop('disabled', false);
				}
				function fromdate() {
					$("#fromdate").prop('disabled', false);
					
				}
				function todate() {
					$("#todate").prop('disabled', false);
					$("#todate").val('');
					
				}
				function search() {
					$("#searchbtn").prop('disabled', false);
				}
			
				$('#fromdate').on('change', function(e) {
					var from = $('#fromdate').val();
					$('#todate').attr("min", from);
				});