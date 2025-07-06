package com.BiteCart.Modal;

import java.sql.Time;
import java.util.Date;

public class Restaurant {

    private int restaurantId;
    private String restaurantName;
    private String cuisineType;
    private String description;
    private String address;
    private String openTime;
    private String closeTime;
    private String imagePath;
    private double rating;
    private Date createDate;
    private String username; // Foreign key from User

    // Constructors
    public Restaurant() {}
    
    public Restaurant(String restaurantName, String cuisineType, String description, String address, String openTime,
    		String closeTime, String imagePath, Date createDate, String username) {
		this.restaurantName = restaurantName;
		this.cuisineType = cuisineType;
		this.description = description;
		this.address = address;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.imagePath = imagePath;
		this.createDate = createDate;
		this.username = username;
	}

	public Restaurant(int restaurantId, String restaurantName, String cuisineType, String description,
                      String address, String openTime, String closeTime, String imagePath,
                      double rating, Date createDate, String username) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cuisineType = cuisineType;
        this.description = description;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imagePath = imagePath;
        this.rating = rating;
        this.createDate = createDate;
        this.username = username;
    }

    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String sqlOpenTime) {
        this.openTime = sqlOpenTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	@Override
	public String toString() {
		return "Restaurant [restaurantName=" + restaurantName + ", cuisineType=" + cuisineType + ", description="
				+ description + ", address=" + address + ", openTime=" + openTime + ", closeTime=" + closeTime
				+ ", imagePath=" + imagePath + "]";
	}
  
}
