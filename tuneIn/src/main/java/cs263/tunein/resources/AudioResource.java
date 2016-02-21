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

import org.glassfish.jersey.media.multipart.FormDataParam;

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
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON )
  public AudioClip getAudioClip(
    @PathParam("id") String id) throws IOException{
      //return an audio object
    System.out.println("came to get function with "+id);
    try{
      Key audio_key=KeyFactory.stringToKey(id);
      Entity result = datastore.get(audio_key);
      System.out.println("got it from datastore");
      AudioClip audioClip = new AudioClip((String)result.getProperty("title"), (Key)result.getProperty("ownerId"), (BlobKey)result.getProperty("audio"), (BlobKey)result.getProperty("image"), (Date)result.getProperty("date"));
      System.out.println(audioClip.toString());
      System.out.println(audioClip.getTitle());
      System.out.println(audioClip.getAudio());
      System.out.println(audioClip.getImage());
      System.out.println(audioClip.getDate().toString());

      return audioClip;
    }
    catch(EntityNotFoundException e){
      throw new RuntimeException("Get: audioClip with " + id +  " not found");
    }
  }

  @GET
  @Path("/{id}/audio")
  public Response getAudio(
    @PathParam("id") String id,
    @QueryParam("blobkey") String blobkey,
    @Context HttpServletResponse response) throws IOException{
      BlobKey blob_key=new BlobKey(blobkey);
      blobstoreService.serve(blob_key,response);
      return Response.ok().build();
  }

  @GET
  @Path("/{id}/image")
  public Response getImage(
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
      Entity audioClip = new Entity("AudioClip");
      audioClip.setProperty("title",request.getParameter("title"));
      audioClip.setProperty("audio",audio_blobKey);
      audioClip.setProperty("image",image_blobKey);
      if(userService.getCurrentUser()!=null){
        audioClip.setProperty("ownerId",userService.getCurrentUser().getUserId());
      }else{
        audioClip.setProperty("ownerId",null);
      }
      audioClip.setProperty("date",new Date());
      datastore.put(audioClip);
      //return Response.seeOther(new URI("/rest/audioClip/"+KeyFactory.keyToString(audioClip.getKey()))).build();
      return Response.seeOther(new URI("/success.jsp?audioclip_key="+KeyFactory.keyToString(audioClip.getKey()))).build();
  }

  @DELETE
  public void deleteIt() {

  }

} 