package tunein.model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and JSON
public class AudioClip {
  private String keyname;
  private String value; //changing to blob in future
  private Date date;

  
  public AudioClip(){

  }

  public AudioClip(String keyname, String value, Date date){
  	this.keyname=keyname;
  	this.value=value;
  	this.date=date;
  }

  public String getKeyname() {
    return keyname;
  }
  public void setKeyname(String keyname) {
    this.keyname = keyname;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
} 
