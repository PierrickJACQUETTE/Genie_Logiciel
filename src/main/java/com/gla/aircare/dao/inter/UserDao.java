package com.gla.aircare.dao.inter;

import com.gla.aircare.dao.object.User;

public interface UserDao {

	public User getUser(String idUser);

	public int setUser(User user);
	
	public int addUser(User user);

}
