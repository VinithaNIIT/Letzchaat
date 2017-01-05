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
import com.niit.model.Forum;
@Repository("forumDAO")
public class ForumDAOImpl implements ForumDAO {
	
	private static Logger log=LoggerFactory.getLogger(BlogDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public boolean insertForum(Forum forum) {
		log.debug("Starting of the FORUMDAO Method INSERTFORUM");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(forum);
			tx.commit();
			session.close();
			log.debug("Ending of the FORUMDAO Method INSERTFORUM");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		log.debug("Starting of the FORUMDAO Method UPDATEFORUM");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(forum);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the FORUMDAO Method UPDATEFORUM");
		return false;
	}

	public boolean deleteForum(int forumid) {
		log.debug("Starting of the FORUMDAO Method DELETEFORUM");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			Forum id=session.get(Forum.class, forumid);
			session.delete(id);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the FORUMDAO Method DELETEFORUM");
		return false;
	}

	public List<Forum> getForumList() {
		log.debug("Starting of the FORUMDAO Method GETFORUMLIST");
		Session session = getSession();
		// Transaction tx = session.beginTransaction();
		String s = "from Forum";
		Query query = session.createQuery(s);
		@SuppressWarnings("unchecked")
		List<Forum> b = query.list();
		// tx.commit();
		session.close();
		log.debug("Ending of the FORUMDAO Method GETFORUMLIST");
		return b;
	}

	public Forum getForumById(int forumid) {
		log.debug("Starting of the FORUMDAO method GETFORUMBYID");
		Session session=getSession();
		//Transaction tx=session.beginTransaction();
		Forum b=session.get(Forum.class, forumid);
		session.close();
		log.debug("Ending of the FORUMDAO method GETFORUMBYID");
		return b;
	}

}
