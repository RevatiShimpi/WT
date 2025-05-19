package com.electricity.calculator.controller;

import com.electricity.calculator.model.Consumer;          // your entity
import com.electricity.calculator.model.Billing;
import com.electricity.calculator.repository.ConsumerRepository;
import com.electricity.calculator.repository.BillingRepository;
import com.electricity.calculator.util.BillCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;              // <-- correct Timestamp
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BillingController {

    @Autowired
    private ConsumerRepository consumerRepo;

    @Autowired
    private BillingRepository billingRepo;

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateBill(@RequestBody Map<String, String> data) {
        String name    = data.get("name");
        String email   = data.get("email");
        String address = data.get("address");
        int units      = Integer.parseInt(data.get("units"));

        // 1️⃣ Save consumer
        Consumer consumer = new Consumer();
        consumer.setName(name);
        consumer.setEmail(email);
        consumer.setAddress(address);
        consumer = consumerRepo.save(consumer);

        // 2️⃣ Calculate amount
        double amount = BillCalculator.calculateBill(units);

        // 3️⃣ Create billing record
        Billing bill = new Billing();
        bill.setConsumer(consumer);
        bill.setUnits(units);
        bill.setAmount(amount);
        bill.setBillingDate(new Timestamp(System.currentTimeMillis()));   // correct Timestamp

        billingRepo.save(bill);

        // 4️⃣ Return only the amount
        return ResponseEntity.ok(Map.of("amount", amount));
    }
}
