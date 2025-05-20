<?php
require '../db.php';

$data = json_decode(file_get_contents("php://input"));
$consumer_id = $data->consumer_id;
$units = $data->units;

function calculateBill($units) {
    $amount = 0;
    if ($units <= 50) {
        $amount = $units * 3.5;
    } elseif ($units <= 150) {
        $amount = 50 * 3.5 + ($units - 50) * 4;
    } elseif ($units <= 250) {
        $amount = 50 * 3.5 + 100 * 4 + ($units - 150) * 5.2;
    } else {
        $amount = 50 * 3.5 + 100 * 4 + 100 * 5.2 + ($units - 250) * 6.5;
    }
    return $amount;
}

$amount = calculateBill($units);

$sql = "INSERT INTO billing (consumer_id, units, amount) VALUES ($consumer_id, $units, $amount)";
echo json_encode(["success" => $conn->query($sql), "amount" => $amount]);
?>
