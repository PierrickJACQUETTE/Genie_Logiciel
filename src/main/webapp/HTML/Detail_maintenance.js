function getServerData(url, success){
  $.ajax({
    dataType: "json",
    url: url
  }).done(success);
}

function postServerData(url, result){
    $.ajax({
    dataType: "json",
    contentType:'application/json',
    data : JSON.stringify(result),
    url : url,
    type:"POST"
    });
}

function changeDone(result){
    if ($("#cbox"+result.idTask).is(":checked"))
    {
        result.done = true;         
    }
    else{
        result.done = false;
    }
    postServerData("ws/ressource/maintenance/", result);
}

var tmp;

function callDone(result){
  var html;
  $.each(result, function (index, item) {
      html="";
      html += "<tr id=\"noir\">";
      html += "<td>";
      html += item.idTask;
      html += "</td>";
      html += "<td>";
       $.each(tmp, function(indexMaintenance, itemMaintenance){
        if(itemMaintenance.idTask == item.idTask){
            html += "<input type= 'checkbox'  id='cbox"+item.idTask+"' class ='btn btn-gris'";
            if(itemMaintenance.done == true){
                html+=" checked";
            }
            html+="/>";        
        }
        });
      html += "</td>";
      html += "</tr>";
      $("#maintenanceDetails").append(html);
      $(document).ready(function() {
        $("#cbox"+item.idTask).click(function(){
            //recuperer en post pas en get
            getServerData("ws/ressource/maintenance/"+temp[1]+"/task/"+item.idTask,changeDone);
        });
    });
  });
}

function maintenance(result){
    tmp = result;
}

var parameters = location.search.substring(1);
var temp = parameters.split("=");
//recuperer en post pas en get
getServerData("ws/ressource/maintenance/" + temp[1], maintenance);
//recuperer en post pas en get
getServerData("ws/ressource/maintenance/"+temp[1]+"/task",callDone);
