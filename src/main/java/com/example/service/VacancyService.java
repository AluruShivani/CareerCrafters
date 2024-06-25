package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Vacancies;

@Service
public interface VacancyService {
	Vacancies saveVacancies(Vacancies vacancy);
	List<Vacancies> getAllVacancies();
	
	boolean isVancancyExit(int vacancyId);
	Vacancies getVacancyById(int vacancyId);
	boolean updateVacancy(Vacancies vacancies);
	boolean deleteVacancy(int vacancyId);
	List<Vacancies> getVacanciesByCompanyId(int companyId);
	
}
