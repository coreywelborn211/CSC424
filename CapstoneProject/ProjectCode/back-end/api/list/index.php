<?php

	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');
	include("../db.php");

		if($_GET['user'] && $_GET['user'] !== -1) {
			// echo 'There is a GET variable named user...';

			$getPostsSQL = "select * from inventory where user_ID = :u";
			$getPostsQuery = $dbh->prepare($getPostsSQL);
			$getPostsQuery->execute(array(
				":u" => $_GET['user']
			));

			$postArray = array();
			$postArray['data'] = array();

			while($theRow = $getPostsQuery->fetch(PDO::FETCH_ASSOC)){
				extract($theRow);

				$postItem = array(
					'user_ID' => $user_ID,
					'item_ID' => $item_ID,
					'item_name' => $item_name,
					'qty' => $qty,
					'price' => $price,
					'dept' => $dept
				);

				array_push($postArray['data'], $postItem);
			}
			echo json_encode($postArray['data']);
		}
?>

