package com.ucsb.cs263.tunein.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class User {

	private String userId;
	private String firstName;
	private String lastName;
	private String emailId;
	
	public User(){
	}

	public User(String userId, String firstName, String lastName, String emailId){
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = firstName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}