package tunein.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
public class AudioClip {
  private String keyname;
  private String audio; //changing to blob in future
  private String image; //changing to blob in future
  private Date date;

  
  public AudioClip(){

  }

  public AudioClip(String keyname, String audio, Date date){
  	this.keyname=keyname;
  	this.audio=audio;
  	this.date=date;
  }

	public String getKeyname() {
		return keyname;
	}
	
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	
	public String getAudio() {
		return audio;
	}
	
	public void setAudio(String audio) {
		this.audio = audio;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

  
} 
