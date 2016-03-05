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
		userEntity.setProperty("email", user.getEmailId());
		datastore.put(userEntity);
	}
}