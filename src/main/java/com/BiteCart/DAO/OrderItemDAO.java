package com.BiteCart.DAO;
import java.util.List;

import com.BiteCart.Modal.OrderItems;


public interface OrderItemDAO {
	List<OrderItems> getAllOrderItems();
	List<OrderItems> getAllOrderItemsByOrderId(int orderId);
	OrderItems getOrderItemById(int orderId);
	int addOrderItem(OrderItems r);
	int updateOrderItem(OrderItems r);
	int deleateOrderItem(int orderItemId);
}
