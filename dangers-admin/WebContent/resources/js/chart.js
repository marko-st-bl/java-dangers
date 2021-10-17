		function drawChart(data){
			
			console.log(data);
			
			var ctx = document.getElementById('activity').getContext('2d');
			console.log(data[12]);
			let myData = new Array(24);
			for (let i=0; i<myData.length; i++){
				if(data[i]){
					myData[i]=data[i];
				} else{
					myData[i]=0;
				}
			}
			var myChart = new Chart(ctx, {
				type: 'bar',
				data: {
					labels: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13',
						'14', '15', '16', '17', '18', '19', '20', '21', '22', '23'],
					datasets: [{
						label: '# of Users',
						data: myData,
						backgroundColor: 'rgba(245, 69, 56, 0.9)',
					}]
				},
				options: {
					scales: {
						yAxes: [{
							ticks: {
								beginAtZer: true,
								stepSize: 1
							}
						}]
					}
				}
			});
		}