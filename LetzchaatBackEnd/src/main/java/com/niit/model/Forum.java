package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Forum extends Error {
	
	@Id
	private int forumid;
	private String username;
	private String forum_topic;
	private Date created_date;
	public int getForumid() {
		return forumid;
	}
	public void setForumid(int forumid) {
		this.forumid = forumid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getForum_topic() {
		return forum_topic;
	}
	public void setForum_topic(String forum_topic) {
		this.forum_topic = forum_topic;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	@Override
	public String toString() {
		return "Forum [forumid=" + forumid + ", username=" + username + ", forum_topic=" + forum_topic
				+ ", created_date=" + created_date + "]";
	}
	

}
