package com.niit.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Chat {
	
	@Id
	private int chatid;
	private String username;
	private String friend_id;
	private String chat_history;
	private Date chat_date;
	public int getChatid() {
		return chatid;
	}
	public void setChatid(int chatid) {
		this.chatid = chatid;
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
	public String getChat_history() {
		return chat_history;
	}
	public void setChat_history(String chat_history) {
		this.chat_history = chat_history;
	}
	public Date getChat_date() {
		return chat_date;
	}
	public void setChat_date(Date chat_date) {
		this.chat_date = chat_date;
	}
	@Override
	public String toString() {
		return "Chat [chatid=" + chatid + ", username=" + username + ", friend_id=" + friend_id + ", chat_history="
				+ chat_history + ", chat_date=" + chat_date + "]";
	}
	
	
	

}
