function getServerData(url, success){
  $.ajax({
    dataType: "json",
    url: url
  }).done(success);
}

function callDone(result){
  var html;
  var arrayMaintenanceDifferente = [];
  $.each(result, function (index, item) {
    if(item.done == false && $.inArray(item.idMaintenance, arrayMaintenanceDifferente)==-1){
      arrayMaintenanceDifferente.push(item.idMaintenance);
      html="";
      html += "<tr id=\"noir\">";
      html += "<td>";
      html += item.idMaintenance;
      html += "</td>";
      html += "<td>";
      html +=  item.idPlane;
      html += "</td>";
      html += "<td data-field-type=\"date\">";
      html +=  item.taskDone;
      html += "</td>";
      html += "<td>";
      html += "<button type = 'button' id='button"+item.idMaintenance+"' class ='btn btn-gris'>";
      html += "Details";
      html += "</button>";
      html += "</td>";
      html += "</tr>";
      $("#maintenanceMRO").append(html);

      $(document).ready(function() {
        $("#button"+item.idMaintenance).click(function(){

            //pas en post pas en get
           window.location.replace("DetailMaintenance.html?id=" + item.idMaintenance);
        });
    });
    }
    
  });
}

//remplacer 1000 par l'id du mro connecte
getServerData("ws/ressource/mro/1000/maintenance",callDone);
