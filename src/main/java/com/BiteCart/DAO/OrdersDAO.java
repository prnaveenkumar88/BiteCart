package com.BiteCart.DAO;

import java.util.List;

import com.BiteCart.Modal.Orders;

public interface OrdersDAO {
	List<Orders> getAllOrders();
	List<Orders> getAllOrdersUserId(int userId);
	Orders getOrderById(int orderId);
	int addOrder(Orders o);
	int updateOrder(Orders r);
	int deleateOrder(int orderId);

}
