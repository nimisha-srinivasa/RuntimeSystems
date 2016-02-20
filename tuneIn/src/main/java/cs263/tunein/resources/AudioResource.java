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


import cs263.tunein.model.*;

@Path("/audio")
public class AudioResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
  
  @GET
  @Produces(MediaType.TEXT_HTML)
  public void showImage(@QueryParam("blobkey") String blobkey,
    @Context HttpServletRequest request,
    @Context HttpServletResponse response) throws IOException{
      System.out.println("##### it came to the GET function!!! "+blobkey+" !@#####");
      BlobKey blob_key=new BlobKey(blobkey);
      blobstoreService.serve(blob_key,response);
      return;

  }

  @POST
  //@Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response newAudioClip(@Context HttpServletRequest request,
    @Context HttpServletResponse response) throws IOException, URISyntaxException{
      System.out.println("##### it came to the post function!!! #####");
      Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
      List<BlobKey> blobKeys = blobs.get("myAudio");
      System.out.println("##### blobkey="+blobKeys.get(0).getKeyString()+" #####");
      /*response.sendRedirect("/rest/audio?blobkey=" + blobKeys.get(0).getKeyString());
      return;*/
      return Response.seeOther(new URI("/success.jsp?blobkey="+blobKeys.get(0).getKeyString())).build();
  }

  @DELETE
  public void deleteIt() {

  }

} 