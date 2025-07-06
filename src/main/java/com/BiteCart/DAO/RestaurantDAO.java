package com.BiteCart.DAO;

import java.util.List;

import com.BiteCart.Modal.Restaurant;

public interface RestaurantDAO {
	List<Restaurant> getAllRestaurant();
	Restaurant getRestaurantById(int restaurantId);
	int addRestaurant(Restaurant r);
	int updateRestaurant(Restaurant r);
	int deleateRestaurant(int restaurantId);

}
