<!-- sign.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
<style>
body {
	font-family: 'Roboto', sans-serif;
	background: linear-gradient(135deg, #667eea, #764ba2);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
	color: #ffffff;
}

.login-container {
	width: 300px;
	background-color: rgba(255, 255, 255, 0.9);
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	text-align: center;
}

.login-container h2 {
	margin-bottom: 30px;
	color: #333333;
}

.login-form {
	display: flex;
	flex-direction: column;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	font-weight: bold;
	font-size: 16px;
	display: block;
	margin-bottom: 10px;
	color: #333333;
}

.form-group input[type="text"], .form-group input[type="password"] {
	width: 100%;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #cccccc;
	border-radius: 5px;
	background-color: #f8f8f8;
	transition: border-color 0.3s ease-in-out;
}

.form-group input[type="text"]:focus, .form-group input[type="password"]:focus
	{
	outline: none;
	border-color: #667eea;
}

.form-group input[type="submit"] {
	width: 100%;
	padding: 12px;
	background-color: #764ba2;
	border: none;
	border-radius: 5px;
	color: #ffffff;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s ease-in-out;
}

.form-group input[type="submit"]:hover {
	background-color: #5e3d89;
}

.form-group input[type="submit"]:focus {
	outline: none;
}
</style>
</head>
<body>
	<div class="login-container">
		<h2>Login</h2>
		<form class="login-form" action="${pageContext.request.contextPath}/user/signIn" method="post">
			<div class="form-group">
				<label for="username">Username</label> <input type="text" id="username" name="username" placeholder="Enter your username" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password" id="password" name="password" placeholder="Enter your password" required>
			</div>
			<div class="form-group">
				<input type="submit" value="Login">
			</div>
		</form>
	</div>
</body>
</html>
