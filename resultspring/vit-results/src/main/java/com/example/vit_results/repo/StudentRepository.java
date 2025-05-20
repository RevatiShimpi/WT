package com.example.vit_results.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vit_results.model.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {}

