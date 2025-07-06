package com.BiteCart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.BiteCart.DAOImpl.RestaurantDAOImpl;
import com.BiteCart.Modal.Restaurant;

/**
 * Servlet implementation class ManuToRestaurant
 */
@WebServlet("/manuToRestaurant")
public class ManuToRestaurant extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int restaurantId = Integer.parseInt(request.getParameter("id"));

		// Get restaurant details as usual...
		RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
		Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);

		// Set restaurantId in session ✅
		request.getSession().setAttribute("restaurantId", restaurantId);

		// Forward to JSP
		request.setAttribute("restaurant", restaurant);
		request.getRequestDispatcher("menu.jsp").forward(request, response);

		RestaurantDAOImpl restro=new RestaurantDAOImpl();
		List<Restaurant> allRestaurant = restro.getAllRestaurant();
		request.setAttribute("restaurantList", allRestaurant);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int restaurantId = Integer.parseInt(request.getParameter("id"));

		// Get restaurant details as usual...
		RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
		Restaurant restaurant = restaurantDAO.getRestaurantById(restaurantId);

		// Set restaurantId in session ✅
		request.getSession().setAttribute("restaurantId", restaurantId);

		// Forward to JSP
		request.setAttribute("restaurant", restaurant);
		request.getRequestDispatcher("menu.jsp").forward(request, response);

		RestaurantDAOImpl restro=new RestaurantDAOImpl();
		List<Restaurant> allRestaurant = restro.getAllRestaurant();
		request.setAttribute("restaurantList", allRestaurant);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
		requestDispatcher.forward(request, response);
	}

}
