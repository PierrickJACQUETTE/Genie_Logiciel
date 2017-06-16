function postServerData(url, result){
	console.log("post");
	$.ajax({
        		dataType: "json",
        		contentType: 'application/json',
        		data : JSON.stringify(result),
        		url: url,
        		type:"POST"
    	});
}

function fichier(){
	var file = $('.input-file')[0].files[0];
	var reader = new FileReader();
		reader.onload = function(){
			var text = reader.result;
			//var node = document.getElementById('output');
			//node.innerText = text;
			console.log(reader.result.substring(0, 200));
			postServerData("ws/ressource/mpd", text);
		};
		
	reader.readAsText($('.input-file')[0].files[0]);
	console.log("test");
		var name = $('.input-file').val()
		var filename = {"name" : "test"}
		console.log(name);
		console.log("test 1");
		//postServerData("ws/ressource/mpd", text);
		
	//on change input et non de la fct 
}