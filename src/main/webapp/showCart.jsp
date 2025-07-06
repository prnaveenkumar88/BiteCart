<%@ page import="java.util.*, com.BiteCart.Modal.Menu, com.BiteCart.DAOImpl.MenuDAOImpl, com.BiteCart.Modal.OrderItems" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Map<Integer, OrderItems> cart = (Map<Integer, OrderItems>) session.getAttribute("cart");
    if (cart == null) {
        cart = new HashMap<>();
        session.setAttribute("cart", cart);
    }

    MenuDAOImpl menuDAO = new MenuDAOImpl();

    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
    if (restaurantId == null) {
        restaurantId = 0; // fallback
    }

    double subtotal = 0.0;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Your Cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: #111;
            color: #fff;
            overflow-x: hidden;
            padding: 20px;
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
            background: rgba(0,0,0,0.3);
            backdrop-filter: blur(2px);
            z-index: -1;
        }

        .cart-container {
            max-width: 800px; margin: 0 auto;
        }

        .cart-item {
            background: rgba(255,255,255,0.05);
            backdrop-filter: blur(3px);
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
        }

        .cart-item h3 {
            margin: 0 0 5px 0;
            font-size: 20px;
            color: #ffeaa7;
            text-align: left;
        }

        .cart-item .details {
            text-align: right;
        }

        .cart-item .details p {
            margin: 4px 0;
            color: #FFFFFF;
        }

        .totals {
            background: rgba(255,255,255,0.05);
            backdrop-filter: blur(3px);
            border-radius: 10px;
            padding: 20px;
            text-align: right;
            margin-top: 20px;
        }

        .totals div {
            margin: 8px 0;
        }

        .buttons {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }

        .buttons a, .buttons button {
            background: #2ecc71;
            color: #111;
            font-size: 16px;
            padding: 12px 25px;
            border: none;
            border-radius: 30px;
            text-decoration: none;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .buttons a:hover, .buttons button:hover {
            transform: translateX(5px);
            background: #27ae60;
        }

        .proceed {
            background: #f1c40f;
        }

        .proceed:hover {
            background: #f39c12;
        }
    </style>
</head>
<body>

<video autoplay muted loop playsinline id="bg-video-desktop">
    <source src="<%=request.getContextPath()%>/videos/pcShowcart.mp4" type="video/mp4">
</video>
<video autoplay muted loop playsinline id="bg-video-mobile">
    <source src="<%=request.getContextPath()%>/videos/mobileShowcart.mp4" type="video/mp4">
</video>
<div class="overlay"></div>

<div class="cart-container">
    <h1>Your Cart</h1>

    <%
        if (cart.isEmpty()) {
    %>
        <h3>Your cart is empty.</h3>
    <%
        } else {
            for (OrderItems orderItem : cart.values()) {
                Menu menuItem = menuDAO.getMenuById(orderItem.getMenuId());
                double price = menuItem.getPrice();
                int quantity = orderItem.getQuantity();
                double itemTotal = price * quantity;
                subtotal += itemTotal;
    %>
        <div class="cart-item">
            <h3><%= menuItem.getItemName() %></h3>
            <div class="details">
                <p>₹<%= String.format("%.2f", price) %> x <%= quantity %> = ₹<%= String.format("%.2f", itemTotal) %></p>
            </div>
        </div>
    <%
            }

            double deliveryCharge = subtotal < 500 ? 49 : 0;
            double cgst = subtotal * 0.025;
            double sgst = subtotal * 0.025;
            double grandTotal = subtotal + deliveryCharge + cgst + sgst;
    %>

    <div class="totals">
        <h2>Billing Summary</h2>
        <div>Subtotal: ₹<%= String.format("%.2f", subtotal) %></div>
        <div>CGST (2.5%): ₹<%= String.format("%.2f", cgst) %></div>
        <div>SGST (2.5%): ₹<%= String.format("%.2f", sgst) %></div>
        <div>Delivery Charge: ₹<%= deliveryCharge %></div>
        <hr>
        <div><strong>Grand Total: ₹<%= String.format("%.2f", grandTotal) %></strong></div>
        <% session.setAttribute("grandTotal", grandTotal); %>
    </div>
    <%
        }
    %>

    <div class="buttons">
        <form method="post" action="Redirect">
            <input type="hidden" name="id" value="<%= restaurantId %>">
            <button type="submit">Back to Restaurant</button>
        </form>

        <% if (!cart.isEmpty()) { %>
        <a href="payment.jsp" class="proceed">Proceed to Payment</a>
        <% } %>
    </div>

</div>

</body>
</html>
