package com.example.vit_results.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vit_results.model.Mse;

public interface MseRepository     extends JpaRepository<Mse,Long> {
  List<Mse> findByStudentId(Long studentId);
}
