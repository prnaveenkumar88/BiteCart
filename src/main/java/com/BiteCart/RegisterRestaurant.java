package com.BiteCart;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Date;
import java.util.UUID;

import com.BiteCart.DAOImpl.RestaurantDAOImpl;
import com.BiteCart.DAOImpl.UserDAOImpl;
import com.BiteCart.Modal.Restaurant;
import com.BiteCart.Modal.User;
import com.BiteCart.util.DBConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/RegisterRestaurant")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,   // 2MB
                 maxFileSize = 1024 * 1024 * 10,        // 10MB
                 maxRequestSize = 1024 * 1024 * 50)     // 50MB
public class RegisterRestaurant extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection connection = DBConnection.getConnection();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 1. Get user data
            String name = request.getParameter("ownerName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String role = request.getParameter("role");

            // 2. Get restaurant-specific data
            String restaurantName = request.getParameter("restaurantName");
            String cuisineType = request.getParameter("cuisineType");
            String description = request.getParameter("description");
            String openTime = request.getParameter("openTime");
            String closeTime = request.getParameter("closeTime");

            // 3. Handle Image Upload
            String imagePath;
            Part imagePart = request.getPart("photo");

            if (imagePart != null && imagePart.getSize() > 0) {
                String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                imagePart.write(uploadDir + File.separator + uniqueFileName);
                imagePath = "uploads/" + uniqueFileName;
            } else {
                System.out.println("No image uploaded or field is missing.");
                imagePath = "uploads/default.png"; // fallback image
            }

            // 4. Insert user data
            User user = new User(name, username, password, email, phone, address, role);
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            int userRows = userDAOImpl.addUser(user);

            // 5. Insert restaurant data
            if (userRows > 0) {
                Restaurant restaurant = new Restaurant();
                restaurant.setUsername(username);
                restaurant.setRestaurantName(restaurantName);
                restaurant.setCuisineType(cuisineType);
                restaurant.setDescription(description);
                restaurant.setAddress(address);
                restaurant.setOpenTime(openTime);
                restaurant.setCloseTime(closeTime);
                restaurant.setImagePath(imagePath);
                restaurant.setCreateDate(new Date()); // 🔥 FIXED

                RestaurantDAOImpl dao = new RestaurantDAOImpl();
                dao.addRestaurant(restaurant);

                request.setAttribute("success", "Restaurant and user registered successfully! Please login.");
                RequestDispatcher rd = request.getRequestDispatcher("relogin.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("error", "User registration failed.");
                RequestDispatcher rd = request.getRequestDispatcher("register-owner.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3>Error occurred: " + e.getMessage() + "</h3>");
        }
    }
}
