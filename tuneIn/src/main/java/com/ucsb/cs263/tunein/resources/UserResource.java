package com.ucsb.cs263.tunein.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import java.io.IOException;
import java.net.URISyntaxException;

import com.ucsb.cs263.tunein.model.User;
import com.ucsb.cs263.tunein.service.UserService;

@Path("/users")
public class UserResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  private UserService userService = new UserService();

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void newUser(User user) throws IOException, URISyntaxException{
    userService.addNewUser(user);
  }

  @GET
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON )
  public User getUserById(@PathParam("userId") String userId)throws IOException{
	  User user = null;
	  user = userService.getUserById(userId);
	  return user;
  }

}