package com.vit.resultapp.controller;

import com.vit.resultapp.dto.ResultDto;
import com.vit.resultapp.model.*;
import com.vit.resultapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ResultController {

  @Autowired StudentRepository studentRepo;
  @Autowired MseRepository mseRepo;
  @Autowired EseRepository eseRepo;

  @PostMapping("/students")
  public Student addStudent(@RequestBody Student s) {
    return studentRepo.save(s);
  }

  @PostMapping("/marks")
  public String addMarks(@RequestBody ResultDto dto) {
    Mse mse = new Mse();
    mse.setStudentId(dto.studentId);
    mse.setSub1(dto.mse1); mse.setSub2(dto.mse2);
    mse.setSub3(dto.mse3); mse.setSub4(dto.mse4);
    mseRepo.save(mse);

    Ese ese = new Ese();
    ese.setStudentId(dto.studentId);
    ese.setSub1(dto.ese1); ese.setSub2(dto.ese2);
    ese.setSub3(dto.ese3); ese.setSub4(dto.ese4);
    eseRepo.save(ese);

    return "OK";
  }

  @GetMapping("/results")
  public List<ResultDto> getResults() {
    return studentRepo.findAll().stream().map(s -> {
      Mse mse = mseRepo.findByStudentId(s.getId());
      Ese ese = eseRepo.findByStudentId(s.getId());

      // Use primitives (or unbox safely) and default to 0
      int m1 = (mse != null ? mse.getSub1() : 0);
      int m2 = (mse != null ? mse.getSub2() : 0);
      int m3 = (mse != null ? mse.getSub3() : 0);
      int m4 = (mse != null ? mse.getSub4() : 0);

      int e1 = (ese != null ? ese.getSub1() : 0);
      int e2 = (ese != null ? ese.getSub2() : 0);
      int e3 = (ese != null ? ese.getSub3() : 0);
      int e4 = (ese != null ? ese.getSub4() : 0);

      // Build DTO
      ResultDto r = new ResultDto();
      r.studentId = s.getId();
      r.name      = s.getName();
      r.rollNo    = s.getRollNo();

      r.mse1 = m1; r.mse2 = m2; r.mse3 = m3; r.mse4 = m4;
      r.ese1 = e1; r.ese2 = e2; r.ese3 = e3; r.ese4 = e4;

      // Compute finals
      r.final1 = m1 * 0.3 + e1 * 0.7;
      r.final2 = m2 * 0.3 + e2 * 0.7;
      r.final3 = m3 * 0.3 + e3 * 0.7;
      r.final4 = m4 * 0.3 + e4 * 0.7;
      r.total  = r.final1 + r.final2 + r.final3 + r.final4;

      return r;
    }).collect(Collectors.toList());
  }


}
