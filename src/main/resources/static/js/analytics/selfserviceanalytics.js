	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawVisualization);

	function drawVisualization() {
			// Some raw data (not necessarily accurate)
			/*var data = google.visualization.arrayToDataTable([
			['Reimbursements', 'Pending', 'Approved', 'Rejected','Average'],
			['Telephone',  1,      5,         7,             4.3],
			['Transport',  2,      6,        1,             3],
			['TADK',  4,      4,        2,             3.3],
			['Electricity',  1,      7,        3,             3.6],
			['CDA',  5,      4,         2,             3.6]
			]);*/
			
			$.ajax({
				type:"GET",
				url:"/mydashboard/getClaimedReimb",
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
							title : 'Analytics of Claimed Reimbursement',
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
			
			/*var chart = new google.visualization.ComboChart(document.getElementById('chart_div1'));
			chart.draw(data, options);
			var chart = new google.visualization.ComboChart(document.getElementById('chart_div2'));
			chart.draw(data, options);*/
	}