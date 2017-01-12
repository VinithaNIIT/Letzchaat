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

import com.niit.model.Friend;
import com.niit.model.User;


@Repository("friendDAO")
public class FriendDAOImpl implements FriendDAO {
	
	private static Logger log=LoggerFactory.getLogger(FriendDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.openSession();
	}

	public List<Friend> friendList(String username) {
		log.debug("Starting of the FRIENDDAO Method FriendList");
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		//String hql="from Friend where friend_name='"+username+"' and friend_request='P'";
		String hql=" from User where username in(select username from Friend where friend_request='P')";
		Query query=session.createQuery(hql);
		List<Friend>j=query.list();
		log.debug("List of friends in FRiendDAO"+j);
		tx.commit();
		session.close();
		log.debug("Username is"+username);
		log.debug("Starting of the FRIENDDAO Method FriendList");
		
		return j;
	}

	public boolean updateFriend(Friend friend) {
		log.debug("Starting of the FRIENDDAO Method UPDATEFRIEND");
		try {
			Session session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(friend);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		log.debug("Ending of the FRIENDDAO Method UPDATEFRIEND");
		return false;
		
	}

	public Friend getFriendRequest(String username, String friend_name) {
		log.debug("Starting of FriendRequest method in FriendDAOImpl");
		System.out.println("Username:"+username +"Friendname"+friend_name);
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		String hql="from Friend where username='"+friend_name+"' and friend_name='"+username+"'";
		Query query=session.createQuery(hql);
		Friend friend=(Friend)query.uniqueResult();
		tx.commit();
		session.close();
		return friend;
	}

	public User getFriendDetails(String friendname) {
log.debug("Staring of the FRIENDDAO Method getFRIENDNAME");
		
		Session session = getSession();
		//Transaction tx=session.beginTransaction();
		User u=session.get(User.class,friendname);
		log.debug("Details of friends"+u);
		session.close();
		log.debug("Friend details "+u);
		log.debug("Ending of the FRIENDDAO Method getFRIENDNAME");
		return u;
	}

	public List<Friend> friendAcceptedList(String username) {
		log.debug("Starting of the FRIENDDAO Method FriendList");
		Session session=getSession();
		Transaction tx=session.beginTransaction();
		//String hql="from Friend where friend_name='"+username+"' and friend_request='P'";
		String hql=" from User where username in(select username from Friend where friend_request='A')";
		Query query=session.createQuery(hql);
		List<Friend>j=query.list();
		log.debug("List of friends in FRiendDAO"+j);
		tx.commit();
		session.close();
		log.debug("Username is"+username);
		log.debug("Starting of the FRIENDDAO Method FriendList");
		
		return j;
	}

	

}
