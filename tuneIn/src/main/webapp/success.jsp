<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<!--display all the audioClip content once uploaded by user -->
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type = "text/javascript">
$.urlParam = function(name){
	var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	return results[1] || 0;
}

$(document).ready(function(){
    audioclip_key = $.urlParam('audioclip_key');
    audioClip_url="http://"+window.location.host+"/rest/audioClip/"+audioclip_key;
    basic_url="http://"+window.location.host+"/rest/audioClip";
    $('body').append(audioClip_url);
     $.ajax({
            url: audioClip_url,
            type: "GET",
            crossDomain: true,
            dataType: "json",
            success: function (data) {
            	image_url = basic_url+"/image?blobkey="+data.image;
            	audio_url = basic_url+"/audio?blobkey="+data.audio;
                alert(image_url+"\n"+audio_url);
                $('#myTable').append('<tr><td>' + data.title + '</td></tr>');
		        $('#myTable').append('<tr><td>' + data.ownerId + '</td></tr>');
		        $('#myTable').append('<tr><td><audio controls> <source src=\"'+audio_url+'\" type="audio/mpeg" /></audio></td></tr>');
		        $('#myTable').append('<tr><td> 	<img src=\"'+image_url+'\" /> </td></tr>');
		        $('#myTable').append('<tr><td>' + data.date + '</td></tr>');
	            },
            error: function (xhr, status) {
                alert("error");
            }
        });
});

</script>
</head>

<body>
<h1>Success</h1>
<table id="myTable">
</table>
<!--<%
String audio_key = (String)request.getParameter("audio_key");
if (audio_key != null) {
String url="http://localhost:8080/rest/audio/"+audio_key;
%>
<label>Title:</label>
<p>
	
</p>
<img src=<%= url %> />

<audio controls>
    <source src=<%= url %> type="audio/mpeg" />
</audio>

<%
}
%> -->
</body>
</html>