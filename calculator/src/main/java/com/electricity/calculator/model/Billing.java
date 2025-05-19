package com.electricity.calculator.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;


import java.sql.Timestamp;
import com.electricity.calculator.model.Consumer;

@Entity
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int units;
    private double amount;
    private Timestamp billingDate;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Timestamp billingDate) {
        this.billingDate = billingDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
// getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
