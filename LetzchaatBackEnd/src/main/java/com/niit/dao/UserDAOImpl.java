package com.niit.dao;

import java.io.Serializable;
import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {
	
private static Logger log=LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession()
	{
		return sessionFactory.openSession();
	}
@Autowired
User user;

	public boolean validate(String username, String password) {
		log.debug("Starting of the Method USERDAOIMPL :: VALIDATE");
		Session session=getSession();
		//Transaction tx=session.beginTransaction();
		boolean userfound=false;
		String sql_query="from User where username=:username and password=:password";
		  Query query=session.createQuery(sql_query);
		  query.setParameter("username", username);
		  query.setParameter("password", password);
		 // query.setParameter("role", role);
		  List<User> list=query.list();
		  if((list!=null)&&(list.size()>0)){
			  userfound=true;

		
		  session.close();
		  log.debug("Ending of the Method USERDAOIMPL :: VALIDATE");
		  return userfound;
		  }
		  else{
			  user.setErrorcode("404");
				return userfound;  
		  }
			
		
	}

	public boolean insertUser(User user) {
		
		try {
			log.debug("Starting of the Method USERDAOIMPL :: INSERTUSER");
			 Session session = getSession();
			  Transaction tx = session.beginTransaction();
			  session.saveOrUpdate(user);
			  tx.commit();
			 /* Serializable username = session.getIdentifier(user);*/
			  session.close();
			  log.debug("Ending of the Method USERDAOIMPL :: INSERTUSER");
			  return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		 
		
	}

	public void updateUser(User user) {
		log.debug("Starting of the Method USERDAOIMPL::UPDATEUSER");
		log.debug("ISONLINE VALUE IS [BEFORE UPDATE]" + user.getIsonline());
		Session session=getSession();
		/*User existingUser=(User)session.get(User.class, user.getUsername());
		//update online status as true
		existingUser.setIsonline(user.getIsonline());;*/ 

		session.update(user);
		session.flush();
		session.close();
		log.debug("ISONLINE VALUE IS [AFTER UPDATE] " + user.getIsonline());
		log.debug("Starting of the Method USERDAOIMPL::UPDATEUSER");
		
	}

}
