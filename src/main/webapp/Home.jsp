<%@ page import="java.util.*, com.BiteCart.Modal.Restaurant"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
  <title>Explore Restaurants | BiteCart</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    * {
      margin: 0; padding: 0; box-sizing: border-box;
    }

    body {
      font-family: 'Segoe UI', sans-serif;
      color: #fff;
      min-height: 100vh;
      overflow-x: hidden;
      background: none;
    }

    #bg-video-desktop, #bg-video-mobile {
      position: fixed; top: 0; left: 0;
      width: 100vw; height: 100vh;
      object-fit: cover;
      z-index: -2; display: none;
    }

    @media (min-width: 768px) {
      #bg-video-desktop { display: block; }
    }
    @media (max-width: 767px) {
      #bg-video-mobile { display: block; }
    }

    .overlay {
      position: fixed; top: 0; left: 0;
      width: 100vw; height: 100vh;
      background: rgba(0, 0, 0, 0.2);
      backdrop-filter: blur(2px);
      z-index: -1;
    }

    .search-bar-container {
      position: fixed; top: 80px; left: 50%;
      transform: translateX(-50%);
      z-index: 15;
    }

    .search-bar {
      width: 100%; max-width: 500px;
      padding: 12px 20px;
      border-radius: 30px; border: none;
      font-size: 16px; outline: none;
      background: rgba(255, 255, 255, 0.15);
      backdrop-filter: blur(8px);
      color: white;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    }

    .search-bar::placeholder { color: #ddd; }

    .container {
      max-width: 1300px; margin: 160px auto 50px;
      padding: 0 20px;
      display: flex; flex-wrap: wrap;
      justify-content: center; gap: 40px;
    }

    .restaurant-card {
      background: rgba(255, 255, 255, 0.08);
      backdrop-filter: blur(8px);
      width: 270px;
      border-radius: 20px;
      overflow: hidden;
      transition: all 0.3s ease;
      box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
      text-decoration: none; color: #fff;
    }

    .restaurant-card:hover {
      transform: scale(1.07);
      background: rgba(255, 255, 255, 0.13);
    }

    .restaurant-card img {
      width: 100%; height: 160px; object-fit: cover;
    }

    .restaurant-card .info {
      padding: 15px;
    }

    .restaurant-card h3 {
      margin: 0 0 10px; font-size: 20px;
    }

    .restaurant-card p {
      font-size: 14px; margin: 4px 0; opacity: 0.85;
    }

    h2.no-data {
      text-align: center; color: #f1f1f1; margin-top: 200px;
    }
    .bottom-nav {
  position: fixed;
  bottom: 0; left: 0;
  width: 100%;
  background: rgba(0, 0, 0, 0.6);
  backdrop-filter: blur(8px);
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 15px 0;
  z-index: 20;
}

.bottom-nav a {
  color: #fff;
  text-decoration: none;
  font-size: 18px;
  transition: transform 0.2s ease;
}

.bottom-nav a:hover {
  transform: translateY(-3px);
  color: #ffeaa7;
}
    
  </style>
</head>

<body>

  <!-- BG Videos -->
  <video autoplay muted loop playsinline id="bg-video-desktop">
    <source src="<%=request.getContextPath()%>/videos/desktop-bg.mp4" type="video/mp4">
  </video>
  <video autoplay muted loop playsinline id="bg-video-mobile">
    <source src="<%=request.getContextPath()%>/videos/mobile-bg.mp4" type="video/mp4">
  </video>

  <!-- Soft blur overlay -->
  <div class="overlay"></div>

  <!-- Search bar form -->
  <div class="search-bar-container">
    <form action="exploreRestaurants.jsp" method="get">
      <input type="text" name="query" class="search-bar" placeholder="Search restaurants...">
    </form>
  </div>

  <!-- Restaurants -->
  <div class="container">
    <%
      List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
      if (restaurantList != null && !restaurantList.isEmpty()) {
          for (Restaurant res : restaurantList) {
    %>
      <a href="menu.jsp?id=<%=res.getRestaurantId()%>" class="restaurant-card">
        <img src="<%=res.getImagePath()%>" alt="Restaurant Image" />
        <div class="info">
          <h3><%=res.getRestaurantName()%></h3>
          <p><strong>Cuisine:</strong> <%=res.getCuisineType()%></p>
          <p>⭐ <%=res.getRating()%> / 5.0</p>
        </div>
      </a>
    <%
          }
      } else {
    %>
      <h2 class="no-data">No restaurants found.</h2>
    <%
      }
    %>
  </div>
  <!-- Bottom Navigation Bar -->
<nav class="bottom-nav">
  <a href="Home.jsp">🏠 Home</a>
  <a href="allItems.jsp">🍽️ All Items</a>
  <a href="orders.jsp">📦 Orders</a>
  <a href="profile.jsp">👤 Profile</a>
</nav>
  

</body>
</html>
