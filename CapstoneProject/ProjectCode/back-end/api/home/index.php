<?php

	header('Access-Control-Allow-Origin: *');
	header('Content-Type: application/json');
	include("../db.php");

		if($_GET['user'] && $_GET['user'] !== -1) {
			// echo 'There is a GET variable named user...';

			$getPostsSQL = "SELECT count(item_ID) as count, sum(price) as sum FROM `inventory` WHERE user_ID = :u";
			$getPostsQuery = $dbh->prepare($getPostsSQL);
			$getPostsQuery->execute(array(
				":u" => $_GET['user']
			));

			$postArray = array();
			$postArray['data'] = array();

			while($theRow = $getPostsQuery->fetch(PDO::FETCH_ASSOC)){
				extract($theRow);

				if(!$count){
					$count = 0.0;
				}

				$postItem = array(
					'count' => $count,
					'sum' => $sum
				);

				array_push($postArray['data'], $postItem);
			}
			echo json_encode($postArray['data']);
		}
?>

