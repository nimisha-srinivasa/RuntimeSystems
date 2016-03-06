package com.ucsb.cs263.tunein.service;

import com.google.appengine.api.blobstore.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.memcache.*;
import com.google.appengine.api.taskqueue.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ucsb.cs263.tunein.model.AudioClip;
import com.ucsb.cs263.tunein.model.AudioClipInstance;
import com.ucsb.cs263.tunein.model.User;

public class AudioClipService{
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
  	MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();

  	public List<AudioClipInstance> getAllAudioClips(String userId){
  		List<AudioClipInstance> audioClipInstanceList = new ArrayList<AudioClipInstance>();
		Filter userFilter = new FilterPredicate("ownerId", FilterOperator.NOT_EQUAL, userId);
	    Query q = new Query("AudioClip").setFilter(userFilter);
	    PreparedQuery pq = datastore.prepare(q);
	    AudioClipInstance audioClipInstance;
  	    User user;
  	    UserService userService = new UserService();
  	    for (Entity result : pq.asIterable()) {
  	    	user = userService.getUserById((String)result.getProperty("ownerId"));
  	    	audioClipInstance = new AudioClipInstance(KeyFactory.keyToString(result.getKey()),(String)result.getProperty("title"), user, (String)result.getProperty("audioId"), (String)result.getProperty("imageId"), (Date)result.getProperty("date"));
  	    	audioClipInstanceList.add(audioClipInstance); 
  	    }
	   
	    return audioClipInstanceList;
	}

	public AudioClip getAudioClip(String id){
		AudioClip audioClip;
		try{
	      Key audio_key=KeyFactory.stringToKey(id);
	      Entity result = datastore.get(audio_key);
	      audioClip = new AudioClip(id,(String)result.getProperty("title"), (String)result.getProperty("ownerId"), (String)result.getProperty("audioId"), (String)result.getProperty("imageId"), (Date)result.getProperty("date"));
	      return audioClip;
	    }
	    catch(EntityNotFoundException e){
	      throw new RuntimeException("Get: audioClip with " + id +  " not found");
	    }
	}

	public List<AudioClip> getAudioClipsByUser(String userId){
		List<AudioClip> audioClipList;
	    Filter userFilter = new FilterPredicate("ownerId", FilterOperator.EQUAL, userId);
	    Query q = new Query("AudioClip").setFilter(userFilter).addSort("date", SortDirection.ASCENDING);
	    PreparedQuery pq = datastore.prepare(q);
	    
	    // check mem-cache for the results first
  		String CACHE_KEY = "USERS_"+userId;
  		audioClipList =  (ArrayList<AudioClip>) syncCache.get(CACHE_KEY);
  		
  		if(audioClipList==null){
  			audioClipList=new ArrayList<AudioClip>();
  			AudioClip audioClip;
  		    for (Entity result : pq.asIterable()) {
  		      audioClip = new AudioClip(KeyFactory.keyToString(result.getKey()),(String)result.getProperty("title"), (String)result.getProperty("ownerId"), (String)result.getProperty("audioId"), (String)result.getProperty("imageId"), (Date)result.getProperty("date"));
  		      audioClipList.add(audioClip);
  		    }
  		    syncCache.put(CACHE_KEY, audioClipList, Expiration.byDeltaSeconds(120));
  		}

	    
	    return audioClipList;
	}

	public String newAudioClip(String userId, String title, String audio, String image){
		Entity audioClip = new Entity("AudioClip");
	    audioClip.setProperty("title",title);
	    audioClip.setProperty("audioId", audio);
	    audioClip.setProperty("imageId", image);
	    audioClip.setProperty("ownerId", userId);
	    audioClip.setProperty("date",new Date());
	    datastore.put(audioClip);
	    return KeyFactory.keyToString(audioClip.getKey());
	}
	
	public void deleteAudioClip(String audioClipId){
		AudioClip audioClip = getAudioClip(audioClipId);
		datastore.delete(KeyFactory.stringToKey(audioClipId));
		//task queue to delete the associated audio and image blobs
        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(TaskOptions.Builder.withUrl("/rest/users/"+audioClip.getOwnerId()+"/audioClips/audio").method(TaskOptions.Method.DELETE).param("blobkey", audioClip.getAudioId()));
        queue.add(TaskOptions.Builder.withUrl("/rest/users/"+audioClip.getOwnerId()+"/audioClips/image").method(TaskOptions.Method.DELETE).param("blobkey", audioClip.getImageId()));
	}
	
	public void deleteBlob(String blobkey){
		BlobKey blob_key=new BlobKey(blobkey);
	    blobstoreService.delete(blob_key);  
	}
}