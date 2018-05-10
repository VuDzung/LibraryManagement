
$('#year').click(function() {
	var today = new Date()
	var dataPoints = [];

	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Total Borrowed Books of da Year"
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "column",
			indexLabelFontColor: "red",
			indexLabel: "{y}",				
			xValueFormatString: "#",
			dataPoints: dataPoints
		}]
	});


	$.ajax({
	    url : "/home/statistic/yearly" ,
	    success : function(data) {
	    	for (var i = 0; i < data.length; i++) {
	    		dataPoints.push({
	    			x: data[i]._year,
	    			y: data[i].borrowed
	    		});
	    	}
	    	chart.render();
	    	console.log('ok');
	    }
	 });

	
	var dataPoints2 = [];
	var chart2 = new CanvasJS.Chart("chartContainer2", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Top 5 Borrowed Books of The Year"
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "pie",
			indexLabelFontColor: "red",
			indexLabel: "{title}: {y} (times)",				
			xValueFormatString: "#",
			dataPoints: dataPoints2
		}]
	});


	$.ajax({
	    url : "/home/statistic/topbook/" + today.getFullYear() ,
	    success : function(data2) {
	    	for (var i = 0; i < data2.length; i++) {
	    		dataPoints2.push({
	    			title: data2[i].Title,
	    			y: data2[i].total
	    		});
	    	}
	    	chart2.render();
	    	console.log('ok');
	    }
	 });
	
}) 
	
	
	
$('#month').click(function() {
	var today = new Date();
	var realMonth = today.getMonth() +1;
	var dataPoints = [];

	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Total Borrowed Books of the Month"
		},
		axisY: {
			title: "Borrowed Books (Units)",
			titleFontSize: 24
			
		},
		axisX: {
			interval: 1,
			intervalType: "month",
			labelFormatter: function(e){
				switch(e.value){
				case 1:
					return 'Jan';
					break;
				case 2:
					return 'Feb';
					break;
				case 3:
					return 'Mar';
					break;
				case 4:
					return 'Apr';
					break;
				case 5:
					return 'May';
					break;
				case 6:
					return 'June';
					break;
				case 7:
					return 'July';
					break;
				case 8:
					return 'Aug';
					break;
				case 9:
					return 'Sep';
					break;
				case 10:
					return 'Oct';
					break;
				case 11:
					return 'Nov';
					break;
				case 12:
					return 'Dec';
					break;
				default:
					return 'Dec';
				}
				
				
			},
			valueFormatString: "#"
		},
		data: [{
			type: "column",
			indexLabel: "{y}",		
			indexLabelFontColor: "red",
			xValueFormatString: "#",
			dataPoints: dataPoints
		}]
	});


	$.ajax({
	    url : "/home/statistic/" + today.getFullYear(),
	    success : function(data) {
	    	for (var i = 0; i < data.length; i++) {
	    		dataPoints.push({
	    			x: data[i]._month,
	    			y: data[i].borrowed
	    		});
	    	}
	    	chart.render();
	    	console.log('ok');
	    }
	 });
	
	

	var dataPoints2 = [];
	var chart2 = new CanvasJS.Chart("chartContainer2", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Top 5 Borrowed Books of Da Month"
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "pie",
			indexLabelFontColor: "red",
			indexLabel: "{title}: {y} (times)",				
			xValueFormatString: "#",
			dataPoints: dataPoints2
		}]
	});


	$.ajax({
	    url : "/home/statistic/topbook/" + today.getFullYear() + "/" + realMonth ,
	    success : function(data2) {
	    	for (var i = 0; i < data2.length; i++) {
	    		dataPoints2.push({
	    			title: data2[i].Title,
	    			y: data2[i].total
	    		});
	    	}
	    	chart2.render();
	    	console.log('ok');
	    }
	 });
}) 
	
	
Date.prototype.getWeek = function() {
  var onejan = new Date(this.getFullYear(),0,1);
  return Math.ceil((((this - onejan) / 86400000) + onejan.getDay()+1)/7);
}
	
$('#week').click(function() {
	var today = new Date()
	var dataPoints = [];

	var chart = new CanvasJS.Chart("chartContainer", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Total Borrowed Books of da Week"
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	  
			labelFormatter: function(e){
				switch(e.value){
				case 1:
					return 'Sunday';
					break;
				case 2:
					return 'Monday';
					break;
				case 3:
					return 'Tuesday';
					break;
				case 4:
					return 'Wednesday';
					break;
				case 5:
					return 'Thursday';
					break;
				case 6:
					return 'Friday';
					break;
				case 7:
					return 'Saturday';
					break;
				default:
					return 'Today';
				}
			},
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####",
			
	    },
		data: [{
			type: "column",
			indexLabelFontColor: "red",
			indexLabel: "{y}",				
			
			dataPoints: dataPoints
		}]
	});


	$.ajax({
	    url : "/home/statistic/" + today.getFullYear() + "/" + today.getWeek() ,
	    success : function(data) {
	    	for (var i = 0; i < data.length; i++) {
	    		if(data[6]==null)
		    		dataPoints.push({
		    			x: data[i]._day,
		    			y: data[i].borrowed
		    		});
	    		
	    	}
	    	if(data[data.length-1]._day!=7){
	    		dataPoints.push({
	    			x: 7
	    		});
	    	}
	    	chart.render();
	    	console.log(dataPoints);
	    }
	 });
	}) 
	

$('#year2').click(function() {
	var today = new Date()
	var dataPoints = [];

	var chart = new CanvasJS.Chart("chartContainer2", {
		animationEnabled: true,
		theme: "light2",
		title: {
			text: "Ahihi"
		},
		axisY: {
			title: "Borrowed Books",
			titleFontSize: 24
		},
		axisX:{	        	       
	   		interval: 1,
			intervalType: "year",
			valueFormatString: "####"
	    },
		data: [{
			type: "pie",
			indexLabelFontColor: "red",
			indexLabel: "{title}: {y} (times)",				
			xValueFormatString: "#",
			dataPoints: dataPoints
		}]
	});


	$.ajax({
	    url : "/home/statistic/topbook/" + today.getFullYear() ,
	    success : function(data) {
	    	for (var i = 0; i < data.length; i++) {
	    		dataPoints.push({
	    			title: data[i].Title,
	    			y: data[i].total
	    		});
	    	}
	    	chart.render();
	    	console.log('ok');
	    }
	 });
	}) 
	
	