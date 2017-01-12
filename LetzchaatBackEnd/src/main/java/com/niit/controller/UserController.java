package com.niit.controller;

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


import com.niit.dao.UserDAOImpl;
import com.niit.model.User;

@RestController
public class UserController {
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDAOImpl userDAOImpl;
	@Autowired
	User user;
	
	@RequestMapping(value="/login/",method=RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User user,HttpServletRequest request,HttpSession session)
	{
		log.debug("Starting of the Method isValidUser");
		String username=user.getUsername();
		String password=user.getPassword();
		user=userDAOImpl.validate(username, password);
		if(user==null)
		{
			
			user.setErrorcode("404");
			user.setErrormessage("Username and password doesnt exists...");
			//Error error=new Error(1,"Username and password doesnt exists...");
			return new ResponseEntity<User>(HttpStatus.OK);
			
			
		}
		else
		{
			session.setAttribute("username", username);
			session.setAttribute("role", user.getRole());
			user.setIsonline('Y');
			user.setErrorcode("200");
			user.setErrormessage("You have successfully Loggedin");
			
			
			log.debug("Ending of the Method isValidUser");
			return new ResponseEntity<User>(user,HttpStatus.OK) ;
		}
	
	}
	
	@RequestMapping(value="/register/", method=RequestMethod.POST)
	public ResponseEntity<User> registerUser(@RequestBody User user)
	{
		
			log.debug("USERCONTROLLER=>REGISTER " + user);
			if(userDAOImpl.getUsername(user.getUsername())==null)
			{
			user.setStatus('N');
			user.setIsonline('N');
			
			if(userDAOImpl.insertUser(user)==true)
				{
				user.setErrorcode("200");
				user.setErrormessage("You have Registered Successfully ");
				return new ResponseEntity<User>(user,HttpStatus.OK);
				}
				else{
					user.setErrorcode("404");
					user.setErrormessage("Couldnt insert user details ");
					return new ResponseEntity<User>(user , HttpStatus.OK);
					
				}
				/*return new ResponseEntity<User>(user, HttpStatus.OK);*/
			}
			log.debug("->->->->User already exist with id " + user.getUsername());
			user.setErrorcode("404");
			user.setErrormessage("User already exist with id : " + user.getUsername());
			return new ResponseEntity<User>(user, HttpStatus.OK);
			
			
		
	}
	
	@RequestMapping(value="/user/logout/",method=RequestMethod.PUT)
	public ResponseEntity<User> logout(HttpSession session)
	{
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			user.setIsonline('N');
			userDAOImpl.updateUser(user);
		}
		
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ResponseEntity<User> myProfile(HttpSession session) {
		log.debug("->->calling method myProfile");
		String username = (String) session.getAttribute("username");
		User user = userDAOImpl.getUsername(username);
		if (user == null) {
			log.debug("->->->-> User does not exist wiht id" + username);
			
			user.setErrorcode("404");
			user.setErrormessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		log.debug("->->->-> User exist with username" + username);
		log.debug(user.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {

		log.debug("->->->->calling method listAllUsers");
		List<User> users = userDAOImpl.list();

		// errorCode :200 :404
		// errorMessage :Success :Not found

		if (users.isEmpty()) {
			user.setErrorcode("404");
			user.setErrormessage("No users are available");
			users.add(user);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody User user) {
		log.debug("->->->->calling method update");
		if (userDAOImpl.getUsername(user.getUsername()) == null) {
			log.debug("->->->->User does not exist with id " + user.getUsername());
			user = new User(); // ?
			user.setErrorcode("404");
			user.setErrormessage("User does not exist with id " + user.getUsername());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

		userDAOImpl.Update(user);
		log.debug("->->->->User updated successfully");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accept/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> accept(@PathVariable("username") String username) {
		log.debug("Starting of the method accept");

		user = updateStatus(username, 'A', "");
		log.debug("Ending of the method accept");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "/reject/{username}/{reason}", method = RequestMethod.GET)
	public ResponseEntity<User> reject(@PathVariable("username") String username, @PathVariable("reason") String reason) {
		log.debug("Starting of the method reject");

		user = updateStatus(username, 'R', reason);
		log.debug("Ending of the method reject");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	private User updateStatus(String username, char status, String reason) {
		log.debug("Starting of the method updateStatus");

		log.debug("status: " + status);
		user = userDAOImpl.getUsername(username);

		if (user == null) {
			user = new User();
			user.setErrorcode("404");
			user.setErrormessage("Could not update the status to " + status);
		} else {

			user.setStatus(status);
			user.setReason(reason);
			
			userDAOImpl.Update(user);
			
			user.setErrorcode("200");
			user.setErrormessage("Updated the status successfully");
		}
		log.debug("Ending of the method updateStatus");
		return user;

	}
	@RequestMapping(value = "/friendRequest/", method = RequestMethod.POST)
	public ResponseEntity<User> friendRequest(@RequestBody String username,HttpSession session) {
		log.debug("->->calling method myProfile");
		String currentusername = (String) session.getAttribute("username");
		if(currentusername==null)
		{
log.debug("->->->-> User does not exist wiht id" + currentusername);
			
			user.setErrorcode("404");
			user.setErrormessage("User does not exist");
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
		else
		{
		userDAOImpl.sendFriendRequest(currentusername, username);
		
		log.debug("->->->-> User exist with username" + currentusername);
		log.debug("friend_name is:"+username);
		return new ResponseEntity<User>( HttpStatus.OK);
	}
	}
	
	@RequestMapping(value="/getUsers",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(HttpSession session){
		String username=(String)session.getAttribute("username");
		List<User> users=userDAOImpl.getAllUsers(username);
		if(username==null)
		{
			user.setErrorcode("404");
			user.setErrormessage("Data not found");
			users.add(user);
		}
		
		
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}

	

}
