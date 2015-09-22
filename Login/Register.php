<?php
	$con=mysqli_connect("31.170.160.101","a5193672_raqbiel","1raymer0","a5193672_logmenu");
	$nazwa = $_Post["nazwa"];
	$login = $_Post["login"];
	$haslo = $_Post["haslo"];
	$email = $_Post["email"];

	$statement = mysli_prepare($con, "INSERT INTO Uzytkownik (nazwa, login, haslo, email) VALUES (?, ?, ?, ?) ");
	mysqli_stmt_bind_param($statement, "siss", $nazwa, $login, $haslo, $email);

	mysqli_stmt_close($statement);


	mysqli_close($con);

?>