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

      //display every element of kind TaskData
      if(keyname==null && value==null){
      	Query q = new Query("TaskData");
      	PreparedQuery pq = datastore.prepare(q);  
      	for (Entity result : pq.asIterable()) {   
		       String taskData_value = (String) result.getProperty("value");   
		       Date taskData_date = (Date) result.getProperty("date"); 
		       resp.getWriter().println("<p>value = "+taskData_value+" <br/> date = "+taskData_date.toString()+"</p>");
		}
      }

      //display element of kind TaskData with key=keyname
      else if(keyname!=null && value==null){
      	Key task_key = KeyFactory.createKey("TaskData", keyname);
      	try{
      		Entity tne = datastore.get(task_key);
      		String tne_value = (String) tne.getProperty("value");
    		Date tne_date = (Date) tne.getProperty("date");
    		resp.getWriter().println("<h2>value = "+tne_value+" <br/> date = "+tne_date.toString()+"</h2>");
      	}catch(EntityNotFoundException e){
      		resp.getWriter().println("<h2>Entity does not exist</h2>");
      	}
      }

      //store element of kind TaskData with key=keyname and value=value
      else if(keyname!=null && value!=null){
      	Entity tne = new Entity("TaskData",keyname);
      	tne.setProperty("value", value); 
      	tne.setProperty("date",new Date());
      	
		datastore.put(tne);
      	resp.getWriter().println("<h2>Stored "+keyname+" and "+value+" in Datastore</h2>");
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