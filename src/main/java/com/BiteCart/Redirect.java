package com.BiteCart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Redirect
 */
@WebServlet("/Redirect")
public class Redirect extends HttpServlet {


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated 
		 String id = request.getParameter("id");
		 System.out.println(id);
		 if(id.equalsIgnoreCase("0")) {
			 request.getRequestDispatcher("/allItems.jsp").forward(request, response);;
			 
		 }
		 else {
			 request.getRequestDispatcher("/menu.jsp?id="+id).forward(request, response);;
			
		}
	}

}
