package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Blog;
import com.niit.model.BlogComment;

@Repository("BlogDAO")
public class BlogDAOImpl implements BlogDAO {

	private static Logger log=LoggerFactory.getLogger(BlogDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.openSession();
	}
	
	public boolean insertBlog(Blog blog) {
		log.debug("Starting of the BLOGDAO Method INSERTBLOG");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(blog);
			tx.commit();
			session.close();
			log.debug("Ending of the BLOGDAO Method INSERTBLOG");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateBlog(Blog blog) {
		log.debug("Starting of the BLOGDAO Method UPDATEBLOG");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(blog);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the BLOGDAO Method UPDATEBLOG");
		return false;
	}

	public boolean deleteBlog(int blogid) {
		log.debug("Starting of the BLOGDAO Method DELETEBLOG");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Blog id=session.get(Blog.class, blogid);
			session.delete(id);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the BLOGDAO Method DELETEBLOG");
		return false;
	}

	public List<Blog> getBlogList() {
		log.debug("Starting of the BLOGDAO Method GETBLOGLIST");
		Session session = getSession();
		// Transaction tx = session.beginTransaction();
		String s = "from Blog";
		Query query = session.createQuery(s);
		List<Blog> b = query.list();
		// tx.commit();
		session.close();
		log.debug("Ending of the BLOGDAO Method GETBLOGLIST");
		return b;
	}

	public boolean insertBlogComment(BlogComment blogcomment) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateBlogComment(BlogComment blogcomment) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteBlogComment(BlogComment blogcomment) {
		// TODO Auto-generated method stub
		return false;
	}

	public Blog getBlogById(int blogid) {
		log.debug("Starting of the BLOGDAO method GETBLOGBYID");
		Session session=getSession();
		//Transaction tx=session.beginTransaction();
		Blog b=session.get(Blog.class, blogid);
		session.close();
		log.debug("Ending of the BLOGDAO method GETBLOGBYID");
		return b;
	}

	public void updateBlogById(int blogid) {
		log.debug("Starting of the BLOGDAO method UPDATEBLOGBYID");
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		Blog b=session.get(Blog.class, blogid);
		session.update(b);
		session.close();
		tx.commit();
		log.debug("Ending of the BLOGDAO method UPDATEBLOGBYID");
		
	}

}
