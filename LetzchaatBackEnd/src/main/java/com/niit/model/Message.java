package com.niit.model;

public class Message {
	
	private String message;
	private String name;
	private int id;
	private String friend_name;
	
	public Message(){
		
	}
	
	public Message(int id,String message,String name,String friend_name){
		this.id=id;
		this.message=message;
		this.name=name;
		this.friend_name=friend_name;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFriend_name() {
		return friend_name;
	}

	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	
	

}
