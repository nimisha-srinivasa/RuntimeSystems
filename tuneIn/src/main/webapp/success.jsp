<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<html>
<body>
<h1>Success</h1>
<%
String blobKey = (String)request.getParameter("blobkey");
if (blobKey != null) {
String url="http://localhost:8080/rest/audio?blobkey="+blobKey
%>
You uploaded : <br/>
<img width="200" height="150" src=<%= url %> />
<%
}
%>
</body>
</html>