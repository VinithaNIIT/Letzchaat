package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Friend {
	
	@Id
	private int id;
	private String username;
	private String friend_id;
	private String friend_request;
	private char is_online;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}
	public String getFriend_request() {
		return friend_request;
	}
	public void setFriend_request(String friend_request) {
		this.friend_request = friend_request;
	}
	public char getIs_online() {
		return is_online;
	}
	public void setIs_online(char is_online) {
		this.is_online = is_online;
	}
	@Override
	public String toString() {
		return "Friend [id=" + id + ", username=" + username + ", friend_id=" + friend_id + ", friend_request="
				+ friend_request + ", is_online=" + is_online + "]";
	}
	
	

}
