package com.BiteCart;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.BiteCart.DAOImpl.RestaurantDAOImpl;
import com.BiteCart.Modal.Restaurant;
import com.BiteCart.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = DBConnection.getConnection();
	private static PreparedStatement statement=null;
	private static final String QUERY = "SELECT * FROM User WHERE username = ? OR email = ?";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.sendRedirect("login.html"); // Or wherever your login form lives
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			statement = connection.prepareStatement(QUERY);
			statement.setString(1, username);
			statement.setString(2, username);
			ResultSet executeQuery = statement.executeQuery();
			if (executeQuery.next()) {
				String pass = executeQuery.getString("password");
				int UserId = executeQuery.getInt("UserId");
				if (pass.equals(password)) {
					session.setAttribute("UserId", UserId);
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					String role = executeQuery.getString("role");
					if (role.equalsIgnoreCase("customer")) {
						RestaurantDAOImpl restro=new RestaurantDAOImpl();
						List<Restaurant> allRestaurant = restro.getAllRestaurant();
						request.setAttribute("restaurantList", allRestaurant);
						session.setAttribute("restaurantList", allRestaurant);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
						requestDispatcher.forward(request, response);
					} else {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ownerInterface.jsp");
						requestDispatcher.forward(request, response);

					}
				}
				else {
					request.setAttribute("error", "Incorrect password! Please try again.");
					RequestDispatcher rd = request.getRequestDispatcher("/relogin.jsp");
					rd.forward(request, response);


				}
			} else {
				request.setAttribute("error", "User doesn't exist. Please register.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/relogin.jsp");
				requestDispatcher.forward(request, response);

			}

		} catch (SQLException e) {
			request.setAttribute("Eception", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.html");
			requestDispatcher.forward(request, response);
		}

	}
	@Override
		public void destroy() {

		}

}
