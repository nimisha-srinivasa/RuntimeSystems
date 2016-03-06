package com.ucsb.cs263.tunein.resources;

import java.io.*;
import java.util.*;
import java.net.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;

import com.ucsb.cs263.tunein.model.*;
import com.ucsb.cs263.tunein.service.*;

@Path("/users")
public class UserResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  private UserService userService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void newUser(User user) throws IOException, URISyntaxException{
    userService = new UserService();
    userService.addNewUser(user);
  }

  @GET
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON )
  public User getUserById(@PathParam("userId") String userId)throws IOException{
	  User user = null;
	  userService = new UserService();
	  user = userService.getUserById(userId);
	  return user;
  }

}