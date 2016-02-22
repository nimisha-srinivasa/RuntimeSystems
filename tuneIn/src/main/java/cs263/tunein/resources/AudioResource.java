package cs263.tunein.resources;

import java.io.*;
import java.util.*;
import java.net.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;
import java.util.*;
import javax.xml.bind.JAXBElement;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
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
    try{
      Key audio_key=KeyFactory.stringToKey(id);
      Entity result = datastore.get(audio_key);
      AudioClip audioClip = new AudioClip(id,(String)result.getProperty("title"), (String)result.getProperty("ownerId"), (String)result.getProperty("audio"), (String)result.getProperty("image"), (Date)result.getProperty("date"));
      return audioClip;
    }
    catch(EntityNotFoundException e){
      throw new RuntimeException("Get: audioClip with " + id +  " not found");
    }
  }

  @GET
  @Path("/byUser")
  @Produces(MediaType.APPLICATION_JSON )
  public List<AudioClip> getAudioClipsByUser() throws IOException{
    List<AudioClip> audioClipList=new ArrayList<AudioClip>();
    String id = null;
    if(userService.getCurrentUser()!=null){
        id = userService.getCurrentUser().getUserId();
        System.out.println("Came to get byUser "+id);
        /*Key user_id=KeyFactory.stringToKey(id);
        System.out.println("user key is  "+user_id);*/
        Filter userFilter = new FilterPredicate("ownerId", FilterOperator.EQUAL, id);
        Query q = new Query("AudioClip").setFilter(userFilter);
        //fetch all for now!
        //Query q = new Query("AudioClip");
        PreparedQuery pq = datastore.prepare(q);

        AudioClip audioClip;
        for (Entity result : pq.asIterable()) {
          audioClip = new AudioClip(id,(String)result.getProperty("title"), (String)result.getProperty("ownerId"), (String)result.getProperty("audio"), (String)result.getProperty("image"), (Date)result.getProperty("date"));
          audioClipList.add(audioClip);
        }
    }

    return audioClipList;
    
  }

  @GET
  @Path("/audio")
  public Response getAudio(
    @QueryParam("blobkey") String blobkey,
    @Context HttpServletResponse response) throws IOException{
      BlobKey blob_key=new BlobKey(blobkey);
      blobstoreService.serve(blob_key,response);
      return Response.ok().build();
  }

  @GET
  @Path("/image")
  public Response getImage(
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
      Key user_key;
      Entity audioClip = new Entity("AudioClip");
      audioClip.setProperty("title",request.getParameter("title"));
      audioClip.setProperty("audio", audio_blobKey.getKeyString());
      audioClip.setProperty("image", image_blobKey.getKeyString());
      if(userService.getCurrentUser()!=null){
        //user_key = KeyFactory.stringToKey(userService.getCurrentUser().getUserId());
        audioClip.setProperty("ownerId", userService.getCurrentUser().getUserId());
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