package tunein.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class User {
	private String userId;
	private String userName;
	private String emailId;
	private String password;
	private List<AudioClip> audioClipList;
	
	public User(){
		audioClipList = new ArrayList<AudioClip>();
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public List<AudioClip> getAudioClipList() {
		return audioClipList;
	}


	public void setAudioClipList(List<AudioClip> audioClipList) {
		this.audioClipList = audioClipList;
	}
	
	public void addAudioClipList(AudioClip audioClip){
		this.audioClipList.add(audioClip);
	}
	
}
