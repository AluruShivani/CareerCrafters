package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "jobapply")
public class JobApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applyId")
    private int applyId;

    @Column(name = "applyDate")
    private Date applyDate;
    
    @Column(name = "finalScore")
    private String finalScore;
    
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name="userId")
    private JobSeekers jobseekers;

    @ManyToOne
    @JoinColumn(name = "vacancyId")
    private Vacancies vacancies;
}