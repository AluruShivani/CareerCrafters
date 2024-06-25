package com.example.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vacancies") // Corrected table name
public class Vacancies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancyId")
    private int vacancyId;

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @Column(name = "postDate")
    private String postDate; // Changed to LocalDate

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "requirements")
    private String requirements;

    @Column(name = "noOfVacancies")
    private int noOfVacancies;

    @Column(name = "openDate")
    private String openDate; // Changed to LocalDate

    @Column(name = "closeDate")
    private String closeDate; // Changed to LocalDate
}
