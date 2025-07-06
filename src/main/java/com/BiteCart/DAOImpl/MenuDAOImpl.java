package com.BiteCart.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.BiteCart.DAO.MenuDAO;
import com.BiteCart.Modal.Menu;
import com.BiteCart.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    static Connection connection = DBConnection.getConnection();

    private static final String INSERT_MENU = 
        "INSERT INTO `Menu` (RestaurantId, ItemName, Description, Price, IsAvailable, Ratings, ImagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_MENU = 
        "UPDATE `Menu` SET RestaurantId = ?, ItemName = ?, Description = ?, Price = ?, IsAvailable = ?, Ratings = ?, ImagePath = ? WHERE MenuId = ?";

    private static final String DELETE_MENU = 
        "DELETE FROM `Menu` WHERE MenuId = ?";

    private static final String GET_MENU_BY_ID = "SELECT * FROM `Menu` WHERE MenuId = ?";

    private static final String ALL_MENUS = "SELECT * FROM `Menu`";

	private static final String GET_MENU_BY_RRESTAURANT_ID="SELECT * FROM `Menu` WHERE restaurantId = ?";

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(ALL_MENUS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuId(rs.getInt("MenuId"));
                menu.setRestaurantId(rs.getInt("RestaurantId"));
                menu.setItemName(rs.getString("ItemName"));
                menu.setDescription(rs.getString("Description"));
                menu.setPrice(rs.getDouble("Price"));
                menu.setAvailable(rs.getBoolean("IsAvailable"));
                menu.setRatings(rs.getDouble("Ratings"));
                menu.setImagePath(rs.getString("ImagePath"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu getMenuById(int menuId) {
        Menu menu = null;
        try (PreparedStatement stmt = connection.prepareStatement(GET_MENU_BY_ID)) {
            stmt.setInt(1, menuId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                menu = new Menu();
                menu.setMenuId(rs.getInt("MenuId"));
                menu.setRestaurantId(rs.getInt("RestaurantId"));
                menu.setItemName(rs.getString("ItemName"));
                menu.setDescription(rs.getString("Description"));
                menu.setPrice(rs.getDouble("Price"));
                menu.setAvailable(rs.getBoolean("IsAvailable"));
                menu.setRatings(rs.getDouble("Ratings"));
                menu.setImagePath(rs.getString("ImagePath"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public int addMenu(Menu m) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_MENU)) {
            stmt.setInt(1, m.getRestaurantId());
            stmt.setString(2, m.getItemName());
            stmt.setString(3, m.getDescription());
            stmt.setDouble(4, m.getPrice());
            stmt.setBoolean(5, m.isAvailable());
            stmt.setDouble(6, m.getRatings());
            stmt.setString(7, m.getImagePath());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateMenu(Menu m) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_MENU)) {
            stmt.setInt(1, m.getRestaurantId());
            stmt.setString(2, m.getItemName());
            stmt.setString(3, m.getDescription());
            stmt.setDouble(4, m.getPrice());
            stmt.setBoolean(5, m.isAvailable());
            stmt.setDouble(6, m.getRatings());
            stmt.setString(7, m.getImagePath());
            stmt.setInt(8, m.getMenuId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleateMenu(int menuId) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_MENU)) {
            stmt.setInt(1, menuId);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {
		List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(GET_MENU_BY_RRESTAURANT_ID);){
        		stmt.setInt(1, restaurantId);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuId(rs.getInt("MenuId"));
                menu.setRestaurantId(rs.getInt("RestaurantId"));
                menu.setItemName(rs.getString("ItemName"));
                menu.setDescription(rs.getString("Description"));
                menu.setPrice(rs.getDouble("Price"));
                menu.setAvailable(rs.getBoolean("IsAvailable"));
                menu.setRatings(rs.getDouble("Ratings"));
                menu.setImagePath(rs.getString("ImagePath"));
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
	}
}
