// src/main/java/com/example/vitresults/dto/SubjectResultDto.java
package com.example.vit_results.dto;

public class SubjectResultDto {
    private String subject;
    private double mse;
    private double ese;
    private double total;

    public SubjectResultDto(String subject, double mse, double ese, double total) {
        this.subject = subject;
        this.mse = mse;
        this.ese = ese;
        this.total = total;
    }
    // Getters
    public String getSubject() { return subject; }
    public double getMse() { return mse; }
    public double getEse() { return ese; }
    public double getTotal() { return total; }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setMse(double mse) {
        this.mse = mse;
    }
    public void setEse(double ese) {
        this.ese = ese;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    
}
