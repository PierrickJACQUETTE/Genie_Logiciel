function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function callDone(result){
	
	var html;
	$.each(result, function (index, item) {
            var html =""; 
			if (index != 0) {     
                	if(item.status == true){
                		html += "<tr class=\"noir\">";
                	}else{
                		html += "<tr class=\"rouge\">";
                	}
                	
                $.each(item, function (valIndex, valItem) {
                    html += "<td>";
                    html += valItem;
                    html += "</td>";
                });
                
               html += "</tr>";
            }
			console.log(item.status);
			if(item.status == true){
				$("#result").append(html);
        	}else{
        		$("#result").prepend(html);
        	}
		$(".noir").hide();	
	});
}

function callDone(result){
	
	var html;
	$.each(result, function (index, item) {
            var html =""; 
			if (index != 0) {     
                	if(item.status == true){
                		html += "<tr class=\"noir\">";
                	}else{
                		html += "<tr class=\"rouge\">";
                	}
                	
                $.each(item, function (valIndex, valItem) {
                    html += "<td>";
                    html += valItem;
                    html += "</td>";
                });
                
               html += "</tr>";
            }
			console.log(item.status);
			if(item.status == true){
				$("#result").append(html);
        	}else{
        		$("#result").prepend(html);
        	}
		$(".noir").hide();	
	});
}


$(".selection").click(function(){
	if($(".selection").val() == "Urgent"){
		$(".noir").hide();
	}else{
		$(".noir").show();
	}
	console.log($(".selection").val());
});
getServerData("ws/ressource/plane",callDone);
getServerData("ws/ressource/maintenance",callDone2);