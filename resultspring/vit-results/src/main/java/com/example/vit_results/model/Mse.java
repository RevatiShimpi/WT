package com.example.vit_results.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="MSE")
public class Mse {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String subject;
  private double marks;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="student_id", nullable=false)
  private Student student;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public double getMarks() {
    return marks;
  }

  public void setMarks(double marks) {
    this.marks = marks;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  /* getters + setters */
  
}
