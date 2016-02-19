package cs263.tunein.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
public class AudioClip {
  private Key audioClipId;
  private String title;
  private String ownerId;
  private BlobKey audio; //changing to blob in future
  private BlobKey image;
  private Date date;

  
  public AudioClip(Key audioClipId, String title, Date date){
  	this.audioClipId = audioClipId;
  	this.title = title;
  	this.date = date;
  }

	public Key getAudioClipId() {
		return audioClipId;
	}
	
	public void setAudioClipId(Key id) {
		this.audioClipId = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public BlobKey getAudio() {
		return audio;
	}
	
	public void setAudio(BlobKey audio) {
		this.audio = audio;
	}
	
	public BlobKey getImage() {
		return image;
	}
	
	public void setImage(BlobKey image) {
		this.image = image;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	

  
} 