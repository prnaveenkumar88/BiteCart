package com.BiteCart;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.BiteCart.DAOImpl.OrderItemDAOImpl;
import com.BiteCart.DAOImpl.OrdersDAOImpl;
import com.BiteCart.Modal.OrderItems;
import com.BiteCart.Modal.Orders;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateOrders
 */
@WebServlet("/UpdateOrders")
public class UpdateOrders extends HttpServlet {
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		 Map<Integer, OrderItems> cart = (Map<Integer, OrderItems>) session.getAttribute("cart");
		int restaurantId = (Integer) session.getAttribute("restaurantId");	
		int UserId = (Integer) session.getAttribute("UserId");
		double grandTotal = (Double) session.getAttribute("grandTotal");	
		
		Orders order = new Orders();
		OrdersDAOImpl orders = new OrdersDAOImpl();
		OrderItemDAOImpl orderItem = new OrderItemDAOImpl();
		//RestaurantId, UserId, TotalAmount, Status, Paymentstatus
		String paymentMethod = request.getParameter("paymentMethod");
		String Paymentstatus=paymentMethod=="upi"?"Payment done":"Payment done by COD";
		order.setRestaurantId(restaurantId);
		order.setUserId(UserId);
		order.setPaymentMode(paymentMethod);
		order.setStatus(Paymentstatus);
		order.setTotalAmount(grandTotal);
		int orderId = orders.addOrder(order);
		Set<Integer> keySet = cart.keySet();
		//OrderId, MenuId, Quantity, TotalAmount
		for(Integer I:keySet ) {
			int menuId = cart.get(I).getMenuId();
			double totalAmount = cart.get(I).getTotalAmount();
			int quantity = cart.get(I).getQuantity();
			OrderItems orderItems = new OrderItems(orderId, menuId, quantity, totalAmount);
			orderItem.addOrderItem(orderItems);
			
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/OrderPlaced.jsp");
		requestDispatcher.forward(request, response);

}
}