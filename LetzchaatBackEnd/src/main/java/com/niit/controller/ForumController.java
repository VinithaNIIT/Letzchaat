package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.ForumDAOImpl;
import com.niit.model.Forum;

@RestController
public class ForumController {
	private static Logger log=LoggerFactory.getLogger(BlogController.class);
	@Autowired
	ForumDAOImpl forumDAOImpl;
	@Autowired
	Forum forum;
	
	@RequestMapping(value="/createforum/",method=RequestMethod.POST)
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			forum.setErrorcode("404");
			forum.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<Forum>(HttpStatus.OK);
		}
		
		else{
			forum.setCreated_date(new Date());
			forumDAOImpl.insertForum(forum);
			forum.setErrorcode("200");
			forum.setErrormessage("The user has successfully created the Forum");
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
			
		}
	}
	
	@RequestMapping(value="/updateforum/{forumid}",method=RequestMethod.PUT)
	public ResponseEntity<Forum> updateForum(@PathVariable int forumid){
		
		forumDAOImpl.updateForum(forum);
		forum.setErrorcode("200");
		forum.setErrormessage("The user has successfully updated the forum");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);

	}
	
	@RequestMapping(value="/deleteforum/{forumid}",method=RequestMethod.DELETE)
	public ResponseEntity<Forum> deleteForum(@PathVariable int forumid)
	{
		forumDAOImpl.deleteForum(forumid);
		forum.setErrorcode("200");
		forum.setErrormessage("The user has successfully deleted the forum");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getforum/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<Forum>getForum(@PathVariable int forumid)
	{
		forumDAOImpl.getForumById(forumid);
		forum.setErrorcode("200");
		forum.setErrormessage("Retrieved the blog");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		
	}
	@RequestMapping(value="/forums/",method=RequestMethod.GET)
	public ResponseEntity<List<Forum>>getAllForum()
	{
		 
		List<Forum>forumlist=forumDAOImpl.getForumList();
		forum.setErrorcode("200");
		forum.setErrormessage("Fetched All the blogs");
		return new ResponseEntity<List<Forum>>(forumlist,HttpStatus.OK);
	}
	

	
	

}
