package com.example.vit_results.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="Students")
public class Student {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @Column(name="roll_no", nullable=false, unique=true)
  private String rollNo;
  @Column(nullable=false)
  private String name;

  @OneToMany(mappedBy="student", cascade=CascadeType.ALL, orphanRemoval=true)
  private List<Mse> mse;

  @OneToMany(mappedBy="student", cascade=CascadeType.ALL, orphanRemoval=true)
  private List<Ese> ese;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRollNo() {
    return rollNo;
  }

  public void setRollNo(String rollNo) {
    this.rollNo = rollNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Mse> getMse() {
    return mse;
  }

  public void setMse(List<Mse> mse) {
    this.mse = mse;
  }

  public List<Ese> getEse() {
    return ese;
  }

  public void setEse(List<Ese> ese) {
    this.ese = ese;
  }

  
}
