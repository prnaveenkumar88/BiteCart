<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payment</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            background: #111;
            color: #fff;
            font-family: 'Segoe UI', sans-serif;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #f1c40f;
        }
        .payment-method {
            background: rgba(255,255,255,0.05);
            padding: 20px;
            border-radius: 10px;
            margin: 10px 0;
            cursor: pointer;
        }
        .payment-method:hover {
            background: rgba(255,255,255,0.1);
        }
        .payment-input {
            display: none;
            margin-top: 10px;
        }
        .payment-input input {
            padding: 10px;
            width: 100%;
            border: none;
            border-radius: 5px;
            margin-top: 5px;
        }
        .place-order {
            display: block;
            width: 100%;
            margin-top: 30px;
            padding: 15px;
            background: #2ecc71;
            color: #111;
            border: none;
            border-radius: 30px;
            font-size: 18px;
            cursor: pointer;
        }
        .place-order:hover {
            background: #27ae60;
        }
    </style>
    <script>
        function selectPayment(method) {
            // Hide all input sections
            let inputs = document.querySelectorAll('.payment-input');
            inputs.forEach(i => i.style.display = 'none');

            // Show selected input
            document.getElementById(method + "-input").style.display = 'block';

            // Set hidden input for server
            document.getElementById("selectedMethod").value = method;
        }

        function validatePayment() {
            const selected = document.getElementById('selectedMethod').value;
            if (!selected) {
                alert("Please select a payment method before placing the order.");
                return false; 
            }
            return true; 
        }
    </script>
</head>
<body>

<h1>Select Payment Method</h1>

<form action="UpdateOrders" method="post" onsubmit="return validatePayment()">
    <!-- Hidden input for the payment method -->
    <input type="hidden" name="paymentMethod" id="selectedMethod">

    <!-- UPI -->
    <div class="payment-method" onclick="selectPayment('upi')">
        UPI
    </div>
    <div id="upi-input" class="payment-input">
        <label>Enter UPI ID:</label>
        <input type="text" name="upiId" placeholder="e.g. name@upi">
    </div>

    <!-- Card -->
    <div class="payment-method" onclick="selectPayment('card')">
        Credit/Debit Card
    </div>
    <div id="card-input" class="payment-input">
        <label>Card Number:</label>
        <input type="text" name="cardNumber" placeholder="xxxx-xxxx-xxxx-xxxx">
    </div>

    <!-- COD -->
    <div class="payment-method" onclick="selectPayment('cod')">
        Cash on Delivery (COD)
    </div>
    <div id="cod-input" class="payment-input">
        <p>Pay with cash when your order is delivered.</p>
    </div>

    <button type="submit" class="place-order">Place Order</button>
</form>

</body>
</html>
