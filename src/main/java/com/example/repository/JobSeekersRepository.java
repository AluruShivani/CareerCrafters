package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.entity.JobSeekers;
import com.example.entity.Question;

@Repository
public interface JobSeekersRepository extends JpaRepository<JobSeekers, Integer> {
	JobSeekers findByEmailAndPassword(String email, String password);

	

	
}
