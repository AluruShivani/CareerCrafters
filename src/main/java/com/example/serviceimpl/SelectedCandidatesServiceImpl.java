package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.SelectedCandidates;
import com.example.repository.SelectedCandidatesRepository;
import com.example.service.SelectedCandidatesService;

@Service
public class SelectedCandidatesServiceImpl implements SelectedCandidatesService{
	public final SelectedCandidatesRepository selectedcandidtaerepository;
	
	@Autowired
	public SelectedCandidatesServiceImpl(SelectedCandidatesRepository selectedcandidtaerepository) {
	this. selectedcandidtaerepository=selectedcandidtaerepository;
	}

	@Override
	public SelectedCandidates saveSelectedCandidates(SelectedCandidates selectedcadidates) {
		return selectedcandidtaerepository.save(selectedcadidates);
	}

	@Override
	public List<SelectedCandidates> getAllSelectedCandidates() {
		return selectedcandidtaerepository.findAll();
	}
}
