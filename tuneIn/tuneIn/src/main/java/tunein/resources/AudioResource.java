package tunein.resources;

import java.io.*;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.logging.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.*;
import javax.xml.bind.JAXBElement;

import tunein.model.*;

@Path("/audio")
public class AudioResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  
  // for the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public AudioClip getAudioClipHTML(String keyname) {
    AudioClip audioClip;
    try{
      Entity result2 = datastore.get(KeyFactory.createKey("AudioClip", keyname));
      audioClip= new AudioClip((String)result2.getProperty("keyname"), (String)result2.getProperty("value"), (Date)result2.getProperty("date"));
      return audioClip;
    }
    catch(EntityNotFoundException e){
      throw new RuntimeException("Get: TaskData with " + keyname +  " not found");
    }
  }
  // for the application
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<AudioClip> getAudioClip(String keyname) {
    List<AudioClip> list= new ArrayList<AudioClip>();
    try{
      Query fetchAll = new Query("AudioClip");
      
      for(Entity td : datastore.prepare(fetchAll).asList(FetchOptions.Builder.withLimit(100))){
        list.add(new AudioClip((String)td.getProperty("keyname"), (String)td.getProperty("value"), (Date)td.getProperty("date")));
      }      
      
    }
    catch(Exception e){
      System.out.println("Exception occured while getting the the task data");
      return null;
    }
    return list;
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newAudioClip(@FormParam("keyname") String keyname,
      @FormParam("value") String value,
      @Context HttpServletResponse servletResponse) throws IOException {
      System.out.println("Posting new audio: " +keyname+" val: "+value+" ts: "+new Date());
      Entity audioClip = new Entity("AudioClip",keyname);
      audioClip.setProperty("value", value); 
      audioClip.setProperty("date",new Date());
      datastore.put(audioClip);
      servletResponse.sendRedirect("/done.html");
  }

  @DELETE
  public void deleteIt() {

  }

} 