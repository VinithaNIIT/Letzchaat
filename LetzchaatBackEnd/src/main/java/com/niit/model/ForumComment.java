package com.niit.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class ForumComment {
	
	@Id
	private int id;
	private String forumid;
	private String username;
	private String forum_comment;
	private Date commented_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getForumid() {
		return forumid;
	}
	public void setForumid(String forumid) {
		this.forumid = forumid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getForum_comment() {
		return forum_comment;
	}
	public void setForum_comment(String forum_comment) {
		this.forum_comment = forum_comment;
	}
	public Date getCommented_date() {
		return commented_date;
	}
	public void setCommented_date(Date commented_date) {
		this.commented_date = commented_date;
	}
	@Override
	public String toString() {
		return "ForumComment [id=" + id + ", forumid=" + forumid + ", username=" + username + ", forum_comment="
				+ forum_comment + ", commented_date=" + commented_date + "]";
	}
	
	

}
