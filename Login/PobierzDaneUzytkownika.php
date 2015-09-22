<?php
	$con=mysqli_connect("31.170.160.101","a5193672_raqbiel","1raymer0","a5193672_logmenu");

	$login = $_Post["login"];
	$haslo = $_Post["haslo"];

	$statement = mysqli_prepare($con, "SELECT * FROM Uzytkownik WHERE "login = ? AND haslo = ?");
	mysqli_stmt_bind_param($statement, "ss" $login, $haslo);
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $userID, $nazwa, $login, $haslo, $email);
	
	$uzytkownik = array();
	
	while(mysqli_stmt+fetch($statement)){
		$uzytkownik[nazwa] = $nazwa;	
		$uzytkownik[login] = $login;	
		$uzytkownik[haslo] = $haslo;	
		$uzytkownik[email] = $email;	


	}
		echo json_encode($uzytkownik);
	
	mysqli_stmt_close($statement);

	mysqli_close($con);