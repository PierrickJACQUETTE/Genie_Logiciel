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

    $.each(result, function (index, item) 
    {
            var html =""; 
            if (index != 0) 
            {   
                html+="<tr class=\"";
                html+=item.idPlane;
                html+="\">";
                
                $.each(item, function (valIndex, valItem) 
                {
                    if (valItem == item.idMaintenance)
                    {
                        html += "<td>";
                        html += valItem;
                        html += "</td>";
                    }
                
                    if (valItem == item.taskDone)
                    {
                        html += "<td>";
                        html += stringArray[index];
                        html += "</td>";
                    }

                    if (valItem == item.idMRO)
                    {
                        html += "<td>";
                        html += valItem;
                        html += "</td>";
                    }
                });               
                html+="</tr>";  
            }
            $("#result").append(html);
    });
    
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
			$(".filtre-plane").options.add(new Option(type,type));
			//obj.options.add(new Option(type,type)); 
		}
	});
}
function fctPlane(element){
	var element = document.getElementsByTagName('tr');
	var test = true;
	for(var i=1; i<element.length; i++){
		if($(".filtre-plane").val() != " "){
			if($(element).attr('class') != $(".filtre-plane").val())
				$(element[i]).hide();
			else
				$(element[i]).show();
		}else{
			$(element[i]).show();
		}
	}
}

getServerData("ws/ressource/plane",loadDataPlane);
getServerData("ws/ressource/maintenance",callDone);