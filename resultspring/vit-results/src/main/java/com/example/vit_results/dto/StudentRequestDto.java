// src/main/java/com/example/vitresults/dto/StudentRequestDto.java
package com.example.vit_results.dto;

import java.util.List;

public class StudentRequestDto {
    private String rollNo;
    private String name;
    private List<SubjectMarkDto> mse;
    private List<SubjectMarkDto> ese;

    // Getters + Setters
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<SubjectMarkDto> getMse() { return mse; }
    public void setMse(List<SubjectMarkDto> mse) { this.mse = mse; }
    public List<SubjectMarkDto> getEse() { return ese; }
    public void setEse(List<SubjectMarkDto> ese) { this.ese = ese; }
}
