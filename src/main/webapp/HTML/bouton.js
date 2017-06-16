function getSeverData(url, success){
			$.ajax({
				url: url, 
				dataType: 'json', 
			}).done(success); 
		}



function callDone(result){

	$('.addTask').click(function(){
		var url = 'http://localhost:2626/add_task.html'; 
		var id = $(this).attr('id'); 
		var idPlane = result[0].idPlane;
			
		window.location.replace(url);					
			$.ajax({
				url: url, 
				dataType: 'text', 
				data: 'idPlane='+idPlane, 
			}); 
							
				
	});
	
	$('.details').click(function(){
		var url = 'http://localhost:2626/Details.html'; 
		var id = $(this).attr('id'); 
		var idPlane = result[0].idPlane;
		
		window.location.replace(url);					
		 $.ajax({
			url: url, 
			dataType: 'text', 
			data: 'idPlane='+idPlane, 
		 });
	});
	
	$('.history').click(function(){
		var url = 'http://localhost:2626/History.html'; 
		var id = $(this).attr('id'); 
		var idPlane = result[0].idPlane;
		
		window.location.replace(url);					
		 $.ajax({
			url: url, 
			dataType: 'text', 
			data: 'idPlane='+idPlane, 
		});
	});
}

getServerData("ws/ressource/flight",callDone);