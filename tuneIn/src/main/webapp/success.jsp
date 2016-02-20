<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<!--display all the audioClip content once uploaded by user -->
<html>
<body>
<h1>Success</h1>
<%
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
%>
</body>
</html>