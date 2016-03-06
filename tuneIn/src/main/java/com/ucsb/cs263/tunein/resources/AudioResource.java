package com.ucsb.cs263.tunein.resources;

import java.io.*;
import java.util.*;
import java.net.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;
import java.util.*;


import com.google.appengine.api.memcache.*;
import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;

import com.ucsb.cs263.tunein.model.*;
import com.ucsb.cs263.tunein.service.*;

@Path("/users/{userId}/audioClips")
public class AudioResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  private AudioClipService audioClipService;
  private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

  @GET
  @Path("/others")
  @Produces(MediaType.APPLICATION_JSON )
  public List<AudioClipInstance> getAllAudioClips(@PathParam("userId") String userId) throws IOException{
    audioClipService = new AudioClipService();
    return audioClipService.getAllAudioClips(userId);
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON )
  
  public AudioClip getAudioClip(@PathParam("id") String id) throws IOException{
      audioClipService = new AudioClipService();
      return audioClipService.getAudioClip(id);
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON )
  public List<AudioClip> getAudioClipsByUser(@PathParam("userId") String userId) throws IOException{
    audioClipService = new AudioClipService();
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
      Key user_key;
      String userId = request.getParameter("userId");
      audioClipService = new AudioClipService();
      String audioclip_key = audioClipService.newAudioClip(userId, request.getParameter("title"), 
        audio_blobKey.getKeyString(), image_blobKey.getKeyString());
      return Response.seeOther(new URI("/success.html?audioclip_key="+audioclip_key+"&userId="+userId)).build();
  }

  @DELETE
  public void deleteAudioClip() {

  }

  @PUT 
  public void updateAudioClip() {

  }

} 