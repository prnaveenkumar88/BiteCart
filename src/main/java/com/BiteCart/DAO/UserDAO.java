package com.BiteCart.DAO;

import java.util.List;

import com.BiteCart.Modal.User;

public interface UserDAO {
	List<User> getAllUsers();
	User getUserById(int userId);
	int addUser(User u);
	int updateUser(User u);
	int deleateUser(int userId);

}
