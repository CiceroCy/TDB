$(document).ready(function() {
	google.charts.load('current', {
		packages : [ 'corechart', 'bar' ] //bar
	});
//	google.charts.setOnLoadCallback(drawChart);
});

function drawChart() {
	
	NTTCore.loader.showLoader();
	
	try{
		var dateFrom = $('#dateFrom').val();
		var dateUntil  = $('#dateUntil').val();
		
		if(dateFrom == "" || dateUntil == ""){
			$('#errorRequiredField').show();
			return;
		} else {
			var From = $("#dateFrom").val().split("/");
			var Until =  $("#dateUntil").val().split("/");
			var dateFromDate = new Date(From[2], From[1] - 1, From[0]);
			var dateUntilDate  = new Date(Until[2], Until[1] - 1, Until[0]);
			$('#errorRequiredField').hide();
			if(dateUntilDate <= dateFromDate){
				$('#errorUntilLowerThanFrom').show();
				return;
			} else {
				$('#errorUntilLowerThanFrom').hide();
			}
		}
	
		var jsonData = $.ajax({
			url : "dashboard/action/getTicketsData?dateFrom="+dateFrom+"&dateUntil="+dateUntil,
			dataType : "json",
			async : false
		}).responseText;
		
		var options1 = {'title':'Tickets Xtts',
					   'legend': { position: 'none' },
					   'height': 300,
					   'colors': ['#6496e4', '#808080'],
					   'bar': {groupWidth: "95%"},
					   'seriesType': 'bars',
					   'series': {13: {type: 'line'}}
					   };
		
		var options2 = {'title':'Tickets Xtts',
				   'legend': { position: 'none' },
				   'height': 300,
				   'colors': ['#6496e4', '#808080'],
				   'bars': 'horizontal',
				   'bar': {groupWidth: "95%"}
				   };
		
		// Create our data table out of JSON data loaded from server.
		var data = new google.visualization.DataTable(jsonData);
	
		data.sort([{column: 14}]);
		
		var view = new google.visualization.DataView(data);
		
		view.setColumns([0,1,{calc: "stringify",sourceColumn: 2,type: "string", role: "annotation" },3,{calc: "stringify",sourceColumn: 4,type: "string", role: "annotation" },
		                 5,{calc: "stringify",sourceColumn: 6,type: "string", role: "annotation" },7,{calc: "stringify",sourceColumn: 8,type: "string", role: "annotation" },
		                 9,{calc: "stringify",sourceColumn: 10,type: "string", role: "annotation" },11,{calc: "stringify",sourceColumn: 12,type: "string", role: "annotation" }]);
		
		var columnchart = new google.visualization.ComboChart(document.getElementById('columnchart'));
		columnchart.draw(view, options1);
		
		var barchart = new google.visualization.BarChart(document.getElementById('barchart'));
		barchart.draw(view, options2);

//		//NEW CHART GRAPH
//		var columnchart2 = new google.charts.Bar(document.getElementById('columnchart2'));
//		columnchart2.draw(view, null);
//		
//		var barchart2 = new google.charts.Bar(document.getElementById('barchart2'));
//		barchart2.draw(view, null);
		
	} finally {
		NTTCore.loader.hideLoader();
	}

}