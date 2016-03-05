package com.ucsb.cs263.tunein.resources;

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

import com.ucsb.cs263.tunein.model.*;

@Path("/user")
public class UserResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void newUser(User user) throws IOException, URISyntaxException{
      	Entity userEntity = new Entity("User", user.getUserId());
		userEntity.setProperty("firstName", user.getFirstName());
		userEntity.setProperty("lastName", user.getLastName());
		userEntity.setProperty("email", user.getEmailId());
		datastore.put(userEntity);
  }

}