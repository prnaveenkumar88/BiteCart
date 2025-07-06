package com.BiteCart.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.BiteCart.DAO.UserDAO;
import com.BiteCart.Modal.User;
import com.BiteCart.util.DBConnection;

public class UserDAOImpl implements UserDAO {

	static Connection connection=DBConnection.getConnection();;
	private String INSERT="Insert into `User` (name,username, password, Email, phone, Address, role,createDate,lastLoginDate) values(?,?,?,?,?,?,?,?,?)";
	private String UPDATE_USER="UPDATE `user` set name=?,username=?, password=?, Email=?, phone=?, Address=?, role =? where userId=?";
	private String DELETE_USER="delete from `user` where userId=?";
	private String GET_USER_BY_ID="select * from `user` where `userId`=?";
	private String ALL_USERS="select * from `user`";
	@Override
	public List<User> getAllUsers() {
		List<User> Users = new ArrayList<User>();
		try {
			PreparedStatement statement = connection.prepareStatement(ALL_USERS);
			ResultSet executeQuery = statement.executeQuery();
			while (executeQuery.next()) {
				int Id = executeQuery.getInt(1);
				String name = executeQuery.getString(2);
				String username = executeQuery.getString(3);
				String password = executeQuery.getString(4);
				String email = executeQuery.getString(5);
				String phone = executeQuery.getString(6);
				String address = executeQuery.getString(7);
				String role = executeQuery.getString(8);
				Timestamp createDate = executeQuery.getTimestamp(9);
				Timestamp lastLoginDate = executeQuery.getTimestamp(10);
				User u=new User(Id, username, password, email, phone, address, role, createDate, lastLoginDate,name );
				Users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return Users;
	}
	@Override
	public User getUserById(int userId) {
		User u=null;
		try {
			PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, userId);
			ResultSet executeQuery = statement.executeQuery();
			while (executeQuery.next()) {
				int Id = executeQuery.getInt(1);
				String name = executeQuery.getString(2);
				String username = executeQuery.getString(3);
				String password = executeQuery.getString(4);
				String email = executeQuery.getString(5);
				String phone = executeQuery.getString(6);
				String address = executeQuery.getString(7);
				String role = executeQuery.getString(8);
				Timestamp createDate = executeQuery.getTimestamp(9);
				Timestamp lastLoginDate = executeQuery.getTimestamp(10);
				u=new User(Id, username, password, email, phone, address, role, createDate, lastLoginDate,name );
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	@Override
	public int addUser(User u) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT);
			statement.setString(1, u.getName());
			statement.setString(2, u.getUsername());
			statement.setString(3, u.getPassword());
			statement.setString(4, u.getEmail());
			statement.setString(5, u.getPhone());
			statement.setString(6, u.getAddress());
			statement.setString(7, u.getRole());
			statement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			statement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			int i = statement.executeUpdate();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int updateUser(User u) {
		try {
			PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
			statement.setString(1, u.getName());
			statement.setString(2, u.getUsername());
			statement.setString(3, u.getPassword());
			statement.setString(4, u.getEmail());
			statement.setString(5, u.getPhone());
			statement.setString(6, u.getAddress());
			statement.setString(8, u.getRole());
			statement.setInt(9, u.getUserId());	
			int executeUpdate = statement.executeUpdate();
			return executeUpdate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
		
	}
	@Override
	public int deleateUser(int userId) {
		// TODO Auto-generated method stub
			try {
				PreparedStatement statement = connection.prepareStatement(DELETE_USER);
				statement.setInt(1, userId);
				return statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userId;		
	}

}
