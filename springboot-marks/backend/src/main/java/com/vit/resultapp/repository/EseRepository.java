package com.vit.resultapp.repository;
import com.vit.resultapp.model.Ese;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EseRepository extends JpaRepository<Ese,Integer> {
    Ese findByStudentId(Integer studentId);
}
