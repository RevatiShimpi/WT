<?php
require '../db.php';

$result = $conn->query("SELECT * FROM consumer");

$consumers = [];
while ($row = $result->fetch_assoc()) {
    $consumers[] = $row;
}
echo json_encode($consumers);
?>
