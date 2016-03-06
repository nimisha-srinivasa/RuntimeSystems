package com.ucsb.cs263.tunein.service;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import com.ucsb.cs263.tunein.model.*;


public class UserService{
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void addNewUser(User user){
		Entity userEntity = new Entity("User", user.getUserId());
		userEntity.setProperty("firstName", user.getFirstName());
		userEntity.setProperty("lastName", user.getLastName());
		userEntity.setProperty("displayName", user.getDisplayName());
		userEntity.setProperty("emailId", user.getEmailId());
		datastore.put(userEntity);
	}
	
	public User getUserById(String userId){
		Key userKey = KeyFactory.createKey("User", userId);
		Filter userFilter = new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.EQUAL, userKey);
	    Query q = new Query("User").setFilter(userFilter);
	    PreparedQuery pq = datastore.prepare(q);
	    
	    User user= null;
	    for (Entity result : pq.asIterable()) {
	    	user = new User((String)result.getProperty("userId"), (String)result.getProperty("firstName"), (String)result.getProperty("lastName"), (String)result.getProperty("displayName"), (String)result.getProperty("emailId"));
	    	break;
	    }
	    
	    return user;
	}
}