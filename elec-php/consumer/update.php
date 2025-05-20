<?php
require '../db.php';

$data = json_decode(file_get_contents("php://input"));

$id = $data->id;
$name = $data->name;
$address = $data->address;

$sql = "UPDATE consumer SET name='$name', address='$address' WHERE id=$id";
echo json_encode(["success" => $conn->query($sql)]);
?>
