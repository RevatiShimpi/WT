package com.example.vit_results.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vit_results.model.Ese;

public interface EseRepository     extends JpaRepository<Ese,Long> {
  List<Ese> findByStudentId(Long studentId);
}