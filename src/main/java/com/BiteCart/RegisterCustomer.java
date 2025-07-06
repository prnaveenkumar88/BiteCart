package com.BiteCart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.BiteCart.util.DBConnection;


@WebServlet("/registerCustomer")
public class RegisterCustomer extends HttpServlet {
	static Connection connection=null;
	static PreparedStatement statement=null;
	private static final String REGISTER_USER ="INSERT INTO User (name, username, password, email, phone, address, role, createDate, lastLoginDate) " +
		    "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
	
	@Override
	public void init() throws ServletException {
		connection=DBConnection.getConnection();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String role = request.getParameter("role");
			
			statement = connection.prepareStatement(REGISTER_USER);
			statement.setString(1, name);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, email);
			statement.setString(5, phone);
			statement.setString(6, address);
			statement.setString(7, role);
			
			int executeUpdate = statement.executeUpdate();
			if(executeUpdate==1) {
				request.setAttribute("success", "Registration successful! Please log in.");
				RequestDispatcher rd = request.getRequestDispatcher("relogin.jsp");
				rd.forward(request, response);

				
			}
		} catch (SQLException e) {
			
			request.setAttribute("error", "User allready Exist");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/relogin.jsp");
			requestDispatcher.forward(request, response);
		}
	
	}
	@Override
	public void destroy() {
		try {
			statement.cancel();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
