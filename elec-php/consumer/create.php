<?php
require '../db.php';

$data = json_decode(file_get_contents("php://input"));

$name = $data->name;
$address = $data->address;

$sql = "INSERT INTO consumer (name, address) VALUES ('$name', '$address')";
echo json_encode(["success" => $conn->query($sql)]);
?>
