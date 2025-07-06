package com.BiteCart.DAO;
import java.util.List;

import com.BiteCart.Modal.Menu;

public interface MenuDAO {
	List<Menu> getAllMenus();
	Menu getMenuById(int orderId);
	int addMenu(Menu m);
	int updateMenu(Menu m);
	int deleateMenu(int menuId);
	List<Menu> getMenuByRestaurantId(int restaurantId);
	

}
