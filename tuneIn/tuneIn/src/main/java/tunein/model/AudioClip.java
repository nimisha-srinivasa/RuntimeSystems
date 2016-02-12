package tunein.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

import com.google.appengine.api.datastore.*;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
public class AudioClip {
  private String audoClipId;
  private String title;
  private String ownerId;
  private Blob audio; //changing to blob in future
  private Blob image;
  private Date date;

  
  public AudioClip(){

  }

	public String getAudoClipId() {
		return audoClipId;
	}
	
	public void setAudoClipId(String id) {
		this.audoClipId = id;
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
	
	public Blob getAudio() {
		return audio;
	}
	
	public void setAudio(Blob audio) {
		this.audio = audio;
	}
	
	public Blob getImage() {
		return image;
	}
	
	public void setImage(Blob image) {
		this.image = image;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	

  
} 
