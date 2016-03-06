hello.init({
    google: '36542558745-1l96ap825s047nutvan7j1jb6l29eiva.apps.googleusercontent.com'
}, 
{
    redirect_uri: 'login.html',
    scope: 'email'
});
hello.on('auth.login', function(auth) {
    // Call user information, for the given network
    hello(auth.network).api('/me').then(function(r) {
        user=r;
        getUserInfo().then(createAudioRecordModal).then(getMyPreviousWork).then(getOthersWork);
    });
});

//hello.logout() doesnt work for google plus!!
function logout(){
	hello.logout();
	window.location = "register.html";
}

function getUserInfo(){
    userJson = {}
    userJson.userId = user.id;
    userJson.firstName = user.first_name;
    userJson.lastName = user.last_name;
    userJson.displayName = user.displayName;
    userJson.emailId = user.email;
    return $.ajax({
        url: "rest/users", 
        method: "POST",
        contentType: "application/json",
        data : JSON.stringify(userJson),
        success: function(data){
            $("#userName").html(user.first_name+" "+user.last_name);            
        }
    });
}

function createAudioRecordModal(){
    blob_upload_url="/rest/users/"+user.id+"/audioClips/blob";
    return $.ajax({
        url: blob_upload_url, 
        method: "GET",
        contentType: "application/json",
        success: function(data){
            $("#audioRecordModalDetails").append('<form action="'+data+'"  method="POST" enctype="multipart/form-data"><input type="hidden" id="audioSubmit_userId" name="userId" value="'+user.id+'"/><div><label> Title:</label><input type="text" name="title"></div><div><label> Image:</label><input type="file" name="myImage"></div><div><label> Audio:</label><input type="file" name="myAudio"></div><button type="submit" class="btn btn-primary"><i class="fa fa-save"></i> Save Audio</button><button type="button" class="btn btn-primary" data-dismiss="modal"><i class="fa fa-times"></i> Cancel</button></form>');
        }
    });
}

function getMyPreviousWork(){
    basic_url="/rest/users/"+user.id+"/audioClips";
        $.ajax({
                url: basic_url,
                type: "GET",
                crossDomain: true,
                dataType: "json",
                success: function (data) {
                    $.each(data, function(i, item) {
                        image_url = basic_url+"/image?blobkey="+item.image;
                        audio_url = basic_url+"/audio?blobkey="+item.audio;
                        $("#myPrevWork").append("<li> <div class=\"timeline-image\"><img class=\"timeline_audio_image img-circle img-responsive\" src=\""+image_url+"\" alt=\"Audio Clip\"></div><div class=\"timeline-panel\"><div class=\"timeline-heading\"><h4>"+item.date+"</h4><h4 class=\"subheading\">"+item.title+"</h4></div><div class=\"timeline-body\"><p class=\"text-muted\"><audio controls> <source src=\""+audio_url+"\" type=\"audio/mpeg\" /></audio></p></div></div></li>");
                    });
                },
                error: function (xhr, status) {
                    alert("error");
                }
        });
}

function getOthersWork(){
    othersWorkModalId=0;
    basic_url="/rest/users/"+user.id+"/audioClips";
    $.ajax({
            url: basic_url+"/others",
            type: "GET",
            crossDomain: true,
            dataType: "json",
            success: function (data) {
                $.each(data, function(i, item) {
                    currModalId=othersWorkModalId++;
                    image_url = basic_url+"/image?blobkey="+item.image;
                    audio_url = basic_url+"/audio?blobkey="+item.audio;
                    $("#othersWork").append('<div class="col-md-4 col-sm-6 portfolio-item"><a href="othersWorkModal'+currModalId+'" class="portfolio-link" data-toggle="modal" data-target="#othersWorkModal'+currModalId+'"><div class="portfolio-hover"><div class="portfolio-hover-content"><i class="fa fa-youtube-play fa-3x"></i></div></div><img src="'+image_url+'" class="audio_image" alt=""></a><div class="portfolio-caption"><h4>'+item.title+'</h4><p class="text-muted">'+item.owner.displayName+'</p></div>');
                    $("#audioRecordModal").after('<div class="audio-modal modal fade" id="othersWorkModal'+currModalId+'" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"><a class="audio-modal close-modal" data-dismiss="modal"><i class="fa fa-3x fa-times"></i></a><h2>'+item.title+'</h2><p class="item-intro text-muted">By '+item.owner.displayName+'</p><img class="portfolio-img img-responsive img-centered" src="'+image_url+'" alt=""><br/><audio controls><source src="'+audio_url+'" type="audio/mpeg" /></audio><br/><br/><ul class="list-inline"><li>Date: '+item.date+'</li></ul><br/></div><div class="clearfix"></div></div></div></div></div>');
                });
            },
            error: function (xhr, status) {
                alert("error");
            }
    });
}

