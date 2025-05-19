package com.electricity.calculator.repository;


import com.electricity.calculator.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import com.electricity.calculator.model.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {
    Consumer save(Consumer consumer);
}
