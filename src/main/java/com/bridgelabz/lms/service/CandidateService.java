package com.bridgelabz.lms.service;

import com.bridgelabz.lms.exception.CandidateException;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAll() {
        List<Candidate> getCandidates = candidateRepository.findAll();
        if (getCandidates.isEmpty()) {
            throw new CandidateException(HttpStatus.NOT_FOUND, "There is not data added yet!!");
        } else
            return getCandidates;
    }

    @Override
    public Candidate getById(long candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            return candidate.get();
        } else
            throw new CandidateException(HttpStatus.NOT_FOUND, "This Id is not found! ");
    }

    @Override
    public List<Candidate> hiredCondidate(String status) {
        List<Candidate>candidate = candidateRepository.findCandidateByStatus(status);
        if (candidate.isEmpty()) {
            throw new CandidateException(HttpStatus.NOT_FOUND, "There are no candidate got hired yet!!");
        }
        return candidate;
    }
}
