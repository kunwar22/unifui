google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawVisualization);

function drawVisualization() {
		$.ajax({
			type:"GET",
			url:"/mydashboard/getManageReimb",
			contentType:"application/json",
			dataType:"json",
			success:function(result){
				//if(result.length>0){
				if(true){
					var data = new google.visualization.DataTable();
					data.addColumn('string', 'Reimbursements');
					data.addColumn('number', 'Pending');
					data.addColumn('number', 'Approved');
					data.addColumn('number', 'Rejected');
					data.addColumn('number', 'Average');
					data.addRows(result.length);
					for(i=0;i<result.length;i++){	
				  		data.setCell(i, 0,result[i].reimbursetype);
						data.setCell(i, 1,result[i].pendingcount);
						data.setCell(i, 2,result[i].approvedcount);
						data.setCell(i, 3,result[i].rejectedcount);
						data.setCell(i, 4,result[i].avgcount);
					}												  			  				 
			  
					var options = {
						title : 'Analytics of Managed Reimbursement',
						vAxis: {title: 'Counts'},
						hAxis: {title: 'Reimbursements'},
						seriesType: 'bars',
						series: {3: {type: 'line'}},
						colors:['#98c04e','#00a78b','#fe9c00','black']
					};
		
					var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
					chart.draw(data, options);
				}
			},
			error:function(e){
				console.log( "ERROR : "+ JSON.stringify(e) );
			}
		});	

}