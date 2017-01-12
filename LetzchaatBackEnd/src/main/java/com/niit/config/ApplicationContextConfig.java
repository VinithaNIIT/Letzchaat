package com.niit.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Chat;
import com.niit.model.Event;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.JobApplied;
import com.niit.model.User;

@ComponentScan("com.niit")
@Configuration
@EnableTransactionManagement
public class ApplicationContextConfig {
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	   DriverManagerDataSource dataSource=new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("LETZ_DB"); //Schema name
	    dataSource.setPassword("root");

	    return dataSource;
	}
	
	public Properties getHibernateProperties()
	{
		Properties properties=new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		//properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
		
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	    sessionBuilder.addProperties(getHibernateProperties());
	    sessionBuilder.addAnnotatedClasses(User.class);
	    sessionBuilder.addAnnotatedClasses(Blog.class);
	    sessionBuilder.addAnnotatedClasses(BlogComment.class);
	    sessionBuilder.addAnnotatedClasses(Forum.class);
	    sessionBuilder.addAnnotatedClasses(ForumComment.class);
	    sessionBuilder.addAnnotatedClasses(Friend.class);
	    sessionBuilder.addAnnotatedClasses(Event.class);
	    sessionBuilder.addAnnotatedClasses(Chat.class);
	    sessionBuilder.addAnnotatedClasses(Job.class);
	    sessionBuilder.addAnnotatedClasses(JobApplied.class);
	   

	    return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new   HibernateTransactionManager(sessionFactory);
	    return transactionManager;
	}
	  
	

}
