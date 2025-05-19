package com.vit.resultapp.repository;
import com.vit.resultapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student,Integer> {}
