<?php
require '../db.php';

$data = json_decode(file_get_contents("php://input"));
$consumer_id = $data->consumer_id;

$sql = "SELECT * FROM billing WHERE consumer_id = $consumer_id ORDER BY billing_date DESC";
$result = $conn->query($sql);

$bills = [];
while ($row = $result->fetch_assoc()) {
    $bills[] = $row;
}

echo json_encode($bills);
?>
