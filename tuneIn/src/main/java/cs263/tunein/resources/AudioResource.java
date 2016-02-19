package cs263.tunein.resources;

import java.io.*;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.logging.*;
import javax.xml.bind.JAXBElement;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.memcache.*;


import cs263.tunein.model.*;

@Path("/audio")
public class AudioResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
  
  // for the browser
  /*@GET
  @Produces(MediaType.TEXT_XML)
  public AudioClip getAudioClipHTML(String id) {
    AudioClip audioClip;
    try{
      Entity result2 = datastore.get(KeyFactory.createKey("AudioClip", id));
      audioClip= new AudioClip((Key)result2.getProperty("audioClipId"), (String)result2.getProperty("title"), (Date)result2.getProperty("date"));
      return audioClip;
    }
    catch(EntityNotFoundException e){
      throw new RuntimeException("Get: TaskData with " + id +  " not found");
    }
  }*/
  @GET
  @Produces(MediaType.TEXT_HTML)
  public void showImage(String blobkey,
    @Context HttpServletResponse response) throws IOException{
      BlobKey blob_Key = new BlobKey(blobkey);
      blobstoreService.serve(blob_Key, response);
    
  }
  // for the application
  /*@GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<AudioClip> getAudioClip(String id) {
    List<AudioClip> list= new ArrayList<AudioClip>();
    try{
      Query fetchAll = new Query("AudioClip");
      
      for(Entity td : datastore.prepare(fetchAll).asList(FetchOptions.Builder.withLimit(100))){
        list.add(new AudioClip((Key)td.getProperty("audioClipId"), (String)td.getProperty("title"), (Date)td.getProperty("date")));
      }      
      
    }
    catch(Exception e){
      System.out.println("Exception occured while getting the the task data");
      return null;
    }
    return list;
  }*/

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public void newAudioClip( @Context HttpServletRequest request,
    @Context HttpServletResponse response) throws IOException{

      Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
      List<BlobKey> blobKeys = blobs.get("audio");

      if (blobKeys == null || blobKeys.isEmpty()) {
          response.sendRedirect("/");
      } else {
          response.sendRedirect("/audio?blobkey=" + blobKeys.get(0).getKeyString());
      }




      
      /*public void newAudioClip(
      /*@FormParam("keyname") String keyname,
      @FormParam("value") String value,
      @Context HttpServletResponse servletResponse) throws IOException {
      System.out.println("Posting new audio: " +keyname+" val: "+value+" ts: "+new Date());
      Entity audioClip = new Entity("AudioClip",keyname);
      audioClip.setProperty("value", value); 
      audioClip.setProperty("date",new Date());
      datastore.put(audioClip);
      servletResponse.sendRedirect("/done.html");*/
  }

  @DELETE
  public void deleteIt() {

  }

} 