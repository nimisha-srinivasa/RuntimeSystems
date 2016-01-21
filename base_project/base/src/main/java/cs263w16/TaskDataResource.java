package cs263w16;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.util.logging.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.*;

import javax.xml.bind.JAXBElement;

public class TaskDataResource {
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  String keyname;
  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
  

  public TaskDataResource(UriInfo uriInfo, Request request, String kname) {
    this.uriInfo = uriInfo;
    this.request = request;
    this.keyname = kname;
  }
  // for the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public TaskData getTaskDataHTML() {
    
    TaskData taskData;
    try{
      String result = (String)syncCache.get(KeyFactory.createKey("TaskData", keyname));
      if(result != null){
        taskData = new TaskData(keyname, result, new Date());  
        return taskData;
      }
      Entity result2 = datastore.get(KeyFactory.createKey("TaskData", keyname));
      taskData = new TaskData((String)result2.getProperty("keyname"), (String)result2.getProperty("value"), (Date)result2.getProperty("date"));
      return taskData;
    }catch(EntityNotFoundException e){
      throw new RuntimeException("Get: TaskData with " + keyname +  " not found");
      
    }
  }
  // for the application
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public TaskData getTaskData() {
    
    TaskData taskData;
    try{
      String result = (String)syncCache.get(KeyFactory.createKey("TaskData", keyname));
      if(result != null){
        taskData = new TaskData(keyname, result, new Date());  
        return taskData;
      }
      Entity result2 = datastore.get(KeyFactory.createKey("TaskData", keyname));
      taskData = new TaskData((String)result2.getProperty("keyname"), (String)result2.getProperty("value"), (Date)result2.getProperty("date"));
      return taskData;
    }catch(EntityNotFoundException e){
      throw new RuntimeException("Get: TaskData with " + keyname +  " not found");
      
    }
  }

  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public Response putTaskData(String val) {
    Response res = null;
    
    try{
      Key task_key = KeyFactory.createKey("TaskData", keyname);
      Entity taskData = datastore.get(task_key);
      //updating the entity
      taskData.setProperty("value",val);
      datastore.put(taskData);
      //signal that we updated the entity
      res = Response.noContent().build();

    }catch(EntityNotFoundException e){
      Entity taskData = new Entity("TaskData",keyname);
      taskData.setProperty("value", val); 
      taskData.setProperty("date",new Date());
      datastore.put(taskData);
      syncCache.put(keyname,val);
      //signal that we created the entity in the datastore 
      res = Response.created(uriInfo.getAbsolutePath()).build();

    }
 
    return res;
  }

  @DELETE
  public void deleteIt() {

    //delete an entity from the datastore
    //just print a message upon exception (don't throw)
    try{
      
      Key task_key = KeyFactory.createKey("TaskData", keyname);
      datastore.delete(task_key);

    }catch(Exception e){
      System.out.println("Exception occured while deleting the the task data");
      
    }
  }

} 