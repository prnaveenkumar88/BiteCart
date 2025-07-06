<%@ page import="java.util.*, com.BiteCart.Modal.Menu, com.BiteCart.DAOImpl.MenuDAOImpl, com.BiteCart.Modal.OrderItems" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // ✅ NO restaurant ID: get all menus instead
    MenuDAOImpl menuDAO = new MenuDAOImpl();
    List<Menu> menuList = menuDAO.getAllMenus();

    Map<Integer, OrderItems> cart = (Map<Integer, OrderItems>) session.getAttribute("cart");
    if (cart == null) {
        cart = new HashMap<>();
        session.setAttribute("cart", cart);
    }

    Integer orderItemSeq = (Integer) session.getAttribute("orderItemSeq");
    if (orderItemSeq == null) {
        orderItemSeq = 1;
    }

    String action = request.getParameter("action");
    if (action != null) {
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        double price = menuDAO.getMenuById(menuId).getPrice();

        if ("add".equals(action)) {
            OrderItems newItem = new OrderItems();
            newItem.setOrderItemId(orderItemSeq);
            newItem.setMenuId(menuId);
            newItem.setQuantity(1);
            newItem.setTotalAmount(price);

            cart.put(orderItemSeq, newItem);
            orderItemSeq++;
        } else {
            int orderItemId = Integer.parseInt(request.getParameter("orderItemId"));
            OrderItems existing = cart.get(orderItemId);

            if (existing != null) {
                int qty = existing.getQuantity();
                if ("increase".equals(action)) {
                    qty++;
                    existing.setQuantity(qty);
                    existing.setTotalAmount(price * qty);
                } else if ("decrease".equals(action)) {
                    if (qty <= 1) {
                        cart.remove(orderItemId);
                    } else {
                        qty--;
                        existing.setQuantity(qty);
                        existing.setTotalAmount(price * qty);
                    }
                }
            }
        }
    }

    session.setAttribute("orderItemSeq", orderItemSeq);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Explore All Menu Items</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background-color: #111;
            color: #fff;
            overflow-x: hidden;
        }
        #bg-video-desktop, #bg-video-mobile {
            position: fixed;
            top: 0; left: 0;
            width: 100vw; height: 100vh;
            object-fit: cover;
            z-index: -2;
            display: none;
        }
        @media (min-width: 768px) { #bg-video-desktop { display: block; } }
        @media (max-width: 767px) { #bg-video-mobile { display: block; } }

        .overlay {
            position: fixed; top: 0; left: 0;
            width: 100vw; height: 100vh;
            background: rgba(0,0,0,0.2);
            backdrop-filter: blur(2px);
            z-index: -1;
        }

        .header {
            padding: 20px; text-align: center;
            background: rgba(0,0,0,0.7);
            backdrop-filter: blur(4px);
            position: sticky; top: 0; z-index: 10;
        }
        .header h1 { margin: 0; font-size: 28px; color: #f8c471; }

        .menu-container {
            max-width: 1000px; margin: 40px auto;
            padding: 0 20px;
        }

        .menu-item {
            background: rgba(255,255,255,0.07);
            backdrop-filter: blur(6px);
            border-radius: 15px;
            padding: 20px; margin-bottom: 20px;
            display: flex; align-items: center; justify-content: space-between;
            gap: 20px;
            box-shadow: 0 6px 12px rgba(0,0,0,0.3);
        }
        .menu-item img {
            width: 100px; height: 100px;
            object-fit: cover; border-radius: 10px;
        }
        .menu-item-details { flex: 1; }
        .menu-item h3 { margin: 0; font-size: 20px; color: #ffeaa7; }
        .menu-item p { font-size: 14px; color: #ddd; margin: 5px 0; }
        .price { font-weight: bold; font-size: 18px; color: #2ecc71; }

        .controls form { display: inline; }
        .controls button {
            background: #e67e22; color: #fff; border: none;
            padding: 6px 12px; margin: 0 5px; cursor: pointer; border-radius: 5px;
        }
        .controls button:hover { background: #d35400; }

        .cart-link {
            position: fixed; bottom: 55px; left: 50%; transform: translateX(-50%);
            background: #f1c40f; color: #111; padding: 10px 30px;
            border-radius: 30px; font-weight: bold;
            text-decoration: none; box-shadow: 0 4px 10px rgba(0,0,0,0.4);
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

<video autoplay muted loop playsinline id="bg-video-desktop">
    <source src="<%=request.getContextPath()%>/videos/desktopMenu.mp4" type="video/mp4">
</video>
<video autoplay muted loop playsinline id="bg-video-mobile">
    <source src="<%=request.getContextPath()%>/videos/mobialMenu.mp4" type="video/mp4">
</video>

<div class="overlay"></div>

<div class="header">
    <h1>Explore All Menu Items</h1>
</div>

<div class="menu-container">
<% if (menuList != null && !menuList.isEmpty()) {
    for (Menu item : menuList) {
        OrderItems matchedItem = null;
        for (OrderItems oi : cart.values()) {
            if (oi.getMenuId() == item.getMenuId()) {
                matchedItem = oi; 
                break;
            }
        }
%>
    <div class="menu-item">
        <img src="<%= item.getImagePath() %>">
        <div class="menu-item-details">
            <h3><%= item.getItemName() %></h3>
            <p><%= item.getDescription() %></p>
            <span class="price">₹<%= item.getPrice() %></span>
            <div class="controls">
                <% if (matchedItem == null) { %>
                    <form method="post">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="action" value="add">
                        <button type="submit">Add to Cart</button>
                    </form>
                <% } else { %>
                    <form method="post">
                        <input type="hidden" name="orderItemId" value="<%= matchedItem.getOrderItemId() %>">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="action" value="decrease">
                        <button type="submit">-</button>
                    </form>
                    <span><%= matchedItem.getQuantity() %></span>
                    <form method="post">
                        <input type="hidden" name="orderItemId" value="<%= matchedItem.getOrderItemId() %>">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="action" value="increase">
                        <button type="submit">+</button>
                    </form>
                <% } %>
            </div>
        </div>
    </div>
<% } 
   }
    else { %>
    <h2 style="text-align:center; color:#ccc;">No menu items found.</h2>
<% } %>
</div>

<nav class="bottom-nav">
  <a href="Home.jsp">🏠 Home</a>
  <a href="allItems.jsp">🍽️ All Items</a>
  <a href="orders.jsp">📦 Orders</a>
  <a href="profile.jsp">👤 Profile</a>
</nav>
<a href="showCart.jsp" class="cart-link">Go to Cart</a>

</body>
</html>
