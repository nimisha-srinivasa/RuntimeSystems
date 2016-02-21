package cs263.tunein.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.blobstore.*;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
public class AudioClip {
  private String title;
  private Key ownerId;
  private BlobKey audio; //changing to blob in future
  private BlobKey image;
  private Date date;

  public AudioClip(){

  }

  public AudioClip(String title, Key ownerId, BlobKey audio, BlobKey image, Date date){
  	this.title = title;
  	this.ownerId = ownerId;
  	this.audio = audio;
  	this.image = image;
  	this.date = date;
  }

	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Key getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(Key ownerId) {
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