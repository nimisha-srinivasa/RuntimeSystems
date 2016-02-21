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
    audio_url="http://"+window.location.host+"/rest/audioClip/"+audioclip_key;
    $('body').append(audio_url);
     $.ajax({
            url: audio_url,
            type: "GET",
            crossDomain: true,
            dataType: "json",
            success: function (data) {
            	
                $('#myTable').append('<tr><td>' + data.title + '</td><td>');
		        $('#myTable').append('<tr><td>' + data.ownerId + '</td><td>');
		        $('#myTable').append('<tr><td>' + data.audio + '</td><td>');
		        $('#myTable').append('<tr><td>' + data.image + '</td><td>');
		        $('#myTable').append('<tr><td>' + data.date + '</td><td>');
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