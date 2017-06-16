
function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function callDone(result){
	var templateExample = _.template($('#templateExample').html());

	var html = templateExample({
		"attribute":JSON.stringify(result)
	});

	$("#result").append(html);
}

$(function(){
	$("#button1").click(function(){
		getServerData("ws/ressource/maintenance",callDone);
	});
});

$(function(){
	$("#button2").click(function(){
		getServerData("ws/ressource/maintenance/42",callDone);
	});
});

$(function(){
	$("#button3").click(function(){
		getServerData("ws/ressource/flight",callDone);
	});
});

$(function(){
	$("#button4").click(function(){
		getServerData("ws/ressource/flight/26/maintenance",callDone);
	});
});

$(function(){
	$("#button5").click(function(){
		getServerData("ws/ressource/search",callDone);
	});
});

$(function(){
	$("#button6").click(function(){
		getServerData("ws/ressource/user/35",callDone);
	});
});
