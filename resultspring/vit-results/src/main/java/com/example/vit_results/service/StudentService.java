// src/main/java/com/example/vitresults/service/StudentService.java
package com.example.vit_results.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vit_results.dto.StudentRequestDto;
import com.example.vit_results.dto.StudentResponseDto;
import com.example.vit_results.dto.SubjectResultDto;
import com.example.vit_results.model.Ese;
import com.example.vit_results.model.Mse;
import com.example.vit_results.model.Student;
import com.example.vit_results.repo.EseRepository;
import com.example.vit_results.repo.MseRepository;
import com.example.vit_results.repo.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepo;
    private final MseRepository mseRepo;
    private final EseRepository eseRepo;

    public StudentService(StudentRepository studentRepo,
                          MseRepository mseRepo,
                          EseRepository eseRepo) {
        this.studentRepo = studentRepo;
        this.mseRepo = mseRepo;
        this.eseRepo = eseRepo;
    }

    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto rq) {
        Student s = new Student();
        s.setRollNo(rq.getRollNo());
        s.setName(rq.getName());
        studentRepo.save(s);

        // save MSE
        rq.getMse().forEach(m -> {
            Mse e = new Mse();
            e.setSubject(m.getSubject());
            e.setMarks(m.getMarks());
            e.setStudent(s);
            mseRepo.save(e);
        });

        // save ESE
        rq.getEse().forEach(ei -> {
            Ese e = new Ese();
            e.setSubject(ei.getSubject());
            e.setMarks(ei.getMarks());
            e.setStudent(s);
            eseRepo.save(e);
        });

        return buildResponse(s.getId());
    }

    public List<StudentResponseDto> listAll() {
        return studentRepo.findAll().stream()
                .map(st -> buildResponse(st.getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }

    private StudentResponseDto buildResponse(Long studentId) {
        Student s = studentRepo.findById(studentId).orElseThrow();
        List<Mse> mses = mseRepo.findByStudentId(studentId);
        List<Ese> eses = eseRepo.findByStudentId(studentId);

        // map subject → mse,ese
        Map<String, Double> mapM = mses.stream()
            .collect(Collectors.toMap(Mse::getSubject, Mse::getMarks));
        Map<String, Double> mapE = eses.stream()
            .collect(Collectors.toMap(Ese::getSubject, Ese::getMarks));

        List<SubjectResultDto> results = new ArrayList<>();
        double sum = 0;
        for (String subj : mapM.keySet()) {
            double m = mapM.getOrDefault(subj, 0.0);
            double e = mapE.getOrDefault(subj, 0.0);
            double tot = Math.round((m*0.3 + e*0.7) * 100.0) / 100.0;
            sum += tot;
            results.add(new SubjectResultDto(subj, m, e, tot));
        }
        double overall = results.isEmpty() ? 0 : Math.round((sum/results.size())*100.0)/100.0;

        return new StudentResponseDto(
            s.getId(), s.getRollNo(), s.getName(), results, overall
        );
    }
}
