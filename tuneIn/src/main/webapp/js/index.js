hello.init({
    google: '36542558745-placeneqlea01hiat8rednod6cqkiq36.apps.googleusercontent.com'
}, 
{
    redirect_uri: 'login.html',
    scope: 'email'
});
hello.on('auth.login', function(auth) {
    // Call user information, for the given network
    hello(auth.network).api('/me').then(function(r) {
        user=r;
        userJson = {}
        userJson.userId = user.id;
        userJson.firstName = user.first_name;
        userJson.lastName = user.last_name;
        userJson.emailId = user.email;
        $.ajax({
        	url: "rest/user", 
        	method: "POST",
        	contentType: "application/json",
			data : JSON.stringify(userJson),
        	success: function(data){
        		$("#userName").html(user.first_name+" "+user.last_name);
    		}
    	});
        $("input[id=audioSubmit_userId]").val(user.id);
        basic_url="/rest/audioClip";
        $.ajax({
                url: basic_url+"/byUser?userId="+user.id,
                type: "GET",
                crossDomain: true,
                dataType: "json",
                success: function (data) {
                    $.each(data, function(i, item) {
                        image_url = basic_url+"/image?blobkey="+item.image;
                        audio_url = basic_url+"/audio?blobkey="+item.audio;
                        $("#myPrevWork").append("<li> <div class=\"timeline-image\"><img class=\"img-circle img-responsive\" src=\""+image_url+"\" alt=\"\"></div><div class=\"timeline-panel\"><div class=\"timeline-heading\"><h4>"+item.date+"</h4><h4 class=\"subheading\">"+item.title+"</h4></div><div class=\"timeline-body\"><p class=\"text-muted\"><audio controls> <source src=\""+audio_url+"\" type=\"audio/mpeg\" /></audio></p></div></div></li>");
                    });
                },
                error: function (xhr, status) {
                    alert("error");
                }
        });
    });
});

//hello.logout() doesnt work for google plus!!
function logout(){
	alert("logging out");
	hello.logout();
	window.location = "login.html";
}

