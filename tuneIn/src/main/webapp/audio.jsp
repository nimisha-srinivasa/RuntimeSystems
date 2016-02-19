<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<html>
  <body>
    <p>Upload an audio</p>
    <form action="<%= blobstoreService.createUploadUrl("/audio") %>" method="post" enctype="multipart/form-data">
      <label>Key:</label> <input type="text" name="keyname">
      <label> Titke:</label>
      <input type="text" name="title">
      <label> Audio:</label><input type="file" name="audio">
      <input type="submit">
    </form>
  </body>
</html>