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


import com.niit.dao.BlogDAOImpl;
import com.niit.model.Blog;

@RestController
public class BlogController {
	private static Logger log=LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	BlogDAOImpl blogDAOImpl;
	@Autowired
	Blog blog;
	
	@RequestMapping(value="/createblog/",method=RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog,HttpSession session,HttpServletRequest request)
	{
		session=request.getSession(false);
		String username=(String)session.getAttribute("username");
		if(username == null)
		{
			log.debug("Useris not Loggedin");
			blog.setErrorcode("404");
			blog.setErrormessage("User is not Logged In");
			
			return new ResponseEntity<Blog>(HttpStatus.OK);
		}
		
		else{
			blog.setDate_of_creation(new Date());
			blogDAOImpl.insertBlog(blog);
			blog.setErrorcode("200");
			blog.setErrormessage("The user has successfully created the blog");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
			
		}
		
		
	}
	@RequestMapping(value="/updateblog/{blogid}",method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable int blogid){
		
		blogDAOImpl.updateBlogById(blogid);
		blog.setErrorcode("200");
		blog.setErrormessage("The user has successfully updated the blog");
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
		
		
		
	}
	
	@RequestMapping(value="/deleteblog/{blogid}",method=RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable int blogid)
	{
		blogDAOImpl.deleteBlog(blogid);
		blog.setErrorcode("200");
		blog.setErrormessage("The user has successfully deleted the blog");
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getblog/{blogid}",method=RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable int blogid)
	{
		blogDAOImpl.getBlogById(blogid);
		blog.setErrorcode("200");
		blog.setErrormessage("Retrieved the blog");
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
	}
	@RequestMapping(value="/blogs/",method=RequestMethod.GET)
	public ResponseEntity<List<Blog>>getAllBlogs()
	{
		 
		List<Blog>bloglist= blogDAOImpl.getBlogList();
		blog.setErrorcode("200");
		blog.setErrormessage("Fetched All the blogs");
		return new ResponseEntity<List<Blog>>(bloglist,HttpStatus.OK);
	}
	

}
