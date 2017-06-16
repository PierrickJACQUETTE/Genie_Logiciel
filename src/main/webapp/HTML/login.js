function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}


function callDown(){
		var id_client = $("#id_user").val();
		var passe = $("#password_user").val();
		getServerData("ws/ressource/user/"+id_client+"/"+passe,compare);
}

function compare(result){
	if(result==1){
		alert("mro success!!!! Please wait a moment...");
		//window.setTimeout("window.location='home.html'",2000);
		window.location.href="home_mro.html";
	}else if(result == 0){
		alert("mcc success!!!! Please wait a moment...");
		//window.setTimeout("window.location='home.html'",2000);
		window.location.href="home.html";
	}else{
		alert("user dose not exist or the password is wrong!");
	}
}