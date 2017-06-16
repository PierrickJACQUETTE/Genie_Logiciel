var plane;
var mro;
var cate;
var idtask;
var hanger;

var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];	
var int = ['0','1','2','3','4','5','6','7','8','9'];
var planeUrl=null;

var taskDoneArr = new Array();
var taskTimeArr = new Array();

var x = 0;
var y = 0;

var timeDep;
var timeArr;

function details(){
	window.location.replace("Details.html?id=" + plane);
}

function mousePosition(event){
    event = event||window.event;
    x=event.pageX;
    y=event.pageY - 20;
}
document.onmousemove = function(event){
    mousePosition(event);
}

function getServerData(url, success){
    	$.ajax({
        		dataType: "json",
        		url: url
    	}).done(success);
}

function postServerData(url, result){
	$.ajax({
        		dataType: "json",
        		contentType: 'application/json',
        		data : JSON.stringify(result),
        		url: url,
        		type:"POST"
    	});
}

function generateMixed(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;
}

function generateId(n) {
    var res = "";
    for(var i = 0; i < n ; i ++) {
        var id = Math.ceil(Math.random()*9);
        res += int[id];
    }
    return res;
}

function repalceString(src, str){
	var tmp = ""+str;
	var tmpStringPart = src.substring(0,8);
	if(tmp.length == 1){
		var a = "0"+tmp;
		tmp = a;
	}
	return tmpStringPart+tmp;

}

function loadDataPlane(result){
	var id;
	var type;
	var select=document.getElementById("planeNum");
	$.each(result, function (index, item){
		var tab = [];
		var tab2 = [];
		id = "";
		type = "";
		if(index != 0){
			$.each(item,function(valIndex,valValue){
				if(valIndex =='idPlane') {
					if(index == 1){
						plane = valValue;
						getServerData("ws/ressource/plane/"+plane+"/flight", loadFlight);
						getServerData("ws/ressource/plane/"+plane+"/maintenance", loadMaintenance);// /"+plane+"/maintenance
					}
					if($.inArray(valValue, tab) == -1){
						tab.push(valValue)
					}
					//id +=valValue;
				} 
				if (valIndex=='type') {
					if($.inArray(valValue, tab) == -1){
						tab2.push(valValue)
					}
					//type+=valValue;
				}
				
			});
			tab.sort();
			for(var i = 0; i < tab.length; i++){
				id = "";
				id+=tab[i];
				id+=" - "+tab2[i];
				select.options.add(new Option(id,id));
			}
			//select.options.add(new Option(id+" - "+type,id)); 
		}
	});
	
	if(planeUrl!=null){
		$('#planeNum option[value='+planeUrl+']').attr("selected","selected");
	}
}

function loadMro(result){
	var m;
	var tab = [];
	var select=document.getElementById("mroNum");
	$.each(result, function (index, item){
		m = "";
		if(index != 0){
			$.each(item,function(valIndex,valValue){
				if (valIndex=='idMRO') {
					if(index == 1)
						mro = valValue;
					if($.inArray(valValue, tab) == -1){
						tab.push(valValue)
					}
				}
			});
		}
	});
	tab.sort();
	for(var i = 0; i < tab.length; i++){
		m = "";
		m+=tab[i];	
		select.options.add(new Option(m,m));
	}
} 

function loadIdMain(result){
	var maintenance;
	var tab = [];
	var select=document.getElementById("maintenanceNum");
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

function loadTask(result){
	var task;
	var tab = [];
	var select=document.getElementById("taskNum");
	$.each(result, function (index, item){
		task = "";
		if(index != 0){
			$.each(item,function(valIndex,valValue){
				if (valIndex=='idTask') {
					if(index == 1)
						idtask = valValue;
					if($.inArray(valValue, tab) == -1){
						tab.push(valValue)
					}
				}//attribute
			});
	 
		}
	});
	tab.sort();
	for(var i = 0; i < tab.length; i++){
		task="";
		task+=tab[i];
		select.options.add(new Option(task,task));	
	}
}

function loadFlight(result){
	var html = "";
	$("#resultFlight").html("");
	$.each(result, function (index, item){
		html += "<tr>";
		$.each(item,function(valIndex,valValue){
			if(valIndex =='dateDepart') {html += "<td>";html += valValue;html += "</td>"; timeDep = valValue;}
			if(valIndex=='dateArrivee') {html += "<td>";html += valValue;html += "</td>"; timeArr = valValue;}
		});
		html += "</tr>"; 
	});
	$("#resultFlight").append(html);
}

function loadMaintenance(result){
	$("#planningLine").html("");
	//document.getElementById('detailPlanning').style.display="none";
	if(taskDoneArr.length > 0)
		taskDoneArr.splice(0,taskDoneArr.length);	
	if(taskTimeArr.length > 0)
		taskTimeArr.splice(0,taskTimeArr.length);	
	$.each(result, function (index, item){	
		$.each(item,function(valIndex,valValue){
			if(valIndex =='idMaintenance') {
				setTimeout(function(){getServerData("ws/ressource/maintenance/"+valValue+"/task", loadTaskDataForPlanning);},1000); 
			}
			if(valIndex=='taskDone') {taskDoneArr.push(valValue);}
		});
	});
}

function loadTaskDataForPlanning(result){
	var timeArr = new Array();
	taskTimeArr.push(timeArr);
	$.each(result, function (index, item){
		$.each(item,function(valIndex,valValue){
			if(valIndex=='duree') {
				var strs = new Array();
				var tmp = "";
				tmp += valValue;
				strs = tmp.split("h");
				timeArr.push(parseFloat(strs[0]));
			}
		});
	});
}

function generatePlanning(obj){
	var index = -1;
	var taskStartTime = 0;
	var html = "";
	var timeTotal = 0;
	var tmpTimeDep = -1;
	var tmpFlight = -1;
	$("#planningLine").html("");
	if($(obj).val().length == 10){
		for(i=0;i<taskDoneArr.length;i++){
			if(taskDoneArr[i].substring(0,10) == $(obj).val()){
				index = i;
				taskStartTime = parseInt(taskDoneArr[i].substring(11,13));
				break;
			}
		}
		if((timeDep.substring(0,10) == $(obj).val())&&(timeDep.substring(0,10) == timeArr.substring(0,10))){
			tmpTimeDep = parseInt(timeDep.substring(11,13));
			tmpFlight = parseInt(timeArr.substring(11,13)) - tmpTimeDep;
		}
	}
	if(index != -1){
		var tmp = taskTimeArr[index];
		for(i=0;i<tmp.length;i++){
			timeTotal += tmp[i];
		}
		if(tmpTimeDep != -1){
			if(taskStartTime > 0 && tmpTimeDep > 0){
				var per = 0;
				if(taskStartTime > tmpTimeDep){
					per = tmpTimeDep;
				}else{
					per = taskStartTime;
				}
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round((per/24)*100) - 1;
				html += "%'>";
				html += "Free";
				html += "</div>";
			}
			if(taskStartTime > tmpTimeDep){
				html += "<div class='progress-bar progress-bar-info' style='width: ";
				html += Math.round((tmpFlight/24)*100);
				html += "%'";
				html += "onmouseover='doOver(this)'";
				html += "onmouseout='doOut(this)'>";
				html += timeDep.substring(11,19);
				html += " - ";
				html += tmpFlight;
				html += "hours</div>";
				
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round(((taskStartTime-(tmpTimeDep+tmpFlight))/24)*100);
				html += "%'>";
				html += "Free";
				html += "</div>";

				html += "<div class='progress-bar progress-bar-warning' style='width: ";
				html += Math.round((timeTotal/24)*100);
				html += "%'";
				html += "onmouseover='doOver(this)'";
				html += "onmouseout='doOut(this)'>";
				html += taskDoneArr[index].substring(11,19);
				html += " - ";
				html += timeTotal;
				html += "hours</div>";
			}else{
				html += "<div class='progress-bar progress-bar-warning' style='width: ";
				html += Math.round((timeTotal/24)*100);
				html += "%'";
				html += "onmouseover='doOver(this)'";
				html += "onmouseout='doOut(this)'>";
				html += taskDoneArr[index].substring(11,19);
				html += " - ";
				html += timeTotal;
				html += "hours</div>";

				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round(((tmpTimeDep-(taskStartTime+timeTotal))/24)*100);
				html += "%'>";
				html += "Free";
				html += "</div>";

				html += "<div class='progress-bar progress-bar-info' style='width: ";
				html += Math.round((tmpFlight/24)*100);
				html += "%'";
				html += "onmouseover='doOver(this)'";
				html += "onmouseout='doOut(this)'>";
				html += timeDep.substring(11,19);
				html += " - ";
				html += tmpFlight;
				html += "hours</div>";

			}
			if((taskStartTime+timeTotal)<24 && (tmpTimeDep+tmpFlight)<24){
				var per = 0;
				if(taskStartTime+timeTotal > tmpTimeDep+tmpFlight){
					per = taskStartTime+timeTotal;
				}else{
					per = tmpTimeDep+tmpFlight;
				}
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round(100-(per/24)*100);
				html += "%'>";
				html += "Free";
				html += "</div>";
			}
		}else{
			if(taskStartTime > 0){
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round((taskStartTime/24)*100) - 1;
				html += "%'>";
				html += "Free";
				html += "</div>";
			}
			html += "<div class='progress-bar progress-bar-warning' style='width: ";
			html += Math.round((timeTotal/24)*100);
			html += "%'";
			html += "onmouseover='doOver(this)'";
			html += "onmouseout='doOut(this)'>";
			html += taskDoneArr[index].substring(11,19);
			html += " - ";
			html += timeTotal;
			html += "hours</div>";
			if((taskStartTime+timeTotal)<24){
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round(100-((taskStartTime+timeTotal)/24)*100);
				html += "%'>";
				html += "Free";
				html += "</div>";
			}
		}
	}else{	
		if(tmpFlight!=-1 && tmpTimeDep!=-1){
			if(tmpTimeDep > 0){
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round((tmpTimeDep/24)*100) - 1;
				html += "%'>";
				html += "Free";
				html += "</div>";
			}
			html += "<div class='progress-bar progress-bar-info' style='width: ";
			html += Math.round((tmpFlight/24)*100);
			html += "%'";
			html += "onmouseover='doOver(this)'";
			html += "onmouseout='doOut(this)'>";
			html += timeDep.substring(11,19);
			html += " - ";
			html += tmpFlight;
			html += "hours</div>";
			if((tmpTimeDep+tmpFlight)<24){
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += Math.round(100-((tmpTimeDep+tmpFlight)/24)*100);
				html += "%'>";
				html += "Free";
				html += "</div>";
			}
		}else{	
			
			var oldTaskIndex = -1;
			var oldTaskStartTime = 0;
			var oldTimeTotal = 0;
			for(i=0;i<taskDoneArr.length;i++){
				if(taskDoneArr[i].substring(0,10) == repalceString($(obj).val(), parseInt(($(obj).val()).substring(8,10))-1)){
					oldTaskIndex = i;
					oldTaskStartTime = parseInt(taskDoneArr[i].substring(11,13));
					break;
				}
			}
			if(oldTaskIndex != -1){
				var oldTask = taskTimeArr[oldTaskIndex];
				for(i=0;i<oldTask.length;i++){
					oldTimeTotal += oldTask[i];
				}
				if(oldTaskStartTime+oldTimeTotal > 24){
					var per = oldTaskStartTime+oldTimeTotal-24;
					html += "<div class='progress-bar progress-bar-warning' style='width: ";
					html += Math.round((per/24)*100);
					html += "%'";
					html += "onmouseover='doOver(this)'";
					html += "onmouseout='doOut(this)'>";
					html += taskDoneArr[oldTaskIndex];
					html += " - ";
					html += per;
					html += "hours left</div>";

					html += "<div class='progress-bar progress-bar-success' style='width: ";
					html += Math.round(((24-per)/24)*100);;
					html += "%'>";
					html += "Free";
					html += "</div>";
				}else{
					html += "<div class='progress-bar progress-bar-success' style='width: ";
					html += 100;
					html += "%'>";
					html += "Free";
					html += "</div>";
				}	
			}
			else if(timeArr.substring(0,10) == $(obj).val()){
					html += "<div class='progress-bar progress-bar-info' style='width: ";
					html += Math.round((parseInt(timeArr.substring(11,13))/24)*100);
					html += "%'";
					html += "onmouseover='doOver(this)'";
					html += "onmouseout='doOut(this)'>";
					html += timeDep;
					html += " - ";
					html += timeArr.substring(11,13);
					html += "hours left</div>";

					html += "<div class='progress-bar progress-bar-success' style='width: ";
					html += Math.round(((24-parseInt(timeArr.substring(11,13)))/24)*100);;
					html += "%'>";
					html += "Free";
					html += "</div>";
			}else{	
				html += "<div class='progress-bar progress-bar-success' style='width: ";
				html += 100;
				html += "%'>";
				html += "Free";
				html += "</div>";
			}	
		}
	}
	$("#planningLine").append(html);	
}

function readDataPlane(){
	var select = document.getElementById("planeNum");
	plane = select.value;
	getServerData("ws/ressource/plane/"+plane+"/flight", loadFlight);
	getServerData("ws/ressource/plane/"+plane+"/maintenance", loadMaintenance);
}

function readDataMro(){
	var select = document.getElementById("mroNum");
	mro = select.value;
}

function readDataCate(){
	var select = document.getElementById("maintenanceNum");
	cate = select.value;
}

function readDataIdTask(){
	var select = document.getElementById("taskNum");
	idtask = select.value;
}

function validate(){
	
	hanger ="";
	var tmp = "";
	if(($("input[name='hangar']:checked").val())=="Yes"){
		hanger += "true";
	}else{
		hanger += "false";
	}

	var maintenance = $("#maintenanceNum").val();
	plane = $("#planeNum").val();
	var res = plane.split(" ");
	plane = res[0];
	console.log(res[0]);
	mro = $("#mroNum").val();
	task = $("#taskNum").val();
	tmp = $("#date-input").val() + ' ' + $("#time-input").val();
	if(maintenance == "----"){
		maintenance = generateId(5);
	}
	
	//verifier format de temp
	if($("#date-input").val()==timeDep.substring(0,10)){
		if(parseInt(timeDep.substring(11,13)) < parseInt($("#time-input").val().substring(0,2)) && parseInt($("#time-input").val().substring(0,2)) < parseInt(timeArr.substring(11,13))){
			alert("TIME WRONG!");
			return;
		}
	}else if($("#date-input").val() == "" || $("#time-input").val() == ""){
		alert("TIME NULL");
		return;
	}
	alert("The task has been added!");
	var obj = {
			"idMaintenance" : maintenance,
			"idPlane" : plane,
			"idMRO" : mro,
			"idTask" : task,
			"taskDone" : tmp,
			"idMaintenanceElastic" : generateMixed(5),
			"done" : "false",
	}
	console.log(obj);
	postServerData("ws/ressource/maintenance", obj);
}

//ul-li de All
$("ul#selectPlanningMode").on("click","li",function(){
     if($(this).text()=="Detail"){
     	//generatePlanning();
     }
 });

function doOver(divObj) {    
	document.getElementById('detailPlanning').innerHTML = divObj.innerHTML;
	document.getElementById('detailPlanning').style.left = x +"px";
	document.getElementById('detailPlanning').style.top = y +"px";
	document.getElementById('detailPlanning').style.display="";
}

function doOut(divObj) {
	document.getElementById('detailPlanning').style.display="none";
}

var parameters = location.search.substring(1);
var temp = parameters.split("=");
planeUrl = temp[1];

getServerData("ws/ressource/mro",loadMro);
getServerData("ws/ressource/maintenance",loadIdMain);
getServerData("ws/ressource/task",loadTask);
getServerData("ws/ressource/plane",loadDataPlane);
