package com.vit.resultapp.repository;
import com.vit.resultapp.model.Mse;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MseRepository extends JpaRepository<Mse,Integer> {
    Mse findByStudentId(Integer studentId);
}
