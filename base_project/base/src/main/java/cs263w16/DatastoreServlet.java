package cs263w16;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.*;

@SuppressWarnings("serial")
public class DatastoreServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      resp.setContentType("text/html");
      resp.getWriter().println("<html><body>");
      

      String keyname = req.getParameter("keyname");
      String value = req.getParameter("value");

      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      // Using the synchronous cache.
	  MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
	  syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));

      //display every element of kind TaskData for /datastore
      if(req.getParameterMap().isEmpty()){
      	//querying from datastore
      	resp.getWriter().println("<h3>Datastore results:</h3>");
      	List<String> listOfKeys=new ArrayList<String>();
      	Query q = new Query("TaskData");
      	PreparedQuery pq = datastore.prepare(q);  
      	for (Entity result : pq.asIterable()) {   
      		String datastore_key = result.getKey().getName();
		    String taskData_value = (String) result.getProperty("value");   
		    Date taskData_date = (Date) result.getProperty("date"); 
		    resp.getWriter().println("<p>keyname = "+datastore_key+"  value = "+taskData_value+" date = "+taskData_date.toString()+"</p>");
		    listOfKeys.add(datastore_key);
		}
		//check which of the keys exist in memcache
		String memcache_value;
		resp.getWriter().println("<h3>Memcache results:</h3>");
		for(String datastore_key: listOfKeys){
			memcache_value = (String) syncCache.get(datastore_key);
			if(memcache_value!=null){
				//String decoded = new String(memcache_value, "UTF-8");
				resp.getWriter().println("<p>keyname = "+datastore_key+" value = "+memcache_value+"</p>");
			}
		}
      }

      //display element of kind TaskData with key=keyname
      else if(keyname!=null && value==null){

      	//first check in the cache
      	String memcache_value = (String) syncCache.get(keyname); // Read from cache.
      	// Get value from datastore
	    Key task_key = KeyFactory.createKey("TaskData", keyname);
	    try{
	      	Entity tne = datastore.get(task_key);
	      	if(memcache_value==null){
	      		resp.getWriter().println("<h2>Datastore</h2>");
	      	}else{
	      		resp.getWriter().println("<h2>Both</h2>");
	      	}
		    
	    }catch(EntityNotFoundException e){
	      	resp.getWriter().println("<h2>Neither</h2>");
	    }
  	}

      //store element of kind TaskData with key=keyname and value=value
      else if(keyname!=null && value!=null){
      	Entity tne = new Entity("TaskData",keyname);
      	tne.setProperty("value", value); 
      	tne.setProperty("date",new Date());
		datastore.put(tne);
		syncCache.put(keyname, value); // Populate cache.
      	resp.getWriter().println("<h2>Stored "+keyname+" and "+value+" in Datastore and Memcache</h2>");
      }

      else{

      	resp.getWriter().println("<h2>You entered wrong query parameters</h2>");
      }


      /*
      Entity tne = new Entity("TaskData", "Person");
	  alice.setProperty("gender", "female");
	  alice.setProperty("age", 20);
	  */

      resp.getWriter().println("</body></html>");
  }
}