package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.SelectedCandidates;

@Service
public interface SelectedCandidatesService {
	SelectedCandidates saveSelectedCandidates(SelectedCandidates selectedcadidates);
	List<SelectedCandidates> getAllSelectedCandidates();

}
