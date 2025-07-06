<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Placing Order...</title>
    <style>
        body {
            background: #111;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            overflow: hidden;
        }

        .loader {
            border: 40px solid rgba(255, 255, 255, 0.1);
            border-top: 40px solid #2ecc71;
            border-radius: 50%;
            width: 500px;
            height: 500px;
            animation: spin 1s linear infinite;
        }

        .success {
            display: none;
            flex-direction: column;
            align-items: center;
        }

        .success svg {
            width: 500px;
            height: 500px;
        }

        .success path {
            stroke: #2ecc71;
            stroke-width: 6;
            stroke-linecap: round;
            stroke-linejoin: round;
            fill: none;
        }

        .success.shrink svg {
            width: 300px;
            height: 300px;
            transition: all 0.5s ease;
        }

        .message {
            color: #2ecc71;
            font-size: 60px;
            margin-top: 50px;
            opacity: 0;
            transition: opacity 0.5s ease;
            text-align: center;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</head>
<body>

<div class="loader"></div>

<div class="success">
    <svg viewBox="0 0 52 52">
        <circle cx="26" cy="26" r="25" stroke="#2ecc71" stroke-width="4" fill="none" />
        <path d="M16 26 L24 34 L38 20" />
    </svg>
    <div class="message">Your order is confirmed</div>
</div>

<script>
    setTimeout(() => {
        document.querySelector('.loader').style.display = 'none';
        const success = document.querySelector('.success');
        success.style.display = 'flex';

        setTimeout(() => {
            success.classList.add('shrink');
            document.querySelector('.message').style.opacity = 1;
        }, 1000);
    }, 3000);
</script>

</body>
</html>
