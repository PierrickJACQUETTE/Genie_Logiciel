function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function callDone(result){
	
	var html = "";
    var plane="";
    var type="";
    var cycle="";
    var nbHeure="";
	$.each(result, function (index, item) {
            html ="";plane="";type="";cycle="";nbHeure="";

			if (index != 0) {     
                	if(item.status == true){
                		html += "<tr class=\"noir\">";
                	}else{
                		html += "<tr class=\"rouge\">";
                	}
            
                $.each(item, function (valIndex, valItem) {
                	
                	if(valItem == item.idPlane)
                	{
                		plane += "<td>";
                        plane += valItem;
                        plane += "</td>";
                	}
                	if(valItem == item.type)
                	{
                		type += "<td>";
                        type += valItem;
                        type += "</td>";
           	    	}	
           	    	if(valIndex == "nbCycle")
                	{
                		cycle += "<td>";
                        cycle += valItem;
                        cycle += "</td>";
                	}
           	    	if(valIndex == "nbHeureVol"){
           	    		nbHeure +="<td>";
           	    		nbHeure += valItem;
           	    		nbHeure += "</td>";
           	    	}
            
                });
                html += plane + type + cycle + nbHeure;
               	html += "<td>";
				html += "<button type = 'button' id='buttonDetail"+item.idPlane+"' class ='btn btn-gris'>";
                html += "Details";
                html += "</button>";

                html += "<button type = 'button' id='buttonHistory"+item.idPlane+"' class ='btn btn-gris'>";
                html += "History";
                html += "</button>";

               html += "<button type = 'button' id='buttonNewTask"+item.idPlane+"' class ='btn btn-gris'>";
                html += "Add Task";
                html += "</button>";
                html += "</td>";
                html += "</tr>";
            }
			if(item.status == true){
				$("#result").append(html);
        	}else{
        		$("#result").prepend(html);
        	}
		$(".noir").hide();	
        $("#buttonDetail"+item.idPlane).click(function(){
			window.location.replace("Details.html?id=" + item.idPlane);
        });
        $("#buttonHistory"+item.idPlane).click(function(){
			window.location.replace("History.html?id=" + item.idPlane);
        });
        $("#buttonNewTask"+item.idPlane).click(function(){
			window.location.replace("add_task.html?id=" + item.idPlane);
        });
	});
}


$(".selection").click(function(){
	if($(".selection").val() == "Urgent"){
		$(".noir").hide();
	}else{
		$(".noir").show();
	}
});

getServerData("ws/ressource/plane",callDone);

