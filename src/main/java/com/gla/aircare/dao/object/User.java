package com.gla.aircare.dao.object;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {

	@JsonProperty
	private String name;
	@JsonProperty
	private String firstname;
	@JsonProperty
	private String email;
	@JsonProperty
	private String password;
	@JsonProperty
	private boolean status; // 0 MCC 1 MRO
	@JsonProperty
	private String idUser;

	public User() {
	}

	public User(String idUser, String name, String firstname, String email, String password, boolean status) {
		this.name = name;
		this.firstname = firstname;
		this.email = email;
		this.password = password;
		this.status = status;
		this.idUser = idUser;
	}

	public String getIdUser() {
		return idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", firstname=" + firstname + ", email=" + email + ", password=" + password
				+ ", status=" + status + "]";
	}

	public String create() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public User createObject(String sourceAsString) {
		if (sourceAsString != null) {
			Gson gson = new GsonBuilder().create();
			return gson.fromJson(sourceAsString, User.class);
		}
		return null;
	}
	
	public int testConnexion(String passeWord){
		if (passeWord.equals(this.password) && this.status==true) {
			return 1;
		}else if(passeWord.equals(this.password) && this.status==false){
			return 0;
		}else{
			return -1;
		}
	}

}
