<?php
require '../db.php';

$data = json_decode(file_get_contents("php://input"));
$id = $data->id;

$sql = "DELETE FROM consumer WHERE id=$id";
echo json_encode(["success" => $conn->query($sql)]);
?>
