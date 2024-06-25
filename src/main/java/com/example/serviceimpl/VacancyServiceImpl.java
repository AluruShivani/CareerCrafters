package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Vacancies;
import com.example.repository.VacancyRepository;
import com.example.service.VacancyService;

@Service
public class VacancyServiceImpl implements VacancyService{
	private final VacancyRepository vacancyrepository;
	
	@Autowired
	public VacancyServiceImpl(VacancyRepository vacancyrepository) {
		this.vacancyrepository=vacancyrepository;
	}

	@Override
	public Vacancies saveVacancies(Vacancies vacancy) {
		return vacancyrepository.save(vacancy);
	}

	@Override
	public List<Vacancies> getAllVacancies() {
		return vacancyrepository.findAll();
	}

	@Override
	public boolean isVancancyExit(int vacancyId) {
		return vacancyrepository.existsById(vacancyId);
	}

	@Override
	public Vacancies getVacancyById(int vacancyId) {
		return vacancyrepository.findById(vacancyId).orElse(null);
	}

	@Override
	public boolean updateVacancy(Vacancies vacancies) {
		if(isVancancyExit(vacancies.getVacancyId())) {
			vacancyrepository.save(vacancies);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteVacancy(int vacancyId) {
		if(vacancyrepository.existsById(vacancyId)) {
			vacancyrepository.deleteById(vacancyId);
			return true;
		}
		return false;
	}

	@Override
	public List<Vacancies> getVacanciesByCompanyId(int companyId) {
		// TODO Auto-generated method stub
		return vacancyrepository.findByCompanyId(companyId);
	}

	
}
