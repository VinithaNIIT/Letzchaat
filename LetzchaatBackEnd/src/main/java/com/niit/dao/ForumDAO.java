package com.niit.dao;

import java.util.List;


import com.niit.model.Forum;

public interface ForumDAO {
	
	public boolean insertForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(int forumid);
	public List<Forum> getForumList();
	public Forum getForumById(int forumid);

}
