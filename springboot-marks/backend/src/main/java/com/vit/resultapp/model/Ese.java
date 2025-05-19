package com.vit.resultapp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "ese")
public class Ese {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private Integer studentId;
    private Integer sub1, sub2, sub3, sub4;

    public Integer getSub4() {
        return sub4;
    }

    public void setSub4(Integer sub4) {
        this.sub4 = sub4;
    }

    public Integer getSub3() {
        return sub3;
    }

    public void setSub3(Integer sub3) {
        this.sub3 = sub3;
    }

    public Integer getSub2() {
        return sub2;
    }

    public void setSub2(Integer sub2) {
        this.sub2 = sub2;
    }

    public Integer getSub1() {
        return sub1;
    }

    public void setSub1(Integer sub1) {
        this.sub1 = sub1;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    // getters/setters
}
