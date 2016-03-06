package com.ucsb.cs263.tunein.service;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.blobstore.*;

import java.util.*;

import com.ucsb.cs263.tunein.model.*;

public class AudioClipService{
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

  	public List<AudioClipInstance> getAllAudioClips(String userId){
  		List<AudioClipInstance> audioClipInstanceList=new ArrayList<AudioClipInstance>();
	    Filter userFilter = new FilterPredicate("ownerId", FilterOperator.NOT_EQUAL, userId);
	    Query q = new Query("AudioClip").setFilter(userFilter);
	    PreparedQuery pq = datastore.prepare(q);

	    AudioClipInstance audioClipInstance;
	    User user;
	    UserService userService = new UserService();
	    for (Entity result : pq.asIterable()) {
	    	user = userService.getUserById((String)result.getProperty("ownerId"));
	    	audioClipInstance = new AudioClipInstance(KeyFactory.keyToString(result.getKey()),(String)result.getProperty("title"), user, (String)result.getProperty("audio"), (String)result.getProperty("image"), (Date)result.getProperty("date"));
	    	audioClipInstanceList.add(audioClipInstance);
	    }
	    return audioClipInstanceList;
	}

	public AudioClip getAudioClip(String id){
		AudioClip audioClip;
		try{
	      Key audio_key=KeyFactory.stringToKey(id);
	      Entity result = datastore.get(audio_key);
	      audioClip = new AudioClip(id,(String)result.getProperty("title"), (String)result.getProperty("ownerId"), (String)result.getProperty("audio"), (String)result.getProperty("image"), (Date)result.getProperty("date"));
	      return audioClip;
	    }
	    catch(EntityNotFoundException e){
	      throw new RuntimeException("Get: audioClip with " + id +  " not found");
	    }
	}

	public List<AudioClip> getAudioClipsByUser(String userId){
		List<AudioClip> audioClipList=new ArrayList<AudioClip>();
	    Filter userFilter = new FilterPredicate("ownerId", FilterOperator.EQUAL, userId);
	    Query q = new Query("AudioClip").setFilter(userFilter).addSort("date", SortDirection.ASCENDING);
	    PreparedQuery pq = datastore.prepare(q);

	    AudioClip audioClip;
	    for (Entity result : pq.asIterable()) {
	      audioClip = new AudioClip(KeyFactory.keyToString(result.getKey()),(String)result.getProperty("title"), (String)result.getProperty("ownerId"), (String)result.getProperty("audio"), (String)result.getProperty("image"), (Date)result.getProperty("date"));
	      audioClipList.add(audioClip);
	    }
	    return audioClipList;
	}

	public String newAudioClip(String userId, String title, String audio, String image){
		Entity audioClip = new Entity("AudioClip");
	    audioClip.setProperty("title",title);
	    audioClip.setProperty("audio", audio);
	    audioClip.setProperty("image", image);
	    audioClip.setProperty("ownerId", userId);
	    audioClip.setProperty("date",new Date());
	    datastore.put(audioClip);
	    return KeyFactory.keyToString(audioClip.getKey());
	}


	
}