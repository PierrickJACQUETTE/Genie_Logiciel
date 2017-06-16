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
            //console.log(item);
            var html =""; 
            var main = "";
            var task = "";
            var mro = "";
            var date ="";
            if (index != 0) 
            {   
                html+="<tr class=\"";
                html+=item.idPlane;
                html+="\">";
                main = "";
                task = "";
                mro = "";
                date ="";
                $.each(item, function (valIndex, valItem) 
                {
                    if (valItem == item.idMaintenance)
                    {
                        main += "<td>";
                        main += valItem;
                        main += "</td>";
                    }
                    
                    if (valItem == item.idTask)
                    {
                        task += "<td>";
                        task += valItem;
                        task += "</td>";
                    }
                    
                    if (valItem == item.taskDone)
                    {
                        date += "<td>";
                        date += valItem;
                        x = $.inArray(valItem, stringArray);
                        date += "</td>";
                    }

                    if (valItem == item.idMRO)
                    {
                        mro += "<td>";
                        mro += valItem;
                        mro += "</td>";
                    }
                });        
                html += main + task + mro + date;
                html+="</tr>";
                while(array[x] != null){
                	x++;
                }	
                array[x] = html;
            }
            //$("#result").prepend(html);
    });
    for(var i = 0; i<array.length; i++){
    	$("#result").prepend(array[i]);
    }
    
}

$(".filtre-plane").click(function(){filtre();});
$(".filtre-mro").click(function(){filtre();});
$(".filtre-task").click(function(){filtre();});
function fctMro(element){
	if($(".filtre-mro").val() != " "){
		if($(element).find('td').eq(2).html() != $(".filtre-mro").val())
			return false;
		else
			return true;
	}
	return true;
}
function fctPlane(element){
	if($(".filtre-plane").val() != " "){
		if($(element).attr('class') != $(".filtre-plane").val()){
			console.log($(element).attr('class'));
			return false;
		}else
			return true;
	}
	return true;	
}
function fctTask(element){
	if($(".filtre-task").val() != " "){
		if($(element).find('td').eq(1).html() != $(".filtre-task").val())
			return false;
		else
			return true;
	}
	return true;	
}
function testFiltre(){
	return (($(".filtre-plane").val() == " ")&&($(".filtre-mro").val() == " ")&&($(".filtre-task").val() == " "));
}
function filtre(){
	console.log("filtre");
	var element = document.getElementsByTagName('tr');
	var test = true;
	for(var i=1; i<element.length; i++){
		test = true;
		if(fctMro($(element[i])) == false){	
			test = false;
			$(element[i]).hide();
		}
		if(fctPlane($(element[i])) == false){
			test = false;
			$(element[i]).hide();
		}
		if(fctTask($(element[i])) == false){
			test = false;
			$(element[i]).hide();
		}
		if(test || testFiltre()){
			$(element[i]).show();
		}
	}
}

function loadMro(result){
	var mro;
	var obj=document.getElementById("mro");
	$.each(result, function (index, item){
		mro = "";
		if(index != 0){
			$.each(item,function(valIndex,valValue){
				if (valIndex =='idMRO') {mro+=valValue;}
			});
			obj.options.add(new Option(mro,mro)); 
		}
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
			obj.options.add(new Option(type,type)); 
		}
	});
	if(plane!=null){
		$('#plane option[value='+plane+']').attr("selected","selected");
		filtre();
	}
}

function loadTask(result){
	var task;
	var obj=document.getElementById("task");
	$.each(result, function (index, item){
		task = "";
		if(index != 0){
			$.each(item,function(valIndex,valValue){
				if (valIndex=='idTask') {task+=valValue;}//attribute
			});
			obj.options.add(new Option(task,task)); 
		}
	});
}

function loadIdMain(result){
	var maintenance;
	var tab = [];
	var select=document.getElementById("maintenance");
	maintenance = "----";
	select.options.add(new Option(maintenance,maintenance));
	$.each(result, function (index, item){
		if(index != 0){
			$.each(item,function(valIndex,valValue){
				if (valIndex=='idMaintenance') {
					if(index == 1)
						cate = valValue;
					if($.inArray(valValue, tab) == -1){
						tab.push(valValue)
					}
				}
			});
		}
	});
	tab.sort();
	for(var i = 0; i < tab.length; i++){
		maintenance = "";
		maintenance+=tab[i];
		select.options.add(new Option(maintenance,maintenance));	
	}
} 
var parameters = location.search.substring(1);
var temp = parameters.split("=");
plane = temp[1];

getServerData("ws/ressource/mro",loadMro);
getServerData("ws/ressource/maintenance",loadIdMain);
getServerData("ws/ressource/task",loadTask);
getServerData("ws/ressource/maintenance",callDone);
getServerData("ws/ressource/plane",loadDataPlane);
