<?php

	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');
	include("../db.php");

		if($_GET['username'] && $_GET['password']) {
			$getPostsSQL = "select user_ID from users where email = :e and password = :p";
			$getPostsQuery = $dbh->prepare($getPostsSQL);
			$getPostsQuery->execute(array(
				':e' => $_GET['username'],
				':p' => $_GET['password']
			));

			$count = $getPostsQuery->rowCount();
			if($count > 0){
				$id = $getPostsQuery->fetchColumn();
				echo json_encode(intval($id));
			} else {
				echo json_encode(-1);
			}
			
		}

?>

