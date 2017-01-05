package com.niit.dao;

import java.util.List;

import com.niit.model.User;

public interface UserDAO {
	
	public boolean validate(String username,String password);
	public boolean insertUser(User user);
	public void updateUser(User user);
	/*public User get(String username);*/

}
