package com.BiteCart.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.BiteCart.DAO.RestaurantDAO;
import com.BiteCart.Modal.Restaurant;
import com.BiteCart.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    private static final Connection connection = DBConnection.getConnection();

    private static final String INSERT_RESTAURANT = 
        "INSERT INTO Restaurant (restaurantName, cuisineType, description, address, openTime, closeTime, imagePath, createDate, username) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_RESTAURANT = 
        "UPDATE Restaurant SET restaurantName=?, cuisineType=?, description=?, address=?, openTime=?, closeTime=?, imagePath=?, rating=?, createDate=?, username=? WHERE restaurantId=?";

    private static final String DELETE_RESTAURANT = 
        "DELETE FROM Restaurant WHERE restaurantId=?";

    private static final String GET_RESTAURANT_BY_ID = 
        "SELECT * FROM Restaurant WHERE restaurantId=?";

    private static final String GET_ALL_RESTAURANTS = 
        "SELECT * FROM Restaurant";

    @Override
    public int addRestaurant(Restaurant r) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_RESTAURANT)) {
            ps.setString(1, r.getRestaurantName());
            ps.setString(2, r.getCuisineType());
            ps.setString(3, r.getDescription());
            ps.setString(4, r.getAddress());
            ps.setString(5, r.getOpenTime());
            ps.setString(6, r.getCloseTime());
            ps.setString(7, r.getImagePath());
            ps.setTimestamp(8, new Timestamp(r.getCreateDate().getTime()));
            ps.setString(9, r.getUsername());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Or use a logger
        }
		return 0;
    }

    @Override
    public int updateRestaurant(Restaurant r) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_RESTAURANT)) {
            ps.setString(1, r.getRestaurantName());
            ps.setString(2, r.getCuisineType());
            ps.setString(3, r.getDescription());
            ps.setString(4, r.getAddress());
            ps.setString(5, r.getOpenTime());
            ps.setString(6, r.getCloseTime());
            ps.setString(7, r.getImagePath());
            ps.setDouble(8, r.getRating());
            ps.setTimestamp(9, new Timestamp(r.getCreateDate().getTime()));
            ps.setString(10, r.getUsername());
            ps.setInt(11, r.getRestaurantId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
    }

    @Override
    public int deleateRestaurant(int restaurantId) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_RESTAURANT)) {
            ps.setInt(1, restaurantId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant r = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_RESTAURANT_BY_ID)) {
            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = extractRestaurantFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        List<Restaurant> restaurants = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_RESTAURANTS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                restaurants.add(extractRestaurantFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    private Restaurant extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
        Restaurant r = new Restaurant();
        r.setRestaurantId(rs.getInt("restaurantId"));
        r.setRestaurantName(rs.getString("restaurantName"));
        r.setCuisineType(rs.getString("cuisineType"));
        r.setDescription(rs.getString("description"));
        r.setAddress(rs.getString("address"));
        r.setOpenTime(rs.getString("openTime"));
        r.setCloseTime(rs.getString("closeTime"));
        r.setImagePath(rs.getString("imagePath"));
        r.setRating(rs.getDouble("rating"));
        r.setCreateDate(rs.getTimestamp("createDate"));
        r.setUsername(rs.getString("username"));
        return r;
    }
}
