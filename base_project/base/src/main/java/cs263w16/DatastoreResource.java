package cs263w16;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.logging.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.*;

//Map this class to /ds route
@Path("/ds")
public class DatastoreResource {
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
  

  // Return the list of entities to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<TaskData> getEntitiesBrowser() {
    //datastore dump -- only do this if there are a small # of entities
    List<TaskData> list = new ArrayList<TaskData>();
    try{
      Query fetchAll = new Query("TaskData");
      
      for(Entity td : datastore.prepare(fetchAll).asList(FetchOptions.Builder.withLimit(100))){
        list.add(new TaskData((String)td.getProperty("keyname"), (String)td.getProperty("value"), (Date)td.getProperty("date")));
      }      
      
    }
    catch(Exception e){
      System.out.println("Exception occured while getting the the task data");
      return null;
    }
    return list;
  }

  // Return the list of entities to applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<TaskData> getEntities() {
    //datastore dump -- only do this if there are a small # of entities
    List<TaskData> list = new ArrayList<TaskData>();
    try{
      Query fetchAll = new Query("TaskData");
      
      for(Entity td : datastore.prepare(fetchAll).asList(FetchOptions.Builder.withLimit(100))){
        list.add(new TaskData((String)td.getProperty("keyname"), (String)td.getProperty("value"), (Date)td.getProperty("date")));
      }      
      
    }
    catch(Exception e){
      System.out.println("Exception occured while getting the the task data");
      return null;
    }
    return list;
  }

  //Add a new entity to the datastore
  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newTaskData(@FormParam("keyname") String keyname,
      @FormParam("value") String value,
      @Context HttpServletResponse servletResponse) throws IOException {
    Date date = new Date();
    System.out.println("Posting new TaskData: " +keyname+" val: "+value+" ts: "+date);
    //add your code here
    Entity taskData = new Entity("TaskData",keyname);
    taskData.setProperty("value", value); 
    taskData.setProperty("date",new Date());
    datastore.put(taskData);
    servletResponse.sendRedirect("/done.html");
  }

  //The @PathParam annotation says that keyname can be inserted as parameter after this class's route /ds
  @Path("{keyname}")
  public TaskDataResource getEntity(@PathParam("keyname") String keyname) {
    System.out.println("GETting TaskData for " +keyname);
    return new TaskDataResource(uriInfo, request, keyname);
  }
}