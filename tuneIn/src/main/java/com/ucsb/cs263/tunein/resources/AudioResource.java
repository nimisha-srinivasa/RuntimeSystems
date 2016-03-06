package com.ucsb.cs263.tunein.resources;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import com.ucsb.cs263.tunein.model.AudioClip;
import com.ucsb.cs263.tunein.model.AudioClipInstance;
import com.ucsb.cs263.tunein.service.AudioClipService;

@Path("/users/{userId}/audioClips")
public class AudioResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  private AudioClipService audioClipService = new AudioClipService();;
  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
  

  @GET
  @Path("/others")
  @Produces(MediaType.APPLICATION_JSON )
  public List<AudioClipInstance> getAllAudioClips(@PathParam("userId") String userId) throws IOException{
    return audioClipService.getAllAudioClips(userId);
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON )
  public AudioClip getAudioClip(@PathParam("id") String id) throws IOException{
      return audioClipService.getAudioClip(id);
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON )
  public List<AudioClip> getAudioClipsByUser(@PathParam("userId") String userId) throws IOException{
      return audioClipService.getAudioClipsByUser(userId);
    
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
  @Path("/blob")
  @Produces(MediaType.TEXT_PLAIN )
  public String getBlobUploadURL(
    @PathParam("userId") String userId,
    @Context HttpServletResponse response) throws IOException{
      String callbackURL="/rest/users/"+userId+"/audioClips";
      return blobstoreService.createUploadUrl(callbackURL);
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
      String userId = request.getParameter("userId");
      String audioclip_key = audioClipService.newAudioClip(userId, request.getParameter("title"), 
    		  audio_blobKey.getKeyString(), image_blobKey.getKeyString());
      return Response.seeOther(new URI("/success.html?audioclip_key="+audioclip_key+"&userId="+userId)).build();
  }

  @DELETE
  @Path("/{id}")
  public void deleteAudioClip(@PathParam("id") String id) {
	  audioClipService.deleteAudioClip(id);
  }
  
  @DELETE
  @Path("/audio")
  public void deleteAudioBlob(
    @QueryParam("blobkey") String blobkey) throws IOException{
	  audioClipService.deleteBlob(blobkey); 
  }
  
  @DELETE
  @Path("/image")
  public void deleteImageBlob(
    @QueryParam("blobkey") String blobkey) throws IOException{
	  if(blobkey!=null){
		  audioClipService.deleteBlob(blobkey);
	  }
  }

  @PUT 
  public void updateAudioClip() {
	  /* TODO */
  }

} 