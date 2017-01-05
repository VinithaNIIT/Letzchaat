package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;
import com.niit.model.BlogComment;

public interface BlogDAO {
	
	public boolean insertBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean deleteBlog(int blogid);
	public List<Blog> getBlogList();
	public Blog getBlogById(int blogid);
	public void updateBlogById(int blogid);
	public boolean insertBlogComment(BlogComment blogcomment);
	public boolean updateBlogComment(BlogComment blogcomment);
	public boolean deleteBlogComment(BlogComment blogcomment);
	
	
	

}
