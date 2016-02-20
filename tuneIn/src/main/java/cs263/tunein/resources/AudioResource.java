package cs263.tunein.resources;

import java.io.*;
import java.util.*;
import java.net.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.logging.*;
import javax.xml.bind.JAXBElement;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.memcache.*;
import com.google.appengine.api.users.*;

import cs263.tunein.model.*;

@Path("/audioClip")
public class AudioResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
  UserService userService = UserServiceFactory.getUserService();
  
  @GET
  @Path("/{id}/audio")
  public Response sendAudio(
    @PathParam("id") String id,
    @QueryParam("blobkey") String blobkey,
    @Context HttpServletResponse response) throws IOException{
      BlobKey blob_key=new BlobKey(blobkey);
      blobstoreService.serve(blob_key,response);
      return Response.ok().build();
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response newAudioClip(@Context HttpServletRequest request,
    @Context HttpServletResponse response) throws IOException, URISyntaxException{
      Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
      BlobKey audio_blobKey = blobs.get("myAudio");
      BlobKey image_blobKey = blobs.get("myImage");
      Entity audioClip = new Entity("audioClip");
      audioClip.setProperty("audio",audio_blobKey);
      audioClip.setProperty("image",image_blobKey);
      audioClip.setProperty("ownerId",userService.getCurrentUser().getUserId());
      audioClip.setProperty("date",new Date());
      datastore.put(audioClip);
      return Response.seeOther(new URI("/success.jsp?audio_key="+audioClip.getKey())).build();
  }

  @DELETE
  public void deleteIt() {

  }

} 