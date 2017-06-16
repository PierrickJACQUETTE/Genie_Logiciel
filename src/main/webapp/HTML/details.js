var plane=null;

function getServerData(url, success)
{
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function callDone(result)
{
    var html;
    var stringArray = [];
    var maintenanceId;

    $.each(result, function (index, item) 
    { 
    	$.each(item, function (valIndex, valItem) 
    	{
    		if (valItem == item.taskDone) 
    	    {
    			stringArray.push(valItem);
    	    }
    	                
    	});
    	stringArray.sort()
    });
    var array = [];
    var x;
    $.each(result, function (index, item) 
    {
            var html ="";
            maintenanceId = "";
            if (index != 0) 
            {   
            	console.log(item);
                html+="<tr class='";
                html+=item.idPlane;
                html+="'>";
                 
                $.each(item, function (valIndex, valItem) 
                {
                    if (valItem == item.idMaintenance)
                    {
                        html += "<td>";
                        html += valItem;
                        html += "</td>";
                        maintenanceId += valItem;
                    }
                
                    if (valItem == item.taskDone)
                    {
                        html += "<td>";
                        x = $.inArray(valItem, stringArray);
                        html += "</td>";
                    }

                    if (valItem == item.idMRO)
                    {
                        html += "<td>";
                        html += valItem;
                        html += "</td>";
                    }
                });  
                html += "<td>";
                html += "<button type = 'button' class ='btn btn-view' value='";
                html += maintenanceId;
                html += "' onclick='viewFlight(this.value)'>";
                html += "View";
                html += "</button>";
                html += "</td>";            
                html += "</tr>";  
            }
            while(array[x] != null){
            	x++;
            }	
            array[x] = html;

            //$("#result").append(html);
    });
    for(var i = 0; i<array.length; i++){
    	$("#result").prepend(array[i]);
    }

}

function refreshSelect(){
	var element = document.getElementsByTagName('tr');
	for(var i=1; i<element.length; i++){
		if($(".filtre-plane").val() != " "){
			if($(element[i]).attr('class') != $(".filtre-plane").val())
				$(element[i]).hide();
			else
				$(element[i]).show();
		}else 
			$(element[i]).show();
	}
}

function loadDataPlane(result){
	var type;
	var obj=document.getElementById("plane");
	$.each(result, function (index, item){
		type = "";
		if(index != 0){
			$.each(item,function(valIndex,valValue){
				if (valIndex=='idPlane') {type+=valValue;}
			});
			obj.options.add(new Option(type,type)); 
		}
	});
	if(plane!=null){
		$('#plane option[value='+plane+']').attr("selected","selected");
		refreshSelect();
	}
}

function readPlane(){
    var select = document.getElementById("plane");
    getServerData("ws/ressource/plane/"+select.value+"/flight", loadFlight);
}

function loadFlight(result){
        var html = "";
        $("#resultFlight").html("");
        $.each(result, function (index, item){
            html += "<tr>";
            $.each(item,function(valIndex,valValue){
                if(valIndex =='dateDepart') {html += "<td>";html += valValue;html += "</td>";}
                if(valIndex=='dateArrivee') {html += "<td>";html += valValue;html += "</td>";}
            });
            html += "</tr>"; 
        });
        $("#resultFlight").append(html);
}

$(".filtre-plane").click(function(){
	refreshSelect();
});

function generatePlanning(result){
    var idArr = new Array();
    var timeArr = new Array();
    var len;
    var totalTime = 0;
    var html = "";
    $.each(result, function (index, item){
        $.each(item,function(valIndex,valValue){
            if(valIndex =='idTask') {len = idArr.push(valValue);}
            if(valIndex=='duree') {
                var strs = new Array();
                var tmp = "";
                tmp += valValue;
                strs = tmp.split("h");
                timeArr.push(parseFloat(strs[0]));
                totalTime += parseFloat(strs[0]);
            }
        });
    });
    for (i=0;i<len ;i++){
        var per = Math.round((timeArr[i]/totalTime)*100);
        if(((i+1)%3) == 1){
            html += "<div class='progress-bar progress-bar-warning' style='width: ";
        }else if(((i+1)%3) == 2){
            html += "<div class='progress-bar progress-bar-success' style='width: ";
        }else{
            html += "<div class='progress-bar progress-bar-info' style='width: ";
        }  
        html += per;
        html += "%'>";
        html +="Id Task:"
        html += idArr[i];
        html += " - ";
        html += timeArr[i];
        html += "hours</div>";
    }
    $("#planningLine").append(html); 
}

function viewFlight(value){
    $("#planningLine").html("");
    getServerData("ws/ressource/maintenance/"+value+"/task", generatePlanning);
}

var parameters = location.search.substring(1);
var temp = parameters.split("=");
plane = temp[1];
getServerData("ws/ressource/maintenance",callDone);
getServerData("ws/ressource/plane",loadDataPlane);
