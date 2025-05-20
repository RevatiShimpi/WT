// src/main/java/com/example/vitresults/dto/StudentResponseDto.java
package com.example.vit_results.dto;

import java.util.List;

public class StudentResponseDto {
    private Long id;
    private String rollNo;
    private String name;
    private List<SubjectResultDto> subjects;
    private double overall;

    public StudentResponseDto(Long id, String rollNo, String name,
                              List<SubjectResultDto> subjects, double overall) {
        this.id = id; this.rollNo = rollNo; this.name = name;
        this.subjects = subjects; this.overall = overall;
    }
    // Getters
    public Long getId() { return id; }
    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public List<SubjectResultDto> getSubjects() { return subjects; }
    public double getOverall() { return overall; }

// Setters
    public void setId(Long id) { this.id = id; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public void setName(String name) { this.name = name; }
    public void setSubjects(List<SubjectResultDto> subjects) { this.subjects = subjects; }
    public void setOverall(double overall) { this.overall = overall; }
}